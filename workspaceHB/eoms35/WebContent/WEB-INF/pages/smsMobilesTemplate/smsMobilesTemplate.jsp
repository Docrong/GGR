
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="smsMobliesTemplateDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="smsMobliesTemplateDetail.heading"/></content> -->


<html:form action="/smsMobilesTemplates.do?method=forward2New" method="post" styleId="smsMobilesTemplateForm"> 
	<table class="formTable">
		<tr>
			<td colspan="5" align="center">
				<bean:message key='smsTitle.mgrContent'/>
			</td> 
		</tr>
		<tr>
			<td class="label">
				<bean:message key='smsMobilesTemplate.name'/>
			</td>
			<td class="label">
				<bean:message key='smsMobilesTemplate.mobiles'/>
			</td>
			<td class="label">
				<bean:message key='smsMobilesTemplate.users'/>
			</td>
			<td class="label">
				<bean:message key='smsMobilesTemplate.remark'/>
			</td>
			<td class="label">
				<bean:message key='smsButton.delete'/>
			</td>
		</tr>
		<logic:iterate id="mobilesList" name="mobilesList">
			<tr>
				<td>
					<a href="${app}/msg/smsMobilesTemplates.do?method=xeditInit&id=<bean:write name="mobilesList" property="id" />"><bean:write name="mobilesList" property="name" /></a>					
				</td>
			
				<td>
					<bean:write name="mobilesList" property="mobiles" /> 
				</td>
			
				<td>
					<bean:write name="mobilesList" property="users" /> 
				</td>
				<td>
					<bean:write name="mobilesList" property="remark" /> 
				</td>
				<td>
					<a href="${app}/msg/smsMobilesTemplates.do?method=xdelete&id=<bean:write name="mobilesList" property="id" />"><bean:message key='smsButton.delete'/></a>
				</td>
			</tr>
		</logic:iterate>
		<tr>
			<td colspan="5" align="center">
				<html:submit property="New"><bean:message key='smsButton.new'/></html:submit>			
				<html:button property="return"><bean:message key='smsButton.return'/></html:button>
			</td>
		</tr>
	</table>	
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>