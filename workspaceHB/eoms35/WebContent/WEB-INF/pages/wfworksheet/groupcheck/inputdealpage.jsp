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
 
<%@ include file="/WEB-INF/pages/wfworksheet/groupcheck/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		 <input type="hidden" id="tmpCompleteLimit" value="" />
         <input type="hidden" name="linkBeanId" value="iGroupCheckLinkManager"/> 
         <input type="hidden" name="beanId" value="iGroupCheckMainManager"/> 
         <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckMain"/>	
         <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.groupcheck.model.GroupCheckLink"/>
         <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
		<c:choose>
		<c:when test="${task.subTaskFlag == 'true'}">
			<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
		</c:when>
		</c:choose>      
		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="operateType" id="operateType" value="4" />
			<c:choose>
				<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" /> 
				</c:when>
				<c:when test="${fPreTaskName =='DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
			<c:otherwise>
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName }" />
			</c:otherwise>
		</c:choose>
		<tr>
			<td class="label">
				备注说明
			</td>
			<td colspan="3"> 
				<textarea name="remark" class="textarea max" id="remark" alt="allowBlank:true,width:500,vtext:'请最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
			</td>
		</tr>
	<%} %>        

		
		
			
			
			
 			
 			<%if(taskName.equals("CityCheck")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" /> 
 			
 			
 			
 				<%if(operateType.equals("101") ||operateType.equals("11")){ %>
			<input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />	 
 				
 		<tr>
 				
 				<td class="label">
				<!-- 联系人 -->
				<bean:message bundle="groupcheck" key="groupCheckLink.linkContact"/>
			</td>
			<td>
				<input type="text"  class="text" name="linkContact" id="linkContact"  alt="allowBlank:true,maxLength:1000,vtext:'请填入 联系人 信息，最多输入 1000 字符'" value="${sheetLink.linkContact}"/>
			</td>
 
 				
 				<td class="label">
				<!-- 联系人电话 -->
				<bean:message bundle="groupcheck" key="groupCheckLink.linkContactPhone"/>
			</td>
			<td>
				<input type="text"  class="text" name="linkContactPhone" id="linkContactPhone"  alt="allowBlank:true,maxLength:1000,vtext:'请填入 联系人电话 信息，最多输入 1000 字符'" value="${sheetLink.linkContactPhone}"/>
			</td>
		</tr>
 				
 			<tr>
 				<td class="label">
				<!-- 自动发现核查原因 -->
				<bean:message bundle="groupcheck" key="groupCheckLink.linkAutoFind"/>*
			</td>
			<td colspan="3">
				<eoms:comboBox name="linkAutoFind" id="linkAutoFind" 
            	      initDicId="1013701"  alt="allowBlank:false" styleClass="select-class" onchange="" defaultValue="${sheetLink.linkAutoFind}" />
			</td>
		</tr>
		
		<tr>
			<td class="label">
				<!-- 核查说明 -->
				<bean:message bundle="groupcheck" key="groupCheckLink.linkExplain"/>*
			</td>
			<td colspan="3">
				 <textarea name="linkExplain" class="textarea max" id="linkExplain" 
			        alt="width:500,maxLength:1600,vtext:'请填入 核查说明 信息，最多输入 1000 字符',allowBlank:false" alt="width:'500px'">${sheetLink.linkExplain}</textarea>
			  </td>
			</td>
		</tr>
 
 				
 				
 <%}%>
 
 			
 			
 			<%}if(taskName.equals("HoldTask")) {%>
			<input type="hidden" name="operateType" id="operateType" value="<%=operateType %>" />
 			
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
              				scope="request" idField="nodeAccessories" appCode="groupcheck" />		   
			     </td>
		</tr>
	 <%}%>
     <%if(taskName.equals("CityCheck")  || taskName.equals("HoldTask") ) {%> 
      	<%if(operateType.equals("61")){ %>
		<input type="hidden" name="operateType" id="operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 		
		<%} }%>
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

  
			
			
			
 			
 				
 				 <% if(taskName.equals("CityCheck")){ %> 
 				
 			   		<% if(operateType.equals("101")){ %>
 			   			

 			   		<% } %>
 			
			
 				<% } %> 