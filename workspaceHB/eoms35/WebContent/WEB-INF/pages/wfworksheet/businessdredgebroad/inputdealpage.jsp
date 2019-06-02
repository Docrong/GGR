<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.businessdredgebroad.task.impl.BusinessDredgebroadTaskImpl" %>

<%
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 

String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
System.out.println("=====taskName======"+taskName+"operateType==="+operateType);
String roleId = "";
String roleName = "";

String operateUserId="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("BusinessDredgebroad");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
 if(request.getAttribute("task")!=null){
	BusinessDredgebroadTaskImpl task = (BusinessDredgebroadTaskImpl)request.getAttribute("task");
	System.out.println("task piid is ====="+task.getProcessId());
	
}
 %>
 <script type="text/javascript">

function openwin(flag) {
	  var url;
	 
	  if(flag=="101050801") {
	  if($('${sheetPageName}linkNetType').value.indexOf('101050801')==-1){
	    $('${sheetPageName}linkNetType').value+='101050801'+',';
	    }
	    $('${sheetPageName}phaseId').value='receiveNet';	   
	    url="${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessDredgebroadMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
      } else if(flag=="101050802") {
       if($('${sheetPageName}linkNetType').value.indexOf('101050802')==-1){
	    $('${sheetPageName}linkNetType').value+='101050802'+',';
	    }
	    $('${sheetPageName}phaseId').value='receiveNet';
		
	    url="${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessDredgebroadMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
	   
      }else if(flag=="101050803") {
      if($('${sheetPageName}linkNetType').value.indexOf('101050803')==-1){
	    $('${sheetPageName}linkNetType').value+='101050803'+',';
	    }
	    $('${sheetPageName}phaseId').value='receiveNet';

	    url="${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessDredgebroadMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
     
      
      }else if(flag=="101050804") {
      if($('${sheetPageName}linkNetType').value.indexOf('101050804')==-1){
	    $('${sheetPageName}linkNetType').value+='101050804'+',';
	    }
	    $('${sheetPageName}phaseId').value='receiveNet';

	    url="${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessDredgebroadMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
      }
       window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');               
   }
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessdredgebroad/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("处理时限不能晚于工单完成时限")}'"/>
      <input type="hidden" name="${sheetPageName}beanId" value="iBusinessDredgebroadMainManager"/> 
      <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadMain"/>	
      <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadLink"/>
      <input type="hidden" name="${sheetPageName}linkBeanId" value="iBusinessDredgebroadLinkManager"/>
	  <c:if test="${taskName != 'HoldHumTask' }">
	    <c:choose>
		  	<c:when test="${preLink.activeTemplateId == 'TaskCreateAuditHumTask' }">
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="<%=operateUserId %>"/>
		  	</c:when>
		  	<c:otherwise>
		  	 <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
		  	</c:otherwise>
	    </c:choose>
     </c:if>
      		
		<%if(taskName.equals("TaskCreateAuditHumTask")){ %>	      
			<%if(operateType.equals("201")){ %>
             <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="201" />
         	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask" />		 
              <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.auditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
                    </td>
		      </tr> 
           <%}else if(operateType.equals("202")){ %>
         	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="202" />
         	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />         	     	 
              <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.auditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
                    </td>
		      </tr> 
           <%}%>

		   <%}else if(taskName.equals("TaskCompleteAuditHumTask")){ %>
		     <%if(operateType.equals("208")){ %>
               <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="203" />
         	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
         	   <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.auditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
                    </td>
		      </tr>			 
             <%}else if(operateType.equals("209")){ %>
         	   <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="204" />
         	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />  
         	   <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.auditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
                    </td>
		      </tr>      	 
           <%}%>
             
		   <%}else if(taskName.equals("ExcuteHumTask")){%>
		   
		     <%if(operateType.equals("111")){ 
		        //确认受理%>
		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111" />	
		        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receiveNet" />
    	       <tr>                 
  				  <td class="label">
  				     <bean:message bundle="sheet" key="common.dispatchType"/>*
  				  </td>
  				   <td class="content max" colspan=3>
  				     <input type="hidden" name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" value="" />
  				     <input type="button" class="btn" value="<eoms:id2nameDB id='101050801' beanId='ItawSystemDictTypeDao'/>" onclick="javascript:openwin('101050801')"> 
                     <input type="button" class="btn" value="<eoms:id2nameDB id='101050802' beanId='ItawSystemDictTypeDao'/>" onclick="javascript:openwin('101050802')"> 
                     <input type="button" class="btn" value="<eoms:id2nameDB id='101050803' beanId='ItawSystemDictTypeDao'/>" onclick="javascript:openwin('101050803')"> 
                     <input type="button" class="btn" value="<eoms:id2nameDB id='101050804' beanId='ItawSystemDictTypeDao'/>" onclick="javascript:openwin('101050804')"> 
                     
                  </td>	                  
	  		   </tr>	  
		      <%}else if(operateType.equals("61")){ %>
		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	        <!-- add by yangna -->
		         <input type="hidden" name="${sheetPageName}sheetNum" id="${sheetPageName}sheetNum" value="${sheetMain.sheetId}" />
		         <!-- end by yangna -->	
    	       <%--  <tr>
	             <td class="label">
	             <bean:message bundle="sheet" key="linkForm.remark" />
		         </td>
			     <td colspan="3">			
		          <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		           alt="width:'500px'">${sheetLink.remark}</textarea>
		         </td>
		      </tr>  
		      --%>	
		     
		     <%}else if(operateType.equals("205")){ //回复%>
        	  		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="205" />
        	  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
        	  		
<!-- add by qinbo begin -->
	<!-- gerenkuandai -->
	<logic:equal value="101100109" property="businesstype1" name="sheetMain">
		<tr>
			<td class="label">${eoms:a2u('业务开通时间 *')}</td>
			<td>
				<input type="text" class="text" name="${sheetPageName}loginUserName" readonly="readonly" 
					id="${sheetPageName}loginUserName" value="${eoms:date2String(sheetLink.loginUserName)}" 
					onclick="popUpCalendar(this, this)" alt="allowBlank:false" />  
			</td>
			<input type="hidden" name="${sheetPageName}apnID" id="${sheetPageName}apnID" value="${sheetMain.radiusValidateIPAdd}" />
			<td class="label">${eoms:a2u('业务序号')}</td>
			<td>
					<bean:write  name="sheetMain"  property="radiusValidateIPAdd"  scope="request"/>
			</td>
		</tr>
<!-- 		<tr>
			
			<td class="label">${eoms:a2u('用户姓名')}</td>
			<td>
				<input type="text"  class="text" name="${sheetPageName}loginUserName" id="${sheetPageName}loginUserName" value="${sheetMain.apnIPPool}" 
					alt="allowBlank:true,maxLength:32,vtext:'${eoms:a2u('请输入用户姓名，最大长度为16个汉字！')}'"/>
			</td>
		</tr>
		<tr>
			<td class="label">${eoms:a2u('用户账号')}</td>
			<td>
				<input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetMain.flowControlPriority}" 
					alt="allowBlank:true,maxLength:32,vtext:'${eoms:a2u('请输入用户账号，最大长度为16个汉字！')}'"/>
			</td>
			<td class="label">${eoms:a2u('用户联系电话')}</td>
			<td>
				<input type="text"  class="text" name="${sheetPageName}nodeAccessories" id="${sheetPageName}nodeAccessories" value="${sheetMain.doubleGGSN}" 
					alt="allowBlank:true,maxLength:32,vtext:'${eoms:a2u('请输入用户联系电话，最大长度为16个汉字！')}'"/>
			</td>
		</tr>    -->
		<tr>
			<td class="label">${eoms:a2u('装机地址')}</td>
			<td colspan ='3'><input type="text"  class="text" name="${sheetPageName}rejectReason" id="${sheetPageName}rejectReason" value="${sheetLink.rejectReason}" 
			alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请输入装机地址，最大长度为500个汉字！')}'"/></td>
		</tr>
		<tr>
			<td class="label">${eoms:a2u('施工人员姓名/工号')}</td>
			<td>
				<input type="text"  class="text" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult" value="${sheetLink.auditResult}" 
					alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请输入后端施工人员姓名/工号，最大长度为500个汉字！')}'"/>
			</td>
			<td class="label">${eoms:a2u('施工人员所属部门')}</td>
			<td>
				<input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}" 
					alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请输入施工人员所属部门，最大长度为500个汉字！')}'"/>
			</td>
		</tr>
		<tr>
			<td class="label">${eoms:a2u('施工人员联系方式')}</td>
			<td colspan ='3'>
				<input type="text"  class="text" name="${sheetPageName}loginUserPassWord" id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}" 
					alt="allowBlank:true,maxLength:32,vtext:'${eoms:a2u('请输入施工人员联系方式，最大长度为16个汉字！')}'"/>
			</td>
