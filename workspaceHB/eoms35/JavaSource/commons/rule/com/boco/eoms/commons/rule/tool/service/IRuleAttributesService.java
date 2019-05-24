package com.boco.eoms.commons.rule.tool.service;

import java.util.List;

import com.boco.eoms.commons.rule.tool.exception.RuleToolException;
import com.boco.eoms.commons.rule.tool.model.Rule;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 10:41:27 AM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public interface IRuleAttributesService {

	/**
	 * 获取所有配置规则列表
	 * 
	 * @return rule中包含id,xmlPath,name
	 * @throws RuleToolException
	 *             从DB中取或xml中取attribute时抛出异常
	 */
	public List listRules() throws RuleToolException;

	/**
	 * 通过rule的ID取规则
	 * 
	 * @param id
	 *            规则主键，唯一标识
	 * @throws RuleToolException
	 *             从DB中取或xml中的规则列表中查询规则
	 * @return 相应的规则对象
	 */
	public Rule findRuleById(String id) throws RuleToolException;

}
