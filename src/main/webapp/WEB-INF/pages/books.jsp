<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>book list</title>

<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		//实现功能：翻页时，记录查询结果
		$("a").click(function() {
			//把 所有隐藏域 表单序列化
			var serializeVal =  $(":hidden").serialize();

			//this.href ==> a 上面的 href 连接
			var href = this.href + "&" + serializeVal ; 
			window.location.href = href ; 

			return false; 
		});

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

<style type="text/css">
td th{
	
}
</style>
</head>
<body>
<!-- 开始光标位置 -->
	<input type="hidden" name="minPrice" value="${param.minPrice }"> 
	<input type="hidden" name="maxPrice" value="${param.maxPrice }">

	<div align="center">
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
		当前第 ${page.pageNo } 页
		&nbsp;&nbsp;
		
		<c:if test="${page.hasPrevPage() }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;	
			<a href="bookServlet?method=getBooks&pageNo=${page.pageNo-1 }">上一页</a>
			&nbsp;&nbsp;
		</c:if>
		
		<c:if test="${page.hasNextPage() }">
			<a href="bookServlet?method=getBooks&pageNo=${page.totalPageNumber }">末页</a>
			&nbsp;&nbsp;	
			<a href="bookServlet?method=getBooks&pageNo=${page.pageNo+1 }">下一页</a>
			&nbsp;&nbsp;
		</c:if>
		
		
		<!-- input - size 宽度 -->
		转到 <input type="text" id="pageNo" size="1"> 页
	</div>
</body>
</html>