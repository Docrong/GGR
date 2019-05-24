<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<!--<form name="tawWorkPlanContentForm" method="post"
	action="${app}/plancontent/tawRmPlanContent.do?method=searchList" styleId="tawWorkPlanContentForm">-->

<html:form action="tawRmLogUnite" method="post" styleId="tawRmLogUniteForm"> 
	<table class="formTable">
		<caption>
			<fmt:message key="tawRmLogUniteDetail.heading" />
		</caption>
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
				<input type="text" name="beginTime" size="30" value="" class="text" onclick="popUpCalendar(this, this);" readonly="true">
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
</html:form>
<!-- </form>-->
<%@ include file="/common/footer_eoms.jsp"%>
