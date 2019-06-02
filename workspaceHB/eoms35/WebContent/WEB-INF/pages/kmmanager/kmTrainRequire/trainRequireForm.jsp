<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainRequireForm'});
});
</script>

<eoms:xbox id="tree"
	dataUrl="${app}/kmmanager/trainSpecialtys.do?method=getNodesRadioTree"
	rootId="1" rootText='专业' valueField="trainSpeciality"
	handler="specialtyName" textField="specialtyName" checktype="forums"
	single="true"></eoms:xbox>

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
					<fmt:message key="trainRequire.trainQuestion" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content" colspan="3">
					<html:text property="trainQuestion" styleId="trainQuestion"
						styleClass="text max" maxlength="100%"
						alt="allowBlank:false,vtext:'',maxLength:100"
						value="${trainRequireForm.trainQuestion}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainUser" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRequireForm.trainUser}" beanId="tawSystemUserDao" />
					<input type="hidden" name="trainUser" value="${trainRequireForm.trainUser}" />
				</td>
				<td class="label">
					<fmt:message key="trainRequire.trainDept" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${trainRequireForm.trainDept}" beanId="tawSystemDeptDao" />
					<input type="hidden" name="trainDept" value="${trainRequireForm.trainDept}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainTel" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainTel" styleId="trainTel" styleClass="text"
						alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRequireForm.trainTel}" />
				</td>

				<td class="label">
					<fmt:message key="trainRequire.trainDate" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<input type="text" size="20" class="text" readonly="readonly"
						name="trainDate" id="trainDate"
						onclick="popUpCalendar(this, this);"
						alt="allowBlank:false,vtext:''"
						value="${trainRequireForm.trainDate}" />
				</td>
			</tr>


			<tr>

				<td class="label">
					<fmt:message key="trainRequire.trainContent" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content" colspan="3">
					<textarea name="trainContent" id="trainContent" cols="50"
						class="textarea max" alt="allowBlank:false,vtext:'',maxLength:2000">${trainRequireForm.trainContent}</textarea>
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainVender" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainVender" styleId="trainVender"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRequireForm.trainVender}" />
				</td>
				<td class="label">
					设备类型&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="trainType" styleId="trainType"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:32"
						value="${trainRequireForm.trainType}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainSpeciality" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content" colspan="3">
					<input type="text" id="specialtyName" name="specialtyName"
						class="text" readonly="readonly"
						value='<eoms:id2nameDB id="${trainRequireForm.trainSpeciality}" beanId="trainSpecialtyDao" />'
						alt="allowBlank:false,vtext:'',maxLength:32" />
					<input type="hidden" id=trainSpeciality name="trainSpeciality"
						value="${trainRequireForm.trainSpeciality}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="trainRequire.trainFile" />
				</td>
				<td class="content" colspan="3">
					<eoms:attachment name="trainRequireForm" property="trainFile"
						scope="request" idField="trainFile" appCode="kmmanager" />
				</td>
			</tr>

		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
			</td>
		</tr>
	</table>
	<html:hidden property="isDelete" value="${trainRequireForm.isDelete}" />
	<html:hidden property="trainNo" value="${trainRequireForm.trainNo}" />
	<html:hidden property="id" value="${trainRequireForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>