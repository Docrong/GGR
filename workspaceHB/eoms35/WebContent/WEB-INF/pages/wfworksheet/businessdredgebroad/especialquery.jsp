<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<table class="formTable">

	<!-- 所属地区 -->
	<tr>
		<td class="label">所属地区</td>
		<td width="100%">
			<input type="hidden" name="cityNameStringExpression" value="like"/>
			<input type="text" name="main.cityName" id="cityName" size="20" class="text"/>  
		</td>
	</tr>
	<!-- 所属小区名称 -->
	<tr>
		<td class="label">所属小区名称</td>
		<td width="100%">
			<input type="hidden" name="siUplinkUrlStringExpression" value="like"/>
			<input type="text" name="main.siUplinkUrl" id="siUplinkUrl" size="30" class="text"/>  
		</td>
	</tr>
	<!-- 客户联系人 -->
	<tr>
		<td class="label">客户联系人</td>
		<td width="100%">
			<input type="hidden" name="factureAreaStringExpression" value="like"/>
			<input type="text" name="main.factureArea" id="factureArea" size="30" class="text"/>  
		</td>
	</tr>
	<!-- 客户联系人电话 -->
	<tr>
		<td class="label">客户联系人电话</td>
		<td width="100%">
			<input type="hidden" name="appServerIPAddStringExpression" value="like"/>
			<input type="text" name="main.appServerIPAdd" id="appServerIPAdd" size="30" class="text"/>  
		</td>
	</tr>
	<!-- 用户账号 -->
	<tr>
		<td class="label">用户账号</td>
		<td width="100%">
			<input type="hidden" name="flowControlPriorityStringExpression" value="like"/>
			<input type="text" name="main.flowControlPriority" id="flowControlPriority" size="30" class="text"/>  
		</td>
	</tr>

	<tr>
		<td class="label">
			是否导出excel
		</td>
		<td>
			<select name=ifExcel>
				<option value=0 selected>不导出</option>
				<option value=1>导出</option>
			</select>
		</td>
	</tr>		
		<tr>
			<td class="label">业务类型</td>
			<td width="100%">
				<input type="hidden" name="main.businesstype1"/> 
				<eoms:comboBox name="businesstype1ChoiceExpression" id="businesstype1" initDicId="1011001"/>
		</td>
	</tr>
	<tr>
		<td class="label">
			归档时间
		</td>
		<td>
			<input type="hidden" name="main.endTime"/>
			<bean:message bundle="sheet" key="worksheet.query.startDate" />
			<input type="hidden" id="endTimeStartDateExpression" name="endTimeStartDateExpression" value=">="/>
			<input type="text" name="endTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text"/> &nbsp;&nbsp;
			<input type="hidden" name="endTimeLogicExpression" value="and"/>
			<bean:message bundle="sheet" key="worksheet.query.endDate" />
			<input type="hidden" id="endTimeEndDateExpression" name="endTimeEndDateExpression" value="<="> 
			<input type="text" name="endTimeEndDate" id="endTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text"/></div>
		</td>
	</tr>
            
</table>