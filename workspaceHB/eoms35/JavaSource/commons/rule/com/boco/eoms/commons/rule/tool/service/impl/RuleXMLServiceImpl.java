package com.boco.eoms.commons.rule.tool.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.rule.config.model.Group;
import com.boco.eoms.commons.rule.config.model.GroupRef;
import com.boco.eoms.commons.rule.config.model.Input;
import com.boco.eoms.commons.rule.config.model.Listener;
import com.boco.eoms.commons.rule.config.model.Listeners;
import com.boco.eoms.commons.rule.config.model.Output;
import com.boco.eoms.commons.rule.config.model.Parameter;
import com.boco.eoms.commons.rule.config.model.Rule;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.tool.dao.IRuleDom4jDao;
import com.boco.eoms.commons.rule.tool.exception.RuleToolDom4jXMLException;
import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLException;
import com.boco.eoms.commons.rule.tool.service.IRuleXMLService;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 23, 2007 5:01:26 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleXMLServiceImpl implements IRuleXMLService {

	private IRuleDom4jDao ruleDom4jDao;

	public void setRuleDom4jDao(IRuleDom4jDao ruleDom4jDao) {
		this.ruleDom4jDao = ruleDom4jDao;
	}

	public Element findRuleByRuleId(String ruleId, Document document)
			throws RuleToolXMLException {
		Element element = (Element) ruleDom4jDao.findRuleById(
				"//ruleEngine/rules/rule[@id='" + ruleId + "']", document);
		if (element == null) {
			throw new RuleToolXMLException("规则id（" + ruleId + "）未找到");
		}
		return element;
	}

	public boolean isRuleExist(String ruleId, Document document) {
		Element element;
		try {
			element = (Element) ruleDom4jDao.findRuleById(
					"//ruleEngine/rules/rule[@id='" + ruleId + "']", document);
		} catch (RuleToolDom4jXMLException e) {
			return false;
		}
		if (element == null) {
			return false;
		}
		return true;
	}

	public Element findRuleGroupByGroupId(String groupId, Document document)
			throws RuleToolXMLException {
		// /ruleEngine/groups/group/@id[@id='" + groupId + "']
		// 查询
		Element element = (Element) ruleDom4jDao.findRuleById(
				"//ruleEngine/groups/group[@id='" + groupId + "']", document);
		// if (list == null || list.isEmpty()) {
		// throw new RuleToolXMLException("规则路由id（" + groupId + "）未找到");
		// }
		// return (Element) list.iterator().next();
		if (element == null) {
			throw new RuleToolXMLException("规则路由id（" + groupId + "）未找到");
		}
		return element;
	}

	public synchronized void saveDocument(Document document, String filePath)
			throws RuleToolXMLException {
		File file = new File(StaticMethod.getFilePath(filePath));
		XMLWriter writer = null;
		// 格式化输出,类型IE浏览一样
		OutputFormat format = OutputFormat.createPrettyPrint();
		/** 指定XML字符集编码 */
		format.setEncoding("GBK");
		try {
			// 若不存在则创建,若在即修改
			if (!file.exists()) {
				file.createNewFile();
			}
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.write(document);
		} catch (IOException e) {
			throw new RuleToolXMLException(e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				throw new RuleToolXMLException(e);
			}
		}

	}

	public boolean isRuleGroupExist(String groupId, Document document) {
		// 查询
		Element element;
		try {
			element = (Element) ruleDom4jDao.findRuleById(
					"//ruleEngine/groups/group[@id='" + groupId + "']",
					document);
		} catch (RuleToolDom4jXMLException e) {
			return false;
		}
		if (element == null) {
			return false;
		}
		return true;
	}

	public Element findOutputParameter(String ruleId, String outputName,
			Document document) throws RuleToolXMLException {

		Element element = (Element) ruleDom4jDao.findRuleById(
				"//ruleEngine/rules/rule[@id='" + ruleId + "']"
						+ "/output/parameter[@name='" + outputName + "']",
				document);
		if (element == null) {
			throw new RuleToolXMLException("输出参数名称（" + outputName + "）未找到");
		}
		return element;
	}

	public boolean isInputParameterExist(String ruleId, String inputName,
			Document document) {

		Element element;
		try {
			element = (Element) ruleDom4jDao.findRuleById(
					"//ruleEngine/rules/rule[@id='" + ruleId + "']"
							+ "/input/parameter[@name='" + inputName + "']",
					document);
		} catch (RuleToolDom4jXMLException e) {
			return false;
		}
		if (element == null) {
			return false;
		}
		return true;
	}

	public boolean isOutputParameterExist(String ruleId, String outputName,
			Document document) {
		Element element;
		try {
			element = (Element) ruleDom4jDao.findRuleById(
					"//ruleEngine/rules/rule[@id='" + ruleId + "']"
							+ "/output/parameter[@name='" + outputName + "']",
					document);
		} catch (RuleToolDom4jXMLException e) {
			return false;
		}
		if (element == null) {
			return false;
		}
		return true;
	}

	public Element findGroups(Document document) throws RuleToolXMLException {
		Element element = (Element) ruleDom4jDao.findRuleById(
				"//ruleEngine/groups", document);
		if (element == null) {
			throw new RuleToolXMLException("groups未找到");
		}
		return element;
	}

	public Element findInputParameter(String ruleId, String name,
			Document document) throws RuleToolXMLException {
		Element element = (Element) ruleDom4jDao.findRuleById(
				"//ruleEngine/rules/rule[@id='" + ruleId + "']"
						+ "/input/parameter[@name='" + name + "']", document);
		if (element == null) {
			throw new RuleToolXMLException("输入参数名称（" + name + "）未找到");
		}
		return element;
	}

	public Element findListener(String ruleId, String name, Document document)
			throws RuleToolXMLException {
		Element element = (Element) ruleDom4jDao
				.findRuleById("//ruleEngine/rules/rule[@id='" + ruleId + "']"
						+ "/listeners/listener[@name='" + name + "']", document);
		if (element == null) {
			throw new RuleToolXMLException("监听名称（" + name + "）未找到");
		}
		return element;
	}

	public Element findRules(Document document) throws RuleToolXMLException {
		Element element = (Element) ruleDom4jDao.findRuleById(
				"//ruleEngine/rules", document);
		if (element == null) {
			throw new RuleToolXMLException("rules未找到");
		}
		return element;
	}

	public boolean isListenerExsit(String ruleId, String name, Document document) {
		Element element;
		try {
			element = (Element) ruleDom4jDao.findRuleById(
					"//ruleEngine/rules/rule[@id='" + ruleId + "']"
							+ "/listeners/listener[@name='" + name + "']",
					document);
		} catch (RuleToolDom4jXMLException e) {
			return false;
		}
		if (element == null) {
			return false;
		}
		return true;
	}

	public RuleEngine getRuleGroups(Integer currPage, Integer pageSize,
			String uri) throws RuleToolXMLException {
		// TODO Auto-generated method stub
		return null;
	}

	public RuleEngine getRules(Integer currPage, Integer pageSize, String uri)
			throws RuleToolXMLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List findRule(Rule rule, Document document)
			throws RuleToolXMLException {
		// 模糊查询
		// List list = (List) ruleDom4jDao.findRulesById(
		// "//ruleEngine/groups/group[contains(@description,'"
		// + description + "')]", document);

		// id,name,description
		List list = null;
		// 组合查询，对id,descrption,className进行查询
		if (!stringIsNull(rule.getId())) {
			list = (List) ruleDom4jDao.findRulesById(
					"//ruleEngine/rules/rule[contains(@id,'" + rule.getId()
							+ "')]", document);
		} else if (!stringIsNull(rule.getDescription())) {
			list = (List) ruleDom4jDao.findRulesById(
					"//ruleEngine/rules/rule[contains(@description,'"
							+ rule.getDescription() + "')]", document);
		} else if (!stringIsNull(rule.getClassName())) {
			list = (List) ruleDom4jDao.findRulesById(
					"//ruleEngine/rules/rule[contains(@className,'"
							+ rule.getClassName() + "')]", document);
		}
		// 所有条件都为空则查询全部
		else {
			list = (List) ruleDom4jDao.findRulesById("//ruleEngine/rules/rule",
					document);
		}

		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				Element element = (Element) it.next();
				// 过滤所有条件,rule/id
				if (!stringIsNull(rule.getId())) {
					// 若xml中的rule/id中不包含查条件内容，则要删除
					if (!contains(element.attributeValue("id"), rule.getId())) {
						it.remove();
					}
				}
				if (!stringIsNull(rule.getDescription())) {
					// 若xml中的rule/description中不包含查条件内容，则要删除
					if (!contains(element.attributeValue("description"), rule
							.getDescription())) {
						it.remove();
					}
				}
				if (!stringIsNull(rule.getClassName())) {
					// 若xml中的rule/className中不包含查条件内容，则要删除
					if (!contains(element.attributeValue("className"), rule
							.getClassName())) {
						it.remove();
					}
				}

			}
		}
		// 将element list mapping 为rule对象
		return element2Rule(list);
	}

	/**
	 * 将dom4j的element手动mapping为Rule对象
	 * 
	 * @param elementList
	 * @return 仅//ruleEngine/rules/rule规则列表
	 */
	private List element2Rule(List elementList) {
		List list = new ArrayList();
		if (elementList != null) {
			for (Iterator it = elementList.iterator(); it.hasNext();) {
				Element element = (Element) it.next();
				Rule rule = new Rule();
				rule.setId(element.attributeValue("id"));
				rule.setDescription(element.attributeValue("description"));
				rule.setClassName(element.attributeValue("className"));
				List inputList = new ArrayList();
				List outputList = new ArrayList();
				List listenerList = new ArrayList();
				if (element.element("input") != null) {
					Element input = element.element("input");
					// 构建输入参数
					for (Iterator parameterIt = input
							.elementIterator("parameter"); parameterIt
							.hasNext();) {
						Element paraElement = (Element) parameterIt.next();
						if (paraElement != null) {
							Parameter parameter = new Parameter();
							parameter.setDescription(paraElement
									.attributeValue("description"));
							parameter.setType(paraElement
									.attributeValue("type"));
							parameter.setName(paraElement
									.attributeValue("name"));
							inputList.add(parameter);
						}
					}
					rule.setInput(new Input(inputList));
				}
				if (element.element("output") != null) {
					Element output = element.element("output");
					// 构建输出参数
					for (Iterator parameterIt = output
							.elementIterator("parameter"); parameterIt
							.hasNext();) {
						Element paraElement = (Element) parameterIt.next();

						if (paraElement != null) {
							Parameter parameter = new Parameter();
							parameter.setDescription(paraElement
									.attributeValue("description"));
							parameter.setType(paraElement
									.attributeValue("type"));
							parameter.setName(paraElement
									.attributeValue("name"));
							parameter.setExpression(paraElement
									.attributeValue("expression"));
							outputList.add(parameter);
						}
					}
					rule.setOutput(new Output(outputList));
				}
				if (element.element("listeners") != null) {
					Element listeners = element.element("listeners");
					for (Iterator listenerIt = listeners
							.elementIterator("listener"); listenerIt.hasNext();) {
						Element listenerElement = (Element) listenerIt.next();
						if (listenerElement != null) {
							Listener listener = new Listener();
							listener.setDescription(listenerElement
									.attributeValue("description"));
							listener.setName(listenerElement
									.attributeValue("name"));
							listenerList.add(listener);
						}
					}
					rule.setListeners(new Listeners(listenerList));
				}
				list.add(rule);
			}
		}
		return list;
	}

	/**
	 * //ruleEngin/groups/group下的element mapping为Group对象
	 * 
	 * @param elementList
	 * @return
	 */
	public List element2RuleGroup(List elementList) {
		List list = new ArrayList();
		if (elementList != null) {
			for (Iterator it = elementList.iterator(); it.hasNext();) {
				Element element = (Element) it.next();
				Group group = new Group();
				group.setId(element.attributeValue("id"));
				group.setDescription(element.attributeValue("description"));
				List refList = new ArrayList();
				// 构建输入参数
				if (element.element("groupRef") != null) {
					for (Iterator refIt = element.elementIterator("groupRef"); refIt
							.hasNext();) {
						Element refElement = (Element) refIt.next();

						GroupRef groupRef = new GroupRef();
						String pri = refElement.attributeValue("pri");
						if (!NumberUtils.isNumber(pri)) {
							// 若不为pri不为数字则设置优先级最低
							pri = "-1";
						}
						groupRef.setPri(new Integer(pri));
						groupRef.setRuleId(refElement.attributeValue("ruleId"));
						refList.add(groupRef);
					}
					group.setGroupRef(refList);
				}
				list.add(group);
			}
		}
		return list;
	}

	public List findRuleGroup(Group group, Document document)
			throws RuleToolXMLException {
		// 模糊查询id,name,description
		List list = null;
		// 组合查询，对id,descrption,className进行查询
		if (!stringIsNull(group.getId())) {
			list = (List) ruleDom4jDao.findRulesById(
					"//ruleEngine/groups/group[contains(@id,'" + group.getId()
							+ "')]", document);
		} else if (!stringIsNull(group.getDescription())) {
			list = (List) ruleDom4jDao.findRulesById(
					"//ruleEngine/groups/group[contains(@description,'"
							+ group.getDescription() + "')]", document);
		}
		// 所有条件都为空则查询全部
		else {
			list = (List) ruleDom4jDao.findRulesById(
					"//ruleEngine/groups/group", document);
		}

		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				Element element = (Element) it.next();
				// 过滤所有条件,rule/id
				if (!stringIsNull(group.getId())) {
					// 若xml中的rule/id中不包含查条件内容，则要删除
					if (!contains(element.attributeValue("id"), group.getId())) {
						it.remove();
					}
				}
				if (!stringIsNull(group.getDescription())) {
					// 若xml中的rule/description中不包含查条件内容，则要删除
					if (!contains(element.attributeValue("description"), group
							.getDescription())) {
						it.remove();
					}
				}

			}
		}

		// 将element list mapping 为group对象
		return element2RuleGroup(list);
	}

	/**
	 * source字符串是否包含target字符串
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	private boolean contains(String source, String target) {
		String s = source == null ? "" : source;
		return s.indexOf(target) > 0;
		//		return s.contains(target);
	}

	/**
	 * 判断字符串是否为空或""
	 * 
	 * @param str
	 * @return
	 */
	private boolean stringIsNull(String str) {
		if (str != null && !"".equals(str)) {
			return false;
		}
		return true;

	}

}
