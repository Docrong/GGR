package com.boco.eoms.km.expert.webapp.action;

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

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:专家分类
 * </p>
 * <p>
 * Description:专家分类
 * </p>
 * <p>
 * Mon Jul 06 17:05:49 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
 
 public final class KmExpertMenuAction extends BaseAction {
 
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
	 * 专家类型树页面
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
	 * 生成专家类型树JSON数据
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
		String nodeId = StaticMethod.null2String(request.getParameter("node")); //当前选择的节点ID
		
		String[] fieldDictIdArray = {"1010104","1050103","1050104","7"};
		int fieldDictIdLength = fieldDictIdArray.length;
		String[] fieldNameArray = {"specialty","expertClass","expertLevel","area"};
		String[] fieldCnNameArray = {"所属专业","专家类型","专家级别","所属地区"};

		JSONArray jsonRoot = new JSONArray();
		if(nodeId.equals("1")){
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
			String selFieldName = "";
			for(int i=0; i<fieldDictIdLength; i++){
				if(nodeId.startsWith(fieldDictIdArray[i])){					
					selFieldName = fieldNameArray[i];
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
					boolean leafFlag = true;
					if (sysarea.getLeaf().equals("0")) {
						leafFlag = false;
					}
					jitem.put("leaf", leafFlag);
					// 设置鼠标悬浮提示
					jitem.put("qtip", sysarea.getAreaname());
					// 设置分类字段
					jitem.put("fieldName", selFieldName);
					jsonRoot.put(jitem);
				}
			}
			else{
				// 取下级节点
				ITawSystemDictTypeManager sysDictMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
				List list = sysDictMgr.getDictSonsByDictid(nodeId);
				
				for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
					TawSystemDictType tawSystemDictType = (TawSystemDictType) nodeIter.next();
					
					JSONObject jitem = new JSONObject();
					jitem.put("id", tawSystemDictType.getDictId());
					// 添加节点名称
					jitem.put("text", tawSystemDictType.getDictName());
					// 设置右键菜单
					jitem.put("allowChild", true);
					jitem.put("allowEdit", true);
					jitem.put("allowDelete", true);
					// 设置左键点击可触发action
					jitem.put("allowClick", true);
					// 设置是否为叶子节点
					boolean leafFlag = true;
					if (tawSystemDictType.getLeaf().intValue() == 0) {
						leafFlag = false;
					}
					jitem.put("leaf", leafFlag);
					// 设置鼠标悬浮提示
					jitem.put("qtip", tawSystemDictType.getDictName());
					// 设置分类字段
					jitem.put("fieldName", selFieldName);
					jsonRoot.put(jitem);
				}
			}
		}

		JSONUtil.print(response, jsonRoot.toString());
		return null;
	}

}