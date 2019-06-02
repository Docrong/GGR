<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonMessageOpertypeDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonMessageOpertypeDetail.heading"/></content>

<html:form action="saveTawCommonMessageOpertype" method="post" styleId="tawCommonMessageOpertypeForm" styleClass="required-validate"> 
<ul>

<html:hidden property="id"/>
<html:hidden property="modelname"/>
    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageOpertypeForm.modelid"/>
        <html:errors property="modelid"/>
        
        <html:select property="modelid">
          <html:options collection="modellist" property="modelid" labelProperty="modelname"/>
        </html:select>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageOpertypeForm.operid"/>
        <html:errors property="operid"/>
        <html:text property="operid" styleId="operid" styleClass="text medium required  max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageOpertypeForm.opername"/>
        <html:errors property="opername"/>
        <html:text property="opername" styleId="opername" styleClass="text medium required  max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageOpertypeForm.operremark"/>
        <html:errors property="operremark"/>
        <html:text property="operremark" styleId="operremark" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonMessageOpertype')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonMessageOpertypeForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>