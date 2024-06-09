
package com.espol.concesionaria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import listas.ArrayListJR;
import modelo.Marca;
import modelo.Tipo;
import modelo.Vehiculo;


/**
 * FXML Controller class
 * estop esnubsunsduasuasdadssdadsasddasads
 * @author Kevin Magallanes
 */
public class PaginaPrincipalController implements Initializable {
    
    public static ArrayListJR<Vehiculo> vehiculos;
    public static ArrayListJR<Marca> marcas;
    public static ArrayListJR<Tipo> tipos;
    public static ArrayListJR<Vehiculo> listaFiltrada;
    @FXML
    private HBox nuevos;
    
    @FXML
    private HBox usados;

    @FXML
    private ComboBox cmbHead;
    
    @FXML
    private ComboBox cmbTipo;
    
    @FXML
    private ComboBox cmbMarca;
    
    @FXML
    private ComboBox cmbModelo;
    
    @FXML
    private ComboBox cmbPrecioDesde;
    
    @FXML
    private ComboBox cmbPrecioHasta;
    
    @FXML
    private ComboBox cmbAñoDesde;
    
    @FXML
    private ComboBox cmbAñoHasta;
    
    @FXML
    private TextField TFHead;
    
    @FXML
    private ImageView IVBuscar;
    
    @FXML
    private Button btnBuscar;
    
    @FXML
    private Button btnVerMas;
    
    @FXML
    private Label LBUser;
    
    @FXML
    private Label LBVende;
    
    @FXML
    private FlowPane fpMasVendidos;
    
