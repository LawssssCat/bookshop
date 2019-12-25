<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title></title>

<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>


<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			//获取删除行的 tr
			var tr = $(this).parent().parent();
			var title = $.trim(tr.find("td:first").text());

			var flag = confirm("确定要删除 《"+title+"》 的信息吗？");

			//这里可以直接删，但还是跳转把。
			return flag; 
		});
	});
</script>


 
<%-- 引入 后缀参数
 --%>
 <jsp:include page="/common/addmin&maxPricetoURL.jsp"></jsp:include>

</head>
<body>
<div align="center">
	您的购物车中有  ${sessionScope.cart.bookNumber } 本书
	<br><br>
	<table border="1">
		<tr>
			<th>书名</th>
			<th>数量</th>
			<th>价格</th>
			<th>&nbsp;</th>
		</tr>
		
		
	<c:forEach items="${sessionScope.cart.itemsCollection }" var="item">
		<tr>
			<td>${item.book.title}</td>
			<td>
				<input  size="1"  type="text" value="${item.quantity }" >
			</td>
			<td >${item.money }</td>
			<td>
				<a href="bookServlet?method=removeItem&pageNo=${param.pageNo }&itemId=${item.itemId }" class="delete">删除</a>
			</td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="4">
				总金额:¥ ${sessionScope.cart.totalMoney }
			</td>
		</tr>
	</table>
	<br><br>	
	<hr>
	<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续	购物</a>
	&nbsp;&nbsp;
	<a href="bookServlet?method=clearCart&pageNo=${param.pageNo }" >清空购物车</a>
	&nbsp;&nbsp;
	<a href="">结账</a>
	&nbsp;&nbsp;
</div>
</body>
</html>