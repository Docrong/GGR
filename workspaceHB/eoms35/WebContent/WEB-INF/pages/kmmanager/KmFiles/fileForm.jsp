<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'fileForm'});
});
</script>

<form enctype="multipart/form-data" name="fileForm" id="fileForm" method="post"
			action="${app}/kmmanager/files.do?method=upload" styleId="fileForm"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmFile.title"/></div>
	</caption>

    <tr>
      <td class="label">
        <fmt:message key="kmFile.nodeId" />:
      </td>
      <td class="content">
       <eoms:id2nameDB id="${fileForm.nodeId}" beanId="kmFileTreeDao" />
       <input type="hidden" name="nodeId" value="${fileForm.nodeId}">      
      </td>
    </tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmFile.userId" />
		</td>
		<td class="content">
		    <eoms:id2nameDB id="${fileForm.userId}" beanId="tawSystemUserDao" />
		    <input type="hidden" name="userId" value="${fileForm.userId}" />
		</td>
	</tr>

	<tr>
		<td  class="label">
			<fmt:message key="kmFile.deptId" />
		</td>
		<td class="content">
		    <eoms:id2nameDB id="${fileForm.deptId}" beanId="tawSystemDeptDao" />
		    <input type="hidden" name="deptId" value="${fileForm.deptId}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.phone" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="phone" styleId="phone" size="50"
						alt="allowBlank:false,maxLength:32,vtext:'请输入联系电话，不超过32个字符...'" value="${fileForm.phone}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.fileGrade" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="fileGrade" selectId="fileGrade" beanId="selectXML" alt="allowBlank:false,vtext:'请选择文件等级...'" defaultId="${fileForm.fileGrade}"/>
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.keywords" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="keywords" styleId="keywords" size="50"
						alt="allowBlank:false,maxLength:128,vtext:'请输入关键字，不超过128个字符...'" value="${fileForm.keywords}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.fileAbstract" />
		</td>
		<td class="content">
			<html:textarea property="fileAbstract" styleId="fileAbstract" cols="37" rows="8"
						alt="maxLength:1000,vtext:''" value="${fileForm.fileAbstract}" />
		</td>
	</tr>
	
    <c:if test="${fileForm.fileName != null}">
	<tr>
      <td class="label">
      	<fmt:message key="kmFile.download" />
      </td>
      <td class="content">
        <a href="${app}/kmmanager/files.do?method=download&id=${fileForm.id}&nodeId=${fileForm.nodeId}" style="text-decoration:none">${fileForm.fileName}</a>
      </td>
    </tr>
    </c:if>

	<tr>
		<td class="label">
			<fmt:message key="kmFile.upload" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<input name="file" type="file" <logic:equal name="fileForm" property="id" value="">alt="allowBlank:false,vtext:'请选择上传的文件...'"</logic:equal> style="width:50%;height:22;border:1px solid #006699" />
		</td>
	</tr>

	<html:hidden property="id" value="${fileForm.id}" />	
	<input type="hidden" name="isDeleted" value="${fileForm.isDeleted}">
	<html:hidden property="version" value="${fileForm.version}" />
</table>

</fmt:bundle>

<br>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>

<br>
<c:if test="${fileForm.fileName != null}">
    <font color='red'>注意：新上传的文件会替换掉原来的文件！</font>
</c:if>

</form>

<%@ include file="/common/footer_eoms.jsp"%>