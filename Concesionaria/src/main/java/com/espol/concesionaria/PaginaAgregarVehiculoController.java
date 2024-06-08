package com.espol.concesionaria;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import listas.ArrayListJR;
import modelo.Marca;
import modelo.Tipo;
import modelo.Vehiculo;

public class PaginaAgregarVehiculoController implements Initializable {

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

    private File archivoVehiculo1;
    private File archivoVehiculo2;
    private File archivoVehiculo3;
    private ArrayListJR<String> fotos=new ArrayListJR<>();
    
    private void guardarArchivo(File archivoAGuardar) throws IOException {
        String nombreArchivo = archivoAGuardar.getName();
        String nombreAleatorio = generarNombreAleatorio(nombreArchivo);
        fotos.add(nombreAleatorio);
        String rutaDirectorio = App.pathImages; // Reemplaza la ruta con tu ruta real
        Path directorio = Paths.get(rutaDirectorio);
        if (!Files.exists(directorio)) {
            Files.createDirectories(directorio);
        }
        Path rutaDestino = Paths.get(rutaDirectorio, nombreAleatorio);
        Files.copy(archivoAGuardar.toPath(), rutaDestino);
    }

    private String generarNombreAleatorio(String nombreOriginal) {
        String extension = "";
        int indicePunto = nombreOriginal.lastIndexOf('.');
        if (indicePunto >= 0) {
            extension = nombreOriginal.substring(indicePunto);
        }
        String nombreAleatorio = UUID.randomUUID().toString().replace("-", "") + extension;
        return nombreAleatorio;
    }

    private void mostrarAlerta(String title, String message,AlertType t) {
        Alert alerta = new Alert(t);
        alerta.setTitle(title);
        alerta.setHeaderText(null);
        alerta.setContentText(message);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbImagen1.setOnMouseClicked(evento -> {
            FileChooser selectorArchivos = new FileChooser();
            archivoVehiculo1 = selectorArchivos.showOpenDialog((Stage) IVVehiculo1.getScene().getWindow());
            if (archivoVehiculo1 != null) {
                try {
                    Image imagen = new Image(archivoVehiculo1.toURI().toString());
                    IVVehiculo1.setImage(imagen);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        lbImagen2.setOnMouseClicked(evento -> {
            FileChooser selectorArchivos = new FileChooser();
            archivoVehiculo2 = selectorArchivos.showOpenDialog((Stage) IVVehiculo2.getScene().getWindow());
            if (archivoVehiculo2 != null) {
                try {
                    Image imagen = new Image(archivoVehiculo2.toURI().toString());
                    IVVehiculo2.setImage(imagen);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        lbImagen3.setOnMouseClicked(evento -> {
            FileChooser selectorArchivos = new FileChooser();
            archivoVehiculo3 = selectorArchivos.showOpenDialog((Stage) IVVehiculo3.getScene().getWindow());
            if (archivoVehiculo3 != null) {
                try {
                    Image imagen = new Image(archivoVehiculo3.toURI().toString());
                    IVVehiculo3.setImage(imagen);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        LBGuardar.setOnMouseClicked(evento -> {
            if (TFPrecio.getText().isEmpty() || TFMarca.getText().isEmpty() || TFModelo.getText().isEmpty() ||
                TFAño.getText().isEmpty() || TFKilometraje.getText().isEmpty() || TFMotor.getText().isEmpty() ||
                TFTransmision.getText().isEmpty() || TFPeso.getText().isEmpty() || TFUbicacion.getText().isEmpty() ||
                TFTipo.getText().isEmpty() || TFCantidadVentas.getText().isEmpty() || TFUN.getText().isEmpty() ||
                (IVVehiculo1.getImage()==null &&
                IVVehiculo2.getImage()==null &&
                IVVehiculo3.getImage()==null) || (a1.getText().isEmpty() && a2.getText().isEmpty() && a3.getText().isEmpty() &&
                a4.getText().isEmpty() && a5.getText().isEmpty() )){
                mostrarAlerta("Error", "Por favor complete todos los campos y seleccione al menos una imagen.", Alert.AlertType.ERROR);
            }else{
                try {
                    if (archivoVehiculo1 != null) {
                        guardarArchivo(archivoVehiculo1);
                    }
                    if (archivoVehiculo2 != null) {
                        guardarArchivo(archivoVehiculo2);
                    }
                    if (archivoVehiculo3 != null) {
                        guardarArchivo(archivoVehiculo3);
                    }
                    ArrayListJR<String> servicios = new ArrayListJR<>();
                    if (!a1.getText().isEmpty()) {
                        servicios.add(a1.getText());
                    }
                    if (!a2.getText().isEmpty()) {
                        servicios.add(a2.getText());
                    }
                    if (!a3.getText().isEmpty()) {
                        servicios.add(a3.getText());
                    }
                    if (!a4.getText().isEmpty()) {
                        servicios.add(a4.getText());
                    }
                    if (!a5.getText().isEmpty()) {
                        servicios.add(a5.getText());
                    }
                    Marca m = PaginaPrincipalController.encontrarMarcaPorNombre(TFMarca.getText());
                    Tipo t = PaginaPrincipalController.encontrarTipoPorNombre(TFTipo.getText());
                    Vehiculo v = new Vehiculo(Double.valueOf(TFPrecio.getText()),m,TFModelo.getText(),Integer.valueOf(TFAño.getText()),
                    Integer.valueOf(TFKilometraje.getText()),TFMotor.getText(),TFTransmision.getText(),TFPeso.getText(),
                    TFUbicacion.getText(),servicios,t,Integer.valueOf(TFCantidadVentas.getText()),fotos,TFUN.getText());
                    PaginaPrincipalController.vehiculos.add(v);
                    PaginaPrincipalController.escribirArchivo(App.pathFiles+"vehiculos.txt", TFPrecio.getText()+","+TFMarca.getText()+
                            ","+TFModelo.getText()+","+TFAño.getText()+","+TFKilometraje.getText()+","+TFMotor.getText()+","+
                            TFTransmision.getText()+","+TFPeso.getText()+","+TFUbicacion.getText()+","+PaginaPrincipalController.concatenarArrayList(servicios)+
                            ","+TFTipo.getText()+","+TFCantidadVentas.getText()+","+PaginaPrincipalController.concatenarArrayList(fotos)+","+
                            TFUN.getText());
                    mostrarAlerta("Vehiculo guardado", "El vehículo ha sido guardado exitosamente.",Alert.AlertType.INFORMATION);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    mostrarAlerta("Error al Guardar", "Ha ocurrido un error al guardar.",Alert.AlertType.INFORMATION);
                }
            }
        });
    }
}

