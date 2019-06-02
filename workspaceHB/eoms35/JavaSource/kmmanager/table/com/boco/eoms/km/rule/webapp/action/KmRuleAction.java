package com.boco.eoms.km.rule.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Feed;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.km.rule.mgr.KmRuleMgr;
import com.boco.eoms.km.rule.model.KmRule;
import com.boco.eoms.km.rule.util.KmRuleConstants;
import com.boco.eoms.km.rule.webapp.form.KmRuleForm;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.model.KmTableColumn;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.util.KmTableUtil;

/**
 * <p>
 * Title:规则库
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmRuleAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return search(mapping, form, request, response);
	}
 	
 	/**
	 * 新增规则库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
     	
    	String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
    	KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
    	IDictService service = (IDictService) ApplicationContextHolder
        .getInstance().getBean("DictService");
    	KmTableGeneral kmTableGeneral=kmTableGeneralMgr.getKmTableGeneral(nodeId);
    	// 取下级节点
		List list = kmTableGeneralMgr.getNextLevelKmTableGenerals(nodeId);
		
		JSONArray root = new JSONArray();
		
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();	
			JSONObject item = new JSONObject();
			  item.put("id", kmTableColumn.getColName());
			  item.put("name", kmTableColumn.getColChname());
			  String colType=(String) service.itemId2name(Util.constituteDictId("dict-kmmanager", "colType"), kmTableColumn.getColType());
			  item.put("colType", colType);
			  String typeValue=(String) service.itemId2description(Util.constituteDictId("dict-kmmanager", "colType"), kmTableColumn.getColType());
			  item.put("typeValue", typeValue);
		    root.put(item);
		}
		int length=KmRuleConstants.idArray.length;
		for(int i=0;i<length;i++){
			JSONObject item = new JSONObject();
			KmTableColumn kmTableColumn=new KmTableColumn();
			kmTableColumn.setColName(KmRuleConstants.idArray[i]);
			kmTableColumn.setColChname(KmRuleConstants.nameArray[i]);
			list.add(kmTableColumn);
			  item.put("id", KmRuleConstants.idArray[i]);
			  item.put("name", KmRuleConstants.nameArray[i]);
			  item.put("colType", KmRuleConstants.typeArray[i]);
			  item.put("typeValue", KmRuleConstants.typeValueArray[i]);
		      root.put(item);			
		}
		request.setAttribute("data", root);	
		request.setAttribute("tableName", kmTableGeneral.getTableName());
		request.setAttribute("list", list);
		request.setAttribute("nodeId", nodeId);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改规则库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmRule kmRule = kmRuleMgr.getKmRule(id);
		KmRuleForm kmRuleForm = (KmRuleForm) convert(kmRule);		
		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
		IDictService service = (IDictService) ApplicationContextHolder
        .getInstance().getBean("DictService");
		KmTableGeneral kmTableGeneral=kmTableGeneralMgr.getKmTableGeneral(kmRuleForm.getContentId());
		// 取下级节点
		List list = kmTableGeneralMgr.getNextLevelKmTableGenerals(kmRuleForm.getContentId());			
		JSONArray root = new JSONArray();			
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableColumn kmTableColumn = (KmTableColumn) nodeIter.next();	
			JSONObject item = new JSONObject();			
		    item.put("id", kmTableColumn.getColName());
		    item.put("name", kmTableColumn.getColChname());
		    String colType=(String) service.itemId2name(Util.constituteDictId("dict-kmmanager", "colType"), kmTableColumn.getColType());
			item.put("colType", colType);
			String typeValue=(String) service.itemId2description(Util.constituteDictId("dict-kmmanager", "colType"), kmTableColumn.getColType());
			item.put("typeValue", typeValue);
			root.put(item);
		}
		int length=KmRuleConstants.idArray.length;
		for(int i=0;i<length;i++){
			JSONObject item = new JSONObject();
			KmTableColumn kmTableColumn=new KmTableColumn();
			kmTableColumn.setColName(KmRuleConstants.idArray[i]);
			kmTableColumn.setColChname(KmRuleConstants.nameArray[i]);
			list.add(kmTableColumn);
			  item.put("id", KmRuleConstants.idArray[i]);
			  item.put("name", KmRuleConstants.nameArray[i]);
			  item.put("colType", KmRuleConstants.typeArray[i]);
			  item.put("typeValue", KmRuleConstants.typeValueArray[i]);
		      root.put(item);			
		}
		request.setAttribute("data", root);
		request.setAttribute("tableName", kmTableGeneral.getTableName());
		request.setAttribute("list", list);	
		updateFormBean(mapping, request, kmRuleForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存规则库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		KmRuleForm kmRuleForm = (KmRuleForm) form;
		boolean isNew = (null == kmRuleForm.getId() || "".equals(kmRuleForm.getId()));
		KmRule kmRule = (KmRule) convert(kmRuleForm);
		Date createTime = StaticMethod.getLocalTime();	
		kmRule.setCreateTime(createTime);				
		TawSystemSessionForm tawSystemSessionForm=this.getUser(request);
		kmRule.setCreateDept(tawSystemSessionForm.getDeptid());
		kmRule.setCreateUser(tawSystemSessionForm.getUserid());
		if (isNew) {
			String nodeId=request.getParameter("nodeId");
			kmRule.setContentId(nodeId);
			kmRuleMgr.saveKmRule(kmRule);
		} else {
			kmRuleMgr.saveKmRule(kmRule);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除规则库
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward remove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmRuleMgr.removeKmRule(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 检查数据库脚本
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkSql(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		String sql = StaticMethod.null2String(request.getParameter("para"));
		String msg=kmRuleMgr.checkSql(sql);		
		response.setContentType("text/xml;charset=utf-8");  //解析为XML档   
		response.setHeader("Cache-Control","no-cache"); 	
		response.getWriter().println("<root>");   
		response.getWriter().println("<msg>");   
		response.getWriter().println("<content>"+msg+"</content>"); 	 
		response.getWriter().println("</msg>"); 		 
		response.getWriter().println("</root>");   

		return null;
	}
	
	
	/**
	 * 分页显示规则库列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmRuleConstants.KMRULE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		Map map = (Map) kmRuleMgr.getKmRules(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmRuleConstants.KMRULE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		// 读取：模型列表
		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
		List generalList = kmTableGeneralMgr.getOpenKmTableGenerals();
		request.setAttribute("KmTableGeneralList", generalList);
		return mapping.findForward("list");
	}
	
	/**
	 * 根据查询条件分页显示模块信息表列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmRuleConstants.KMRULE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmRuleForm kmRuleForm = (KmRuleForm) form;
		String whereStr=KmTableUtil.getQueryCondition(kmRuleForm);
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		Map map = (Map) kmRuleMgr.getKmRules(pageIndex, pageSize, whereStr);
		
		List list = (List) map.get("result");
		request.setAttribute(KmRuleConstants.KMRULE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("kmRuleForm", kmRuleForm);
		// 读取：模型列表
		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
		List generalList = kmTableGeneralMgr.getOpenKmTableGenerals();		
		request.setAttribute("KmTableGeneralList", generalList);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示规则库列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
			Map map = (Map) kmRuleMgr.getKmRules(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmRule kmRule = new KmRule();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmRule = (KmRule) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmRule/kmRules.do?method=edit&id="
//						+ kmRule.getId() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}
			
			// 每页显示条数
			feed.setText(map.get("total").toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 规则库树页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("tree");
	}
	
	/**
	 * 生成规则库树JSON数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		JSONArray jsonRoot = new JSONArray();
		KmRuleMgr kmRuleMgr = (KmRuleMgr) getBean("kmRuleMgr");
		// 取下级节点
		List list = kmRuleMgr.getNextLevelKmRules(nodeId);
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableGeneral kmTableGeneral = (KmTableGeneral) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmTableGeneral.getId());
			// TODO 添加节点名称
			jitem.put("text", kmTableGeneral.getTableChname());
			// 设置右键菜单
			jitem.put("allowChild", true);
			jitem.put("allowEdit", true);
			jitem.put("allowDelete", true);
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			// 设置是否为叶子节点
			boolean leafFlag = true;			
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			jitem.put("qtip", kmTableGeneral.getTableChname());
			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}

}