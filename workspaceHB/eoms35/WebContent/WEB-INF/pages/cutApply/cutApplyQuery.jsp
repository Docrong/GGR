<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<title></title>

<html:form action="cutApplys.do?method=search" method="post"
	styleId="cutApplyForm">
	<table class="formTable" width="75%">
		<tr>
			<td colspan="4" align="center">
				<h2>
					干线割接管理查询
				</h2>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				割接申请人
			</td>
			<td >
				<input type="hidden" id="userId" name="userId" />
				<div id="reportView" class="viewer-box"></div>
				<input type="button" id="reportUser" name="reportUser" value="选择人员"
					class="button" />
			</td>
			<td class="label" align="right">
				割接申请部门
			</td>
			<td >
				<input type="hidden" id="deptId" name="deptId" />
				<div id="deptView" class="viewer-box"></div>
				<input type="button" id="reportDept" name="reportDept" value="选择部门"
					class="button" />
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				割接开始时间
			</td>
			<td>
				<html:text property="cutStartTime" styleId="cutStartTime" 
						styleClass="text medium"
				    readonly="true" alt="vtype:'lessThen',link:'cutEndTime',vtext:'不得晚于结束时间'"
					onclick="popUpCalendar(this, this,null,null,null,false,-1);"/>
			</td>
			<td class="label" align="right">
				割接结束时间
			</td>
			<td>
				<html:text property="cutEndTime" styleId="cutEndTime"
						styleClass="text medium"
					 readonly="true" alt="vtype:'moreThen',link:'cutStartTime',vtext:'不得早于结束时间'"
					onclick="popUpCalendar(this, this,null,null,null,false,-1);"/>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				割接所属地州
			</td>
			<td>
				<eoms:comboBox name="areaId" id="areaId" initDicId="11601"
					styleClass="select-class" />
			</td>
			<td class="label" align="right">
				是否影响任务
			</td>
			<td>
				<eoms:comboBox name="isAffect" id="isAffect" initDicId="11602"
					styleClass="select-class" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<html:submit styleClass="button" property="method.save"
					onclick="bCancel=false">
					<bean:message key="button.query" />
				</html:submit>
			</td>
			<td colspan="2" align="left">
				<html:reset styleClass="button" onclick="bCancel=true">
					<fmt:message key="button.reset" />
				</html:reset>
			</td>
		</tr>

	</table>

</html:form>
<script language="javascript">
	var reportUserTree;
	var reportDeptTree;
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}	
	Ext.onReady(function(){
		reportUserViewer = new Ext.JsonView("reportView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		var r='[]';
		reportUserViewer.jsonData = eoms.JSONDecode(r);
		reportUserViewer.refresh();
		var	treeAction='${app}/xtree.do?method=userFromDept';
		reportUserTree = new xbox({
			btnId:'reportUser',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'人员选择树',treeChkMode:'single',treeChkType:'user',
			saveChkFldId:'userId',viewer:reportUserViewer
		});
	});
	Ext.onReady(function(){
		reportDeptViewer = new Ext.JsonView("deptView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		var s='[]';
		reportDeptViewer.jsonData = eoms.JSONDecode(s);
		reportDeptViewer.refresh();
		var	treeAction2='${app}/xtree.do?method=dept';
		reportDeptTree = new xbox({
			btnId:'reportDept',dlgId:'hello-dlg',
			treeDataUrl:treeAction2,treeRootId:'-1',treeRootText:'部门选择树',treeChkMode:'single',treeChkType:'dept',
			saveChkFldId:'deptId',viewer:reportDeptViewer
		});
	});
</script>
<%@ include file="/common/footer_eoms.jsp"%>
