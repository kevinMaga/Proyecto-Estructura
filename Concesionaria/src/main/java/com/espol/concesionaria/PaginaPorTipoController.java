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
    
    private boolean llno=true;
    
    public static Tipo tipo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String dFoto;
        switch (tipo) {
            case AUTOS:
                dFoto="automovil.png";
                break;
            case PESADOS:
                dFoto="pesado.png";
                break;
            case MOTOS:
                dFoto="moto.png";
                break;
            case MAQUINARIAS:
                dFoto="maquinaria.png";
                break;
            default:
                dFoto="acuatico.png";
                break;
        }
        infoTipo.getChildren().clear();
        ImageView tipImg = null;
        try {
            FileInputStream f = new FileInputStream("src/main/resources/images/" + dFoto);
            Image img = new Image(f,200,140,true,true);
            tipImg = new ImageView(img);
            tipImg.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        infoTipo.setAlignment(Pos.CENTER_LEFT);
        infoTipo.setStyle("-fx-background-color: white;");
        Label ltipo = new Label(tipo.toString());
        ltipo.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 10px; -fx-background-color: white; -fx-border-radius: 3px;");
        ltipo.setAlignment(Pos.CENTER);
        infoTipo.getChildren().addAll(tipImg,ltipo);
        
        
        fpVehiculos.getChildren().clear();
        ArrayList<Vehiculo> vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, tipo);
            for(int i = 0; i < vehiculos.size(); i++){
                if(vehiculos.get(i).getUsadoONuevo().equals("Nuevo")){
                    llno=false;
                    VBox v = new VBox();
                    v.setAlignment(Pos.CENTER);
                    v.setStyle("-fx-background-color: white;");
                    Label lbl = new Label(vehiculos.get(i).getMarca() + " " + vehiculos.get(i).getModelo());
                    lbl.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 10px; -fx-background-color: white; -fx-border-radius: 3px;");
                    ImageView iv = null;
                    try {
                        FileInputStream f = new FileInputStream("src/main/resources/images/" + vehiculos.get(i).getRutaFoto());
                        Image img = new Image(f,200,140,true,true);
                        iv = new ImageView(img);
                        iv.setPreserveRatio(true);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    v.getChildren().addAll(iv,lbl);
                    fpVehiculos.getChildren().add(v);
                }
            }
        if(llno){
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
