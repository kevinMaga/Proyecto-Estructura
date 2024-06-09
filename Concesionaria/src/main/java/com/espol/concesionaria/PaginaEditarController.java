/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Justin Roldan
 */
public class PaginaEditarController implements Initializable {

    @FXML
    private Label LBVolver;
    @FXML
    private ImageView IVInicio;
    @FXML
    private VBox vehicleContainer; // Label to show the vehicle name

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

         // Cargar información del vehículo para el usuario que ha iniciado sesión
        try {
            Usuario user = InicioSesionController.usuario;
            for (int i=0; i<user.getPlacas().size(); i++){
                String userPlaca = user.getPlacas().get(i);
                if (userPlaca != null) {
                loadVehicleInfo(userPlaca);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    

  private void loadVehicleInfo(String placa) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("vehiculos.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts[0].equals(placa)) {
                String vehicleName = parts[2]; // Asumiendo que el nombre está en la tercera columna
                String vehicleImageFile = parts[14]; // Asumiendo que la ruta de la imagen está en la decimoquinta columna

                // Crear Label y ImageView dinámicamente
                Label vehicleNameLabel = new Label(vehicleName);
                ImageView vehicleImageView = new ImageView(new Image("file:" + vehicleImageFile));
                vehicleImageView.setFitHeight(100); // Ajustar el tamaño de la imagen
                vehicleImageView.setFitWidth(100);

                // Agregar el Label y el ImageView al contenedor
                vehicleContainer.getChildren().addAll(vehicleNameLabel, vehicleImageView);
                break;
            }
        }
        br.close();
    }
    
}
