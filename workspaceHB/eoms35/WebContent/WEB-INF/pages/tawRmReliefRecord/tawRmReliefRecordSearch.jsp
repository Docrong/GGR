<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Hashtable"%>
<%@ page import="java.util.Enumeration"%>
<script language="javascript">
Ext.onReady(function(){
	var	handoverTreeAction='${app}/xtree.do?method=userFromDept';
	var successorTreeAction='${app}/xtree.do?method=userFromDept';
	handoverTree = new xbox({
		btnId:'handoverTreeBtn',dlgId:'dlg-huser',	
		treeDataUrl:handoverTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("交班人列表")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'handoverName',saveChkFldId:'tmpHandoverId' 
	});
	successorTree = new xbox({
		btnId:'successorTreeBtn',dlgId:'dlg-suser',	
		treeDataUrl:successorTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("接班人列表")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'successorName',saveChkFldId:'tmpSuccessorId' 
	});
})
</script>
<html:form action="tawRmReliefRecord" method="post" styleId="tawRmReliefRecordForm"> 

	<table class="formTable">
		<caption>
			<fmt:message key="tawRmReliefRecordDetail.heading" />
		</caption>
		<tr>
			<input type="hidden" name="tmpHandoverId"/>
			<td width="100" class="label">
				<fmt:message key="tawRmReliefRecordForm.handoverId" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="handoverName" size="30" value="" class="text" readonly="true">
				<input type="button" value="${eoms:a2u('交班人列表')}" id="handoverTreeBtn" class="btn" />
			</td>
		</tr>
		<tr>
			<input type="hidden" name="tmpSuccessorId"/>
			<td width="100" class="label">
				<fmt:message key="tawRmReliefRecordForm.successorId" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="successorName" size="30" value="" class="text" readonly="true">
				<input type="button" value="${eoms:a2u('接班人列表')}" id="successorTreeBtn" class="btn" />
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmPlanContentForm.roomId" />
			</td>
			<td width="500" colspan="3">
				<html:select property="tmpRoomId">   
				     <html:option value=""><fmt:message key="tawRmPlanContentForm.select" /></html:option>   
				     <html:options collection="roomList"  property="id" labelProperty="roomname"/>   
				</html:select> 
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmReliefRecordForm.time" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="tmpTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmPlanContentForm.startTime" />
			</td>
			<td width="500" colspan="3">
				<input type="text" name="startTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmPlanContentForm.endTime" />
			</td>
			<td width="500" colspan="3">
				<input type="text" name="endTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);" readonly="true">
			</td>
		</tr>
	</table>
	<br>
	<html:submit styleClass="button" property="method.searchList" onclick="bCancel=false">
        <fmt:message key="button.query"/>
    </html:submit>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
