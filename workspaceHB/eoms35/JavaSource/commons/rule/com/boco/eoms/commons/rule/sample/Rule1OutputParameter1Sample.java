package com.boco.eoms.commons.rule.sample;


/**
 * <p>
 * Title:rule1的输出参数
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 10:16:03 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class Rule1OutputParameter1Sample {
	/**
	 * 是否通过
	 */
	private boolean isOk;

	/**
	 * 通过人姓名
	 */
	private String name;

	//	@RuleAnnotation(description = "是否通过")
	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	//	@RuleAnnotation(description = "获取名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
