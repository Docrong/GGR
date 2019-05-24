﻿<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet"%>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%@page import="com.boco.eoms.base.util.StaticMethod"%>

<%
	String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
	String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
	String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
	String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
	System.out.println("=====taskName======" + taskName);
	String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
	request.setAttribute("operateType", operateType);
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

	function encode(url)
	{
		return '123';
	}
	
	
	function isNeedTest(obj){
		if(obj == "101400401"){//需要
			eoms.$('di2').hide();
			eoms.$('di1').show();
			document.getElementById('phaseId').value='ConfirmDataTask';
     		document.getElementById('operateType').value='109';
     		
     		document.getElementById('dealPerformer').value='';
     		document.getElementById('dealPerformerLeader').value='';
     		document.getElementById('dealPerformerType').value='';
     		
     		eoms.$('contentDesc').show();
     		eoms.$('accessId').show();
     		document.getElementById('linkRemarkDe').alt='allowBlank:false';
     		document.getElementById('nodeAccessories').alt='allowBlank:false';
		}else if(obj == "101400402"){//不需要
			eoms.$('di2').show();
			eoms.$('di1').hide();
			document.getElementById('phaseId').value='HoldTask';
     		document.getElementById('operateType').value='104';
     		document.getElementById('dealPerformer').value='${sheetMain.sendRoleId}';
     		document.getElementById('dealPerformerLeader').value='${sheetMain.sendUserId}';
     		document.getElementById('dealPerformerType').value='user';
     		
     		eoms.$('contentDesc').hide();    		 		
     		eoms.$('accessId').hide();
     		
     		document.getElementById('linkRemarkDe').alt='allowBlank:true';
     		document.getElementById('nodeAccessories').alt='allowBlank:true';
     		 		
		}
	}
	
 </script>

