<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
//Ext.onReady(function() {
//	v = new eoms.form.Validation({form:'operuserForm'});
//});
Ext.onReady(function(){
	 v = new eoms.form.Validation({form:'operuserForm'});
	var	deptAction ='${app}/xtree.do?method=dept';
	new xbox({
				btnId:'deptname',dlgId:'dlg-dept',dlgTitle:'请选择该人员所属部门',
				treeDataUrl:deptAction,treeRootId:'-1',treeRootText:'所有部门',treeChkMode:'single',treeChkType:'dept',
				showChkFldId:'deptname',saveChkFldId:'deptid'
			});
});
</script>

<html:form action="/operusers.do?method=save" styleId="operuserForm" method="post" > 

<fmt:bundle basename="config/applicationResource-operuser">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="operuser.form.heading"/></div>
	</caption>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.name" />
		</td>
		<td class="content">
			<!--<eoms:comboBox name="state" id="state" initDicId="11501"
      						styleClass="text medium" alt="allowBlank:false,vtext:''" />-->
			<html:text property="name" styleId="name" styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${operuserForm.name}" />
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.deptid" />
		</td>
		<td class="content">
					<input type="hidden" id="deptid" name="deptid" value="${operuserForm.deptid}"/>
					<input type="text" value="${operuserForm.deptname}" id="deptname" name="deptname"
					 class="text medium" alt="allowBlank:false,vtext:''" readonly="readonly"/>
					 
					 
			<!--<html:text property="deptid" styleId="deptid"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${operuserForm.deptid}" />-->
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.sex" />
		</td>
		<td class="content">						
			<eoms:comboBox name="sex" id="sex" initDicId="1150109" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.sex}"/>
			
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.subarea" />
		</td>
		<td class="content">
			<eoms:comboBox name="subarea" id="subarea" initDicId="1150101" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.subarea}"/>	 	
		</td>
	</tr>
<c:if test="${not empty operuserForm.oid}">
	<tr>
		<td  class="label">
			<fmt:message key="operuser.savetime" />
		</td>
		<td class="content">
			<html:text property="savetime" styleId="savetime" styleClass="text medium" 
					readonly="true"  value="${operuserForm.savetime}" />	
		</td>
	</tr>
</c:if>
	<tr>
		<td  class="label">
			<fmt:message key="operuser.majortype" />
		</td>
		<td class="content">
			<eoms:comboBox name="majortype" id="majortype" initDicId="1150102" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.majortype}"/>	
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.birthday" />
		</td>
		<td class="content">
			<!--<html:text property="birthday" styleId="birthday"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${operuserForm.birthday}" />-->
			<input id="birthday" name="birthday" type="text" class="text"
					readonly="readonly" value="${operuserForm.birthday}"
					alt="allowBlank:false,link:'endTime',vtext:''"
					onclick="popUpCalendar(this, this,null,null,null,false,-1);" />			
		</td>
	</tr>
	<c:if test="${not empty operuserForm.oid}">
	<tr>
		<td  class="label">
			<fmt:message key="operuser.age" />
		</td>
		<td class="content">
			<html:text property="age" readonly="true" styleClass="text medium" />
			
		</td>
	</tr>
	</c:if>
	<tr>
		<td  class="label">
			<fmt:message key="operuser.jobtype" />
		</td>
		<td class="content">
			<eoms:comboBox name="jobtype" id="jobtype" initDicId="1150103" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.jobtype}"/>				
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.joblevele" />
		</td>
		<td class="content">
			<eoms:comboBox name="joblevele" id="joblevele" initDicId="1150104" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.joblevele}"/>
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.schoollevel" />
		</td>
		<td class="content">
			<eoms:comboBox name="schoollevel" id="schoollevel" initDicId="1150105" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.schoollevel}"/>
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.powerlevel" />
		</td>
		<td class="content">
			<eoms:comboBox name="powerlevel" id="powerlevel" initDicId="1150106" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.powerlevel}"/>
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.prizelevel" />
		</td>
		<td class="content">
			<eoms:comboBox name="prizelevel" id="prizelevel" initDicId="1150107" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.prizelevel}"/>
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.worklevel" />
		</td>
		<td class="content">
			<eoms:comboBox name="worklevel" id="worklevel" initDicId="1150108" alt="allowBlank:false,vtext:''"
      						styleClass="select-class" defaultValue="${operuserForm.worklevel}"/>
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="operuser.remark" />
		</td>
		<td class="content">
			<html:textarea property="remark" styleId="remark"
						styleClass="text medium"
						 value="${operuserForm.remark}" />
		</td>
	</tr>

</table>

</fmt:bundle>
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty operuserForm.oid}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/operuser/operusers.do?method=remove&id=${operuserForm.oid}';
						location.href=url}"
						/>
			</c:if>
		</td>
	</tr>
</table>

<html:hidden property="oid" value="${operuserForm.oid}" />
<html:hidden property="nodeId" value="${operuserForm.nodeId}" />
<html:hidden property="parentNodeId" value="${operuserForm.parentNodeId}" />
<html:hidden property="leaf" value="${operuserForm.leaf}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>