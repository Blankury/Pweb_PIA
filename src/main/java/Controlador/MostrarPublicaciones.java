/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.PublicacionDAO;
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
@WebServlet(name = "MostrarPublicaciones", urlPatterns = {"/MostrarPublicaciones"})
public class MostrarPublicaciones extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HashMap resultado = new HashMap();
        PublicacionDAO pubDAO = new PublicacionDAO();
        
        String Indice = request.getParameter("indice");
        String Cantidad = request.getParameter("cantidad");
        
        resultado.put("publicacion", pubDAO.listar(Integer.parseInt(Indice), Integer.parseInt(Cantidad)));
        
        String json = new Gson().toJson(resultado);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
       
}
