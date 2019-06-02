<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script language="JavaScript" src="../css/Validator.js"></script>
 
<script>
function checkForm(){ 
 
    if(document.getElementById("thisFile").value==null||document.getElementById("thisFile").value==""){
    	alert("${eoms:a2u('请选择上传文件')}");
    	return false;
    }else if(document.getElementById("thisFile").value.indexOf(".xls")<0){
    	alert("${eoms:a2u('确认选择的文件为Excel')}");
    	return false;
    }else{
    return true;
    }
}
</script>
<html:form action="tawWorkbenchContacts.do?method=importExcel"
	method="post" styleId="tawWorkbenchContactGroupForm" enctype="multipart/form-data" onsubmit="return checkForm()">
	
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
<input type="submit" value="${eoms:a2u('导入')}" class="btn"  />

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>


