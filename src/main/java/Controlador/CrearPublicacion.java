/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.PublicacionDAO;
import Model.Publicacion;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CrearPublicacion", urlPatterns = {"/CrearPublicacion"})
public class CrearPublicacion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HashMap resultado = new HashMap();
      
        
        String titulo = request.getParameter("Titulo"); /*aqui se toma el name*/
        String descripcion = request.getParameter("Descripcion");
        String estado = "Activo";
        String categoria = request.getParameter("select");
        String id_usuario = request.getParameter("id_user");
        
        
        
        Publicacion publicacion = new Publicacion(titulo, descripcion, estado,id_usuario, categoria);
        PublicacionDAO postDAO = new PublicacionDAO();

        resultado.put("resultado", postDAO.insertPost(publicacion));
        
        resultado.put("publicacion", publicacion);
        
        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