<%@ include file="/WEB-INF/pages/wfworksheet/mofficedata/baseinputlinkhtmlnew.jsp"%>
<br />
<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" />
	<input type="hidden" name="linkBeanId" value="iMofficeDataLinkManager" />
	<input type="hidden" name="beanId" value="iMofficeDataMainManager" />
	<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.mofficedata.model.MofficeDataMain" />
	<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.mofficedata.model.MofficeDataLink" />
	<input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}" />
	<c:choose>
		<c:when test="${task.subTaskFlag == 'true'}">
			<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
		</c:when>
	</c:choose>
	<%
	if (operateType.equals("4")) {
	%>
	<input type="hidden" name="operateType" id="operateType" value="4" />
	<input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}" />
	<tr>
		<td class="label">备注说明*</td>
		<td colspan="3"><textarea name="remark" class="textarea max"
			id="remark" alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
			alt="width:'500px'">${sheetLink.remark}</textarea></td>
	</tr>
	<%}%>
	<%if (taskName.equals("AuditDataTask")) {%>
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("101") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="${nextPhasesId4s}" />
		<input type="hidden" name="corrKey" id="corrKey" value="${corrKey}" />
		<input type="hidden" name="proMatchSize" id="proMatchSize" value="${proMatchSize}" />
		<tr>
			<td class="label"><!-- 审批意见 --> 
				<bean:message bundle="mofficedata" key="mofficeDataLink.linkAuditSu" /></td>
			<td><textarea class="textarea max" name="linkAuditSu" id="linkAuditSu"
				alt="allowBlank:true,maxLength:500,vtext:'请填入 审批意见 信息，最多输入 500 字符'">${sheetLink.linkAuditSu}</textarea>
			</td>
		</tr>
		<tr>
			<td class="label">是否调用</td>
			<td>
				<eoms:comboBox name="linkIsNeedTest" id="linkIsNeedTest" initDicId="1014006"
				   defaultValue="${sheetLink.linkIsNeedTest}"/>
			</td>
		</tr>
		<%}%>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("102") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="RejectTask" />
		<tr>
			<td class="label"><!-- 审批意见 --> 
				<bean:message bundle="mofficedata" key="mofficeDataLink.linkAuditSu" />*</td>
			<td><textarea class="textarea max" name="linkAuditSu" id="linkAuditSu"
				alt="allowBlank:false,maxLength:500,vtext:'请填入 审批意见 信息，最多输入 500 字符'">${sheetLink.linkAuditSu}</textarea>
			</td>
		</tr>
		<%}%>
	<%}
	if (taskName.equals("OfficeMadeTask")) { %>
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("103") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="DataCheckTask" />
		<tr>
			<td class="label">进入局数据入口</td>
			<td colspan="3"><a href="http://10.25.117.214/portal"  target="_blank">局数据入口</a></td>
		</tr>
		<%}%>
		<%if (operateType.equals("115") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="DataInsTask" />
		<tr>
			<td class="label">制作方案</td>
			<td><textarea class="textarea max" name="remark" id="remark"
				alt="allowBlank:true,maxLength:500,vtext:'请填入 制作方案 信息，最多输入 500 字符'">${sheetLink.remark}</textarea>
			</td>
		</tr>		
		<%}%>
	<%}
	if (taskName.equals("DataCheckTask")) {%>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("104") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />
		<tr>
		   <td class="label">		     
             受理时限*
           </td>
           <td class="content"> 
            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" id="${sheetPageName}nodeAcceptLimit" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
           </td>
 
       
       	   <td class="label">		     
             处理时限*
           </td>
           <td class="content"> 
            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
           </td>
       </tr>
       <c:if test="${prolist!=null }">
       <c:forEach var="mat" items="${prolist}" varStatus="status">
       		<tr>
			<td class="label">数据核查执行结果</td>
			<c:choose>
				<c:when test="${mat.mainStyle6!=null}">
					<td colspan="3">
						<iframe id="UIFrame1-accessories"
						name="UIFrame1-accessories" class="uploadframe" frameborder="0"
						scrolling="auto"
						src="${app}/accessories/pages/view.jsp?appId=mofficedata&filelist=${mat.mainStyle6}"
						style="height:80%;width:100%"></iframe>
					</td>
				</c:when>
				<c:otherwise>
					<td colspan="3"></td>
				</c:otherwise>
			</c:choose>	
		</tr>
		<tr>
			<td class="label">数据制作执行结果</td>
			<c:choose>
				<c:when test="${mat.mainStyle7!=null}">
					<td colspan="3">
						<iframe id="UIFrame1-accessories"
						name="UIFrame1-accessories" class="uploadframe" frameborder="0"
						scrolling="auto"
						src="${app}/accessories/pages/view.jsp?appId=mofficedata&filelist=${mat.mainStyle7}"
						style="height:80%;width:100%"></iframe>
					</td>
				</c:when>
				<c:otherwise>
					<td colspan="3"></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td class="label">业务拨测执行结果</td>
			<c:choose>
				<c:when test="${mat.mainStyle8!=null}">
					<td colspan="3">
						<iframe id="UIFrame1-accessories"
						name="UIFrame1-accessories" class="uploadframe" frameborder="0"
						scrolling="auto"
						src="${app}/accessories/pages/view.jsp?appId=mofficedata&filelist=${mat.mainStyle8}"
						style="height:80%;width:100%"></iframe>
					</td>
				</c:when>
				<c:otherwise>
					<td colspan="3"></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td class="label">信令监测执行结果</td>
			<c:choose>
				<c:when test="${mat.mainStyle9!=null}">
					<td colspan="3">
						<iframe id="UIFrame1-accessories"
						name="UIFrame1-accessories" class="uploadframe" frameborder="0"
						scrolling="auto"
						src="${app}/accessories/pages/view.jsp?appId=mofficedata&filelist=${mat.mainStyle9}"
						style="height:80%;width:100%"></iframe>
					</td>
				</c:when>
				<c:otherwise>
					<td colspan="3"></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<td class="label">话单反查执行结果</td>
			<c:choose>
				<c:when test="${mat.mainStyle10!=null}">
					<td colspan="3">
						<iframe id="UIFrame1-accessories"
						name="UIFrame1-accessories" class="uploadframe" frameborder="0"
						scrolling="auto"
						src="${app}/accessories/pages/view.jsp?appId=mofficedata&filelist=${mat.mainStyle10}"
						style="height:80%;width:100%"></iframe>
					</td>
				</c:when>
				<c:otherwise>
					<td colspan="3"></td>
				</c:otherwise>
			</c:choose>
		</tr>
       </c:forEach>
       </c:if>
		<c:if test="${sheetMain.mainSendModeType==0 }">
		<tr>
			<td class="label">人工上传LOG文件</td>
			<td colspan="3"><eoms:attachment name="sheetLink"
				property="nodeAccessories" scope="request" idField="nodeAccessories"
				appCode="mofficedata" /></td>
		</tr>
		</c:if>
		<c:if test="${sheetMain.mainSendModeType==0 }">
		<tr>
			<td class="label">说明</td>
			<td colspan="3"><textarea class="textarea max" name="remark" id="remark"
				alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetLink.remark}</textarea>
			</td>
		</tr>
		</c:if>
		<%}%>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("111") ) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="AutoACheckTask" />
		<input type="hidden" name="corrKey" id="corrKey" value="${corrKey}" />
		<input type="hidden" name="proMatchSize" id="proMatchSize" value="${proMatchSize}" />
		<tr>
			<td class="label"><!-- 驳回原因 --> <bean:message
				bundle="mofficedata" key="mofficeDataLink.linkRejectRe" />*</td>
			<td ><textarea class="textarea max"
				name="linkRejectRe" id="linkRejectRe"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 驳回原因 信息，最多输入 200 字符'">${sheetLink.linkRejectRe}</textarea>
			</td>
		</tr>
		<%}%>
		<%if (operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="AutoACheckTask" />
		<tr>
			<td class="label"><!-- 处理结果描述 --> <bean:message
				bundle="mofficedata" key="mofficeDataLink.linkDealDesc" />*</td>
			<td ><textarea class="textarea max"
				name="linkDealDesc" id="linkDealDesc"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 驳回原因 信息，最多输入 200 字符'">${sheetLink.linkDealDesc}</textarea>
			</td>
		</tr>
		<%}%>
		<%if (operateType.equals("117") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="OfficeMadeTask" />
		<tr>
			<td class="label">说明</td>
			<td colspan="3"><textarea class="textarea max" name="remark" id="remark"
				alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetLink.remark}</textarea>
			</td>
		</tr>
		<%}%>
	<%}
	if (taskName.equals("ConfirmDataTask")) {%>
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("106") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />
		<tr>
			<td class="label"><!-- 处理结果描述 --> <bean:message
				bundle="mofficedata" key="mofficeDataLink.linkDealDesc" />*</td>
			<td ><textarea class="textarea max"
				name="linkDealDesc" id="linkDealDesc"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 处理结果描述 信息，最多输入 200 字符'">${sheetLink.linkDealDesc}</textarea>
	
			</td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet" key="tawSheetAccessForm.access" /></td>
			<td colspan="3"><eoms:attachment name="tawSheetAccess"
				property="accesss" scope="request" idField="accesss"
				appCode="toolaccess" viewFlag="Y"/></td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet" key="linkForm.accessories" />*</td>
			<td colspan="3"><eoms:attachment name="sheetLink"
				property="nodeAccessories" scope="request" idField="nodeAccessories"
				appCode="mofficedata" alt="allowBlank:false" /></td>
		</tr>
		<%}%>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<% if (operateType.equals("107") || operateType.equals("11")) { %>
		<input type="hidden" name="phaseId" id="phaseId" value="DataCheckTask" />
		<tr>
			<td class="label"><!-- 驳回原因 --> <bean:message
				bundle="mofficedata" key="mofficeDataLink.linkRejectWh" />*</td>
			<td ><textarea class="textarea max"
				name="linkRejectWh" id="linkRejectWh"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 驳回原因 信息，最多输入 200 字符'">${sheetLink.linkRejectWh}</textarea>
	
			</td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet" key="tawSheetAccessForm.access" /></td>
			<td colspan="3"><eoms:attachment name="tawSheetAccess"
				property="accesss" scope="request" idField="accesss"
				appCode="toolaccess" viewFlag="Y"/></td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet" key="linkForm.accessories" />*</td>
			<td colspan="3"><eoms:attachment name="sheetLink"
				property="nodeAccessories" scope="request" idField="nodeAccessories"
				appCode="mofficedata" alt="allowBlank:false" /></td>
		</tr>
		<%}%>
	<%}
	if (taskName.equals("DataInsTask")) {%><!-- alter by weichao 20170331 改为局数据核查 -->
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<%if (operateType.equals("116") || operateType.equals("11")) {%>
		<input type="hidden" name="phaseId" id="phaseId" value="DataCheckTask" />
		<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
		<tr>
			<td class="label">检查结果*</td>
			<td colspan="3"><textarea class="textarea max"
				name="linkDealDesc" id="linkDealDesc"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 检查结果 信息，最多输入 200 字符'">${sheetLink.linkDealDesc}</textarea>
			</td>
		</tr>
		<tr>
			<td class="label">说明</td>
			<td colspan="3"><textarea class="textarea max" name="remark" id="remark"
				alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetLink.remark}</textarea>
			</td>
		</tr>
		<%}%>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
		<% if (operateType.equals("118") || operateType.equals("11")) { %>
		<input type="hidden" name="phaseId" id="phaseId" value="OfficeMadeTask" />
		<tr>
			<td class="label">检查结果*</td>
			<td colspan="3"><textarea class="textarea max"
				name="linkDealDesc" id="linkDealDesc"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 检查结果 信息，最多输入 200 字符'">${sheetLink.linkDealDesc}</textarea>
			</td>
		</tr>
		<tr>
			<td class="label">说明</td>
			<td colspan="3"><textarea class="textarea max" name="remark" id="remark"
				alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetLink.remark}</textarea>
			</td>
		</tr>
		<%}%>
	<%} else if (taskName.equals("HoldTask")) {%>
		<%if (operateType.equals("18")) {%>
		<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
		<input type="hidden" name="operateType" id="operateType" value="18" />
		<input type="hidden" name="phaseId" id="phaseId" value="Over" />
		<input type="hidden" name="status" id="status" value="1" />
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="mainForm.holdStatisfied" />*</td>
			<td colspan='3'><eoms:comboBox name="holdStatisfied"
				id="holdStatisfied"
				defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
				initDicId="10303" styleClass="select" alt="allowBlank:false" /></td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="mainForm.endResult" />*</td>
			<td colspan="3"><textarea name="endResult" id="endResult"
				class="textarea max"
				alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
			</td>
		</tr>
		<%}%>
		<% if (operateType.equals("108")) { %>
		<input type="hidden" name="phaseId" id="phaseId" value="ConfirmDataTask" />
		<input type="hidden" name="operateType" id="operateType" value="108" />
		<tr>
			<td class="label"><!-- 驳回原因 --> <bean:message
				bundle="mofficedata" key="mofficeDataLink.linkRejectHt" />*</td>
			<td ><textarea class="textarea max"
				name="linkRejectHt" id="linkRejectHt"
				alt="allowBlank:false,maxLength:200,vtext:'请填入 退回原因 信息，最多输入 200 字符'">${sheetLink.linkRejectHt}</textarea>
	
			</td>
		</tr>
		<%}%>
	<%}%>
	<%if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4") && !operateType.equals("105")
	&& !operateType.equals("106")&& !operateType.equals("107")&& !operateType.equals("108")&& !operateType.equals("101")&& !operateType.equals("104")) {%>
	<tr>
		<td class="label"><bean:message bundle="sheet"
			key="tawSheetAccessForm.access" /></td>
		<td colspan="3"><eoms:attachment name="tawSheetAccess"
			property="accesss" scope="request" idField="accesss"
			appCode="toolaccess" viewFlag="Y" /></td>
	</tr>
	<tr id="accessId">
		<td class="label"><bean:message bundle="sheet"
			key="linkForm.accessories" /></td>
		<td colspan="3"><eoms:attachment name="sheetLink"
			property="nodeAccessories" scope="request" idField="nodeAccessories"
			appCode="mofficedata" alt="allowBlank:true"/></td>
	</tr>
	<%}%>
	<%
	if (taskName.equals("AuditDataTask") || taskName.equals("OfficeMadeTask")|| taskName.equals("DataCheckTask")
			 || taskName.equals("ConfirmDataTask") || taskName.equals("HoldTask")) {%>
		<%if (operateType.equals("61")) {%>
		<input type="hidden" name="operateType" id="operateType" value="61" />
		    <!--<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />
			    </td>
				<td colspan="3">			
			        <textarea name="remark" class="textarea max" id="remark" 
			        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
			  </td>
			</tr>-->
		<%}
	}%>
	<%if (taskName.equals("cc")) {%>
	<tr>
		<td class="label"><bean:message bundle="sheet" key="linkForm.remark" />*</td>
		<td colspan="3"><input type="hidden" name="operateType"
			id="operateType" value="-15" /> <textarea name="remark" class="textarea max" id="remark"
			alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
			alt="width:'500px'">${sheetLink.remark}</textarea></td>
	</tr>
	<%}%>
