<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<fmt:message key="tawCommonLogOperatorDetail.heading"/>

<html:form action="saveTawCommonLogOperator" method="post" styleId="tawCommonLogOperatorForm" styleClass="required-validate"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.beginnotetime"/>
        <eoms:datePicker bindingto="beginnotetime"/>
        <html:text property="beginnotetime" styleId="beginnotetime" styleClass="text medium required" readonly="true"/>
		
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.bzremark"/>
        <html:text property="bzremark" styleId="bzremark" styleClass="text medium max-length-20"/>
        

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.currentday"/>
        <html:errors property="currentday"/>
        <html:text property="currentday" styleId="currentday" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.currentmonth"/>
        <html:errors property="currentmonth"/>
        <html:text property="currentmonth" styleId="currentmonth" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.issucess"/>
        <html:errors property="issucess"/>
        <html:text property="issucess" styleId="issucess" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.modelid"/>
        <html:errors property="modelid"/>
        <html:text property="modelid" styleId="modelid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.modelname"/>
        <html:errors property="modelname"/>
        <html:text property="modelname" styleId="modelname" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.notemessage"/>
        <html:errors property="notemessage"/>
        <html:text property="notemessage" styleId="notemessage" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.operatetime"/>
        <html:errors property="operatetime"/>
        <html:text property="operatetime" styleId="operatetime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.operid"/>
        <html:errors property="operid"/>
        <html:text property="operid" styleId="operid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.opername"/>
        <html:errors property="opername"/>
        <html:text property="opername" styleId="opername" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.operremark"/>
        <html:errors property="operremark"/>
        <html:text property="operremark" styleId="operremark" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.remoteip"/>
        <html:errors property="remoteip"/>
        <html:text property="remoteip" styleId="remoteip" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.url"/>
        <html:errors property="url"/>
        <html:text property="url" styleId="url" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonLogOperatorForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonLogOperator')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>