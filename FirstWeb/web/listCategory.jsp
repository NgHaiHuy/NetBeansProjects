<%-- 
    Document   : listCategory
    Created on : Jun 10, 2026, 10:22:42 AM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, model.Category" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Danh sách Category</title>
    </head>
    <body>
        <div class="container">
            <h2>Danh sách Category</h2>

            <div class="table-container">
                <%
                    Map<Integer, Category> listC = (Map<Integer, Category>) request.getAttribute("listC");
                    if (listC == null || listC.isEmpty()) {
                %>
                    <div class="empty-list">Danh sách trống. Không có category nào.</div>
                <%
                    } else {
                %>
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên Category</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Map.Entry<Integer, Category> entry : listC.entrySet()) {
                                Category c = entry.getValue();
                            %>
                                <tr>
                                    <td><%= c.getId() %></td>
                                    <td><%= c.getName() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
            
            <div class="actions">
                <a href="<%=request.getContextPath() %>/index.html" class="btn btn-white">Trang chủ</a>
            </div>
        </div>
    </body>
</html>
