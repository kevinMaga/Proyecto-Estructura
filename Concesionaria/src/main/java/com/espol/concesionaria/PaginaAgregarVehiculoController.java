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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import listas.ArrayListJR;

public class PaginaAgregarVehiculoController implements Initializable{

    @FXML
    private HBox hb;
    
    
    private List<File> selectedFiles;
    
    @FXML
    private ImageView i;
    
    @FXML
    private Label mensajeImagen;

    @FXML
    private void agregarImagen() {
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
            String destino = "src/main/resources/images/"; // Ruta específica donde deseas guardar las imágenes
            File destinoFile = new File(destino + imagenFile.getName());
            Files.copy(sourcePath, destinoFile.toPath());
            // Puedes mostrar un mensaje de éxito o realizar otras acciones aquí
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i.setOnMouseClicked(e->{
            agregarImagen();
        });
    }
}
