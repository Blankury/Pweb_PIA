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
@WebServlet(name = "EditarPublicacion", urlPatterns = {"/EditarPublicacion"})
public class EditarPublicacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HashMap resultado = new HashMap();
        PublicacionDAO userDao = new PublicacionDAO();
        String id = request.getParameter("id_");
        String combo = request.getParameter("combo");
        String titulo = request.getParameter("titulo");
        String desc = request.getParameter("descripcion");

        Publicacion respuestapub = userDao.identificar(id);

        if (respuestapub != null){
            resultado.put("respuesta", true);
            resultado.put("post", respuestapub);
        }
        else{
            resultado.put("respuesta", false);
        }
        
        if (userDao.updatePost(titulo, desc, combo, id )){
            resultado.put("respuesta2", true);
            resultado.put("editado", true);
            
        }
        else{
            resultado.put("respuesta2", false);
        }
        
        
        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }


}
