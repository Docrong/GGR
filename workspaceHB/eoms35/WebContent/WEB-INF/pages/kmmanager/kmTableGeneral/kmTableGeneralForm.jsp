<jsp:directive.page import="com.boco.eoms.km.table.util.KmTableGeneralConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" %>

<html:form action="/kmTableGenerals.do?method=save" styleId="kmTableGeneralForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<script type="text/javascript">
var tree;
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmTableGeneralForm'});

	var config = {
		btnId:'themeName',
		treeDataUrl:'${app}/kmmanager/kmTableThemes.do?method=getNodesRadioTreeFirst',
		treeRootId:'<%=KmTableGeneralConstants.TREE_ROOT_ID%>',
		treeRootText:'<fmt:message key="kmTableGeneral.themeId" />',
		treeChkMode:'single',
		treeChkType:'forums',
		showChkFldId:'themeName',
		saveChkFldId:'themeId'
	}
	tree = new xbox(config);	
});

</script>

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmTableGeneral.form.heading"/></div>
	</caption>

	<!--  tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.id" />
		</td>
		<td class="content">
			<html:text property="id" styleId="id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableGeneralForm.id}" />
		</td>
	</tr-->

	<tr>		
			<td class="label">
			<fmt:message key="kmTableGeneral.tableChname" />
		</td>
		<td class="content">
			<html:text property="tableChname" styleId="tableChname"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请填写表中文名'" value="${kmTableGeneralForm.tableChname}" />
			<input type="hidden" id="tableName"   name="tableName" value="${kmTableGeneralForm.tableName}" />
		</td>
		<td class="label">
			<fmt:message key="kmTableGeneral.themeId" />
		</td>
		<td class="content">
		    <c:if test="${kmTableGeneralForm.isCreated != 1}">
		        <input type="text" id="themeName" name="themeName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${kmTableGeneralForm.themeId}" beanId="kmTableThemeDao" />' alt="allowBlank:false'"/>			
			</c:if>
			<c:if test="${kmTableGeneralForm.isCreated == 1}">
			    <eoms:id2nameDB id="${kmTableGeneralForm.themeId}" beanId="kmTableThemeDao" />
			    <input type="hidden" id="themeName" name="themeName" value="<eoms:id2nameDB id="${kmTableGeneralForm.themeId}" beanId="kmTableThemeDao" />" />
			</c:if>
			    <input type="hidden" id="themeId" name="themeId" value="${kmTableGeneralForm.themeId}" />
		</td>
	</tr>

<!--
	<tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.createUser" />
		</td>
		<td class="content">
			<html:text property="createUser" styleId="createUser"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableGeneralForm.createUser}" />
		</td>
			<td class="label">
			<fmt:message key="kmTableGeneral.createDept" />
		</td>
		<td class="content">
			<html:text property="createDept" styleId="createDept"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableGeneralForm.createDept}" />
		</td>
	</tr>
-->

<!--
	<tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.createTime" />
		</td>
		<td class="content">
			<html:text property="createTime" styleId="createTime"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmTableGeneralForm.createTime}" />
		</td>
	</tr>
-->

	<tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.orderBy" />
		</td>
		<td class="content">
			<html:text property="orderBy" styleId="orderBy"
						styleClass="text medium" alt="vtype:'number',allowBlank:false"
						 value="${kmTableGeneralForm.orderBy}" />
		</td>
			<td class="label">
			<fmt:message key="kmTableGeneral.isOpen" />
		</td>
		<td class="content">
		<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false" alt="allowBlank:false,vtext:'请选择是否开放(字典)...'"
				defaultId="${kmTableGeneralForm.isOpen}" selectId="isOpen" beanId="selectXML" />
		</td>
	</tr>

<!--
	<tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.isVisibl" />
		</td>
		<td class="content">	
			<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false"
				defaultId="${kmTableGeneralForm.isVisibl}" selectId="isVisibl" beanId="selectXML" />		
			
		</td>
			<td class="label">
			<fmt:message key="kmTableGeneral.isAudit" />
		</td>
		<td class="content">
		<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false"
				defaultId="${kmTableGeneralForm.isAudit}" selectId="isAudit" beanId="selectXML" />	
		</td>
	</tr>
-->
    <input type="hidden" name="isVisibl" value="1" />
    <input type="hidden" name="isAudit" value="0" />

<!--
	<tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.isDeleted" />
		</td>
		<td class="content">		
			<eoms:comboBox name="isDeleted" id="isDeleted" initDicId="10301" defaultValue="1030102" form="kmTableGeneralForm"/>
		</td>
		
	</tr>
-->	
    <input type="hidden" name="isDeleted" value="0" />
    

	<tr>
		<td class="label">
			<fmt:message key="kmTableGeneral.remark" />
		</td>
		<td class="content" colspan="3">
		  <textarea name="remark" cols="50" id="remark" class="textarea max" alt="allowBlank:false" onkeyup="this.value=this.value.slice(0, 180)">${kmTableGeneralForm.remark}</textarea>
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="button" class="btn" value="<fmt:message key="button.next"/>" 
			        onclick="javascript:next()"/>			      
			<c:if test="${not empty kmTableGeneralForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('<fmt:message key="message.delMessage"/>')){
						var url='${app}/kmmanager/kmTableGenerals.do?method=remove&id=${kmTableGeneralForm.id}';
						location.href=url}"	/>				
			</c:if>
		</td>
	</tr>
</table>

<html:hidden property="id" value="${kmTableGeneralForm.id}" />
<html:hidden property="isCreated" value="${kmTableGeneralForm.isCreated}" />

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>

<script>
 function next(){  
   document.forms[0].action="${app}/kmmanager/kmTableGenerals.do?method=tree";
   //document.forms[0].submit();
    var themeId = document.forms[0].themeId.value;
    if(v.check()){
      if (themeId.length <= 0) {
         alert("请填写知识分类");
         return false;
       }
    document.forms[0].submit()
    }    
   //return true;
 }
</script>
