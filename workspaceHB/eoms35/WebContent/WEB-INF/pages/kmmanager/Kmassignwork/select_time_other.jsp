<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<head>
<title>排班配置(第二步)</title>

</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<br>
<form  name="form1" id="form1" method="post"  action="../../duty/TawRmAssignwork/assign.do">
<table cellpadding="0" cellspacing="0" border="0" width="500" align="center">
<tr>
<td>
<table  width="100%">
  <tr>
<td class="table_title" align="center">

<%=request.getAttribute("ROOMNAME") %>
&nbsp;&nbsp;&nbsp;
<bean:message key="TawRmAssignwork.apparatusroom"/>
&nbsp;&nbsp;&nbsp;
<bean:message key="TawRmAssignwork.tablename"/>

  </td>
</tr>



<tr>
<td>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<tr class="tr_show">
<td width="20%"   class="clsfth" rowspan="3">
<bean:message key="TawRmAssignwork.assign_type"/>
</td>
<td width="40%" colspan="2">
<input id = "assign_auto" type="radio" name="assign_type" value="assign_auto" onclick = "javascript:click_assign_auto();">自动排班
</td>
<td width="40%">
<input id = "assign_manu" type="radio" name="assign_type" value="assign_manu" onclick = "javascript:click_assign_manu();">手动排班调整
</td>
</tr>
<tr class="tr_show">

<td width="40%" colspan="2">
<input id = "auto_cover" type="radio" name="assign_type" value="auto_cover" checked = "checked" onclick = "javascript:click_auto_cover();">自动排班并覆盖已有排班
</td>
<td width="40%" >
<input id = "auto_cover" type="radio" name="assign_type" value="auto_daily"  >行政班
</td>
</tr>
<tr class="tr_show">

<td width="25%">
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
<td width="25%">
<bean:message key="TawRmAssignwork.usernum"/>
<%
Vector vUserNum = (Vector)request.getAttribute("VectorUserNum");
String UserNum = (String)request.getAttribute("UserNum");     //每次值班人数
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

<td width="40%">
<bean:message key="TawRmAssignwork.begin_dutyman"/>
<%
Vector RoomUser = (Vector)request.getAttribute("RoomUser");    //机房人数Vector
int groupNum=RoomUser.size()/(2*Integer.parseInt(UserNum));    //天数

%>
<select name="sRoomUser" style="width:100"  >
<%
for (int i=0;i*2<RoomUser.size();i++)
{
%>
<option value="<%out.print(RoomUser.elementAt(i*2));%>"><%out.print(RoomUser.elementAt(i*2+1));%></option>
<%
}
%>
</select>
</td>
</tr>


<tr class="tr_show">
<td width="20%"   class="clsfth">
<bean:message key="TawRmAssignwork.begin_date"/>
</td>
<td  align = "center" colspan="4">
<%String str_start = String.valueOf(request.getAttribute("str_start"));%>
<eoms:SelectDate name="start_" formName="form1" flag = "1" value = "<%=str_start%>"/>
<input type="hidden" name="groupNum" value="<%=groupNum%>">
<%--
<tr class="tr_show">
<td width="20%"   class="clsfth">
<bean:message key="TawRmAssignwork.end_date"/>
</td>
<td  align = "center" colspan="4">
<eoms:SelectDate name="end_" formName="form1" flag = "1" value = "<%=str_start%>" />
</td>
</tr>
--%>
</table>
</td>
</tr>
<tr>
<td>
<table  height="32" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr align="right" valign="middle">
<td>
<input type="submit" class="clsbtn2" name="save" value=<bean:message key="TawRmAssignwork.assign"/> >
&nbsp;&nbsp;&nbsp;
<input type="button" class="clsbtn2" name="save" value='补排班' <%="1".equals(request.getAttribute("haveAssign"))?"disabled":""%>  onclick="javascript:click_assign_modify();">
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
  /*
  document.all.form1.end_year.disabled = "";
  document.all.form1.end_month.disabled = "";
  document.all.form1.end_day.disabled = "";
  */
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
  /*
  document.all.form1.end_year.disabled = "";
  document.all.form1.end_month.disabled = "";
  document.all.form1.end_day.disabled = "";
  */
}
function click_assign_manu()
{
  document.all.form1.sRoomUser.options[0].selected;
  //document.all.form1.sTeamNum.disabled = "";
  //document.all.form1.sUserNum.disabled = "";
  document.all.form1.sRoomUser.disabled = "disabled";
   /*
  document.all.form1.end_year.disabled = "disabled";
  document.all.form1.end_month.disabled = "disabled";
  document.all.form1.end_day.disabled = "disabled";
  */
}
function click_assign_modify()
{
  click_assign_auto();
  document.all.form1.action = "../../duty/TawRmAssignwork/assign.do?assign_type_modify=assign_modify";
  document.all.form1.submit();
}
//-->
</SCRIPT>
</form>
</body>
