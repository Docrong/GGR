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


 <body>


 <br>
    <table border="0" width="80%" cellspacing="0" align="center">
      <tr>
	    <td width="100%" align="center" class="table_title">

	        <bean:message key="label.view"/>&nbsp;<bean:message key="TawInfAddressBook.label"/>

	    </td>
      </tr>
   </table>
   <table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.company"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="company" scope="request"/>
	   </td>
     </tr>
      <tr class="tr_show">
            <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.deptName"/>
	   </td>
       <td width="70%" height="25">
          <bean:write name="tawInfAddressBookForm" property="deptName" scope="request"/>

	   </td>
    </tr>

    <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.specialty"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="specialty" scope="request"/>
	   </td>
     </tr>

     <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.duty"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="duty" scope="request"/>
	   </td>

     </tr>
      <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.name"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="name" scope="request"/>
	   </td>

     </tr>
     <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.groupName"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="groupName" scope="request"/>
	   </td>

     </tr>
     <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.mobile"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="mobile" scope="request"/>
	   </td>

     </tr>
     <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.officeTel"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="officeTel" scope="request"/>
	   </td>
     </tr>
    <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.smart"/>
	   </td>
	   <td  width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="smart" scope="request"/>
	   </td>
     </tr>
     <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.email"/>
	   </td>
	   <td width="70%" height="25">
         <a href='mailto:<bean:write name="tawInfAddressBookForm" property="email" scope="request"/>'><bean:write name="tawInfAddressBookForm" property="email" scope="request"/></a>
	   </td>
     </tr>
      <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.remark"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="remark" scope="request"/>
	   </td>
     </tr>
    <tr class="tr_show">
           <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.recType"/>
	   </td>
	   <td width="70%" height="25">
         <bean:write name="tawInfAddressBookForm" property="recType" scope="request"/>
	   </td>
     </tr>

   </table>
   <table border="0" width="80%" cellspacing="0" align="center">
    <tr>
      <td width="100%" height="32" align="right">
        <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
      </td>

    </tr>
   </table>
  </body>


</html:html>
