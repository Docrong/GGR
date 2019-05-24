<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<head>
 
</head>
<br>
<table cellpadding="0" cellspacing="0" border="0" width="100%" class="formTable">
	<tr>
		<td width="100%" align="center" class="label">
			&nbsp;&nbsp;
			<bean:message key="label.list" />
			&nbsp;&nbsp;
			&nbsp;
		</td>
	</tr>
	<tr>
		<td width="100%" align="right">
			<bean:write name="pagerHeader" scope="request" filter="false" />
			<%!String key;%>
		</td>
	</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="100%"  class="listTable">
	<tr>
		<td>
			<table border="0" width="100%" cellspacing="1" cellpadding="1"
				class="formTable" align=center>
				<tr class="td_label">
					<td class="label">
						<bean:message key="TawRmRecord.id" />
					</td>
					<td class="label">
						<bean:message key="TawRmRecord.flag" />
					</td>
					<td class="label">
						<bean:message key="TawRmRecord.starttime" />
					</td>
					<td class="label">
						<bean:message key="TawRmRecord.endtime" />
					</td>
					<td class="label">
						<bean:message key="TawRmRecord.dutyman" />
					</td>
					<td class="label">
						<bean:message key="TawRmRecord.dutyrecord" />
					</td>
					<td class="label">
						<bean:message key="label.edit" />
					</td>
				</tr>
				<logic:iterate id="tawRmRecord" name="TAWRMRECORDS"
					type="com.boco.eoms.duty.model.TawRmRecord">

					<tr class="tr_show">
						<td>
							<bean:write name="tawRmRecord" property="id" scope="page" />

						</td>
						<td>
							<%
							if (tawRmRecord.getFlag() == 0) {
							%>
							<bean:message key="tawRmRecord.NO" />
							<%
							} else {
							%>
							<bean:message key="tawRmRecord.YES" />
							<%
							}
							%>

						</td>
						<td>
							<bean:write name="tawRmRecord" property="starttime" scope="page" />
						</td>
						<td>
							<bean:write name="tawRmRecord" property="endtime" scope="page" />

						</td>
						<td>
							<bean:write name="tawRmRecord" property="dutyman" scope="page" />

						</td>
						<td>
							<bean:write name="tawRmRecord" property="dutyrecord" scope="page" />

						</td>
						<td align="center">
							<html:link href="viewedit.do" paramId="id"
								paramName="tawRmRecord" paramProperty="id">
								<img
									src="<%=request.getContextPath()%>/duty/images/an_bj.gif"
									border="0">
							</html:link>
							&nbsp;
						</td>
					</tr>

				</logic:iterate>

			</table>
		</td>
	</tr>
</table>
<%@ include file="/common/footer_eoms.jsp"%>
