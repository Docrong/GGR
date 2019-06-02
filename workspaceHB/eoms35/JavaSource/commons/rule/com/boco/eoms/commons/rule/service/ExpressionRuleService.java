package com.boco.eoms.commons.rule.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.rule.config.model.Parameter;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 13, 2007 5:09:52 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class ExpressionRuleService extends RuleService {

	protected Map execute(Map inputMap,
			Rule rule) throws RuleException {
		// 创建表达式解析service
		OgnlExpressionService oes = OgnlExpressionService.create(null);
		Map outMap = new HashMap();
		// 取输出参数
		for (Iterator it = rule.getOutput().getParameters().iterator(); it
				.hasNext();) {

			Parameter para = (Parameter) it.next();
			// 将输出参数配置的表达式结果按照配置名称写入outMap
			outMap.put(para.getName(), oes.getValue(para.getExpression(),
					inputMap));

		}

		return outMap;
	}

	/**
	 * @param ruleListeners
	 * @param rules
	 * @param rule
	 * @param xmlPath
	 */
	public ExpressionRuleService(List ruleListeners, RuleEngine rules,
			Rule rule, String xmlPath) {
		super(ruleListeners, rules, rule, xmlPath);
	}

}
