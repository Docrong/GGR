package com.boco.eoms.commons.system.reported.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.commons.system.reported.service.TawSystemReportedManager;
import com.boco.eoms.commons.system.reported.util.TawSystemReportedUtil;
import com.boco.eoms.commons.system.reported.model.TawSystemReported;
import com.boco.eoms.commons.system.reported.model.TawSystemReportedUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.reported.webapp.form.TawSystemReportedForm;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

public final class TawSystemReportedAction extends BaseAction {

	private TawSystemSessionForm saveSessionBeanForm;

	/**
	 * 转向新增、修改页面
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		TawSystemReportedForm tawSystemReportedForm = (TawSystemReportedForm) form;
		TawSystemReportedManager mgr = (TawSystemReportedManager) getBean("tawSystemReportedManager");
		if (tawSystemReportedForm.getId() != null) {
			TawSystemReported tawSystemReported = (TawSystemReported) convert(tawSystemReportedForm);
			tawSystemReported = mgr.getTawSystemReported(tawSystemReported
					.getId());
			tawSystemReportedForm = (TawSystemReportedForm) convert(tawSystemReported);
			updateFormBean(mapping, request, tawSystemReportedForm);
		}
		List list = mgr
				.getTawSystemReportedUsersByReportedId(tawSystemReportedForm
						.getId());
		String reportedOrg = this.TawSystemReportedUser2jsonStr(list);
		request.setAttribute("reportedOrg", reportedOrg);

		return mapping.findForward("edit");
	}

	/**
	 * 新增或修改
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		try {
			saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
					.getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TawSystemReportedForm tawSystemReportedForm = (TawSystemReportedForm) form;
		TawSystemReportedManager mgr = (TawSystemReportedManager) getBean("tawSystemReportedManager");
		TawSystemReported tawSystemReported = (TawSystemReported) convert(tawSystemReportedForm);
		List list = jsonOrg2Orgs(tawSystemReportedForm.getReportedOrg());
		tawSystemReported.setUserId(saveSessionBeanForm.getUserid());
		if(StaticMethod.null2String(tawSystemReported.getId()).equals("")){
			mgr.saveTawSystemReported(tawSystemReported, list);
		}else{
			mgr.updateTawSystemReported(tawSystemReported, list);
		}
		return mapping.findForward("success");
	}

	/**
	 * 删除信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		String id = request.getParameter("id");
		TawSystemReportedManager mgr = (TawSystemReportedManager) getBean("tawSystemReportedManager");
		mgr.removeTawSystemReported(id);
		return mapping.findForward("success");
	}

	/**
	 * 列表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'list' method");
		}
		try {
			saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
					.getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawSystemReportedUtil.TAWSYSTEMREPORTED_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String whereStr = " tawSystemReported.userId = '" + saveSessionBeanForm.getUserid() + "'";
		TawSystemReportedManager mgr = (TawSystemReportedManager) getBean("tawSystemReportedManager");
		ITawSystemUserManager userManager=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawSystemDeptManager deptManager=(ITawSystemDeptManager)getBean("ItawSystemDeptManager");
		ITawSystemRoleManager roleManager = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		Map map = mgr.getTawSystemReportedPage(pageIndex, pageSize, whereStr);
		List list = (List) map.get("result");
		ArrayList result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			TawSystemReported tawSystemReported = (TawSystemReported) list
					.get(i);
			List users = mgr.getTawSystemReportedUsersByReportedId(tawSystemReported.getId());
			String name = "";
			for (int j = 0; j < users.size(); j++) {
				TawSystemReportedUser tawSystemReportedUser = (TawSystemReportedUser) users
						.get(j);
				if (tawSystemReportedUser.getReportedOrgType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_USER)) {
					name = name
							+ userManager.getUserByuserid(tawSystemReportedUser
									.getReportedOrgId()).getUsername() + ",";
				} else if (tawSystemReportedUser.getReportedOrgType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
					name = name
							+ roleManager.getRoleNameById(Long
									.parseLong(tawSystemReportedUser
											.getReportedOrgId())) + "(角色),";
				} else if (tawSystemReportedUser.getReportedOrgType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_DEPT)) {
					name = name
							+ deptManager.getDeptinfobydeptid(
									tawSystemReportedUser.getReportedOrgId(),
									"0").getDeptName() + "(部门),";

				}
			}
			name = name.substring(0,name.length()-1);
			tawSystemReported.setReportedOrgName(name);
			result.add(tawSystemReported);
		}
		request.setAttribute(TawSystemReportedUtil.TAWSYSTEMREPORTED_LIST, result);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	/**
	 * 查询列表页面
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		try {
			saveSessionBeanForm = (TawSystemSessionForm) request.getSession()
					.getAttribute("sessionform");
			if (saveSessionBeanForm == null) {
				return mapping.findForward("timeout");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TawSystemReportedForm tawSystemReportedForm = (TawSystemReportedForm) form;
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				TawSystemReportedUtil.TAWSYSTEMREPORTED_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String whereStr = " tawSystemReported.userId = '" + saveSessionBeanForm.getUserid() + "'";
		if(!tawSystemReportedForm.getModelId().equals("")){
			whereStr += " and tawSystemReported.modelId = '" + tawSystemReportedForm.getModelId() + "'";
		}
		if(!tawSystemReportedForm.getFunctionId().equals("")){
			whereStr += " and tawSystemReported.functionId = '" + tawSystemReportedForm.getFunctionId() + "'";
		}
		if(!tawSystemReportedForm.getRemark().equals("")){
			whereStr += " and tawSystemReported.remark like '%" + tawSystemReportedForm.getRemark() + "%'";
		}
		TawSystemReportedManager mgr = (TawSystemReportedManager) getBean("tawSystemReportedManager");
		ITawSystemUserManager userManager=(ITawSystemUserManager)getBean("itawSystemUserManager");
		ITawSystemDeptManager deptManager=(ITawSystemDeptManager)getBean("ItawSystemDeptManager");
		ITawSystemRoleManager roleManager = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		Map map = mgr.getTawSystemReportedPage(pageIndex, pageSize, whereStr);
		List list = (List) map.get("result");
		ArrayList result = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			TawSystemReported tawSystemReported = (TawSystemReported) list
					.get(i);
			List users = mgr.getTawSystemReportedUsersByReportedId(tawSystemReported.getId());
			String name = "";
			for (int j = 0; j < users.size(); j++) {
				TawSystemReportedUser tawSystemReportedUser = (TawSystemReportedUser) users
						.get(j);
				if (tawSystemReportedUser.getReportedOrgType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_USER)) {
					name = name
							+ userManager.getUserByuserid(tawSystemReportedUser
									.getReportedOrgId()).getUsername() + ",";
				} else if (tawSystemReportedUser.getReportedOrgType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_ROLE)) {
					name = name
							+ roleManager.getRoleNameById(Long
									.parseLong(tawSystemReportedUser
											.getReportedOrgId())) + "(角色),";
				} else if (tawSystemReportedUser.getReportedOrgType().equals(
						StaticVariable.PRIV_ASSIGNTYPE_DEPT)) {
					name = name
							+ deptManager.getDeptinfobydeptid(
									tawSystemReportedUser.getReportedOrgId(),
									"0").getDeptName() + "(部门),";

				}
			}
			name = name.substring(0,name.length()-1);
			tawSystemReported.setReportedOrgName(name);
			result.add(tawSystemReported);
		}
		request.setAttribute(TawSystemReportedUtil.TAWSYSTEMREPORTED_LIST, result);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}

	/**
	 * 初始化查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("reportedSearch");
	}
	/**
	 * 从json中取发布组织列表
	 * 
	 * @param orgs
	 *            json串
	 * @return
	 */
	private List jsonOrg2Orgs(String orgs) {
		JSONArray jsonOrgs = JSONArray.fromString(orgs);
		List orgList = new ArrayList();
		for (Iterator it = jsonOrgs.iterator(); it.hasNext();) {
			JSONObject org = (JSONObject) it.next();
			// 发布组织id
			String id = org.getString(UIConstants.JSON_ID);
			// 节点类型
			String nodeType = org.getString(UIConstants.JSON_NODETYPE);
			// 限制发布范围
			if (TawSystemReportedUtil.ORG_DEPT.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(nodeType)) {
				// 写入orgList，供保存
				orgList.add(new TawSystemReportedUser(id,
						StaticVariable.PRIV_ASSIGNTYPE_DEPT));
			} else if (TawSystemReportedUtil.ORG_ROLE.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(nodeType)) {
				orgList.add(new TawSystemReportedUser(id,
						StaticVariable.PRIV_ASSIGNTYPE_ROLE));
			} else if (TawSystemReportedUtil.ORG_USER.equals(nodeType)
					|| StaticVariable.PRIV_ASSIGNTYPE_USER.equals(nodeType)) {
				orgList.add(new TawSystemReportedUser(id,
						StaticVariable.PRIV_ASSIGNTYPE_USER));
			}
		}
		return orgList;
	}

