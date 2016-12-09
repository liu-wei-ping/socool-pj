function _g_offset_time() {
	var date = new Date();
	var yy = date.getFullYear();
	var mm = date.getMonth();
	var dd = date.getDate();
	var hh = date.getHours();
	var ii = date.getMinutes();
	var ss = date.getSeconds();
	var i;
	var tm = 0;
	for (i = 1970; i < yy; i++) {
		if ((i % 4 == 0 && i % 100 != 0) || (i % 100 == 0 && i % 400 == 0)) {
			tm = tm + 31622400;
		} else {
			tm = tm + 31536000;
		}
	}
	mm = mm + 1;

	for (i = 1; i < mm; i++) {
		if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10
				|| i == 12) {
			tm = tm + 2678400;
		} else {
			if (i == 2) {
				if ((yy % 4 == 0 && yy % 100 != 0)
						|| (yy % 100 == 0 && yy % 400 == 0)) {
					tm = tm + 2505600;
				} else {
					tm = tm + 2419200;
				}
			} else {
				tm = tm + 2592000;
			}
		}
	}

	tm = tm + (dd - 1) * 86400;
	tm = tm + hh * 3600;
	tm = tm + ii * 60;
	tm = tm + ss;
	return tm;
}
//写cookies
function WriteCookie(name, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = "; expires=" + expire.toGMTString() + ";path=/;";
        var d=_g_domain(document.domain);
        if(d != ""){
        	expire +="domain="+d+";";
        }
    }
    document.cookie = name + "=" + escape(value) + expire;
}
//取cookies
function GetCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) {
            return null;
        };
    } else {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1) {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}
//删除cookies
function delCookie(name){
    var dc = document.cookie;
    console.log(dc);
	var cval=GetCookie(name);
	if(cval){
		console.log(cval);
		WriteCookie(name,cval,-1);
	}
}

//encode url
function _convert_en(str)
{
	var en="",i=0;
	for(i=0;i<str.length;i++){ 
		if(str.charCodeAt(i)>=0&&str.charCodeAt(i)<=255){ 
			  en=en+escape(str.charAt(i));
			}
			else {
				en=en+str.charAt(i);
			}
	}
  return en;
}

/* check browse's state start */
//get screen width&height
function _g_screen_pix() {
	if (self.screen) { 
		sr=screen.width+"x"+screen.height;
	}
	else if (self.java) { 
		var j=java.awt.Toolkit.getDefaultToolkit();
		var s=j.getScreenSize();
		sr=s.width+"x"+s.height;
	}
	return sr;
}

//get navigator's appName
function _g_nvapp() {
	var so="";
	var n=navigator;
	if (n.appName) {
		so=n.appName;
	}
	return so;
}

//get screen color depth
function _g_scr_color() { 
	var sc="";
	if (self.screen) {
		sc=screen.colorDepth+"-bit";
	}
	return sc;
}

//get browser Language
function _g_language() {
	var lg="";var n=navigator;if (n.language) {
		lg=n.language.toLowerCase();
	}
	else if (n.browserLanguage) {
		lg=n.browserLanguage.toLowerCase();
	}
	return lg;
}

//get browser userAgent
function _g_agent() {
	var ag="";
	var n=navigator;
	if (n.userAgent) { 
		ag = n.userAgent;
	}
  return ag;
}
//is or not allow java
function _g_javaenabled() { 
	var je="";
	var n=navigator;
	je = n.javaEnabled()?1:0;return je;
}

//get flash version
function _g_flashv() {
	var f="",n=navigator;
	if (n.plugins && n.plugins.length) {
		for (var ii=0;ii<n.plugins.length;ii++) {
			if (n.plugins[ii].name.indexOf('Shockwave Flash')!=-1) {
				f=n.plugins[ii].description.split('Shockwave Flash ')[1];
				break;
			}
		}
	}
  else if (window.ActiveXObject) {//ie
  	for (var ii=10;ii>=2;ii--) {
  		try {
  			var fl=eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash."+ii+"');");
  			if (fl) { f=ii + '.0';
  				break;
  			}
  		}
  		catch(e)
  		{}
  	}
  }
  return f;
}

// is or not allow cookie
function _g_cookieenabled() {
	var c_en = (navigator.cookieEnabled)? 1 : 0;return c_en;
}
/* check browse's state end */

//get domain
function _g_domain(host)
{
	var d=host.replace(/^www\./, "");

	var ss=d.split(".");
	var l=ss.length;

	if(l == 3){
		if(_c_ctry_top_domain(ss[1]) && _c_ctry_domain(ss[2])){
		}
		else{
			d = ss[1]+"."+ss[2];
		}
	}
	else if(l >= 3){
		var ip_pat = "^[0-9]*\.[0-9]*\.[0-9]*\.[0-9]*$";

		if(host.match(ip_pat)){ return d;}

		if(_c_ctry_top_domain(ss[l-2]) && _c_ctry_domain(ss[l-1])){
			d = ss[l-3]+"."+ss[l-2]+"."+ss[l-1];
		}
		else{ d = ss[l-2]+"."+ss[l-1];}
	}
		
	return d;
}


function removeAllChild(obj) {
    var div = $x(obj);
    while (div.hasChildNodes()) //当div下还存在子节点时 循环继续
    {
        div.removeChild(div.firstChild);
    }
}

