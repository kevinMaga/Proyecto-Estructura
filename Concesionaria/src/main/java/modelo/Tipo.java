
package modelo;

/**
 *
 * @author Justin Roldan
 */
public class Tipo {
    private String nombre;
    private String foto;

    public Tipo(String nombre, String foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
    
}
