<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.util.*,java.io.File,
                 com.boco.eoms.commons.accessories.service.*,
                 com.boco.eoms.commons.accessories.model.*,
                 com.boco.eoms.base.util.StaticMethod,
                 com.boco.eoms.base.util.ApplicationContextHolder"%>

<head>
  <title>文件展现页面</title>
</head>
<link rel="stylesheet" type="text/css" media="all" href="${app}/styles/default/theme.css" />
<body topmargin="0" leftmargin="0" style="background-image:none">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
 String fileIdList = StaticMethod.nullObject2String(request.getParameter("filelist"));

 System.out.println("fileIdList="+fileIdList);
 String[] fileIds=fileIdList.split(",");
 fileIdList="";
 for(int k=0;k<fileIds.length;k++){
   fileIdList=fileIdList+fileIds[k]+",";
 }
  System.out.println("fileIdList="+fileIdList);
 fileIdList=fileIdList.substring(0,fileIdList.length()-1);
  System.out.println("fileIdList="+fileIdList);
 List list = null;
 String fileid = "";
 ITawCommonsAccessoriesManager mgr = 
    (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
 if(!fileIdList.equals(""))
 {
   list =mgr.getAllFileById(fileIdList);
 }

 if(list != null) {
   for(int i=0; i<list.size(); i++)
 {
   TawCommonsAccessories accessories=(TawCommonsAccessories)list.get(i);
   fileid = fileid + "," + accessories.getId();
%>
<tr>
<td>
<script language="javascript">
document.write("<a href=\"${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=<%=accessories.getId()%>&filelist=<%=fileIdList%>\">");
document.write("<img src=\"${app}/images/page.gif\" border=\"0\" align=\"absmiddle\"><%=StaticMethod.nullObject2String(accessories.getAccessoriesCnName())%></a>");
</script>
</td>
</tr>

<%
} 
}

%>
<ul>
<c:if test="${noteInfo != null}">
<li class="error">
	<img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
	${noteInfo}
</li>
</c:if>
</ul>
</table>

</body>
