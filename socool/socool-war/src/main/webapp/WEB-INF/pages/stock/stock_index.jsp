<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>股票行情数据</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
  <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-win.js"></script> --%>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
	<div style="width:80%;margin: 0 auto;">
	  <table id="stock-table"></table>
	</div>
	  <div id="toolbar">
	  	  <a class="easyui-linkbutton"   data-options="iconCls : 'icon-back'" href="javascript:void(0);" onclick="to_url('${pageContext.request.contextPath}/main/index.html')" >返回首页</a>&nbsp;&nbsp;
	  	  <a class="easyui-linkbutton"   id="add_data" data-options="iconCls : 'icon-reload'" onclick="refreshStockHq('stock-table')" >刷新行情</a>&nbsp;
	  	  <a class="easyui-linkbutton"   id="add_data" data-options="iconCls : 'icon-import'" onclick=" $('#stock-win').window('open')" >录入股票</a>&nbsp;
	  	  <label>开始日期：</label><input id="startDate" class="easyui-datebox" data-options="disabled:false" style="width: 200px"> ~
	  	  <label>结束日期：</label><input id="endDate" class="easyui-datebox" data-options="disabled:false" style="width: 200px">
	  	  <label>股票代码/名称：</label><input id="stock" class="easyui-textbox" style="width: 200px">
	  	  <a class="easyui-linkbutton"   data-options="iconCls : 'icon-search'" onclick="searchStockHq('stock-table')" >查询</a>
	  </div>
	  <div class="easyui-window" id="stock-win" title="录入股票" data-options="iconCls:'icon-save',modal:true,closed:true,minimizable:false,maximizable:false" style="width:300px;height:300px;text-align: center;padding: 10px;">
	    <form id="stock-form">
			  <div tyle="height:80%;margin: 0 auto">
			  	<label>&nbsp;交易所：</label><select class="easyui-combobox" name="excode" required data-options="panelHeight:'auto',width:160">
			  		<option value="sh">上海交易所</option>
			  		<option value="sz">深圳交易所</option>
			  	</select><br><br>
			  	<label>股票代码：</label><input name="stockCode" class="easyui-textbox" required style="width: 160px"><br><br>
			  	<label>股票名称：</label><input name="stockName" class="easyui-textbox" required style="width: 160px">
			  </div>
		  </form>
		  <div style="height:10%;margin: 0 auto"></div>
	  	  <div style="height:10%;margin: 0 auto" >
    			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="loadNewStock()">确 定</a>&nbsp;
   				 <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#stock-win').window('close');" data-options="iconCls:'icon-cancel'" id="cancel-btn">取 消</a>
  		</div>
	  </div>
</body>
<script type="text/javascript">
$('#stock-table').datagrid({
    title:"股票行情数据",
    url: '${pageContext.request.contextPath}/stock/getStockHqInfo.shtml',
    method: 'POST',
    idField: 'code',
    striped: true,
    fitColumns: true,
    singleSelect: false,
    rownumbers: true,
    pagination: true,
    nowrap: false,
    pageSize:20,
    remoteSort: true,
    sortOrder:'desc',
    sortName:'increase',
    pageList: [20,30,50,80],
    showFooter: true,
    toolbar:'#toolbar',
    columns:[[
              {field: 'ck', checkbox: true,hidden:false},
              {field:'code',title:'股票代码',align:'center',width:'6%'},
              {field:'name',title:'股票名称',align:'center',width:'7%'},
              {field:'currentprice',title:'当前价格',align:'center',width:'6%',sortable:true,},
              {field:'closingprice',title:'昨日收盘价',align:'center',width:'6%',sortable:false},
              {field:'hprice',title:'今日最高价',align:'center',width:'6%',sortable:false},
              {field:'lprice',title:'今日最低价',align:'center',width:'6%',sortable:false},
              {field:'openningprice',title:'开盘价',align:'center',width:'6%',sortable:false},
              {field:'totalnumber',title:'成交量股',align:'center',width:'8%',sortable:true},
              {field:'turnover',title:'成交额',align:'center',width:'8%',sortable:true},
              {field:'increase',title:'涨幅',align:'center',width:'6%',sortable:true,styler:function(value,row,index){
            	  if(row.increaseStr>0){
            		  return 'color:#FF0000;font-weight: bold';
            	  }else{
            		  return 'color:#0FD442;font-weight: bold';
            	  }
              }},
              {field:'date',title:'日期',align:'center',width:'6%'},
              {field:'time',title:'时间',align:'center',width:'6%'},
              {field:'updateTime',title:'刷新时间',align:'center',width:'13%'},
              ]]
    })

    function refreshStockHq(table){
		var url="${pageContext.request.contextPath}/stock/updatestockHq.shtml"
		ajaxFun(url, null,function(req){
			messageBox(req["result"]=="Y", "获取行情成功!", "获取行情失败!",function(){
				searchByFrom(table);
			})
		})
    }
    function searchStockHq(table){
    	var stock=$.trim($("#stock").val());
    	var startDate=$.trim($("#startDate").datebox("getValue"));
    	var endDate=$.trim($("#endDate").datebox("getValue"));
    	var params={"stock":stock,"startDate":startDate,"endDate":endDate}
		searchByParams(table,params);
    }
    function loadNewStock(){
    	if(!is_stock($.trim($("input[name='stockCode']").val()))){
    		messageBox(false,"","填写正确的股票代码!");
    		return;
    	}
    	var params=$("#stock-form").serializeArray();
    	var url="${pageContext.request.contextPath}/stock/loadNewStock.shtml"
		ajaxFun(url, params,function(req){
			messageBox(req["result"]=="Y", "录入成功", "录入失败!",function(){
				searchByFrom(table);
			})
		})
    }
    function is_stock(code){
		if( /\d{6}/.test(code)){
			code = code.toString();
			if( code.substring(0,1) == "4" ){ //三板
				return false;
			}
			else if ("100,101,108,109,111,112,115,119,125,126,129,131,010,019,020,130,110,113,104,105,106,107,201,202,203,204".indexOf(code.substring(0, 3)) > -1 || "75,12".indexOf(code.substring(0, 2)) > -1){ //债券
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
    }
    function code_market(code){
		var sh_or_sz = "sh";
		var i = code.substring(0, 1);
		var j = code.substring(0, 3);
		if (i == "5" || i == "6" || i == "9"){
			//上证股票
		}
		else{
			if (code == "000003" || code == "000300"){
				//上证股票
			}
			if (j == "009" || j == "126" || j == "110"){
				//上证股票
			}
			else{
				sh_or_sz = "sz";//深圳股票
			}
		}
		return sh_or_sz;
   }
    
</script>
</html>