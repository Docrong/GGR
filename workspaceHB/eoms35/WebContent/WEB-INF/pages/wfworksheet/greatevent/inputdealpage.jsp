<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink"%>
<%
String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 System.out.println("=====operateType======"+operateType);
 String ifInvoke=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
 System.out.println("=====ifInvoke======"+ifInvoke);
 String roleId = "";
 String roleName="";
 %>

<script type="text/javascript">
function openwin(flag) {
	  var url;
	 
	  if(flag=="101160301") {
	    $('${sheetPageName}phaseId').value='callProcess';	   
	    url="${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
      } else if(flag=="101160302") {
	    $('${sheetPageName}phaseId').value='callProcess';
	    url="${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
      }else if(flag=="101160303") {
	    $('${sheetPageName}phaseId').value='callProcess';
	    url="${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
      }else if(flag=="101160304") {
	    $('${sheetPageName}phaseId').value='callProcess';
	    url="${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
      }
       window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');               
   }
function openwin1(flag) {
	  var url;
	  if(flag=="101160301") {  
	     url="${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
      } else if(flag=="101160302") {
	    url="${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
      }else if(flag=="101160303") {
	    url="${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
      }else if(flag=="101160304") {
	    url="${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iGreatEventMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
      }
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
<%@ include file="/WEB-INF/pages/wfworksheet/greatevent/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
		<%if(!operateType.equals("61")) {%>
		<caption><bean:message bundle="greatevent" key="greatevent.header"/></caption>
		<%} %>
     <input type="hidden" name="${sheetPageName}beanId" value="iGreatEventMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.greatevent.model.GreatEventMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.greatevent.model.GreatEventLink"/>   
    
    <c:if test="${taskName != 'HoldTask'}">
       <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
        </c:if>
          <%if(taskName.equals("MakeTask")){ 
	          if(operateType.equals("90")|| operateType.equals("11")){%>
	             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />	             
	             <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
			      <tr>
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
	</tr>
			    <tr>
		            <td class="label"><bean:message bundle="greatevent" key="greatevent.linkResReadyResult"/>*</td>
		            <td colspan="3"> 
		                 <textarea class="textarea max" name="${sheetPageName}linkResReadyResult" id="${sheetPageName}linkResReadyResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入资源准备结果，最多输入125字'">${sheetLink.linkResReadyResult}</textarea
		        </tr>
	            <tr>
  				    <td class="label"><bean:message bundle="greatevent" key="greatevent.linkGreatSecurityProgram"/>*</td>
		   		    <td colspan='3'>
		   				 <eoms:attachment name="sheetLink" property="linkGreatSecurityProgram" 
            		                      scope="request" idField="${sheetPageName}linkGreatSecurityProgram" appCode="greatevent" alt="allowBlank:false"/> 
		            </td>
		        </tr>
		            <%}} %>
		    <%if(taskName.equals("AuditTask")){ 
	             if(operateType.equals("91")|| operateType.equals("55")){%>
	             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApprovalTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
	                <tr>
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
	</tr>
	                <tr>
		                 <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>*</td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkAuditAdvice" id="${sheetPageName}linkAuditAdvice" 
            	      initDicId="1011601"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass(this.value)" defaultValue="${sheetLink.linkAuditAdvice}" />
			        </td>                
		            </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" defaultValue="${sheetLink.linkAuditResult}" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
                    </td>
		          </tr>
		           <%}} %>
		     <%if(taskName.equals("ApprovalTask")){ 
	             if(operateType.equals("92")|| operateType.equals("55")){%>
	              <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PerformTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
	               <tr>
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
	</tr>
	            <tr>
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkPermisAdvice"/>*</td>
		            <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkPermisAdvice" id="${sheetPageName}linkPermisAdvice" 
            	                   initDicId="1011604"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass1(this.value)" defaultValue="${sheetLink.linkPermisAdvice}"/>
			        </td>	                
		         </tr>
			     <tr>
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkPermisResult"/>*</td>
		            <td colspan="3"> 	
    				       <textarea class="textarea max" name="${sheetPageName}linkPermisResult" id="${sheetPageName}linkPermisResult" defaultValue="${sheetLink.linkPermisResult}" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审批结果，最多输入125字'">${sheetLink.linkPermisResult}</textarea>
                    </td>
		          </tr>
		           <%}} %>
		     <%if(taskName.equals("PerformTask")){ 
	             if(operateType.equals("93")|| operateType.equals("11")){%>
	            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditEndTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
	              <tr>
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
	</tr>
			      <tr>
		              <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkSencePerformSummary"/>*</td>
		              <td colspan="3"> 	
    				       <textarea class="textarea max" name="${sheetPageName}linkSencePerformSummary" id="${sheetPageName}linkSencePerformSummary" defaultValue="${sheetLink.linkSencePerformSummary}" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入现场实施总结，最多输入125字'">${sheetLink.linkSencePerformSummary}</textarea>
                      </td>
		          </tr>
	              <tr>
  					  <td class="label"><bean:message bundle="greatevent" key="greatevent.linkSencePerformReport"/>*</td>
		   			  <td colspan='3'>
		   				     <eoms:attachment name="sheetLink" property="linkSencePerformReport" 
            		              scope="request" idField="${sheetPageName}linkSencePerformReport" appCode="greatevent" alt="allowBlank:false"/> 
		                
		              </td>
		          </tr>
		             <%if(operateType.equals("93")){ %>
		          <tr >
		           <!-- 是否启动网络变更配置工单 -->
		              <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkIfStartChangeProcess"/>*</td>
		              <td colspan="3"> 	
    				       <eoms:comboBox name="${sheetPageName}linkIfStartChangeProcess" id="${sheetPageName}linkIfStartChangeProcess" 
            	                  initDicId="1011602"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetLink.linkIfStartChangeProcess}" onchange="popOtherFlow(this.value);"/>
                          <html:button styleClass="btn" style="display:none" property="method.save" styleId="startCircuit" onclick="openwin('101160301')">电路调度工单</html:button>
                          <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSoftChange" onclick="openwin('101160302')">软件变更工单</html:button>
                          <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetChange" onclick="openwin('101160304')">网络综合调整工单</html:button>
                          <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetData" onclick="openwin('101160303')">网络数据管理工单</html:button>
                      </td>
		          </tr>
		             <%}}} %>
		       <%if(taskName.equals("AuditEndTask")){ 
	                if(operateType.equals("94")|| operateType.equals("55")){%>
	               <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AssessmentTask" />
	               <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
	              <tr>
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
	</tr>
	               <tr>
		               <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>*</td>
		               <td colspan="3">  
				          <eoms:comboBox name="${sheetPageName}linkAuditAdvice" id="${sheetPageName}linkAuditAdvice" 
            	               initDicId="1011601"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass2(this.value);" defaultValue="${sheetLink.linkAuditAdvice}" />
			           </td>                
		           </tr>
			       <tr>
		               <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>*</td>
		               <td colspan="3"> 	
    				        <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
                       </td>
		           </tr>
		           <%}} %>
		      <%if(taskName.equals("AssessmentTask")){ 
	               if(operateType.equals("95")|| operateType.equals("11")){%>
	               <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditReportTask" />
	               <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
	               <tr>
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
	</tr>
	             <tr>
  						<td class="label"><bean:message bundle="greatevent" key="greatevent.linkSenceSecuritySummary"/>*</td>
		   				 <td colspan='3'>
		   				 <eoms:attachment name="sheetLink" property="linkSenceSecuritySummary" 
            		          scope="request" idField="${sheetPageName}linkSenceSecuritySummary" appCode="greatevent" alt="allowBlank:false"/> 
		                </td>
		          </tr>
	              <tr>
  						<td class="label"><bean:message bundle="greatevent" key="greatevent.linkSenceSecurityReport"/>*</td>
		   				 <td colspan='3'>
		   				<eoms:attachment name="sheetLink" property="linkSenceSecurityReport" 
            		          scope="request" idField="${sheetPageName}linkSenceSecurityReport" appCode="greatevent" alt="allowBlank:false"/> 
		                </td>
		          </tr>
	              <tr>
  						<td class="label"><bean:message bundle="greatevent" key="greatevent.linkAssessReport"/>*</td>
		   				 <td colspan='3'>
		   				<eoms:attachment name="sheetLink" property="linkAssessReport" 
            		          scope="request" idField="${sheetPageName}linkAssessReport" appCode="greatevent" alt="allowBlank:false"/> 
		                </td>
		          </tr>
		             <%if(operateType.equals("95")){ %>
		          <tr >
		           <!-- 是否启动网络变更配置工单 -->
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkIfStartChangeProcess"/></td>
		              <td colspan="3"> 	
    				  <eoms:comboBox name="${sheetPageName}linkIfStartChangeProcess" id="${sheetPageName}linkIfStartChangeProcess" 
            	      initDicId="1011602"  alt="allowBlank:true" styleClass="select-class" defaultValue="${sheetLink.linkIfStartChangeProcess}" onchange="popOtherFlow1(this.value);"/>
                         <html:button styleClass="btn" style="display:none" property="method.save" styleId="startCircuit" onclick="openwin1('101160301')">电路调度工单</html:button>
                         <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSoftChange" onclick="openwin1('101160302')">软件变更工单</html:button>
                        <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetChange" onclick="openwin1('101160304')">网络综合调整工单</html:button>
                          <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetData" onclick="openwin1('101160303')">网络数据管理工单</html:button>
                      </td>
		         </tr>
		             <%}}} %>
		     <%if(taskName.equals("AuditReportTask")){ 
	              if(operateType.equals("96")|| operateType.equals("55")){%> 
	              <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ModifyTask" />
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
	              <tr>
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
	</tr>
	               <tr>
		               <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditAdvice"/>*</td>
		               <td colspan="3">  
				           <eoms:comboBox name="${sheetPageName}linkAuditAdvice" id="${sheetPageName}linkAuditAdvice" 
            	             initDicId="1011601"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass3(this.value);" defaultValue="${sheetLink.linkAuditAdvice}" />
			           </td>                
		           </tr>
			       <tr>
		               <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAuditResult"/>*</td>
		               <td colspan="3"> 	
    				        <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审核结果，最多输入125字'">${sheetLink.linkAuditResult}</textarea>
                       </td>
		           </tr>
		           <tbody id="ifchangeplan">
		           <tr>
		                <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkIfModifyPlans"/>*</td>
		                <td colspan="3">  
				        <eoms:comboBox name="${sheetPageName}linkIfModifyPlans" id="${sheetPageName}linkIfModifyPlans" 
            	            initDicId="1011605"  alt="allowBlank:false" styleClass="select-class" onchange="ifAuditPass4(this.value);" defaultValue="${sheetLink.linkIfModifyPlans}" />
			            </td>                
		            </tr>
		           </tbody>
		           <%}} %>
		          <%if(taskName.equals("ModifyTask")){ 
	             if(operateType.equals("97")|| operateType.equals("11")){%> 
	             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
	            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />        
			      <tr>
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
	</tr>
			      <tr>
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.linkAmendPlanHelp"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkAmendPlanHelp" id="${sheetPageName}linkAmendPlanHelp" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入修订预案说明，最多输入125字'">${sheetLink.linkAmendPlanHelp}</textarea>
                    </td>
		          </tr>
	                <tr>
  						<td class="label"><bean:message bundle="greatevent" key="greatevent.linkEmergencyPlan"/>*</td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment name="sheetLink" property="linkEmergencyPlan" 
            		          scope="request" idField="${sheetPageName}linkEmergencyPlan" appCode="greatevent" alt="allowBlank:false" /> 
		                </td>
		            </tr>
		            
		           <%}}%>
		            
		 <%if(taskName.equals("HoldTask")) {%>  	
			<%if(operateType.equals("18")){ %>
     	
	     	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="over" />
	     	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	     	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
	     	<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>  	
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
		      <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请最多输入125字'" id="${sheetPageName}endResult"  class="textarea max">${sheetMain.endResult}</textarea>
		    </td>
		  </tr>	
		  <%}else if(operateType.equals("102")){%>
     	    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
	        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />	
	    	<tr>
		       <td class="label">
		        <bean:message bundle="sheet" key="linkForm.remark" />*
			    </td>
				<td colspan="3">			
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
			        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
			  </td>
			</tr> 	
		  <%}}%>
     	   <!--流程中的字段域 结束-->
          <!-- 公共功能，抄送和确认受理 -->
     	    <!-- 驳回到上一级 -->
     	     <%if(operateType.equals("4")){ %>
 		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		<c:if test="${taskName=='PerformTask' }">
		<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ApprovalTask" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${taskName!='PerformTask' }">
		<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject" />
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>	
		</c:if>				
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="link.linkRejectCause" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>
      
      <!-- 确认受理 -->     
         <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value="${fOperateroleidType}" />
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />	
		
  		<%}%>        
  </table>
  <!-- 公共功能，抄送和确认受理 结束 -->
    <!-- 各个环节中的选择角色 -->
    <br/> 				
	 <!-- 方案制定  -->
	  <%if(taskName.equals("MakeTask")&& operateType.equals("90")) {%>	
		<fieldset id="link10">
		<legend>
			<bean:message bundle="greatevent" key="greatevent.linkPermisObject"/>:
			<bean:message bundle="greatevent" key="role.emergencymanager"/>
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="342" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
	 <%} %> 
	 <!-- 方案审核  -->
  <%if(taskName.equals("AuditTask")&& operateType.equals("91")) {%>	
		<fieldset id="link10">
		<legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>	
				 		 
		 </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="343" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
		<%} %>
		<!-- 方案审批  -->
		<%if(taskName.equals("ApprovalTask")&& operateType.equals("92")) {%>	
		<fieldset id="link10">
		<legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>	
				 		 
		 </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="344" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
		<%} %>
     <!-- 方案实施 -->
  <%if(taskName.equals("PerformTask")&& operateType.equals("93")) {%>	
	    <fieldset id="link10">
		<legend>
			<bean:message bundle="greatevent" key="greatevent.linkPermisObject"/>:
			<bean:message bundle="greatevent" key="role.emergencymanager"/> 
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="342" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
  <%} %> 
  <!-- 审批保障结束申请 -->
