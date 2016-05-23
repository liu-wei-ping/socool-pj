/**
 *
 */
package com.socool.site.entry;

import java.sql.Timestamp;

import lombok.Data;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
@Data
public class BaseEntry {
	private int createTime;
	private Timestamp updateTime;
}
