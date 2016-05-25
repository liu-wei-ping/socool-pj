<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<style type="text/css">
.pg {
	text-align: left;
	/* 	overflow: auto; */
	padding-top: 2px;
	padding-left: 10px;
	/* 	font: 12px Arial; */
	height:150px;
	margin:6px; 
	color: #504141;
	/*  	box-shadow: 0 10px 0 #fff inset;  */
	border-radius: 10px;
}
.err{
	text-align: left;
	padding-top: 2px;
	padding-left: 10px;
	margin:6px; 
	height:150px;
	color: #504141;
	/*  	box-shadow: 0 10px 0 #fff inset;  */
	border-radius: 10px;
	border: 1px solid red;
}
.cx-btns {
	width: 50%;
	margin: 0 auto;
	text-align: center;
}

.cx-btns .base-btn {
	display: block;
	background: rgb(254, 231, 154);
	background: -moz-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: -webkit-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: -o-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: -ms-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fee79a',
		endColorstr='#fec151', GradientType=0);
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	-ms-border-radius: 30px;
	-o-border-radius: 30px;
	border-radius: 30px;
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	-ms-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	-o-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	border: 1px solid #D69E31;
	color: #85592e;
	cursor: pointer;
	font: bold 15px Helvetica, Arial, sans-serif;
	height: 35px;
	position: relative;
	text-decoration: none;
	margin: 0px 0 0px 10px;
	line-height: 35px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	width: 120px;
	color: #7E7E7E;
	float: left;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
}
.cx-btns .dis{
			
		background: rgb(254, 231, 154); 
	background: -moz-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: -webkit-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: -o-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: -ms-linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	background: linear-gradient(top, rgba(254, 231, 154, 1) 0%,
		rgba(254, 193, 81, 1) 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fee79a',
		endColorstr='#fec151', GradientType=0);
	-webkit-border-radius: 30px;
	-moz-border-radius: 30px;
	-ms-border-radius: 30px;
	-o-border-radius: 30px;
	border-radius: 30px;
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	-ms-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	-o-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.8) inset;
	cursor: not-allowed;
}
.cx-btns a[class=base-btn]:HOVER {
	text-decoration: none;
	background: rgb(254, 193, 81);
	background: -moz-linear-gradient(top, rgba(254, 193, 81, 1) 0%,
		rgba(254, 231, 154, 1) 100%);
	background: -webkit-linear-gradient(top, rgba(254, 193, 81, 1) 0%,
		rgba(254, 231, 154, 1) 100%);
	background: -o-linear-gradient(top, rgba(254, 193, 81, 1) 0%,
		rgba(254, 231, 154, 1) 100%);
	background: -ms-linear-gradient(top, rgba(254, 193, 81, 1) 0%,
		rgba(254, 231, 154, 1) 100%);
	background: linear-gradient(top, rgba(254, 193, 81, 1) 0%,
		rgba(254, 231, 154, 1) 100%);
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fec151',
		endColorstr='#fee79a', GradientType=0);
}

.pg .t_num {
	font: 20px Arial;
}
.pg .t_answer{
	text-align:right;
	font: 30px Arial;
	margin: 0px 20%;

	color: #0028FD;
}

</style>
<body>
	<form action="">
		<div class="base-container">
			<c:forEach items="${list}" var="item" varStatus="vs">
				<div class="pg" id="pg_${vs.index}"
					style="background:${vs.index%2==0?'rgba(52, 81, 232, 0.32)':'#E2D2D4'};">
					<h3>
						<span class="t_num">${vs.index+1}、</span>${item.testContent}？<span class="t_answer" id="t_answer_${vs.index}"></span>
					</h3>
					<ul style="list-style: none">
						<li>
						<c:forEach items="${item.optionsArr}" var="arr" varStatus="vr">
							<input type="radio" onclick="getRadioV(this,'t_answer_${vs.index}','${item.type}')" class="t_radio"  name="aa_${vs.index}" value="${fn:substring(arr,0,1)}" />${arr}</br>
						</c:forEach>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
			<div class="cx-btns">
				<a href="###" onclick="return false" disabled  style="display: none;" class="base-btn dis"  id="test-non">没题可做了！</a>
				<a href="###" class="base-btn" page="2" id="test-continue">继续做题</a><a
					href="###" class="base-btn" id="test-finish">答题结束</a>
				<a href="###" onclick="againTest(this)"  style="display: none;" class="base-btn"  id="test-again">重新做题</a>
			</div>
		</from>
