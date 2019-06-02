<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
</script>
<%
 String specialType = (String)request.getAttribute("specialType");
 String autingState = (String)request.getAttribute("autingState");
 String url = "supplierkpi/editTawSupplierkpiInstanceAss.do?specialType="+specialType;
 if(autingState!=null&&autingState.equals("yishenhe"))url = "supplierkpi/editTawSupplierkpiInstanceAss.do?method=view&specialType="+specialType;
%>
<div class="list-title">
	<fmt:message key="tawSupplierkpiInstanceDetail.heading"/>
</div>
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
<html:form action="updateTawSupplierkpiInstanceAss" method="post" styleId="tawSupplierkpiInstanceForm"> 
<html:hidden property="id"/>
<html:hidden property="specialType"/>
    <tr height="30">
    <td width = "20%">
        <bean:message key="tawSupplierkpiInstanceList.supplier" />
    </td>
    <td width = "80%" colspan="3">
        <bean:write name="tawSupplierkpiInstanceForm" property="manufacturerName" /> 
    </td>
    </tr>

    <tr height="30">
      <td width = "20%">
        <bean:message key="tawSupplierkpiInstanceList.statictsCycle" />
   	  </td>
      <td width = "30%">
      	<eoms:dict key="dict-supplierkpi" dictId="statictsCycle" 
      		itemId="${tawSupplierkpiInstanceForm.statictsCycle}" beanId="id2nameXML" />
      </td>
      <td width = "20%">
        <bean:message key="tawSupplierkpiInstanceList.unit" />
      </td>
      <td width = "30%">
      	<eoms:dict key="dict-supplierkpi" dictId="unit" 
      		itemId="${tawSupplierkpiInstanceForm.unit}" beanId="id2nameXML" />
      </td>
    </tr>
    <tr height="50">
      <td>
        <bean:message key="tawSupplierkpiInstanceList.assessContent" />        
      </td>
    <td colspan="3">
        <bean:write name="tawSupplierkpiInstanceForm" property="assessContent" />
    </td>
    </tr>
    <tr height="50">
      <td>
        <bean:message key="tawSupplierkpiInstanceList.examineContent" />
      </td>
      <td colspan="3">
        <bean:write name="tawSupplierkpiInstanceForm" property="examineContent" />  
      </td>      
    </tr>
    <tr height="50">
      <td>
        <bean:message key="tawSupplierkpiInstanceForm.memo" />
      </td>
      <td colspan="3">
        <bean:write name="tawSupplierkpiInstanceForm" property="memo" />  
      </td>
    </tr>
  
    <tr class="buttonBar bottom">
       <td colspan="4">
        <input type="button" class="btn" value="${eoms:a2u('返回')}" onclick="window.location.replace('${app}/<%=url %>');"/>
        </td>
    </tr>
</html:form>
</table>
</div>

<%@ include file="/common/footer_eoms.jsp"%>