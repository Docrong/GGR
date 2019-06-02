<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title>
  <fmt:message key="tawCommonsJobsortDetail.title"/>
</title>
<content tag="heading">
  <fmt:message key="tawCommonsJobsortDetail.heading"/>
</content>
<!-- 表单验证需要的文件 -->
  <script type="text/javascript" src="${app}/scripts/form/validation.js"></script>
   <script type="text/javascript" src="${app}/scripts/form/Options.js"></script>
  <script type="text/javascript" src="${app}/scripts/form/combobox.js"></script>
    <script type="text/javascript" src="${app}/scripts/ext/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="${app}/scripts/ext/ext-all.js"></script>
  <script type="text/javascript" src="${app}/scripts/adapter/ext-ext.js"></script>
  <script type="text/javascript" src="${app}/scripts/ext/source/locale/ext-lang-zh_CN.js"></script>
 <!-- 8888 --> 
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawCommonsJobsortForm'});
});

   function confirms(form){
     var msg="<fmt:message key="tawCommonsJobsortDetail.deleted"/>";
     ans=confirm(msg);
     if (ans) {
        return true;
    } else {
        return false;
    }
   }
</script>
<html:form action="saveTawCommonsJobsort" method="post" styleId="tawCommonsJobsortForm"> 
 <ul>
   <html:hidden property="id"/>
   <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsortForm.jobSortName"/>
        <html:errors property="jobSortName"/>
        <html:text property="jobSortName" styleId="jobSortName" alt="allowBlank:false,vtext:''" styleClass="text medium"/><font color="red">${exist }</font>
   </li>
   <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsortForm.jobClassName"/>
        <html:errors property="jobClassName"/>
        <html:text property="jobClassName" styleId="jobClassName"  alt="allowBlank:false,vtext:''" styleClass="text medium"/>
   </li>
   <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsortForm.maxExecuteTime"/>
        <html:errors property="maxExecuteTime"/>
        <html:text property="maxExecuteTime" styleId="maxExecuteTime" styleClass="text medium"/>
   </li>
   <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsortForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>
   </li>
  
   <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirms('TawCommonsJobsort')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonsJobsortForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>