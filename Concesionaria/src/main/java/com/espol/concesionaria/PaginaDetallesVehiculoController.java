/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import listas.ArrayListJR;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Justin Roldan
 */
public class PaginaDetallesVehiculoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label lblNombre;
    
    @FXML
    private Label lblDetalles;
    
    @FXML
    private Label lblEstado;
    
    @FXML
    private Label lblPrecio;
    
    @FXML
    private ImageView imgVehiculo;
    
    @FXML
    private ImageView avanzar;
    
    @FXML
    private ImageView retroceder;
   
    
    public static Vehiculo v;
    
    private int indice=0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1=null;
        try {
            ArrayListJR<String> rutas = v.getRutasFotos();
            img1 = new Image(new FileInputStream(App.pathImages+rutas.get(0)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        imgVehiculo.setImage(img1);
        lblNombre.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblPrecio.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblDetalles.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblEstado.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblNombre.setText(v.getMarca()+" "+v.getModelo());
        lblDetalles.setText(v.getAño() + "   "+v.getKilometraje()+" kms . "+v.getUbicacionActualVehiculo());
        lblPrecio.setText("$ "+v.getPrecio());
        lblEstado.setText(v.getUsadoONuevo());
        avanzar.setOnMouseClicked(e->{
            indice = (indice + 1) % v.getRutasFotos().size();
            Image img=null;
            try {
                ArrayListJR<String> rutas = v.getRutasFotos();
                img = new Image(new FileInputStream(App.pathImages+rutas.get(indice)));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            imgVehiculo.setImage(img);
        });

        retroceder.setOnMouseClicked(e->{
            indice = (indice - 1 + v.getRutasFotos().size()) % v.getRutasFotos().size();
            Image img=null;
            try {
                ArrayListJR<String> rutas = v.getRutasFotos();
                img = new Image(new FileInputStream(App.pathImages+rutas.get(indice)));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            imgVehiculo.setImage(img);
        });

    }    
    
}
