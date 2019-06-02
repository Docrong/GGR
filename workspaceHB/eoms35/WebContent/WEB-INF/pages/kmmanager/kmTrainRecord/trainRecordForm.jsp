<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainRecordForm'});
});
</script>

<eoms:xbox id="tree"
	dataUrl="${app}/kmmanager/trainSpecialtys.do?method=getNodesRadioTree"
	rootId="1" rootText='专业' valueField="trainSpeciality"
	handler="specialityName" textField="specialityName" checktype="forums"
	single="true"></eoms:xbox>

<html:form action="/trainRecords.do?method=save" styleId="trainRecordForm" method="post">

	<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">
		<font color='red'>*</font>号为必填内容
		
		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="trainRecord.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainName" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content" colspan="3">
					<html:text property="trainName" styleId="trainName"
						styleClass="text max" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainName}" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainUser" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRecordForm.trainUser}" beanId="tawSystemUserDao" />
					<input type="hidden" id="trainUser" name="trainUser" value="${trainRecordForm.trainUser}">
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainDept" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRecordForm.trainDept}" beanId="tawSystemDeptDao" />
					<input type="hidden" id="trainDept" name="trainDept" value="${trainRecordForm.trainDept}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainUserSex" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainUserSex" styleId="trainUserSex"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainUserSex}" />
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainAddress" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainAddress" styleId="trainAddress"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainAddress}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainUnit" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainUnit" styleId="trainUnit"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainUnit}" />
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainDocument" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainDocument" styleId="trainDocument"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainDocument}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainContent" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content" colspan="3">
					<textarea name="trainContent" id="trainContent" cols="50"
						class="textarea max" alt="allowBlank:false,vtext:'',maxLength:2000">${trainRecordForm.trainContent}</textarea>
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainDate" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainDate" styleId="trainDate"
						styleClass="text" readonly="true"
						onclick="popUpCalendar(this, this);"
						alt="allowBlank:false,vtext:''"
						value="${trainRecordForm.trainDate}" />
				</td>

				<td class="label">
					<fmt:message key="trainRecord.trainTime" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainTime" styleId="trainTime"
						styleClass="text" alt="allowBlank:false,vtext:'',vtype:'number',maxLength:32"
						value="${trainRecordForm.trainTime}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainVender" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainVender" styleId="trainVender"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainVender}" />
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainSpeciality" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<input type="text" id="specialityName" name="specialityName"
						class="text" readonly="readonly"
						value='<eoms:id2nameDB id="${trainRecordForm.trainSpeciality}" beanId="trainSpecialtyDao" />'
						alt="allowBlank:false,vtext:'',maxLength:32" />
					<input type="hidden" id=trainSpeciality name="trainSpeciality"
						value="${trainRecordForm.trainSpeciality}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainType" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainType" styleId="trainType"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainType}" />
				</td>
				<td class="label">
					<fmt:message key="trainRecord.trainCenter" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainCenter" styleId="trainCenter"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRecordForm.trainCenter}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRecord.trainFile" />
				</td>
				<td class="content" colspan="3">
					<eoms:attachment name="trainRecordForm" property="trainFile"
						scope="request" idField="trainFile" appCode="kmmanager" />
				</td>
			</tr>
			<input type="hidden" name="requireId" value="${requireId }">
		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			</td>
		</tr>
	</table>

	<html:hidden property="id" value="${trainRecordForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>