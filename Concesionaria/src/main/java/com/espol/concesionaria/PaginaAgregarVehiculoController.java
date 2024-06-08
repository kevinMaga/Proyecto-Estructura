package com.espol.concesionaria;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import listas.ArrayListJR;

public class PaginaAgregarVehiculoController implements Initializable{

    
    
    /*private List<File> selectedFiles;
    
    private ImageView i;
    
    private Label mensajeImagen;*/
    @FXML
    private Label titulo;
    @FXML
    private TextField TFPrecio;
    @FXML
    private TextField TFMarca;
    @FXML
    private TextField TFModelo;
    @FXML
    private TextField TFAno;
    @FXML
    private TextField TFKilometraje;
    @FXML
    private Label titulo132;
    @FXML
    private Label titulo133;
    @FXML
    private Spinner<?> SPAccidente;
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
    private TextField TFCantidad;
    @FXML
    private TextField TFUN;
    @FXML
    private ImageView IVVehiculo1;
    @FXML
    private ImageView IVVehiculo2;
    @FXML
    private ImageView IVVehiculo3;
    @FXML
    private Label LBGuardar;

   /* private void agregarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif")
        );
        selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (int i=0;i<selectedFiles.size();i++) {
                File file =selectedFiles.get(i);
                guardarImagen(file);
                mensajeImagen.setText("Imagen guardada satisfactoriamente");
            }
        }
    }

    private void guardarImagen(File imagenFile) {
        try {
            Path sourcePath = Paths.get(imagenFile.getAbsolutePath());
            String destino = App.pathImages; // Ruta específica donde deseas guardar las imágenes
            File destinoFile = new File(destino + imagenFile.getName());
            Files.copy(sourcePath, destinoFile.toPath());
            // Puedes mostrar un mensaje de éxito o realizar otras acciones aquí
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }*/
    private void updateImages() {
        String marca = TFMarca.getText();
        if ("Ford".equalsIgnoreCase(marca)) {
            IVVehiculo1.setImage(new Image(getClass().getResource("/images/ford1.png").toExternalForm()));
            IVVehiculo2.setImage(new Image(getClass().getResource("/images/ford2.png").toExternalForm()));
            IVVehiculo3.setImage(new Image(getClass().getResource("/images/ford3.png").toExternalForm()));
        } else {
            IVVehiculo1.setImage(new Image(getClass().getResource("/images/agregarImagen.png").toExternalForm()));
            IVVehiculo2.setImage(new Image(getClass().getResource("/images/agregarImagen.png").toExternalForm()));
            IVVehiculo3.setImage(new Image(getClass().getResource("/images/agregarImagen.png").toExternalForm()));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add a listener to the TFMarca TextField to detect changes
        TFMarca.addEventHandler(KeyEvent.KEY_RELEASED, event -> updateImages());
        /*i.setOnMouseClicked(e->{
            agregarImagen();
        });*/
    }
}
