<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
a {
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
				<li><a href="###" class="tab" id='message'>短信验证</a></li>
				<li><a href="###" class="tab" id='stock'>实时股票</a></li>
				<li><a href="###" class="tab" id='identity'>身份证</a></li>
			</ul>
		</div>
		<div id="weather_min">
			<fieldset>
				<legend>天气查询</legend>
				<label>城市：</label> <select id="cityId" style="width: 100px;">
					<option value="101020100">上海</option>
					<option value="101010100">北京</option>
					<option value="101250101">长沙</option>
					<option value="101251409">新田</option>
					<option value="101201001">恩施</option>
				</select> <input type="button" id="search_bn" value="查 询" />
			</fieldset>
			<div id="weather_p"></div>
		</div>
		<div id="stock_min" style="display: none;"></div>
		<div id="message_min" style="display: none;">
			<fieldset>
				<legend>发送短信</legend>
				<dl>
					<label>手机号码：</label>
					<input style="width: 200px;" id="msg_phone" />
				</dl>
				<dl>
					<label>短信类容：</label>
					<textarea style="width: 200px;max-width: 200px;" id="msg_content"></textarea>
				</dl>
				<dl>
					<input type="button" id="message_send" value="发送" />
				</dl>
			</fieldset>
			<div id="message_p"></div>
		</div>
		<div id="identity_min" style="display: none;">
				<fieldset>
					<legend>身份证查询</legend>
					<dl>
						<label>身份证：</label>
						<input style="width: 200px;" id="identity_no" /><input type="button" id="identity_cx" value="查询" />
					</dl>
					<dl>
						<span id="identity_req"></span>
					</dl>
				</fieldset>
		</div>
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
								$("#stock_min").hide();
								$("#message_min").hide();
								$("#identity_min").hide();
								$("#weather_min").show();
							} else if (tab_id == "stock") {
								$("#weather_min").hide();
								$("#message_min").hide();
								$("#identity_min").hide();
								$("#stock_min").show();
								var url = "${pageContext.request.contextPath}/java-api/stock.shtml";
								ajaxFun(url, null, "html", function(req) {
									$("#stock_min").html(req);
								})
							} else if (tab_id == "message") {
								$("#weather_min").hide();
								$("#stock_min").hide();
								$("#identity_min").hide();
								$("#message_min").show();
							}else if(tab_id=="identity"){
								$("#weather_min").hide();
								$("#stock_min").hide();
								$("#message_min").hide();
								$("#identity_min").show();
							}
						})
	}
	$("#search_bn")
			.on(
					"click",
					function() {
						var cityId = $('#cityId').val();
						var url = "${pageContext.request.contextPath}/java-api/weather.shtml?cityId="
								+ cityId;
						ajaxFun(url, null, "html", function(req) {
							$("#weather_p").html(req);
						})
					})
					
$("#message_send").on("click",function(){
	var phone = $.trim($('#msg_phone').val());
	var content = $.trim($('#msg_content').val());
	if(!phone){alert("请填写手机号码！");return;}
	if(!content){alert("请填写短信内容！");return;}
	var url = "${pageContext.request.contextPath}/java-api/message.shtml?phone="
			+ phone+"&content="+content;
	ajaxFun(url, null, "html", function(req) {
		$("#message_p").html(req);
	})
})

$("#identity_cx").on("click",function(){
	var identity_no=$.trim($("#identity_no").val());
	if(!identity_no){alert("请填写身份证号码！");return;}
	var url="${pageContext.request.contextPath}/java-api/bdIdentity.html?identity="+identity_no;
	ajaxFun(url, null, "html", function(req) {
		$("#identity_req").html(req);
	})
})

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