</body>
<script type="text/javascript">
var totalPage=${totalPage};
var page=$("#test-continue").attr("page");
	function againTest(e){
		getTestInfo(1);
	}
	if(totalPage<page){
		$("#test-continue").hide();
		$("#test-again").css("display","block");
		$("#test-non").css("display","block");
	}
	function getTestInfo(page){
		$("#test-continue").show();
		$("#test-again").css("display","none");
		$("#test-non").css("display","none");
		$(".base-container").html('');
		var url="${pageContext.request.contextPath}/java-info/base.shtml?page="+page;
		$.ajax({
			url : url,
			async:false,
			cache : false,
			type : 'GET',
			dataType : 'json',
			success : function(res) {
				console.log(res);
//					var jsonRes = eval("(" + res + ")");
				var result="";
				$("#test-continue").attr("page",++page);
				if(res['totalPage']<page){
					$("#test-continue").hide();
					$("#test-again").css("display","block");
					$("#test-non").css("display","block");
				}
				$.each(res['list'],function(i,v){
					var li="";
					var type=v['type'];
// 					alert(type);
					$.each(v['optionsArr'],function(ii,vv){
						var kv=$.trim(vv).substr(0,1);
						var n=type==1?"aa_"+i+"_"+ii:"aa_"+i;
// 						alert(n);
						li+="<input type=\"radio\" onclick=\"getRadioV(this,\'t_answer_"+i+"\',"+type+")\" class=\"t_radio\" name="+n+" value=\""+kv+"\" />"+$.trim(vv)+"</br>"
					})
					var bg=i%2==0?"background:rgba(52, 81, 232, 0.32)":"background:#E2D2D4";
						result+="<div class=\"pg\" style=\""+bg+"\";\">"
						+"<h3>"
						+"	<span class=\"t_num\">"+(i+1)+"、</span>"+v['testContent']+"？<span class=\"t_answer\" id=\"t_answer_"+i+"\"></span>"
						+"</h3>"
						+"<ul style=\"list-style: none\"><li>"+li+"</li></ul>"
						+"</div>"
				})
				$(".base-container").html(result);
			}
			
		})
	}
	
function checRadio(){
	var f=true;
	$(".base-container").children("div").each(function(i,v){
		var list=$("input:radio[name='aa_"+i+"']:checked").val();
		if(list==null){
			$(this).attr("class","pg err");
			$("#t_answer_"+i).html("<font style='color:red;'>请选择答案</font>")
// 			$(this).attr("style","border: 1px solid red");
			f=false;
		}else{
			$(this).attr("class","pg");
		}
	})
	return f;
}
var vs="";
function getRadioV(e,n,type){
// 	vs="";
	var en=$(e).attr("name");
// 	alert(en);
	var v=$("input:radio[name='"+en+"']:checked").val();
// 	var v=$(e).val();
	if(type==1){
// 		vs="";
		if(vs.indexOf(v)==-1){	
			vs+=v;
		}
		$("#"+n).html(vs);
// 		vs="";
	}else{
// 		vs="";
		$("#"+n).html(v);
	}
	
}
	$(function(){
		var dds=$(".dis").html();
		$("#test-continue").on('click',function(){
// 			vs="";
			var f=checRadio();
			if(f){
				var page=$(this).attr("page");
				getTestInfo(page);
			}
// 			$(".base-container").remove();
		});
		$("#test-finish").on('click',function(){
			vs="";
			checRadio();
		});
	})
</script>
</html>