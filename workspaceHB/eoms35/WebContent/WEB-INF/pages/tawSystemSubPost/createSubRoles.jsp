<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/formlibs.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>

<%
	String path = request.getContextPath();
	int roleId = StaticMethod.nullObject2int(request.getAttribute("roleId"));
%>

<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("dept","${app}/xtree.do?method=dept&id=-1&t=deptCheckboxTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "deptName";
	tree.hiddField = "deptId";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<title><fmt:message key="tawSystemSubRoleDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemSubRoleDetail.heading"/></content>

<html:form action="saveTawSystemSubRole" method="post" styleId="tawSystemSubRoleForm"> 
<ul>
    <li>
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.deptId"/>
        <html:errors property="deptId"/>
        <html:hidden property="deptId" styleId="deptId" styleClass="text medium"/>
        <input type="text" name="deptName" id="deptId" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>

<input type="hidden" name="roleId" id="roleId" value="<%=roleId%>">

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.createSubRoles" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemSubRoleForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>