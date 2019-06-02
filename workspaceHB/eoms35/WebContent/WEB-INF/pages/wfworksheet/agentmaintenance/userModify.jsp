<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.sheet.agentmaintenance.model.UserMade" %>
<style>
.formTable1{
	width: 60%;
	border-collapse: collapse;
	font-size: 12px;
}

.formTable1 td{
	border: 1px solid #C9DEFA;
	padding: 6px 6px;
	background-color:#FFFFFF;
}

.formTable1 tr.header td{
	background: #cae8ea;
	color:#006699;	
}
</style>
<form method="post" action="${app}/sheet/agentmaintenance/agentmaintenance.do?method=saveUser">
<table class="formTable1" align="center">
	<caption>
		<div class="header center">角色定制</div>
	</caption>
	<tr>
		<td class="lable" width="30%" align="middle">操作人</td>
		<td class="content max" width="70%">
			<eoms:id2nameDB id="${madeUser_id}" beanId="tawSystemUserDao" />
			<input type="hidden" name="dealPerformer" id="dealPerformer" value="${madeUser_id}">
			<input type="hidden" name="id" id="id" value="${id}">
		</td>
	</tr>
	<tr>
		<td class="lable" width="30%" align="middle">定制人员</td>
		<td>
			<legend>
				<bean:message bundle="agentmaintenance" key="role.toOrgName"/>
					<span id="roleName">:人员或部门
				</span>
			</legend>	
			<div class="x-form-item" >
				<eoms:chooser id="subuser" category="[{id:'auditPerformer',childType:'user,dept',limit:'none',text:'选择'}]" type="user,dept" config="{returnJSON:true,showLeader:true}" 
				data="${madeToUser}"/>
			</div> 		   
			
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input class="button" type="submit" name="submit" id="submit" value="保存">
		</td>
	</tr>
</table>
</form>
<%@ include file="/common/footer_eoms.jsp"%>