<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmFileTreeForm'});
});
</script>

<html:form action="/kmFileTrees.do?method=save" styleId="kmFileTreeForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmFileTree.tree.header"/></div>
	</caption>

	<tr>
		<td  class="label">
			<fmt:message key="kmFileTree.userId" />
		</td>
		<td class="content">
		    <eoms:id2nameDB id="${kmFileTreeForm.userId}" beanId="tawSystemUserDao" />
		    <input type="hidden" name="userId" value="${kmFileTreeForm.userId}" />
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmFileTree.createTime" />
		</td>
		<td class="content">
		    ${kmFileTreeForm.createTime}
		    <input type="hidden" name="createTime" value="${kmFileTreeForm.createTime}" />
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmFileTree.nodeName" />
		</td>
		<td class="content">
			<html:text property="nodeName" styleId="nodeName"
						styleClass="text medium"
						alt="allowBlank:false,maxLength:30,vtext:''" value="${kmFileTreeForm.nodeName}" />
			<html:hidden property="parentNodeId" styleId="parentId"
						styleClass="text medium"
						value="${kmFileTreeForm.parentNodeId}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			是否继承父节点操作
		</td>
		<td class="content">
		<eoms:dict key="dict-kmmanager" dictId="isOrNot" selectId="hasParentOperate" beanId="selectXML" alt="allowBlank:false,vtext:'请选择权限...'" defaultId="${kmFileTreeForm.hasParentOperate}"/>
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
<html:hidden property="nodeId" value="${kmFileTreeForm.nodeId}" />
<html:hidden property="parentNodeId" value="${kmFileTreeForm.parentNodeId}" />
<html:hidden property="leaf" value="${kmFileTreeForm.leaf}" />
<html:hidden property="id" value="${kmFileTreeForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>