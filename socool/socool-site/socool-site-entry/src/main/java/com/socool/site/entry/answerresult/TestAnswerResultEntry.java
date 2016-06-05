/*** */
package com.socool.site.entry.answerresult;

import com.socool.site.entry.BaseEntry;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestAnswerResultEntry extends BaseEntry {
	private Long id;
	private int status;
	private Long testId;
	private String userAnswer;
	private Long userId;
}
