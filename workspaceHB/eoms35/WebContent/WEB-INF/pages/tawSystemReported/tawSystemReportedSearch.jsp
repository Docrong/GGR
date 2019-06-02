<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html:html>
<head>
	<title>search</title>
</head>
<body>
	<center>
		<table cellSpacing="0" cellPadding="0" width="85%" border="0">
			<tr>
				<td class="table_title" align="center">
					<b>${eoms:a2u('上报设置查询')}</b>
				</TD>
			</tr>
		</table>
		<html:form action="/tawSystemReported.do?method=search" method="post" styleId="tawSystemReportedForm">
			<table border="0" width="500" cellspacing="1" cellpadding="1" class="formTable" align=center>
				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.modelName" />
					</td>
					<td >
						<eoms:dict key="dict-reported" dictId="modelId" beanId="selectXML" isQuery="false" selectId="modelId" alt="allowBlank:false" defaultId="${tawSystemReportedForm.modelId}"/>
					</td>
				</tr>
				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.functionName" />
					</td>
					<td >
						<eoms:dict key="dict-reported" dictId="functionId" beanId="selectXML" isQuery="false" selectId="functionId" alt="allowBlank:false" defaultId="${tawSystemReportedForm.functionId}"/>
					</td>
				</tr>
				<tr class="tr_show">
					<td class="label">
						<bean:message key="tawSystemReported.remark" />
					</td>
					<td >
        				<html:textarea property="remark" styleId="remark" styleClass="textarea medium"/>
					</td>
				</tr>
			</table>
			<BR>

			<TABLE cellSpacing="0" cellPadding="0" width="95%" border="0" align=center>
				<TR>
					<TD align="right">
						<html:submit styleClass="button">
							<fmt:message key="button.query"/>
						</html:submit>
						&nbsp;
					</TD>
				</TR>
			</TABLE>
		</html:form>
	</center>
</body>
</html:html>

<%@ include file="/common/footer_eoms.jsp"%>