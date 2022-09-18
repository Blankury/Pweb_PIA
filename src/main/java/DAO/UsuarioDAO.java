/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.Conexion;
import Interfaces.UserCRUD;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author beliz
 */
public class UsuarioDAO implements UserCRUD{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuario user;
    
    @Override
    public List SelectUsuarios(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @Override
    public boolean insertUser(Usuario _user){
        String query = "INSERT INTO usuarios (nombre, apellido, correo, contraseña, usuario, nacimiento, imagen) VALUES('" + _user.getNombre() + "', '" + _user.getApellido() +"', '" + _user.getCorreo() +"', '" + _user.getContraseña() + "', '" + _user.getUsuario() +"',  '" + _user.getNacimiento() + "', '" + _user.getImagen() + "');"; 

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
    
    public Usuario identificar(Usuario _user){
        String query = "SELECT id_usuario, nombre, apellido, correo, contraseña, usuario, nacimiento, imagen, fecha FROM usuarios WHERE usuario = '" + _user.getUsuario() + "' AND contraseña = '" + _user.getContraseña() + "';";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()){
                user = new Usuario(rs.getInt("id_usuario"), rs.getString("usuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo"),  rs.getString("contraseña"),  rs.getString("nacimiento"), rs.getString("imagen"), rs.getString("fecha"));
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
        }
        return user;
    }
    
    
     public Usuario identificar2(String id){
        String query = "SELECT id_usuario, nombre, apellido, correo, contraseña, usuario, nacimiento, imagen, fecha FROM usuarios WHERE id_usuario = " + id + ";";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()){
                user = new Usuario(rs.getInt("id_usuario"), rs.getString("usuario"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("correo"),  rs.getString("contraseña"),  rs.getString("nacimiento"), rs.getString("imagen"), rs.getString("fecha"));
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
        }
        return user;
    }
     
     
    @Override
    public boolean updateUser(String username, String nombre, String apellido,String correo, String nacimiento, String imagen, String id){

        String query = "UPDATE usuarios SET nombre = '" + nombre + "', apellido = '" + apellido + "', correo = '" + correo +"', usuario = '" + username + "', imagen = '" + imagen + "', nacimiento = '" +  nacimiento + "' WHERE id_usuario = "+ id +";";
        
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
}
