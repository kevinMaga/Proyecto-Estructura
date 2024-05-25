/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import listas.ArrayList;
/**
 *
 * @author Justin Roldan
 */

public class Vehiculo {
    private double precio;
    private String marca;
    private String modelo;
    private int año;
    private String kilometraje;
    private String motor;
    private String transmisión;
    private double peso;
    private String ubicacionActualVehiculo;
    private ArrayList<String> accidentes;

    public Vehiculo(double precio, String marca, String modelo, int año, String kilometraje, String motor, String transmisión, double peso, String ubicacionActualVehiculo, ArrayList<String> accidentes) {
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmisión = transmisión;
        this.peso = peso;
        this.ubicacionActualVehiculo = ubicacionActualVehiculo;
        this.accidentes = accidentes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getTransmisión() {
        return transmisión;
    }

    public void setTransmisión(String transmisión) {
        this.transmisión = transmisión;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getUbicacionActualVehiculo() {
        return ubicacionActualVehiculo;
    }

    public void setUbicacionActualVehiculo(String ubicacionActualVehiculo) {
        this.ubicacionActualVehiculo = ubicacionActualVehiculo;
    }

    public ArrayList<String> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(ArrayList<String> accidentes) {
        this.accidentes = accidentes;
    }
    
}
