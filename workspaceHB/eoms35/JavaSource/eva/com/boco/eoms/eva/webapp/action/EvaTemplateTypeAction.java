package com.boco.eoms.eva.webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.eva.mgr.IEvaTreeMgr;
import com.boco.eoms.eva.model.EvaTree;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.eva.webapp.form.EvaTreeForm;

public final class EvaTemplateTypeAction extends BaseAction {

	/**
	 * 新建考核模板分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward newTemplateType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String nodeId = request.getParameter("nodeId");
		EvaTreeForm evaTreeForm = (EvaTreeForm) form;
		evaTreeForm.setParentNodeId(nodeId);
		updateFormBean(mapping, request, evaTreeForm);
		return mapping.findForward("newTemplateType");
	}

	/**
	 * 修改模板分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward editTemplateType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IEvaTreeMgr evaTreeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
		String nodeId = request.getParameter("nodeId");
		EvaTree evaTree = evaTreeMgr.getTreeNodeByNodeId(nodeId);
		EvaTreeForm evaTreeForm = (EvaTreeForm) convert(evaTree);
		updateFormBean(mapping, request, evaTreeForm);
		return mapping.findForward("editTemplateType");
	}

	/**
	 * 删除模板分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward removeTemplateType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IEvaTreeMgr evaTreeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
		String nodeId = request.getParameter("nodeId");
		evaTreeMgr.removeTreeNodeByNodeId(nodeId);
		return mapping.findForward("success");
	}
	
	/**
	 * 保存模板分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTemplateType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		IEvaTreeMgr evaTreeMgr = (IEvaTreeMgr) getBean("IevaTreeMgr");
		EvaTree evaTree = new EvaTree();
		EvaTreeForm evaTreeForm = (EvaTreeForm) form;
		if (null == evaTreeForm.getId() || "".equals(evaTreeForm.getId())) {
			evaTree = (EvaTree) convert(evaTreeForm);
			String newNodeId = evaTreeMgr.getMaxNodeId(evaTree
					.getParentNodeId());
			evaTree.setNodeId(newNodeId);
			evaTree.setNodeType(EvaConstants.NODETYPE_TEMPLATETYPE);
			evaTree.setLeaf(EvaConstants.NODE_LEAF);
			evaTreeMgr.saveTreeNode(evaTree);
		} else {
			evaTree = evaTreeMgr.getTreeNode(evaTreeForm.getId());
			evaTree.setNodeName(evaTreeForm.getNodeName());
			evaTree.setNodeRemark(evaTreeForm.getNodeRemark());
			evaTreeMgr.saveTreeNode(evaTree);
		}
		return mapping.findForward("success");
	}

}
