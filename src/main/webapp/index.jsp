<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
        <%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title></title>
</head>
<body>
<!-- 开始光标位置 -->
<h3>Hello! Welcome to bookshop!</h3>

<%
	response.sendRedirect(request.getContextPath()+"/bookServlet?method=getBooks") ; 
%>

</body>
</html>