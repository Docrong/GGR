<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>

<%@ page language="java" import="com.boco.eoms.common.util.StaticMethod"%>

<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.duty.controller.TawRmRecordForm,com.boco.eoms.duty.model.TawRmDutyfile,com.boco.eoms.common.util.StaticMethod,com.boco.eoms.duty.model.*,com.boco.eoms.duty.model.TawRmAddonsTable,com.boco.eoms.duty.util.StaticDutycheck"%>
<%
	String roomName = request.getAttribute("roomName").toString();
	String workserial = (String) request.getAttribute("workserial");
%>
<%--<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css">--%>

<%
			String exchangeFlag = request.getAttribute("EXCHANGEFLAG")
			.toString();
%>

<html:form method="post" action="/TawRmRecord/exchange">

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
        tawRmRecordForm.action = "savemerge.do";
        tawRmRecordForm.submit();
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
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.receivertime" />
							</TD>
							<TD noWrap colSpan="3">
								<input name="starttime" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="18"
									value="<bean:write name="tawRmRecordForm" property="starttime" scope="request"/>" />
							</TD>
							<html:hidden property="flag" />
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.hander" />
							</TD>
							<TD noWrap colSpan="5">
								<html:hidden property="hander" />
								<input name="hander" id="hander" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="tawRmRecordForm" property="hander" scope="request"/>" />

							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.dutyman" />
							</TD>
							<TD noWrap colSpan="5">
								<html:hidden property="dutyman" />
								<input name="dutyman" id="dutyman" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="tawRmRecordForm" property="dutyman" scope="request"/>" />
						</TR>
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.receiver" />
							</TD>
							<TD noWrap colSpan="5">
								<html:hidden property="receiver" />
								<input name="receiver" id="receiver" type="text"
									style="BACKGROUND-COLOR: lightgrey" readOnly="" size="58"
									value="<bean:write name="tawRmRecordForm" property="receiver" scope="request"/>" />
							</TD>
						</TR>
						<tr class="tr_show">
							<TD noWrap rowSpan="2" align="center" class="label">
								<bean:message key="TawRmRecord.circumstance" />
							</TD>
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.weather" />
							</TD>
							<TD>
								<html:hidden property="weather" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="20"
									value="<bean:write name="tawRmRecordForm" property="weather" scope="request"/>" />
								**
							</TD>
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.temperature" />
							</TD>
							<TD>
								<html:hidden property="temperature" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="5"
									value="<bean:write name="tawRmRecordForm" property="temperature" scope="request"/>" />
								&nbsp;
							</TD>
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.wet" />
							</TD>
							<TD>
								<html:hidden property="wet" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="5"
									value="<bean:write name="tawRmRecordForm" property="wet" scope="request"/>" />
								&nbsp;%
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap class="label">
								<bean:message key="TawRmRecord.clean" />
							</TD>
							<TD noWrap colSpan="1">
								<html:hidden property="clean" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="10"
									value="<bean:write name="tawRmRecordForm" property="clean" scope="request"/>" />
								**
							</TD>
							<html:hidden property="clean1" />
						 
							<TD class="label">
								<bean:message key="TawRmRecord.conditioner" />
							</TD>
							<TD colSpan="3">
								<html:hidden property="conditioner" />
								<input name="tempStr" id="tempStr" type="text"
									style="BACKGROUND-COLOR: lightgrey" class="clstext" readOnly=""
									size="10"
									value="<bean:write name="tawRmRecordForm" property="conditioner" scope="request"/>" />
								**
							</TD>
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
											TawRmDutyfile tawRmDutyfile = (TawRmDutyfile) vecDutyFile
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
								<bean:message key="TawRmRecord.personrecord" />
							</TD>
							<TD colSpan=6>
								&nbsp;
								<SPAN id=spanSubReocrd> <logic:iterate
										id="tawRmRecordSub" name="TAWRMRECORD_DUTYMAN"
										type="com.boco.eoms.duty.model.TawRmRecordSub">
										<html:link href="../TawRmRecordSub/view.do" target="blank"
											paramId="id" paramName="tawRmRecordSub" paramProperty="id">
											<bean:write name="tawRmRecordSub" property="dutyman"
												scope="page" />
										</html:link>
									</logic:iterate>&nbsp; </SPAN>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.workrecord" />
								<BR>
								<BR>
								<INPUT id=btnGetSub
									onclick="window.location.href='coalition.do'" type=button
									class="button"
									value="<bean:message key="TawRmRecord.mergerecord"/>"
									name=btnGetSub>
							</TD>
							<%
									String strDutyrecord = "";
									String strTemprecord = "";
									if (request.getAttribute("COALITIONRECORD") != null) {
										strDutyrecord = StaticMethod.nullObject2String(request
										.getAttribute("COALITIONRECORD"));
										strTemprecord = StaticMethod.nullObject2String(request
										.getAttribute("COALITIONTEMPRECORD"));
									} else {
										//strDutyrecord =  tawRmRecordForm.getDutyrecord();
									}
							%>
							<TD noWrap align=left colSpan=6>
								<TEXTAREA id="dutyrecord" name="dutyrecord" rows=6 cols=80 type="_moz"><%=strDutyrecord%></TEXTAREA>
								<SPAN id=Customvalidator1 style="DISPLAY: none; COLOR: red"
									clientvalidationfunction="CheckDutyRecord"
									evaluationfunction="CustomValidatorEvaluateIsValid"
									display="Dynamic" errormessage="<BR>"
									controltovalidate="Dutyrecord"><BR>
								</SPAN>
							</TD>
						</TR>
						<TR class="tr_show">
							<TD noWrap align="center" class="label">
								<bean:message key="TawRmRecord.notes" />
							</TD>
							<TD colSpan=6>
								<TEXTAREA id=notes name=notes rows=4 cols=80 type="_moz"><bean:write name="tawRmRecordForm" property="notes"  scope="request" /></TEXTAREA>
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
				<%--
