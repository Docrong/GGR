<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
			new xbox({
				btnId:'auditmanName',dlgId:'dlg-audit',dlgTitle:'${eoms:a2u('请选择人员')}',
				treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u('所有人员')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'auditmanName',saveChkFldId:'auditman'
			});
			new xbox({
				btnId:'producerName',dlgId:'dlg-audit',dlgTitle:'${eoms:a2u('请选择人员')}',
				treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u('所有人员')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'producerName',saveChkFldId:'producer'
			});
})
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawBureauDataForm'});
});

</script>
  
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawBureauDataDetail.title" />
</title>
<!-- <content tag="heading"><fmt:message key="tawBureauDataDetail.heading"/></content> -->

<!--对表单的自动生成的处�?-->
 
<html:form action="tawBureauDatas" method="post"
	styleId="tawBureauDataForm">
	<ul>

		<!--表示对所有的域有�? -->

		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.bigNet" />
			<html:errors property="bigNet" />
			<eoms:comboBox name="bigNet" id="bigNet" initDicId="10601" sub="net" 
				styleClass="select-class" defaultValue="${tawBureauDataForm.bigNet}" alt="allowBlank:false,vtext:'${eoms:a2u('请选择大网元')}'" />
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.net" />
			<html:errors property="net" />
			<eoms:comboBox name="net" id="net" styleClass="select-class"
				defaultValue="${tawBureauDataForm.net}" alt="allowBlank:false,vtext:'${eoms:a2u('请选择网元')}'" />
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.factory" />
			<html:errors property="factory" />
			<eoms:comboBox name="factory" id="factory" initDicId="10601"
				styleClass="select-class"
				defaultValue="${tawBureauDataForm.factory}" alt="allowBlank:false,vtext:'${eoms:a2u('请选厂商')}'" />
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.data" />
			<html:errors property="data" />

			<input type="text" name="data" size="30" readonly="true"
				value="${tawBureauDataForm.data}" class="text"
				onclick="popUpCalendar(this, this);">
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.type" />
			<html:errors property="type" />
			<eoms:comboBox name="type" id="type" initDicId="10601"
				styleClass="select-class" defaultValue="${tawBureauDataForm.type}" alt="allowBlank:false,vtext:'${eoms:a2u('请选择项类型')}'" />
			<%--
			        <html:text property="type" styleId="type" styleClass="text medium"/>
		    --%>
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.content" />
			<html:errors property="content" />
			<html:textarea property="content" styleId="content"
				styleClass="text medium" rows="5" cols="17" alt="allowBlank:false,vtext:'${eoms:a2u('请选择执行情况')}'" />
		</li>

		<html:hidden property="id" />



		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.title" />
			<html:errors property="title" />

			<html:text property="title" styleId="title" styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请选择项标题')}'" />
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.producer" />
			<html:errors property="producer" />
			<input type="hidden" name="producer"
						value="${tawBureauDataForm.producer}">
			<html:text property="producerName" styleId="producerName"
				styleClass="text medium" readonly="true" />
			<html:hidden property="producer" />
			 
		</li>
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.auditman" />
			<html:errors property="auditman" />
			<input type="hidden" name="auditman"
						value="${tawBureauDataForm.auditman}">
			<html:text property="auditmanName" styleId="auditmanName"
				styleClass="text medium" readonly="true" /> 
			<html:hidden property="auditman" />
			 
		</li>
 
		<li>

			<eoms:label styleClass="desc" key="tawBureauDataForm.remark" />
			<html:errors property="remark" />
			<html:textarea property="remark" styleId="remark"
				styleClass="text medium" rows="5" cols="17" />
		</li>
		<li class="buttonBar bottom">
			<html:submit styleClass="button" property="method.xsave"
				onclick="bCancel=false">
				<fmt:message key="button.save" />
			</html:submit>
			<!--用自动生成的参数调用Javascript -->
			<html:submit styleClass="button" property="method.xdelete"
				onclick="bCancel=true; return confirmDelete('TawBureauData')">
				<fmt:message key="button.delete" />
			</html:submit>

		</li>
	</ul>
	
	<!--自动生成的Javascript脚本-->

</html:form>
 
<%@ include file="/common/footer_eoms.jsp"%>
