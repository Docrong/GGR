<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ page language="java" import="com.boco.eoms.common.util.StaticMethod"%>

<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.km.duty.webapp.form.KmrecordForm,
	com.boco.eoms.common.util.StaticMethod,com.boco.eoms.km.duty.model.*,com.boco.eoms.duty.util.StaticDutycheck"%>
<%
	String roomName = request.getAttribute("roomName").toString();
	String workserial = (String) request.getAttribute("workserial");
%>

<%
			String exchangeFlag = request.getAttribute("EXCHANGEFLAG")
			.toString();
%>

<html:form method="post" action="/Kmrecord/exchange">

	<HEAD>
		<TITLE></TITLE>
		<SCRIPT language=javascript>
function CheckDutyRecord(oSrc, args){
	args.IsValid = (window.Form1.Dutyrecord.value.length <= 2000);
}
function CheckNotes(oSrc, args){
	args.IsValid = (window.Form1.Notes.value.length <= 120);
}
function save_merge(){
        kmrecordForm.action = "savemerge.do";
       kmrecordForm.submit();
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
			<center>
				<br>
				<TABLE cellSpacing="1" cellPadding="1" width="600" align="center"
					border="0" class="formTable">
					<TBODY>
						<html:hidden property="id" />
						<html:hidden property="roomId" />
						<TR class="tr_show">
							<TD noWrap rowSpan="4" align="center" class="label">
								&nbsp;
								<bean:message key="TawRmRecord.baseinfo" />
							</TD>
							<TD noWrap class="label">
								<bean:message key="TawRmSysteminfo.roomName" />
							</TD>
							<TD noWrap colSpan="1">
								<INPUT id="RoomName" style="BACKGROUND-COLOR: lightgrey"
									readOnly maxLength=150 value="<%=roomName%>" name="RoomName">
									
							<html:hidden property="flag" />
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.dutyman" />
							</TD>
							<TD noWrap colSpan="5">
								<html:hidden property="dutyman" />
								<input name="dutyman" id="dutyman" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="kmrecordForm" property="dutyman" scope="request"/>" />
						</TR>
						
						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.accessories" />
							</TD>
							<%
									Vector vecDutyFile = (Vector) request
									.getAttribute("vecDutyFile");
							%>
							<TD noWrap colSpan=6>
								&nbsp;
								<%
											for (int i = 0; i < vecDutyFile.size(); i++) {
											Kmdutyfile tawRmDutyfile = (Kmdutyfile) vecDutyFile
											.elementAt(i);
											if (StaticMethod.getUploadType().equals("UpE")) {
										out.print("<a href='../upload/"
												+ java.net.URLEncoder.encode(tawRmDutyfile
												.getEncodename()) + "'>"
												+ tawRmDutyfile.getFilename() + "</a>");
											} else {
										out.print("<a href='../upload/"
												+ tawRmDutyfile.getEncodename() + "'>"
												+ tawRmDutyfile.getFilename() + "</a>");
											}
											out.print("&nbsp;");
										}
								%>
							</TD>
						</TR>
					<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.notes" />
							</TD>
							<TD colSpan=6>
								<TEXTAREA id=notes name=notes rows=4 cols=80 type="_moz"><bean:write name="kmrecordForm" property="notes"  scope="request" /></TEXTAREA>
								<SPAN id=Customvalidator2 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckNotes"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>" controltovalidate="Notes"><BR>
								</SPAN>
							</TD>
						</TR>
					</TBODY>
				</TABLE>
				<TABLE cellSpacing="0" cellPadding="0" width="600" align="center"
					border="0">
					<TR>
						<%
						if (exchangeFlag.equals("1")) {
						%>
						<TD align=right height="32" colSpan=7>
							<input type="button" name="button" class="clsbtn2"
								value=<bean:message key="label.save"/> Onclick="save_merge();">
						</TD>
						<%
						} else {
						%>
						<TD align=right height="32" colSpan=7>
							<html:submit styleClass="button">
								<bean:message key="label.nextp" />
							</html:submit>
						</TD>
						<%
						}
						%>
					</TR>
				</TABLE>
				<SCRIPT language=javascript>
<!--
	var Page_Validators =  new Array(document.all["Customvalidator1"], document.all["Customvalidator2"]);
		// -->
</SCRIPT>

				<SCRIPT language=javascript>
function ValidatorOnSubmit() {
    if (Page_ValidationActive) {
        ValidatorCommonOnSubmit();
    }
}
// -->
</SCRIPT>
				
				<TABLE cellSpacing="0" cellPadding="0" width="600"
					borderColorLight="#709FD5" borderColorDark="#ffffff" align="center"
					bgColor="#f3f3f3" border="1">

					<TR>
						<TD align="center" class="clsscd1" colSpan="6">
							<bean:message key="TawRmRecord.dutyrecord" />
						</TD>
					</TR>
					<TR>

						<TD align="center" class="label">
							<bean:message key="TawRmRecord.recordName" />
						</TD>
						<TD align="center" class="label">
							<bean:message key="TawRmRecord.recordType" />
						</TD>
						<TD align="center" class="label">
							<bean:message key="TawRmRecord.recordTime" />
						</TD>
						<TD align="center" class="label">
							<bean:message key="TawRmRecord.recordContant" />
						</TD>
						<TD align="center" class="label">
							<bean:message key="TawRmRecord.isCompate" />
						</TD>
					</TR>
					<%
							Vector vecPerRecords = new Vector();
							Vector vecPerRecord = null;
							String record_id = "";
							String Workserial = "";
							String Recordtime = "";
							String DutyMan = "";
							String DutyManName = "";
							String Dutyrecord = "";
							int complete_flag = 0;
							String url = "";
							String typename = "";

							vecPerRecords = (Vector) request.getAttribute("vecPerRecords");
							for (int i = 0; i < vecPerRecords.size(); i++) {
								vecPerRecord = new Vector();
								vecPerRecord = (Vector) vecPerRecords.elementAt(i);
								record_id = String.valueOf(vecPerRecord.elementAt(0));
								Workserial = String.valueOf(vecPerRecord.elementAt(1));
								Recordtime = String.valueOf(vecPerRecord.elementAt(2));
								DutyMan = String.valueOf(vecPerRecord.elementAt(3));
								DutyManName = String.valueOf(vecPerRecord.elementAt(4));
								Dutyrecord = String.valueOf(vecPerRecord.elementAt(5));
								complete_flag = Integer.parseInt(String
								.valueOf(vecPerRecord.elementAt(6)));
								url = String.valueOf(vecPerRecord.elementAt(7));
								typename = String.valueOf(vecPerRecord.elementAt(8));
					%>
					<TR>
						<TD align="center" class="label">
							<%=DutyManName%>
							&nbsp;
						</TD>
						<TD align="center" class="label">
							<%=typename%>
						</TD>
						<TD align="center" class="label">
							<%=Recordtime%>
							&nbsp;
						</TD>
						<TD align="center" class="label">
						
							<%
							if (url == null || "".equals(url.trim())) {
							%>
							<textarea rows="2" cols="30" readonly ><%=Dutyrecord%></textarea>
							<%
							} else {
							%>
							<textarea rows="2" cols="30" readonly ><%=Dutyrecord%></textarea>
							<%
							}
							%>
						</TD>
						<TD align="center" class="label">
							<%
							if (complete_flag == 0) {
							%>
							<bean:message key="TawRmRecord.compltetNo" />
							<%
							} else {
							%>
							<bean:message key="TawRmRecord.compltetYes" />
							<%
							}
							%>
						</TD>
					</TR>
					<%
							vecPerRecord = null;
							}
							vecPerRecords = null;
					%>
				</TABLE>
</html:form>
</CENTER>
</BODY>
