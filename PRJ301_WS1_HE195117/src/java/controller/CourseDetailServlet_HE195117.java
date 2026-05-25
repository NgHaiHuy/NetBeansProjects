package controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * CourseDetailServlet_HE195117
 * Servlet xử lý Bước 3: Thu thập thông tin chi tiết của khóa học đã đăng ký.
 * Sở hữu bộ lọc truy cập (Access Guard) để bảo vệ ứng dụng khỏi truy cập trái phép.
 * 
 * @author LECOO
 */
public class CourseDetailServlet_HE195117 extends HttpServlet {

    private Set<String> allowedDomains;
    private int minNameLen;
    private int maxCourses;

    @Override
    public void init() throws ServletException {
        // Đọc các giá trị xác thực toàn cục phục vụ cho bộ lọc Access Guard
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

        // Đọc các thông số bắt buộc từ Bước 1
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mode = req.getParameter("mode");
        String[] programs = req.getParameterValues("program");

        // ==== BỘ LỌC TRUY CẬP (ACCESS GUARD) ====
        // Nếu thiếu bất cứ thông tin bắt buộc nào từ bước 1, lập tức chuyển hướng về trang chủ
        if (name == null || email == null || mode == null || programs == null || programs.length == 0) {
            resp.sendRedirect("registration_HE195117.html");
            return;
        }

        // Kiểm tra xem dữ liệu có khớp với cấu hình trong web.xml không nhằm ngăn bypass url
        if (name.trim().length() < minNameLen || !isValidEmail(email) || programs.length > maxCourses) {
            resp.sendRedirect("registration_HE195117.html");
            return;
        }

        PrintWriter out = resp.getWriter();
        out.println(buildCourseDetailForm(name, email, mode, programs));
    }

    /**
     * Kiểm tra đuôi email hợp lệ
     */
    private boolean isValidEmail(String email) {
        if (!email.contains("@")) {
            return false;
        }
        String domain = email.substring(email.lastIndexOf('@') + 1).trim().toLowerCase();
        return allowedDomains.contains(domain);
    }

    /**
     * Dựng form thu thập thông tin chi tiết bằng mã HTML thuần
     */
    private String buildCourseDetailForm(String name, String email, String mode, String[] programs) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
          .append("<html lang=\"en\">\n")
          .append("<head>\n")
          .append("    <meta charset=\"UTF-8\">\n")
          .append("    <title>Course Detail Form</title>\n")
          .append("</head>\n")
          .append("<body>\n")
          .append("    <h1>Course Detail Form</h1>\n")
          .append("    <p>Welcome <strong>").append(escape(name)).append("</strong>! Please provide the detailed course selection information below.</p>\n")
          .append("    \n")
          .append("    <form action=\"confirmRegistrationServlet_HE195117\" method=\"GET\">\n")
          .append("        <!-- Thẻ Input ẩn để tiếp tục truyền các dữ liệu của Bước 1 mà không dùng Session/Cookie -->\n")
          .append("        <input type=\"hidden\" name=\"name\" value=\"").append(escape(name)).append("\">\n")
          .append("        <input type=\"hidden\" name=\"email\" value=\"").append(escape(email)).append("\">\n")
          .append("        <input type=\"hidden\" name=\"mode\" value=\"").append(escape(mode)).append("\">\n");

        for (String prog : programs) {
            sb.append("        <input type=\"hidden\" name=\"program\" value=\"").append(escape(prog)).append("\">\n");
        }

