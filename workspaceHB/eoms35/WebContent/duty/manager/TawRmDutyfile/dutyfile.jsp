 
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.model.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<html>
<head>
<title>table</title>
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">

<script language="javascript">
function addattach()
{
  if (tawRmDutyfileForm.attachName.value==""){
  return false;
  }
  else
  {
  var fileStr= tawRmDutyfileForm.attachName.value;
  var index=fileStr.lastIndexOf("\\");
  fileStr=fileStr.substring(index+1,fileStr.length);
  tawRmDutyfileForm.filename.value=fileStr;
  //alert (fileStr);
  tawRmDutyfileForm.submit();
  return true;
  }
}
function checkdelete(){
  var bolCheck= false;
   
  if (document.forms[0].elements[0].name!="ListDelete")
  {
    alert("<bean:message key="TawRmDutyfile.deletealert"/>");
    return bolCheck;
  }
  else{
    if(document.forms[0].ListDelete.length>0){
      var listNum=document.forms[0].ListDelete.length;
      document.forms[0].selected_id.value = "-1";
      for(i=0;i<listNum;i++){
          if(forms[0].ListDelete[i].checked)
          {
            document.forms[0].selected_id.value = document.forms[0].selected_id.value+","+document.forms[0].ListDelete[i].value;
            bolCheck=true;
          }
       }
    }
    else{
     if(document.forms[0].ListDelete.checked)
     {
          document.forms[0].selected_id.value=document.forms[0].ListDelete.value;
          bolCheck=true;
     }

    }
    if (!bolCheck){
     alert("<bean:message key="TawRmDutyfile.deletealert"/>");
    }
    else{
      if (confirm("<bean:message key="TawRmDutyfile.deletecheck"/>")){
         tawRmDutyfileForm.action="delete.do";
         tawRmDutyfileForm.submit();
      }
    }
    //alert(document.tawRmDutyfileForm.selected_id.value);
    return bolCheck;
  }
}

</script>
</head>

<body leftmargin="0" topmargin="0">
<div style="width:100%;height:100%;background-color:#E7EFFF;">
<html:form method="post" action="/TawRmDutyfile/save" enctype="multipart/form-data">
<table cellpadding="1" cellspacing="0" border="0" width="100%">
<%Vector vecDutyFile=(Vector)request.getAttribute("vecDutyFile");%>
<%
int j=0;
%>
<%for (int i = 0;i<(vecDutyFile.size()+1)/2;i++){%>
<tr>
<td>
<%
if (j<vecDutyFile.size()){
TawRmDutyfile tawRmDutyfile = (TawRmDutyfile)vecDutyFile.elementAt(j);
j++;
if(StaticMethod.getUploadType().equals("UpE")){
out.print("<input type=checkbox name=ListDelete value='"+tawRmDutyfile.getId()+"'><a href='"+request.getContextPath()+"/duty/upload/"+java.net.URLEncoder.encode(tawRmDutyfile.getEncodename()) + "' target='blank'>" + tawRmDutyfile.getFilename() + "</a>");
//out.print("<input type=checkbox name=ListDelete value='"+tawRmDutyfile.getId()+"'><a href='"+request.getContextPath()+"/duty/download.jsp?name="+java.net.URLEncoder.encode(tawRmDutyfile.getEncodename()) + "'>" + tawRmDutyfile.getFilename() + "</a>");
}else{
 out.print("<input type=checkbox name=ListDelete value='"+tawRmDutyfile.getId()+"'><a href='"+request.getContextPath()+"/duty/upload/"+tawRmDutyfile.getEncodename() + "' target='blank'>" + tawRmDutyfile.getFilename() + "</a>");
 // modified by lw 2004-01-05
 //out.print("<input type=checkbox name=ListDelete value='"+tawRmDutyfile.getId()+"'><a href='"+request.getContextPath()+"/duty/download.jsp?name="+tawRmDutyfile.getEncodename() + "'>" + tawRmDutyfile.getFilename() + "</a>");

}
}
%>
</td>
<td>
<%
if (j<vecDutyFile.size()){
TawRmDutyfile tawRmDutyfile = (TawRmDutyfile)vecDutyFile.elementAt(j);
j++;
if(StaticMethod.getUploadType().equals("UpE")){
out.print("<input type=checkbox name=ListDelete value='"+tawRmDutyfile.getId()+"'><a href='"+request.getContextPath()+"/duty/upload/"+java.net.URLEncoder.encode(tawRmDutyfile.getEncodename()) + "' target='blank'>" + tawRmDutyfile.getFilename() + "</a>");
}else{
out.print("<input type=checkbox name=ListDelete value='"+tawRmDutyfile.getId()+"'><a href='"+request.getContextPath()+"/duty/upload/"+tawRmDutyfile.getEncodename() + "' target='blank'>" + tawRmDutyfile.getFilename() + "</a>");
}
}
%>
</td>
</tr>
<%}%>
<tr>
<td>
<html:file property="attachName" />
</td>
<td align="left">
      <INPUT TYPE="hidden" name="selected_id" VALUE="">
      <INPUT TYPE="button" class="button" VALUE="<bean:message key="label.save"/>" onclick="return addattach();">
      <INPUT TYPE="button" class="button" VALUE="<bean:message key="label.delete"/>" onclick="return checkdelete();">
</td>
</tr>
</table>
<html:hidden property="workserial" />
<html:hidden property="filename" />
</html:form>
</div>
</body>
</html>