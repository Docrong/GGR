<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String roleId = "";
 String roleName="";
 %>
<script type="text/javascript">
 </script>
<%@ include file="/WEB-INF/pages/wfworksheet/safeaudit/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		<%if(!operateType.equals("61")) {%>
		 <caption><bean:message bundle="safeaudit" key="safeaudit.header"/></caption>
		<%} %>
		<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
        <input type="hidden" name="${sheetPageName}beanId" value="iSafeAuditMainManager"/> 
        <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.safeaudit.model.SafeAuditMain"/>	
        <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.safeaudit.model.SafeAuditLink"/>
        <c:if test="${taskName != 'HoldTask' }">
	      <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
	    </c:if>
	    <%if(taskName.equals("EvaluateMaterialHumTask")){ 
	      if(operateType.equals("90")|| operateType.equals("11")){%>
	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="EditReportHumTask" />
     	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />	
	        <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
	        <tr>
		            <td  class="label"><bean:message bundle="safeaudit" key="safeaudit.linkPresentDescribe"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkPresentDescribe" value="${sheetLink.linkPresentDescribe}" id="${sheetPageName}linkPresentDescribe" alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入安全现状描述，最多输入250字'">${sheetLink.linkPresentDescribe}</textarea>
                    </td>
		          </tr>
	           <tr>
		            <td  class="label"><bean:message bundle="safeaudit" key="safeaudit.linkPresentReport"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkPresentReport" value="${sheetLink.linkPresentReport}" id="${sheetPageName}linkPresentReport" alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入安全现状报告，最多输入250字'">${sheetLink.linkPresentReport}</textarea>
                    </td>
		          </tr>
			     
		     <%}} %>
		 <%if(taskName.equals("EditReportHumTask")){ 
		  if(operateType.equals("91")|| operateType.equals("11")){%>
		    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApprovingHumTask" />
	        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	        <tr>
	          <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
	        </tr>
	                <tr>
  						<td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkAuditReport"/>*</td>
		   				 <td  colspan='3'>
		   		          <eoms:attachment name="sheetLink" property="linkAuditReport" 
            		          scope="request" idField="${sheetPageName}linkAuditReport" appCode="safeaudit" alt="allowBlank:false"/> 
		     </td>
		           </tr>
	         
	     
	     <%}} %>  
	      <%if(taskName.equals("ApprovingHumTask")){ 
		     if(operateType.equals("93")|| operateType.equals("55")){%>
		        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	 <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				  <tr>
		            <td  class="label"><bean:message bundle="safeaudit" key="safeaudit.linkCheckIdeas"/>*</td>
		              <td colspan="3"> 	
    				  <eoms:comboBox name="${sheetPageName}linkCheckIdeas" id="${sheetPageName}linkCheckIdeas" 
            	      initDicId="1011301"  alt="allowBlank:false" styleClass="select-class" 
            	      defaultValue="${sheetLink.linkCheckIdeas}" onchange="ifAuditPass(this.value,'AuditTask')"/>
                    </td>
                   
		      </tr>
	          <tr>
  						<td class="label"><bean:message bundle="safeaudit" key="safeaudit.linkCheckResult"/>*</td>
		   				 <td  colspan='3'>
		   		          <textarea class="textarea max" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult" alt="width:500,allowBlank:false">${sheetLink.linkCheckResult}</textarea>
		                 </td>
		      </tr>
	     
	          <%}} %> 
	           <%if(taskName.equals("HoldHumTask")) {%>  
			<%if(operateType.equals("18")|| operateType.equals("11")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>	     	
			 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select"/>
		    </td>
		    </tr>
		    <tr>
		       <td class="label"><bean:message bundle="safeaudit" key="safeaudit.LinkIfStartSecurityDeal"/></td>
		        <td colspan="3"> 	
    				  <eoms:comboBox name="${sheetPageName}LinkIfStartSecurityDeal" id="${sheetPageName}LinkIfStartSecurityDeal" 
            	      initDicId="1011303"  alt="allowBlank:true" styleClass="select-class" defaultValue="${baseLink.LinkIfStartSecurityDeal}" onchange="popOtherFlow1(this.value);"/>
			  <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSecurityDeal" onclick="openwin1('101160301')">安全问题处理工单</html:button>
		    </tr>	
		    
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:1000,vtext:'请填入信息，最多输入1000字'" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>  
		   <%}else if(operateType.equals("17")){%>
     	        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApprovingHumTask" />
	        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />	
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />
			    </td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
			  </td>
			</tr> 
		    
	     <%} }%>
	      <!--流程中的字段域 结束-->
          <!-- 公共功能，抄送和确认受理 -->
     	    <!-- 驳回到上一级 -->
     	     <%if(operateType.equals("4")){ %>
 		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>					
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="link.linkRejectCause" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
       <!-- 抄送--> 	
      <% if(taskName.equals("cc")){%>    
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:1000,width:500,vtext:'请填入信息，最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
          <%} %>
      <!-- 确认受理 -->     
       <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />	
		
  		<%}%>       
  </table>
   <!-- 公共功能，抄送和确认受理 结束 -->
    <!-- 各个环节中的选择角色 -->
    <br/> 				
	 <!-- 提交材料  -->
	  <%if(taskName.equals("EvaluateMaterialHumTask")&& operateType.equals("90")) {%>	
		<fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="safeaudit" key="role.auditer"/>
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="333" flowId="072" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%} %> 
	 <!-- 提交审批 -->
  <%if(taskName.equals("EditReportHumTask")&& operateType.equals("91")) {%>	
		<fieldset id="link10">
		<legend>
			<bean:message bundle="safeaudit" key="safeaudit.linkCheckObject"/>:
			<bean:message bundle="safeaudit" key="role.safeManager"/> 
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="334" flowId="072" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
		<%} %>
     <!-- 审核安全审计报告 -->
  <%if(taskName.equals("ApprovingHumTask")&& operateType.equals("93")) {%>	
	    <fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName"></span>	
			
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="333" flowId="072" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
  <%} %> 
  <br/>

