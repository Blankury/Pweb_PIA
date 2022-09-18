package Model;

public class Publicacion {
   private int id_publicacion;
   private String fecha, id_usuario_publicacion, id_categoria_publicacion;
   private String titulo, descripcion, estado;

    public Publicacion() {
    }

    //Constructor para enviar a la base de datos
    public Publicacion(String titulo, String descripcion, String estado,  String id_usuario_publicacion, String id_categoria_publicacion) {
        this.id_usuario_publicacion = id_usuario_publicacion;
        this.id_categoria_publicacion = id_categoria_publicacion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    //Constructor para guardar los datos de la base de datos a NetBeans
    public Publicacion(int id_publicacion, String titulo, String descripcion, String estado, String id_usuario_publicacion, String id_categoria_publicacion, String fecha) {
        this.id_usuario_publicacion = id_usuario_publicacion;
        this.id_categoria_publicacion = id_categoria_publicacion;
        this.id_publicacion = id_publicacion;
        this.fecha = fecha;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    public int getId_publicacion() {
        return id_publicacion;
    }

    public String getId_usuario_publicacion() {
        return id_usuario_publicacion;
    }

    public String getId_categoria_publicacion() {
        return id_categoria_publicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }
}
