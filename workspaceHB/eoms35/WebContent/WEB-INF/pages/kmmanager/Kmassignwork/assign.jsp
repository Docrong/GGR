<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
 
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod,java.util.GregorianCalendar;"%>
 
 
<head>
<title> </title>

</head>
<script type="text/javascript">

function model_set()
{
  
  document.forms[0].action = "${app}/kmmanager/Kmassignwork/modelSetAndAssign.do";
  document.forms[0].submit();
}
<!--
function changeUser(obj){
     
    var checks=document.getElementsByName('check_user');
 	var k = 0;
    var currSelectText =obj.options[obj.selectedIndex].text;        
    for(var i=0;i<checks.length;i++){
         if(checks[i].checked==true){
            var check_user=document.getElementById(checks[i].value);
           	k=k+1;
            jsSelectIsExitItem(check_user,obj.value); 
            
        }
    }
    if(k>=1){
    if (window.confirm('请核对你更改的信息是否正确')==true){
    for(var i=0; i<checks.length; i++) {
    	if(checks[i].type.toLowerCase() == "checkbox" )
      		checks[i].checked = false;
      			 
		}
		}
		}
}
function jsSelectIsExitItem(objSelect, objItemValue) {           
    var isExit = false;           
    for (var i = 0;i<objSelect.options.length; i++) {           
        if (objSelect.options[i].value == objItemValue) {           
            isExit = true;     
            objSelect.selectedIndex=i;      
            break;           
        }           
    }           
    return isExit;           
}   
 
//-->
</script>
<body><br>

<html:form method="post" action="/Kmassignwork/save_assign.do">
<html:hidden property="strutsAction"/>
<%
String workSelect = (String)request.getAttribute("workSelect");
%>
<input type="hidden" name="workSelect" value=<%=workSelect%> >
<table cellpadding="0" cellspacing="0" border="0" width="800" align="center" >

<tr>

<td>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
<td align="center" class="label">

<%=request.getAttribute("ROOMNAME") %>
&nbsp;&nbsp;&nbsp;
 
  <br></td><%--
  <td align="center">
    	<input type="checkbox" onclick="eoms.util.checkAll(this,'check_user')"/>
	  </td>
--%></tr>
</table>
<table border="0"  cellspacing="1" cellpadding="1" align=center class = "listTable">
  <tr>
<td>

<%int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());
  int stagger = Integer.parseInt(String.valueOf(request.getAttribute("STAGGER")).trim());
  System.out.println(stagger+"---------------");
  Vector expert = (Vector)request.getAttribute("EXPERT");
  int expert_name = 0;
%>



<%String AssignType = String.valueOf(request.getAttribute("ASSIGNTYPE")).trim();%>
<input type = "hidden" name = "assign_type" value = "<%=AssignType%>">
<%
if (AssignType.equals("auto_cover")){
///////////////////////////////////auto_cover
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%
//get how many user a team have
int user_num = Integer.parseInt(String.valueOf(request.getAttribute("USERNUM")).trim());
//write title
%>
<tr class="td_label">
<td class="label">日期<br></td>
<td class="label">时间<br></td>
<td class="label">值班长<br></td>
<%for (int i=1;i<user_num;i++){%>
<td class="label">值班人员<%=i%><br></td>
<%}%>
<!-- 
<td class="label">值班专家<br></td>
 -->
</tr>

<%
//get time_start,time_end and convert to calendar
int year_start = Integer.parseInt(String.valueOf(request.getAttribute("YEARSTART")).trim());
int month_start = Integer.parseInt(String.valueOf(request.getAttribute("MONTHSTART")).trim());
int day_start = Integer.parseInt(String.valueOf(request.getAttribute("DAYSTART")).trim());
int year_end = Integer.parseInt(String.valueOf(request.getAttribute("YEAREND")).trim());
int month_end = Integer.parseInt(String.valueOf(request.getAttribute("MONTHEND")).trim());
int day_end = Integer.parseInt(String.valueOf(request.getAttribute("DAYEND")).trim());
GregorianCalendar cal_start = new GregorianCalendar();
GregorianCalendar cal_end = new GregorianCalendar();
Date date_start = new Date(year_start-1900,month_start-1,day_start-0);
Date date_end = new Date(year_end-1900,month_end-1,day_end-0);
cal_start.setTime(date_start);
cal_end.setTime(date_end);
//get how many team a day have
int team_num = Integer.parseInt(String.valueOf(request.getAttribute("TEAMNUM")).trim());
//init the "text,select" name
int date_name = 0;
int exchang_name = 0;
int roomuser_name = 0;

//get the exchang_time and room_user Vector from the TawRmSysteminfo
Vector vector_exchang_time = (Vector)request.getAttribute("EXCHANGETIME");
Vector vector_room_user = (Vector)request.getAttribute("RoomUser");
//get the first man sequense and set the user currently       for the use iterate
int user_num_current=1;
String sRoomUser = String.valueOf(request.getAttribute("SROOMUSER")).trim();
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++){
if (String.valueOf(vector_room_user.elementAt(room_user_i*2)).trim().equals(sRoomUser)){
user_num_current = room_user_i;
}
}
//get how many user in the room
int user_num_all = vector_room_user.size() / 2 ;
%>

