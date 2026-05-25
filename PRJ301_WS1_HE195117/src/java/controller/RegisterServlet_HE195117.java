package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RegisterServlet_HE195117
 * Servlet xử lý Bước 2: Xác thực thông tin cơ bản của sinh viên.
 * Hiển thị lại form đăng ký kèm các thông báo lỗi nếu xác thực thất bại,
 * hoặc chuyển hướng sang bước tiếp theo nếu thành công.
 * Tất cả giao diện hiển thị bằng tiếng Anh, chú thích bằng tiếng Việt.
 * 
 * @author LECOO
 */
public class RegisterServlet_HE195117 extends HttpServlet {

    private Set<String> allowedDomains;
    private int minNameLen;
    private int maxCourses;

    @Override
    public void init() throws ServletException {
        // Đọc các tham số cấu hình chung từ Context Parameters trong web.xml
        ServletContext ctx = getServletContext();
        String domainStr = ctx.getInitParameter("allowedDomain");
        if (domainStr != null) {
            allowedDomains = new HashSet<>(Arrays.asList(domainStr.split(";")));
        } else {
            allowedDomains = new HashSet<>(Arrays.asList("fpt.edu.vn", "fe.edu.vn", "fsoft.com.vn"));
        }

        try {
            minNameLen = Integer.parseInt(ctx.getInitParameter("minStudentNameLength"));
        } catch (NumberFormatException e) {
            minNameLen = 5;
        }

        try {
            maxCourses = Integer.parseInt(ctx.getInitParameter("maxCoursesPerStudent"));
        } catch (NumberFormatException e) {
            maxCourses = 3;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu gửi từ Welcome page
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mode = req.getParameter("mode");
        String[] programs = req.getParameterValues("program");

        // Danh sách lưu trữ các lỗi phát hiện được
        List<String> errors = new ArrayList<>();

        // Kiểm tra độ dài họ tên
        if (name == null || name.trim().length() < minNameLen) {
            errors.add("Student Name: length must be >= " + minNameLen + " characters.");
        }

        // Kiểm tra định dạng và tên miền của email
        if (email == null || !isValidEmail(email)) {
            errors.add("Email: the domain part (after @) must be among the domains listed in allowedDomain (" + getServletContext().getInitParameter("allowedDomain") + ").");
        }

        // Kiểm tra số lượng khóa học đã tích chọn
        if (programs == null || programs.length == 0) {
            errors.add("Program of Interest: the student must choose at least one program.");
        } else if (programs.length > maxCourses) {
            errors.add("Program of Interest: the student can choose no more than " + maxCourses + " programs.");
        }

        // Trường hợp xác thực thất bại: Hiển thị lại form và danh sách lỗi chi tiết
        if (!errors.isEmpty()) {
            PrintWriter out = resp.getWriter();
            out.println(buildErrorPage(errors, name, email, mode, programs));
            return;
        }

        // Trường hợp xác thực thành công: Tạo chuỗi truy vấn và chuyển hướng sang bước tiếp theo
        StringBuilder query = new StringBuilder();
        query.append("name=").append(URLEncoder.encode(name, "UTF-8"));
        query.append("&email=").append(URLEncoder.encode(email, "UTF-8"));
        query.append("&mode=").append(URLEncoder.encode(mode, "UTF-8"));
        for (String prog : programs) {
            query.append("&program=").append(URLEncoder.encode(prog, "UTF-8"));
        }

        resp.sendRedirect("courseDetailServlet_HE195117?" + query.toString());
    }

    /**
     * Hàm kiểm tra đuôi domain email có hợp lệ không
     */
    private boolean isValidEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        String domain = email.substring(email.lastIndexOf('@') + 1).trim().toLowerCase();
        return allowedDomains.contains(domain);
    }

