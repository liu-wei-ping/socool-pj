
/**
 *
 * @param url
 * @param data
 * @param callback
 */
function ajaxFun(url, data, callback) {
    var type =( data==undefined||data == null) ? "GET" : "POST";
    $.ajax({
        url : url,
        data : data,
        type : type,
        dataType : 'json',
        success : function(req) {
            if(typeof(callback)!=="function"){
                messageBox(true);
            }else{
             callback(req);
            }
        },
        error : function() {
            messageBox(false,"系统错误!");
        }
    });
}
/**
 * 数组删除指定的值
 * @param arr
 * @param val
 */
function removeByValue(arr, val) {
    for(var i=0; i<arr.length; i++) {
        if(arr[i] == val) {
            arr.splice(i, 1);
            break;
        }
    }
}



/**
 *  获取中文月份
 * @param month 01,02....12
 */
function getMonthCn(month){
    var months_cn = [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
        '八月', '九月', '十月', '十一月', '十二月' ];
    for(var $i=1;$i<=12;$i++){
        var m=$i<10 ? "0"+$i : $i;
        if(month==$i){
            return months_cn[$i-1];
        }
    }
}

/**
 *
 * @param flag
 * @param successMsg
 * @param errorMsg
 * @param callback
 * @param timeOut
 */
function messageBox(flag,successMsg,errorMsg,callback,title,timeOut){
     timeOut=timeOut||2000;
     title=title||"";
    var icon;
    var msg="";
    if(flag){
        icon="success";
        msg=successMsg!=undefined &&  successMsg!="" ? successMsg : "操作成功!";
    }else{
        icon="error";
        msg=errorMsg!=undefined && errorMsg!="" ? errorMsg : "操作失败!";
    }
    var timeOut2=timeOut-1000>0 ? timeOut-1000 : 1000;
   var top=document.body.scrollHeight/2;
    var dlg = $.messageBox({
        title:title,
        msg:msg,
        width:'auto',
        height:'auto',
        icon: icon,//info,warning ,question,error
        showType: 'slide',
        timeout:0,
        style:{
            position: 'fixed',
            right:'',
            top:'50%',
            bottom:''
        }
    });
    var tt= setTimeout(function(){
            dlg.window('close');
        if(flag){
            setTimeout(function(){
                if (typeof callback === "function"){
                    callback();
                }
                clearTimeout(tt);
            },timeOut2);
        }
    },timeOut)
}

function confirmBox(tipMessage,callblck){
    $.messager.confirm("确认操作",tipMessage,function(falg){
            callblck(falg);
    });
}
/**
 *  文件上传 参照 witnessInformation.phtml
 * @param e
 */
function uploadFile(e)
{
    var fileId=$(e).attr("relation-file");
    var formId=$(e).attr("relation-form");
    if(fileId==undefined || fileId==undefined){
        messageBox(false,"","插件参数错误！");
        return;
    }
    var file= $.trim($("#"+fileId).filebox("getValue"))||"";
    if(file!=""){
        var fileName=file.substr(file.lastIndexOf("\\")+1) ||file;
        $.messager.confirm("确认操作","确定要上传[<font color='red'>"+fileName+"</font>]吗？",function(data){
            if (data) {
                $("#"+formId).submit();
            }
        })
    }else{
        messageBox(false,"","请选择上传的文件!");
    }
}
/**
 *
 *  文件上传回调函数
 * @param res
 */
function uploadAfter(res){
    var jsonRes = eval("("+res+")");
    var f=(jsonRes.result == 'Y');
    if(f){
        if(jsonRes.errorCode=="5001"){
            $.messager.alert('',jsonRes.errorMsg,'warning');
            return;
        }
    }
    messageBox(f,jsonRes.errorMsg,jsonRes.errorMsg);
}

/*
* 表单条件 查询方法
* */
function searchByFrom(tableId,formId,url) {
    var tableElement=$('#'+tableId);
    var options = tableElement.datagrid('options');
    if(formId!=undefined){
        var formElement=$('#'+formId);
        var params=options.queryParams;
        var fields = formElement.serializeArray();
        $.each(fields, function(i, field) {
            params[field.name] = field.value; //绑定参数
        });
    }
    if(url!=undefined && url!=""){
        options.url=url;
        tableElement.datagrid({
            pagination: true,
        });
    }else{
      tableElement.datagrid('reload');
    }
}

function searchByParams(tableId,paramsArr){

    var tableElement=$('#'+tableId);
    if(paramsArr){
        var params = tableElement.datagrid('options').queryParams;
        $.each(paramsArr, function(key, value) {
            params[key] = value;
        });
    }
    tableElement.datagrid('reload');
}
/**
 *  保存操作
 * @param url
 * @param formId [{name:key1,value:value1},{name:key2,value:value2}]
 * @param callback
 */
function saveFormPost(url,fields,callback){
    if(fields!=undefined && fields!=null){
        var params={};
        $.each(fields, function(i, field) {
            params[field.name] = field.value; //绑定参数
        });
        //console.log(params);
        ajaxFun(url,params,callback);
    }
}
/**
 *  格式化参数
 * @param {}
 * @returns {Array} [{name:key1,value:value1},{name:key2,value:value2}]
 */
