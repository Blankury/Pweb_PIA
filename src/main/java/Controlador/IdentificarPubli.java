package Controlador;

import DAO.CategoriasDAO;
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

@WebServlet(name = "IdentificarPubli", urlPatterns = {"/IdentificarPubli"})
public class IdentificarPubli extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HashMap resultado = new HashMap();
        PublicacionDAO userDao = new PublicacionDAO();
        CategoriasDAO categoriasDAO = new CategoriasDAO(); 
        String id = request.getParameter("id_");
        
      
        

                
        Publicacion respuestapub = userDao.identificar(id);
        
        if (respuestapub != null){
            resultado.put("respuesta", true);
            resultado.put("post", respuestapub);
            resultado.put("categorias", categoriasDAO.listar());
        }
        else{
            resultado.put("respuesta", false);
        }
        
   
        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }


}
