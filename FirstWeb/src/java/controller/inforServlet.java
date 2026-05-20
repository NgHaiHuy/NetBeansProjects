/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
 * @author LECOO
 */
@WebServlet(name = "inforServlet", urlPatterns = {"/inforServlet"})
public class inforServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet inforServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet inforServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Thiết lập bộ mã UTF-8 để hiển thị đúng tiếng Việt
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String birthYear = request.getParameter("birthYear");
        String gender = request.getParameter("gender");
        String major = request.getParameter("major");
        String hobbies = request.getParameter("hobbies");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='vi'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Thông tin đã lưu</title>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap' rel='stylesheet'>");

            // Nhúng CSS trực tiếp vào Servlet để giữ nguyên style
            out.println("<style>");
            out.println("* { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Inter', sans-serif; }");
            out.println("body { background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 50%, #f0f9ff 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }");
            out.println(".result-container { background: rgba(255, 255, 255, 0.98); padding: 40px; border-radius: 24px; box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05); width: 100%; max-width: 450px; text-align: left; }");
            out.println("h2 { font-size: 24px; font-weight: 600; color: #111827; margin-bottom: 24px; text-align: center; }");
            out.println(".info-row { background: #f3f4f6; padding: 16px; border-radius: 12px; margin-bottom: 12px; color: #1f2937; font-size: 15px; }");
            out.println(".info-row strong { color: #4b5563; display: inline-block; width: 100px; }");
            out.println(".btn-secondary { display: block; width: 100%; padding: 14px; text-align: center; background-color: white; color: #1f2937; border: 1px solid #e5e7eb; border-radius: 12px; font-size: 16px; font-weight: 500; text-decoration: none; margin-top: 24px; transition: all 0.2s; }");
            out.println(".btn-secondary:hover { background-color: #f9fafb; border-color: #d1d5db; }");
            out.println("</style>");
            out.println("</head>");

            out.println("<body>");
            out.println("<div class='result-container'>");
            out.println("<h2>Thông Tin Của Bạn</h2>");

            // In các dữ liệu ra theo dạng danh sách
            out.println("<div class='info-row'><strong>Full name:</strong> " + fullName + "</div>");
            out.println("<div class='info-row'><strong>Year of birth:</strong> " + birthYear + "</div>");
            out.println("<div class='info-row'><strong>Gender:</strong> " + gender + "</div>");
            out.println("<div class='info-row'><strong>Field of study:</strong> " + major + "</div>");
            out.println("<div class='info-row'><strong>Hobby:</strong> " + hobbies + "</div>");

            // Nút quay lại trang chủ (Giả sử file html chính của bạn tên là index.html hoặc Main.html)
            out.println("<a href='index.html' class='btn-secondary'>Return to main page</a>");

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
