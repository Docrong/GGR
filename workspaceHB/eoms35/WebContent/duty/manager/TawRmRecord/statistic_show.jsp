<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
 
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr>
		<td width="100%" align="center" valign="middle" class="table_title">
			&nbsp;&nbsp;
			<bean:message key="TawRmSysteminfo.statresult" />
			&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td>
			<table border="0" cellspacing="1" cellpadding="1" class="formTable"
				align=center width="90%">
				<tr class="td_label">
					<td class="label">
						<bean:message key="TawRmSysteminfo.username" />
					</td>
					<td class="label">
						<bean:message key="TawRmSysteminfo.latecount" />
					</td>
					<td class="label">
						<bean:message key="TawRmSysteminfo.earlyleave" />
					</td>
				</tr>
				<%
							Vector StatisticsVector = (Vector) request
							.getAttribute("STATISTICSVECTOR");
				%>
				<%
				for (int i = 0; i < StatisticsVector.size(); i++) {
				%>
				<%
						Vector StatisticVector = (Vector) StatisticsVector.elementAt(i);
						String user_name = "";
						String int1 = "";
						String int2 = "";
						user_name = String.valueOf(StatisticVector.elementAt(0));
						int1 = String.valueOf(StatisticVector.elementAt(1));
						int2 = String.valueOf(StatisticVector.elementAt(2));
				%>
				<tr class="tr_show">
					<td>
						<%=user_name%>
					</td>
					<td>
						<%=int1%>
					</td>
					<td>
						<%=int2%>
					</td>
					<%
					}
					%>
				</tr>
			</table>
		</td>
	</tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>

