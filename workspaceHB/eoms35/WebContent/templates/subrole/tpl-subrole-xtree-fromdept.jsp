<%@include file="/common/header_tpl_json.jsp"%>
<%@page import="com.boco.eoms.commons.system.dept.model.TawSystemDept"%>
<%@page import="com.boco.eoms.commons.system.role.model.TawSystemSubRole"%>
<% 
	List deptlist = (List)request.getAttribute("deptlist");
	List rolelist = (List)request.getAttribute("rolelist");
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
		
	if (rolelist.size() > 0) {
		for (int j = 0; j < rolelist.size(); j++) {
			TawSystemSubRole subrole = (TawSystemSubRole) rolelist.get(j);
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, subrole.getId());
			jitem.put(UIConstants.JSON_TEXT, subrole.getSubRoleName());
			jitem.put(UIConstants.JSON_NODETYPE, "subrole");
			jitem.put("iconCls", "subrole");
			jitem.put("leaf", 1);
			json.put(jitem);
		}
	}
	out.print(json);
%>