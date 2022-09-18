/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import javax.servlet.http.Part;

@WebServlet(name = "InsertarUsuario", urlPatterns = {"/InsertarUsuario"})
@MultipartConfig(maxFileSize = 16177216) //1.5MB

public class InsertarUsuario extends HttpServlet {

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
        String contraseña = request.getParameter("Contra");
        String nacimiento = request.getParameter("FechaNacimiento");
        Part filePart = request.getPart("fotoPerfil");

        String nombreArchivo = String.valueOf(System.currentTimeMillis());
        filePart.write(urlFile + "/" + nombreArchivo + extraerExtension(filePart) );
        
        String imagen = "FotosImg/" + nombreArchivo + extraerExtension(filePart);
        
        Usuario user = new Usuario(username, nombre, apellido, correo, contraseña, nacimiento, imagen);
        if (userDAO.insertUser(user)){
            resultado.put("resultado", true);
        }
        else{
            resultado.put("resultado", false);
        }
        
        String json = new Gson().toJson(resultado);
        
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
