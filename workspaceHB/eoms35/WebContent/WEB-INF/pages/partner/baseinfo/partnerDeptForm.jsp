<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'partnerDeptForm'});
});
function checkNum(theInput,str){ 
  a=parseInt(theInput); 
    if (isNaN(a)) 
  { 
      alert(str+"请输入数字"); 
      return false;
  } 
  else 
  return true;
  }
</script>

<html:form action="/partnerDepts.do?method=save" styleId="partnerDeptForm" method="post"> 

<input type="hidden" name="parentNodeId" value="${parentNodeId }">
<html:hidden property="treeNodeId"/>
<html:hidden property="treeId"/>

<fmt:bundle basename="config/applicationResources-partner-baseinfo">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="partnerDept.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="partnerDept.name" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="name" styleId="name"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.name}" /> 
		</td>
		
		<td class="label">
			<fmt:message key="partnerDept.type" />&nbsp;*
		</td>
		<td class="content">				
		    <eoms:comboBox name="type" id="type" initDicId="1120101"
					defaultValue='${partnerDeptForm.type}' alt="allowBlank:false,vtext:''" />
		</td>
		
	</tr>


	<tr>
		<td class="label">
			<fmt:message key="partnerDept.aptitude" />&nbsp;*
		</td>
		<td class="content">

		    <eoms:comboBox name="aptitude" id="aptitude" initDicId="1120102"
					defaultValue='${partnerDeptForm.aptitude}' alt="allowBlank:false,vtext:''" />						
		</td>
		
		<td class="label">
			<fmt:message key="partnerDept.deadline" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="deadline" styleId="deadline" readonly="true"
						styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.deadline}" />
		</td>
	</tr>



	<tr>
		<td class="label">
			<fmt:message key="partnerDept.manager" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="manager" styleId="manager"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.manager}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerDept.areaName" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="areaName" styleId="areaName" readonly="true"
						styleClass="text medium" 
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.areaName}" />
		</td>
	</tr>



	<tr>
		<td class="label">
			<fmt:message key="partnerDept.fund" />
		</td>
		<td class="content">
			<html:text property="fund" styleId="fund" 
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerDeptForm.fund}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerDept.phone" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="phone" styleId="phone" 
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.phone}" />
		</td>
	</tr>



	<tr>
		<td class="label">
			<fmt:message key="partnerDept.address" />
		</td>
		<td class="content">
			<html:text property="address" styleId="address"
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerDeptForm.address}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerDept.fax" />
		</td>
		<td class="content">
			<html:text property="fax" styleId="fax" 
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerDeptForm.fax}" />
		</td>
	</tr>



	<tr>
	
	    <td class="label">
			<fmt:message key="partnerDept.bank" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="bank" styleId="bank"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.bank}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerDept.account" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="account" styleId="account" 
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerDeptForm.account}" />
		</td>
		

	</tr>



	<tr>
	
	    <td class="label">
			<fmt:message key="partnerDept.third" />
		</td>
		<td class="content">
			<html:text property="third" styleId="third"
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerDeptForm.third}" />
		</td>
	
		<td class="label">
			<fmt:message key="partnerDept.accessory" />
		</td>
		<td class="content">
		<!-- <html:text property="accessory" styleId="accessory"
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerDeptForm.accessory}" />  -->	
						
			<eoms:attachment idList="" idField="accessory" appCode="partnerBaseinfo" scope="request" name="partnerDeptForm" property="accessory"/> 
		</td>
		

	</tr>
	<tr>
		<td class="label">
			<fmt:message key="partnerDept.remark" />
		</td>
		<td class="content" colspan="3">
			<html:textarea property="remark" styleId="remark"
						styleClass="text medium" style="width:80%" rows="3"
						alt="allowBlank:true,vtext:''" value="${partnerDeptForm.remark}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty partnerDeptForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('确定删除?')){
						var url='${app}/partner/baseinfo/partnerDepts.do?method=remove&id=${partnerDeptForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${partnerDeptForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>