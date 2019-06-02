<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<html:form action="tawWorkbenchContacts.do?method=exportExcel"
	method="post" styleId="tawWorkbenchContactGroupForm">
	
 <li>
   <eoms:label styleClass="desc" key="tawWorkbenchContactGroupForm.groupName"/>	
   <html:select property="groupId">
   		 
          <html:options collection="groupList" property="groupId" labelProperty="groupName"/>
   </html:select>
 </li>
<BR> 
<input type="submit" value="${eoms:a2u('导出')}" class="btn" />

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


