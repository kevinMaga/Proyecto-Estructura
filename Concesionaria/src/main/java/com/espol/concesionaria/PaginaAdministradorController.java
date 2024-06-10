/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class PaginaAdministradorController implements Initializable {

    @FXML
    private Label LBAgregar;
    @FXML
    private Label LBEditar;
    @FXML
    private Label LBRemover;
    @FXML
    private ImageView IVInicio;
    @FXML
    private ImageView IVAgregar;
    @FXML
    private ImageView IVRemover;
    @FXML
    private ImageView IVEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LBAgregar.setOnMouseClicked(e->{
            try {
                App.abrirNuevaVentana("paginaAgregarVehiculo", 807, 719);
                Stage s =(Stage)LBAgregar.getScene().getWindow();
                s.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        LBEditar.setOnMouseClicked(e -> {
            try {
                App.abrirNuevaVentana("paginaEditar", 800, 719);
                Stage s1 = (Stage) LBEditar.getScene().getWindow();
                s1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        LBRemover.setOnMouseClicked(e -> {
           try {
                App.abrirNuevaVentana("paginaRemover", 778, 613);
                Stage s2 = (Stage) LBEditar.getScene().getWindow();
                s2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        });
        IVAgregar.setOnMouseClicked(e->{
            try {
                App.abrirNuevaVentana("paginaAgregarVehiculo", 807, 719);
                Stage s =(Stage)LBAgregar.getScene().getWindow();
                s.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        IVEditar.setOnMouseClicked(e -> {
            try {
                App.abrirNuevaVentana("paginaEditar", 800, 719);
                Stage s1 = (Stage) LBEditar.getScene().getWindow();
                s1.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        IVRemover.setOnMouseClicked(e -> {
           try {
                App.abrirNuevaVentana("paginaRemover", 778, 613);
                Stage s2 = (Stage) LBEditar.getScene().getWindow();
                s2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        });
        IVInicio.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPrincipal", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
}
