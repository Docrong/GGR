<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<style type="text/css">
  	body{background-image:none;}
</style>

<script type="text/javascript">
Ext.onReady(function(){
	reloadFreeSpaceInfo();
})

function reloadFreeSpaceInfo() {
  var freeSpaceInfo = document.getElementById('freeSpaceInfo');
  freeSpaceInfo.innerHTML = '<%=com.boco.eoms.workbench.netdisk.webapp.util.NetDiskAttriubuteLocator.getFreeSpaceSizeInfo(((com.boco.eoms.commons.system.session.form.TawSystemSessionForm) request
				.getSession().getAttribute("sessionform")).getUserid())%>';
}
</script>

<script type="text/javascript">
  try{
	//刷新父框架中的整颗树
	parent.parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.parent.AppFrameTree.reloadNode();
  }catch(e){}
</script>

<div class="list-title">
	<B><span id="freeSpaceInfo"></span></B>
</div>
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
			<a href="javascript:if(confirm('${eoms:a2u("确定要删除该文件?")}')){var mappingName='<%=mappingName%>';var url='${app}/workbench/netdisk/tawWorkbenchFiles.do?method=deleteFile&mappingName=';url=url+mappingName;location.href=url}">${eoms:a2u('删除')}</a>
		</td>
	</tr>
</logic:iterate>

</table>
<script src="${app}/scripts/util/iframe.js" type="text/javascript"/>
<script type="text/javascript">
	colorRows("list-table");
</script>
<%@ include file="/common/footer_eoms.jsp"%>