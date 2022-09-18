package Model;


public class Usuario {
    private int id_usuario;
    private String usuario, nombre, apellido, correo, contraseña, imagen, nacimiento, fecha;
    
    public Usuario(){
    }

    public Usuario (String usuario, String contraseña){
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    
    //Lleva esta información  a la base de datos
    public Usuario(String usuario, String nombre, String apellido, String correo, String contraseña, String nacimiento, String imagen){
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.contraseña = contraseña;
        this.imagen = imagen;
    }
    //Usado para guardar la información que traje de la base de datos
    public Usuario(int id_usuario, String usuario, String nombre, String apellido, String correo, String contraseña, String nacimiento, String imagen, String fecha) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nacimiento = nacimiento;
        this.imagen = imagen;
        this.fecha = fecha;
                this.id_usuario = id_usuario;
    }
    
    public int getIdusuario() {
        return id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public String getFecha() {
        return fecha;
    }
}