<div ID="idSpecia2"></div> 

<script type="text/javascript">
 var v1 = eoms.form;
 var operateType = "${operateType}";
  function ifAuditPass(input, task){
   if (operateType != "55") {
		if(input=="101130101" && task == "AuditTask"){	
			chooser_sendRole.enable();
			//审核通过到归档
			$('${sheetPageName}phaseId').value='HoldHumTask';
			$('roleName').innerHTML="安全审计计划员";
			$('${sheetPageName}operateType').value='921';
	
		} else if(input=="101130102" && task == "AuditTask"){
			//审核不通过到编写报告
			chooser_sendRole.disable();
			$('${sheetPageName}phaseId').value='EditReportHumTask';
			$('roleName').innerHTML="安全审计计划员";
			$('${sheetPageName}operateType').value='922';	
	        }
        }
}
ifAuditPass('${sheetLink.linkCheckIdeas}','HoldHumTask');
ifAuditPass('${sheetLink.linkCheckIdeas}','EditReportHumTask');

function openwin1() {
	
	var url="${app}/sheet/securitydeal/securitydeal.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSafeAuditMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
	window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
function popOtherFlow1(value){
		if(value=='101130301'){
			document.getElementById('startSecurityDeal').style.display='';
		}else if(value=='101130302'){
		    $('${sheetPageName}operateType').value='18';
		    $('${sheetPageName}phaseId').value='HoldHumTask'
            document.getElementById('startSecurityDeal').style.display='none';
		}	
	}

    var frm5 = document.forms[0];
    var temp5 = frm5.linkIfStartChangeProcess ? frm5.LinkIfStartSecurityDeal.value : '';
    if(temp5!=''&&'<%=operateType%>'!='61')
    {
    ifAuditPass5(temp5);
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
  
   