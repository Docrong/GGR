package com.boco.eoms.commons.rule.sample;


/**
 * <p>
 * Title:rule2的输出参数
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 10:16:14 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Rule1OutputParameter2Sample {
	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 通过否
	 */
	private boolean isOk;

	//	@RuleAnnotation(description = "是否ok")
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	//	@RuleAnnotation(description = "获取性别")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
