<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>

<script language="JavaScript">

  viewDelicious  = function (id, tag) {  
	var portlet = Light.getPortletById(id);  
	ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "tag="+tag);
  }  
</script>
<fmt:bundle basename="resourceBundle">
<%
String responseId = (String)request.getAttribute("responseId");
%>

<c:if test='${requestScope.delicious == null}'> 
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
Account not configured, use the Edit button to set your user name and password. 
</td>
</tr>
<tr>
<td class='portlet-table-td-left'>
If you are not a member of del.icio.us, please click <a href="http://del.icio.us/" target="_blank">http://del.icio.us/</a> to register.
</td>
</tr>
</table>
</c:if>

<c:if test='${requestScope.delicious != null}'> 
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<tr>
<td class='portlet-table-td-left'>
<a href='javascript:void(0);' name="all" onClick="javascript:viewDelicious('<%= responseId %>',this.name);" >All</a>
<c:forEach var="tag" items="${requestScope.tags}" >
<a href='javascript:void(0);' name="<c:out value='${tag.tag}'/>" onClick="javascript:viewDelicious('<%= responseId %>',this.name);" ><c:out value="${tag.tag}"/></a>
</c:forEach>
</td>
</tr>
</table>
<table border='0' cellpadding='0' cellspacing='0' width='100%'>
<c:forEach var="delicious" items="${requestScope.delicious}" >
<tr>
<td class='portlet-table-td-left' width='80%'>
<a href='<c:out value="${delicious.value}"/>' target='_blank'><c:out value="${delicious.name}"/></a>
</td>
<td class='portlet-table-td-right'>
<input type="image" src="portal/light/images/edit.gif" name="<c:out value='${delicious.name}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
<input type="image" src="portal/light/images/deleteLink.gif" name="<c:out value='${delicious.value}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</td>
</tr>
</c:forEach>
</table>
</form>
</c:if>
</fmt:bundle>
</body>
</html>