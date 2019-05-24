/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.userdefinegroup.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import atg.taglib.json.util.JSONObject;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.service.ITawSystemRoleManager;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.sheet.base.service.ITawSystemWorkflowManager;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.userdefinegroup.model.UserdefineGroup;
import com.boco.eoms.sheet.userdefinegroup.service.IUserdefineGroupManager;




/**
 * @author 
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserdefineGroupAction extends BaseAction {

    /**
	 * 显示配置文件页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showUserdefineGroupPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserdefineGroup UserdefineGroup = new UserdefineGroup();
		request.setAttribute("UserdefineGroup", UserdefineGroup);
		return mapping.findForward("showUserdefineGroupPage");
	}
	
	 /**
	 * 处理配置
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
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		Map pagemap = request.getParameterMap();
		
		UserdefineGroup group = new UserdefineGroup();
		SheetBeanUtils.populateMap2Bean(group, pagemap);
		if (group.getId() == null || group.getId().equals("")) {
			group.setId(UUIDHexGenerator.getInstance().getID());
		} 	
		group.setCreateDate(new Date());
		group.setUserId(sessionform.getUserid());
		IUserdefineGroupManager UserdefineGroupManager = (IUserdefineGroupManager) ApplicationContextHolder.getInstance().getBean("iUserdefineGroupManager");
		UserdefineGroupManager.saveObject(group);
		
		return mapping.findForward("success");
	}
	
	 /**
	 * 栓查是否有重复的记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void checkRepeateRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map condition = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		
		StringBuffer where = new StringBuffer();
		where.append("where flowName ='").append(flowName).append("'").append(" and userId = '").append(sessionform.getUserid()).append("'");
		if (!id.equals("")) {
			where.append(" and id <> '").append(id).append("'");
		}
		//拼出条件SQL语句
		condition.put("where", where.toString());
		Integer pageSize = new Integer(-1);
		int[] aTotal = { 0 };
		IUserdefineGroupManager UserdefineGroupManager = (IUserdefineGroupManager) ApplicationContextHolder.getInstance().getBean("iUserdefineGroupManager");
		UserdefineGroupManager.getObjectsByCondtion(new Integer(0), pageSize, aTotal, condition,"number");
		
		JSONObject jsonRoot = new JSONObject();
		int allnumber = aTotal[0];
		jsonRoot.put("number",  allnumber);
		JSONUtil.print(response, jsonRoot.toString());
	}
	
	/**
	 * 得到所有的配置文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder.getInstance().getBean("SheetAttributes")).getPageSize();
        //页数的参数名
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 当前页数
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		// 分页取得列表
		int[] aTotal = { 0 };


		String orderCondition = " where userId = '"+ sessionform.getUserid() +"' order by createDate desc";

		
		Map condition = new HashMap();
		condition.put("where", orderCondition);

		String exportType = StaticMethod.null2String(request.getParameter(new org.displaytag.util.ParamEncoder("taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		IUserdefineGroupManager UserdefineGroupManager = (IUserdefineGroupManager) ApplicationContextHolder.getInstance().getBean("iUserdefineGroupManager");
		
		List list = UserdefineGroupManager.getObjectsByCondtion(pageIndex, pageSize, aTotal, condition, "recorde");
		request.setAttribute("taskList", list);
		request.setAttribute("total", new Integer(aTotal[0]));
		request.setAttribute("pageSize", pageSize);
		
		return mapping.findForward("list");
	}
	
	/**
	 * 编辑配置文件
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
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		IUserdefineGroupManager UserdefineGroupManager = (IUserdefineGroupManager) ApplicationContextHolder.getInstance().getBean("iUserdefineGroupManager");
		UserdefineGroup UserdefineGroup = (UserdefineGroup)UserdefineGroupManager.getObject(UserdefineGroup.class, id);
		request.setAttribute("UserdefineGroup", UserdefineGroup);
		request.setAttribute("type", type);
		return mapping.findForward("showUserdefineGroupPage");
	}
	
	/**
	 * 删除
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
		String id = request.getParameter("id");
		IUserdefineGroupManager UserdefineGroupManager = (IUserdefineGroupManager) ApplicationContextHolder.getInstance().getBean("iUserdefineGroupManager");
		UserdefineGroupManager.removeObject(UserdefineGroup.class, id);
		return mapping.findForward("success");
	}
	
	/**
	 * 查找根据条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("success");
	}
	
	/**
	 * 查找根据条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findFlow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ITawSystemWorkflowManager tawSystemWorkFlowService = (ITawSystemWorkflowManager) ApplicationContextHolder.getInstance().getBean("ITawSystemWorkflowManager");
		List workflows = tawSystemWorkFlowService.getTawSystemWorkflows();
		request.setAttribute("total",new Integer(workflows.size()));
		request.setAttribute("pageSize", new Integer(100));
		request.setAttribute("taskList", workflows);
		return mapping.findForward("workflows");
	}
	
	
	public ActionForward showTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		
		String flowId = request.getParameter("flowId");
		StringBuffer where = new StringBuffer();
		where.append("where flowId ='").append(flowId).append("'").append(" and userId = '").append(sessionform.getUserid()).append("'");
		Map condition = new HashMap();
		//拼出条件SQL语句
		condition.put("where", where.toString());
		Integer pageSize = new Integer(-1);
		int[] aTotal = { 0 };
		IUserdefineGroupManager userdefineGroupManager = (IUserdefineGroupManager) ApplicationContextHolder.getInstance().getBean("iUserdefineGroupManager");
		List list  = userdefineGroupManager.getObjectsByCondtion(new Integer(0), pageSize, aTotal, condition,"recorder");
		ID2NameService service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		UserdefineGroup usergroup = null;
		if (list.size() > 0) {
			usergroup = (UserdefineGroup)list.iterator().next();
		}
		if (usergroup != null) {
			
			String type = StaticMethod.null2String(request.getParameter("node"));
			String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
//			如果是子角色
			if (type.equals("folder-subrole")) {
				String roleIds =  usergroup.getDealRoleId();
				if (roleIds == null) roleIds = "";
				StringBuffer whereString = new StringBuffer();
				String[] roleIdArray = roleIds.split(",");
				for (int i = 0; i < roleIdArray.length; i ++) {
					whereString.append(" id = '").append(roleIdArray[i]).append("'");
					if (whereString.length() > 0 && i < (roleIdArray.length - 1) ) {
						whereString.append(" or ");
					}
				}
				
				List roles = userdefineGroupManager.getRolesByCondition(" where " + whereString.toString());
				request.setAttribute("roles", roles);
				request.setAttribute("nodetype", "subRoleList");
				
			}
			
			//如果是子角色下的人员
			if (nodeType.equals("subrole")) {
				String subRoleId = StaticMethod.null2String(request.getParameter("node"));
				StringBuffer whereString = new StringBuffer();
				whereString.append(" where subRoleid = '").append(subRoleId).append("'");
				List userRefRoles = userdefineGroupManager.getUserBySubRoleCondtion(whereString.toString());
				List users = new ArrayList();
				for (Iterator iterator = userRefRoles.iterator(); iterator.hasNext(); ) {
					TawSystemUserRefRole userrefrole = (TawSystemUserRefRole)iterator.next();
					String userId = userrefrole.getUserid();
					String userName = service.id2Name(userId,"tawSystemUserDao");
					userrefrole.setRemark(userName);
					users.add(userrefrole);
				}
				request.setAttribute("subusers", users);
				request.setAttribute("nodetype", "roleuserList");
			}
			
			//如果是部门的
			if (type.equals("folder-dept")) {
				String deptIds =  usergroup.getDealDeptId();
				StringBuffer whereString = new StringBuffer();
				if (deptIds == null) deptIds = "";
				String[] deptIdArray = deptIds.split(",");
				for (int i = 0; i < deptIdArray.length; i ++) {
					whereString.append(" deptId = '").append(deptIdArray[i]).append("'");
					if (whereString.length() > 0 && i < (deptIdArray.length - 1) ) {
						whereString.append(" or ");
					}
				}
				List depts = userdefineGroupManager.getDeptsByCondition(" where " + whereString.toString());
				request.setAttribute("depts", depts);

				request.setAttribute("nodetype", "deptList");
			}
			
			//如果是人员的
			if (type.equals("folder-user")) {
				String userIds =  usergroup.getDealUserId();
				if (userIds == null) userIds = "";
				StringBuffer whereString = new StringBuffer();
				String[] userIdArray = userIds.split(",");
				for (int i = 0; i < userIdArray.length; i ++) {
					whereString.append(" userId = '").append(userIdArray[i]).append("'");
					if (whereString.length() > 0 && i < (userIdArray.length - 1) ) {
						whereString.append(" or ");
					}
				}
				List users = userdefineGroupManager.getUsersByCondition(" where " + whereString.toString());
				request.setAttribute("users", users);
				request.setAttribute("nodetype", "userList");
			}
			
			
			//如果是自定义的
			if (type.equals(usergroup.getDefineName())) {
				String deptIds =  usergroup.getDealDeptId();
				StringBuffer whereString = new StringBuffer();
				if (deptIds == null) deptIds = "";
				String[] deptIdArray = deptIds.split(",");
				for (int i = 0; i < deptIdArray.length; i ++) {
					whereString.append(" deptId = '").append(deptIdArray[i]).append("'");
					if (whereString.length() > 0 && i < (deptIdArray.length - 1) ) {
						whereString.append(" or ");
					}
				}
				List depts = userdefineGroupManager.getDeptsByCondition(" where " + whereString.toString());
				request.setAttribute("defines", depts);

				request.setAttribute("nodetype", "defineList");
			}
			
			//如果是根目录
			if (type.equals("-1")) {
				
//				StringBuffer jsonext = new StringBuffer();
//				jsonext.append("[{'id':'folder-subrole','text':'角色','leaf':0,'nodeType':'folder'},{'id':'folder-dept','text':'部门','leaf':0,'nodeType':'folder'},{'id':'folder-user','text':'人员','leaf':0,'nodeType':'folder'}");
//				for(int i =0;i<list.size();i++){
//					if(list.size()>i){
//						jsonext.append(",");
//					}
//					usergroup = (UserdefineGroup)list.get(i);
//					jsonext.append("{'id':'").append(usergroup.getId()).append("','text':'").append(usergroup.getDefineName()).append("','leaf':0,'nodeType':'folder'}");
//				}
//				jsonext.append("]");
				request.setAttribute("jsonext",list);

				return mapping.findForward("tpl-floder-xtree");
			}

			return mapping.findForward("tpl-role-dept-user-xtree");
			
		} else {
			request.setAttribute("novalue", "novalue");
		}

		return mapping.findForward("tpl-floder-xtree");
	}
	
	

}
