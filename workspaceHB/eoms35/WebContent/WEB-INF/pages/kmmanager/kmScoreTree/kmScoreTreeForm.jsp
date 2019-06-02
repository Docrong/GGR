<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmScoreTreeForm'});
});
</script>

<html:form action="/kmScoreTrees.do?method=save" styleId="kmScoreTreeForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	
	<tr>
		<td  class="label">
			<fmt:message key="kmScoreTree.userId" />
		</td>
		<td class="content">
		    <eoms:id2nameDB id="${kmScoreTreeForm.userId}" beanId="tawSystemUserDao" />
		    <input type="hidden" name="userId" value="${kmScoreTreeForm.userId}" />
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmScoreTree.createTime" />
		</td>
		<td class="content">
		    ${kmScoreTreeForm.createTime}
		    <input type="hidden" name="createTime" value="${kmScoreTreeForm.createTime}" />
		</td>
	</tr>
		
	<tr>
		<td  class="label">
			<fmt:message key="kmScoreTree.nodeName" />
		</td>
		<td class="content">
			<html:text property="nodeName" styleId="nodeName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmScoreTreeForm.nodeName}" />
		</td>
	</tr>
	
		<tr>
		<td  class="label">
			<fmt:message key="kmScoreTree.weight" />
		</td>
		<td class="content">
			<html:text property="weight" styleId="weight"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmScoreTreeForm.weight}" />
		</td>
	</tr>
	
	
</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmScoreTreeForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}/kmScoreTree/kmScoreTrees.do?method=remove&nodeId=${kmScoreTreeForm.nodeId}';
						location.href=url}"
						/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmScoreTreeForm.id}" />
<html:hidden property="nodeId" value="${kmScoreTreeForm.nodeId}" />
<html:hidden property="parentNodeId" value="${kmScoreTreeForm.parentNodeId}" />
<html:hidden property="leaf" value="${kmScoreTreeForm.leaf}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>