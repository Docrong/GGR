package com.boco.eoms.commons.rule.sample;


/**
 * <p>
 * Title:模拟Rule1Parameter2对应Rule1的参数parameter2的sample
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 9:26:39 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Rule1InputParameter2Sample {
	/**
	 * 性别
	 */
	private String sex;

	//	@RuleAnnotation(description = "获取性别")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
