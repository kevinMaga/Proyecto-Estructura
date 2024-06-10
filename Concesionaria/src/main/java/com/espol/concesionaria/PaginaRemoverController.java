/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import listas.ArrayListJR;
import modelo.Usuario;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Justin Roldan
 */
public class PaginaRemoverController implements Initializable {

    @FXML
    private Label LBVolver;
    @FXML
    private ImageView IVInicio;
    @FXML
    private VBox vehicleContainer;

    
    /**
     * Initializes the controller class.
     */
    private VBox contenedorParaImagenesUsuario(String ruta,String contenido1,String contenido2,String contenido3,Vehiculo veh){
        VBox v;
        v = new VBox();
        v.setPadding(new Insets(20,20,20,20));
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-background-color: white;");
        Label lbl = new Label(contenido1);
        lbl.setAlignment(Pos.CENTER);
        lbl.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 40px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        Label lbl1 = new Label(contenido2);
        lbl1.setAlignment(Pos.CENTER);
        lbl1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 36px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        Label lb2 = new Label(contenido3);
        lb2.setAlignment(Pos.CENTER);
        lb2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 40px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        ImageView iv=null;
        try {
            FileInputStream f = new FileInputStream(ruta);
            Image img =new Image(f,520,450,true,true);
            iv = new ImageView(img);
            iv.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        Button editButton = new Button("Eliminar");
        
        // Estilo del botón
        editButton.setStyle(
            "-fx-background-color: #4CAF50; " + // Fondo verde
            "-fx-text-fill: white; " + // Texto blanco
            "-fx-font-size: 16px; " + // Tamaño de fuente
            "-fx-font-weight: bold; " + // Fuente en negrita
            "-fx-padding: 10 20 10 20; " + // Relleno interno
            "-fx-background-radius: 5;" // Fondo redondeado
        );
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, 0.2)); 
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        editButton.setEffect(shadow);
        editButton.setOnAction(e->{
            PaginaAgregarVehiculoController.mostrarAlerta("Vehiculo Editado", "Se ha eliminado el vehiculo correctamente", Alert.AlertType.INFORMATION);
            Stage s = (Stage) LBVolver.getScene().getWindow();
            s.close();
            try {
                eliminarVehiculo(veh);
                
                App.abrirNuevaVentana("paginaAdministrador", 650, 600);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        v.getChildren().addAll(iv,lbl,lbl1,lb2,editButton);
        return v;
    }
    
    public void eliminarVehiculo(Vehiculo v) throws IOException {
        

        // Ruta al archivo de vehículos
        File archivoVehiculos = new File(App.pathFiles + "vehiculos.txt");

        // Leer todas las líneas del archivo de vehículos y excluir la del vehículo a eliminar
        ArrayListJR<String> lineasVehiculos = new ArrayListJR<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoVehiculos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] info = linea.split(",");
                if (!info[0].equals(v.getPlaca())) {
                    lineasVehiculos.add(linea);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Escribir las líneas filtradas de nuevo en el archivo de vehículos
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoVehiculos))) {
            for (int i = 0; i < lineasVehiculos.size(); i++) {
                String linea = lineasVehiculos.get(i);
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Ruta al archivo de usuarios
        File archivoUsuarios = new File(App.pathFiles + "usuarios.txt");

        // Leer todas las líneas del archivo de usuarios y eliminar la placa correspondiente
        ArrayListJR<String> lineasUsuarios = new ArrayListJR<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] info = linea.split(",");
                if (info.length > 4) {
                    String[] placas = info[4].split(";");
                    String nuevasPlacas = new String();
                    for (int j = 0; j < placas.length; j++) {
                        if (!placas[j].equals(v.getPlaca())) {
                            if (nuevasPlacas.length() > 0) {
                                nuevasPlacas+=(";");
                            }
                            nuevasPlacas+=placas[j];
                        }
                    }
                    info[4] = nuevasPlacas;
                    linea = String.join(",", info);
                }
                lineasUsuarios.add(linea);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Escribir las líneas actualizadas de nuevo en el archivo de usuarios
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios))) {
            for (int i = 0; i < lineasUsuarios.size(); i++) {
                String linea = lineasUsuarios.get(i);
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        // Eliminar el vehículo de la lista de vehículos en la aplicación
        ArrayListJR<Vehiculo> vehiculos = PaginaPrincipalController.vehiculos;
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).equals(v)) {
                PaginaPrincipalController.cargarVehiculos();
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         // Cargar información del vehículo para el usuario que ha iniciado sesión
        Usuario user = InicioSesionController.usuario;
        ArrayListJR<Vehiculo> vehiculos1 = PaginaPrincipalController.vehiculos;
        ArrayListJR<Vehiculo> vehiculosUser = new ArrayListJR<>();
        for(int i=0;i<user.getPlacas().size();i++){  
            for(int j=0;j<vehiculos1.size();j++){
                if(user.getPlacas().get(i).equals(vehiculos1.get(j).getPlaca())){
                    vehiculosUser.add(vehiculos1.get(j));
                    Vehiculo a = vehiculos1.get(j);
                    VBox v =contenedorParaImagenesUsuario(App.pathImages+a.getRutasFotos().get(0),a.getMarca()+" "+a.getModelo()
                    ,a.getAño() + "   "+a.getKilometraje()+" kms . "+a.getUbicacionActualVehiculo()+"\n"
                    +a.getUsadoONuevo(),"$ "+a.getPrecio(),a);
                    vehicleContainer.getChildren().add(v);
                }
            }
        }
        
        
        IVInicio.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPrincipal", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        LBVolver.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAdministrador", 650, 600);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
    }     
    
}
