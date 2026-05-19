/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Year;

/**
 *
 * @author HP
 */
public class InfoServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InfoServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InfoServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InfoServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            try {
                String name = request.getParameter("name");
                if(name==null){
                    out.println("Name is null<br/>");
                }else{
                    out.println("Name: "+name+"<br/>");
                }
                //int yob = Integer.parseInt(request.getParameter("yob"));
            } catch (Exception e) {
                out.println("Error read the name!");
            }
            try {
                int yob = Integer.parseInt(request.getParameter("yob"));
                out.println("Tuoi: "+(Year.now().getValue()-yob)+"<br/>");
            } catch (Exception e) {
                out.println("Error read the year!");
            }
            String gender = request.getParameter("gender");
            gender = gender==null?"":gender;
            gender=gender.isBlank()?"ko xac dinh":gender;
            out.println("Gioi tinh: "+gender+"<br/>");
            
            String department = request.getParameter("department");
            department = department==null?"":department;
            department=department.isBlank()?"ko xac dinh":department;
            out.println("Khoa: "+department+"<br/>");
            
            String[] hobbies = request.getParameterValues("hobbies");
            if(hobbies==null || hobbies.length==0){
                out.println("Don't have hobby! <br/>");
            }else{
                out.println("So Thich: <br/>");
                out.println("ul");
                for (String h : hobbies) {
                    out.println("<li>"+h+"</li>");
                }
                out.println("/ul");
                
                
            }
           
            out.println("</body>");
            out.println("</html>");
            }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
