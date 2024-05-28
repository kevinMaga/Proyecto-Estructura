/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import static com.espol.concesionaria.PaginaPrincipalController.vehiculos;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listas.ArrayList;
import modelo.Marca;
import modelo.Tipo;

/**
 * FXML Controller class
 *
 * @author Cesar
 */

public class PaginaAutosNuevosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView IVInicio;
    @FXML
    private FlowPane fpLogos;
    
    @FXML
    private HBox hbTipos;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fpLogos.getChildren().clear();
        for(int i=0;i<PaginaPrincipalController.marcas.size();i++){
            Marca m = PaginaPrincipalController.marcas.get(i);
            ImageView iv=null;
            try {
                FileInputStream f = new FileInputStream("src/main/resources/images/"+m.getImagen());
                Image img =new Image(f,150,120,true,true);
                iv = new ImageView(img);
                iv.setPreserveRatio(true);
                iv.setCursor(Cursor.HAND);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            iv.setOnMouseClicked(e->{
                PaginaPorMarcaController.marca=m;
                Stage s =(Stage) fpLogos.getScene().getWindow();
                s.close();
                try {
                    App.abrirNuevaVentana("paginaPorMarca",929 , 628);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            fpLogos.getChildren().add(iv);
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
        
        //Llenar contenedor de tipos
        hbTipos.getChildren().clear();
        for(int i=0;i<PaginaPrincipalController.tipos.size();i++){
            Tipo t = PaginaPrincipalController.tipos.get(i);
            ImageView iv = null;
            try(FileInputStream f = new FileInputStream("src/main/resources/images/"+t.getFoto())){
                Image img = new Image(f,180,120,true,true);
                iv = new ImageView(img);
            }catch(IOException e){
            }
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);
            v.setStyle("-fx-background-color: white;");
            Label lbl = new Label(t.getNombre());
            lbl.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 10px; -fx-background-color: white; -fx-border-radius: 3px;");
            v.getChildren().addAll(iv,lbl);
            v.setOnMouseClicked(e->{
                PaginaPorTipoController.tipo=t;
                Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
                ventanaActual.close();
                try {
                    App.abrirNuevaVentana("paginaPorTipo", 929, 681);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            hbTipos.getChildren().add(v);
        }
    }    
    
}
