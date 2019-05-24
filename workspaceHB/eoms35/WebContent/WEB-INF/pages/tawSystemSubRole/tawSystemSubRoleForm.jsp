<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>

<%
	String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
	String flag=StaticMethod.nullObject2String(request.getAttribute("workflowFlag"));
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

<div id="checkboxusertree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxusertree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree2 = new WebFXLoadTree("deptuser","${app}/xtree.do?method=deptuser&id=-1&t=userDeptTemplate");
	tree2.write();
	tree2.checkboxmode = "true";
	tree2.showField = "userName";
	tree2.hiddField = "userId";
    tree2.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxusertree").hide();
</script>

<title><fmt:message key="tawSystemSubRoleDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemSubRoleDetail.heading"/></content>

<html:form action="saveTawSystemSubRole" method="post" styleId="tawSystemSubRoleForm"> 
<ul>
	<li>
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.subRoleName"/>
        <html:errors property="subRoleName"/>
        <html:text property="subRoleName" styleId="subRoleName" styleClass="text medium"/>
    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.deptId"/>
        <html:errors property="deptId"/>
        <html:hidden property="deptId" styleId="deptId" styleClass="text medium"/>
        <input type="text" name="deptName" id="deptId" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>

		<html:hidden property="id"/>
		<input type="hidden" name="roleId" id="roleId" value="<%=roleId%>">
		
	<li>
		<eoms:label styleClass="desc" key="tawSystemSubRoleForm.firstclass"/>
        <html:errors property="type1"/>
        <html:select property="type1">
            <html:option value="1"> </html:option> 
            <html:option value="2">gongjian</html:option>  
        </html:select>
    </li>
    
    <li>
		<eoms:label styleClass="desc" key="tawSystemSubRoleForm.secondclass"/>
        <html:errors property="type2"/>
        <html:select property="type2">
            <html:option value="1"> </html:option> 
            <html:option value="2">gongjian</html:option>  
        </html:select>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.limitCount"/>
        <html:errors property="limitCount"/>
        <html:text property="limitCount" styleId="limitCount" styleClass="text medium"/>

    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.userId"/>
        <html:errors property="userId"/>
        <html:hidden property="userId" styleId="userId" styleClass="text medium"/>
        <input type="text" name="userName" id="userName" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxusertree').toggle();"/>
    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemSubRole')">
            <fmt:message key="button.delete"/>
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