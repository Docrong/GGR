<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmTableThemeForm'});
});
</script>

<html:form action="/kmTableThemes.do?method=save" styleId="kmTableThemeForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmTableTheme.form.heading"/></div>
	</caption>

	<tr>
		<td  class="label">
			<fmt:message key="kmTableTheme.themeName" />
		</td>
		<td class="content">
			<html:text property="themeName" styleId="themeName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableThemeForm.themeName}" />
		</td>
	</tr>
	<!--  tr>
		<td  class="label">
			<fmt:message key="kmTableTheme.isLeaf" />
		</td>
		<td class="content">		
		  <eoms:comboBox name="leaf" id="leaf" initDicId="10301" form="kmTableThemeForm"/>
		</td>
	</tr>-->
	<tr>
		<td  class="label">
			<fmt:message key="kmTableTheme.isOpen" />
		</td>
		<td class="content">	
			<eoms:comboBox name="isOpen" id="isOpen" initDicId="10301" defaultValue="1030101" form="kmTableThemeForm"/>
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmTableTheme.isDeleted" />
		</td>
		<td class="content">
			<eoms:comboBox name="isDeleted" id="isDeleted" initDicId="10301" defaultValue="1030102" form="kmTableThemeForm"/>			
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmTableTheme.orderBy" />
		</td>
		<td class="content">
			<html:text property="orderBy" styleId="orderBy"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableThemeForm.orderBy}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty kmTableThemeForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('<fmt:message key="message.delMessage"/>')){
						var url='${app}/kmmanager/kmTableThemes.do?method=remove&nodeId=${kmTableThemeForm.nodeId}';
						location.href=url}"
						/>
			</c:if>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmTableThemeForm.id}" />
<html:hidden property="nodeId" value="${kmTableThemeForm.nodeId}" />
<html:hidden property="parentNodeId" value="${kmTableThemeForm.parentNodeId}" />
<html:hidden property="leaf" value="${kmTableThemeForm.leaf}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>