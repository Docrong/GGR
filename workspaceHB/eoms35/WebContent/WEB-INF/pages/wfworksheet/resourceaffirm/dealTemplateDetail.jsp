<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.resourceaffirm.model.resourceaffirmLink  resourceaffirmLink = (com.boco.eoms.sheet.resourceaffirm.model.resourceaffirmLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(resourceaffirmLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(resourceaffirmLink.getOperateType()));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		alert('<bean:message bundle="sheet" key="template.save" />');
   		return false;
   	}
   	form.action = "${app}/sheet/resourceaffirm/resourceaffirm.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/resourceaffirm/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/resourceaffirm.do" styleId="theform">       
	<br/>
               <input type="hidden" name="${sheetPageName}beanId" value="iresourceaffirmMainManager"/>
               <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.resourceaffirm.model.resourceaffirmMain"/>	<!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->	
               <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.resourceaffirm.model.resourceaffirmLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
     <table class="listTable">
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.ndeptContact"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact" value="${sheetLink.ndeptContact}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.ndeptContactPhone"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.dealResult"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult" value="${sheetLink.dealResult}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.dealDesc"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc" value="${sheetLink.dealDesc}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.netResCapacity"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.expectFinishdays"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.circuitCode"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"/></td>
		          </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.clientPgmCapacity"/></td>
		            <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}"/></td>
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
