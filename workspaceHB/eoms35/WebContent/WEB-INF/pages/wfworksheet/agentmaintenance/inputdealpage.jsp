<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sheet.agentmaintenance.model.UserMade" %>
<%@page import="com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager"%>
<%@page import="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain" %>
<%@page import="com.boco.eoms.sheet.commonfault.model.CommonFaultAuto"%>
<%@page import="java.util.*"%>
<%@page import="java.util.Iterator"%>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 String operateUserId="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("AgentMaintenance");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager)  ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
Map condition = new HashMap();
String where = "";
if (request.getAttribute("sheetMain") != null) {
	 AgentMaintenanceMain agentMaintenanceMain = (AgentMaintenanceMain)request.getAttribute("sheetMain");
	 String mainAlarmId = agentMaintenanceMain.getMainFaultAlarmId();
	 where = " where remark1 = '" + mainAlarmId +"'";
} else {
	where = " where 1 != 1 ";
}
condition.put("where",where);
int[] aTotal = { 0 };
List steplist = autoservice.getObjectsByCondtion(new Integer(0),new Integer(-1), aTotal ,condition,"record");
//List steplist=autoservice.getSteps();
System.out.println("lizhi:steplist"+steplist);
request.setAttribute("steplist",steplist);
 %>
<script type="text/javascript">
		//å¤çæ¶éä¸è½è¶è¿å·¥åå®ææ¶é
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
<%@ include file="/WEB-INF/pages/wfworksheet/agentmaintenance/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("å¤çæ¶éä¸è½æäºå·¥åå®ææ¶é")}'"/>
         <input type="hidden" name="${sheetPageName}beanId" value="iAgentMaintenanceMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceLink"/>
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
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />           
           <%if(operateType.equals("201")){ %>
         	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditResult"/></td>
		            <td colspan="3"> 	
			           ${eoms:a2u('éè¿')}   
                    </td>
		     </tr>
			 <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="linkTaskAuditIdea" id="linkTaskAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å®¡æ¹æè§ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkTaskAuditIdea}</textarea>
                    </td>
		      </tr>
           <%}else if(operateType.equals("202")){ %>
         	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
         	<input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('ä¸éè¿')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å®¡æ¹æè§ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>
         	 
           <%}%>
         
         <%}else if(taskName.equals("TaskCompleteAuditHumTask")){ %>
 		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />                   
            <%if(operateType.equals("208")){ %>
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
             <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditResult"/></td>
		            <td colspan="3"> 	
			            ${eoms:a2u('éè¿')}  
                    </td>
		     </tr>
			 <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å®¡æ¹æè§ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>             
             
           <%}else if(operateType.equals("209")){ %>
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('ä¸éè¿')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å®¡æ¹æè§ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>             
             
           <%}%>
         
         <%}else if(taskName.equals("ExcuteHumTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 		           
      	    <% if(operateType.equals("205")){ %>
      	     <input type="hidden" name="mainSheetState" id="mainSheetState" value="2" />
      	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
      	    	<c:if test="${sheetMain.type=='commonfault'}">
       	  		 <tr>
	            	<td  class="label">是否重大事故</td>
	              	<td >
	              		<c:if test="${!empty dwpreLink.linkFaultIfGreatFault}">
		              		<eoms:comboBox name="linkFaultIfGreatFault" id="linkFaultIfGreatFault" initDicId="10301" 
								defaultValue="${dwpreLink.linkFaultIfGreatFault}"/> 	
						</c:if>
						<c:if test="${empty dwpreLink.linkFaultIfGreatFault}">
							<eoms:comboBox name="linkFaultIfGreatFault" id="linkFaultIfGreatFault" initDicId="10301" defaultValue="1030102"/> 
						</c:if>
                   	</td>
                   	<td  class="label">故障处理结果*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultDealResult" id="linkFaultDealResult" initDicId="1010306" 
							defaultValue="${dwpreLink.linkFaultDealResult}" alt="allowBlank:false"/> 	
                   	</td>
	          	</tr>
	          	<tr>
	            	<td  class="label">故障原因类别*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultReasonSort" id="linkFaultReasonSort" initDicId="1010303" 
							defaultValue="${dwpreLink.linkFaultReasonSort}" sub="linkFaultReasonSubsection" alt="allowBlank:false"/> 	
                   	</td>
                   	<td  class="label">故障原因细分*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultReasonSubsection" id="linkFaultReasonSubsection" initDicId="${dwpreLink.linkFaultReasonSort}" 
							defaultValue="${dwpreLink.linkFaultReasonSubsection}" alt="allowBlank:false"/> 	
                   	</td>
	          	</tr>
	          	<tr>
	            	<td  class="label">是否实施网络变更*</td>
	              	<td>
	              		<c:if test="${!empty dwpreLink.linkFaultIfExcuteNetChange}">
		              		<eoms:comboBox name="linkFaultIfExcuteNetChange" id="linkFaultIfExcuteNetChange" initDicId="10301" 
							defaultValue="${dwpreLink.linkFaultIfExcuteNetChange}" alt="allowBlank:false"/> 	
						</c:if>
						<c:if test="${empty dwpreLink.linkFaultIfExcuteNetChange}">
							<eoms:comboBox name="linkFaultIfExcuteNetChange" id="linkFaultIfExcuteNetChange" initDicId="10301" defaultValue="1030102"/> 
						</c:if>
	              			
                   	</td>
                   	<td  class="label">是否为最终解决方案*</td>
	              	<td >
	              		<c:if test="${!empty dwpreLink.linkFaultIfFinallySolveProject}">
		              		<eoms:comboBox name="linkFaultIfFinallySolveProject" id="linkFaultIfFinallySolveProject" initDicId="10301" 
								defaultValue="${dwpreLink.linkFaultIfFinallySolveProject}" alt="allowBlank:false"/> 
						</c:if>
						<c:if test="${empty dwpreLink.linkFaultIfFinallySolveProject}">
							<eoms:comboBox name="linkFaultIfFinallySolveProject" id="linkFaultIfFinallySolveProject" initDicId="10301" defaultValue="1030102"/> 
						</c:if>
                   	</td>
	          	</tr>
	          	<tr>
		            <td  class="label">是否申请入案例库*</td>
		              <td> 	
		              	<c:if test="${!empty dwpreLink.linkFaultIfAddCaseDataBase}">
		              		<eoms:comboBox name="linkFaultIfAddCaseDataBase" id="linkFaultIfAddCaseDataBase" initDicId="10301" 
							defaultValue="${dwpreLink.linkFaultIfAddCaseDataBase}" alt="allowBlank:false"/>
						</c:if>
						<c:if test="${empty dwpreLink.linkFaultIfAddCaseDataBase}">
							<eoms:comboBox name="linkFaultIfAddCaseDataBase" id="linkFaultIfAddCaseDataBase" initDicId="10301" defaultValue="1030102"/> 
						</c:if>
                    </td>
                    <td  class="label">影响业务时长</td>
		              <td > 	
		              	<input type="text"  class="text" name="linkFaultAffectTimeLength" id="linkFaultAffectTimeLength"  alt="allowBlank:true" 
							value="${dwpreLink.linkFaultAffectTimeLength}"/>
                    </td>
		          </tr>
		          <tr>
                    <td  class="label">故障消除时间*</td>
		              <td > 	
		              	<input type="text" class="text" name="linkFaultAvoidTime" readonly="readonly" id="linkFaultAvoidTime" 
				    		value="${eoms:date2String(dwpreLink.linkFaultAvoidTime)}" 
							onclick="popUpCalendar(this, this,null,null,null,true,-1)"  alt="allowBlank:false"/>
                    </td>
                     <td  class="label">业务恢复时间</td>
		              <td > 	
    				  	<input type="text" class="text" name="linkFaultOperRenewTime" readonly="readonly" id="linkFaultOperRenewTime" 
				    		value="${eoms:date2String(dwpreLink.linkFaultOperRenewTime)}" 
							onclick="popUpCalendar(this, this,null,null,null,true,-1)"  alt="allowBlank:true"/>	
                    </td>
		          </tr>
				<tr>
					 <td class="label">采取措施的时间*</td>
					 <td>
					 <input class="text" onclick="popUpCalendar(this, this,null,null,null,true,-1)" type="text" 
                   name="${sheetPageName}commonFaultdealTime" readonly="readonly" 
                   value="${eoms:date2String(sheetLink.commonFaultdealTime)}" id="${sheetPageName}commonFaultdealTime" alt="allowBlank:false"/>
					 </td>
					  <td class="label">故障处理人*</td>
					 <td>
					<input type="text" class="text" name="${sheetPageName}commonFaultTreatment" 
                     id="${sheetPageName}commonFaultTreatment" value="${sheetLink.commonFaultTreatment}" alt="allowBlank:false"/>
					 </td>	
	      </tr>
	      <tr>
	       <td class="label">故障原因说明*</td>
					 <td colspan="3">
					  <textarea name="${sheetPageName}commonFaultdealdesc" id="${sheetPageName}commonFaultdealdesc" class="textarea max" 
					 alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.commonFaultdealdesc}</textarea>	
					 </td>
					
	      </tr>
				<tr>
				  <td class="label">处理措施*</td>
				   
				  <td colspan="3"> 
				  	<select name="selectStep" onchange="isifdeal(this.value)" alt="allowBlank:false">
				   <option value="">请选择</option> 
					<%
					if (steplist.size() > 0 ) {
					 for(int i=0;i<steplist.size();i++){
					 	  CommonFaultAuto auto =  (CommonFaultAuto)steplist.get(i);
					 	  String commonFaultDesc = auto.getCommonFaultDesc();
					%>
					<option value="<%=commonFaultDesc%>"><%=commonFaultDesc%></option> 
					<%} } else {%>
						<option value="其它">其它</option> 
					<% }%>
					</select> 
			  	     <input type="hidden" name="linkFaultDealStep" id="linkFaultDealStep" value=""/>	
				   </td>
				</tr>
		
					<tr id="isIfDeal" style="display:none">
				  <td class="label">处理说明*</td>
				  <td colspan="3" >
					<textarea name="${sheetPageName}commonLinkDealdesc" id="${sheetPageName}commonLinkDealdesc" class="textarea max"  alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'">${sheetLink.commonLinkDealdesc}</textarea>
				
				  </td>
				</tr>
		          </c:if>
		          <c:if test="${sheetMain.type=='complaint'}">
		          	<tr>
                    <td  class="label">联系人*</td>
		              <td > 	
		              	<input type="text"  class="text" name="linkComContactUser" id="linkComContactUser" value="${dwpreLink.linkComContactUser}"/>
                    </td>
                     <td  class="label">联系人电话*</td>
		              <td > 	
    				  	<input type="text"  class="text" name="linkComContactPhone" id="linkComContactPhone" value="${dwpreLink.linkComContactPhone}"/>	
                    </td>
		          </tr>
		          <tr>
	            	<td  class="label">投诉性质*</td>
	              	<td>
	              		<eoms:comboBox name="linkComcompProp" id="linkComcompProp" initDicId="1010610" defaultValue="${dwpreLink.linkComcompProp}"/> 	
                   	</td>
                   	<td  class="label">是否已答复客户*</td>
	              	<td >
	              		<c:if test="${!empty dwpreLink.linkComIsContactUser}">
		              		<eoms:comboBox name="linkComIsContactUser" id="linkComIsContactUser" initDicId="10301" defaultValue="${dwpreLink.linkComIsContactUser}"/> 	
						</c:if>
						<c:if test="${empty dwpreLink.linkComIsContactUser}">
							<eoms:comboBox name="linkComIsContactUser" id="linkComIsContactUser" initDicId="10301" defaultValue="1030102"/> 
						</c:if>
                   	</td>
	          	</tr>
	          	<tr>
                    <td  class="label">故障消除时间*</td>
		              <td > 	
		              	<input type="text" class="text" name="linkComFaultEndTime" readonly="readonly" id="linkComFaultEndTime" 
				    		value="${eoms:date2String(dwpreLink.linkComFaultEndTime)}" 
							onclick="popUpCalendar(this, this,null,null,null,true,-1)"  alt="allowBlank:false"/>
                    </td>
                     <td  class="label">处理结果*</td>
	              	<td >
	              		<eoms:comboBox name="linkComdealResult" id="linkComdealResult" initDicId="1010611" defaultValue="${dwpreLink.linkComdealResult}"/> 	
                   	</td>
		          </tr>
		           <tr>
		            <td  class="label">问题原因*</td>
		              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="linkComTransmitReason" id="linkComTransmitReason" 
	    				  	alt="width:500,allowBlank:false">${dwpreLink.linkComTransmitReason}</textarea>
                    </td>
		          </tr>
		           <tr>
		            <td  class="label">解决措施*</td>
		              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="linkComLocalDealDesc" id="linkComLocalDealDesc" 
	    				  	alt="width:500,allowBlank:false">${dwpreLink.linkComLocalDealDesc}</textarea>
                    </td>
		          </tr>
		          </c:if>
		          <c:if test="${sheetMain.type=='commontask' }">
			          <tr>
			            <td  class="label">完成情况*</td>
			              <td colspan="3"> 	
		    				  <textarea class="textarea max" name="linkTaskComplete" id="linkTaskComplete" 
		    				  	alt="width:500,allowBlank:false">${dwpreLink.linkTaskComplete}</textarea>
	                    </td>
			          </tr>
		          </c:if>
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
			              				scope="request" idField="nodeAccessories" appCode="agentmaintenance" />		   
						     </td>
					</tr>
        	    <%}else if(operateType.equals("206")){ %>
        	        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask" />
        	        <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.linkTaskComplete"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å®ææåµï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
        	    <%}else if(operateType.equals("10")){ %>
        	    	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
        	       <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.fenpairesion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkComUntreadReason" id="${sheetPageName}linkComUntreadReason" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥åæ´¾çç±ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkComUntreadReason}</textarea>
                    </td>
		          </tr>
        	    <% }else if(operateType.equals("8")){ %>
         			 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask" />
         			  <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.yijiaoresion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥ç§»äº¤çç±ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
                <%}%>       	        	
         <%}else if( taskName.equals("AffirmHumTask") ){%>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />          
         	 <%if(operateType.equals("212")){ %>
         		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         		 <input type="hidden" name="mainSheetState" id="mainSheetState" value="2" />
         		  <tr>
		            <td  class="label"><bean:message bundle="agentmaintenance" key="agentmaintenance.advice"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å»ºè®®ï¼æå¤è¾å¥1000æ±å­')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
         		 
            <%}else if(operateType.equals("211")){ %>
         	 	<!-- input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" / -->
      			<!-- input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" / -->
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Receive" / >
      			<!-- input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/ -->
         		 <input type="hidden" name="mainSheetState" id="mainSheetState" value="0" />
         		 <c:if test="${sheetMain.type=='commonfault'}">
       	  		 <tr>
	            	<td  class="label">是否重大事故</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultIfGreatFault" id="linkFaultIfGreatFault" initDicId="10301" 
							defaultValue="${dwpreLink.linkFaultIfGreatFault}"/> 	
                   	</td>
                   	<td  class="label">故障处理结果*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultDealResult" id="linkFaultDealResult" initDicId="1010306" 
							defaultValue="${dwpreLink.linkFaultDealResult}" alt="allowBlank:false"/> 	
                   	</td>
	          	</tr>
	          	<tr>
	            	<td  class="label">故障原因类别*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultReasonSort" id="linkFaultReasonSort" initDicId="1010303" 
							defaultValue="${dwpreLink.linkFaultReasonSort}" sub="linkFaultReasonSubsection" alt="allowBlank:false"/> 	
                   	</td>
                   	<td  class="label">故障原因细分*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultReasonSubsection" id="linkFaultReasonSubsection" initDicId="${dwpreLink.linkFaultReasonSort}" 
							defaultValue="${dwpreLink.linkFaultReasonSubsection}" alt="allowBlank:false"/> 	
                   	</td>
	          	</tr>
	          	<tr>
	            	<td  class="label">是否实施网络变更*</td>
	              	<td>
	              		<eoms:comboBox name="linkFaultIfExcuteNetChange" id="linkFaultIfExcuteNetChange" initDicId="10301" 
							defaultValue="${dwpreLink.linkFaultIfExcuteNetChange}" alt="allowBlank:false"/> 	
                   	</td>
                   	<td  class="label">是否为最终解决方案*</td>
	              	<td >
	              		<eoms:comboBox name="linkFaultIfFinallySolveProject" id="linkFaultIfFinallySolveProject" initDicId="10301" 
							defaultValue="${dwpreLink.linkFaultIfFinallySolveProject}" alt="allowBlank:false"/> 	
                   	</td>
	          	</tr>
	          	<tr>
		            <td  class="label">是否申请入案例库*</td>
		              <td> 	
    				  	<eoms:comboBox name="linkFaultIfAddCaseDataBase" id="linkFaultIfAddCaseDataBase" initDicId="10301" 
							defaultValue="${dwpreLink.linkFaultIfAddCaseDataBase}" alt="allowBlank:false"/> 	
                    </td>
                    <td  class="label">影响业务时长*</td>
		              <td > 	
		              	<input type="text"  class="text" name="linkFaultAffectTimeLength" id="linkFaultAffectTimeLength"  alt="allowBlank:false" 
							value="${dwpreLink.linkFaultAffectTimeLength}"/>
                    </td>
		          </tr>
		          <tr>
                    <td  class="label">故障消除时间*</td>
		              <td > 	
		              	<input type="text" class="text" name="linkFaultAvoidTime" readonly="readonly" id="linkFaultAvoidTime" 
				    		value="${eoms:date2String(dwpreLink.linkFaultAvoidTime)}" 
							onclick="popUpCalendar(this, this,null,null,null,true,-1)"  alt="allowBlank:false"/>
                    </td>
                     <td  class="label">业务恢复时间*</td>
		              <td > 	
    				  	<input type="text" class="text" name="linkFaultOperRenewTime" readonly="readonly" id="linkFaultOperRenewTime" 
				    		value="${eoms:date2String(dwpreLink.linkFaultOperRenewTime)}" 
							onclick="popUpCalendar(this, this,null,null,null,true,-1)"/>	
                    </td>
		          </tr>
		          <tr>
		            <td  class="label">处理措施*</td>
		              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="linkFaultDealStep" id="linkFaultDealStep" 
	    				  	alt="width:500,allowBlank:false">${dwpreLink.linkFaultDealStep}</textarea>
                    </td>
		          </tr>
		          </c:if>
		          <c:if test="${sheetMain.type=='complaint'}">
		          	<tr>
                    <td  class="label">联系人*</td>
		              <td > 	
		              	<input type="text"  class="text" name="linkComContactUser" id="linkComContactUser" value="${dwpreLink.linkComContactUser}"/>
                    </td>
                     <td  class="label">联系人电话*</td>
		              <td > 	
    				  	<input type="text"  class="text" name="linkComContactPhone" id="linkComContactPhone" value="${dwpreLink.linkComContactPhone}"/>	
                    </td>
		          </tr>
		          <tr>
	            	<td  class="label">投诉性质*</td>
	              	<td>
	              		<eoms:comboBox name="linkComcompProp" id="linkComcompProp" initDicId="1010610" defaultValue="${dwpreLink.linkComcompProp}"/> 	
                   	</td>
                   	<td  class="label">是否已答复客户*</td>
	              	<td >
	              		<eoms:comboBox name="linkComIsContactUser" id="linkComIsContactUser" initDicId="10301" defaultValue="${dwpreLink.linkComIsContactUser}"/> 	
                   	</td>
	          	</tr>
	          	<tr>
                    <td  class="label">故障消除时间*</td>
		              <td > 	
		              	<input type="text" class="text" name="linkComFaultEndTime" readonly="readonly" id="linkComFaultEndTime" 
				    		value="${eoms:date2String(dwpreLink.linkComFaultEndTime)}" 
							onclick="popUpCalendar(this, this,null,null,null,true,-1)"  alt="allowBlank:false"/>
                    </td>
                     <td  class="label">处理结果*</td>
	              	<td >
	              		<eoms:comboBox name="linkComdealResult" id="linkComdealResult" initDicId="1010611" defaultValue="${dwpreLink.linkComdealResult}"/> 	
                   	</td>
		          </tr>
		           <tr>
		            <td  class="label">问题原因*</td>
		              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="linkComTransmitReason" id="linkComTransmitReason" 
	    				  	alt="width:500,allowBlank:false">${dwpreLink.linkComTransmitReason}</textarea>
                    </td>
		          </tr>
		           <tr>
		            <td  class="label">解决措施*</td>
		              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="linkComLocalDealDesc" id="linkComLocalDealDesc" 
	    				  	alt="width:500,allowBlank:false">${dwpreLink.linkComLocalDealDesc}</textarea>
                    </td>
		          </tr>
		          </c:if>
		          <c:if test="${sheetMain.type=='commontask' }">
			          <tr>
			            <td  class="label">完成情况*</td>
			              <td colspan="3"> 	
		    				  <textarea class="textarea max" name="linkTaskComplete" id="linkTaskComplete" 
		    				  	alt="width:500,allowBlank:false">${dwpreLink.linkTaskComplete}</textarea>
	                    </td>
			          </tr>
		          </c:if>         		 
            <%}%>
          <%}else if( taskName.equals("HoldHumTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverHumTask" />
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
			      alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('è¯·è¾å¥å½æ¡£åå®¹ï¼æå¤è¾å¥1000æ±å­')}'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			 
         			 
         			 
              <%}%>
          <%}%>
        <% if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'${eoms:a2u('è¯·æå¤è¾å¥1000æ±å­')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 
     <%if(taskName.equals("TaskCreateAuditHumTask")||taskName.equals("AffirmHumTask")||taskName.equals("TaskCompleteAuditHumTask") ||taskName.equals("ExcuteHumTask")) {%>
      
      <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'${eoms:a2u('æå¤è¾å¥100æ±å­')}'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 	
		
		<%} }%>
		
		<%if(operateType.equals("4")){ %>
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
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,vtext:'${eoms:a2u('è¯·æå¤è¾å¥1000å­')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>		
			     
  </table>
<%if(taskName.equals("TaskCreateAuditHumTask")){ %>
     <% if(operateType.equals("201")){ %>
   <fieldset>
	 <legend>
	 </legend>
<%
int total = Integer.parseInt(request.getAttribute("total").toString());
if(total==0){
%>
		<eoms:chooser id="test"
		   category="[{id:'dealPerformer',childType:'user,dept',allowBlank:false,limit:'none',text:'${eoms:a2u('æ´¾å')}',vtext:'${eoms:a2u('è¯·éæ©æ´¾åå¯¹è±¡')}'}]"
		   data="${sendUserAndRoles}"/>
<%}else{
UserMade userMade = (UserMade)request.getAttribute("userMade");
if(userMade.getUsertoRole()!=null && !"".equals(userMade.getUsertoRole())){
String[] users = userMade.getUsertoRole().split(",");
for(int i=0;i<users.length;i++){%>
	<input type="checkbox" name="user" value="<%=users[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=users[i]%>" beanId="tawSystemUserDao" /><br>
<%}}
if(userMade.getUsertoDept()!=null && !"".equals(userMade.getUsertoDept())){
String[] depts = userMade.getUsertoDept().split(",");
for(int i=0;i<depts.length;i++){
%>
<input type="checkbox" name="dept" value="<%=depts[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=depts[i]%>" beanId="tawSystemDeptDao" /><br>
<%}}%>
	<input type="hidden" name="dealPerformer" id="dealPerformer" value="">
	<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="">
	<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="">
<%}%>
   </fieldset>
<% } }%>
  
<%if(taskName.equals("ExcuteHumTask")){ %>
<% if(operateType.equals("8")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="agentmaintenance" key="role.excute"/>
			 </span>
	 </legend>
<%
int total = Integer.parseInt(request.getAttribute("total").toString());
if(total==0){
%>
			<eoms:chooser id="test"
			   category="[{id:'dealPerformer',allowBlank:false,childType:'user,dept',text:'${eoms:a2u('æ´¾å')}',vtext:'${eoms:a2u('è¯·éæ©æ´¾åå¯¹è±¡')}'}]"/>
<%}else{
UserMade userMade = (UserMade)request.getAttribute("userMade");
if(userMade.getUsertoRole()!=null && !"".equals(userMade.getUsertoRole())){
String[] users = userMade.getUsertoRole().split(",");
for(int i=0;i<users.length;i++){%>
	<input type="checkbox" name="user" value="<%=users[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=users[i]%>" beanId="tawSystemUserDao" /><br>
<%}}
if(userMade.getUsertoDept()!=null && !"".equals(userMade.getUsertoDept())){
String[] depts = userMade.getUsertoDept().split(",");
for(int i=0;i<depts.length;i++){
%>
<input type="checkbox" name="dept" value="<%=depts[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=depts[i]%>" beanId="tawSystemDeptDao" /><br>
<%}}%>
	<input type="hidden" name="dealPerformer" id="dealPerformer" value="">
	<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="">
	<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="">
<%}%>	
   </fieldset>
<% } %>
<% if(operateType.equals("10")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="agentmaintenance" key="role.excute"/>
			 </span>
	 </legend>
<%
int total = Integer.parseInt(request.getAttribute("total").toString());
if(total==0){
	if(taskName.equals("ExcuteHumTask")){%>
		<eoms:chooser id="test" type="role" roleId="800212" flowId="472"
		   category="[{id:'dealPerformer',childType:'subrole',allowBlank:false,limit:'none',
		   text:'${eoms:a2u('æ´¾å')}',vtext:'${eoms:a2u('è¯·éæ©æ´¾åå¯¹è±¡')}'},
		   {id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
	<%}else{%>
		<eoms:chooser id="test" type="role" roleId="800212" flowId="472"
		   category="[{id:'dealPerformer',childType:'subrole',allowBlank:false,limit:'none',
		   text:'${eoms:a2u('æ´¾å')}',vtext:'${eoms:a2u('è¯·éæ©æ´¾åå¯¹è±¡')}'}]"/>
	<%}%>
<%}else{
UserMade userMade = (UserMade)request.getAttribute("userMade");
if(userMade.getUsertoRole()!=null && !"".equals(userMade.getUsertoRole())){
String[] users = userMade.getUsertoRole().split(",");
for(int i=0;i<users.length;i++){%>
	<input type="checkbox" name="user" value="<%=users[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=users[i]%>" beanId="tawSystemUserDao" /><br>
<%}}
if(userMade.getUsertoDept()!=null && !"".equals(userMade.getUsertoDept())){
String[] depts = userMade.getUsertoDept().split(",");
for(int i=0;i<depts.length;i++){
%>
<input type="checkbox" name="dept" value="<%=depts[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=depts[i]%>" beanId="tawSystemDeptDao" /><br>
<%}}%>
	<input type="hidden" name="dealPerformer" id="dealPerformer" value="">
	<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="">
	<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="">
<%}%>
   </fieldset>
<% } %>
<% if(operateType.equals("206")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="agentmaintenance" key="role.TaskCompleteAudit"/>
			 </span>
	 </legend>
<%
int total = Integer.parseInt(request.getAttribute("total").toString());
if(total==0){
%>
      <eoms:chooser id="test"
		category="[{id:'auditPerformer',text:'${eoms:a2u('å®¡æ ¸')}',childType:'user,dept',limit:'1',allowBlank:false,vtext:'${eoms:a2u('è¯·éæ©å®¡æ ¸å¯¹è±¡')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('æé')}'}]" 
		/>
<%}else{
UserMade userMade = (UserMade)request.getAttribute("userMade");
if(userMade.getUsertoRole()!=null && !"".equals(userMade.getUsertoRole())){
String[] users = userMade.getUsertoRole().split(",");
for(int i=0;i<users.length;i++){%>
	<input type="checkbox" name="user" value="<%=users[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=users[i]%>" beanId="tawSystemUserDao" /><br>
<%}}
if(userMade.getUsertoDept()!=null && !"".equals(userMade.getUsertoDept())){
String[] depts = userMade.getUsertoDept().split(",");
for(int i=0;i<depts.length;i++){
%>
<input type="checkbox" name="dept" value="<%=depts[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB id="<%=depts[i]%>" beanId="tawSystemDeptDao" /><br>
<%}}%>
	<input type="hidden" name="dealPerformer" id="dealPerformer" value="">
	<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="">
	<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="">
<%}%>
 </fieldset>
<% } %>
<% if(operateType.equals("205")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
			 <span id="roleName">:${eoms:a2u('ä¸ä¸çº§ä»»å¡æ§è¡äºº')}
			 </span>
	 </legend>
   </fieldset>
<% } %>
<%} %>

