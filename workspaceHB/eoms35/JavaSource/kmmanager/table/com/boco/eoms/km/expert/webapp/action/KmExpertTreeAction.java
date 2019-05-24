package com.boco.eoms.km.expert.webapp.action;

import java.io.IOException;
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

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.km.expert.mgr.KmExpertTreeMgr;

public class KmExpertTreeAction extends BaseAction {
	
	/**
	 * 查询普通用户树（不包含专家身份的用户）
	 * 
	 * @param mapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward userFromDept(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String node = StaticMethod.null2String(request.getParameter("node"), StaticVariable.ProvinceID + "");
		String selfFlag = StaticMethod.null2String(request.getParameter("noself"),"");

		TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
		TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
		KmExpertTreeMgr kmExpertTreeMgr = (KmExpertTreeMgr)this.getBean("kmExpertTreeMgr");

		ArrayList userlist = new ArrayList();
		ArrayList deptlist = new ArrayList();

		try{				
			deptlist = (ArrayList) deptbo.getNextLevecDepts(node, "0");
			if (selfFlag.equals("true")) {//不包括自己的人员list
				TawSystemSessionForm sessionform = this.getUser(request);
				userlist = (ArrayList) kmExpertTreeMgr.getUserBydeptidsNoSelf(node,sessionform.getUserid());
			} else {
				userlist = (ArrayList) kmExpertTreeMgr.getUserBydeptids(node);
			}
			
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}
	
		JSONArray json = new JSONArray();

		if (deptlist.size() > 0){
	    	for (int i = 0; i < deptlist.size(); i++) {
				TawSystemDept subDept = (TawSystemDept) deptlist.get(i);			
				JSONObject jitem = new JSONObject();
				jitem.put(UIConstants.JSON_ID, subDept.getDeptId());
				jitem.put(UIConstants.JSON_TEXT, subDept.getDeptName());
				jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_DEPT);
				jitem.put("iconCls", "dept");
				
				//判断是否还有子节点
				List flaguser = userrolebo.getUserBydeptids(subDept.getDeptId());
				List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(), "0");
				if (flagdept == null || flagdept.size() <= 0) {
					if (flaguser == null || flaguser.size() <= 0) {
						jitem.put("leaf", 1);
					}
				} else {
					jitem.put("leaf", 0);
				}
				json.put(jitem);				
			}
		}

		if (userlist.size() > 0) {
			for (int j = 0; j < userlist.size(); j++) {
				TawSystemUser sysuser = (TawSystemUser) userlist.get(j);			
				JSONObject jitem = new JSONObject();
				jitem.put(UIConstants.JSON_ID, sysuser.getUserid());
				jitem.put(UIConstants.JSON_TEXT, sysuser.getUsername());
				jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_USER);
				jitem.put("leaf", 1);
				jitem.put("iconCls", "user");
				jitem.put("mobile",sysuser.getMobile());
				json.put(jitem);
			}
		}

		JSONUtil.print(response, json.toString());
		return null;
	}

	public ActionForward userFromMenu(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String nodeId = StaticMethod.null2String(request.getParameter("node"), "-1"); //当前选择的节点ID
		
		String[] fieldDictIdArray = {"1010104","1050103","1050104","7"};
		int fieldDictIdLength = fieldDictIdArray.length;
		String[] fieldNameArray = {"specialty","expertClass","expertLevel","area"};
		String[] fieldCnNameArray = {"所属专业","专家类型","专家级别","所属地区"};
		
		ITawSystemDictTypeManager sysDictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		
		JSONArray jsonRoot = new JSONArray();
		String selFieldName = "";
		String selFieldValue = "";
		
		if(nodeId.equals("-1")){
			// 取下级节点
			for(int i=0; i<fieldDictIdLength; i++){
				JSONObject jitem = new JSONObject();
				//设置节点的ID
				jitem.put("id", fieldDictIdArray[i]);
				//设置节点的名称
				jitem.put("text", fieldCnNameArray[i]);
				// 设置右键菜单
				jitem.put("allowChild", true);
				jitem.put("allowEdit", true);
				jitem.put("allowDelete", true);
				// 设置左键点击可触发action
				jitem.put("allowClick", true);
				// 设置是否为叶子节点
				jitem.put("leaf", false);
				// 设置鼠标悬浮提示
				jitem.put("qtip", fieldCnNameArray[i]);
				// 设置分类字段
				jitem.put("fieldName", fieldNameArray[i]);
				jsonRoot.put(jitem);
			}
		}
		else{
			for(int i=0; i<fieldDictIdLength; i++){
				if(nodeId.startsWith(fieldDictIdArray[i])){					
					selFieldName = fieldNameArray[i];
					selFieldValue = nodeId;
					break;
				}
			}
			
			if(selFieldName.equals("area")){
				// 取下级节点
				ITawSystemAreaManager mgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
				List list = mgr.getSonAreaByAreaId(nodeId);
				
				for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
					TawSystemArea sysarea = (TawSystemArea) nodeIter.next();
					
					JSONObject jitem = new JSONObject();
					jitem.put("id", sysarea.getAreaid());
					// 添加节点名称
					jitem.put("text", sysarea.getAreaname());
					// 设置右键菜单
					jitem.put("allowChild", true);
					jitem.put("allowEdit", true);
					jitem.put("allowDelete", true);
					// 设置左键点击可触发action
					jitem.put("allowClick", true);
					// 设置是否为叶子节点
					jitem.put("leaf", false);
					// 设置鼠标悬浮提示
					jitem.put("qtip", sysarea.getAreaname());
					jitem.put("fieldName", selFieldName); //设置分类字段
					jsonRoot.put(jitem);
				}				
			}else{
			
			// 取下级节点
			List list = sysDictMgr.getDictSonsByDictid(nodeId);			
			for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
				TawSystemDictType tawSystemDictType = (TawSystemDictType) nodeIter.next();
				
				JSONObject jitem = new JSONObject();
				//设置节点的ID
				jitem.put("id", tawSystemDictType.getDictId());
				//设置节点的名称
				jitem.put("text", tawSystemDictType.getDictName());
				// 设置右键菜单
				jitem.put("allowChild", true);
				jitem.put("allowEdit", true);
				jitem.put("allowDelete", true);
				// 设置左键点击可触发action
				jitem.put("allowClick", true);
				// 设置是否为叶子节点
				jitem.put("leaf", false);
				// 设置鼠标悬浮提示
				jitem.put("qtip", tawSystemDictType.getDictName());
				jitem.put("fieldName", selFieldName); //设置分类字段
				jsonRoot.put(jitem);
			}}
		}
		
		if(!selFieldName.equals("") && !selFieldValue.equals("")){
			KmExpertTreeMgr kmExpertTreeMgr = (KmExpertTreeMgr)this.getBean("kmExpertTreeMgr");
			List userlist = kmExpertTreeMgr.getUserByKmExpertBasicField(selFieldName, selFieldValue);

			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					TawSystemUser sysuser = (TawSystemUser) userlist.get(j);			
					JSONObject jitem = new JSONObject();
					jitem.put(UIConstants.JSON_ID, sysuser.getUserid());
					jitem.put(UIConstants.JSON_TEXT, sysuser.getUsername());
					jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_USER);
					jitem.put("leaf", 1);
					jitem.put("iconCls", "user");
					jitem.put("mobile",sysuser.getMobile());
					jsonRoot.put(jitem);
				}
			}
		}

		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}
	public void getUserListByAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String nodeId = StaticMethod.null2String(request.getParameter("node"), "-1"); //当前选择的节点ID
		
		String fieldDictId = StaticMethod.null2String(request.getParameter("fieldDictId")); //字典所对应的根ID
		String fieldName = StaticMethod.null2String(request.getParameter("fieldName")); //字典所对应的字段
		String[] fieldDictIdArray = fieldDictId.split("/");
		int fieldDictIdLength = fieldDictIdArray.length;
		String[] fieldNameArray = fieldName.split("/");
		String selFieldName = "";
		String selFieldValue = "";
		String strUserList="";
		for(int i=0; i<fieldDictIdLength; i++){
			if(nodeId.startsWith(fieldDictIdArray[i])){					
				selFieldName = fieldNameArray[i];
				selFieldValue = nodeId;
				break;
			}
		}
		if(!selFieldName.equals("") && !selFieldValue.equals("")){
			KmExpertTreeMgr kmExpertTreeMgr = (KmExpertTreeMgr)this.getBean("kmExpertTreeMgr");
			List userlist = kmExpertTreeMgr.getUserByKmExpertBasicField(selFieldName, selFieldValue);
			
			if (userlist.size() > 0) {
				for (int j = 0; j < userlist.size(); j++) {
					TawSystemUser sysuser = (TawSystemUser) userlist.get(j);	
					strUserList+=sysuser.getUserid()+",";
					strUserList+=sysuser.getUsername()+",";
				}
			}
			strUserList = strUserList.substring(0, strUserList.length()-1);
		}
		response.setContentType("text/xml; charset=UTF-8");
		response.getWriter().print(strUserList);
	}

	public ActionForward getAreas(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String node = StaticMethod.null2String(request.getParameter("node"), "-1");
		
		JSONArray json = new JSONArray();
		ITawSystemAreaManager mgr = (ITawSystemAreaManager) ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
		ArrayList list = new ArrayList();

		try{				
			list = (ArrayList) mgr.getSonAreaByAreaId(node);

			for (int i = 0; i < list.size(); i++) {
				TawSystemArea sysarea = (TawSystemArea) list.get(i);

				JSONObject jitem = new JSONObject();
				jitem.put("id", sysarea.getAreaid());
				jitem.put("text", sysarea.getAreaname());
				jitem.put(UIConstants.JSON_NODETYPE, "area");
				jitem.put("iconCls", "area");
				if (sysarea.getLeaf().equals("1")) {
					jitem.put("leaf", 1);
				}
				jitem.put("allowChild", true);
				jitem.put("allowDelete", true);
				jitem.put("checked", false);
				json.put(jitem);
			}
			
		} catch (Exception ex) {
			BocoLog.error(this, "生成部门用户树图时报错：" + ex);
		}

		JSONUtil.print(response, json.toString());
		return null;
	}
}
