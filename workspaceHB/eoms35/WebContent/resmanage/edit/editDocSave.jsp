<%@page contentType="text/html;charset=ISO8859_1"%>
<%@page import="java.util.*,java.io.File"%>
<%@page import="com.jspsmart.upload.*"%>
<%@page import="com.boco.eoms.resmanage.entity.*"%>
<%@page import="mcs.common.db.*"%>
<%@include file="../power.jsp"%>
<jsp:useBean id="myUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />
<%
 if(!bflag)
  out.println("<script>alert('您已经掉线，请重新登陆！');parent.location='../index.jsp';</script>");
 request.setCharacterEncoding("GBK");

RoomOpt roomopt = new RoomOpt();
Vector docVec = new Vector();
myUpload.initialize(pageContext);
myUpload.upload();
String docPath="";
if(myUpload.getRequest().getParameter("docPath") != null)
	docPath = myUpload.getRequest().getParameter("docPath");
String type = myUpload.getRequest().getParameter("type");
int cityFlag=0;
if(myUpload.getRequest().getParameter("cityflag") != null)
	cityFlag = Integer.parseInt(myUpload.getRequest().getParameter("cityflag"));
String cc_dis  = myUpload.getRequest().getParameter("cc_dis");
String cc_data  = myUpload.getRequest().getParameter("cc_data");
String cityId  = myUpload.getRequest().getParameter("fi_city");
String cc_memo = myUpload.getRequest().getParameter("cc_memo");
int count=0;
String uploaddir ="";
if (cityFlag == 0)
{
	uploaddir = docPath;
}
else
{
	uploaddir = docPath + cityId+"/";
}
try{
//上载文件
  for (int i=0;i<myUpload.getFiles().getCount();i++) 
	  {
		com.jspsmart.upload.File file = myUpload.getFiles().getFile(i);
		String fileName2 = file.getFileName() ;
		String fileName=new String(fileName2.getBytes("GB2312"),"GBK");
		if (!file.isMissing())
			{
				file.saveAs(uploaddir + fileName2);
				String cc_docName = file.getFileName();
				//String cc_doc = uploaddir + file.getFileName();
				/*out.println("FieldName = " + file.getFieldName() + "<BR>");
				out.println("Size = " + file.getSize() + "<BR>");
				out.println("FileName = " + file.getFileName() + "<BR>");
				out.println("FileExt = " + file.getFileExt() + "<BR>");
				out.println("FilePathName = " + file.getFilePathName() + "<BR>");
				out.println("ContentType = " + file.getContentType() + "<BR>");
				out.println("ContentDisp = " + file.getContentDisp() + "<BR>");
				out.println("TypeMIME = " + file.getTypeMIME() + "<BR>");
				out.println("SubTypeMIME = " + file.getSubTypeMIME() + "<BR>");
				*/
				count++;
				DocClass docclass = new DocClass();
				docclass.setCc_name(cc_docName);
				docclass.setCc_data(cc_data);
				docclass.setFi_city(Integer.parseInt(cityId));
				docclass.setFi_doc_type(Integer.parseInt(type));
				docclass.setCc_discription(cc_dis);
				docclass.setCc_memo(cc_memo);
				docVec.addElement(docclass);
				int row = roomopt.docOpt(docVec);
			}
		
	 }
} catch (Exception e) 
	{
        out.println(e.toString());
    }
	//out.println(count + " file(s) uploaded.");
%>
<%
String retpage = null;
if(count >0)
	retpage = "editDocList.jsp";
else
	retpage = "editDocInsert.jsp";
%>
<body onload="returnInput()">
   <form action="<%=retpage%>" method=POST name=editSaveForm>
	<input type=hidden name=type value='<%=type%>'>
	<input type=hidden name=cityflag value='<%=cityFlag%>'>
	<input type=hidden name=docPath value='<%=docPath%>'>
	</form>
	</body>
<script>
function returnInput()
{
	var count=<%=count%>;
	if (count>0)
		alert("文档上传成功！");
	else
		alert("文档上传失败！");
	editSaveForm.submit();
}
</script>