
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

@WebServlet(name = "Total_publicaciones", urlPatterns = {"/Total_publicaciones"})
public class Total_publicaciones extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap resultado = new HashMap();
        PublicacionDAO pubDAO = new PublicacionDAO();
        
        int cantidad = pubDAO.totalpubs();
        
        if(cantidad > 0){
            resultado.put("Respuesta", true);
            resultado.put("Cantidad", cantidad);


            
        }else{
            resultado.put("Respuesta", false);
            resultado.put("Cantidad", cantidad);

        }
        
        
        String json = new Gson().toJson(resultado);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    


}
