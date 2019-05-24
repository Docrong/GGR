<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Enumeration"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>


<%
String str = request.getParameter("folderPath").toString();
%>
 
<form name="tawWorkbenchMemoForm" method="post"
	action="${app}/workbench/memo/tawWorkbenchMemoNodes.do?method=searchList&folderPath=<%=str%>" styleId="tawWorkbenchMemoForm">

	<table class="formTable">
		<caption>
			<fmt:message key="tawWorkbenchMemoDetail.heading" />
		</caption>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.title" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="title" size="30" value="" class="text">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.content" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="content" size="30" value="" class="text">
			</td>
		</tr>

		<tr>
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.creattime" />
			</td>
			<td>
				<input type="text" name="creatBeginTime" size="20"
					onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true" class="text">
			</td>

			<td>
				<input type="text" name="creatEndTime" size="30" readonly="true"
					class="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);">
			</td>
		</tr>

		<tr>
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.level" />
			</td>
			<td width="500" colspan="2">
				<eoms:dict key="memo-dict" isQuery="true" dictId="level"
					beanId="selectXML" selectId="level" />
			</td>
		</tr>

		<tr>
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.sendflag" />
			</td>
			<td width="500" colspan="2">
				<eoms:dict key="memo-dict" dictId="sendflag" beanId="selectXML"
					selectId="sendflag" onchange="changeType(this.value);" />
			</td>
		</tr>
	</table>
	<table class="formTable">
		<tr id="view">
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.reciever" />
			</td>
			<td width="500" colspan="5">
			<input type="text" name="reciever" size="30" value="" class="text">
			</td>
		</tr>
		<tr id="view1">
			<td width="100" class="label">
				<fmt:message key="tawWorkbenchMemoForm.sendtime" />
			</td>
			<td>
				<input type="text" name="sendBeginTime" size="30"
					onclick="popUpCalendar(this, this);" readonly="true" class="text">
			</td>
			<td>
				<input type="text" name="sendEndTime" size="30"
					onclick="popUpCalendar(this, this);" readonly="true" class="text">
			</td>
		</tr>
	</table>
	<br>
	<input type="submit" value=" ${eoms:a2u(" 查询")}" name="B1"
		class="submit">

</form>
<script language="javascript">
function changeType(v){
  document.getElementById("view").style.display = (v==0) ? "none" : "";
  document.getElementById("view1").style.display = (v==0) ? "none" : "";
}
changeType(0);
</script>
<%@ include file="/common/footer_eoms.jsp"%>
