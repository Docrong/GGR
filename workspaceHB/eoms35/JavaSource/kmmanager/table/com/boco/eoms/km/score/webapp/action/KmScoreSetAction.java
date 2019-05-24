package com.boco.eoms.km.score.webapp.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.score.mgr.KmScoreSetMgr;
import com.boco.eoms.km.score.mgr.KmScoreTreeMgr;
import com.boco.eoms.km.score.mgr.KmScoreWeightMgr;
import com.boco.eoms.km.score.model.KmScoreSet;
import com.boco.eoms.km.score.model.KmScoreTree;
import com.boco.eoms.km.score.model.KmScoreWeight;
import com.boco.eoms.km.score.util.KmScoreSetConstants;
import com.boco.eoms.km.score.webapp.form.KmScoreSetForm;

/**
 * <p>
 * Title:积分设定表
 * </p>
 * <p>
 * Description:积分设定表
 * </p>
 * <p>
 * Fri Aug 07 14:32:13 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() me
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmScoreSetAction extends BaseAction {
 
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
	 * 新增积分设定表
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
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
//		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr)getBean("kmScoreTreeMgr");
		KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr)getBean("kmScoreWeightMgr");
		KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);

		KmScoreSetForm kmScoreSetForm = (KmScoreSetForm) form;
		kmScoreSetForm.setId("");
//		kmScoreSetForm.setParentNodeId(nodeId);
		kmScoreSetForm.setActionName(kmScoreWeight.getActionName());
		kmScoreSetForm.setPowerName(kmScoreWeight.getPowerName());
		kmScoreSetForm.setScoreWeightId(kmScoreWeight.getId());
		kmScoreSetForm.setIsDeleted(new Integer(0));
		Integer actionWeight = kmScoreWeight.getActionWeight();
		Integer powerWeight = kmScoreWeight.getPowerWeight();
		updateFormBean(mapping, request, kmScoreSetForm);
		request.setAttribute("actionWeight", actionWeight);
		request.setAttribute("powerWeight", powerWeight);
		request.setAttribute("nodeId", nodeId);
		return mapping.findForward("edit");
	}
	
	/**
	 * 文件管理树树页面
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
	 * 修改积分设定表
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
		KmScoreSetMgr kmScoreSetMgr = (KmScoreSetMgr) getBean("kmScoreSetMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmScoreSet kmScoreSet = kmScoreSetMgr.getKmScoreSet(id);
		KmScoreSetForm kmScoreSetForm = (KmScoreSetForm) convert(kmScoreSet);
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
//		KmScoreTreeMgr kmScoreTreeMgr = (KmScoreTreeMgr)getBean("kmScoreTreeMgr");
		KmScoreWeightMgr kmScoreWeightMgr = (KmScoreWeightMgr)getBean("kmScoreWeightMgr");
		KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);
		Integer actionWeight = kmScoreWeight.getActionWeight();
		Integer powerWeight = kmScoreWeight.getPowerWeight();
		request.setAttribute("actionWeight", actionWeight);
		request.setAttribute("powerWeight", powerWeight);

		updateFormBean(mapping, request, kmScoreSetForm);
		request.setAttribute("nodeId", request.getParameter("nodeId"));
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存积分设定表
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
		KmScoreSetMgr kmScoreSetMgr = (KmScoreSetMgr) getBean("kmScoreSetMgr");
		KmScoreSetForm kmScoreSetForm = (KmScoreSetForm) form;
		boolean isNew = (null == kmScoreSetForm.getId() || "".equals(kmScoreSetForm.getId()));
		KmScoreSet kmScoreSet = (KmScoreSet) convert(kmScoreSetForm);
		if (isNew) {
			kmScoreSetMgr.saveKmScoreSet(kmScoreSet);
		} else {
			kmScoreSetMgr.saveKmScoreSet(kmScoreSet);
		}
		return search(mapping, kmScoreSetForm, request, response);
	}
	
	/**
	 * 删除积分设定表
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
		KmScoreSetMgr kmScoreSetMgr = (KmScoreSetMgr) getBean("kmScoreSetMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmScoreSetMgr.removeKmScoreSetId(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示积分设定表列表
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
		String nodeId = StaticMethod.nullObject2String(request.getParameter("nodeId"));;
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmScoreSetConstants.KMSCORESET_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmScoreSetMgr kmScoreSetMgr = (KmScoreSetMgr) getBean("kmScoreSetMgr");
		KmScoreWeightMgr kmScoreWeightMgr =(KmScoreWeightMgr) getBean("kmScoreWeightMgr");
		KmScoreWeight kmScoreWeight = kmScoreWeightMgr.getKmScoreWeightByNodeId(nodeId);
		Map map = null;
		if(nodeId.length()==3){
			map = (Map) kmScoreSetMgr.getKmScoreSets(pageIndex, pageSize, " ,KmScoreWeight kmScoreWeight WHERE kmScoreSet.scoreWeightId = kmScoreWeight.id and kmScoreSet.isDeleted='0' and kmScoreSet.powerName='" +kmScoreWeight.getPowerName()+"' and kmScoreWeight.nodeId like '"+nodeId.substring(0, nodeId.length()-2)+"%'");
		}else{
			map = (Map) kmScoreSetMgr.getKmScoreSets(pageIndex, pageSize, " ,KmScoreWeight kmScoreWeight WHERE kmScoreSet.scoreWeightId = kmScoreWeight.id and kmScoreSet.isDeleted='0' and kmScoreSet.actionName='" +kmScoreWeight.getActionName()+"' and kmScoreWeight.nodeId like '"+nodeId.substring(0, nodeId.length()-2)+"%'");
		}
		
		List list = (List) map.get("result");
		request.setAttribute("nodeId", nodeId);
		request.setAttribute(KmScoreSetConstants.KMSCORESET_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 生成文件夹树JSON数据
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
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String nodeId = request.getParameter("nodeId");
		BocoLog.debug(this, "Init nodeId is:" + request.getParameter("nodeId"));
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
		
		KmScoreTreeMgr netdiskMgr = (KmScoreTreeMgr) getBean("kmScoreTreeMgr");
		List subNodeList = netdiskMgr.getNextLevelKmScoreTrees(nodeId);

		JSONArray jsonRoot = new JSONArray();
		if (subNodeList == null) {
			return null;
		}
		
		for (int i = 0; i < subNodeList.size(); i++) {
			KmScoreTree treeNode = (KmScoreTree) subNodeList.get(i);
			String operateType = StaticMethod.nullObject2String(kmOperateMap.get(treeNode.getNodeId()));
			if("".equals(operateType)&&"1".equals(treeNode.getHasParentOperate())){
				String parentId = treeNode.getParentNodeId();
				operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
			}
			if(("".equals(operateType)||operateType.indexOf("N")!=-1)&&!"admin".equals(operateUserId)){
				continue;
			}
			JSONObject jitem = new JSONObject();
			jitem.put("id", treeNode.getNodeId());
			jitem.put("text", treeNode.getNodeName());
			jitem.put("allowClick", true);
			if(operateType.indexOf("W")!=-1||"admin".equals(operateUserId)){
				jitem.put("allowChild", true);			
				jitem.put("allowEdit", true);
				jitem.put("allowDelete", true);
			}
			if(operateType.indexOf("G")!=-1||"admin".equals(operateUserId)){
				jitem.put("allowaudnode", true);
				jitem.put("allowoperate", true);
			}
			// 设置是否叶节点
			String leaf = treeNode.getLeaf();
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
	 * 分页显示积分设定表列表，支持Atom方式接入Portal
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
			KmScoreSetMgr kmScoreSetMgr = (KmScoreSetMgr) getBean("kmScoreSetMgr");
			Map map = (Map) kmScoreSetMgr.getKmScoreSets(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmScoreSet kmScoreSet = new KmScoreSet();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				kmScoreSet = (KmScoreSet) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/kmScoreSet/kmScoreSets.do?method=edit&id="
						+ kmScoreSet.getId() + "' target='_blank'>"
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
}