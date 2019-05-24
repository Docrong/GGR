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
					<b>${eoms:a2u('故障记录')}&nbsp;<bean:message key="label.edit" /></b>
				</TD>
			</tr>
		</table>

		<html:form action="/Faultrecord/update" styleId="BASEINFOVO">

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
						<html:text name="BASEINFOVO" property="startTime" size="30" styleClass="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.networkName" />
					</td>
					<td colspan=3>
			  			 <html:text name="BASEINFOVO" property="networkName" size="20"  title="网元名称" styleClass="text"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.devVendor" />
					</td>
					<td colspan=3>
						<eoms:dict key="dict-plancontent" dictId="devVendor" beanId="selectXML" isQuery="false" selectId="devVendor" alt="allowBlank:false" defaultId="${BASEINFOVO.devVendor}"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.devicetype" />
					</td>
					<td colspan=3>
						<eoms:dict key="dict-plancontent" dictId="devicetype" beanId="selectXML" isQuery="false" selectId="devicetype" alt="allowBlank:false" defaultId="${BASEINFOVO.devicetype}"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultUnitLevel" />
					</td>
					<td colspan=3>
						<eoms:dict key="dict-plancontent" dictId="faultUnitLevel" beanId="selectXML" isQuery="false" selectId="faultUnitLevel" alt="allowBlank:false" defaultId="${BASEINFOVO.faultUnitLevel}"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultLevel" />
					</td>
					<td colspan=3>
						<eoms:dict key="dict-plancontent" dictId="faultLevel" beanId="selectXML" isQuery="false" selectId="faultLevel" alt="allowBlank:false" defaultId="${BASEINFOVO.faultLevel}"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultContent" />
					</td>
					<td colspan=3>
						<html:textarea name="BASEINFOVO" property="faultContent" rows="4" style="width:100%" title="${eoms:a2u('故障内容')}" styleClass="textarea"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.faultResult" />
					</td>
					<td colspan=3>
						<html:textarea name="BASEINFOVO" property="faultResult" rows="4" style="width:100%" title="${eoms:a2u('故障处理情况')}" styleClass="textarea" />
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.declareUser" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="declareUser" size="20" title="${eoms:a2u('故障申报人')}" styleClass="text"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.declareTime" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="declareTime" size="30" styleClass="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.dealUser" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="dealUser" size="20" title="${eoms:a2u('故障处理人')}" styleClass="text"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.dealTime" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="dealTime" size="30" styleClass="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.endTime" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="endTime" size="30" styleClass="text" onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.problemSolveInfo" />
					</td>
					<td colspan=3>
						<eoms:dict key="dict-plancontent" dictId="problemSolveInfo" beanId="selectXML" isQuery="false" selectId="problemSolveInfo" alt="allowBlank:false" defaultId="${BASEINFOVO.problemSolveInfo}" />
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.totalTime" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="totalTime" size="20" title="${eoms:a2u('故障历时')}" styleClass="text"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.operHaltTime" />
					</td>
					<td colspan=3>
						<html:text name="BASEINFOVO" property="operHaltTime" size="20" title="${eoms:a2u('业务中断')}" styleClass="text"/>
					</td>
				</tr>

				<tr class="tr_show">
					<td class="label">
						<bean:message key="Faultrecord.remark" />
					</td>
					<td colspan=3>
						<html:textarea name="BASEINFOVO" property="remark" rows="4" style="width:100%" title="${eoms:a2u('备注')}" styleClass="textarea"/>
					</td>
				</tr>


				<tr class="tr_show">
					<td class="label">
						${eoms:a2u("附件管理")}
					</td>
					<td colspan=3>
    	  				<eoms:attachment name="BASEINFOVO" property="accessories" scope="request" idField="accessories" appCode="9"/>
					</td>
				</tr>
			</table>

			<BR>

			<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
				<TR>
					<TD align="right">
						<html:submit styleClass="button">
							<fmt:message key="button.save"/>
						</html:submit>
						&nbsp;
					</TD>
				</TR>
			</TABLE>
		</html:form>
	</center>
</body>
</html:html>
