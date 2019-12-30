<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 结账！ </title>

<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>

<!-- 记录查询记录 -->
<jsp:include page="/common/addmin&maxPricetoURL.jsp"></jsp:include>

</head>
<body>
	<center>
	<hr>
		<br><br>
		<h3>结账</h3>
		<br><br>
		<c:if test="${not empty errorMsg }">
			<font color="red">${errorMsg }</font>		
			<br><br>
		</c:if>
		您一共买了  <font id="bookNumber">${sessionScope.cart.bookNumber }</font> 本书
		<br><br>
		结账金额：¥ ${sessionScope.cart.totalMoney } 
		
		<br><br>
		<hr>
		<form action="bookServlet?method=cash" method="post">
			<table  cellpadding="10" >
				<tr>
					<td>信用卡名：</td>
					<td><input type="text" name="username" placeholder="输入持卡人姓名.."></td>
				</tr>
				<tr>
					<td>信用卡账号：</td>
					<td><input type="text" name="accountId" placeholder="输入信用卡账号.."></td>
				</tr>
				<tr>
					<td colspan="2" >
						<input value="确认结账" type="submit">
					</td>
				</tr>
			</table>
		</form>
		
		<hr>
		<a  href="bookServlet?method=toPage&page=cart&pageNo=${param.pageNo }">返回	购物车</a>
		&nbsp;&nbsp;
		<a  href="bookServlet?method=getBooks&pageNo=${param.pageNo }">返回	图书列表</a>
	</center>
</body>
</html>