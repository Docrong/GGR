<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.lang.*, org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*,com.boco.eoms.base.util.*"%>
 <%
 String date = StaticMethod.getLocalString();
 String idlist =(String)request.getAttribute("idlist");
 %>
<html>
<head>
 
</head>
<body>
<div align="center">
<html:form  method="post" action="/TawTestcardManager/returnupdate" onsubmit="true">
<script language="javascript">


function isnumeric(p)
{
 if (p == "")
  return false;
 var l = p.length;
 var count=0;
 for(var i=0; i<l; i++)
 {
  var digit = p.charAt(i);
  if(digit == "." )
 {
    ++count;
    if(count>1) {
	return false; }
   }
  else if(digit < "0" || digit > "9")
  {
  return false;
  }
 }
 return true;
}
</script>
<script language="JavaScript" >
function bb(main){
if (document.tawTranfaultreportForm.city.options.length!=0) {
for (i=0;i<=document.tawTranfaultreportForm.city.options.length;i++){
if(document.tawTranfaultreportForm.city.options[i].value!=main)
{document.tawTranfaultreportForm.city.options[i].selected="true";
document.tawTranfaultreportForm.city.disabled="false";
}
}
}
}
</script>
<table border="0" width="70%" cellspacing="1" class="formTable">
  <tr>
  <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center"><b>${eoms:a2u("测 试 卡 归 还")}&nbsp;&nbsp;</b> </td>
  </tr>
       <tr bgcolor="#FFFFFF">
         <td nowrap class="label" align="center">${eoms:a2u("归还人")}</td>
         <td nowrap   align="center"><html:text property="returner" readonly="false"/>&nbsp</td>
         <td nowrap class="label" align="center">${eoms:a2u("归还部门")}</td>
         <td nowrap   align="center"><html:text property="returndept"  readonly="false"/></td>
       </tr>
       <tr bgcolor="#FFFFFF">
         <td nowrap class="label" align="center" >${eoms:a2u("归还时间")}</td>
         <td nowrap   align="center" colspan="3">
         
				<input type="text" name="returntime" size="30" value="<%=date%>" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true" 
					class="text">
        
         </td>
         
       </tr>
       <tr bgcolor="#FFFFFF">
         <td colspan=4 nowrap  align="right">
         <INPUT type="submit" class="button"  value=${eoms:a2u("保存")}  name="submit">&nbsp;&nbsp;
         <html:reset styleClass="button">${eoms:a2u("重置")}</html:reset>&nbsp;&nbsp;
		 </td>
	   </tr>
</table>
</html:form>
</div>
</body>
</html>