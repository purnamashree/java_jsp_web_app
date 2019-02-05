package com.venkat.controller;

import com.venkat.enity.Resume;
import com.venkat.service.ResumeUploadService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@MultipartConfig
public class UploadController extends HttpServlet {

    private ResumeUploadService resumeService;

    public void init() {
        resumeService = new ResumeUploadService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
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
            throws ServletException, IOException {

        String action = request.getServletPath();
        try {
            if (action.contains("/file_uploader")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("upload2.jsp");
                dispatcher.forward(request, response);
            } else if(action.contains("/uploader")) {
                insertEmp(request, response);
            }else if(action.contains("/resume_list")) {
                listResumes(request, response);
            }else if(action.contains("/delete")) {
                deleteResume(request, response);
            }else if(action.contains("/download")) {
                downloadResume(request, response);
            }else{
                listResumes(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    private void listResumes(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {

        List<Resume> listResume = resumeService.fetchResumes();
        request.setAttribute("listResumes", listResume);
        RequestDispatcher dispatcher = request.getRequestDispatcher("resumesList.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEmp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,SQLException, IOException, ClassNotFoundException {

        //List<String> files = new ArrayList<>();
        Collection<Part> parts = request.getParts();
        for(Part p : parts){
            System.out.println(p.getName());
            System.out.println(p.getSize());
            System.out.println(p.getContentType());

            InputStream inputStream  = p.getInputStream();
            String name = getFilename(p);

            Resume resume = new Resume(name, inputStream);
            resumeService.addResume(resume);

            /*if(p.getContentType() != null){
                files.add(getFilename(p));
            }*/
        }
        response.sendRedirect("resume_list");
    }

    private void deleteResume(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("id"));

        Resume resume = new Resume(id);
        resumeService.deleteResume(resume);
        response.sendRedirect("resume_list");

    }

    private void downloadResume(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {

        int id = Integer.parseInt(request.getParameter("id"));

        Resume resume = resumeService.getResume(id);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String filename = resume.getName();
        InputStream inputStream = resume.getContent();
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + filename + "\"");

        int i;
        while ((i = inputStream.read()) != -1) {
            out.write(i);
        }
        inputStream.close();
        out.close();
        response.sendRedirect("resume_list");
    }
}
