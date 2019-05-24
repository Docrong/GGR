<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawWorkbenchContactGroupForm'});
});
</script>
<html:form action="tawWorkbenchDeptContacts.do?method=importExcel"
	method="post" styleId="tawWorkbenchDeptContactGroupForm" enctype="multipart/form-data">
	
 <li>
   <eoms:label styleClass="desc" key="tawWorkbenchContactGroupForm.groupName"/>	
   <html:select property="groupId">
          <html:options collection="groupList" property="groupId" labelProperty="groupName"/>
   </html:select>
 </li>
<BR> 
<li>
<BR>
${eoms:a2u('导入')}
<BR>
 
<input name="thisFile" type="file" style="width:30%;height:22;border:1px solid #006699" />
</li>
<input type="submit" value="${eoms:a2u('导入')}" class="btn" />

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


