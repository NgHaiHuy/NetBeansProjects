package controller;

import dal.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import model.Category;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/listCategory"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Khởi tạo đối tượng CategoryDAO để kết nối và truy vấn DB
        CategoryDAO dao = new CategoryDAO();

        // 2. Lấy Map<Integer, Category> chứa danh sách các Category từ SQL Server
        Map<Integer, Category> listC = dao.getAllCategories();

        // 3. Nếu xảy ra lỗi kết nối hoặc truy vấn trong DAO, gửi thông báo lỗi sang JSP
        if (dao.getLastError() != null) {
            request.setAttribute("error", "Lỗi kết nối cơ sở dữ liệu: " + dao.getLastError());
        }

        // 4. Đẩy danh sách vào Request Attribute
        request.setAttribute("listC", listC);

        // 5. Chuyển tiếp (Forward) dữ liệu sang trang listCategory.jsp để hiển thị
        request.getRequestDispatcher("listCategory.jsp").forward(request, response);
    }
}
