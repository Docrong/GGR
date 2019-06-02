package com.boco.eoms.commons.rule.tool.service.impl;

import java.util.List;

import com.boco.eoms.commons.rule.tool.exception.RuleToolException;
import com.boco.eoms.commons.rule.tool.model.Rule;
import com.boco.eoms.commons.rule.tool.service.IRuleAttributesService;
import com.boco.eoms.commons.rule.util.RuleAttributes;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 10:43:50 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleAttributesServiceImpl implements IRuleAttributesService {

	/**
	 * 规则属性配置
	 */
	private RuleAttributes ruleAttributes;

	public Rule findRuleById(String id) throws RuleToolException {
		return ruleAttributes.findRuleById(id);
	}

	public List listRules() throws RuleToolException {
		return ruleAttributes.getRules();
	}

	public void setRuleAttributes(RuleAttributes ruleAttributes) {
		this.ruleAttributes = ruleAttributes;
	}

}
