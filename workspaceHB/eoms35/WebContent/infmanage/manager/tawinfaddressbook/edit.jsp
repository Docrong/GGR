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

<html:form action="/TawInfAddressBook/editsave" method="POST">
<html:hidden property="id"/>
 <body>
 <br>
    <table border="0" width="80%" cellspacing="0" align="center">
      <tr>
	    <td width="100%" align="center" class="table_title">

	        <bean:message key="label.edit"/>&nbsp;<bean:message key="TawInfAddressBook.label"/>

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
	        &nbsp<bean:message key="TawInfAddressBook.name"/><font color="#FF0000">**</font>
	   </td>
	   <td width="70%" height="25">
                  <html:text styleClass="clstext" property="name" size="40"/>
	   </td>
     </tr>
    <tr class="tr_show">
	    <td width="30%" height="25" class="clsfth" align="center">&nbsp;
	        &nbsp<bean:message key="TawInfAddressBook.groupName"/><font color="#FF0000">**</font>
	   </td>
	   <td width="70%" height="25">
               <html:select property="groupId">
		<html:optionsCollection name="tawInfAddressBookForm" property="groupTypeCollection"/>
               </html:select>&nbsp;&nbsp;&nbsp;&nbsp;
	       <a href='<%=request.getContextPath()%>/infmanage/TawInfAddressBook/addGroup.do?type=edit&id=<bean:write name="tawInfAddressBookForm" property="id" scope="request"/>'>新增组别</a>
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
	        &nbsp<bean:message key="TawInfAddressBook.email"/><font color="#FF0000">**</font>
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
          <input type="hidden" name="id" value="<bean:write name="tawInfAddressBookForm" property="id" scope="request"/>">
	      <input  Class="clsbtn2" type="submit" name="tosubmit" value="<bean:message key="label.save"/>" onClick="toSubmit()">
	      &nbsp;
          <input Class="clsbtn2" type="reset" name="toreset" value="<bean:message key="label.reset"/>">
           &nbsp;
          <input type="button" value="<bean:message key="label.cancel"/>" onclick="toList()" class="clsbtn2"/>
	     </td>

    </tr>
   </table>
  </body>
 </html:form>

</html:html>
<script language="javascript">

  function toSubmit(){

   if(document.all.name.value==""){
          alert("姓名不能空！");
          return false;
   }

   if(document.all.groupId.value==""){
          alert("组别不能空！");
          return false;
   }
   if(document.all.email.value==""){
          alert("电子邮箱不能空！");
          return false;
   }

  window.document.tawInfAddressBookForm.submit();
  }

  function toList(){
    window.location.href="queryDo.do";
  }

</script>
