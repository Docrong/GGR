<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style>
#tabs {
	width: 90%;
}

#tabs .x-tabs-item-body {
	display: none;
	padding: 10px;
}
</style>


<content tag="heading">
<bean:message key="interfaceMonitoring.Log" />
</content>
<table border="0" cellpadding="0" cellspacing="1">
	<tr>
		<td width="2%" class="clsfth">
		
		</td>
		<td width="98%" class="clsfth">

		</td>
	</tr>
</table>

<table border="0" cellpadding="0" cellspacing="1" class="formTable">
	<TR class="sheet-info-row">
		<TD  width="50%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.Provider'/></TD>
		<TD width="50%" class="form_content"><bean:write name="InterfaceMonitoring" property="provider"/>
					</TD>
	</TR>
	<TR class="sheet-info-row">
		<TD width="50%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.CallingSide'/></TD>
		<TD width="50%" class="form_content"><bean:write name="InterfaceMonitoring" property="callingSide"/>

		</TD>
	</TR>
	<TR class="sheet-info-row">
		<TD width="50%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.InterFaceType'/></TD>
		<TD width="50%" class="form_content"><bean:write name="InterfaceMonitoring" property="interFaceType"/>

		</TD>
	</TR>
	<TR class="sheet-info-row">
		<TD class="label"><bean:message key='interfaceMonitoring.InterfaceType'/></TD>
		<TD colspan="3" class="form_content"><bean:write name="InterfaceMonitoring" property="interFaceMethod"/>

		</TD>
	</TR>
	<TR class="sheet-info-row">
		<TD class="label"><bean:message key='interfaceMonitoring.Method'/></TD>
		<TD colspan="3" class="form_content"><bean:write name="InterfaceMonitoring" property="method"/>

		</TD>
	</TR>
	<TR>
		<TD width="20%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.CallungTime'/></TD>
		<TD colspan="3" class="form_content"><bean:write name="InterfaceMonitoring" property="callingTime"/>

		</TD>
	</TR>
	<TR>
		<TD width="20%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.Success'/></TD>
		<TD colspan="3" class="form_content"><bean:write name="InterfaceMonitoring" property="successName"/>
			
		</TD>
	</TR>
	<TR>
		<TD width="20%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.Keyword'/></TD>
		<TD colspan="3" class="form_content"><bean:write name="InterfaceMonitoring" property="keyword"/>
			
		</TD>
	</TR>
	<TR>
		<TD width="20%" class="label" nowrap="true"><bean:message key='interfaceMonitoring.Text'/></TD>
		<TD colspan="3" class="form_content"><textarea rows="12" cols="50" readonly="true" wrap="hard">${InterfaceMonitoring.text}</textarea>
			
		</TD>
	</TR>



</table>

<%@ include file="/common/footer_eoms.jsp"%>