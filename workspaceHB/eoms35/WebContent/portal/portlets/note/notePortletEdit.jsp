<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
<head>
</head>
<body>

<fmt:bundle basename="resourceBundle">
<form action="<portlet:actionURL portletMode='VIEW'/>">
<table border='0' cellpadding='0' cellspacing='0'>
<tr>
<td class='portlet-table-td-left'>
<textarea name='content' class='portlet-form-textarea-field' 
 style='background:<c:out value="${requestScope.note.bgColor}"/>; color:<c:out value="${requestScope.note.color}"/>;' rows='<c:out value="${requestScope.note.height}"/>' cols='<c:out value="${requestScope.note.width}"/>' type="_moz">
<c:out value="${requestScope.note.displayContent}"/>
</textarea>
</td>
</tr>
<tr>
<td class='portlet-table-td-right'>
<input type='submit' name='action' onClick="document.pressed='save'" value='<fmt:message key="portlet.button.save"/>' class='portlet-form-button' />
<input type='submit' name='action' onClick="document.pressed='cancel'" value='<fmt:message key="portlet.button.cancel"/>' class='portlet-form-button' />
</td>
</tr>
</table>
</form>
</fmt:bundle>
</body>
</html>
