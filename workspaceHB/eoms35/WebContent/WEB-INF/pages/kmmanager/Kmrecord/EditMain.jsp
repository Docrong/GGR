<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java" import ="com.boco.eoms.common.util.StaticMethod"%>
 
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.duty.controller.TawRmRecordForm,com.boco.eoms.duty.model.TawRmDutyfile,com.boco.eoms.duty.model.TawRmRecord"%>
<%
String roomName = request.getAttribute("roomName").toString();
List cutoversheetlist = (List) request
			.getAttribute("cutoversheetlist");
	List faultreportsheetlist = (List) request
			.getAttribute("faultreportsheetlist");
	List faultsheetlist = (List) request.getAttribute("faultsheetlist");
List monthPlanVOList = (List) request.getAttribute("monthPlanVOList");
%>

<html:form method="post" action="/TawRmRecord/updatemain">
 

<HEAD><TITLE>Index</TITLE>
<SCRIPT language=javascript>
			function CheckDutyRecord(oSrc, args){
				args.IsValid = (window.Form1.Dutyrecord.value.length <= 2000);
				}
		    function CheckNotes(oSrc, args){
				args.IsValid = (window.Form1.Notes.value.length <= 120);
				}
		</SCRIPT>
</HEAD>
<BODY>
<CENTER>
<SCRIPT language=javascript>
<!--
	function __doPostBack(eventTarget, eventArgument) {
		var theform = document.Form1;
		theform.__EVENTTARGET.value = eventTarget;
		theform.__EVENTARGUMENT.value = eventArgument;
		theform.submit();
	}
// -->
</SCRIPT>
<table border="0" width="85%" cellspacing="1" cellpadding="1" class="listTable" align=center>
  <TBODY>
  <html:hidden property="id" />
  <TR class="tr_show">
    <TD noWrap rowSpan="5" align="center" class="label">&nbsp;<bean:message key="TawRmRecord.baseinfo"/></TD>
    <TD noWrap class="label"><bean:message key="TawRmSysteminfo.roomName"/></TD>
    <TD colSpan=5>
    <INPUT id=RoomName  readOnly maxLength=150 value="<%=roomName%>" name=RoomName>
  <TR class="tr_show">
	<TD class="label">
            <bean:message key="TawRmRecord.hander"/>
        </TD>
	<TD colSpan="5">
        <html:hidden property="hander"  />
        <input name="hander" id="hander" type="text"  readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

        </TD>
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.dutyman"/></TD>
	<TD colSpan="5">
	<html:hidden property="dutyman" />
	<input name="dutyman" id="dutyman" type="text"  readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.receiver"/></TD>
	<TD colSpan="5">
	<html:hidden property="receiver" />
	<input name="receiver" id="receiver" type="text"  readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
        </TD>
</TR>
  <TR class="tr_show">
    <TD class="label"><bean:message key="TawRmRecord.receivertime"/></TD>
    <TD>
        <input name="starttime" type="text"  readOnly="" size="18" value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
    </TD>
    <TD class="label"><bean:message key="TawRmRecord.flag"/></TD>
    <TD colSpan="3" >
        <html:hidden property="flag" />
          <c:choose>
          <c:when test="${requestScope['tawRmRecordForm'].flag == 0}">
            <input name="flag" id="flag" type="text"  class="clstext" readOnly="" size="12" value='<bean:message key="TawRmRecord.compltetNo" />' />
          </c:when>
          <c:when test="${requestScope['tawRmRecordForm'].flag == 1}">
           <input name="flag" id="flag" type="text"  class="clstext" readOnly="" size="12" value='<bean:message key="TawRmRecord.compltetYes" />' />
          </c:when>
       </c:choose>
    </TD>
<TR class="tr_show">
	<TD noWrap rowSpan="2" align="center" class="label"><bean:message key="TawRmRecord.circumstance"/></TD>
	<TD class="label"><bean:message key="TawRmRecord.weather"/></TD>
	<TD ><html:hidden property="weather" /><input name="tempStr" id="tempStr" type="text"  class="clstext" readOnly="" size="20" value="<bean:write name="tawRmRecordForm" property="weather" scope="request"/>" />**
	<TD class="label"><bean:message key="TawRmRecord.temperature"/></TD>
        <TD ><html:hidden property="temperature" /><input name="tempStr" id="tempStr" type="text"  class="clstext" readOnly="" size="5" value="<bean:write name="tawRmRecordForm" property="temperature" scope="request"/>" />&nbsp;C
	</TD>
        <TD class="label"><bean:message key="TawRmRecord.wet"/></TD>
        <TD ><html:hidden property="wet" /><input name="tempStr" id="tempStr" type="text"  class="clstext" readOnly="" size="5" value="<bean:write name="tawRmRecordForm" property="wet" scope="request"/>" />&nbsp;%
	</TD>
	</TD>

</TR>
<TR class="tr_show">
	<TD class="label"><bean:message key="TawRmRecord.clean"/></TD>
	<TD ><html:hidden property="clean" /><input name="tempStr" id="tempStr" type="text"  class="clstext" readOnly="" size="10" value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>" />**
	</TD>
        <html:hidden property="clean1" />
        <TD class="label"><bean:message key="TawRmRecord.conditioner"/></TD>
        <TD colSpan="3" ><html:hidden property="conditioner" /><input name="tempStr" id="tempStr" type="text"  class="clstext" readOnly="" size="10" value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>" />**</TD>
</TR>

<TR class="tr_show">
	<TD noWrap align="center" class="label"><bean:message key="TawRmHangover.prehangquestion"/></TD>
	<TD colSpan="8">
        <TEXTAREA id=hangQuestion0 name=hangQuestion0 rows=4 cols=70  readOnly=""><%=String.valueOf(request.getAttribute("strHangQuestion"))%></TEXTAREA>
        </TD>