<%if(taskName.equals("AuditEndTask")&& operateType.equals("94")) {%>	
	    <fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<span id="roleName"></span>	
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="344" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
  <%} %>
  <!-- 完成保障报告，提交审核 -->
  <%if(taskName.equals("AssessmentTask")&& operateType.equals("95")) {%>	
	    <fieldset id="link10">
		<legend>
			<bean:message bundle="greatevent" key="greatevent.linkPermisObject"/>:
			<bean:message bundle="greatevent" key="role.emergencymanager"/> 
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="342" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'}]"/>	
		</fieldset>
		<fieldset id="link10">
   	      <legend>
             审阅对象：<bean:message bundle="greatevent" key="role.Responsibler"/>
           </legend>
	  		<eoms:chooser id="test2" type="role" roleId="343" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'subrole',allowBlank:false,text:'抄送',vtext:'请选择抄送对象'}]"
			   />			   
     </fieldset>
     		
  <%} %>
  <!-- 审核保障报告 -->
  <%if(taskName.equals("AuditReportTask")&& operateType.equals("96")) {%>	
	    <fieldset id="link10">
		<legend>
				<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
				<span id="roleName"></span>	
				 		 
		 </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="344" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		    <eoms:chooser id="sendRole1" type="role" roleId="342" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
  <%} %>
  <!-- 完成预案修订，提交预案 -->
  <%if(taskName.equals("ModifyTask")&& operateType.equals("97")) {%>	
	    <fieldset id="link10">
		<legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="greatevent" key="role.emergencymanager"/> 
	    </legend>
	 		<eoms:chooser id="sendRole" type="role" roleId="342" flowId="062" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>	
		</fieldset>		
  <%} %>
  
