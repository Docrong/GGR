<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<!--<form name="tawWorkPlanContentForm" method="post"
	action="${app}/plancontent/tawRmPlanContent.do?method=searchList" styleId="tawWorkPlanContentForm">-->

<html:form action="tawRmPlanContent" method="post" styleId="tawRmPlanContentForm"> 
	<table class="formTable">
		<caption>
			<fmt:message key="tawRmPlanContentDetail.heading" />
		</caption>
		<tr>
			<td width="100" class="label">
				<fmt:message key="tawRmPlanContentForm.monthplanName" />
			</td>
			<td width="500" colspan="3">
				<input type="text" name="monthplanName" size="30" value="" class="text">
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
		<tr>
		      <td width="100" class="label">
		        <bean:message key="label.year"/>
		      </td>
		      <td width="200">
		        <eoms:dict key="dict-plancontent" dictId="yearflag" beanId="selectXML"
						isQuery="false" defaultId="${tawRmPlanContentForm.yearflag}"
						selectId="yearflag" alt="allowBlank:true" />
		      </td>
		
		      <td width="100" class="label">
		        <bean:message key="label.month"/>
		      </td>
		      <td width="200">
		        <eoms:dict key="dict-plancontent" dictId="monthflag" beanId="selectXML"
						isQuery="false" defaultId="${tawRmPlanContentForm.monthflag}"
						selectId="monthflag" alt="allowBlank:true" />
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
