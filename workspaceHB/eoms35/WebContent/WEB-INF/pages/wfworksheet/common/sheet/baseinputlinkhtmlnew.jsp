<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
	<table class="formTable">
		  <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
		  <tr>
		  	   <!-- 操作人 -->
			   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
		             <input type="hidden" name="operateUserId" value="${sheetLink.operateUserId}"/>
	           </td>
	           <td class="content">
	              	 <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
	           </td>
	           <!-- 操作组织 -->
			   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
		             <input type="hidden" name="operateDeptId" value="${sheetLink.operateDeptId}"/>
	           </td>
	           <td class="content"> 
	                 <eoms:id2nameDB id="${sheetLink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
	           </td>
		  </tr>
		  <tr>
		  	   <!-- 操作人角色 -->
		       <td class="label">
			       	 <bean:message bundle="sheet" key="linkForm.operateRoleName"/>*
	                 <input type="hidden" name="operateRoleId" value="${sheetLink.operateRoleId}"/>
               </td>
               <td colspan="3">
	                 <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
	                 <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;       
           	   </td>
		  </tr>  
		  <tr>
		  	   <!-- 联系人方式 -->
			   <td class="label">
			     	<bean:message bundle="sheet" key="linkForm.operaterContact"/> *           
	           </td>
	           <td class="content">
	              	<input type="text" class="text" name="operaterContact" 
	                id="operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
	           </td>
	           <!-- 操作时间 -->
			   <td class="label">		     
	             	<bean:message bundle="sheet" key="linkForm.operateTime"/>*
	           </td>
	           <td class="content"> 
	            	<input class="text" onclick="popUpCalendar(this, this)" type="text" 
	               			name="operateTime" readonly="readonly" 
	                  		value="${eoms:date2String(sheetLink.operateTime)}" id="operateTime" alt="allowBlank:false"/>
	           </td>
		  </tr>
	</table>     
