<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'evaTemplateForm'});
	// 部门树
	var	deptTreeAction='${app}/xtree.do?method=dept';
	deptViewer = new Ext.JsonView("dept-list",
		'<div id="dept-{id}" class="viewlistitem-{nodeType}">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div>未选择接收单位</div>'
		}
	);
	var deptStr = '[]';
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
	var userStr = '[]';
	userViewer.jsonData = eoms.JSONDecode(userStr);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'recieverOrgId', returnJSON:'true'
	});
})
</script>

<html:form action="/evaTemplates.do?method=search" styleId="evaTemplateForm" method="post"> 
<table class="formTable" id="form">
	<caption>
		<div class="header center">考核任务查询</div>
	</caption>
	<tr>
		<td class="label">
			接收单位类型
		</td>
		<td class="content" colspan="3">
			<eoms:dict key="dict-eva" dictId="orgType" defaultId="dept"
				 selectId="orgType" beanId="selectXML" onchange="changeOrg(this.value);" />
		</td>
	</tr>
	<tr id="deptTr" style="display:block">
		<td class="label">
			接收单位
		</td>
		<td class="content" colspan="3">
			<div id="dept-list" class="viewer-box"></div>
			<input type="button" value="选择接收单位" id="deptTreeBtn" class="btn"/>
		</td>
	</tr>
	<tr id="userTr" style="display:none">
		<td class="label">
			接收人
		</td>
		<td class="content" colspan="3">
			<div id="user-list" class="viewer-box"></div>
			<input type="button" value="选择接收人" id="userTreeBtn" class="btn"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			时间维度
		</td>
		<td class="content">
			<select name="year" id="year" alt="allowBlank:false,vtext:'请选择年份'">
				<option value="">请选择</option>
				<option value="2005">2005</option>
				<option value="2006">2006</option>
				<option value="2007">2007</option>
				<option value="2008">2008</option>
				<option value="2009">2009</option>
				<option value="2010">2010</option>
				<option value="2011">2011</option>
				<option value="2012">2012</option>
				<option value="2013">2013</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
			</select>年
			<select name="month" id="month" alt="allowBlank:false,vtext:'请选择月份'">
				<option value="">请选择</option>
				<option value="01">1</option>
				<option value="02">2</option>
				<option value="03">3</option>
				<option value="04">4</option>
				<option value="05">5</option>
				<option value="06">6</option>
				<option value="07">7</option>
				<option value="08">8</option>
				<option value="09">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>月
		</td>
		<td class="label">
			状态
		</td>
		<td class="content">
			<eoms:dict key="dict-eva" dictId="templateStatus" defaultId="<%=EvaConstants.TEMPLATE_REPORTED%>"
				 selectId="actionType" beanId="selectXML" />
		</td>
	</tr>
</table>
<table id="submit-btn" align="left">
	<tr>
		<td>
			<input type="submit" class="btn" value="查询" />
		</td>
	</tr>
</table>
<html:hidden property="recieverOrgId" />
</html:form>
<script type="text/javascript">
function changeOrg(org) {
	if (org == 'dept') {
		document.getElementById('deptTr').style.display='block';
		document.getElementById('userTr').style.display='none';
	} else if (org == 'user') {
		document.getElementById('deptTr').style.display='none';
		document.getElementById('userTr').style.display='block';
	}
}
</script>
<%@ include file="/common/footer_eoms.jsp"%>