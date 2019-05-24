<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod,com.boco.eoms.duty.model.TawRmAssignwork"%>
<%!
 public String FormatDate(String time)
{
 String shorttime=time.substring(time.indexOf(" "));
 shorttime=shorttime.substring(0,shorttime.length()-3);
 return shorttime;
}
%>
<%
Vector RoomUser=(Vector)request.getAttribute("RoomUser");
Vector vectorWorkseiral=(Vector)request.getAttribute("vectorWorkseiral");
%>
<head>
<title></title>
 

</head>
<html:form method="post" action="/TawRmRecordPer/showresult.do">
<input type="hidden" name="starttime" value="<%=request.getAttribute("starttime")%>">
<input type="hidden" name="endtime" value="<%=request.getAttribute("endtime")%>">
<input type="hidden" name="roomId" value="<%=request.getAttribute("roomId")%>">
<br>
<table cellpadding="0" cellspacing="0" border="0" width="400" align="center">
<tr>
<td align="center" valign="middle" class="table_title" colspan="2"><bean:message key="TawRmRecordPer.dutystat"/></td>
</tr>
</table>
<table  cellspacing="1" cellpadding="1" border="0" width="400" align="center" class="listTable">
<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecordPer.selectstattype"/>
</td>
<td>
               <select name="typename">
               <option value=""><bean:message key="TawRmRecordPer.select"/></option><%--
               <option value="<bean:message key="TawRmRecordPer.gongdan"/>"><bean:message key="TawRmRecordPer.gongdan"/></option>
               --%><option value="<bean:message key="TawRmRecordPer.duty"/>"><bean:message key="TawRmRecordPer.duty"/></option><%--
               <option value="<bean:message key="TawRmRecordPer.workplan"/>"><bean:message key="TawRmRecordPer.workplan"/></option>
               <option value="<bean:message key="TawRmRecordPer.guzhanggongdan"/>"><bean:message key="TawRmRecordPer.guzhanggongdan"/></option>
               <option value="<bean:message key="TawRmRecordPer.workdutygd"/>"><bean:message key="TawRmRecordPer.workdutygd"/></option>
               <option value="<bean:message key="TawRmRecordPer.jushujugd"/>"><bean:message key="TawRmRecordPer.jushujugd"/></option>
               <option value="<bean:message key="TawRmRecordPer.shengaonggd"/>"><bean:message key="TawRmRecordPer.shengaonggd"/></option>
               <option value="<bean:message key="TawRmRecordPer.dlsqddgd"/>"><bean:message key="TawRmRecordPer.dlsqddgd"/></option>
               <option value="<bean:message key="TawRmRecordPer.txfhydsqgd"/>"><bean:message key="TawRmRecordPer.txfhydsqgd"/></option>
               <option value="<bean:message key="TawRmRecordPer.wyzxgd"/>"><bean:message key="TawRmRecordPer.wyzxgd"/></option>
               <option value="<bean:message key="TawRmRecordPer.ywzxgd"/>"><bean:message key="TawRmRecordPer.ywzxgd"/></option>
               <option value="<bean:message key="TawRmRecordPer.yhsqspgd"/>"><bean:message key="TawRmRecordPer.yhsqspgd"/></option>
               <option value="<bean:message key="TawRmRecordPer.xlgjgd"/>"><bean:message key="TawRmRecordPer.xlgjgd"/></option>
               <option value="<bean:message key="TawRmRecordPer.qggd"/>"><bean:message key="TawRmRecordPer.qggd"/></option>
               --%></select>
</td>
</tr>
<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecordPer.finishtype"/>
</td>
<td>
               <select name="flag">
               <option value=""><bean:message key="TawRmRecordPer.select"/></option>
               <option value="0"><bean:message key="TawRmRecordPer.nofinish"/></option>
               <option value="1"><bean:message key="TawRmRecordPer.finished"/></option>
               </select>

</td>
</tr>

<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecordPer.operator"/>
</td>
<td>
               <select name="userId">
               <option value=""><bean:message key="TawRmRecordPer.select"/></option>
               <%
               for(int i=0;i<RoomUser.size();i=i+2) {
               %>
               <option value='<%=RoomUser.elementAt(i).toString()%>'><%=RoomUser.elementAt(i+1).toString()%></option>
               <%}%>
               </select>
