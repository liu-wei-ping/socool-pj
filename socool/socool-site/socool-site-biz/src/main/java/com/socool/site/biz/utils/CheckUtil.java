/****/
package com.socool.site.biz.utils;

/**
 * @author liuwp
 * @date 2016年8月10日
 */
public class CheckUtil {

	// GENERAL_PUNCTUATION 判断中文的“号
	// CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
	// HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
	public static final boolean isChinese(final String strName) {
		final char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			final char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}

	private static final boolean isChinese(final char c) {
		final Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}
}
