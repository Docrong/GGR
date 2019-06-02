<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 request.setAttribute("operateType",operateType);
 String operateUserId="";
     String ifInvoke=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
 System.out.println("=====ifInvoke======"+ifInvoke);
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("ArithmeticModify");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
 BaseMain basemain = (BaseMain)request.getAttribute("sheetMain");
 Date acceptLimit = new Date();
 Date completeLimit = new Date();
 Date sendTime = basemain.getSendTime();
 long time = sendTime.getTime();
 acceptLimit.setTime(time+24*60*60*1000);
 completeLimit.setTime(time+36*60*60*1000);
 request.setAttribute("acceptLimit",acceptLimit);
 request.setAttribute("completeLimit",completeLimit);
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
<%@ include file="/WEB-INF/pages/wfworksheet/arithmeticmodify/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	     <input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
         <input type="hidden" name="${sheetPageName}beanId" value="iArithmeticModifyMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink"/>
	  <c:if test="${taskName != 'HoldTask' }">
            <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
      </c:if>   
	<c:choose>
	<c:when test="${task.subTaskFlag == 'true'}">
		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	</c:when>
	</c:choose>           
		<%if(operateType.equals("4")){ %>
			 <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
			 <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
			 <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>				
		<%} %>
		 		<%if( taskName.equals("PermitTask")){%>
		 			<%if(operateType.equals("101")){ %> 
		 			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RequireConfirmTask" />
		 			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />	
		 			 <tr>
				       <td class="label">审批结果*</td>
						  <td colspan="3">			   
						      <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" 
						      initDicId="1011601" defaultValue="${sheetLink.linkPermitResult}"  styleClass="select-class" alt="allowBlank:false" onchange="permitResult(this.value);"/>
						 </td>
	  				 </tr>	  	
		 			<tr>
					  	<td class="label">审批意见*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkPermitOpinion" id="${sheetPageName}linkPermitOpinion" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkPermitOpinion}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("RequireConfirmTask")){%>
		 			<%if(operateType.equals("104")){ %> 
		 			<tr>
					  	<td class="label">需求确认结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkRequireConfirm" id="${sheetPageName}linkRequireConfirm" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkRequireConfirm}</textarea>
					    </td>
			  		</tr>
			  		<tr>
				  		<td class="label">测试开始时间*</td>
						  <td class="content">
						    <input type="text" class="text" name="${sheetPageName}linkTestStartTime" 
								id="${sheetPageName}linkTestStartTime" value="${eoms:date2String(sheetLink.linkTestStartTime)}" 
								onclick="popUpCalendar(this, this)"
								alt="allowBlank:false" /> 
				 		 </td>	
				 		 <td class="label">测试结束时间*</td>
						  <td class="content">
						    <input type="text" class="text" name="${sheetPageName}linkTestEndTime" 
								id="${sheetPageName}linkTestEndTime" value="${eoms:date2String(sheetLink.linkTestEndTime)}" 
								onclick="popUpCalendar(this, this)"
								alt="vtype:'moreThen',link:'${sheetPageName}linkTestStartTime',vtext:'测试结束时间不能早于测试开始时间',allowBlank:false" /> 
				 		 </td>	
				 	</tr>
		 		<%}} %>
		 		<%if( taskName.equals("DeployImplementTask")){%>
		 			<%if(operateType.equals("105")){ %> 
		 			<tr>
					  	<td class="label">测试结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkTestResult}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("ResultConfirmTask")){%>
		 			<%if(operateType.equals("106")){ %> 
		 			<tr>
					  	<td class="label">测试结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkTestResult}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("CheckDataSameTask")){%>
		 			<%if(operateType.equals("107")){ %> 
		 			<tr>
					  	<td class="label">测试结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkTestResult}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("ResultCheckTask")){%>
		 			<%if(operateType.equals("108")){ %> 
		 			<tr>
					  	<td class="label">验证报告结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkYZReportResult" id="${sheetPageName}linkYZReportResult" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkYZReportResult}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("TargetCheckTask")){%>
		 			<%if(operateType.equals("109")){ %> 
		 			<tr>
					  	<td class="label">检查报告结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkJCReportResult" id="${sheetPageName}linkJCReportResult" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkJCReportResult}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("TargetConfirmTask")){%>
		 			<%if(operateType.equals("110")){ %> 
		 			<tr>
					  	<td class="label">指标检查结果*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkTargetCheckResult" id="${sheetPageName}linkTargetCheckResult" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkTargetCheckResult}</textarea>
					    </td>
			  		</tr>
			  		<tr>
					  <td class="label">相关OA号或算法审批工单号*</td>
					  <td colspan="3">
				  		<textarea name="${sheetPageName}linkSheetNumber" id="${sheetPageName}linkSheetNumber" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkSheetNumber}</textarea>   
					  </td>
					</tr>
					<script type="text/javascript">
						var level='${sheetMain.mainTargetLevel}';
						if(level=="101700203"){
							$('${sheetPageName}linkSheetNumber').alt="allowBlank:true";
						}else{
							$('${sheetPageName}linkSheetNumber').alt="allowBlank:false";
						}
					</script>	
		 		<%}} %>
		 		<%if( taskName.equals("FormalDeployTask")){%>
		 			<%if(operateType.equals("111")){ %> 
		 			<tr>
					  	<td class="label">部署结果报告*</td>
					    <td colspan="3">
					      <textarea name="${sheetPageName}linkBSResultReport" id="${sheetPageName}linkBSResultReport" class="textarea max"
					      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetLink.linkBSResultReport}</textarea>
					    </td>
			  		</tr>
		 		<%}} %>
		 		<%if( taskName.equals("PublishNoticeTask")){%>
		 			<%if(operateType.equals("112")){ %> 
		 			<tr>
					  	<td class="label">算法发布时间*</td>
					    <td colspan="3">
						    <input type="text" class="text" name="${sheetPageName}linkPulishTime" 
								id="${sheetPageName}linkPulishTime" value="${eoms:date2String(sheetLink.linkPulishTime)}" 
								onclick="popUpCalendar(this, this)"
								alt="allowBlank:false" /> 
				 		 </td>	
			  		</tr>
		 		<%}} %>
		 		
   		    <%if( taskName.equals("HoldTask")){%>
   		    
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
      			<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>	 
	 		 <tr>
			  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
			    <td colspan='3'>
			      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
			        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
			    </td>
		     </tr>
			  
			  <tr>
			  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
			    <td colspan="3">
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
			      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			    			 
              <%}%>
               <%if(operateType.equals("17")){ %>
               <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckTask" />
	           <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	           <tr>
		       <td class="label">退回原因*</td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
			  </td>
			</tr>
               <%}%>
          <%}%>
        <%if(!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4") && !operateType.equals("17")&& !operateType.equals("90")){ %>  	
			   <tr>
			     <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
			     <td colspan="3">				
					     <eoms:attachment name="sheetLink" property="nodeAccessories" 
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="arithmeticmodify" />		   
			     </td>
		</tr>
	 <%}%>
	  <%if(operateType.equals("61") || operateType.equals("9") || operateType.equals("4") || operateType.equals("-11") || operateType.equals("-15") || operateType.equals("11")){ %>  	
		<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
	    </tr>  
	 <%}%>
   					     
  </table>
  
	
	<!-- 审批 -->
	<%if(taskName.equals("PermitTask") && operateType.equals("101")) {%>  		
	    <fieldset id="link5">
	     <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName"></span>
	    </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80047" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}%>	
	<!-- 需求确认 -->
	<%if(taskName.equals("RequireConfirmTask")) {
		if(operateType.equals("104")){
	%>  	
	<input type="hidden" name="phaseId" id="phaseId" value="DeployImplementTask" />
	<input type="hidden" name="operateType" id="operateType" value="104" />	
	<fieldset id="link6">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法修改实施组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80048" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 测试部署实施 -->
	<%if(taskName.equals("DeployImplementTask")) {
		if(operateType.equals("105")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="ResultConfirmTask" />
	<input type="hidden" name="operateType" id="operateType" value="105" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法管理组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80047" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 部署结果确认 -->
	<%if(taskName.equals("ResultConfirmTask")) {
		if(operateType.equals("106")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="CheckDataSameTask" />
	<input type="hidden" name="operateType" id="operateType" value="106" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法一致性验证组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80049" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 验证数据一致性 -->
	<%if(taskName.equals("CheckDataSameTask")) {
		if(operateType.equals("107")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="ResultCheckTask" />
	<input type="hidden" name="operateType" id="operateType" value="107" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法管理组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80047" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 验证结果确认 -->
	<%if(taskName.equals("ResultCheckTask")) {
		if(operateType.equals("108")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="TargetCheckTask" />
	<input type="hidden" name="operateType" id="operateType" value="108" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">技术专家组</span>
			
	 </legend>
		<eoms:chooser id="sendRole1"  type="role" roleId="80046" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
		<eoms:chooser id="sendRole2"  type="role" roleId="80050" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
		<eoms:chooser id="sendRole3"  type="role" roleId="80051" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<script type="text/javascript">
	   function setRole(){
			var type='${sheetMain.mainTargetType}';
			if(type=="101700301"){
				$('roleName').innerHTML="网优中心技术组";
				chooser_sendRole1.enable();
				chooser_sendRole2.disable();
				chooser_sendRole3.disable();
			}
			if(type=="101700302"){
				$('roleName').innerHTML="增值业务技术组";
				chooser_sendRole2.enable();
				chooser_sendRole1.disable();
				chooser_sendRole3.disable();
			}
			if(type=="101700303"){
				$('roleName').innerHTML="核心网技术组";
				chooser_sendRole3.enable();
				chooser_sendRole1.disable();
				chooser_sendRole2.disable();
			}
		}
		setRole(); 
	</script>
	<%}} %>
	<!-- 指标合理性检查 -->
	<%if(taskName.equals("TargetCheckTask")) {
		if(operateType.equals("109")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="TargetConfirmTask" />
	<input type="hidden" name="operateType" id="operateType" value="109" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法管理组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80047" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 指标确认部署 -->
	<%if(taskName.equals("TargetConfirmTask")) {
		if(operateType.equals("110")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="FormalDeployTask" />
	<input type="hidden" name="operateType" id="operateType" value="110" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法修改实施组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80048" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 正式部署 -->
	<%if(taskName.equals("FormalDeployTask")) {
		if(operateType.equals("111")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="PublishNoticeTask" />
	<input type="hidden" name="operateType" id="operateType" value="111" />
	<fieldset id="link7">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName">算法管理组</span>
			
	 </legend>
		<eoms:chooser id="sendRole"  type="role" roleId="80047" flowId="20223" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  />
	</fieldset>
	<%}} %>
	<!-- 算法发布通知 -->
	<%if(taskName.equals("PublishNoticeTask")) {
		if(operateType.equals("112")){
	%>  		
	<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />
	<input type="hidden" name="operateType" id="operateType" value="112" />
	
	<%}} %>
	<script type="text/javascript">
  function permitResult(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160101"){
	    eoms.$('link5').show();
		chooser_sendRole.enable();
		//审核通过到需求确认
		$('${sheetPageName}phaseId').value='RequireConfirmTask';
		$('${sheetPageName}operateType').value='103';
		$('roleName').innerHTML="算法管理组";
	} else if(input=="101160102"){
		//审核不通过到驳回  
		eoms.$('link5').hide();
		chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='RejectTask';
		$('${sheetPageName}operateType').value='102';
		$('roleName').innerHTML="";
	} else{
	    eoms.$('link5').hide();
	    chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
 	var frm = document.forms[0];
    var temp = frm.linkPermitResult ? frm.linkPermitResult.value : '';
    if("${taskName}"=="PermitTask"&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    permitResult(temp);
    }		
 </script>
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