<%
//begin iterate
for(;!cal_start.after(cal_end);){
java.util.Date date = new java.util.Date(
 cal_start.get(cal_start.YEAR) - 1900, cal_start.get(cal_start.MONTH), cal_start.get(cal_start.DATE));
int numOfWeek = date.getDay();
 if((numOfWeek!=0&&numOfWeek!=6)||workSelect.equals("1")){
//first col in a day
%>
<tr class="tr_show">
<td rowspan = "<%=team_num%>" ><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<br><td>
<%  
if(vector_exchang_time.size()==1){
out.print(vector_exchang_time.elementAt(0));
}else{ 
out.print(vector_exchang_time.elementAt(0)+"--"+vector_exchang_time.elementAt(1));
}
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<input type="checkbox" name="check_user"  value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" />
<select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)" >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
if (user_num_current == room_user_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" selected><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}else{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
roomuser_name++;
%>
</select>
<br></td>
<%
user_num_current++;
if (user_num_current >= user_num_all)
user_num_current=1;
}%>
<!-- 
<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td> -->
</tr>
 

<%
//other col in the day
for (int exchang_time_i=1;exchang_time_i<vector_exchang_time.size();exchang_time_i++){
%>
<tr class="tr_show">
<td>
<%
if(exchang_time_i+1 ==vector_exchang_time.size())
out.print(StaticMethod.getHourbyMinute((String)vector_exchang_time.elementAt(exchang_time_i),-stagger)+"--"+vector_exchang_time.elementAt(0)+"T");
else
out.print(StaticMethod.getHourbyMinute((String)vector_exchang_time.elementAt(exchang_time_i),-stagger)+"--"+vector_exchang_time.elementAt(exchang_time_i+1));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(exchang_time_i)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td >
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" />
<select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100" onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)" >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
if (user_num_current == room_user_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" selected><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}else{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}//end if else
}//end for int room_user_i=0
roomuser_name++;
%>
</select>
<br></td>
<%
user_num_current++;
if (user_num_current >= user_num_all)
user_num_current=1;
}
%>
<!-- 
<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td> -->
</tr>
<%}%>  
<%
date_name++;
}
cal_start.add(5,1);


}
//get the duty end time
exchang_name++;
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<tr>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name="date_num" value="<%=date_name%>">
<input type="hidden" name="team_num" value="<%=team_num%>">
<input type="hidden" name="user_num" value="<%=user_num%>">
<td>
<br></td>
</tr>
</table>
<%
///////////////////////////////////auto_cover
}
else if((AssignType.equals("auto_daily")))
{
///////////////////////////////////auto_daily
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%
//get how many user a team have
int user_num = Integer.parseInt(String.valueOf(request.getAttribute("USERNUM")).trim());
//write title
%>
<tr class="td_label">
<td class="label">日期<br></td>
<td class="label">时间<br></td>
<td class="label">值班长<br></td>
<%for (int i=1;i<user_num;i++){%>
<td class="label">值班人员<%=i%><br></td>
<%}%>
<%--<td class="label">值班专家<br></td>--%>
</tr>

<%
//get time_start,time_end and convert to calendar
int year_start = Integer.parseInt(String.valueOf(request.getAttribute("YEARSTART")).trim());
int month_start = Integer.parseInt(String.valueOf(request.getAttribute("MONTHSTART")).trim());
int day_start = Integer.parseInt(String.valueOf(request.getAttribute("DAYSTART")).trim());
int year_end = Integer.parseInt(String.valueOf(request.getAttribute("YEAREND")).trim());
int month_end = Integer.parseInt(String.valueOf(request.getAttribute("MONTHEND")).trim());
int day_end = Integer.parseInt(String.valueOf(request.getAttribute("DAYEND")).trim());
GregorianCalendar cal_start = new GregorianCalendar();
GregorianCalendar cal_end = new GregorianCalendar();
Date date_start = new Date(year_start-1900,month_start-1,day_start-0);
Date date_end = new Date(year_end-1900,month_end-1,day_end-0);
cal_start.setTime(date_start);
cal_end.setTime(date_end);
//get how many team a day have
int team_num = Integer.parseInt(String.valueOf(request.getAttribute("TEAMNUM")).trim());
//init the "text,select" name
int date_name = 0;
int exchang_name = 0;
int roomuser_name = 0;
//get the exchang_time and room_user Vector from the TawRmSysteminfo
Vector vector_exchang_time = (Vector)request.getAttribute("EXCHANGETIME");
Vector vector_room_user = (Vector)request.getAttribute("RoomUser");
//get the first man sequense and set the user currently       for the use iterate
int user_num_current=1;
String sRoomUser = String.valueOf(request.getAttribute("SROOMUSER")).trim();
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++){
if (String.valueOf(vector_room_user.elementAt(room_user_i*2)).trim().equals(sRoomUser)){
user_num_current = room_user_i;
}
}
//get how many user in the room
int user_num_all = vector_room_user.size() / 2 ;
%>

