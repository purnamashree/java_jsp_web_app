package com.venkat.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@MultipartConfig
public class UploadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);

        /*response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        Collection<Part> parts = request.getParts();
        for(Part p : parts){
            if(p.getContentType() != null){
                String fileName = getFilename(p);
                response.getWriter().write("File " + fileName + " successfully uploaded");
            }
        }
*/
        /*Part file = request.getPart("file");
        String fileName = getFilename(file);
        InputStream filecontent = file.getInputStream();
        // ... Do your file saving job here.


        response.getWriter().write("File " + fileName + " successfully uploaded");*/
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        String action = request.getServletPath();

        if(action.contains("/file_uploader")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("upload.jsp");
            dispatcher.forward(request, response);
        }else{
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            String fileName = " ";
            Collection<Part> parts = request.getParts();
            for(Part p : parts){
                if(p.getContentType() != null){
                    fileName += getFilename(p) + ", ";
                }
            }

            response.getWriter().write("File " + fileName + " successfully uploaded");
        }


    }
}
