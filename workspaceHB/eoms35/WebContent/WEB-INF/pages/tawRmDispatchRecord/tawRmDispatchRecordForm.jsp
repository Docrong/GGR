<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable"%>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmDispatchRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmDispatchRecordDetail.heading"/></content>

<%
	String recordId = (String) request.getParameter("id");
	String fileName=request.getParameter("tmpFileName");
	String fileSource=request.getParameter("tmpFileSource");
	String fileProperty=request.getParameter("tmpFileProperty");
	String time=request.getParameter("tmpTime");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	String dispatchDeptId=request.getParameter("tmpDispatchDeptId");
	String dispatchDept=request.getParameter("tmpDispatchDept");
	String dispatcherId=request.getParameter("tmpDispatcherId");
	String dispatcher=request.getParameter("tmpDispatcher");
%>

<script type="text/javascript">
var	userTreeAction='${app}/xtree.do?method=dept';
var treeAction='${app}/xtree.do?method=userByDept';
function deptCallBack(jsonData,data){
	dispatcherTree.resetRoot(treeAction+"&node="+data);
}

Ext.onReady(function(){
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-dept',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("所属部门")}',treeChkMode:'single',treeChkType:'dept',
		showChkFldId:'dispatchDept',saveChkFldId:'dispatchDeptId',callback:deptCallBack
	});
	dispatcherTree = new xbox({
		btnId:'dispatcherTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:treeAction,treeRootId:'-2',treeRootText:'${eoms:a2u("人员列表")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'dispatcher',saveChkFldId:'dispatcherId' 
	});
})
function confirmDelete(){
if ( confirm('${eoms:a2u("是否要删除此收发文记录")}') ){
	return true;
	}else{
	return false;
	}
}

Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmDispatchRecordForm'});
});
</script>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmDispatchRecord" method="post" styleId="tawRmDispatchRecordForm"> 
<ul>
<!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpFileName" />
		   <html:hidden property="tmpFileSource" />
		   <html:hidden property="tmpFileProperty" />
		   <html:hidden property="tmpTime" />
		   <html:hidden property="roomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />
		   <html:hidden property="tmpDispatchDeptId" />
		   <html:hidden property="tmpDispatchDept" />
		   <html:hidden property="tmpDispatcherId" />
		   <html:hidden property="tmpDispatcher" />
		   <br>

		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.fileName" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="fileName" styleId="fileName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入文件名称')}'" />
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.fileSource" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="fileSource" styleId="fileSource"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入文件来源')}'"/>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.fileProperty" />
				</td> 
				<td width="500" colspan="2">
		      		<eoms:dict key="dict-plancontent" dictId="fileProperty" beanId="selectXML"
						isQuery="false" defaultId="${tawRmDispatchRecordForm.fileProperty}"
						selectId="fileProperty" alt="allowBlank:false" />
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.time" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="time" styleId="time"
						styleClass="text medium"
						onclick="popUpCalendar(this, this);" readonly="true"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入时间')}'"/>
				</td>
			</tr>
			
			<tr>
				<html:hidden property="dispatchDeptId" styleId="dispatchDeptId" styleClass="text medium" />

				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.dispatchDept" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="dispatchDept" styleId="dispatchDept"
						styleClass="text medium" readonly="true" 
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入发文单位')}'"/>
						
				<input type="button" value="${eoms:a2u('部门列表')}" id="userTreeBtn" class="btn" />
				</td>
			</tr>
			
			<tr>
				<html:hidden property="dispatcherId" styleId="dispatcherId" styleClass="text medium" />
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.dispatcher" />
				</td> 
				<td width="500" colspan="2">
					<html:text property="dispatcher" styleId="dispatcher"
						styleClass="text medium" readonly="true"
						alt="allowBlank:false,vtext:'${eoms:a2u('请输入发文人')}'"/>
					<input type="button" value="${eoms:a2u('人员列表')}" id="dispatcherTreeBtn" class="btn" />
					<font style="font-size:13px;color:#CC0000;"><strong>${eoms:a2u('请先选择发文单位再选择发文人')}</strong></font>	
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.excuteRequest" />
				</td> 
				<td width="500" colspan="2">
					<html:textarea property="excuteRequest" styleId="excuteRequest"
						styleClass="text medium" rows="5" cols="30"/>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.remark" />
				</td> 
				<td width="500" colspan="2">
					<html:textarea property="remark" styleId="remark"
						styleClass="text medium" rows="5" cols="30"/>
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            			<fmt:message key="button.save"/>
        			</html:submit>
				</td>
				
				<%
				if (recordId != null) {
				%>
				<td>
					<html:submit styleClass="button" property="method.delete"
						onclick="bCancel=true; return confirmDelete()">
						<fmt:message key="button.delete" />
					</html:submit>
				</td>
				<td>
					<html:submit styleClass="button" property="method.searchList2">
		           		<fmt:message key="button.back"/>
		       		</html:submit>
				</td>
				<%
				}
				%>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>