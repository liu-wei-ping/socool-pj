<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="../../css/center.css" />
<script type="text/javascript" src="../../js/jquery.easyui.min.js"></script>
<script src="../../js/menu.js"></script>
<script type="text/javascript">
    function showMainContent(u) {
    	var content=$('#main-content');
//         $('#main-content').load(u,function(){});

        $.ajax({
//         	async: false,
            url: u,
            cache: false,
            type: 'Get',
            dataType: 'html',
            success: function(res) {
                try {
                    var jsonRes = eval("(" + res + ")");
                    if (jsonRes.error && jsonRes.error != '') {
                        content.html(jsonRes.error);
                        return;
                    }
                } catch (err) {
                }
                content.html(res);
            }
        });
    }

// 	function replaceMainContent(){
// 		var iframeHTML = $(document.getElementById('supportiframe').contentWindow.document.body).html();

// 		if(iframeHTML != ''){
// 			alert(iframeHTML);
// 			$('#main-content').html(iframeHTML);
// 			$(document.getElementById('supportiframe').contentWindow.document.body).html('');
// 		}
// 	}
</script>
</head>



<body class="main-body">
	<div name="tree-area" id="tree-area" class="wrap-menu">
		<!-- 		<div id="submenu_tree" class="wrap-menu"> -->
		<!-- 		<ul class="smenu-children" id="smenu"> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/agreement/index" s-menu-id="87" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>A50点买人APP</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/article/index" s-menu-id="94" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>协议合约编辑</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/patch/index" s-menu-id="108" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>补丁管理</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/stock/index" s-menu-id="109" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>股票列表管理</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link smenu-clicked" href="javascript:void(0);" url="/help/index" s-menu-id="85" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>帮助中心管理</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/traderinfo/index" s-menu-id="90" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>券商管理</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/sendnotice/index" s-menu-id="95" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>站内消息</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/forbben/index" s-menu-id="100" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>敏感词管理</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/Tools/messageshow" s-menu-id="160" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>用户消息查看</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/Msgtools/getmsguidshow" s-menu-id="164" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>站内消息阅读量查询</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/Msgtools/strtourl" s-menu-id="167" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>机器人问题生成器</a> -->
		<!-- 			</li> -->
		<!-- 				    <li class="smenu-node"> -->
		<!-- 							<a class="smenu-link" href="javascript:void(0);" url="/ResetPass/redisdshow" s-menu-id="169" s-menu-pid="86"> -->
		<!-- 						<i class="smenu-icon" style="background-image: url(/public/images/backstage/favouricon.png); background-size: auto; background-position: 50% 50%;"></i>重置密码错误次数查询</a> -->
		<!-- 			</li> -->
		<!-- 				</ul> -->
		<!-- 		</div> -->
	</div>

	<!-- 	<iframe id="supportiframe" name="supportiframe" onload="replaceMainContent();" style="display:none"></iframe> -->
	<div id="all-content">
		<div name="main-content" id="main-content"></div>

		<script type="text/javascript">
// $(document).ready(function() {

//     $('#submenu_tree #smenu').find("a").click(function() {
//         $('.smenu-link > a').each(function(i) {
//             $(this).removeClass('smenu-link smenu-clicked');
//             $(this).addClass('smenu-link');
//         });
//         $('#submenu_tree ul li a').removeClass('smenu-link smenu-clicked');
//         $('#submenu_tree ul li a').addClass('smenu-link');
//         $(this).removeClass('smenu-link');
//         $(this).addClass('smenu-link smenu-clicked');

//         var url = $(this).attr('url');
//     	var n_url = 'http://jyzd.gxq.com.cn/' + 'frame/show/m/' + $(this).attr('s-menu-pid') + '/s/' + $(this).attr('s-menu-id');

// 		top.history.pushState({ 1: 1 }, 1, n_url);

//     	showMainContent(url);
//     });

// 	    $('#submenu_tree').find('a').first().click();
// 	});

		var leftmenu=${leftmenu};

	$(function(){
		var m=new AccordionMenu({containerCls:'.wrap-menu',menuArrs:leftmenu});
		$("#tree-area").find("a[url!='javascript:void(0)']").click(function(){
			var url= $(this).attr('url');
	         $("a[url!='javascript:void(0)']").each(function(i) {
		         $(this).removeClass('menu-clicked');
		         $(this).addClass('menu-unClicked');
   		  	  });
// alert($(this).html());
	         $(this).removeClass('menu-unClicked');
	         $(this).addClass('menu-clicked');
		 		showMainContent(url);
		});

	});
	</script>



		<ul class="vakata-context"></ul>
		<div style="display: block;" id="jstree-marker">&nbsp;</div>
</body>
</html>