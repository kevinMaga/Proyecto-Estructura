/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;
import static com.espol.concesionaria.PaginaPrincipalController.contenedorParaImagenes;
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
import javafx.scene.layout.VBox;
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
    public static String ordenamiento=null;
    
    private void cambiarColorLabel(String color,Label label){
        LBAutos.setStyle("-fx-background-color:black;");
        LBMotos.setStyle("-fx-background-color:black;");
        LBMaquinarias.setStyle("-fx-background-color:black;");
        LBAcuaticos.setStyle("-fx-background-color:black;");
        LBPesados.setStyle("-fx-background-color:black;");
        label.setStyle("-fx-background-color:"+color+";");
    }
    
    private void llenarContenedorDetalles(ArrayListJR<Vehiculo> vehiculos1){
        fpVehiculos.getChildren().clear();
        for (int i = 0; i < vehiculos1.size(); i++) {
            Vehiculo a = vehiculos1.get(i);
            final int j = i;
            VBox vbox =contenedorParaImagenes(App.pathImages+a.getRutasFotos().get(0),a.getMarca()+" "+a.getModelo()
                    ,a.getAño() + "   "+a.getKilometraje()+" kms . "+a.getUbicacionActualVehiculo()+"\n"
                    +a.getUsadoONuevo(),"$ "+a.getPrecio());
            vbox.setOnMouseClicked(e -> {
                PaginaDetallesVehiculoController.v = vehiculos1.get(j);
                try {
                    App.abrirNuevaVentana("paginaDetallesVehiculo", 834, 687);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            fpVehiculos.getChildren().add(vbox);
        }       
    }
    
    private void comboBoxOrdenamiento(ArrayListJR<Vehiculo> vehiculos){
        cmbOrdenar.setOnAction(ev->{
            if(cmbOrdenar.getValue().equals("Cantidad de Ventas")){
                Comparator<Vehiculo> c =(v1,v2)->{
                    return v2.getCantidadVentas()-v1.getCantidadVentas();
                };
                PaginaPrincipalController.ordenar(vehiculos, c);
                llenarContenedorDetalles(vehiculos);
            }else if(cmbOrdenar.getValue().equals("Precio")){
                Comparator<Vehiculo> c =(v1,v2)->{
                    return Double.compare(v1.getPrecio(), v2.getPrecio());
                };
                PaginaPrincipalController.ordenar(vehiculos, c);
                llenarContenedorDetalles(vehiculos);
            }else if(cmbOrdenar.getValue().equals("Kilometraje")){
                Comparator<Vehiculo> c =(v1,v2)->{
                    return v1.getKilometraje()-v2.getKilometraje();
                };
                PaginaPrincipalController.ordenar(vehiculos, c);
                llenarContenedorDetalles(vehiculos);
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbOrdenar.getItems().addAll("Cantidad de Ventas","Precio","Kilometraje");
        cmbOrdenar.setValue("Cantidad de Ventas");
        fpVehiculos.getChildren().clear(); 
        LBAutos.setStyle("-fx-background-color:blue;");
        Comparator<Vehiculo> c = (v1, v2) -> {
            return v2.getCantidadVentas() - v1.getCantidadVentas();
        };
        ArrayListJR<Vehiculo> vehiculos =PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos, 
                PaginaPrincipalController.encontrarTipoPorNombre("AUTOS"));
        PaginaPrincipalController.ordenar(vehiculos, c);
        llenarContenedorDetalles(vehiculos);
        comboBoxOrdenamiento(vehiculos);
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
            cambiarColorLabel("blue", LBAutos);
            Tipo t =PaginaPrincipalController.encontrarTipoPorNombre("AUTOS");
            ArrayListJR<Vehiculo> vehiculos1 = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos,t);
            llenarContenedorDetalles(vehiculos1);
            comboBoxOrdenamiento(vehiculos1);
        });
        LBMotos.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            cambiarColorLabel("blue",LBMotos);
            Tipo t =PaginaPrincipalController.encontrarTipoPorNombre("MOTOS");
            ArrayListJR<Vehiculo> vehiculos1 = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos,t);
            llenarContenedorDetalles(vehiculos1);
            comboBoxOrdenamiento(vehiculos1);
        });
        LBMaquinarias.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            cambiarColorLabel("blue",LBMaquinarias);
            Tipo t =PaginaPrincipalController.encontrarTipoPorNombre("MAQUINARIAS");
            ArrayListJR<Vehiculo> vehiculos1 = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos,t);
            llenarContenedorDetalles(vehiculos1);
            comboBoxOrdenamiento(vehiculos1);
        });
        LBPesados.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            cambiarColorLabel("blue",LBPesados);
            Tipo t =PaginaPrincipalController.encontrarTipoPorNombre("PESADOS");
            ArrayListJR<Vehiculo> vehiculos1 = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos,t);
            llenarContenedorDetalles(vehiculos1);
            comboBoxOrdenamiento(vehiculos1);
        });
        LBAcuaticos.setOnMouseClicked(e->{
            cmbOrdenar.setValue("");
            cambiarColorLabel("blue",LBAcuaticos);
            Tipo t =PaginaPrincipalController.encontrarTipoPorNombre("ACUATICOS");
            ArrayListJR<Vehiculo> vehiculos1 = PaginaPrincipalController.vehiculosPorTipo(PaginaPrincipalController.vehiculos,t);
            llenarContenedorDetalles(vehiculos1);
            comboBoxOrdenamiento(vehiculos1);
        }); 
    }    
    
}
