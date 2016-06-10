/*** */
package com.socool.site.biz.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Map;

/**
 * @author mr.lwp
 * @see 2016年6月10日
 */
public class TransformDB {
	/**
	 * @param map
	 * @param destinationClass
	 * @return
	 */
	public static <C> C convertMapToBo(final Map map, final Class<C> destinationClass) {
		C obj = null;
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(destinationClass); // 获取类属性
			obj = destinationClass.newInstance(); // 创建 JavaBean 对象

			// 给 JavaBean 对象的属性赋值
			final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				final PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				for (int j = 0; j < propertyName.length(); j++) {
					final char pro = propertyName.charAt(j);
					if (Character.isUpperCase(pro)) {
						propertyName = propertyName.replace(String.valueOf(pro),
								"_" + String.valueOf(pro).toLowerCase());
						break;
					}
				}
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					final Object value = map.get(propertyName);

					final Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (final Exception e) {
			// TODO: handle exception
		}
		return obj;
	}
}