<%
//begin iterate
for(;!cal_start.after(cal_end);){
//first col in a day
%>
<tr class="tr_show">
<td rowspan = "<%=team_num%>" ><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<br><td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100" onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)" >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
if (user_num_current == room_user_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" selected><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}else{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
roomuser_name++;
%>
</select>
<br></td>
<%
user_num_current++;
if (user_num_current >= user_num_all)
user_num_current=1;
}%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>


<%
//other col in the day
for (int exchang_time_i=1;exchang_time_i<vector_exchang_time.size();exchang_time_i++){
%>
<tr class="tr_show">
<td>
<%
out.print(vector_exchang_time.elementAt(exchang_time_i));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(exchang_time_i)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td >
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select  id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)" >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%

}//end for int room_user_i=0
roomuser_name++;
%>
</select>
<br></td>
<%
if (user_num_current >= user_num_all)
user_num_current=1;
}
%>
</tr>
<%}%>
<%
cal_start.add(5,1);
date_name++;
}
//get the duty end time
exchang_name++;
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<tr>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name="date_num" value="<%=date_name%>">
<input type="hidden" name="team_num" value="<%=team_num%>">
<input type="hidden" name="user_num" value="<%=user_num%>">
<td>
<br></td>
</tr>
</table>
<%
///////////////////////////////////auto_daily
}
else if((AssignType.equals("assign_auto")))
{
///////////////////////////////////assign_auto
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%
//get how many user a team have
int user_num = Integer.parseInt(String.valueOf(request.getAttribute("USERNUM")).trim());
//write title
%>
<tr class="td_label">
<td class="label">日期<br></td>
<td class="label">时间<br></td>
<td class="label">值班长<br></td>
<%for (int i=1;i<user_num;i++){%>
<td class="label">值班人员<%=i%><br></td>
<%}%>
<%--<td class="label">值班专家<br></td>--%>
</tr>
<%
Vector vecAssignAuto = (Vector)request.getAttribute("vecAssignAuto");
Vector vecDutydate = new Vector();
//Vector vecWorkId = new Vector();
Vector vecUser = new Vector();
Vector vecDutyman = new Vector();
if (vecAssignAuto.size() > 0){
vecDutydate = (Vector)vecAssignAuto.elementAt(0);
//vecWorkId = (Vector)vecAssignAuto.elementAt(1);
vecUser = (Vector)vecAssignAuto.elementAt(1);
}
//get time_start,time_end and convert to calendar
int year_start = Integer.parseInt(String.valueOf(request.getAttribute("YEARSTART")).trim());
int month_start = Integer.parseInt(String.valueOf(request.getAttribute("MONTHSTART")).trim());
int day_start = Integer.parseInt(String.valueOf(request.getAttribute("DAYSTART")).trim());
int year_end = Integer.parseInt(String.valueOf(request.getAttribute("YEAREND")).trim());
int month_end = Integer.parseInt(String.valueOf(request.getAttribute("MONTHEND")).trim());
int day_end = Integer.parseInt(String.valueOf(request.getAttribute("DAYEND")).trim());
GregorianCalendar cal_start = new GregorianCalendar();
GregorianCalendar cal_end = new GregorianCalendar();
Date date_start = new Date(year_start-1900,month_start-1,day_start-0);
Date date_end = new Date(year_end-1900,month_end-1,day_end-0);
cal_start.setTime(date_start);
cal_end.setTime(date_end);
//get how many team a day have
int team_num = Integer.parseInt(String.valueOf(request.getAttribute("TEAMNUM")).trim());
//init the "text,select" name
int date_name = 0;
int exchang_name = 0;
int roomuser_name = 0;
//get the exchang_time and room_user Vector from the TawRmSysteminfo
Vector vector_exchang_time = (Vector)request.getAttribute("EXCHANGETIME");
Vector vector_room_user = (Vector)request.getAttribute("RoomUser");
//get how many user in the room
int user_num_all = vector_room_user.size() / 2 ;
%>

<%
//begin iterate
for(;!cal_start.after(cal_end);){
java.util.Date date = new java.util.Date(
 cal_start.get(cal_start.YEAR) - 1900, cal_start.get(cal_start.MONTH), cal_start.get(cal_start.DATE));
int numOfWeek = date.getDay();
 if((numOfWeek!=0&&numOfWeek!=6)||workSelect.equals("1")){
//first col in a day
%>
<tr class="tr_show">
<td rowspan = "<%=team_num%>"><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<%
int intAssignAuto = -1;
for (int intAuto = 0 ; intAuto < vecDutydate.size();intAuto++){
if (String.valueOf(vecDutydate.elementAt(intAuto)).equals(String.valueOf(cal_start.get(cal_start.YEAR))+"-"+String.valueOf(cal_start.get(cal_start.MONTH)+1)+"-"+String.valueOf(cal_start.get(cal_start.DATE))+" "+vector_exchang_time.elementAt(0)+":00.0")) {
intAssignAuto = intAuto;
}
}
%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<br><td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)">
<%
if (intAssignAuto>=0){

for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
vecDutyman = (Vector)vecUser.elementAt(intAssignAuto);
if (vecDutyman.size() > user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" <%if (StaticMethod.null2String(String.valueOf(vector_room_user.elementAt(room_user_i*2))).trim().equals(String.valueOf(vecDutyman.elementAt(user_num_i)).trim())){%> selected <%}%>><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
if (vecDutyman.size() <= user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
}
if (intAssignAuto < 0){
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
roomuser_name++;
%>
</select>
<br></td>
<%
}%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>


<%
//other col in the day
for (int exchang_time_i=1;exchang_time_i<vector_exchang_time.size();exchang_time_i++){
intAssignAuto = -1;
for (int intAuto = 0 ; intAuto < vecDutydate.size();intAuto++){
//out.print(vecDutydate.elementAt(intAuto));
//out.print(String.valueOf(cal_start.get(cal_sart.YEAR))+"-"+String.valueOf(cal_start.get(cal_start.MONTH)+1)+"-"+String.valueOf(cal_start.get(cal_start.DATE))+" "+vector_exchang_time.elementAt(exchang_time_i)+":00.0"));
if (String.valueOf(vecDutydate.elementAt(intAuto)).equals(String.valueOf(cal_start.get(cal_start.YEAR))+"-"+String.valueOf(cal_start.get(cal_start.MONTH)+1)+"-"+String.valueOf(cal_start.get(cal_start.DATE))+" "+vector_exchang_time.elementAt(exchang_time_i)+":00.0")) {
intAssignAuto = intAuto;
}
}
%>
<tr class="tr_show">
<td>
<%
out.print(vector_exchang_time.elementAt(exchang_time_i));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(exchang_time_i)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)">
<%
if (intAssignAuto>=0){
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
vecDutyman = (Vector)vecUser.elementAt(intAssignAuto);
if (vecDutyman.size() > user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" <%if (StaticMethod.null2String(String.valueOf(vector_room_user.elementAt(room_user_i*2))).equals(String.valueOf(vecDutyman.elementAt(user_num_i)))){%> selected <%}%>><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
if (vecDutyman.size() <= user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
}
if (intAssignAuto < 0){
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
roomuser_name++;
%>
</select>
<br></td>
<%
}
%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>
<%}%>
<%
date_name++;
}
cal_start.add(5,1);

}
//get the duty end time
exchang_name++;
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<tr>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name="date_num" value="<%=date_name%>">
<input type="hidden" name="team_num" value="<%=team_num%>">
<input type="hidden" name="user_num" value="<%=user_num%>">
<td>
<br></td>
</tr>
</table>
<%
///////////////////////////////////assign_auto
}
///////////////////////////////////assign_manu
else if((AssignType.equals("assign_manu")))
{
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%int user_num = Integer.parseInt(String.valueOf(request.getAttribute("SUSERNUM")).trim());%>
<tr class="td_label">
<td class="label">日期<br></td>
<td class="label">时间<br></td>
<td class="label">值班长<br></td>
<%for (int i=1;i<user_num;i++){%>
<td class="label">值班人员<%=i%><br></td>
<%}%>
<%--<td class="label">值班专家<br></td>--%>
</tr>
<%
//get time_start,time_end and convert to calendar
int year_start = Integer.parseInt(String.valueOf(request.getAttribute("YEARSTART")).trim());
int month_start = Integer.parseInt(String.valueOf(request.getAttribute("MONTHSTART")).trim());
int day_start = Integer.parseInt(String.valueOf(request.getAttribute("DAYSTART")).trim());
GregorianCalendar cal_start = new GregorianCalendar();
Date date_start = new Date(year_start-1900,month_start-1,day_start-0);
cal_start.setTime(date_start);
//get how many team a day have
int team_num = Integer.parseInt(String.valueOf(request.getAttribute("STEAMNUM")).trim());
//init the "text,select" name
int date_name = 0;
int exchang_name = 0;
int roomuser_name = 0;
Vector vector_room_user = (Vector)request.getAttribute("RoomUser");
//get how many user in the room
int user_num_all = vector_room_user.size() / 2 ;
%>

<tr class="tr_show">
<%//first col in a day%>
<td rowspan = "<%=team_num%>"><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<br></td>
<td >
<select name="<%="hour_name"+String.valueOf(exchang_name)%>">
<%for (int hour_i=0;hour_i<24;hour_i++){%>
<option value="<%=hour_i%>" ><%=hour_i%></option>
<%}%>
</select>
<select name="<%="minute_name"+String.valueOf(exchang_name)%>" >
<%for (int minute_i=0;minute_i<60;minute_i++){%>
<option value="<%=minute_i%>" ><%=minute_i%></option>
<%}%>
</select>
<%exchang_name++;%>
<br></td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)" >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
roomuser_name++;
%>
</select>
<br></td>
<%}%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>

<%
//other col in the day
for (int team_num_i=1;team_num_i<team_num;team_num_i++){
%>
<tr class="tr_show">
<td>
<select name="<%="hour_name"+String.valueOf(exchang_name)%>" >
<%for (int hour_i=0;hour_i<24;hour_i++){%>
<option value="<%=hour_i%>" ><%=hour_i%></option>
<%}%>
</select>
<select name="<%="minute_name"+String.valueOf(exchang_name)%>" >
<%for (int minute_i=0;minute_i<60;minute_i++){%>
<option value="<%=minute_i%>" ><%=minute_i%></option>
<%}%>
</select>
<%exchang_name++;%>
<br></td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>"   onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)" >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
roomuser_name++;
%>
</select>
<br></td>
<%}%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>
<%}%>


