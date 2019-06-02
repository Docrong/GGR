<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil"%>
<%@ page import="com.boco.eoms.common.util.*"%>
<%@ page import="java.io.File"%>

<jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />
<%

String action=request.getParameter("action");
String resulturl=request.getParameter("resulturl");
String name=request.getParameter("name");
String numname=request.getParameter("numname");
String delFile="";
if(resulturl!=null){
  //页码传输的中文解码问题
  //resulturl=new String(resulturl.getBytes("ISO-8859-1"), "GB2312");
  resulturl=StaticMethod.null2String(resulturl);
  }

if(action==null){
	//SmartUpload的初始化
	mySmartUpload.initialize(pageContext);
	mySmartUpload.upload();

	//获取变量
	resulturl=mySmartUpload.getRequest().getParameter("resulturl");
	action=mySmartUpload.getRequest().getParameter("action");
	name=mySmartUpload.getRequest().getParameter("name");
	numname=mySmartUpload.getRequest().getParameter("numname");
	delFile=mySmartUpload.getRequest().getParameter("delFile");
	//操作上传
		if(action.equals("add")){
			//取得所有上传的文件名称
			com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);
			//创建一个file对象 myFile
			if (!myFile.isMissing()) {
                          //获得时间参数
			Date puredate = null;
			SimpleDateFormat dateFormat = null;
			String date=null;
			puredate = new Date();
			dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			date = dateFormat.format(puredate);
/*********************存储*******************/
System.out.print(TawwpStaticVariable.wwwDir);
			//如果文件存在，则存储
			myFile.saveAs(TawwpStaticVariable.wwwDir+"workplan/tawwpfile/tempfileupload/"+date+"."+myFile.getFileExt(), mySmartUpload.SAVE_PHYSICAL);
				resulturl=resulturl+","+myFile.getFileName()+"@../tawwpfile/tempfileupload/"+date+"."+myFile.getFileExt();
			}
	}
/**********************删除*****************/
if(delFile!=null){
                String[] d=delFile.split(",");
		for(int i=0;i<d.length;i++)
		{
			if (!d[i].equals("")){
			String[] delete=d[i].split("@");
			File myDelFile = new File(TawwpStaticVariable.wwwDir+"workplan/upload/"+TawwpUtil.getFileName(delete[1]));
                        myDelFile.delete();
			}
		}
	}
}

%>
<html>
<head>
<title>附件管理</title>
<link rel="stylesheet" href="../css/table_style.css" type="text/css">
<STYLE><!--
body,td{font-family:arial;font-size:12px}

//-->
</STYLE>
<script language="JavaScript">
var callerWindowObj = dialogArguments;
function Transfer() {
	callerWindowObj.document.forms[0].<%=name%>.value=uploadform.resulturl.value;
	callerWindowObj.document.forms[0].<%=numname%>.value=uploadform.num.value;

	}

function WinClose() {
	window.close();
	}
<%if (!action.equals("read")){%>
function checkdelete(){
  var bolCheck= false;
  var myform=document.uploadform;
  myform.submitBut.disabled=false;
  if (myform.elements[0].name!="ListDelete")
  {

  }
  else{
  myform.resulturl.value="";
  myform.delFile.value="";
    if(myform.ListDelete.length>=0){
      var listNum=myform.ListDelete.length;
      for(i=0;i<listNum;i++){
          if(!myform.ListDelete[i].checked)
          {
		if(myform.resulturl.value==""){
		 myform.resulturl.value = myform.ListDelete[i].value;
		}else{
		myform.resulturl.value = myform.resulturl.value+","+myform.ListDelete[i].value;
		}
          }else{
            myform.submitBut.disabled=true;
            	if(myform.delFile.value==""){
		 myform.delFile.value = myform.ListDelete[i].value;
		}else{
		myform.delFile.value = myform.delFile.value+","+myform.ListDelete[i].value;
		}

            }
       }

    }else{
	if(!myform.ListDelete.checked)
          {
		myform.resulturl.value = myform.resulturl.value+","+myform.ListDelete.value;
          }else{
           	myform.submitBut.disabled=true;
		myform.delFile.value = myform.delFile.value+","+myform.ListDelete.value;
            }
	}
  }
}
<%}%>
</script>
</head>
<%if (!action.equals("read")){
  System.out.print("read");
  %>
<body onload="checkdelete();Transfer()">
  <%}else{%>
<body>
    <%}%>
<base target="_self">
            <table cellpadding="0" cellspacing="0" border="0" width="100%">
            <tr>
              <td class="table_title" width="80%" valign="middle">
                附加表附件管理
              </td>
            </tr>
          </table>
<table align=center border=0 cellspacing="1" cellpadding="1" class="table_show">
  <form method="post" enctype="multipart/form-data" name="uploadform" action="./fileupload.jsp">
    <tr class="tr_show">
	  <td width="30%" class="td_label"> <%if (!action.equals("read")){%>附件删除<%}else{%>附件下载<%}%>： </td>
	<td>
		<%
                String[] n=null;
		String[] a=resulturl.split(",");
		int num=0;
		for(int i=0;i<a.length;i++)
		{
			if (!a[i].equals("")){
			if (!action.equals("read")){
                       n=a[i].split("@");
                       if(n.length==2){
                        if(!n[0].equals("")||!n[1].equals("")||n[0]!=null||n[1]!=null){
                          out.println("<input type=\"checkbox\" name=ListDelete value=\""+a[i]+"\" onclick=\"checkdelete()\"><a target=\"_blank\" href=\""+n[1]+"\">"+n[0]+"</a>");
                          }
                       }
                       //附件数量
			num++;
			}else{
                          n=a[i].split("@");
                          if(n.length==2){
                        if(!n[0].equals("")||!n[1].equals("")||n[0]!=null||n[1]!=null){
                          out.println("<li><a target=\"_blank\" href=\""+n[1]+"\">"+n[0]+"</a>");
                        }
                          }
                       //附件数量
			num++;
  			}
			}
		}
		%>
	<input type="hidden" name="resulturl" VALUE="<%=resulturl%>">
	<input type="hidden" name="action" value="add">
	<input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="delFile" value="">
	<input type="hidden" name="numname" value="<%=numname%>">
	<input type="hidden" name="num" value="<%=num%>">
	</td>
</tr>
<%if (!action.equals("read")){%>
<tr class="tr_show">
	  <td class="td_label"> 上传附件： </td>
	  <td>
        <input type='file' name='File' size='20' maxlength='20'>
	</td>
</tr>
<tr class="tr_show">
	<td colspan="2" align=center>
		<input type="hidden" name="action" value="add">
		<input type="submit" value="确认">
		<input type="button" name="submitBut" value="完成" onclick="Transfer();WinClose();">
	</td>
</tr>
<%}%>
</form>
</table>
</BODY>
</HTML>
