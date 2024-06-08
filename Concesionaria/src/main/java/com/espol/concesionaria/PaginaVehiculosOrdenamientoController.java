/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import listas.ArrayListJR;
import modelo.Tipo;
import modelo.Vehiculo;

/**
 * FXML Controller class
 *
 * @author Justin Roldan
 */
public class PaginaVehiculosOrdenamientoController implements Initializable {

    /**
     * Initializes the controller class.
     */
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
    private ComboBox cmbOrdenar;
    private ArrayListJR<Vehiculo> vehiculos=new ArrayListJR<>();
    public static String ordenamiento=null;
    
    private void cambiarColorLabel(String color,Label label){
        LBAutos.setStyle("-fx-background-color:black;");
        LBMotos.setStyle("-fx-background-color:black;");
        LBMaquinarias.setStyle("-fx-background-color:black;");
        LBAcuaticos.setStyle("-fx-background-color:black;");
        LBPesados.setStyle("-fx-background-color:black;");
        label.setStyle("-fx-background-color:"+color+";");
    }
    
    private void comboBoxOrdenamiento(){
        cmbOrdenar.getItems().addAll("Cantidad de Ventas","Precio","Kilometraje");
        if(ordenamiento!=null){
            cmbOrdenar.setValue(ordenamiento);  
            Comparator<Vehiculo> c1 =(v1,v2)->{
                return v2.getCantidadVentas()-v1.getCantidadVentas();
            };
            vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, PaginaPrincipalController.encontrarTipoPorNombre("AUTOS"));
            PaginaPrincipalController.ordenar(vehiculos, c1);
            PaginaPrincipalController.llenarVehiculosEnContenedor("ninguno", fpVehiculos, vehiculos);
        }else{
            PaginaPrincipalController.llenarVehiculosEnContenedor("ninguno", fpVehiculos, PaginaPrincipalController.vehiculos);
        }
        cmbOrdenar.setOnAction(e->{
            if(cmbOrdenar.getValue().equals("Cantidad de Ventas")){
                Comparator<Vehiculo> c =(v1,v2)->{
                    return v2.getCantidadVentas()-v1.getCantidadVentas();
                };
                PaginaPrincipalController.ordenar(vehiculos, c);
                PaginaPrincipalController.llenarVehiculosEnContenedor("ninguno", fpVehiculos, vehiculos);
            }else if(cmbOrdenar.getValue().equals("Precio")){
                Comparator<Vehiculo> c =(v1,v2)->{
                    return Double.compare(v1.getPrecio(), v2.getPrecio());
                };
                PaginaPrincipalController.ordenar(vehiculos, c);
                PaginaPrincipalController.llenarVehiculosEnContenedor("ninguno", fpVehiculos, vehiculos);
            }else if(cmbOrdenar.getValue().equals("Kilometraje")){
                Comparator<Vehiculo> c =(v1,v2)->{
                    return v1.getKilometraje()-v2.getKilometraje();
                };
                PaginaPrincipalController.ordenar(vehiculos, c);
                PaginaPrincipalController.llenarVehiculosEnContenedor("ninguno", fpVehiculos, vehiculos);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LBAutos.setStyle("-fx-background-color:blue;");
        vehiculos=PaginaPrincipalController.vehiculosPorTipo(vehiculos, PaginaPrincipalController.encontrarTipoPorNombre("AUTOS"));
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
            cmbOrdenar.setValue("");
            Tipo a = PaginaPrincipalController.encontrarTipoPorNombre("AUTOS");
            cambiarColorLabel("blue",LBAutos);
            vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, a);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,vehiculos);
        });
        LBMotos.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            Tipo m = PaginaPrincipalController.encontrarTipoPorNombre("MOTOS");
            cambiarColorLabel("blue",LBMotos);
            vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, m);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,vehiculos);
        });
        LBMaquinarias.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            Tipo maq = PaginaPrincipalController.encontrarTipoPorNombre("MAQUINARIAS");
            cambiarColorLabel("blue",LBMaquinarias);
            vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, maq);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,vehiculos);
        });
        LBPesados.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            Tipo p = PaginaPrincipalController.encontrarTipoPorNombre("PESADOS");
            cambiarColorLabel("blue",LBPesados);
            vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, p);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,vehiculos);
        });
        LBAcuaticos.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            Tipo acua = PaginaPrincipalController.encontrarTipoPorNombre("ACUATICOS");
            cambiarColorLabel("blue",LBAcuaticos);
            vehiculos = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, acua);
            PaginaPrincipalController.llenarVehiculosEnContenedor("Usado",fpVehiculos,vehiculos);
        });
        comboBoxOrdenamiento();
    }    
    
}
