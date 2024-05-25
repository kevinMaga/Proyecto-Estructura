
package com.espol.concesionaria;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import modelo.Tipo;

/**
 * FXML Controller class
 * estop esnubsunsduasuasdadssdadsasddasads
 * @author Justin Roldan
 */
public class PaginaPrincipalController implements Initializable {

    @FXML
    private HBox head;
    
    @FXML
    private HBox menu;
    
    @FXML
    private HBox filtro;
    
    @FXML
    private ComboBox cmbHead;
    
    @FXML
    private TextField TFHead;
    
    @FXML
    private ImageView IVBuscar;
    
    @FXML
    private Label LBIniciarSesion;
    
    @FXML
    private Label LBVende;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbHead.getItems().addAll(Tipo.AUTOS,Tipo.ACUATICOS,Tipo.MAQUINARIAS,Tipo.MOTOS,Tipo.PESADOS);
    }    
    
    
}
