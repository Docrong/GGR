<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
   <html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">

</head>
 <body onLoad="refreshIt()">
<html:form action="/TawInfAddressBook/mailTo" method="POST">
<html:hidden property="userId"/>


  <br>
    <table border="0" width="80%" cellspacing="0" align="center">
      <tr>
	    <td width="100%" align="center" class="table_title">

	        <bean:message key="TawInfAddressBook.select"/>

	    </td>
      </tr>
   </table>
   <table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align="center">

    <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.company"/>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="company" size="40"/>
	   </td>
     </tr>
     <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.deptName"/>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="deptName" size="40"/>
	   </td>
     </tr>
     <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.specialty"/>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="specialty" size="40"/>
	   </td>
     </tr>
     <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.duty"/>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="duty" size="40"/>
	   </td>
     </tr>
     <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.name"/>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="name" size="40"/>
	   </td>
     </tr>
    <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.groupName"/>
	   </td>
	   <td width="70%" height="25">
               <html:select property="groupId">
		<html:optionsCollection name="tawInfAddressBookForm" property="groupTypeCollection"/>
               </html:select>
	   </td>
     </tr>


      <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.mobile"/>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="mobile" size="40"/>
	   </td>
     </tr>

     <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.officeTel"/>
	   </td>
	   <td width="70%" height="25">
               <html:text styleClass="clstext" property="officeTel" size="40"/>
	   </td>

     </tr>
      <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.smart"/>
	   </td>
	   <td width="70%" height="25">
              <html:text styleClass="clstext" property="smart" size="40"/>
	   </td>

     </tr>
      <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.email"/>
	   </td>
	   <td width="70%" height="25">
               <html:text styleClass="clstext" property="email" size="40"/>
	   </td>

     </tr>
      <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.remark"/>
	   </td>
	   <td width="70%" height="25">
               <html:text styleClass="clstext" property="remark" size="40"/>
	   </td>

     </tr>
      <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.recType"/>
	   </td>
	   <td width="70%" height="25">
               <html:select property="recType">
		<html:optionsCollection name="tawInfAddressBookForm" property="recTypeCollection"/>
               </html:select>
	   </td>

     </tr>

   </table>
   <table border="0" width="80%" cellspacing="0" align="center">
    <tr>
	     <td width="100%" colspan="2" height="32" align="right">

	        <input  Class="clsbtn2" type="submit" name="tosubmit" value="<bean:message key="label.query"/>" >&nbsp;&nbsp;
                <input type="button" value="<bean:message key="label.cancel"/>" onclick="toback()" class="clsbtn2"/>
         </td>
    </tr>
   </table>
  </body>
 </html:form>

</html:html>

<script language="javascript">
 function refreshIt(){

     window.document.all.company.value="";
     window.document.all.deptName.value="";
     window.document.all.specialty.value="";
     window.document.all.duty.value="";
     window.document.all.name.value="";
     window.document.all.groupId.value="";
     window.document.all.mobile.value="";
     window.document.all.officeTel.value="";
     window.document.all.smart.value="";
     window.document.all.email.value="";
     window.document.all.remark.value="";

  }
  function toback(){
    window.location.href="config.do";
  }

</script>

