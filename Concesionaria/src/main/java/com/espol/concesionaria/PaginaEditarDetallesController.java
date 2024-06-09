package com.espol.concesionaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import listas.ArrayListJR;
import modelo.Vehiculo;

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

    private File archivoVehiculo1;
    private File archivoVehiculo2;
    private File archivoVehiculo3;

    private Vehiculo vehiculo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IVInicio.setOnMouseClicked(e -> {
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaPrincipal", 929, 681);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        volver.setOnMouseClicked(e -> {
            Stage ventanaActual = (Stage) IVInicio.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaEditar", 800, 700);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        lbImagen1.setOnMouseClicked(evento -> {
            FileChooser selectorArchivos = new FileChooser();
            archivoVehiculo1 = selectorArchivos.showOpenDialog((Stage) IVVehiculo1.getScene().getWindow());
            if (archivoVehiculo1 != null) {
                try {
                    Image imagen = new Image(archivoVehiculo1.toURI().toString());
                    IVVehiculo1.setImage(imagen);
                    guardarArchivo(archivoVehiculo1, 0);
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
                    guardarArchivo(archivoVehiculo2, 1);
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
                    guardarArchivo(archivoVehiculo3, 2);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        vehiculo = PaginaEditarController.vehiculo;
        try {
            llenarCampos(vehiculo);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        LBGuardar.setOnMouseClicked(event -> {
            PaginaAgregarVehiculoController.mostrarAlerta("Vehiculo Editado", "Se ha editado el vehiculo correctamente", Alert.AlertType.INFORMATION);
            Stage s = (Stage) lbImagen1.getScene().getWindow();
            s.close();
            try {
                guardarCambios(vehiculo);
                App.abrirNuevaVentana("paginaAdministrador", 650, 600);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void guardarArchivo(File archivoAGuardar, int index) throws IOException {
        String nombreArchivo = archivoAGuardar.getName();
        String nombreAleatorio = generarNombreAleatorio(nombreArchivo);
        String rutaDirectorio = App.pathImages; // Reemplaza la ruta con tu ruta real
        Path directorio = Paths.get(rutaDirectorio);
        if (!Files.exists(directorio)) {
            Files.createDirectories(directorio);
        }
        Path rutaDestino = Paths.get(rutaDirectorio, nombreAleatorio);
        Files.copy(archivoAGuardar.toPath(), rutaDestino);
        
        // Actualizar la lista de rutas de imágenes del vehículo
        if (vehiculo.getRutasFotos().size() > index) {
            vehiculo.getRutasFotos().set(index, nombreAleatorio);
        } else {
            vehiculo.getRutasFotos().add(nombreAleatorio);
        }
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

        if (v.getRutasFotos().size() > 0)
            IVVehiculo1.setImage(new Image(new FileInputStream(App.pathImages + v.getRutasFotos().get(0))));
        if (v.getRutasFotos().size() > 1)
            IVVehiculo2.setImage(new Image(new FileInputStream(App.pathImages + v.getRutasFotos().get(1))));
        if (v.getRutasFotos().size() > 2)
            IVVehiculo3.setImage(new Image(new FileInputStream(App.pathImages + v.getRutasFotos().get(2))));
    }

    private void guardarCambios(Vehiculo v) throws IOException {
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
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).equals(v)) {
                PaginaPrincipalController.vehiculos.set(i, v);
            }
        }

        // Guardar los cambios en el archivo
        File archivo = new File(App.pathFiles + "vehiculos.txt");

        ArrayListJR<String> lineas = new ArrayListJR<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] info = linea.split(",");
                if (info[0].equals(v.getPlaca())) {
                    String nuevaLinea = v.getPlaca() + "," + v.getPrecio() + "," + v.getMarca().toString()
                            + "," + v.getModelo() + "," + v.getAño() + "," + v.getKilometraje() + "," + v.getMotor() + ","
                            + v.getTransmisión() + "," + v.getPeso() + "," + v.getUbicacionActualVehiculo() + "," + PaginaPrincipalController.concatenarArrayList(v.getAccidentesOServicios())
                            + "," + v.getTipo().toString() + "," + v.getCantidadVentas() + "," + PaginaPrincipalController.concatenarArrayList(v.getRutasFotos()) + ","
                            + v.getUsadoONuevo();
                    linea = nuevaLinea;
                }
                lineas.add(linea);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Escribir todas las líneas de nuevo en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                bw.write(linea + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
