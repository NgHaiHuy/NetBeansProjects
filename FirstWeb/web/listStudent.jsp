<%-- 
    Document   : listStudent
    Created on : May 31, 2026, 8:57:41 PM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.Student" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách sinh viên</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
                font-family: 'Inter', sans-serif;
            }

            body {
                background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 50%, #f0f9ff 100%);
                min-height: 100vh;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                padding: 20px;
            }

            .container {
                background: rgba(255, 255, 255, 0.98);
                padding: 40px;
                border-radius: 24px;
                box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
                width: 100%;
                max-width: 650px;
            }

            h2 {
                font-size: 26px;
                font-weight: 700;
                color: #111827;
                margin-bottom: 24px;
                text-align: center;
            }

            /* Alert Styles */
            .alert {
                padding: 14px 20px;
                border-radius: 12px;
                font-size: 14px;
                font-weight: 500;
                margin-bottom: 24px;
                line-height: 1.5;
            }

            .alert-error {
                background-color: #fee2e2;
                color: #991b1b;
                border: 1px solid #fca5a5;
            }

            .alert-success {
                background-color: #dcfce7;
                color: #166534;
                border: 1px solid #86efac;
            }

            /* Table Styles */
            .table-container {
                width: 100%;
                overflow-x: auto;
                border-radius: 12px;
                border: 1px solid #e5e7eb;
                margin-bottom: 24px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                text-align: left;
                font-size: 15px;
            }

            th, td {
                padding: 14px 18px;
            }

            th {
                background-color: #f9fafb;
                color: #4b5563;
                font-weight: 600;
                border-bottom: 1px solid #e5e7eb;
            }

            tr {
                border-bottom: 1px solid #f3f4f6;
                transition: background-color 0.2s;
            }

            tr:last-child {
                border-bottom: none;
            }

            tr:hover {
                background-color: #f9fafb;
            }

            .empty-list {
                text-align: center;
                color: #6b7280;
                padding: 30px;
                font-size: 15px;
            }

            /* Action area */
            .actions {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 24px;
            }

            .btn {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                padding: 12px 24px;
                border-radius: 12px;
                font-size: 15px;
                font-weight: 500;
                text-decoration: none;
                transition: all 0.2s;
                cursor: pointer;
            }

            .btn-black {
                background-color: #1f2937;
                color: white;
                border: none;
            }

            .btn-black:hover {
                background-color: #111827;
            }

            .btn-white {
                background-color: white;
                color: #1f2937;
                border: 1px solid #e5e7eb;
            }

            .btn-white:hover {
                background-color: #f9fafb;
                border-color: #d1d5db;
            }

            .btn-danger {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                padding: 6px 14px;
                border-radius: 8px;
                font-size: 13px;
                font-weight: 500;
                text-decoration: none;
                transition: all 0.2s;
                background-color: #fee2e2;
                color: #b91c1c;
                border: 1px solid #fca5a5;
                cursor: pointer;
            }

            .btn-danger:hover {
                background-color: #b91c1c;
                color: white;
                border-color: #b91c1c;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Danh sách sinh viên</h2>

            <%
                String error = (String) request.getAttribute("error");
                String message = (String) request.getAttribute("message");
                if (error != null) {
            %>
                <div class="alert alert-error">
                    <%= error %>
                </div>
            <%
                }
                if (message != null) {
            %>
                <div class="alert alert-success">
                    <%= message %>
                </div>
            <%
                }
            %>

            <div class="table-container">
                <%
                    ArrayList<Student> list = (ArrayList<Student>) request.getAttribute("list");
                    if (list == null || list.size() == 0) {
                %>
                    <div class="empty-list">Danh sách trống. Vui lòng thêm sinh viên mới.</div>
                <%
                    } else {
                %>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Họ và Tên</th>
                                <th>GPA</th>
                                <th style="text-align: center;">Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Student s : list) { %>
                                <tr>
                                    <td><%= s.getId() %></td>
                                    <td><%= s.getName() %></td>
                                    <td><%= s.getGpa() %></td>
                                    <td style="text-align: center;">
                                        <a href="<%=request.getContextPath() %>/listStudent?action=delete&id=<%= s.getId() %>" 
                                           class="btn-danger" 
                                           onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên <%= s.getName() %> (ID: <%= s.getId() %>)?');">
                                            Xóa
                                        </a>
                                    </td>
                                </tr>
                            <% }  %>
                        </tbody>
                    </table>
                <% } %>
            </div>
            
            <div class="actions">
                <a href="<%=request.getContextPath() %>/index.html" class="btn btn-white">Trang chủ</a>
                <a href="<%=request.getContextPath() %>/addStudent.jsp" class="btn btn-black">Thêm sinh viên</a>
            </div>
        </div>
    </body>
</html>
