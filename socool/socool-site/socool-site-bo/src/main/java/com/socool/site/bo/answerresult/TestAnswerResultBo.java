/*** */
package com.socool.site.bo.answerresult;

import com.socool.site.bo.BaseBo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestAnswerResultBo extends BaseBo {
	private Long id;
	private int status;
	private Long testId;
	private String userAnswer;
	private Long userId;
}
