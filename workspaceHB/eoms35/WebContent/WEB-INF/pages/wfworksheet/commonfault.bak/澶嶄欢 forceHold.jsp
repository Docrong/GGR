<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">

Ext.onReady(function(){
 v = new eoms.form.Validation({form:'theform'});
	
});
</script>


<div id="sheetform">
<html:form action="/commonfault.do?method=performFroceHold" styleId="theform">


          <input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultMainManager"/> 
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"/>	<!--mainè¡¨Modelå¯¹è±¡ç±»å-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.commonfault.model.CommonFaultLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
	      <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
          <input type="hidden" name="${sheetPageName}processTemplateName" value="CommonFaultMainFlowProcess" />
	      <input type="hidden" name="${sheetPageName}operateName" value="forceHold" />
	      <input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
	      <input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${sheetMain.piid}"/>
	      <input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
	      <input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
	       <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetMain.sendRoleId}"/>
	        <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
       
  <table class="listTable">
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
    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2">
          	<bean:message bundle="sheet" key="button.done"/>
       	</html:submit>
   </div>
</html:form>

</div>
