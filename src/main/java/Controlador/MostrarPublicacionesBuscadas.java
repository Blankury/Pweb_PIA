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
@WebServlet(name = "MostrarPublicacionesBuscadas", urlPatterns = {"/MostrarPublicacionesBuscadas"})
public class MostrarPublicacionesBuscadas extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HashMap resultado = new HashMap();
        PublicacionDAO pubDAO = new PublicacionDAO();
        
        String Indice = request.getParameter("indice");
        String Cantidad = request.getParameter("cantidad");
        String Desc = request.getParameter("descripcion");
        Desc = Desc.replace("'", ""); 
        
        resultado.put("publicacion", pubDAO.buscarPost(Integer.parseInt(Indice), Integer.parseInt(Cantidad), Desc));
        
        String json = new Gson().toJson(resultado);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }


}
