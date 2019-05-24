<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====taskName======"+taskName+"=====operateType====="+operateType);
 request.setAttribute("operateType",operateType);
 String operateUserId="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("CommonTask");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
 %>
<script type="text/javascript"><!--
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
		
function searchContent(id){
	Ext.Ajax.request({
		url : "${app}/sheet/agentmaintenance/agentmaintenance.do?method=searchContent&type=commontask&sheetid="+id,				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var dwTotal = data[0].dwTotal;
			if(dwTotal==0){
				alert("此工单无对应代维流程!");
			}else{
				var total = data[0].total;
				if(total==0){
					alert("此工单对应的代维流程未回复，无回复内容!");
				}else{
					$('${sheetPageName}linkTaskComplete').value = data[0].linkTaskComplete;
				}
			}
			
		}
	});
}
function openwin(id) {
	Ext.Ajax.request({
		url : "${app}/sheet/agentmaintenance/agentmaintenance.do?method=searchContent&type=commontask&sheetid="+id,				
		method: 'POST',
		success: function (res) {
			var data = eoms.JSONDecode(res.responseText);
			var dwTotal = data[0].dwTotal;
			if(dwTotal==0){
				var url="${app}/sheet/agentmaintenance/agentmaintenance.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iCommonTaskMainManager"
				+"&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism&type=commontask";
				window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
			}else{
				alert("此工单已调用代维流程!");
			}
			
		}
	});
}
		function confirmCli(){
			
			if (confirm("是否复发挂牌?")==true){
						alert('${sheetMain.id}');
				Ext.Ajax.request({
						url : "${app}/sheet/supervisetask/supervisetask.do?method=supervisetaskRecordAddSave&id=${sheetMain.id}",				
						method: 'post',
						success: function(data){
						alert("已复发生成督办");
						
					},error:function(){
						alert("fail");
					}
		
				});
				/*
				*/
    	}else{
			document.getElementsByName("confirmMainIfReListed")[1].checked=true;
			}
		}
 --></script>
					<!-- 
						<input type="radio" name="confirmMainIfReListed" value="否" checked="checked">否
 <c:if test="${sheetmain.mainIfReListed=='是'}">
					 是否复发挂牌:<input type="radio" name="confirmMainIfReListed" value="是" onclick="confirmCli()">是
				</c:if>
					 -->
					 <%if(taskName.equals("ExcuteHumTask")){ %>
						是否复发挂牌1:<input type="radio" name="confirmMainIfReListed" value="是" onclick="confirmCli()">是
						<input type="radio" name="confirmMainIfReListed" value="否" checked="checked">否
						<%} %>  
						是否复发挂牌2:<input type="radio" name="confirmMainIfReListed" value="是" onclick="confirmCli()">是
						<input type="radio" name="confirmMainIfReListed" value="否" checked="checked">否
						<%if(taskName.equals("ExcuteHumTask")&&operateType.equals("61")){ %>
						是否复发挂牌3:<input type="radio" name="confirmMainIfReListed" value="是" onclick="confirmCli()">是
						<input type="radio" name="confirmMainIfReListed" value="否" checked="checked">否
						<%} %>  	
