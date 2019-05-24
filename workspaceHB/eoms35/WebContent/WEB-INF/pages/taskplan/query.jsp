<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/extlibs.jsp"%>
<%@ page import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script type="text/javascript">
var	userTreeAction='${app}/xtree.do?method=dept';
var treeAction='${app}/xtree.do?method=userByDeptForTaskplan';
function deptCallBack(jsonData,data){
	dispatcherTree.resetRoot(treeAction+"&node="+data);
}

Ext.onReady(function(){
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-dept',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'deptName',saveChkFldId:'deptid',callback:deptCallBack
	});
	dispatcherTree = new xbox({
		btnId:'dispatcherTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:treeAction,treeRootId:'-2',treeRootText:'${eoms:a2u("人员列表")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'stakeholdersName',saveChkFldId:'stakeholders' 
	});  
})

</script>
<style>
#tabs {
	width:90%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}
</style>
<script type="text/javascript">
var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('form', '${eoms:a2u('项目查询')}');
        tabs.addTab('info', '${eoms:a2u('帮助')}');
        tabs.activate('form');
     }
}
Ext.onReady(Tabs.init, Tabs, true);
</script>
<script language="javascript">

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'taskplanQueryForm'});
});
</script>
<div id="tabs">
<div id="form" class="tab-content">
<html:form action="/taskplan/querydo" method="post"
	styleId="taskplanQueryForm">
<table border="0" width="95%" cellspacing="1">
       
	
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('项目名称')}</td>
		<td colspan=3>
				<eoms:comboBox name="project_name" id="project_name" initDicId="120"
				sub="project_decompose" styleClass="select-class"
				defaultValue="${taskplanForm.project_name}" />
		
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('项目分解')}</td>
		<td colspan=3>
	 <!--  input type="text" name="name">-->

	 		<eoms:comboBox name="project_decompose" id="project_decompose"
				styleClass="select-class"
				defaultValue="${taskplanForm.project_decompose}" />
				 

	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('部门')}</td>
		<td colspan=3>
		<html:hidden property="deptid" styleId="deptid"
				styleClass="text medium" />
			<html:text property="deptName" styleId="deptName"
				styleClass="text medium" readonly="true" />

			<input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn"
				class="btn" />
	   </td>
 </tr>
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('人员')}</td>
		<td colspan=3>
	<html:hidden property="stakeholders" styleId="stakeholders"
				styleClass="text medium" />
			<html:text property="stakeholdersName" styleId="stakeholdersName"
				styleClass="text medium" readonly="true" />

			<input type="button" value="${eoms:a2u('人员列表')}"
				id="dispatcherTreeBtn" class="btn" />
			
	   </td>
 </tr>
 
 <tr class="tr_show">
		<td class="clsfth">${eoms:a2u('分页情况')}</td>
		<td colspan=3>
	<select name="fenye">
  <option value="0">${eoms:a2u('不分页')}</option>
  <option value="1">${eoms:a2u('分页')}</option>
</select>
	
	
	   </td>
 </tr>
 
 
 
 
</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:submit property="strutsButton" styleClass="clsbtn2">
                     ${eoms:a2u('查询')}
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</html:form>
</div>
  <div id="info">
    ${eoms:a2u('请填写查询条件，然后点击查询')}
  </div>
</div>
</body>
</html>
