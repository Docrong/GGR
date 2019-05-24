package com.boco.eoms.commons.rule.config.model;

/**
 * <p>
 * Title:castor的group的ref mapping对象
 * </p>
 * <p>
 * Description: <groups> <group id="RuleSample1"> <ref ruleId="Rule1Sample"
 * pri="1" /> </group> </groups>
 * </p>
 * <p>
 * Apr 19, 2007 3:44:43 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class GroupRef {
	/**
	 * 规则ID
	 */
	private String ruleId;

	/**
	 * 优先级
	 */
	private Integer pri;

	public Integer getPri() {
		return pri;
	}

	public void setPri(Integer pri) {
		this.pri = pri;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

}
