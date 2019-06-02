<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<script type="text/javascript">
	var v;
   Ext.onReady(function(){
   
     v = new eoms.form.Validation({form:'sheetLimitForm'});
   });
 
 </script> 
   
<fmt:setBundle basename="config/ApplicationResources-sheet-sheetLimit" var="sheetLimit"/>

<title><fmt:message bundle="${sheetLimit}" key="sheetLimit.title"/></title>
<content tag="heading"><fmt:message bundle="${sheetLimit}" key="sheetLimit.title"/></content>
<html:form action="sheetLimit.do" method="post" styleId="sheetLimitForm"> 
<ul>
<html:hidden property="id"/>
  
   <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty2"/>
        <html:errors property="specialty2"/>
        <eoms:comboBox name="specialty2" id="${sheetLimitForm.specialty2}" defaultValue="${sheetLimitForm.specialty2}" initDicId="1010304" 
	  	      size="1" alt="allowBlank:false" styleClass="select-class"/>
   </li>

   <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.acceptLimit"/>
        <html:errors property="acceptLimit"/>
        <html:text property="acceptLimit" styleId="acceptLimit" styleClass="text medium" alt="allowBlank:false"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.replyLimit"/>
        <html:errors property="replyLimit"/>
        <html:text property="replyLimit" styleId="replyLimit"  styleClass="text medium" alt="allowBlank:false"/>
   </li>
  
    
    <li class="buttonBar bottom">
        <html:submit styleClass="button" property="method.saveDealLimit" onclick="bCancel=false">
            <fmt:message key="button.save"/>
        </html:submit>

        <html:submit styleClass="button" property="method.deleteDealLimit" onclick="bCancel=false">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:cancel styleClass="button" onclick="bCancel=true">
            <fmt:message key="button.cancel"/>
        </html:cancel>
    </li>

  </ul>

 </html:form>


<%@ include file="/common/footer_eoms.jsp"%>