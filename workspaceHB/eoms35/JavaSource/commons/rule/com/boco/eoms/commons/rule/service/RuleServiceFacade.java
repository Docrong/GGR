package com.boco.eoms.commons.rule.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.rule.exception.RuleException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 19, 2007 8:05:33 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleServiceFacade {

	private static RuleServiceFacade instance;

	public static RuleServiceFacade create() {
		if (instance == null) {
			instance = new RuleServiceFacade();
		}
		return instance;
	}

	private RuleServiceFacade() {

	}

	/**
	 * 调用RuleService
	 * 
	 * @param xmlPath
	 *            xml配置文件
	 * @param ruleId
	 *            规则ID
	 * @param inputMap
	 *            输入参数
	 * @return
	 * @throws RuleException
	 */
	public Map invokeRuleService(String xmlPath, String ruleId, Map inputMap)
			throws RuleException {
		return RuleServiceFactory.create(xmlPath, ruleId, inputMap).invoke();
	}

	/**
	 * 调用不同参数的规则组内的所有规则，通过优先级，先后判断估先级为9,8,7,6的ruleService，当遇到正确反回参数时，返回，没有正确抛出异常
	 * 
	 * @param xmlPath
	 *            配置文件路径
	 * @param groupId
	 *            规则组id
	 * @param map
	 *            new Map(ruleId,inputMap);
	 * @return
	 * @throws RuleException
	 */
	public Map invokeRuleGroupForDiffInputMap(String xmlPath, String groupId,
			Map map) throws RuleException {
		List list = RuleServiceFactory
				.createGroupForDifferentInputMap(xmlPath, groupId, map);
		return gotoRightRuleAndReturnOutMap(list, groupId);
	}

	/**
	 * 调用不同参数的规则组内的所有规则，通过优先级，先后判断估先级为9,8,7,6的ruleService，当遇到正确反回参数时，返回，没有正确抛出异常
	 * 
	 * @param xmlPath
	 *            配置文件路径
	 * @param groupId
	 *            规则组id
	 * @param inputMap
	 *            输入参数
	 * @return
	 * @throws RuleException
	 */
	public Map invokeRuleGroupForSampeInputMap(String xmlPath, String groupId,
			Map inputMap) throws RuleException {
		List list = RuleServiceFactory.createGroupForSameInputMap(
				xmlPath, groupId, inputMap);
		return gotoRightRuleAndReturnOutMap(list, groupId);
	}

	/**
	 * 定位到规则按照配置outputParameter正确输出，则返回，没有正确抛出异常
	 * 
	 * @param list
	 * @param groupId
	 * @return
	 * @throws RuleException
	 */
	private Map gotoRightRuleAndReturnOutMap(List list,
			String groupId) throws RuleException {
		for (Iterator it = list.iterator(); it.hasNext();) {
			RuleService ruleService = (RuleService) it.next();
			Map outMap = null;
			try {
				outMap = ruleService.invoke();
			} catch (RuleException e) {
				// checkListener验证输出参数，则捕获异常，继续直行
				continue;
			}
			// 通过chekcListener的输出参数验证，证明符合规则业务，返回输出map
			return outMap;
		}
		throw new RuleException(groupId + "中没有符合条件的规则");
	}
}
