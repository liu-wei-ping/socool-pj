<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="百度地图,百度地图API，百度地图自定义工具，百度地图所见即所得工具" />
    <meta name="description" content="百度地图API自定义地图，帮助用户在可视化操作下生成百度地图" />
    <title>百度地图API自定义地图</title>

  </head>
  
  <body>
    <!--百度地图容器-->
 <div>
	城市: <input id="cityName" type="text" style="width:100px; margin-right:10px;" />
	<input type="button" value="城市查询" onclick="theLocation()" />
	关键字: <input id="mapKey" type="text" style="width:100px; margin-right:10px;" />
	<input type="button" value="关键字查询" onclick="localSearch()" />
    <div style="width:800px;height:700px;float:left; border:#ccc solid 1px;font-size:12px" id="map"></div><div id="r-result"></div>
</div>
  </body>
  <script type="text/javascript">
  var map;
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
      map = new BMap.Map("map"); 
     map.centerAndZoom(new BMap.Point(121.48789949,31.24916171),11);
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
    //向地图添加控件
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
// 			map.centerAndZoom(r.point.lng+','+r.point.lat);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
}
  	// 百度地图API功能
//   	var point = new BMap.Point(121.48789949,31.24916171);
//   	map.centerAndZoom(point,11);

  	function theLocation(){
  		var city = document.getElementById("cityName").value;
  		if(city != ""){
  			map.centerAndZoom(city,11);      // 用城市名设置地图中心点
  		}
  	}
  	function localSearch(){
  		var key=document.getElementById("mapKey").value;
  		var myKeys=[];  
  		myKeys.push(key);
  		var local = new BMap.LocalSearch(map, {
  			renderOptions:{map: map, panel:"r-result"},
  			pageCapacity:5
  		});
  		local.searchInBounds(myKeys, map.getBounds());
  	}

  </script>
</html>