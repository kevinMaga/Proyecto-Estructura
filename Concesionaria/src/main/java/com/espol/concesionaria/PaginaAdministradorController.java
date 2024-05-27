/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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
    }    
    
}
