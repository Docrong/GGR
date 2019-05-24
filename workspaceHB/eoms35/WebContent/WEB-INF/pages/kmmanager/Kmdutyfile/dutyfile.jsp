<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java"
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.km.duty.model.*,com.boco.eoms.common.util.StaticMethod"%>

<script language="javascript">
function addattach()
{
  if (kmdutyfileForm.attachName.value==""){
    alert("请选择文件");
    return false;
  }
  else
  {
    var fileStr= kmdutyfileForm.attachName.value;
    var index=fileStr.lastIndexOf("\\");
    fileStr=fileStr.substring(index+1,fileStr.length);
    kmdutyfileForm.filename.value=fileStr;
    kmdutyfileForm.submit();
    return true;
  }
}
function checkdelete(){
  var bolCheck= false;
  var list = document.getElementsByName('ListDelete');
  var flag = 0;
   for(i=0;i<list.length;i++){
     if(list[i].checked){flag=1;}
   }
   if(flag==0){
     alert("<bean:message key="TawRmDutyfile.deletealert"/>")
     return bolCheck;
   }else{
     if(list.length>0){
       var listNum=list.length;
       document.getElementById('selected_id').value = "-1";
       for(i=0;i<listNum;i++){
          if(list[i].checked)
          {
           document.getElementById('selected_id').value = document.getElementById('selected_id').value+","+list[i].value;
            bolCheck=true;
          }
       }
       if (confirm("<bean:message key="TawRmDutyfile.deletecheck"/>")){
         kmdutyfileForm.action="${app}/kmmanager/Kmdutyfile/delete.do";
         kmdutyfileForm.submit();
       }
     }
   }
}
</script>

<html:form method="post" action="/Kmdutyfile/save" enctype="multipart/form-data">
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<%
			Vector vecDutyFile = (Vector) request.getAttribute("vecDutyFile");
			int j = 0;
			for (int i = 0; i < (vecDutyFile.size() + 1) / 2; i++) {
		%>
		<tr>
			<td>
				<%
					if (j < vecDutyFile.size()) {
							Kmdutyfile kmdutyfile = (Kmdutyfile) vecDutyFile.elementAt(j);
							j++;
							if (StaticMethod.getUploadType().equals("UpE")) {
								out.print("<input type=checkbox name=ListDelete id=ListDelete value='" + kmdutyfile.getId() + "'><a href='"
												+ request.getContextPath()+ "/kmduty/upload/" + java.net.URLEncoder.encode(kmdutyfile.getEncodename())
												+ "' target='blank'>"
												+ kmdutyfile.getFilename() + "</a>");
							} else {
								out.print("<input type=checkbox name=ListDelete id=ListDelete value='" + kmdutyfile.getId() + "'><a href='"
												+ request.getContextPath() + "/kmduty/upload/" + kmdutyfile.getEncodename() 
												+ "' target='blank'>"
												+ kmdutyfile.getFilename() + "</a>");
							}
						}
				%>
			</td>
			<td>
				<%
					if (j < vecDutyFile.size()) {
							Kmdutyfile kmdutyfile = (Kmdutyfile) vecDutyFile.elementAt(j);
							j++;
							if (StaticMethod.getUploadType().equals("UpE")) {
								out.print("<input type=checkbox name=ListDelete id=ListDelete value='" + kmdutyfile.getId() + "'><a href='"
												+ request.getContextPath() + "/kmduty/upload/" + java.net.URLEncoder.encode(kmdutyfile.getEncodename())
												+ "' target='blank'>"
												+ kmdutyfile.getFilename() + "</a>");
							} else {
								out.print("<input type=checkbox name=ListDelete id=ListDelete value='" + kmdutyfile.getId() + "'><a href='"
												+ request.getContextPath() + "/kmduty/upload/" + kmdutyfile.getEncodename()
												+ "' target='blank'>"
												+ kmdutyfile.getFilename() + "</a>");
							}
						}
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<table cellpadding="1" cellspacing="0" border="0" width="100%">
		<tr>
			<td>
				<html:file property="attachName" />
			</td>
			<td align="left">
				<html:hidden property="workserial" />
				<html:hidden property="filename" />			
				<INPUT TYPE="hidden" name="selected_id" id="selected_id">
				<INPUT TYPE="button" class="button" VALUE="<bean:message key="label.save"/>"   onclick="return addattach();">
				<INPUT TYPE="button" class="button" VALUE="<bean:message key="label.delete"/>" onclick="return checkdelete();">
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
