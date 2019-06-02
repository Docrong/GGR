package com.boco.eoms.partner.baseinfo.webapp.action;

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
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.partner.baseinfo.mgr.AreaDeptTreeMgr;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserMgr;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserAndAreaMgr;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserAndAreaMgr;
import com.boco.eoms.partner.baseinfo.model.AreaDeptTree;
import com.boco.eoms.partner.baseinfo.model.PartnerUserAndArea;
import com.boco.eoms.partner.baseinfo.util.AreaDeptTreeConstants;
import com.boco.eoms.partner.baseinfo.webapp.form.AreaDeptTreeForm;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.partner.baseinfo.util.RoleIdList;

/**
 * <p>
 * Title:地域部门树
 * </p>
 * <p>
 * Description:地域部门树
 * </p>
 * <p>
 * Fri Feb 06 11:46:50 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() liujinlong
 * @moudle.getVersion() 3.5
 * 
 */
 
 public final class AreaDeptTreeAction extends BaseAction {
 
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
	 * 地域部门树树页面
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
	 * 生成地域部门树树JSON数据
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
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		// 根据登录用户的权限取下级节点
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");		
		PartnerUserAndAreaMgr partnerUserAndAreaMgr = (PartnerUserAndAreaMgr) getBean("partnerUserAndAreaMgr");
        String operuserid = sessionform.getUserid();
        PartnerUserAndArea object = partnerUserAndAreaMgr.getObjectByUserId(operuserid);
        
		AreaDeptTree tree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
		List list = new ArrayList();
		if(object!=null){
		if(tree.getId()!=null&&tree.getNodeType().equals("province")&&!object.getAreaType().equals("province")){
			String[] areas = object.getAreaNames().split(",");
			String areaNames = "";
			for(int i = 0;i<areas.length;i++){
				areaNames += ("'"+areas[i]+"'"+",");
			}
			areaNames = areaNames.substring(0, areaNames.length()-1);
			list = areaDeptTreeMgr.getNextLevelAreaDeptTrees(nodeId, areaNames);
		}
		else list = areaDeptTreeMgr.getNextLevelAreaDeptTrees(nodeId);
		}
		else if(object==null&&operuserid.equals("admin"))list = areaDeptTreeMgr.getNextLevelAreaDeptTrees(nodeId);
		
		//2009-5-26 新建类RoleIdList，定义角色属性
		RoleIdList roleIdList = (RoleIdList) getBean("roleIdList");
		
		for (Iterator nodeIter = list.iterator(); nodeIter.hasNext();) {
			AreaDeptTree areaDeptTree = (AreaDeptTree) nodeIter.next();
			JSONObject jitem = new JSONObject();
			jitem.put("id", areaDeptTree.getNodeId());
			// TODO 添加节点名称
			jitem.put("text", areaDeptTree.getNodeName());
			//String clickUrl = "${app}/partner/baseinfo/areaDeptTrees.do?method=edit";
			// 设置右键菜单
			if(AreaDeptTreeConstants.NODE_TYPE_PROVINCE.equals(areaDeptTree.getNodeType())){
				
				
				//2009-5-26 登陆用户角色是合作伙伴省公司管理员，才能编辑或删除
				List roleList = sessionform.getRolelist();
				for(int i=0;i<roleList.size();i++){
					TawSystemSubRole tempRole = (TawSystemSubRole)roleList.get(i);
					if(tempRole.getRoleId() == roleIdList.getProvinceAdminRoleId().intValue()){
						jitem.put("allowChild", true);
						jitem.put("allowEdit", true);
						jitem.put("allowDelete", true);
					}
				}
				if(sessionform.getUserid().equals("admin")){
					jitem.put("allowChild", true);
					jitem.put("allowEdit", true);
					jitem.put("allowDelete", true);
				}
				jitem.put("allowSearchInProvince", true);
			}
			if(AreaDeptTreeConstants.NODE_TYPE_AREA.equals(areaDeptTree.getNodeType())){
				jitem.put("allowChild", false);
				jitem.put("allowNewFactory", true);
				jitem.put("allowListFactory", true);
				jitem.put("allowSearchFactory", true);
				//2009-5-26 2009-5-26 登陆用户角色是合作伙伴省公司管理员或是合作伙伴地市管理员，才能编辑或删除
				List roleList = sessionform.getRolelist();
				for(int i=0;i<roleList.size();i++){
					TawSystemSubRole tempRole = (TawSystemSubRole)roleList.get(i);
					if(tempRole.getRoleId() == roleIdList.getAreaAdminRoleId().intValue()||
							tempRole.getRoleId() == roleIdList.getProvinceAdminRoleId().intValue()){
						jitem.put("allowEdit", true);
						jitem.put("allowDelete", true);
					}
				};
				if(sessionform.getUserid().equals("admin")){
					jitem.put("allowEdit", true);
					jitem.put("allowDelete", true);
				}
				jitem.put("allowSearchInArea", true);
			}
			if(AreaDeptTreeConstants.NODE_TYPE_FACTORY.equals(areaDeptTree.getNodeType())){
				jitem.put("allowChild", false);
				jitem.put("allowEditFactory", true);
				jitem.put("allowDelFactory", false);
				jitem.put("allowSearchInFactory", true);
			}
			if(AreaDeptTreeConstants.NODE_TYPE_USER.equals(areaDeptTree.getNodeType())){
				jitem.put("allowChild", false);
				jitem.put("allowNewUser", true);
				jitem.put("allowSearchUser", true);
				//clickUrl = "${app}/partner/baseinfo/partnerUsers.do?method=search&nodeId=";
			}
			if(AreaDeptTreeConstants.NODE_TYPE_INSTRUMENT.equals(areaDeptTree.getNodeType())){
				jitem.put("allowChild", false);
				jitem.put("allowNewInstrument", true);
				jitem.put("allowSearchInstrument", true);
			}
			if(AreaDeptTreeConstants.NODE_TYPE_CAR.equals(areaDeptTree.getNodeType())){
				jitem.put("allowChild", false);
				jitem.put("allowNewCar", true);
				jitem.put("allowSearchCar", true);
			}
			if(AreaDeptTreeConstants.NODE_TYPE_OIL.equals(areaDeptTree.getNodeType())){
				jitem.put("allowChild", false);
				jitem.put("allowNewOil", true);
				jitem.put("allowSearchOil", true);
			}
//			jitem.put("allowChild", true);
//			jitem.put("allowEdit", true);
//			jitem.put("allowDelete", true);
			// 设置左键点击可触发action
//			request.setAttribute("clickUrl", clickUrl);
			jitem.put("allowClick", false);
			// 设置是否为叶子节点
			boolean leafFlag = true;
			if (areaDeptTreeMgr.isHasNextLevel(areaDeptTree.getNodeId())) {
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
	 * 新增地域部门树
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
//		String nodeType = request.getParameter("nodeType");
//		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
//		AreaDeptTree areaDeptTree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
//		if(nodeType!=null&&nodeType.equals(AreaDeptTreeConstants.NODE_TYPE_AREA)){
//			String areaId = areaDeptTree.getId();
//			String areaName = areaDeptTree.getNodeName();
//			request.setAttribute("parentNodeId", nodeId);
//			request.setAttribute("areaId", areaId);
//			request.setAttribute("areaName", areaName);
//			return mapping.findForward("editFactory");
//		}
		AreaDeptTreeForm areaDeptTreeForm = (AreaDeptTreeForm) form;
		areaDeptTreeForm.setParentNodeId(nodeId);
		updateFormBean(mapping, request, areaDeptTreeForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改地域部门树
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
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		AreaDeptTree areaDeptTree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
		AreaDeptTreeForm areaDeptTreeForm = (AreaDeptTreeForm) convert(areaDeptTree);
		updateFormBean(mapping, request, areaDeptTreeForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存地域部门树
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
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		AreaDeptTreeForm areaDeptTreeForm = (AreaDeptTreeForm) form;
		AreaDeptTree areaDeptTree = (AreaDeptTree) convert(areaDeptTreeForm);
		
		if(areaDeptTree.getParentNodeId().equals(AreaDeptTreeConstants.TREE_ROOT_ID)){
			areaDeptTree.setNodeType(AreaDeptTreeConstants.NODE_TYPE_PROVINCE);
		}
		else areaDeptTree.setNodeType(AreaDeptTreeConstants.NODE_TYPE_AREA);
		
		areaDeptTreeMgr.saveAreaDeptTree(areaDeptTree);
		return mapping.findForward("success");
	}
	
	/**
	 * 删除地域部门树
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
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		PartnerUserMgr partnerUserMgr = (PartnerUserMgr) getBean("partnerUserMgr");
		AreaDeptTree areaDeptTree = areaDeptTreeMgr.getAreaDeptTreeByNodeId(nodeId);
		List list = areaDeptTreeMgr.getLeavesByNodeId(areaDeptTree.getNodeId(), "");
		Iterator it = list.iterator();
		while(it.hasNext()){
			AreaDeptTree tree = (AreaDeptTree)it.next();
			if(tree.getNodeType().equals("user")){
				partnerUserMgr.removePartnerUserByTreeNodeId(tree.getNodeId());
			}
		}
		areaDeptTreeMgr.removeAreaDeptTreeByNodeId(nodeId);
		return mapping.findForward("success");
	}
	
	//选择省下的地市
	public ActionForward selectAreas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AreaDeptTreeMgr areaDeptTreeMgr = (AreaDeptTreeMgr) getBean("areaDeptTreeMgr");
		String type = request.getParameter("type");
		if(type!=null&&type.equals("addUser")){//新增人力信息时，只查出所有地市
			List list = areaDeptTreeMgr.getAreaByProvince("");
			request.setAttribute("areaLists", list);
			return mapping.findForward("selectAreas");
		}
		//当人员地域单表新建时，省和地市都得查出来
		List list = areaDeptTreeMgr.getAreaByProvince("");
		List provinces = areaDeptTreeMgr.getProvinceNodes();
		request.setAttribute("areaLists", list);
		request.setAttribute("provinceLists", provinces);//得到省
		return mapping.findForward("selectAreas");
	}
}