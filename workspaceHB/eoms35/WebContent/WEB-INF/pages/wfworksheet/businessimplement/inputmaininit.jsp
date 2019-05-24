<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.boco.local.model.ParseModel" %> 
<%
 String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
 System.out.println("roleId===="+roleId);
 
 ParseModel parseModel = (ParseModel)request.getSession().getAttribute("parseModel");
%>
<!-- base info -->
<input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}mainId" id="${sheetPageName}mainId" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sheetKey" id="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
<input type="hidden" name="${sheetPageName}sheetKey" id="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}sendUserId" id="${sheetPageName}sendUserId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="${sheetPageName}sendOrgType" id="${sheetPageName}sendOrgType" value="${sheetMain.sendOrgType}"/>
<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="${status}"/>
<input type="hidden" name="${sheetPageName}aiid" id="${sheetPageName}aiid" value="${taskId}"/>
<input type="hidden" name="${sheetPageName}piid" id="${sheetPageName}piid" value="${piid}"/>
<input type="hidden" name="${sheetPageName}activeTemplateId" id="${sheetPageName}activeTemplateId" value="${taskName}"/>		    
<input type="hidden" name="${sheetPageName}correlationKey" id="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
<input type="hidden" name="${sheetPageName}parentCorrelation" id="${sheetPageName}parentCorrelation" value="${sheetMain.parentCorrelation}"/>
<input type="hidden" name="${sheetPageName}parentSheetName" id="${sheetPageName}parentSheetName" value="${sheetMain.parentSheetName}"/>
<input type="hidden" name="${sheetPageName}parentSheetId" id="${sheetPageName}parentSheetId" value="${sheetMain.parentSheetId}"/>
<input type="hidden" name="${sheetPageName}operateUserId" id="${sheetPageName}operateUserId" value="${sheetMain.sendUserId}"/>
<input type="hidden" name="${sheetPageName}operateDeptId" id="${sheetPageName}operateDeptId" value="${sheetMain.sendDeptId}"/>
<input type="hidden" name="${sheetPageName}tkid" id="${sheetPageName}tkid" value="${tkid}"/>
<input type="hidden" name="${sheetPageName}preLinkId" id="${sheetPageName}preLinkId" value="${preLink.id}"/>
<input type="hidden" name="${sheetPageName}sendDeptId" id="${sheetPageName}sendDeptId" value="${sheetMain.sendDeptId}"/>
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${preLink.operateType}"/>
<input type="hidden" name="${sheetPageName}taskAcceptLimit" id="${sheetPageName}taskAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"/>
<input type="hidden" name="${sheetPageName}taskCompleteLimit" id="${sheetPageName}taskCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"/>
<input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
<input type="hidden" name="methodBeanId"  id="methodBeanId" value="${methodBeanId}"/>
<table class="formTable">
<tr>
  <td class="label"><bean:message bundle="sheet" key="mainForm.title"/>*</td>
  <td colspan="3">
  <c:if test="${!empty taskName}">
  <c:if test="${empty sheetMain.title}">
    <input type="text" name="${sheetPageName}title" class="text max" id="${sheetPageName}title" 
		value="${sheetMain.title}" alt="allowBlank:false,vtext:'<bean:message bundle="sheet" key="mainForm.titleCheck"/>'"/>
  </c:if>
  <c:if test="${!empty sheetMain.title}">
	<bean:write name="sheetMain" property="title" scope="request"/>
  </c:if>
  </c:if>
  <c:if test="${empty taskName}">
      <input type="text" name="${sheetPageName}title" class="text max" id="${sheetPageName}title" 
		value="<%=parseModel.getTitle() %>" alt="allowBlank:false,vtext:'<bean:message bundle="sheet" key="mainForm.titleCheck"/>'"/>
  </c:if>
  </td>
</tr>

