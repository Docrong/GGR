<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmOperateForm'});
});
</script>

<html:form action="/kmOperates.do?method=save" styleId="kmOperateForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmOperate.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmOperate.nodeId" />
		</td>
		<td class="content">
		<c:if test="${kmOperateForm.nodeType == 'file'}">
		<eoms:id2nameDB id="${kmOperateForm.nodeId}" beanId="kmFileTreeDao"/>	
		</c:if>	
		<c:if test="${kmOperateForm.nodeType == 'content'}">
		<eoms:id2nameDB id="${kmOperateForm.nodeId}" beanId="kmTableThemeDao"/>	
		</c:if>	
		<html:hidden property="nodeId" value="${kmOperateForm.nodeId}" />
		<html:hidden property="nodeType" value="${kmOperateForm.nodeType}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperate.orgId" />
		</td>
		<td class="content">
		<c:if test="${kmOperateForm.orgType == 'user'}">
		<eoms:id2nameDB id="${kmOperateForm.orgId}" beanId="tawSystemUserDao" />
		</c:if>		
		<c:if test="${kmOperateForm.orgType == 'dept'}">
		<eoms:id2nameDB id="${kmOperateForm.orgId}" beanId="tawSystemDeptDao" />
		</c:if>
		<html:hidden property="orgId" value="${kmOperateForm.orgId}" />		
		<html:hidden property="orgType" value="${kmOperateForm.orgType}" />			
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperate.operateType" />
		</td>
		<td class="content">
		<eoms:dict key="dict-kmmanager" dictId="operateType" selectId="operateType" beanId="selectXML" alt="allowBlank:false,vtext:'请选择权限...'" defaultId="${kmOperateForm.operateType}"/>
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmOperateForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>