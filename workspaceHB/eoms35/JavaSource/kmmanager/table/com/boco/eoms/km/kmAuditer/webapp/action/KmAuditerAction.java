package com.boco.eoms.km.kmAuditer.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditer.util.KmAuditerConstants;
import com.boco.eoms.km.kmAuditer.webapp.form.KmAuditerForm;
import com.boco.eoms.km.table.mgr.KmTableColumnMgr;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;

/**
 * <p>
 * Title:知识管理审核人配置表
 * </p>
 * <p>
 * Description:知识管理审核人配置表
 * </p>
 * <p>
 * Wed Apr 29 15:46:36 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 戴志刚
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmAuditerAction extends BaseAction {
 
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
	 * 新增知识管理审核人配置表
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
    	// 读取：可以被使用的模型列表
		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
		List generalList = kmTableGeneralMgr.getOpenKmTableGenerals();
		request.setAttribute("KmTableGeneralList", generalList);

		//查询已存在的记录作为页面校验
		KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
		List auditerList = kmAuditerMgr.getKmAuditers();
		String themeIdStr = "";
		for(int i=0;i<auditerList.size();i++){
			themeIdStr = "'"+((KmAuditer)auditerList.get(i)).getThemeId()+"',";
		}
		request.setAttribute("themeIdStr", themeIdStr);
		// 判断：是否有可以被使用的模型，如果没有则跳转到提示页面
		if (generalList.size() > 0) {
			// 读取：模型的ID
			String TABLE_ID = StaticMethod.null2String(request.getParameter("TABLE_ID"));
			// 定义：模型绑定的分类
			String THEME_ID = null;

			// 判断：是否选择了某类模型
			if (TABLE_ID.equals("")) {
				// 如果用户没有选择模型，则默认初始化一个
				KmTableGeneral kmTableGeneral = (KmTableGeneral) generalList.get(0);
				TABLE_ID = kmTableGeneral.getId();
				THEME_ID = kmTableGeneral.getThemeId();
			} else {
				// 如果用户选择了模型，则读取用户选择的模型的定义
				KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneral(TABLE_ID);
				THEME_ID = kmTableGeneral.getThemeId();
			}

			// 设置：用户默认选择的模型ID
			request.setAttribute("TABLE_ID", TABLE_ID);

			// 读取：模型绑定的分类
			KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
			KmTableTheme tableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(THEME_ID);
			request.setAttribute("KmTableTheme", tableTheme);

		}
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改知识管理审核人配置表
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
		KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		if(id.equals("")){
			id = StaticMethod.nullObject2String(request.getAttribute("id"));
		}
		KmAuditer kmAuditer = kmAuditerMgr.getKmAuditer(id);
		KmAuditerForm kmAuditerForm = (KmAuditerForm) convert(kmAuditer);
    	String auditType = kmAuditer.getAuditType();
    	request.setAttribute("auditType", auditType);
		updateFormBean(mapping, request, kmAuditerForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存知识管理审核人配置表
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
		KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
		KmAuditerForm kmAuditerForm = (KmAuditerForm) form;
		boolean isNew = (null == kmAuditerForm.getId() || "".equals(kmAuditerForm.getId()));
		KmAuditer kmAuditer = (KmAuditer) convert(kmAuditerForm);
    	String date = StaticMethod.getLocalString();
    	kmAuditer.setCreateTime(date);
		if (isNew) {
			kmAuditerMgr.saveKmAuditer(kmAuditer);
		} else {
			kmAuditerMgr.saveKmAuditer(kmAuditer);
		}
		request.setAttribute("id",kmAuditer.getId());
		return edit(mapping, kmAuditerForm, request, response);
	}
	
	/**
	 * 删除知识管理审核人配置表
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
		KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmAuditerMgr.removeKmAuditer(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示知识管理审核人配置表列表
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
				KmAuditerConstants.KMAUDITER_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
		Map map = (Map) kmAuditerMgr.getKmAuditers(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmAuditerConstants.KMAUDITER_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示知识管理审核人配置表列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
			Map map = (Map) kmAuditerMgr.getKmAuditers(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmAuditer kmAuditer = new KmAuditer();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				kmAuditer = (KmAuditer) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/kmAuditer/kmAuditers.do?method=edit&id="
						+ kmAuditer.getId() + "' target='_blank'>"
						+ display name for list + "</a>");
				entry.setSummary(summary);
				entry.setContent(content);
				entry.setLanguage(language);
				entry.setText(text);
				entry.setRights(tights);
				
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
				entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
				Person person = entry.addAuthor(userId);
				person.setName(userName);
			}
			
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
	*/


	/**
	 * 取得某用户所有大角色和子角色，返回JSON
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void xgetRoleAndSubRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String id = StaticMethod.null2String(request.getParameter("id"));
		String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
		String node = StaticMethod.null2String(request.getParameter("node"));
//		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//		.getSession().getAttribute("sessionform");
//		String userId = sessionform.getUserid();
		ITawSystemUserRefRoleManager userRefRoleMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
		ITawSystemSubRoleManager subRoleManager = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		JSONArray jsonRoot = new JSONArray();
		if(node.equals("-1")){
			TawSystemSubRole subRole = subRoleManager.getTawSystemSubRole(id);
			JSONObject jitem = new JSONObject();
			jitem.put("id", subRole.getId());
			jitem.put("text", subRole.getSubRoleName());
			jitem.put("name", subRole.getSubRoleName());
			jitem.put("iconCls", "subrole");
			jitem.put("nodeType","subrole");
			jitem.put("leaf",false);
			jsonRoot.put(jitem);
		}else {
			List userList =  userRefRoleMgr.getUserbyroleid(node);
			for (Iterator iter = userList.iterator(); iter.hasNext();) {
				JSONObject jitem = new JSONObject();
				TawSystemUser tawSystemUser = (TawSystemUser) iter.next();
				jitem.put("id", tawSystemUser.getUserid());
				jitem.put("text", tawSystemUser.getUsername());
				jitem.put("name", tawSystemUser.getUsername());
				jitem.put("iconCls", "user");
				jitem.put("nodeType","user");
				jitem.put("leaf",true);
				jsonRoot.put(jitem);
				
			}
		}
		JSONUtil.print(response, jsonRoot.toString());
	}

	/**
	 * 角色树
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getRoleTreeNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String id = StaticMethod.null2String(request.getParameter("id"));
		String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
		String node = StaticMethod.null2String(request.getParameter("node"));
		if(node.equals("-1")){
			node = "1";
		}
		JSONArray jsonRoot = new JSONArray();

		ITawSystemRoleManager mgr = (ITawSystemRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemRoleManager");
		ITawSystemSubRoleManager subrolemgr = (ITawSystemSubRoleManager) ApplicationContextHolder
				.getInstance().getBean("ItawSystemSubRoleManager");
		// TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
		List rolelist = null;
		try {
			if(nodeType.equals("role")){
			rolelist = (ArrayList) mgr.getChildRoleByRoleIdPirv(Long
					.parseLong(node));
			if (rolelist != null) {
				String assignroleid = "";
				for (int i = 0; i < rolelist.size(); i++) {
					JSONObject jitem = new JSONObject();
					TawSystemRole sysrole = (TawSystemRole) rolelist.get(i);
					assignroleid = String.valueOf(sysrole.getRoleId());
					jitem.put("id", assignroleid);
					jitem.put("text", sysrole.getRoleName());
					jitem.put(UIConstants.JSON_NODETYPE, "role");
					// jitem.put("hasAsg",
					// assignbo.hasAssign(assignroleid,"role"));
					jitem.put("allowEdit", false);
					jsonRoot.put(jitem);
				}
				List subrolelist = null;
				subrolelist = (ArrayList) subrolemgr.getTawSystemSubRoles(Long
						.parseLong(node));
				for (int i = 0; i < subrolelist.size(); i++) {
					JSONObject jitem = new JSONObject();
					TawSystemSubRole syssubrole = (TawSystemSubRole) subrolelist
							.get(i);
					String subroleid = String.valueOf(syssubrole.getId());
					// boolean hasAsg = assignbo.hasAssign(subroleid,"subrole");
					String subRoleName = syssubrole.getSubRoleName();
					jitem.put("id", subroleid);
					jitem.put("text", subRoleName);
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "subrole");
					// jitem.put("hasAsg", hasAsg);
					jitem.put("allowEdit", true);
					jitem.put("leaf", true);
					jsonRoot.put(jitem);
				}
			}
			}else if(nodeType.equals("subrole")){
				List subrolelist = (ArrayList) subrolemgr.getTawSystemSubRoles(Long.parseLong(node));
				for (int i = 0; i < subrolelist.size(); i++) {
					JSONObject jitem = new JSONObject();
					TawSystemSubRole syssubrole = (TawSystemSubRole) subrolelist
							.get(i);
					String subroleid = String.valueOf(syssubrole.getId());
					// boolean hasAsg = assignbo.hasAssign(subroleid,"subrole");
					String subRoleName = syssubrole.getSubRoleName();
					jitem.put("id", subroleid);
					jitem.put("text", subRoleName);
					jitem.put(UIConstants.JSON_NODETYPE, "subrole");
					jitem.put("iconCls", "subrole");
					// jitem.put("hasAsg", hasAsg);
					jitem.put("allowEdit", true);
					jitem.put("leaf", true);
					jsonRoot.put(jitem);
				}
			}
		} catch (Exception ex) {
			BocoLog.error(this, "生成权限使用的大角色和小角色树图时报错：" + ex);
		}
		
		JSONUtil.print(response, jsonRoot.toString());
	}
}