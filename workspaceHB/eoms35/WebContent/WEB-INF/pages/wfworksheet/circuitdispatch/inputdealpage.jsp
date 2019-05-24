<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
 <%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
  <%@page import="com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink"%>
 <%
 String enableService = com.boco.eoms.base.util.StaticMethod.nullObject2String(com.boco.eoms.commons.util.xml.XmlManage
					.getFile("/config/circuitdispatch-util.xml").getProperty(
							"EnableService"));
 CircuitDispatchLink preLink = (CircuitDispatchLink)request.getAttribute("preLink");
 String linkRejectFlag = com.boco.eoms.base.util.StaticMethod.null2String(preLink.getLinkRejectFlag());
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<script type="text/javascript">
	function getUrl(operateType)
	{
	    if('<%=enableService%>'=='true'){
			var url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=getIrmsUrl&operateType="+operateType+"&sheetId=${sheetMain.sheetId}&taskId=${task.id}";

			if(operateType=='design'){
				var linkRejectFlag = '<%=linkRejectFlag%>';
				if(linkRejectFlag!='1'){
					var nodeCompleteLimit = $('linkExecuteEndDate').value;		
	
					if(nodeCompleteLimit==""){
						alert('请先选择完成时限');
						return;
					}
	
					var date = new Date(Date.parse(nodeCompleteLimit.replace(/-/g,"/"))); 
	
					url = url + "&completeTime="+date.getTime();
				}
			}
			window.open(url);
		}else{
			alert('该接口暂时尚未开放，请与管理员联系');
		}
		
	}	
	function handleCallBack(originalRequest) //回调函数，对服务端的响应处理，监视response状态
	{
		if('<%=enableService%>'=='true'){
			var url=originalRequest.responseText;
			window.open(url);
		}else{
			alert('该接口暂时尚未开放，请与管理员联系');
		}
	}
	function upload(){
	
		var attachId = $('nodeAccessories').value;
		if(attachId=='')
			alert('请先上传附件');
		else{
			var sheetId = $('sheetId').value;
			Ajax.Request(
			  "${app}/sheet/circuitdispatch/circuitdispatch.do?method=uploadFile",
			  { 
		  		method:"GET",
		  		parameters:"&sheetId="+sheetId+"&attachId="+attachId,
		  		onComplete: uploadCallBack
			  }
		  	);
		}
	}
	function uploadCallBack(originalRequest) //回调函数，对服务端的响应处理，监视response状态
	{
		var result = originalRequest.responseText;
		
		if(result=='0'){
			alert('上传成功');
		}else{
			alert('上传失败：'+result);
		}
	}
