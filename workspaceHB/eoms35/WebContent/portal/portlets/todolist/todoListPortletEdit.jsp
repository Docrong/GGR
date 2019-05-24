<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>
<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='EDIT'/>">
   <c:if test='${requestScope.edit == null}'>	           
	<table border='0' cellpadding='0' cellspacing='0'>
		<c:if test='${requestScope.missingField != null}'>
			<tr>
			<td class='portlet-table-td-left' colspan='2'>
			Name is required field.
			</td>
			</tr>
			</c:if>
			<tr>
			<td class='portlet-table-td-left'>
			<fmt:message key="portlet.label.todo"/>:
			</td>
			<td class='portlet-table-td-left'>
			<input type='text' name='name' class='portlet-form-input-field' size='24' value=''/>
			</td>
			</tr>			
			<tr>
			<td class='portlet-table-td-right' colspan ='2'>
			<input type='submit' name='action' onClick="document.pressed='save'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
			<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
			</td>
			</tr>
			</table>		
   </c:if>
   <c:if test='${requestScope.edit != null}'>	           
	<table border='0' cellpadding='0' cellspacing='0'>
		<c:if test='${requestScope.missingField != null}'>
			<tr>
			<td class='portlet-table-td-left' colspan='2'>
			Name is required field.
			</td>
			</tr>
			</c:if>
			<tr>
			<td class='portlet-table-td-left'>
			<fmt:message key="portlet.label.todo"/>:
			</td>
			<c:if test="${requestScope.value !='0'}">
			<td class='portlet-table-td-left' style="text-decoration: underline line-through;">
			<input type='text' name='name' class='portlet-form-input-field' size='24' value='<c:out value="${requestScope.name}"/>'/>
			</c:if>
			<c:if test="${requestScope.value =='0'}">
			<td class='portlet-table-td-left'>
			<input type='text' name='name' class='portlet-form-input-field' size='24' value='<c:out value="${requestScope.name}"/>'/>
			</c:if>
			<INPUT TYPE='hidden' NAME="oldName" value='<c:out value="${requestScope.name}"/>' />
			</td>
			</tr>			
			<tr>
			<td class='portlet-table-td-right' colspan ='2'>
			<input type='submit' name='action' onClick="document.pressed='save'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
			<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
			</td>
			</tr>
			</table>		
   </c:if>
</form>
</fmt:bundle>
</body>
</html>