<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java"
	import="java.util.*,java.lang.*,org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%
	String roomId = (String)request.getAttribute("roomId");
	String timeForm = String.valueOf(request.getAttribute("TIMEFROM")).trim();
	String timeTo = String.valueOf(request.getAttribute("TIMETO")).trim();
 %>
<head>
	<title><bean:message key="label.theSecond" /></title>
<script language="javascript">
function onExport()
{     
  window.location.href="export.do?roomId=<%=roomId%>&timeForm=<%=timeForm%>&timeTo=<%=timeTo%>";
}
</script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar">
	<html:form method="post" action="/Kmassignwork/query_result">
		<html:hidden property="strutsAction" />

		<table cellpadding="0" cellspacing="0" border="0" width="800"
			align="center">
			<tr>
				<td width="100%" align="center" valign="middle" class="table_title">

					&nbsp;
					<bean:message key="TawRmAssignwork.tablename" />

				</td>
			</tr>
			<tr>
				<td width="100%"></td>
			</tr>
			<tr>
				<td>

				</td>
			</tr>



			<tr>
				<td>
					<table height="30" cellpadding="0" cellspacing="0" border="0"
						width="100%" class="formTable">
						<tr align="center" valign="middle">
							<td class="table_title">
								<%=request.getAttribute("ROOMNAME")%>
								&nbsp;&nbsp;&nbsp;
								<bean:message key="TawRmAssignwork.apparatusroom" />
								&nbsp;
								<%=String.valueOf(request.getAttribute("TIMEFROM"))
								.trim()%>
								&nbsp;
								<bean:message key="TawRmAssignwork.to" />
								&nbsp;
								<%=String
										.valueOf(request.getAttribute("TIMETO"))
										.trim()%>
								&nbsp;
								<bean:message key="TawRmAssignwork.assign" />
							</td>
						</tr>
					</table>
				</td>
			</tr>


			<%
					Vector vectorQueryResult = (Vector) request
					.getAttribute("QUERYRESULT");
					Vector getDutydate = (Vector) vectorQueryResult.elementAt(0);

					Vector getStarttime = (Vector) vectorQueryResult.elementAt(1);
					Vector getEndtime = (Vector) vectorQueryResult.elementAt(2);
					Vector getDutymaster = (Vector) vectorQueryResult.elementAt(3);
					Vector getDutyman = (Vector) vectorQueryResult.elementAt(4);
			%>
			<tr>
				<td>
					<table height="30" cellpadding="1" cellspacing="1" border="0"
						width="100%" class="listTable">
						<%
						if (getDutydate.size() == 0) {
						%>
						<tr class="tr_show">
							<td class="label" colspan=10>
								<bean:message key="TawRmAssignwork.alertnoassign" />
							</td>
						</tr>
						<%
						} else {
						%>
						<tr align="center" valign="middle" class="td_label">
							<td class="label">
								<bean:message key="TawRmAssignwork.dutydate" />
							</td>
							<td class="label">
								<bean:message key="label.week" />

							</td>
							<td class="label">
								<bean:message key="TawRmAssignwork.begin_date" />
							</td>
							<td class="label">
								<bean:message key="TawRmAssignwork.end_date" />
							</td>
							<td class="label">
								<bean:message key="TawRmAssignwork.dutymaster" />
							</td>
							<td class="label">
								<bean:message key="TawRmAssignSub.dutyman" />
							</td>
						</tr>
						<%
									Vector td_num = new Vector();
									String temp_Dutydate = "";
									int tdNum = 0;
									for (int i = 0; i < getDutydate.size(); i++) {
								td_num.add("0");
									}
									for (int i = 0; i < getDutydate.size();) {
								temp_Dutydate = String
										.valueOf(getDutydate.elementAt(i));
								tdNum = 0;
								for (int j = i; j < getDutydate.size(); j++) {
									if (temp_Dutydate.equals(String.valueOf(getDutydate
									.elementAt(j)))) {
										tdNum++;
									}
								}
								td_num.setElementAt(String.valueOf(tdNum), i);
								i += tdNum;
									}
						%>
						<%
									GregorianCalendar cal = new GregorianCalendar();
									String week = (String) request.getAttribute("week");
									System.out.println(week);

									String[] weekName = week.split(",");
									//String weekName[] = {"label.Sunday","label.Monday","label.Tuesday","label.Wednesday","label.Thursday","label.Friday","label.Saturday"};
									for (int i = 0; i < getDutydate.size(); i++) {
						%>
						<tr align="center" valign="middle" class="tr_show">
							<%
							if (!String.valueOf(td_num.elementAt(i)).equals("0")) {
							%>
							<td rowspan="<%=String.valueOf(td_num.elementAt(i))%>">
								<%=String.valueOf(getDutydate.elementAt(i))%>
							</td>
							<%
										Vector date_vector = StaticMethod.getVector(String
										.valueOf(getDutydate.elementAt(i)).trim(),
										"-");
										int year = Integer.parseInt(String.valueOf(
										date_vector.elementAt(0)).trim());
										int month = Integer.parseInt(String.valueOf(
										date_vector.elementAt(1)).trim());
										int day = Integer.parseInt(String.valueOf(
										date_vector.elementAt(2)).trim());
										java.util.Date date = new java.util.Date(
										year - 1900, month - 1, day - 0);

										int numOfWeek = date.getDay();
							%>
							<td rowspan="<%=String.valueOf(td_num.elementAt(i))%>"
								<%if (numOfWeek==0 || numOfWeek==6){%> <%}else{%> <%}%>>
								<%=weekName[numOfWeek]%>
							</td>
							<%
							}
							%>
							<td>
								<%=String.valueOf(getStarttime.elementAt(i))
										.substring(11, 18)%>
							</td>
							<td>
								<%=String.valueOf(getEndtime.elementAt(i))
										.substring(11, 18)%>
							</td>
							<td>
								<%=String.valueOf(getDutymaster.elementAt(i))%>
							</td>
							<td>
								<%=String.valueOf(getDutyman.elementAt(i))%>
							</td>
						</tr>
						<%
								}
								}
						%>
					</table>
				</td>
			</tr>

<!-- 
			<tr>
				<td colspan=10 align='center' height="40">
					<input type='button' Class="button"
						value='<bean:message key ="label.print" />'
						onclick='javascript:window.print()'>
					<html:button onclick="javascript:onExport();" styleClass="button"
						property="button">
						<bean:message key="TawRmRecord.export" />
					</html:button>
					 -->
				<td>
			</tr>
		</table>


	</html:form>
</body>
