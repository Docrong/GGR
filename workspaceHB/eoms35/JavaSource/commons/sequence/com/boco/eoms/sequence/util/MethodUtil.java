package com.boco.eoms.sequence.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.boco.eoms.sequence.Job;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 24, 2008 3:47:52 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class MethodUtil {

	/**
	 * 调用方法
	 * 
	 * @param job
	 *            方法相关参数
	 * @return 返回结果
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	public static Object invoke(Job job) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		Method method = job.getClz().getClass().getMethod(job.getMethodName(),
				job.getParamTypes());
		return method.invoke(job.getClz(), job.getParams());
	}
}
