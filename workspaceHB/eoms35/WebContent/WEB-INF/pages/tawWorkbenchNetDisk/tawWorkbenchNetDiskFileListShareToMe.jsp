<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<div class="list-title">
	${eoms:a2u('文件列表')}
</div>
<div class="list">
<table class="listTable" id="list-table">
	<tr height="30" class="header">
		<td nowrap="nowrap" width="20%">
			${eoms:a2u('文件名')}
		</td>
		<td nowrap="nowrap" width="20%">
			${eoms:a2u('上传时间')}
		</td>
		<td nowrap="nowrap" width="20%">
			${eoms:a2u('文件大小')}
		</td>
		<td nowrap="nowrap" width="20%">
			${eoms:a2u('下载次数')}
		</td>
		<td nowrap="nowrap" width="20%">
			${eoms:a2u('操作')}
		</td>
	</tr>
<logic:iterate id="fileIt" name="fileIt">
	<tr height="30">
		<bean:define id="mappingName" name="fileIt" property="mappingName"/>
		<bean:define id="userId" name="fileIt" property="userId"/>
		<td nowrap="nowrap" width="20%">
			<bean:write name="fileIt" property="fileName"/>
		</td>
		<td nowrap="nowrap" width="20%">
			<bean:write name="fileIt" property="uploadTime"/>
		</td>
		<td nowrap="nowrap" width="20%">
			<bean:write name="fileIt" property="fileSize"/> KB
		</td>
		<td nowrap="nowrap" width="20%">
			<bean:write name="fileIt" property="scanTimes"/>
		</td>
		<td nowrap="nowrap" width="20%">
			<a href="${app}/workbench/netdisk/tawWorkbenchFiles.do?method=download&mappingName=<%=mappingName%>&userId=<%=userId%>">${eoms:a2u('下载')}</a>
		</td>
	</tr>
</logic:iterate>

</table>
</div>
<script type="text/javascript">
	colorRows("list-table");
</script>
<%@ include file="/common/footer_eoms.jsp"%>