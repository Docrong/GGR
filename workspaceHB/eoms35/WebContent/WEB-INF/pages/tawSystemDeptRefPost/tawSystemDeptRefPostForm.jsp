<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<title><fmt:message key="tawSystemDeptRefPostDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemDeptRefPostDetail.heading"/></content>

<html:form action="saveTawSystemDeptRefPost" method="post" styleId="tawSystemDeptRefPostForm"> 
<ul>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptRefPostForm.deptId"/>
        <html:errors property="deptId"/>
        <html:text property="deptId" styleId="deptId" styleClass="text medium"/>

    </li>

<html:hidden property="id"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptRefPostForm.postId"/>
        <html:errors property="postId"/>
        <html:text property="postId" styleId="postId" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemDeptRefPost')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemDeptRefPostForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>