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
 
<%@ include file="/WEB-INF/pages/wfworksheet/numberapply/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
		 <input type="hidden" id="tmpCompleteLimit" value="" />
         <input type="hidden" name="linkBeanId" value="iNumberApplyLinkManager"/> 
         <input type="hidden" name="beanId" value="iNumberApplyMainManager"/> 
         <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.numberapply.model.NumberApplyMain"/>	
         <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.numberapply.model.NumberApplyLink"/>
        <c:if test="${taskName != 'HoldTask' }">
	      <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
	    </c:if>
	   
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
 			<%if(taskName.equals("PermitTask")) {%>			
 				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 
 				 <%if(operateType.equals("90")||operateType.equals("55")){ %>
 				 <input type="hidden" name="phaseId" id="phaseId" value="AllotResourceTask" />	
 				 <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="" />
					<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="" />
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
 				<tr>				
 				<td class="label">审核结果*</td>
			    <td class="content" colspan="3">
				    <eoms:comboBox name="linkIfPass" id="linkIfPass" initDicId="1010401" defaultValue="${sheetLink.linkIfPass}" alt="allowBlank:false" onchange="ifAuditPass(this.value);"/>
			    </td>
			    </tr>
			<tr>				
 				<td class="label"><bean:message bundle="numberapply" key="numberApplyLink.linkPermitsuggest"/>*</td>
			<td class="content" colspan="3">
				<textarea class="textarea max" name="linkPermitsuggest" id="linkPermitsuggest" alt="allowBlank:false,maxLength:255,vtext:'请填入 审核意见 信息，最多输入 255 字符'">${sheetLink.linkPermitsuggest}</textarea>
			</td>
		</tr>
 <%}%>
 			<%}if(taskName.equals("AllotResourceTask")) {%>
 				<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 
 				 <%if(operateType.equals("91")||operateType.equals("11")){ %>
 				 <input type="hidden" name="phaseId" id="phaseId" value="HoldTask" />
 				 <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${sheetMain.sendUserId}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${sheetMain.sendUserId}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${sheetMain.sendOrgType}" />
<%}%>
          <%}else if( taskName.equals("HoldTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true" />
      			<input type="hidden" name="operateType" id="operateType" value="18" />
      			<input type="hidden" name="phaseId" id="phaseId" value="Over" />
      			<input type="hidden" name="status" id="status" value="1"/>	
         			 
	 		 <tr>
			  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
			    <td colspan='3'>
			      <eoms:comboBox name="holdStatisfied" 
			        id="holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
			    </td>
		     </tr>
			  
			  <tr>
			  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
			    <td colspan="3">
			      <textarea name="endResult" id="endResult" class="textarea max"
			      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			    			 
              <%}%>            
          <%}%>
        <%if(!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")){ %>  
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
              				scope="request" idField="nodeAccessories" appCode="numberapply" />		   
			     </td>
		</tr>
	 <%}%>
     <%if(taskName.equals("PermitTask")  || taskName.equals("AllotResourceTask")  || taskName.equals("HoldTask") ) {%> 
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
 		<% if(taskName.equals("PermitTask")){ %> 
 			   <% if(operateType.equals("90")){ %>
 			   	<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">
									 </span>
							  </legend>
							  <div class="x-form-item" >
				  					 <eoms:chooser id="sendRole"  type="dept"  flowId="93" data="[{id:'huangfen',nodeType:'user',categoryId:'dealPerformer'}]" config="{returnJSON:true,showLeader:true}"
			   						category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,dept',limit:'none',text:'抄送'}]" />				   
							    </div>
  						</fieldset>
 			   		<% } %>
 				<% } if(taskName.equals("AllotResourceTask")){ %>
 				
 				
 			   		<% if(operateType.equals("91")){ %>
 			   			<fieldset>
							  <legend>
							     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
									 <span id="roleName">
									 </span>
							  </legend>
  						</fieldset>
 			   		<% } %>		
 				<% } %> 
<script type="text/javascript">
function ifAuditPass(input){
	if('<%=operateType%>'!='55'){
	if(input=="101040101"){
		chooser_sendRole.enable();
		//审核通过到资源分配
		$('${sheetPageName}phaseId').value='AllotResourceTask';
		$('${sheetPageName}operateType').value='901';
			$('${sheetPageName}dealPerformer').disabled=true;
			$('${sheetPageName}dealPerformerLeader').disabled=true;
			$('${sheetPageName}dealPerformerType').disabled=true;
		$('roleName').innerHTML="资源管理员";
	} else if(input=="101040102"){
		//审核不通过到驳回  
		chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='RejectTask';
		$('${sheetPageName}operateType').value='902';
			$('${sheetPageName}dealPerformer').disabled=false;
			$('${sheetPageName}dealPerformerLeader').disabled=false;
			$('${sheetPageName}dealPerformerType').disabled=false;
		    $('${sheetPageName}dealPerformer').value='${sheetMain.sendUserId}';
			$('${sheetPageName}dealPerformerLeader').value='${sheetMain.sendUserId}';
			$('${sheetPageName}dealPerformerType').value='${sheetMain.sendOrgType}';
		$('roleName').innerHTML="地市分公司";
	} else{
	chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
 	var frm = document.forms[0];
    var temp = frm.linkIfPass ? frm.linkIfPass.value : '';
   if("${taskName}"=="PermitTask"&&temp!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
     ifAuditPass(temp);
    }
     </script>