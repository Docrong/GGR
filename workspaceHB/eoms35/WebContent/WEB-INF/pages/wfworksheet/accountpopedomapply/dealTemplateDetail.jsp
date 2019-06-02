<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.accountpopedomapply.model.accountpopedomapplyLink  accountpopedomapplyLink = (com.boco.eoms.sheet.accountpopedomapply.model.accountpopedomapplyLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(AccountPopedomApplyLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(AccountPopedomApplyLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/accountpopedomapply/accountpopedomapply.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
   	form.submit();
 }
function removeTemplate() {
	if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
		thisform.submit();
	}
}
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/accountpopedomapply/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/accountpopedomapply.do" styleId="theform">       
	<br/>
               <input type="hidden" name="${sheetPageName}beanId" value="iaccountpopedomapplyMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.accountpopedomapply.model.accountpopedomapplyMain"/>	<!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.accountpopedomapply.model.accountpopedomapplyLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
     <table class="listTable">
			      <tr>
		            <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.applyAttitude"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}applyAttitude" id="${sheetPageName}applyAttitude" >${sheetLink.applyAttitude}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.applyResult"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}applyResult" id="${sheetPageName}applyResult" >${sheetLink.applyResult}</textarea>
                    </td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.deResult"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}deResult" id="${sheetPageName}deResult" >${sheetLink.deResult}</textarea>
                    </td>
		          </tr>
	 </table>
</table>
</html:form>
<logic:present name="type">
<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('current')">
          	<bean:message bundle="sheet" key="button.saveCurTemplate" />
    	</html:button>
</c:if>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
         <bean:message bundle="sheet" key="button.delete" />
</html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
         <bean:message bundle="sheet" key="button.back" />
</html:button>
