/****/
package com.socool.site.action.java;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.socool.site.action.BaseAction;
import com.socool.site.biz.javaapi.IJavaApiBiz;
import com.socool.site.biz.utils.Constants;
import com.socool.site.bo.baiduapi.WeatherMixBo;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Controller
@RequestMapping("java-api")
public class JavaApiAction extends BaseAction {
	@Autowired
	private IJavaApiBiz iJavaApiBiz;

	/**
	 * @return
	 */
	@RequestMapping(value = "/bdApi.html")
	public ModelAndView baiduAip() {
		final ModelAndView model = new ModelAndView();
		model.setViewName(getViewUrl("baiduApi"));
		return model;
	}

	@RequestMapping(value = "/weather.shtml")
	public String stockApi(final Map<String, Object> map) {
		final WeatherMixBo we = iJavaApiBiz.queryWeather("101020100");
		map.put("we", we);
		return getViewUrl("api_weather");
	}

	@RequestMapping(value = "/stock.shtml")
	public String weatherApi() {
		return getViewUrl("api_stock");
	}

	@Override
	protected String getType() {
		return Constants.TYPE_JAVA;
	}
}