<script type="text/javascript">
function ifAuditPass(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160101"){
		chooser_sendRole.enable();
		//审核通过到需求分析
		$('${sheetPageName}phaseId').value='ApprovalTask';
		$('${sheetPageName}operateType').value='911';
		$('roleName').innerHTML="应急通信负责人";
	} else if(input=="101160102"){
		//审核不通过到驳回  
		chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='MakeTask';
		$('${sheetPageName}operateType').value='912';
		$('roleName').innerHTML="应急通信保障工作组";
	} else{
	chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
 	var frm = document.forms[0];
    var temp = frm.linkAuditAdvice ? frm.linkAuditAdvice.value : '';
    if("${taskName}"=="AuditTask"&&temp!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    ifAuditPass(temp);
    }
function ifAuditPass1(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160402"){
		chooser_sendRole.disable();
		//审批不通过到方案制定
		$('${sheetPageName}phaseId').value='MakeTask';
		$('${sheetPageName}operateType').value='922';
		$('roleName').innerHTML="应急通信保障工作组";
	} else if(input=="101160401"){
		//审核通过到方案实施  
		chooser_sendRole.enable();
		$('${sheetPageName}phaseId').value='PerformTask';
		$('${sheetPageName}operateType').value='921';
		$('roleName').innerHTML="应急通信保障工作组";
	}  else{
	chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
		}
	}
	}
	var frm1 = document.forms[0];
    var temp1 = frm1.linkPermisAdvice ? frm1.linkPermisAdvice.value : '';
    if("${taskName}"=="ApprovalTask"&&temp1!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    ifAuditPass1(temp1);
    }