<%if(taskName.equals("cc")) {%>	
	<fieldset id="link4">
	 <legend>
	     	 <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
			 <span id="roleName">:${eoms:a2u('æéäºº')}
			 </span>
	 </legend>	
	 <div class="x-form-item" >
		<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
		   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('æé')}'}]"/>
	 </div> 		   
	   </fieldset>		
<%} %>   
<script type="text/javascript">
var v1 = eoms.form;

function addDealPerformer(){
	var dealPerformer = "";
	var dealPerformerType = "";
	var arr = document.getElementsByName("user");
	for(var i=0;i<arr.length;i++){
	   if(arr[i].type == "checkbox" && arr[i].checked){
	   		if(dealPerformer.length > 0){
	   			dealPerformer = dealPerformer + ",";
	   			dealPerformerType = dealPerformerType + ",";
	   		}
	        dealPerformer = dealPerformer + arr[i].value;
	        dealPerformerType = dealPerformerType + "user";
	    }
	}
	var arr1 = document.getElementsByName("dept");
	for(var i=0;i<arr1.length;i++){
	   if(arr1[i].type == "checkbox" && arr1[i].checked){
	   		if(dealPerformer.length > 0){
	   			dealPerformer = dealPerformer + ",";
	   			dealPerformerType = dealPerformerType + ",";
	   		}
	        dealPerformer = dealPerformer + arr1[i].value;
	        dealPerformerType = dealPerformerType + "dept";
	    }
	}
	$('dealPerformer').value=dealPerformer;
	$('dealPerformerLeader').value=dealPerformer;
	$('dealPerformerType').value=dealPerformerType;
}

