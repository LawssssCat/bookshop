<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><!-- 设置编码 -->

<script type="text/javascript">
    //加载后
    $(function() {
    	
    	//实现功能：翻页时，记录查询结果
		// url 添加 minPrice maxPrice
        $("a").click(function() {
        	//把 所有隐藏域 表单序列化
            // 序列化
		    var serializeVal = $(":hidden").serialize() ; 
            //添加 上url
           	//this.href ==> a 上面的 href 连接
            var href = this.href + "&" +  serializeVal ; 
            window.location.href = href ; 
            return false; 
        });
		
    });
</script>

<!-- ==============  显示书信息  ================================ -->
<input type="hidden" name="minPrice" value="${param.minPrice }">
<input type="hidden" name="maxPrice" value="${param.maxPrice  }">