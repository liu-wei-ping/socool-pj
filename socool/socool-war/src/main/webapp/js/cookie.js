
var CookieUtil = {
    get: function (name){
    	name=encodeURIComponent(name);
        var cookieArray = document.cookie.split(";");
		for (var i = 0; i < cookieArray.length; i++) {
			var arr = cookieArray[i].split("=");
			if (arr[0].replace(" ", "") == name) return unescape(decodeURI(arr[1]));
		}
		return "";
    },
    set: function (name, value, expires, path, domain, secure) {
        var cookieText = encodeURIComponent(name) + "=" + 
                         encodeURIComponent(value);
     
        if (expires instanceof Date) {
            cookieText += "; expires=" + expires.toGMTString();
        }else if(!isNaN(expires)){
        	var  date = new Date((new Date()).getTime() + expires * 3600000);
        	 cookieText += "; expires=" + date.toGMTString();
        }
     
        if (path) {
            cookieText += "; path=" + path;
        }else{
            cookieText += "; path=/";
        }
     
        if (domain) { //如果设置.sina.com 形式可以共享cookie
            cookieText += "; domain=" + domain;
        }else{
            var d=httpDomain(document.domain);
            if(d != ""){
            	 cookieText +="; domain="+d;
            }
        }
     
        if (secure) {
            cookieText += "; secure";
        }
     
        document.cookie = cookieText;
    },
    unset: function (name, path, domain, secure){
        this.set(name, "", new Date(0), path, domain, secure);
    }
};

httpDomain=function(host)
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