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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    @FXML
    private TextField TFDesde1;
    @FXML
    private TextField TFHasta1;
    @FXML
    private ImageView IVBuscarPrecio1;
    @FXML
    private TextField TFDesde2;
    @FXML
    private TextField TFHasta2;
    @FXML
    private ImageView IVBuscarPrecio2;
    @FXML
    private Label LMarcas;
    @FXML
    private Label LProvincias;
    @FXML
    private TextField TFDesde3;
    @FXML
    private TextField TFHasta3;
    @FXML
    private ImageView IVBuscarPrecio3;
    @FXML
    private Label LTransmision;
    @FXML
    private ImageView IVCars1;
    @FXML
    private ImageView IVCars2;
    @FXML
    private ImageView IVCars3;
    @FXML
    private ImageView IVCars4;
    @FXML
    private ImageView IVCars5;
    @FXML
    private ImageView IVCars6;
    @FXML
    private ImageView IVCars7;
    @FXML
    private ImageView IVCars8;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
