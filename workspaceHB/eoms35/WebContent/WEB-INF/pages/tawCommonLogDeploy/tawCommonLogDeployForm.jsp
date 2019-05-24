<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<script type="text/javascript" src="${app}/scripts/form/fieldctrl.js"></script>
<script type="text/javascript">
fieldCtrlConfig ={
	handler:"issendimediat",
	fields:["issendnight"],
	rules:[
		{value:"110301",dis:["issendnight"]}
	]
};
</script>
<title><fmt:message key="tawCommonLogDeployDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonLogDeployDetail.heading"/></content>

<html:form action="saveTawCommonLogDeploy" method="post" styleId="tawCommonLogDeployForm"> 
<c:set var="f">tawCommonLogDeployForm</c:set>
<ul>

<html:hidden property="id"/>

      <li>
        <eoms:label styleClass="desc" key="tawCommonLogDeployForm.userid"/>
        <html:errors property="userid"/>
        <html:text property="userid" styleId="userid" styleClass="text medium"/>

    </li>
     <li>
         <eoms:label styleClass="desc" key="tawCommonLogDeployForm.modelid"/>
        <html:errors property="modelid"/>
        <html:text property="modelid" styleId="modelid" styleClass="text medium"/>
       <%-- <eoms:comboBox name="modelid" id="modelid" sub="modelname" initDicId="1"/>--%>

    </li>
     <li>
      <eoms:label styleClass="desc" key="tawCommonLogDeployForm.modelname"/>
        <html:errors property="modelname"/>
        <html:text property="modelname" styleId="modelname" styleClass="text medium"/>

    </li>
    <li>
     <eoms:label styleClass="desc" key="tawCommonLogDeployForm.operid"/>
        <html:errors property="operid"/>
        <html:text property="operid" styleId="operid" styleClass="text medium"/>

    </li>
      <li>
       <eoms:label styleClass="desc" key="tawCommonLogDeployForm.opername"/>
        <html:errors property="opername"/>
        <html:text property="opername" styleId="opername" styleClass="text medium"/>

    </li>
     <li>
         <eoms:label styleClass="desc" key="tawCommonLogDeployForm.operdesc"/>
        <html:errors property="operdesc"/>
        <html:text property="operdesc" styleId="operdesc" styleClass="text medium"/>

    </li>
    
     <li>
        <eoms:label styleClass="desc" key="tawCommonLogDeployForm.savetype"/>
        <html:errors property="savetype"/>
         <html:select property="savetype">
         <html:option value="0">DB</html:option> 
         <html:option value="1">FILE</html:option> 
        </html:select>
       <%--<eoms:comboBox name="modelid" id="modelid" sub="modelname" initDicId="2202"/>--%>

    </li>
     <li>
        <eoms:label styleClass="desc" key="tawCommonLogDeployForm.filesavepath"/>
        <html:errors property="filesavepath"/>
        <html:text property="filesavepath" styleId="filesavepath" styleClass="text medium"/>

    </li>
     <li>
        <eoms:label styleClass="desc" key="tawCommonLogDeployForm.filecutsize"/>
        <html:errors property="filecutsize"/>
        <html:text property="filecutsize" styleId="filecutsize" styleClass="text medium"/>

    </li>
 
    <li>
         <eoms:label styleClass="desc" key="tawCommonLogDeployForm.noteremark"/>
        <html:errors property="noteremark"/>
        <html:text property="noteremark" styleId="noteremark" styleClass="text medium"/>

    </li>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonLogDeploy')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonLogDeployForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>