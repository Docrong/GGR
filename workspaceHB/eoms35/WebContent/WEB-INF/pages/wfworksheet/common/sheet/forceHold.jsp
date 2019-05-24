<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
 v = new eoms.form.Validation({form:'theform'});   
});
</script>


<div id="sheetform">
<html:form action="/${module}.do?method=performFroceHold" styleId="theform">
	  
	  <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/baseinputlinkhtmlnew.jsp" />
	  <br>
	  <table class="formTable">
	    	<div ID="idSpecial"></div>
	    	<c:if test="${operateType == 'forceHold'}">
	            <tr>
				  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
				    <td colspan="3">
				       <eoms:comboBox name="holdStatisfied" id="holdStatisfied" alt="allowBlank:false" initDicId="10303" styleClass="select"/>
				       <input type="hidden" name="operateType" value="-13" />
				    </td>
	            </tr> 
			</c:if>	 
	       <tr>
	            <td class="label">
	           			 <bean:message bundle="sheet" key="mainForm.cancelReason"/>
				 </td>
	             <td colspan="3">
	    			 <c:set var="vtext" value="请填入备注信息，最多输入4000字符"/>
					 <textarea class="textarea max" name="cancelReason" id="cancelReason" alt="allowBlank:false,maxLength:4000,vtext:'${vtext}'"></textarea>
				</td>		
		   </tr>
   </table>
   
   <div class="form-btns">
    	<html:submit styleClass="btn" property="method.save2" styleId="method.save2">
          	<bean:message bundle="sheet" key="button.done"/>
       	</html:submit>
    	  <c:if test="${operateType == 'nullity' }">
          		  <input type="hidden" name="operateType" value="-12" />
          </c:if>	          	
          <c:if test="${operateType == 'forceNullity' }">
          		  <input type="hidden" name="operateType" value="-14" />
          </c:if>
        	  
        	       	  
          <!-- 下面的隐藏域的值不需要修改 -->
          <input type="hidden" name="operateName" value="forceHold" />  
		  <input type="hidden" name="beanId" id="beanId" value="${beanId }"/> 
          <input type="hidden" name="mainClassName"  id="mainClassName" value="${mainClassName}"/>	
          <input type="hidden" name="linkClassName" id="linkClassName" value="${linkClassName }"/>
          <input type="hidden" name="processTemplateName" id="processTemplateName" value="${processTemplateName}" />
	      <input type="hidden" name="id" id="id" value="${sheetMain.id}"/>
	      <input type="hidden" name="correlationKey" id="correlationKey" value="${sheetMain.correlationKey}"/>
	      <input type="hidden" name="piid" id="piid" value="${sheetMain.piid}"/>
	      <input type="hidden" name="operateUserId" id="operateUserId" value="${sheetMain.sendUserId}"/>
	      <input type="hidden" name="operateDeptId" id="operateDeptId" value="${sheetMain.sendDeptId}"/>
	      <input type="hidden" name="operateRoleId" id="operateRoleId" value="${sheetMain.sendRoleId}"/>
	      <input type="hidden" name="sheetId" id="sheetId" value="${sheetMain.sheetId}"/>
   </div>
</html:form>

</div>
