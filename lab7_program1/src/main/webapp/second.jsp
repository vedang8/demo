<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      Welcome
     <%
       HttpSession se = request.getSession();
       String str = (String)se.getAttribute("username"); 
     %>
     <%=str %><br>
     You are in second.jsp
</body>
</html>