<tr>
  <td class="label"><bean:message bundle="sheet" key="mainForm.sendUserName"/>*</td>
  <td class="content"><eoms:id2nameDB id="${sheetMain.sendUserId}" beanId="tawSystemUserDao"/></td>
  <td class="label"><bean:message bundle="sheet" key="mainForm.sendDeptName"/>*</td>
  <td class="content"><eoms:id2nameDB id="${sheetMain.sendDeptId}" beanId="tawSystemDeptDao"/></td>
</tr>

<!--  <tr>
  <td class="label"><bean:message bundle="sheet" key="mainForm.sendRoleName"/>*</td>
  <td colspan="3">
     <c:if test="${empty sheetMain.sendRoleId}">               
        <eoms:roleComboBox name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" 
	  	  userId="${sheetMain.sendUserId}" roleId="${roleId}" defaultValue="${sheetMain.sendRoleId}"/>     
     </c:if>
     <c:if test="${!empty sheetMain.sendRoleId}">
       <input type="hidden" name="${sheetPageName}sendRoleId" id="${sheetPageName}sendRoleId" value="${sheetMain.sendRoleId}"/>
       <c:if test="${empty endRoleId}">                      
           <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${sheetMain.sendRoleId}"/>                       
       </c:if>
        <c:if test="${!empty endRoleId}">                  
           <input type="hidden" name="${sheetPageName}operateRoleId" id="${sheetPageName}operateRoleId" value="${endRoleId}"/>                       
       </c:if>
       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
     </c:if>
  </td>
  
</tr> -->

 <tr>
   <td class="label"><bean:message bundle="sheet" key="mainForm.sendContact"/>*</td>
  <td >
    <input type="text" class="text" name="${sheetPageName}sendContact" id="${sheetPageName}sendContact" value="${sheetMain.sendContact}" alt="allowBlank:false"/>
  </td>
   <td class="label"><bean:message bundle="sheet" key="mainForm.sendTime"/>*</td>
   <td >
    <input type="text" class="text" name="${sheetPageName}sendTime" readonly="readonly" 
					id="${sheetPageName}sendTime" value="${eoms:date2String(sheetMain.sendTime)}" 
					onclick="popUpCalendar(this, this)" alt="allowBlank:false"/> 	
  </td>
 </tr>






<c:if test="${status==1}">
<%--
  <tr>
    <td class="label"><bean:message bundle="sheet" key="mainForm.endUserName"/></td>
	<td> 
      <input type="hidden" name="${sheetPageName}endUserId" value="${endUserId}"/>
      <eoms:id2nameDB id="${endUserId}" beanId="tawSystemUserDao"/>&nbsp;
    </td>
    <td class="label"><bean:message bundle="sheet" key="mainForm.endDeptName"/></td>
	<td>
      <input type="hidden" name="${sheetPageName}endDeptId" value="${endDeptId}"/>
      <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
    </td>
  </tr>
  
  <tr>
    <td class="label"><bean:message bundle="sheet" key="mainForm.endRoleName"/></td>
    <td colspan="3">
      <input type="hidden" name="${sheetPageName}endRoleId" value="${endRoleId}"/>
      <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
    </td>
  </tr>
--%>

  <tr>
  	<td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></td>
    <td colspan="3">
      <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied" initDicId="10303" styleClass="select"/>
    </td>
  </tr>
  
  <tr>
  	<td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
    <td colspan="3">
      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" alt="width:'500px',allowBlank:false"></textarea>
    </td>
  </tr>

</c:if>

<c:if test="${status==-1}">
	      <tr>
	      	<td class="label"><bean:message bundle="sheet" key="mainForm.endUserName"/></td>
	        <td>
	          <input type="hidden" name="${sheetPageName}endUserId" value="${endUserId}"/>
	          <eoms:id2nameDB id="${sheetMain.endUserId}" beanId="tawSystemUserDao"/>&nbsp;
	        </td>
			<td class="label"><bean:message bundle="sheet" key="mainForm.endDeptName"/></td>
	        <td>
	          <input type="hidden" name="${sheetPageName}endDeptId" value="${endDeptId}"/>
	          <eoms:id2nameDB id="${endDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
	        </td>
	      </tr>
	  
	    <tr>
	       <td class="label"><bean:message bundle="sheet" key="mainForm.endRoleName"/></td>
	       <td colspan="3">
	          <input type="hidden" name="${sheetPageName}endRoleId" value="${endRoleId}"/>
	          <eoms:id2nameDB id="${endRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
	       </td>
	    </tr>
	    <tr>
	      <td class="label">
	      	<bean:message bundle="sheet" key="mainForm.cancelReason"/>
	      </td>
	       <td colspan="3">          
	         <textarea class="textarea" name="${sheetPageName}endResult" id="${sheetPageName}endResult" alt="allowBlank:false"></textarea>
	       </td>
	    </tr>

