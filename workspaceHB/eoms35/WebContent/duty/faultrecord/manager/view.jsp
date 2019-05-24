<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>

<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.taglib.AttachmentTag"%>
<%@ page import="java.lang.String"%>

<html:html>
<head>
	<title>edit</title>
</head>

<body>
	<center>
		<table cellSpacing="0" cellPadding="0" width="85%" border="0">
			<tr>
				<td class="table_title" align="center">
					<b>${eoms:a2u('故障记录')}&nbsp;<bean:message key="label.view" /></b>
				</TD>
			</tr>
		</table>
			<table border="0" width="95%" cellspacing="1">
				<tr>
					<td align="right">
					</td>
				</tr>
			</table>

			<table border="0" width="500" cellspacing="1" cellpadding="1" class="formTable" align=center>
				<tr class="tr_show">
					<td width="15%" class="label">
						${eoms:a2u('创建人')}
					</td>
					<td width="400">
			  			<bean:write name="BASEINFOVO" property="userName" scope="request"/>
			  			<html:hidden name="BASEINFOVO" property="userId"/>
					</td>
					<td width="20%" class="label">
						${eoms:a2u('创建部门')}
					</td>
					<td width="400">
			  			<bean:write name="BASEINFOVO" property="deptName" scope="request"/>
			  			<html:hidden name="BASEINFOVO" property="deptId"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						${eoms:a2u('创建时间')}
					</td>
					<td colspan=3>
			  			<bean:write name="BASEINFOVO" property="insertTime" scope="request"/>
			  			<html:hidden name="BASEINFOVO" property="insertTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.startTime" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="startTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.networkName" />
					</td>
					<td colspan=3>
			  			 <bean:write name="BASEINFOVO" property="networkName"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.devVendor" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="devVendorName"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.devicetype" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="devicetypeName"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultUnitLevel" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="faultUnitLevelName"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultLevel" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="faultLevelName"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultContent" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="faultContent"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultResult" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="faultResult"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.declareUser" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="declareUser"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.declareTime" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="declareTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.dealUser" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="dealUser"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.dealTime" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="dealTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.endTime" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="endTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.problemSolveInfo" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="problemSolveInfoName"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.totalTime" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="totalTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.operHaltTime" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="operHaltTime"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.remark" />
					</td>
					<td colspan=3>
						<bean:write name="BASEINFOVO" property="remark"/>
					</td>
				</tr>


				<tr class="tr_show">
					<td class="label">
						${eoms:a2u("附件管理")}
					</td>
					<td colspan=3>
    	  				<eoms:attachment name="BASEINFOVO" property="accessories" scope="request" idField="accessories" appCode="9"  viewFlag="Y"/>
					</td>
				</tr>
			</table>

			<BR>

			<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
				<TR>
					<TD align="right">
      					<input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="button"/>&nbsp;
						&nbsp;
					</TD>
				</TR>
			</TABLE>
	</center>
</body>
</html:html>
