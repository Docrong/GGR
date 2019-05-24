<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
 var v = new eoms.form.Validation({form:'theform'});
</script>

<div id="sheetform">
<html:form action="/businessimplement.do?method=performTransferWorkItem" styleId="theform">
       <%@ include file="/WEB-INF/pages/wfworksheet/businessimplement/baseinputlinkhtmlnew.jsp"%>
    <br/>
           <input type="hidden" name="${sheetPageName}beanId"   id="${sheetPageName}beanId"  value="iBusinessImplementMainManager"/>
           <input type="hidden" name="${sheetPageName}mainClassName" id="${sheetPageName}mainClassName"  value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain"/>		
           <input type="hidden" name="${sheetPageName}linkClassName" id="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink"/> 
           <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
       <table class="listTable">
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
      <eoms:chooser id="test"  config="{{returnJSON:true}"
                category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                panels="[
                   {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                   {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                 ]"
          />
	 </div>	
	</fieldset>	
   <div class="form-btns">
       	<input type="submit" class="submit" name="method.save" id="method.save"   value="<bean:message bundle='sheet' key='button.done'/>" >
        <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/businessimplement/businessimplement.do?method=performSaveInfo'" value="<bean:message bundle='sheet' key='button.save'/>" />
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/businessimplement/businessimplement.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
	    <input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/businessimplement/businessimplement.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
   </div>
</html:form>

</div>

