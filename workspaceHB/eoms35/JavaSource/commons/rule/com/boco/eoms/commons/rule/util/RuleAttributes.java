package com.boco.eoms.commons.rule.util;

import java.util.Iterator;
import java.util.List;

import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLAttributesException;
import com.boco.eoms.commons.rule.tool.model.Rule;

/**
 * <p>
 * Title:规则spring配置参数
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2007 3:41:51 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleAttributes {

	/**
	 * 多个rule配置(spring中配置),如：流程平台=processPlatform.xml
	 */
	private List rules;

	public List getRules() {
		return rules;
	}

	public void setRules(List rules) {
		this.rules = rules;
	}

	/**
	 * 通过规则主键取规则
	 * 
	 * @param id
	 *            规则主键
	 */
	public Rule findRuleById(String id) throws RuleToolXMLAttributesException {
		List list = this.getRules();
		if (list == null) {
			throw new RuleToolXMLAttributesException(
					"规则xml配置中未配置规则列表，请检查config/applicationContext-rule.xml");
		}
		if (id == null || "".equals(id)) {
			throw new RuleToolXMLAttributesException("规则id（标识）为null ，或空串");
		}
		for (Iterator it = list.iterator(); it.hasNext();) {
			Rule rule = (Rule) it.next();
			if (id.equals(rule.getId())) {
				return rule;
			}
		}
		throw new RuleToolXMLAttributesException("规则列表中没有id（标识）的规则");
	}

	/**
	 * 每页显示多少条
	 */
	private Integer pageSize;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
