<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page language="java" import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.km.duty.webapp.form.KmrecordForm,com.boco.eoms.km.duty.model.Kmdutyfile,com.boco.eoms.common.util.StaticMethod,com.boco.eoms.duty.util.StaticDutycheck,com.boco.eoms.km.duty.model.Kmrecord"%>

<SCRIPT language=javascript>
function CheckDutyRecord(oSrc, args){
  args.IsValid = (window.Form1.Dutyrecord.value.length <= 2000);
}
function CheckNotes(oSrc, args){
  args.IsValid = (window.Form1.Notes.value.length <= 120);
}
function goto_transmit()
{
  document.kmrecordForm.action="<%=request.getContextPath()%>/duty/TawRmRecord/audit.do";
  document.kmrecordForm.submit();
}
</SCRIPT>

<%
	String roomName = request.getAttribute("roomName").toString();
	List cutoversheetlist = (List) request.getAttribute("cutoversheetlist");
	List faultreportsheetlist = (List) request.getAttribute("faultreportsheetlist");
	List faultsheetlist = (List) request.getAttribute("faultsheetlist");
	List monthPlanVOList = (List) request.getAttribute("monthPlanVOList");
	String setStrutsAction = StaticMethod.nullObject2String(request.getAttribute("setStrutsAction"));
	//System.out.println("setStrutsAction2:"+setStrutsAction);
	int statuss = 0;
	if (!setStrutsAction.equals(""))
		statuss = 1;
	//System.out.println("statuss:"+statuss);
	String dutycheck = request.getAttribute("dutycheck").toString();
%>

<html:form method="post" action="/Kmrecord/dodelete">

	<table class="formTable">
		<html:hidden property="id" />

		<TR class="tr_show">
			<TD class="label">
				值班小组名称
			</TD>
			<TD>
				<input type="hidden" id="RoomName" name="RoomName" value="<%=roomName%>" />
				<%=roomName%>
			</TD>
		</TR>

		<TR class="tr_show">
			<TD class="label">
				<bean:message key="TawRmRecord.hander" />
			</TD>
			<TD>
				<html:hidden property="hander" />
				<bean:write name="kmrecordForm" property="hander" scope="request" />
			</TD>
		</TR>

		<TR class="tr_show">
			<TD class="label">
				<bean:message key="TawRmRecord.dutyman" />
			</TD>
			<TD>
				<html:hidden property="dutyman" />
				<bean:write name="kmrecordForm" property="dutyman" scope="request" />
		</TR>

		<TR class="tr_show">
			<TD class="label">
				<bean:message key="TawRmRecord.receiver" />
			</TD>
			<TD>
				<html:hidden property="receiver" />
				<bean:write name="kmrecordForm" property="receiver" scope="request" />
			</TD>
		</TR>

		<TR class="tr_show">
			<TD class="label">
				<bean:message key="TawRmRecord.receivertime" />
			</TD>
			<TD>
				<input type="hidden" id="starttime" name="starttime"
					value="<bean:write name="kmrecordForm" property="starttime" scope="request"/>" />
				<bean:write name="kmrecordForm" property="starttime" scope="request" />
			</TD>
		</TR>

		<TR class="tr_show">
			<TD class="label">
				<bean:message key="TawRmRecord.flag" />
			</TD>
			<TD>
				<html:hidden property="flag" />
				<c:choose>
					<c:when test="${requestScope['kmrecordForm'].flag == 0}">
						<bean:message key="TawRmRecord.compltetNo" />
					</c:when>
					<c:when test="${requestScope['kmrecordForm'].flag == 1}">
						<bean:message key="TawRmRecord.compltetYes" />
					</c:when>
				</c:choose>
			</TD>
		</TR>

		<TR class="tr_show">
			<TD noWrap align="center" class="label">
				<bean:message key="TawRmHangover.prehangquestion" />
			</TD>
			<TD>
				<TEXTAREA id="hangQuestion0" name="hangQuestion0" cols="50"
					class="textarea max" readonly="readonly"><%=String.valueOf(request.getAttribute("strHangQuestion"))%></TEXTAREA>
			</TD>
		</TR>

		<TR class="tr_show">
			<TD noWrap align="center" class="label">
				<bean:message key="TawRmRecord.accessories" />
			</TD>
			<TD colSpan=3>				
				<%
				    Vector vecDutyFile = (Vector) request.getAttribute("vecDutyFile");
					for (int i = 0; i < vecDutyFile.size(); i++) {
						Kmdutyfile tawRmDutyfile = (Kmdutyfile) vecDutyFile.elementAt(i);
						if (StaticMethod.getUploadType().equals("UpE")) {
							out.print("&nbsp;<a href='" + request.getContextPath() + "/kmduty/upload/" + java.net.URLEncoder.encode(tawRmDutyfile.getEncodename()) + "'>"
									+ tawRmDutyfile.getFilename() + "</a>&nbsp;");
						} else {
							out.print("&nbsp;<a href='" + request.getContextPath() + "/kmduty/upload/" + tawRmDutyfile.getEncodename() + "'>" 
							        + tawRmDutyfile.getFilename() + "</a>&nbsp;");
						}
					}
				%>
			</TD>
		</TR>
	</TABLE>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
