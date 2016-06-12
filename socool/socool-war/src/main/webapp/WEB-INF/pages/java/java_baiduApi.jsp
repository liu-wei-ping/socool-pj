<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
a{
	text-decoration: none;
}
.api-title ul {
	height: 60px;
	line-height: 60px;
}

.api-title li {
	width: 90px;
	float: left;
	font-size: 14px;
	font-weight: bold;
	list-style-type: none;
}

.api-title li a:hover, .api-title li a.hover {
	color: #ff0000;
	text-decoration: underline;
}
</style>
</head>
<body>
	<fieldset>
		<legend>百度API接口调用</legend>
		<div class="api-title">
			<ul id="tabmenu">
				<li><a href="###" class="tab hover" id='weather'>实时天气</a></li>
				<li><a href="###" class="tab" id='stock'>实时股票</a></li>
			</ul>
		</div>
		<div id="main_container"></div>
	</fieldset>
	<br />

</body>
<script type="text/javascript">
	titleClick();
	function titleClick() {
		$("#tabmenu li a.tab")
				.click(
						function() {
							$('#tabmenu li a.tab').removeClass('hover');
							$(this).addClass('hover');
							var tab_id = $(this).attr('id');
							if (tab_id == "weather") {
								var url = "${pageContext.request.contextPath}/java-api/weather.shtml";
								ajaxFun(url, null, "html", function(req) {
									$("#main_container").html(req);
								})
							} else if (tab_id = "stock") {
								var url = "${pageContext.request.contextPath}/java-api/stock.shtml";
								ajaxFun(url, null, "html", function(req) {
									$("#main_container").html(req);
								})
							}
						})
	}

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