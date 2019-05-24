<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- 工单基本信息 -->
<input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
<input type="hidden" name="parentCorrelation" id="parentCorrelation" value="${sheetMain.parentCorrelation}"/>
<input type="hidden" name="parentSheetName" id="parentSheetName" value="${sheetMain.parentSheetName}"/>
<input type="hidden" name="parentSheetId" id="parentSheetId" value="${sheetMain.parentSheetId}"/>
<input type="hidden" name="parentPhaseName" id="parentPhaseName" value="${sheetMain.parentPhaseName}"/>
<input type="hidden" name="operateType" id="operateType" />
<input type="hidden" name="id" id="id" value="${mainId}"/>


<table class="formTable">
<tr>
	  <!-- 工单标题 -->
	  <td class="label"><bean:message bundle="sheet" key="mainForm.title"/>*</td>
	  <td colspan="3">
	    <input type="text" name="title" class="text max" id="title" 
			value="${sheetMain.title}" alt="allowBlank:false,vtext:'<bean:message bundle="sheet" key="mainForm.titleCheck"/>'"/>
	  </td>
</tr>
<tr>
	  <!-- 操作人和操作人部门 -->
	  <td class="label"><bean:message bundle="sheet" key="mainForm.sendUserName"/></td>
	  <td class="content"><eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/></td>
	  <td class="label"><bean:message bundle="sheet" key="mainForm.sendDeptName"/></td>
	  <td class="content"><eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/></td>
</tr>
<tr>
	  <!-- 操作人角色 -->
  	  <td class="label"><bean:message bundle="sheet" key="mainForm.sendRoleName"/>*</td>
	  <td colspan="3">
	     <c:if test="${empty sheetMain.sendRoleId}">               
	        <eoms:roleComboBox name="sendRoleId" id="sendRoleId" 
		  	  userId="${sheetMain.sendUserId}" roleId="${roleId}" defaultValue="${sheetMain.sendRoleId}"/>     
	     </c:if>
	     <c:if test="${!empty sheetMain.sendRoleId}">
	       <input type="hidden" name="sendRoleId" id="sendRoleId" value="${sheetMain.sendRoleId}"/>
	       <c:if test="${empty endRoleId}">                      
	           <input type="hidden" name="operateRoleId" id="operateRoleId" value="${sheetMain.sendRoleId}"/>                       
	       </c:if>
	        <c:if test="${!empty endRoleId}">                  
	           <input type="hidden" name="operateRoleId" id="operateRoleId" value="${endRoleId}"/>                       
	       </c:if>
	       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
	     </c:if>
	  </td>
</tr> 
<tr>
	  <!-- 联系人方式 -->
	  <td class="label"><bean:message bundle="sheet" key="mainForm.sendContact"/></td>
	  <td colspan="3">
	    <input type="text" class="text" name="sendContact" id="sendContact" value="${sheetMain.sendContact}" alt="allowBlank:false"/>
	  </td>
</tr>
</table>
<br/>