	/**
	 * 信息权限列表转换成json的字符串
	 * 
	 * @param threadPermimissionOrgs
	 *            信息权限列表
	 * @return 信息权限转换后的字符串
	 */
	private String TawSystemReportedUser2jsonStr(List TawSystemReportedUsers) {
		String jsonOrgs = "[]";
		// 构造信息发布范围json对象
		ITawSystemUserManager mgrUser = (ITawSystemUserManager) getBean("itawSystemUserManager");
		ITawSystemDeptManager mgrDept = (ITawSystemDeptManager) getBean("ItawSystemDeptManager");
		if (null != TawSystemReportedUsers && !TawSystemReportedUsers.isEmpty()) {
			JSONArray jsonRoot = new JSONArray();
			for (Iterator it = TawSystemReportedUsers.iterator(); it.hasNext();) {
				TawSystemReportedUser org = (TawSystemReportedUser) it.next();
				JSONObject item = new JSONObject();
				// 构造json对象
				item.put(UIConstants.JSON_ID, org.getReportedOrgId());
				// 判断发布对象类型
				int orgtype = StaticMethod.null2int(org.getReportedOrgType());
				String orgtypestr = "";
				String name = "";
				switch (orgtype) {
				case 1:
					orgtypestr = "dept";
					name = mgrDept.getDeptinfobydeptid(org.getReportedOrgId(),
							"0").getDeptName();
					break;
				case 2:
					orgtypestr = "user";
					name = mgrUser.getUserByuserid(org.getReportedOrgId())
							.getUsername();
					break;
				}
				item.put(UIConstants.JSON_NAME, name);
				item.put(UIConstants.JSON_NODETYPE, orgtypestr);
				jsonRoot.put(item);
			}
			jsonOrgs = jsonRoot.toString();
		}
		return jsonOrgs;
	}

}
