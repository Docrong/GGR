
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="smsContentTemplateDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="smsContentTemplateDetail.heading"/></content> -->


<html:form action="/smsContentTemplates.do?method=forward2New" method="post" styleId="smsContentTemplateForm"> 
	<table class="formTable">
		<tr>
			<td colspan="4" align="center">
				<bean:message key='smsTitle.mgrContent'/>
			</td> 
		</tr>
		<tr>
			<td class="label">
				<bean:message key='smsContentTemplate.name'/>
			</td>
			<td class="label">
				<bean:message key='smsContentTemplate.content'/>
			</td>
			<td class="label">
				<bean:message key='smsContentTemplate.remark'/>
			</td>
			<td class="label">
				<bean:message key='smsButton.delete'/>
			</td>
		</tr>
		<logic:iterate id="contentList" name="contentList">
			<tr>
				<td>
					<a href="${app}/msg/smsContentTemplates.do?method=xeditInit&id=<bean:write name="contentList" property="id" />"><bean:write name="contentList" property="name" /></a>					
				</td>
			
				<td>
					<bean:write name="contentList" property="content" /> 
				</td>
			
				<td>
					<bean:write name="contentList" property="remark" /> 
				</td>				
				<td>
					<a href="${app}/msg/smsContentTemplates.do?method=xdelete&id=<bean:write name="contentList" property="id" />"><bean:message key='smsButton.delete'/></a>					
				</td>
			</tr>
		</logic:iterate>
		<tr>
			<td colspan="4" align="center">
				<html:submit property="New"><bean:message key='smsButton.new'/></html:submit>			
				<html:button property="return"><bean:message key='smsButton.return'/></html:button>
			</td>
		</tr>
	</table>	
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>