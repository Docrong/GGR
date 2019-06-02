<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsMain" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
  System.out.println("=====operateType======"+operateType);
 request.setAttribute("operateType",operateType);
 String operateUserId="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("BusinessImplementSms");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
 		String pwd = sessionform.getPassword();
 		
 String ifInvoke=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
 System.out.println("@@ifInvoke="+ifInvoke);
 //added by liufyuan
BusinessImplementSmsMain basemain = (BusinessImplementSmsMain)request.getAttribute("sheetMain");
String title = basemain.getTitle();
String orderSheetId = basemain.getOrderSheetId();
String mainSpecifyType =basemain.getMainSpecifyType();
System.out.println("@mainSpecifyType"+mainSpecifyType);

String specialtyType = mainSpecifyType;
 %>
 <script type="text/javascript">
 
function changeNeeds(){
		var linkIfProvidedNeeds = document.getElementById("linkIfProvidedNeeds").value;
	
		return;	
	}
	
	function selectPhaseId() {
	if(${sheetPageName}linkIfProvidedNeeds)
		var task = document.getElementsByName("${sheetPageName}checkTaskName");
		var taskName;
		for (var i = 0; i < task.length; i++) {
 	     if(task[i].checked){
 	        taskName = task[i].value;     
 	       }
 	     }
      if(taskName=='UserTask'||taskName=='AccessTask'){
      	eoms.form.enableArea('new1');
      }else{      	
      	eoms.form.disableArea('new1',true);
      }

  	}
  	
  	function IfTestPass(input){
	if(input=="1"){
		$('${sheetPageName}phaseId').value='MakeTask';
		$('${sheetPageName}operateType').value='201';
		chooser_test1.enable();
		chooser_test2.disable();				
		eoms.$('link1').show();
		eoms.$('link2').hide();	
	}else if(input=="0"){  
		$('${sheetPageName}phaseId').value='TranferTask';
		$('${sheetPageName}operateType').value='202';
		eoms.$('link1').hide();
		chooser_test2.enable();
		chooser_test1.disable();
		eoms.$('link2').show();
	} 
	}

 </script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementsms/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
         <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementSmsMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsLink"/>

	  <c:if test="${taskName != 'HoldTask' }">
		  <%if(operateType.equals("")){ %>
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${task.operateRoleId}"/>
		  <%}else {%>
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
		  <%} %>
      </c:if>
      
	<c:choose>
	<c:when test="${task.subTaskFlag == 'true'}">
		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	</c:when>
	</c:choose>      
           
          <%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		  <c:choose> 
		  		<c:when test="${taskName=='AuditTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
		  	    <c:when test="${taskName=='MakeTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
				</c:when>
				<c:when test="${taskName=='TranferTask'}">
			
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
				
				
				</c:when>
				<c:when test="${taskName=='NetMakeTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask" />
				</c:when>
				
				<c:when test="${taskName=='NetMakeTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask" />
				</c:when>
				
				<c:when test="${taskName=='DataAcceptTask'}">
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BossMakeTask" />
				</c:when>	
				
				<c:when test="${taskName=='DevelopTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DataAcceptTask" />
				</c:when>
				<c:when test="${taskName=='InstallTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DevelopTask" />
				</c:when>				
				<c:when test="${taskName=='TestTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="InstallTask" />
				</c:when>
				<c:when test="${taskName=='ImplementAcceptTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TestTask" />
				</c:when>
				<c:when test="${taskName=='HoldTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplementAcceptTask" />
				</c:when>
			</c:choose>				
    	<tr>
	       <td class="label">
	        		备注说明*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,vtext:'请最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>     
           
           
           
           
           
           
           
      <!-- 订单审核 -->
        <%if(taskName.equals("AuditTask")){ %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
             <%if(operateType.equals("201")||operateType.equals("11")){ %>

   		    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask" />		    			  
				<tr>
				  <td class="label">互联网接入是否具体条件*</td>
				  <td  colspan="3">
				  	 <eoms:comboBox name="${sheetPageName}linkIfProvidedNeeds" id="${sheetPageName}linkIfProvidedNeeds" 
				  	       initDicId="1010106" defaultValue="${sheetLink.linkIfProvidedNeeds}" alt="allowBlank:false" onchange="IfTestPass(this.value)"/>
				  </td>				    
		         </tr>    
			
			  <tr>
			  	<td class="label">处理意见*</td>
			    <td colspan="3">
			      <textarea name="${sheetPageName}linkArgument" alt="allowBlank:false,maxLength:255,vtext:'请填入处理意见，最多输入255字符'" id="${sheetPageName}linkArgument"  class="textarea max">${sheetLink.linkArgument}</textarea>
			    </td>
			  </tr>			         
         <%}} %>
           <%if(taskName.equals("TranferTask")){ %>
             <%if(operateType.equals("220")||operateType.equals("11")){ %>
             
				     	 <%if(ifInvoke.equals("")||ifInvoke.equals("no")){
				     	 	System.out.println("@@ifInvoke=no");
				     	 %>
			   		      		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="waitReturn" />     		    	
			              		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111" />
			              	 
			              	 <%}else if(ifInvoke.equals("yes")){ 
			              	 System.out.println("@@ifInvoke=yes");
			              	 %>
								<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DataMakeTask" />  
								<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="74" />
   	
			 				<%} %> 	    	
			     <tr>
					<td class="label">是否调用业务开通工单</td>
					<td  colspan='3'>
				    <!-- add by liufuyuan -->
				    <eoms:comboBox name="${sheetPageName}linkIfProvidedNeeds" id="${sheetPageName}linkIfProvidedNeeds" initDicId="1013301" defaultValue="${sheetLink.linkIfProvidedNeeds}" styleClass="select" alt="allowBlank:false" 
						  	onchange="popOtherFlow('circuitdispatch',this.value);"/>
				    <html:button styleClass="btn" property="method.save" styleId="startCircuit" onclick="openwin('startTranfer')">
				         		传输专线
				     </html:button>
				     <html:button styleClass="btn" property="method.save" styleId="startCircuit2" onclick="openwin('startIp')">
				         		互联网专线
				     </html:button>
				    </td>
				  </tr>	
				    <tbody id="BianHao" style="display:none">	  
				  <tr>
				   <td class="label">
						接入客户日期        
		           </td>
		           <td colspan="3">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}linkinputGetUserDate" id="${sheetPageName}linkinputGetUserDate" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.linkinputGetUserDate)}" alt="allowBlank:true"/>
		          
		           </td>	
				 </tr>
					<tr>
					<!-- 
				    <td class="label">电路编号</td>
			        <td class="content"> 
			        <input type="text"  class="text" name="${sheetPageName}linkCircuitNum" id="${sheetPageName}linkCircuitNum"  alt="allowBlank:true" value="${sheetLink.linkCircuitNum}"/>
			        </td>
			         -->
			        <td class="label">电路调度结果*</td>
		            <td colspan="3">
		             <eoms:comboBox   name="${sheetPageName}linkCircuitResult" id="${sheetPageName}linkCircuitResult"  initDicId="1013306" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkCircuitResult}" onchange="onchangelinkIsDataOk(this.value);"/>	     	
		        	</td>
			  		 </tr>
			   </tbody>
			   
			   <tbody id="IsDataOk" style="display:none">	 
				<tr>
				    <td class="label">不成功原因</td>
			        <td colspan='3'> 
			           <textarea class="textarea max"  name="${sheetPageName}linkUnsuccessDesc" id="${sheetPageName}linkUnsuccessDesc" 
			      		alt="allowBlank:true,maxLength:200,vtext:'不成功原因描述，最多输入100汉字'">${sheetLink.linkUnsuccessDesc}</textarea>
			        </td>

			   </tr>
			   </tbody>
		
			<tr>
			  	<td class="label">回复意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkStartSheetDesc" id="${sheetPageName}linkStartSheetDesc" 
			      		alt="allowBlank:true,maxLength:200,vtext:'回复意见描述，最多输入100汉字'">${sheetLink.linkStartSheetDesc}</textarea>
			    </td> 
		     </tr>		         
         <%}} %>
      <!--数据制作-->
        <%if(taskName.equals("MakeTask")){ %>
            			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
             <%if(operateType.equals("203")||operateType.equals("11")){ %>

   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
   		    	<input type="hidden" name="${sheetPageName}extendKey2" id="${sheetPageName}extendKey2" value="BossMakeTask" /> 
   		    	<input type="hidden" name="${sheetPageName}toMorePhaseId" id="${sheetPageName}toMorePhaseId" value="NetMakeTask,SupportMakeTask" /> 		    
				<tr>
				  <td class="label">企业代码*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkEnterpriseNum" id="${sheetPageName}linkEnterpriseNum"  value="${sheetLink.linkEnterpriseNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入企业代码，六位数字'"/>
				  </td>		
				  <td class="label">服务代码*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkServiceNum" id="${sheetPageName}linkServiceNum"  value="${sheetLink.linkServiceNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>			    
		         </tr>    
				 <tr>
	              <td  class="label">接入号码*</td>				 
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkPointNum" id="${sheetPageName}linkPointNum"  value="${sheetLink.linkPointNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入接入号码'"/>
				  </td>			 
	              <td  class="label">黑名单*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkBlackName" id="${sheetPageName}linkBlackName"  value="${sheetLink.linkBlackName}" alt="allowBlank:true"/>
				  </td>				
				</tr>    		  	    				
			  <tr>
			  	<td class="label">白名单*</td>
			  	<td class="content" colspan=3>
	                 <input type="text"  class="text" name="${sheetPageName}linkWhiteName" id="${sheetPageName}linkWhiteName"  value="${sheetLink.linkWhiteName}" alt="allowBlank:true"/>
				  </td>	
			  </tr>			         
         <%}} %>
         <!--网关数据制作-->
        <%if(taskName.equals("NetMakeTask")){ %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
             <%if(operateType.equals("205")||operateType.equals("11")){ %>

   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BossMakeTask" />		    			  
				 <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />        
       		 <tr>
				  <td class="label">数据是否完成*</td>
				  <td class="content">
				  <eoms:comboBox name="${sheetPageName}linkDataIfOver" id="${sheetPageName}linkDataIfOver" 
				  	       initDicId="1010106" defaultValue="${sheetLink.linkDataIfOver}" alt="allowBlank:false" />
				  </td>		
				  <td class="label">行业网关地址*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkGatewayAddress" id="${sheetPageName}linkGatewayAddress"  value="${sheetLink.linkGatewayAddress}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>			    
		         </tr> 
		          <tr>
				  <td class="label">彩信中心/彩信网关地址*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkSmsGatewayAddress" id="${sheetPageName}linkSmsGatewayAddress"  value="${sheetLink.linkSmsGatewayAddress}" alt="allowBlank:false,maxLength:50,vtext:'请填入企业代码，六位数字'"/>
				  </td>	
				   <td class="label">业务流向限制*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkBusinessLimit" id="${sheetPageName}linkBusinessLimit"  value="${sheetLink.linkBusinessLimit}" alt="allowBlank:false,maxLength:50,vtext:'请填入企业代码，六位数字'"/>
				  </td>			
				  	    
		         </tr> 
		         
		          <tr>
		          <td class="label">登录网关的帐号*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkLoginName" id="${sheetPageName}linkLoginName"  value="${sheetLink.linkLoginName}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>		
				  <td class="label">登录网关的密码*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkLoginPassword" id="${sheetPageName}linkLoginPassword"  value="${sheetLink.linkLoginPassword}" alt="allowBlank:false,maxLength:50,vtext:'请填入企业代码，六位数字'"/>
				  </td>		
				 	    
		         </tr> 
		         
		          <tr>
				   <td class="label">企业流控忙时*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkCompanyBusy" id="${sheetPageName}linkCompanyBusy"  value="${sheetLink.linkCompanyBusy}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>		
				  <td class="label">企业流控闲时*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkCompanyFree" id="${sheetPageName}linkCompanyFree"  value="${sheetLink.linkCompanyFree}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>			    
		         </tr> 
		          
		          <tr>
				  <td class="label">彩信上行URL*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkSmsUrl" id="${sheetPageName}linkSmsUrl"  value="${sheetLink.linkSmsUrl}" alt="allowBlank:false,maxLength:50,vtext:'请填入企业代码，六位数字'"/>
				  </td>		
				  <td class="label">ProvisionURL*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkProvisionURL" id="${sheetPageName}linkProvisionURL"  value="${sheetLink.linkProvisionURL}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>			    
		         </tr> 
		          
		          <tr>
				 
				  <td class="label">数据制作结果*</td>
				  <td class="content" >
	                 <input type="text"  class="text" name="${sheetPageName}linkBusinessLimit" id="${sheetPageName}linkBusinessLimit"  value="${sheetLink.linkBusinessLimit}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>		
				    <%if(mainSpecifyType.equals("101230106")){ %>	    
				  <td class="label">提供服务端口号</td>
				  <td class="content" >
	                 <input type="text"  class="text" name="${sheetPageName}linksupportServicePortNumber" 
	                 id="${sheetPageName}linksupportServicePortNumber"  value="${sheetLink.linksupportServicePortNumber}" 
	                />
				  </td>	
				    <%} %>
				  </tr> 
				  
				  <%if(mainSpecifyType.equals("101230105")){ %>
			          <tr>
					  <td class="label">网关IP地址（EC/SI）</td>
					  <td class="content" >
		                 <input type="text"  class="text" name="${sheetPageName}linknetWayIpAdd" id="${sheetPageName}linknetWayIpAdd"  value="${sheetLink.linknetWayIpAdd}" />
					  </td>			    
					  <td class="label">网关名称（EC/SI）</td>
					  <td class="content" >
		                 <input type="text"  class="text" name="${sheetPageName}linknetWayName" 
		                 id="${sheetPageName}linknetWayName"  value="${sheetLink.linknetWayName}" 
		                />
					  </td>	
					  </tr> 
				   <%} %>
		         
         <%}} %>
          <!--支撑数据制作-->
         					  
        <%if(taskName.equals("SupportMakeTask")){ %>
        		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" /> 
             <%if(operateType.equals("204")||operateType.equals("11")){ %>
  
   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BossMakeTask" />		    			  
				 <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />        
        <tr>
				  <td class="label">回复意见*</td>
				  <td>
				   <textarea name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult" class="textarea max"
			      alt="allowBlank:false,maxLength:100,vtext:'请输入归档内容，最多输入125汉字'">${sheetLink.linkDealResult}</textarea>	
		         </td>
		         </tr>   
         <%}} %>
     <!--Boss数据录入-->
        <%if(taskName.equals("BossMakeTask")){ %>
       <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />  
             <%if(operateType.equals("207")||operateType.equals("11")){ %>
 				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DataAcceptTask" />		    			  
				<tr>
				  <td class="label">回复意见*</td>
				  <td colspan="3">
				   <textarea name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult" class="textarea max"
			      alt="allowBlank:false,vtext:'请输入归档内容，最多输入125汉字'">${sheetLink.linkDealResult}</textarea>	
		         </td>
		         </tr>    
				         
         <%}} %>
          <!--Boss数据确认-->
        <%if(taskName.equals("DataAcceptTask")){ %>
        		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
             <%if(operateType.equals("208")||operateType.equals("11")){ %>

   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DevelopTask" />		    			  
								<tr>
				  <td class="label">是否需要MAS*</td>
				  <td  colspan="3">
				  	 <eoms:comboBox name="${sheetPageName}linkIfProvidedNeeds" id="${sheetPageName}linkIfProvidedNeeds" 
				  	       initDicId="1010106" defaultValue="${sheetLink.linkIfProvidedNeeds}" alt="allowBlank:false" onchange="isNeedMas(this.value)"/>
				  </td>				    
		         </tr>    
				
				<tr>
				  <td class="label">回复意见*</td>
				  <Td colspan="3">
				   <textarea name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult" class="textarea max"
			      alt="allowBlank:false,maxLength:100,vtext:'请输入归档内容，最多输入125汉字'">${sheetLink.linkDealResult}</textarea>	
			      <td>
		         </tr>    
				         		         
         <%}} %>
           <!--Mas服务器开发-->
        <%if(taskName.equals("DevelopTask")){ %>
        		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />  
             <%if(operateType.equals("209")||operateType.equals("11")){ %>
 
   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="InstallTask" />		    			  
    
				<tr>
				  <td class="label">MASID/MAS密码*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkEnterpriseNum" id="${sheetPageName}linkEnterpriseNum"  value="${sheetLink.linkEnterpriseNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入企业代码，六位数字'"/>
				  </td>		
				  <td class="label">行业网关密码*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkServiceNum" id="${sheetPageName}linkServiceNum"  value="${sheetLink.linkServiceNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入服务代码，以106开头的7位-20位，以10086、1258、12114、9开头的20位以内。'"/>
				  </td>			    
		         </tr>     
				         		         
         <%}} %>
           <!--Mas服务器安装-->
        <%if(taskName.equals("InstallTask")){ %>
        		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" /> 
             <%if(operateType.equals("210")||operateType.equals("11")){ %>
  
   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TestTask" />		    			  
  
    
				<tr>
				  <td class="label">MAS服务器编号</td>
				  <td colspan="3">
	                 <input type="text"  class="text" name="${sheetPageName}linkMasServerNum" id="${sheetPageName}linkMasServerNum"  value="${sheetLink.linkMasServerNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入MAS服务器电路编号'"/>
				  </td>	
		         </tr>    
				<tr>
				  <td class="label">回复意见*</td>
				  <td colspan="3">
				   <textarea name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult" class="textarea max"
			      alt="allowBlank:false,maxLength:100,vtext:'请输入归档内容，最多输入125汉字'">${sheetLink.linkDealResult}</textarea>	
		        </td>
		         </tr>    
				         		         
         <%}} %>
             <!--业务测试-->
        <%if(taskName.equals("TestTask")){ %>
        		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
             <%if(operateType.equals("211")||operateType.equals("11")){ %>

   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplementAcceptTask" />		    			  
  
    
				<tr>
				  <td class="label">测试结果</td>
				  <td colspan="3">
				  	 <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" 
				  	       initDicId="1010112" defaultValue="${sheetLink.linkTestResult}" alt="allowBlank:false" />
				  </td>	
				  <!-- 
				    <td class="label"> 电路编号*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkCircuitNum" id="${sheetPageName}linkCircuitNum"  value="${sheetLink.linkCircuitNum}" alt="allowBlank:false,maxLength:50,vtext:'请填入电路编号'"/>
				  </td>	 -->	
		         </tr>    
				<tr>
				  <td class="label">处理意见*</td>
				  <Td  colspan="3">	
				   <textarea name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult" class="textarea max"
			      alt="allowBlank:false,maxLength:100,vtext:'请输入归档内容，最多输入125汉字'">${sheetLink.linkDealResult}</textarea>	
		          </td>
		         </tr>    
				         		         
         <%}} %>
            <!--开通确认-->
        <%if(taskName.equals("ImplementAcceptTask")){ %>
        		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />  
             <%if(operateType.equals("213")||operateType.equals("11")){ %>
 
   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />		    			  
    
				<tr>
				  <td class="label">满意度</td>
				  <td class="content" >
				  	 <eoms:comboBox name="${sheetPageName}linkSatisfied" id="${sheetPageName}linkSatisfied" 
				  	       initDicId="1010105" defaultValue="${sheetLink.linkSatisfied}" alt="allowBlank:false" />
				  </td>		
				    <td class="label">是否满足需求</td>
				  <td class="content" >
				  	 <eoms:comboBox name="${sheetPageName}linkIfSatisfied" id="${sheetPageName}linkIfSatisfied" 
				  	       initDicId="1010109" defaultValue="${sheetLink.linkIfSatisfied}" alt="allowBlank:false" />
				  </td>		
		         </tr>  
		         
				<tr>
				  <td class="label">不满足原因</td>
				  <td colspan="3">
				   <textarea name="${sheetPageName}linkUnSatisfiedReason" id="${sheetPageName}linkUnSatisfiedReason" class="textarea max"
			      alt="allowBlank:false,maxLength:100,vtext:'请输入不满足原因，最多输入125汉字'">${sheetLink.linkUnSatisfiedReason}</textarea>	
		         </td>
		         </tr>  	
				  
			    <tr>
		         <td class="label">
		    	  <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			     </td>	
			    <td  colspan="3">					
		          <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		        </td>
		       </tr>		
			   <tr>
			     <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
			     <td  colspan="3">				
					     <eoms:attachment name="sheetLink" property="nodeAccessories" 
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessimplement" />		   
			     </td>
		</tr>
         <%}} %>
       
		  <!-- 待归档 -->
          <%if( taskName.equals("HoldTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
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
          <%}%>

      <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 	
		
		<%}%>
		
			     
  </table>
		
<%if(taskName.equals("AuditTask")){ %>
   	<% if(operateType.equals("201") || operateType.equals("202") ){ %>    
		<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:数据制作角色
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6003" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]" 
			 />		       	
			 </div>	
			 </fieldset>
			
			
		 <fieldset id ="link2">
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:传输专线开通人
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test2"  type="role" roleId="6002" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]" 
			 />		       	
			 </div>	
			 </fieldset>
	   	
<%}} %> 
<%if(taskName.equals("MakeTask")){ %>
   	<% if(operateType.equals("203") ){ %>    
		<fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:网关数据制作角色
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6004" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'supplier1Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>
			 
			 <fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:支撑数据制作角色
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test2"  type="role" roleId="6005" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'supplier2Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>
			 			 <fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:BOSS数据录入角色
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test3"  type="role" roleId="6006" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'gatherDealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>
	   	
<%}} %> 
<%if(taskName.equals("BossMakeTask")){ %>
   	<% if(operateType.equals("207") ){ %>    
			<fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:BOSS数据确认人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6007" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>   	
<%}} %>
<%if(taskName.equals("DataAcceptTask")){ %>
   <%if(operateType.equals("208")||operateType.equals("11")){ %>
			<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName1">:MAS服务器开发人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6008" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>  
			

				<fieldset id ="link2">
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName2">:业务测试人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test2"  type="role" roleId="6010" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>   	 	
<%} %>
<%} %>
<%if(taskName.equals("InstallTask")){ %>
	<% if(operateType.equals("210") ){ %>
		<fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:业务测试人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6010" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		
			 </div>	
		 </fieldset>
	   
	 <%} %>		
<%} %>
<%if(taskName.equals("DevelopTask")){ %>
	<% if(operateType.equals("209") ){ %>
		<fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:MAS服务器安装人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6009" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		
			 </div>	
		 </fieldset>
	   
	 <%} %>		
<%} %>
<%if(taskName.equals("TestTask")){ %>
	<% if(operateType.equals("211") ){ %>
		<fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:业务测试人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6010" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		
			 </div>	
		 </fieldset>   
	 <%} %>		
<%} %>
<%if(taskName.equals("ImplementAcceptTask")){ %>
	<% if(operateType.equals("212") ){ %>
		<fieldset>
			 <legend>
			     	 <bean:message bundle="businessimplementsms" key="role.toOrgName"/>
					 <span id="roleName">:需求确认人员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
		   <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
		   <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemUserDao" />
	       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemDeptDao" />&nbsp;&nbsp;	</br>	
			 </div>	
		 </fieldset>
	   
	 <%} %>		
<%} %>
<%if(taskName.equals("TranferTask")){ %>
     <% if(operateType.equals("220")){ %>
  <fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:数据制作人
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="6003" flowId="333" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
  </fieldset>
	<% } %>

<%} %> 
<%if(taskName.equals("HoldTask")){ %>
		   
	
<%} %>
<script type="text/javascript">
	if('<%=operateType%>'=='220'){
		
		popOtherFlow('circuitdispatch',$('${sheetPageName}linkIfProvidedNeeds').value);

	}
	function popOtherFlow(input,value){
		if(input=='circuitdispatch'&&value=='101330101'){
			eoms.form.disableArea('BianHao',true);  
			document.getElementById('startCircuit').style.display='';
			document.getElementById('startCircuit2').style.display='';
			$('${sheetPageName}phaseId').value='waitReturn';
			$('${sheetPageName}operateType').value='111';
			chooser_test1.disable();
		}else if(input=='circuitdispatch'&&value!='101330101'){
			eoms.form.enableArea('BianHao');
		    document.getElementById('startCircuit').style.display='none';
			document.getElementById('startCircuit2').style.display='none';
			$('${sheetPageName}phaseId').value='MakeTask';
			$('${sheetPageName}operateType').value='222';
			chooser_test1.enable();
		}
	}
function onchangelinkIsDataOk(input){
   		var frm = document.forms[0];
	    var temp = frm.linkCircuitResult ? frm.linkCircuitResult.value : '';
	    
		if(temp != ''){
		   	if(input==101330602){
				eoms.form.enableArea('IsDataOk'); 
		    }else {
				eoms.form.disableArea('IsDataOk',true);
	 		}	  
		}
}
	function openwin(flag){
	
		var url;
		
		if(flag=="startTranfer") {
	
			url="${app}/sheet/businessimplement/businessimplement.do?method=showNewSheetPage"
			  +"&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessImplementSmsMainManager"
			  +"&title=<%=title%>"
			  +"&specialtyType=<%=specialtyType%>"
			  +"&isShowSms=yes"
			  +"&isCalledBySms=SmsTyes"
			  +"&startType=startTranfer"
			  +"&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}"
			  +"&orderSheetId=<%=orderSheetId%>";
		
			$('${sheetPageName}phaseId').value='waitReturn';
		}
		if(flag=="startIp") {
	
			url="${app}/sheet/businessimplement/businessimplement.do?method=showNewSheetPage"
			  +"&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessImplementSmsMainManager"
			  +"&title=<%=title%>"
			  +"&specialtyType=<%=specialtyType%>"
			  +"&isShowSms=yes"
			  +"&startType=startIp"
			  +"&isCalledBySms=yes"
			  +"&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}"
			  +"&orderSheetId=<%=orderSheetId%>";
			
			$('${sheetPageName}phaseId').value='waitReturn';
		}  
		window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
 </script>
 <script type="text/javascript">
 isNeedMas();
 function isNeedMas(input){
 
	if(input=="1"){
	
		$('${sheetPageName}phaseId').value='DevelopTask';
		$('${sheetPageName}operateType').value='209';
		chooser_test1.enable();
		chooser_test2.disable();				
		eoms.$('link1').show();
		eoms.$('link2').hide();	
	}else if(input=="0"){  
		$('${sheetPageName}phaseId').value='TestTask';
		$('${sheetPageName}operateType').value='211';
		eoms.$('link1').hide();
		chooser_test2.enable();
		chooser_test1.disable();
		eoms.$('link2').show();
	} 
	}
 </script>


