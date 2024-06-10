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
    private ImageView IVInicio;
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
    @FXML
    private TextField TFPrecioDesde;
    
    @FXML
    private TextField TFPrecioHasta;
    
    @FXML
    private TextField TFAñoDesde;
    
    @FXML
    private TextField TFAñoHasta;
    @FXML
    private TextField TFKilometrosDesde;
    @FXML
    private TextField TFKilometrajeHasta;
    @FXML
    private ImageView IVBuscarP;
    @FXML
    private ImageView IVBuscarA;
    @FXML
    private ImageView IVBuscarK;
    /**
     * Initializes the controller class.
     */
    private ArrayListJR<Vehiculo> vehicles = new ArrayListJR<>();
    @FXML
    private HBox head;
    @FXML
    private HBox menu;
    @FXML
    private HBox filtro;
    private void limpiarCampos() {
        TFPrecioDesde.clear();
        TFPrecioHasta.clear();
        TFAñoDesde.clear();
        TFAñoHasta.clear();
        TFKilometrosDesde.clear();
        TFKilometrajeHasta.clear();
    }
    
    
    private void cambiarColorLabel(String color,Label label){
        LBAutos.setStyle("-fx-background-color:black;");
        LBMotos.setStyle("-fx-background-color:black;");
        LBMaquinarias.setStyle("-fx-background-color:black;");
        LBAcuaticos.setStyle("-fx-background-color:black;");
        LBPesados.setStyle("-fx-background-color:black;");
        label.setStyle("-fx-background-color:"+color+";");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LBAutos.setStyle("-fx-background-color:black;");
        LBMotos.setStyle("-fx-background-color:black;");
        LBMaquinarias.setStyle("-fx-background-color:black;");
        LBAcuaticos.setStyle("-fx-background-color:black;");
        LBPesados.setStyle("-fx-background-color:black;");
        vehicles=PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.listaFiltrada);
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
            limpiarCampos();
            Tipo a = PaginaPrincipalController.encontrarTipoPorNombre("AUTOS");
            cambiarColorLabel("blue",LBAutos);
            vehicles =PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, a));
        });
        LBMotos.setOnMouseClicked(e->{
            limpiarCampos();
            Tipo m = PaginaPrincipalController.encontrarTipoPorNombre("MOTOS");
            cambiarColorLabel("blue",LBMotos);
            vehicles =PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, m));
        });
        LBMaquinarias.setOnMouseClicked(e->{
            limpiarCampos();
            Tipo maq = PaginaPrincipalController.encontrarTipoPorNombre("MAQUINARIAS");
            cambiarColorLabel("blue",LBMaquinarias);
            vehicles =PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, maq));
        });
        LBPesados.setOnMouseClicked(e->{
            limpiarCampos();
            Tipo p = PaginaPrincipalController.encontrarTipoPorNombre("PESADOS");
            cambiarColorLabel("blue",LBPesados);
            vehicles =PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, p));
        });
        LBAcuaticos.setOnMouseClicked(e->{
            limpiarCampos();
            Tipo acua = PaginaPrincipalController.encontrarTipoPorNombre("ACUATICOS");
            cambiarColorLabel("blue",LBAcuaticos);
            vehicles =PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, acua));
        }); 
        IVBuscarA.setOnMouseClicked(e->{
            TFPrecioDesde.clear();
            TFPrecioHasta.clear();
            TFKilometrosDesde.clear();
            TFKilometrajeHasta.clear();
            ArrayListJR<Vehiculo> vehicles1=vehicles;
            if(!TFAñoDesde.getText().isEmpty()){
                fpVehiculos.getChildren().clear();
                vehicles1=PaginaPrincipalController.vehiculosPorAño(vehicles1, Integer.valueOf(TFAñoDesde.getText()), "desde");
            }
            if(!TFAñoHasta.getText().isEmpty()){
                fpVehiculos.getChildren().clear();
                vehicles1 =PaginaPrincipalController.vehiculosPorAño(vehicles1, Integer.valueOf(TFAñoHasta.getText()), "hasta");
            }
            for (int i = 0; i < vehicles1.size(); i++) {
                final int j = i;
                Vehiculo a = vehicles1.get(i);
                VBox v = PaginaPrincipalController.contenedorParaImagenes(App.pathImages + a.getRutasFotos().get(0), a.getMarca() + " " + a.getModelo(),
                         a.getAño() + "   " + a.getKilometraje() + " kms . " + a.getUbicacionActualVehiculo() + "\n"
                        + a.getUsadoONuevo(), "$ " + a.getPrecio());
                fpVehiculos.getChildren().add(v);
            }
        });
        IVBuscarP.setOnMouseClicked(e->{
            TFAñoDesde.clear();
            TFAñoHasta.clear();
            TFKilometrosDesde.clear();
            TFKilometrajeHasta.clear();
            ArrayListJR<Vehiculo> vehicles1=vehicles;
            if(!TFPrecioDesde.getText().isEmpty()){
                fpVehiculos.getChildren().clear();
                vehicles1 =PaginaPrincipalController.vehiculosPorPrecio(vehicles1, Integer.valueOf(TFPrecioDesde.getText()), "desde");
            }
            if(!TFPrecioHasta.getText().isEmpty()){
                fpVehiculos.getChildren().clear();
                vehicles1 =PaginaPrincipalController.vehiculosPorPrecio(vehicles1, Integer.valueOf(TFPrecioHasta.getText()), "hasta");
            }
            for (int i = 0; i < vehicles1.size(); i++) {
                final int j = i;
                Vehiculo a = vehicles1.get(j);
                VBox v = PaginaPrincipalController.contenedorParaImagenes(App.pathImages + a.getRutasFotos().get(0), a.getMarca() + " " + a.getModelo(),
                         a.getAño() + "   " + a.getKilometraje() + " kms . " + a.getUbicacionActualVehiculo() + "\n"
                        + a.getUsadoONuevo(), "$ " + a.getPrecio());
                fpVehiculos.getChildren().add(v);
            }
        });
        IVBuscarK.setOnMouseClicked(e->{
            TFPrecioDesde.clear();
            TFPrecioHasta.clear();
            TFAñoDesde.clear();
            TFAñoHasta.clear();
            ArrayListJR<Vehiculo> vehicles1=vehicles;
            if(!TFKilometrosDesde.getText().isEmpty()){
                fpVehiculos.getChildren().clear();
                vehicles1=PaginaPrincipalController.vehiculosPorKilometraje(vehicles1, Integer.valueOf(TFKilometrosDesde.getText()), "desde");
                
            }
            if(!TFKilometrajeHasta.getText().isEmpty()){
                fpVehiculos.getChildren().clear();
                vehicles1 =PaginaPrincipalController.vehiculosPorKilometraje(vehicles1, Integer.valueOf(TFKilometrajeHasta.getText()), "hasta");
            
            }
            for (int i = 0; i < vehicles1.size(); i++) {
                final int j = i;
                Vehiculo a = vehicles1.get(j);
                VBox v = PaginaPrincipalController.contenedorParaImagenes(App.pathImages + a.getRutasFotos().get(0), a.getMarca() + " " + a.getModelo(),
                         a.getAño() + "   " + a.getKilometraje() + " kms . " + a.getUbicacionActualVehiculo() + "\n"
                        + a.getUsadoONuevo(), "$ " + a.getPrecio());
                fpVehiculos.getChildren().add(v);
            }
        });
    }
    
}
