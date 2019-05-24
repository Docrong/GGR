<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" import="java.util.*;"%>
<fmt:bundle basename="com/boco/eoms/duty/config/ApplicationResources-duty">
	<!--根据给定的实例名生成标题 -->
	<title>专家值班查询</title>
	<html:form action="TawRmAssignExpert.do?method=xquery" method="post"
		styleId="tawRmAssignExpertForm">

		<html:hidden property="id" />
		<html:hidden property="room" />
		<table class="formTable middle" align="center">
			<tr>
				<td colspan="6" align="center">
					<h2>
						专家值班查询
					</h2>
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					开始时间
				</td>
				<td colspan="2">
					<html:text property="startTime" styleId="startTime"
						styleClass="text medium" readonly="true"
						onclick="popUpCalendar(this, this,null,null,null,true,-1); " />
				</td>
				<td class="label" nowrap="nowrap" align="right">
					结束时间
				</td>
				<td colspan="2">
					<html:text property="endTime" styleId="endTime"
						styleClass="text medium" readonly="true"
						onclick="popUpCalendar(this, this,null,null,null,true,-1); " />
				</td>
			</tr>
			<tr>
				<td class="label" nowrap="nowrap" align="right">
					专家姓名
				</td>
				<td colspan="2">
					<select id="expert" name="expert" style="width:100">
						<%
							Vector expertList = (Vector) request.getAttribute("expertList");
							if (expertList != null) {
								for (int room_expert_i = 0; room_expert_i * 2 < expertList
								.size(); room_expert_i++) {
						%>
						<option
							value="<%out.print(expertList.elementAt(room_expert_i*2));%>">
							<%
							out.print(expertList.elementAt(room_expert_i * 2 + 1));
							%>
						</option>
						<%
							}
							}
						%>
					</select>

				</td>
				<td colspan="3" align="center">
					<html:submit styleClass="button" property="method.query">
						查询
					</html:submit>
			</tr>

		</table>
	</html:form>
	<logic:present name="tawRmAssignExpertFormlist">
		<display:table name="tawRmAssignExpertFormlist" cellspacing="0"
			cellpadding="0" id="tawRmAssignExpertFormlist" pagesize="12"
			class="table tawRmAssignExpertFormlist"
			requestURI="${app}/duty/TawRmAssignExpert.do?method=xquery"
			sort="external" size="resultSize">

			<display:column headerClass="sortable" titleKey="expert.name" property="expert">
				
			</display:column>
			<display:column property="dutyTime" headerClass="sortable"
				titleKey="expert.date" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			<display:column property="startTime" headerClass="sortable"
				titleKey="expert.startTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			<display:column property="endTime" headerClass="sortable"
				titleKey="expert.endTime" format="{0,date,yyyy-MM-dd HH:mm:ss}" />
			<display:column headerClass="sortable" titleKey="expert.setInfo">
				<html:link href="${app}/duty/TawExpertInfo.do?method=searchfromzhiban"
					paramId="notes" paramProperty="notes"
					paramName="tawRmAssignExpertFormlist">
					<img src="${app}/duty/images/an_bj.gif">
				</html:link>
			</display:column>
			<display:column headerClass="sortable"
				titleKey="expert.score">
				<html:link href="${app}/duty/TawExpertSub.do?method=xspread"
					paramId="notes" paramProperty="notes" paramName="tawRmAssignExpertFormlist">
					<img src="${app }/images/plus.gif">
				</html:link>
			</display:column>
		</display:table>
	</logic:present>
	<script type="text/javascript">
	function notdeal(){
	alert("您不是创建者或者已经发布，不能进行修改！");
	}
	</script>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>
