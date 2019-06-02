<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.filemanager.extra.fileupload.RecordFileInfo,
                 java.sql.ResultSet,
                 java.sql.Connection,
                 com.boco.eoms.db.util.ConnectionPool,
                 com.boco.eoms.filemanager.SchemeMgrDAO,
                 com.boco.eoms.filemanager.extra.fileupload.FileInfo"%>
<%@ page import="java.util.*"%>
<jsp:directive.page import="com.boco.eoms.filemanager.extra.fileupload.RecordFileInfo"/>
<jsp:directive.page import="com.boco.eoms.filemanager.extra.fileupload.FileInfo"/>
<META name="MS.LOCALE" content="ZH-CN">
<META http-equiv="Content-Type" content="text/html; charset=gb2312">
<html><head><title>修改</title>
    <LINK rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/general.css"/>
    <Script>
    function modFile(ownerId,fileType,fileId,filename,flag)
    {
        if (confirm("确定要删除'"+filename+"'吗？")) {
        	if(flag == "1"){
        		alert("文件不能少于1个，不能删除！");
        	}else{
           		 window.location.href="<%=request.getContextPath()%>/filemanager/fileUpload/modUploadFile.jsp?ownerId="+ownerId+"&fileType="+fileType+"&fileId="+fileId;
                 }
        }
    }
    </Script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body bgcolor="#D5E4FD">
<script>
 var show="";
<%
String delete =request.getParameter("delete");
String fileType =request.getParameter("fileType");
String ownerId=request.getParameter("ownerId");
String realPath=request.getRealPath("").toString();
String  fileId=request.getParameter("fileId");
String flag = "0";
if(fileId==null)        //删除文件
{
 fileId="-1"; 
}
SchemeMgrDAO dao=new SchemeMgrDAO();
Vector fileList=dao.deleteFileInfo(realPath,fileType,ownerId,fileId);
System.out.print(fileList.size());
if(fileList.size() < 2){
 		flag = "1";
 	}
for(int i=0;i<fileList.size();i++){
    FileInfo info=(FileInfo)fileList.elementAt(i);
    if("0".equals(delete)){
 %>
 show+='<%=info.getFileRealName()%><br>';
 <%
 }
 else{
 %>
 show+='<A href="javascript:modFile(<%=ownerId%>,<%=fileType%>,<%=info.getFileId()%>,'+"'<%=info.getFileRealName()%>'"+',<%=flag%>)"><%=info.getFileRealName()%></a><br>';
 <%
 }
 }
  dao.release();
 %>
 document.write(show);
 </script>
</body>
</html>