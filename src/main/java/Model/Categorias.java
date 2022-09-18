
package Model;

/**
 *
 * @author beliz
 */
public class Categorias {
    private int id_categoria;
    private String nombre;

    public Categorias() {
    }
    //Constructor que trae informacion de la base de datos
    public Categorias(int id_categoria, String nombre) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public String getNombre() {
        return nombre;
    }
    
}
