<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.industrysms.model.IndustrySmsMain" %>

<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 request.setAttribute("operateType",operateType);
 IndustrySmsMain basemain = (IndustrySmsMain)request.getAttribute("sheetMain");
 String isUrgent = StaticMethod.nullObject2String(basemain.getIsUrgent());
 String regional = StaticMethod.nullObject2String(basemain.getRegional());
 System.out.println("=====isUrgent======"+isUrgent);
 System.out.println("=====regional======"+regional);
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
 
<%@ include file="/WEB-INF/pages/wfworksheet/industrysms/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		 <input type="hidden" id="tmpCompleteLimit" value="" />
         <input type="hidden" name="linkBeanId" value="iIndustrySmsLinkManager"/> 
         <input type="hidden" name="beanId" value="iIndustrySmsMainManager"/> 
         <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.industrysms.model.IndustrySmsMain"/>	
         <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.industrysms.model.IndustrySmsLink"/>
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

		
		
			
			
			
 			
 			<%if(taskName.equals("AuditHumTask")) {%>
			<input type="hidden" name=operateType" id="operateType" value="<%=operateType %>" /> 
 			
 			
 			
 				<%if(operateType.equals("102")||operateType.equals("11")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="DataHumTask" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 审核结果 -->
				<bean:message bundle="industrysms" key="industrySmsLink.approveResult"/>*
			</td>
			<td>
				<input type="text"  class="text" name="approveResult" id="approveResult"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 审核结果 信息，最多输入 1000 字符'" value="${sheetLink.approveResult}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 审核意见 -->
				<bean:message bundle="industrysms" key="industrySmsLink.approveOpinion"/>*
			</td>
			<td>
				<input type="text"  class="text" name="approveOpinion" id="approveOpinion"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 审核意见 信息，最多输入 1000 字符'" value="${sheetLink.approveOpinion}"/>
			</td>
		</tr>
 
 <%}%>
 
  				<%if(operateType.equals("22")||operateType.equals("22")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="DraftTask" />	 
 				
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("DataHumTask")) {%>
			<input type="hidden" name=operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("103")||operateType.equals("11")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />	 
 				
 				
 				<tr>
 				
 				<td class="label">
				<!-- 数据制作结果 -->
				数据制作结果*
			</td>
			<td>
				<input type="text"  class="text" name="dataResult" id="dataResult"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 数据制作结果 信息，最多输入 1000 字符'" value="${sheetLink.dataResult}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 数据制作失败原因 -->
				数据制作失败原因*
			</td>
			<td>
				<input type="text"  class="text" name="failureInfo" id="failureInfo"  alt="allowBlank:false,maxLength:1000,vtext:'请填入 数据制作失败原因 信息，最多输入 1000 字符'" value="${sheetLink.failureInfo}"/>
			</td>
		</tr>
 
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("HoldTask")) {%>
			<input type="hidden" name=operateType" id="operateType" value="<%=operateType %>" />
 			
 				<%if(operateType.equals("18")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="Over" />
			<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
      	    <input type="hidden" name="status" id="status" value="1"/>	
      	    <%if(isUrgent.equals("1030101") && regional.equals("101250202")){ %>
				<tr><td class="label">
						<!-- 集团批复工单号 -->
						<bean:message bundle="industrysms" key="industrySmsMain.groupNumber"/>*
					</td>
					<td>
						<input type="text"  class="text" name="groupNumber" id="groupNumber"  alt="allowBlank:false,maxLength:64,vtext:'请填入 集团批复工单号 信息，最多输入 64 字符'" value="${sheetMain.groupNumber}"/>
					</td>
				</tr> 
			<%}%>
 				
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
              				scope="request" idField="nodeAccessories" appCode="industrysms" />		   
			     </td>
		</tr>
	 <%}%>
     <%if(taskName.equals("AuditHumTask")  || taskName.equals("DataHumTask")  || taskName.equals("HoldTask") ) {%> 
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

  
			
			
			
 			
 				
 				 <% if(taskName.equals("AuditHumTask")){ %> 
 				
 			   		<% if(operateType.equals("102")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:数据制作人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="AuditHumTask0"  type="role" roleId="1862" flowId="616" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
 				<% } if(taskName.equals("DataHumTask")){ %>
 				
 				
 			   		<% if(operateType.equals("103")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">:建单人
									 </span>
							  </legend>
							  <div class="x-form-item" >
							  		
									<eoms:chooser id="DataHumTask0"  type="role" roleId="1860" flowId="616" config="{returnJSON:true,showLeader:true}"  
									   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
									  data="${sendUserAndRoles}" />
									  			   
							    </div>
  						</fieldset>
 			   		<% } %>
 			
			
 				<% } %> 
<script type="text/javascript">
	var taskName = '<%=taskName %>';
	var operateType = '<%=operateType %>';
	  function addGroupNumber()                                   
	  {  
		if (taskName == 'HoldTask' && operateType == '18') {
			var isUrgent = '<%=isUrgent %>';
			var regional = '<%=regional %>';
			var groupNumber = document.getElementById("groupNumber");
			if (isUrgent == '1030101' && regional == '101250202' && groupNumber.value == '') {
				alert('集团批复工单号不能为空');
				return false;
			}
		}
		alert(isUrgent);
	  }
</script>