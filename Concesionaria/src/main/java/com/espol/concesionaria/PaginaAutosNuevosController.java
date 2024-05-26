/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private ImageView icMAudi;
    @FXML
    private ImageView icMbmw;
    @FXML
    private ImageView icMbobcat;
    @FXML
    private ImageView icMcat;
    
    
    
    
    public static Stage irPantallaMarca(double n,double m,String Nombre) throws IOException {
        Stage nuevaVentana = new Stage();
        
        FXMLLoader loader = new FXMLLoader(App.class.getResource("paginaPorMarca.fxml"));
        Parent root = loader.load();
        PaginaPorMarcaController controller = loader.getController();
        controller.setDatos(Nombre);
        
        
        
        Scene nuevaScene = new Scene(root, n, m);
        
        
        
        nuevaVentana.setScene(nuevaScene);
        nuevaVentana.setResizable(false);
        nuevaVentana.show();
        return nuevaVentana;
    }
    public static Stage irPantallaTipo(double n,double m,String Nombre) throws IOException {
        Stage nuevaVentana = new Stage();
        FXMLLoader loader = new FXMLLoader(App.class.getResource("paginaPorTipo.fxml"));
        Parent root = loader.load();
        //PaginaPorTipoController controller = loader.getController();
        //controller.setDatos(Nombre);
        
        
        Scene nuevaScene = new Scene(root, n, m);
        
        
        
        nuevaVentana.setScene(nuevaScene);
        nuevaVentana.setResizable(false);
        nuevaVentana.show();
        return nuevaVentana;
    }
    
    
    
    
    
    
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
        icTAuto.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) icTAuto.getScene().getWindow();
            ventanaActual.close();
            try {
                irPantallaTipo(929, 681,"Auto");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        
        
        
        
    }    
    
}
