package com.boco.eoms.base.util;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:eoms上下文
 * </p>
 * <p>
 * Description:保存eoms 上下文信息
 * </p>
 * <p>
 * Date:May 8, 2008 6:47:13 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class EOMSContext {
	/**
	 * 构造方法
	 */
	private EOMSContext() {
		// 初使化user对象
		this.user = new TawSystemSessionForm();
	}

	/**
	 * 实例一个threadLocal，保存用户信息
	 */
	private static ThreadLocal context = null;

	/**
	 * 获取context
	 * 
	 * @return eoms的context
	 */
	public static EOMSContext get() {
		if (null == context) {
			context = new ThreadLocal() {
				protected synchronized Object initialValue() {
					return new EOMSContext();
				}
			};
		}
		return (EOMSContext) context.get();
	}

	/**
	 * 清除contex中的对象
	 * 
	 */
	public void clear() {
		this.user = null;
	}

	/**
	 * 用户信息
	 */
	private TawSystemSessionForm user;

	/**
	 * @return the user
	 */
	public TawSystemSessionForm getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(TawSystemSessionForm user) {
		this.user = user;
	}

}
