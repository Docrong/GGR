<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
function initPage(){
	v = new eoms.form.Validation({form:'theform'});   		
 } 
 
Ext.onReady(function(){
 var el = Ext.get("idSpecial");
	var mgr = el.getUpdateManager();
	mgr.loadScripts = true;
	mgr.on("update", initPage);
	
});
</script>


<div id="sheetform">
<html:form action="/${module}.do?method=newPerformNonFlow" styleId="theform">
		<%@ include file="/WEB-INF/pages/wfworksheet/common/basedetailnew.jsp"%>
		<% String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));%>
	    <br/>
    	     
    	<table class="formTable">
    	
  			<div ID="idSpecial"></div>
       	
       	</table>
       	
   		<div class="form-btns">
	    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2">
	          	<bean:message bundle="sheet" key="button.read"/>
	       	</html:submit>
	       	
	       	
	       	<!-- 下面的隐藏域不需要进行任何修改 -->
            <input type="hidden" name="beanId" id="beanId" value="${beanId }"/> 
            <input type="hidden" name="mainClassName"  id="mainClassName" value="${mainClassName}"/>	
            <input type="hidden" name="linkClassName" id="linkClassName" value="${linkClassName }"/>
            <input type="hidden" name="processTemplateName" id="processTemplateName" value="${processTemplateName}" />   
            <input type="hidden" name="linkId" id="linkId" value="${baselink.id}"/>       
		    <input type="hidden" name="id" id="id" value="${baselink.id}"/>
		    <input type="hidden" name="sheetId" id="sheetId" value="${sheetMain.sheetId}"/>
			<input type="hidden" name="piid" value="${sheetMain.piid}"/>
		    <input type="hidden" name="activeTemplateId" value="${taskName}"/>
		    <input type="hidden" name="mainId" value="${sheetMain.id}"/>
		    <input type="hidden" name="sheetKey" value="${sheetMain.id}"/>
		    <input type="hidden" name="correlationKey" value="${sheetMain.correlationKey}"/>		    
		    <input type="hidden" name="TKID" value="${tkid}"/>
		    <input type="hidden" name="preLinkId" value="${preLink.id}"/>
		    <input type="hidden" name="taskCompleteTime" value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
		    <input type="hidden" name="taskName" value="${taskName}"/>
	     	<input type="hidden" name="taskStatus" value="${taskStatus}"/>  
		    <input type="hidden" name="ifNeedMain" value="true"/>
		    
		    <c:if test="${taskName == 'reply'}">
		    	<input type="hidden" name="operateType" value="9" />
		    </c:if>
		    <c:if test="${taskName == 'advice'}">
		    	<input type="hidden" name="operateType" value="-11" />
		    </c:if>
   		</div>
</html:form>

</div>
