<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%
Vector vec_workserial_id = (Vector)request.getAttribute("VECWORKSERIALID");
Vector vec_workserial_name = (Vector)request.getAttribute("VECWORKSERIALNAME");
%>
<style type="text/css">
body{
  background-image:none;
  background-color:#c3daf9;
}
#border{
	position:absolute;
	left:30%;
	top:20%;
	border:1px solid #6593cf;
	padding:2px;
	background:#c3daf9;
	width:400px;
	z-index:20001;
}

.room {
	background-color:#F3FAFC;
	border:0pt none;
	color:#003366;
	font-size: 12px;
	padding:10px;
	margin:0;
}
#room-text, #room-select, #workserial, #workserial-text, #btn{
	margin-bottom:8px;
}
</style>
<script type="text/javascript">
function fun_confirm()
{
  var i = document.forms[0].str_workserial.selectedIndex;
  if (i==-1){
    alert ("${eoms:a2u("请选择班次")}");
    return false;
  }
  return true;
}
</script>

<html:form method="post" action="/saveworkserial.do?method=saveWorkSerial"  styleId="tawSystemSessionForm">

<div id="border">

<div class="room">
  <div id="workserial">
    <div id="workserial-text"><img src="${app}/images/icons/date.gif" align="absmiddle">&nbsp;<fmt:message key="SaveSessionBean.selectWorkserial"/></div>
	<select id="str_workserial" name="str_workserial" style="width:350px" size="<%=vec_workserial_id.size()%>">
      <%for(int i=0;i<vec_workserial_id.size();i++) {%>
      <option value='<%=vec_workserial_id.get(i).toString()%>'><%=vec_workserial_name.get(i).toString()%></option>
      <%}%>	
	</select>
  </div>
  
  <div id="btn">
    <input type="submit" name="submit" id="submit" class="submit" value='<fmt:message key="label.ok" />' onclick="return fun_confirm();">
  <div>
</div>

</div>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
