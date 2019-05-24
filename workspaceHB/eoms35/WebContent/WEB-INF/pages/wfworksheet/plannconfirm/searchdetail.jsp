<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

	<display:table name="taskList" cellspacing="0" cellpadding="0"
		id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
		export="true" requestURI="plannconfirm.do"
		sort="external" size="total" partialList="true">

		<display:column property="number" sortable="true"
			headerClass="sortable" title="序号" sortName="number" />
			
		<display:column property="sheetId" sortable="true"
			headerClass="sortable" title="工单流水号" sortName="sheetId" />
			
		<display:column property="title" sortable="true"
			headerClass="sortable" title="工单主题"  sortName="title" />
			
		<display:column   sortable="true" headerClass="sortable" title="状态" paramId="status" paramProperty="status" >	
			<c:if test="${taskList.status == '1'}">
				已归档
			</c:if>
			<c:if test="${taskList.status == '0'}">
				未归档
			</c:if>
		</display:column>

		<display:column   sortable="true" headerClass="sortable" title="操作人" paramId="sendUserId" paramProperty="sendUserId" >
			<eoms:id2nameDB id="${taskList.sendUserId}" beanId="tawSystemUserDao"/>		
		</display:column>
					
		<display:column   sortable="true" headerClass="sortable" title="操作人部门" paramId="sendDeptId" paramProperty="sendDeptId" >
			<eoms:id2nameDB id="${taskList.sendDeptId}" beanId="tawSystemDeptDao"/>		
		</display:column>
			
		<display:column property="sendContact" sortable="true"
			headerClass="sortable" title="操作人联系方式"  sortName="sendContact" />

		<display:column property="sendTime" sortable="true"
			headerClass="sortable" title="操作时间"  sortName="sendTime" />

		<display:column   sortable="true" headerClass="sortable" title="网络分类二级类别" paramId="networkTypeTwo" paramProperty="networkTypeTwo" >
			<eoms:id2nameDB id="${taskList.networkTypeTwo}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			
			
		<display:column property="planningNumber" sortable="true"
			headerClass="sortable" title="规划编号"  sortName="planningNumber" />
			
		<display:column   sortable="true" headerClass="sortable" title="属地分公司" paramId="territorialBranch" paramProperty="territorialBranch" >
			<eoms:id2nameDB id="${taskList.territorialBranch}" beanId="tawSystemDeptDao"/>		
		</display:column>	
					
		<display:column property="stationSite" sortable="true"
			headerClass="sortable" title="基站站址"  sortName="stationSite" />
			
		<display:column property="sceneType" sortable="true"
			headerClass="sortable" title="场景类型"  sortName="sceneType" />
			
		<display:column property="longitude" sortable="true"
			headerClass="sortable" title="经度"  sortName="longitude" />
			
		<display:column property="latitude" sortable="true"
			headerClass="sortable" title="纬度"  sortName="latitude" />
			
		<display:column property="buildingType" sortable="true"
			headerClass="sortable" title="楼宇类型"  sortName="buildingType" />
			
		<display:column property="buildingNumber" sortable="true"
			headerClass="sortable" title="楼宇层数"  sortName="buildingNumber" />
			
		<display:column property="antennaHeight" sortable="true"
			headerClass="sortable" title="天线挂高"  sortName="antennaHeight" />
			
		<display:column property="platformType" sortable="true"
			headerClass="sortable" title="平台类型"  sortName="platformType" />

		<display:column   sortable="true" headerClass="sortable" title="经纬度符合要求" paramId="requirement" paramProperty="requirement" >
			<eoms:id2nameDB id="${taskList.requirement}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			

		<display:column   sortable="true" headerClass="sortable" title="挂高符合要求" paramId="highRequirement" paramProperty="highRequirement" >
			<eoms:id2nameDB id="${taskList.highRequirement}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			

		<display:column   sortable="true" headerClass="sortable" title="存在阻挡" paramId="existenceBarrier" paramProperty="existenceBarrier" >
			<eoms:id2nameDB id="${taskList.existenceBarrier}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			
			
		<display:column property="otherCircumstance" sortable="true"
			headerClass="sortable" title="其他情况"  sortName="otherCircumstance" />

		<display:column   sortable="true" headerClass="sortable" title="确认结果" paramId="validationResult" paramProperty="validationResult" >
			<eoms:id2nameDB id="${taskList.validationResult}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			
			
		<display:column property="percentAgreement" sortable="true"
			headerClass="sortable" title="同意比例"  sortName="percentAgreement" />

		<display:column   sortable="true" headerClass="sortable" title="协调组讨论" paramId="groupDiscussion" paramProperty="groupDiscussion" >
			<eoms:id2nameDB id="${taskList.groupDiscussion}" beanId="ItawSystemDictTypeDao"/>		
		</display:column>			
			
		<display:column property="planningAzimuth" sortable="true"
			headerClass="sortable" title="规划方位角"  sortName="planningAzimuth" />
			
		<display:column property="electronicAngle" sortable="true"
			headerClass="sortable" title="规划电子倾角"  sortName="electronicAngle" />
			
		<display:column property="mechanicalAngle" sortable="true"
			headerClass="sortable" title="规划机械倾角"  sortName="mechanicalAngle" />
			
		<display:column property="planningConfiguration" sortable="true"
			headerClass="sortable" title="规划配置"  sortName="planningConfiguration" />
			
		<display:column property="particularCase" sortable="true"
			headerClass="sortable" title="具体情况"  sortName="particularCase" />
			
		<display:column property="checkPeople" sortable="true"
			headerClass="sortable" title="确认人"  sortName="checkPeople" />
			
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>

	</display:table>

<%@ include file="/common/footer_eoms.jsp"%>