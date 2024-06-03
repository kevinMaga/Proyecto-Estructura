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
    public static void llenarVehiculosEnContenedor(String usadoONuevo,FlowPane fpVehiculos,ArrayListJR<Vehiculo> vehiculos){
        fpVehiculos.getChildren().clear();
        for(int i = 0; i < vehiculos.size(); i++){
            if(vehiculos.get(i).getUsadoONuevo().equals(usadoONuevo)){
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
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.listaFiltrada);
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
            LBAutos.setStyle("-fx-background-color:blue;");
            LBMotos.setStyle("-fx-background-color:black;");
            LBMaquinarias.setStyle("-fx-background-color:black;");
            LBAcuaticos.setStyle("-fx-background-color:black;");
            LBPesados.setStyle("-fx-background-color:black;");
            llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, a));
        });
        LBMotos.setOnMouseClicked(e->{
            Tipo m = PaginaPrincipalController.encontrarTipoPorNombre("MOTOS");
            LBAutos.setStyle("-fx-background-color:black;");
            LBMotos.setStyle("-fx-background-color:blue;");
            LBMaquinarias.setStyle("-fx-background-color:black;");
            LBAcuaticos.setStyle("-fx-background-color:black;");
            LBPesados.setStyle("-fx-background-color:black;");
            llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, m));
        });
        LBMaquinarias.setOnMouseClicked(e->{
            Tipo maq = PaginaPrincipalController.encontrarTipoPorNombre("MAQUINARIAS");
            LBAutos.setStyle("-fx-background-color:black;");
            LBMotos.setStyle("-fx-background-color:black;");
            LBMaquinarias.setStyle("-fx-background-color:blue;");
            LBAcuaticos.setStyle("-fx-background-color:black;");
            LBPesados.setStyle("-fx-background-color:black;");
            llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, maq));
        });
        LBPesados.setOnMouseClicked(e->{
            Tipo p = PaginaPrincipalController.encontrarTipoPorNombre("PESADOS");
            LBAutos.setStyle("-fx-background-color:black;");
            LBMotos.setStyle("-fx-background-color:black;");
            LBMaquinarias.setStyle("-fx-background-color:black;");
            LBAcuaticos.setStyle("-fx-background-color:black;");
            LBPesados.setStyle("-fx-background-color:blue;");
            llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, p));
        });
        LBAcuaticos.setOnMouseClicked(e->{
            Tipo acua = PaginaPrincipalController.encontrarTipoPorNombre("ACUATICOS");
            LBAutos.setStyle("-fx-background-color:black;");
            LBMotos.setStyle("-fx-background-color:black;");
            LBMaquinarias.setStyle("-fx-background-color:black;");
            LBAcuaticos.setStyle("-fx-background-color:blue;");
            LBPesados.setStyle("-fx-background-color:black;");
            llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, acua));
        });
        
    }    
    
}
