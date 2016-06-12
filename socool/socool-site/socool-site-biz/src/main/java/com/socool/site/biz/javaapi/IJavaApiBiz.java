/****/
package com.socool.site.biz.javaapi;

import com.socool.site.bo.baiduapi.WeatherMixBo;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
public interface IJavaApiBiz {
	/**
	 * @param cityId
	 * @return
	 */
	WeatherMixBo queryWeather(final String cityId);
}
