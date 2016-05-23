/****/
package com.socool.site.action;

/**
 * @author liuwp
 * @date 2016年5月20日
 */
public abstract class BaseAction {

	/**
	 * 返回页面 <br>
	 * 规则 java_a,php_a
	 *
	 * @param view
	 * @return
	 */
	protected String getViewUrl(final String view) {
		return getType().concat("/").concat(getType()).concat("_").concat(view);
	}

	protected abstract String getType();
}
