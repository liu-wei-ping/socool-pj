/*** */
package com.socool.site.entry.answerresult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.socool.site.entry.BaseEntry;
import com.socool.site.entry.testinfo.TestInfoEntry;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestAnswerResultEntry extends BaseEntry {
	private String categoryType;
	private Long id;
	private int status;
	private Long testId;
	private TestInfoEntry testInfo;
	private String userAnswer;
	private Long userId;
}
