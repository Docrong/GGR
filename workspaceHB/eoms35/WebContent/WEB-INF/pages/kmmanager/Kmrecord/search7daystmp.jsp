<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<head>
	<title>TawRmRecord.dutyrecord</title>
	<META HTTP-EQUIV="Refresh" content="1200">
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/web/duty/css/table_style.css"
		type="text/css">
</head>
<script language="javascript">
function defaultfocus()
{
    document.all.frm.focusflag.focus();
}
</script>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" class="clssclbar"
	onload="defaultfocus()">
	<table border="0" width="100%" cellspacing="1" cellpadding="1"
		class="listTable" align=center>
		<tr>
			<td class="label">
				<bean:message key="TawRmRecord.dutyrecord" />
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
				<bean:message key="label.view" />
			</td>
		</tr>
		<logic:iterate id="tawRmRecord" name="TAWRMRECORDS"
			type="com.boco.eoms.duty.model.TawRmRecord" scope="request">

			<tr class="tr_show">
				<td>
					<%=tawRmRecord.getDutyrecord()%>

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
				<td align="center">
					<html:link href="view.do" paramId="id" paramName="tawRmRecord"
						paramProperty="id" target="_blank">
						<img src="<%=request.getContextPath()%>/duty/images/an_xs.gif"
							border="0">
					</html:link>
					&nbsp;
				</td>
			</tr>

		</logic:iterate>
	</table>
	<form action="" name="frm">
		<table border="0">
			<tr class="clsscd1" align="right">
				<td>
					<input readonly="readonly" type='text' name="focusflag"
						class="clsscd1" style="height:1;width:1" size="1">
				</td>
			</tr>
		</table>
	</form>
</body>

