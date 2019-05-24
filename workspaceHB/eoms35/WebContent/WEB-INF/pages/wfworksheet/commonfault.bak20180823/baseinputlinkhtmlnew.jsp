<%@page import="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="java.util.Date"%>
<%
String _operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
String _taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("taskName"));
System.out.println("===_operateType==>"+_operateType);
System.out.println("===_taskName==>"+_taskName);
 %>	


		<script type="text/javascript">
		     if($("${sheetPageName}operateTime").value==""){
		      var dt = new Date();
		      //alert(dt);
		      $("${sheetPageName}operateTime").value=dt.format('Y-m-d H:i:s');
		      }		      
		</script>


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
		    <input type="hidden" name="${sheetPageName}subTaskFlag" value="${task.subTaskFlag}"/>
		    <input type="hidden" name="sheetPageName" id="sheetPageName" value="${sheetPageName}"/>
           <input type="hidden" name="methodBeanId"  id="methodBeanId" value="${methodBeanId}"/>
           <input type="hidden" name="linkBeanId" value="iCommonFaultLinkManager"/>
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

		
		  <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.operaterContact"/> *          
           </td>
           <td class="content">
              <input type="text" class="text" name="${sheetPageName}operaterContact" 
                id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}" alt="allowBlank:false"/>
           </td>
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.operateTime"/>* 
           </td>
           <td class="content"> 
            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
               name="${sheetPageName}operateTime" readonly="readonly" 
                  value="${eoms:date2String(sheetLink.operateTime)}" id="${sheetPageName}operateTime" alt="allowBlank:false"/>
           </td>
		  </tr>
		  
		  <%		 
		    if(!_operateType.equals("61") && !_operateType.equals("46") && !_operateType.equals("4")  && !_operateType.equals("-10") && !_operateType.equals("18") && !_operateType.equals("11")
		    	&& !_operateType.equals("6") && !_operateType.equals("7") && !_operateType.equals("5") && !_operateType.equals("64")&& !_operateType.equals("66")
		    	&& !_operateType.equals("88") && !_operateType.equals("55") && !_taskName.equals("reply") ){
		  %>

		   <tr>
		   <td class="label">
		     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
           </td>
           <!--
           <td class="content">
              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" 
                 alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:true"/>
          
           </td>
           -->
           <td  class="content">
           <%
	Object objlink=request.getAttribute("sheetMain");
           	if(objlink!=null){
           	CommonFaultMain mainlink = (CommonFaultMain)objlink;
           	Date date1link = mainlink.getMainCompleteLimitT1();
           	Date date2link = mainlink.getMainCompleteLimitT2();
           	Date date3link = mainlink.getMainCompleteLimitT3();
           	           	
           	if(_operateType.equals("1")){ 
           		if(date2link.before(new Date())){
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeAcceptLimit"  readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeAcceptLimit"/>
           		<%
           		}else{
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeAcceptLimit" 
                  
           		<%}%>
           	<%}else if(_operateType.equals("2")){ 
           		if(date3link.before(new Date())){
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeAcceptLimit" /> 
           		<%
           		}else{
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeAcceptLimit" /> 
           		<%}%>
    
                          	
           	<%}else if(_operateType.equals("8") || _operateType.equals("10")){ %>           		
           		<%if(_taskName.equals("FirstExcuteHumTask")){
           		if(date1link.before(new Date())){
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeAcceptLimit"   readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" id="${sheetPageName}nodeAcceptLimit" />
           		<%
           		}else{
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" id="${sheetPageName}nodeAcceptLimit" />
           		<%} %>
           		
           		<%} %>           		
           		<%if(_taskName.equals("SecondExcuteHumTask")){ 
           		if(date2link.before(new Date())){
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeAcceptLimit"    readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeAcceptLimit" />
           		<%}else{%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeAcceptLimit" />
           		<%}%>
           		<%} %>           		
           		<%if(_taskName.equals("ThirdExcuteHumTask")){ 
           		if(date3link.before(new Date())){
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeAcceptLimit"  readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeAcceptLimit" />
           		<%
           		}else{
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeAcceptLimit" />
           		<%}%>
            		
           		<%} %>          	
           	<%}else{ %>
            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
               name="${sheetPageName}nodeAcceptLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeAcceptLimit" />           	
           	<%} }%> 
           	
           
           </td>
           
           
                      	
           
           
           
           
           
           
		   <td class="label">		     
             <bean:message bundle="sheet" key="linkForm.completeLimit"/> 
           </td>
           <td class="content"> 
           
           	<%
           	
           	Object obj=request.getAttribute("sheetMain");
           	if(obj!=null){
           	CommonFaultMain main = (CommonFaultMain)obj;
           	Date date1 = main.getMainCompleteLimitT1();
           	Date date2 = main.getMainCompleteLimitT2();
           	Date date3 = main.getMainCompleteLimitT3();
           	           	
           	if(_operateType.equals("1")){ 
           		if(date2.before(new Date())){
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeCompleteLimit"  readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%
           		}else{
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit" />   
           		<%}%>
           	<%}else if(_operateType.equals("2")){ 
           		if(date3.before(new Date())){
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeCompleteLimit"  readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeCompleteLimit" /> 
           		<%
           		}else{
           		%>
            <input class="text" type="text" 
               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeCompleteLimit" /> 
           		<%}%>
    
                          	
           	<%}else if(_operateType.equals("8") || _operateType.equals("10")){ %>           		
           		<%if(_taskName.equals("FirstExcuteHumTask")){
           		if(date1.before(new Date())){
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit"   readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%
           		}else{
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%} %>
           		
           		<%} %>           		
           		<%if(_taskName.equals("SecondExcuteHumTask")){ 
           		if(date2.before(new Date())){
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit"    readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%}else{%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%}%>
           		<%} %>           		
           		<%if(_taskName.equals("ThirdExcuteHumTask")){ 
           		if(date3.before(new Date())){
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit"   readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%
           		}else{
           		%>
		            <input class="text" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}" id="${sheetPageName}nodeCompleteLimit" />
           		<%}%>
            		
           		<%} %>          	
           	<%}else{ %>
            <input class="text"  type="text" 
               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" />           	
           	<%} %> 
           	
           </td>
		  </tr>
		  <%}}else{ %>			  
		  
		  <input type="hidden" name="${sheetPageName}nodeAcceptLimit" readonly="readonly" value="${eoms:date2String(task.acceptTimeLimit)}" id="${sheetPageName}nodeAcceptLimit" />
		  <input type="hidden" name="${sheetPageName}nodeCompleteLimit" readonly="readonly" value="${eoms:date2String(task.completeTimeLimit)}" id="${sheetPageName}nodeCompleteLimit"/>
		  <%} %>
		</table>     
