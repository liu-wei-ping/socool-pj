/**
 *
 */
package com.socool.site.entry.testinfo;

import com.socool.site.entry.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestInfoEntry extends BaseEntry {
	/*** */
	private Long categoryId;
	/*** */
	private Long id;
	/*** */
	private String remark;
	/*** */
	private String testAnswer;
	/*** */
	private String testContent;
	/*** */
	private String testOptions;

	private int type;
}
