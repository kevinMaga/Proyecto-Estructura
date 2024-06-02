/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;


/**
 *
 * @author Justin Roldan
 */

public class Vehiculo{
    private double precio;
    private String marca;
    private String modelo;
    private int año;
    private String kilometraje;
    private String motor;
    private String transmisión;
    private String peso;
    private String ubicacionActualVehiculo;
    private ArrayList<String> accidentesOServicios;
    private Tipo tipo;
    private int cantidadVentas;
    private ArrayList<String> rutasFotos;
    private String usadoONuevo;

    public Vehiculo(double precio, String marca, String modelo, int año, String kilometraje, String motor, String transmisión, String peso, String ubicacionActualVehiculo, ArrayList<String> accidentesOServicios, Tipo tipo, int cantidadVentas, ArrayList<String> rutasFotos, String usadoONuevo) {
        this.precio = precio;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.kilometraje = kilometraje;
        this.motor = motor;
        this.transmisión = transmisión;
        this.peso = peso;
        this.ubicacionActualVehiculo = ubicacionActualVehiculo;
        this.accidentesOServicios = accidentesOServicios;
        this.tipo = tipo;
        this.cantidadVentas = cantidadVentas;
        this.rutasFotos = rutasFotos;
        this.usadoONuevo = usadoONuevo;
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getUbicacionActualVehiculo() {
        return ubicacionActualVehiculo;
    }

    public void setUbicacionActualVehiculo(String ubicacionActualVehiculo) {
        this.ubicacionActualVehiculo = ubicacionActualVehiculo;
    }

    public ArrayList<String> getAccidentesOServicios() {
        return accidentesOServicios;
    }

    public void setAccidentesOServicios(ArrayList<String> accidentesOServicios) {
        this.accidentesOServicios = accidentesOServicios;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public ArrayList<String> getRutasFotos() {
        return rutasFotos;
    }

    public void setRutasFotos(ArrayList<String> rutasFotos) {
        this.rutasFotos = rutasFotos;
    }

    public String getUsadoONuevo() {
        return usadoONuevo;
    }

    public void setUsadoONuevo(String usadoONuevo) {
        this.usadoONuevo = usadoONuevo;
    }

}