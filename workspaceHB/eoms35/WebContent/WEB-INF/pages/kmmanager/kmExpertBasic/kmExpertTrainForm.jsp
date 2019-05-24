<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExpertTrainForm'});
});

	window.onload = function(){
        var userId = document.getElementById('userId').value;
		var url = "${app}/kmmanager/kmExpertTrains.do?method=search&userId="+userId;
		//window.opener.location=url;
		var operType = '${operType}';
		if(operType == 'save'){
			window.opener.location=url;//刷新父窗口
			window.close(); //关闭自己			
		}
	}
</script>

<html:form action="/kmExpertTrains.do?method=save" styleId="kmExpertTrainForm" method="post"> 

<html:hidden property="userId"/>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmExpertTrain.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertTrainDate" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertTrainDate" styleId="expertTrainDate"
						styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true"							
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertTrainDate}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertTrainTime" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertTrainTime" styleId="expertTrainTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertTrainTime}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertTrainLesson" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertTrainLesson" styleId="expertTrainLesson"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertTrainLesson}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertCity" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertCity" styleId="expertCity"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertCity}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertUnit" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertUnit" styleId="expertUnit"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertUnit}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertLicense" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="expertLicense" styleId="expertLicense"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertLicense}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmExpertTrain.expertTrainDes" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:textarea property="expertTrainDes" styleId="expertTrainDes"
						styleClass="text medium" cols="30" rows="5"
						alt="allowBlank:false,vtext:''" value="${kmExpertTrainForm.expertTrainDes}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<input type="button" class="btn" value="关闭" onclick="javascript:window.close();"/>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmExpertTrainForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>