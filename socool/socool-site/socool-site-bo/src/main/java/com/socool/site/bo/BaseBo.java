/*** */
package com.socool.site.bo;

import java.sql.Timestamp;

import com.socool.site.condition.PageCondition;

import lombok.Data;

/**
 * @author mr.lwp
 * @see 2016年5月22日
 */
@Data
public class BaseBo {
	private int createTime;
	private PageCondition rePage;
	private Timestamp updateTime;
}
