<%@ include file="/common/taglibs.jsp" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String roleId = "";
    String roleName = "";
%>
<script type="text/javascript">
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="listTable">
    <caption><bean:message bundle="commonfaultpack" key="commonfaultpack.header"/></caption>
    <input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultPackMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain"/>
</table>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:RoleName
			 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test" type="role" roleId="167" flowId="701"
                      category="[{id:'dealPerformer',text:'Send',allowBlank:false,vtext:'Please Select'}]"
        />
</fieldset>
<!-- xbox -->
</fieldset>
