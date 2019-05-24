package com.boco.eoms.km.train.webapp.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.km.exam.mgr.KmExamSpecialtyMgr;
import com.boco.eoms.km.exam.model.KmExamSpecialty;
import com.boco.eoms.km.train.mgr.TrainSpecialtyMgr;
import com.boco.eoms.km.train.model.TrainSpecialty;
import com.boco.eoms.km.train.webapp.form.TrainSpecialtyForm;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:专业分类
 * </p>
 * <p>
 * Description:专业分类
 * </p>
 * <p>
 * Thu Jul 09 10:50:06 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
 
 public final class TrainSpecialtyAction extends BaseAction {
 
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
		return tree(mapping, form, request, response);
	}
	
	/**
	 * 专业分类树页面
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
	 * 生成专业分类树JSON数据
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
		TrainSpecialtyMgr trainSpecialtyMgr = (TrainSpecialtyMgr) getBean("trainSpecialtyMgr");
		// 取下级节点
		List list = trainSpecialtyMgr.getNextLevelTrainSpecialtys(nodeId);
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			TrainSpecialty trainSpecialty = (TrainSpecialty) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", trainSpecialty.getNodeId());
			// TODO 添加节点名称
			jitem.put("text", trainSpecialty.getSpecialtyName());
			// 设置右键菜单
			jitem.put("allowChild", true);
			jitem.put("allowEdit", true);
			jitem.put("allowDelete", true);
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			// 设置是否为叶子节点
			boolean leafFlag = true;
			if (trainSpecialtyMgr.isHasNextLevel(trainSpecialty.getNodeId())) {
				leafFlag = false;
			}
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
//			jitem.put("qtip", your tips here);
			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}
 	
	/**
	 * 获取模型分类树(第一层)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getNodesRadioTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		JSONArray jsonRoot = new JSONArray();
		TrainSpecialtyMgr trainSpecialtyMgr = (TrainSpecialtyMgr) getBean("trainSpecialtyMgr");
		// 取下级节点
		List list = trainSpecialtyMgr.getNextLevelTrainSpecialtys(nodeId);
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			TrainSpecialty trainSpecialty = (TrainSpecialty) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", trainSpecialty.getNodeId());
			// TODO 添加节点名称
			jitem.put("text",trainSpecialty.getSpecialtyName());
			jitem.put(UIConstants.JSON_NODETYPE, "folder");
			jitem.put("iconCls", "folder");
			jitem.put("qtip", trainSpecialty.getSpecialtyName());
			
			// 设置是否为叶子节点
			boolean leafFlag = true;
			if (trainSpecialtyMgr.isHasNextLevel(trainSpecialty.getNodeId())) {
				leafFlag = false;
			}
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			//jitem.put("qtip", your tips here);
			jitem.put("checked", false);		
			jsonRoot.put(jitem);
		}
		//JSONUtil.print(response, jsonRoot.toString());		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
	}
	
 	/**
	 * 新增专业分类
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
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		TrainSpecialtyForm trainSpecialtyForm = (TrainSpecialtyForm) form;
		trainSpecialtyForm.setParentNodeId(nodeId);
		updateFormBean(mapping, request, trainSpecialtyForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改专业分类
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
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		TrainSpecialtyMgr trainSpecialtyMgr = (TrainSpecialtyMgr) getBean("trainSpecialtyMgr");
		TrainSpecialty trainSpecialty = trainSpecialtyMgr.getTrainSpecialtyByNodeId(nodeId);
		TrainSpecialtyForm trainSpecialtyForm = (TrainSpecialtyForm) convert(trainSpecialty);
		updateFormBean(mapping, request, trainSpecialtyForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存专业分类
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
		TrainSpecialtyMgr trainSpecialtyMgr = (TrainSpecialtyMgr) getBean("trainSpecialtyMgr");
		TrainSpecialtyForm trainSpecialtyForm = (TrainSpecialtyForm) form;
		TrainSpecialty trainSpecialty = (TrainSpecialty) convert(trainSpecialtyForm);
		trainSpecialtyMgr.saveTrainSpecialty(trainSpecialty);
		return mapping.findForward("success");
	}
	
	/**
	 * 删除专业分类
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
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
		TrainSpecialtyMgr trainSpecialtyMgr = (TrainSpecialtyMgr) getBean("trainSpecialtyMgr");
		trainSpecialtyMgr.removeTrainSpecialtyByNodeId(nodeId);
		return mapping.findForward("success");
	}
}