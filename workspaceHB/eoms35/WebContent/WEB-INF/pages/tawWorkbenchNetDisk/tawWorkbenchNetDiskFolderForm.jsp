<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%
String folderName = request.getAttribute("folderName").toString();
String folderPath = request.getAttribute("folderPath").toString();
String operType = request.getAttribute("operType").toString();
%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchFolderForm'});
});
</script>
<div class="list-title">
	${eoms:a2u('编辑文件夹')}
</div>

<html:form action="/tawWorkbenchFolders.do?method=saveFolder" styleId="tawWorkbenchFolderForm" method="post" onsubmit="return validate();"> 

<table>
	<tr height="30">
		<td width="30%">
			${eoms:a2u('文件夹名称')}
		</td>
		<td width="70%">
			<html:text property="folderName" styleId="folderName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入文件夹名称')}'" value="<%=folderName%>" />
		</td>
	</tr>
	<tr height="30">
		<td colspan="2">
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>

<html:hidden property="folderPath" value="<%=folderPath%>" />
<html:hidden property="operType" value="<%=operType%>" />
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>