    /**
     * Dựng giao diện trang HTML báo lỗi và điền lại thông tin cũ vào form
     */
    private String buildErrorPage(List<String> errors, String name, String email, String mode, String[] programs) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
          .append("<html lang=\"en\">\n")
          .append("<head>\n")
          .append("    <meta charset=\"UTF-8\">\n")
          .append("    <title>Registration Error</title>\n")
          .append("</head>\n")
          .append("<body>\n")
          .append("    <h1>Online Course Registration System</h1>\n")
          .append("    \n")
          .append("    <!-- Khung hiển thị các lỗi xác thực -->\n")
          .append("    <div style=\"color: red;\">\n")
          .append("        <h3>Validation Errors:</h3>\n")
          .append("        <ul>\n");
        
        for (String err : errors) {
            sb.append("            <li>").append(escape(err)).append("</li>\n");
        }
        
        sb.append("        </ul>\n")
          .append("    </div>\n")
          .append("\n")
          .append("    <form action=\"registerServlet_HE195117\" method=\"GET\" id=\"registerForm\">\n")
          .append("        <!-- Họ và tên sinh viên -->\n")
          .append("        <p>\n")
          .append("            <label for=\"name\">Student Name:</label><br>\n")
          .append("            <input type=\"text\" id=\"name\" name=\"name\" value=\"").append(escape(name)).append("\" required>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Địa chỉ email -->\n")
          .append("        <p>\n")
          .append("            <label for=\"email\">Email:</label><br>\n")
          .append("            <input type=\"email\" id=\"email\" name=\"email\" value=\"").append(escape(email)).append("\" required>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Hình thức học tập mong muốn -->\n")
          .append("        <p>\n")
          .append("            <label>Preferred Learning Mode:</label><br>\n")
          .append("            <input type=\"radio\" id=\"online\" name=\"mode\" value=\"Online\"").append("Online".equals(mode) ? " checked" : "").append(">\n")
          .append("            <label for=\"online\">Online</label>\n")
          .append("            <input type=\"radio\" id=\"offline\" name=\"mode\" value=\"Offline\"").append("Offline".equals(mode) ? " checked" : "").append(">\n")
          .append("            <label for=\"offline\">Offline</label>\n")
          .append("            <input type=\"radio\" id=\"hybrid\" name=\"mode\" value=\"Hybrid\"").append("Hybrid".equals(mode) ? " checked" : "").append(">\n")
          .append("            <label for=\"hybrid\">Hybrid</label>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Các chương trình học quan tâm -->\n")
          .append("        <p>\n")
          .append("            <label>Program of Interest (Select 1 to 3 programs):</label><br>\n");

        List<String> options = Arrays.asList(
            "Software Engineering",
            "Artificial Intelligence",
            "Cyber Security",
            "Information Assurance",
            "Internet of Things (IoT)"
        );

        Set<String> selectedPrograms = new HashSet<>();
        if (programs != null) {
            selectedPrograms.addAll(Arrays.asList(programs));
        }

        for (int i = 0; i < options.size(); i++) {
            String opt = options.get(i);
            boolean isChecked = selectedPrograms.contains(opt);
            sb.append("            <input type=\"checkbox\" id=\"prog").append(i + 1).append("\" name=\"program\" value=\"").append(escape(opt)).append("\"").append(isChecked ? " checked" : "").append(">\n")
              .append("            <label for=\"prog").append(i + 1).append("\">").append(escape(opt)).append("</label><br>\n");
        }

        sb.append("        </p>\n")
          .append("\n")
          .append("        <!-- Các nút điều khiển -->\n")
          .append("        <p>\n")
          .append("            <input type=\"reset\" value=\"Reset\">\n")
          .append("            <input type=\"submit\" value=\"Register\">\n")
          .append("        </p>\n")
          .append("    </form>\n")
          .append("</body>\n")
          .append("</html>");

        return sb.toString();
    }

    /**
     * Hàm phòng chống tấn công XSS bằng cách mã hóa thực thể HTML
     */
    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
