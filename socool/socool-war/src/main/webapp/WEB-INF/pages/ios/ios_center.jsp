<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			name : "IOS技术",
			submenu : [ {
				name : 'IOS入门',
				url : "http://www.cocoachina.com/ios/"
			} ]
		}, {
			name : "IOS面试",
			submenu : [ {
				name : 'IOS-1',
				url : 'ios-info/base.html'
			} ]
		} ];
		menuTree(leftmenu, '${pageContext.request.contextPath}');
		//  $('#submenu_tree #smenu').find("a").click(function() {
		//      $('.smenu-link > a').each(function(i) {
		//          $(this).removeClass('smenu-link smenu-clicked');
		//          $(this).addClass('smenu-link');
		//      });
		//      $('#submenu_tree ul li a').removeClass('smenu-link smenu-clicked');
		//      $('#submenu_tree ul li a').addClass('smenu-link');
		//      $(this).removeClass('smenu-link');
		//      $(this).addClass('smenu-link smenu-clicked');

		//      var url = $(this).attr('url');
		//  	var n_url = 'http://jyzd.gxq.com.cn/' + 'frame/show/m/' + $(this).attr('s-menu-pid') + '/s/' + $(this).attr('s-menu-id');

		//		top.history.pushState({ 1: 1 }, 1, n_url);

		//  	showMainContent(url);
		//  });

		//	    $('#submenu_tree').find('a').first().click();
		//	});

		//		var leftmenu=${leftmenu};
		// 	function replaceMainContent(){
		// 		var iframeHTML = $(document.getElementById('supportiframe').contentWindow.document.body).html();
		// 		if(iframeHTML != ''){
		// 			alert(iframeHTML);
		// 			$('#main-content').html(iframeHTML);
		// 			$(document.getElementById('supportiframe').contentWindow.document.body).html('');
		// 		}
		// 	}
	})
</script>
</head>
<body class="main-body">
	<div name="tree-area" id="tree-area" class="wrap-menu"></div>
	<!-- 	<iframe id="supportiframe" name="supportiframe" onload="replaceMainContent();" style="display:none"></iframe> -->
	<div id="all-content">
		<div name="main-content" id="main-content"></div>
		<iframe id="main-content-frame" frameborder="0"
			name="main-content-frame" src="" scrolling="no" style="display: none"></iframe>
		<ul class="vakata-context"></ul>
		<div style="display: block;" id="jstree-marker">&nbsp;</div>
</body>
</html>