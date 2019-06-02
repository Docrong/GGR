<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.km.duty.model.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ page language="java"
	import="java.util.*,java.io.*,com.boco.eoms.common.util.StaticMethod"%>

<%
	String setStrutsAction = StaticMethod.nullObject2String(request
			.getAttribute("setStrutsAction"));
	//System.out.println("setStrutsAction:1111" + setStrutsAction);
%>


<html:form method="post" action="/Kmrecord/record">
	<html:hidden property="strutsAction" value="1" />
	<html:hidden property="id" />

	<table class="formTable">
		<TR>
			<TD align="center" class="label" colSpan="5">
				<bean:message key="TawRmRecord.dutyrecord" />
			</TD>
		</TR>

		<TR>
			<TD align="center" class="label" colSpan="5">
				<bean:define id="duty_id" name="kmrecordForm" property="id" type="java.lang.Integer" />
				<% String url1 = request.getContextPath() + "/kmmanager/Kmrecord/viewmain.do?id=" + duty_id + "&setStrutsAction=" + setStrutsAction; %>
				<IFRAME ID=IFrame1 FRAMEBORDER=0 width="100%" height="400" SCROLLING="NO" SRC="<%=url1%>"></IFRAME>
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
				complete_flag = Integer.parseInt(String.valueOf(vecPerRecord.elementAt(6)));
				url = String.valueOf(vecPerRecord.elementAt(7));
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
				<%
					if (url == null || "".equals(url.trim())
								|| "null".equals(url.trim())) {
				%>
				<textarea rows="3" cols="30" readonly><%=Dutyrecord%></textarea>
				<%
					} else {
				%>
				<textarea rows="3" cols="30" readonly><%=Dutyrecord%></textarea>
				<%
					}
				%>
			</TD>
			<TD align="center">
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

	<TABLE align="center">
		<TR>
			<TD align="center" colSpan="6">
				<input type=button Class="button" name="load" value='<bean:message key="label.print"/>' onclick="javascript:window.print();"> &nbsp;&nbsp;&nbsp;
				<%--
				<input type=button Class="button" name="load" value='<bean:message key="label.back"/>' onclick="javascript:window.history.back();">
				--%>
			</TD>
		</TR>
	</TABLE>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
