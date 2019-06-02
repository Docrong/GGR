<%@ include file="/common/header_tpl_json.jsp"%>
<%@page import="com.boco.eoms.commons.system.dept.model.TawSystemDept"%>
<%@page import="com.boco.eoms.commons.system.user.model.TawSystemUser"%>
<%@page import="com.boco.eoms.commons.system.role.model.TawSystemSubRole"%>
<%@page import="com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager"%>
<%@page import="com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<% 
	List deptlist = (List)request.getAttribute("deptlist");
	List userlist = (List)request.getAttribute("userlist");
	List subrolelist = (List)request.getAttribute("subrolelist");
	ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
	TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
	JSONArray json = new JSONArray();	
	if (deptlist.size() > 0){
    	for (int i = 0; i < deptlist.size(); i++) {
			TawSystemDept dept = (TawSystemDept) deptlist.get(i);				
			String deptId = dept.getDeptId();
			String deptName = dept.getDeptName();
			String isPartners = dept.getIsPartners();
				
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, deptId);
			jitem.put(UIConstants.JSON_TEXT, deptName);
			jitem.put(UIConstants.JSON_NODETYPE, "dept");
			if("1".equals(isPartners)){
				jitem.put("iconCls", "partner-dept");
			}
			else{
				jitem.put("iconCls", "dept");
			}
				 
			json.put(jitem);
		}
	}
		
	if(subrolelist.size()>0){
		for (int j = 0; j < subrolelist.size(); j++) {
			TawSystemSubRole subrole = (TawSystemSubRole) subrolelist.get(j);
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, subrole.getId());
			jitem.put(UIConstants.JSON_TEXT, subrole.getSubRoleName());
			jitem.put(UIConstants.JSON_NODETYPE, "subrole");
			jitem.put("iconCls", "subrole");
			jitem.put("leaf", 1);
			json.put(jitem);
		}
	}
	if(userlist.size()>0){
		for (int n = 0; n < userlist.size(); n++) {
			TawSystemUser sysuser = (TawSystemUser) userlist.get(n);			
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, sysuser.getUserid());
			jitem.put(UIConstants.JSON_TEXT, sysuser.getUsername());
			jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_USER);
			jitem.put("leaf", 1);
			jitem.put("iconCls", "user");
			json.put(jitem);	
		}
	}
	out.print(json);
%>
