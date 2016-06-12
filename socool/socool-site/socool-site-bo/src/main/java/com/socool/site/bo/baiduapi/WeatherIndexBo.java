/****/
package com.socool.site.bo.baiduapi;

import java.io.Serializable;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Data
public class WeatherIndexBo implements Serializable {
	/*** */
	private static final long serialVersionUID = 2830521968099697886L;
	/*** 指标编码 */
	private String code;
	/*** 描述 */
	private String details;
	/*** 等级 */
	private String index;
	/*** 指数指标 */
	private String name;
	/*** 其它信息 */
	private String otherName;
}
