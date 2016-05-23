/*** */
package com.socool.site.condition;

import lombok.Data;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Data
public class TestInfoCondition {
	private PageCondition rePage;
	private String testAnswer;

	public TestInfoCondition() {
		this.rePage = new PageCondition();
	}
}
