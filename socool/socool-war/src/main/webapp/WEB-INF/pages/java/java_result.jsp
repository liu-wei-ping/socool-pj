<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>题目ID</th>
			<th>科目</th>
			<th>答案</th>
		</tr>
		<c:forEach items="${result}" var="item">
			<tr>
				<th>${item.id}</th>
				<th>${item.testId}</th>
				<th>${item.userId}</th>
				<th>${item.userAnswer}</th>
			</tr>
		</c:forEach>
	</table>
</body>
</html>