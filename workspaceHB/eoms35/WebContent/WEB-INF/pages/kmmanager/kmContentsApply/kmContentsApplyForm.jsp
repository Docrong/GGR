<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmContentsApplyForm'});
	
	var config = {
		btnId:'themeName',
		treeDataUrl:'${app}/kmmanager/kmTableThemes.do?method=getNodesRadioTree',
		treeRootId:'1',
		treeRootText:'知识库分类',
		treeChkMode:'single',
		treeChkType:'forums',
		showChkFldId:'themeName',
		saveChkFldId:'applyTheme'
	}
	tree = new xbox(config);	
});
</script>

<html:form action="/kmContentsApplys.do?method=save" styleId="kmContentsApplyForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<font color='red'>*</font>号为必填内容
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContentsApply.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyUser" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmContentsApplyForm.applyUser}" beanId="tawSystemUserDao" />
			<input type="hidden" name="applyUser" value="${kmContentsApplyForm.applyUser}" />
		</td>
	
		<td class="label">
			<fmt:message key="kmContentsApply.applyDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmContentsApplyForm.applyDept}" beanId="tawSystemDeptDao" />
			<input type="hidden" name="applyDept" value="${kmContentsApplyForm.applyDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyTitle" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<html:text property="applyTitle" styleId="applyTitle"
						styleClass="text max"
						alt="allowBlank:false,maxLength:100,vtext:'请输入标题，不超过100个字符...'" 
						value="${kmContentsApplyForm.applyTitle}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyTheme" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<input type="text" id="themeName" name="themeName" class="text" readonly="readonly" 
			value="<eoms:id2nameDB id="${kmContentsApplyForm.applyTheme}" beanId="kmContentsApplyDao" />" 
			alt="allowBlank:false,vtext:'请选择知识分类(字典)...'"/>
			<input type="hidden" id="applyTheme" name="applyTheme" value="${kmContentsApplyForm.applyTheme}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyContent" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<textarea name="applyContent" id="applyContent" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:'',maxLength:500">${kmContentsApplyForm.applyContent}</textarea>	
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.fileName" />
		</td>
		<td class="content" colspan="3">
			<eoms:attachment name="kmContentsApplyForm" property="fileName" scope="request" idField="fileName" appCode="kmmanager" />
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
<html:hidden property="id" value="${kmContentsApplyForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>