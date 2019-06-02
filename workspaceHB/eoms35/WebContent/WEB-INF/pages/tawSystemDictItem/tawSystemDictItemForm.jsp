<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<head>
<title><fmt:message key="tawSystemDictItemDetail.title"/></title>
  
  <script language="JavaScript" type="text/JavaScript">   
  <!--   
    function submitAction() {
        var el = $("dictId");
        $("dictName").value = el.options[el.selectedIndex].text;
	    document.forms[0].submit();
    }
  //-->   
  </script>

<head>
<content tag="heading"><fmt:message key="tawSystemDictItemDetail.heading"/></content>

<html:form action="saveTawSystemDictItem" method="post" styleId="tawSystemDictItemForm" styleClass="required-validate"> 
<ul>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.itemId"/>
        <html:errors property="itemId"/>
        <html:text property="itemId" styleId="itemId" styleClass="text medium required max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.itemName"/>
        <html:errors property="itemName"/>
        <html:text property="itemName" styleId="itemName" styleClass="text medium required max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.dictName"/>
        <html:errors property="dictName"/>
        <html:select property="dictId" styleId="dictId">
            <html:options collection="DictTypeList" property="dictId" labelProperty="dictName"/>
        </html:select>
        <html:hidden property="dictName" styleId="dictName"/>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.itemCode"/>
        <html:errors property="itemCode"/>
        <html:text property="itemCode" styleId="itemCode" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.itemCodeEx"/>
        <html:errors property="itemCodeEx"/>
        <html:text property="itemCodeEx" styleId="itemCodeEx" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.itemRemark"/>
        <html:errors property="itemRemark"/>
        <html:text property="itemRemark" styleId="itemRemark" styleClass="text medium max-length-512"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictItemForm.sysType"/>
        <html:errors property="sysType"/>
        <html:text property="sysType" styleId="sysType" styleClass="text medium max-length-10"/>

    </li>

    <li class="buttonBar bottom">
        <html:button styleClass="button" property="method.save" onclick="bCancel=false; submitAction()">
            <fmt:message key="button.save"/>
        </html:button>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemDictItem')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemDictItemForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>