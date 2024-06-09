package com.espol.concesionaria;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import listas.ArrayListJR;
import modelo.Vehiculo;

/**
 * FXML Controller class
 */
public class PaginaEditarDetallesController implements Initializable {

    @FXML
    private ImageView IVVehiculo1;
    @FXML
    private ImageView IVVehiculo2;
    @FXML
    private ImageView IVVehiculo3;
    @FXML
    private Label LBGuardar;
    @FXML
    private TextField TFPrecio;
    @FXML
    private TextField TFMarca;
    @FXML
    private TextField TFModelo;
    @FXML
    private TextField TFAño;
    @FXML
    private TextField TFKilometraje;
    @FXML
    private TextField TFMotor;
    @FXML
    private TextField TFTransmision;
    @FXML
    private TextField TFPeso;
    @FXML
    private TextField TFUbicacion;
    @FXML
    private TextField TFTipo;
    @FXML
    private TextField a1;
    @FXML
    private TextField a2;
    @FXML
    private TextField a3;
    @FXML
    private TextField a4;
    @FXML
    private TextField a5;
    @FXML
    private TextField TFCantidadVentas;
    @FXML
    private TextField TFUN;
    @FXML
    private Label lbImagen1;
    @FXML
    private Label lbImagen2;
    @FXML
    private Label lbImagen3;
    @FXML
    private Label volver;
    @FXML
    private ImageView IVInicio;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Vehiculo v = PaginaEditarController.vehiculo;
        try {
            llenarCampos(v);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        LBGuardar.setOnMouseClicked(event -> guardarCambios(v));
    }    
    
    private void llenarCampos(Vehiculo v) throws FileNotFoundException {
        
        TFPrecio.setText(String.valueOf(v.getPrecio()));
        TFMarca.setText(v.getMarca().toString());
        TFModelo.setText(v.getModelo());
        TFAño.setText(String.valueOf(v.getAño()));
        TFKilometraje.setText(String.valueOf(v.getKilometraje()));
        TFMotor.setText(v.getMotor());
        TFTransmision.setText(v.getTransmisión());
        TFPeso.setText(v.getPeso());
        TFUbicacion.setText(v.getUbicacionActualVehiculo());
        TFTipo.setText(v.getTipo().toString());
        TFCantidadVentas.setText(String.valueOf(v.getCantidadVentas()));
        TFUN.setText(v.getUsadoONuevo());

        if (v.getAccidentesOServicios().size() > 0) a1.setText(v.getAccidentesOServicios().get(0));
        if (v.getAccidentesOServicios().size() > 1) a2.setText(v.getAccidentesOServicios().get(1));
        if (v.getAccidentesOServicios().size() > 2) a3.setText(v.getAccidentesOServicios().get(2));
        if (v.getAccidentesOServicios().size() > 3) a4.setText(v.getAccidentesOServicios().get(3));
        if (v.getAccidentesOServicios().size() > 4) a5.setText(v.getAccidentesOServicios().get(4));
        
        if (v.getRutasFotos().size() > 0) IVVehiculo1.setImage(new Image(new FileInputStream(App.pathImages+v.getRutasFotos().get(0))));
        if (v.getRutasFotos().size() > 1) IVVehiculo2.setImage(new Image(new FileInputStream(App.pathImages+v.getRutasFotos().get(1))));
        if (v.getRutasFotos().size() > 2) IVVehiculo3.setImage(new Image(new FileInputStream(App.pathImages+v.getRutasFotos().get(2))));
    }

    private void guardarCambios(Vehiculo v) {
        v.setPrecio(Double.valueOf(TFPrecio.getText()));
        v.setMarca(PaginaPrincipalController.encontrarMarcaPorNombre(TFMarca.getText()));
        v.setModelo(TFModelo.getText());
        v.setAño(Integer.parseInt(TFAño.getText()));
        v.setKilometraje(Integer.parseInt(TFKilometraje.getText()));
        v.setMotor(TFMotor.getText());
        v.setTransmisión(TFTransmision.getText());
        v.setPeso(TFPeso.getText());
        v.setUbicacionActualVehiculo(TFUbicacion.getText());
        v.setTipo(PaginaPrincipalController.encontrarTipoPorNombre(TFTipo.getText()));
        v.setCantidadVentas(Integer.valueOf(TFCantidadVentas.getText()));
        v.setUsadoONuevo(TFUN.getText());
        v.getAccidentesOServicios().clear();
        if (!a1.getText().isEmpty()) v.getAccidentesOServicios().add(a1.getText());
        if (!a2.getText().isEmpty()) v.getAccidentesOServicios().add(a2.getText());
        if (!a3.getText().isEmpty()) v.getAccidentesOServicios().add(a3.getText());
        if (!a4.getText().isEmpty()) v.getAccidentesOServicios().add(a4.getText());
        if (!a5.getText().isEmpty()) v.getAccidentesOServicios().add(a5.getText());
        ArrayListJR<Vehiculo> vehiculos = PaginaPrincipalController.vehiculos;
        for(int i=0;i<vehiculos.size();i++){
            if(vehiculos.get(i).equals(v)){
                PaginaPrincipalController.vehiculos.set(i, v);
            }
        }
    }
}
