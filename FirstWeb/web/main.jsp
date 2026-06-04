<%-- 
    Document   : main
    Created on : May 30, 2026, 10:22:24 AM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.Student" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Main</title>
    </head>
    <body>
        <h1>Main JSP!</h1>
        <%
            // Kiểm tra tránh lỗi NullPointerException nếu URL không có tham số x
            String xParam = request.getParameter("x");
            if (xParam != null) {
                float x = Float.parseFloat(xParam);
                out.println("x=" + x + "<br>");
            }
            
            // Lấy danh sách từ request dựa vào key "studentList"
            ArrayList<Student> lists = (ArrayList<Student>) request.getAttribute("studentList");
if (lists == null) {
    // fallback to session attribute (useful if page accessed directly)
    lists = (ArrayList<Student>) request.getSession().getAttribute("studentList");
}
            if (lists != null && lists.size() > 0) {
                for (Student s : lists) {
                    // Class Student cần ghi đè (override) hàm toString() để hiển thị đẹp mắt
                    out.println(s + "<br>"); 
                }
            } else {
                out.println("Không có sinh viên nào trong danh sách.");
            }
        %>
    </body>
</html>
