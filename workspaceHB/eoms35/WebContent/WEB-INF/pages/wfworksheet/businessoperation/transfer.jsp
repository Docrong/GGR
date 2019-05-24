<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
 var v = new eoms.form.Validation({form:'theform'});
</script>

<div id="sheetform">
<html:form action="/businessoperation.do?method=performTransferWorkItem" styleId="theform">
       <%@ include file="/WEB-INF/pages/wfworksheet/businessoperation/baseinputlinkhtmlnew.jsp"%>
    <br/>
           <input type="hidden" name="${sheetPageName}beanId"   id="${sheetPageName}beanId"  value="iBusinessOperationMainManager"/>
           <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"  value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationMain"/>	<!--mainè¡¨Modelå¯¹è±¡ç±»å-->	
           <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink"/> <!--linkè¡¨Modelå¯¹è±¡ç±»å-->
           <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
        
       <table class="formTable">
        <tr>
		     <td class="label">
		       <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
		     </td>
            <td class="content">
                 <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                   value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" 
                    id="${sheetPageName}nodeAcceptLimit" />
		    </td>
		    <td class="label">
		      <bean:message bundle="sheet" key="linkForm.completeLimit"/>
            </td>
            <td class="content">
              <!--   <input type="hidden" name="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"/> -->
                 <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly" value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"/>
          
		    </td>
		  </tr>
        <tr>
           <td class="label">
           			 <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
			 </td>
             <td colspan="3">
		           <textarea name="${sheetPageName}transferReason" class="textarea max" id="${sheetPageName}transferReason" 
		            alt="allowBlank:false,maxLength:1000,width:500,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'" alt="width:'500px'"></textarea>	
			</td>	
	   </tr>
	   
	   </table> 
    <fieldset>
	 <legend>
			${eoms:a2u('请选择派发对象')}
	 </legend>
     <eoms:chooser  id="test"    config="{{returnJSON:true}"
                               category="[{id:'dealPerformer',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择派发人')}'}]"
                               panels="[
                                     {text:'${eoms:a2u('可选人员')}',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                                     {text:'${eoms:a2u('备选子角色')}',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                                 ]"
                   />
	</fieldset>	
	
    
       
   <div class="form-btns">
       	<input type="submit" class="submit" name="method.save" id="method.save"   value="<bean:message bundle='sheet' key='button.done'/>" >
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/businessoperation/businessoperation.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	    
       	
   </div>
</html:form>

</div>

