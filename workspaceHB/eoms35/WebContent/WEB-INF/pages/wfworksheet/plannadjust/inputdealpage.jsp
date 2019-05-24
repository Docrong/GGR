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
 
<%@ include file="/WEB-INF/pages/wfworksheet/plannadjust/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		 <input type="hidden" id="tmpCompleteLimit" value="" />
         <input type="hidden" name="linkBeanId" value="iPlannAdjustLinkManager"/> 
         <input type="hidden" name="beanId" value="iPlannAdjustMainManager"/> 
         <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.plannadjust.model.PlannAdjustMain"/>	
         <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.plannadjust.model.PlannAdjustLink"/>
         <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
		<c:choose>
		<c:when test="${task.subTaskFlag == 'true'}">
			<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
		</c:when>
		</c:choose>      
		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="operateType" id="operateType" value="4" />
		  	<input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}" />		
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

		
		
			
			
			
 			
 			<%if(taskName.equals("ConfirmTask")) {%>
			<input type="hidden" name=operateType" id="operateType" value="<%=operateType %>" /> 
 			
 			
 			
 				<%if(operateType.equals("102")||operateType.equals("11")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="AuditTask" />	 
 				
 				
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("AuditTask")) {%>
			<input type="hidden" name=operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("103")||operateType.equals("11")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 审核确认结果 -->
				<bean:message bundle="plannadjust" key="plannAdjustLink.checkResult"/>*
			</td>
			<td>
				<eoms:comboBox name="checkResult" id="checkResult" 
						 initDicId="1013004"  
						defaultValue="${sheetLink.checkResult}" alt="allowBlank:false"/>
			</td>
 
 				
 				<td class="label">
				<!-- 驳回理由 -->
				<bean:message bundle="plannadjust" key="plannAdjustLink.reasonRejection"/>
			</td>
			<td>
				<input type="text"  class="text" name="reasonRejection" id="reasonRejection"  alt="maxLength:2000,vtext:'请填入 驳回理由 信息，最多输入 2000 字符'" value="${sheetLink.reasonRejection}"/>
			</td>
		</tr>
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("HoldTask")) {%>
			<input type="hidden" name=operateType" id="operateType" value="<%=operateType %>" />
 			
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
              				scope="request" idField="nodeAccessories" appCode="plannadjust" />		   
			     </td>
		</tr>
	 <%}%>
     <%if(taskName.equals("ConfirmTask")  || taskName.equals("AuditTask")  || taskName.equals("HoldTask") ) {%> 
		<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 		
		<%}%>
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

  
			
			
			
 			
 				
 				 <% if(taskName.equals("ConfirmTask")){ %> 
 				
 			   		<% if(operateType.equals("102")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:规划站址调整审核人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="ConfirmTask0"  type="role" roleId="1972" flowId="618" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
 				<% } if(taskName.equals("AuditTask")){ %>
 				
 				
 			   		<% if(operateType.equals("103")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:规划站址调整申请人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="AuditTask0"  type="role" roleId="1970" flowId="618" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
			
 				<% } %> 