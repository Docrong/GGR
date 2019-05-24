<%@ page contentType="text/html; charset=gb2312"%>

<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<head>
<title>排班配置(第三步)</title>

</head>
<body>

<html:form method="post" action="/TawRmAssignwork/save_assign.do">
<html:hidden property="strutsAction"/>

<table cellpadding="0" cellspacing="0" border="0" width="800" align="center">

<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
<td width="100%" align="center" class="table_title">

<%=request.getAttribute("ROOMNAME") %>
&nbsp;&nbsp;&nbsp;
<bean:message key="TawRmAssignwork.apparatusroom"/>
&nbsp;&nbsp;&nbsp;
<bean:message key="TawRmAssignwork.tablename"/>

  </td>
</tr>
</table>
<table border="0"  cellspacing="1" cellpadding="1" align=center>
  <tr>
<td>

<%int roomId = Integer.parseInt(String.valueOf(request.getAttribute("roomId")).trim());%>



<%String AssignType = String.valueOf(request.getAttribute("ASSIGNTYPE")).trim();%>
<input type = "hidden" name = "assign_type" value = "<%=AssignType%>">
<%
if (AssignType.equals("auto_cover") || AssignType.equals("assign_auto")){
///////////////////////////////////auto_cover
%>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<%
//get how many user a team have
int user_num = Integer.parseInt(String.valueOf(request.getAttribute("USERNUM")).trim());
//write title
%>
<tr class="td_label">
<td><bean:message key="TawRmAssignwork.dutydate"/></td>
<td><bean:message key="TawRmAssignwork.starttimeDefined"/></td>
<td><bean:message key="TawRmAssignwork.dutymaster"/></td>
<%for (int i=1;i<user_num;i++){%>
<td><bean:message key="TawRmAssignSub.dutyman"/><%=i%></td>
<%}%>
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
//把用户信息存放到一个二维数组里
System.out.println("size()"+vector_room_user.size());
int groupNum=(vector_room_user.size()-2)/(2*user_num)+1;
System.out.println("groupNum="+groupNum);
int[][] RoomUserSuffix=new int[groupNum][user_num];
int initUserId=2;
for(int group_i=0;group_i<groupNum;group_i++)
{
   for(int user_num_i=0;user_num_i<user_num;user_num_i++)
   {
   RoomUserSuffix[group_i][user_num_i]=initUserId;
   initUserId+=2;
   }
}
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
int group_id=0;
int userOfgroup_id=0;
int[] flagGroup_id={RoomUserSuffix[0][0],RoomUserSuffix[0][0]};
boolean endflag=false;
//begin iterate
//cal_end(5,-1);    //最后一天单独做
for(;!cal_start.after(cal_end);){
//first col in a day
%>
<tr class="tr_show">
<td rowspan = "<%=team_num%>" ><%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>
<input type="hidden" name="<%="date_name"+String.valueOf(date_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%>">
<td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%
userOfgroup_id=0;
exchang_name++;
flagGroup_id[0]=flagGroup_id[1];
flagGroup_id[1]=RoomUserSuffix[group_id][userOfgroup_id];
System.out.println("group_id="+group_id);
if(group_id==groupNum-1)    //最后一天的排班
{
group_id=1;
endflag=true;
}
%>
</td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){
%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
if (RoomUserSuffix[group_id][userOfgroup_id] == room_user_i*2){
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
</td>
<%
/*
user_num_current++;
if (user_num_current >= user_num_all)
user_num_current=1;
*/
userOfgroup_id++;
}
group_id++;
%>
</tr>


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
</td>
<%
userOfgroup_id=0;
if(endflag) //最后一天的另一个班次
{
group_id=groupNum-1;
}
if(group_id==1)
{
  user_num_current=RoomUserSuffix[group_id][userOfgroup_id];
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
if (user_num_current== room_user_i*2){
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
</td>
<%
user_num_current+=2;
}
  group_id++;
}
else
{
  user_num_current=flagGroup_id[0];
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
<%
for (int room_user_i=0;room_user_i*2<vector_room_user.size();room_user_i++)
{
if (user_num_current== room_user_i*2){
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
</td>
<%
//userOfgroup_id++;
user_num_current+=2;
/*
user_num_current++;
if (user_num_current >= user_num_all)
user_num_current=1;
*/
}
}
%>
</tr>
<%
}
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
</td>
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
<td><bean:message key="TawRmAssignwork.dutydate"/></td>
<td><bean:message key="TawRmAssignwork.starttimeDefined"/></td>
<td><bean:message key="TawRmAssignwork.dutymaster"/></td>
<%for (int i=1;i<user_num;i++){%>
<td><bean:message key="TawRmAssignSub.dutyman"/><%=i%></td>
<%}%>
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
<td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
</td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
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
</td>
<%
user_num_current++;
if (user_num_current >= user_num_all)
user_num_current=1;
}%>
</tr>


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
</td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td >
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
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
</td>
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
</td>
</tr>
</table>
<%
///////////////////////////////////auto_daily
}
else if((AssignType.equals("assign_auto_other")))
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
<td ><bean:message key="TawRmAssignwork.dutydate"/></td>
<td><bean:message key="TawRmAssignwork.starttimeDefined"/></td>
<td><bean:message key="TawRmAssignwork.dutymaster"/></td>
<%for (int i=1;i<user_num;i++){%>
<td><bean:message key="TawRmAssignSub.dutyman"/><%=i%></td>
<%}%>
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
<td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
</td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
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
</td>
<%
}%>
</tr>


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
</td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
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
</td>
<%
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
</td>
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
<td><bean:message key="TawRmAssignwork.dutydate"/></td>
<td><bean:message key="TawRmAssignwork.starttimeDefined"/></td>
<td><bean:message key="TawRmAssignwork.dutymaster"/></td>
<%for (int i=1;i<user_num;i++){%>
<td><bean:message key="TawRmAssignSub.dutyman"/><%=i%></td>
<%}%>
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
</td>
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
</td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>"   >
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
</td>
<%}%>
</tr>

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
</td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>"   >
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
</td>
<%}%>
</tr>
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
</td>
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
</td>
<td colspan="<%=user_num%>">
</td>
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
<td ><bean:message key="TawRmAssignwork.dutydate"/></td>
<td><bean:message key="TawRmAssignwork.starttimeDefined"/></td>
<td><bean:message key="TawRmAssignwork.dutymaster"/></td>
<%for (int i=1;i<user_num;i++){%>
<td><bean:message key="TawRmAssignSub.dutyman"/><%=i%></td>
<%}%>
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
<td>
<%
out.print(vector_exchang_time.elementAt(0));
%>
<input type="hidden" name="<%="exchang_name"+String.valueOf(exchang_name)%>" value="<%=cal_start.get(cal_start.YEAR)%>-<%=cal_start.get(cal_start.MONTH)+1%>-<%=cal_start.get(cal_start.DATE)%><%=" "%><%=vector_exchang_time.elementAt(0)%><%=":00"%>">
<%exchang_name++;%>
</td>
<%for (int user_num_i=0;user_num_i<user_num;user_num_i++){%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
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
</td>
<%
}%>
</tr>


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
</td>
<%
for (int user_num_i=0;user_num_i<user_num;user_num_i++)
{
%>
<td>
<select name="<%="roomuser_name"+String.valueOf(roomuser_name)%>" style="width:100"  >
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
</td>
<%
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
</td>
</tr>
</table>
<%
///////////////////////////////////assign_modify
}
%>






<table height="32" cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
<td align="right">
<%if (AssignType.equals("auto_cover")||AssignType.equals("assign_auto")){%>
<bean:message key="label.save"/>
<select name="s_cycle_num" >
<%for (int i=1;i<11;i++){%>
<option value="<%=i%>"><%=i%></option>
<%}%>
</select>
<bean:message key="TawRmAssignwork.cycle"/>
<%}%>
      <html:submit styleClass="clsbtn2">
        <bean:message key="label.save"/>
      </html:submit>

  </td>
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

