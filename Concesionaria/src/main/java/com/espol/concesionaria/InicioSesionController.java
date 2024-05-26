/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
*/
package com.espol.concesionaria;
 
import modelo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import listas.ArrayList;
import modelo.Rol;
 
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
    public static ArrayList<Usuario> cargarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/resources/files/usuarios.txt"))){
            br.readLine();
            String linea;
            while((linea=br.readLine())!=null){
                String[] datos = linea.split(",");
                Usuario user = new Usuario(datos[0],datos[1],Rol.valueOf(datos[2]));
                usuario=user;
                usuarios.add(user);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return usuarios;
    }
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Usuario> usuarios = cargarUsuarios();
        BTIngresar.setOnAction(e ->{
            for(int i=0;i<usuarios.size();i++){
                Usuario user =usuarios.get(i);
                if(user.getContrasena().equals(TFContrasena.getText()) && user.getUsuario().equals(TFUsuario.getText())){
                //if (user.getContrasena().contains(TFContrasena.getText() && user.getUsuario().contains(TFUsuario.getText()))){
                    usuario=usuarios.get(i);
                    if(usuario.getRol().equals(Rol.Administrador)){
                        Stage s =(Stage)BTIngresar.getScene().getWindow();
                        s.close();
                        try {
                            App.abrirNuevaVentana("paginaPrincipal",929,628);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else{
                        Stage s =(Stage)BTIngresar.getScene().getWindow();
                        s.close();
                        try {
                            App.abrirNuevaVentana("paginaPrincipal",929,628);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }
 
   
}