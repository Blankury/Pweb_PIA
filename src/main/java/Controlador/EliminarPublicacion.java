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

/**
 *
 * @author beliz
 */
@WebServlet(name = "EliminarPublicacion", urlPatterns = {"/EliminarPublicacion"})
public class EliminarPublicacion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap resultado = new HashMap();
                
        //Publicacion publicacion = new Publicacion(titulo, descripcion, estado, categoria,id_usuario);
        PublicacionDAO postDAO = new PublicacionDAO();
        String id = request.getParameter("id");
        postDAO.deletePost(id);
        
        resultado.put("resultado", true);

        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();

    }

 

}
