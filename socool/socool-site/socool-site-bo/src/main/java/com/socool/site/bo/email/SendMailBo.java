/****/
package com.socool.site.bo.email;

import java.util.List;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年8月12日
 */
@Data
public class SendMailBo {
	private String content;
	private String fromMail;
	private String subject;
	private List<String> toMail;
}
