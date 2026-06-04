/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author LECOO
 */
@WebServlet(name = "listStudent", urlPatterns = {"/listStudent"})
public class listStudent extends HttpServlet {

    ArrayList<Student> list = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        // Khởi tạo dữ liệu
        list.add(new Student(1, "Nguyen Van A", 2.0f));
        list.add(new Student(2, "Nguyen Van B", 3.5f));
    }

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
            out.println("<title>Servlet listStudent</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet listStudent at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        action = action == null ? "list" : action;
        
        if ("add".equalsIgnoreCase(action)) {
            boolean success = add(request, response);
            if (!success) {
                request.getRequestDispatcher("addStudent.jsp").forward(request, response);
                return;
            }
        } else if ("delete".equalsIgnoreCase(action)) {
            delete(request, response);
        }
        list(request, response);
    }

    protected boolean add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String gpaStr = request.getParameter("gpa");

        if (idStr == null || name == null || gpaStr == null) {
            request.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            return false;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            String trimmedName = name.trim();
            float gpa = Float.parseFloat(gpaStr.trim());

            if (trimmedName.isEmpty()) {
                request.setAttribute("error", "Tên sinh viên không được để trống.");
                return false;
            }

            // Kiểm tra trùng lặp ID
            boolean isDuplicate = false;
            for (Student s : list) {
                if (s.getId() == id) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                request.setAttribute("error", "Trùng lặp: ID " + id + " đã tồn tại!");
                return false;
            } else {
                list.add(new Student(id, trimmedName, gpa));
                request.setAttribute("message", "Thêm sinh viên mới thành công!");
                return true;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID phải là số nguyên và GPA phải là số thực hợp lệ.");
            return false;
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            request.setAttribute("error", "Thiếu ID sinh viên cần xóa.");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            boolean found = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == id) {
                    list.remove(i);
                    found = true;
                    break;
                }
            }

            if (found) {
                request.setAttribute("message", "Đã xóa sinh viên với ID " + id + " thành công!");
            } else {
                request.setAttribute("error", "Không tìm thấy sinh viên với ID " + id + " để xóa.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Mã ID sinh viên không hợp lệ.");
        }
    }
    
    protected void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Đẩy danh sách vào request với key là "list"
        request.setAttribute("list", list);

        // Chuyển hướng dữ liệu sang file listStudent.jsp
        request.getRequestDispatcher("listStudent.jsp").forward(request, response);

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
        processRequest(request, response);
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
