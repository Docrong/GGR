<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>

<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 request.setAttribute("operateType",operateType);
 %>
<script type="text/javascript">
		//处理时限不能超过工单完成时限
		var dtnow = new Date();
		var str = '${sheetMain.sheetCompleteLimit}';
		str = str.replace(/-/g,"/");
		str = str.substring(0,str.length-2);
		var dt2 = new Date(str);
		if(dt2>dtnow){
			document.getElementById("tmpCompleteLimit").value='${sheetMain.sheetCompleteLimit}';
		}else{
			document.getElementById("tmpCompleteLimit").value='';
		}
 </script>
 
<%@ include file="/WEB-INF/pages/wfworksheet/maintenanceservice/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		 <input type="hidden" id="tmpCompleteLimit" value="" />
         <input type="hidden" name="linkBeanId" value="iMaintenanceServiceLinkManager"/> 
         <input type="hidden" name="beanId" value="iMaintenanceServiceMainManager"/> 
         <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.maintenanceservice.model.MaintenanceServiceMain"/>	
         <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.maintenanceservice.model.MaintenanceServiceLink"/>
         <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
		<c:choose>
		<c:when test="${task.subTaskFlag == 'true'}">
			<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
		</c:when>
		</c:choose>      
		<%if(operateType.equals("4") && !taskName.equals("Fill") && !taskName.equals("Approval")){ %>
			<input type="hidden" name="operateType" id="operateType" value="4" />
		  	<c:choose>
				<c:when test="${empty fPreTaskName }">
		  			<input type="hidden" name="phaseId" id="phaseId" value="RejectTask" />	
				</c:when>
				<c:when test="${fPreTaskName=='DraftTask' }">
		  			<input type="hidden" name="phaseId" id="phaseId" value="RejectTask" />	
				</c:when>
				<c:otherwise>
		  			<input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}" />	
				</c:otherwise>
			</c:choose>	
    	<tr>
	       <td class="label">
	        		备注说明*
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="allowBlank:false,width:500,vtext:'请最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  		
		<%} %>   

		
		
			
			
			
 			
 			<%if(taskName.equals("Confirmation")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" /> 
 			
 			
 			
 				<%if(operateType.equals("102")||operateType.equals("102")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Fill" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 说明 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkExplain"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkExplain" id="linkExplain"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 说明 信息，最多输入 2000 字符'" value="${sheetLink.linkExplain}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 描述 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkDescribe"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkDescribe" id="linkDescribe"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 描述 信息，最多输入 2000 字符'" value="${sheetLink.linkDescribe}"/>
			</td>
		</tr>
 
 
 <%}%>
 
 				<%if(operateType.equals("106")||operateType.equals("106")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 强制归档原因 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkFilingReason"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkFilingReason" id="linkFilingReason"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 强制归档原因 信息，最多输入 2000 字符'" value="${sheetLink.linkFilingReason}"/>
			</td>
		</tr>
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("Fill")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("103")||operateType.equals("103")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Examine" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 种类 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkType" id="linkType"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 种类 信息，最多输入 2000 字符'" value="${sheetLink.linkType}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 序号 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSerialNumber"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkSerialNumber" id="linkSerialNumber"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 序号 信息，最多输入 2000 字符'" value="${sheetLink.linkSerialNumber}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 设备名称 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkDeviceName"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkDeviceName" id="linkDeviceName"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备名称 信息，最多输入 2000 字符'" value="${sheetLink.linkDeviceName}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 设备厂家 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkManufacturers"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkManufacturers" id="linkManufacturers"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备厂家 信息，最多输入 2000 字符'" value="${sheetLink.linkManufacturers}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 系统名称 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSystemName"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkSystemName" id="linkSystemName"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 系统名称 信息，最多输入 2000 字符'" value="${sheetLink.linkSystemName}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 设备型号 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkEquipmentType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkEquipmentType" id="linkEquipmentType"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备型号 信息，最多输入 2000 字符'" value="${sheetLink.linkEquipmentType}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 设备序列号 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkDeviceNumber"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkDeviceNumber" id="linkDeviceNumber"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备序列号 信息，最多输入 2000 字符'" value="${sheetLink.linkDeviceNumber}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 入网时间 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkAccessTime"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkAccessTime" id="linkAccessTime"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 入网时间 信息，最多输入 2000 字符'" value="${sheetLink.linkAccessTime}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 出保日期 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkOutDate"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkOutDate" id="linkOutDate"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 出保日期 信息，最多输入 2000 字符'" value="${sheetLink.linkOutDate}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 接口类型及数量 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkInterfaceType"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkInterfaceType" id="linkInterfaceType"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 接口类型及数量 信息，最多输入 2000 字符'" value="${sheetLink.linkInterfaceType}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 是否配置双机热备软件和其它随机软件 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSoftware"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkSoftware" id="linkSoftware"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 是否配置双机热备软件和其它随机软件 信息，最多输入 2000 字符'" value="${sheetLink.linkSoftware}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 操作系统名称及版本 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkSystemName"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkSystemName" id="linkSystemName"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 操作系统名称及版本 信息，最多输入 2000 字符'" value="${sheetLink.linkSystemName}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 系统集成商名称 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkIntegratorName"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkIntegratorName" id="linkIntegratorName"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 系统集成商名称 信息，最多输入 2000 字符'" value="${sheetLink.linkIntegratorName}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 拟购买的服务级别 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkLevelService"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkLevelService" id="linkLevelService"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 拟购买的服务级别 信息，最多输入 2000 字符'" value="${sheetLink.linkLevelService}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 设备物理位置 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkPhysicalLocation"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkPhysicalLocation" id="linkPhysicalLocation"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备物理位置 信息，最多输入 2000 字符'" value="${sheetLink.linkPhysicalLocation}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 备注 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkRemarks"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkRemarks" id="linkRemarks"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 备注 信息，最多输入 2000 字符'" value="${sheetLink.linkRemarks}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 联系人 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkContacts"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkContacts" id="linkContacts"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 联系人 信息，最多输入 2000 字符'" value="${sheetLink.linkContacts}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 联系方式 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkContactInformation"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkContactInformation" id="linkContactInformation"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 联系方式 信息，最多输入 2000 字符'" value="${sheetLink.linkContactInformation}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 单位 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkCompany"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkCompany" id="linkCompany"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 单位 信息，最多输入 2000 字符'" value="${sheetLink.linkCompany}"/>
			</td>
		</tr>
 
 <%}%>
 
 				<%if(operateType.equals("4")||operateType.equals("4")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Confirmation" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 驳回原因 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkReasonRejection"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkReasonRejection" id="linkReasonRejection"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 驳回原因 信息，最多输入 2000 字符'" value="${sheetLink.linkReasonRejection}"/>
			</td>
		</tr>
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("Examine")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("104")||operateType.equals("104")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Approval" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 审批结果 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkResult"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkResult" id="linkResult"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 审批结果 信息，最多输入 2000 字符'" value="${sheetLink.linkResult}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 审批意见 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkApprovalOpinion"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkApprovalOpinion" id="linkApprovalOpinion"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 审批意见 信息，最多输入 2000 字符'" value="${sheetLink.linkApprovalOpinion}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 服务内容是否满意 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkServiceContent"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkServiceContent" id="linkServiceContent"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 服务内容是否满意 信息，最多输入 2000 字符'" value="${sheetLink.linkServiceContent}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 评分 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkScore"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkScore" id="linkScore"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 评分 信息，最多输入 2000 字符'" value="${sheetLink.linkScore}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 处理是否及时 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkTimelyProcessing"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkTimelyProcessing" id="linkTimelyProcessing"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 处理是否及时 信息，最多输入 2000 字符'" value="${sheetLink.linkTimelyProcessing}"/>
			</td>
		</tr>
 
 <%}%>
 
 				<%if(operateType.equals("4")||operateType.equals("4")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Fill" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 驳回原因 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkReasonRejection"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkReasonRejection" id="linkReasonRejection"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 驳回原因 信息，最多输入 2000 字符'" value="${sheetLink.linkReasonRejection}"/>
			</td>
		</tr>
 
 <%}%>
 
 				<%if(operateType.equals("106")||operateType.equals("106")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 强制归档原因 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkFilingReason"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkFilingReason" id="linkFilingReason"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 强制归档原因 信息，最多输入 2000 字符'" value="${sheetLink.linkFilingReason}"/>
			</td>
		</tr>
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("Approval")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("105")||operateType.equals("105")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 服务内容 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkServiceContent"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkServiceContent" id="linkServiceContent"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 服务内容 信息，最多输入 2000 字符'" value="${sheetLink.linkServiceContent}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 维护开始时间 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkMaintenanceStartTime"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkMaintenanceStartTime" id="linkMaintenanceStartTime"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 维护开始时间 信息，最多输入 2000 字符'" value="${sheetLink.linkMaintenanceStartTime}"/>
			</td>
		</tr>
 
 				
 				<tr>
 				
 				<td class="label">
				<!-- 维护结束时间 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkMaintenanceEndTime"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkMaintenanceEndTime" id="linkMaintenanceEndTime"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 维护结束时间 信息，最多输入 2000 字符'" value="${sheetLink.linkMaintenanceEndTime}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 处理是否及时 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkTimelyProcessing"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkTimelyProcessing" id="linkTimelyProcessing"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 处理是否及时 信息，最多输入 2000 字符'" value="${sheetLink.linkTimelyProcessing}"/>
			</td>
		</tr>
 
 <%}%>
 
 				<%if(operateType.equals("4")||operateType.equals("4")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Examine" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 驳回原因 -->
				<bean:message bundle="maintenanceservice" key="maintenanceServiceLink.linkReasonRejection"/>*
			</td>
			<td>
				<input type="text"  class="text" name="linkReasonRejection" id="linkReasonRejection"  alt="allowBlank:false,maxLength:2000,vtext:'请填入 驳回原因 信息，最多输入 2000 字符'" value="${sheetLink.linkReasonRejection}"/>
			</td>
		</tr>
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("HoldTask")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("18")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Over" />
			<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
      	    <input type="hidden" name="status" id="status" value="1"/>	 
 				
 <%}%>
 
			
        <%} if(!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")){ %>  
         <tr>
		         <td class="label">
		    	  <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			     </td>	
			    <td colspan="3">					
		          <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		        </td>
		       </tr>		
			   <tr>
			     <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
			     <td colspan="3">				
					     <eoms:attachment name="sheetLink" property="nodeAccessories" 
              				scope="request" idField="nodeAccessories" appCode="maintenanceservice" />		   
			     </td>
		</tr>
	 <%}%>
     <%if(taskName.equals("Confirmation")  || taskName.equals("Fill")  || taskName.equals("Examine")  || taskName.equals("Approval")  || taskName.equals("HoldTask") ) {%> 
      	<%if(operateType.equals("61")){ %>
		<input type="hidden" name="operateType" id="operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 		
		<%} }%>
		<% if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="operateType" id="operateType" value="-15" />
		           <textarea name="remark" class="textarea max" id="remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 			     
  </table>

  
			
			
			
 			
 				
 				 <% if(taskName.equals("Confirmation")){ %> 
 				
 			   		<% if(operateType.equals("102")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:厂家
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="Confirmation0"  type="role" roleId="1762" flowId="60" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			   		<% if(operateType.equals("106")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:建单人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="Confirmation1"  type="role" roleId="1760" flowId="60" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
 				<% } if(taskName.equals("Fill")){ %>
 				
 				
 			   		<% if(operateType.equals("103")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:报修人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="Fill0"  type="role" roleId="1763" flowId="60" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			   		<% if(operateType.equals("4")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:设备维护人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  					   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
 				<% } if(taskName.equals("Examine")){ %>
 				
 				
 			   		<% if(operateType.equals("104")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:主管领导
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="Examine0"  type="role" roleId="1764" flowId="60" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			   		<% if(operateType.equals("4")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:厂家
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  					   
							    </div>
  						</fieldset>
 			   		<% } %>
 			   		<% if(operateType.equals("106")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:建单人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  					   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
 				<% } if(taskName.equals("Approval")){ %>
 				
 				
 			   		<% if(operateType.equals("105")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:建单人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  					   
							    </div>
  						</fieldset>
 			   		<% } %>
 			   		<% if(operateType.equals("4")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:报修人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  					   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
			
 				<% } %> 