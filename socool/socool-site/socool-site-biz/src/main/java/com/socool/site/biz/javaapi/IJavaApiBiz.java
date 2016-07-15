/****/
package com.socool.site.biz.javaapi;

import java.util.Map;

import com.socool.site.bo.baiduapi.IdentityBo;
import com.socool.site.bo.baiduapi.MessageBo;
import com.socool.site.bo.baiduapi.WeatherMixBo;
import com.socool.site.condition.baiduapi.LocationCondition;

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

	/**
	 * @param phone
	 * @param content
	 * @return
	 */
	MessageBo sendMessageToPhone(final String phone, final String content);

	/**
	 * @param IdentityNo
	 * @return
	 */
	IdentityBo queryIdentityInfo(final String identityNo);

	/**
	 * @param condition
	 * @return
	 */
	Map<String, Object> getAddressLocation(final LocationCondition condition);
}
