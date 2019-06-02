<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String roleName="";
 %> 

<%@ include file="/WEB-INF/pages/wfworksheet/securityevaluate/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
		<!--  <caption><bean:message bundle="securityevaluate" key="securityevaluate.header"/></caption>-->
     <input type="hidden" name="${sheetPageName}beanId" value="iSecurityEvaluateMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.securityevaluate.model.SecurityEvaluateMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.securityevaluate.model.SecurityEvaluateLink"/>
    	<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
    <input type="hidden" name="${sheetPageName}mainNetSortOne" value="${sheetMain.mainNetSortOne}"/>
    <input type="hidden" name="${sheetPageName}mainNetSortTwo" value="${sheetMain.mainNetSortTwo}"/>
    <input type="hidden" name="${sheetPageName}mainNetSortThree" value="${sheetMain.mainNetSortThree}"/>
	<c:if test="${taskName != 'hold' }">
	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
	</c:if>
	<%if(taskName.equals("EvaluateTask")){ 
	     if(operateType.equals("90")|| operateType.equals("11")){%>
	     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
	     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
			      <tr>
		            <td  class="label"><bean:message bundle="securityevaluate" key="securityevaluate.linkAnalysisResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAnalysisResult" id="${sheetPageName}linkAnalysisResult" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入分析结果，最多输入2000字'">${sheetLink.linkAnalysisResult}</textarea>
                    </td>
		          </tr>
		             <tr>
					    <td class="label">
					    	<bean:message bundle="securityevaluate" key="securityevaluate.linkAnalysisReport"/>
						</td>	
						<td  colspan='3'>
		   		          <eoms:attachment name="sheetLink" property="linkAnalysisReport" 
            		          scope="request" idField="${sheetPageName}linkAnalysisReport" appCode="securityevaluate" alt="allowBlank:true"/> 
		             </td>
		           </tr>
	               
		  <%}} %>
		  
		  <%if(taskName.equals("AuditTask")){ 
	          if(operateType.equals("91")|| operateType.equals("55")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	                <tr>
		                 <td  class="label"><bean:message bundle="securityevaluate" key="securityevaluate.linkAuditOpinion"/>*</td>
		                <td colspan="3">  
	        <eoms:comboBox name="${sheetPageName}linkAuditOpinion" id="${sheetPageName}linkAuditOpinion"  defaultValue="${sheetLink.linkAuditOpinion}"
         	      initDicId="1011301"  alt="allowBlank:false" onchange="ifAuditPass(this.value);" styleClass="select-class"/>
              
              </td>            
		      <tr>
		            <td  class="label"><bean:message bundle="securityevaluate" key="securityevaluate.linkAuditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
                    </td>
		          </tr>
		          <%}} %>

		          
		          

		       
		<%if(operateType.equals("4")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		
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
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" >${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
		       
         <%if(taskName.equals("HoldTask")) {%>  
			<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>	     	
		    <tr>
		           <td  class="label"><bean:message bundle="securityevaluate" key="securityevaluate.linkIsStartProcess"/>*</td>
		           <td colspan="3">  
                    <input type="button" class="btn" value="启动安全处理问题流程" onclick="javascript:openwin()"> 
              
              </td>            
		      <tr>			 
			 <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
		    <td colspan="3">
		      <eoms:comboBox name="${sheetPageName}holdStatisfied" 
		        id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}" initDicId="10303" styleClass="select" alt="allowBlank:false"/>
		    </td>
		  </tr>		  
		  <tr>
		  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
		    <td colspan="3">
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请填入信息，最多输入125字'" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>					       			     	
     	   <%}else if(operateType.equals("92")){%>
     	    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
	        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />	
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />*
			    </td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" >${sheetLink.remark}</textarea>
			  </td>
			</tr> 				       			     	
     	    <%} }%>
     	
      <% if(taskName.equals("cc")){%>    
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" >${sheetLink.remark}</textarea>
		  </td>
		</tr>  
          <%} %> 
 
         
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />					
    	<%-- <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:1000,vtext:'请最多输入1000字'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	--%>
		
		<%}%>
  </table>
  
  
   	 <%if(taskName.equals("cc")) {%>	
		<fieldset id="link4">
	<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
		</fieldset>					
	 <%} %>   
	 
	 
	 <%if(taskName.equals("EvaluateTask")) {
	  	   if(operateType.equals("90")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="securityevaluate" key="securityevaluate.linkAuditObject"/>:
				 <bean:message bundle="securityevaluate" key="role.securitymanager"/>		 
		 </legend>		

			<eoms:chooser id="sendRole" type="role" roleId="325" flowId="071" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%}} %>
	 
	 	 <%if(taskName.equals("AuditTask")) {
	  	   if(operateType.equals("91")){ %>	
		<fieldset id="link4">
		 <legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>	 
		 </legend>
       </fieldset>		
	 <%}} %>
<script type="text/javascript">
  function ifAuditPass(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101130101"){
		//审核通过
		$('${sheetPageName}phaseId').value='HoldTask';
		$('${sheetPageName}operateType').value='911';
		$('roleName').innerHTML="建单人";
	} else if(input=="101130102"){
		//审核不通过  
		$('${sheetPageName}phaseId').value='EvaluateTask';
		$('${sheetPageName}operateType').value='912';
		$('roleName').innerHTML="安全维护组";
	} else{
			$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	
	}
	}
    var frm = document.forms[0];
    var temp1 = frm.linkAuditOpinion ? frm.linkAuditOpinion.value : '';
    	if(temp1 != ''){
		ifAuditPass(temp1);
	}
	function openwin() {
	
	var url="${app}/sheet/securitydeal/securitydeal.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSecurityEvaluateMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
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

	 
 
