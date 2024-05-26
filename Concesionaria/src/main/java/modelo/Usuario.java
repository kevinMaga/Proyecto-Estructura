/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Rol;

/**
 *
 * @author Kevin
 */
public class Usuario {
    private String usuario;
    private String contrasena;
    private Rol rol;
    public Usuario(String usuario, String contrasena, Rol rol){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Rol getRol() {
        return rol;
    }
    
}
