/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import static com.espol.concesionaria.PaginaPorTipoController.tipo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listas.ArrayListJR;
import modelo.Marca;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class PaginaPorMarcaController implements Initializable {
    @FXML
    private ImageView IVInicio;
    @FXML
    private Label volver;
    @FXML
    private HBox infoMarca;
    @FXML
    private FlowPane fpVehiculos;
    
    public static Marca marca;
    
    @FXML
    private Label lblMarca;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoMarca.getChildren().clear();
        lblMarca.setText(marca.getNombre());
        infoMarca.getChildren().add(lblMarca); 
        fpVehiculos.getChildren().clear();
        ArrayListJR<Vehiculo> ve=PaginaPrincipalController.vehiculos;
        ArrayListJR<Vehiculo> vehiculosPorMarca=PaginaPrincipalController.vehiculosPorMarca(ve, marca);
        PaginaPrincipalController.llenarVehiculosEnContenedor("Nuevo", fpVehiculos, vehiculosPorMarca);
        if(fpVehiculos.getChildren().isEmpty()){
            Label l = new Label("No se encontraron vehiculos");
            l.setAlignment(Pos.CENTER);
            l.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 10px; -fx-background-color: white; -fx-border-radius: 25px;");
            
            fpVehiculos.getChildren().add(l);
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
        volver.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAutosNuevos", 1129, 720);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
}
