<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title></title>
<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>
<jsp:include page="/common/addmin&maxPricetoURL.jsp"></jsp:include>

</head>
<body>
<!-- 开始光标位置 -->



<div align="center" >
<h3> 书目 - 详情 </h3>

    书名:${book.title }
    <br><br>
    作者:${book.author }
    <br><br>
    售价:${book.price }
    <br><br>
    发行时间:${book.publishingDate }
    <br><br>
    售卖数量:${book.salesAmount }
    <br><br>
    库存:${book.storeNumber }
    <br><br>
    备注:${book.remark }
    <br><br>
    
	<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续 购书</a>
</div>

</body>
</html>