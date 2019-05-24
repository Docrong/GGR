<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script language="javascript">
Ext.onReady(function() {
	var	borrowerTreeAction='${app}/xtree.do?method=userFromDept';
	borrowerTree = new xbox({
		btnId:'borrowerTreeBtn',dlgId:'dlg-huser',	
		treeDataUrl:borrowerTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u("人员列表")}',treeChkMode:'single',treeChkType:'user',
		showChkFldId:'borrowerName',saveChkFldId:'borrowerId' 
	});
	v = new eoms.form.Validation({form:'tawRmRenewalForm'});
});
</script>
<html:form action="tawRmRenewals" method="post" styleId="tawRmRenewalForm"> 
	<table class="formTable">
		<caption>
			<fmt:message key="tawRmRenewalDetail.heading" />
		</caption>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmInoutRecordForm.borrowDate" />
			</td>
			<td width="500" colspan="3">
				<fmt:message key="tawRmInoutRecordForm.from" /><input type="text" name="borrowStartDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
				<fmt:message key="tawRmInoutRecordForm.to" /><input type="text" name="borrowEndDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmInoutRecordForm.intendingReturnDate" />
			</td>
			<td width="500" colspan="3">
				<fmt:message key="tawRmInoutRecordForm.from" /><input type="text" name="intendingReturnStartDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
				<fmt:message key="tawRmInoutRecordForm.to" /><input type="text" name="intendingReturnEndDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmInoutRecordForm.renewDate" />
			</td>
			<td width="500" colspan="3">
				<fmt:message key="tawRmInoutRecordForm.from" /><input type="text" name="renewStartDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
				<fmt:message key="tawRmInoutRecordForm.to" /><input type="text" name="renewEndDate" size="30" value="" class="text" onclick="popUpCalendar(this, this,'yyyy-mm-dd',-1,-1,false);" readonly="true">
			</td>
		</tr>
		<tr>
			<html:hidden property="borrowerId" styleId="borrowerId" styleClass="text medium" />
			<td width="100" class="label">
				<eoms:label styleClass="desc" key="tawRmInoutRecordForm.borrowerId" />
			</td> 
			<td width="500" colspan="2">
				<html:text property="borrowerName" styleId="borrowerName"
					styleClass="text medium" readonly="true" />
				<input type="button" value="${eoms:a2u('人员列表')}" id="borrowerTreeBtn" class="btn" />
			</td>
		</tr>
	</table>
	<br>
		<html:submit styleClass="button" property="method.searchList" onclick="bCancel=false">
            <fmt:message key="button.query"/>
        </html:submit>
</html:form>
<!-- </form>-->
<%@ include file="/common/footer_eoms.jsp"%>
