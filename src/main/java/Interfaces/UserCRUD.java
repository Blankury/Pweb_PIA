package Interfaces;

import Model.Usuario;
import java.util.List;

public interface UserCRUD {
    public List SelectUsuarios();
    public boolean insertUser(Usuario user);
    public boolean updateUser(String username, String nombre, String apellido,String correo, String nacimiento, String imagen, String id);
}
