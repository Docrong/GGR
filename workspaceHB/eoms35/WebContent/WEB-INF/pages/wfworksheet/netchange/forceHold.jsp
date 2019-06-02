<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
 v = new eoms.form.Validation({form:'theform'}); 
});
function ifInvoke(){
	if(${invoke}=='true'){
		alert("${eoms:a2u('该工单已经调用了其他工单，请先将被调工单作废！')}");
		return false;
	}else{
		return true;
	}
}
</script>


<div id="sheetform">
<html:form action="/netchange.do?method=performFroceHold" styleId="theform">
	 <%@ include file="/WEB-INF/pages/wfworksheet/netchange/baseinputlinkhtmlnew.jsp"%>
       
  <table class="listTable">
    	<div ID="idSpecial"></div>
        <tr>
          <td width="320" colspan="2">
          <%
   String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateType"));
   if(operateType != null){
      if(operateType.equals("forceHold")) {
      %>
          <input type="hidden" name="${sheetPageName}operateType" value="-13" />
          <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">

		      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" alt="allowBlank:false" initDicId="10303" styleClass="select"/>

		    </td>
          </tr> 
      <%
      }else if(operateType.equals("nullity")){
      %>
          <input type="hidden" name="${sheetPageName}operateType" value="-12" />
      <%
      }else if(operateType.equals("forceNullity")){
      %>
          <input type="hidden" name="${sheetPageName}operateType" value="-14" />
      <%
      }
     }
 %>
          <input type="hidden" name="${sheetPageName}beanId" value="iNetChangeMainManager"/> 
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netchange.model.NetChangeMain"/>	<!--mainè¡¨Modelå¯¹è±¡ç±»å-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netchange.model.NetChangeLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
	      <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
          <input type="hidden" name="${sheetPageName}processTemplateName" value="NetChangeMainProcess" />
	      <input type="hidden" name="${sheetPageName}operateName" value="forceHold" />
	      <input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
	      <input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${sheetMain.piid}"/>
	      <input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
	      <input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
	       <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
	        <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
          </td>		
	   </tr>	 
     	<%String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
     	  if(parentProcessName.equals("iBusinessPilotMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessPilot" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="pilot" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />	
     	<%}else if(parentProcessName.equals("iBusinessOperationMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessOperation" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="operate" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessBackoutMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessBackout" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessDredgeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessDredge" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessChangeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessChange" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
       <%}else if(parentProcessName.equals("iNetOptimizeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="NetOptimize" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iResourceAffirmMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="ResourceAffirm" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iSecurityDealMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToSecurityDeal" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="SecurityDeal" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iGreatEventMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToGreatEvent" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="GreatEvent" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}%>
       <tr>
           <td class="label">
           			 <bean:message bundle="sheet" key="mainForm.cancelReason"/>*
			 </td>
             <td colspan="3">
    				<c:set var="vtext" value="${eoms:a2u('请填入备注信息，最多输入4000字符')}"/>
				 <textarea class="textarea max" name="${sheetPageName}cancelReason" id="${sheetPageName}cancelReason" alt="allowBlank:false,maxLength:4000,vtext:'${vtext}'"></textarea>
			</td>		
	   </tr>
	   
       </table>
      
  
   <div class="form-btns">
    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2" onclick="return ifInvoke();">
          	<bean:message bundle="sheet" key="button.done"/>
       	</html:submit>
   </div>
</html:form>

</div>
