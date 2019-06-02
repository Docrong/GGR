package com.boco.eoms.commons.rule.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.boco.eoms.commons.fileconfig.exception.ParseXMLException;
import com.boco.eoms.commons.fileconfig.service.impl.ParseXmlService;
import com.boco.eoms.commons.rule.config.model.Group;
import com.boco.eoms.commons.rule.config.model.GroupRef;
import com.boco.eoms.commons.rule.config.model.Listener;
import com.boco.eoms.commons.rule.config.model.Parameter;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.exception.RuleCheckListenerException;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.exception.RuleListenerException;
import com.boco.eoms.commons.rule.listener.IRuleListener;
import com.boco.eoms.commons.rule.listener.RuleListenerFactory;

/**
 * <p>
 * Title:规则封装包
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Apr 12, 2007 8:40:22 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleConfigWrapper {

	private final static Logger logger = Logger
			.getLogger(RuleConfigWrapper.class);

	/**
	 * 缓存规则map
	 */
	private static Map ruleMap = new HashMap();

	/**
	 * 缓存groupMap
	 */
	private static Map groupMap = new HashMap();

	/**
	 * 缓存ruleEngine
	 */
	private static Map ruleEngineMap = new HashMap();

	/**
	 * @param xmlPath
	 *            xml路径
	 * @param groupId
	 *            路由组ID 清除路由分组缓存
	 */
	public static void flushGroup(String xmlPath, String groupId) {
		ruleEngineMap.remove(xmlPath);
		groupMap.remove(getGroupKey(xmlPath, groupId));
	}

	/**
	 * 删除所有rule
	 *  
	 */
	public static void flushRule() {
		ruleMap.clear();
		groupMap.clear();
		ruleEngineMap.clear();

	}

	/**
	 * 清空规则缓存
	 * 
	 * @param xmlPath
	 *            xml路径
	 * @param ruleId
	 *            规则id
	 */
	public static void flushRule(String xmlPath, String ruleId) {
		ruleEngineMap.remove(xmlPath);
		ruleMap.remove(getRuleKey(xmlPath, ruleId));
	}

	/**
	 * 验证xml配置的ruleId是否为空或有重复
	 * 
	 * @param ruleEngine
	 *            xml mapping对象
	 * @throws RuleCheckListenerException
	 *             ruleId为空，有重复抛出异常
	 */
	public static void checkRulesId(RuleEngine ruleEngine, String xmlPath)
			throws RuleCheckListenerException {
		if (ruleEngine == null || ruleEngine.getRules() == null) {
			throw new RuleCheckListenerException(xmlPath + "配置文件中不含有rules");
		}
		Map map = new HashMap();
		for (Iterator it = ruleEngine.getRules().getRule().iterator(); it
				.hasNext();) {
			Rule rule = (Rule) it.next();
			if (rule.getId() == null || "".equals(rule.getId())) {
				throw new RuleCheckListenerException("rule id 为空");
			}
			if (map.containsKey(rule.getId())) {
				throw new RuleCheckListenerException("rule id 有重复");
			}
			map.put(rule.getId(), "");
		}
	}

	/**
	 * 规则缓存key
	 * 
	 * @param xmlPath
	 *            配置文件路径
	 * @param ruleId
	 *            规则id
	 * @return
	 */
	private static String getRuleKey(String xmlPath, String ruleId) {
		return xmlPath + "-" + ruleId;
	}

	/**
	 * 规则组缓存key
	 * 
	 * @param xmlPath
	 *            配置文件路径
	 * @param groupId
	 *            组id
	 * @return
	 */
	private static String getGroupKey(String xmlPath, String groupId) {
		return xmlPath + "-" + groupId;
	}

	/**
	 * 通过roleId取Rule
	 * 
	 * @param ruleId
	 *            xml <rule id>的id值
	 * @return rule
	 * @throws RuleCheckListenerException
	 */
	public static Rule findRulesByRuleId(RuleEngine ruleEngine, String ruleId)
			throws RuleException {
		// 缓存中取rule
		if (ruleMap.containsKey(getRuleKey(ruleEngine.getXmlPath(), ruleId))) {
			return (Rule) ruleMap.get(getRuleKey(ruleEngine.getXmlPath(),
					ruleId));
		}
		for (Iterator it = ruleEngine.getRules().getRule().iterator(); it
				.hasNext();) {
			Rule rule = (Rule) it.next();
			if (ruleId.equals(rule.getId())) {
				// 扔到缓存
				ruleMap.put(getRuleKey(ruleEngine.getXmlPath(), ruleId), rule);
				return rule;
			}
		}
		throw new RuleCheckListenerException(ruleId + "找不到");
	}

	/**
	 * 通过xmlPath，groupId、ruleId取Rule
	 * 
	 * @param xmlPath
	 *            xml配置文件
	 * @param groupId
	 *            规则组id
	 * @param ruleId
	 *            规则ID
	 * @return
	 * @throws RuleException
	 */
	public static GroupRef findGroupRefRule(String xmlPath, String groupId,
			String ruleId) throws RuleException {
		Group group = RuleConfigWrapper.findGroupByGroupId(xmlPath, groupId);
		for (Iterator it = group.getGroupRef().iterator(); it.hasNext();) {
			GroupRef groupRef = (GroupRef) it.next();
			if (ruleId.equals(groupRef.getRuleId())) {
				return groupRef;
			}
		}
		throw new RuleException("在" + xmlPath + "没有" + groupId + "组的" + ruleId
				+ "规则");
	}

	/**
	 * 通过groupId取规则group
	 * 
	 * @param ruleEngine
	 * @param groupId
	 * @return
	 */
	public static Group findGroupByGroupId(String xmlPath, String groupId)
			throws RuleException {
		// 缓存中取ruleGroup
		if (ruleMap.containsKey(getRuleKey(xmlPath, groupId))) {
			return (Group) groupMap.get(getRuleKey(xmlPath, groupId));
		}
		RuleEngine ruleEngine = RuleConfigWrapper.getRuleEngine(xmlPath);
		for (Iterator it = ruleEngine.getGroups().getGroup().iterator(); it
				.hasNext();) {
			Group group = (Group) it.next();
			if (groupId.equals(group.getId())) {
				// 扔到缓存
				groupMap.put(getGroupKey(ruleEngine.getXmlPath(), groupId),
						group);
				return group;
			}
		}
		throw new RuleCheckListenerException(groupId + "找不到");
	}

	/**
	 * 通过groupId取规则group
	 * 
	 * @param ruleEngine
	 * @param groupId
	 * @return
	 */
	public static Group findGroupByGroupId(RuleEngine ruleEngine, String groupId)
			throws RuleException {
		// 缓存中取ruleGroup
		if (ruleMap.containsKey(getRuleKey(ruleEngine.getXmlPath(), groupId))) {
			return (Group) groupMap.get(getRuleKey(ruleEngine.getXmlPath(),
					groupId));
		}
		for (Iterator it = ruleEngine.getGroups().getGroup().iterator(); it
				.hasNext();) {
			Group group = (Group) it.next();
			if (groupId.equals(group.getId())) {
				// 扔到缓存
				groupMap.put(getGroupKey(ruleEngine.getXmlPath(), groupId),
						group);
				return group;
			}
		}
		throw new RuleCheckListenerException(groupId + "找不到");
	}

	/**
	 * 检查parameter 列表， 验证map中的所存的对象与xml配置是否相符
	 * 
	 * @param map
	 *            输入/输出 map
	 * @param params
	 *            参数列表
	 * @return
	 */
	public static boolean checkMapType(Map map, Collection params) {
		// 若传入参数为空则返回false
		if (params == null) {
			return false;
		}
		try {
			for (Iterator it = params.iterator(); it.hasNext();) {
				Parameter para = (Parameter) it.next();
				// 检验输出参数与配置输出是否匹配
				RuleConfigWrapper.checkMapType(map, para);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 验证map中的所存的对象与xml配置是否相符
	 * 
	 * @param map
	 *            输入/输出map
	 * @param para
	 *            参数
	 * @throws RuleCheckListenerException
	 */
	public static void checkMapType(Map map, Parameter para)
			throws RuleException {
		if (para.getType() == null || para.getName() == null) {
			throw new RuleListenerException("parameter中的type或name不得为空");
		}
		Class cls;
		try {
			cls = Class.forName(para.getType());
		} catch (ClassNotFoundException e) {
			logger.error(e);
			throw new RuleListenerException(e);
		}
		Object param = map.get(para.getName());
		if (param == null) {
			throw new RuleListenerException(para.getName() + "在map参数中并不存在"
					+ "\n" + map2string(map));
		}
		if (!cls.equals(param.getClass())) {
			throw new RuleListenerException(para.getName() + para.getType()
					+ "与传入参数不符" + "\n" + map2string(map));
		}

	}

	/**
	 * 将map转成string ，格式为key="",value=""
	 * 
	 * @param map
	 * @return
	 */
	private static String map2string(Map map) {
		if (map == null) {
			return "map参数null";
		} else if (map.isEmpty()) {
			return "map参数isEmpty";
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator it = map.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			sb.append("key:" + key);
			sb.append(",value:" + map.get(key));
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * 获取RuleEngine的castor mapping对象
	 * 
	 * @param xmlPath
	 *            rule xml的配置文件
	 * @return
	 */
	public static RuleEngine getRuleEngine(String xmlPath) throws RuleException {

		// 从缓存取ruleEngine
		if (ruleEngineMap.containsKey(xmlPath)) {
			return (RuleEngine) ruleEngineMap.get(xmlPath);
		}

		// 文件配置组件返回rules
		RuleEngine ruleEngine = null;
		try {
			// 调用文件配置组件,解析xml
			ruleEngine = (RuleEngine) ParseXmlService.create().xml2object(
					RuleEngine.class, "RuleMapping", xmlPath);
			ruleEngine.setXmlPath(xmlPath);
			ruleEngineMap.put(xmlPath, ruleEngine);
		} catch (ParseXMLException e) {
			logger.error(e);
			throw new RuleException(xmlPath + "规则文件解析错误");
		}
		return ruleEngine;
	}

	/**
	 * 取得rule的listener
	 * 
	 * @param rule
	 * @return
	 * @throws RuleCheckListenerException
	 */
	public static List getListener(Rule rule) throws RuleListenerException {
		List list = new ArrayList();
		// 判断xml配置的listener是否为空
		if (rule.getListeners() != null) {

			// 实例化开发人员配置的listener

			// 遍历所有listener
			for (Iterator it = rule.getListeners().getListeners().iterator(); it
					.hasNext();) {
				// <listener name="com.boco.eoms.Listener"/>
				Listener listener = (Listener) it.next();

				IRuleListener ruleListener;
				try {
					ruleListener = RuleListenerFactory.create(listener
							.getName());
				} catch (RuleListenerException e) {
					logger.error(e);
					throw new RuleListenerException(listener.getName()
							+ "类没有找到");
				}
				list.add(ruleListener);
			}

		}
		return list;
	}
}