</c:if>
    </table>



		
	<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessImplementMainFlowProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${sheetPageName}operateType" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink"/>
    <br>
	<!-- sheet info --> 
     <table class="formTable">
					 <tr>
					   <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/></td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
							id="${sheetPageName}sheetAcceptLimit" value="<%=parseModel.getEndAcceptLimit() %>" 
							onclick="popUpCalendar(this, this)"
							alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>   
					  </td>
					  
					  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/></td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
							id="${sheetPageName}sheetCompleteLimit" value="<%=parseModel.getEndDealLimit() %>" 
							onclick="popUpCalendar(this, this)"
							alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>   
					  </td>
					</tr>
	                <tr>
		               <td  class="label"><bean:message bundle="businessimplement" key="businessimplement.mainNetSort1"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSort1" id="${sheetPageName}mainNetSort1" 
            	      initDicId="1010104" sub="${sheetPageName}mainNetSort2" alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort1}" styleClass="select-class"/>
			        </td>	                
		               <td  class="label"><bean:message bundle="businessimplement" key="businessimplement.mainNetSort2"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSort2" id="${sheetPageName}mainNetSort2" 
            	      initDicId="${sheetMain.mainNetSort1}" sub="${sheetPageName}mainNetSort3" alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort2}" styleClass="select-class"/>
			        </td>	                
		            </tr>

	                <tr>
		                 <td  class="label"><bean:message bundle="businessimplement" key="businessimplement.mainNetSort3"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSort3" id="${sheetPageName}mainNetSort3" 
            	      initDicId="${sheetMain.mainNetSort2}"  alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort3}" styleClass="select-class"/>
			        </td>	                
		               <td  class="label"><bean:message bundle="businessimplement" key="businessimplement.mainTaskType"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainTaskType" id="${sheetPageName}mainTaskType" 
            	      initDicId="1010102"  alt="allowBlank:false" defaultValue="${sheetMain.mainTaskType}" styleClass="select-class"/>
			        </td>	                
		            </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="businessimplement" key="businessimplement.mainTaskDescription"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainTaskDescription" id="${sheetPageName}mainTaskDescription" 
    				  alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入任务描述，多输入100汉字')}'"><%=parseModel.getMainTaskContent() %></textarea>
                    </td>
		          </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="businessimplement" key="businessimplement.mainRemark"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainRemark" id="${sheetPageName}mainRemark" 
    				  alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('最多输入1000汉字')}'">${sheetMain.mainRemark}</textarea>
                    </td>
		          </tr>
	<%-- accessories --%>
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
		    <td class="label">
		    	<bean:message bundle="sheet" key="mainForm.accessories"/>
			</td>	
			<td colspan="3">					

		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="businessimplement" /> 				
		    </td>
		  </tr>		
	</table>

	
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:${eoms:a2u('任务创建审批人')}
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'user,dept',limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择任务执行人')}'},{id:'auditPerformer',childType:'user',text:'${eoms:a2u('审批')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"
			   data="${sendUserAndRoles}"/>
			 </div>
	</fieldset>
	</c:if>
	
<script type="text/javascript">

	function parse(){
		alert("aaaa");
		var frm = document.forms[0];
		alert(frm);
		frm.enctype="multipart/form-data";
		frm.action="/eoms35/local/upload.do";
		alert(frm.action);
		frm.submit();
	}
</script>
	
	
