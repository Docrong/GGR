<%@ page contentType="text/html; charset=gb2312" language="java"
import="java.util.*,com.boco.eoms.filemanage.*,com.boco.eoms.filemanage.appinfo.*,
java.io.File,com.boco.eoms.attachment.dao.TawAttachmentDAO,
com.boco.eoms.attachment.model.TawAttachment,com.boco.eoms.common.util.StaticMethod,
com.boco.eoms.db.util.ConnectionPool" errorPage="" %>

<head>
<title>文件上传处理页面</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body topmargin="0" leftmargin="0">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
<%
String fileIdList = "";
String[] fileIds;

AppInformations appInformations = AppInformations.getInstance();
AppInformation aAppInformation = appInformations.get(request.getParameter("app"));

fileIdList = request.getParameter("filelist");
fileIdList = fileIdList == null?"":fileIdList;
ConnectionPool ds=com.boco.eoms.db.util.ConnectionPool.getInstance();
TawAttachmentDAO tawAttachmentDAO = new TawAttachmentDAO(ds);


if( fileIdList!=null)
{
fileIds = fileIdList.split(",");
request.setAttribute("filelist",fileIdList);
}
else
{
request.setAttribute("filelist","");
}

List list = null;

if(!fileIdList.equals(""))
{
  list = tawAttachmentDAO.list(fileIdList);
}

TawAttachment tawAttachment = null;
if(list != null)
{
for(int i=0; i<list.size(); i++)
{
  tawAttachment = (TawAttachment)list.get(i);
%>
<tr class="tr_show">
	<td><a href='do_download.jsp?app=<%=aAppInformation.getAppCode()%>&fileid=<%=tawAttachment.getAttachmentId()%>'>
    <img src="../images/img/page.gif" border="0" align="absmiddle"><%=StaticMethod.dbNull2String(tawAttachment.getAttachmentName())%>
  </a></td>
</tr>

<%
}
}
%>
</table>
</body>
