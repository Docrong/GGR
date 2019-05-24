<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="true" requestURI="plannadjust.do"
		sort="external" size="total" partialList="true">

		<display:column property="number" sortable="true"
			headerClass="sortable" title="序号" sortName="number" />
			
		<display:column property="sheetId" sortable="true"
			headerClass="sortable" title="工单流水号" sortName="sheetId" />
			
		<display:column property="sendTime" sortable="true"
			headerClass="sortable" title="申请时间"  sortName="sendTime" />
			
		<display:column   sortable="true" headerClass="sortable" title="状态" paramId="status" paramProperty="status" >	
			<c:if test="${taskList.status == '1'}">
				已归档
			</c:if>
			<c:if test="${taskList.status == '0'}">
				未归档
			</c:if>
		</display:column>

		<display:column property="planningNumber" sortable="true"
			headerClass="sortable" title="规划编号"  sortName="planningNumber" />			
			
		<display:column property="planningScheme" sortable="true"
			headerClass="sortable" title="所属规划方案"  sortName="planningScheme" />

		<display:column   sortable="true" headerClass="sortable" title="网元" paramId="netType" paramProperty="netType" >
			<eoms:id2nameDB id="${taskList.netType}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			

		<display:column   sortable="true" headerClass="sortable" title="属地分公司" paramId="territorialBranch" paramProperty="territorialBranch" >
			<eoms:id2nameDB id="${taskList.territorialBranch}" beanId="tawSystemDeptDao"/>		
		</display:column>				
			
		<display:column property="administrativeArea" sortable="true"
			headerClass="sortable" title="行政区域"  sortName="administrativeArea" />
			
		<display:column property="sendUserId" sortable="true"
			headerClass="sortable" title="操作人"  sortName="sendUserId" />
			
		<display:column property="sendContact" sortable="true"
			headerClass="sortable" title="操作人联系方式"  sortName="sendContact" />

		<display:column   sortable="true" headerClass="sortable" title="调整原因属性" paramId="reasonAdjust" paramProperty="reasonAdjust" >
			<eoms:id2nameDB id="${taskList.reasonAdjust}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			
			
		<display:column property="planningStartTime" sortable="true"
			headerClass="sortable" title="规划起始时间"  sortName="planningStartTime" />
			
		<display:column property="planningEndTime" sortable="true"
			headerClass="sortable" title="规划截至时间"  sortName="planningEndTime" />

		<display:column   sortable="true" headerClass="sortable" title="规划区内投诉情况" paramId="planningAreaComplaint" paramProperty="planningAreaComplaint" >
			<eoms:id2nameDB id="${taskList.planningAreaComplaint}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>					
			
		<display:column property="operateTime" sortable="true"
			headerClass="sortable" title="回单时间"  sortName="operateTime" />
			
		<display:column   sortable="true" headerClass="sortable" title="审核确认结果" paramId="checkResult" paramProperty="checkResult" >
			<eoms:id2nameDB id="${taskList.checkResult}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>	
			
		<display:column property="operateUserId" sortable="true"
			headerClass="sortable" title="审核人"  sortName="operateUserId" />
			
		<display:column property="reasonRejection" sortable="true"
			headerClass="sortable" title="驳回理由"  sortName="reasonRejection" />
			
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>

	</display:table>

<%@ include file="/common/footer_eoms.jsp"%>