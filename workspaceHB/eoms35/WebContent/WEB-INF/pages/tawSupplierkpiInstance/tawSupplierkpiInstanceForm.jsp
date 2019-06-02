<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawSupplierkpiInstanceDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSupplierkpiInstanceDetail.heading"/></content>
<div class="list">
<table>
<html:form action="updateTawSupplierkpiInstance" method="post" styleId="tawSupplierkpiInstanceForm"> 
<html:hidden property="id"/>
    <tr height="50">
    <td width = "6%">
        <bean:message key="tawSupplierkpiInstanceList.assessContent" />        
    </td>
    <td width = "35%">
        <bean:write name="tawSupplierkpiInstanceForm" property="assessContent" />

    </td>

    <td width = "6%">
        <bean:message key="tawSupplierkpiInstanceList.supplier" />
    </td>
    <td width = "30%">
        <bean:write name="tawSupplierkpiInstanceForm" property="manufacturerName" /> 
    </td>
    </tr>

    <tr height="50">
      <td>
        <bean:message key="tawSupplierkpiInstanceList.statictsCycle" />
      </td>
      <td>
        <bean:write name="tawSupplierkpiInstanceForm" property="statictsCycleName" />        
      </td>
      <td>
        <bean:message key="tawSupplierkpiInstanceList.unit" />
      </td>
      <td>
        <bean:write name="tawSupplierkpiInstanceForm" property="kpiName" />
      </td>
    </tr>
    <tr height="50">
      <td>
        <bean:message key="tawSupplierkpiInstanceList.examineContent" />
      </td>
      <td colspan="3">
        <html:text style="width:100%;height:30;border:1px solid #006699"  property="examineContent" styleId="assessRole" styleClass="text medium"/>
      </td>      
    </tr>
    <tr height="50">
      <td>
        <bean:message key="tawSupplierkpiInstanceForm.memo" />
      </td>
      <td colspan="3">
        <html:textarea style="width:100%;height:50;border:1px solid #006699" property="memo" styleId="memo" styleClass="text medium"/>
      </td>
    </tr>
  
    <tr class="buttonBar bottom">
       <td colspan="4">
        <html:submit styleClass="button" property="method.update" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>
<!--
        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSupplierkpiInstance')">
            <fmt:message key="button.delete"/>
        </html:submit>
-->
        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
        </td>
    </tr>
</html:form>
</table>
</div>
<script type="text/javascript">
    //Form.focusFirstElement($("tawSupplierkpiInstanceForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>