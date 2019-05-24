<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.base.util.StaticMethod"%>
<%@ include file="/common/xtreelibs.jsp"%>


<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("deptuser","${app}/xtree.do?method=deptuser&id=-1&t=userDeptTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "toUserName";
	tree.hiddField = "toUserId";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<title><fmt:message key="tawSystemOrganizationProxyDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemOrganizationProxyDetail.heading"/></content>

<html:form action="saveTawSystemOrganizationProxy" method="post" styleId="tawSystemOrganizationProxyForm"> 
<ul>

<html:hidden property="id"/>

	<li>
    	<eoms:label styleClass="desc" key="tawSystemOrganizationProxyForm.proxyUser"/>        
    	<html:errors property="toUserId"/>
        <html:hidden property="toUserId" styleId="toUserId" styleClass="text medium"/>
        <input type="text" name="toUserName" id="toUserName" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemOrganizationProxyForm.proxyPostId"/>
        <html:errors property="proxyPostId"/>        
        <html:select property="proxyPostId">
            <html:options collection="roleList" property="id" labelProperty="subPostName"/>
        </html:select>
    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemOrganizationProxy')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemOrganizationProxyForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>