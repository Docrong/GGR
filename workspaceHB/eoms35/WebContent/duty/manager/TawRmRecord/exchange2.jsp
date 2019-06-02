 <%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page language="java" import ="com.boco.eoms.common.util.StaticMethod,java.util.*,java.lang.*"%>
 
 
<html>
<head>
<title> <bean:message key="TawRmRecord.exchange"/></title>
 
</head>
<body    leftmargin="0" topmargin="0" >


<%String strWorkSerial = String.valueOf(request.getAttribute("strWorkSerial"));%>
<%String strNextWorkSerial = String.valueOf(request.getAttribute("strNextWorkSerial"));%>
<%String strHQ = String.valueOf(request.getAttribute("strHQ"));%>
<html:form method="post" action="/TawRmRecord/submit2">
<html:hidden property="id" />
 <TABLE cellSpacing="1" cellPadding="1" width="600" align="center"
					border="0" class="formTable">
                    <TR class="tr_show">
                    <TD align="center" class="label" colSpan="7" noWrap><bean:message key="TawRmRecord.exchange_per"/></TD>
                        
                    </TR>
                    <TR class="tr_show">
                    	<TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.nightrm_exchange"/></TD>
                    	<TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.dutyman1"/></TD>
                        <TD noWrap colSpan="1"><input name="dutyman" id="dutyman" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="20"
									value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" /></TD>
						<TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.receiver1"/></TD>
						<TD noWrap colSpan="1"><input name="receiver" id="receiver" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="20"
									value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" /></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.exchangetime"/></TD>
                        <TD noWrap colSpan="1">
							<input type="text" name="exchangeTime" size="20" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"  class="text">
						</TD>
                    </TR>
                    <TR class="tr_show">
                        <TD align="center" class="label" rowSpan="2" noWrap><bean:message key="TawRmRecord.alarmnums"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.pr_alarmnums"/></TD>
                        <TD noWrap >
							<html:text property="prAlarmNums" size="20"/>
						</TD>
						<TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.al_alarmnum"/></TD>
                        <TD noWrap >
							<html:text property="alAlarmNum" size="20"/>
						</TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.no_alarmnum"/></TD>
                        <TD noWrap >
							<html:text property="noAlarmNum" size="20"/>
						</TD>
                    </TR>
					<TR class="tr_show">
							<TD class="label" noWrap>
								<bean:message key="TawRmRecord.operationalstate" />
							</TD>
							<TD noWrap colSpan="5">
								<TEXTAREA id=notes name=operationalState rows=2 cols=80 type="_moz"><bean:write name="tawRmRecordForm" property="operationalState"  scope="request" /></TEXTAREA>
								<SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckNotes"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>" controltovalidate="Notes"><BR>
								</SPAN>
							</TD>
					</TR>
                    <TR class="tr_show">
                        <TD align="center" class="label" rowSpan="2" noWrap><bean:message key="TawRmRecord.sheet"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.sendsheetnum"/></TD>
                        <TD align="center" class="label" colSpan="2"  noWrap><bean:message key="TawRmRecord.leavesheetnum"/></TD>
                        <TD align="center" class="label" colSpan="2"  noWrap><bean:message key="TawRmRecord.leavesheetnums"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.chiefsheetnum"/></TD>
                    </TR>
					<TR class="tr_show">
							<TD noWrap colSpan="1">
							<html:text property="sendSheetNum" size="20"/>
						</TD>
						<TD noWrap colSpan="2">
							<html:text property="leaveSheetNum" size="40"/>
						</TD>
						<TD noWrap colSpan="2">
							<html:text property="leaveSheetNums" size="40"/>
						</TD>
						<TD noWrap colSpan="1">
							<html:text property="chiefSheetNum" size="20"/>
						</TD>
					</TR>
                    <TR class="tr_show">
                         <TD align="center" class="label" rowSpan="2" noWrap><bean:message key="TawRmRecord.operation"/></TD>
                         <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.purpose"/></TD>
                         <TD noWrap colSpan="5">
							<TEXTAREA id=notes name=purpose rows=2 cols=80 type="_moz"><bean:write name="tawRmRecordForm" property="purpose"  scope="request" /></TEXTAREA>
								<SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckNotes"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>" controltovalidate="Notes"><BR>
								</SPAN>
						</TD>
                    </TR>
                    <TR class="tr_show">
                         <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.complete_flag"/></TD>
                         <TD noWrap colSpan="5">
							<TEXTAREA id=notes name=completeFlag rows=2 cols=80 type="_moz"><bean:write name="tawRmRecordForm" property="completeFlag"  scope="request" /></TEXTAREA>
								<SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckNotes"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>" controltovalidate="Notes"><BR>
								</SPAN>
						</TD>
                    </TR>
                    <TR class="tr_show">
                    	<TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.linetype"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.tlinetype"/></TD>
                        <TD noWrap colSpan="1">
							<html:text property="toLinetype" size="20"/>
						</TD>
						<TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.ylinetype"/></TD>
                        <TD noWrap colSpan="1">
							<html:text property="yeLinetype" size="20"/>
						</TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.dif_linetype"/></TD>
                        <TD noWrap colSpan="1">
							<html:text property="difLinetype" size="20"/>
						</TD>
                    </TR>
                    <TR class="tr_show">
                         <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.attentive"/></TD>
                         <TD noWrap colSpan="6">
							<TEXTAREA id=notes name=attentive rows=2 cols=80 type="_moz"><bean:write name="tawRmRecordForm" property="attentive"  scope="request" /></TEXTAREA>
								<SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckNotes"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>" controltovalidate="Notes"><BR>
								</SPAN>
						</TD>
                    </TR>
                     <TR class="tr_show">
                        <TD align="center" class="label" rowSpan="2" noWrap><bean:message key="TawRmRecord.gross"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.city"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.sheetid"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.reason"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.person"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.phone"/></TD>
                        <TD align="center" class="label"  noWrap><bean:message key="TawRmRecord.rate"/></TD>
                    </TR>
					<TR class="tr_show">
						<TD noWrap colSpan="1">
							<html:text property="city" size="20"/>
						</TD>
						<TD noWrap colSpan="1">
							<html:text property="sheetId" size="20"/>
						</TD>
						<TD noWrap colSpan="1">
							<html:text property="reason" size="20"/>
						</TD>
						<TD noWrap colSpan="1">
							<html:text property="person" size="20"/>
						</TD>
						<TD noWrap colSpan="1">
							<html:text property="phone" size="20"/>
						</TD>
						<TD noWrap colSpan="1">
							<html:text property="rate" size="20"/>
						</TD>
					</TR>
           
 </TABLE>
		<TABLE cellSpacing="0" cellPadding="0" width="520" align="center" border="0" class="formTable">
                    <TR>
                      <TD>
                       <IFRAME ID=IFrame1  FRAMEBORDER=0 width="100%" height="130%" SCROLLING=YES SRC='../TawRmHangover/show.do?HANGWORKSERIAL=<%=strWorkSerial%>&RECEIVEWORKSERIAL=<%=strNextWorkSerial%>&strHQ=<%=strHQ%>'>
                       </IFRAME>
                      </TD>
                    </TR>
                </TABLE>
                <br>
