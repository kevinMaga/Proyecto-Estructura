
package com.espol.concesionaria;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import modelo.Marca;
import modelo.Tipo;
import modelo.Vehiculo;


/**
 * FXML Controller class
 * estop esnubsunsduasuasdadssdadsasddasads
 * @author Justin Roldan
 */
public class PaginaPrincipalController implements Initializable {
    
    public static ArrayList<Vehiculo> vehiculos=new ArrayList<>();
    public static ArrayList<Marca> marcas = new ArrayList<>();
    
    @FXML
    private HBox nuevos;
    
    @FXML
    private HBox usados;

    @FXML
    private ComboBox cmbHead;
    
    @FXML
    private ComboBox cmbTipo;
    
    @FXML
    private ComboBox cmbMarca;
    
    @FXML
    private ComboBox cmbModelo;
    
    @FXML
    private ComboBox cmbPrecioDesde;
    
    @FXML
    private ComboBox cmbPrecioHasta;
    
    @FXML
    private ComboBox cmbAñoDesde;
    
    @FXML
    private ComboBox cmbAñoHasta;
    
    @FXML
    private TextField TFHead;
    
    @FXML
    private ImageView IVBuscar;
    
    @FXML
    private Button btnBuscar;
    
    @FXML
    private Label LBUser;
    
    @FXML
    private Label LBVende;
    
    @FXML
    private FlowPane fpMasVendidos;
    
    public static void llenarListaMarcas(){
        marcas=new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/files/marcas.txt"))){
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                String[] info = linea.split(",");
                Marca m = new Marca(info[0],info[1]);
                if(!marcas.contains(m)){
                    marcas.add(m);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void cargarVehiculos(){
        ArrayList<Vehiculo> vehiculos1 = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/files/vehiculos.txt"))){
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                String[] info = linea.split(",");
                String[] lista=info[9].split(";");
                ArrayList<String> accidentesOServicios = new ArrayList<>();
                for(int i=0;i<lista.length;i++){
                    accidentesOServicios.add(lista[i]);
                }
                vehiculos1.add(new Vehiculo(Double.valueOf(info[0]),info[1],info[2],Integer.valueOf(info[3]),info[4],
                info[5],info[6],info[7],info[8],accidentesOServicios,Tipo.valueOf(info[10]),Integer.valueOf(info[11]),info[12],info[13]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        vehiculos=vehiculos1;
        
    }
    
    private void llenarCuadroDeFiltro(){
        cmbTipo.getItems().addAll(Tipo.ACUATICOS,Tipo.AUTOS,Tipo.MAQUINARIAS,Tipo.MOTOS,Tipo.PESADOS);        
        for(int i=0;i<vehiculos.size();i++){
            Vehiculo v = vehiculos.get(i);
            if(!vehiculos.contains(v.getModelo())){
                cmbModelo.getItems().add(v.getModelo());
            }
        }
        for(int i=0;i<marcas.size();i++){
            cmbMarca.getItems().add(marcas.get(i));
        }
        for(int i=0;i<31;i++){
            cmbPrecioDesde.getItems().add(5000*i);
            cmbPrecioHasta.getItems().add(5000*i);
        }
        for(int i=2005;i<2025;i++){
            cmbAñoDesde.getItems().add(i);
            cmbAñoHasta.getItems().add(i);
        }
    }
    
    public static ArrayList<Vehiculo> vehiculosPorTipo(ArrayList<Vehiculo> vehiculos,Tipo t){
        ArrayList<Vehiculo> array = new ArrayList<>();
        for(int i=0;i<vehiculos.size();i++){
            Vehiculo v = vehiculos.get(i);
            if(v.getTipo().equals(t)){
                array.add(v);
            }
        }
        return array;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarVehiculos();
        llenarListaMarcas();
        llenarCuadroDeFiltro();
        LBUser.setText(InicioSesionController.usuario.getUsuario());
        cmbHead.getItems().addAll(Tipo.AUTOS,Tipo.ACUATICOS,Tipo.MAQUINARIAS,Tipo.MOTOS,Tipo.PESADOS);
        nuevos.setOnMouseClicked(e ->{
            Stage ventanaActual = (Stage) nuevos.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAutosNuevos", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        usados.setOnMouseClicked(e ->{
            Stage ventanaActual = (Stage) usados.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAutosUsados", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        //Vehiculos mas vendidos en pagina principal
        ArrayList<Vehiculo> autos = vehiculosPorTipo(vehiculos,Tipo.AUTOS);
        Collections.sort(autos,(a1,a2)->Integer.compare(a2.getCantidadVentas(), a1.getCantidadVentas()));
        for(int i=0;i<5;i++){
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setStyle("-fx-background-color: white;");
            Label lbl = new Label(autos.get(i).getMarca()+" "+autos.get(i).getModelo());
            Label lbl1 = new Label(String.valueOf(autos.get(i).getAño()));
            lbl.setAlignment(Pos.CENTER);
            lbl1.setAlignment(Pos.CENTER);
            lbl.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
            lbl1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
            ImageView iv=null;
            try {
                FileInputStream f = new FileInputStream("src/main/resources/images/"+autos.get(i).getRutaFoto());
                Image img =new Image(f,220,150,true,true);
                iv = new ImageView(img);
                iv.setPreserveRatio(true);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            v.getChildren().addAll(iv,lbl,lbl1);
            fpMasVendidos.getChildren().add(v);
        }
        //Boton buscar por filtros
        btnBuscar.setOnMouseClicked(e->{
            Stage s =(Stage)btnBuscar.getScene().getWindow();
            s.close();
            try {
                App.abrirNuevaVentana("paginaAutosUsados", 929, 628);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    
}
