<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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
<html:form action="/focusresourceerrata.do?method=newPerformNonFlow" styleId="theform">
	<%@ include file="/WEB-INF/pages/wfworksheet/common/basedetailnew.jsp"%>
             <%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
   if(taskName != null){
      if(taskName.equals("reply")) {
      %>
          <input type="hidden" name="operateType" value="9" />
      <%
      }else if(taskName.equals("advice")){
      %>
          <input type="hidden" name="operateType" value="-11" />
      <%
      }
     }
  %>
           <input type="hidden" name="beanId" value="iFocusResourceErrataMainManager"/> 
           <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataMain"/>	
           <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataLink"/>
           <input type="hidden" name="processTemplateName" value="FocusResourceErrata" />
	       <input type="hidden" name="operateName" value="nonFlowOperate" />
            <input type="hidden" name="linkId" id="linkId" value="${baselink.id}"/>
		    <input type="hidden" name="id" id="id" value="${baselink.id}"/>
		    <input type="hidden" name="sheetId" id="sheetId" value="${sheetMain.sheetId}"/>
			<input type="hidden" name="piid" value="${sheetMain.piid}"/>
		    <input type="hidden" name="piid" value="${taskId}"/>
		    <input type="hidden" name="activeTemplateId" value="${taskName}"/>
		    <input type="hidden" name="mainId" value="${sheetMain.id}"/>
		    <input type="hidden" name="sheetKey" value="${sheetMain.id}"/>
		    <input type="hidden" name="correlationKey" value="${sheetMain.correlationKey}"/>		    
		    <input type="hidden" name="TKID" value="${tkid}"/>
		    <input type="hidden" name="preLinkId" value="${preLink.id}"/>
		    <input type="hidden" name="taskCompleteTime" value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
		    <input type="hidden" name="taskName" value="${taskName}"/>
		     <input type="hidden" name="taskStatus" value="${taskStatus}"/>  
  
  <div ID="idSpecial"></div>
  <br>
    <table class="formTable">
               <tr>
	  				  <td class="label">
	  				     <bean:message bundle="sheet" key="linkForm.remark"/>
	  				  </td>
	  				  <td colspan=3>
	  				   	 <bean:write name="baselink" property="remark" scope="request"/>
	                  </td>
		  		</tr>   
       </table>      
   <div class="form-btns">
    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2">
          	<bean:message bundle="sheet" key="button.read"/>
       	</html:submit>
   </div>
</html:form>

</div>
