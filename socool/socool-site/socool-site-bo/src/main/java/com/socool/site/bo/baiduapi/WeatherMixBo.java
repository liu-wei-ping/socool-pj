/****/
package com.socool.site.bo.baiduapi;

import java.util.List;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Data
public class WeatherMixBo {
	private String city;
	private List<WeatherInfoBo> forecastWeather;
	private List<WeatherInfoBo> historyWeather;
	private WeatherInfoBo todayWeather;
}
