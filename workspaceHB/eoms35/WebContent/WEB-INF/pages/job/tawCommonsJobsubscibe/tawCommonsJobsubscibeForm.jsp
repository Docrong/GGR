<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawCommonsJobsubscibeDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonsJobsubscibeDetail.heading"/></content>

<html:form action="saveTawCommonsJobsubscibe" method="post" styleId="tawCommonsJobsubscibeForm"> 
<ul>

 <html:hidden property="id"/>
 <html:hidden property="subId"/>
    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsubscibeForm.jobSortId"/>
        <html:errors property="jobSortId"/>
         <html:select property="jobSortId">
            <html:option value=""><fmt:message key="tawCommonsJobsubscibeForm.jobSortId"/></html:option>
            <html:options  collection="jobSortList"  property="value" labelProperty="label" />
          </html:select>
    </li>
    
    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsubscibeForm.jobType"/>
        <html:errors property="jobType"/>
        <html:select property="jobType">
          <html:option value="">
           <fmt:message key="tawCommonsJobsubscibeForm.jobType"/>
         </html:option>
          <html:option value="simple">simple</html:option>
          <html:option value="cron">cron</html:option>
        </html:select>
    </li>
    <li>
        <eoms:label styleClass="desc" key="tawCommonsJobsubscibeForm.jobCycle"/>
        <html:errors property="jobCycle"/>
        <html:text property="jobCycle" styleId="jobCycle" styleClass="text medium"/>
    </li>
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonsJobsubscibe')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonsJobsubscibeForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>