    @FXML
    private HBox administrar;
   
    
    public static void llenarListaMarcas(){
        try(BufferedReader br = new BufferedReader(new FileReader(App.pathFiles+"marcas.txt"))){
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                String[] info = linea.split(",");
                Marca m = new Marca(info[0],info[1]);
                marcas.add(m);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void escribirArchivo(String rutaArchivo, String texto) {
        try (BufferedWriter bufferEscritor = new BufferedWriter(new FileWriter(rutaArchivo,true))) {
            bufferEscritor.write(texto+"\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
    public static String concatenarArrayList(ArrayListJR<String> lista) {
        String resultado = "";
        for (int i=0;i<lista.size();i++) {
            String frase = lista.get(i);
            resultado += frase + ";";
        }
        if (!resultado.isEmpty()) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        return resultado;
    }
    
    public static void llenarVehiculosEnContenedor(String usadoONuevo,FlowPane fpVehiculos,ArrayListJR<Vehiculo> vehiculos){
        fpVehiculos.getChildren().clear();
        if (usadoONuevo.equals("Usado") || usadoONuevo.equals("Nuevo")) {
            for(int i = 0; i < vehiculos.size(); i++){
                if(vehiculos.get(i).getUsadoONuevo().equals(usadoONuevo)){
                    Vehiculo a = vehiculos.get(i);
                    VBox v =contenedorParaImagenes(App.pathImages+a.getRutasFotos().get(0),a.getMarca()+" "+a.getModelo()
                    ,a.getAño() + "   "+a.getKilometraje()+" kms .   "+a.getUbicacionActualVehiculo()+"\n"
                    +a.getUsadoONuevo(),"$ "+a.getPrecio());
                    v.setOnMouseClicked(e->{
                        PaginaDetallesVehiculoController.v=a;
                        try {
                            App.abrirNuevaVentana("paginaDetallesVehiculo", 834, 687);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    fpVehiculos.getChildren().add(v);
                }           
            }
        }
        else{
            for(int i = 0; i < vehiculos.size(); i++){
                Vehiculo a = vehiculos.get(i);
                VBox v =contenedorParaImagenes(App.pathImages+a.getRutasFotos().get(0),a.getMarca()+" "+a.getModelo()
                    ,a.getAño() + "   "+a.getKilometraje()+" kms .   "+a.getUbicacionActualVehiculo()+"\n"
                    +a.getUsadoONuevo(),"$ "+a.getPrecio());
                fpVehiculos.getChildren().add(v);        
            }
        }
    }
     
    public static void llenarListaTipos(){
        try(BufferedReader br = new BufferedReader(new FileReader(App.pathFiles+"tipos.txt"))){
            String linea;
            while((linea=br.readLine())!=null){
                String[] info = linea.split(",");
                Tipo t = new Tipo(info[0],info[1]);
                tipos.add(t);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void filtrarVehiculos(){
        ArrayListJR<Vehiculo> lista = vehiculos;
        if(cmbTipo.getValue()!=null){
            lista=vehiculosPorTipo(vehiculos, cmbTipo.getValue());
        }
        if(cmbMarca.getValue()!=null){
            lista=vehiculosPorMarca(lista, cmbMarca.getValue());
        }
        if(cmbModelo.getValue()!=null){
            lista=vehiculosPorModelo(lista, cmbModelo.getValue());
        }
        if(cmbPrecioDesde.getValue()!=null){
            lista=vehiculosPorValor(lista, (int) cmbPrecioDesde.getValue(),"desde");
        }
        if(cmbPrecioHasta.getValue()!=null){
            lista=vehiculosPorValor(lista, (int) cmbPrecioHasta.getValue(),"hasta");
        }
        if(cmbAñoDesde.getValue()!=null){
            lista=vehiculosPorValor(lista,(int)cmbAñoDesde.getValue(),"desde");
        }
        if(cmbAñoHasta.getValue()!=null){
            lista=vehiculosPorValor(lista,(int)cmbAñoHasta.getValue(),"hasta");
        }
        listaFiltrada=lista;
    }
    
    public static VBox contenedorParaImagenes(String ruta,String contenido1,String contenido2,String contenido3){
        VBox v;
        v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.setStyle("-fx-background-color: white;");
        Label lbl = new Label(contenido1);
        lbl.setAlignment(Pos.CENTER);
        lbl.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        Label lbl1 = new Label(contenido2);
        lbl1.setAlignment(Pos.CENTER);
        lbl1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-text-fill: #757575; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        Label lb2 = new Label(contenido3);
        lb2.setAlignment(Pos.CENTER);
        lb2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-text-fill: black; -fx-padding: 4px; -fx-background-color: white; -fx-border-radius: 3px;");
        ImageView iv=null;
        try {
            FileInputStream f = new FileInputStream(ruta);
            Image img =new Image(f,220,150,true,true);
            iv = new ImageView(img);
            iv.setPreserveRatio(true);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        v.getChildren().addAll(iv,lbl,lbl1,lb2);
        return v;
    }
    
    
    public static <T> void ordenar(ArrayListJR<T> elementos, Comparator<T> comparador) {
        PriorityQueue<T> colaPrioridad = new PriorityQueue<>(comparador);
        for (int i=0;i<elementos.size();i++) {
            T elemento = elementos.get(i);
            colaPrioridad.offer(elemento);
        }
        elementos.clear();
        while (!colaPrioridad.isEmpty()) {
            elementos.add(colaPrioridad.poll());
        }
    }
    
    public static ArrayListJR<Vehiculo> vehiculosPorValor(ArrayListJR<Vehiculo> vehiculos,int precio,String limite){
        ArrayListJR<Vehiculo> vehiculosPrecio = new ArrayListJR<>();
        if(limite.equals("desde")){
            for(int i=0;i<vehiculos.size();i++){
                if(vehiculos.get(i).getPrecio()>=precio){
                    vehiculosPrecio.add(vehiculos.get(i));
                }
            }
        }else if(limite.equals("hasta")){
            for(int i=0;i<vehiculos.size();i++){
                if(vehiculos.get(i).getPrecio()<=precio){
                    vehiculosPrecio.add(vehiculos.get(i));
                }
            }
        }
        return vehiculosPrecio;
    }
    
    public static <E> ArrayListJR<Vehiculo> vehiculosPorModelo(ArrayListJR<Vehiculo> vehiculos,E modelo){
        ArrayListJR<Vehiculo> vehiculosPorModelo = new ArrayListJR<>();
        for(int i=0;i<vehiculos.size();i++){
            if(vehiculos.get(i).getModelo().equals(modelo))
                vehiculosPorModelo.add(vehiculos.get(i));
        }
        return vehiculosPorModelo;
    }
    
    public static <E> ArrayListJR<Vehiculo> vehiculosPorMarca(ArrayListJR<Vehiculo> vehiculos,E m){
        ArrayListJR<Vehiculo> array = new ArrayListJR<>();
        for(int i=0;i<vehiculos.size();i++){
            if(vehiculos.get(i).getMarca().equals(m)){
                array.add(vehiculos.get(i));
            }
        }
        return array;
    }
    
    public static Tipo encontrarTipoPorNombre(String nombre){
        for(int i =0;i<tipos.size();i++){
            if(tipos.get(i).getNombre().equals(nombre)){
                return tipos.get(i);
            }
        }
        return null;
    }
    
    public static Marca encontrarMarcaPorNombre(String nombre){
        for(int i =0;i<marcas.size();i++){
            if(marcas.get(i).getNombre().equals(nombre)){
                return marcas.get(i);
            }
        }
        return null;
    }
   
    
    public static void cargarVehiculos(){
        ArrayListJR<Vehiculo> vehiculos1 = new ArrayListJR<>();
        try(BufferedReader br = new BufferedReader(new FileReader(App.pathFiles+"vehiculos.txt"))){
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                String[] info = linea.split(",");
                String[] lista=info[10].split(";");
                String[] listaF =info[13].split(";");
                ArrayListJR<String> accidentesOServicios = new ArrayListJR<>();
                ArrayListJR<String> listaFotos = new ArrayListJR<>();
                for(int i=0;i<lista.length;i++){
                    accidentesOServicios.add(lista[i]);
                }
                for(int i=0;i<listaF.length;i++){
                    listaFotos.add(listaF[i]);
                }
                Tipo t =encontrarTipoPorNombre(info[11]);
                Marca m =encontrarMarcaPorNombre(info[2]);
                vehiculos1.add(new Vehiculo(info[0],Double.valueOf(info[1]),m,info[3],Integer.valueOf(info[4]),Integer.valueOf(info[5]),
                info[6],info[7],info[8],info[9],accidentesOServicios,t,Integer.valueOf(info[12]),listaFotos,info[14]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        vehiculos=vehiculos1;
        
    }
    
    private void llenarCuadroDeFiltro(){
        for(int i=0;i<tipos.size();i++){
            if(!cmbHead.getItems().contains(tipos.get(i)) && !cmbTipo.getItems().contains(tipos.get(i))){
                cmbHead.getItems().add(tipos.get(i));
                cmbTipo.getItems().add(tipos.get(i));
            }
        }
        for(int i=0;i<vehiculos.size();i++){
            Vehiculo v = vehiculos.get(i);
            if(!cmbModelo.getItems().contains(v.getModelo()))
                cmbModelo.getItems().add(v.getModelo());
        }
        for(int i=0;i<marcas.size();i++){
            if(!cmbMarca.getItems().contains(marcas.get(i)))
                cmbMarca.getItems().add(marcas.get(i));
        }
        for(int i=0;i<31;i++){
            cmbPrecioDesde.getItems().add(5000*i);
            cmbPrecioHasta.getItems().add(5000*i);
        }
        for(int i=2005;i<2025;i++){
            cmbAñoDesde.getItems().add(i);
            cmbAñoHasta.getItems().add(i);
        }
    }
    
    public static <E> ArrayListJR<Vehiculo> vehiculosPorTipo(ArrayListJR<Vehiculo> vehiculos,E t){
        ArrayListJR<Vehiculo> array = new ArrayListJR<>();
        for(int i=0;i<vehiculos.size();i++){
            Vehiculo v = vehiculos.get(i);
            if(v.getTipo().equals(t)){
                array.add(v);
            }
        }
        return array;
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipos=new ArrayListJR<>();
        vehiculos = new ArrayListJR<>();
        marcas= new ArrayListJR<>();   
        llenarListaTipos();
        llenarListaMarcas();
        cargarVehiculos(); 
        llenarCuadroDeFiltro();
        
        listaFiltrada=vehiculos;
        LBUser.setText(InicioSesionController.usuario.getNombre()+" "+InicioSesionController.usuario.getApellido());
        nuevos.setOnMouseClicked(e ->{
            Stage ventanaActual = (Stage) nuevos.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAutosNuevos", 1129, 720);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        usados.setOnMouseClicked(e ->{
            Stage ventanaActual = (Stage) usados.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAutosUsados", 929, 722);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        administrar.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) usados.getScene().getWindow();
            ventanaActual.close();
            try {
                App.abrirNuevaVentana("paginaAdministrador", 650, 600);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnVerMas.setOnMouseClicked(e->{
            Stage ventanaActual = (Stage) usados.getScene().getWindow();
            ventanaActual.close();
            PaginaVehiculosOrdenamientoController.ordenamiento="Cantidad de Ventas";
            try {
                App.abrirNuevaVentana("paginaVehiculosOrdenamiento", 929, 722);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        //Vehiculos mas vendidos en pagina principal
        Tipo t = encontrarTipoPorNombre("AUTOS");
        ArrayListJR<Vehiculo> autos = vehiculosPorTipo(vehiculos,t);
        Comparator<Vehiculo> c =(v1,v2)->{
            return v2.getCantidadVentas()-v1.getCantidadVentas();
        };
        ordenar(autos,c);
        for(int i=0;i<6;i++){
            Vehiculo a =autos.get(i);
            VBox v =contenedorParaImagenes(App.pathImages+a.getRutasFotos().get(0),a.getMarca()+" "+a.getModelo()
                    ,a.getAño() + "   "+a.getKilometraje()+" kms . "+a.getUbicacionActualVehiculo()+"\n"
                    +a.getUsadoONuevo(),"$ "+a.getPrecio());
            v.setOnMouseClicked(e->{
                PaginaDetallesVehiculoController.v=a;
                try {
                    App.abrirNuevaVentana("paginaDetallesVehiculo", 834, 687);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            fpMasVendidos.getChildren().add(v);
        }
        //Boton buscar por filtros
        btnBuscar.setOnMouseClicked(e->{
            filtrarVehiculos();
            Stage s =(Stage)btnBuscar.getScene().getWindow();
            s.close();
            try {
                App.abrirNuevaVentana("paginaAutosUsados", 929, 722);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }    
    
    
}
