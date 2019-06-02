<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<base target="_self">

<script type="text/javascript">
    Ext.onReady(function() {
        v = new eoms.form.Validation({form:'kmExpertComForm'});
    });

	window.onload = function(){
        var userId = document.getElementById('userId').value;
		var url = "${app}/kmmanager/kmExpertComs.do?method=search&userId="+userId;
		//window.opener.location=url;
		var operType = '${operType}';
		if(operType == 'save'){
			window.opener.location=url;//刷新父窗口
			window.close(); //关闭自己			
		}
	}
	function sub(){
		var name = document.forms[0].expertComPath.value;
		if (name == '' || name.length <= 0) {
	 		alert('文件不能为空'); 
	 		return false;
		}
	}
</script>

<html:form action="/kmExpertComs.do?method=save" styleId="kmExpertComForm" method="post">

	<html:hidden property="userId" />

	<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="kmExpertCom.form.heading" />
				</div>
			</caption>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCom.expertComDate" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:text property="expertComDate" styleId="expertComDate"
						styleClass="text medium"
						onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						readonly="true" alt="allowBlank:false,vtext:'请输入表彰时间...'"
						value="${kmExpertComForm.expertComDate}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCom.expertComDetail" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<html:textarea property="expertComDetail" styleId="expertComDetail"
						styleClass="text medium" cols="30" rows="5"
						alt="allowBlank:false,vtext:'请输入简介...'"
						value="${kmExpertComForm.expertComDetail}" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertCom.expertComPath" />&nbsp;<font color='red'>*</font>
				</td>
				<td class="content">
					<eoms:attachment idList="" idField="expertComPath" appCode="kmmanager"
						scope="request" name="kmExpertComForm" property="expertComPath" />
				</td>
			</tr>

		</table>

	</fmt:bundle>

	<table>
		<tr>
			<td>
				<input type="submit" class="btn" onclick="return sub()"  value="<fmt:message key="button.save"/>" />
				<input type="button" class="btn" value="关闭" onclick="javascript:window.close();" />
			</td>
		</tr>
	</table>
	
	<html:hidden property="id" value="${kmExpertComForm.id}" />

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
