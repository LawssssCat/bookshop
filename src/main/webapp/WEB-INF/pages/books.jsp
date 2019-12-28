<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>book list</title>

<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {

		//实现功能：pageNo 输入框内容改变，触发
		$("#pageNo").change(function() {
			//得到当前页码
			var pageNo = $(this).val() ; 
			
			//判断，false=传入字符不合法
			var flag = false; 
			//正则判断
			var reg = /^\d+$/g ; 
			// 如果 pageNo 是数字
			if(reg.test(pageNo)) {
				// 解析成 Int
				var pageNo = parseInt(pageNo) ;
				//判断是否在 范围
				if(pageNo>=1 && pageNo<=parseInt("${page.totalPageNumber}")){
					flag = true ; 
				}
			}

			if(!flag) {
				$(this).val("");
				alert("输入页码有误！");
				return ;
			}
			
			//翻页
			var criteria = $(":hidden").serialize();
			var href = "bookServlet?method=getBooks&pageNo="+pageNo + "&" + criteria ; 
			window.location.href  = href; 
		}) ; 
		
	}) ;
	
	//清空搜索记录
	function clearCriteria() {
		window.location.href = "bookServlet?method=getBooks" ; 	
	}
</script>

<!-- 引入 后缀参数 -->
<jsp:include page="/common/addmin&maxPricetoURL.jsp"></jsp:include>

<style type="text/css">
td th{
	
}
</style>
</head>
<body>
<!-- 开始光标位置 -->

	<div align="center">
	
	<c:if test="${not empty param.bookTitle}">
		成功把 <font color="red">《${param.bookTitle}》</font> 加入购物车！
	</c:if>
	
	<c:if test="${not empty sessionScope.cart.itemsCollection }">
	<br><br>
		您的购物车中有 ${sessionScope.cart.bookNumber } 本书 , <a href="bookServlet?method=toPage&page=cart&pageNo=${page.pageNo }">查看购物车</a> 
	</c:if>
	
<!-- ==========  搜索 CriteriaBook  =================================== -->
		<br><br>
		<form action="bookServlet?method=getBooks" method="post" >
			Price:
			<input type="text" size="1" name="minPrice" value="${param.minPrice }" /><!-- value="${param.minPrice }" -->
			 -  
			<input type="text" size="1" name="maxPrice" value="${param.maxPrice }"  /><!-- value="${param.maxPrice }"  -->
			 &nbsp;
			<input type="submit" value="search">
			 &nbsp;
			<input type="button" value="clear" onclick="clearCriteria()">
		</form>
		<br>
		<br>
		
<!-- =======  展示书  List Books ======================================== -->
		<table border="1" >
		<tr>
			<th>
				书名/作者
			</th>
			<th>
				price
			</th>
			<th> 
			</th>
		</tr>
		<c:forEach items="${page.list }" var="book">
		<tr>
			<td >
				<a href="bookServlet?method=getBook&pageNo=${page.pageNo }&bookID=${book.bookID}">《${book.title }》</a>
				<br>
				> ${book.author }
			</td>
			<td>
				${book.price }
			</td>
			<td>
				<a href="bookServlet?method=addToCart&pageNo=${page.pageNo }&bookID=${book.bookID}&bookTitle=${book.title}">加入购物车</a>
			</td>
		</tr>
		</c:forEach>
		</table>
		
<!-- =======  翻页  ==================================================== -->
		<br><br>
		<hr>
		共${page.totalPageNumber } 页 
		&nbsp;&nbsp;
		当前第 ${page.pageNo } 页
		&nbsp;&nbsp;
		
		<c:if test="${page.hasPrevPage() }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;	
			<a href="bookServlet?method=getBooks&pageNo=${page.pageNo-1 }">上一页</a>
			&nbsp;&nbsp;
		</c:if>
		<c:if test="${page.hasPrevPage() == false  }">
			<font>首页</font>
			&nbsp;&nbsp;	
			<font>上一页</font>
			&nbsp;&nbsp;
		</c:if>
		
		<c:if test="${page.hasNextPage() }">
			<a href="bookServlet?method=getBooks&pageNo=${page.totalPageNumber }">末页</a>
			&nbsp;&nbsp;	
			<a href="bookServlet?method=getBooks&pageNo=${page.pageNo+1 }">下一页</a>
			&nbsp;&nbsp;
		</c:if>
			<c:if test="${page.hasNextPage() == false  }">
			<font>末页</font>
			&nbsp;&nbsp;	
			<font>下一页</font>
			&nbsp;&nbsp;
		</c:if>
		
		<!-- input - size 宽度 -->
		转到 <input type="text" id="pageNo" size="1" value="${page.pageNo }"> 页
<!-- =======  The End  =============================================== -->
	</div>
</body>
</html>