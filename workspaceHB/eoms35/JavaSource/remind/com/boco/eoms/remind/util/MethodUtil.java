package com.boco.eoms.remind.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-10-6 上午11:09:37
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class MethodUtil {

	/**
	 * 调用方法
	 * 
	 * @param className
	 *            类名
	 * @param methodName
	 *            方法名
	 * @param paramTypes
	 *            参数类型
	 * @param params
	 *            参数
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static Object invoke(String className, String methodName,
			Class[] paramTypes, Object[] params) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException, ClassNotFoundException {
		Class implClass = Class.forName(className);
		Object implInstance = implClass.newInstance();
		Method method = implInstance.getClass().getMethod(methodName,
				paramTypes);
		return method.invoke(implInstance, params);
	}
}
