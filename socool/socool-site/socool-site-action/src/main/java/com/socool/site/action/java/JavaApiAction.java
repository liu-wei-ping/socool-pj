/****/
package com.socool.site.action.java;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.socool.site.action.BaseAction;
import com.socool.site.biz.javaapi.IJavaApiBiz;
import com.socool.site.biz.utils.Constants;
import com.socool.site.bo.baiduapi.MessageBo;
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

	@RequestMapping(value = "/bdMap.html")
	public ModelAndView bdMap() {
		final ModelAndView model = new ModelAndView();
		model.setViewName(getViewUrl("api_bdMap"));
		return model;
	}

	@RequestMapping(value = "/message.shtml")
	public String messageApi(
			@RequestParam(value = "phone", required = true) final String phone,
			@RequestParam(value = "content", required = true) final String content,
			final Map<String, Object> map) {
		final MessageBo messageBo = iJavaApiBiz.sendMessageToPhone(phone,
				content);
		map.put("msg", messageBo);
		return getViewUrl("api_message");
	}

	@RequestMapping(value = "/stock.shtml")
	public String stockApi() {
		return getViewUrl("api_stock");
	}

	@RequestMapping(value = "/weather.shtml")
	public String weatherApi(@RequestParam("cityId") final String cityId,
			final Map<String, Object> map) {
		final WeatherMixBo we = iJavaApiBiz.queryWeather(cityId);
		map.put("we", we);
		return getViewUrl("api_weather");
	}

	@Override
	protected String getType() {
		return Constants.TYPE_JAVA;
	}
}
