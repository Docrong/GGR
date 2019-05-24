<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.duty.model.*,com.boco.eoms.duty.util.StaticDutycheck"%>
<%
	String roomName = request.getAttribute("roomName").toString();
	String roomId = (String) request.getAttribute("roomId");
	String workserial = (String) request.getAttribute("workserial");
	String dutyType = (String) request.getAttribute("dutyType");
%>

<SCRIPT LANGUAGE=javascript>
<!--
function fun_save()
{
    document.all.kmrecordForm.action="${app}/kmmanager/Kmrecord/saveper.do";
    document.all.kmrecordForm.submit;
}
function fun_delete(record_id)
{
    document.all.kmrecordForm.action="${app}/kmmanager/Kmrecord/deleteper.do?record_id="+record_id;
    document.all.kmrecordForm.submit;
}
function fun_update(record_id)
{
    document.all.kmrecordForm.action="${app}/kmmanager/Kmrecord/updateper.do?record_id="+record_id;
    document.all.kmrecordForm.submit;
}
function fun_refresh()
{
    document.all.kmrecordForm.action="${app}/kmmanager/Kmrecord/record.do";
    document.all.kmrecordForm.submit;
}

//-->
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar" onload="kmrecordForm.Dutyrecord.focus();">
	<html:form method="post" action="/Kmrecord/record">
		<html:hidden property="strutsAction" value="1" />
		<html:hidden property="id" />

		<table class="formTable">
			<TR>
				<TD align="center" class="label" colSpan="6">
					<bean:message key="TawRmRecord.dutyrecord" />
				</TD>
			</TR>

			<TR>
				<TD align="center" class="label">
					<bean:message key="TawRmRecord.accessories" />
				</TD>
				<TD align="center" class="label" colSpan="5">
					<IFRAME ID="IFrame1" FRAMEBORDER="0" width="100%" height="100" SCROLLING="YES"
						SRC='${app}/kmmanager/Kmdutyfile/dutyfile.do?WORKSERIAL=<bean:write name="kmrecordForm" property="id"/>'>
					</IFRAME>
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
	String url = "";
	String typename = "";
	int complete_flag = 0;
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
		complete_flag = Integer.parseInt(String.valueOf(vecPerRecord
				.elementAt(6)));
		url = String.valueOf(vecPerRecord.elementAt(7));
		url = "../" + "../" + "../" + url;
		//System.out.println("sss"+url);
		typename = String.valueOf(vecPerRecord.elementAt(8));
%>
			<TR>
				<TD align="center">
					<%=DutyManName%>
				</TD>
				<TD align="center">
					<%=typename%>
				</TD>
				<TD align="center">
					<%=Recordtime%>
				</TD>
				<TD align="center">
					<textarea rows="2" name="Dutyrecord<%=record_id%>" cols="30" type="_moz" ><%=Dutyrecord%></textarea>
				</TD>
				<TD align="center">
					<select id='complete_flag' name='complete_flag<%=record_id%>'>
						<option value='0' <%if (complete_flag==0){%> selected <%}%>>
							<bean:message key="TawRmRecord.compltetNo" />
						</option>
						<option value='1' <%if (complete_flag==1){%> selected <%}%>>
							<bean:message key="TawRmRecord.compltetYes" />
						</option>
					</select>
				</TD>
				<TD align="center">
					<input type="submit" class="btn" name="load" value='<bean:message key="label.editas" />' onclick="javascript:fun_update(<%=record_id%>);">&nbsp;
					<input type="submit" class="btn" name="load" value='<bean:message key="label.delete" />' onclick="javascript:fun_delete(<%=record_id%>);">
				</TD>
			</TR>
<%
		vecPerRecord = null;
	}
	vecPerRecords = null;
%>
		</table>
		
		<br>

		<table class="formTable">
			<TR>
				<TD align="center" colSpan="6">
					<textarea rows="3" name="Dutyrecord" class="textarea max" cols="100"></textarea>
					<input type="hidden" name="typename" value="<%=dutyType%>"><br>
					<input type="submit" class="btn" name="load" value='<bean:message key="label.add" />'  onclick="javascript:fun_save();">&nbsp;&nbsp;
					<input type="submit" class="btn" name="load" value='<bean:message key="label.rush" />' onclick="javascript:fun_refresh();">				
				</TD>
			</TR>
		</table>
	</html:form>
</body>

<%@ include file="/common/footer_eoms.jsp"%>
