
package Controlador;

import DAO.UsuarioDAO;
import Model.Usuario;
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

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion;
        HashMap resultado = new HashMap();
        UsuarioDAO userDao = new UsuarioDAO();
        
        String username = request.getParameter("User");
        String contraseña = request.getParameter("Nombre");
        
        Usuario user = new Usuario(username,contraseña);
        Usuario respuestaUsuario = userDao.identificar(user);
        
        if (respuestaUsuario != null){
            sesion = request.getSession();
            sesion.setAttribute("id_usuario", respuestaUsuario);
            resultado.put("respuesta", true);            
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
