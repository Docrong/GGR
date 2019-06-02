<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmOperateScoreForm'});
});
</script>

<html:form action="/kmOperateScores.do?method=save" styleId="kmOperateScoreForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmOperateScore.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateScore.operateName" />
		</td>
		<td class="content">
			<html:text property="operateName" styleId="operateName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateScoreForm.operateName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateScore.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateScoreForm.score}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmOperateScore.operateId" />
		</td>
		<td class="content">
			<html:text property="operateId" styleId="operateId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmOperateScoreForm.operateId}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmOperateScoreForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmOperateScore/kmOperateScores.do?method=remove&id=${kmOperateScoreForm.id}';
						location.href=url}"	/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmOperateScoreForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>