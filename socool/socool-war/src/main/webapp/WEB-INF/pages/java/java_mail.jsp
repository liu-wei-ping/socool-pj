<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮件发送</title>

<style type="text/css">
	table th{
		text-align: left;
	}
	input,textarea{
		width: 500px;
	}
	table a{
		color:#000;
		display:block;
		background:#525DEA;
		text-decoration: none;
		width: 200px;
		height:30px;
		text-align:center;
		margin: 0 auto;
		line-height: 30px;
	}
</style>
</head>
<body>
		<fieldset>
			<legend>邮件发送</legend>
			<form id="mail_form">
			<table>
				<tr>
					<th><label>发送邮箱：</label></th>
					<th>
						<select name="fromMail">
							<option value="323212891@qq.com">刘伟平QQ邮箱</option>
							<option value="liu_weipinglove@163.com">刘伟平网易邮箱</option>
						</select>
					</th>
				</tr>
				<tr>
					<th><label>收件人：</label></th>
					<th><input name="toMail"/></th>
				</tr>
				<tr>
					<th><label>邮箱类容：</label></th>
					<th><textarea rows="20" cols="" name="content"></textarea></th>
				</tr>
				<tr>
					<th><label>主题：</label></th>
					<th><input name="subject"/></th>
				</tr>
				<tr>
					<th></th>
					<th><a href="#" id="send">发 送</a></th>
				</tr>
			</table>
			</form>
		</fieldset>
</body>
<script type="text/javascript">
	$("#send").on("click",function(){
		var url="${pageContext.request.contextPath}/mail/sendMail.shtml";
		var data=$("#mail_form").serializeArray();
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