<%-- 
    Document   : menu
    Created on : May 31, 2026, 8:21:20 PM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Menu Page</h1>
        <a href="<%=request.getContextPath()%>/footer.jsp">Footer</a> 
        
        <!-- Dùng <%=request.getContextPath()%> để lấy đúng file nếu file đó có nằm ở thư mục khác  -->
    </body>
</html>
