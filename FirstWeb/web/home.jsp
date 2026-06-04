<%-- 
    Document   : home
    Created on : May 31, 2026, 8:05:55 PM
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
        <% int y = 20; %>
        <h1>Hello World!</h1>
        <%@include file = "includePage.jsp" %>
        <jsp:include page = "includePage.jsp"/>
    </body>
</html>
