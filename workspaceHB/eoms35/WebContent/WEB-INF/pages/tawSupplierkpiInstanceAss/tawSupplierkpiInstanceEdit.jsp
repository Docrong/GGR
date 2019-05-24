<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
    //init Form validation and styles
	//valider({form:'tawSupplierkpiInstanceAssForm',vbtn:'method.save'});
})

function cancel() {
  var specialType = document.forms[0].specialType.value;
  var url = "<c:url value="/supplierkpi/editTawSupplierkpiInstanceAss.do?method=monitorForm"/>";
  url = url + "&specialType=" + specialType;
  location.href = url;
}
function reject() {
  var id = document.forms[0].id.value;
  var specialType = document.forms[0].specialType.value;
  var url = "<c:url value="/supplierkpi/editInstance.do?method=rejectInstance"/>";
  url = url + "&specialType=" + specialType;
  url = url + "&id=" + id;
  location.href = url;
}
</script>
<div class="list-title">
	<fmt:message key="tawSupplierkpiInstanceDetail.heading"/>
</div>
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
<html:form action="updateTawSupplierkpiInstanceAss" method="post" styleId="tawSupplierkpiInstanceForm"> 
<html:hidden property="id"/>
<html:hidden property="specialType"/>
    <tr height="30">
    <td width = "15%">
        <bean:message key="tawSupplierkpiInstanceList.assessContent" />        
    </td>
    <td width = "35%">
        <bean:write name="tawSupplierkpiInstanceForm" property="assessContent" />

    </td>

    <td width = "15%">
        <bean:message key="tawSupplierkpiInstanceList.supplier" />
    </td>
    <td width = "35%">
        <bean:write name="tawSupplierkpiInstanceForm" property="manufacturerName" /> 
    </td>
    </tr>

    <tr height="30">
      <td>
        <bean:message key="tawSupplierkpiInstanceList.statictsCycle" />
      </td>
      <td>
      	<eoms:dict key="dict-supplierkpi" dictId="statictsCycle" 
      		itemId="${tawSupplierkpiInstanceForm.statictsCycle}" beanId="id2nameXML" />     
      </td>
      <td>
        <bean:message key="tawSupplierkpiInstanceList.unit" />
      </td>
      <td>
      	<eoms:dict key="dict-supplierkpi" dictId="unit" 
      		itemId="${tawSupplierkpiInstanceForm.unit}" beanId="id2nameXML" />
      </td>
    </tr>
    <tr height="40">
      <td>
        <bean:message key="tawSupplierkpiInstanceList.examineContent" />
      </td>
      <td colspan="3">
        <html:textarea property="examineContent" styleId="examineContent" styleClass="textarea small" style="width:88%"/>
      </td>      
    </tr>
    <tr height="40">
      <td>
        <bean:message key="tawSupplierkpiInstanceForm.memo" />
      </td>
      <td colspan="3">
        <html:textarea property="memo" styleId="memo" styleClass="textarea small" style="width:88%"/>
      </td>
    </tr>
  
    <tr class="buttonBar bottom">
       <td colspan="4">
        <input type="submit" class="btn" name="method.update" value="${eoms:a2u('修改')}" />
		<input type="button" class="btn" value="${eoms:a2u('驳回')}" onclick="reject()"/>
        <input type="button" class="btn" value="${eoms:a2u('返回')}" onclick="cancel()"/>
        </td>
    </tr>
</html:form>
</table>
</div>

<%@ include file="/common/footer_eoms.jsp"%>