</TR>

  <TR class="tr_show">
    <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.accessories"/></TD>
    <%Vector vecDutyFile=(Vector)request.getAttribute("vecDutyFile");%>
    <TD colSpan="6" class="label">
        <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height=60 SCROLLING=YES SRC='../TawRmDutyfile/dutyfile.do?WORKSERIAL=<bean:write name="tawRmRecordForm" property="id"/>'>
        </IFRAME>
    </TD>
  </TR>
  <TR class="tr_show">
    <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.personrecord"/></TD>
    <TD colSpan=6>&nbsp;
      <SPAN id=spanSubReocrd>
        <logic:iterate id="tawRmRecordSub" name="TAWRMRECORD_DUTYMAN" type="com.boco.eoms.duty.model.TawRmRecordSub">
            <html:link href="../TawRmRecordSub/view.do" target="blank"  paramId="id" paramName="tawRmRecordSub" paramProperty="id">
                <bean:write name="tawRmRecordSub" property="dutyman" scope="page"/>
            </html:link>
        </logic:iterate>&nbsp;
      </SPAN></TD>
  </TR>
  <TR class="tr_show">
    <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.workrecord"/> </TD>
    <TD noWrap align=left colSpan=6><TEXTAREA id="dutyrecord" name="dutyrecord" rows=6 cols=64 ><bean:write name="tawRmRecordForm" property="dutyrecord" scope="request"/></TEXTAREA>
      <SPAN id=Customvalidator1 style="DISPLAY: none; COLOR: red"
      clientvalidationfunction="CheckDutyRecord"
      evaluationfunction="CustomValidatorEvaluateIsValid" display="Dynamic"
      errormessage="<BR>"
      controltovalidate="Dutyrecord"><BR></SPAN></TD></TR>
  <TR class="tr_show">
    <TD noWrap align="center" class="label"><bean:message key="TawRmRecord.notes"/></TD>
    <TD colSpan=6><TEXTAREA id=notes name=notes rows=4 cols=64 ><bean:write name="tawRmRecordForm" property="notes" scope="request"/></TEXTAREA>
      <SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
      clientvalidationfunction="CheckNotes"
      evaluationfunction="CustomValidatorEvaluateIsValid" display="Dynamic"
      errormessage="<BR>"
      controltovalidate="Notes"><BR></SPAN></TD></TR>
  <!-- add by sunshengtai start
	<tr class="tr_show">
		<td class="label"  rowspan="4" valign="middle">
			<bean:message key="TawRmRecordSub.watchSummary"/>
		</td>
		<td class="label">
			<bean:message key="TawRmRecordSub.importantNetFault"/>
		</td>
		<td colspan="2">			
			<textarea id="importantnetfault" name="importantnetfault" rows="3" cols="71">
				<bean:write name="tawRmRecordForm" property="importantnetfault" scope="request"/>
			</textarea>
		</td>
	</tr>
		
	<tr>
		<td class="label">
			<bean:message key="TawRmRecordSub.importantSocietyDisaster"/>
		</td>
		<td colspan="2">
			<textarea id="importantsocietydisaster" name="importantsocietydisaster" rows="3" cols="71">
				<bean:write name="tawRmRecordForm" property="importantsocietydisaster" scope="request"/>
			</textarea>
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<bean:message key="TawRmRecordSub.needCorrespond"/>
		</td>
		<td colspan="2">
			<textarea id="needcorrespond" name="needcorrespond" rows="3" cols="71">
				<bean:write name="tawRmRecordForm" property="needcorrespond" scope="request"/>
			</textarea>
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<bean:message key="TawRmRecordSub.handOverProceeding"/>
		</td>
		<td colspan="2">
			<textarea id="handoverproceeding" name="handoverproceeding" rows="3" cols="71">
				<bean:write name="tawRmRecordForm" property="handoverproceeding" scope="request"/>
			</textarea>
		</td>
	</tr> -->
	<!-- add by sunshengtai end -->
      <!--TR class="tr_show">
					<TD  class="label" colSpan="7" align="center">
						${eoms:a2u('值班期间处理的工单及作业计划')}
					</TD></TR>
 <TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('故障')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(faultsheetlist!=null&&faultsheetlist.size()>0){
					for(int i=0;i<faultsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('作业计划记录')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(monthPlanVOList!=null&&monthPlanVOList.size()>0){
					for(int i=0;i<monthPlanVOList.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)monthPlanVOList.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%>
					</TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
					${eoms:a2u('重大故障上报')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(faultreportsheetlist!=null&&faultreportsheetlist.size()>0){
					for(int i=0;i<faultreportsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR>
					<TR class="tr_show">
					<TD  class="label">
						${eoms:a2u('割接')}
					</TD>
					<TD  colSpan="6" class="label">
					<%
					if(cutoversheetlist!=null&&cutoversheetlist.size()>0){
					for(int i=0;i<faultsheetlist.size();i++){
					TawRmRecord tawRmRecord =new TawRmRecord();
					tawRmRecord=(TawRmRecord)faultsheetlist.get(i);
					String url=tawRmRecord.getSheetUrl();
									%>
					
					[<%=url%>]<br>
										<%}}%></TD>
					</TR-->
</TBODY></TABLE>
<table border="0" width="85%" cellspacing="1" cellpadding="1" align=center>
  <TR>
    <TD align="center" height="32">
        <input type = "submit" name = <bean:message key="label.save"/> class="button" value = <bean:message key="label.save"/> >
        <input type = "button" name = <bean:message key="label.back"/> class="button" value = <bean:message key="label.back"/> onclick = "javascript:history.back();">
    </TD>
  </TR>
</TABLE>
</CENTER></BODY>
</html:form>
