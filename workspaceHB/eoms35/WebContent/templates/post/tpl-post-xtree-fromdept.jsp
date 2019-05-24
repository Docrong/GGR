<%@include file="/common/header_tpl_json.jsp"%>
<%@page import="com.boco.eoms.commons.system.dept.model.TawSystemDept"%>
<%@page import="com.boco.eoms.commons.system.role.model.TawSystemPost"%>
<%@page import="com.boco.eoms.commons.system.role.service.ITawSystemPostManager"%>
<%@page import="com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<% 
	List deptlist = (List)request.getAttribute("deptlist");
	List postlist = (List)request.getAttribute("postlist");
	JSONArray json = new JSONArray();
	ITawSystemPostManager mgr = (ITawSystemPostManager) ApplicationContextHolder
					.getInstance().getBean("ItawSystemPostManager");
	TawSystemDeptBo deptbo = TawSystemDeptBo.getInstance();
	
	if (deptlist.size() > 0){
    	for (int i = 0; i < deptlist.size(); i++) {
			TawSystemDept dept = (TawSystemDept) deptlist.get(i);				
			String deptId = dept.getDeptId();
			String deptName = dept.getDeptName();
			String isPartners = dept.getIsPartners();
				
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, deptId);
			jitem.put(UIConstants.JSON_TEXT, deptName);
			jitem.put(UIConstants.JSON_NODETYPE, UIConstants.NODETYPE_DEPT);
			jitem.put("isPartners",isPartners);
			if("1".equals(isPartners)){
				jitem.put("iconCls", "partner-dept");
			}
			else{
				jitem.put("iconCls", "dept");
			}
				 
			//判断是否还有子节点
			List flagpost = mgr.getPostsByDeptId(deptId);
			List flagdept = deptbo.getNextLevecDepts(deptId,"0");
			if (flagdept == null || flagdept.size() <= 0) {
				if (flagpost == null || flagpost.size() <= 0) {
					jitem.put("leaf", 1);
				}
			} else {
				jitem.put("leaf", 0);
			}	
			json.put(jitem);
		}
	}
		
	if (postlist.size() > 0) {
		for (int j = 0; j < postlist.size(); j++) {
			TawSystemPost post = (TawSystemPost) postlist.get(j);
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, Long.toString(post.getPostId()));
			jitem.put(UIConstants.JSON_TEXT, post.getPostName());
			jitem.put(UIConstants.JSON_NODETYPE, "post");
			jitem.put("iconCls", "post");
			jitem.put("leaf", 1);
			json.put(jitem);	
		}
	}
	out.print(json);
%>

