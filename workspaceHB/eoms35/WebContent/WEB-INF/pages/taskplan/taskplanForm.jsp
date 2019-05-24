
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
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
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'taskplanForm'});
});

</script>
<!--  <content tag="heading"><fmt:message key="taskplanDetail.heading"/></content>  -->
<content tag="heading">
${eoms:a2u('网络管理中心2008年工作计划表')}
</content>

<html:form action="taskplans" method="post" styleId="taskplanForm">
	<ul>
		<li>

			<eoms:label styleClass="desc" key="taskplanForm.project_name" />
			<html:errors property="project_name" />

			<!--  <html:text property="project_name" styleId="project_name" styleClass="text medium"/> -->
			<!--dan级字典的调用
			         <eoms:comboBox name="project_name" id="project_name" initDicId="120" styleClass="select-class" defaultValue="12001"/> -->

			<!-- 二级字典的调用 -->
			<eoms:comboBox name="project_name" id="project_name" initDicId="120"
				sub="project_decompose" styleClass="select-class"
				defaultValue="${taskplanForm.project_name}" />
		</li>
		<li>

			<eoms:label styleClass="desc" key="taskplanForm.project_decompose" />
			<html:errors property="project_decompose" />
			<eoms:comboBox name="project_decompose" id="project_decompose"
				styleClass="select-class"
				defaultValue="${taskplanForm.project_decompose}" 
				alt="allowBlank:false,vtext:'${eoms:a2u('请选择项目分解')}'"/>
			<!--  <html:text property="project_decompose" styleId="project_decompose" styleClass="text medium"/>-->
		</li>

		<li>

			<eoms:label styleClass="desc" key="taskplanForm.deptid" />
			<html:errors property="deptid" />
			<html:hidden property="deptid" styleId="deptid"
				styleClass="text medium" />
			<html:text property="deptName" styleId="deptName"
				styleClass="text medium" readonly="true" alt="allowBlank:false,vtext:'${eoms:a2u('请选择部门')}'" />

			<input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn"
				class="btn" />
		</li>

		<html:hidden property="id" />
		<li>

			<eoms:label styleClass="desc" key="taskplanForm.stakeholders" />
			<html:errors property="stakeholders" />
			<html:hidden property="stakeholders" styleId="stakeholders"
				styleClass="text medium" />
			<html:text property="stakeholdersName" styleId="stakeholdersName"
				styleClass="text medium" readonly="true" />

			<input type="button" value="${eoms:a2u('人员列表')}"
				id="dispatcherTreeBtn" class="btn" />
		</li>


		<li>

			<eoms:label styleClass="desc" key="taskplanForm.month_mark" />
			<html:errors property="month_mark" />

			<!-- <html:text property="month_mark" styleId="month_mark" styleClass="text medium"/> -->
			<select name="month_mark" id="month_mark" alt="allowBlank:false,vtext:'${eoms:a2u('请选择月份')}'">
				<option value="" >
					====${eoms:a2u('请选择月份')}====
				</option>
				<option value="3">
					3${eoms:a2u('月')}
				</option>
				<option value="4">
					4${eoms:a2u('月')}
				</option>
				<option value="5">
					5${eoms:a2u('月')}
				</option>
				<option value="6">
					6${eoms:a2u('月')}
				</option>
				<option value="7">
					7${eoms:a2u('月')}
				</option>
				<option value="8">
					8${eoms:a2u('月')}
				</option>
				<option value="9">
					9${eoms:a2u('月')}
				</option>
				<option value="10">
					10${eoms:a2u('月')}
				</option>
				<option value="11">
					11${eoms:a2u('月')}
				</option>
				<option value="12">
					12${eoms:a2u('月')}
				</option>
			</select>
		</li>
		<li>

			<eoms:label styleClass="desc" key="taskplanForm.task_plan" />
			<html:errors property="task_plan" />

			<!--  <html:text property="task_plan" styleId="task_plan" styleClass="text medium"/>-->

			<td>
				<html:textarea property="task_plan" styleId="task_plan"
					styleClass="text medium" rows="3" cols="30"
					alt="allowBlank:false,vtext:'${eoms:a2u('请输入计划')}'" />

			</td>
		</li>
		<li>

			<eoms:label styleClass="desc" key="taskplanForm.task_complete" />
			<html:errors property="task_complete" />

			<!--  <html:text property="task_complete" styleId="task_complete" styleClass="text medium"/>-->
			<td>
				 
					<html:textarea property="task_complete" styleId="task_complete"
					styleClass="text medium" rows="3" cols="30"
					alt="allowBlank:false,vtext:'${eoms:a2u('请输入任务完成情况')}'" />
			</td>
		</li>

		<!--   <li>
		         
		        <eoms:label styleClass="desc" key="taskplanForm.serial_mark"/>
		        <html:errors property="serial_mark"/>
			  
			        <html:text property="serial_mark" styleId="serial_mark" styleClass="text medium"/>
		    </li> -->



		<li class="buttonBar bottom">
			<html:submit styleClass="button" property="method.xsave"
				onclick="bCancel=false">
				<fmt:message key="button.save" />
			</html:submit>
			<html:submit styleClass="button" property="method.delete"
				onclick="bCancel=true; return confirmDelete('Taskplan')">
				<fmt:message key="button.delete" />
			</html:submit>
		</li>
	</ul>


</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
