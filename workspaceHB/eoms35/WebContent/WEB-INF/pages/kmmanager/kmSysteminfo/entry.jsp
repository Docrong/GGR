<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<SCRIPT LANGUAGE=javascript>
<!--
function add_Time()
{	   
   var selected_spr_text = "";
   var abc = document.forms[0].sHour.value; 
   selected_spr_value=document.forms[0].sHour.value+":"+document.forms[0].sMinute.value;
   var sel_sprlen=document.forms[0].sTime.options.length-1;

   var exist_flag=1;
   var j=0;
   for(j=0;j<=sel_sprlen;j++)
   {
     if(document.forms[0].sTime.options[j].value==selected_spr_value)
     {
       exist_flag=0;
       break;
     }
   }

   if(exist_flag)
   {
     if (sel_sprlen >= 0){
     	var i=0;
        var k=0;
     	for(j=0;j<=sel_sprlen;j++)
     	{
     		if(selected_spr_value > document.forms[0].sTime.options[j].value)
     		{
                    i=j;
                    k=1;
     		}
     	}

        if (k==0){
            i--;
        }
        for (j=sel_sprlen;j>i;j--)
        {
 		  selected_spr_text = document.forms[0].sTime.options[j].text;
     		  var test1 = new Option(selected_spr_text);
     		  test1.value=document.forms[0].sTime.options[j].value;
     		  document.forms[0].sTime.options[j+1]=test1;
 	 }
         var test1=new Option(document.forms[0].sHour.value+":"+document.forms[0].sMinute.value);
       	 test1.value=selected_spr_value;
         document.all.sTime.options[i+1]=test1;

     }else{
     	var test1=new Option(document.forms[0].sHour.value+":"+document.forms[0].sMinute.value);
     	test1.value=selected_spr_value;
     	document.all.sTime.options[0]=test1;
     }

   }
   else alert("<bean:message key="entry.TawRmSysteminfo.warnReset" />");

}
function del_Time()
{
  var sel_sprindex=document.forms[0].sTime.selectedIndex;
  if(sel_sprindex!=-1)
    document.forms[0].sTime.options[sel_sprindex]=null;
}

function sent_submit()
{
var sel_sprlen=document.forms[0].sTime.options.length-1;
//针对特殊排班的处理
if(<%=StaticMethod.getNodeName("DUTY.OtherAssign").equals(request.getAttribute("roomId"))?"true":"false"%> && sel_sprlen>1)
  {
  //alert("提示:该机房每天只能有两个班次!");
  //return;
  }
if (sel_sprlen >= 0){
document.forms[0].exchangetime.value =  document.forms[0].sTime.options[0].value;
if (sel_sprlen >= 1){
for (i=1;i<=sel_sprlen;i++){
document.forms[0].exchangetime.value =  document.forms[0].exchangetime.value + "," + document.forms[0].sTime.options[i].value;
}
}
document.forms[0].action="../Kmsysteminfo/save.do";
document.forms[0].submit();
}else{
alert ("<bean:message key="entry.TawRmSysteminfo.warnSetTime" />");
}
}
//alter table taw_rm_systeminfo add cycle_time int 
//-->
</SCRIPT>
<br>
<html:form method="post" action="/Kmsysteminfo/save">
<html:hidden property="strutsAction"/>
<input type="hidden" name="SAVEFLAG" value="<%=String.valueOf(request.getAttribute("SAVEFLAG"))%>"/>
<table border="0" width="100%" cellspacing="1" cellpadding="1" class="listTable" align=center>
	<caption>
		<%=request.getAttribute("ROOMNAME") %>
		 
		<%
		String saveflag = String.valueOf(request.getAttribute("SAVEFLAG"));
		if (saveflag.trim().equals("true"))
		{
		%>
		 <font color="red"><bean:message key="entry.TawRmSysteminfo.savesuccess"/></font>
		<%
		}
		%>
	</caption>
	<tr>
		<td class="label">
			<html:hidden property="roomId" />
			<html:hidden property="deptId" />
			<bean:message key="TawRmSysteminfo.maxdutynum"/>
		</td>
		<td>
			<html:select property="maxdutynum" styleClass="select">
				<html:options collection="MAXDUTYNUM" property="value"  labelProperty="label"/>
			</html:select>
			<bean:message key="TawRmSysteminfo.maxdutynum_unit"/>
		</td>
	</tr>
	<tr>
		<td class="label"><bean:message key="entry.TawRmSysteminfo.cycleTime"/></td>
		<td>
			<html:select property="cycleTime" styleClass="select">
				<html:options collection="CYCLETIME" property="value"  labelProperty="label"/>
			</html:select>
			<bean:message key="TawRmSysteminfo.maxerrortime_unit"/>
		</td>
	</tr>
	<tr>
		<td class="label"><bean:message key="entry.TawRmSysteminfo.staggertime"/></td>
		<td>
			<html:select property="staggertime" styleClass="select">
				<html:options collection="STAGGER" property="value"  labelProperty="label"/>
			</html:select>
			<bean:message key="entry.TawRmSysteminfo.maxerrortime_hours"/>
		</td>
	</tr>
	<tr>
		<td class="label"><bean:message key="TawRmSysteminfo.maxerrortime"/></td>
		<td>
			<html:select property="maxerrortime" styleClass="select">
				<html:options collection="MAXERRORTIME" property="value"  labelProperty="label"/>
			</html:select>
			<bean:message key="entry.TawRmSysteminfo.maxerrortime_unit"/>
		</td>
	</tr>
	<tr>
		<td class="label"><bean:message key="entry.TawRmExchange.exchangetime"/></td>
		<td>
			<table>
			<tr>
				<td width="30%">
					<select name="sHour">
					<%
					for (int i=0;i<24;i++){
					if (i<10){
					%>
					<option value="0<%=i%>"><%=i%></option>
					<%}else{%>
					<option value="<%=i%>"><%=i%></option>
					<%}}%>
					</select>
					<bean:message key="entry.TawRmSysteminfo.hour_unit"/>
					<select name="sMinute">
					<%
					for (int i=0;i<60;i++){
					if (i<10){
					%>
					<option value="0<%=i%>"><%=i%></option>
					<%}else{%>
					<option value="<%=i%>"><%=i%></option>
					<%}}%>
					</select>
					<bean:message key="entry.TawRmSysteminfo.minute_unit"/>
				</td>
				<td class="label" width="10%">
					<input type=button Class="button" name=add value =<bean:message key="entry.TawRmSysteminfo.btn_add"/> onclick="javascript:add_Time();">
					<p>
					<input type=button Class="button" name=del value =<bean:message key="entry.TawRmSysteminfo.btn_del"/> onclick="javascript:del_Time();">
				</td>
				<td width="80%">
					<c:choose>
					<c:when test="${requestScope['kmsysteminfoForm'].strutsAction == 1}">
					<select name="sTime" size=5 class="select">
					</select>
					</c:when>
					<c:otherwise>
					<%
					Vector vector_exchangetime = (Vector)request.getAttribute("exchangetime");
					%>
					<select name="sTime" size=8 class="select">
					<%
					for (int i=0;i<vector_exchangetime.size();i++){
					%>
					<option value="<%=vector_exchangetime.elementAt(i)%>"><%=vector_exchangetime.elementAt(i)%></option>
					<%
					}
					%>
					</select>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</table>
		</td>
	</tr>
