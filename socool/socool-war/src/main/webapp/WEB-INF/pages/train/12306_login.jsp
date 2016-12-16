<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.login_box{
		margin: 0 auto;
	}
</style>
<title>12306登录</title>
</head>
<body>
	<div class="login_box">
		<div>
			<label>登录名称：</label><input  type="text" id=user_name/>
		</div>
		<div>
			<label>密码：</label><input  type="password" id=user_pwd/>
		</div>
		<div>
			<input type="button" value="点击刷新验证码">
			<input type="button" value="登录">
		</div>
	</div>
</body>
</html>