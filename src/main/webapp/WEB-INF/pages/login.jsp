<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form action="" method="post">
			username:
			<input type="text" name="username" placeholder="请输入用户名 ... "> 
			<input type="submit" value="查看">
			
		</form>
		<br><br>
		<hr>
		<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">返回购物</a>
	</center>
</body>
</html>