<%
cal_start.add(5,1);
date_name++;
exchang_name++;
%>
<tr class="tr_show">
<td>
<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<br></td>
<td>
<select name="<%="hour_name"+String.valueOf(exchang_name)%>" >
<%for (int hour_i=0;hour_i<24;hour_i++){%>
<option value="<%=hour_i%>" ><%=hour_i%></option>
<%}%>
</select>
<select name="<%="minute_name"+String.valueOf(exchang_name)%>" >
<%for (int minute_i=0;minute_i<60;minute_i++){%>
<option value="<%=minute_i%>" ><%=minute_i%></option>
<%}%>
</select>
<br></td>
<td colspan="<%=user_num%>">
<br></td>
</tr>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name="date_num" value="<%=date_name%>">
<input type="hidden" name="team_num" value="<%=team_num%>">
<input type="hidden" name="user_num" value="<%=user_num%>">
</table>
<%
///////////////////////////////////assign_manu
}

///////////////////////////////////assign_modify
else if((AssignType.equals("assign_modify")))
{
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%
//get how many user a team have
int user_num = Integer.parseInt(String.valueOf(request.getAttribute("USERNUM")).trim());
//write title
%>
<tr class="td_label">
<td class="label">日期<br></td>
<td class="label">时间<br></td>
<td class="label">值班长<br></td>
<%for (int i=1;i<user_num;i++){%>
<td class="label">值班人员<%=i%><br></td>
<%}%>
<%--<td class="label">值班专家<br></td>--%>
</tr>

<%
Vector vecAssignAuto = (Vector)request.getAttribute("vecAssignAuto");
Vector vecDutydate = new Vector();
//Vector vecWorkId = new Vector();
Vector vecUser = new Vector();
Vector vecDutyman = new Vector();
if (vecAssignAuto.size() > 0){
vecDutydate = (Vector)vecAssignAuto.elementAt(0);
//vecWorkId = (Vector)vecAssignAuto.elementAt(1);
vecUser = (Vector)vecAssignAuto.elementAt(1);
}
//get time_start,time_end and convert to calendar
int year_start = Integer.parseInt(String.valueOf(request.getAttribute("YEARSTART")).trim());
int month_start = Integer.parseInt(String.valueOf(request.getAttribute("MONTHSTART")).trim());
int day_start = Integer.parseInt(String.valueOf(request.getAttribute("DAYSTART")).trim());
int year_end = Integer.parseInt(String.valueOf(request.getAttribute("YEAREND")).trim());
int month_end = Integer.parseInt(String.valueOf(request.getAttribute("MONTHEND")).trim());
int day_end = Integer.parseInt(String.valueOf(request.getAttribute("DAYEND")).trim());
GregorianCalendar cal_start = new GregorianCalendar();
GregorianCalendar cal_end = new GregorianCalendar();
Date date_start = new Date(year_start-1900,month_start-1,day_start-0);
Date date_end = new Date(year_end-1900,month_end-1,day_end-0);
cal_start.setTime(date_start);
cal_end.setTime(date_end);
//get how many team a day have
int team_num = Integer.parseInt(String.valueOf(request.getAttribute("TEAMNUM")).trim());
//init the "text,select" name
int date_name = 0;
int exchang_name = 0;
int roomuser_name = 0;
//get the exchang_time and room_user Vector from the TawRmSysteminfo
Vector vector_exchang_time = (Vector)request.getAttribute("EXCHANGETIME");
Vector vector_room_user = (Vector)request.getAttribute("RoomUser");
//get how many user in the room
int user_num_all = vector_room_user.size() / 2 ;
%>

<%
//begin iterate
for(;!cal_start.after(cal_end);){
//first col in a day
%>
<tr class="tr_show">
<td rowspan = "<%=team_num%>"><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<%
int intAssignAuto = -1;
for (int intAuto = 0 ; intAuto < vecDutydate.size();intAuto++){
if (String.valueOf(vecDutydate.elementAt(intAuto)).equals(String.valueOf(cal_start.get(cal_start.YEAR))+"-"+String.valueOf(cal_start.get(cal_start.MONTH)+1)+"-"+String.valueOf(cal_start.get(cal_start.DATE))+" "+vector_exchang_time.elementAt(0)+":00.0")) {
intAssignAuto = intAuto;
}
}
%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<br><td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)">
<%
if (intAssignAuto>=0){

for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
vecDutyman = (Vector)vecUser.elementAt(intAssignAuto);
if (vecDutyman.size() >= user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" <%if (StaticMethod.null2String(String.valueOf(vector_room_user.elementAt(room_user_i*2))).trim().equals(String.valueOf(vecDutyman.elementAt(user_num_i)).trim())){%> selected <%}%>><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
if (vecDutyman.size() < user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
}
if (intAssignAuto < 0){
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
roomuser_name++;
%>
</select>
<br></td>
<%
}%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>


<%
//other col in the day
for (int exchang_time_i=1;exchang_time_i<vector_exchang_time.size();exchang_time_i++){
intAssignAuto = -1;
for (int intAuto = 0 ; intAuto < vecDutydate.size();intAuto++){
//out.print(vecDutydate.elementAt(intAuto));
//out.print(String.valueOf(cal_start.get(cal_sart.YEAR))+"-"+String.valueOf(cal_start.get(cal_start.MONTH)+1)+"-"+String.valueOf(cal_start.get(cal_start.DATE))+" "+vector_exchang_time.elementAt(exchang_time_i)+":00.0"));
if (String.valueOf(vecDutydate.elementAt(intAuto)).equals(String.valueOf(cal_start.get(cal_start.YEAR))+"-"+String.valueOf(cal_start.get(cal_start.MONTH)+1)+"-"+String.valueOf(cal_start.get(cal_start.DATE))+" "+vector_exchang_time.elementAt(exchang_time_i)+":00.0")) {
intAssignAuto = intAuto;
}
}
%>
<tr class="tr_show">
<td>
<%
out.print(vector_exchang_time.elementAt(exchang_time_i));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(exchang_time_i)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td>
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)">
<%
if (intAssignAuto>=0){
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
vecDutyman = (Vector)vecUser.elementAt(intAssignAuto);
if (vecDutyman.size() >= user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" <%if (StaticMethod.null2String(String.valueOf(vector_room_user.elementAt(room_user_i*2))).equals(String.valueOf(vecDutyman.elementAt(user_num_i)))){%> selected <%}%>><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
if (vecDutyman.size() < user_num_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
}
if (intAssignAuto < 0){
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}
}
roomuser_name++;
%>
</select>
<br></td>
<%
}
%>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%></tr>
<%}%>
<%
cal_start.add(5,1);
date_name++;
}
//get the duty end time
exchang_name++;
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<tr>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name="date_num" value="<%=date_name%>">
<input type="hidden" name="team_num" value="<%=team_num%>">
<input type="hidden" name="user_num" value="<%=user_num%>">
<td>
<br></td>
</tr>
</table>
<%
///////////////////////////////////assign_modify
}
if (AssignType.equals("auto_new")){
///////////////////////////////////auto_cover
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" 

align=center>
<%
//get how many user a team have
int user_num = Integer.parseInt(String.valueOf(request.getAttribute("USERNUM")).trim());
//write title
%>
<tr class="td_label">
<td class="label">日期<br></td>
<td class="label">时间<br></td>
<td class="label">值班长<br></td>
<%for (int i=1;i<user_num;i++){%>
<td class="label">值班人员<%=i%><br></td>
<%}%>
<%--<td class="label">值班专家<br></td>--%>
</tr>

<%
//get time_start,time_end and convert to calendar
int year_start = Integer.parseInt(String.valueOf(request.getAttribute("YEARSTART")).trim());
int month_start = Integer.parseInt(String.valueOf(request.getAttribute("MONTHSTART")).trim());
int day_start = Integer.parseInt(String.valueOf(request.getAttribute("DAYSTART")).trim());
int year_end = Integer.parseInt(String.valueOf(request.getAttribute("YEAREND")).trim());
int month_end = Integer.parseInt(String.valueOf(request.getAttribute("MONTHEND")).trim());
int day_end = Integer.parseInt(String.valueOf(request.getAttribute("DAYEND")).trim());
GregorianCalendar cal_start = new GregorianCalendar();
GregorianCalendar cal_end = new GregorianCalendar();
Date date_start = new Date(year_start-1900,month_start-1,day_start-0);
Date date_end = new Date(year_end-1900,month_end-1,day_end-0);
cal_start.setTime(date_start);
cal_end.setTime(date_end);
//get how many team a day have
int team_num = Integer.parseInt(String.valueOf(request.getAttribute("TEAMNUM")).trim());
//init the "text,select" name
int date_name = 0;
int exchang_name = 0;
int roomuser_name = 0;
//get the exchang_time and room_user Vector from the TawRmSysteminfo
Vector vector_exchang_time = (Vector)request.getAttribute("EXCHANGETIME");
Vector vector_room_user = (Vector)request.getAttribute("RoomUser");
//get the first man sequense and set the user currently       for the use iterate
int user_num_current=1;
String sRoomUser = String.valueOf(request.getAttribute("SROOMUSER")).trim();
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++){
if (String.valueOf(vector_room_user.elementAt(room_user_i*2)).trim().equals(sRoomUser)){
user_num_current = room_user_i;
}
}
//get how many user in the room
int user_num_all = vector_room_user.size() / 2 ;
%>

