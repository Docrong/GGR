package com.boco.eoms.commons.system.myinfo.webapp.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;

import com.boco.eoms.commons.system.myinfo.service.ITawSystemMyinfoManager;
import com.boco.eoms.commons.system.myinfo.webapp.form.TawSystemMyinfoForm;

import com.boco.eoms.workbench.netdisk.webapp.util.FilePathProcessor;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2008-7-30 10:04:17
 * </p>
 * 
 * @author 龚玉峰
 * @version 1.0
 */
public final class TawSystemMyinfoAction extends BaseAction {

	// 撤销
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	// 删除 返回查询页面
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 根据人员userid得到这个人员的所有角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward getRoleByUserid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TawSystemMyinfoForm tawSystemMyinfoForm = (TawSystemMyinfoForm) form;
		String userid = "";
		List list = new ArrayList();
		ITawSystemMyinfoManager mgr = (ITawSystemMyinfoManager) getBean("tawSystemMyinfoMgr");
		try {
			userid = tawSystemMyinfoForm.getUserid();
			list = mgr.getRoleByUserid(userid);
			request.setAttribute("rolebyuserid", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("edit");
	}

	/**
	 * 
	 * @see 根据人员userid得到这个人员的所有部门
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDeptByUserid(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		TawSystemMyinfoForm tawSystemMyinfoForm = (TawSystemMyinfoForm) form;
		String userid = "";
		List list = new ArrayList();
		ITawSystemMyinfoManager mgr = (ITawSystemMyinfoManager) getBean("tawSystemMyinfoMgr");
		try {
			userid = tawSystemMyinfoForm.getUserid();
			list = mgr.getDeptByUserid(userid);
			request.setAttribute("deptbyuserid", list);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", "错误");
			return mapping.findForward("false");
		}

		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 为这个人员添加角色
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemMyinfoForm tawSystemMyinfoForm = (TawSystemMyinfoForm) form;
		ITawSystemMyinfoManager mgr = (ITawSystemMyinfoManager) getBean("tawSystemMyinfoMgr");
		String subRoleid = "";
		String userid = "";
		String roleType = "";
		String[] roleid;
		String roleidStr = "";

		try {

			subRoleid = tawSystemMyinfoForm.getSubRoleid();
			userid = tawSystemMyinfoForm.getUserid();
			roleType = tawSystemMyinfoForm.getRoleType();
			roleid = subRoleid.split(",");
			for (int i = 0; i < roleid.length; i++) {
				roleidStr = roleid[i];

				if (mgr.isExist(roleidStr, userid)) {
					return mapping.findForward("isExist");
				} else {
					mgr.addRole(subRoleid, userid, roleType);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("falseMessage", "错误");
			return mapping.findForward("false");
		}

		return mapping.findForward("list");

		// add success messages

	}

	/**
	 * 
	 * @see 为这个人员添加部门域
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemMyinfoForm tawSystemMyinfoForm = (TawSystemMyinfoForm) form;
		ITawSystemMyinfoManager mgr = (ITawSystemMyinfoManager) getBean("tawSystemMyinfoMgr");
		String userid="";
		String deptid= "";
		String[] deptArray ;
		String deptStr="";
		try {
			deptid = tawSystemMyinfoForm.getDeptid();
			userid = tawSystemMyinfoForm.getUserid();
			deptArray = deptid .split(",");
			for(int i = 0 ;i<deptArray.length;i++){
				deptStr = deptArray[i];
				if(mgr.isExistDept(deptStr, userid)){
					return mapping.findForward("isExistDept");
				}else{
					mgr.addDept(deptStr, userid);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("list");
	}

	/**
	 * 
	 * @see 删除选中的角色这时候该人员将不在用这个角色的权限
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward deleteRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemMyinfoForm tawSystemMyinfoForm = (TawSystemMyinfoForm) form;
		ITawSystemMyinfoManager mgr = (ITawSystemMyinfoManager) getBean("tawSystemMyinfoMgr");
		String subRoleid = "" ;
		String userid = "";

		try {
			userid = request.getParameter("userid");
			subRoleid = request.getParameter("subRoleidredio");
			mgr.deleteRole(subRoleid, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("searchList");
	}

	/**
	 * 
	 * @see 删除选中的部门这时候该人员将不在用这个部门的权限
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward deleteDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemMyinfoForm tawSystemMyinfoForm = (TawSystemMyinfoForm) form;
		ITawSystemMyinfoManager mgr = (ITawSystemMyinfoManager) getBean("tawSystemMyinfoMgr");
		String deptid = "" ;
		String userid = "";
		try {
			userid = request.getParameter("userid");
			deptid = request.getParameter("subRoleidredio");
			mgr.deleteDept(deptid, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("xSearchList");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
	}

	public ActionForward xgetRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String folderPath = request.getParameter("folderPath");
		request.setAttribute("folderPath", folderPath);
		return mapping.findForward("role");
	}

	public ActionForward xgetDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String folderPath = request.getParameter("folderPath");
		request.setAttribute("folderPath", folderPath);
		return mapping.findForward("dept");
	}

	/**
	 * 生成树JSON数据
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
		String nodeId = FilePathProcessor.recoverPath(request
				.getParameter("node"));

		JSONArray jsonRoot = new JSONArray();
		if ("-1".equals(nodeId)) { // 初始化三个节点
			jsonRoot = initNodes();
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private JSONArray initNodes() {
		JSONArray jsonRoot = new JSONArray();
		JSONObject role = new JSONObject();
		role.put("id", "1");
		role.put("text", "角色");
		role.put("nodeType", "1");
		role.put("allowChild", true);
		role.put("allowEdit", false);

		role.put("leaf", false);
		role.put("iconCls", "folder");
		JSONObject dept = new JSONObject();
		dept.put("id", "2");
		dept.put("text", "部门");
		dept.put("nodeType", "2");
		dept.put("allowChild", false);
		dept.put("allowEdit", true);

		dept.put("leaf", false);
		dept.put("iconCls", "folder");

		jsonRoot.put(role);
		jsonRoot.put(dept);

		return jsonRoot;
	}

}
