package com.boco.eoms.commons.rule.tool.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.rule.config.model.Group;
import com.boco.eoms.commons.rule.config.model.Groups;
import com.boco.eoms.commons.rule.config.model.RuleEngine;
import com.boco.eoms.commons.rule.config.model.Rules;
import com.boco.eoms.commons.rule.exception.RuleException;
import com.boco.eoms.commons.rule.service.OgnlExpressionService;
import com.boco.eoms.commons.rule.service.RuleConfigWrapper;
import com.boco.eoms.commons.rule.tool.exception.RuleToolXMLException;
import com.boco.eoms.commons.rule.tool.model.LabelBean;
import com.boco.eoms.commons.rule.tool.model.Rule;
import com.boco.eoms.commons.rule.tool.service.IBusinessDictService;
import com.boco.eoms.commons.rule.tool.service.IRuleAttributesService;
import com.boco.eoms.commons.rule.tool.service.IRuleXMLDom4jDocumentFactoryService;
import com.boco.eoms.commons.rule.tool.service.IRuleXMLService;
import com.boco.eoms.commons.rule.tool.service.impl.ClassHelper;
import com.boco.eoms.commons.rule.util.RuleAttributes;

/**
 * 
 * <p>
 * Title:规则web工具action
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2007 3:37:30 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class RuleWebToolAction extends BaseAction {

	/**
	 * 分页列出规则组
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailRuleGroup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 查询条件
		// 组描述
		String description = request.getParameter("qdescription");
		// 组id
		String groupId = request.getParameter("qgroupId");

		// spring 中配置的规则id
		String ruleId = RequestUtils.getStringParameter(request, "id");
		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(ruleId);
		// 取规则配置
		RuleEngine rules = RuleConfigWrapper.getRuleEngine(rule.getXmlPath());

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());
		// 组合条件查询
		Group groupQO = new Group();
		groupQO.setDescription(description);
		groupQO.setId(groupId);
		List ruleGroups = ruleXMLService.findRuleGroup(groupQO, document);

		// 要转向的页码
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		// 新增查询功能，暂时注释
		// List ruleGroups = rules.getGroups().getGroup();
		// 分页后的xml对象
		Map result = paginationForList(currPage, ruleGroups);

		List pagedRules = (List) result.get("result");
		// 为规则组分页
		RuleEngine pagedRuleEngine = copyRuleEngineForRuleGroup(rules,
				pagedRules);
		// 将当前页写到页面
		request.setAttribute("currPage", result.get("currPage"));
		request.setAttribute("rule", rule);
		request.setAttribute("rules", pagedRuleEngine);
		request.setAttribute("qdescription", description);
		request.setAttribute("qgroupId", groupId);

		// 总条数
		request.setAttribute("total", result.get("total"));
		// 总页数
		request.setAttribute("pageTotal", result.get("pageTotal"));
		return mapping.findForward("detailRuleGroup");
	}

	/**
	 * 复制分页结果，仅改变规则部分 <rules></rules>
	 * 
	 * @param sourceEngine
	 *            原ruleEngine
	 * @param sourceRuleGroups
	 *            规则组list
	 * @return
	 */
	private RuleEngine copyRuleEngineForRuleGroup(RuleEngine sourceEngine,
			List sourceRuleGroups) {
		// 所有xml分页结果
		RuleEngine resultRule = new RuleEngine();
		resultRule.setDict(sourceEngine.getDict());
		resultRule.setExpStyles(sourceEngine.getExpStyles());
		Groups groups = new Groups();
		groups.setGroup(sourceRuleGroups);
		resultRule.setGroups(groups);
		resultRule.setXmlPath(sourceEngine.getXmlPath());

		resultRule.setRules(sourceEngine.getRules());
		return resultRule;
	}

	/**
	 * 分页列出规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailRule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 查询条件,//ruleEngine/rules/rule的id
		String queryId = request.getParameter("qid");
		String description = request.getParameter("qdescription");
		String className = request.getParameter("qclassName");
		// spring 中配置的规则id
		String ruleId = RequestUtils.getStringParameter(request, "id");
		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(ruleId);
		// 取规则配置
		RuleEngine rules = RuleConfigWrapper.getRuleEngine(rule.getXmlPath());

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());
		// 组合条件查询
		com.boco.eoms.commons.rule.config.model.Rule ruleQO = new com.boco.eoms.commons.rule.config.model.Rule();
		ruleQO.setDescription(description);
		ruleQO.setId(queryId);
		ruleQO.setClassName(className);
		List ruleList = ruleXMLService.findRule(ruleQO, document);

		// 要转向的页码
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		// 暂时注释
		// 对规则分页
		// List ruleList = rules.getRules().getRule();
		// 分页后的xml对象
		Map result = paginationForList(currPage, ruleList);
		List pagedRules = (List) result.get("result");
		RuleEngine pagedRuleEngine = copyRuleEngineForRule(rules, pagedRules);
		// 将当前页写到页面
		request.setAttribute("currPage", result.get("currPage"));
		request.setAttribute("rule", rule);
		request.setAttribute("rules", pagedRuleEngine);
		// 总条数
		request.setAttribute("total", result.get("total"));
		// 总页数
		request.setAttribute("pageTotal", result.get("pageTotal"));
		request.setAttribute("qid", queryId);
		request.setAttribute("qdescription", description);
		request.setAttribute("qclassName", className);
		return mapping.findForward("detailRule");
	}

	/**
	 * 对规则分页
	 * 
	 * @param currPage
	 *            要转向的页码
	 * @param paginationList
	 *            要分页的list
	 * @return
	 */
	private Map paginationForList(Integer currPage, List paginationList) {
		// TODO 性能问题需要调整 利用SAX改写
		Map map = new HashMap();
		RuleAttributes ruleAttributes = (RuleAttributes) getBean("ruleAttributes");
		Integer pageSize = ruleAttributes.getPageSize();
		// 规则分页结果
		List result = new ArrayList();
		if (currPage == null || currPage.intValue() < 1) {
			currPage = new Integer(1);
		}
		// 转向页乘以每页显示数量=从当前行开始
		int rows = currPage.intValue() * pageSize.intValue()
				- pageSize.intValue();

		int total = paginationList.size();
		int pageTotal = total % pageSize.intValue() == 0 ? total
				/ pageSize.intValue() : total / pageSize.intValue() + 1;

		// 如：数据总数为1，每页显示数为2
		if (pageSize.intValue() >= total) {
			for (int i = 0; i < total; i++) {
				result.add(paginationList.get(i));
				currPage = new Integer(1);
			}
		}
		// 要转向页码（记录数）大于总记录数，大于最多页
		else if (rows + pageSize.intValue() >= total) {
			// 总记录数减去总记录数与每页显示记录数的余数
			int page = total - (total % pageSize.intValue());
			for (int i = page; i < total; i++) {
				result.add(paginationList.get(i));
			}
			currPage = new Integer(pageTotal);
		} else {
			for (int i = rows; i < rows + pageSize.intValue(); i++) {
				result.add(paginationList.get(i));
			}
		}

		// 分页后的xml对象
		map.put("result", result);
		// 当前页
		map.put("currPage", currPage);
		// 总记录数
		map.put("total", new Integer(total));
		map.put("pageTotal", new Integer(pageTotal));
		return map;
	}

	/**
	 * 复制分页结果，仅改变规则部分 <rules></rules>
	 * 
	 * @param sourceEngine
	 *            原ruleEngine
	 * @param sourceRules
	 *            规则list
	 * @return
	 */
	private RuleEngine copyRuleEngineForRule(RuleEngine sourceEngine,
			List sourceRules) {
		// 所有xml分页结果
		RuleEngine resultRule = new RuleEngine();
		resultRule.setDict(sourceEngine.getDict());
		resultRule.setExpStyles(sourceEngine.getExpStyles());
		resultRule.setGroups(sourceEngine.getGroups());
		resultRule.setXmlPath(sourceEngine.getXmlPath());
		Rules rulesModel = new Rules();
		rulesModel.setRule(sourceRules);
		resultRule.setRules(rulesModel);
		return resultRule;
	}

	/**
	 * 初始化注册过的规则列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listRule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 取规则列表
		List ruleList = ruleAttributesService.listRules();

		request.setAttribute("ruleList", ruleList);
		return mapping.findForward("listRule");
	}

	/**
	 * 修改路由分组
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 规则ID，用于取xml path
		String id = request.getParameter("id");
		// 规则路由id
		String groupId = request.getParameter("groupId");
		// 修改后的mgroupId
		String mgroupId = request.getParameter("mgroupId");

		// 规则路由描述
		String groupDescription = request.getParameter("groupDescription");
		// 规则ID（多个）
		String[] groupRuleId = request.getParameterValues("groupRuleId");
		// 规则优先级（多个）
		String[] groupRulePri = request.getParameterValues("groupRulePri");

		// 查询组合条件
		// 查询条件,//ruleEngine/rules/rule的id
		String qgroupid = request.getParameter("qgroupId");
		String qdescription = request.getParameter("qdescription");

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());

		// 若用户修改了规则组ID，则判断定新规则组ID在原有XML中是否存在
		if (groupId != null && !groupId.equals(mgroupId)) {
			if (ruleXMLService.isRuleGroupExist(mgroupId, document)) {
				// TODO 需转向错误页面
				response.getWriter().println(
						"The rule group id has already exist");
				return null;
			}
		}

		// 获取groupId的element
		Element element = (Element) ruleXMLService.findRuleGroupByGroupId(
				groupId, document);
		// 修改组 ID
		element.attribute("id").setText(mgroupId);
		// 修改描述
		element.attribute("description").setText(groupDescription);
		// 删除所有groupGef 元素
		for (Iterator it = element.elementIterator("groupRef"); it.hasNext();) {
			Element el = (Element) it.next();
			element.remove(el);
		}
		if (groupRuleId != null) {
			// 添加修改的groupGef元素
			for (int i = 0; i < groupRuleId.length; i++) {
				Element groupRefElement = element.addElement("groupRef");
				groupRefElement.addAttribute("ruleId", groupRuleId[i]);
				groupRefElement.addAttribute("pri", groupRulePri[i]);
			}
		}
		// 分页
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");

		try {
			saveAndFlushGroup(document, rule.getXmlPath(), groupId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}

		ActionForward forward = mapping.findForward("forwardDetailRuleGroup");
		String path = forward.getPath() + "&id=" + id + "&currPage" + currPage
				+ "&qdescription=" + qdescription + "&qgroupId=" + qgroupid;
		return new ActionForward(path, false);
	}

	/**
	 * 保存并刷新缓存
	 * 
	 * @param document
	 *            dom4j document
	 * @param xmlPath
	 *            路径
	 * @param groupId
	 *            组id
	 * @throws RuleToolXMLException
	 */
	private synchronized void saveAndFlushGroup(Document document,
			String xmlPath, String groupId) throws RuleToolXMLException {
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 修改
		ruleXMLService.saveDocument(document, xmlPath);
		// 更新路由分组缓存
		RuleConfigWrapper.flushGroup(xmlPath, groupId);
	}

	/**
	 * 修改规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateRule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 规则xml ID，用于取xml path
		String id = request.getParameter("id");
		// 规则id
		String ruleId = request.getParameter("ruleId");

		// 规则类名
		String ruleClassName = request.getParameter("ruleClassName");
		// 修改后的夫则ID
		String mruleId = request.getParameter("mruleId");

		// 规则描述
		String ruleDescription = request.getParameter("ruleDescription");

		// 输入参数描述
		String inputDescription[] = request
				.getParameterValues("inputDescription");

		// 输入参数名称
		String inputName[] = request.getParameterValues("inputName");

		// 输入参数类型
		String inputType[] = request.getParameterValues("inputType");
		// 输出参数描述
		String outputDescription[] = request
				.getParameterValues("outputDescription");
		// 输出参数名称
		String outputName[] = request.getParameterValues("outputName");
		// 输入参数类型
		String outputType[] = request.getParameterValues("outputType");
		// 输出表达式
		String outputExpression[] = request
				.getParameterValues("outputExpression");
		// 监听类名
		String listenerName[] = request.getParameterValues("listenerName");
		// 监听描述
		String listenerDescription[] = request
				.getParameterValues("listenerDescription");

		// 查询组合条件

		// 修改后转向页码
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");

		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());
		// 若用户修改了规则组ID，则判断定新规则组ID在原有XML中是否存在
		if (ruleId != null && !ruleId.equals(mruleId)) {
			if (ruleXMLService.isRuleExist(mruleId, document)) {
				// TODO 需转向错误页面
				response.getWriter().println(
						"The rule rule id has already exist");
				return null;
			}
		}

		Element element = ruleXMLService.findRuleByRuleId(ruleId, document);
		// 规则类名
		element.attribute("className").setText(ruleClassName);
		// 规则id
		element.attribute("id").setText(mruleId);
		// 规则描述
		element.attribute("description").setText(ruleDescription);

		// 删除input/下的parameter
		Element inputElement = element.element("input");
		if (inputElement != null) {
			for (Iterator it = inputElement.elementIterator("parameter"); it
					.hasNext();) {
				Element parameter = (Element) it.next();
				inputElement.remove(parameter);
			}
		} else {
			element.addElement("input");
			inputElement = element.element("input");
		}
		// 输入参数名称验证map,判断多个输出参数中是否有相同参数
		Map inputMap = new HashMap();
		// 输出参数名称验证map，判断多个输出参数中是否有相同参数
		Map outputMap = new HashMap();
		if (inputName != null) {
			for (int i = 0; i < inputName.length; i++) {
				Element parameter = inputElement.addElement("parameter");
				parameter.addAttribute("name", inputName[i]);
				parameter.addAttribute("type", inputType[i]);
				parameter.addAttribute("description", inputDescription[i]);
				// 验证输入参数名称是否有重复
				if (inputMap.containsKey(inputName[i])) {
					// TODO 需转向错误页面
					response.getWriter().println(
							"The input parameter name has already exist");
					return null;
				}
				inputMap.put(inputName[i], "");
			}
		}

		// 删除output/下的parameter
		Element outputElement = element.element("output");
		if (outputElement != null) {
			for (Iterator it = outputElement.elementIterator("parameter"); it
					.hasNext();) {
				Element parameter = (Element) it.next();
				outputElement.remove(parameter);
			}
		} else {
			element.addElement("output");
			outputElement = element.element("output");
		}

		if (outputName != null) {
			for (int i = 0; i < outputName.length; i++) {
				Element parameter = outputElement.addElement("parameter");
				parameter.addAttribute("name", outputName[i]);
				parameter.addAttribute("type", outputType[i]);
				parameter.addAttribute("description", outputDescription[i]);
				parameter.addAttribute("expression", outputExpression[i]);
				// 验证输出参数name是否有重复
				if (outputMap.containsKey(outputName[i])) {
					// TODO 需转向错误页面
					response.getWriter().println(
							"The output parameter name has already exist");
					return null;
				}
				outputMap.put(outputName[i], "");
			}
		}

		// 删除listener/下的listener
		Element listenersElement = element.element("listeners");
		if (listenersElement != null) {
			for (Iterator it = listenersElement.elementIterator("listener"); it
					.hasNext();) {
				Element listener = (Element) it.next();
				listenersElement.remove(listener);
			}
		} else {
			element.addElement("listeners");
			listenersElement = element.element("listeners");
		}

		if (listenerName != null) {
			for (int i = 0; i < listenerName.length; i++) {
				Element listener = listenersElement.addElement("listener");
				listener.addAttribute("name", listenerName[i]);
				listener.addAttribute("description", listenerDescription[i]);
			}
		}
		// 查询条件,//ruleEngine/rules/rule的id
		String qid = request.getParameter("qid");
		String qdescription = request.getParameter("qdescription");
		String qclassName = request.getParameter("qclassName");
		try {
			saveAndFlushRule(document, rule.getXmlPath(), ruleId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}
		ActionForward forward = mapping.findForward("forwardDetailRule");
		String path = forward.getPath() + "&id=" + id + "&currPage=" + currPage
				+ "&qclassName=" + qclassName + "&qid=" + qid
				+ "&qdescription=" + qdescription;
		return new ActionForward(path, false);
	}

	/**
	 * 保存并刷新缓存
	 * 
	 * @param document
	 *            dom4j document
	 * @param xmlPath
	 *            路径
	 * @param ruleId
	 *            规则id
	 * @throws RuleToolXMLException
	 */
	private synchronized void saveAndFlushRule(Document document,
			String xmlPath, String ruleId) throws RuleToolXMLException {
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 修改
		ruleXMLService.saveDocument(document, xmlPath);
		// 缓存更新
		RuleConfigWrapper.flushRule(xmlPath, ruleId);

	}

	/**
	 * 编辑表达式
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initEditExpression(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// spring 中配置的规则id
		String id = request.getParameter("id");

		// 规则id
		String ruleId = request.getParameter("ruleId");

		// 输出参数name

		String outputName = request.getParameter("outName");

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule ruleConfig = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(ruleConfig.getXmlPath());
		String expression = "";
		if (outputName != null) {
			// 取输出参数名称的输出参数，将表达式setAttributes入jsp
			Element element = ruleXMLService.findOutputParameter(ruleId,
					outputName, document);
			expression = element.attributeValue("expression");
		}
		// 通过id取规则
		// 取规则配置
		RuleEngine rules = RuleConfigWrapper.getRuleEngine(ruleConfig
				.getXmlPath());
		// 通过ruleId取mapping的rule对象，页面使用rule中的输入输出参数
		com.boco.eoms.commons.rule.config.model.Rule rule = RuleConfigWrapper
				.findRulesByRuleId(rules, ruleId);

		IBusinessDictService dictService = (IBusinessDictService) getBean("businessDictService");
		// 取字典
		List dictList = dictService.findBusinessDictForModule(rules.getDict()
				.getDictTypeSql());
		// 写入ruleId进request
		request.setAttribute("dictList", dictList);
		// 编辑输出参数名称
		request.setAttribute("outName", outputName);
		request.setAttribute("ruleConfig", ruleConfig);
		request.setAttribute("rule", rule);
		request.setAttribute("rules", rules);
		request.setAttribute("expression", expression);
		return mapping.findForward("editExpression");
	}

	/**
	 * 字典关联菜单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward relateDict(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		// 字典类型ID
		String dictId = request.getParameter("dictId");
		// 规则id
		String id = request.getParameter("id");
		IBusinessDictService dictService = (IBusinessDictService) getBean("businessDictService");
		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule ruleConfig = ruleAttributesService.findRuleById(id);
		// 通过id取规则
		// 取规则配置
		RuleEngine rules = RuleConfigWrapper.getRuleEngine(ruleConfig
				.getXmlPath());
		// 取字典
		List dictList = dictService.findBusinessDictByModuleId(rules.getDict()
				.getDictSql(), dictId);

		StringBuffer str = new StringBuffer("<dicts>");
		if (dictList != null) {
			for (Iterator it = dictList.iterator(); it.hasNext();) {
				LabelBean lb = (LabelBean) it.next();
				str.append("<dict name='" + lb.getName() + "' value='"
						+ lb.getValue() + "'/>");
			}
		}
		str.append("</dicts>");
		response.setContentType("text/xml");
		response.getWriter().print(str.toString());
		return null;
	}

	/**
	 * 删除路由分组规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delGroupRule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 规则ID，用于取xml path
		String id = request.getParameter("id");
		// 规则路由id
		String groupId = request.getParameter("groupId");

		// 规则ID
		String groupRuleId = request.getParameter("ruleId");

		if (groupRuleId == null || "".equals(groupRuleId)) {
			// TODO 修改转向页面
			response.getWriter().println("Group Rule Id is null or ''");
		}

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());

		// 获取groupId的element
		Element element = (Element) ruleXMLService.findRuleGroupByGroupId(
				groupId, document);

		// 删除所有groupGef 元素
		for (Iterator it = element.elementIterator("groupRef"); it.hasNext();) {
			Element el = (Element) it.next();
			if (groupRuleId.equals(el.attributeValue("ruleId"))) {
				element.remove(el);
			}
		}

		// // 修改
		// ruleXMLService.saveDocument(document, rule.getXmlPath());
		// // 更新路由分组缓存
		// RuleConfigWrapper.flushGroup(rule.getXmlPath(), groupId);
		try {
			saveAndFlushGroup(document, rule.getXmlPath(), groupId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}
		// 查询条件,//ruleEngine/rules/rule的id
		String qgroupId = request.getParameter("qgroupId");
		String qdescription = request.getParameter("qdescription");

		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		ActionForward forward = mapping.findForward("forwardDetailRuleGroup");
		String path = forward.getPath() + "&id=" + id + "&currPage=" + currPage
				+ "&qgroupId=" + qgroupId + "&qdescription=" + qdescription;
		return new ActionForward(path, false);
	}

	/**
	 * 新增路由分组
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 规则ID，用于取xml path
		String id = request.getParameter("id");
		// 规则路由id
		String groupId = request.getParameter("groupId");

		// 规则路由描述
		String groupDescription = request.getParameter("groupDescription");
		// 规则ID（多个）
		String[] groupRuleId = request.getParameterValues("groupRuleId");
		// 规则优先级（多个）
		String[] groupRulePri = request.getParameterValues("groupRulePri");

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());

		// 若用户修改了规则组ID，则判断定新规则组ID在原有XML中是否存在
		if (groupId != null) {
			if (ruleXMLService.isRuleGroupExist(groupId, document)) {
				// TODO 需转向错误页面
				response.getWriter().println(
						"The rule group id has already exist");
				return null;
			}
		}

		// 获取groupId的element
		Element element = (Element) ruleXMLService.findGroups(document);
		// 组内新增规则
		Element groupElement = element.addElement("group");
		groupElement.addAttribute("id", groupId);
		groupElement.addAttribute("description", groupDescription);

		if (groupRuleId != null) {
			// 添加修改的groupGef元素
			for (int i = 0; i < groupRuleId.length; i++) {
				Element groupRefElement = groupElement.addElement("groupRef");
				groupRefElement.addAttribute("ruleId", groupRuleId[i]);
				groupRefElement.addAttribute("pri", groupRulePri[i]);
			}
		}
		// // 更新路由分组缓存
		// RuleConfigWrapper.flushGroup(rule.getXmlPath(), groupId);
		// // 修改
		// ruleXMLService.saveDocument(document, rule.getXmlPath());

		try {
			saveAndFlushGroup(document, rule.getXmlPath(), groupId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}
		// 查询条件,//ruleEngine/rules/rule的id
		String qgroupId = request.getParameter("qgroupId");
		String qdescription = request.getParameter("qdescription");
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		ActionForward forward = mapping.findForward("forwardDetailRuleGroup");
		String path = forward.getPath() + "&id=" + id + "&currPage=" + currPage
				+ "&qgroupId=" + groupId + "&qdescription=" + qdescription;
		return new ActionForward(path, false);
	}

	/**
	 * 添加规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addRule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// spring 中配置的规则id
		String id = request.getParameter("id");

		String ruleId = request.getParameter("ruleId");
		// 名称
		String className = request.getParameter("className");
		// 描述
		String description = request.getParameter("description");

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");

		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());

		// 判断添加的groupId是否已存在
		if (ruleXMLService.isRuleExist(ruleId, document)) {
			// TODO 转向页面
			response.getWriter().println("The Rule Id(" + ruleId + " is Exist");
			return null;
		}

		// 获取groupId的element
		Element element = (Element) ruleXMLService.findRules(document);

		// 组内新增规则
		Element ruleElement = element.addElement("rule");
		ruleElement.addAttribute("className", className);
		ruleElement.addAttribute("id", ruleId);
		ruleElement.addAttribute("description", description);
		try {
			saveAndFlushRule(document, rule.getXmlPath(), ruleId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}
		// 查询条件,//ruleEngine/rules/rule的id
		String qid = request.getParameter("qid");
		String qdescription = request.getParameter("qdescription");
		String qclassName = request.getParameter("qclassName");

		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		// 返回详细页面
		ActionForward forward = mapping.findForward("forwardDetailRule");

		String path = forward.getPath() + "&id=" + id + "&currPage=" + currPage
				+ "&qid=" + ruleId + "&qdescription=" + qdescription
				+ "&qclassName=" + qclassName;
		return new ActionForward(path, false);

	}

	/**
	 * 删除分组
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 规则ID，用于取xml path
		String id = request.getParameter("id");
		// 规则路由id
		String groupId = request.getParameter("groupId");

		// TODO 应该为不用
		// // 规则ID
		// String groupRuleId = request.getParameter("ruleId");
		//
		// if (groupRuleId == null || "".equals(groupRuleId)) {
		// // TODO 修改转向页面
		// response.getWriter().println("Group Rule Id is null or ''");
		// return null;
		// }

		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());

		// 获取groupId的element
		Element element = (Element) ruleXMLService.findGroups(document);

		for (Iterator it = element.elementIterator("group"); it.hasNext();) {
			Element groupElement = (Element) it.next();
			if (groupId.equals(groupElement.attributeValue("id"))) {
				element.remove(groupElement);
			}
		}

		// // 修改
		// ruleXMLService.saveDocument(document, rule.getXmlPath());
		// // 更新路由分组缓存
		// RuleConfigWrapper.flushGroup(rule.getXmlPath(), groupId);
		try {
			saveAndFlushGroup(document, rule.getXmlPath(), groupId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}
		// 查询条件,//ruleEngine/rules/rule的id
		String qgroupId = request.getParameter("qgroupId");
		String qdescription = request.getParameter("qdescription");
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		ActionForward forward = mapping.findForward("forwardDetailRuleGroup");
		String path = forward.getPath() + "&id=" + id + "&currPage=" + currPage
				+ "&qgroupId=" + qgroupId + "&qdescription=" + qdescription;
		return new ActionForward(path, false);
	}

	/**
	 * 删除规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delRule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 规则ID，用于取xml path
		String id = request.getParameter("id");

		// 规则ID
		String ruleId = request.getParameter("ruleId");

		if (ruleId == null || "".equals(ruleId)) {
			// TODO 修改转向页面
			response.getWriter().println("Group Rule Id is null or ''");
		}
		// 规则参数service
		IRuleAttributesService ruleAttributesService = (IRuleAttributesService) getBean("ruleAttributesService");
		// 通过id取规则
		Rule rule = ruleAttributesService.findRuleById(id);

		// 取documentFactory bean
		IRuleXMLDom4jDocumentFactoryService dfs = (IRuleXMLDom4jDocumentFactoryService) getBean("ruleXMLDom4jDocumentFactoryService");
		// 取xml操作service
		IRuleXMLService ruleXMLService = (IRuleXMLService) getBean("ruleXMLService");
		// 通过xmlPath取document
		Document document = dfs.getDocument(rule.getXmlPath());

		// 获取groupId的element
		Element element = (Element) ruleXMLService.findRules(document);

		for (Iterator it = element.elementIterator("rule"); it.hasNext();) {
			Element ruleElement = (Element) it.next();
			if (ruleId.equals(ruleElement.attributeValue("id"))) {
				element.remove(ruleElement);
			}
		}

		// // 修改
		// ruleXMLService.saveDocument(document, rule.getXmlPath());
		// // 更新路由分组缓存
		// RuleConfigWrapper.flushRule(rule.getXmlPath(), ruleId);

		try {
			saveAndFlushRule(document, rule.getXmlPath(), ruleId);
		} catch (RuleToolXMLException e) {
			response.getWriter().println("save error");
			return null;
		}
		// 查询条件,//ruleEngine/rules/rule的id
		String qid = request.getParameter("qid");
		String qdescription = request.getParameter("qdescription");
		String qclassName = request.getParameter("qclassName");
		Integer currPage = RequestUtils.getIntParameter(request, "currPage");
		ActionForward forward = mapping.findForward("forwardDetailRule");
		String path = forward.getPath() + "&id=" + id + "&currPage=" + currPage
				+ "&qid=" + qid + "&qdescription=" + qdescription
				+ "&qclassName=" + qclassName;
		return new ActionForward(path, false);
	}

	/**
	 * 字典关联菜单，输入，输出参数com.boco.ClassA列出所有method
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward relateParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		// 类名
		String className = request.getParameter("className");
		// 对应类的所有方法

		List list = null;
		try {
			list = ClassHelper.getMethodsForClass(className);
		} catch (RuleException e) {

		}

		StringBuffer str = new StringBuffer("<methods>");

		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				LabelBean lb = (LabelBean) it.next();
				str.append("<method name='" + lb.getName() + "' " + "value='"
						+ lb.getValue() + "'/>");
			}
		}

		str.append("</methods>");
		response.setContentType("text/xml");
		response.getWriter().print(str.toString());
		return null;
	}

	/**
	 * 检查表达式
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkExpression(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String expression = RequestUtils.getStringParameter(request,
				"expression");
		OgnlExpressionService oes = OgnlExpressionService.create(null);
		boolean isValadated = oes.checkExpression(expression);

		response.setContentType("text/html;charset=UTF-8");

		response.setContentType("text/xml");
		// 表达式是否有效
		response.getWriter().print(isValadated);
		return null;
	}

}
