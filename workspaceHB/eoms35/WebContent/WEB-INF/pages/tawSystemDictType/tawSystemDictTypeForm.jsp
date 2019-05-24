<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<div id="checkboxtree" class="checkboxtree" style="left: 400px;">
<strong><fmt:message key="tawSystemDictTypeForm.parentDictId"/></strong>
&nbsp;[&nbsp;<A HREF="#" onclick="javascript:$('checkboxtree').hide();"><fmt:message key="label.hide"/></A>&nbsp;]
<BR>
<script type="text/javascript">
	var tree = new WebFXLoadTree("dept","${app}/xtree.do?method=dict&dictId=-1");
	tree.write();
	tree.checkboxmode = "true";
	tree.showField = "parentDictName";
	tree.hiddField = "parentDictId";
    tree.expand();
</script>
</div>
<script type="text/javascript">
$("checkboxtree").hide();
</script>

<head>
<title><fmt:message key="tawSystemDictTypeDetail.title"/></title>
  
  <script language="JavaScript" type="text/JavaScript">   
  <!--   
    function submitAction() {
        el = document.getElementById("moduleId");

        if (el.options[el.selectedIndex].value=="-1") {
            document.getElementById("moduleName").value = "";
        }
        else {
            document.getElementById("moduleName").value = el.options[el.selectedIndex].text;
        }
       	document.forms[0].submit();
    }
  //-->   
  </script>

<head>
<content tag="heading"><fmt:message key="tawSystemDictTypeDetail.heading"/></content>

<html:form action="saveTawSystemDictType" method="post" styleId="tawSystemDictTypeForm" styleClass="required-validate"> 
<ul>

<html:hidden property="id"/>
<html:hidden property="dictId"/>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.dictCode"/>
        <html:errors property="dictCode"/>
        <html:text property="dictCode" styleId="dictCode" styleClass="text medium required max-length-100"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.dictName"/>
        <html:errors property="dictName"/>
        <html:text property="dictName" styleId="dictName" styleClass="text medium required max-length-100"/>

    </li>
<!--  
    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.parentDictId"/>
        <html:errors property="parentDictId"/>
        <html:select property="parentDictId">
            <html:option value="-1">Select</html:option> 
            <html:options collection="SysDictTypeList" property="dictId" labelProperty="dictName"/>
        </html:select>

    </li>
-->
    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.parentDictId"/>
        <html:errors property="parentDictId"/>
        <html:hidden property="parentDictId" styleId="parentDictId" styleClass="text medium"/>

        <html:text property="parentDictName" styleId="parentDictName" styleClass="text medium required max-length-100"/>
        <input type="button" value="<fmt:message key="label.menutree"/>" onclick="$('checkboxtree').toggle();"/>
    </li>
<!-- 
    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.moduleName"/>
        <html:errors property="moduleName"/>
        <html:select property="moduleId">
            <html:option value="">Select</html:option> 
            <html:options collection="AppList" property="appId" labelProperty="appName"/>
        </html:select>
        <html:hidden property="moduleName" styleId="moduleName"/>
    </li>
-->
    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.sysType"/>
        <html:errors property="sysType"/>
        <html:text property="sysType" styleId="sysType" styleClass="text medium max-length-10"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawSystemDictTypeForm.dictRemark"/>
        <html:errors property="dictRemark"/>
        <html:text property="dictRemark" styleId="dictRemark" styleClass="text medium max-length-480"/>

    </li>

    <li class="buttonBar bottom">
<!-- 
        <html:button styleClass="button" property="method.save" onclick="bCancel=false; submitAction()">
            <fmt:message key="button.save"/>
        </html:button>
-->
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawSystemDictType')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawSystemDictTypeForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>