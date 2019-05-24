<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.boco.eoms.km.expert.config.KmExpertProps" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
    Ext.onReady(function() {
        v = new eoms.form.Validation({form:'kmExpertEduForm'});
    });
    
    window.onload = function(){
        var userId = document.getElementById('userId').value;
		var url = "${app}/kmmanager/kmExpertEdus.do?method=search&userId="+userId;
		//window.opener.location=url;
		var operType = '${operType}';
		if(operType == 'save'){
			window.opener.location=url;//刷新父窗口
			window.close(); //关闭自己			
		}
	}
</script>

<html:form action="/kmExpertEdus.do?method=save" styleId="kmExpertEduForm" method="post">

	<html:hidden property="userId" />

	<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="kmExpertEdu.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduSch" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertEduSch" styleId="expertEduSch" styleClass="text medium" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduSch}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduEdu" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
				    <eoms:comboBox name="expertEduEdu" id="expertEduEdu" initDicId='<%=KmExpertProps.getDictRootId("RootEducation")%>'
				        defaultValue="${kmExpertEduForm.expertEduEdu}" 
				        alt="allowBlank:false,vtext:'请选择专家最高学历(单选字典)...'"/>				        					        			
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduStart" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertEduStart" styleId="expertEduStart" styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduStart}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduEnd" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertEduEnd" styleId="expertEduEnd" styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduEnd}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduPro" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertEduPro" styleId="expertEduPro" styleClass="text medium" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduPro}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduProName" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertEduProName" styleId="expertEduProName" styleClass="text medium" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduProName}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduCity" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertEduCity" styleId="expertEduCity" styleClass="text medium" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduCity}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertEdu.expertEduDes" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:textarea property="expertEduDes" styleId="expertEduDes" styleClass="text medium" cols="30" rows="5" alt="allowBlank:false,vtext:''"
						value="${kmExpertEduForm.expertEduDes}" />
				</td>
			</tr>

		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
				<input type="button" class="btn" value="关闭" onclick="javascript:window.close();" />
			</td>
		</tr>
	</table>
	<html:hidden property="id" value="${kmExpertEduForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>