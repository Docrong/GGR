<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainRecordForm'});
});
</script>

<html:form action="/trainRecords.do?method=save" styleId="trainRecordForm" method="post">

	<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="trainRecord.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainName" />
				</td>
				<td class="content" colspan="3">
					${trainRecordForm.trainName}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainUser" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRecordForm.trainUser}" beanId="tawSystemUserDao" />
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainDept" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRecordForm.trainDept}" beanId="tawSystemDeptDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainUserSex" />
				</td>
				<td class="content">
					${trainRecordForm.trainUserSex}
				</td>			
				<td class="label">
					<fmt:message key="trainRecord.trainAddress" />
				</td>
				<td class="content">
					${trainRecordForm.trainAddress}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainUnit" />
				</td>
				<td class="content">
					${trainRecordForm.trainUnit}
				</td>				
				<td class="label">
					<fmt:message key="trainRecord.trainDocument" />
				</td>
				<td class="content">
					${trainRecordForm.trainDocument}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainContent" />
				</td>
				<td class="content" colspan="3">
					<textarea cols="50" class="textarea max" readonly="readonly">${trainRecordForm.trainContent}</textarea>
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainDate" />
				</td>
				<td class="content">
					${trainRecordForm.trainDate}
				</td>			
				<td class="label">
					<fmt:message key="trainRecord.trainTime" />
				</td>
				<td class="content">
					${trainRecordForm.trainTime}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainVender" />
				</td>
				<td class="content">
					${trainRecordForm.trainVender}
				</td>

				<td class="label">
					<fmt:message key="trainRecord.trainSpeciality" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRecordForm.trainSpeciality}" beanId="trainSpecialtyDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainType" />
				</td>
				<td class="content">
					${trainRecordForm.trainType}
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainCenter" />
				</td>
				<td class="content">
					${trainRecordForm.trainCenter}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainFile" />
				</td>
				<td class="content" colspan="3">
					<eoms:attachment name="trainRecordForm" property="trainFile"
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
				<c:if test="${trainRecordForm.trainUser == sessionScope.sessionform.userid}">

					<input type="button" class="btn"
						value="<fmt:message key="button.edit"/>"
						onclick="javascript:if(confirm('确定修改吗?')){
						var url='${app}/kmmanager/trainRecords.do?method=edit&id=${trainRecordForm.id}';
						location.href=url}" />

					<input type="button" class="btn"
						value="<fmt:message key="button.delete"/>"
						onclick="javascript:if(confirm('确定删除吗?')){
						var url='${app}/kmmanager/trainRecords.do?method=remove&id=${trainRecordForm.id}';
						location.href=url}" />

				</c:if>
			</td>
		</tr>
	</table>

	<html:hidden property="id" value="${trainRecordForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>