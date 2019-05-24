<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawSystemCptroomDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemCptroomDetail.heading"/></content>

<html:form action="saveTawSystemCptroom" method="post" styleId="tawSystemCptroomForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.address"/>
        <html:errors property="address"/>
        <html:text property="address" styleId="address" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.deleted"/>
        <html:errors property="deleted"/>
        <html:text property="deleted" styleId="deleted" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.deptid"/>
        <html:errors property="deptid"/>
        <html:text property="deptid" styleId="deptid" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.endtime"/>
        <html:errors property="endtime"/>
        <html:text property="endtime" styleId="endtime" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.fax"/>
        <html:errors property="fax"/>
        <html:text property="fax" styleId="fax" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.manager"/>
        <html:errors property="manager"/>
        <html:text property="manager" styleId="manager" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.mobile"/>
        <html:errors property="mobile"/>
        <html:text property="mobile" styleId="mobile" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.notes"/>
        <html:errors property="notes"/>
        <html:text property="notes" styleId="notes" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.phone"/>
        <html:errors property="phone"/>
        <html:text property="phone" styleId="phone" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.roomname"/>
        <html:errors property="roomname"/>
        <html:text property="roomname" styleId="roomname" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.tempmanager"/>
        <html:errors property="tempmanager"/>
        <html:text property="tempmanager" styleId="tempmanager" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.leaf"/>
        <html:errors property="leaf"/>
        <html:text property="leaf" styleId="leaf" styleClass="text medium"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemCptroomForm.parentid"/>
        <html:errors property="parentid"/>
        <html:text property="parentid" styleId="parentid" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemCptroom')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemCptroomForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>