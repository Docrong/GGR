<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function initPage(){
	v = new eoms.form.Validation({form:'theform'});   		
 } 
Ext.onReady(function(){
 //showPage();
 var el = Ext.get("idSpecial");
	var mgr = el.getUpdateManager();
	mgr.loadScripts = true;
	
	mgr.on("update", initPage);
	
});
</script>


<div id="sheetform">
<html:form action="/businesschange.do?method=performDeal" styleId="theform">
	<%@ include file="/WEB-INF/pages/wfworksheet/businesschange/basedetailnew.jsp"%>
             <%
   String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateType"));
   if(operateType != null){
      if(operateType.equals("reply")) {
      %>
          <input type="hidden" name="${sheetPageName}operateType" value="9" />
      <%
      }else if(operateType.equals("advice")){
      %>
          <input type="hidden" name="${sheetPageName}operateType" value="-11" />
      <%
      }
     }
 %>
          <input type="hidden" name="${sheetPageName}beanId" value="iBusinessChangeMainManager"/> 
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businesschange.model.BusinessChangeMain"/>	<!--mainè¡¨Modelå¯¹è±¡ç±»å-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businesschange.model.BusinessChangeLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
           <input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessChangeProcess" />
	       <input type="hidden" name="${sheetPageName}operateName" value="nonFlowOperate" />
            <input type="hidden" name="${sheetPageName}linkId" id="${sheetPageName}linkId" value="${baselink.id}"/>
		    <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${baselink.id}"/>
		    <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
			<input type="hidden" name="${sheetPageName}piid" value="${sheetMain.piid}"/>
		    <input type="hidden" name="${sheetPageName}piid" value="${taskId}"/>
		    <input type="hidden" name="${sheetPageName}activeTemplateId" value="${taskName}"/>
		    <input type="hidden" name="${sheetPageName}mainId" value="${sheetMain.id}"/>
		    <input type="hidden" name="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
		    <input type="hidden" name="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>		    
		    <input type="hidden" name="${sheetPageName}TKID" value="${tkid}"/>
		    <input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
		    <input type="hidden" name="${sheetPageName}taskCompleteTime" value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
		    <input type="hidden" name="${sheetPageName}taskName" value="${taskName}"/>
		     <input type="hidden" name="${sheetPageName}taskStatus" value="${taskStatus}"/>  
		     <input type="hidden" name="${sheetPageName}ifNeedMain" value="true"/>
    <table class="formTable">
  <div ID="idSpecial"></div>
          <%--         <tr>
	  				  <td class="label">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td colspan=3>
	  				   	 <bean:write name="baselink" property="remark" scope="request"/>
	                  </td>
		  		</tr>	        --%> 
       </table>
   <div class="form-btns">
    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2">
          	<bean:message bundle="sheet" key="button.read"/>
       	</html:submit>
   </div>
</html:form>

</div>