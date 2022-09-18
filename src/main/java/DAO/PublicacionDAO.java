package DAO;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import Interfaces.PublicacionCRUD;
import Model.Publicacion;
import java.util.ArrayList;

public class PublicacionDAO implements PublicacionCRUD {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Publicacion post;
    
    @Override
    public boolean insertPost(Publicacion _post){
        String query = "INSERT INTO publicacion (titulo, descripcion, estado, id_usuario_publicacion, id_categoria_publicacion) VALUES ('" + _post.getTitulo() + "', '" + _post.getDescripcion() +"','" + _post.getEstado()+"', " + _post.getId_usuario_publicacion() + ","+ _post.getId_categoria_publicacion() + ");";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            int resultado = ps.executeUpdate();
            if (resultado > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
            return false;
        }
    }
    
     @Override
    public boolean updatePost(String titulo, String descripcion, String categoria, String id){
         String query = "UPDATE publicacion SET titulo = '"+ titulo +"', descripcion = '"+ descripcion +"', id_categoria_publicacion = " + categoria +" WHERE id_publicacion = " + id +";";
         try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            int resultado = ps.executeUpdate();
            
            if(resultado > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
            return false;
        }

    }
    
    public Publicacion identificar(String id){
        String query = "Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as id_categoria_publicacion, p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion  AND p.id_publicacion ="+ id +" ;";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()){
                post = new Publicacion (rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("estado"), rs.getString("id_usuario_publicacion"), rs.getString("id_categoria_publicacion"), rs.getString("fecha"));
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
        }
        return post;
    }
    
    @Override
    public boolean deletePost(String id){
        //String query = "DELETE FROM publicacion WHERE id_publicacion =" + _post.getId_publicacion() +";";
        String query = "UPDATE publicacion SET estado = 'Eliminado' WHERE id_publicacion = "+id +";";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            int resultado = ps.executeUpdate();
            if (resultado > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
            return false;
        }
    }
    
    public int totalpubs(){
        String query = "SELECT COUNT(*) AS Total FROM publicacion";
        
        try{
           con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
            if (rs.next()){
               return rs.getInt("Total");
            }
        }
        catch(Exception ex){
            System.out.println("" + ex);
            return  0;
        } finally{
            //cerrarConexiones();
        }
        return 0;
    }
    
    @Override
    public List listar(){
        ArrayList<Publicacion> list = new ArrayList();
        String sql = "Select id_publicacion, id_usuario_publicacion, id_categoria_publicacion, fecha, titulo, descripcion, estado FROM publicacion;";
    
        try{
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
                Publicacion temppub = new Publicacion (rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("estado"), rs.getString("id_usuario_publicacion"), rs.getString("id_categoria_publicacion"), rs.getString("fecha"));
                list.add(temppub);
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return list;
    }
    
    public List listar(int indice, int cantidad){
        ArrayList<Publicacion> list = new ArrayList();
        //String sql = "Select id_publicacion, id_usuario_publicacion, id_categoria_publicacion, fecha, titulo, descripcion, estado FROM publicacion LIMIT ?, ?;";
    String sql = "Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as id_categoria_publicacion, "
            + "p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join "
            + "usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion AND p.estado = 'Activo'"
            + " ORDER BY p.id_publicacion ASC LIMIT ?,?;";

        try{
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, indice);
            ps.setInt(2, cantidad);
            rs = ps.executeQuery();
            
            
            while (rs.next()){
                list.add(new Publicacion (rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("estado"), rs.getString("id_usuario_publicacion"), rs.getString("id_categoria_publicacion"), rs.getString("fecha")));
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return list;
    }
    
    
    public List listar_por_categoria(int indice, int cantidad, int categoria){
        ArrayList<Publicacion> list = new ArrayList();
        String sql = "Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as id_categoria_publicacion, "
            + "p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join "
            + "usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion AND p.estado = 'Activo'"
            + " AND c.id_categoria = " + categoria +" ORDER BY p.id_publicacion ASC LIMIT ?,?;";
    

        try{
           con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, indice);
            ps.setInt(2, cantidad);
            rs = ps.executeQuery();
            
            
            while (rs.next()){
                list.add(new Publicacion (rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("estado"), rs.getString("id_usuario_publicacion"), rs.getString("id_categoria_publicacion"), rs.getString("fecha")));
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        return list;
    }
     
    public List buscarPost (int indice, int cantidad, String desc){
       ArrayList<Publicacion> list = new ArrayList();
         String query = "Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as id_categoria_publicacion, "
            + "p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join "
            + "usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion AND p.estado = 'Activo'"
            + " AND p.descripcion LIKE \"%" + desc + "%\" ORDER BY p.id_publicacion ASC LIMIT ?,?;";
     
       
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(query);
           ps.setInt(1, indice);
           ps.setInt(2, cantidad);
           rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Publicacion (rs.getInt("id_publicacion"), rs.getString("titulo"), rs.getString("descripcion"),
                        rs.getString("estado"), rs.getString("id_usuario_publicacion"), rs.getString("id_categoria_publicacion"), rs.getString("fecha")));
            }
            
        }
        catch(Exception ex){
            System.out.println("" + ex);
        } finally{
            //cerrarConexiones();
        }
        return list;

    }
    public boolean buscarPostAvanzado(String desc){
       String query = "Select p.id_publicacion, u.nombre as id_usuario_publicacion, c.nombre as nombre_categoria_publicacion, c.id_categoria as id_cat, p.fecha, p.titulo, p.descripcion, p.estado  FROM publicacion as p inner join categorias as c inner join usuarios as u  WHERE c.id_categoria = p.id_categoria_publicacion AND u.id_usuario = p.id_usuario_publicacion AND p.estado = 'Activo' AND p.descripcion LIKE \"%"+desc+"%\" ORDER BY p.id_publicacion ASC;";
       try{
           con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
        }
        catch(Exception ex){
            System.out.println("" + ex);
        } finally{
            //cerrarConexiones();
        }
                 return true;     
    }
   
    
}
