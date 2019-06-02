<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page
	import="java.util.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
 <!-- duty/TawRmRecord/duty.do -->
<form name=tawRmRecord>
  <table class="listTable" id="list">
    <caption> </caption>
	<BODY bgcolor="#eeeeee">

		<%
					TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
					.getSession().getAttribute("sessionform");
			if (saveSessionBeanForm.getWorkSerial().equals("0")) {
		%>
		<bean:message key="tawRmRecord.NoDuty" />
	
		<%
		} else {
		%>
		<!--bean:message key="tawRmRecord.please"/>   --> 
		<a href='<%=request.getContextPath()%>/duty/TawRmRecord/record.do?portal=true' target="_parent"><bean:message key="tawRmRecord.write" />
			 </a>
		<bean:message key="tawRmRecord.record" />  

		<%
		}
		%>
	</table>
	 
 </form>