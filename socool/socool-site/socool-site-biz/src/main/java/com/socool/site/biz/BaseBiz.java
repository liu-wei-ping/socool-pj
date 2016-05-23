/*** */
package com.socool.site.biz;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

/**
 * @author mr.lwp
 * @see 2016年5月21日
 */
public class BaseBiz extends DozerBeanMapper {
	/**
	 * @param sourceList
	 * @param destinationClass
	 * @return
	 */
	public <B, E> List<B> mapList(final List<E> sourceList, final Class<B> destinationClass) {
		final List<B> tagaList = new ArrayList<B>();
		for (final E e : sourceList) {
			tagaList.add(super.map(e, destinationClass));
		}
		return tagaList;
	}
}
