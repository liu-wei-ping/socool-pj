/****/
package com.socool.site.bo.baiduapi;

import java.io.Serializable;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年6月12日
 */
@Data
public class CityBo implements Serializable {

	private static final long serialVersionUID = 8482551624376087979L;
	/** 城市代码 */
	private String areaId;
	/** 市 */
	private String districtCn;
	/** 区、县 */
	private String nameCn;
	/** 城市拼音 */
	private String nameEn;
	/*** 省 */
	private String provinceCn;
}
