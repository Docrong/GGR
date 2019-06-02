package com.boco.eoms.remind;

import java.util.List;

/**
 * <p>
 * Title:提醒服务列表
 * </p>
 * <p>
 * Description:使用提醒服务的模块注册的列表
 * </p>
 * <p>
 * Date:2008-10-6 上午10:01:56
 * </p>
 * 
 * @author 李秋野
 * @version 3.5.1
 * 
 */
public class RemindRegister {

	/**
	 * sequence 注册列表
	 */
	private List register;

	/**
	 * @return the register
	 */
	public List getRegister() {
		return register;
	}

	/**
	 * @param register
	 *            the register to set
	 */
	public void setRegister(List register) {
		this.register = register;
	}

	/**
	 * 注册总数
	 * 
	 * @return 注册总数
	 */
	public int total() {
		return this.register.size();
	}
}
