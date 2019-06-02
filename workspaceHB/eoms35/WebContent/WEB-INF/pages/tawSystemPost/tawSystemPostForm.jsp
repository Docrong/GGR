<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/formlibs.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>
<%@ include file="/common/xtreelibs.jsp"%>

<%
	String regionId = StaticMethod.nullObject2String(request.getAttribute("REGIONID"),"1");
	String flag=StaticMethod.nullObject2String(request.getAttribute("workflowFlag"));
	String path = request.getContextPath();

	int postId = 1;
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

<title><fmt:message key="tawSystemPostDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemPostDetail.heading"/></content>

<html:form action="saveTawSystemPost" method="post" styleId="tawSystemPostForm"> 
<ul>
	<html:hidden property="postId"/>
	
	<input type="hidden" name="parentId" id="parentId" value="<%=postId%>">
	
    <li>
        <eoms:label styleClass="desc" key="tawSystemPostForm.postName"/>
        <html:errors property="postName"/>
        <html:text property="postName" styleId="postName" styleClass="text medium"/>
    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemSubRoleForm.deptId"/>
        <html:errors property="deptId"/>
        <html:hidden property="deptId" styleId="deptId" styleClass="text medium"/>
        <input type="text" name="deptName" id="deptId" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>
    
    <li>
    	<eoms:label styleClass="desc" key="tawSystemPostForm.postUsers"/>        
    	<input type="text" id="sort1_userid" name="userIdArray"/>
    	<input type="button" value="<fmt:message key="button.select"/>" onclick="$('checkboxtree').toggle();"/>
    </li>

    <li class="buttonBar bottom">        
        
        <html:submit styleClass="button" property="method.addNew" onclick="bCancel=false">
            <fmt:message key="tawSystemPostForm.addPost"/>
        </html:submit>
        
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemPost')">
            <fmt:message key="button.delete"/>
        </html:submit>
        
        <html:submit styleClass="button" property="method.addRole" onclick="bCancel=false">
            <fmt:message key="tawSystemRoleForm.addRole"/>
        </html:submit>

    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemPostForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>