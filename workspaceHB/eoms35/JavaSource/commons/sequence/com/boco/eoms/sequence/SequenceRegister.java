package com.boco.eoms.sequence;

import java.util.List;

/**
 * <p>
 * Title:sequence注册列表
 * </p>
 * <p>
 * Description:如：作业计划，值班管理使用各自列表
 * </p>
 * <p>
 * Date:Apr 28, 2008 4:23:30 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceRegister {

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
