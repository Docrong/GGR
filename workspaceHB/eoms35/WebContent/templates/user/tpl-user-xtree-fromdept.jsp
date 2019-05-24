<%@ include file="/common/header_tpl_json.jsp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="com.boco.eoms.commons.ui.util.UIConstants"%>
<%@page import="com.boco.eoms.commons.system.dept.model.TawSystemDept"%>
<%@page import="com.boco.eoms.commons.system.user.model.TawSystemUser"%>
<%@page import="com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo"%>
<%@page import="com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo"%>
<% 
	List deptlist = (List)request.getAttribute("deptlist");
	List userlist = (List)request.getAttribute("userlist");		
	TawSystemUserRoleBo userrolebo = TawSystemUserRoleBo.getInstance();
	TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
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
			List flaguser = userrolebo
						.getUserBydeptids(subDept.getDeptId());
			List flagdept = deptbo.getNextLevecDepts(subDept.getDeptId(),
						"0");
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
	out.print(json);
%>