function isifdeal(input){
   		if(input=="其他"||input=="其它"){
   			document.getElementById("isIfDeal").style.display = "";
   		}else{
   			document.getElementById("isIfDeal").style.display = "none";
   		}  	
   }
   
var stepflag = false;
function setStep(){
				var stepobj = document.getElementById("linkFaultDealStep");
				var descobj = document.getElementById("commonLinkDealdesc");
				var ss =   document.getElementById("selectStep");
	      var ft = document.getElementById("${sheetPageName}commonFaultdealTime");      
	      var fd = document.getElementById("${sheetPageName}commonFaultdealdesc"); 
	     	var faultTreatment = document.getElementById("${sheetPageName}commonFaultTreatment");
	     	var linkDealdesc = document.getElementById("${sheetPageName}commonLinkDealdesc");	      		
	   if(ss.value=="其他"||ss.value=="其它"){ 
       stepflag = false;
       stepobj.value = "采取措施的时间："+ft.value +"\n" +"处理措施：" +linkDealdesc.value +"\n" +"故障原因说明：" + fd.value +"\n" + "故障处理人："+faultTreatment.value;
       if (descobj.value=="") {
       		alert("处理说明不能为空!");
       		return false;
       	} else {
       		return true;
       		}
       }else{
       stepflag = false;
       stepobj.value = "采取措施的时间："+ft.value +"\n" +"处理措施：" +ss.value +"\n" +"故障原因说明：" + fd.value +"\n" + "故障处理人："+faultTreatment.value;
       return true;
       }     
}
function onFaultAvoid(){
		var linkFaultAvoidTime = document.getElementById("linkFaultAvoidTime");
		var linkFaultOperRenewTime = document.getElementById("linkFaultOperRenewTime");
		var linkFaultAffectTimeLength = document.getElementById("linkFaultAffectTimeLength");
		Ext.Ajax.request({
			method : 'post',
			url : '${app}/sheet/alarmsolveDate/alarmsolveDate.do?method=getAlarmsolveDateByAgent&sheetKey=${sheetMain.id}',
			success : success
		});
function success(x) {
			var o = eoms.JSONDecode(x.responseText);
			if(o.status=='0'){
				linkFaultAvoidTime.value=o.data[0].date;
				linkFaultOperRenewTime.value=o.data[0].date;
				var day = linkFaultAvoidTime.value.substring(8,10)-'${sheetMain.mainFaultGenerantTime}'.substring(8,10);
				day = day*24;
				linkFaultAffectTimeLength.value = linkFaultAvoidTime.value.substring(11,13)-'${sheetMain.mainFaultGenerantTime}'.substring(11,13) + day;
				alert("告警平台有故障清除时间,已同步告警清除时间!");
			}else if(o.status=='1'){
				alert("该故障没有故障清除时间！您可以通过上传附件方式证实告警已恢复回单！");
			}
		}
}

var operateType = <%=operateType%>;
if('${sheetMain.mainFaultSendMode}' !='101030502'&&operateType=='205'&&'${sheetMain.mainFaultAlarmSolveDate}' ==''){
	onFaultAvoid();	
}
</script>