<!-- 			<td class="label">${eoms:a2u('施工情况')}</td><!-- netResCapacity 1011060 -->
<!-- 			<td>
				<eoms:comboBox name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity"  defaultValue="${sheetLink.netResCapacity}"
		     	      initDicId="1011060"  alt="allowBlank:true" styleClass="select-class"/>
			</td>    -->
		</tr>
		<tr>
			<td class="label">${eoms:a2u('回复类型')}</td><!-- clientPgmCapacity 1011061 -->
			<td>
				<eoms:comboBox name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity"  defaultValue="${sheetLink.clientPgmCapacity}"
		     	      initDicId="1011061"  alt="allowBlank:false" styleClass="select-class"/>
			</td>
			<td class="label">${eoms:a2u('待装时间')}</td><!-- completeTime -->
			<td>
				<input type="text" class="text" name="${sheetPageName}expectFinishdays" readonly="readonly" 
					id="${sheetPageName}expectFinishdays" value="${eoms:date2String(sheetLink.expectFinishdays)}" 
					onclick="popUpCalendar(this, this)" alt="allowBlank:true" />  
			</td>
		</tr>
		<tr>
			<td class="label">${eoms:a2u('完成原因类型*')}</td><!-- linkNetType -->
			<td colspan ='3'>
				<eoms:comboBox name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType"  defaultValue="${sheetLink.linkNetType}"
		     	      initDicId="1011062"  alt="allowBlank:false" styleClass="select-class"/>
			</td>
		</tr>
		<tr>
			<td class="label">${eoms:a2u('施工完成原因')}</td>
			<td colspan ='3'>
				<textarea class="textarea max" name="${sheetPageName}remark" id="${sheetPageName}remark" 
		        alt="allowBlank:true,width:500,maxLength:2000,vtext:'${eoms:a2u('请最多输入1000字')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
			</td>
		</tr>
		<tr>
			<td class="label">${eoms:a2u('满意度')}</td><!-- testReport -->
			<td>
				<input type="text"  class="text" name="${sheetPageName}testReport" id="${sheetPageName}testReport" value="${sheetLink.testReport}" 
					alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('请输入满意度，最大长度为10个汉字！')}'"/>
			</td>
			<td class="label">${eoms:a2u('客户意见')}</td><!-- dealResult -->
			<td>
				<input type="text"  class="text" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason" value="${sheetLink.transferReason}" 
					alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请输入客户意见，最大长度为500个汉字！')}'"/>
			</td>
		</tr>
	</logic:equal>

