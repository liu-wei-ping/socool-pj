<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录跳转...</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>
<body>
<style>
        body,h2,dl,dd,p,ul{  margin: 0;}
        ul,dt{ padding: 0}
        ul li{ list-style: none}
        body{ font: 0.6rem "微软雅黑","黑体",  "宋体", Arial;}
        a {text-decoration: none; outline: none;}
        html {
            font-size: 20px;
        }
        @media (min-device-width: 320px) {
            html {
                font-size: 20px;
            }
        }
        @media (min-device-width: 360px) {
            html {
                font-size: 22.5px;
            }
        }
        @media (min-device-width: 375px) and (max-device-width: 410px) {
            html {
                font-size: 23.4375px;
            }
        }
        @media (min-device-width: 414px) {
            html {
                font-size: 25.875px;
            }
        }
        .screen{
            text-align: center;
            margin-top: 10%;
        }
        .top1{
            color: #666;
            font-size: 1.275rem;
            margin-bottom:0.9rem;
        }
        .top2 img{
            width:5.4rem;
            height:1.1rem;
        }
        .top3{
            color: #d7d7d7;
            font-size: 0.51rem;
            margin-top: 0.3rem;
        }
        .top3 span{
            color: #ffac48;
            margin-right: 0.11rem;
        }
    </style>
<body id="content"></body>
<div class="screen">
    <p class="top1">登录跳转</p>
    <p class="top2"><img src="${pageContext.request.contextPath}/images/hclwait.gif"> </p>
    <p class="top3"><span id='tt'></span>正在为您跳转，请稍候</p>
</div>
<script type="text/javascript" language="javascript">
var locationUrl = "../main/index.html"
getLoginCallback(3,locationUrl);
var timer = null;
var t = 0;
function getLoginCallback(num,url){
    if(t){
        $('#tt').empty().html((num - t) + '秒');
    }else{
        $('#tt').empty().html(num + '秒');
    }
    
    if(timer){
        clearTimeout(timer);
    }
    if(t>=num){
        location.href = url;
    }else{
        timer = setTimeout(function(){
            t++;
            getLoginCallback(num,url);
        },1000)
    }
}
</script>
</body>
</html>