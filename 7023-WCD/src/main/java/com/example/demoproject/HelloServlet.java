package com.example.demoproject;

import java.io.*;
import java.util.Enumeration;

import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
//
//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello babe!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        String name = getInitParameter("name");
        if(name != null) {
            message = name;
        }
        Cookie[] cookies = request.getCookies();
        Enumeration<String> headers = request.getHeaderNames();
        String className = request.getParameter("name");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1> Cookie </h1>");
        for(int i= 0; i< cookies.length; i++) {
            Cookie c = cookies[i];
            out.println("<p> cookie name: " + c.getName() + "  value :"+ c.getValue()+ "</p>");
        }
        out.println("<h1> Header name and value </h1>");
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String value = request.getHeader(headerName);
            out.println("<p> header name: " + headerName + " header value :"+ value + "</p>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}