<%@ include file="/WEB-INF/pages/wfworksheet/commontask/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("处理时限不能晚于工单完成时限")}'"/>
         <input type="hidden" name="${sheetPageName}beanId" value="iCommonTaskMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.commontask.model.CommonTaskMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.commontask.model.CommonTaskLink"/>
	  <c:if test="${taskName != 'HoldHumTask' }">
	    <c:choose>
		  	<c:when test="${preLink.activeTemplateId == 'TaskCreateAuditHumTask' }">
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
        <%if(taskName.equals("TaskCreateAuditHumTask")){ %>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />           
           <%if(operateType.equals("201")){ %>
         	 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditResult"/></td>
		            <td colspan="3"> 	
			           ${eoms:a2u('通过')}   
                    </td>
		     </tr>
			 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>
           <%}else if(operateType.equals("202")){ %>
         	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask" />
         	<input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('不通过')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>
         	 
           <%}%>
         
         <%}else if(taskName.equals("TaskCompleteAuditHumTask")){ %>
 		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />                   
            <%if(operateType.equals("208")){ %>
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
             <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040101" />
         	 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditResult"/></td>
		            <td colspan="3"> 	
			            ${eoms:a2u('通过')}  
                    </td>
		     </tr>
			 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>             
             
           <%}else if(operateType.equals("209")){ %>
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         	 <input type="hidden" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" value="101040102" />
         	 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditResult"/></td>
		            <td colspan="3"> 	
		              ${eoms:a2u('不通过')}
                    </td>
		     </tr>
			  <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkAuditIdea"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditIdea" id="${sheetPageName}linkAuditIdea" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审批意见，最多输入1000汉字')}'">${sheetLink.linkAuditIdea}</textarea>
                    </td>
		      </tr>             
             
           <%}%>
         
         <%}else if(taskName.equals("ExcuteHumTask")){%>
         <script type="text/javascript">
         //alert(2);
         </script>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 		           
   		    是否复发挂牌:<input type="radio" name="confirmMainIfReListed" value="是" onclick="confirmCli()">是
						<input type="radio" name="confirmMainIfReListed" value="否" checked="checked">否
      	    <% if(operateType.equals("205")){ %>
         		 <input type="button" class="submit" value="任务工单调用代维流程"
	  				onclick="openwin('${sheetMain.id}')"/>
	  			&nbsp;&nbsp;&nbsp;&nbsp;
	  			<input type="button" class="submit" value="代维回复内容" onclick="searchContent('${sheetMain.id}')"/>
        	  		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask" />
        	  		 <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkTaskComplete"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入完成情况，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
        	    <%}else if(operateType.equals("206")){ %>
        	        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask" />
        	        <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.linkTaskComplete"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入完成情况，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
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
		            <td  class="label"><bean:message bundle="commontask" key="commontask.fenpairesion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入分派理由，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
        	    <% }else if(operateType.equals("8")){ %>
         			 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask" />
         			  <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.yijiaoresion"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入移交理由，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
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
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="commontask" />		   
			     </td>
			   </tr>
			<%}%> 
			<!-- end by yangna -->      	        	
         <%}else if( taskName.equals("AffirmHumTask") ){%>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />          
         	 <%if(operateType.equals("212")){ %>
         		 <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask" />
         		  <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.advice"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入建议，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>
         		 
            <%}else if(operateType.equals("211")){ %>
            	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Receive" />
         		  <tr>
		            <td  class="label"><bean:message bundle="commontask" key="commontask.advice"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTaskComplete" id="${sheetPageName}linkTaskComplete" 
    				  alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入建议，最多输入1000汉字')}'">${sheetLink.linkTaskComplete}</textarea>
                    </td>
		          </tr>         		 
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
			      alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入归档内容，最多输入1000汉字')}'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			 
         			 
         			 
              <%}%>
          <%}%>
          
     <%if(taskName.equals("TaskCreateAuditHumTask")||taskName.equals("ExcuteHumTask")||taskName.equals("TaskCompleteAuditHumTask")||taskName.equals("AffirmHumTask")) {%> 
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
<%if(taskName.equals("TaskCreateAuditHumTask")){ %>
     <% if(operateType.equals("201")){ %>
   <fieldset>
	 <legend>
	 </legend>
		<eoms:chooser id="test"
		   category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"
		   data="${sendUserAndRoles}"/>

   </fieldset>
<% } }%>
  
<%if(taskName.equals("ExcuteHumTask")){ %>
<% if(operateType.equals("8")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="commontask" key="role.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="commontask" key="role.excute"/>
			 </span>
	 </legend>

			<eoms:chooser id="test"
			   category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"/>
	
   </fieldset>
<% } %>
<% if(operateType.equals("10")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="commontask" key="role.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="commontask" key="role.excute"/>
			 </span>
	 </legend>
      	<eoms:chooser id="test"
		   category="[{id:'dealPerformer',childType:'user,dept',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>

   </fieldset>
<% } %>
<% if(operateType.equals("206")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="commontask" key="role.toOrgName"/>
			 <span id="roleName">:<bean:message bundle="commontask" key="role.TaskCompleteAudit"/>
			 </span>
	 </legend>
      <eoms:chooser id="test"
		category="[{id:'auditPerformer',text:'${eoms:a2u('审核')}',childType:'user',limit:'1',allowBlank:false,vtext:'${eoms:a2u('请选择审核对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]" 
		/>

 </fieldset>
<% } %>
<% if(operateType.equals("205")){ %>
   <fieldset>
	 <legend>
	     	 <bean:message bundle="commontask" key="role.toOrgName"/>
			 <!--  <span id="roleName">:${eoms:a2u('上一级任务执行人(')}
			 <eoms:id2nameDB id="${preLink.operateUserId}" beanId="tawSystemUserDao"/>
			 ${eoms:a2u(')')}
			 </span>-->
	 </legend>
   </fieldset>
<% } %>
<%} %>

<%if(taskName.equals("cc")) {%>	
	<fieldset id="link4">
	 <legend>
	     	 <bean:message bundle="commontask" key="role.toOrgName"/>
			 <span id="roleName">:${eoms:a2u('抄送人')}
			 </span>
	 </legend>	
	 <div class="x-form-item" >
		<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
		   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
	 </div> 		   
	   </fieldset>		
<%} %>   