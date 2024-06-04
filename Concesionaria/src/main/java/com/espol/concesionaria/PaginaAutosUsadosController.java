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
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import listas.ArrayListJR;
import modelo.Tipo;
import modelo.Vehiculo;

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
    private Label LBAutos;
    @FXML
    private Label LBMotos; 
    @FXML
    private Label LBPesados;
    @FXML
    private Label LBMaquinarias;
    @FXML
    private Label LBAcuaticos;
    @FXML
    private FlowPane fpVehiculos;
    /**
     * Initializes the controller class.
     */
    
    private void cambiarColorLabel(String color,Label label){
        LBAutos.setStyle("-fx-background-color:black;");
        LBMotos.setStyle("-fx-background-color:black;");
        LBMaquinarias.setStyle("-fx-background-color:black;");
        LBAcuaticos.setStyle("-fx-background-color:black;");
        LBPesados.setStyle("-fx-background-color:black;");
        label.setStyle("fx-background-color:"+color+";");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LBAutos.setStyle("-fx-background-color:black;");
        LBMotos.setStyle("-fx-background-color:black;");
        LBMaquinarias.setStyle("-fx-background-color:black;");
        LBAcuaticos.setStyle("-fx-background-color:black;");
        LBPesados.setStyle("-fx-background-color:black;");
        PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.listaFiltrada);
        IVInicio.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPrincipal", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        LBAutos.setOnMouseClicked(e->{
            Tipo a = PaginaPrincipalController.encontrarTipoPorNombre("AUTOS");
            cambiarColorLabel("blue",LBAutos);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, a));
        });
        LBMotos.setOnMouseClicked(e->{
            Tipo m = PaginaPrincipalController.encontrarTipoPorNombre("MOTOS");
            cambiarColorLabel("blue",LBMotos);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, m));
        });
        LBMaquinarias.setOnMouseClicked(e->{
            Tipo maq = PaginaPrincipalController.encontrarTipoPorNombre("MAQUINARIAS");
            cambiarColorLabel("blue",LBMaquinarias);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, maq));
        });
        LBPesados.setOnMouseClicked(e->{
            Tipo p = PaginaPrincipalController.encontrarTipoPorNombre("PESADOS");
            cambiarColorLabel("blue",LBPesados);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, p));
        });
        LBAcuaticos.setOnMouseClicked(e->{
            Tipo acua = PaginaPrincipalController.encontrarTipoPorNombre("ACUATICOS");
            cambiarColorLabel("blue",LBAcuaticos);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, acua));
        });
        
    }    
    
}
