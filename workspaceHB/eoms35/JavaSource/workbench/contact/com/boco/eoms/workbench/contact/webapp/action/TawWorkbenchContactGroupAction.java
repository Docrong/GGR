package com.boco.eoms.workbench.contact.webapp.action;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactGroupManager;
import com.boco.eoms.workbench.contact.webapp.form.TawWorkbenchContactGroupForm;

import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
 
/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */

public final class TawWorkbenchContactGroupAction extends BaseAction {
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

		TawWorkbenchContactGroupForm tawWorkbenchContactGroupForm = (TawWorkbenchContactGroupForm) form;
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		ITawWorkbenchContactGroupManager mgr = (ITawWorkbenchContactGroupManager) getBean("ItawWorkbenchContactGroupManager");
		TawWorkbenchContactGroup tawWorkbenchContactGroup = (TawWorkbenchContactGroup) convert(tawWorkbenchContactGroupForm);
		// 存储group_id 的数字

		tawWorkbenchContactGroup.setUserId(sessionform.getUserid());
		if (tawWorkbenchContactGroupForm.getId() == null
				|| tawWorkbenchContactGroupForm.getId().equals("")) {
			//

			tawWorkbenchContactGroup.setGroupId(mgr.getMaxGroupId() + "");

			tawWorkbenchContactGroup.setDeleted("0");
		} else {

			tawWorkbenchContactGroup.setDeleted("0");
			if (tawWorkbenchContactGroupForm.getGroupId().equals("")) {
				String group_id = (String) request.getParameter("groupId");
				tawWorkbenchContactGroup.setGroupId(group_id);
			}
		}
		mgr.saveTawWorkbenchContactGroup(tawWorkbenchContactGroup);
		return mapping.findForward("success");
	}

	/**
	 * 根据模块或功能的编码，删除该对象 zzj 2008/05/13
	 */
	public void xdelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TawWorkbenchContactGroupForm tawWorkbenchContactGroupForm = (TawWorkbenchContactGroupForm) form;

		ITawWorkbenchContactGroupManager mgr = (ITawWorkbenchContactGroupManager) getBean("ItawWorkbenchContactGroupManager");
		mgr
				.removeTawWorkbenchContactGroup(tawWorkbenchContactGroupForm
						.getId());

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
		ITawWorkbenchContactGroupManager mgr = (ITawWorkbenchContactGroupManager) getBean("ItawWorkbenchContactGroupManager");
		TawWorkbenchContactGroup tawWorkbenchContactGroup = mgr
				.getTawWorkbenchContactGroupById(_strId);
		request.setAttribute("tawWorkbenchContactGroupForm",
				tawWorkbenchContactGroup);
		return mapping.findForward("edit");
	}

	public ActionForward saveToPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		return mapping.findForward("saveToPage");
	}
}
