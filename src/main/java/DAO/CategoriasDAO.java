package DAO;

import Config.Conexion;
import Interfaces.CategoriasCRUD;
import Model.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO implements CategoriasCRUD{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;    
    
    @Override
    public List listar(){
        ArrayList<Categorias> list = new ArrayList();
        String query = "SELECT id_categoria, nombre FROM categorias;";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            
             while (rs.next()){
                Categorias tempcat = new Categorias (rs.getInt("id_categoria"), rs.getString("nombre"));
                list.add(tempcat);
            }
        }
        catch (Exception ex){
            System.out.println("Error; " + ex);
        }
        return list;
    }
}
