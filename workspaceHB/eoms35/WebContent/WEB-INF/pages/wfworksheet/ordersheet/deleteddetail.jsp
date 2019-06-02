<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted")); %>
<script type="text/javascript">
 function query() {
 window.open("./ordersheets.do?method=relationSheet&procode=${ordersheetForm.procode}",null,"left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes");
 }
 </script> 
<title><fmt:message key="ordersheetDetail.title"/></title>
<content tag="heading">产品详细信息</content>

<html:form action="ordersheets.do?method=xsave" method="post" styleId="detail">
	<br>
	<br>
   <table class="formTable"> 
   <tr>
     <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.procode"/>
     </td>
     <td class="content">
        <html:errors property="procode"/>
        <bean:write name="ordersheetForm" property="procode" scope="request"/>
     </td>  
     <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.nbPName"/>
     </td>
     <td class="content">
        <html:errors property="nbPName"/>
        <bean:write name="ordersheetForm" property="nbPName" scope="request"/>
     </td> 
   </tr>
   <tr>
     <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.businessDept"/>
     </td>
     <td class="content">
		    <eoms:id2nameDB id="${ordersheetForm.businessDept}" beanId="ItawSystemDictTypeDao"/>
     </td>
     <td class="label">
      
         <bean:message bundle="ordersheet" key="ordersheet.keyword"/>
     </td>
     <td class="content">
         <html:errors property="keyword"/>
         <bean:write name="ordersheetForm" property="keyword" scope="request"/>
         
     </td>
  </tr>
  
   <tr>
      <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.businessSort"/>
     </td>
     <td class="content">
         <html:errors property="businessSort"/>
         <eoms:id2nameDB id="${ordersheetForm.businessSort}" beanId="ItawSystemDictTypeDao" />   
     </td>
     <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.businessSortTwo"/>
     </td>
     <td class="content">
         <html:errors property="businessSort"/>
         <eoms:id2nameDB id="${ordersheetForm.businessSortTwo}" beanId="ItawSystemDictTypeDao" />   
     </td>
  </tr>
  
  
  <tr>
    <td class="label">   
        <bean:message bundle="ordersheet" key="ordersheet.recorder"/>
    </td>
    <td class="content">
        <html:errors property="recorder"/>
        <eoms:id2nameDB id="${ordersheetForm.recorder}" beanId="tawSystemUserDao"/>	
    </td> 
    <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.recordTime"/>
    </td>
    <td class="content">
        <html:errors property="recordTime"/>
         <bean:write name="ordersheetForm" property="recordTime" scope="request"/>
    </td>
  </tr>
  <tr>
    <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.recorderDept"/>
    </td>
    <td class="content">
         <html:errors property="recorderDept"/>
         <bean:write name="ordersheetForm" property="recorderDept" scope="request"/>
    </td>  
    <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.recorderContact"/>
    </td>
    <td class="content">
         <html:errors property="recorderContact"/>
         <bean:write name="ordersheetForm" property="recorderContact" scope="request"/>
    </td>
  
  <tr>
    <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.nbPdescription"/>
    </td>
    <td class="content" colspan="3">
         <html:errors property="nbPdescription"/>
         <bean:write name="ordersheetForm" property="nbPdescription" scope="request"/>
    </td>
  </tr>   
  <tr>
    <td class="label">
        <bean:message bundle="ordersheet" key="ordersheet.remarks"/>
    </td>
    <td class="content" colspan="3">
        <html:errors property="remarks"/>
         <bean:write name="ordersheetForm" property="remarks" scope="request"/>
    </td>
   </tr> 
 	<tr>
	  <td class="label"><bean:message bundle="ordersheet" key="ordersheet.node"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="ordersheetForm" property="node" 
		            scope="request" idField="node" appCode="ordersheet" 
		             viewFlag="Y"/> 
		    </td>
		</tr>
</table>
<table>
<tr>
<td>
<input type="button"  value="相关工单" onclick="query();">
<logic:empty name="type">
     <input type="button" style="margin-right: 5px"
         onclick="location.href='<c:url value="/businessupport/order/ordersheets.do?method=xqueryDeleted&ifReference=${ifReference}"/>'" 
        value="返回"/>
</logic:empty>
</tr>
</table>
</html:form>

<!-- footer_eoms.jsp end-->