</td>
</tr>

<tr class="tr_show">
<td class="label">
    <bean:message key="TawRmRecordPer.class"/>
</td>
<td>
               <select name="Workseiral" <%if (vectorWorkseiral==null) out.print("disabled");%> >
               <option value=""><bean:message key="TawRmRecordPer.select"/></option>
               <%
               if(vectorWorkseiral!=null)
               {
                TawRmAssignwork tawRmAssignwork=null;
                for(int j=0;j<vectorWorkseiral.size();j++)
     		{
     		 tawRmAssignwork=(TawRmAssignwork)vectorWorkseiral.elementAt(j);
     		 out.println("<option value='"+tawRmAssignwork.getId()+"'>"+FormatDate(tawRmAssignwork.getStarttimeDefined())+"-"+FormatDate(tawRmAssignwork.getEndtimeDefined())+"</option>");
      		 }
               }
               %>
               </select>

</td>
</tr>

</table>

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

<table cellpadding="0" cellspacing="0" border="0" width="300" align="center" >
<tr align="left">
<td height="32" align="left">
      
      <input type="submit" name="name" value =<bean:message key="TawRmSysteminfo.stat"/> class="button" >
  </td>
</tr>
</table>
<%
Vector vecPerRecords = (Vector) request.getAttribute("vecPerRecords");
if(vecPerRecords!=null)
{
Vector vecPerRecord =null;
String record_id = "";
String Workserial = "";
String Recordtime = "";
String DutyMan = "";
String DutyManName = "";
String Dutyrecord = "";
int complete_flag = 0;
String url="";
String typename="";
%>
<TABLE cellSpacing="0" cellPadding="0" width="95%"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1" class="listTable">
<TR>
        <TD align="center" class="label" colSpan="6"><bean:message key="TawRmRecordPer.dutyrecord"/></TD>
</TR>
<TR>
        <TD align="center" class="label" ><bean:message key="TawRmRecordPer.recordman"/></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecordPer.recordtype"/></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecordPer.recordtime"/></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecordPer.recorddetail"/></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecordPer.finishstate"/></TD>
</TR>
<%
for (int i=0;i<vecPerRecords.size();i++){
vecPerRecord = new Vector();
vecPerRecord = (Vector) vecPerRecords.elementAt(i);
record_id = String.valueOf(vecPerRecord.elementAt(0));
Workserial = String.valueOf(vecPerRecord.elementAt(1));
Recordtime = String.valueOf(vecPerRecord.elementAt(2));
DutyMan = String.valueOf(vecPerRecord.elementAt(3));
DutyManName = String.valueOf(vecPerRecord.elementAt(4));
Dutyrecord = String.valueOf(vecPerRecord.elementAt(5));
complete_flag = Integer.parseInt(String.valueOf(vecPerRecord.elementAt(6)));
url = String.valueOf(vecPerRecord.elementAt(7));
typename = String.valueOf(vecPerRecord.elementAt(8));

%>
<TR>
        <TD align="center" class="label" ><%=DutyManName%>&nbsp;</TD>
        <TD align="center" class="label" ><%=typename%></TD>
        <TD align="center" class="label" ><%=Recordtime%>&nbsp;</TD>
		<TD align="center" class="label">
        <%if(url==null || "".equals(url.trim())){%>
           <textarea rows="2" cols="30" readonly><%=Dutyrecord%></textarea>
        <%}else{%>
              [<a href="<%=url%>" target="_blank"><%=Dutyrecord%></a>]
		<%}%>
        </TD>
	    <TD align="center" class="label" ><%if (complete_flag==0){%><bean:message key="TawRmRecordPer.nofinish"/><%}else{%><bean:message key="TawRmRecordPer.finished"/><%}%></TD>
</TR>
<%
vecPerRecord =null;
}
vecPerRecords =null;
%>
</Table>
<TABLE  align="center" >
<TR>
        <TD align="middle" colSpan="6" class="clsfth">
          <input type=button Class="button" name="load" value="<bean:message key="TawRmRecordPer.print"/>" onclick="javascript:window.print();">&nbsp;&nbsp;&nbsp;
        </TD>
</TR>
</Table>
<%
}
%>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

