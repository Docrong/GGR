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
 String operateUserId="";
 String practiveTemplateId ="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("OfflineData");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	practiveTemplateId = base.getActiveTemplateId();
	operateUserId = base.getOperateUserId();
	}
}
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
<%@ include file="/WEB-INF/pages/wfworksheet/offlineData/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("处理时限不能晚于工单完成时限")}'"/>
         <input type="hidden" name="${sheetPageName}beanId" value="iOfflineDataMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.offlineData.model.OfflineDataMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.offlineData.model.OfflineDataLink"/>
	  <c:if test="${taskName != 'HoldTask' }">
	    <c:choose>
		  	<c:when test="${preLink.activeTemplateId == 'TaskAuditHumTask' }">
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="<%=operateUserId %>"/>
		  	</c:when>
		  	<c:when test="${operateType==211 }">
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${task.operateRoleId}"/>
		  	</c:when>
		  	<c:otherwise>
		  	 <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
		  	</c:otherwise>
	    </c:choose>
      </c:if>
      
      	<%if(taskName.equals("ExamineTask")) {%>
			
       <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
		-->	
		<%} %>
		
       <%if(operateType.equals("66")||operateType.equals("64") || operateType.equals("55")){ %>
		<tr>
		  <td class="label">${eoms:a2u('审批内容')}</td>
		  <td colspan="3">
	  		<textarea name="linkTransmitContent" id="linkTransmitContent" class="textarea max">${sheetLink.linkTransmitContent}</textarea>			   
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
		  <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
		  <td colspan="3">
				    <eoms:attachment name="sheetLink" property="nodeAccessories" 
              scope="request" idField="${sheetPageName}nodeAccessories" appCode="offlineData" />		   
		  </td>
		</tr>
		<%} %>
		
		
		
     <%}%>  
        <%if(taskName.equals("TaskAuditHumTask")){ %>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />           
           <%if(operateType.equals("201")){ %>
         	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="offlineData" key="offlineData.linkAuditResult"/></td>
		            <td colspan="3"> 	
			           ${eoms:a2u('通过')}   
                    </td>
		     </tr>
			 <tr>
		            <td  class="label">${eoms:a2u('审批意见')}*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}remark" id="${sheetPageName}remark" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.remark}</textarea>
                    </td>
		      </tr>
           <%}else if(operateType.equals("202")){ %>
         	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
         	<input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="offlineData" key="offlineData.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('不通过')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label">${eoms:a2u('审批意见')}*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}remark" id="${sheetPageName}remark" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.remark}</textarea>
                    </td>
		      </tr>
         	 
           <%}%>
         
         <%}else if(taskName.equals("ExcuteHumTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 		           
      	    <% if(operateType.equals("205")||operateType.equals("11")){ %>
        	  		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
        	  		  <tr>
		            <td  class="label">${eoms:a2u('是否相关部门确认')}*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}isConfirmation" id="${sheetPageName}isConfirmation" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入完成情况，最多输入1000汉字')}'">${sheetLink.isConfirmation}</textarea>
                    </td>
		          </tr>
        	  		 <tr>
		            <td  class="label">${eoms:a2u('相关确认部门')}*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}indeedDept" id="${sheetPageName}indeedDept" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入完成情况，最多输入1000汉字')}'">${sheetLink.indeedDept}</textarea>
                    </td>
		          </tr>
        	    <%}else if(operateType.equals("10")){ %>
        	    	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
        	       <tr>
					   <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
							id="${sheetPageName}nodeAcceptLimit" value="${eoms:date2String(task.acceptTimeLimit)}" 
							onclick="popUpCalendar(this, this)"
							alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>   
					  </td>
					  
					  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
							id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(task.completeTimeLimit)}" 
							onclick="popUpCalendar(this, this)"
							alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>   
					  </td>
					</tr>
        	       <tr>
		            <td  class="label"><bean:message bundle="offlineData" key="offlineData.fenpairesion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}isConfirmation" id="${sheetPageName}isConfirmation" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入分派理由，最多输入1000汉字')}'">${sheetLink.isConfirmation}</textarea>
                    </td>
		          </tr>
        	    <% }else if(operateType.equals("8")){ %>
         			 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask" />
         			  <tr>
		            <td  class="label"><bean:message bundle="offlineData" key="offlineData.yijiaoresion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}isConfirmation" id="${sheetPageName}isConfirmation" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入移交理由，最多输入1000汉字')}'">${sheetLink.isConfirmation}</textarea>
                    </td>
		          </tr>
                <%}%>   
                <!-- add by yangna -->
                 <% if(operateType.equals("205") || operateType.equals("10")){ %>
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
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="offlineData" />		   
			     </td>
			   </tr>
			<%}%> 
			<!-- end by yangna -->      	        	
         <%}else if( taskName.equals("HoldTask")){%>
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
			      alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入归档内容，最多输入1000汉字')}'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			 
         			 
         			 
              <%}%>
          <%}%>
          
     <%if(taskName.equals("TaskAuditHumTask")||taskName.equals("ExcuteHumTask")) {%> 
      <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'${eoms:a2u('最多输入100汉字')}'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 	
		
		<%} }%>
		
		<%if(operateType.equals("5")){ %>		
		
			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask" />
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="5" />
			
			<tr>
			  <td class="label">'${eoms:a2u('申请内容')}'</td>
			  <td colspan="3">
		  		<textarea name="linkTransmitContent" id="linkTransmitContent" class="textarea max"
		  		alt="allowBlank:true,width:500,maxLength:1000,vtext:'${eoms:a2u('最多输入500汉字')}'" >${sheetLink.linkTransmitContent}</textarea>			   
			  </td>
			</tr>			
		
		<%} %>
		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		  <c:choose> 
		  	    <c:when test="${taskName=='TaskAuditHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${taskName=='ExcuteHumTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskAuditHumTask" />
				</c:when>
			</c:choose>				
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,vtext:'${eoms:a2u('请最多输入1000字')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
		
		<% if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'${eoms:a2u('请最多输入1000汉字')}'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 		
			     
  </table>


