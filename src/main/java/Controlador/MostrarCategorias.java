package Controlador;

import DAO.CategoriasDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MostrarCategorias", urlPatterns = {"/MostrarCategorias"})
public class MostrarCategorias extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HashMap resultado = new HashMap();
        CategoriasDAO categoriasDAO = new CategoriasDAO(); 
        
        resultado.put("categorias", categoriasDAO.listar());
                
        
        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
