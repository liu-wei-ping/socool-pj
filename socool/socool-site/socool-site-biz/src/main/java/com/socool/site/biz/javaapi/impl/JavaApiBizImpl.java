/****/
package com.socool.site.biz.javaapi.impl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.socool.site.biz.javaapi.IJavaApiBiz;
import com.socool.site.biz.utils.BaiduApi;
import com.socool.site.biz.utils.JsonConvertHelper;
import com.socool.site.bo.baiduapi.WeatherIndexBo;
import com.socool.site.bo.baiduapi.WeatherInfoBo;
import com.socool.site.bo.baiduapi.WeatherMixBo;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Service
public class JavaApiBizImpl implements IJavaApiBiz {
	String httpUrl = "http://apis.baidu.com/apistore/weatherservice/recentweathers";

	// String httpUrl =
	// "http://apis.baidu.com/apistore/weatherservice/citylist";

	@Override
	public WeatherMixBo queryWeather(final String cityId) {
		final String result = BaiduApi.request(httpUrl, "cityid=" + cityId);
		final JSONObject re = new JSONObject(result);
		final JSONObject retData = re.getJSONObject("retData");
		final String city = retData.getString("city");
		final JSONObject todayObj = retData.getJSONObject("today");
		final JSONArray todayIndexArr = todayObj.getJSONArray("index");
		final JSONArray historyArr = retData.getJSONArray("history");
		final JSONArray forecastArr = retData.getJSONArray("forecast");
		final List<WeatherInfoBo> forecastWeather = JsonConvertHelper.getObjectsFromJson(forecastArr.toString(),
				List.class, WeatherInfoBo.class);
		final List<WeatherInfoBo> historyWeather = JsonConvertHelper.getObjectsFromJson(historyArr.toString(),
				List.class, WeatherInfoBo.class);
		final List<WeatherIndexBo> indexList = JsonConvertHelper.getObjectsFromJson(todayIndexArr.toString(),
				List.class, WeatherIndexBo.class);
		final WeatherInfoBo todayWeather = JsonConvertHelper.jsonToObject(todayObj.toString(), WeatherInfoBo.class);
		todayWeather.setIndex(indexList);
		final WeatherMixBo weatherMixBo = new WeatherMixBo();
		weatherMixBo.setForecastWeather(forecastWeather);
		weatherMixBo.setHistoryWeather(historyWeather);
		weatherMixBo.setTodayWeather(todayWeather);
		weatherMixBo.setCity(city);
		return weatherMixBo;
	}
}
