/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Justin Roldan
 */
public class DetallesASController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vbAS;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i=0;i<PaginaDetallesVehiculoController.v.getAccidentesOServicios().size();i++){
            String AS = PaginaDetallesVehiculoController.v.getAccidentesOServicios().get(i);
            Label label = new Label(AS);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10, 10, 10, 10));
            hBox.setSpacing(10);
            hBox.getChildren().add(label);
            vbAS.getChildren().add(hBox);
        }
    }    
    
}
