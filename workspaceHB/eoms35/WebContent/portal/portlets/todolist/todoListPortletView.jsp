<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>

<script language="JavaScript">

  newToDO  = function (id) {  
	var portlet = Light.getPortletById(id);  
	ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
                         , "mode=EDIT"
				         , "action=add");
	}			         
    changeStatus  = function (id, name) {  
	var portlet = Light.getPortletById(id);  
	ajaxEngine.sendRequest(portlet.request
				         , "responseId="+id
				         , "tabId="+Light.portal.tabs[portlet.tIndex].tabServerId
                         , "portletId="+portlet.serverId
				         , "action=changeStatus"
				         , "name="+encodeURIComponent(name));				         
  }  
</script>
<fmt:bundle basename="resourceBundle">
<%
String responseId = (String)request.getAttribute("responseId");
%>
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0'  width='100%'>
<c:forEach var="todoList" items="${requestScope.todoLists}" >
<tr>
<c:if test="${todoList.value !='0'}">
<td class='portlet-table-td-left' width='80%' style="text-decoration: line-through;">
<input TYPE='checkbox' name='<c:out value="${todoList.name}"/>' checked='yes' value='1' onClick="javascript:changeStatus('<%= responseId %>',this.name);"><c:out value="${todoList.name}"/></input>
</c:if>
<c:if test="${todoList.value =='0'}">
<td class='portlet-table-td-left' width='80%' style="color: rgb(0, 0, 255);">
<input TYPE='checkbox' name='<c:out value="${todoList.name}"/>' value='1' onClick="javascript:changeStatus('<%= responseId %>',this.name);"><c:out value="${todoList.name}"/></input>
</c:if>
</td>
<td class='portlet-table-td-right'>
<input type="image" src="portal/light/images/edit.gif" name="<c:out value='${todoList.name}'/>" onClick="document.pressed='edit';document.parameter=this.name;document.mode='EDIT';"/>
<input type="image" src="portal/light/images/deleteLink.gif" name="<c:out value='${todoList.name}'/>" onClick="document.pressed='delete';document.parameter=this.name;"/>
</td>
</tr>
</c:forEach>
<tr>
<td class='portlet-table-td-left'>
<a href='javascript:void(0);' onClick="javascript:newToDO('<%= responseId %>');" ><fmt:message key="portlet.button.addToDo"/></a>
</td>
<td></td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>