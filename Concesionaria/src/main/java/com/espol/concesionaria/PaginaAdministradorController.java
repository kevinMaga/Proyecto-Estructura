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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class PaginaAdministradorController implements Initializable {

    @FXML
    private Label LBUser;
    @FXML
    private Label LBAgregar;
    @FXML
    private Label LBEditar;
    @FXML
    private Label LBRemover;
    @FXML
    private Label LBListado;
    @FXML
    private RadioButton RBNuevos;
    @FXML
    private RadioButton RBUsados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LBUser.setText(InicioSesionController.usuario.getUsuario());
        ToggleGroup tg=new ToggleGroup();
        RBNuevos.setToggleGroup(tg);
        RBUsados.setToggleGroup(tg);
        LBAgregar.setOnMouseClicked(e->{
            
            if(RBNuevos.isSelected()){
                try {
                    App.abrirNuevaVentana("paginaAgregarVehiculo", 807, 719);
                    Stage s =(Stage)LBAgregar.getScene().getWindow();
            s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else if(RBUsados.isSelected()){
                try {
                    App.abrirNuevaVentana("paginaAgregarVehiculo", 807, 719);
                    Stage s =(Stage)LBAgregar.getScene().getWindow();
            s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else{
               // Mostrar alerta
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText(null);
                alert.setContentText("Debe seleccionar una opción (Nuevos o Usados) antes de continuar.");
                alert.showAndWait(); 
            }  
        });
        
        LBEditar.setOnMouseClicked(e -> {
            if (RBNuevos.isSelected()) {
                try {
                    App.abrirNuevaVentana("paginaEditar", 566, 426);
                    Stage s1 = (Stage) LBEditar.getScene().getWindow();
                    s1.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (RBUsados.isSelected()) {
                try {
                    App.abrirNuevaVentana("paginaEditar", 566, 426);
                    Stage s1 = (Stage) LBEditar.getScene().getWindow();
                    s1.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Mostrar alerta
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText(null);
                alert.setContentText("Debe seleccionar una opción (Nuevos o Usados) antes de continuar.");
                alert.showAndWait();
            }
        });
        LBRemover.setOnMouseClicked(e -> {
           if (RBNuevos.isSelected()) {
                try {
                    App.abrirNuevaVentana("paginaRemover", 778, 613);
                    Stage s2 = (Stage) LBEditar.getScene().getWindow();
                    s2.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
            } else if (RBUsados.isSelected()) {
                try {
                    App.abrirNuevaVentana("paginaRemover", 778, 613);
                    Stage s2 = (Stage) LBEditar.getScene().getWindow();
                    s2.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Mostrar alerta
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText(null);
                alert.setContentText("Debe seleccionar una opción (Nuevos o Usados) antes de continuar.");
                alert.showAndWait();
            } 
        
        });
    }    
    
}
