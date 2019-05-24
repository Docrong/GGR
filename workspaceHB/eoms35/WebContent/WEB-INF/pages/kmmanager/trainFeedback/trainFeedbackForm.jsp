<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainFeedbackForm'});
});
</script>

<html:form action="/trainFeedbacks.do?method=save" styleId="trainFeedbackForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<font color='red'>*</font>号为必填内容
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="trainFeedback.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="trainFeedback.feedbackUser" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainFeedbackForm.feedbackUser}" beanId="tawSystemUserDao" />
			<input type="hidden" name="feedbackUser" value="${trainFeedbackForm.feedbackUser}" />
		</td>
		<td class="label">
			<fmt:message key="trainFeedback.trainFeedbackDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainFeedbackForm.trainFeedbackDept}" beanId="tawSystemDeptDao" />
			<input type="hidden" name="trainFeedbackDept" value="${trainFeedbackForm.trainFeedbackDept}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainFeedback.trainPlanId" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainFeedbackForm.trainPlanId}" beanId="trainPlanDao" />
			<input type="hidden" name="trainPlanId" value="${trainFeedbackForm.trainPlanId}" />
		</td>
		
		<td class="label">
			<fmt:message key="trainFeedback.trainFeedbackTel" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainFeedbackTel" styleId="trainFeedbackTel"
						styleClass="text max"
						alt="allowBlank:false,vtext:'',vtype:'number'" value="${trainFeedbackForm.trainFeedbackTel}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainFeedback.trainFeedbackContent" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<textarea name="trainFeedbackContent" id="trainFeedbackContent" 
			          cols="50" class="textarea max"  alt="allowBlank:false,vtext:''"
			          >${trainFeedbackForm.trainFeedbackContent}</textarea>	
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainFeedback.trainFeedbackRemark" />
		</td>
		<td class="content" colspan="3">
			<textarea name="trainFeedbackRemark" id="trainFeedbackRemark" 
			          cols="50" class="textarea max" 
			          >${trainFeedbackForm.trainFeedbackRemark}</textarea>	
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<c:if test="${empty trainFeedbackForm.id}">
				<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			</c:if>
			<c:if test="${trainFeedbackForm.feedbackUser == sessionScope.sessionform.userid}">
				<c:if test="${not empty trainFeedbackForm.id}">
					<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
						onclick="javascript:if(confirm('确认删除吗？')){
						var url='${app}/kmmanager/trainFeedbacks.do?method=remove&id=${trainFeedbackForm.id}';
						location.href=url}"	/>
				</c:if>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${trainFeedbackForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>