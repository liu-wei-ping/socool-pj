<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {background: #3976B3; font-family:Arial, Helvetica, sans-serif; font-size:12px; margin:0px; margin-bottom:2px;}
	.active a {color: #FFFFFF;background:#2a68a6;display:block;}
	.active a:hover,.active a.hover{background: #f7b42c;}
	.inactive a {color: #FFFFFF;background:#2a68a6;display:block;}
	.inactive a:hover,.inactive a.hover{background: #2a68a6;}
	</style>
	<style type="text/css">
h1 {color: #FFF;}
a {color: #FFF; text-decoration: none;/*防止滤镜下链接失效*/position:relative;}
ul { list-style:none;margin:0px; overflow:hidden;}
#all {width: 100%;}
#banner {margin-top: 8px; margin-left: 32px; overflow:hidden; position:relative;}
#banner h1{display:block; width:500px;}
#main {width: 100%; margin-bottom: 2px; background:#eeeeee; margin-left: 0px; margin-right:0px; height: 30px; color: #000; line-height: 2.4;overflow: auto;}
#main a {color:#000;}
#welcome {font-weight: 800; padding-left: 8px; position:absolute;color:#FFF;top:23px;right:13px;}
#adminop { float:left; width: 59%; position:relative; text-align:right; line-height:1; *line-height:2.2;}
#nav {width: 100%; clear: both; overflow:hidden;}
#smenu{float:left;padding-left:30px;}
#nav ul li {float: left; width:82px; height:25px; line-height: 2.1; text-align: center;margin:0 5px;}
#manage{overflow:hidden; float:right;width:250px;}
#manage li{width:80px;margin-top:10px;float: right;}

.blankgray {background:#bbb; height:2px; width:100%; margin:0; padding:0; clear:both; font-size:2px;}

#menu img {_margin-top: 12px;}/*没办法,ie6对list-style-image支持不好*/
#all {width: 100%;height:100%;}
#menu {width: 100%;}
#menu ul {padding:0; margin: 0; list-style: none; overflow:hidden;background-color:#DDD;padding-left:30px;border-bottom:2px solid #ccc;border-top:2px solid #ccc;}
#menu ul li {float:left;height: 22px; margin: 2px 20px; line-height: 22px; list-style-image: url("http://jyzd.gxq.com.cn/public/images/backstage/li.jpg");}
#menu ul li a{padding:2px;}
.menu_selected{border-bottom:1px solid #000;}
#authmgr .l-btn-text{display: inline-block; vertical-align: top; width: auto; line-height: 100%; font-size: 12px; padding: 0; margin-right: 20px;}
</style>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<div id="all">
    <div id="banner">
			<h1>Android</h1>
	        <div id="welcome">
            欢迎! ${sessionScope['socool_user']['username']}
                        &nbsp;     </div>
       <ul id="manage">
<!--             <li><a class="" href="http://jyzd.sina.com/" target="_blank">官网首页</a></li> -->
<!--             <li><a href="###" target="b_main" style=C"display: none;">修改密码</a></li> -->
            <li><a class="logout" href="${pageContext.request.contextPath}/login/quit.html" target="_top">退出管理</a></li>
            <li><a href="${pageContext.request.contextPath}/main/index.html" target="_top">首 页</a></li>
        </ul>
    </div>
   
    <div id="nav">
    	<!-- 显示顶层菜单 -->
                <ul id="smenu">
<%--                         ${smenu} --%>
<!--                         <li class="nav_menu inactive" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="119" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/119"><span class="parent_menu">运营统计<span></span></span></a> -->
<!--                           </li>  -->
<!--                         <li class="nav_menu inactive" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="8" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/8"><span class="parent_menu">用户管理<span></span></span></a> -->
<!--                           </li>  -->
<!--                         <li class="nav_menu inactive" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="27" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/27"><span class="parent_menu">账户管理<span></span></span></a> -->
<!--                           </li>  -->
<!--                         <li class="nav_menu inactive" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="88" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/88"><span class="parent_menu">风控管理<span></span></span></a> -->
<!--                           </li>  -->
<!--                         <li class="nav_menu inactive" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="91" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/91"><span class="parent_menu">中介管理<span></span></span></a> -->
<!--                           </li>  -->
<!--                         <li class="nav_menu active" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="86" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/86"><span class="parent_menu">站点支援管理<span></span></span></a> -->
<!--                           </li>  -->
<!--                         <li class="inactive nav_menu" id="account_set">  -->
<!--                               <a class="hover" main-menu-id="111" href="###" url="http://jyzd.gxq.com.cn/frame/main/id/111"><span class="parent_menu">核算<span></span></span></a> -->
<!--                           </li>  -->
 
                    </ul>
        
    </div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var groupid = 1
	if(groupid==0){
		$("#authmgr").css("display","block");
	}else{
		$("#authmgr").css("display","none");
	}
	//控制头部link点击时的显示效果
    $('#nav #smenu').find("a").click(function() {
        $('.nav_menu > a').each(function(i) {
            $(this).parents('li').removeClass('active');
            $(this).parents('li').addClass('inactive');
        });
        $(this).parents('li').removeClass('inactive');
        $(this).parents("li").addClass('active');
		$('#b_main', window.top.document).attr('src',$(this).attr('url'));
    });

	$('.nav_menu').removeClass('active');
	$('a[main-menu-id=0]').parents('li').removeClass('inactive');
	$('a[main-menu-id=0]').parents('li').addClass('active');
	
	//默认跳转至头部的第一个link
	    $('#nav').find('.parent_menu').first().click();
    

	$("#menu_mgr").click(function(){
		window.parent.frames["b_main"].location.href="${pageContext.request.contextPath}/sources/menumgr/menumgr.html";
	});
	
	$("#user_mgr").click(function(){
		window.parent.frames["b_main"].location.href="${pageContext.request.contextPath}/sources/usermgr/usermgr.html";
	});
	
	$("#group_mgr").click(function(){
		window.parent.frames["b_main"].location.href="${pageContext.request.contextPath}/sources/groupmgr/groupmgr.html";
	});
	
});
</script>
</html>