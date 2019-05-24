<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainEnterForm'});
});
</script>

<html:form action="/trainEnters.do?method=save" styleId="trainEnterForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<font color='red'>*</font>号为必填内容
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="trainEnter.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="trainEnter.enterName" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainEnterForm.enterName}" beanId="tawSystemUserDao" />
			<input type="hidden" name="enterName" value="${trainEnterForm.enterName}" />
		</td>
		<td class="label">
			<fmt:message key="trainEnter.trainEnterDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainEnterForm.trainEnterDept}" beanId="tawSystemDeptDao" />
			<input type="hidden" name="trainEnterDept" value="${trainEnterForm.trainEnterDept}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainEnter.trainPlanId" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainEnterForm.trainPlanId}" beanId="trainPlanDao" />
			<input type="hidden" name="trainPlanId" value="${trainEnterForm.trainPlanId}" />
		</td>
		
		<td class="label">
			<fmt:message key="trainEnter.trainEnterTel" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainEnterTel" styleId="trainEnterTel"
						styleClass="text max"
						alt="allowBlank:false,vtext:'',vtype:'number'" value="${trainEnterForm.trainEnterTel}" />
		</td>
	</tr>

	
	<tr>
		<td class="label">
			<fmt:message key="trainEnter.trainEnterRemark" />
		</td>
		<td class="content" colspan="3">
			<textarea name="trainEnterRemark" id="trainEnterRemark" 
			          cols="50" class="textarea max" 
			          >${trainEnterForm.trainEnterRemark}</textarea>	
		</td>
	</tr>


</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<c:if test="${empty trainEnterForm.id}">
				<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			</c:if>
			<c:if test="${trainEnterForm.enterName == sessionScope.sessionform.userid}">
				<c:if test="${not empty trainEnterForm.id}">
					<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
						onclick="javascript:if(confirm('确认删除吗？')){
						var url='${app}/kmmanager/trainEnters.do?method=remove&id=${trainEnterForm.id}';
						location.href=url}"	/>
			</c:if>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${trainEnterForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>