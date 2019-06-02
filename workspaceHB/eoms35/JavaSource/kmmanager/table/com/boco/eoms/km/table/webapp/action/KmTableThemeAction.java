package com.boco.eoms.km.table.webapp.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.km.file.mgr.KmFileTreeMgr;
import com.boco.eoms.km.file.model.KmFileTree;
import com.boco.eoms.km.kmAuditer.mgr.KmAuditerMgr;
import com.boco.eoms.km.kmAuditer.model.KmAuditer;
import com.boco.eoms.km.kmAuditer.webapp.form.KmAuditerForm;
import com.boco.eoms.km.kmOperate.mgr.KmOperateMgr;
import com.boco.eoms.km.kmOperate.model.KmOperate;
import com.boco.eoms.km.knowledge.mgr.KmContentsMgr;
import com.boco.eoms.km.log.mgr.KmOperateLogMgr;
import com.boco.eoms.km.log.util.KmOperateDefine;
import com.boco.eoms.km.table.mgr.KmTableGeneralMgr;
import com.boco.eoms.km.table.mgr.KmTableThemeMgr;
import com.boco.eoms.km.table.model.KmTableGeneral;
import com.boco.eoms.km.table.model.KmTableTheme;
import com.boco.eoms.km.table.webapp.form.KmTableThemeForm;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:模型分类表
 * </p>
 * <p>
 * Description:模型分类
 * </p>
 * <p>
 * Thu Mar 26 10:16:39 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() hsl
 * @moudle.getVersion() 1.0
 * 
 */
 
 public final class KmTableThemeAction extends BaseAction {
 
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
	 * 模型分类表树页面
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
		String listType =  StaticMethod.nullObject2String(request.getParameter("listType"));
		request.setAttribute("listType", listType);
		return mapping.findForward("tree");
	}
 	
 	/**
	 * 生成模型分类表树JSON数据
	 * 动作：知识模型-知识分类
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
		// 读取：当前操作用户信息
		TawSystemSessionForm sessionform = this.getUser(request);
		String operateDeptId = sessionform.getDeptid();
		String operateUserId = sessionform.getUserid();

		// 部门权限
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");			
		List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateDeptId, "dept");
		Map kmOperateMap = new HashMap();
		for(int i=0;i<kmOperateToDeptList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToDeptList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}
		// 人员权限
		List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateUserId, "user");
		for(int i=0;i<kmOperateToUserList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToUserList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}

		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		JSONArray jsonRoot = new JSONArray();
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		// 取下级节点
		List list = kmTableThemeMgr.getNextLevelKmTableThemes(nodeId);
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableTheme kmTableTheme = (KmTableTheme) nodeIter.next();
			JSONObject jitem = new JSONObject();
			
			String operateType = StaticMethod.nullObject2String(kmOperateMap.get(kmTableTheme.getNodeId()));
			if("".equals(operateType)&&"1".equals(kmTableTheme.getHasParentOperate())){
				String parentId = kmTableTheme.getParentNodeId();
				operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
			}
			if(("".equals(operateType)||operateType.indexOf("N")!=-1)&&!"admin".equals(operateUserId)){
				continue;
			}
			
			jitem.put("id", kmTableTheme.getNodeId());
			// TODO 添加节点名称
			jitem.put("text", kmTableTheme.getThemeName());
			// 设置右键菜单
			if(operateType.indexOf("W")!=-1||"admin".equals(operateUserId)){
				jitem.put("allowChild", true);
				jitem.put("allowEdit", true);
				if(kmTableTheme.getIsUsed().equals("0")){ //如果节点未被使用
				  jitem.put("allowDelete", true);
				}
			}
			if(operateType.indexOf("G")!=-1||"admin".equals(operateUserId)){
				//jitem.put("allowaudnode", true); del by zhangxiaobo
				jitem.put("allowoperate", true);
			}			
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			
			// 设置是否为叶子节点
			if (kmTableThemeMgr.isHasNextLevel(kmTableTheme.getNodeId())) {
				jitem.put("leaf", false);
			}
			else{
				jitem.put("leaf", true);
				if(operateType.indexOf("G")!=-1||"admin".equals(operateUserId)){
					jitem.put("allowaudnode", true); //add by zhangxiaobo
				}				
			}
			
			// TODO 设置鼠标悬浮提示
			//jitem.put("qtip", your tips here);
			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}

 	/**
	 * 生成模型分类表树JSON数据
	 * 动作：知识内容-知识列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String getNodesShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//权限管理
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String operateDeptId = sessionform.getDeptid();
		String operateUserId = sessionform.getUserid();
		
		// 部门权限
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");			
		List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateDeptId, "dept");
		Map kmOperateMap = new HashMap();
		for(int i=0;i<kmOperateToDeptList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToDeptList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}
		// 人员权限
		List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateUserId, "user");
		for(int i=0;i<kmOperateToUserList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToUserList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}

		String nodeId = StaticMethod.null2String(request.getParameter("node"));
		
		List list = new ArrayList();
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		if(nodeId.length() <=1){
			list = kmTableThemeMgr.getFirstCreateThemes();
		}
		else{
			list = kmTableThemeMgr.getNextLevelShowThemes(nodeId);
		}
		
		JSONArray jsonRoot = new JSONArray();
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			KmTableTheme kmTableTheme = (KmTableTheme) nodeIter.next();
			JSONObject jitem = new JSONObject();
			
			String operateType = StaticMethod.nullObject2String(kmOperateMap.get(kmTableTheme.getNodeId()));
			if("".equals(operateType)&&"1".equals(kmTableTheme.getHasParentOperate())){
				String parentId = kmTableTheme.getParentNodeId();
				operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
			}
			if(("".equals(operateType)||operateType.indexOf("N")!=-1)&&!"admin".equals(operateUserId)){
				continue;
			}
			
			jitem.put("id", kmTableTheme.getNodeId());
			// TODO 添加节点名称
			jitem.put("text", kmTableTheme.getThemeName());
			// 设置右键菜单
			if(operateType.indexOf("W")!=-1||"admin".equals(operateUserId)){
				jitem.put("allowChild", true);
				jitem.put("allowEdit", true);
				jitem.put("allowDelete", true);
			}
			if(operateType.indexOf("G")!=-1||"admin".equals(operateUserId)){
			jitem.put("allowaudnode", true);
			jitem.put("allowoperate", true);
			}
			// 设置左键点击可触发action
			jitem.put("allowClick", true);
			// 设置是否为叶子节点
			boolean leafFlag = true;
			if (kmTableThemeMgr.isHasNextLevel(kmTableTheme.getNodeId())) {
				leafFlag = false;
			}
			jitem.put("leaf", leafFlag);
			// TODO 设置鼠标悬浮提示
			//jitem.put("qtip", your tips here);
			jsonRoot.put(jitem);
		}
		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}

	/**
	 * 获取模型分类树(单选，只能选择叶子节点)
	 * 动作：知识添加-知识分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 */
	public void getNodesRadioTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//权限管理
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String operateDeptId = sessionform.getDeptid();
		String operateUserId = sessionform.getUserid();

		// 部门权限
		KmOperateMgr kmOperateMgr = (KmOperateMgr) getBean("kmOperateMgr");			
		List kmOperateToDeptList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateDeptId, "dept");
		Map kmOperateMap = new HashMap();
		for(int i=0;i<kmOperateToDeptList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToDeptList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}
		// 人员权限
		List kmOperateToUserList = kmOperateMgr.getKmOperatesByOrgIdAndOrgType("content", operateUserId, "user");
		for(int i=0;i<kmOperateToUserList.size();i++){
			KmOperate kmOperate = (KmOperate)kmOperateToUserList.get(i);
			kmOperateMap.put(kmOperate.getNodeId(),kmOperate.getOperateType());
		}

		// 获取节点id
		String nodeId = request.getParameter("node");		
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		// 取下级节点
		List list = kmTableThemeMgr.getNextLevelKmTableThemes(nodeId);
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				KmTableTheme forum = (KmTableTheme) nodeIter.next();
				JSONObject item = new JSONObject();
				
				String operateType = StaticMethod.nullObject2String(kmOperateMap.get(forum.getNodeId()));
				if("".equals(operateType)&&"1".equals(forum.getHasParentOperate())){
					String parentId = forum.getParentNodeId();
					operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
					while(operateType.equals("") && parentId.length() >3){
						parentId = parentId.substring(0, parentId.length()-2);
						operateType = StaticMethod.nullObject2String(kmOperateMap.get(parentId));
					}
				}			
				if(operateType.indexOf("W")==-1&&!"admin".equals(operateUserId)){
					continue;
				}

				item.put("id", forum.getNodeId()); // 添加节点ID
				item.put("text", forum.getThemeName()); // 添加节点名称
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true); // 设置右键菜单
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				
				item.put("iconCls", "folder");
				item.put("qtip", forum.getThemeName());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);				
				//item.put(UIConstants.JSON_NODETYPE, InfopubConstants.NODETYPE_INFOPUB_FORUMS);

				// 设置是否为叶子节点
				boolean leafFlag = true;
				if (kmTableThemeMgr.isHasNextLevel(forum.getNodeId())) {
					leafFlag = false;
				}
				item.put("leaf", leafFlag);
				
				boolean openFlag = (forum.getIsOpen()!=null && forum.getIsOpen().equals("1")) ? true : false;

				// 设置叶子节点可以被选择
				if(leafFlag && openFlag){
					item.put("checked", false);
				}											
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());
	}

	/**
	 * 获取模型分类树(单选，只能选择叶子节点)
	 * 动作：知识查询-知识分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 */
	public void getNodesRadioTreeForQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");		
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		// 取下级节点
		List list = kmTableThemeMgr.getNextLevelKmTableThemes(nodeId);
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				KmTableTheme forum = (KmTableTheme) nodeIter.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getNodeId()); // 添加节点ID
				item.put("text", forum.getThemeName()); // 添加节点名称
				item.put(UIConstants.JSON_NODETYPE, "folder");
				item.put("iconCls", "folder");
				item.put("qtip", forum.getThemeName());
				// 设置是否为叶子节点
				boolean leafFlag = true;
				if (kmTableThemeMgr.isHasNextLevel(forum.getNodeId())) {
					leafFlag = false;
				}
				item.put("leaf", leafFlag);
				boolean openFlag = (forum.getIsOpen()!=null && forum.getIsOpen().equals("1")) ? true : false;
				// 设置叶子节点可以被选择
				if(leafFlag && openFlag){
					item.put("checked", false);
				}											
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());
	}
	
	/**
	 * 获取模型分类树(单选)
	 * 用于知识内容新增页面选择知识属于哪一个分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 */
	public void getNodesRadioTreeForAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");		
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		// 取下级节点
		List list = kmTableThemeMgr.getNextLevelKmTableThemes(nodeId);
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				KmTableTheme forum = (KmTableTheme) nodeIter.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getNodeId()); // 添加节点ID
				item.put("text", forum.getThemeName()); // 添加节点名称
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true); // 设置右键菜单
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				
				item.put("iconCls", "folder");
				item.put("qtip", forum.getThemeName());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);				
				//item.put(UIConstants.JSON_NODETYPE, InfopubConstants.NODETYPE_INFOPUB_FORUMS);

				// 设置是否为叶子节点
				boolean leafFlag = true;
				if (kmTableThemeMgr.isHasNextLevel(forum.getNodeId())) {
					leafFlag = false;
				}
				item.put("leaf", leafFlag);
				// 设置所有节点可以被选择				
				item.put("checked", false);														
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());
	}
	
	/**
	 * 获取平台“组织管理-字典管理-知识管理”菜单下定义的字典
	 * 用于模型定义字典类型与字典的绑定和知识内容增删改字典的呈现
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhangxb
	 */
	public void getCommonDict(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");
		ITawSystemDictTypeManager mgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");		
		// 取下级节点
		List list = mgr.getDictSonsByDictid(nodeId);
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				TawSystemDictType tawSystemDictType = (TawSystemDictType) nodeIter.next();
				JSONObject item = new JSONObject();
				item.put("id", tawSystemDictType.getDictId());				
				item.put("text", tawSystemDictType.getDictName());
				item.put("dictId", tawSystemDictType.getDictId());
				item.put("parentDictId", tawSystemDictType.getParentDictId());
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true); // 设置右键菜单
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				
				item.put("iconCls", "folder");
				item.put("qtip", tawSystemDictType.getDictName());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);				
				//item.put(UIConstants.JSON_NODETYPE, InfopubConstants.NODETYPE_INFOPUB_FORUMS);

				// 设置是否为叶子节点
				boolean leafFlag = false;
				Integer leaf = tawSystemDictType.getLeaf();
				if (leaf!=null && leaf.intValue() == 1) {
					leafFlag = true;
				}
				item.put("leaf", leafFlag);

				// 设置叶子节点可以被选择(不可以选择叶子节点)
				if(!leafFlag){
					item.put("checked", false);	
				}													
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());
	}
	
	public void getFileNodes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String nodeId = request.getParameter("node");
		KmFileTreeMgr netdiskMgr = (KmFileTreeMgr) getBean("kmFileTreeMgr");
		BocoLog.debug(this, "In getMyFolderNodes nodeId is:" + nodeId);
		List subNodeList = netdiskMgr.getNextLevelKmFileTrees(nodeId);
		BocoLog.debug(this, "folder list length is:" + String.valueOf(subNodeList.size()));
		JSONArray jsonRoot = new JSONArray();		
		for (int i = 0; i < subNodeList.size(); i++) {
			KmFileTree treeNode = (KmFileTree) subNodeList.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", treeNode.getNodeId());
			jitem.put("text", treeNode.getNodeName());			
			// 设置图标
			jitem.put("iconCls", "folder");
			// 设置所有节点可以被选择				
			jitem.put("checked", false);	
			jsonRoot.put(jitem);
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonRoot.toString());
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
	public void getNodesRadioTreeFirst(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");		
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		// 取下级节点	
		List list = kmTableThemeMgr.getFirstLevelUnUsedByParentNodeId(nodeId);
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				KmTableTheme forum = (KmTableTheme) nodeIter.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getNodeId()); // 添加节点ID
				item.put("text", forum.getThemeName()); // 添加节点名称
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true); // 设置右键菜单
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				
				item.put("iconCls", "folder");
				item.put("qtip", forum.getThemeName());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);				
				//item.put(UIConstants.JSON_NODETYPE, InfopubConstants.NODETYPE_INFOPUB_FORUMS);

				// 设置是否为叶子节点
				boolean leafFlag = true;
