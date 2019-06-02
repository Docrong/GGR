<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>		
<%

String _operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
String _taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("taskName"));
System.out.println("===_taskName==>"+_taskName);
 %>		
		<!-- base info -->
		    <input type="hidden" name="${sheetPageName}linkId" id="${sheetPageName}linkId" value="${sheetLink.id}"/>
		    <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetLink.id}"/>
		    <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
			<input type="hidden" name="${sheetPageName}piid" value="${sheetMain.piid}"/>
		    <input type="hidden" name="${sheetPageName}aiid" value="${sheetLink.tkid}"/>
		    <input type="hidden" name="${sheetPageName}activeTemplateId" value="${taskName}"/>
		    <input type="hidden" name="${sheetPageName}mainId" value="${sheetMain.id}"/>
		    <input type="hidden" name="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
		    <input type="hidden" name="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>		    
		    <input type="hidden" name="${sheetPageName}TKID" value="${sheetLink.tkid}"/>
		    <input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
		    <input type="hidden" name="${sheetPageName}taskCompleteLimit" id="${sheetPageName}taskCompleteLimit" value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
		    <input type="hidden" name="${sheetPageName}taskAcceptLimit" id="${sheetPageName}taskAcceptLimit" value="${eoms:date2String(preLink.nodeAcceptLimit)}"/>
		    <input type="hidden" name="${sheetPageName}taskName" value="${taskName}"/>
		     <input type="hidden" name="${sheetPageName}taskStatus" value="${taskStatus}"/>
		    <input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
           <input type="hidden" name="methodBeanId"  id="methodBeanId" value="${methodBeanId}"/>
           <input type="hidden" name="linkBeanId" value="iComplaintLinkManager"/>
		<table class="formTable">
		  <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
		  <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
             <input type="hidden" name="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
           </td>
           <td class="content">
              <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
           </td>
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
             <input type="hidden" name="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
           </td>
           <td class="content"> 
                 <eoms:id2nameDB id="${sheetLink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
           </td>
		  </tr>
		  
		  <%if(!_operateType.equals("-10") ){ %>
		  <tr>
		    <td class="label">
		       <bean:message bundle="sheet" key="linkForm.operateRoleName"/>*
                  <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
               </td>
               <td colspan="3">
               <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
               <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
           </td>
		  </tr>
		  <%} %>
		  
		  <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.operaterContact"/>*           
           </td>
           <td class="content" colspan="3">
              <input type="text" class="text" name="${sheetPageName}operaterContact" 
                id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
           </td>
		  </tr>		  
		  
		  <%		 
		    if(!_operateType.equals("61") && !_operateType.equals("4") && !_operateType.equals("54") && !_operateType.equals("56")&& !_operateType.equals("-10")&& !_operateType.equals("18") 
		    	&& !_operateType.equals("6") && !_operateType.equals("7") && !_operateType.equals("11") && !_operateType.equals("46") 
		    	&& !_taskName.equals("SendDeferExamine") && !_operateType.equals("66") && !_operateType.equals("64") && !_taskName.equals("reply")  ){
		  %>
		   		  

		  
		   <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*         
           </td>
           <td class="content">
              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" 
                 alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
                 />
          
           </td>
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
           </td>
           <td class="content"> 
           	<%if(_operateType.equals("1")){ %>
	            <input class="text" type="text" 
	               name="${sheetPageName}nodeCompleteLimit" 
	                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit" 
	                  alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
	                  />
	          
            <%}else if(_operateType.equals("8") || _operateType.equals("10")){ %>      
            	<%if(_taskName.equals("FirstExcuteHumTask")){ %>
		            <input class="text" type="text" onclick="popUpCalendar(this, this)"
		               name="${sheetPageName}nodeCompleteLimit"
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" id="${sheetPageName}nodeCompleteLimit" 
		                  alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
		                  />               	
	                  
            	<%} %>
            	<%if(_taskName.equals("SecondExcuteHumTask")){ %>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit" 
		                  alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
		                  />             
 	
            	<%} %>         	
            <%}else{ %>
	            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
	               name="${sheetPageName}nodeCompleteLimit" 
	                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" 
	                  alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
	                  /> 
	           
            <%} %>
           </td>
		  </tr>
		  <%}else{ %>
			  <input type="hidden" name="${sheetPageName}nodeAcceptLimit" readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" id="${sheetPageName}nodeAcceptLimit" />
			  <input type="hidden" name="${sheetPageName}nodeCompleteLimit" readonly="readonly" value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit"/>
	      <%} %>  
		</table>     