</script>
  
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 String fPreTaskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("fPreTaskName"));
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String taskId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("TKID"));
  System.out.println("=====taskId======"+taskId);
  String acceptLimit = com.boco.eoms.base.util.StaticMethod.getLocalString(2);
  String completeLimit = com.boco.eoms.base.util.StaticMethod.getLocalString(3);
  String eAcceptLimit = com.boco.eoms.base.util.StaticMethod.getLocalString(3);
  String eCompleteLimit = com.boco.eoms.base.util.StaticMethod.getLocalString(7);
 %> 

    <%@ include file="/WEB-INF/pages/wfworksheet/circuitdispatch/baseinputlinkhtmlnew.jsp"%>
    <br/>
	      <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName" value="CircuitDispatchMainFlowProcess" />
          <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate" />
          <input type="hidden" name="${sheetPageName}beanId" value="iCircuitDispatchMainManager"/>
          <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchMain"/>	<!--main表Model对象类名-->	
          <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.circuitdispatch.model.CircuitDispatchLink"/> <!--link表Model对象类名-->
          <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
          <input type="hidden" name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne" value="${sheetMain.mainNetSortOne}" />
		  <input type="hidden" name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo" value="${sheetMain.mainNetSortTwo}" />
		  <input type="hidden" name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree" value="${sheetMain.mainNetSortThree}" />
		  <input type="hidden" name="${sheetPageName}mainEquipmentFactory" id="${sheetPageName}mainEquipmentFactory" value="${sheetMain.mainEquipmentFactory}" />
		  
		  <c:if test="${taskName != 'HoldTask' }">
			  <%if(operateType.equals("113")){ %>
			  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${ProjectCreateTaskOperateroleid}"/>
			  <%}else{ %>		
			  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>  
			  <%} %>
		  </c:if>
     <table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
     <%if(taskName.equals("ProjectCreateTask")) {%>
           <%if(operateType.equals("110")||operateType.equals("11")){ %>
           <%if(!linkRejectFlag.equals("1")){ %>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask" />
     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
		<tr>
     		<!-- 实施完成时限 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExecuteEndDate"/>*</td>
     		<td colspan="3">
    			<input type="text" class="text" name="${sheetPageName}linkExecuteEndDate" readonly="readonly" 
					id="${sheetPageName}linkExecuteEndDate" value="${eoms:date2String(sheetLink.linkExecuteEndDate)}" 
					onclick="popUpCalendar(this, this);" alt="vtype:'lessThen',link:'tmpCompleteLimit',vtext:'实施完成时限不能晚于工单完成时限',allowBlank:false"/>
			  </td>
		</tr>
		<tr>
		<!-- 变更涉及省份  -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedProvince"/>*</td>
			  <td class="content">
			      <fmt:message key="webapp.province"/>
			  	  <input type="hidden" name="${sheetPageName}linkInvolvedProvince" 
			  	   id="${sheetPageName}linkInvolvedProvince" 
			  	   value="<fmt:message key='webapp.province'/>" 
			  	   alt="allowBlank:false"/>
			  </td>
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedCity"/>*</td>              
               <td>
			  	<div id="areaview" class="hide"></div>
			    <script type="text/javascript">
	             
	            //viewer
				var areaViewer = new Ext.JsonView("areaview",
					'<div class="viewlistitem-{nodeType}">{name}</div>',
					{ 
						emptyText : '<div>没有选择项目</div>'
				}
				);
				var data = "[{id:'${sheetLink.linkInvolvedCity}',name:'<eoms:id2nameDB id='${sheetLink.linkInvolvedCity}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
				areaViewer.jsonData = eoms.JSONDecode(data);
				areaViewer.refresh();
				 
				//area tree
	            var	deptTreeAction='${app}/xtree.do?method=areaTree';
	            deptetree = new xbox({

    	          btnId:'${sheetPageName}showDept',dlgId:'dlg3',

    	          treeDataUrl:deptTreeAction,treeRootId:'-1',treeRootText:'地市',treeChkMode:'single',treeChkType:'area',
    	          showChkFldId:'${sheetPageName}showDept',saveChkFldId:'${sheetPageName}linkInvolvedCity',viewer:areaViewer
	            });
               </script>

               <input type="text" class="text"  readonly="readonly" name="${sheetPageName}showDept" id="${sheetPageName}showDept" alt="allowBlank:false,vtext:'请选择地域名称'" value="<eoms:id2nameDB id='${sheetLink.linkInvolvedCity}' beanId='tawSystemAreaDao'/>"/>
               <input type="hidden" name="${sheetPageName}linkInvolvedCity" id="${sheetPageName}linkInvolvedCity" value="${sheetLink.linkInvolvedCity}"/>			  
                </td>
			</tr>
			
		 <!-- <tr>
           变更涉及省份 
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedProvince"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}linkInvolvedProvince" id="${sheetPageName}linkInvolvedProvince" initDicId="1011608" defaultValue="${sheetLink.linkInvolvedProvince}" alt="allowBlank:false"/>
			  </td>
			  变更涉及地市 
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkInvolvedCity"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkInvolvedCity" id="${sheetPageName}linkInvolvedCity" initDicId="1011608" defaultValue="${sheetLink.linkInvolvedCity}" alt="allowBlank:false"/>
			  </td>
			</tr>-->
	<!--	<tr>
			 需求详细说明
			<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkServiceResData"/>*</td>
     		<td colspan="3">
    			<eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="circuitdispatch" 
		             viewFlag="Y"/>
		        <a href="#" onclick=""><bean:message bundle="circuitdispatch" key="button.putData"/></a>
		        <!-- <html:button styleClass="btn" property="putData" onclick="">
		        	<bean:message bundle='circuitdispatch' key='button.putData'/>
		        </html:button>
			  </td>
		</tr> -->
		<tr>
     		<!-- 技术方案附件 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectAcc"/></td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
              <input type="button"  styleClass="button" onclick="upload();" value="上传方案"/>
			  </td>
		</tr>
		<tr>
			  <!-- 方案设计 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="button.projectCreate"/>*</td>
			  <td class="content">
			  <a onclick="javascript:getUrl('design')"><bean:message bundle='circuitdispatch' key='button.projectCreate'/></a>
    			<!-- <html:button styleClass="btn" property="projectCreate" onclick="">
		        	<bean:message bundle='circuitdispatch' key='button.projectCreate'/>
		        </html:button> -->
			  </td>
     		<!-- 资源方案号 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.mainResProjectNo"/></td>
     		<td>
    			<input type="text" class="text" readonly="readonly" name="${sheetPageName}mainResourceNo" id="${sheetPageName}mainResourceNo" value="${sheetMain.mainResourceNo}" alt="allowBlank:true"/>
			  </td>
		</tr>
     	<tr>
     		<!-- 技术方案关键字 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectKey"/>*</td>
     		<td colspan="3">
    			<input type="text" class="text" name="${sheetPageName}linkProgrammeKey" id="${sheetPageName}linkProgrammeKey" value="${sheetLink.linkProgrammeKey}" alt="allowBlank:false,maxLength:40,vtext:'请填入技术方案关键字，最多输入40个字符'"/>
			  </td>
     	</tr>
     	<tr>
     		<!-- 技术方案说明 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkProjectExplain"/></td>
     		<td colspan="3">
     			<textarea name="linkProgrammeExplain" id="linkProgrammeExplain" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'请填入技术方案说明，最多输入2000个字符'">${sheetLink.linkProgrammeExplain}</textarea>
			  </td>
		</tr>
     	<tr>
     		<!-- 风险评估 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkRiskEvaluate"/>*</td>
     		<td colspan="3">
     			<textarea name="linkRiskEvaluate" id="linkRiskEvaluate" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入风险评估，最多输入2000个字符'">${sheetLink.linkRiskEvaluate}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- 影响业务分析 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkOperationConstrue"/>*</td>
     		<td colspan="3">
     			<textarea name="linkOperationConstrue" id="linkOperationConstrue" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入影响业务分析，最多输入2000个字符'">${sheetLink.linkOperationConstrue}</textarea>
			  </td>
     	</tr>

		 <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		  </tr>	
     	
		<%}else{ %>
		           <input type="hidden" name="linkRejectFlag" id="linkRejectFlag" value="${preLink.linkRejectFlag}" />
		<tr>
			  <!-- 方案设计 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="button.projectCreate"/>*</td>
			  <td class="content" colspan="3">
			  <a onclick="javascript:getUrl('design')"><bean:message bundle='circuitdispatch' key='button.projectCreate'/></a>
    			<!-- <html:button styleClass="btn" property="projectCreate" onclick="">
		        	<bean:message bundle='circuitdispatch' key='button.projectCreate'/>
		        </html:button> -->
			  </td>
		</tr>
		<%} %>
		<%}%>

     <%}else if(taskName.equals("PermitTask")){%>
     <%if(operateType.equals("131")||operateType.equals("55")){ %>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PlanTask" />
     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
     
	<tr>
	       <!-- 接单时限 -->
		<td class="label">
			<bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		</td>
		<c:choose>
			<c:when test="${empty sheetLink.nodeAcceptLimit }">
				<td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="<%=acceptLimit %>" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td>
			</c:when>
			<c:otherwise>
				<td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td>
			</c:otherwise>
		</c:choose>
		<!-- 完成时限 -->
		<td class="label">		     
		    <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		</td>
		<c:choose>
			<c:when test="${empty sheetLink.nodeCompleteLimit }"><td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="<%=completeLimit %>" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td></c:when>
			<c:otherwise><td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td></c:otherwise>
		</c:choose>
	</tr>
		
		<tr>
     		<!-- 查看设计结果 -->
     		<td class="label">查看设计结果</td>
     		<td colspan="3">
     			 <a onclick="javascript:getUrl('designinfo')">浏览设计方案</a>
			  </td>
		</tr>
        <tr>
     		<!-- 审批结果 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPermitResult"/>*</td>
     		<td colspan="3">
     			<eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult" initDicId="1010908" defaultValue="${sheetLink.linkPermitResult}" alt="allowBlank:false" onchange="ifPermitPass(this.value);"/>
			  </td>
		</tr>
        <tr>
     		<!-- 审批意见 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPermitIdea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkPermitIdea" id="linkPermitIdea" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入审批意见，最多输入2000个字符'">${sheetLink.linkPermitIdea}</textarea>
			  </td>
		</tr>
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
     		<!-- 附件 -->
     		<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
		<%} %>
     <%}else if(taskName.equals("PlanTask")){%>
     <%if(operateType.equals("112")||operateType.equals("11")){ %>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask" />
     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
	<tr>
	    <!-- 接单时限 -->
		<td class="label">
			<bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		</td>
		<c:choose>
			<c:when test="${empty sheetLink.nodeAcceptLimit }">
				<td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="<%=acceptLimit %>" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td>
			</c:when>
			<c:otherwise>
				<td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td>
			</c:otherwise>
		</c:choose>
		<!-- 完成时限 -->
		<td class="label">		     
		    <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		</td>
		<c:choose>
			<c:when test="${empty sheetLink.nodeCompleteLimit }">
				<td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="<%=completeLimit %>" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td>
			</c:when>
			<c:otherwise>
				<td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td>
			</c:otherwise>
		</c:choose>
	</tr>
     	<tr>
     		<!-- 实施负责人 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcutePrincipal"/>*</td>
     		<td class="content">
    			<input type="text" class="text" name="${sheetPageName}linkExcutePrincipal" id="${sheetPageName}linkExcutePrincipal" value="${sheetLink.linkExcutePrincipal}" alt="allowBlank:false,maxLength:40,vtext:'请填入实施负责人，最多输入40个字符'"/>
			  </td>
     		<!-- 联系方式 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkContact"/>*</td>
     		<td>
    			<input type="text" class="text" name="${sheetPageName}linkContact" id="${sheetPageName}linkContact" value="${sheetLink.linkContact}" alt="allowBlank:false,maxLength:40,vtext:'请填入联系方式，最多输入40个字符'"/>
			  </td>
     	</tr>
     	<tr>
     		<!-- 排程看板 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.planSheet"/></td>
     		<td colspan="3">
     			<a href="#" onclick="popModifyTime()"><bean:message bundle="circuitdispatch" key="circuitdispatch.lookPlanSheet"/></a>
			  </td>
		</tr>
		   <tr>
		   <!-- 计划开始时间 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanStartDate"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}linkPlanStartDate" readonly="readonly" 
					id="${sheetPageName}linkPlanStartDate" onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}linkPlanEndDate',vtext:'计划开始时间不能晚于计划结束时间',allowBlank:false"/>
			  </td>
			  <!-- 计划结束时间 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanEndDate"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}linkPlanEndDate" readonly="readonly" 
					id="${sheetPageName}linkPlanEndDate" onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}linkPlanStartDate',vtext:'计划结束时间不能早于计划开始时间',allowBlank:false"/> 
			  </td>
			</tr>
			<tr>
	     		<!-- 查看设计结果 -->
	     		<td class="label">查看设计结果</td>
	     		<td colspan="3">
	     			 <a onclick="javascript:getUrl('designinfo')">浏览设计方案</a>
				  </td>
			</tr>
            <!-- 是否影响业务 -->
            <tr>
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfAffectOperation"/></td>
			  <td colspan="3">
			  	 <eoms:comboBox name="${sheetPageName}linkIfAffectOperation" id="${sheetPageName}linkIfAffectOperation" initDicId="10301" defaultValue="${sheetLink.linkIfAffectOperation}" alt="allowBlank:true"/>
			  </td>
			</tr>
        <tr>
     		<!-- 影响业务情况 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectSituation"/></td>
     		<td colspan="3">
     			<textarea name="${sheetPageName}linkAffectSituation" id="${sheetPageName}linkAffectSituation" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'请填入影响业务情况，最多输入2000个字符'">${sheetLink.linkAffectSituation}</textarea>
			  </td>
		</tr>
			<tr>
			<!-- 涉及业务部门 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkReferOperateDept"/></td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkReferOperateDept" id="${sheetPageName}linkReferOperateDept" initDicId="1010902" defaultValue="${sheetLink.linkReferOperateDept}" alt="allowBlank:true"/>
			  </td>
			</tr>
        <tr>
     		<!-- 影响网元范围 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkNetAffectArea"/>*</td>
     		<td colspan="3">
     			<textarea name="linkNetAffectArea" id="linkNetAffectArea" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入影响网元范围，最多输入2000个字符'">${sheetLink.linkNetAffectArea}</textarea>
			  </td>
		</tr>
        <tr>
     		<!-- 影响网管情况 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectNetManage"/></td>
     		<td colspan="3">
     			<textarea name="linkAffectNetManage" id="linkAffectNetManage" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'请填入影响网管情况，最多输入2000个字符'">${sheetLink.linkAffectNetManage}</textarea>
			  </td>
		</tr>
		<tr>
			  <!-- 是否通知客服 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfNotify"/>*</td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkIfNotify" id="${sheetPageName}linkIfNotify" initDicId="10301" defaultValue="${sheetLink.linkIfNotify}" alt="allowBlank:false"/>
			  </td>
		</tr>
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
     		<!-- 实施方案 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExecuteAcc"/></td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" />
			  </td>
		</tr>
		<%} %>
     <%}else if(taskName.equals("ExecuteTask")){%>
     <%if(operateType.equals("113")||operateType.equals("11")){ %>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask" />
     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
     <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="invokeProcess" />
	<tr>
	    <!-- 接单时限 -->
		<td class="label">
			<bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		</td>
		<c:choose>
			<c:when test="${empty sheetLink.nodeAcceptLimit }"><td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="<%=eAcceptLimit %>" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td></c:when>
			<c:otherwise><td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td></c:otherwise>
		</c:choose>
		<!-- 完成时限 -->
		<td class="label">		     
		    <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		</td>
		<c:choose>
			<c:when test="${empty sheetLink.nodeCompleteLimit }"><td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="<%=eCompleteLimit %>" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td></c:when>
			<c:otherwise><td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td></c:otherwise>
		</c:choose>
	</tr>

			<tr>
              <!-- 查看设计结果 -->
			  <td class="label">查看设计结果</td>
			  <td class="content">
			  	 <a onclick="javascript:getUrl('designinfo')">浏览设计方案</a>
			  </td>
			  <!-- 查看施工明细 -->
			  <td class="label">查看施工明细</td>
			  <td>
			  	<a onclick="javascript:getUrl('execute')">浏览施工明细</a>
			  </td>
			</tr>
		
     		<tr>
            <!-- 实施结果 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteResult"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}linkExcuteResult" id="${sheetPageName}linkExcuteResult" initDicId="1010903" defaultValue="${sheetLink.linkExcuteResult}" alt="allowBlank:false" onchange="executeResult(this.value);"/>
			  </td>
			  <!-- 失败原因 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkFailedReason"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkFailedReason" id="${sheetPageName}linkFailedReason" initDicId="1010905" defaultValue="${sheetLink.linkFailedReason}" alt="allowBlank:true"/>
			  </td>
			</tr>
			<tr>
			<!-- 是否完全按照方案实施 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIsAccordanceProgramme"/>*</td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkIsAccordanceProgramme" id="${sheetPageName}linkIsAccordanceProgramme" initDicId="10301" defaultValue="${sheetLink.linkIsAccordanceProgramme}" alt="allowBlank:false"/>
			  </td>
 			  <!-- 测试结果
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkTestResult"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult" initDicId="1010904" defaultValue="${sheetLink.linkTestResult}" alt="allowBlank:false"/>
			  </td> -->
			</tr>
        <tr>
     		<!-- 实施情况说明 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkExcuteExplain" id="linkExcuteExplain" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入实施情况说明，最多输入2000个字符'">${sheetLink.linkExcuteExplain}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- 影响业务情况说明 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAffectOperationExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAffectOperationExplain" id="linkAffectOperationExplain" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入影响业务情况说明，最多输入2000个字符'">${sheetLink.linkAffectOperationExplain}</textarea>
			  </td>
		</tr>
		<tr>
     		<!-- 告警情况记录 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkAlarmRecord"/>*</td>
     		<td colspan="3">
     			<textarea name="linkAlarmRecord" id="linkAlarmRecord" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入告警情况记录，最多输入2000个字符'">${sheetLink.linkAlarmRecord}</textarea>
			  </td>
		</tr>
	<!--  统计报告异常说明  	<tr>
     		 
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkReportAbnormityExplain"/>*</td>
     		<td colspan="3">
     			<textarea name="linkReportAbnormityExplain" id="linkReportAbnormityExplain" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入统计报告异常说明，最多输入2000个字符'">${sheetLink.linkReportAbnormityExplain}</textarea>
			  </td>
		</tr>-->
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
     		<!-- 测试报告 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkTestReport"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" alt="allowBlank:false"/>
			  </td>
		</tr>
		<%} %>
     <%}else if(taskName.equals("ValidateTask")){%>
     <%if(operateType.equals("114")||operateType.equals("11")){ %>
     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
	<tr>
	    <!-- 接单时限 -->
		<td class="label">
			<bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		</td>
		<td class="content">
		    <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		           name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		           readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		</td>
		<!-- 完成时限 -->
		<td class="label">		     
		    <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		</td>
		<td class="content"> 
		     <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		            name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		            value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		</td>
	</tr>
			<!-- 资源归档 -->
			  <td class="label">资源归档</td>
			  <td class="content">
			  <a onclick="javascript:getUrl('hold')">资源归档</a>
    			<!-- <html:button styleClass="btn" property="projectCreate" onclick="">
		        	<bean:message bundle='circuitdispatch' key='button.projectCreate'/>
		        </html:button> -->
			  </td>
		<tr>
     		<!-- 执行情况分析 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkExcuteConstrue"/></td>
     		<td colspan="3">
     			<textarea name="linkExcuteConstrue" id="linkExcuteConstrue" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'请填入执行情况分析，最多输入2000个字符'">${sheetLink.linkExcuteConstrue}</textarea>
			  </td>
		</tr>
     		<tr>
            <!-- 是否需要更新作业计划 
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIfUpdatePlan"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}linkIfUpdatePlan" id="${sheetPageName}linkIfUpdatePlant" initDicId="10301" defaultValue="${sheetLink.linkIfUpdatePlan}" alt="allowBlank:false"/>
			  </td>-->
				<!-- 是否涉及工程交维 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkIsToMaintenance"/>*</td>
			  <td class="content">
			  	<eoms:comboBox name="${sheetPageName}linkIsToMaintenance" id="${sheetPageName}linkIsToMaintenance" initDicId="10301" defaultValue="${sheetLink.linkIsToMaintenance}" alt="allowBlank:false"/>
			  </td>			  
			  <!-- 资源库电路数据更新 -->
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkCircuitUpdate"/>*</td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}linkCircuitUpdate" id="${sheetPageName}linkCircuitUpdate" initDicId="1010906" defaultValue="${sheetLink.linkCircuitUpdate}" alt="allowBlank:false"/>
			  </td>
			</tr>
	<!--	<tr>
     		 作业计划更新建议 
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkPlanUpdateIdea"/></td>
     		<td colspan="3">
     			<textarea name="linkPlanUpdateIdea" id="linkPlanUpdateIdea" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'请填入作业计划更新建议，最多输入2000个字符'">${sheetLink.linkPlanUpdateIdea}</textarea>
			  </td>
		</tr>-->
		<tr>
     		<!-- 后续工作安排 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkWorkPlan"/></td>
     		<td colspan="3">
     			<textarea name="linkWorkPlan" id="linkWorkPlan" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'请填入后续工作安排，最多输入2000个字符'">${sheetLink.linkWorkPlan}</textarea>
			  </td>
		</tr>
			<tr>

			</tr>
		<tr>
     		<!-- 交维描述 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkMaintenanceDes"/>*</td>
     		<td colspan="3">
     			<textarea name="linkMaintenanceDes" id="linkMaintenanceDes" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入交维描述，最多输入2000个字符'">${sheetLink.linkMaintenanceDes}</textarea>
			  </td>
		</tr>
		 <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		  </tr>	
		  <%if(!enableService.equals("true")) {%>
     	<tr>
     		<!-- 业务侧资源数据反馈 -->
     		<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkServiceResData"/>*</td>
     		<td colspan="3">
     			<eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="circuitdispatch" alt="allowBlank:false"/>
			  </td>
		</tr>
		<%}} %>
     <%}else if(taskName.equals("HoldTask") && (operateType.equals("115")||operateType.equals("11"))){%>
	 <input type="hidden" name="${sheetPageName}phaseId" value="Over" />
	 <input type="hidden" name="${sheetPageName}status" value="1" />
	 
	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="115" />
     	<%String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
     	  if(parentProcessName.equals("iNetChangeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToNetChange" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="NetChange" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExecuteTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessPilotMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessPilot" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="pilot" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessOperationMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="callProcess" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessOperation" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="operate" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessBackoutMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessBackout" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessDredgeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessDredge" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iBusinessChangeMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BusinessChange" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iResourceAffirmMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="receiveNet" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="ResourceAffirm" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />
     	<%}else if(parentProcessName.equals("iSecurityDealMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToSecurityDeal" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="SecurityDeal" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" /> 
     	<%}else if(parentProcessName.equals("iGreatEventMainManager")){%>
     	   <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
		   <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}" />	
		   <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToGreatEvent" />
		   <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="GreatEvent" />							
     	   <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask" />	
     	   <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" /> 
     	<%}else{%>
     		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
     	<%}%>
	 <tr>
	  	 <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.mainIfDemonstrateCase"/>*</td>
	    <td colspan="3">
	      <eoms:comboBox name="${sheetPageName}mainIfDemonstrateCase" id="${sheetPageName}mainIfDemonstrateCase" 
            	      initDicId="1010912"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainIfDemonstrateCase}" onchange="ifCase(this.value);"/>
	    </td>    
	 </tr>     	
     	
     	
	 <tr>
	  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
	    <td colspan="3">
	      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" alt="width:'500px',allowBlank:false" styleClass="select"/>
	    </td>
	 </tr>
	  
	 <tr>
	  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
	    <td colspan="3">
	      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"  alt="width:'500px',allowBlank:false">${sheetMain.endResult}</textarea>
	    </td>
	 </tr>
  
		  <tr id="ifDcase">
		  	<td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.mainCaseKeywords"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}mainCaseKeywords" alt="allowBlank:false,maxLength:100,vtext:'请填入案例关键字，最多输入100字符'" id="${sheetPageName}mainCaseKeywords"  class="textarea max">${sheetMain.mainCaseKeywords}</textarea>
		    </td>
		  </tr>	
		  
		  <script type="text/javascript">
      var v1 = eoms.form;	
	  function ifCase(CaseKeywords){
		if(CaseKeywords=='101091201'){
		    v1.enableArea('ifDcase');
		}else{
			v1.disableArea('ifDcase',true);
		}		
	}
	ifCase($('${sheetPageName}mainIfDemonstrateCase').value);

</script>
<%} %>
    <%  if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请填入备注，最多输入2000个字符'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
  <%} %> 
  <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!-- <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:2000,vtext:'请填入备注，最多输入2000个字符'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		 -->
		
 <%} %> 
 		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
			<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
			<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<%if(taskName.equals("ProjectCreateTask")&&(fPreTaskName == null||fPreTaskName.equals("")||fPreTaskName.equals("DraftTask"))){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="124" />
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
			<!-- 驳回原因 -->
			<tr>
			  <td class="label"><bean:message bundle="circuitdispatch" key="circuitdispatch.linkRejectReason"/>*</td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}linkRejectReason" id="${sheetPageName}linkRejectReason" initDicId="1010911" defaultValue="${sheetLink.linkRejectReason}" alt="allowBlank:false"/>
			  </td>
			</tr>
		<%}else{ %>
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
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />*
			    </td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请填入备注，最多输入2000个字符'">${sheetLink.remark}</textarea>
			  </td>
			</tr>  	
		
		<%} %>
	</table>
	 
  	<%if(taskName.equals("ProjectCreateTask") && operateType.equals("110")) {%>  		
	<fieldset id="link4">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="circuitdispatch" key="role.Permit"/>			 
	 </legend>
		<eoms:chooser id="circuitdispatchSendRole" type="role" roleId="269" flowId="042" config="{returnJSON:true,showLeader:true}"
            category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
	</fieldset>		
	<%} %>
 
	 <%if(taskName.equals("PermitTask") && operateType.equals("131")&&!enableService.equals("true")) {%>
	<input type="hidden" name="${sheetPageName}mainRejectTimes" id="${sheetPageName}mainRejectTimes" value="${sheetMain.mainResourceNo}"/>
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>
		 </legend>		
			<eoms:chooser id="circuitdispatchSendRole" type="role" roleId="270" flowId="042" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',limit:'none',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>	
	 <%} %>
  
 	 	 
  	<%if(taskName.equals("PlanTask") && operateType.equals("112")) {%>  	
	<fieldset id="link4">
        <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="circuitdispatch" key="role.PlanExecute"/>	 
		 </legend>
		<eoms:chooser id="circuitdispatchSendRole" type="role" roleId="270" flowId="042" config="{returnJSON:true,showLeader:true}"
		  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
	</fieldset>		
	<%} %>
	
   <%if(taskName.equals("ExecuteTask") && operateType.equals("113")) {%>  	
	<fieldset id="link4">
        <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<bean:message bundle="circuitdispatch" key="role.ProjectCreate"/>	 
		 </legend>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${ProjectCreateTaskOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ProjectCreateTaskTaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${ProjectCreateTaskOperateroleidType}" />
	</fieldset>		
	<%} %>
   <%if(taskName.equals("ValidateTask") && operateType.equals("114")) {%>  	
	<%} %>
	<%if(taskName.equals("cc")) {%>	
  <fieldset id="link4">
 <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
      category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
  </fieldset>  	
	 <%} %> 

<br/>

<div ID="idSpecia2"></div>

<script type="text/javascript">
 var v1 = eoms.form;
 if('<%=operateType%>'=='131'||('${taskName}'=='PermitTask'&&'<%=operateType%>'=='55')){
	var permit = document.all.${sheetPageName}linkPermitResult.value;
	if(permit!=null&&permit!=''){
		ifPermitPass(permit);
	}
  }
  if('<%=operateType%>'=='113'||('${taskName}'=='ExecuteTask'&&'<%=operateType%>'=='11')){
	  var result = document.all.${sheetPageName}linkExcuteResult.value;
	  if(result!=null&&result!=''){
	  	executeResult(result);
	  }
  }

  
  function ifPermitPass(input){
  	if('<%=operateType%>'!='11'&&'<%=operateType%>'!='55'){
	if(input=="101090801"){
		//v1.enableArea('permit');
		if('<%=enableService%>'!='true'){
			chooser_circuitdispatchSendRole.enable();
			 $('roleName').innerHTML="设备维护组";
		}
		$('${sheetPageName}phaseId').value='PlanTask';
		$('${sheetPageName}operateType').value='111';
		selectLimit('PlanTask');
	}else if(input=="101090802"){  
		//v1.disableArea('permit',true);
		if('<%=enableService%>'!='true'){
			chooser_circuitdispatchSendRole.disable();
			$('roleName').innerHTML="电路调度数据制作员";
		}
		$('${sheetPageName}phaseId').value='ProjectCreateTask';
		$('${sheetPageName}operateType').value='121';
		selectLimit('ProjectCreateTask');
	}else if(input=="101090803"){
		if('<%=enableService%>'!='true'){
			chooser_circuitdispatchSendRole.disable();
			$('roleName').innerHTML="建单人";
		}
		//v1.disableArea('permit',true);
		$('${sheetPageName}phaseId').value='RejectTask';
		$('${sheetPageName}operateType').value='122';
		selectLimit('RejectTask');
	}else{
		if('<%=enableService%>'!='true'){
			chooser_circuitdispatchSendRole.enable();
			$('roleName').innerHTML="设备维护组";
		}
		$('${sheetPageName}phaseId').value='PlanTask';
		$('${sheetPageName}operateType').value='111';
		selectLimit('PlanTask');
	}
	}
	}
	function executeResult(input){
		if(input=="101090301"){
			document.all.${sheetPageName}linkFailedReason.disabled=true;
			document.all.${sheetPageName}linkFailedReason.value='';
		}else{
			document.all.${sheetPageName}linkFailedReason.disabled=false;
		}
	}
	function popModifyTime(){
		var height = window.screen.height/6;
   	 	var width = window.screen.width/4;
    	features = "dialogWidth:"+1024+"px;dialogHeight:"+768+"px; scroll:2; help:0; status:No; fullscreen;";
    	features += "dialogLeft:" + width + ";dialogTop:" + height;
		window.open('${app}/sheet/modifyTime/modifyTime.do?method=xquery','PlanSheet', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
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
	//当是否影响业务选择否时，影响业务情况和涉及业务部门不需要填写
	if('<%=operateType%>'=='112'||('${taskName}'=='PlanTask'&&'<%=operateType%>'=='11')){
		ifAffect(document.getElementById("linkIfAffectOperation").value);
	}
	function ifAffect(affectValue){
		if(affectValue!=null&&affectValue=='1030102'){
			//document.getElementById("linkAffectSituation").value='';
			//document.getElementById("linkReferOperateDept").value='';
			//document.getElementById("linkAffectSituation").disabled=true;
			//document.getElementById("linkReferOperateDept").disabled=true;
			eoms.form.disable("linkAffectSituation");
			eoms.form.disable("linkReferOperateDept");
		}else{
			//document.getElementById("linkAffectSituation").disabled=false;
			//document.getElementById("linkReferOperateDept").disabled=false;
			eoms.form.enable("linkAffectSituation");
			eoms.form.enable("linkReferOperateDept");
		}
	}
	//v = new eoms.form.Validation({form:'theform'});
	//v.custom = function(){ 
	//	var datestr = $('${sheetPageName}nodeCompleteLimit').value;
	//	datestr = datestr.replace(/-/g,"/");
	//	datestr = datestr.substring(0,datestr.length-2);
	//	var limit = new Date(datestr);
	//	var str = '${sheetMain.sheetCompleteLimit}';
	//	str = str.replace(/-/g,"/");
	//	str = str.substring(0,str.length-2);
	//	var dt2 = new Date(str);

	//    if(dt2<limit){
	//      alert("处理时限不能超过完成时限"); 
	//      v.markInvalid(document.forms[0].${sheetPageName}nodeCompleteLimit,"处理时限不能超过完成时限");
	 //     return false; 
	 //   }else{
	 //   	clearInvalid(document.forms[0].${sheetPageName}nodeCompleteLimit) ;
	 //   	return true;
	 //   }
	    
  	//}
	function selectLimit(nextPhaseId){
    var temp1=$("${sheetPageName}mainNetSortOne").value;
    var temp2=$("${sheetPageName}mainNetSortTwo").value;
    var temp3=$("${sheetPageName}mainNetSortThree").value;
    var temp4=$("${sheetPageName}mainEquipmentFactory").value;
    Ext.Ajax.request({
		method:"get",
		url: "softchange.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&specialty4="+temp4+"&flowName=SoftChangeMainProcess&taskName="+nextPhaseId,
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	    //$("${sheetPageName}sheetAcceptLimit").value = "";
        	    //$('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}nodeAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}nodeCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
 </script>