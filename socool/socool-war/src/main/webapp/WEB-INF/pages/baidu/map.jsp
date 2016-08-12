<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>百度地图</title>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#container {
	height: 100%
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=${ak}"></script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib/bdMP.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lib/richMarker.js"></script>

</head>
<body>
	<div id="container"></div>
</body>
<script type="text/javascript"> 
// var map = new BMap.Map("container");          // 创建地图实例  
// var point = new BMap.Point(116.404, 39.915);  // 创建点坐标  
// map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别  
// var map = new BMap.Map("container");    
  
//map.centerAndZoom(point, 15);    
//map.centerAndZoom("上海",15);   
// window.setTimeout(function(){  
//     map.panTo(new BMap.Point(116.409, 39.918));    
// }, 2000);
// var myCity = new BMap.LocalCity();
// myCity.get(myFun);


// function myFun(result){
// 	console.log(result);
// 	var cityName = result.name;
// 	map.setCenter(cityName);
// 	var point = new BMap.Point(result.center.lat, result.center.lng);  
// //	map.centerAndZoom(point, 15); 
// 	map.centerAndZoom(cityName);
// 	var marker = new BMap.Marker(point);  // 创建标注
// 	map.addOverlay(marker);               // 将标注添加到地图中
// 	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
// 	alert("当前定位城市:"+cityName);
// }


var map;
var geoc;
initMap();
  //创建和初始化地图函数：
  function initMap(){
    createMap();//创建地图
    getPosition();
    setMapEvent();//设置地图事件
    addMapControl();//向地图添加控件
    addMapOverlay();//向地图添加覆盖物
  }

  function createMap(){ 
     map = new BMap.Map("container"); 
     var myCity = new BMap.LocalCity();
     geoc = new BMap.Geocoder();    
 	var label = new BMap.Label("我是文字标注哦",{offset:new BMap.Size(0,0)});

    // var infoWindow = new BMap.InfoWindow("我在这。。。"); 
    
   // console.log(map.pointToOverlayPixel(point));
     myCity.get(function(result){   
    	
    	 //map.centerAndZoom(result.center, result.level);
    	// var traffic = new BMap.TrafficLayer();        // 创建交通流量图层实例      
    	 //map.addTileLayer(traffic);  
    	var level=result.level;
    	var point= new BMap.Point(result.center.lng, result.center.lat); 
    	 console.log(point);
    	BMap.Convertor.translate(point,0,function(point){
    		console.log(point);
    		map.centerAndZoom(point, level);
           // var text="银湖海岸城",mouseoverTxt=text+" "+parseInt(Math.random()*1000,10)+"套";
         //var myCompOverlay = new ComplexCustomOverlay(point, "银湖海岸城",mouseoverTxt);
var html2 = '<div style="position: absolute; margin: 0pt; padding: 0pt; width: 80px; height: 26px; left: -10px; top: -35px; overflow: hidden;">'
                +     '<img id="rm3_image" style="border:none;left:0px; top:0px; position:absolute;" src="http://api.map.baidu.com/library/RichMarker/1.2/examples/images/back1.png">'
				+ '</div>'
				+ '<label class=" BMapLabel" unselectable="on" style="position: absolute; -moz-user-select: none; display: inline; cursor: inherit; border: 0px none; padding: 2px 1px 1px; white-space: nowrap; font: 12px arial,simsun; z-index: 80; color: rgb(255, 102, 0); left: 15px; top: -35px;">$ 20 B</label>'
            var myCompOverlay= new BMapLib.RichMarker(html2, point,
					  {"anchor" : new BMap.Size(-18, -27),
					   "enableDragging" : true});
   
            map.addOverlay(myCompOverlay);
    	});

    	 geoc.getLocation(point,function(rs){
    		 var addComp = rs.addressComponents;
    		 var text=addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber
    		 label.setStyle({border:'1px solid #fff'});
    		 label.setContent("<div>"+text+"</div>");
    	 });
//     	var mk=new BMap.Marker(point);
//     	mk.setLabel(label);
//     	map.addOverlay(mk);    	
     });
     //var point = new BMap.Point(121.480237, 31.24916171); 
   //  map.openInfoWindow(infoWindow,point); 
    // map.centerAndZoom(new BMap.Point(121.48789949,31.24916171),11);
    }
    function setMapEvent(){
      map.enableScrollWheelZoom();
      map.enableKeyboard();
      map.enableDragging();
      map.enableDoubleClickZoom()
    }
    function addClickHandler(target,window){
      target.addEventListener("click",function(){
        target.openInfoWindow(window);
      });
    }
    function addMapOverlay(){
    }

    function addMapControl(){
      var scaleControl = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
      scaleControl.setUnit(BMAP_UNIT_IMPERIAL);
      map.addControl(scaleControl);
      var navControl = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
      map.addControl(navControl);
      var overviewControl = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:true});
      map.addControl(overviewControl);
    }
    
    function getPosition(){
      	var geolocation = new BMap.Geolocation();
    	geolocation.getCurrentPosition(function(r){
    		if(this.getStatus() == BMAP_STATUS_SUCCESS){
    			var mk = new BMap.Marker(r.point);
    			map.addOverlay(mk);
    			map.panTo(r.point);
    			//alert('您的位置：'+r.point.lng+','+r.point.lat);
//     			map.centerAndZoom(r.point.lng+','+r.point.lat);
    		}
    		else {
    			alert('failed'+this.getStatus());
    		}        
    	},{enableHighAccuracy: true})
    }
</script>
</html>