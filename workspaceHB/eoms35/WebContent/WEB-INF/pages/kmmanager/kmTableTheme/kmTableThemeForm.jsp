<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmTableThemeForm'});
});
</script>

<html:form action="/kmTableThemes.do?method=save" styleId="kmTableThemeForm" method="post">

	<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">
		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="kmTableTheme.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="kmTableTheme.themeName" />
				</td>
				<td class="content">
					<html:text property="themeName" styleId="themeName"
						styleClass="text medium" alt="allowBlank:false,vtext:''"
						value="${kmTableThemeForm.themeName}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmTableTheme.isOpen" />
				</td>
				<td class="content">
					<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false"
						defaultId="${kmTableThemeForm.isOpen}" selectId="isOpen"
						beanId="selectXML" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmTableTheme.orderBy" />
				</td>
				<td class="content">
					<html:text property="orderBy" styleId="orderBy"
						styleClass="text medium"
						alt="vtype:'number',allowBlank:false,vtext:''"
						value="${kmTableThemeForm.orderBy}" />
				</td>
			</tr>
				
			<c:if test="${kmTableThemeForm.parentNodeId != 1}">
			<tr>
				<td class="label">
					 是否继承父节点操作
				</td>
				<td class="content">
					<eoms:dict key="dict-kmmanager" dictId="isOrNot"
						selectId="hasParentOperate" beanId="selectXML"
						alt="allowBlank:false,vtext:'请选择权限...'"
						defaultId="${kmTableThemeForm.hasParentOperate}" />
				</td>
			</tr>
			</c:if>
			<c:if test="${kmTableThemeForm.parentNodeId == 1}">
			    <html:hidden property="hasParentOperate" value="0" />
			</c:if>
		</table>

<br>

		<table>
			<tr>
				<td>
					<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
					<c:if test="${not empty kmTableThemeForm.id}">
						<input type="button" class="btn" 
						    value="<fmt:message key="button.delete"/>" 
						    onclick="javascript:if(confirm('<fmt:message key="kmMessage.delMessage"/>')){var url='${app}/kmmanager/kmTableThemes.do?method=remove&nodeId=${kmTableThemeForm.nodeId}';location.href=url}" />
					</c:if>
				</td>
			</tr>
		</table>

	</fmt:bundle>
			
	<html:hidden property="isDeleted" value="0" />
	<html:hidden property="id" value="${kmTableThemeForm.id}" />
	<html:hidden property="nodeId" value="${kmTableThemeForm.nodeId}" />
	<html:hidden property="parentNodeId"value="${kmTableThemeForm.parentNodeId}" />
	<html:hidden property="leaf" value="${kmTableThemeForm.leaf}" />
	<html:hidden property="isUsed" value="${kmTableThemeForm.isUsed}" />
	<html:hidden property="tableId" value="${kmTableThemeForm.tableId}" />

<br>
    <font color="red">
      <c:if test="${kmTableThemeForm.isUsed == 1}">
            注意：该知识分类已经被名称为“<eoms:id2nameDB id="${kmTableThemeForm.tableId}" beanId="kmTableGeneralDao" />”的知识模型使用！
      </c:if>            
      <c:if test="${kmTableThemeForm.isUsed == null || kmTableThemeForm.isUsed == '' || kmTableThemeForm.isUsed == 0}">
            注意：该知识分类未和知识模型绑定！ 
	  </c:if>
	</font>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>