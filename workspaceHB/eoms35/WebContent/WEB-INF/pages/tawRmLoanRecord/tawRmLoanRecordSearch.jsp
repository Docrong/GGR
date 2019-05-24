<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<!-- <form name="tawRmLoanRecordForm" method="post"
	action="${app}/loanrecord/tawRmLoanRecord.do?method=searchList" styleId="tawRmLoanRecordForm">-->
	
<html:form action="tawRmLoanRecord" method="post" styleId="tawRmLoanRecordForm"> 

	<table class="formTable">
		<caption>
			<fmt:message key="tawRmLoanRecordDetail.heading" />
		</caption>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmLoanRecordForm.articleName" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="tmpArticleName" size="30" value="" class="text">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmLoanRecordForm.piece" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="tmpPiece" size="30" value="" class="text">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmLoanRecordForm.borrowerName" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="tmpBorrowerName" size="30" value="" class="text">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmLoanRecordForm.loanTime" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="tmpLoanTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmLoanRecordForm.returnTime" />
			</td>
			<td width="500" colspan="2">
				<input type="text" name="tmpReturnTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);" readonly="true">
			</td>
		</tr>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmPlanContentForm.roomId" />
			</td>
			<td width="500" colspan="3">
				<html:select property="roomId">   
				     <html:option value=""><fmt:message key="tawRmPlanContentForm.select" /></html:option>   
				     <html:options collection="roomList"  property="id" labelProperty="roomname"/>   
				</html:select> 
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
	<!-- <input type="submit" value=" ${eoms:a2u("查询")}" name="B1"
		class="submit">-->
		
		<html:submit styleClass="button" property="method.searchList" onclick="bCancel=false">
            <fmt:message key="button.query"/>
        </html:submit>

<!-- </form>-->
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
