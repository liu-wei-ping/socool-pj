<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/center.css" />
<style type="text/css">
#main-content-frame {
	width: 100%;
	margin: 0px auto;
	padding-top: 6px;
	text-align: center;
	height: 100%;
	overflow: auto;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/menu.js"></script>
<script src="${pageContext.request.contextPath}/js/socool-center.js"></script>
<script type="text/javascript">
	$(function() {
		var leftmenu = [ {
			name : "JAVA面试",
			submenu : [ {
				name : 'java基础测试',
				url : 'java-info/base.html'
			}]
		},{
			name : "JAVA技术",
			submenu : [ {
				name : 'JAVA入门',
				submenu:[{
					name:"java1",
					submenu:[{
						name:'java1-1',
						url : "http://www.cjsdn.net/"
					}]
				}]
			} ]
		}];
		menuTree(leftmenu, '${pageContext.request.contextPath}');
	})
</script>
</head>
<body class="main-body">
	<div name="tree-area" id="tree-area" class="wrap-menu"></div>
	<div id="all-content">
		<div name="main-content" id="main-content"></div>
		<iframe id="main-content-frame" frameborder="0"
			name="main-content-frame" src="" scrolling="no"
			style="display: none;"></iframe>
		<ul class="vakata-context"></ul>
		<div style="display: block;" id="jstree-marker">&nbsp;</div>
</body>
</html>