<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
 var v = new eoms.form.Validation({form:'theform'});
</script>


<div id="sheetform">
<html:form action="/${module}.do?method=newPerformNonFlow" styleId="theform">
       <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/baseinputlinkhtmlnew.jsp" />
       <br/>	
        	
       <table class="formTable">
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />*
			    </td>
				<td colspan="3">			
				 	   <input type="hidden" name="operateType" id="operateType" value="-15" />
			           <textarea name="remark" class="textarea max" id="remark" 
			        alt="allowBlank:false,width:300,maxLength:300,vtext:'请填入备注，最多输入150个字符'">${sheetLink.remark}</textarea>
			  </td>
			</tr>  
	   </table> 
	   <fieldset>
			<legend> 
				请选择抄送对象
		 	</legend>
	 		<div class="x-form-item" >
				 	<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
				      category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
			</div>	
	   </fieldset>	
	   
   <div class="form-btns">
       	<input type="submit" class="submit" name="method.save" id="method.save"   value="<bean:message bundle='sheet' key='button.done'/>" >
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/${module}/${module}.do?method=newShowListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    <input type="hidden" name="taskId" id="taskId" value="${sheetLink.tkid}"/>
	    <input type="hidden" name="activeTemplateId" value="${taskName}"/>
	    <input type="hidden" name="preLinkId" value="${preLink.id}"/>
	    <input type="hidden" name="mainId" value="${sheetMain.id}"/>    
   </div>
</html:form>

</div>

