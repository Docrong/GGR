<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
v = new eoms.form.Validation({form:'theform'});
</script>

<div id="sheetform">
<html:form action="/urgentfault.do?method=performTransferWorkItem" styleId="theform">
       <%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtmlnew.jsp"%>
       
       <input type="hidden" name="${sheetPageName}beanId" value="iUrgentFaultMainManager"/>
       <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain"/>	<!--main表Model对象类名-->	
       <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink"/> <!--link表Model对象类名-->
  
  
    <table class="formTable">
        <tr>
           <td class="label">
           			 <bean:message bundle="sheet" key="linkForm.transmitReason"/>
			 </td>
             <td colspan="3">
    				<textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason" alt="width:'500px'">${sheetLink.transferReason}</textarea>
			</td>		
	   </tr>
	   
	   </table> 
    <fieldset>
	 <legend>
			请选择派发对象
	 </legend>
	 <div class="x-form-item" >
           <eoms:chooser id="test" type="role" roleId="${bigRoleId}" flowId="53" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'user,subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]" 
			 />   	
	 </div>	
	</fieldset>	
	
    
       
   <div class="form-btns">
    	
       	
       	<input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:isCopy()" value="<bean:message bundle='sheet' key='button.done'/>" >
	  
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=performSaveInfo'" value="<bean:message bundle='sheet' key='button.save'/>" />
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	 
	      <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	   
   </div>
</html:form>

</div>