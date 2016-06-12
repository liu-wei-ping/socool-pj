<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<fieldset>
			<legend>【${we.city}】当前天气</legend>
			<div>
			<dd><label>当前温度：</label>${we.todayWeather['curTemp']}&nbsp;${we.todayWeather['type']}</br>
			</dd>
			</div>
		</fieldset>
		<fieldset>
		<legend>【${we.city}】后4天天气</legend>
		<table border="1" cellpadding="0">
			<tr>
				<th>日期</th>
				<th>星期</th>
				<th>最高温度</th>
				<th>最低温度</th>
				<th>风向</th>
				<th>风力</th>
				<th>Type</th>
			</tr>
		<c:forEach items="${we['forecastWeather']}" var="fe">
			<tr>
				<th>${fe.date}</th>
				<th>${fe.week}</th>
				<th>${fe.hightemp}</th>
				<th>${fe.lowtemp}</th>
				<th>${fe.fengxiang}</th>
				<th>${fe.fengli}</th>
				<th>${fe.type}</th>
			</tr>
		</c:forEach>
		</table>
		</fieldset>
	</div>
</body>
</html>