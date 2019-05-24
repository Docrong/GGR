<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String roleId = "";
 String roleName="";
 %>
<script type="text/javascript">
 </script>
<%@ include file="/WEB-INF/pages/wfworksheet/accountpopedomapply/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		<%if(!operateType.equals("61")) {%>
		<caption><bean:message bundle="accountpopedomapply" key="accountpopedomapply.header"/></caption>
		<%} %>
        <input type="hidden" name="${sheetPageName}beanId" value="iAccountPopedomApplyMainManager"/> 
        <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.accountpopedomapply.model.AccountPopedomApplyMain"/>	
        <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.accountpopedomapply.model.AccountPopedomApplyLink"/>
        <c:if test="${taskName != 'HoldTask' }">
	      <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
	    </c:if>
	    <%if(taskName.equals("AuditHumTask")){ 
	      if(operateType.equals("51")){%>
	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ActualizeHumTask" />
     	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
         <tr>
  			<td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.applyAttitude"/>*</td>
		   	<td  colspan='3'>
		   	   <eoms:comboBox name="${sheetPageName}applyAttitude" id="${sheetPageName}applyAttitude" 
            	      initDicId="1011301"  alt="allowBlank:false" styleClass="select-class" 
            	      defaultValue="${sheetLink.applyAttitude}" onchange="ifAuditPass(this.value,'AuditHumTask')"/>${sheetLink.applyAttitude}
		    </td>
		 </tr>   
		 <tr>
  			<td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.applyResult"/>*</td>
		   	<td  colspan='3'>
		   	   <textarea class="textarea max" name="${sheetPageName}applyResult" id="${sheetPageName}applyResult" alt="width:500,allowBlank:false">${sheetLink.applyResult}</textarea>
		    </td>
		 </tr>  
		 <%}else if(operateType.equals("52")){
		 %>
		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
     	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
         <tr>
  			<td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.applyAttitude"/>*</td>
		   	<td  colspan='3'>
		   	   <eoms:comboBox name="${sheetPageName}applyAttitude" id="${sheetPageName}applyAttitude" 
            	      initDicId="1011301"  alt="allowBlank:false" styleClass="select-class" 
            	      defaultValue="${sheetLink.applyAttitude}" onchange="ifAuditPass(this.value,'AuditHumTask')"/>${sheetLink.applyAttitude}
		    </td>
		 </tr>   
		 <tr>
  			<td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.applyResult"/>*</td>
		   	<td  colspan='3'>
		   	   <textarea class="textarea max" name="${sheetPageName}applyResult" id="${sheetPageName}applyResult" alt="width:500,allowBlank:false">${sheetLink.applyResult}</textarea>
		    </td>
		 </tr> 
		 <%
		 }else if(operateType.equals("55")){
		 %>
		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="callProcess" />
     	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	 
		 <tr>
	       <td class="label">
	         <bean:message bundle="sheet" key="linkForm.remark" />*
		   </td>
		   <td colspan="3">			
		      <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>
		 <%
		 }
		 } %>
		 <%if(taskName.equals("ActualizeHumTask")){ 
		  if(operateType.equals("46")){%>
		    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldHumTask" />
	        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	        <tr>
  				<td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.link.deResult"/>*</td>
		   		<td  colspan='3'>
		   		    <textarea class="textarea max" name="${sheetPageName}deResult" id="${sheetPageName}deResult" alt="width:500,allowBlank:false">${sheetLink.deResult}</textarea>
		        </td>
		   </tr>
	     <%}else if(operateType.equals("11")){
		 %>
		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="callProcess" />
     	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	 
		 <tr>
	       <td class="label">
	         <bean:message bundle="sheet" key="linkForm.remark" />*
		   </td>
		   <td colspan="3">			
		      <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>
		 <%
		 }
	     } %>  
	      <%if(taskName.equals("HoldHumTask")) {%>  
			<%if(operateType.equals("18")|| operateType.equals("11")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>	     	
			 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select"/>
		    </td>
		  </tr>		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请填入信息，最多输入125字'" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>    
	     <%} }%>
	      <!--流程中的字段域 结束-->
          <!-- 公共功能，抄送和确认受理 -->
     	    <!-- 驳回到上一级 -->
     	     <%if(operateType.equals("4")){ %>
 		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>					
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="link.linkRejectCause" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
       <!-- 抄送--> 	
      <% if(taskName.equals("cc")){%>    
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
          <%} %>
      <!-- 确认受理 -->     
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />	
		
  		<%}%>        
  </table>
   <!-- 公共功能，抄送和确认受理 结束 -->
    <!-- 各个环节中的选择角色 -->
    <br/> 				
	 <!-- 待审核  -->
	  <%if(taskName.equals("AuditHumTask")&& operateType.equals("51")) {%>	
		<fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName"></span>
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="338" flowId="074" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%} %> 
	 <%if(taskName.equals("AuditHumTask")&& operateType.equals("52")) {%>	
		<fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName"></span>
	    </legend>
	        派发:
	       <div class="viewlistitem-subrole" style="display: inline;">
            <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
           </div>        
		</fieldset>		
	 <%} %> 
     <!-- 实施中 -->
  <%if(taskName.equals("ActualizeHumTask")&& operateType.equals("46")) {%>	
	    <fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			建单人
	    </legend> 
		</fieldset>		
  <%} %> 
  <br/>

<div ID="idSpecia2"></div> 
<script type="text/javascript">
 var v1 = eoms.form;
 var operateType = "${operateType}";
  function ifAuditPass(input, task){
   if (operateType != "11") {
		if(input=="101130101" && task == "AuditHumTask"){	
			chooser_sendRole.enable();
			//审核通过到实施中
			$('${sheetPageName}phaseId').value='ActualizeHumTask';
			$('roleName').innerHTML="账号配置员";
			$('${sheetPageName}operateType').value='51';
	
		} else if(input=="101130102" && task == "AuditHumTask"){
			//审核不通过到驳回 
			chooser_sendRole.disable();
			$('${sheetPageName}phaseId').value='ByRejectHumTask';
			$('roleName').innerHTML="建单人";
			$('${sheetPageName}operateType').value='52';	
	        }
        }
}
</script>
   