<%
//begin iterate
for(;!cal_start.after(cal_end);){
java.util.Date date = new java.util.Date(
cal_start.get(cal_start.YEAR) - 1900, cal_start.get(cal_start.MONTH), 

 cal_start.get(cal_start.DATE));
 int numOfWeek = date.getDay();
 if((numOfWeek!=0&&numOfWeek!=6)||workSelect.equals("1")){
//first col in a day
%>
<tr class="tr_show">
<td rowspan = "<%=team_num+1%>" ><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
 

<br></tr>


<%
//other col in the day
for (int exchang_time_i=0;exchang_time_i<vector_exchang_time.size();exchang_time_i++){
%>
<tr class="tr_show">
<td>
<%
out.print(vector_exchang_time.elementAt(exchang_time_i));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" 

value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(exchang_time_i)%><%=":00"%>">
<%exchang_name++;%>
<br></td>
<%

%>
<td >
<input type="checkbox" name="check_user" value="<%="roomuser_name"+String.valueOf(roomuser_name)%>" /><select id="<%="roomuser_name"+String.valueOf(roomuser_name)%>" name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  onchange="changeUser(this,<%=String.valueOf(roomuser_name)%>)">
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{

if (user_num_current == room_user_i){
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>" 

selected><%out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}else{
%>
<option value="<%out.print(vector_room_user.elementAt(room_user_i*2));%>"><%

out.print(vector_room_user.elementAt(room_user_i*2+1));%></option>
<%
}//end if else
}//end for int room_user_i=0
roomuser_name++;
%>
</select>
<br></td>
<%--<td>
<select id="<%="expert_name"+String.valueOf(expert_name)%>" name="<%="expert_name"+String.valueOf(expert_name)%>" style="width:100" >
<%
for (int room_expert_i=0;room_expert_i*2<expert.size();room_expert_i++)
{
%>
<option value="<%out.print(expert.elementAt(room_expert_i*2));%>"><%out.print(expert.elementAt(room_expert_i*2+1));%></option>
<%} expert_name++;%></select><br></td>
--%><%

if(exchang_time_i==2 ||exchang_time_i==0 )
user_num_current++;
if(exchang_time_i==1)
user_num_current--;
if(user_num_current==0)
user_num_current=3;
if (user_num_current >= user_num_all)
user_num_current=1;

%>
</tr>
<%}%>  
<%
date_name++;
}
cal_start.add(5,1);


}
//get the duty end time
exchang_name++;
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" 

