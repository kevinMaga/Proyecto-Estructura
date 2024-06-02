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
import java.util.ArrayList;
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
    
    private boolean llno=true;
    
    public static Marca marca;
    
    public void setDatos(String tipo){
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoMarca.getChildren().clear();
        ImageView marcImg = null;
        try {
            FileInputStream f = new FileInputStream("src/main/resources/images/" + marca.getImagen());
            Image img = new Image(f,200,140,true,true);
            marcImg = new ImageView(img);
            marcImg.setPreserveRatio(true);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        infoMarca.setAlignment(Pos.CENTER_LEFT);
        infoMarca.setStyle("-fx-background-color: white;");
        Label brand = new Label(marca.getNombre());
        brand.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 10px; -fx-background-color: white; -fx-border-radius: 3px;");
        brand.setAlignment(Pos.CENTER);
        infoMarca.getChildren().addAll(marcImg,brand);
        
        
        
        
        
        fpVehiculos.getChildren().clear();
        ArrayList<Vehiculo> vehiculos = PaginaPrincipalController.vehiculosPorMarca(PaginaPrincipalController.vehiculos, marca);
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
                        FileInputStream f = new FileInputStream("src/main/resources/images/" + vehiculos.get(i).getRutasFotos().get(0));
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
