<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawContractForm'});
});
</script>

<html:form action="/tawContracts.do?method=save"
	styleId="tawContractForm" method="post">

	<fmt:bundle basename="config/applicationResource-tawcontract">

		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="tawContract.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.contractName" />
				</td>
				<td class="content">
					<html:textarea property="contractName" styleId="contractName"
						styleClass="textarea max" rows="1" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:128" style="{height:36px}"
						value="${tawContractForm.contractName}" />
				</td>

				<td class="label">
					<fmt:message key="tawContract.name_A" />
				</td>
				<td class="content">
					<html:textarea property="name_A" styleId="name_A"
						styleClass="textarea max" rows="1" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:128" value="${tawContractForm.name_A}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.name_B" />
				</td>
				<td class="content">
					<html:textarea property="name_B" styleId="name_B"
						styleClass="textarea max" rows="1" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:128" value="${tawContractForm.name_B}" />
				</td>

				<td class="label">
					<fmt:message key="tawContract.maintenanceRange" />
				</td>
				<td class="content">
					<html:textarea property="maintenanceRange"
						styleId="maintenanceRange" styleClass="textarea max" rows="1"
						cols="40" alt="allowBlank:false,vtext:'',maxLength:128" style="{height:36px}"
						value="${tawContractForm.maintenanceRange}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.right_A" />
				</td>
				<td class="content">
					<html:textarea property="right_A" styleId="right_A"
						styleClass="textarea max" rows="2" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:255" value="${tawContractForm.right_A}" />
				</td>

				<td class="label">
					<fmt:message key="tawContract.right_B" />
				</td>
				<td class="content">
					<html:textarea property="right_B" styleId="right_B"
						styleClass="textarea max" rows="2" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:255" value="${tawContractForm.right_B}" />
				</td>
			</tr>

			<tr>

				<td class="label">
					<fmt:message key="tawContract.interface_A" />
				</td>
				<td class="content">
					<html:textarea property="interface_A" styleId="interface_A"
						styleClass="textarea max" rows="1" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:128" style="{height:36px}"
						value="${tawContractForm.interface_A}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.interface_B" />
				</td>
				<td class="content">
					<html:textarea property="interface_B" styleId="interface_B"
						styleClass="textarea max" rows="1" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:128" style="{height:36px}"
						value="${tawContractForm.interface_B}" />
				</td>
			</tr>


			<tr>

				<td class="label">
					<fmt:message key="tawContract.qualityCheck" />
				</td>
				<td class="content">
					<html:textarea property="qualityCheck" styleId="qualityCheck"
						styleClass="textarea max" rows="1" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:255" style="{height:36px}"
						value="${tawContractForm.qualityCheck}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.qualityChangeDeal" />
				</td>
				<td class="content">
					<html:textarea property="qualityChangeDeal"
						styleId="qualityChangeDeal" styleClass="textarea max" rows="1"
						cols="40" alt="allowBlank:false,vtext:'',maxLength:255" style="{height:36px}"
						value="${tawContractForm.qualityChangeDeal}" />
				</td>

			</tr>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.beyond" />
				</td>
				<td class="content">
					<html:textarea property="beyond" styleId="beyond"
						styleClass="textarea max" rows="1" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:255" value="${tawContractForm.beyond}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.secret" />
				</td>
				<td class="content">
					<html:textarea property="secret" styleId="secret"
						styleClass="textarea max" rows="1" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:128" value="${tawContractForm.secret}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.stopCondition" />
				</td>
				<td class="content">
					<html:textarea property="stopCondition" styleId="stopCondition"
						styleClass="textarea max" rows="1" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:128" style="{height:36px}"
						value="${tawContractForm.stopCondition}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.breachFaith" />
				</td>
				<td class="content">
					<html:textarea property="breachFaith" styleId="breachFaith"
						styleClass="textarea max" rows="1" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:128" style="{height:36px}"
						value="${tawContractForm.breachFaith}" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<fmt:message key="tawContract.solve" />
				</td>
				<td class="content">
					<html:textarea property="solve" styleId="solve" style="{height:36px}"
						styleClass="textarea max" rows="2" cols="40"
						alt="allowBlank:false,vtext:'',maxLength:255" value="${tawContractForm.solve}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.add_ons" />
				</td>
				<td class="content">
					<html:textarea property="add_ons" styleId="add_ons"
						styleClass="textarea max" rows="2" cols="40" style="{height:36px}"
						alt="allowBlank:false,vtext:'',maxLength:255" value="${tawContractForm.add_ons}" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<fmt:message key="tawContract.contact_A" />
				</td>
				<td class="content">
					<html:text property="contact_A" styleId="contact_A" style="{width:70%}"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:128"
						value="${tawContractForm.contact_A}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.contact_B" />
				</td>
				<td class="content">
					<html:text property="contact_B" styleId="contact_B" style="{width:70%}"
						styleClass="text" alt="allowBlank:false,vtext:'',maxLength:128"
						value="${tawContractForm.contact_B}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.cost" />
				</td>
				<td class="content">
					<html:text property="cost" styleId="cost" styleClass="text" style="{width:70%}"
						alt="allowBlank:false,vtext:'',maxLength:16" value="${tawContractForm.cost}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.payType" />
				</td>
				<td class="content">
					<html:text property="payType" styleId="payType" styleClass="text" style="{width:70%}"
						alt="allowBlank:false,vtext:'',maxLength:128" value="${tawContractForm.payType}" />
				</td>

			</tr>

			<tr>
				<td class="label">
					<fmt:message key="tawContract.payCycle" />
				</td>
				<td class="content">
					<html:text property="payCycle" styleId="payCycle" styleClass="text"
						alt="allowBlank:false,vtext:'',maxLength:128" style="{width:70%}"
						value="${tawContractForm.payCycle}" />
				</td>

				<td class="label">
					<fmt:message key="tawContract.timeLimit" />
				</td>
				<td class="content">
					<html:text property="timeLimit" styleId="timeLimit" readonly="true"
						styleClass="text" style="{width:70%}"
						onclick="popUpCalendar(this, this,null,null,null,true,-1);"
						alt="allowBlank:false,vtext:''"
						value="${tawContractForm.timeLimit}" />
				</td>
			</tr>



			<%--<tr>
		<td class="label">
			<fmt:message key="tawContract.createTime" />
		</td>
		<td class="content">
			<html:text property="createTime" styleId="createTime"
						styleClass="textarea max"
						alt="allowBlank:false,vtext:''" value="${tawContractForm.createTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="tawContract.creator" />
		</td>
		<td class="content">
			<html:text property="creator" styleId="creator"
						styleClass="textarea max"
						alt="allowBlank:false,vtext:''" value="${tawContractForm.creator}" />
		</td>
	</tr>

	--%>
			<tr>
				<td class="label">
					<fmt:message key="tawContract.payed" />
				</td>
				<td class="content">
					<html:text property="payed" styleId="payed" styleClass="text" style="{width:70%}"
						alt="allowBlank:false,vtext:'',maxLength:128" value="${tawContractForm.payed}" />
				</td>
				<td class="label">
					<fmt:message key="tawContract.accessory" />
				</td>
				<td class="content">
					<eoms:attachment idList="" idField="accessory" appCode="contract"
						property="accessory" name="accessory" />
				</td>
			</tr>

		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<input type="submit" class="btn"
					value="<fmt:message key="button.save"/>" />&nbsp;&nbsp;
					<c:if test="${empty tawContractForm.id}">
					<input type="button" class="btn" value="批量导入"
						onclick="javascript:{
						var url='${app}/contract/tawContracts.do?method=toXls';
						location.href=url}" />
						</c:if>
				<c:if test="${not empty tawContractForm.id}">
				<input type="button" class="btn" value="返回"
						onclick="javascript:{
						var url='${app}/contract/tawContracts.do?method=backToSearch';
						location.href=url}" />&nbsp; &nbsp;
					<input type="button" class="btn"
						value="<fmt:message key="button.delete"/>"
						onclick="javascript:if(confirm('confirm?')){
						var url='${app}/tawContract/tawContracts.do?method=remove&id=${tawContractForm.id}';
						location.href=url}" />
				</c:if>
			</td>
		</tr>
	</table>
	<html:hidden property="id" value="${tawContractForm.id}" />
	<html:hidden property="createTime" value="${tawContractForm.createTime}" />
	<html:hidden property="creator" value="${tawContractForm.creator}" />
	<html:hidden property="creatorName" value="${tawContractForm.creatorName}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
