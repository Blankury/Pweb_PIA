package Controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RevisarSesion", urlPatterns = {"/RevisarSesion"})
public class RevisarSesion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap respuesta = new HashMap();
        HttpSession sesion = request.getSession();
        
        if(sesion.getAttribute("id_usuario") != null){
            respuesta.put("usuario", sesion.getAttribute("id_usuario"));
            respuesta.put("resultado", true);
        }
        else{
            respuesta.put("resultado", false);
        }
       
        String json = new Gson().toJson(respuesta);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