        sb.append("\n")
          .append("        <!-- Ngày khai giảng mong muốn -->\n")
          .append("        <p>\n")
          .append("            <label for=\"startDate\">Desired Start Date:</label><br>\n")
          .append("            <input type=\"date\" id=\"startDate\" name=\"startDate\" required>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Thời gian học mong muốn -->\n")
          .append("        <p>\n")
          .append("            <label>Schedule Preference:</label><br>\n")
          .append("            <input type=\"radio\" id=\"morning\" name=\"schedule\" value=\"Weekday Morning\" checked>\n")
          .append("            <label for=\"morning\">Weekday Morning</label>\n")
          .append("            <input type=\"radio\" id=\"evening\" name=\"schedule\" value=\"Weekday Evening\">\n")
          .append("            <label for=\"evening\">Weekday Evening</label>\n")
          .append("            <input type=\"radio\" id=\"weekend\" name=\"schedule\" value=\"Weekend\">\n")
          .append("            <label for=\"weekend\">Weekend</label>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Hiển thị động ô nhập số buổi mỗi tuần cho từng khóa học đã đăng ký từ Bước 1 -->\n")
          .append("        <p>\n")
          .append("            <label>Number of Sessions per Selected Program (minimum 1 session/week):</label><br>\n");

        for (String prog : programs) {
            String inputName = "sessions_" + prog.replace(" ", "_");
            sb.append("            <label for=\"sess_").append(prog.replace(" ", "_")).append("\">").append(escape(prog)).append(" (sessions/week):</label>\n")
              .append("            <input type=\"number\" id=\"sess_").append(prog.replace(" ", "_")).append("\" name=\"").append(escape(inputName)).append("\" min=\"1\" value=\"1\" required><br>\n");
        }

        sb.append("        </p>\n")
          .append("\n")
          .append("        <!-- Trình độ học viên -->\n")
          .append("        <p>\n")
          .append("            <label for=\"level\">Student Level:</label><br>\n")
          .append("            <select id=\"level\" name=\"level\" required>\n")
          .append("                <option value=\"Beginner\">Beginner</option>\n")
          .append("                <option value=\"Intermediate\">Intermediate</option>\n")
          .append("                <option value=\"Advanced\">Advanced</option>\n")
          .append("                <option value=\"Professional\">Professional</option>\n")
          .append("            </select>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Phương thức thanh toán học phí -->\n")
          .append("        <p>\n")
          .append("            <label for=\"payment\">Payment Method:</label><br>\n")
          .append("            <select id=\"payment\" name=\"payment\" required>\n")
          .append("                <option value=\"Full Payment\">Full Payment</option>\n")
          .append("                <option value=\"Scholarship Waiver\">Scholarship Waiver</option>\n")
          .append("                <option value=\"Corporate Sponsorship\">Corporate Sponsorship</option>\n")
          .append("            </select>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Số điện thoại liên lạc -->\n")
          .append("        <p>\n")
          .append("            <label for=\"phone\">Phone Number:</label><br>\n")
          .append("            <input type=\"text\" id=\"phone\" name=\"phone\" placeholder=\"Enter phone number\" required>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Các yêu cầu hỗ trợ bổ sung -->\n")
          .append("        <p>\n")
          .append("            <label>Additional Requests:</label><br>\n")
          .append("            <input type=\"checkbox\" id=\"add1\" name=\"additional\" value=\"Provide printed course materials\">\n")
          .append("            <label for=\"add1\">Provide printed course materials</label><br>\n")
          .append("            <input type=\"checkbox\" id=\"add2\" name=\"additional\" value=\"Request bilingual instructor (EN/VN)\">\n")
          .append("            <label for=\"add2\">Request bilingual instructor (EN/VN)</label><br>\n")
          .append("            <input type=\"checkbox\" id=\"add3\" name=\"additional\" value=\"Access to recorded sessions\">\n")
          .append("            <label for=\"add3\">Access to recorded sessions</label><br>\n")
          .append("            <input type=\"checkbox\" id=\"add4\" name=\"additional\" value=\"Mentoring sessions after class\">\n")
          .append("            <label for=\"add4\">Mentoring sessions after class</label>\n")
          .append("        </p>\n")
          .append("\n")
          .append("        <!-- Các nút gửi/hủy form -->\n")
          .append("        <p>\n")
          .append("            <input type=\"reset\" value=\"Reset\">\n")
          .append("            <input type=\"submit\" value=\"Confirm Registration\">\n")
          .append("        </p>\n")
          .append("    </form>\n")
          .append("</body>\n")
          .append("</html>");

        return sb.toString();
    }

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