//				if (kmTableThemeMgr.isHasNextLevel(forum.getNodeId())) {
//					leafFlag = false;
//				}
				item.put("leaf", leafFlag);

				// 设置叶子节点可以被选择
				
				item.put("checked", false);															
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());
	}

	/**
	 * 查询第一级已使用的模型分类
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void getUsedFirstTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取节点id
		String nodeId = request.getParameter("node");		
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		// 取下级节点	
		List list = kmTableThemeMgr.getFirstLevelIsUsedByParentNodeId(nodeId);
		// json根（树根节点）
		JSONArray root = new JSONArray();
		// 遍历子版块
		if (null != list) {
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				KmTableTheme forum = (KmTableTheme) nodeIter.next();
				JSONObject item = new JSONObject();
				item.put("id", forum.getNodeId()); // 添加节点ID
				item.put("text", forum.getThemeName()); // 添加节点名称
				item.put(UIConstants.JSON_NODETYPE, "folder");
				// item.put("allowChild", true); // 设置右键菜单
				// item.put("allowEdit", true);
				// item.put("allowDelete", true);
				
				item.put("iconCls", "folder");
				item.put("qtip", forum.getThemeName());
				// item.put("allowNewThread", true);
				// item.put("allowListThreads", true);
				// item.put("allowListUnreadThreads", true);				
				//item.put(UIConstants.JSON_NODETYPE, InfopubConstants.NODETYPE_INFOPUB_FORUMS);

				// 设置是否为叶子节点
				boolean leafFlag = true;
//				if (kmTableThemeMgr.isHasNextLevel(forum.getNodeId())) {
//					leafFlag = false;
//				}
				item.put("leaf", leafFlag);
				
				// 设置叶子节点可以被选择				
				item.put("checked", false);
				
				root.put(item);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(root.toString());
	}

 	/**
	 * 新增模型分类表
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
		KmTableThemeForm kmTableThemeForm = (KmTableThemeForm) form;
		kmTableThemeForm.setParentNodeId(nodeId);
		kmTableThemeForm.setIsOpen("1");
		updateFormBean(mapping, request, kmTableThemeForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改模型分类表
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
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		KmTableTheme kmTableTheme = kmTableThemeMgr.getKmTableThemeByNodeId(nodeId);
		KmTableThemeForm kmTableThemeForm = (KmTableThemeForm) convert(kmTableTheme);
		updateFormBean(mapping, request, kmTableThemeForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存模型分类表
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
		KmTableThemeForm kmTableThemeForm = (KmTableThemeForm) form;		
		KmTableTheme kmTableTheme = (KmTableTheme) convert(kmTableThemeForm);
		
		Date createTime = StaticMethod.getLocalTime();	
		kmTableTheme.setCreateTime(createTime);				
		
		// 读取：当前操作用户信息
		TawSystemSessionForm tawSystemSessionForm=this.getUser(request);
		String operateUserId = tawSystemSessionForm.getUserid();
		String operateDeptId = tawSystemSessionForm.getDeptid();
		
		kmTableTheme.setCreateUser(operateUserId);
		kmTableTheme.setCreateDept(operateDeptId);
		
		// 保存：模型分类
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		kmTableThemeMgr.saveKmTableTheme(kmTableTheme);
		
		// 记录：操作历史记录
		KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
		if (null == kmTableTheme.getId() || "".equals(kmTableTheme.getId())) {
			kmOperateLogMgr.saveKmOperateLog(kmTableTheme.getNodeId(),
					"TABLE_ID", "ID",
					KmOperateDefine.KM_OPERATE_NAME_THEME_ADD, operateUserId,
					operateDeptId, operateUserId);
		} else {
			kmOperateLogMgr.saveKmOperateLog(kmTableTheme.getNodeId(),
					"TABLE_ID", "ID",
					KmOperateDefine.KM_OPERATE_NAME_THEME_MODIFY,
					operateUserId, operateDeptId, operateUserId);
		}
		
		return mapping.findForward("success");
	}
	
	/**
	 * 删除模型分类表
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
		
		//删除模型分类
		KmTableThemeMgr kmTableThemeMgr = (KmTableThemeMgr) getBean("kmTableThemeMgr");
		kmTableThemeMgr.removeKmTableThemeByNodeId(nodeId);

		//删除模型分类下的所以知识条目
		KmContentsMgr kmContentsMgr = (KmContentsMgr) getBean("kmContentsMgr");
		kmContentsMgr.removeKmContentsByThemeId(nodeId);
		
		// 读取：当前操作用户信息
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String operateUserId = sessionform.getUserid();
		String operateDeptId = sessionform.getDeptid();
		
		// 记录：操作历史记录
		KmOperateLogMgr kmOperateLogMgr = (KmOperateLogMgr) getBean("kmOperateLogMgr");
		kmOperateLogMgr.saveKmOperateLog(nodeId, "TABLE_ID", "ID",
				KmOperateDefine.KM_OPERATE_NAME_THEME_DELETE, operateUserId,
				operateDeptId, operateUserId);

		return mapping.findForward("success");
	}
	
    
	/**
	 * 审核人配置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nodeId = StaticMethod.null2String(request.getParameter("nodeId"));
		String rootNodeId = nodeId.substring(0, 3);
		KmTableGeneralMgr kmTableGeneralMgr = (KmTableGeneralMgr) getBean("kmTableGeneralMgr");
		KmTableGeneral kmTableGeneral = kmTableGeneralMgr.getKmTableGeneralByThemeId(rootNodeId);
		KmAuditerMgr kmAuditerMgr = (KmAuditerMgr) getBean("kmAuditerMgr");
		request.setAttribute("nodeId", nodeId);
		request.setAttribute("tableId",kmTableGeneral.getId() );
		request.setAttribute("auditType", "content");
		KmAuditer kmAuditer = kmAuditerMgr.getKmAuditerByTheme(nodeId);
		KmAuditerForm kmAuditerForm  = (KmAuditerForm) convert(kmAuditer);
		request.setAttribute("kmAuditerForm", kmAuditerForm);
		return mapping.findForward("audit");
	}
}