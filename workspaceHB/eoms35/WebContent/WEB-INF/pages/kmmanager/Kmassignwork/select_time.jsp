 <%@ page language="java" pageEncoding="UTF-8" %> 
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 
<head>
<title><bean:message key="entry.TawRmExchange.dutySet"/></title>

</head>

<body leftmargin="0" topmargin="0" class="clssclbar">
<br>
<form  name="form1" id="form1" method="post"  action="${app }/kmmanager/Kmassignwork/assign.do"  onsubmit="return checkForm()" >
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center" class="formTable">
<tr>
<td>
<table  width="100%">
  <tr>
<td class="label" align="center">

<%=request.getAttribute("ROOMNAME") %>
&nbsp;&nbsp;&nbsp;
<%--<bean:message key="TawRmAssignwork.apparatusroom"/>
&nbsp;&nbsp;&nbsp;
--%>小组

  </td>
</tr>



<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="listTable" align=center>
<tr class="tr_show">
<td width="10%"   class="label" rowspan="3">
<bean:message key="TawRmAssignwork.assign_type"/>
</td>
<td width="40%" colspan="2" class="label">
<input id = "assign_auto" type="radio" name="assign_type" value="assign_auto" onclick = "javascript:click_assign_auto();"><bean:message key="entry.TawRmExchange.dutyzidong"/>
</td>
<td width="40%" class="label">
<input id = "assign_manu" type="radio" name="assign_type" value="assign_manu" onclick = "javascript:click_assign_manu();"><bean:message key="entry.TawRmExchange.dutyshoudong"/>
</td>
</tr>
 
<tr class="tr_show">

<td width="40%" colspan="2" class="label">
<input id = "auto_cover" type="radio" name="assign_type" value="auto_cover" checked = "checked" onclick = "javascript:click_auto_cover();"><bean:message key="entry.TawRmExchange.dutyFree"/>
</td>
<td width="40%" class="label" >
<%--<input id = "auto_cover" type="radio" name="assign_type" value="auto_daily"  ><bean:message key="entry.TawRmExchange.dutyCord"/>
--%>
<input id = "auto_cover" type="radio" name="assign_type" value="auto_new" checked = "checked" onclick = "javascript:click_auto_new();"><bean:message key="entry.TawRmExchange.dutyquick"/>
(<bean:message key="entry.TawRmExchange.city" />)
</td>
</tr>
<%--<tr class="tr_show">

<td width="40%" colspan="3" class="label">
<input id = "auto_cover" type="radio" name="assign_type" value="auto_new" checked = "checked" onclick = "javascript:click_auto_new();"><bean:message key="entry.TawRmExchange.dutyquick"/>
(<bean:message key="entry.TawRmExchange.city" />)
</td>
  
 
</tr>
--%><tr class="tr_show">

<td width="25%" class="label">
<bean:message key="TawRmAssignwork.teamnum"/>
<%
Vector vTeamNum = (Vector)request.getAttribute("VectorTeamNum");
String TeamNum = (String)request.getAttribute("TeamNum");
String RoomUserNum = (String)request.getAttribute("RoomUserNum");
%>
<select name="sTeamNum" disabled = "disabled" >
<%
for (int i=0;i<vTeamNum.size();i++)
{
if (String.valueOf(vTeamNum.elementAt(i)).equals(TeamNum))
{
%>
<option value="<%out.print(vTeamNum.elementAt(i));%>" selected><%out.print(vTeamNum.elementAt(i));%></option>
<%
}
else
{
%>
<option value="<%out.print(vTeamNum.elementAt(i));%>" ><%out.print(vTeamNum.elementAt(i));%></option>
<%
}}
%>
</select>
</td>
<td width="25%" class="label">
<bean:message key="TawRmAssignwork.usernum"/>
<%
Vector vUserNum = (Vector)request.getAttribute("VectorUserNum");
String UserNum = (String)request.getAttribute("UserNum");
%>
<select name="sUserNum" disabled  = "disabled">
<%
for (int i=0;i<vUserNum.size();i++)
{
if (String.valueOf(vUserNum.elementAt(i)).equals(UserNum))
{
%>
<option value="<%out.print(vUserNum.elementAt(i));%>" selected><%out.print(vUserNum.elementAt(i));%></option>
<%
}
else
{
%>
<option value="<%out.print(vUserNum.elementAt(i));%>"><%out.print(vUserNum.elementAt(i));%></option>
<%
}}
%>
</select>
</td>

<td width="40%" class="label">
<bean:message key="TawRmAssignwork.begin_dutyman"/>
<%
Vector RoomUser = (Vector)request.getAttribute("RoomUser");

%>
<select name="sRoomUser" style="width:100"  >
<%
int k = 0;
for (int i=0;i*2<RoomUser.size();i++)
{
k=i;
%>
<option value="<%out.print(RoomUser.elementAt(i*2));%>"><%out.print(RoomUser.elementAt(i*2+1));%></option>
<%
}
%>
</select>
</td>
</tr>


<tr class="tr_show">
<td width="20%"   class="label">
<bean:message key="TawRmAssignwork.begin_date"/>
</td>
<td  align = "center" colspan="4" class="label">
<%String str_start = String.valueOf(request.getAttribute("str_start"));%>
<eoms:SelectDate name="start_" formName="form1" flag = "1" value = "<%=str_start%>"/>

