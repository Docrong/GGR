package com.boco.eoms.km.core.hibernate.util;

/**
 * 
 * @author zhangxb
 *
 */
public class ReflectHelper {

	public static Class classForName(String name) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if (contextClassLoader!=null) {
				return contextClassLoader.loadClass(name);
			} 
			else {
				return Class.forName(name);
			}
		}
		catch (Exception e) {
			return Class.forName(name);
		}
	}
}