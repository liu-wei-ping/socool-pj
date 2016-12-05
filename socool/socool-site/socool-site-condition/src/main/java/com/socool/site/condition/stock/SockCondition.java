/****/
package com.socool.site.condition.stock;

import lombok.Data;

import com.socool.site.condition.PageCondition;

/**
 * @author liuwp
 * @date 2016年11月25日
 */
@Data
public class SockCondition {
	private String endDate;
	private PageCondition pageCondition;
	private String startDate;
	private String stock;
}