function  formatParams(params){
    var pp=new Array();
    $.each(params, function(i, v) {
        var p={};
        p['name']=i;
        p['value']=v;
        pp.push(p);
    });
    return pp;
}
/**
 *  差集
 * @param arr1
 * @param arr2
 * @returns {*}
 */
function diffArray(arr1, arr2){
    for (var i = 0 ; i < arr1.length ; i ++ ){
        for(var j = 0 ; j < arr2.length ; j ++ ){
            if (arr1[i] === arr2[j]){
                arr1.splice(i,1);
            }
        }
    }
    return arr1;
}
/**
 *  交集
 * @param arr1
 * @param arr2
 * @returns {Array}
 */
function intersectArray(arr1,arr2){
    var result = new Array();
    if(!arr1 || arr1==undefined || !arr2 || arr2==undefined){
        return result;
    }
    arr1= arr1.sort();
    arr2= arr2.sort();
    var ai=0, bi=0;
    while ( ai < arr1.length && bi < arr2.length )
    {
        if( arr1[ai] < arr2[bi] ){ ai++; }
        else if ( arr1[ai] > arr2[bi]){ bi++; }
        else
        {
            result.push ( arr1[ai] );
            ai++;
            bi++;
        }
    }
    return result;
}

function checkTimeParams(startId,endId){
    var startDate =$('#'+startId).datetimebox('getValue');
    var endDate =$('#'+endId).datetimebox('getValue');
    var startArr = startDate.split("-");
    var startTime = new Date(startArr[0], startArr[1], startArr[2]);
    var startTimes = startTime.getTime();
    var endArr = endDate.split("-");
    var endTime = new Date(endArr[0], endArr[1], endArr[2]);
    var endTimes = endTime.getTime();
    alert(startTimes);
    alert(endTimes);
    if (startTimes > endTimes) {
        return false;
    }
    return true;
}


    /**
     *  datetimebox 控件设置时间 默认当前时间  注意调用 $(function(){}) 格式 YYYY-MM-DD HH:mm:ss
     * @param el:控件ID，diffDate:距当前时间天数,curr_time:时间
     * @param curr_time
     */
    function defaultDateTime(el,diffDate,curr_time){
            var dateObj= $("#"+el)||el;
            var curr_time =curr_time==undefined ? new Date() : new Date(curr_time);
            if(diffDate!==undefined){
                curr_time.setDate(curr_time.getDate()+diffDate);
            }
            var month=curr_time.getMonth()+1+"-";
            var day= curr_time.getDate();
            var strDate = curr_time.getFullYear()+"-";
            strDate += (curr_time<10 ? "0"+month : month);
            strDate += (day<10 ? "0"+day : day)+" ";
            strDate += curr_time.getHours()+":";
            strDate += curr_time.getMinutes()+":";
            strDate += curr_time.getSeconds();
            dateObj.datetimebox('setValue', strDate);
        }


/**
 *
 *
 */
$.extend($.fn.validatebox.defaults.rules,{
    //TimeCheck:日期时间比较验证扩展 调用DEMO validType="TimeCheck['apply_start_time','apply_end_time','gt']"
    TimeCheck:{
        validator:function(value,param){
            var flag=true;
            //var s = $("input[name="+param[0]+"]").val();
            var startTime = $("#"+param[0]).datetimebox("getValue");
            var endTime = $("#"+param[1]).datetimebox("getValue");
            var compare=(param[2]||"gt").toLocaleLowerCase();//gt:大于，lt:小于 默认大于
            if(compare=="lt" && endTime!=''){
                flag=  value<=endTime;
            }else if(compare=="gt" && startTime!=''){
                flag= value>=startTime
            }
            return flag;
        },
        // 消息重写属性 invalidMessage="错误信息提示"
        message:'请选择正确的日期！'
    },
    //移动手机号码验证
    Mobile: {
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '请输入正确的手机号码！.'
    },
    //国内邮编验证
    ZipCode: {
        validator: function (value) {
            var reg = /^[0-9]\d{5}$/;
            return reg.test(value);
        },
        message: '请输入正确的邮政编码！'
    },
    //数字
    Number: {
        validator: function (value) {
            var reg =/^[0-9]*$/;
            return reg.test(value);
        },
        message: '请输入数字！'
    },
});

$.extend({
    messageBox: function (options) {
        var _msg = '<div style="width: 100%;">';
        if (options.icon != undefined) {
            _msg += '<span class="messager-icon messager-' + options.icon + '"></span>';
        }
        if (options.msg != undefined) {
            _msg += '<span style="word-break:break-all;font-size: 25px;font-weight:500;">' + options.msg + '</span>';
        }
        _msg += '</div>';
        options.msg = _msg;
       return   $.messager.show(options);

    }

})

//$.extend($.fn.datagrid.defaults.editors, {
//    text: {
//        init: function(container, options){
//            var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);
//            return input;
//        },
//        destroy: function(target){
//            $(target).remove();
//        },
//        getValue: function(target){
//            return $(target).val();
//        },
//        setValue: function(target, value){
//            $(target).val(value);
//        },
//        resize: function(target, width){
//            $(target)._outerWidth(width);
//        }
//    }
//});
$.extend($.fn.datagrid.methods, {
    addEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor; });
        } else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    removeEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    }
});

