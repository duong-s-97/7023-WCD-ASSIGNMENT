package com.example.demoproject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/UpLoadServlet")
@MultipartConfig(maxFileSize = 1024*10,maxRequestSize = 1024*50)

public class UpLoadServlet extends HttpServlet  {
    private String message;
    private  Boolean login = true;

    private static final long serialVersionUID = 1L;


    public void init() {
        message = "Upload thành công";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher rd = request.getRequestDispatcher("webapp/index.jsp");
        rd.forward(request,response);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> "+ message+"</h1>");
        out.println("<html><body>");
//        out.println("<html><body>");
//        if (login ) {
//            out.println("<form action=\"/upload\" method=\"post\" enctype=\"multipart/form-data\">");
//
//            out.println("<input type=\"file\" name=\"file\">");
//            out.println("<input type=\"submit\" value=\"Upload\">");
//        }else {
//            out.println("<h1> Đăng nhập để upload file</h1>");
//        }
//
//        out.println("<html><body>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part part = req.getPart("file");
            String realPart = req.getServletContext().getRealPath("/files");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Path.of(realPart))) {
                Files.createDirectory(Path.of(realPart));
            }

            part.write(realPart+"/"+fileName);
        }catch (Exception e) {

        }
    }





    public void destroy() {
    }
}