<!-- add by qinbo end -->

			<!-- GPRS -->
		<logic:equal value="101100101" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.apnID"/>*</td>
				         <td  class="content" colspan ='3'> 
				         <input type="text"  class="text" name="${sheetPageName}apnID" id="${sheetPageName}apnID" value="${sheetLink.apnID}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入apnID，最大长度为100个汉字！')}'"/>
				         </td>
				</tr>
				         
		</logic:equal>
		
		          <!-- MMS -->
<logic:equal value="101100102" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserName"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserName" id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关用户名，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserPassWord"/>*</td>
		            <td class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserPassWord" id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关密码，最大长度为100个汉字！')}'"/></td>
		          </tr>	
		          <!-- add by yangna -->
				       <tr>
				         <td  class="label">${eoms:a2u('业务代码')}</td>
				         <td  class="content">
				         <input type="text" class="text" name="${sheetPageName}tempBusinessCode" id="${sheetPageName}tempBusinessCode" value="" />
			             </td>
			             <td  class="label">${eoms:a2u('MAS ID')}</td>
				         <td  class="content">
				         <input type="text" class="text" name="${sheetPageName}tempMasId" id="${sheetPageName}tempMasId" value="" />
			             </td>
			           </tr>
			           <tr>
			             <td  class="label">${eoms:a2u('帐户密码')}</td>
				         <td  class="content" colspan ='3'>
				         <input type="text" class="text" name="${sheetPageName}tempPassword" id="${sheetPageName}tempPassword" value="" />
			             </td> 
			          </tr>	
			          <!-- end by yangna -->
