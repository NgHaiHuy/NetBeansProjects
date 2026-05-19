
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name="FirstServlet", urlPatterns={"/first"})
public class FirstServlet extends HttpServlet {
   
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
            out.println("<title>Servlet FirstServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FirstServlet at " + request.getContextPath () + "</h1>");
            
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
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

   // processRequest(request, response);
   String userName = request.getParameter("username");
   String passWord = request.getParameter("password");
   response.setContentType("text/html;charset=UTF-8");
   try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FirstServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FirstServlet at " + request.getContextPath () + "</h1>");
            
            if(userName.equalsIgnoreCase("Huy") && passWord.equals("123")){
                //out.println("<p> Hello"+userName+", "+passWord+"</p>");
                response.sendRedirect("info.html");
            }else{
                out.println("Try Again!");
                out.println("<form id=\"Formlogin\" action=\"first\" method=\"post\">");
                out.println("Username: <input type=\"text\" name=\"username\" id =\"userInput\"/><br/>");
                out.println("Password:<input type=\"text\" name=\"password\" id =\"passInput\"/><br/>");
                out.println("<input type=\"submit\" id=\"btnSubmit\" value=\"Login\" />");
                out.println("</body>");
                out.println("</html>");
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
   protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    String userName = request.getParameter("username");
    String passWord = request.getParameter("password");

    try (PrintWriter out = response.getWriter()) {

        // username chỉ chứa chữ
        boolean validUser = userName.matches("[a-zA-Z]+");

        // password chỉ chứa số
        boolean validPass = passWord.matches("[0-9]+");

        out.println("<html>");
        out.println("<body>");

        if(validUser && validPass) {

            out.println("<h1>Hello my friend</h1>");

        } else {
            request.setAttribute("Try again", out);
            //out.println("<h1>You need try again</h1>");
            response.sendRedirect("login.html");
            
        }

        out.println("</body>");
        out.println("</html>");
    }
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
