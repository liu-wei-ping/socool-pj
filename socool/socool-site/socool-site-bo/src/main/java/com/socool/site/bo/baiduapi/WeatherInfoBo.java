/****/
package com.socool.site.bo.baiduapi;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Data
public class WeatherInfoBo implements Serializable {
	/*** */
	private static final long serialVersionUID = 1L;
	/*** PM值 */
	private String aqi;
	/*** 当前温度 */
	private String curTemp;
	/*** 日期 */
	private String date;
	/*** 风力 */
	private String fengli;
	/*** 风向 */
	private String fengxiang;
	/** 最高温度* */
	private String hightemp;
	/*** */
	private List<WeatherIndexBo> index;
	/*** 最低温度 */
	private String lowtemp;
	/*** 天气状态 */
	private String type;
	/*** 今日星期 */
	private String week;
}
