<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
Ext.onReady(function(){
  v = new eoms.form.Validation({form:'theform'}); 
});
function ifInvoke(){
	if('${invoke}'=='true'){
		alert("该工单已经调用了其他工单，请先将被调工单作废！");
		return false;
	}else{
		return true;
	}
}
</script>

<div id="sheetform">
<html:form action="/itrequirement.do?method=performFroceHold" styleId="theform">
	<%@ include file="/WEB-INF/pages/wfworksheet/itrequirement/baseinputlinkhtmlnew.jsp"%>
  <table class="formTable">
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

		      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" alt="allowBlank:false" styleClass="select"/>

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
      	<%String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
     	  if(parentProcessName.equals("iProcessChangeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToProcessChange" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="ProcessChange" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ReleaseTask" />	
     	  <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}%>
          <input type="hidden" name="${sheetPageName}beanId" value="iITRequirementMainManager"/> 
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.itrequirement.model.ITRequirementMain"/>	<!--main\u00e8\u00a1\u00a8Model\u00e5\u00af\u00b9\u00e8\u00b1\u00a1\u00e7\u00b1\u00bb\u00e5\u0090\u008d-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.itrequirement.model.ITRequirementLink"/> <!--link\u00e8\u00a1\u00a8Model\u00e5\u00af\u00b9\u00e8\u00b1\u00a1\u00e7\u00b1\u00bb\u00e5\u0090\u008d-->
	      <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
          <input type="hidden" name="${sheetPageName}processTemplateName" value="ITRequirementProcess" />
	      <input type="hidden" name="${sheetPageName}operateName" value="forceHold" />
	      <input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
	      <input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${sheetMain.piid}"/>
	      <input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
	      <input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
	       <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
	       <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
          </td>		
	   </tr>	 
       <tr>
           <td class="label">
           			 <bean:message bundle="sheet" key="mainForm.cancelReason"/>*
			 </td>
             <td colspan="3">
    			 <c:set var="vtext" value="请填入备注信息，最多输入125字"/>
				 <textarea class="textarea max" name="${sheetPageName}cancelReason" id="${sheetPageName}cancelReason" alt="allowBlank:false,maxLength:255,vtext:'${vtext}'"></textarea>
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
