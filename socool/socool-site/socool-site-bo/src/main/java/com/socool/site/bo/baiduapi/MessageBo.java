/****/
package com.socool.site.bo.baiduapi;

import java.io.Serializable;

import lombok.Data;

/**
 * @author liuwp
 * @date 2016年6月13日
 */
@Data
public class MessageBo implements Serializable {
	/*** */
	private static final long serialVersionUID = 1010745400956887231L;
	/*** 返回信息 */
	private String message;
	/*** 运营商结算无意义，可不用解析 */
	private String remainpoint;
	/*** 返回状态值：成功返回Success 失败返回：Faild */
	private String returnstatus;
	/*** 返回成功短信数 */
	private String successCounts;
	/*** 返回本次任务的序列ID */
	private String taskID;
}