<TABLE cellSpacing="0" cellPadding="0" width="600"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1">
<TR>
        <TD align="center" class="clsscd1" colSpan="6"><bean:message key="TawRmRecord.nextWork" /></TD>
</TR>
<TR>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordName" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordType" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordTime" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.recordContant" /></TD>
        <TD align="center" class="label" ><bean:message key="TawRmRecord.isCompate" /></TD>
</TR>
<%
Vector vecPerRecords = new Vector();
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

vecPerRecords = (Vector) request.getAttribute("vecPerRecords");
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
	    <TD align="center" class="label" ><%if (complete_flag==0){%>
	   <bean:message key="TawRmRecord.compltetNo" />
	    <%}else{%>
	   <bean:message key="TawRmRecord.compltetYes" />
	    <%}%></TD>
</TR>
<%
vecPerRecord =null;
}
vecPerRecords =null;
%>
</TABLE>
                <br>
		<TABLE cellSpacing="1" cellPadding="1" width="290" align="center" border="0" class="formTable">
                    <TR class="tr_show">
                        <TD class="label" align="middle" valign="center" width="130" Nowrap><bean:message key="TawRmRecord.dutymanname"/></TD>
                        <TD>
                            <%Vector vecDutyManId = (Vector) request.getAttribute("vecDutyManId");%>
                            <%Vector vecDutyManName = (Vector) request.getAttribute("vecDutyManName");%>
                            <select name="Hander">
                            <%
                                for(int i=0;i<vecDutyManId.size();i++) {
                            %>
                               <option value='<%=vecDutyManId.get(i).toString()%>'><%=vecDutyManName.get(i).toString()%></option>
                            <%}%>
                            </select>
                        </TD>
                    </TR>
                    <TR class="tr_show">
                        <TD class="label"><bean:message key="TawRmRecord.handerpassword"/></TD>
                        <TD><input name="HanderPwd" id="HanderPwd" type="password" style="WIDTH: 153px; HEIGHT: 24px" maxlength="25" /></TD>
                    </TR>
					<TR>
                        <TD class="label"><bean:message key="TawRmRecord.exflag"/></TD>
                        <TD><input type="radio" name="fruit" value = "alread"><bean:message key="TawRmRecord.alread"/>
							<input type="radio" name="fruit" checked value = "noread"><bean:message key="TawRmRecord.noread"/></TD>
                    </TR>
                    <%if (!strNextWorkSerial.equals("-1")){%>
                    <TR class="tr_show">
                        <TD class="label"><bean:message key="TawRmRecord.receivername"/></TD>
                        <TD>
                            <%Vector vecReceiverId = (Vector) request.getAttribute("vecReceiverId");%>
                            <%Vector vecReceiverName = (Vector) request.getAttribute("vecReceiverName");%>
                            <select name="Receiver">
                            <%
                                for(int i=0;i<vecReceiverId.size();i++) {
                            %>
                               <option value='<%=vecReceiverId.get(i).toString()%>'><%=vecReceiverName.get(i).toString()%></option>
                            <%}%>
                            </select>
                        </TD>
                       
                    </TR>
                    <TR class="tr_show">
                        <TD class="label"><bean:message key="TawRmRecord.receiverpassword"/></TD>
                        <TD><input name="ReceiverPwd" id="ReceiverPwd" type="password" style="WIDTH: 153px; HEIGHT: 24px" maxlength="25" /></TD>
                    </TR>
                    <%}else{%>
                    <TR class="tr_show">
                        <TD colspan="4" align = "center"><bean:message key="TawRmRecord.notnextworkserial"/></TD>
                    </TR>
                    <%}%>
                    <input type = "hidden" name = "boolNextWorkSerial" value = "<%=strNextWorkSerial%>">
                    <TR class="tr_show">
                        <TD colspan="2" align="middle" height="30">&nbsp;
                        <html:submit styleClass="button">
                        <bean:message key="TawRmRecord.exchange"/>
                        </html:submit>
                        </TD>
                    </TR>
           
 
    </TABLE>
                <br>
                <br>
		<TABLE cellSpacing="1" cellPadding="1" width="500"  align="center"  border="0" class="formTable">
                   <TR class="tr_show">
                   	<TD class="label">
                               <bean:message key="TawRmRecord.hander"/>
                        </TD>
                   	<TD colSpan="3">
                           <html:hidden property="hander"  />
                           <input name="hander" id="hander" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

                        </TD>
                   </TR>
                   <TR class="tr_show">
                   	<TD class="label"><bean:message key="TawRmRecord.dutyman"/></TD>
                   	<TD colSpan="3">
                   	<html:hidden property="dutyman" />
                   	<input name="dutyman" id="dutyman" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
                   </TR>
                   <TR class="tr_show">
                   	<TD class="label"><bean:message key="TawRmRecord.receiver"/></TD>
                   	<TD colSpan="3">
                   	<html:hidden property="receiver" />
                   	<input name="receiver" id="receiver" type="text" style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58" value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
                        </TD>
                   </TR>
                </TABLE>
       
</html:form>
</body>
</html>