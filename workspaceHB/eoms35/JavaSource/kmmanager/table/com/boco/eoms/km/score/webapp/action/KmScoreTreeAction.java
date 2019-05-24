package com.boco.eoms.km.score.webapp.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.km.file.mgr.KmFileTreeMgr;
import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.file.webapp.form.KmFileTreeForm;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.score.mgr.KmScoreTreeMgr;
import com.boco.eoms.km.score.mgr.KmScoreWeightMgr;
import com.boco.eoms.km.score.model.KmScoreTree;
import com.boco.eoms.km.score.model.KmScoreWeight;
import com.boco.eoms.km.score.webapp.form.KmScoreTreeForm;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:积分设定树
 * </p>
 * <p>
 * Description:积分设定树
 * </p>
 * <p>
 * Thu Aug 13 14:07:31 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() me
 * @moudle.getVersion() 1.0
 * 
 */
 
 public final class KmScoreTreeAction extends BaseAction {
 
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
	 * 积分设定树树页面
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
	 * 生成积分设定树树JSON数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getNodes1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		JSONArray jsonRoot = new JSONArray();
		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
		// 取下级节点
		List list = kmScoreTreeMgr.getNextLevelKmScoreTrees(nodeId);
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmScoreTree kmScoreTree = (KmScoreTree) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmScoreTree.getNodeId());
			// TODO 添加节点名称
			jitem.put("text", kmScoreTree.getNodeName());
			// 设置右键菜单
			jitem.put("allowChild", true);
			jitem.put("allowEdit", true);
			jitem.put("allowDelete", true);
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			// 设置是否为叶子节点
			boolean leafFlag = true;
			if (kmScoreTreeMgr.isHasNextLevel(kmScoreTree.getNodeId())) {
				leafFlag = false;
			}
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
//			jitem.put("qtip", your tips here);
//			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}
	public String getNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("node");
		BocoLog.debug(this, "Init nodeId is:" + request.getParameter("node"));
		BocoLog.debug(this, "recovered nodeId is:" + nodeId);
		JSONArray jsonRoot = new JSONArray();
		jsonRoot = getSubNodes(nodeId,sessionform);
		response.setContentType("text/xml;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
		return null;
	}

	/**
	 * 生成文件夹树JSON数据
	 */
	private JSONArray getSubNodes(String nodeId, TawSystemSessionForm sessionform) {
		//权限管理
		String operateDeptId = sessionform.getDeptid();
		String operateUserId = sessionform.getUserid();

		// 部门权限
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");			
		List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("file", operateDeptId, "dept");
		Map kmOperateMap = new HashMap();
		for(int i=0;i<kmOperateToDeptList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToDeptList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}
		// 人员权限
		List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("file", operateUserId, "user");
		for(int i=0;i<kmOperateToUserList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToUserList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}
		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
		// 取下级节点
		List list = kmScoreTreeMgr.getNextLevelKmScoreTrees(nodeId);
//		KmFileTreeMgr netdiskMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
//		List subNodeList = netdiskMgr.getNextLevelKmFileTrees(nodeId);

		JSONArray jsonRoot = new JSONArray();
		if (list == null) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			KmScoreTree kmScoreTree = (KmScoreTree) list.get(i);
			String operateType = StaticMethod.nullObject2String(kmOperateMap.get(kmScoreTree.getNodeId()));

			if(("".equals(operateType)||operateType.indexOf("N")!=-1)&&!"admin".equals(operateUserId)){
				continue;
			}
			JSONObject jitem = new JSONObject();
			jitem.put("id", kmScoreTree.getNodeId());
			jitem.put("text", kmScoreTree.getNodeName());
			jitem.put("allowClick", true);
			if(kmScoreTree.getNodeId().length()==3){
				if(operateType.indexOf("W")!=-1||"admin".equals(operateUserId)){
					jitem.put("allowChild", true);			
					jitem.put("allowEdit", true);
					jitem.put("allowDelete", true);
				}
				if(operateType.indexOf("G")!=-1||"admin".equals(operateUserId)){
					jitem.put("allowaudnode", true);
					jitem.put("allowoperate", true);
				}
			}else{
				if(operateType.indexOf("W")!=-1||"admin".equals(operateUserId)){		
					jitem.put("allowEdit", true);
					jitem.put("allowDelete", true);
				}
				if(operateType.indexOf("G")!=-1||"admin".equals(operateUserId)){
					jitem.put("allowaudnode", true);
					jitem.put("allowoperate", true);
				}
			}	
			// 设置是否叶节点
			String leaf = kmScoreTree.getLeaf();
			if (leaf.trim().equals("0")) {
				jitem.put("leaf", false);
			} else {
				jitem.put("leaf", true);
			}
			// 设置图标
			jitem.put("iconCls", "folder");
			jsonRoot.put(jitem);
		}
		return jsonRoot;
	}
 	/**
	 * 新增积分设定树
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
    	
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");    	
		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		KmScoreTreeForm kmScoreTreeForm = (KmScoreTreeForm) form;
		kmScoreTreeForm.setId("");
		kmScoreTreeForm.setUserId(sessionform.getUserid());
		kmScoreTreeForm.setParentNodeId(nodeId);
		kmScoreTreeForm.setCreateTime(StaticMethod.getCurrentDateTime());
		updateFormBean(mapping, request, kmScoreTreeForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改积分设定树
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
		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
		KmScoreTree kmScoreTree = kmScoreTreeMgr.getKmScoreTreeByNodeId(nodeId);
		KmScoreTreeForm kmScoreTreeForm = (KmScoreTreeForm) convert(kmScoreTree);
		updateFormBean(mapping, request, kmScoreTreeForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存积分设定树
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
		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
		KmScoreTreeForm kmScoreTreeForm = (KmScoreTreeForm) form;
		KmScoreTree kmScoreTree = (KmScoreTree) convert(kmScoreTreeForm);
		kmScoreTreeMgr.saveKmScoreTree(kmScoreTree);
		String nodeId = kmScoreTree.getNodeId();
		KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr) getBean("kmScoreWeightMgr");
		KmScoreWeight kmScoreWeight = new KmScoreWeight();
		if(nodeId.length()==3){
			kmScoreWeight.setPowerName(kmScoreTree.getNodeName());
			kmScoreWeight.setPowerWeight(kmScoreTree.getWeight());
		}else{
			String parentNodeId = kmScoreTree.getParentNodeId();
			KmScoreTree kmScoreTreeParent = kmScoreTreeMgr.getKmScoreTreeByNodeId(parentNodeId);
			kmScoreWeight.setId("1");
			kmScoreWeight.setPowerName(kmScoreTreeParent.getNodeName());
			kmScoreWeight.setPowerWeight(kmScoreTreeParent.getWeight());
			kmScoreWeight.setActionName(kmScoreTree.getNodeName());
			kmScoreWeight.setActionWeight(kmScoreTree.getWeight());
		}
		kmScoreWeight.setIsDeleted(new Integer(0));
		kmScoreWeightMgr.saveKmScoreWeight(kmScoreWeight);
		return mapping.findForward("success");
	}
	
	/**
	 * 删除积分设定树
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
		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
		kmScoreTreeMgr.removeKmScoreTreeByNodeId(nodeId);
		return mapping.findForward("success");
	}
}