value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<tr>
<input type="hidden" name = "roomId" value = "<%=roomId%>">
<input type="hidden" name="date_num" value="<%=date_name%>">
<input type="hidden" name="team_num" value="<%=team_num%>">
<input type="hidden" name="user_num" value="<%=user_num%>">
<input type="hidden" name="flag" value="1">

<td>
<br></td>
</tr>
</table>
<%
///////////////////////////////////auto_cover
}
%>

<table height="32" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td align="left">
<%if (AssignType.equals("auto_cover")||AssignType.equals("assign_auto")||AssignType.equals("auto_new") ){%>
<bean:message key="label.save"/>
<select name="s_cycle_num" >
<%for (int i=1;i<21;i++){%>
<option value="<%=i%>"><%=i%></option>
<%}%>
</select>
<bean:message key="TawRmAssignwork.cycle"/>
<%}%>
      <%--<html:submit styleClass="clsbtn2">
        <bean:message key="label.save"/>
      </html:submit>
      --%><input type="submit" name="sub1" class="button" value= <bean:message key="label.save"/> >
      &nbsp;&nbsp;&nbsp;
<%--<input type="button" class="button" name="save" value="保存并设置为周期模板" onclick="javascript:model_set();">

  --%></td>
</tr>
</table>
</td>
</tr>
  </table>
</td>
</tr>
</table>

</html:form>

</body>

