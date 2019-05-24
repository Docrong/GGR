<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
	long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
	String userId = sessionform.getUserid();
	String deptId = sessionform.getDeptid();
%>
<script type="text/javascript">

</script>
<html:form action="/netownershipwireless.do" style="theform">
	<table id="sheet" class="formTable">	
		<tr>
	 		<td class="label">网元Id</td>	
			<td colspan="3">
				<input type="text" class="text" name="netId"  id="netId" />   
			</td>			
		</tr>
		<tr>	 		
			<td class="label">网元名称</td>	
			<td >
				<input type="text" class="text" name="netName"  id="netName" />   
			</td>
			<td class="label">综资网元名称</td>	
			<td >
				<input type="text" class="text" name="netNameByEdis"  id="netNameByEdis" />   
			</td>
		</tr>		
		<tr>
	 		<td class="label">网元类型</td>	
			<td >
				<input type="text" class="text" name="netType"  id="netType" />   
			</td>
			<td class="label">地市</td>	
			<td>
			  	<input type="text" class="text" name="city"  id="city" /> 
			</td>  
		</tr>
		<tr>
	 		<td class="label">区县</td>	
			<td class="content">
				<input type="text" class="text" name="county"  id="county" />   
			</td>
			<td class="label">入库时间</td>	
			<td class="content">
			    <input type="text" class="text" name="saveTime"  readonly="readonly" id="saveTime" 
					onclick="popUpCalendar(this, this)" />		  			  	
			</td>
		</tr>
		<tr>
	 		<td class="label">创建人</td>	
			<td class="content">
				<eoms:id2nameDB id="<%=userId %>" beanId="tawSystemUserDao"/>   
			</td>
			<td class="label">创建人所属部门</td>	
			<td class="content">
			    <eoms:id2nameDB id="<%=deptId %>" beanId="tawSystemDeptDao"/>   
			</td>
		</tr>
		<tr>
			<td class="label">维护班组</td>
			<td class="content" colspan="3">
			 	<eoms:chooser id="teamRoleIds" type="role" roleId="192" flowId="051" config="{returnJSON:true,showLeader:true}" 
             		category="[{id:'teamRoleId',text:'选择',childType:'subrole',vtext:'请选择维护班组'}]"/>	
            </td>            
		</tr>				
	</table>
	<input type="submit" name="method.xsave" value="提交" id="method.xsave" class="btn"/>	
</html:form>	  
	
		