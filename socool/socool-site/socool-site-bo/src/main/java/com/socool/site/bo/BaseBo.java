/*** */
package com.socool.site.bo;

import lombok.Data;

import com.socool.site.condition.PageCondition;

/**
 * @author mr.lwp
 * @see 2016年5月22日
 */
@Data
public class BaseBo {
	private int createTime;
	private PageCondition rePage;
	private String updateTime;
}