</logic:equal>		          
		          <!-- SMS -->
<logic:equal value="101100103" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserName"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserName" id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关用户名，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserPassWord"/>*</td>
		            <td class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserPassWord" id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关密码，最大长度为100个汉字！')}'"/></td>
		          </tr>	
		          <!-- add by yangna -->
				       <tr>
				         <td  class="label">${eoms:a2u('业务代码')}</td>
				         <td  class="content">
				         <input type="text" class="text" name="${sheetPageName}tempBusinessCode" id="${sheetPageName}tempBusinessCode" value="" />
			             </td>
			             <td  class="label">${eoms:a2u('MAS ID')}</td>
				         <td  class="content">
				         <input type="text" class="text" name="${sheetPageName}tempMasId" id="${sheetPageName}tempMasId" value="" />
			             </td>
			           </tr>
			           <tr>
			             <td  class="label">${eoms:a2u('帐户密码')}</td>
				         <td  class="content" colspan ='3'>
				         <input type="text" class="text" name="${sheetPageName}tempPassword" id="${sheetPageName}tempPassword" value="" />
			             </td> 
			          </tr>	
			          <!-- end by yangna -->
</logic:equal>	
		          <!-- chuanshu -->
  <logic:equal value="101100104" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode"  value="${sheetLink.circuitCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
  <!-- 语音专线 -->
  <logic:equal value="101100106" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode"  value="${sheetLink.circuitCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
  <!-- 城域网宽带 -->
  <logic:equal value="101100107" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode"  value="${sheetLink.circuitCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
  <!-- GPRS专线 -->
  <logic:equal value="101100108" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode"  value="${sheetLink.circuitCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.ndeptContact"/>*</td>
		            <td  class="content"> 
		             <input type="text"  class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"  value="${sheetLink.ndeptContact}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为100个汉字！')}'"/></td>
		           <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.ndeptContactPhone"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为100个汉字！')}'"/></td>
		          </tr>
		           <tr>
		             <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealResult"/>*</td>
		                <td colspan ='3'>  
				        <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult" defaultValue="${sheetLink.dealResult}"
            	      initDicId="1010401"  alt="allowBlank:false" styleClass="select-class"/>
			        </td>	
			        </tr>
			        <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入处理说明，最大长度为1000个汉字！')}'">${sheetLink.dealDesc}</textarea>
                    </td>
		          </tr>
		       	
			   <tr>
     			<td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
     		    <td colspan="3">
     			 <eoms:attachment name="sheetLink" property="nodeAccessories" 
                scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessdredgebroad" />
			   </td>
		     </tr>   
        	    <%}else if(operateType.equals("206")){ //回复送审%>
        	  		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206" />
        	        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask" />
        	      <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.ndeptContact"/>*</td>
		            <td  class="content"> 
		             <input type="text"  class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact" value="${sheetLink.ndeptContact}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为100个汉字！')}'"/></td>
		           <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.ndeptContactPhone"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          			<!-- GPRS -->
		<logic:equal value="101100101" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.apnID"/>*</td>
				         <td  class="content" colspan ='3'> 
				         <input type="text"  class="text" name="${sheetPageName}apnID" id="${sheetPageName}apnID" value="${sheetLink.apnID}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入apnID，最大长度为100个汉字！')}'"/>
				         </td>
			       </tr>	
		</logic:equal>
		
		          <!-- MMS -->
<logic:equal value="101100102" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserName"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserName" id="${sheetPageName}loginUserName" value="${sheetLink.loginUserName}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关用户名，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserPassWord"/>*</td>
		            <td class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserPassWord" id="${sheetPageName}loginUserPassWord" value="${sheetLink.loginUserPassWord}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关密码，最大长度为100个汉字！')}'"/></td>
		          </tr>	
</logic:equal>		          
		          <!-- SMS -->
<logic:equal value="101100103" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserName"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserName" id="${sheetPageName}loginUserName"  value="${sheetLink.loginUserName}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关用户名，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserPassWord"/>*</td>
		            <td class="content"> <input type="text"  class="text" name="${sheetPageName}loginUserPassWord" id="${sheetPageName}loginUserPassWord"  value="${sheetLink.loginUserPassWord}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入登录网关密码，最大长度为100个汉字！')}'"/></td>
		          </tr>	
</logic:equal>	
		          <!-- chuanshu -->
  <logic:equal value="101100104" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity"  value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity"   value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
   <!-- 语音专线  -->
  <logic:equal value="101100106" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity"  value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity"   value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
   <!-- 城域网宽带 -->
  <logic:equal value="101100107" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity"  value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity"   value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
   <!-- GPRS专线 -->
  <logic:equal value="101100108" property="businesstype1" name="sheetMain">        
     	          <tr >
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}expectFinishdays" id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为100个汉字！')}'"/></td>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/></td>
		          </tr>
		          
		          <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity"  value="${sheetLink.netResCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}clientPgmCapacity" id="${sheetPageName}clientPgmCapacity"   value="${sheetLink.clientPgmCapacity}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为100个汉字！')}'"/></td>
		          
		          </tr>
  </logic:equal>	
		           <tr>
		            
		             <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealResult"/>*</td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult" defaultValue="${sheetLink.dealResult}"
            	      initDicId="1010401"  alt="allowBlank:false" styleClass="select-class"/>
			        </td>	
			       </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc" alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入处理说明，最大长度为1000个汉字！')}'">${sheetLink.dealDesc}</textarea>
                    </td>
		          </tr>
		       <tr>
     			<td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
     		    <td colspan="3">
     			 <eoms:attachment name="sheetLink" property="nodeAccessories" 
                scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessdredgebroad" />
			   </td>
		     </tr>   
        	    <%}else if(operateType.equals("10")){ //分派%>
        	    	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="10" />
        	    	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
        	       <!-- add by yangna -->
        	       <!-- MMS 彩信（业务类型字典还要改）-->
                <logic:equal value="101100102" property="businesstype1" name="sheetMain">
                 <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.SIEnterpriseCode"/>*</td>
    				<td  class="content"><input type="text"  class="text" name="${sheetPageName}siEnterpriseCode" id="${sheetPageName}siEnterpriseCode" value="${sheetMain.siEnterpriseCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI企业代码，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.SIServerCode"/>*</td>
    				<td  class="content"><input type="text"  class="text" name="${sheetPageName}siServerCode" id="${sheetPageName}siServerCode" value="${sheetMain.siServerCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入EC/SI名称，最大长度为100个汉字！')}'"/></td>
		         </tr>
				 <tr>
		            <td  class="label">${eoms:a2u('厂商编码')}*</td>
    				<td  class="content"> <input type="text"  class="text" name="${sheetPageName}mainFactoryCode" id="${sheetPageName}mainFactoryCode"  value="${sheetMain.mainFactoryCode}" alt="allowBlank:false,maxLength:100,vtext:'${eoms:a2u('请输入厂商编码，最大长度为100个字符！')}'"/></td>
                    <td  class="label">${eoms:a2u('业务类型')}*</td>
    				<td  class="content"> 
    				<eoms:comboBox name="${sheetPageName}mainBusinessType" id="${sheetPageName}mainBusinessType" 
            	      initDicId="1011053"  alt="allowBlank:false" defaultValue="${sheetMain.mainBusinessType}" styleClass="select-class"/>
    				</td>
		         </tr>
                </logic:equal>		          
		          <!-- SMS 短信 （业务类型字典还要改）-->
               <logic:equal value="101100103" property="businesstype1" name="sheetMain">
				<tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.enterpriseCode"/>*</td>
    				<td  class="content"><input type="text"  class="text" name="${sheetPageName}enterpriseCode" id="${sheetPageName}enterpriseCode" value="${sheetMain.enterpriseCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入企业代码，最大长度为100个汉字！')}'"/></td>
                    <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.serverCode"/>*</td>
    				<td  class="content"><input type="text"  class="text" name="${sheetPageName}serverCode" id="${sheetPageName}serverCode" value="${sheetMain.serverCode}" alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入服务代码，最大长度为100个汉字！')}'"/></td>
		         </tr>
				 <tr>
		            <td  class="label">${eoms:a2u('厂商编码')}*</td>
    				<td  class="content"> <input type="text"  class="text" name="${sheetPageName}mainFactoryCode" id="${sheetPageName}mainFactoryCode"  value="${sheetMain.mainFactoryCode}" alt="allowBlank:false,maxLength:100,vtext:'${eoms:a2u('请输入厂商编码，最大长度为100个字符！')}'"/></td>
                    <td  class="label">${eoms:a2u('业务类型')}*</td>
    				<td  class="content"> 
    				<eoms:comboBox name="${sheetPageName}mainBusinessType" id="${sheetPageName}mainBusinessType" 
            	      initDicId="1011053"  alt="allowBlank:false" defaultValue="${sheetMain.mainBusinessType}" styleClass="select-class"/>
    				</td>
		         </tr>
               </logic:equal>
		          <!-- end by yangna -->
        	       <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.transmitReason"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason" alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入转派理由，最大长度为1000个汉字！')}'">${sheetLink.transferReason}</textarea>
                    </td>
		          </tr>
		          <tr>
     			<td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
     		    <td colspan="3">
     			 <eoms:attachment name="sheetLink" property="nodeAccessories" 
                scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessdredgebroad" />
			   </td>
		     </tr>   
        	    <% }else if(operateType.equals("8")){//移交 %>
         			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8" />
         			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask" />
         			  <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.yijiaoresion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入移交理由，最大长度为1000个汉字！')}'">${sheetLink.transferReason}</textarea>
                    </td>
		          </tr>
		           <tr>
     			<td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
     		    <td colspan="3">
     			 <eoms:attachment name="sheetLink" property="nodeAccessories" 
                scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessdredgebroad" />
			   </td>
		     </tr>   
               
                <%}%>
           <%}else if( taskName.equals("AffirmHumTask") ){%>
         	 <%if(operateType.equals("7")){ %>
         		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7" />
         		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         		  <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc" alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入处理意见，最大长度为1000个汉字！')}'">${sheetLink.dealDesc}</textarea>
                    </td>
		          </tr>
         		 
            <%}else if(operateType.equals("6")){ %>
         		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="6" />
         		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive" />
         		  <tr>
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealResult"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"  alt="width:500,allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('请输入处理意见，最大长度为1000个汉字！')}'">${sheetLink.dealDesc}</textarea>
                    </td>
		          </tr>
         		 
            <%}%>
         <%}else if( taskName.equals("DraftHumTask")){%>
         		
             <%if(operateType.equals("22")){ %>
         			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="22" />
         			 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask" />
         			 
         			 
              <%}%>
              
          <%}else if( taskName.equals("HoldHumTask")){%>
         	 <%if(operateType.equals("18")){ %>
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
         		<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
         		 <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />	 
	 		 <tr>
			  	<td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.holdStatisfied"/>*</td>
			    <td colspan="3">
			      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
			        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" defaultValue="${sheetMain.holdStatisfied}"  styleClass="select" alt="allowBlank:false"/>
			    </td>
			  </tr>
			  
              <tr>
			      
		            <td  class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.endResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}endResult" id="${sheetPageName}endResult" value="${sheetMain.endResult}" alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入归档意见，最大长度为1000个汉字！')}'">${sheetMain.endResult}</textarea>
                    </td>
		          </tr>
        			 
              <%}%>
           <%}else if(taskName.equals("cc")){%>
     		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
	 	
	 		 <tr>
			    <td class="label"><bean:message bundle="businessdredgebroad" key="businessdredgebroad.remark" />*</td>
			    
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'${eoms:a2u('请最多输入1000字')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
	 		 </tr>       
			   
		   <%}%>
	 <%if(taskName.equals("TaskCreateAuditHumTask")||taskName.equals("ExcuteHumTask")||taskName.equals("TaskCompleteAuditHumTask")||taskName.equals("AffirmHumTask")) {%>      
         
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />		
		<!-- add by yangna -->
		<input type="hidden" name="${sheetPageName}sheetNum" id="${sheetPageName}sheetNum" value="${sheetMain.sheetId}" />
		<!-- end by yangna -->					
    	<%-- <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:1000,vtext:'${eoms:a2u('请最多输入1000字')}'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	--%>
		
		<%} }%>
		 
		     <% if(operateType.equals("4")){ %>
		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />	
		        <c:choose> 
		  	    <c:when test="${taskName=='TaskCreateAuditHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
				<c:when test="${taskName=='ExcuteHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
				</c:when>
				<c:when test="${taskName=='TaskCompleteAuditHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
				</c:when>				
			   </c:choose>						
    	        <tr>
	             <td class="label">
	               <bean:message bundle="businessdredgebroad" key="businessdredgebroad.rejectCause" />*
		         </td>
			     <td colspan="3">			
		          <textarea name="${sheetPageName}rejectReason" class="textarea max" id="${sheetPageName}rejectReason"  
		           alt="allowBlank:false,width:500,maxLength:2000,vtext:'${eoms:a2u('请输入驳回原因，最多输入1000字')}'">${sheetLink.rejectReason}</textarea>
		         </td>
		      </tr> 
		      <%} %>
  </table>
  