<input type="hidden" name="exchangetime" value = "">
    <tr>
		<td class="label"><bean:message key="entry.TawRmSysteminfo.exRequest"/></td>
		<td>
			<font size="2" face="Tahoma">
			<html:select property="exRequest" styleClass="select">
				<html:options collection="EXREQUEST" property="value"  labelProperty="label"/>
			</html:select>
			</font>
		</td>
	</tr>
    <tr>
		<td class="label"><bean:message key="entry.TawRmSysteminfo.exAnswer"/></td>
		<td>
			<font size="2" face="Tahoma">
			<html:select property="exAnswer" styleClass="select">
				<html:options collection="EXANSWER" property="value"  labelProperty="label"/>
			</html:select>
			</font>
		</td>
	</tr>
    <tr>
		<td class="label"><bean:message key="entry.TawRmSysteminfo.dutyInform"/></td>
		<td>
			<font size="2" face="Tahoma">
			<html:select property="dutyInform" styleClass="select">
				<html:options collection="DUTYINFORM" property="value"  labelProperty="label"/>
			</html:select>
			</font>
		</td>
	</tr>
    <tr>
		<td class="label"><bean:message key="entry.TawRmSysteminfo.exchangeFlag"/></td>
		<td>
			<font size="2" face="Tahoma">
			<html:select property="exchangeFlag" styleClass="select">
				<html:options collection="LISTFLAG" property="value"  labelProperty="label"/>
			</html:select>
			</font>
		</td>
	</tr>
</table>
<br>
<logic:messagesPresent>
  <bean:message key="errors.header"/>
  <ul>
    <html:messages id="error">
      <li>
        <bean:write name="error"/>
      </li>
    </html:messages>
  </ul>
  <hr/>
</logic:messagesPresent>
<br>
<input type="button" Class="button" name=save value=<bean:message key="entry.TawRmSysteminfo.btn_save"/> onclick="sent_submit();">&nbsp;&nbsp;
<INPUT id="transmit" type="button" Class="button" value='<bean:message key="entry.TawUserRoom.btn_new"/>'  name=button Onclick="window.location.href ='../KmuserRoom/new.do?roomId=<bean:write name="kmsysteminfoForm" property="roomId"/>'">&nbsp;&nbsp;
<%--<INPUT id="expert" type="button" Class="button" value='配置专家' name=button Onclick="window.location.href ='../TawUserRoom/expert.do?roomId=<bean:write name="tawRmSysteminfoForm" property="roomId"/>'">
--%></html:form>

<%@ include file="/common/footer_eoms.jsp"%>

