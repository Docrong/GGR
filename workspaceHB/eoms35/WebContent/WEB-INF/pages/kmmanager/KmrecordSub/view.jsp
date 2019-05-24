<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page language="java" import ="com.boco.eoms.common.util.StaticMethod,com.boco.eoms.duty.util.StaticDutycheck"%>
 
<html>
<head>
<title></title>

</head>
<body>
<%
String dutycheck = request.getAttribute("dutycheck").toString();
%>
<center>
<br>
<TABLE cellSpacing="0" cellPadding="0" width="500" align="center" border="0">
<tr>
<td width="100%" align="center" class="table_title">
    &nbsp;<bean:message key="TawRmRecord.tablename"/>
</td>
</tr>
</table>
<table border="0" width="500" cellspacing="1" cellpadding="1" class="listTable" align=center>
<logic:present name="tawRmRecordSubForm" scope="request">
<TR class="tr_show">
        <TD class="label"><bean:message key="TawRmRecordSub.dutyman"/></TD>
        <TD>
            <%String strDutyman = String.valueOf(request.getAttribute("DUTYMAN"));%>
            <input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" maxlength="150" size="20" value="<%=strDutyman%>" />
        </TD>
        <TD class="label"><bean:message key="TawRmRecordSub.roomId"/></TD>
        <TD>
            <%String strRoomname = String.valueOf(request.getAttribute("ROOMNAME"));%>
        <input name="roomId" id="roomId" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=strRoomname%>" />
        </TD>
</TR>
<TR class="tr_show">
        <TD class="label"><bean:message key="TawRmRecordSub.starttimeDefined"/></TD>
        <TD>
        <input name="starttimeDefined" id="starttimeDefined" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordSubForm" property="starttimeDefined" scope="request"/>" />
        </TD>
        <TD class="label"><bean:message key="TawRmRecordSub.endtimeDefined"/></TD>
        <TD>
        <input name="endtimeDefined" id="endtimeDefined" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordSubForm" property="endtimeDefined" scope="request"/>" />
        </TD>
</TR>
<TR class="tr_show">
        <TD class="label"><bean:message key="TawRmRecordSub.starttime"/></TD>
        <TD>
        <input name="starttime" id="starttime" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordSubForm" property="starttime" scope="request"/>" />
        </TD>
        <TD class="label"><bean:message key="TawRmRecordSub.endtime"/></TD>
        <TD>
        <input name="endtime" id="endtime" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordSubForm" property="endtime" scope="request"/>" />
        </TD>
</TR>
<TR class="tr_show">
        <TD class="label"><bean:message key="TawRmRecordSub.workflag"/></TD>
        <TD>
        <%
        String WorkFlag = String.valueOf(request.getAttribute("WORKFALG"));
      
        %>
        <input name="workflag" id="workflag" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=WorkFlag%>" />
        </TD>
        <TD class="label"><bean:message key="TawRmRecordSub.status"/></TD>
        <TD>
        <%
         String Status = String.valueOf(request.getAttribute("STATUS"));
        
        %>
        <input name="status" id="status" type="text" style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly="" size="20" value="<%=Status%>" />
        </TD>
</TR>

<tr class="tr_show">
  <td class="label">
    
  </td>
  <td colspan="3" >
<%
  for(int i = 1; i <= StaticDutycheck.dutycheckcount; i++ ){
    if (dutycheck.lastIndexOf(StaticDutycheck.getDutycheckId(i))!=-1)
      out.print(StaticDutycheck.getDutycheckName( StaticDutycheck.getDutycheckId(i) ) + " ");
  }
%>
  </td>
</tr>

<TR class="tr_show">
        <TD colSpan="1" class="label"><bean:message key="TawRmRecordSub.statement"/></TD>
          <TD colSpan="3"><textarea name="statement" style="BACKGROUND-COLOR: lightgrey" id="statement" rows="4" cols="60" readOnly="" Class="clstext" type="_moz"><bean:write name="tawRmRecordSubForm" property="statement" scope="request"/>&nbsp;</textarea>
            <span id="Customvalidator2" controltovalidate="Statement" errormessage="<BR> " display="Dynamic" evaluationfunction="CustomValidatorEvaluateIsValid" clientvalidationfunction="CheckStatement" style="color:Red;display:none;"><BR>
             </span></TD>
</TR>
</table>
<table border="0" width="500" cellspacing="1" cellpadding="1" align=center>
<TR>
        <TD height="32" align="right">
        <input type = "button" Class="button" name=<bean:message key="label.logoff"/> value=<bean:message key="label.logoff"/> onclick="javascript:window.close();">
        </TD>
</TR>

</logic:present>
<logic:notPresent name="tawRmRecordSubForm" scope="request">
  <tr>
    <td>
    <bean:message key="error.notfound"/>
    </td>
  <tr>
</logic:notPresent>
</table>
</body>