</table>

	
	<%if (taskName.equals("AuditDataTask")) {%>
		<%if(operateType.equals("101")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作人
			</span> </legend>
			<div class="x-form-item">
				${dealPerformerName }
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${dealPerformer}" alt="allowBlank:false">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${dealPerformer}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="${dealPerformerType }">	
			</div>
			</fieldset>
		<%}%>
		<%if (operateType.equals("102")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作管理员
			</span> </legend>
			<div class="x-form-item">
				<eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${sheetMain.sendRoleId}" alt="allowBlank:false">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${sheetMain.sendUserId}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="user">	
			</div>
			</fieldset>
		<%}%>
	<%}
	if (taskName.equals("OfficeMadeTask")) {%>
		<%if (operateType.equals("115")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:核查人
			</span> </legend>
				<div class="x-form-item">
				<eoms:chooser id="sendObjectMore" type="role" roleId="58705" flowId="587" config="{returnJSON:true,showLeader:true}"
				category="[{id:'dealPerformer',childType:'subrole',limit:'1',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
				</div>			
			</fieldset>
		<%}%>
	<%}
	if (taskName.equals("DataCheckTask")) {%>
		<%if (operateType.equals("104")) {%>		
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作管理员
			</span></legend>
			<div class="x-form-item">
				<eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${sheetMain.sendRoleId}" alt="allowBlank:false">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${sheetMain.sendUserId}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="user">	
			</div>
			</fieldset>			
		<%}%>
		<%if (operateType.equals("111")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作人
			</span> </legend>
			<div class="x-form-item">
				${dealPerformerName }
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${dealPerformer}" alt="allowBlank:false">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${dealPerformer}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="${dealPerformerType }">	
			</div>
			</fieldset>
		<%}%>
		<%if (operateType.equals("117")) {%>
		<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据核查人</span> </legend>
			<div class="x-form-item">
				    <input type="hidden" name="dealPerformer" id="dealPerformer" value=""/> 
    					<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value=""/> 
    					<input type="hidden" name="dealPerformerType" id="dealPerformerType" value=""/> 
    					<input type="hidden" name="linkRejectIds" id="linkRejectIds" value=""/> 
				  <table class="listTable">
			         <logic:present name="baseLinks" scope="request"> 
			          <tr>
			           <td class="label">
			            请选择驳回对象：
			           </td>
			        </tr>
			      <logic:iterate id="toTask" name="baseLinks" type="com.boco.eoms.sheet.mofficedata.model.MofficeDataLink">  
			        <tr>
			          <td>
			              <input type="checkbox" name="deal" id="deal" value="${toTask.operateUserId}">
			              <eoms:id2nameDB id="${toTask.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
			              <input type="hidden" name="performer" id="performer" value="${toTask.operateRoleId}" />
			              <input type="hidden" name="performerType" id="performerType" value="user" />
			              <input type="hidden" name="performerLeader" id="performerLeader" value="${toTask.operateUserId}" />
			              <input type="hidden" name="linkRejectId" id="linkRejectId" value="${toTask.id}" />
			          </td>
			        </tr>
			      </logic:iterate> 
			     </logic:present>
			     </table>
			</div>
		</fieldset>
		<%}%>
	<%}
	if (taskName.equals("ConfirmDataTask")) {%>
		<%if (operateType.equals("106")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作管理员
			</span> </legend>
			<div class="x-form-item">
				<eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${sheetMain.sendRoleId}">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${sheetMain.sendUserId}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="user">	
			</div>
			</fieldset>
		<%}%>
		<%if (operateType.equals("107")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作管理员
			</span> </legend>
			<div class="x-form-item">
				<eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${fOperateroleid}">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${fOperateuserid}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="user">	
			</div>
			</fieldset>
		<%}%>
	<%}
	if (taskName.equals("DataInsTask")) {%><!-- 改成局数据核查 alter by weichao 20170331 -->
		<%if (operateType.equals("116")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:建单人
			</span> </legend>
			<div class="x-form-item">
				<eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/>
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${sheetMain.sendRoleId}">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${sheetMain.sendUserId}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="user">	
			</div>
			</fieldset>
		<%}%>
		<%if (operateType.equals("118")) {%>
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
			<span id="roleName">:局数据制作管理员
			</span> </legend>
			<div class="x-form-item">
				<eoms:id2nameDB id="${fOperateuserid}" beanId="tawSystemUserDao"/>
				<input type="hidden" name="dealPerformer" id="dealPerformer" value="${fOperateroleid}">
				<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${fOperateuserid}">
				<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="user">	
			</div>
			</fieldset>
		<%}%>
	<%}
	 if (taskName.equals("HoldTask")) {%>
	 <%if (operateType.equals("108")) {%>	
			<fieldset><legend> <bean:message bundle="sheet" key="mainForm.toOperateRoleName" /> 
				<span id="roleName">:地市验证人员</span> </legend>
				<div class="x-form-item">
					    <input type="hidden" name="dealPerformer" id="dealPerformer" value=""/> 
     					<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value=""/> 
     					<input type="hidden" name="dealPerformerType" id="dealPerformerType" value=""/> 
     					<input type="hidden" name="linkRejectIds" id="linkRejectIds" value=""/> 
					  <table class="listTable">
				         <logic:present name="baseLinks" scope="request"> 
				          <tr>
				           <td class="label">
				            请选择驳回对象：
				           </td>
				        </tr>
				      <logic:iterate id="toTask" name="baseLinks" type="com.boco.eoms.sheet.mofficedata.model.MofficeDataLink">  
				        <tr>
				          <td>
				              <input type="checkbox" name="deal" id="deal" value="${toTask.operateUserId}">
				              <eoms:id2nameDB id="${toTask.operateUserId}" beanId="tawSystemUserDao" />&nbsp;
				              <input type="hidden" name="performer" id="performer" value="${toTask.operateRoleId}" />
				              <input type="hidden" name="performerType" id="performerType" value="user" />
				              <input type="hidden" name="performerLeader" id="performerLeader" value="${toTask.operateUserId}" />
				              <input type="hidden" name="linkRejectId" id="linkRejectId" value="${toTask.id}" />
				          </td>
				        </tr>
				      </logic:iterate> 
				     </logic:present>
				     </table>
				</div>
			</fieldset>
		<%}%>
	 <%}%>