/****/
package com.socool.site.condition.baiduapi;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liuwp
 * @date 2016年7月15日
 */
@Data
public class LocationCondition {
	private String ak;
	private String coor;
	private String ip;
	private String sn;

	public LocationCondition() {
		this.coor = "bd09ll";
		this.ak = "b7t556Hc2iDvllZgNAriyVT3PDTwmQTP";
	}

	public LocationCondition(final String ak, final String ip) {
		this.coor = "bd09ll";
		this.ak = ak;
		this.ip = ip;
	}

	@Override
	public String toString() {
		String str = "ak=" + this.ak;
		if (!StringUtils.isBlank(this.ip)) {
			str += "&ip=" + this.ip;
		}
		if (!StringUtils.isBlank(this.coor)) {
			str += "&coor=" + this.coor;
		}
		if (!StringUtils.isBlank(this.sn)) {
			str += "&sn=" + this.sn;
		}
		return str;
	}

}
