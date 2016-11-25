/**
 * Created by Administrator on 2016/11/9.
 */

var $base_win=$('<div id="new-win"/>').window({
    title:'窗口',
    iconCls:'icon-save',
    width:800,
    height:600,
    modal:true,
    closed:true,
    closable:true,
    collapsible:false,
    minimizable:false,
    maximizable:true
});

/**
 * Open 窗口 模态
 * */
function openModalWin(url,title,width,height,params,py_width,py_height){
    var  im_width=(width||800)-(py_width||50);
    var  im_height=(height||600)-(py_height||50);
    var c= '<iframe scrolling="auto"  height="'+im_height+'"  width="'+im_width+'" style="position: absolute; left: 0; top: 0; right: 0; bottom:0;margin: auto;" frameborder="0"  src="'+url+'" id="new-iframe"></iframe>';
    $base_win.window({content:c,title:title,width:width,height:height, modal:true,params:params});
    $base_win.window('open');
}

function openModalHrefWin(url,title,width,height,params,py_width,py_height){
    var  im_width=(width||800)-(py_width||50);
    var  im_height=(height||600)-(py_height||50);
    //  var c= '<iframe scrolling="auto"  height="'+im_height+'"  width="'+im_width+'" frameborder="0"  src="'+url+'" id="new-iframe"></iframe>';
    $base_win.window({href:url,title:title,width:width,height:height, modal:true,params:params});
    $base_win.window('open');
}

/**
 * Open 窗口 非模态
 * */
function openNoModalWin(url,title,width,height,params){
    var  im_width=(width||800)-(py_width||50);
    var  im_height=(height||600)-(py_height||50);
    var c= '<iframe scrolling="auto"  height="'+im_height+'"  width="'+im_width+'" frameborder="0"  src="'+url+'" id="new-iframe"></iframe>';
    $base_win.window({content:c,title:title,width:width,height:height, modal:false,params:params});
    $base_win.window('open');
}

/*
 * 关闭窗口 parent.closeWin();
 * */
function closeWin(){
    $base_win.window('close');
}
