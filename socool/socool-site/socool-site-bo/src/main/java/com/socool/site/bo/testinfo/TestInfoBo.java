/*** */
package com.socool.site.bo.testinfo;

import lombok.Data;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Data
public class TestInfoBo {
	/*** */
	private Long categoryId;
	/*** */
	private Long id;
	/*** */
	private String[] optionsArr;
	/*** */
	private String remark;
	/*** */
	private String testAnswer;
	/*** */
	private String testContent;
	/*** */
	private String testOptions;
	/*** */
	private int type;
}
