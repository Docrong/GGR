package com.boco.eoms.commons.rule.tool.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.boco.eoms.commons.rule.config.model.Group;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLException;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 3:33:05 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public interface IRuleXMLService {

	/**
	 * 通过组ID取规则分组
	 * 
	 * @param groupId
	 * @param document
	 *            xmlPath的document(dom4j)
	 * @return dom4j元素
	 * @throws RuleToolXMLException
	 */
	public Element findRuleGroupByGroupId(String groupId, Document document)
			throws RuleToolXMLException;

	/**
	 * 通过规则id取规则
	 * 
	 * @param ruleId
	 *            规则id
	 * @param document
	 *            dom4j document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public Element findRuleByRuleId(String ruleId, Document document)
			throws RuleToolXMLException;

	/**
	 * 通过输出参数名称取输出参数
	 * 
	 * @param outputName
	 *            输出名称
	 * @param document
	 *            dom4j document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public Element findOutputParameter(String ruleId, String outputName,
			Document document) throws RuleToolXMLException;

	/**
	 * 输入参数是否存在
	 * 
	 * @param inputName
	 * @param document
	 * @return
	 */
	public boolean isInputParameterExist(String ruleId, String inputName,
			Document document);

	/**
	 * 输出参数是否存在
	 * 
	 * @param outputName
	 * @param document
	 * @return
	 */
	public boolean isOutputParameterExist(String ruleId, String outputName,
			Document document);

	/**
	 * 通过规则ID判断规则是否存在
	 * 
	 * @param ruleId
	 *            规则id
	 * @param document
	 *            dom4j document
	 * @return
	 */
	public boolean isRuleExist(String ruleId, Document document);

	/**
	 * 重写document
	 * 
	 * @param document
	 * @param filePath
	 *            保存路径
	 * @throws RuleToolXMLException
	 */
	public void saveDocument(Document document, String filePath)
			throws RuleToolXMLException;

	/**
	 * 判断document 中是否已有groupId
	 * 
	 * @param groupId
	 *            规则组id
	 * @param document
	 *            dom4j document
	 * @return
	 */
	public boolean isRuleGroupExist(String groupId, Document document);

	/**
	 * 查找到group
	 * 
	 * @param document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public Element findGroups(Document document) throws RuleToolXMLException;

	/**
	 * 查找输入参数
	 * 
	 * @param document
	 * @param name
	 * @return
	 * @throws RuleToolXMLException
	 */
	public Element findInputParameter(String ruleId, String name,
			Document document) throws RuleToolXMLException;

	/**
	 * 查找监听
	 * 
	 * @param document
	 * @param name
	 * @return
	 * @throws RuleToolXMLException
	 */
	public Element findListener(String ruleId, String name, Document document)
			throws RuleToolXMLException;

	/**
	 * 判断监听是否存在
	 * 
	 * @param document
	 * @param name
	 * @return
	 */
	public boolean isListenerExsit(String ruleId, String name, Document document);

	/**
	 * 查找rules
	 * 
	 * @param document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public Element findRules(Document document) throws RuleToolXMLException;

	/**
	 * 获取所有规则
	 * 
	 * @param currPage
	 *            转向页
	 * @param pageSize
	 *            每页数
	 * @param uri
	 *            路径
	 * @return
	 * @throws RuleToolXMLException
	 */
	public RuleEngine getRules(Integer currPage, Integer pageSize, String uri)
			throws RuleToolXMLException;

	/**
	 * 获取规则组
	 * 
	 * @param currPage
	 *            转向页
	 * @param pageSize
	 *            每页数
	 * @param uri
	 *            路径
	 * @return
	 * @throws RuleToolXMLException
	 */
	public RuleEngine getRuleGroups(Integer currPage, Integer pageSize,
			String uri) throws RuleToolXMLException;

	/**
	 * 模糊查询规则分组
	 * 
	 * @param group
	 * @param document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public List findRuleGroup(Group group, Document document)
			throws RuleToolXMLException;

	/**
	 * 模糊查询规则
	 * 
	 * @param rule
	 * @param document
	 * @return
	 * @throws RuleToolXMLException
	 */
	public List findRule(Rule rule, Document document)
			throws RuleToolXMLException;
}