<%
Vector vecDefRecord=(Vector)request.getAttribute("vecDefRecord");
if(vecDefRecord!=null && vecDefRecord.size()>0)
{
%>
<TABLE cellSpacing="0" cellPadding="0" width="600"  borderColorLight="#709FD5"  borderColorDark="#ffffff" align="center" bgColor="#f3f3f3" border="1">
<TR>
        <TD align="center" class="clsscd1" colSpan="6">�Զ����¼</TD>
</TR>
<tr>
        <TD align="center" colSpan="6" class="label">
<%
TawRmDefineTable tawRmDefineTable=null;
for(Iterator itr=vecDefRecord.iterator();itr.hasNext();)
{
tawRmDefineTable=(TawRmDefineTable)itr.next();
%>
        ��<a href="recordtable.do?action=VIEW&roomId=<%=tawRmDefineTable.getRoomId()%>&tableDesc=<%=tawRmDefineTable.getTableDesc()%>&workserial=<bean:write name='tawRmRecordForm' property='id'/>&tableId=<%=tawRmDefineTable.getTableId()%>" target="_blank"><%=tawRmDefineTable.getTableDesc()%></a>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%
}
out.println("</td></tr>");
}
%>
--%>
				<TABLE cellSpacing="0" cellPadding="0" width="600"
					borderColorLight="#709FD5" borderColorDark="#ffffff" align="center"
					bgColor="#f3f3f3" border="1">
					<c:choose>
						<c:when test="${requestScope['tawRmRecordForm'].realCount >0 }">
							<TR>
								<TD align="center" class="clsscd1" colSpan="6">
									<bean:message key="TawRmRecord.fineTable" />
								</TD>
							</TR>
							<tr class="tr_show">
								<td width="13%" class="label">
									<bean:message key="TawRmRecord.lookRecord" />
								</td>
								<td align="center" colspan="6">
									<!-- htmlformservlet?sheet_id=9&action=list&id=1,2,3-->
									<logic:iterate id="tawDutySheet" name="FILLEDSHEETLIST"
										type="com.boco.eoms.duty.model.TawDutySheet">
										<%
													String sheetId = String.valueOf(tawDutySheet
													.getSheetId());
													String bocoIds = String.valueOf(tawDutySheet
													.getStrBocoId());
													String path = String.valueOf(tawDutySheet.getUrl());
													System.out.println(path);
										%>

              [ <a
											href='<%=request.getContextPath()%>/duty/manager/ntko/showDetail.jsp?id=<%=sheetId%>&url=<%=path%>&path=<%=path%>'><bean:write
												name="tawDutySheet" property="name" scope="page" />
										</a>]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </logic:iterate>
								</td>
							</tr>
						</c:when>
					</c:choose>

				
					<c:choose>
						<c:when test="${requestScope['tawRmRecordForm'].cycleCount >0 }">
							<TR>
								<TD align="center" class="clsscd1" colSpan="6">
									<bean:message key="TawRmRecord.cycletable" />
								</TD>
							</TR>
								<logic:iterate id="tawRmCycleTable" name="CYCLETABLELIST"
									type="com.boco.eoms.duty.model.TawRmCycleTableSub">
							 <tr>
							
									<td width="10%" class="clsfth">
										<bean:message key="label.di" />
										<%=tawRmCycleTable.getCycle()%>
										<bean:message key="label.cycle" />

									</td>
								 
									<td align="center" colspan="6">
								 
										<!-- htmlformservlet?sheet_id=9&action=list&id=1,2,3-->

										<%
													int sell = tawRmCycleTable.getCycle();
													int iterator = sell - 1;
													String id = String.valueOf(tawRmCycleTable
													.getSheetId());

													String path = String.valueOf(tawRmCycleTable
													.getUrl());
													String url = path;
										%>

										[
										<a
											href='<%=request.getContextPath()%>/duty/manager/TawRmCycleTable/ntko/showDetail.jsp?id=<%=id%>&url=<%=url%>&sell=<%=sell%>&iterator=<%=iterator%>&path=<%=path%>'><bean:write
												name="tawRmCycleTable" property="name" scope="page" /> </a> ]
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
									</td>
								

							</tr>
</logic:iterate>

						</c:when>
					</c:choose>

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
