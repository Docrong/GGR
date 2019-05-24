<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
    Ext.onReady(function() {
        v = new eoms.form.Validation({form:'kmExpertExpForm'});
    });
    
    window.onload = function(){
        var userId = document.getElementById('userId').value;
		var url = "${app}/kmmanager/kmExpertExps.do?method=search&userId="+userId;
		//window.opener.location=url;
		var operType = '${operType}';
		if(operType == 'save'){
			window.opener.location=url;//刷新父窗口
			window.close(); //关闭自己			
		}
	}
</script>

<html:form action="/kmExpertExps.do?method=save" styleId="kmExpertExpForm" method="post"> 

<html:hidden property="userId"/>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExpertExp.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertExp.expertCompany" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertCompany" styleId="expertCompany"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertExpForm.expertCompany}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="kmExpertExp.expertStart" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertStart" styleId="expertStart"
						styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true"								
						alt="allowBlank:false,vtext:''" value="${kmExpertExpForm.expertStart}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertExp.expertEnd" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertEnd" styleId="expertEnd"
						styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true"		
						alt="allowBlank:false,vtext:''" value="${kmExpertExpForm.expertEnd}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertExp.expertPosition" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertPosition" styleId="expertPosition"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertExpForm.expertPosition}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertExp.expertAddress" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertAddress" styleId="expertAddress"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertExpForm.expertAddress}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertExp.expertResponsiblitily" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:textarea property="expertResponsiblitily" styleId="expertResponsiblitily"
						styleClass="text medium" cols="30" rows="5"
						alt="allowBlank:false,vtext:''" value="${kmExpertExpForm.expertResponsiblitily}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
			<input type="button" class="btn" value="关闭" onclick="javascript:window.close();"/>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExpertExpForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>