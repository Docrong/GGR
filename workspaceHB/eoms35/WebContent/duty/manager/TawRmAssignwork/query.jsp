<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>

<%@ page import="java.util.*,java.lang.*,org.apache.struts.util.*"%>
<%
String roomId = request.getAttribute("roomId").toString();
%>
<head>
	<title><bean:message key="entry.TawRmExchange.workQuery" />
	</title>
	 
</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
	<html:form method="post" action="/TawRmAssignwork/query_result">
		<html:hidden property="strutsAction" />
		<input type="hidden" name="roomId" value=<%=roomId%> />
		<br>
		<table cellpadding="0" cellspacing="0" border="0" width="400"
			align="center">
			<tr>
				<td align="center" class="table_title">
					&nbsp;
					 
				</td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0" border="0" width="400"
			align="center">
			<tr>
				<td>
					<table border="0" width="100%" cellspacing="1" cellpadding="1"
						class="formTable" align=center>

						<tr class="tr_show">
							<td class="label">
								<bean:message key="TawRmAssignwork.begin_date" />
							</td>
							<td>
								<eoms:SelectDate name="time_from" formName="tawRmAssignworkForm" />
							</td>
						</tr>
						<tr class="tr_show">
							<td class="label">
								<bean:message key="TawRmAssignwork.end_date" />
							</td>
							<td>
								<eoms:SelectDate name="time_to" formName="tawRmAssignworkForm" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="400"
						align="left">
						<tr align="left" height="32">
							<td>
								<input type=submit Class="button"
									value='<bean:message key="label.find" />'>
								<%--
								
      <html:submit styleClass="clsbtn2">
      <bean:message key="label.find"/>
      </html:submit>

  --%>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
</body>

