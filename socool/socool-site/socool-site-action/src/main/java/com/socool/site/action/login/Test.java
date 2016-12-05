package com.socool.site.action.login;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(final String[] args)
			throws UnsupportedEncodingException {
		// 运行结果：2
		System.out.println("测".getBytes("ISO8859-1").length);
		// 运行结果：4
		System.out.println("测".getBytes("GB2312").length);
		// 运行结果：4
		System.out.println("测".getBytes("GBK").length);
		// 运行结果：6
		System.out.println("测".getBytes("UTF-8").length);
	}
}