<tr class="tr_show">
<td width="20%"  class="label">
<bean:message key="TawRmAssignwork.end_date"/>
</td>
<td  align = "center" colspan="4" class="label">
<eoms:SelectDate name="end_" formName="form1" flag = "1" value = "<%=str_start%>"/>
</td>
</tr>
<tr>
<td width="20%"  class="label">
<bean:message key="entry.TawRmExchange.weekduty"/>
</td>
<td width="40%" colspan="2" class="label">
<input  type="radio" name="workSelect" value="1"  ><bean:message key="entry.TawRmExchange.yes"/>
 
<input  type="radio" name="workSelect" value="0" checked = "checked" ><bean:message key="entry.TawRmExchange.no"/>
</td>
<td class="label">

</td>

</tr>
</table>
</td>
</tr>
<tr>
<td>
<table  height="32" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="right" valign="middle">
<td>
<%--<input type="button" class="button" name="save" value="使用模板排班" onclick="xls_input()";>
&nbsp;&nbsp;&nbsp;
<input type="button" class="button" name="save" value="重复上一周期" onclick="model_copy()";>
&nbsp;&nbsp;&nbsp;
--%><input type="submit" class="button" name="save" value=<bean:message key="TawRmAssignwork.assign" /> >
&nbsp;&nbsp;&nbsp;
<input type="button" class="button" name="save" value=<bean:message key="entry.TawRmExchange.dutyBucong"/>  <%="1".equals(request.getAttribute("haveAssign"))?"disabled":""%>  onclick="javascript:click_assign_modify();">
  </td>
</tr>
</table>
</td>
</tr>

  </table>
</td>
</tr>
</table>
<%
int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());
%>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name = "TeamNum" value = "<%=TeamNum%>">
<input type="hidden" name = "UserNum" value = "<%=UserNum%>">
<SCRIPT LANGUAGE=javascript>
<!--
function click_auto_cover()
{
  document.all.form1.sTeamNum.value = "<%=TeamNum%>";
  document.all.form1.sUserNum.value = "<%=UserNum%>";
  document.all.form1.sTeamNum.disabled = "disabled";
  document.all.form1.sUserNum.disabled = "disabled";
  document.all.form1.sRoomUser.disabled = "";
  document.all.form1.end_year.disabled = "";
  document.all.form1.end_month.disabled = "";
  document.all.form1.end_day.disabled = "";
}
function click_auto_new()
{
  document.all.form1.sTeamNum.value = "<%=TeamNum%>";
  document.all.form1.sUserNum.value = "<%=UserNum%>";
  document.all.form1.sTeamNum.disabled = "disabled";
  document.all.form1.sUserNum.disabled = "disabled";
  document.all.form1.sRoomUser.disabled = "";
  document.all.form1.end_year.disabled = "";
  document.all.form1.end_month.disabled = "";
  document.all.form1.end_day.disabled = "";
}
function click_auto_daily()
{
 
  document.all.form1.sTeamNum.value = "<%=TeamNum%>";
  document.all.form1.sUserNum.value = "<%=RoomUserNum%>";
  document.all.form1.sTeamNum.disabled = "disabled";
  document.all.form1.sUserNum.disabled = "disabled";
  document.all.form1.sRoomUser.disabled = "";
  document.all.form1.end_year.disabled = "";
  document.all.form1.end_month.disabled = "";
  document.all.form1.end_day.disabled = "";
}
function click_assign_auto()
{
  document.all.form1.sTeamNum.value = "<%=TeamNum%>";
  document.all.form1.sUserNum.value = "<%=UserNum%>";
  document.all.form1.sRoomUser.options[0].selected;
  document.all.form1.sTeamNum.disabled = "disabled";
  document.all.form1.sUserNum.disabled = "disabled";
  document.all.form1.sRoomUser.disabled = "disabled";
  document.all.form1.end_year.disabled = "";
  document.all.form1.end_month.disabled = "";
  document.all.form1.end_day.disabled = "";
}
function click_assign_manu()
{
  document.all.form1.sRoomUser.options[0].selected;
  document.all.form1.sTeamNum.disabled = "";
  document.all.form1.sUserNum.disabled = "";
  document.all.form1.sRoomUser.disabled = "disabled";
  document.all.form1.end_year.disabled = "disabled";
  document.all.form1.end_month.disabled = "disabled";
  document.all.form1.end_day.disabled = "disabled";
}    
function click_assign_modify()
{
  click_assign_auto();
  document.all.form1.action = "${app }/kmmanager/Kmassignwork/assign.do?assign_type_modify=assign_modify";
  document.all.form1.submit();
}
function model_copy()
{
  
  document.all.form1.action = "${app }/kmmanager/Kmassignwork/modelCopy.do";
  document.all.form1.submit();
}
function xls_input()
{
  
  document.all.form1.action = "${app }/kmmanager/Kmassignwork/select.do";
  document.all.form1.submit();
}
function checkForm()
{
 var answer= document.getElementsByName("assign_type");
  var check ;
  for(var i=0;i<answer.length;i++){
 	if(answer[i].checked == true){
 	check = answer[i].value;
 	}
  }
  if(check=='auto_new'){
  var user = <%=k+1%>;
   
  var userNum = <%=UserNum%>;
   if(userNum!=1){
   alert('<bean:message key="entry.TawRmExchange.tishi1"/> ');
   return false;
   }
  var teamNum = <%=TeamNum%>;
  if(teamNum!='3'){
 	alert('<bean:message key="entry.TawRmExchange.tishi2"/> ');
 	return false;
  }
  }else if (check=='auto_daily'){
  var teamNum = <%=TeamNum%>;
   
  if(teamNum!=1){
   alert('<bean:message key="entry.TawRmExchange.tishi3"/> ');
   return false;
  }
 
  }
  
 return true;
}
//-->
</SCRIPT>
</form>
</body>
