function menuTree(leftmenu,baseUrl){
		var m = new AccordionMenu({
			containerCls : '.wrap-menu',
			menuArrs : leftmenu
		});
		//a[url!='javascript:void(0)']
		$("#tree-area").find("a[url!='javascript:void(0)']").click(function() {
			var url = $(this).attr('url');
			$("a[url!='javascript:void(0)']").each(function(i) {
				$(this).removeClass('menu-clicked');
				$(this).addClass('menu-unClicked');
			});
			$(this).removeClass('menu-unClicked');
			$(this).addClass('menu-clicked');
			showMainContent(url,baseUrl);
		});
		//默认打开第一个有url的菜单
		$('#tree-area').find("a[url!='javascript:void(0)']").first().on('firstClick',function(){
			$(".lev-1").find('ul').each(function(){
					$(this).attr('style','disyplay:block;')
				})
	
				$(this).click();
			});
		 $('#tree-area').find("a[url!='javascript:void(0)']").first().trigger('firstClick');
	}
	function showMainContent(u,baseUrl) {
		// var base='<%="http://" + request.getServerName() + ":" +
		// request.getServerPort()+request.getContextPath()+"/"%>';
		var content = $('#main-content');
		var content_frame = $('#main-content-frame');
		if (u.match(/^http|https:\/\/.*$/)) {
			content.css('display', 'none');
			content_frame.attr('src', u);
			content_frame.css('display', 'block');
		} else {
			content.css('display', 'block');
			u = baseUrl+"/" + u;
			// $('#main-content').load(u,function(){});
			$.ajax({
				url : u,
				async:false,
				cache : false,
				type : 'GET',
				dataType : 'html',
				success : function(res) {
					content_frame.css('display', 'none');
					try {
						var jsonRes = eval("(" + res + ")");
						if (jsonRes.error && jsonRes.error != '') {
							content.html(jsonRes.error);
							return;
						}
					} catch (err) {
					}
					content.html(res);
				}
			});
		}
}
