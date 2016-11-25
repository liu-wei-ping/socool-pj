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
	private PageCondition pageCondition;
	private String stock;
}
