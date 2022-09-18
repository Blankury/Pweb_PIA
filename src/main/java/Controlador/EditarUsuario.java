package Controlador;

import DAO.UsuarioDAO;
import Model.Usuario;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author beliz
 */
@WebServlet(name = "EditarUsuario", urlPatterns = {"/EditarUsuario"})
@MultipartConfig(maxFileSize = 16177216) //1.5MB

public class EditarUsuario extends HttpServlet {
     private String extraerExtension(Part part){
        String content = part.getHeader("content-disposition");
        String[] items = content.split(";");
        for (String s:items){
            if (s.trim().startsWith("filename")){
                String filename = s.substring(s.indexOf("=") + 2 , s.length()-1);
                return filename.substring(filename.indexOf("."), filename.length());
            }
        }
    return "";
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HashMap resultado = new HashMap();
        UsuarioDAO userDAO = new UsuarioDAO();
        
        String urlFile = getServletContext().getRealPath("/FotosImg/");
        File fdir = new File(urlFile);
        if (!fdir.exists()){
            fdir.mkdir();
        }
        
        String username = request.getParameter("User");
        String nombre = request.getParameter("Nombre");
        String apellido = request.getParameter("Apellidos");
        String correo = request.getParameter("Correo");
        String nacimiento = request.getParameter("FechaNacimiento");
        String id = request.getParameter("id_user");
        String imagen;
        Part filePart = request.getPart("fotoPerfile");
        
        try{
            String nombreArchivo = String.valueOf(System.currentTimeMillis());
            filePart.write(urlFile + "/" + nombreArchivo + extraerExtension(filePart) );
            imagen = "FotosImg/" + nombreArchivo + extraerExtension(filePart);
        }
        catch (Exception ex){
            imagen = request.getParameter("fotoPerfil");
        }
        
        if (userDAO.updateUser(username, nombre, apellido, correo, nacimiento, imagen, id)){
            resultado.put("resultado", true);
            }
            
        else
        {
            resultado.put("resultado", false);
        }
        
        Usuario respuestaUsuario = userDAO.identificar2(id);
        
        HttpSession sesion;

        if (respuestaUsuario != null){
            sesion = request.getSession();
            sesion.setAttribute("id_usuario", respuestaUsuario);
        }
        
        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }


}
