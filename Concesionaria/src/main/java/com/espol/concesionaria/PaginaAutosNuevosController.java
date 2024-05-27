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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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
    private ImageView icTAuto;
    @FXML
    private ImageView icTMoto;
    @FXML
    private ImageView icTPesado;
    @FXML
    private ImageView icTMaq;
    @FXML
    private ImageView icTAcuat;
    @FXML
    private FlowPane fpLogos;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PaginaPrincipalController.llenarListaMarcas();
        for(int i=0;i<PaginaPrincipalController.marcas.size();i++){
            Marca m = PaginaPrincipalController.marcas.get(i);
            ImageView iv=null;
            try {
                FileInputStream f = new FileInputStream("src/main/resources/images/"+m.getImagen());
                Image img =new Image(f,150,120,true,true);
                iv = new ImageView(img);
                iv.setPreserveRatio(true);
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
        icTMoto.setOnMouseClicked(e->{
            PaginaPorTipoController.tipo=Tipo.MOTOS;
            Stage ventanaActual = (Stage) icTAuto.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPorTipo", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        icTMaq.setOnMouseClicked(e->{
            PaginaPorTipoController.tipo=Tipo.MAQUINARIAS;
            Stage ventanaActual = (Stage) icTAuto.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPorTipo", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        icTAcuat.setOnMouseClicked(e->{
            PaginaPorTipoController.tipo=Tipo.ACUATICOS;
            Stage ventanaActual = (Stage) icTAuto.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPorTipo", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        icTAuto.setOnMouseClicked(e->{
            PaginaPorTipoController.tipo=Tipo.AUTOS;
            Stage ventanaActual = (Stage) icTAuto.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPorTipo", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        icTPesado.setOnMouseClicked(e->{
            PaginaPorTipoController.tipo=Tipo.PESADOS;
            Stage ventanaActual = (Stage) icTAuto.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPorTipo", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
}
