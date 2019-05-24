<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<title></title>
<html:form action="OvertimeAction.do?act=overtimeList" method="post"
	styleId="SearchListForm">
	<table class="formTable" width="75%">
		<tr>
			<td colspan="4" align="center">
				<h2>
					<b>超时查询</b>
				</h2>
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				<b>开始时间</b>
			</td>
			<td>
				<input id="startTime" name="startTime" type="text" class="text"
					readonly="readonly"
					alt="vtype:'lessThen',link:'endTime',vtext:'开始时间不能晚于结束时间！'"
					onclick="popUpCalendar(this, this,null,null,null,false,-1);" />
			</td>
			<td class="label" align="right">
				<b>结束时间</b>
			</td>
			<td>
				<input id="endTime" name="endTime" type="text" class="text"
					readonly="readonly"
					alt="vtype:'moreThen',link:'startTime',vtext:'结束时间不能早于开始时间！'"
					onclick="popUpCalendar(this, this,null,null,null,false,-1);" />
			</td>
		</tr>
		<tr>
			<td class="label" align="right">
				<b>任务选择</b>
			</td>
			<td>
				<html:select property="reportId" value="0" styleClass="select">
                <html:options collection="reportId" property="value" labelProperty="label"/>
                </html:select>
			</td>
			<td class="label" align="right">
				<b>是否超时</b>
			</td>
			<td>
				<html:select property="overtimeQuery" value="" >
					<html:option value="2">
						全部
					</html:option>
					<html:option value="0">
						及时
					</html:option>
					<html:option value="1">
						超时
					</html:option>
				</html:select>
			</td>
		</tr>
		<tr>		
			<td class="label" align="right">
				<b>公司选择</b>
			</td>
			<td colspan="3">
					<input type="hidden" id="subCompany" name="subCompany" />
					<div id="user-list" class="viewer-list"></div>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="公司选择树" id="userTreeBtn" class="btn" />
			</td>	
		</tr>
		<tr>
			<td colspan="4" align="center">
				<html:submit styleClass="button" property="method.save"
					onclick="bCancel=false">
					<bean:message key="button.query" />
				</html:submit>
				&nbsp;&nbsp;
				<html:reset styleClass="button" onclick="bCancel=true">
					<fmt:message key="button.reset" />
				</html:reset>
			</td>
		</tr>

	</table>

</html:form>
<script language="javascript">
	Ext.onReady(function(){
	 v = new eoms.form.Validation({form:'SearchListForm'});
	var	userTreeAction='${app}/xtree.do?method=dept';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: false,		
			emptyText : '<div>请选择公司...</div>'								
		}
	);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'公司选择树',treeChkMode:'single',treeChkType:'dept',
		viewer:userViewer,saveChkFldId:'subCompany',returnJSON:true 
	});
});
</script>
<%@ include file="/common/footer_eoms.jsp"%>
