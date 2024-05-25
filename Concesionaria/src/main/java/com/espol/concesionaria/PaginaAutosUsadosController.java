/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class PaginaAutosUsadosController implements Initializable {

    @FXML
    private HBox head;
    @FXML
    private ComboBox<?> cmbHead1;
    @FXML
    private TextField TFHead1;
    @FXML
    private ImageView IVBuscar1;
    @FXML
    private Label LBIniciarSesion1;
    @FXML
    private HBox menu;
    @FXML
    private ImageView IVInicio;
    @FXML
    private HBox filtro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
