<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title> 购物车 ！</title>

<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>


<script type="text/javascript">
	$(function() {
		var deleteItem = function(n) {
			//获取删除行的 tr
			var tr = $(n).parent().parent();
			var title = $.trim(tr.find("td:first").text());

			var flag = confirm("确定要删除 《"+title+"》 的信息吗？");

			//这里可以直接删，但还是跳转把。
			return flag; 
		}
		$(".delete").click(function() {
			return deleteItem(this) ; 
		});
		//ajax 修改订单数量
		//1. 获取所有的text, 为其添加onchange响应函数，弹出对话框：你确定要修改 ....... 吗？
		$(":text").change(function() {

			
			//检验有效性
			//是否为数字
			var rag = /^\d+$/g;
			var quantityStr = $.trim($(this).val());
			var flag = rag.test(quantityStr) ;
			var quantity = -1 ; 
			if(flag) {
				quantity = parseInt(quantityStr); 
				//数字是否正确
				if(quantity < 0){
					flag = false ;
				}
			}

			if(!flag){
				var oldVal = $(this).attr("name");
				$(this).val(oldVal);
				alert("输入错误！");
				return false; 
			}

			//确认修改
			var flag = true ; 
			var $tr = $(this).parent().parent();
			if(quantity==0){
				var $d = $tr.find(".delete");
				flag = $d[0].click();
			}else{
				var title = $.trim($tr.find("td:first").text());
				flag = confirm("你确定要修改 《"+title+"》 吗？");
				//返回旧值
			}

			//返回旧值
			if(!flag){
				var oldVal = $(this).attr("name") ;
				$(this).val(oldVal)  ; 
				return false;
			}
						
			//2. 请求地址为 bookServlet
			var URL = "bookServlet" ; 

			var itemId = $tr.attr("id") ; 
			//3. 请求参数 method:updateItemQuantity, id:  ,quantity:val, time:new Date()
			var data = {"method":"updateItemQuantity" , "id":itemId , "quantity":quantity , "time":new Date()} ; 
			//4. 在 updateItemQuantity 方法中，获取 quantity, id, 再获取购物车对象, 调用 service 的方法修改
			$.post(URL , data, function(data){
				//5. 传回 JSON 数据: bookNumber:xx,totalNumber:xx
				
				//总价
				$("#totalMoney").text(data.totalMoney);
				//单个总价
				$tr.find(".money").text(data.itemMoney);
				//总书数
				$("#bookNumber").text(data.bookNumber) ; 
				// item 的 val 值，不改有bug
				
				$tr.find(".quantity").attr("name" , data.quantity) ; 
				//6, 更新当前页面的 #bookNumber 和 #totalNumber
			
			} , "json");
		});
	});
</script>


 
<%-- 引入 后缀参数
 --%>
 <jsp:include page="/common/addmin&maxPricetoURL.jsp"></jsp:include>

</head>
<body>
<div align="center">
	您的购物车中有  <font id="bookNumber">${sessionScope.cart.bookNumber }</font> 本书
	<br><br>
	<table border="1">
		<tr>
			<th>书名</th>
			<th>数量</th>
			<th>价格</th>
			<th>&nbsp;</th>
		</tr>
		
		
	<c:forEach items="${sessionScope.cart.itemsCollection }" var="item">
		<tr id="${item.itemId }">
			<td class="title">${item.book.title}</td>
			<td>
				<input class="quantity" name="${item.quantity }" size="1"  type="text" value="${item.quantity }" >
			</td>
			<td class="money" >${item.money }</td>
			<td>
				<a href="bookServlet?method=removeItem&pageNo=${param.pageNo }&itemId=${item.itemId }" class="delete">删除</a>
			</td>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="4">
				总金额:¥ <font id="totalMoney">${sessionScope.cart.totalMoney }</font>
			</td>
		</tr>
	</table>
	<br><br>	
	<hr>
	<a href="bookServlet?method=getBooks&pageNo=${param.pageNo }">继续	购物</a>
	&nbsp;&nbsp;
	<a href="bookServlet?method=clearCart&pageNo=${param.pageNo }" >清空购物车</a>
	&nbsp;&nbsp;
	<a href="bookServlet?method=toPage&page=cash&pageNo=${param.pageNo }">进入	结账</a>
	&nbsp;&nbsp;
</div>
</body>
</html>