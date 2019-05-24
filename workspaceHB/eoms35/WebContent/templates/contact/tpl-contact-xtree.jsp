<%@ include file="/common/header_tpl_json.jsp"%>
<%@page import="com.boco.eoms.workbench.contact.model.TawWorkbenchContact"%>
<%@page import="com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup"%>
<% 
	List grouplist = (List)request.getAttribute("grouplist");
	List contactlist = (List)request.getAttribute("contactlist");
	JSONArray json = new JSONArray();
			
	if (grouplist.size() > 0){
    	for (int i = 0; i < grouplist.size(); i++) {
			TawWorkbenchContactGroup group = (TawWorkbenchContactGroup) grouplist.get(i);
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, group.getGroupId());
			jitem.put(UIConstants.JSON_TEXT, group.getGroupName());
			jitem.put(UIConstants.JSON_NODETYPE, "group");
			jitem.put("leaf", 0);
			jitem.put("iconCls", "file");
			json.put(jitem);
		}
	}
		
	if (contactlist.size() > 0) {
		for (int j = 0; j < contactlist.size(); j++) {
			TawWorkbenchContact tawWorkbenchContact = (TawWorkbenchContact) contactlist.get(j);
			JSONObject jitem = new JSONObject();
			jitem.put(UIConstants.JSON_ID, tawWorkbenchContact.getId());
			jitem.put(UIConstants.JSON_TEXT, tawWorkbenchContact.getContactName());
			jitem.put(UIConstants.JSON_NODETYPE, "contact");
			jitem.put("leaf", 1);
			jitem.put("iconCls", "user");
			json.put(jitem);
		}
	}
	out.print(json);
%>
