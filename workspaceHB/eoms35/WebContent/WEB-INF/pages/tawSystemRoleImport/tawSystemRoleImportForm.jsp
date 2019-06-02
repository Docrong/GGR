<%@ page import="com.boco.eoms.commons.system.role.util.RoleConstants" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'tawSystemRoleImportForm'});
})
</script>

<fmt:bundle basename="config.ApplicationResources-role">
<html:form action="systemRoleImport.do?method=save" method="post"
	styleId="tawSystemRoleImportForm">
	<div id="roleInfo">
		<fieldset>
			<legend>
				<fmt:message key="tawSystemRoleImportDetail.roleInfo"/>
			</legend>
			<html:hidden property="id" />
			<ul>
			<li>
			<fmt:message key="tawSystemRoleImportForm.versionnote"/>
			<br/>
			</li>
			<li>
			<fmt:message key="tawSystemRoleImportForm.version"/>
			<html:text property="version" styleId="version"
				styleClass="text medium" alt="allowBlank:false,vtext:''" />
			</li>
			<li>
			<fmt:message key="tawSystemRoleImportForm.memo"/>
			<html:textarea property="memo" styleId="memo" styleClass="text medium" />
			</li>
			</ul>
		</fieldset>
	</div>
	<div id="fileImport">
		<fieldset>
			<legend>
				<fmt:message key="tawSystemRoleImportDetail.fileImport"/>
			</legend>
			<ul>
			<c:if test="${noAccessories != null}">
			<li class="error">
				<img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
				${noAccessories}
			</li>
			</c:if>
			</ul>
			<eoms:attachment idList="" idField="accessories" appCode="<%=RoleConstants.ROLE_ACCESSORIES_APP_ID %>"/>
		</fieldset>
	</div>
	<br/>
	<div id="btn">
		<fmt:bundle basename="config.ApplicationResources">
			<html:submit styleClass="button" property="method.save"
				onclick="bCancel=false">
				<fmt:message key="button.save" />
			</html:submit>
		</fmt:bundle>
	</div>
</html:form>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>
