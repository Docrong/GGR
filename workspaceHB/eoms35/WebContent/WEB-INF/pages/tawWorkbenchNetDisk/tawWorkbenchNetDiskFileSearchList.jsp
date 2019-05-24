<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<script type="text/javascript">
    function delFile(mappingName,folderMappingId) {
    	//alert("aaaa");
    	if(confirm('确定要删除该文件?')){
    		var frm = document.forms[0];
    		frm.action="${app}/workbench/netdisk/searchFiles.do?method=deleteSearchFile&mappingName="+mappingName+"&folderMappingId="+folderMappingId;
    		frm.submit();
    		//var mappingName='"+file.getMappingName()+"';var url='"+path+"/workbench/netdisk/tawWorkbenchFiles.do?method=deleteSearchFile&mappingName=';url=url+mappingName;location.href=url
   		 }
    }
</script>

<fmt:bundle basename="config.ApplicationResources-workbench-netdisk">
<display:table name="netDiskFiles" cellspacing="0" cellpadding="0"
    id="netDiskFiles" pagesize="15" class="table tawCommonsAccessoriesConfigList"
    decorator="com.boco.eoms.workbench.netdisk.webapp.util.NetDiskFileTableDecorator"
    export="false" requestURI="" sort="list" >
   
    <display:column property="fileName"  sortable="true" headerClass="sortable" titleKey="tawWorkbenchNetDisk.fileName" />
    <display:column property="uploadUserName"  sortable="true" headerClass="sortable" titleKey="tawWorkbenchNetDisk.uploadUserName" />
     <display:column property="uploadTime" sortable="true" headerClass="sortable" titleKey="tawWorkbenchNetDisk.uploadTime"/>
    <display:column property="fileSize" sortable="true" headerClass="sortable" titleKey="tawWorkbenchNetDisk.fileSize"/>
    <display:column property="scanTimes" sortable="true" headerClass="sortable" titleKey="tawWorkbenchNetDisk.scanTimes"/>
    <display:column property="fileOperation" sortable="false" headerClass="sortable"  titleKey="tawWorkbenchNetDisk.fileOperation" />
    
</display:table>
</fmt:bundle>
<form action="" method="post">
	<%
		String useridsearch = request.getAttribute("userIdSearch")==null?"":(String)request.getAttribute("userIdSearch"); 
		String fileName = request.getAttribute("fileName")==null?"":(String)request.getAttribute("fileName"); 
		String uploadStratTime = request.getAttribute("uploadStratTime")==null?"":(String)request.getAttribute("uploadStratTime"); 
		String uploadEndTime = request.getAttribute("uploadEndTime")==null?"":(String)request.getAttribute("uploadEndTime"); 
		String uploadUser = request.getAttribute("uploadUser")==null?"":(String)request.getAttribute("uploadUser"); 
	%>
	<input type="hidden" name="userIdSearch" value="<%=useridsearch%>">
	<input type="hidden" name="fileName" value="<%=fileName%>">
	<input type="hidden" name="uploadStratTime" value="<%=uploadStratTime%>">
	<input type="hidden" name="uploadEndTime" value="<%=uploadEndTime%>">
	<input type="hidden" name="uploadUser" value="<%=uploadUser%>">
</form> 

<%@ include file="/common/footer_eoms.jsp"%>