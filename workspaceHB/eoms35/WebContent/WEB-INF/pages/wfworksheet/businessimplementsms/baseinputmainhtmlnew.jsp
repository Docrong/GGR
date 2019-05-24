
<%
 String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
 System.out.println("roleId===="+roleId);
%>

<!-- base info -->
<input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}mainId" id="${sheetPageName}mainId" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sheetKey" id="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
<input type="hidden" name="${sheetPageName}sheetKey" id="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sendUserId" id="${sheetPageName}sendUserId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="${sheetPageName}sendOrgType" id="${sheetPageName}sendOrgType" value="${sheetMain.sendOrgType}"/>
<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="${status}"/>
<input type="hidden" name="${sheetPageName}aiid" id="${sheetPageName}aiid" value="${taskId}"/>
<input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${piid}"/>
<input type="hidden" name="${sheetPageName}activeTemplateId" id="${sheetPageName}activeTemplateId" value="${taskName}"/>		    
<input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
<input type="hidden" name="${sheetPageName}parentCorrelation" id="${sheetPageName}parentCorrelation" value="${sheetMain.parentCorrelation}"/>
<input type="hidden" name="${sheetPageName}parentSheetName" id="${sheetPageName}parentSheetName" value="${sheetMain.parentSheetName}"/>
<input type="hidden" name="${sheetPageName}parentSheetId" id="${sheetPageName}parentSheetId" value="${sheetMain.parentSheetId}"/>
<input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetMain.sendDeptId}"/>
<input type="hidden" name="${sheetPageName}tkid" id="${sheetPageName}tkid" value="${tkid}"/>
<input type="hidden" name="${sheetPageName}preLinkId" id="${sheetPageName}preLinkId" value="${preLink.id}"/>
<input type="hidden" name="${sheetPageName}sendDeptId" id="${sheetPageName}sendDeptId" value="${sheetMain.sendDeptId}"/>
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${preLink.operateType}"/>
<input type="hidden" name="${sheetPageName}taskAcceptLimit" id="${sheetPageName}taskAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"/>
<input type="hidden" name="${sheetPageName}taskCompleteLimit" id="${sheetPageName}taskCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"/>
<input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
<input type="hidden" name="methodBeanId"  id="methodBeanId" value="${methodBeanId}"/>
<input  type="hidden"  name="${sheetPageName}parentPhaseName"  id="${sheetPageName}parentPhaseName"  value="${parentPhaseName}"/>
<table class="formTable">
<tr>
<input  type="hidden"  name="${sheetPageName}parentPhaseName"  id="${sheetPageName}parentPhaseName"  value="${parentPhaseName}"/ >
  <td class="label"><bean:message bundle="sheet" key="mainForm.title"/>*</td>
  <td colspan="3">
  <c:if test="${!empty taskName}">
  <c:if test="${empty sheetMain.title}">
    <input type="text" name="${sheetPageName}title" class="text max" id="${sheetPageName}title" 
		value="${sheetMain.title}" alt="allowBlank:false,vtext:'<bean:message bundle="sheet" key="mainForm.titleCheck"/>'"/>
  </c:if>
  <c:if test="${!empty sheetMain.title}">
	<bean:write name="sheetMain" property="title" scope="request"/>
  </c:if>
  </c:if>
  <c:if test="${empty taskName}">
      <input type="text" name="${sheetPageName}title" class="text max" id="${sheetPageName}title"
		value="${sheetMain.title}" alt="allowBlank:false,vtext:'<bean:message bundle="sheet" key="mainForm.titleCheck"/>'"/>
  </c:if>
  </td>
</tr>

<tr>
  <td class="label"><bean:message bundle="sheet" key="mainForm.sendUserName"/>*</td>
  <td class="content"><eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/></td>
  <td class="label"><bean:message bundle="sheet" key="mainForm.sendDeptName"/>*</td>
  <td class="content"><eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/></td>
</tr>

