<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<fieldset>
		<legend>网络爬虫</legend>
		<div>
			<input name="webUrl"  />&nbsp;<input type="button" id="crawler_start" value="开始爬取">&nbsp;<input type="button" value="停止爬取">
		</div>
	</fieldset>
</body>
<script type="text/javascript">
	$("#crawler_start").on("click",function(){
		var url="${pageContext.request.contextPath}/crawler/webCrawler.shtml";
		var data=$.trim($("input[name='']").val());
		ajaxFun(url,data,"json",function(req){
			alert(req["result"]);
		});
	});
	function ajaxFun(url, data, dataType, callback) {
		var type = data == null ? "GET" : "POST";
		$.ajax({
			url : url,
			data : data,
			type : type,
			dataType : dataType,
			success : function(req) {
				callback(req);
			},
			error : function() {
				alert("系统:ERROR!");
			}
		});
	}
</script>
</html>