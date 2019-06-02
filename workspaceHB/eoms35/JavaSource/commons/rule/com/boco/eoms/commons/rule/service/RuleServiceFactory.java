package com.boco.eoms.commons.rule.service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.rule.config.model.Group;
import com.boco.eoms.commons.rule.config.model.GroupRef;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleException;

/**
 * <p>
 * Title:规则service 工厂
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 12, 2007 7:52:31 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleServiceFactory {
	private static final Logger logger = Logger
			.getLogger(RuleServiceFactory.class);

	/**
	 * 规则service缓存
	 */
	private static Map ruleServiceMap = new HashMap();

	/**
	 * 规则组缓存
	 */
	private static Map ruleGroupMap = new HashMap();

	/**
	 * 将ruleService排序,优先级按3,2,1
	 * 
	 * @param list
	 *            要排序的ruleService
	 */
	private static void sortRuleServiceList(List list) {
		// 排序顺序3,2,1,0,-1
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				RuleService r1 = (RuleService) o1;
				RuleService r2 = (RuleService) o2;
				if (r1.getPri().compareTo(r2.getPri()) < 0) {
					return 1;
				}
				return -1;
			}
		});
	}

	/**
	 * 
	 * 为相同输入参数创建规则组
	 * 
	 * @param xmlPath
	 *            rule配置文件
	 * @param groupId
	 *            rule分组
	 * @param inputMap
	 *            输入参数
	 * @return
	 */
	public static List createGroupForSameInputMap(String xmlPath,
			String groupId, Map inputMap) throws RuleException {
		// 判断ruleId及对应的inputMap是否有值
		if (inputMap == null || inputMap.isEmpty()) {
			throw new RuleException("map参数(ruleId及inputMap)为空");
		}
		// 判断缓存中是否存在
		if (ruleGroupMap.containsKey(RuleServiceFactory.getRuleGroupKey(
				xmlPath, groupId))) {
			// 从缓存取
			return (List) ruleGroupMap.get(RuleServiceFactory.getRuleGroupKey(
					xmlPath, groupId));
		}
		// 通过groupId取规则组
		Group group = RuleConfigWrapper.findGroupByGroupId(xmlPath, groupId);
		// 存规则service组
		List list = new ArrayList();
		for (Iterator it = group.getGroupRef().iterator(); it.hasNext();) {
			GroupRef ref = (GroupRef) it.next();
			RuleService ruleService = RuleServiceFactory.create(xmlPath, ref
					.getRuleId(), inputMap);
			// 将优先级赋予
			ruleService.setPri(ref.getPri());
			// 将ruleService扔入运行队列
			list.add(ruleService);
		}
		// 按优先级排序
		RuleServiceFactory.sortRuleServiceList(list);
		// 将规则组缓存
		ruleGroupMap.put(RuleServiceFactory.getRuleGroupKey(xmlPath, groupId),
				list);
		return list;
	}

	/**
	 * 取规则组缓存
	 * 
	 * @param xmlPath
	 *            xml路径
	 * @param groupId
	 *            规则组id
	 */
	private static String getRuleGroupKey(String xmlPath, String groupId) {
		return xmlPath + "-" + groupId;
	}

	/**
	 * 
	 * 为不同输入参数创建规则组
	 * 
	 * @param xmlPath
	 *            rule配置文件
	 * @param groupId
	 *            rule分组
	 * @param map
	 *            new Map(ruleId,inputMap);
	 * @return
	 */
	public static List createGroupForDifferentInputMap(String xmlPath,
			String groupId, Map map) throws RuleException {
		// 判断ruleId及对应的inputMap是否有值
		if (map == null || map.isEmpty()) {
			throw new RuleException("map参数(ruleId及inputMap)为空");
		}
		// 判断缓存中是否存在
		if (ruleGroupMap.containsKey(RuleServiceFactory.getRuleGroupKey(
				xmlPath, groupId))) {
			return (List) ruleGroupMap.get(RuleServiceFactory.getRuleGroupKey(
					xmlPath, groupId));
		}
		List list = new ArrayList();
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			// 取ruleId
			String ruleId = (String) it.next();
			// 取对应ruleId的输入参灵敏
			Map inputMap = (Map) map.get(ruleId);
			// 创建service
			RuleService ruleService = RuleServiceFactory.create(xmlPath,
					ruleId, inputMap);

			// 取分组mapping 对象
			GroupRef groupRef = RuleConfigWrapper.findGroupRefRule(xmlPath,
					groupId, ruleId);
			// 设置优先级
			ruleService.setPri(groupRef.getPri());
			// 按优先级排序
			list.add(ruleService);
		}
		RuleServiceFactory.sortRuleServiceList(list);
		// 将规则组缓存
		ruleGroupMap.put(RuleServiceFactory.getRuleGroupKey(xmlPath, groupId),
				list);
		return list;
	}

	/**
	 * 创建ruleService
	 * 
	 * @param xmlPath
	 *            rule配置文件
	 * @param ruleId
	 *            rule分组
	 * @param inputMap
	 *            输入参数
	 * @return
	 * @throws RuleException
	 */
	public static RuleService create(String xmlPath, String ruleId, Map inputMap)
			throws RuleException {
		synchronized (RuleServiceFactory.class) {
			// 以xmlPath+ruleId做为instanceKey
			String instanceKey = getInstanceKey(xmlPath, ruleId);
			RuleService ruleService = null;
			// 利用map保存多个实例
			if (!ruleServiceMap.containsKey(instanceKey)) {
				ruleService = initRuleService(xmlPath, ruleId);
				// 将输入参数map放入缓存
				ruleServiceMap.put(instanceKey, ruleService);
			}
			ruleService = (RuleService) ruleServiceMap.get(instanceKey);
			// 将输入参数赋值,输入参数不被缓存
			ruleService.setInputMap(inputMap);
			return ruleService;
		}
	}

	/**
	 * 通过ruleId取Rule,通过rule.getClassName实例化RuleService
	 * 
	 * @param xmlPath
	 * @param ruleId
	 * @return
	 * @throws RuleException
	 */
	private static RuleService initRuleService(String xmlPath, String ruleId)
			throws RuleException {
		// 通过xmlPaht(xml配置文件)取rules
		RuleEngine rules = initRuleEngine(xmlPath);
		// 初使化rule，目的在内存中保存rule
		Rule rule = RuleConfigWrapper.findRulesByRuleId(rules, ruleId);

		// 获取rule的listener
		List listeners = RuleConfigWrapper.getListener(rule);
		RuleService ruleService = null;
		// 实例化RuleService
		try {
			Class cls = Class.forName(rule.getClassName());
			// 实例化RuleSerivce
			Constructor constructor = cls.getConstructor(new Class[] {
					List.class, RuleEngine.class, Rule.class, String.class });
			ruleService = (RuleService) constructor.newInstance(new Object[] {
					listeners, rules, rule, xmlPath });
		} catch (Exception e) {
			logger.error(e);
			throw new RuleException(rule.getClassName() + " 无法实例化");
		}
		return ruleService;
	}

	/**
	 * 初始化Rule配置文件
	 * 
	 * @param xmlPath
	 * @return
	 * @throws RuleException
	 */
	private static RuleEngine initRuleEngine(String xmlPath)
			throws RuleException {

		// 通过xmlPaht(xml配置文件)取rules
		RuleEngine ruleEngine = RuleConfigWrapper.getRuleEngine(xmlPath);
		return ruleEngine;
	}

	/**
	 * 获取instanceKey,xmlPath+id可以唯一确定
	 * 
	 * @param xmlPath
	 *            xml配置文件
	 * @param ruleId
	 *            <rule id/> id值
	 * @return
	 */
	private static String getInstanceKey(String xmlPath, String ruleId) {
		return xmlPath + "-" + ruleId;
	}

}
