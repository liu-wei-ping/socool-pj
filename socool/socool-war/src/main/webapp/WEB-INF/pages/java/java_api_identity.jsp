<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div>
		<span>性别：${identity['sexStr']}</span><br> <span>出生日期：${identity['birthday']}</span><br>
		<span>身份证归属地：${identity['address']}</span><br>
	</div>
</body>
</html>