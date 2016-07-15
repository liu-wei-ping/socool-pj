/****/
package com.socool.site.action.baidu;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.socool.site.biz.javaapi.IJavaApiBiz;
import com.socool.site.condition.baiduapi.LocationCondition;

/**
 * @author liuwp
 * @date 2016年7月5日
 */
@Controller
@RequestMapping("baiduApi")
public class BdApiAction {

	@Autowired
	private IJavaApiBiz iJavaApiBiz;

	@RequestMapping("/index.html")
	public String bdIndex() {
		return "baidu/index";
	}

	@RequestMapping("/bdMap.html")
	public String bdMap(final Map<String, Object> map) {
		final String ak = "VXGVbHDvutI4WQFjzH509VD1HXbdhwFE";
		map.put("ak", ak);
		final Map<String, Object> mapl = iJavaApiBiz
				.getAddressLocation(new LocationCondition());
		map.put("result", mapl);
		return "baidu/map";
	}

}
