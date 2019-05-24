<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
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

<title><fmt:message key="tawCommonMessageAddServiceDetail.title"/></title>
<content tag="heading"><fmt:message key="tawCommonMessageAddServiceDetail.heading"/></content>

<html:form action="saveTawCommonMessageAddService" method="post" styleId="tawCommonMessageAddServiceForm" >
<c:set var="f">tawCommonMessageAddServiceForm</c:set>
<ul>
<html:hidden property="modelid"/>
<html:hidden property="modelname"/>
<html:hidden property="operid"/>
<html:hidden property="opername"/>
    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageAddServiceForm.modeloperid"/>
        <html:errors property="modeloperid"/>
      <html:select property="modeloperid">
          <html:options collection="opertypelist" property="id" labelProperty="opername"/>
        </html:select>
    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageAddServiceForm.issendimediat"/>
        <html:errors property="issendimediat"/>    
         <eoms:comboBox name="issendimediat" id="issendimediat" initDicId="10301" form="${f}"/>
       
    </li>  

    <li id="li-issendnight">
        <eoms:label styleClass="desc" key="tawCommonMessageAddServiceForm.issendnight"/>
        <html:errors property="issendnight"/>
        <eoms:comboBox name="issendnight" id="issendnight" initDicId="10301" form="${f}"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageAddServiceForm.messagetype"/>
        <html:errors property="messagetype"/>
      <!--    <eoms:comboBox name="messagetype" id="messagetype" initDicId="10306" form="${f}"/>-->
        <html:select property="messagetype">
         <html:option value="0">MOBILE</html:option> 
         <html:option value="1">EMAIL</html:option> 
         <html:option value="2">SOUND</html:option> 
        </html:select>
    </li>


    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageAddServiceForm.urgency"/>
        <html:errors property="urgency"/>

         <eoms:comboBox name="urgency" id="urgency" initDicId="10302" form="${f}"/>

    </li>

    <li>
        <eoms:label styleClass="desc" key="tawCommonMessageAddServiceForm.remark"/>
        <html:errors property="remark"/>
        <html:text property="remark" styleId="remark" styleClass="text medium"/>

    </li>

  

<html:hidden property="id"/>

    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.delete" onclick="bCancel=true; return confirmDelete('TawCommonMessageAddService')">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>
</ul>
</html:form>

<script type="text/javascript">
    Form.focusFirstElement($("tawCommonMessageAddServiceForm"));
</script>

<%@ include file="/common/footer_eoms.jsp"%>