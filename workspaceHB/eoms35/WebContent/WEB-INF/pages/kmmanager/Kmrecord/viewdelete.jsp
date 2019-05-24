<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.duty.model.*"%>

<html>
	<head>
		<title></title>
	</head>
	<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
		<html:form method="post" action="/TawRmRecord/dodelete">
			<html:hidden property="strutsAction" value="1" />
			<html:hidden property="id" />
			<table width="100%" height="100%">
				<tr>
					<td align="center" valign="middle">
						<TABLE cellSpacing="0" cellPadding="0" width="95%"
							borderColorLight="#709FD5" borderColorDark="#ffffff"
							align="center" bgColor="#f3f3f3" border="1" class="listTable">
							<TR>
								<TD align="center" class="clsscd1" colSpan="6">
									<bean:message key="TawRmRecord.fineRecord" />
								</TD>
							</TR>
							<TR>
								<TD colSpan="6" class="label">
									<IFRAME ID=IFrame1 FRAMEBORDER=0 width="100%" height=500
										SCROLLING=YES
										SRC='viewmain.do?id=<bean:write name="tawRmRecordForm" property="id"/>'>
									</IFRAME>
								</TD>
							</TR>


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
								<TD align="center" class="label" colSpan="6">
									<bean:message key="TawRmRecord.cycletable" />
								</TD>
							</TR>
								<logic:iterate id="tawRmCycleTable" name="CYCLETABLELIST"
									type="com.boco.eoms.duty.model.TawRmCycleTableSub">
							 <tr>
							
									<td width="10%" class="label">
										<bean:message key="label.di" />
										<%=tawRmCycleTable.getCycle()%>
										<bean:message key="label.cycle" />

									</td>
								 
									<td align="center" colspan="6" class="label">
								 
										<!-- htmlformservlet?sheet_id=9&action=list&id=1,2,3-->

										<%
													int sell = tawRmCycleTable.getCycle();
													int iterator = sell - 1;
													String id = String.valueOf(tawRmCycleTable
													.getSheetId());

													String path  = String.valueOf(tawRmCycleTable
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
								<TD align="center" class="label" colSpan="6">
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
								<TD align="center" class="label">
								<bean:message key="TawRmRecord.operation" />
									 
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
												if (url == null || "".equals(url.trim())
												|| "null".equals(url.trim())) {
									%>
									<textarea rows="2" cols="30" readonly type="_moz"> <%=Dutyrecord%> </textarea>
									<%
									} else {
									%>
									<textarea rows="2" cols="30" readonly type="_moz"> <%=Dutyrecord%> </textarea>
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
								<TD align="center" class="label">
								 
								</TD>
							</TR>
							<%
									vecPerRecord = null;
									}
									vecPerRecords = null;
							%>
						</TABLE>
						<table border="0" width="95%" cellspacing="1" cellpadding="1"
							align=center>
							<TR>
								<TD align="center" colSpan="6">
									<input type="submit" name=<bean:message key="label.remove"/>
										class="button" value=<bean:message key="label.remove"/>>
									<input type="button" name=<bean:message key="label.back"/>
										class="button" value=<bean:message key="label.back"/>
										onclick="javascript:history.back();">
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
			</table>

		</html:form>

	</body>
</html>