function ifAuditPass2(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160101"){
		chooser_sendRole.enable();
		//审批通过到完成保障报告
		$('${sheetPageName}phaseId').value='AssessmentTask';
		$('${sheetPageName}operateType').value='941';
		$('roleName').innerHTML="应急通信保障工作组";
	} else if(input=="101160102"){
		//审核不通过到方案实施  
		chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='PerformTask';
		$('${sheetPageName}operateType').value='942';
		$('roleName').innerHTML="应急通信保障工作组";
	}else{
	    chooser_sendRole.disable();		    
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML=""; 
	}
	}
	}
	var frm2 = document.forms[0];
    var temp2 = frm2.linkAuditAdvice ? frm2.linkAuditAdvice.value : '';
    if("${taskName}"=="AuditEndTask"&&temp2!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    ifAuditPass2(temp2);
    }
	function ifAuditPass3(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160101"){
		//审核通过
	     eoms.form.enableArea('ifchangeplan');		
	     chooser_sendRole.disable();
	     chooser_sendRole1.disable();	     
	} else if(input=="101160102"){
		//审核不通过
		 eoms.form.disableArea('ifchangeplan',true);
		 document.all.${sheetPageName}linkIfModifyPlans.value='';			  	
	     chooser_sendRole.disable();
	     chooser_sendRole1.disable();	
		$('${sheetPageName}phaseId').value='AssessmentTask';
		$('${sheetPageName}operateType').value='963';
		$('roleName').innerHTML="应急通信保障工作组";
	}else{
	chooser_sendRole.disable();
		 eoms.form.disableArea('ifchangeplan',true);
		 document.all.${sheetPageName}linkIfModifyPlans.value='';		    
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
	var frm3 = document.forms[0];
    var temp3 = frm3.linkAuditAdvice ? frm3.linkAuditAdvice.value : '';
    if("${taskName}"=="AuditReportTask"&&temp3!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    ifAuditPass3(temp3);
    }
	function ifAuditPass4(input){  
	if('<%=operateType%>'!='55'){
	if(input=="101160502"){
	//无需修订	
	     chooser_sendRole1.enable();
	     chooser_sendRole.disable();
		$('${sheetPageName}phaseId').value='HoldTask';
		$('${sheetPageName}operateType').value='961';
		$('roleName').innerHTML="应急通信管理员";	     
	     	     
	} else if(input=="101160501"){
		 //需修订 	
	     chooser_sendRole.enable();
	     chooser_sendRole1.disable();
		$('${sheetPageName}phaseId').value='ModifyTask';
		$('${sheetPageName}operateType').value='962';
		$('roleName').innerHTML="应急通信保障工作组";
	}else{
	chooser_sendRole.disable();
	     chooser_sendRole1.disable();
	     chooser_sendRole.disable();	    
		$('${sheetPageName}phaseId').value='';
		$('${sheetPageName}operateType').value='';
		$('roleName').innerHTML="";
	}
	}
	}
	var frm4 = document.forms[0];
    var temp4 = frm4.linkIfModifyPlans ? frm4.linkIfModifyPlans.value : '';
    if("${taskName}"=="AuditReportTask"&&temp4!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    ifAuditPass4(temp4);
    }	
  function popOtherFlow(value){
		if(value=='101160201'){
		    $('${sheetPageName}operateType').value='111';
		    $('${sheetPageName}phaseId').value='callProcess'
			document.getElementById('startCircuit').style.display='';
			document.getElementById('startSoftChange').style.display='';
			document.getElementById('startNetData').style.display='';
			document.getElementById('startNetChange').style.display='';
		}else if(value=='101160202'){
		    if("${ifInvoke}"=="no"){
		    alert("任务正在等待回调中!请选择'是'选项");
		    $('${sheetPageName}phaseId').value='callProcess'
		    $('${sheetPageName}operateType').value='111';
		    document.all.${sheetPageName}linkIfStartChangeProcess.value="101160201"
		    }else{
		    $('${sheetPageName}operateType').value='93';
		    $('${sheetPageName}phaseId').value='AuditEndTask'
		    document.getElementById('startCircuit').style.display='none';
			document.getElementById('startSoftChange').style.display='none';
			document.getElementById('startNetData').style.display='none';
			document.getElementById('startNetChange').style.display='none';
		}	
	}
    }
    if("${taskName}"=="PerformTask"&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4'){
 	var frm = document.forms[0];
    if("${ifInvoke}"=="no"){
        document.forms[0].linkIfStartChangeProcess.value="101160201";
    } 	
        var temp = frm.linkIfStartChangeProcess ? frm.linkIfStartChangeProcess.value : '';
        popOtherFlow(temp);
    }	
	function popOtherFlow1(value){
		if(value=='101160201'){
			document.getElementById('startCircuit').style.display='';
			document.getElementById('startSoftChange').style.display='';
			document.getElementById('startNetData').style.display='';
			document.getElementById('startNetChange').style.display='';
		}else if(value=='101160202'){
		    $('${sheetPageName}operateType').value='95';
		    $('${sheetPageName}phaseId').value='AuditReportTask'
		    document.getElementById('startCircuit').style.display='none';
			document.getElementById('startSoftChange').style.display='none';
			document.getElementById('startNetData').style.display='none';
			document.getElementById('startNetChange').style.display='none';
		}	
	}

    var frm5 = document.forms[0];
    var temp5 = frm5.linkIfStartChangeProcess ? frm5.linkIfStartChangeProcess.value : '';
    if("${taskName}"=="AssessmentTask"&&temp4!=''&&'<%=operateType%>'!='61'&&'<%=operateType%>'!='4')
    {
    ifAuditPass5(temp5);
    }		
 </script>
  

