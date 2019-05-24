<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
    Ext.onReady(function() {
        v = new eoms.form.Validation({form:'kmExpertCetForm'});
    });

	window.onload =   function(){
		var userId = document.getElementById('userId').value;
		var url = "${app}/kmmanager/kmExpertCets.do?method=search&userId="+userId;
		//window.opener.location=url;
		var operType = '${operType}';
		if(operType == 'save'){
			window.opener.location=url;//刷新父窗口
			window.close(); //关闭自己			
		}
	}
	function sub(){
		var name = document.forms[0].expertCetPath.value;
		if (name == '' || name.length <= 0) {
	 		alert('文件不能为空'); 
	 		return false;
		}
	}

</script>

<html:form action="/kmExpertCets.do?method=save" styleId="kmExpertCetForm" method="post">

	<html:hidden property="userId" value="${kmExpertCetForm.userId}" />

	<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="kmExpertCet.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCet.expertCetName" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertCetName" styleId="expertCetName"
						styleClass="text medium" alt="allowBlank:false,vtext:'请输入证书名称...'"
						value="${kmExpertCetForm.expertCetName}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCet.expertCetDate" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertCetDate" styleId="expertCetDate"
						styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true" alt="allowBlank:false,vtext:'请输入获奖时间...'"
						value="${kmExpertCetForm.expertCetDate}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCet.expertCetDetail" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:textarea property="expertCetDetail" styleId="expertCetDetail"
						styleClass="text medium" cols="30" rows="5"
						alt="allowBlank:false,vtext:'请输入简介...'"
						value="${kmExpertCetForm.expertCetDetail}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCet.expertCetPath" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<eoms:attachment idList="" idField="expertCetPath" appCode="kmmanager"
						scope="request" name="kmExpertCetForm" property="expertCetPath" />
				</td>
			</tr>

		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<input type="submit" onclick="return sub()" class="btn" value="<fmt:message key="button.save"/>" />
				<input type="button" class="btn" value="关闭" onclick="javascript:window.close();" />
			</td>
		</tr>
	</table>

	<html:hidden property="id" value="${kmExpertCetForm.id}" />

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
