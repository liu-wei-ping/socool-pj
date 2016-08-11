<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
 th a{
	width: 10px;
	text-decoration: underline;
}
a:HOVER {
	color: red;
}

</style>
</head>
<body>
<hr>
		<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<th>股票名称</th>
					<th>股票代码</th>
					<th>日期时间</th>
					<th>开盘价格(元)</th>
					<th>收盘价格(元)</th>
					<th>当前价格(元)</th>
					<th>今日最高价(元)</th>
					<th>今日最低价(元)</th>
					<th>成交的股票数</th>
					<th>成交额(元)</th>
					<th>涨幅</th>
					<th>分时K线图</th>
					<th>日K线图</th>
					<th>周K线图</th>
					<th>月K线图</th>
				</tr>
				<c:forEach items="${stock_info}" var="item">
						<tr>
						    <th>${item.name}</th>
							<th>${item.code}</th>
							<th>${item.date}(${item.time})</th>
							<th>${item.openningprice}</th>
							<th>${item.closingprice}</th>
							<th>${item.currentprice}</th>
							<th>${item.hprice}</th>
							<th>${item.lprice}</th>
							<th>${item.totalnumber}</th>
							<th>${item.turnover}</th>
							<th>${item.increaseStr}</th>
							<th><a href="${item.minurl}" target="_bank">分时K线图..</a></th>
							<th><a href="${item.dayurl}" target="_bank">日K线图..</a></th>
							<th><a href="${item.weekurl}" target="_bank">周K线图..</a></th>
							<th><a href="${item.monthurl}" target="_bank">月K线图..</a></th>
						</tr>
				</c:forEach>
			</table>
</body>
</html>