<%if(taskName.equals("cc")) {%>	
		<fieldset id="link4">
	<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
		</fieldset>					
<%} %>  
<%if(taskName.equals("TaskCreateAuditHumTask")){ %>
	<% if(operateType.equals("201")){ %>	       
 <fieldset>
	 <legend>
	     	 <bean:message bundle="businessdredgebroad" key="businessdredgebroad.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="businessdredgebroad" key="businessdredgebroad.excute"/>
			 </span>
	 </legend>
		<eoms:chooser id="test"
		   category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"
		   data="${sendUserAndRoles}"/>

   </fieldset>  
		       
<%} }%>
<%if(taskName.equals("ExcuteHumTask")){ %>
<% if(operateType.equals("8")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="businessdredgebroad" key="businessdredgebroad.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="businessdredgebroad" key="businessdredgebroad.excute"/>
			 </span>
	 </legend>


			<eoms:chooser id="test"
			   category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"/>
	
   </fieldset>
<% } %>
<% if(operateType.equals("10")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="businessdredgebroad" key="businessdredgebroad.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="businessdredgebroad" key="businessdredgebroad.excute"/>
			 </span>
	 </legend>

		<eoms:chooser id="test"
		   category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"/>

   </fieldset>
<% } %>
<% if(operateType.equals("206")){ //回复送审%>
   <fieldset>
	 <legend>
	      <bean:message bundle="businessdredgebroad" key="businessdredgebroad.toOrgName"/>
		   <span id="roleName">:<bean:message bundle="businessdredgebroad" key="businessdredgebroad.TaskCompleteAudit"/> </span>
	 </legend>
      <eoms:chooser id="test"
		category="[{id:'dealPerformer',text:'${eoms:a2u('审核')}',childType:'user',limit:'1',allowBlank:false,vtext:'${eoms:a2u('请选择审核对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]" 
		/>

 </fieldset>
<% } %>
<% if(operateType.equals("46")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="businessdredgebroad" key="businessdredgebroad.toOrgName"/>
			 <span id="roleName">:${eoms:a2u('上一级执行者')}
			 </span>
	 </legend>
   </fieldset>
<% } %>
<%} %>
