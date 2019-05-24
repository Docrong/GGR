<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.filemanager.extra.fileupload.UploadFileInfo,
                 com.boco.eoms.filemanager.extra.fileupload.RecordFileInfo"%>

<jsp:useBean id="mySmartUpload" scope="page" class="com.boco.eoms.filemanager.extra.fileupload.SmartUpload" />
<%

    String projectId="-1";
    int fileId=-1;
//    if (MultipartFormDataRequest.isMultipartFormData(request))
//	      {
//			// Uses MultipartFormDataRequest to parse the HTTP request.
//			MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
//			projectId = mrequest.getParameter("projectId");
//    }

    UploadFileInfo info=new UploadFileInfo();

	// Variables
	int count=0;

	String filename="";
	String virName="";

	//save file path
	String filePath="/upload";
    try{
	// Initialization
	mySmartUpload.initialize(pageContext);

	// Upload
	mySmartUpload.upload();
    String[] projectIds=mySmartUpload.getRequest().getParameterValues("projectId");
    projectId=projectIds[0];

	// Select each file
	for (int i=0;i<mySmartUpload.getFiles().getCount();i++){

		// Retreive the current file
		com.boco.eoms.filemanager.extra.fileupload.File myFile = mySmartUpload.getFiles().getFile(i);
		// Save it only if this file exists
		if (!myFile.isMissing()) {
            String fileRealPath=request.getRealPath("").toString().replace('\\', '/') + filePath;
            info.setFileName(myFile.getFileName());
            info.setFileExt(myFile.getFileExt());
            info.setFilePath(filePath);
            info.setProjectId(Integer.parseInt(projectId));
            info.setProjectTypeId(1);
            RecordFileInfo record=new RecordFileInfo(info);
            fileId=record.getFileId();
			myFile.saveAs(fileRealPath + "/"+record.getFileRealName(), mySmartUpload.SAVE_PHYSICAL);
			filename=myFile.getFileName();

			count ++;

		}

	}
    }
    catch(Exception e){
        System.out.println(e);
    }

	// Display the number of files which could be uploaded
	//out.println("<BR>" + mySmartUpload.getFiles().getCount() + " files could be uploaded.<BR>");

	// Display the number of files uploaded
	//out.println(count + " file(s) uploaded.");
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>noPrivilege</title>
    <LINK rel="stylesheet" type="text/css" href="<%=session.getAttribute("RootURL")%>/css/general.css"/>
<STYLE>
        .LinkComponent{behavior: url("../behavior/LinkComponent.htc");}
        .hide {display: none}
        TD{font:9pt;}
        A{color:blue;}
        BUTTON{cursor:hand;}
    </STYLE>
</head>
<SCRIPT language="javascript">
parent.document.forms[0].filepath.value="<%=filePath%>";
if(parent.document.forms[0].filename.value=="")
	{
		parent.document.forms[0].filename.value="<%=filename%>";
		parent.document.forms[0].virname.value="<%=virName%>";
	}
else    {
		parent.document.forms[0].filename.value+=",<%=filename%>";
		parent.document.forms[0].virname.value+=",<%=virName%>";
	}
alert("已上传文件："+"<%=filename%>");
parent.document.all.showUploadFile.innerHTML='<iframe name="upfiles" frameborder=0 width=100%  height="100%" scrolling=yes src=projUpload/modUploadFile.jsp?id=<%=projectId%>></iframe>';
parent.document.all.file.innerHTML='<iframe name="continue" frameborder=0 width=100% height="100%" scrolling=no src=projUpload/uploadfile.jsp?id=<%=projectId%>></iframe>';
</SCRIPT>
</html>
