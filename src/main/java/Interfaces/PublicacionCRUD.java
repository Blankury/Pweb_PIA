package Interfaces;

import Model.Publicacion;
import java.util.List;


public interface PublicacionCRUD {
    public List listar();
    public boolean insertPost(Publicacion post);
    public boolean updatePost(String titulo, String descripcion, String categoria, String id);
    public boolean deletePost(String id);
}
