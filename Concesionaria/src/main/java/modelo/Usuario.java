/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import listas.ArrayListJR;

/**
 *
 * @author Kevin
 */
public class Usuario {
    private String usuario;
    private String contrasena;
    private String nombre;
    private String Apellido;
    private ArrayListJR<String> placas;

    public Usuario(String usuario, String contrasena, String nombre, String Apellido, ArrayListJR<String> placas) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.placas = placas;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public ArrayListJR<String> getPlacas() {
        return placas;
    }

    public void setPlacas(ArrayListJR<String> placas) {
        this.placas = placas;
    }

   
}