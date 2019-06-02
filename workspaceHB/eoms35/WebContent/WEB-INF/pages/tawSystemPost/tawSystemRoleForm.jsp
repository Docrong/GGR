<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%
	String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
	String flag=StaticMethod.nullObject2String(request.getAttribute("workflowFlag"));
	String path = request.getContextPath();

	int roleId = 1;
%>

<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("deptuser","${app}/xtree.do?method=deptuser&id=-1&t=userDeptTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "userIdArray";
	tree.hiddField = "sort1_userid";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<title><fmt:message key="tawSystemRoleDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemRoleDetail.heading"/></content>

<html:form action="saveTawSystemRole" method="post" styleId="tawSystemRoleForm"> 
<ul>
	<html:hidden property="roleId"/>
	
	<input type="hidden" name="parentId" id="parentId" value="<%=roleId%>">
	
    <li>
        <eoms:label styleClass="desc" key="tawSystemRoleForm.roleName"/>
        <html:errors property="roleName"/>
        <html:text property="roleName" styleId="roleName" styleClass="text medium"/>

    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemRoleForm.roleTypeId"/>
        <html:errors property="roleTypeId"/>
        <html:select property="roleTypeId">
            <html:option value="1">role</html:option> 
            <html:option value="2">operater</html:option>  
        </html:select>
    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemroleForm.roleTypeId"/>
        <html:errors property="workflowFlag"/>
        <html:select property="workflowFlag">
            <html:option value="1">faultsheet</html:option> 
            <html:option value="2">gongjian</html:option>  
        </html:select>
    </li>
    

    <li class="buttonBar bottom">             
        
        <html:submit styleClass="button" property="method.addNew" onclick="bCancel=false">
            <fmt:message key="tawSystemRoleForm.addRole"/>
        </html:submit>
        
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemRole')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
        
        <html:submit styleClass="button" property="method.subRoleList" onclick="bCancel=false">
            <fmt:message key="tawSystemRoleForm.subRoleList"/>
        </html:submit>

    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemRoleForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>