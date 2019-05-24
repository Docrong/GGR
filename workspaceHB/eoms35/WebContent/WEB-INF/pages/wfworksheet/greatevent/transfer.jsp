<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
 var v = new eoms.form.Validation({form:'theform'});
</script>

<div id="sheetform">
<html:form action="/greatevent.do?method=performTransferWorkItem" styleId="theform">
       <%@ include file="/WEB-INF/pages/wfworksheet/greatevent/baseinputlinkhtmlnew.jsp"%>
    <br/>
           <input type="hidden" name="${sheetPageName}beanId"   id="${sheetPageName}beanId"  value="iGreatEventMainManager"/>
           <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"  value="com.boco.eoms.sheet.greatevent.model.GreatEventMain"/>	
           <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.greatevent.model.GreatEventLink"/> 
           <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
       <table class="formTable">
         <tr>
            <td class="label">
           			 <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
			 </td>
             <td colspan="3">
		           <textarea name="${sheetPageName}transferReason" class="textarea max" id="${sheetPageName}transferReason" 
		            alt="allowBlank:false,maxLength:500,width:255,vtext:'请填入信息，最多输入125字'" alt="width:'500px'"></textarea>	
			</td>	
	     </tr>
	   </table> 
    <fieldset>
	 <legend>
			请选择派发对象
	 </legend>
     <eoms:chooser  id="test"    config="{{returnJSON:true}"
                               category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                               panels="[
                                     {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                                     {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                                 ]"
                   />
	</fieldset>	
   <div class="form-btns">
       	<input type="submit" class="submit" name="method.save" id="method.save"   value="<bean:message bundle='sheet' key='button.done'/>" >
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/greatevent/greatevent.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    
       	
   </div>
</html:form>

</div>

