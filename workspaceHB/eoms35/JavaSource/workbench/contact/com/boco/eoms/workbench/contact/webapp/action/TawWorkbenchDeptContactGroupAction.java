package com.boco.eoms.workbench.contact.webapp.action;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContactGroup;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactGroupManager;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactGroupForm;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchDeptContactGroupForm;
 
/**
 * <p>
 * Title:部门通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jue 30, 2008 20:30:30 PM
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */

public final class TawWorkbenchDeptContactGroupAction extends BaseAction {
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// return mapping.findForward("search");
		return null;
	}

	/**
	 * 根据父节点的id得到�?有子节点的JSON数据
	 */
	public void getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	}

	/**
	 * ajax保存
	 */
	public ActionForward xsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TawWorkbenchDeptContactGroupForm tawWorkbenchDeptContactGroupForm = (TawWorkbenchDeptContactGroupForm) form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) getBean("ItawWorkbenchDeptContactGroupManager");
		TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = (TawWorkbenchDeptContactGroup) convert(tawWorkbenchDeptContactGroupForm);
		// 存储group_id 的数字

		tawWorkbenchDeptContactGroup.setUserId(userId);
		tawWorkbenchDeptContactGroup.setDeptId(sessionform.getDeptid());
		if (tawWorkbenchDeptContactGroupForm.getId() == null
				|| tawWorkbenchDeptContactGroupForm.getId().equals("")) {
			//

			tawWorkbenchDeptContactGroup.setGroupId(mgr.getMaxGroupId() + "");

			tawWorkbenchDeptContactGroup.setDeleted("0");
		} else {			
			tawWorkbenchDeptContactGroup.setDeleted("0");
			if (tawWorkbenchDeptContactGroupForm.getGroupId().equals("")) {
				String group_id = (String) request.getParameter("groupId");
				tawWorkbenchDeptContactGroup.setGroupId(group_id);
			}
		}
		mgr.saveTawWorkbenchDeptContactGroup(tawWorkbenchDeptContactGroup);
		return mapping.findForward("success");
	}

	/**
	 * 根据模块或功能的编码，删除该对象 zzj 2008/05/13
	 */
	public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkbenchDeptContactGroupForm tawWorkbenchDeptContactGroupForm = (TawWorkbenchDeptContactGroupForm) form;

		ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) getBean("ItawWorkbenchDeptContactGroupManager");
		mgr.removeTawWorkbenchDeptContactGroup(tawWorkbenchDeptContactGroupForm.getId());

	}

	/**
	 * ajax请求修改某节点的详细信息 zzj 2008/05/13
	 */
	public String xedit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawWorkbenchContactGroupForm tawWorkbenchContactGroupForm = (TawWorkbenchContactGroupForm) form;

		if (tawWorkbenchContactGroupForm.getId() != null) {
			ITawWorkbenchContactGroupManager mgr = (ITawWorkbenchContactGroupManager) getBean("ItawWorkbenchContactGroupManager");
			TawWorkbenchContactGroup tawWorkbenchContactGroup = (TawWorkbenchContactGroup) convert(tawWorkbenchContactGroupForm);

			mgr.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);
			// mgr.updateTawWorkbenchContactGroup(tawWorkbenchContactGroup);
		}

		return null;
	}

	/**
	 * ajax请求获取某节点的详细信息 zzj 2008/05/13
	 */
	public ActionForward xget(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String _strId = request.getParameter("nodeid");
		ITawWorkbenchDeptContactGroupManager mgr = (ITawWorkbenchDeptContactGroupManager) getBean("ItawWorkbenchDeptContactGroupManager");
		TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = mgr
				.getTawWorkbenchDeptContactGroupById(_strId);
		request.setAttribute("tawWorkbenchDeptContactGroupForm",
				tawWorkbenchDeptContactGroup);
		return mapping.findForward("edit");
	}

	public ActionForward saveToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		return mapping.findForward("saveToPage");
	}
}
