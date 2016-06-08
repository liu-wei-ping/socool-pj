/*** */
package com.socool.site.bo.answerresult;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.socool.site.bo.BaseBo;

/**
 * @author mr.lwp
 * @see 2016年6月5日
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TestAnswerResultBo extends BaseBo {
	private String categoryType;
	private Long id;
	private int status;
	private Long testId;
	private String userAnswer;
	private Long userId;

}
