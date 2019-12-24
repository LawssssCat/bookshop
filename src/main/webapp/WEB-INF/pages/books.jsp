<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title></title>
</head>
<body>
<!-- 开始光标位置 -->
	<div align="center">
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			Price:
			<input type="text" size="1" name="minPrice" /> - 
			<input type="text" size="1" name="maxPrice" />  
		</form>
		
		<br>
		<br>
		<table border="1" >
		<c:forEach items="${page.list }" var="book">
		<tr>
			<td>
				<a href="">${book.title }</a>
				<br>
				${book.author }
			</td>
			<td>
				${book.price }
			</td>
			<td>
				<a href="">加入购物车</a>
			</td>
		</tr>
		</c:forEach>
		</table>
		
		<br><br>
		共${page.totalPageNumber } 页 
		&nbsp;&nbsp;
		当前第 ${page.pageNo }页
		&nbsp;&nbsp;
		
		<c:if test="${page.hasPrevPage() }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;	
			<a href="bookServlet?method=getBooks&pageNo=${page.pageNo-1 }">上一页</a>
		</c:if>
		&nbsp;&nbsp;
		<c:if test="${page.hasNextPage() }">
			<a href="bookServlet?method=getBooks&pageNo=${page.totalPageNumber }">末页</a>
			&nbsp;&nbsp;	
			<a href="bookServlet?method=getBooks&pageNo=${page.pageNo+1 }">下一页</a>
		</c:if>
		
	</div>
</body>
</html>