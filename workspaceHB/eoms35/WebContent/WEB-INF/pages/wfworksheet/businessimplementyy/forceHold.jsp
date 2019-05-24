<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
 v = new eoms.form.Validation({form:'theform'});
});
function ifInvoke(){
	if(${invoke}=='true'){
		alert("该工单已经调用了其他工单，请先将被调工单作废！");
		return false;
	}else{
		return true;
	}
}
</script>

<div id="sheetform">
<html:form action="/businessimplementyy.do?method=performFroceHold" styleId="theform">
	<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementyy/baseinputlinkhtmlnew.jsp"%>
  <table class="formTable">
    	<div ID="idSpecial"></div>
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
          <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementYYMainManager"/> 
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain"/>	<!--main\u00e8\u00a1\u00a8Model\u00e5\u00af\u00b9\u00e8\u00b1\u00a1\u00e7\u00b1\u00bb\u00e5\u0090\u008d-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink"/> <!--link\u00e8\u00a1\u00a8Model\u00e5\u00af\u00b9\u00e8\u00b1\u00a1\u00e7\u00b1\u00bb\u00e5\u0090\u008d-->
	      <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
          <input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessImplementYYProcess" />
	      <input type="hidden" name="${sheetPageName}operateName" value="forceHold" />
	      <input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
	      <input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${sheetMain.piid}"/>
	      <input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
	      <input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
	       <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
	        <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
	     <%String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
     	  if(parentProcessName.equals("iCircuitDispatchMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessPilot" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="pilot" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />	
     		<%}else{%>
     		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
     	<%}%>    
       <tr>
           <td class="label">
           			 <bean:message bundle="sheet" key="mainForm.cancelReason"/>*
			 </td>
             <td colspan="3">
    				<c:set var="vtext" value="${eoms:a2u('è¯·å¡«å¥å¤æ³¨ä¿¡æ¯ï¼æå¤è¾å¥1000æ±å­')}"/>
				 <textarea class="textarea max" name="${sheetPageName}remark" id="${sheetPageName}remark" alt="allowBlank:false,maxLength:2000,vtext:'${vtext}'"></textarea>
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
