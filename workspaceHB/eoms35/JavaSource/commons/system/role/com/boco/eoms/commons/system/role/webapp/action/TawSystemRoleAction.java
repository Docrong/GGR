// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TawSystemRoleAction.java

package com.boco.eoms.commons.system.role.webapp.action;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.*;
import com.boco.eoms.commons.system.dict.service.impl.ID2NameServiceFactory;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.*;
import com.boco.eoms.commons.system.role.util.RoleFilter;
import com.boco.eoms.commons.system.role.util.RoleMapSchema;
import com.boco.eoms.commons.system.role.webapp.form.TawSystemRoleForm;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.taglib.ChooserTag;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.*;
import org.displaytag.util.ParamEncoder;

public final class TawSystemRoleAction extends BaseAction
{

	public TawSystemRoleAction()
	{
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return mapping.findForward("search");
	}

	public ActionForward addNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		request.setAttribute("RoleId", new Long(tawSystemRoleForm.getRoleId()));
		try
		{
			long parentId = tawSystemRoleForm.getRoleId();
			tawSystemRoleForm.setParentId(parentId);
		}
		catch (Exception e)
		{
			return mapping.findForward("failure");
		}
		return mapping.findForward("add");
	}

	public ActionForward subRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		long roleId = tawSystemRoleForm.getRoleId();
		String pageIndexName = (new ParamEncoder("tawSystemSubRoleList")).encodeParameterName("p");
		Integer pageSize = new Integer(25);
		Integer pageIndex = new Integer(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 0 : Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
		Map map = mgr.getTawSystemSubRoles(pageIndex, pageSize, "roleId=" + roleId);
		List list = (List)map.get("result");
		request.setAttribute("tawSystemSubRoleList", list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("roleId", new Long(roleId));
		return mapping.findForward("subRolelist");
	}

	public ActionForward addSubRole(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		request.setAttribute("roleId", new Long(tawSystemRoleForm.getRoleId()));
		return mapping.findForward("addSubRole");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ActionMessages messages = new ActionMessages();
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		mgr.removeTawSystemRole(tawSystemRoleForm.getRoleId());
		messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemRole.deleted"));
		saveMessages(request.getSession(), messages);
		request.setAttribute("lastEditId", new Long(tawSystemRoleForm.getRoleId()));
		return mapping.findForward("search");
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		long id = StaticMethod.null2Long(request.getParameter("id"));
		if (id > 0L)
		{
			ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
			TawSystemRole tawSystemRole = mgr.getTawSystemRole(id);
			tawSystemRoleForm = (TawSystemRoleForm)convert(tawSystemRole);
			updateFormBean(mapping, request, tawSystemRoleForm);
			request.setAttribute("workflowFlag", String.valueOf(tawSystemRole.getWorkflowFlag()));
			request.setAttribute("roleId", String.valueOf(tawSystemRole.getParentId()));
		}
		return mapping.findForward("edit");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ActionMessages messages = new ActionMessages();
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		boolean isNew = tawSystemRoleForm.getRoleId() == 0L;
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		TawSystemRole tawSystemRole = (TawSystemRole)convert(tawSystemRoleForm);
		tawSystemRole.setDeleted(Integer.valueOf("0"));
		if (tawSystemRole.getLeaf() == null)
			tawSystemRole.setLeaf(Integer.valueOf("1"));
		mgr.saveTawSystemRole(tawSystemRole);
		mgr.setLeaf(tawSystemRole.getParentId(), Integer.valueOf("0"));
		if (isNew)
		{
			messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemRole.added"));
			saveMessages(request.getSession(), messages);
			request.setAttribute("lastNewId", new Long(tawSystemRoleForm.getRoleId()));
			return mapping.findForward("search");
		} else
		{
			messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemRole.updated"));
			saveMessages(request, messages);
			request.setAttribute("lastEditId", new Long(tawSystemRoleForm.getRoleId()));
			return mapping.findForward("search");
		}
	}

	public ActionForward saveNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		ActionMessages messages = new ActionMessages();
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		boolean isNew = tawSystemRoleForm.getRoleId() == 0L;
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		TawSystemRole tawSystemRole = (TawSystemRole)convert(tawSystemRoleForm);
		tawSystemRole.setDeleted(Integer.valueOf("0"));
		tawSystemRole.setSingleId(StaticMethod.getCurrentDateTime());
		tawSystemRole.setLeaf(Integer.valueOf("1"));
		if (tawSystemRole.getParentId() > 0L)
			tawSystemRole.setStructureFlag(mgr.getNewStructureFlag(tawSystemRole.getParentId()));
		mgr.saveTawSystemRole(tawSystemRole);
		mgr.setLeaf(tawSystemRole.getParentId(), Integer.valueOf("0"));
		if (isNew)
		{
			messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemRole.added"));
			saveMessages(request.getSession(), messages);
			request.setAttribute("lastNewId", new Long(tawSystemRoleForm.getRoleId()));
			return mapping.findForward("search");
		} else
		{
			messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage("tawSystemRole.updated"));
			saveMessages(request, messages);
			request.setAttribute("lastEditId", new Long(tawSystemRoleForm.getRoleId()));
			return mapping.findForward("search");
		}
	}

	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm actionform = (TawSystemRoleForm)form;
		try
		{
			int postId = StaticMethod.null2int(request.getParameter("dept"));
			ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
			TawSystemRole orgRole = mgr.getTawSystemRole(postId, 0);
			if (orgRole == null)
				actionform.setStrutsAction(4);
		}
		catch (Exception e)
		{
			return mapping.findForward("failure");
		}
		return mapping.findForward("list");
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		return search(mapping, form, request, response);
	}

	public void getNodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
	      long nodeId = StaticMethod.null2Long(request.getParameter("node"));
	      String roleTypeId = request.getParameter("roleTypeId");
	      ITawSystemRoleManager roleMgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
	      ArrayList list = new ArrayList();
	      if("1".equals(roleTypeId))
	      {
	          int workflowFlag = (int)nodeId;
	          list = (ArrayList)roleMgr.getFlwRolesByWorkflowFlag(workflowFlag);
	      } else
	      {
	          list = (ArrayList)roleMgr.getSysRolesByRoleId(nodeId);
	      }
	      JSONArray jsonRoot = new JSONArray();
	   
	      String dept = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("ignoreDeptId"));
	      String roleid = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("ignoreRoleId"));
	      for(int i = 0; i < list.size(); i++)
	      {
	          TawSystemRole role = (TawSystemRole)list.get(i);
	          String roleName = String.valueOf(role.getRoleName());
	          String note = StaticMethod.null2String(role.getNotes());
	          //角色的权限控制 add by weichao begin 20130624
	          String roleId = String.valueOf(role.getRoleId());        
	          JSONObject jitem = roleMgr.getJSON4TreeNode(role);
	          if(roleid.equals(roleId)){
	        	  TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
	        	  String deptId = sessionform.getDeptid();
	        	  if(dept.contains(",")){
	        		  String[] deptArray = dept.split(",");
	            	  boolean flag = false;
	            	  List deptList = Arrays.asList(deptArray);
	            	  if(deptList.contains(deptId)){
	            		  flag = true;
	            	  }
	            	  /*for(int m=0;m<deptList.length;m++){
	            		  String right = deptList[m];
	            		  if(right.equals(deptId)){
	            			  flag = true;
	            			  break;
	            		  }
	            	  }*/
	            	  if(flag){
	            		  jitem.put("qtip", "角色ID: " + role.getRoleId() + ("".equals(note) ? "" : "<br/>备注:" + note));
	                      jitem.put("qtipTitle", roleName);
	                      jsonRoot.put(jitem);
	            	  }
	        	  }else{
	        		  if(deptId.equals(dept)){
	        			  jitem.put("qtip", "角色ID: " + role.getRoleId() + ("".equals(note) ? "" : "<br/>备注:" + note));
	                      jitem.put("qtipTitle", roleName);
	                      jsonRoot.put(jitem);
	        		  }
	        	  }
	        	 
	        	  
	          }else{
	        	  jitem.put("qtip", "角色ID: " + role.getRoleId() + ("".equals(note) ? "" : "<br/>备注:" + note));
	              jitem.put("qtipTitle", roleName);
	              jsonRoot.put(jitem);
	          }         
	         
	      }

	      JSONUtil.print(response, jsonRoot.toString());
	}

	public void xsave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		TawSystemRole tawSystemRole = (TawSystemRole)convert(tawSystemRoleForm);
		tawSystemRole.setSingleId(StaticMethod.getCurrentDateTime());
		long parentId = tawSystemRole.getParentId();
		String roleTypeId = String.valueOf(tawSystemRole.getRoleTypeId());
		if (("2".equals(roleTypeId) || "3".equals(roleTypeId)) && parentId > 0L)
			tawSystemRole.setStructureFlag(mgr.getNewStructureFlag(parentId));
		mgr.saveTawSystemRole(tawSystemRole);
		if (("2".equals(roleTypeId) || "3".equals(roleTypeId)) && parentId > 0L)
			mgr.setLeaf(parentId, new Integer(0));
		JSONUtil.success(response, "success");
	}

	public void xdel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		long id = StaticMethod.null2Long(request.getParameter("id"));
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		mgr.removeTawSystemRole(id);
		JSONUtil.success(response, "success");
	}

	public void xget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		long id = StaticMethod.null2Long(request.getParameter("id"));
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		ITawSystemUserRefRoleManager refMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
		ITawSystemUserManager ugr = (ITawSystemUserManager)getBean("itawSystemUserManager");
		TawSystemRole tawSystemRole = mgr.getTawSystemRole(id);
		Map map = refMgr.getGroupUserbyroleid(String.valueOf(id));
		JSONArray userList = new JSONArray();
		JSONObject jitem;
		for (Iterator it = map.keySet().iterator(); it.hasNext(); userList.put(jitem))
		{
			String userId = it.next().toString();
			TawSystemUser user = ugr.getUserByuserid(userId);
			String userName = user.getUsername();
			String groupType = (String)map.get(userId);
			String text = "2".equals(groupType) ? userName + "(" + "组长" + ")" : userName;
			jitem = new JSONObject();
			jitem.put("id", user.getUserid());
			jitem.put("text", text);
			jitem.put("name", userName);
			jitem.put("grouptype", groupType);
		}

		JSONObject jsonRoot = new JSONObject();
		jsonRoot = JSONObject.fromObject(tawSystemRole);
		jsonRoot.put("users", userList);
		jsonRoot.remove("leaf");
		jsonRoot.put("leaf", tawSystemRole.getLeaf().equals(new Integer(1)) ? 1 : 0);
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void xedit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		TawSystemRoleForm tawSystemRoleForm = (TawSystemRoleForm)form;
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		TawSystemRole tawSystemRole = (TawSystemRole)convert(tawSystemRoleForm);
		tawSystemRole.setLeaf(tawSystemRoleForm.getLeaf());
		mgr.saveTawSystemRole(tawSystemRole);
		JSONUtil.success(response, "success");
	}

	public void xGetSubRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String roleId = StaticMethod.null2String(request.getParameter("id"));
		String deptId = StaticMethod.null2String(request.getParameter("deptId"));
		String type1 = StaticMethod.null2String(request.getParameter("class1"));
		String type2 = StaticMethod.null2String(request.getParameter("class2"));
		String type3 = StaticMethod.null2String(request.getParameter("class3"));
		String type4 = StaticMethod.null2String(request.getParameter("class4"));
		String query = StaticMethod.null2String(request.getParameter("q"));
		String isShowUser = StaticMethod.null2String(request.getParameter("isShowUser"));
		Integer start = new Integer(StaticMethod.nullObject2int(request.getParameter("start")));
		Integer limit = new Integer(StaticMethod.nullObject2int(request.getParameter("limit")));
		String whereStr = "";
		if (!"".equals(roleId))
		{
			whereStr = whereStr + "roleId=" + roleId;
			if (!"".equals(deptId))
				whereStr = whereStr + " and deptId='" + deptId + "'";
			if (!"".equals(type1))
				whereStr = whereStr + " and type1='" + type1 + "'";
			if (!"".equals(type2))
				whereStr = whereStr + " and type2='" + type2 + "'";
			if (!"".equals(type3))
				whereStr = whereStr + " and type3='" + type3 + "'";
			if (!"".equals(type4))
				whereStr = whereStr + " and type4='" + type4 + "'";
			if (!"".equals(query))
				whereStr = whereStr + " and UPPER(subRoleName) like UPPER('%" + query + "%')";
		}
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
		Map map = mgr.mapTawSystemSubRoles(start, limit, whereStr);
		List list = (List)map.get("result");
		Integer total = (Integer)map.get("total");
		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
		JSONArray jsonList = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			TawSystemSubRole subrole = (TawSystemSubRole)list.get(i);
			String deptName = ID2NameServiceFactory.getId2nameServiceDB().id2Name(subrole.getDeptId(), "tawSystemDeptDao");
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("subRoleName", subrole.getSubRoleName());
			jitem.put("deptId", subrole.getDeptId());
			jitem.put("deptName", deptName);
			jitem.put("roleId", subrole.getRoleId());
			jitem.put("type1", subrole.getType1());
			jitem.put("type2", subrole.getType2());
			jitem.put("type3", subrole.getType3());
			jitem.put("type4", subrole.getType4());
			if ("true".equals(isShowUser))
			{
				List userList = userMgr.getUserbyroleid(subrole.getId());
				JSONArray userArray = new JSONArray();
				for (int j = 0; j < userList.size(); j++)
				{
					TawSystemUser user = (TawSystemUser)userList.get(j);
					JSONObject useritem = new JSONObject();
					useritem.put("userid", user.getUserid());
					useritem.put("username", user.getUsername());
					userArray.put(useritem);
				}

				jitem.put("userList", userArray);
			}
			jsonList.put(jitem);
		}

		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("total", total);
		jsonRoot.put("rows", jsonList);
		JSONUtil.print(response, jsonRoot.toString());
	}

	public void resetChooserRoleId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String chooserId = StaticMethod.null2String(request.getParameter("chooserId"));
		long roleId = StaticMethod.null2Long(request.getParameter("roleId"));
		ITawSystemRoleManager mgr = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		String roleName = mgr.getRoleNameById(roleId);
		String html = ChooserTag.getDeptList(chooserId, roleId);
		String data = "{newRoleName:'" + roleName + "',filterHTML:'" + html + "'}";
		JSONUtil.print(response, data);
	}

	public ActionForward xSearchSubRoleNodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String roleId = StaticMethod.null2String(request.getParameter("roleId"));
		String deptId = StaticMethod.null2String(request.getParameter("deptId"));
		String type1 = StaticMethod.null2String(request.getParameter("class1"));
		String type2 = StaticMethod.null2String(request.getParameter("class2"));
		String type3 = StaticMethod.null2String(request.getParameter("class3"));
		String type4 = StaticMethod.null2String(request.getParameter("class4"));
		String node = StaticMethod.null2String(request.getParameter("node"));
		String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
		String template = StaticMethod.null2String(request.getParameter("tpl"));
		IRoleMgr roleMgr = (IRoleMgr)getBean("RoleMgrFlush");
		TawSystemRole role = roleMgr.getRole(roleId);
		boolean isVirtual = String.valueOf(role.getRoleTypeId()).equals("3");
		String whereStr = "";
		if (!"".equals(roleId))
		{
			whereStr = whereStr + "roleId=" + roleId;
			if (!"".equals(deptId) && request.getParameter("allDept") == null)
				whereStr = whereStr + " and deptId in(" + deptId + ")";
			if (!"".equals(type1) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type1='" + type1 + "'";
			if (!"".equals(type2) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type2='" + type2 + "'";
			if (!"".equals(type3) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type3='" + type3 + "'";
			if (!"".equals(type4) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type4='" + type4 + "'";
		}
		List list = new ArrayList();
		if ("root".equals(nodeType))
		{
			ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
			list = mgr.getTawSystemSubRoles(whereStr);
			template = "tpl-subrole-xtree";
			if (isVirtual)
				request.setAttribute("nodeTemplate", "node-with-leader");
		} else
		if ("subrole".equals(nodeType))
		{
			ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
			list = userMgr.getUserbyroleid(node);
			if (isVirtual)
			{
				String leader[] = roleMgr.getRoleLeaderBySubRoleid(node);
				if (leader != null && leader.length > 0)
				{
					list = leaderSorter(list, leader[0]);
					request.setAttribute("nodeTemplate", "node-with-leader");
					request.setAttribute("leaderId", leader[0]);
				}
			}
			template = "tpl-user-xtree";
		}
		request.setAttribute("list", list);
		return mapping.findForward(template);
	}

	public ActionForward xGetSubRoleNodesFromFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String node = StaticMethod.null2String(request.getParameter("node"));
		String parentNode = StaticMethod.null2String(request.getParameter("parentNode"));
		String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
		String template = StaticMethod.null2String(request.getParameter("tpl"));
		List list = new ArrayList();
		ITawSystemRoleManager roleManager = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		if ("root".equals(nodeType))
		{
			int workflowFlag = StaticMethod.null2int(node);
			list = (ArrayList)roleManager.getFlwRolesByWorkflowFlag(workflowFlag);
			request.setAttribute("defaultLeaf", new Integer(0));
			template = "tpl-role-xtree";
		} else
		if ("role".equals(nodeType))
		{
			list = roleManager.getAreaByRoleId(StaticMethod.null2Long(node));
			TawSystemArea areanull = new TawSystemArea();
			areanull.setAreaid("null");
			areanull.setAreaname("其他");
			areanull.setLeaf("0");
			list.add(areanull);
			template = "tpl-area-xtree";
		} else
		if ("area".equals(nodeType))
		{
			String whereStr = "deptId =" + node;
			if (!"".equals(parentNode))
				whereStr = whereStr + " and roleId=" + parentNode;
			ITawSystemSubRoleManager subrolemgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
			list = subrolemgr.getTawSystemSubRoles(whereStr);
			template = "tpl-subrole-xtree";
		} else
		if ("subrole".equals(nodeType))
		{
			ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
			list = userMgr.getUserbyroleid(node);
			template = "tpl-user-xtree";
		}
		request.setAttribute("list", list);
		return mapping.findForward(template);
	}

	public ActionForward xGetSubRoleNodesFromArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String node = StaticMethod.nullObject2String(request.getParameter("node"));
		String nodeType = StaticMethod.nullObject2String(request.getParameter("nodeType"));
		String template = StaticMethod.nullObject2String(request.getParameter("tpl"));
		String roleId = StaticMethod.nullObject2String(request.getParameter("roleId"));
		String flowId = StaticMethod.nullObject2String(request.getParameter("flowId"));
		String ifSpecial = StaticMethod.nullObject2String(request.getParameter("ifSpecial"));
		List list = new ArrayList();
		ITawSystemRoleManager roleManager = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		ITawSystemSubRoleManager subroleManager = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		ITawSystemAreaManager areaManager = (ITawSystemAreaManager) getBean("ItawSystemAreaManager");
		TawSystemRole role = roleManager.getTawSystemRole(StaticMethod.null2Long(roleId));
		String ignoreRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("ignoreRoleId"));
		boolean isVirtual = String.valueOf(role.getRoleTypeId()).equals("3");
		if ("root".equals(nodeType))
		{
			System.out.println("lizhi:root:ifSpecial="+ifSpecial);
			if (ignoreRoleId.equals(roleId) && !"".equals(ifSpecial) && !"undefined".equals(ifSpecial)) {
				TawSystemArea tawsystemarea = areaManager.getAreaByAreaId(ifSpecial);
				list.add(tawsystemarea);
			} else {
				list = roleManager.getAreaByRoleId(StaticMethod.null2Long(roleId));
				TawSystemArea areanull = new TawSystemArea();
				areanull.setAreaid("null");
				areanull.setAreaname("其他");
				areanull.setLeaf("0");
				list.add(areanull);
			}
			template = "tpl-area-xtree";
		} else
		if ("area".equals(nodeType))
		{
			System.out.println("lizhi:area:node="+node+"roleId="+roleId);
			if (ignoreRoleId.equals(roleId)) {
				list = subroleManager.getCountyByRoleId(node, Integer.parseInt(roleId));
				template = "tpl-county-xtree";
			} else {
				if ("".equalsIgnoreCase(node) || "null".equalsIgnoreCase(node))
					node = null;
				if (flowId != null)
				{
					int iflowId = Integer.parseInt(flowId);
					flowId = (new StringBuffer(String.valueOf(iflowId))).toString();
				}
				List filters = RoleMapSchema.getInstance().getRoleMappingListById(flowId);
				if (filters != null && filters.size() > 0)
				{
					RoleFilter filter = (RoleFilter)filters.get(0);
					String dictId = filter.getDictId();
					ITawSystemDictTypeManager _objDictManager = (ITawSystemDictTypeManager)getBean("ItawSystemDictTypeManager");
					list = _objDictManager.listDict(dictId, roleId, node);
				}
				IRoleMgr mgr = (IRoleMgr)getBean("RoleMgrImpl");
				List subrolelist = null;
				if (filters != null)
					subrolelist = mgr.listSubRoleWithType1Null(node, roleId);
				else
					subrolelist = mgr.listSubRole(node, Integer.parseInt(roleId));
				request.setAttribute("subrolelist", subrolelist);
				template = "tpl-dict-xtree-subrole";
			}
		} else
			if ("county".equals(nodeType))
			{
				System.out.println("lizhi:county:node="+node+"roleId="+roleId);
				if ("".equalsIgnoreCase(node) || "null".equalsIgnoreCase(node))
					node = null;
				List subrolelist = null;
					subrolelist = subroleManager.listCountySubRole(node, Integer.parseInt(roleId));
				request.setAttribute("subrolelist", subrolelist);
				template = "tpl-dict-xtree-subrole";
			} else
		if ("dict".equals(nodeType))
		{
			String areaId = "";
			if ("area".equals(request.getParameter("parentNodeType")))
				areaId = StaticMethod.null2String(request.getParameter("parentNode"));
			StringBuffer whereSb = new StringBuffer();
			if (!areaId.equals("") && !areaId.equals("null"))
				whereSb.append("deptId='" + areaId + "'");
			else
				whereSb.append(" (deptId='' or deptId is null or deptId='null') ");
			if (!node.equals("") && !node.equals("null"))
				whereSb.append(" and type1='" + node + "' and roleId=" + roleId);
			else
				whereSb.append(" and (type1='' or type1 is null or type1='null') and roleId=" + roleId);
			ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
			list = mgr.getTawSystemSubRoles(whereSb.toString());
			template = "tpl-subrole-xtree";
			if (isVirtual)
				request.setAttribute("nodeTemplate", "node-with-leader");
		} else
		if ("subrole".equals(nodeType))
		{
			ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
			list = userMgr.getUserbyroleid(node);
			if (isVirtual)
			{
				IRoleMgr subroleMgr = (IRoleMgr)getBean("RoleMgrFlush");
				String leader[] = subroleMgr.getRoleLeaderBySubRoleid(node);
				if (leader != null && leader.length > 0)
				{
					list = leaderSorter(list, leader[0]);
					request.setAttribute("nodeTemplate", "node-with-leader");
					request.setAttribute("leaderId", leader[0]);
				}
			}
			template = "tpl-user-xtree";
		}
		request.setAttribute("list", list);
		return mapping.findForward(template);
	}

	public ActionForward xTreeSubRoleByArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		String node = StaticMethod.null2String(request.getParameter("node"));
		String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
		String template = StaticMethod.null2String(request.getParameter("tpl"));
		String roleId = StaticMethod.null2String(request.getParameter("roleId"));
		String flowId = StaticMethod.null2String(request.getParameter("flowId"));
		if (flowId != null)
		{
			int iflowId = Integer.parseInt(flowId);
			flowId = (new StringBuffer(String.valueOf(iflowId))).toString();
		}
		List list = new ArrayList();
		ITawSystemRoleManager roleManager = (ITawSystemRoleManager)getBean("ItawSystemRoleManager");
		TawSystemRole role = roleManager.getTawSystemRole(StaticMethod.null2Long(roleId));
		boolean isVirtual = String.valueOf(role.getRoleTypeId()).equals("3");
		List filters = RoleMapSchema.getInstance().getRoleMappingListById(flowId);
		if ("root".equals(nodeType))
		{
			list = roleManager.listFirstLevelAreaByRoleId(StaticMethod.null2Long(roleId));
			request.setAttribute("nodeTemplate", "node-with-other");
			template = "tpl-area-xtree";
		} else
		if ("other".equals(nodeType))
		{
			list = listDictFolders(roleId, filters, null);
			List subrolelist = listDirectSubroles(roleId, filters, null);
			request.setAttribute("subrolelist", subrolelist);
			template = "tpl-dict-xtree-subrole";
		} else
		if ("area".equals(nodeType))
		{
			List dictlist = listDictFolders(roleId, filters, node);
			List subrolelist = listDirectSubroles(roleId, filters, node);
			request.setAttribute("dictlist", dictlist);
			request.setAttribute("subrolelist", subrolelist);
			request.setAttribute("nodeTemplate", "area-dict-subrole");
			template = "tpl-area-xtree";
		} else
		if ("dict".equals(nodeType))
		{
			String areaId = "";
			if ("area".equals(request.getParameter("parentNodeType")))
				areaId = StaticMethod.null2String(request.getParameter("parentNode"));
			list = listSubrolesByAreaDict(roleId, areaId, node);
			template = "tpl-subrole-xtree";
			if (isVirtual)
				request.setAttribute("nodeTemplate", "node-with-leader");
		} else
		if ("subrole".equals(nodeType))
		{
			ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
			list = userMgr.getUserbyroleid(node);
			if (isVirtual)
			{
				IRoleMgr subroleMgr = (IRoleMgr)getBean("RoleMgrFlush");
				String leader[] = subroleMgr.getRoleLeaderBySubRoleid(node);
				if (leader != null && leader.length > 0)
				{
					list = leaderSorter(list, leader[0]);
					request.setAttribute("nodeTemplate", "node-with-leader");
					request.setAttribute("leaderId", leader[0]);
				}
			}
			template = "tpl-user-xtree";
		}
		request.setAttribute("list", list);
		return mapping.findForward(template);
	}
	
	public void xGetSubRoleNodes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String roleId = StaticMethod.null2String(request.getParameter("roleId"));
		String deptId = StaticMethod.null2String(request.getParameter("deptId"));
		String type1 = StaticMethod.null2String(request.getParameter("class1"));
		String type2 = StaticMethod.null2String(request.getParameter("class2"));
		String type3 = StaticMethod.null2String(request.getParameter("class3"));
		String type4 = StaticMethod.null2String(request.getParameter("class4"));
		String isShowUser = StaticMethod.null2String(request.getParameter("isShowUser"));
		String whereStr = "";
		if (!"".equals(roleId))
		{
			whereStr = whereStr + "roleId=" + roleId;
			if (!"".equals(deptId) && request.getParameter("allDept") == null)
				whereStr = whereStr + " and deptId in(" + deptId + ")";
			if (!"".equals(type1) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type1='" + type1 + "'";
			if (!"".equals(type2) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type2='" + type2 + "'";
			if (!"".equals(type3) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type3='" + type3 + "'";
			if (!"".equals(type4) && request.getParameter("allClass") == null)
				whereStr = whereStr + " and type4='" + type4 + "'";
		}
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
		List list = mgr.getTawSystemSubRoles(whereStr);
		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
		JSONArray jsonList = new JSONArray();
		int listSize = list.size();
		for (int i = 0; i < listSize; i++)
		{
			TawSystemSubRole subrole = (TawSystemSubRole)list.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("text", subrole.getSubRoleName());
			jitem.put("name", subrole.getSubRoleName());
			jitem.put("iconCls", "subrole");
			jitem.put("nodeType", "subrole");
			if ("true".equals(isShowUser))
			{
				List userList = userMgr.getUserbyroleid(subrole.getId());
				Map map = userMgr.getGroupUserbyroleid(subrole.getId());
				JSONArray userArray = new JSONArray();
				int userListSize = userList.size();
				for (int j = 0; j < userListSize; j++)
				{
					TawSystemUser user = (TawSystemUser)userList.get(j);
					JSONObject useritem = new JSONObject();
					String userId = user.getUserid();
					String userName = user.getUsername();
					useritem.put("id", userId);
					useritem.put("name", userName);
					useritem.put("text", userName);
					useritem.put("groupType", map.get(userId));
					String groupType = StaticMethod.nullObject2String(map.get(userId), "1");
					if (groupType.equals("2"))
					{
						jitem.put("leaderId", userId);
						jitem.put("leaderName", userName);
						jitem.put("info", "(组长:" + userName + ")");
					}
					useritem.put("iconCls", "user");
					useritem.put("nodeType", "user");
					useritem.put("leaf", true);
					userArray.put(useritem);
				}

				jitem.put("user", userArray);
				if (userList.size() == 0)
					jitem.put("leaf", true);
			}
			jsonList.put(jitem);
		}

		JSONUtil.print(response, jsonList.toString());
	}

	public void getAllWorkflow(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		JSONArray jsonRoot = new JSONArray();
		List list = new ArrayList();
		try
		{
			list = DictMgrLocator.getDictService().getDictItems("dict-wfworksheet#allworkflow");
		}
		catch (Exception exception) { }
		JSONObject j;
		for (Iterator it = list.iterator(); it.hasNext(); jsonRoot.put(j))
		{
			DictItemXML workflow = (DictItemXML)it.next();
			String workflowId = workflow.getId();
			String workflowName = workflow.getName();
			j = new JSONObject();
			j.put("id", workflowId);
			j.put("text", workflowName);
			j.put("nodeType", "workflow");
		}

		JSONUtil.print(response, jsonRoot.toString());
	}

	public void xGetSubRoleListMatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		String roleId = StaticMethod.null2String(request.getParameter("id"));
		String deptId = StaticMethod.null2String(request.getParameter("deptId"));
		String type1 = StaticMethod.null2String(request.getParameter("class1"));
		String isShowUser = StaticMethod.null2String(request.getParameter("isShowUser"));
		Integer pageIndex = new Integer(StaticMethod.nullObject2int(request.getParameter("start")));
		Integer pageSize = new Integer(StaticMethod.nullObject2int(request.getParameter("limit")));
		String whereStr = "";
		if (!"".equals(roleId))
		{
			whereStr = whereStr + "roleId=" + roleId;
			if (!"".equals(deptId))
				whereStr = whereStr + " and deptId in(" + deptId + ")";
			if (!"".equals(type1))
				whereStr = whereStr + " and type1 in(" + type1 + ")";
		}
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
		Map map = mgr.getTawSystemSubRoles(pageIndex, pageSize, whereStr);
		List list = (List)map.get("result");
		Integer total = (Integer)map.get("total");
		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager)getBean("itawSystemUserRefRoleManager");
		JSONArray jsonList = new JSONArray();
		for (int i = 0; i < list.size(); i++)
		{
			TawSystemSubRole subrole = (TawSystemSubRole)list.get(i);
			String deptName = ID2NameServiceFactory.getId2nameServiceDB().id2Name(subrole.getDeptId(), "tawSystemDeptDao");
			JSONObject jitem = new JSONObject();
			jitem.put("id", subrole.getId());
			jitem.put("subRoleName", subrole.getSubRoleName());
			jitem.put("deptId", subrole.getDeptId());
			jitem.put("deptName", deptName);
			jitem.put("roleId", subrole.getRoleId());
			jitem.put("type1", subrole.getType1());
			jitem.put("type2", subrole.getType2());
			jitem.put("type3", subrole.getType3());
			jitem.put("type4", subrole.getType4());
			if ("true".equals(isShowUser))
			{
				List userList = userMgr.getUserbyroleid(subrole.getId());
				JSONArray userArray = new JSONArray();
				for (int j = 0; j < userList.size(); j++)
				{
					TawSystemUser user = (TawSystemUser)userList.get(j);
					JSONObject useritem = new JSONObject();
					useritem.put("id", user.getUserid());
					useritem.put("name", user.getUsername());
					useritem.put("text", user.getUsername());
					useritem.put("leader", false);
					useritem.put("iconCls", "user");
					useritem.put("nodeType", "user");
					useritem.put("leaf", true);
					userArray.put(useritem);
				}

				jitem.put("user", userArray);
			}
			jsonList.put(jitem);
		}

		JSONObject jsonRoot = new JSONObject();
		jsonRoot.put("total", total);
		jsonRoot.put("rows", jsonList);
		JSONUtil.print(response, jsonRoot.toString());
	}

	private List leaderSorter(List userlist, String leaderId)
		throws Exception
	{
		final String _leaderId = leaderId;
		Collections.sort(userlist, new Comparator() {

			public int compare(Object a, Object b)
			{
				String ida = ((TawSystemUser)a).getUserid();
				String idb = ((TawSystemUser)b).getUserid();
				if (_leaderId.equals(ida) && !_leaderId.equals(idb))
					return -1;
				return _leaderId.equals(ida) || !_leaderId.equals(idb) ? 0 : 1;
			}

		});
		return userlist;
	}

	private List listDictFolders(String roleId, List filters, String areaId)
	throws Exception
	{
		List list = new ArrayList();
		if (filters != null && filters.size() > 0)
		{
			RoleFilter filter = (RoleFilter)filters.get(0);
			String dictId = filter.getDictId();
			ITawSystemDictTypeManager _objDictManager = (ITawSystemDictTypeManager)getBean("ItawSystemDictTypeManager");
			list = _objDictManager.listDict(dictId, roleId, areaId);
		}
		return list;
	}

	private List listDirectSubroles(String roleId, List filters, String areaId)
		throws Exception
	{
		List list = null;
		IRoleMgr mgr = (IRoleMgr)getBean("RoleMgrImpl");
		if (filters != null)
			list = mgr.listSubRoleWithType1Null(areaId, roleId);
		else
			list = mgr.listSubRole(areaId, Integer.parseInt(roleId));
		return list;
	}

	private List listSubrolesByAreaDict(String roleId, String areaId, String dictId)
		throws Exception
	{
		List list = null;
		StringBuffer whereSb = new StringBuffer();
		if (!areaId.equals("") && !areaId.equals("null"))
			whereSb.append("deptId='" + areaId + "'");
		else
			whereSb.append(" (deptId='' or deptId is null or deptId='null') ");
		if (!dictId.equals("") && !dictId.equals("null"))
			whereSb.append(" and type1='" + dictId + "' and roleId=" + roleId);
		else
			whereSb.append(" and (type1='' or type1 is null or type1='null') and roleId=" + roleId);
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager)getBean("ItawSystemSubRoleManager");
		list = mgr.getTawSystemSubRoles(whereSb.toString());
		return list;
	}

	/**
	 * 获取大角色下的
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listSubrolesReUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
	String roleId = StaticMethod.null2String(request.getParameter("roleId"));
	String node = StaticMethod.null2String(request.getParameter("node"));
	String nodeType = StaticMethod.null2String(request.getParameter("nodeType"));
	JSONArray jsonList = new JSONArray();

	String whereStr = "";
	if (!"".equals(roleId)) {
		whereStr += "roleId=" + roleId;
		
	}
	 if("1".equals(node)){
		ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) getBean("ItawSystemSubRoleManager");
		List list = mgr.getTawSystemSubRoles(whereStr);
		if(list!=null){
			int listSize = list.size();
			for (int i = 0; i < listSize; i++) {
				TawSystemSubRole subrole = (TawSystemSubRole) list.get(i);
				JSONObject jitem = new JSONObject();
				jitem.put("id", subrole.getId());
				jitem.put("text", subrole.getSubRoleName());
				jitem.put("name", subrole.getSubRoleName());
				jitem.put("nodeType", "subrole");
				jitem.put("iconCls", "subrole");
				jsonList.put(jitem);
			}
		}
	}
	else{
		ITawSystemUserRefRoleManager userMgr = (ITawSystemUserRefRoleManager) getBean("itawSystemUserRefRoleManager");
		List userList = userMgr.getUserbyroleid(node);
		if(userList!=null){
			int userListSize = userList.size();
			for (int j = 0; j < userListSize; j++) {
				TawSystemUser user = (TawSystemUser) userList.get(j);
				JSONObject useritem = new JSONObject();
				String userId = user.getUserid();
				String userName = user.getUsername();
				useritem.put("id", userId);
				useritem.put("name", userName);
				useritem.put("text", userName);
				useritem.put("nodeType", "user");
				useritem.put("leaf", true);
				jsonList.put(useritem);
			}
		}
	}
	JSONUtil.print(response, jsonList.toString());

	}
}
