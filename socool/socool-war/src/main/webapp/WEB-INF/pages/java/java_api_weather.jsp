<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#weather_c dd {
	width: 150px;
	height: 150px;
	background: #0EB547;
	text-align: center;
	color: #fff;
	float: left;
	margin: 10px;
	padding: 30px
}

#weather_c dd:first-child {
	background: #2087BB;
}

#weather_c dl span {
	font: 30px bold;
}
</style>
</head>
<body>
	<fieldset>
		<legend>【${we.city}】天气</legend>
		<div id="weather_c">
			<dl>
				<dd>
					${we.todayWeather['week']}</br> ${we.todayWeather['date']}<br> <span>${we.todayWeather['curTemp']}</span></br>
					<span>${we.todayWeather['type']}</span></br>
					${we.todayWeather['lowtemp']}~${we.todayWeather['hightemp']}</br>
					${we.todayWeather['fengli']}${we.todayWeather['fengxiang']}
				</dd>
				<c:forEach items="${we['forecastWeather']}" var="fe">
					<dd>
						${fe.week}</br> ${fe.date}</br> <span>${fe.type}</span></br>
						${fe.hightemp}~${fe.lowtemp}</br> ${fe.fengli}${fe.fengxiang}
					</dd>
				</c:forEach>
			</dl>

		</div>
	</fieldset>
</body>

</html>