<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'evaTemplateForm'});
try{
	// 部门树
	var	deptTreeAction='${app}/xtree.do?method=dept';
	deptViewer = new Ext.JsonView("dept-list",
		'<div id="dept-{id}" class="viewlistitem-{nodeType}">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div>未选择接收单位</div>'
		}
	);
	var deptStr = '${requestScope.orgIds}';
	deptViewer.jsonData = eoms.JSONDecode(deptStr);
	deptViewer.refresh();
	
	deptTree = new xbox({
		btnId:'deptTreeBtn',dlgId:'dlg-dept',
		treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'部门',treeChkMode:'',treeChkType:'dept',
		viewer:deptViewer,saveChkFldId:'recieverOrgId', returnJSON:'true'
	});
	
	// 人员树
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",
		'<div id="user-{id}" class="viewlistitem-{nodeType}">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div>未选择接收人</div>'
		}
	);
	var userStr = '${requestScope.orgIds}';
	userViewer.jsonData = eoms.JSONDecode(userStr);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'recieverOrgId', returnJSON:'true'
	});
} catch(e){}
})
</script>

<html:form action="/evaTemplates.do?method=distribute" styleId="evaTemplateForm" method="post"> 
<table class="formTable" id="form">
	<caption>
		<div class="header center">下发考核模板</div>
	</caption>
	<tr>
		<td class="label">
			模板名称
		</td>
		<td class="content" colspan="3">
			${evaTemplateForm.templateName}
		</td>
	</tr>
	<tr>
		<td class="label">
			考核周期
		</td>
		<td class="content">
			<eoms:dict key="dict-eva" dictId="cycle" itemId="${evaTemplateForm.cycle}" beanId="id2nameXML" />
		</td>
		<td class="label">
			接收单位类型
		</td>
		<td class="content">
			<eoms:dict key="dict-eva" dictId="orgType" itemId="${evaTemplateForm.orgType}" beanId="id2nameXML" />
		</td>
	</tr>
	<tr>
		<td class="label">
			起始时间
		</td>
		<td class="content">
			${evaTemplateForm.startTime}
		</td>
		<td class="label">
			结束时间
		</td>
		<td class="content">
			${evaTemplateForm.endTime}
		</td>
	</tr>
	<tr>
		<td class="label">
			备注
		</td>
		<td class="content" colspan="3">
			${evaTemplateForm.remark}
		</td>
	</tr>
	<tr id="deptTr" style="display:none">
		<td class="label">
			接收单位
		</td>
		<td class="content" colspan="3">
			<div id="dept-list" class="viewer-box"></div>
			<input type="button" value="选择接收单位" id="deptTreeBtn" class="btn"/>
			<html:hidden property="recieverOrgId" />
		</td>
	</tr>
	<tr id="userTr" style="display:none">
		<td class="label">
			接收人
		</td>
		<td class="content" colspan="3">
			<div id="user-list" class="viewer-list"></div>
			<input type="button" value="选择接收人" id="userTreeBtn" class="btn"/>
			<html:hidden property="recieverOrgId" />
		</td>
	</tr>
	<tr height="30">
		<td colspan="4">
			<input type="submit" class="btn" value="下发模板" />
		</td>
	</tr>
</table>

<html:hidden property="id" value="${evaTemplateForm.id}" />
</html:form>
<c:if test="${evaTemplateForm.id != null}">
<iframe id="fileList" name="fileList" frameborder="0" width="100%" height="100%" 
	src="${app}/eva/evaTemplateKpis.do?method=listKpiOfTemplate&templateId=${evaTemplateForm.id}">
</iframe>
</c:if>
<script type="text/javascript">
  try{
	//刷新父框架中的整颗树
	parent.AppFrameTree.refreshTree();
  }catch(e){}
</script>
<c:if test="${evaTemplateForm.orgType == 'dept'}">
<script type="text/javascript">
document.getElementById('deptTr').style.display='block';
</script>
</c:if>
<c:if test="${evaTemplateForm.orgType == 'user'}">
<script type="text/javascript">
document.getElementById('userTr').style.display='block';
</script>
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>