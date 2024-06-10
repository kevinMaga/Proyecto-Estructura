/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.espol.concesionaria;

import modelo.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listas.ArrayListJR;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class InicioSesionController implements Initializable {

    public static Usuario usuario;
    @FXML
    private TextField TFUsuario;
    @FXML
    private TextField TFContrasena;
    @FXML
    private Button BTIngresar;

    private boolean usuarioEncontrado = false;

    public static String generarPlacaAleatoria() {
        // Lista de letras permitidas en una placa
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // Generador de números aleatorios
        Random rand = new Random();
        // Inicializar una cadena para la placa
        StringBuilder placa = new StringBuilder();
        
        // Generar las tres primeras letras de la placa
        for (int i = 0; i < 3; i++) {
            // Obtener un índice aleatorio para seleccionar una letra de la lista
            int index = rand.nextInt(letras.length());
            // Agregar la letra seleccionada a la placa
            placa.append(letras.charAt(index));
        }
        
        // Generar los tres últimos dígitos de la placa
        for (int i = 0; i < 3; i++) {
            // Generar un número aleatorio entre 0 y 9
            int numero = rand.nextInt(10);
            // Agregar el número generado a la placa
            placa.append(numero);
        }
        return placa.toString();
    }

    public static ArrayListJR<Usuario> cargarUsuarios() {
        ArrayListJR<Usuario> usuarios = new ArrayListJR<>();
        try (BufferedReader br = new BufferedReader(new FileReader(App.pathFiles + "usuarios.txt"))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String[] info = datos[4].split(";");
                ArrayListJR<String> placas = new ArrayListJR<>();
                for (String s : info) {
                    placas.add(s);
                }
                Usuario user = new Usuario(datos[0], datos[1], datos[2], datos[3], placas);
                usuario = user;
                usuarios.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayListJR<Usuario> usuarios = cargarUsuarios();
        BTIngresar.setOnAction(e -> {
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario user = usuarios.get(i);
                if (user.getContrasena().equals(TFContrasena.getText()) && user.getUsuario().equals(TFUsuario.getText())) {
                    usuario = usuarios.get(i);
                    usuarioEncontrado = true;
                    Stage s = (Stage) BTIngresar.getScene().getWindow();
                    s.close();
                    try {
                        App.abrirNuevaVentana("paginaPrincipal", 929, 628);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (!usuarioEncontrado) {
                PaginaAgregarVehiculoController.mostrarAlerta("Error", "Usuario o contraseña incorrecto", Alert.AlertType.ERROR);
            }
        });
    }

}
