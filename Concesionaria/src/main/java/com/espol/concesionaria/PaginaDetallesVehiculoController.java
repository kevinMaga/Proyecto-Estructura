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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import listas.ArrayListCircular;
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
    private Label lblDetalles1;
    @FXML
    private Label lblDetalles2;
    
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
    
    @FXML
    private ImageView IVAS;
   
    
    public static Vehiculo v;
    
    private int indice=0;
    @FXML
    private HBox head;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1=null;
        try {
            ArrayListCircular<String> rutas = v.getRutasFotos();
            img1 = new Image(new FileInputStream(App.pathImages+rutas.get(0)));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        imgVehiculo.setImage(img1);
        lblNombre.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblPrecio.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblDetalles.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblEstado.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblDetalles1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblDetalles2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        lblNombre.setText(v.getMarca()+" "+v.getModelo());
        lblDetalles.setText(v.getAño() + "   "+v.getKilometraje()+" kms . "+v.getUbicacionActualVehiculo());
        lblDetalles1.setText("Motor: "+v.getMotor()+"\n"+"Peso: "+v.getPeso()+"\n"+"Transmisión: "+v.getTransmisión());
        lblDetalles2.setText("Accidentes o servicios: ");
        lblPrecio.setText("$ "+v.getPrecio());
        int nAS = v.getAccidentesOServicios().size();
        if (v.getAccidentesOServicios().get(0).equals("Ninguno")) {
            nAS = 0;
        }
        lblEstado.setText(v.getUsadoONuevo()+"\t | A/S: "+nAS);
        avanzar.setOnMouseClicked(e->{
            Image img=null;
            try {
                ArrayListCircular<String> rutas = v.getRutasFotos();
                img = new Image(new FileInputStream(App.pathImages+rutas.getNext()));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            imgVehiculo.setImage(img);
        });

        retroceder.setOnMouseClicked(e->{         
            Image img=null;
            try {
                ArrayListCircular<String> rutas = v.getRutasFotos();
                img = new Image(new FileInputStream(App.pathImages+rutas.getPrevious()));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            imgVehiculo.setImage(img);
        });
        IVAS.setOnMouseClicked(e->{
            try {
                App.abrirNuevaVentana("detallesAS", 370, 480);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
}
