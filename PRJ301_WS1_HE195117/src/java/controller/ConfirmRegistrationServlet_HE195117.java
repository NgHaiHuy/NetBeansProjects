package controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ConfirmRegistrationServlet_HE195117
 * Servlet xử lý Bước 4: Hiển thị bảng tổng quan thông tin đăng ký cuối cùng của sinh viên.
 * Đọc dữ liệu cài đặt cơ sở từ Servlet Init Parameters.
 * Sở hữu bộ lọc truy cập (Access Guard) để bảo vệ ứng dụng khỏi truy cập trực tiếp.
 * 
 * @author LECOO
 */
public class ConfirmRegistrationServlet_HE195117 extends HttpServlet {

    private String centerName;
    private String supportEmail;
    private String hotline;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Đọc các giá trị Servlet Init Parameters đã cấu hình trong tệp web.xml
        centerName = config.getInitParameter("centerName");
        supportEmail = config.getInitParameter("supportEmail");
        hotline = config.getInitParameter("hotline");

        // Thiết lập giá trị mặc định phòng ngừa lỗi tệp tin cấu hình
        if (centerName == null) centerName = "FPT Training Center";
        if (supportEmail == null) supportEmail = "registration_support@fpt.edu.vn";
        if (hotline == null) hotline = "1800-6000";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        // Lấy tất cả các tham số truyền từ Bước 3 sang
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mode = req.getParameter("mode");
        String[] programs = req.getParameterValues("program");

        String startDate = req.getParameter("startDate");
        String schedule = req.getParameter("schedule");
        String level = req.getParameter("level");
        String payment = req.getParameter("payment");
        String phone = req.getParameter("phone");
        String[] additional = req.getParameterValues("additional");

        // ==== BỘ LỌC TRUY CẬP (ACCESS GUARD) ====
        // Nếu bất kỳ trường dữ liệu quan trọng nào bị thiếu, lập tức đẩy học viên về trang chào mừng
        if (name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            mode == null || mode.trim().isEmpty() ||
            programs == null || programs.length == 0 ||
            startDate == null || startDate.trim().isEmpty() ||
            schedule == null || schedule.trim().isEmpty() ||
            level == null || level.trim().isEmpty() ||
            payment == null || payment.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty()) {
            
            resp.sendRedirect("registration_HE195117.html");
            return;
        }

        PrintWriter out = resp.getWriter();
        out.println(buildSummaryPage(name, email, mode, programs, startDate, schedule, level, payment, phone, additional, req));
    }

    /**
     * Tạo mã HTML hiển thị bảng tổng hợp thông tin khóa học (Confirmation Summary Display)
     */
    private String buildSummaryPage(String name, String email, String mode, String[] programs,
                                    String startDate, String schedule, String level, String payment,
                                    String phone, String[] additional, HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
          .append("<html lang=\"en\">\n")
          .append("<head>\n")
          .append("    <meta charset=\"UTF-8\">\n")
          .append("    <title>Registration Summary</title>\n")
          .append("</head>\n")
          .append("<body>\n")
          .append("    <h1>Registration Summary</h1>\n")
          .append("    <p>Your course registration has been successfully submitted! Here is the summary of your registration data:</p>\n")
          .append("    \n")
          .append("    <h3>1. Student Basic Information</h3>\n")
          .append("    <p>\n")
          .append("        <strong>Student Name:</strong> ").append(escape(name)).append("<br>\n")
          .append("        <strong>Email Address:</strong> ").append(escape(email)).append("<br>\n")
          .append("        <strong>Phone Number:</strong> ").append(escape(phone)).append("<br>\n")
          .append("        <strong>Preferred Learning Mode:</strong> ").append(escape(mode)).append("\n")
          .append("    </p>\n")
          .append("    \n")
          .append("    <h3>2. Course Selection Details</h3>\n")
          .append("    <p>\n")
          .append("        <strong>Desired Start Date:</strong> ").append(escape(startDate)).append("<br>\n")
          .append("        <strong>Schedule Preference:</strong> ").append(escape(schedule)).append("<br>\n")
          .append("        <strong>Student Level:</strong> ").append(escape(level)).append("<br>\n")
          .append("        <strong>Payment Method:</strong> ").append(escape(payment)).append("\n")
          .append("    </p>\n")
          .append("    \n")
          .append("    <h4>Selected Programs and Sessions:</h4>\n")
          .append("    <table border=\"1\">\n")
          .append("        <thead>\n")
          .append("            <tr>\n")
          .append("                <th>Program Name</th>\n")
          .append("                <th>Sessions per Week</th>\n")
          .append("            </tr>\n")
          .append("        </thead>\n")
          .append("        <tbody>\n");

        for (String prog : programs) {
            String inputName = "sessions_" + prog.replace(" ", "_");
            String sessions = req.getParameter(inputName);
            if (sessions == null) {
                sessions = "1";
            }
            sb.append("            <tr>\n")
              .append("                <td>").append(escape(prog)).append("</td>\n")
              .append("                <td>").append(escape(sessions)).append(" session(s)</td>\n")
              .append("            </tr>\n");
        }

        sb.append("        </tbody>\n")
          .append("    </table>\n");

        if (additional != null && additional.length > 0) {
            sb.append("    <h4>Additional Requests:</h4>\n")
              .append("    <ul>\n");
            for (String addStr : additional) {
                sb.append("        <li>").append(escape(addStr)).append("</li>\n");
            }
            sb.append("    </ul>\n");
        }
        
        sb.append("    <h3>3. Center Information</h3>\n")
          .append("    <p>\n")
          .append("        <strong>Center Name:</strong> ").append(escape(centerName)).append("<br>\n")
          .append("        <strong>Support Email:</strong> ").append(escape(supportEmail)).append("<br>\n")
          .append("        <strong>Hotline:</strong> ").append(escape(hotline)).append("\n")
          .append("    </p>\n")
          .append("    \n")
          .append("    <p>\n")
          .append("        <a href=\"registration_HE195117.html\">Back to Home Page</a>\n")
          .append("    </p>\n")
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
