<%@ include file="/common/taglibs.jsp"%>
<%@page import="java.util.List" %>  
<%@page import="java.util.ArrayList" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.base.util.StaticVariable" %>
<%@page import="com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager" %>
<%@page import="com.boco.eoms.commons.system.priv.bo.TawSystemAssignBo" %>
<%@page import="com.boco.eoms.commons.system.priv.model.TawSystemPrivRegion" %>
<%@page import="com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<%
String objId = StaticMethod.null2String(request.getParameter("objId"));
String objType = StaticMethod.null2String(request.getParameter("nodeType"));
String regionType = StaticVariable.PRIV_TYPE_REGION_CPTROOM;

if("user".equalsIgnoreCase(objType)){
	objType = StaticVariable.PRIV_ASSIGNTYPE_USER;
}
else if("subrole".equalsIgnoreCase(objType)){
	objType = StaticVariable.PRIV_ASSIGNTYPE_ROLE;
}
System.out.println(objId+"-"+objType+"-"+regionType);
TawSystemAssignBo assignbo = TawSystemAssignBo.getInstance();
List list = assignbo.getPermissions(objId,objType,regionType);

ArrayList savedRegions = new ArrayList();

for (int i = 0; i < list.size(); i++) {
	TawSystemPrivRegion assign = (TawSystemPrivRegion)list.get(i);
	savedRegions.add(assign.getRegionid());
}
%>

<form id="cptRoomForm" method="post" action="${app}/priv/tawSystemPrivAssigns.do?method=xSaveRegionConfig">
  <input type="hidden" name="objId" value="<%=objId%>">
  <input type="hidden" name="objType" value="<%=objType%>">
  <input type="hidden" name="regionType" value="<%=regionType%>">
  <table class="listTable">
    <tr>
      <td class="checkColumn header">
    	<input type="checkbox" onclick="eoms.util.checkAll(this,'regionId')"/>
	  </td>
	<td class="header">${eoms:a2u('请选择机房域(点击全选)')}</td></tr>
<%	
	ITawSystemCptroomManager cptroommr = (ITawSystemCptroomManager)ApplicationContextHolder.getInstance().getBean("ItawSystemCptroomManager");
	TawSystemCptroom cptroom = new TawSystemCptroom();
	List roomlist = cptroommr.getTawSystemCptrooms(cptroom);
	for (int i = 0; i < roomlist.size(); i++) {
		TawSystemCptroom room = (TawSystemCptroom) roomlist.get(i);
		String roomName = room.getRoomname();
		Integer roomId = room.getId();
		String checked = "";
		if(StaticMethod.fHasId(roomId.intValue(),savedRegions)){
			checked = "checked=\"true\"";
		}
		out.println("<tr><td class=\"checkColumn\"><input type=\"checkbox\" name=\"regionId\" value=\""+roomId+"\" "+checked+"></td><td>"+roomName+"</td></tr>");
	}
%>
	<tr><td colspan="2"><input type="submit" value="${eoms:a2u('提交')}" class="btn"/></td></tr>
  </table>
</form>
<%@ include file="/common/footer_eoms_innerpage.jsp"%>
