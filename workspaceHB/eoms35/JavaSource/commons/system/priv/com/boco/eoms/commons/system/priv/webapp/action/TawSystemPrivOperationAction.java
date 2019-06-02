package com.boco.eoms.commons.system.priv.webapp.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.service.ITawSystemPrivOperationManager;
import com.boco.eoms.commons.system.priv.util.PrivConstants;
import com.boco.eoms.commons.system.priv.webapp.form.TawSystemPrivOperationForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.bo.TawSystemTreeBo;

/**
 * Action class to handle CRUD on a TawSystemPrivOperation object
 * 
 * @struts.action name="tawSystemPrivOperationForm"
 *                path="/tawSystemPrivOperations" scope="request"
 *                validate="false" parameter="method" input="mainMenu"
 * @struts.action name="tawSystemPrivOperationForm"
 *                path="/editTawSystemPrivOperation" scope="request"
 *                validate="false" parameter="method" input="list"
 * @struts.action name="tawSystemPrivOperationForm"
 *                path="/saveTawSystemPrivOperation" scope="request"
 *                validate="true" parameter="method" input="edit"
 * @struts.action-set-property property="cancellable" value="true"
 * @struts.action-forward name="edit"
 *                        path="/WEB-INF/pages/tawSystemPrivOperation/tawSystemPrivOperationForm.jsp"
 * @struts.action-forward name="list"
 *                        path="/WEB-INF/pages/tawSystemPrivOperation/tawSystemPrivOperationList.jsp"
 * @struts.action-forward name="search" path="/tawSystemPrivOperations.html"
 *                        redirect="true"
 */
