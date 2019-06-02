<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.common.util.*"%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<script language="javascript">
function addattach(flag)
{
  if (tawFileUploadForm.attachName.value=="")
      return false;
  else
  {
       var fileStr= tawFileUploadForm.attachName.value;
       var index=fileStr.lastIndexOf("\\");
       fileStr=fileStr.substring(index+1,fileStr.length);
       for (i=0;i<tawFileUploadForm.attachlist.options.length;i++)
       {
         if (tawFileUploadForm.attachlist.options[i].text==fileStr)
         {
           alert("上传附件名重复，请改名后再上传。");
           return false;
         }
       }
       tawFileUploadForm.upType.value=flag;
       tawFileUploadForm.fileName.value=fileStr;
       tawFileUploadForm.submit();
       return true;
  }
}

function removeattach()
{
   if (tawFileUploadForm.attachlist.value!="")
    {
        tawFileUploadForm.action="../TawFileUpload/del.do";
        tawFileUploadForm.submit();
        return true;
    }
    else
        return false;
}
</script>
<%
String attValue="";
String attText="";
String imgValue="";
String imgText="";
Object attValueObj=request.getAttribute("attValue");
Object attTextObj=request.getAttribute("attText");
Object imgValueObj=request.getAttribute("imgValue");
Object imgTextObj=request.getAttribute("imgText");

if (attValueObj!=null)
  attValue=attValueObj.toString();

if (attTextObj!=null)
  attText=attTextObj.toString();

if (imgValueObj!=null)
  imgValue=imgValueObj.toString();

if (imgTextObj!=null)
  imgText=imgTextObj.toString();

//out.println("attValue:" + attValue);
//out.println("attText:" + attText);
//out.println("imgValue:" + imgValue);
//out.println("imgText:" + imgText);

String attValueArr[]=attValue.split(",",0);
String attTextArr[] =attText.split(",",0);
String imgValueArr[]=imgValue.split(",",0);
String imgTextArr[] =imgText.split(",",0);
%>
<body leftmargin="0" topmargin="0" class="clssclbar">
<html:form  action="/TawFileUpload/add.do" name="tawFileUploadForm" type="com.boco.eoms.kbs.controller.TawFileUploadForm" method="post" enctype="multipart/form-data">
<table align="center" width="70%" >
  <tr>
    <td valign="top" nowrap>
      <bean:message key="TawInformation.attachName"/>
    </td>
    <TD ALIGN="left" VALIGN="top">
      <table id="attachTable">
	<tr id="attachTr">
          <td>
	    <html:file property="attachName" styleClass="clstext"/>
          </td>
        </tr>
      </table>
    </TD>
    <td align="right" VALIGN="top" rowspan="2">
      <input type="button" Class="clsbtn2" VALUE=">><bean:message key="taw.attach"/>>>" onclick="return addattach(1);">
      <input type="button" Class="clsbtn2" VALUE="<<<bean:message key="label.remove"/><<" onclick='return removeattach();'>
      <%--<input type="button" Class="clsbtn2" VALUE=">><bean:message key="taw.pasteImg"/>>>" onclick='return addattach(2);'>--%>
    </td>
    <TD ALIGN="left" VALIGN="top" rowspan=2>
      <SELECT NAME="attachlist"  SIZE="4" style="font-size: 9pt;width:150">
        <%
        for (int i=0;i<attValueArr.length;i++)
        {
          if (!"".equals(attValueArr[i]))
            out.println("<OPTION value='" + attValueArr[i] + "'>" + attTextArr[i] +"</option>");
        }
        for (int i=0;i<imgValueArr.length;i++)
        {
          if (!"".equals(imgValueArr[i]))
            out.println("<OPTION value='" + imgValueArr[i] + "'>" + imgTextArr[i] +"</option>");
        }
        %>
      </SELECT>
    </td>
  </tr>
</table>
<input type=hidden name="upType" value="">
<input type=hidden name="fileName" value="">
<input type=hidden name="fileValue" value="<%=attValue%>">
<input type=hidden name="fileText" value="<%=attText%>">
<input type=hidden name="imgValue" value="<%=imgValue%>">
<input type=hidden name="imgText" value="<%=imgText%>">
</html:form>
</body>
</html>
