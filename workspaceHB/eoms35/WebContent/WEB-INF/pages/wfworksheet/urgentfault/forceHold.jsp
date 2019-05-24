<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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
<html:form action="/urgentfault.do?method=performFroceHold" styleId="theform">
	<%@ include file="/WEB-INF/pages/wfworksheet/common/basedetailnew.jsp"%>
       
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
          <input type="hidden" name="${sheetPageName}beanId" value="iUrgentFaultMainManager"/> 
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain"/>	<!--mainè¡¨Modelå¯¹è±¡ç±»å-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
	      <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
          <input type="hidden" name="${sheetPageName}processTemplateName" value="UrgentFaultMainFlowProcess" />
	      <input type="hidden" name="${sheetPageName}operateName" value="forceHold" />
	     
	       <input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
	       <input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${sheetMain.piid}"/>
	        <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
          </td>		
	   </tr>	  
    <tr>
           <td class="label">
           			 <bean:message bundle="sheet" key="mainForm.cancelReason"/>*
			 </td>
             <td colspan="3">
    				<c:set var="vtext" value="请填入备注信息，最多输入4000字符"/>
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