public final class TawSystemPrivOperationAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("search");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// Extract attributes and parameters we will need
		ActionMessages messages = new ActionMessages();
		TawSystemPrivOperationForm tawSystemPrivOperationForm = (TawSystemPrivOperationForm) form;
		boolean isNew = ("".equals(tawSystemPrivOperationForm.getId()) || tawSystemPrivOperationForm
				.getId() == null);
		String requestMode = request.getParameter("requestMode");
		ITawSystemPrivOperationManager mgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
		TawSystemPrivOperation tawSystemPrivOperation = (TawSystemPrivOperation) convert(tawSystemPrivOperationForm);
		if (tawSystemPrivOperationForm.getIsApp().equals(
				StaticVariable.PRIV_TYPE_BUTTON)) {
			tawSystemPrivOperation.setDeleted(StaticVariable.PRIV_NO_SHOW);
		}
		/** add by hsl 2007-5-24 begin* */
		/** 为该对象重新设置CODE值* */
		if (isNew) {
			tawSystemPrivOperation.setDeleted(Constants.NOT_DELETED_FLAG);
			tawSystemPrivOperation.setHided(PrivConstants.MENU_NO_HIDDEN);
			String codeValue = "";
			String _strParentId = tawSystemPrivOperation.getParentcode();
			if ("1".equals(_strParentId) || "".equals(_strParentId)) {
				String _strFatherId = "-1";
				codeValue = mgr.getCodeValueForModify(_strFatherId);
				tawSystemPrivOperation.setParentcode("-1");
			} else {
				codeValue = mgr
						.getCodeValueForModify(tawSystemPrivOperationForm
								.getParentcode());
			}

			if (codeValue.endsWith("99")) {
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"该模块的容量已经饱和，请与系统管理员联系！"));

				// save messages in session to survive a redirect
				saveMessages(request.getSession(), messages);

				return mapping.findForward("search");
			}
			tawSystemPrivOperation.setCode(codeValue);
		}
		/** add by hsl 2007-5-24 end* */

		mgr.saveTawSystemPrivOperation(tawSystemPrivOperation);

		if (requestMode.equalsIgnoreCase("ajax")) {
			return null;
		} else {
			request.setAttribute("parentId", tawSystemPrivOperation
					.getParentcode());

			// add success messages
			if (isNew) {

				// save messages in session to survive a redirect
				saveMessages(request.getSession(), messages);

				return mapping.findForward("search");
			} else {

				saveMessages(request, messages);

				return mapping.findForward("search");
			}
		}
	}

	/**
	 * 直接得到一级和二级菜单
	 * 
	 */
	public String module(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSystemPrivOperationManager _objOptMgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
		List list = new ArrayList();
		list = _objOptMgr.getModules(StaticVariable.ROOT_NODE,
				StaticVariable.UNSTRSELETED);

		JSONArray jsonRoot = new JSONArray();

		for (Iterator rowIt = list.iterator(); rowIt.hasNext();) {
			TawSystemPrivOperation _objOneOpt = (TawSystemPrivOperation) rowIt
					.next();

			JSONObject jitem = new JSONObject();
			jitem.put("id", "Module-" + _objOneOpt.getCode());
			jitem.put("text", _objOneOpt.getName());
			jitem.put("url", _objOneOpt.getUrl());

			// 二级菜单
			JSONArray itemsArray = new JSONArray();
			List sublist = new ArrayList();
			sublist = _objOptMgr.getModules(_objOneOpt.getCode(),
					StaticVariable.UNSTRSELETED);

			if (sublist.size() > 0) {
				for (Iterator subrowIt = sublist.iterator(); subrowIt.hasNext();) {
					TawSystemPrivOperation subprivitem = (TawSystemPrivOperation) subrowIt
							.next();
					JSONObject subjitem = new JSONObject();
					subjitem.put("id", "Module-" + subprivitem.getCode());
					subjitem.put("text", subprivitem.getName());
					subjitem.put("url", subprivitem.getUrl());
					if (subprivitem.isLeaf()) {
						subjitem.put("leaf", subprivitem.isLeaf());
					}

					itemsArray.put(subjitem);
				}

				jitem.put("sub", itemsArray);

			}
			jsonRoot.put(jitem);
		}

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 根据父节点的id得到所有子节点的JSON数据 mios 070723 显示菜单管理
	 */
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");
		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		boolean isadmin = sessionform.isAdmin();
		JSONArray jsonRoot;
		if (isadmin) {
			// TODO 在菜单管理中显示所有菜单（包括隐藏）
			// jsonRoot = treebo.getPrivOperationTree(nodeId, "",
			// StaticVariable.UNSTRSELETED);
			jsonRoot = treebo.getPrivOperationTree(nodeId, "",
					StaticVariable.ALLDELETED);
		} else {
			jsonRoot = treebo.getUserPrivTree(nodeId, sessionform.getUserid());
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 根据父节点的id得到所有子节点的JSON数据 mios 070723 显示菜单方案
	 */
	public String getNodes4privmenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");
		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		boolean isadmin = sessionform.isAdmin();
		JSONArray jsonRoot;
		if (isadmin) {

			jsonRoot = treebo.getPrivOperationTree(nodeId, "",
					StaticVariable.UNSTRSELETED);
		} else {
			jsonRoot = treebo.getUserPrivTree(nodeId, sessionform.getUserid());
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 根据父节点的id得到所有子节点的JSON数据 mios 070723
	 */
	public String getExitsNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = request.getParameter("node");
		TawSystemTreeBo treebo = TawSystemTreeBo.getInstance();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		boolean isadmin = sessionform.isAdmin();
		JSONArray jsonRoot;
		if (isadmin) {
			jsonRoot = treebo.getPrivOperationTree(nodeId, "",
					StaticVariable.UNSTRSELETED);
		} else {
			jsonRoot = treebo.getUserPrivTree(nodeId, sessionform.getUserid());
		}
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 根据模块或功能的编码，删除该对象。
	 * 页面访问：http://hostname:port/webappname/tawSystemPrivOperations.html?method=xdelete&id=inputvalue
	 */
	public String xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");
		ITawSystemPrivOperationManager _objOptMgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
		TawSystemPrivOperation _objOneOpt = (TawSystemPrivOperation) _objOptMgr
				.getTawSystemPrivOperation(_strId);

		_objOptMgr.removeTawSystemPrivOperation(_strId);

		return null;
	}

	/**
	 * ajax请求获取某节点的详细信息。 mios 070724
	 */
	public String xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _strId = request.getParameter("id");
		ITawSystemPrivOperationManager _objOptMgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");
		TawSystemPrivOperation _objOneOpt = (TawSystemPrivOperation) _objOptMgr
				.getTawSystemPrivOperation(_strId);

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(_objOneOpt);

		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * ajax请求修改某节点的详细信息。 mios 070724
	 */
	public ActionForward xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemPrivOperationForm tawSystemPrivOperationForm = (TawSystemPrivOperationForm) form;
		// 设置未删除标记 edit by liqiuye 20080905 页面上已经可以正确传递参数到后台，不用设置
		tawSystemPrivOperationForm.setDeleted(Constants.NOT_DELETED_FLAG);
		ITawSystemPrivOperationManager mgr = (ITawSystemPrivOperationManager) getBean("ItawSystemPrivOperationManager");

		if (tawSystemPrivOperationForm.getId() != null) {
			TawSystemPrivOperation _objOneOpt = (TawSystemPrivOperation) convert(tawSystemPrivOperationForm);
			// ITawSystemPrivUserAssignManager userassignmgr =
			// (ITawSystemPrivUserAssignManager)
			// getBean("ItawSystemPrivUserAssignManager");
			// // if (userassignmgr.isExitsPrivid(_objOneOpt.getCode())) {
			// // // update by jiangcheng 同步更新用户菜单选项 原来只更新是否隐藏 现在同步更新 菜单名称 菜单类别
			// // // 是否隐藏 url
			// // //
			// userassignmgr.updateUserAssignByPrivid(_objOneOpt.getCode(),
			// // // _objOneOpt.getDeleted());
			// // userassignmgr.updateUserAssignByPrivid(_objOneOpt);
			// // }
			mgr.saveTawSystemPrivOperation(_objOneOpt);
		}

		// tawSystemPrivOperationForm.setParentName(_strParentName);
		return null;
	}
}
