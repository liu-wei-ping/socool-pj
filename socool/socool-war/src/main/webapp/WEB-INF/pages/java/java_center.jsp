<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
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
<!--<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>  -->
<script src="http://lib.sinaapp.com/js/jquery/3.1.0/jquery-3.1.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/menu.js"></script>
<script src="${pageContext.request.contextPath}/js/socool-center.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=VXGVbHDvutI4WQFjzH509VD1HXbdhwFE">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
<script type="text/javascript">
	$(function() {
		var leftmenu = [ {
			name : "JAVA技术",
			submenu : [ {
				name : "百度地图",
				url : "java-api/bdMap.html"
			},{
				name : "百度API",
				url : "java-api/bdApi.html"
			}, {
				name : "JAVA爬虫",
				url : "java-tech/crawler.html"
			}, {
				name : "JAVA邮箱",
				url : "java-tech/mail.html"
			},{
				name : 'JAVA入门',
				submenu : [ {
					name : "java1",
					submenu : [ {
						name : 'java1-1',
						url : "http://www.cjsdn.net/"
					} ]
				} ]
			} ]
		}, {
			name : "JAVA面试",
			submenu : [ {
				name : 'java基础测试',
				url : 'java-info/base.html'
			} ]
		} ];
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