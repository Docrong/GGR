
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="smsContentTemplateDetail.title"/></title>
<!-- <content tag="heading"><fmt:message key="smsContentTemplateDetail.heading"/></content> -->


<html:form action="/saveSmsContentTemplate.do?method=xsave" method="post" styleId="smsContentTemplateForm"> 
	<table class="formTable">
		<caption><bean:message key='smsTitle.newContent'/></caption>
		<tr>
			<td class="label">
				<bean:message key='smsContentTemplate.name'/>
			</td>
			<td class="content max">
				<html:text property="name" size="120"></html:text>
			</td>
		</tr>
		<tr>
			<td class="label">
				<bean:message key='smsContentTemplate.content'/>
			</td>
			<td class="content max">
				<html:textarea property="content" cols="90" rows="5"></html:textarea>
			</td>
		</tr>
		<tr>
			<td class="label">
				<bean:message key='smsContentTemplate.remark'/>
			</td>
			<td class="content max">
				<html:textarea property="remark" cols="90" rows="5"></html:textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<html:submit property="save"><bean:message key="button.save"/></html:submit>	
				<html:button property="return"><bean:message key='smsButton.return'/></html:button>
			</td>
		</tr>		
	</table>
	<html:hidden property="id"/>
	<html:hidden property="deleted"/>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>