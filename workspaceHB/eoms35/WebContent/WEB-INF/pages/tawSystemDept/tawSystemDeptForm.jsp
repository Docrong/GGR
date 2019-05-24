<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<div id="checkboxtree">
<strong><fmt:message key="label.deptTree"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("dept","${app}/xtree.do?method=dept&id=-1&t=deptCheckboxTemplate");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "parentDeptName";
	tree.hiddField = "parentDeptid";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<title><fmt:message key="tawSystemDeptDetail.title"/></title>
<content tag="heading"><fmt:message key="tawSystemDeptDetail.heading"/></content>

<html:form action="saveTawSystemDept" method="post" styleId="tawSystemDeptForm" styleClass="required-validate"> 
<c:set var="f">tawSystemDeptForm</c:set>
<ul>
<html:hidden property="id"/>
<html:hidden property="deptId"/>
<html:hidden property="opertime"/> 
   
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.operuserid"/>
        <html:errors property="operuserid"/>
        <html:text property="operuserid" styleId="operuserid" styleClass="text medium"/>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptName"/>
        <html:errors property="deptName"/>
        <html:text property="deptName" styleId="deptName" styleClass="text medium required max-length-100 " />

    </li>
     <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptmanager"/>
        <html:errors property="deptmanager"/>
         <html:select property="deptmanager" styleId="deptmanager">
            <html:options collection="userlist" property="username" labelProperty="username"/>
        </html:select>

    </li>
     <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.tmporaryManager"/>
        <html:errors property="tmporaryManager"/>
        <html:select property="tmporaryManager" styleId="tmporaryManager">
            <html:options collection="userlist" property="username" labelProperty="username"/>
        </html:select>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.tmporarybegintime"/>
        <html:errors property="tmporarybegintime"/>
        <html:text property="tmporarybegintime" styleId="tmporarybegintime" styleClass="text medium max-length-50"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.tmporarystopTime"/>
        <html:errors property="tmporarystopTime"/>
        <html:text property="tmporarystopTime" styleId="tmporarystopTime" styleClass="text medium max-length-50"/>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptemail"/>
        <html:errors property="deptemail"/>
        <html:text property="deptemail" styleId="deptemail" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptfax"/>
        <html:errors property="deptfax"/>
        <html:text property="deptfax" styleId="deptfax" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptmobile"/>
        <html:errors property="deptmobile"/>
        <html:text property="deptmobile" styleId="deptmobile" styleClass="text medium max-length-100"/>

    </li>

   

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptphone"/>
        <html:errors property="deptphone"/>
        <html:text property="deptphone" styleId="deptphone" styleClass="text medium max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.deptType"/>
        <html:errors property="deptType"/>
         <eoms:comboBox name="deptType" id="deptType" initDicId="4401" form="${f}"/>
    </li>


   <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.parentDeptid"/>
        <html:errors property="parentDeptid"/>
       <html:hidden property="parentDeptid" styleId="parentDeptid" styleClass="text medium"/>
        <input type="text" name="parentDeptName" id="parentDeptName" class="text medium"/>
        <input type="button" value="<fmt:message key="label.deptTree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>


    <li>
        <eoms:label styleClass="desc" key="tawSystemPostForm.postName"/>
        <html:errors property="postId"/>
        <html:select property="postId" styleId="postId">
            <html:options collection="postlist" property="postName" labelProperty="postName"/>
        </html:select>

    </li>
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.regionflag"/>
        <html:errors property="regionflag"/>
        <eoms:comboBox name="regionflag" id="regionflag" initDicId="4402" form="${f}"/>

    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.regionflag"/>
        <html:errors property="linkId"/>
        <eoms:comboBox name="linkId" id="linkId"/>

    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawSystemDeptForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemDept')">
            <fmt:message key="button.delete"/>
        </html:submit>
       <html:submit styleClass="button" property="method.deletes" onclick="bCancel=true; return confirmDelete('TawSystemDept')">
            <fmt:message key="button.deletes"/>
        </html:submit>
        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemDeptForm"));
    
</script>

<%@ include file="/common/footer_eoms.jsp"%>