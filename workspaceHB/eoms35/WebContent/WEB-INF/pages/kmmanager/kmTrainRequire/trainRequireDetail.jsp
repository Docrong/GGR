<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainRequireForm'});
});
</script>

<html:form action="/trainRequires.do?method=save" styleId="trainRequireForm" method="post">

	<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">
		<font color='red'>*</font>号为必填内容
		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="trainRequire.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainQuestion" />
				</td>
				<td class="content" colspan="3">
					${trainRequireForm.trainQuestion}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainUser" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRequireForm.trainUser}" beanId="tawSystemUserDao" />
				</td>
				<td class="label">
					<fmt:message key="trainRequire.trainDept" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRequireForm.trainDept}" beanId="tawSystemDeptDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainTel" />
				</td>
				<td class="content">
					${trainRequireForm.trainTel}
				</td>

				<td class="label">
					<fmt:message key="trainRequire.trainDate" />
				</td>
				<td class="content">
					${trainRequireForm.trainDate}
				</td>
			</tr>


			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainContent" />
				</td>
				<td class="content" colspan="3">
					<textarea cols="50" class="textarea max" readonly="readonly">${trainRequireForm.trainContent}</textarea>
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainVender" />
				</td>
				<td class="content">
					${trainRequireForm.trainVender}
				</td>
				<td class="label">
					<fmt:message key="trainRequire.trainType" />
				</td>
				<td class="content">
					${trainRequireForm.trainType}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainSpeciality" />
				</td>
				<td class="content" colspan="3">
					<eoms:id2nameDB id="${trainRequireForm.trainSpeciality}" beanId="trainSpecialtyDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainFile" />
				</td>
				<td class="content" colspan="3">
					<eoms:attachment name="trainRequireForm" property="trainFile"
						scope="request" idField="trainFile" appCode="kmmanager"
						viewFlag="Y" />
				</td>
			</tr>
		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<!-- 只有自己能对自己写的需求进行操作 -->
				<c:if
					test="${trainRequireForm.trainUser == sessionScope.sessionform.userid}">

					<input type="button" class="btn"
						value="<fmt:message key="button.edit"/>"
						onclick="javascript:if(confirm('确定修改吗?')){
						var url='${app}/kmmanager/trainRequires.do?method=edit&id=${trainRequireForm.id}';
						location.href=url}" />

					<input type="button" class="btn"
						value="<fmt:message key="button.delete"/>"
						onclick="javascript:if(confirm('确定删除吗?')){
						var url='${app}/kmmanager/trainRequires.do?method=remove&id=${trainRequireForm.id}';
						location.href=url}" />

				</c:if>
			</td>
		</tr>
	</table>

	<html:hidden property="id" value="${trainRequireForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>