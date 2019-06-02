
<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.common.util.*"%>

 
 
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
       //alert(tawFileUploadForm.fileName);
       
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

Object attValueObj=request.getAttribute("attValue") ==null?StaticMethod.dbNull2String(request.getParameter("attValue")):request.getAttribute("attValue");
Object attTextObj=request.getAttribute("attText") == null?StaticMethod.dbNull2String(request.getParameter("attText")):request.getAttribute("attText");

if (attValueObj!=null)
  attValue=attValueObj.toString().equals("null")?"":attValueObj.toString();

if (attTextObj!=null)
  attText=attTextObj.toString().equals("null")?"":attTextObj.toString();

String attValueArr[]=attValue.split(",",0);
String attTextArr[] =attText.split(",",0);
%>
<body topmargin=0 marginheight=0 leftmargin=0 marginwidth=0 >
<div style="width:100%;height:100%;" class="tr_show">
<!---正文开始------------------------------------------------------------------------------------------>
<table align="center" width="100%" height="100%" class="tr_show">
<html:form  action="/TawFileUpload/add.do"   method="post" enctype="multipart/form-data">
  <tr>
    <TD ALIGN="left" VALIGN="top" height="100%">
      <table id="attachTable">
		<tr id="attachTr">
          <td>
	    <html:file property="attachName" styleClass="clstext"/>
          </td>
        </tr>
      </table>
    </TD>
    <td align="right" VALIGN="top" rowspan="2" height="100%">
      <INPUT TYPE="button" Class="clsbtn2" VALUE=">><bean:message key="taw.attach"/>>>" onclick="return addattach(1);">
      <INPUT TYPE="button" Class="clsbtn2" VALUE="<<<bean:message key="label.remove"/><<" onclick='return removeattach();'>
    </td>
    <TD ALIGN="left" VALIGN="top" rowspan=2 height="100%">
      <SELECT NAME="attachlist"  SIZE="4" style="font-size: 9pt;width:150">
        <%
        for (int i=0;i<attValueArr.length;i++)
        {
          if (!"".equals(attValueArr[i]) && !attValueArr[i].equals("null"))
            out.println("<OPTION value=" + attValueArr[i] + ">" + attTextArr[i] +"</option>");
        }
        %>
      </SELECT>
    </td>
  </tr>
<input type=hidden name="upType" value="">
<input type=hidden name="fileName" value="">
<input type=hidden name="fileValue" value="<%=attValue%>">
<input type=hidden name="fileText" value="<%=attText%>">
</html:form>
</table>
<!---正文结束------------------------------------------------------------------------------------------>
</div>
</body>
<script language="javascript">
<!--
parent.document.forms[0].fileValue.value = "<%=attValue.equals("null")?"":attValue%>";
parent.document.forms[0].fileText.value = "<%=attText.equals("null")?"":attText%>";
-->
</script>
</html>
