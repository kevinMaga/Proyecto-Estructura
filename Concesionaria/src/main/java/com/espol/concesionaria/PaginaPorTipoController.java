/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Marca;
import modelo.Tipo;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Cesar
 */
public class PaginaPorTipoController implements Initializable {
    @FXML
    private ImageView IVInicio;
    @FXML
    private Label volver;
    @FXML
    private HBox infoTipo;
    @FXML
    private FlowPane fpVehiculos;

    public static Tipo tipo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoTipo.getChildren().clear();
        ImageView tipImg = null;
        try {
            FileInputStream f = new FileInputStream("src/main/resources/images/" + tipo.getFoto());
            Image img = new Image(f,200,140,true,true);
            tipImg = new ImageView(img);
            tipImg.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        infoTipo.setAlignment(Pos.CENTER_LEFT);
        infoTipo.setStyle("-fx-background-color: white;");
        Label ltipo = new Label(tipo.getNombre());
        ltipo.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 10px; -fx-background-color: white; -fx-border-radius: 3px;");
        ltipo.setAlignment(Pos.CENTER);
        infoTipo.getChildren().addAll(tipImg,ltipo);  
        PaginaAutosUsadosController.llenarVehiculosEnContenedor("Nuevo", tipo, fpVehiculos);
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
                App.abrirNuevaVentana("paginaAutosNuevos", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
}