<tr>
  <td class="label"><bean:message bundle="sheet" key="mainForm.sendRoleName"/>*</td>
  <td colspan="3">
     <c:if test="${empty sheetMain.sendRoleId}">               
        <eoms:roleComboBox name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" 
	  	  userId="${sheetMain.sendUserId}" roleId="${roleId}" defaultValue="${sheetMain.sendRoleId}"/>     
     </c:if>
     <c:if test="${!empty sheetMain.sendRoleId}">
       <input type="hidden" name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" value="${sheetMain.sendRoleId}"/>
       <c:if test="${empty endRoleId}">                      
           <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetMain.sendRoleId}"/>                       
       </c:if>
        <c:if test="${!empty endRoleId}">                  
           <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${endRoleId}"/>                       
       </c:if>
       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
     </c:if>
  </td>
  
</tr>

 <tr>
   <td class="label"><bean:message bundle="sheet" key="mainForm.sendContact"/>*</td>
  <td >
    <input type="text" class="text" name="${sheetPageName}sendContact" id="${sheetPageName}sendContact" value="${sheetMain.sendContact}" alt="allowBlank:false"/>
  </td>
   <td class="label"><bean:message bundle="sheet" key="mainForm.sendTime"/>*</td>
   <td >
    <input type="text" class="text" name="${sheetPageName}sendTime" readonly="readonly" 
					id="${sheetPageName}sendTime" value="${eoms:date2String(sheetMain.sendTime)}" 
					onclick="popUpCalendar(this, this)" alt="allowBlank:false"/> 	
  </td>
 </tr>






<c:if test="${status==1}">

  <tr>
    <td class="label"><bean:message bundle="sheet" key="mainForm.endUserName"/></td>
	<td> 
      <input type="hidden" name="${sheetPageName}endUserId" value="${endUserId}"/>
      <eoms:id2nameDB id="${endUserId}" beanId="tawSystemUserDao"/>&nbsp;
    </td>
    <td class="label"><bean:message bundle="sheet" key="mainForm.endDeptName"/></td>
	<td>
      <input type="hidden" name="${sheetPageName}endDeptId" value="${endDeptId}"/>
      <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
    </td>
  </tr>
  
  <tr>
    <td class="label"><bean:message bundle="sheet" key="mainForm.endRoleName"/></td>
    <td colspan="3">
      <input type="hidden" name="${sheetPageName}endRoleId" value="${endRoleId}"/>
      <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
    </td>
  </tr>


  <tr>
  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></td>
    <td colspan="3">
      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" styleClass="select"/>
    </td>
  </tr>
  
  <tr>
  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
    <td colspan="3">
      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" alt="width:'500px',allowBlank:false"></textarea>
    </td>
  </tr>

</c:if>

<c:if test="${status==-1}">
	      <tr>
	      	<td class="label"><bean:message bundle="sheet" key="mainForm.endUserName"/></td>
	        <td>
	          <input type="hidden" name="${sheetPageName}endUserId" value="${endUserId}"/>
	          <eoms:id2nameDB id="${sheetMain.endUserId}" beanId="tawSystemUserDao"/>&nbsp;
	        </td>
			<td class="label"><bean:message bundle="sheet" key="mainForm.endDeptName"/></td>
	        <td>
	          <input type="hidden" name="${sheetPageName}endDeptId" value="${endDeptId}"/>
	          <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
	        </td>
	      </tr>
	  
	    <tr>
	       <td class="label"><bean:message bundle="sheet" key="mainForm.endRoleName"/></td>
	       <td colspan="3">
	          <input type="hidden" name="${sheetPageName}endRoleId" value="${endRoleId}"/>
	          <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
	       </td>
	    </tr>
	    <tr>
	      <td class="label">
	      	<bean:message bundle="sheet" key="mainForm.cancelReason"/>
	      </td>
	       <td colspan="3">          
	         <textarea class="textarea" name="${sheetPageName}endResult" id="${sheetPageName}endResult" alt="allowBlank:false"></textarea>
	       </td>
	    </tr>

</c:if>


    </table>

		