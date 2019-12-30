<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看	交易记录</title>

<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>
<!-- 引入 后缀参数 -->
<jsp:include page="/common/addmin&maxPricetoURL.jsp"></jsp:include>

</head>
<body>
	<center>
		<hr>
		<br><br>
		<h3>查看	交易记录</h3>
		<br><br>
		<c:if test="${not empty errorMsg }">
			<font color="red">${errorMsg }</font>
			<br><br>
		</c:if>
		<hr>
		<br><br>
		<form action="userServlet?method=getTradesUser" method="post">
			username:
			<input type="text" name="username" placeholder="请输入用户名 ... "> 
			<input type="submit" value="查看">
			
		</form>
		<c:if test="${not empty user }">
		<div align="left" style="margin:0 auto;">
			<br><br>
			<hr>
			<h2>Hello ~ ${user.username } </h2>
			<h4>--- 您的交易记录如下 ---</h4>
			<c:forEach items="${user.trades }" var="trade">
				<c:if test="${not empty trade.items }" >
				+  交易时间：${trade.tradeTime }<br>
					<c:forEach items="${trade.items }" var="item" varStatus="i">
						+ + + 【${i.count }】 书名:《${item.book.title }》<br> 
						+ + + - - - price:¥ ${item.book.price } <br>
						+ + + - - - 购买数量：${item.quantity }(本)<br>
					</c:forEach>
					<br>
				</c:if> 
			</c:forEach>
		</div>
		</c:if>
		<br><br>
		<hr>
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">返回购物</a>
	</center>
</body>
</html>