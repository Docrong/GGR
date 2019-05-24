package com.boco.eoms.commons.rule.config.model;

/**
 * <p>
 * Title:规则,RuleMapping.xml根<rules/>
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 11, 2007 2:55:06 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class RuleEngine {

	/**
	 * 此RuleEngine所在xml路径
	 */
	private String xmlPath;

	/**
	 * 规则组
	 */
	private Groups groups;

	/**
	 * 规则
	 */
	private Rules rules;

	/**
	 * 字典配置
	 */
	private Dict dict;

	/**
	 * 表达式样式
	 */
	private ExpStyles expStyles;

	public ExpStyles getExpStyles() {
		return expStyles;
	}

	public void setExpStyles(ExpStyles expStyles) {
		this.expStyles = expStyles;
	}

	public Rules getRules() {
		return rules;
	}

	public void setRules(Rules rules) {
		this.rules = rules;
	}

	public Groups getGroups() {
		return groups;
	}

	public void setGroups(Groups groups) {
		this.groups = groups;
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

}
