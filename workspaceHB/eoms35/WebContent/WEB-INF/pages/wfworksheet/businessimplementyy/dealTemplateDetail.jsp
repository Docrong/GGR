<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink  businessimplementyyLink = (com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink)request.getAttribute("sheetLink");
 java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessimplementyyLink.getActiveTemplateId());
 java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessimplementyyLink.getOperateType()));
 String ifInvoke=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
 %>
 <script type="text/javascript">
var frm = document.forms[0];
function saveDealTemplate(type) { 
   	var form = document.forms[0];
   	if (form.templateName.value == "") {
   		
   		return false;
   	}
   	form.action = "${app}/sheet/businessimplementyy/businessimplementyy.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
   	form.submit();
 }
function removeTemplate() {
	if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
		var thisform = document.forms[0];
		thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
		thisform.submit();
	}
}
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementyy/baseinputlinkhtmlnew.jsp"%>
 <html:form action="/businessimplementyy.do" styleId="theform">       
	<br/>
	
	<table class="formTable">
	
     	<caption><bean:message bundle="businessimplementyy" key="businessimplementyy.header"/></caption>
		<tr>
           <td class="label" >
           		<bean:message bundle="sheet" key="input.templateName" />
           </td>
           <td colspan="3">
           		<input type="text" name="templateName" class="text max" id="templateName" value="${sheetLink.templateName}"/>
           </td>         
       </tr>	
	
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'å¤çæ¶éä¸è½æäºå·¥åå®ææ¶é'"/>
         <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementYYMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink"/>
         <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
	  <c:if test="${taskName != 'HoldTask' }">
		  <%if(operateType.equals("204")){ %>
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${task.operateRoleId}"/>
		  <%}else {%>
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
		  <%} %>
      </c:if>
      
	<c:choose>
	<c:when test="${task.subTaskFlag == 'true'}">
		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	</c:when>
	</c:choose>      
      
      
		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		  <c:choose> 
		  	    <c:when test="${taskName=='AcceptTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${taskName=='ExecuteTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask" />
				</c:when>
				<c:when test="${taskName=='AcceptReply'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask" />
				</c:when>				
			</c:choose>				
    	<tr>
	       <td class="label">
	        		å¤æ³¨è¯´æ*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,vtext:'è¯·æå¤è¾å¥1000å­'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>      

		 	<%if(taskName.equals("AuditTask")){%>
   		     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		     <%if(operateType.equals("71")||operateType.equals("55")){ %> 
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CodeDispthTask" />
	              <!-- 
	               <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>  -->
			<tr>
			    <td class="label">是否签收*</td>
		        <td colspan="3"> 
		             <eoms:comboBox   name="${sheetPageName}linkIsAppled" id="${sheetPageName}linkIsAppled"  initDicId="1013301" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsAppled}" onchange="onchangeAppled(this.value);"/>	     	
		        </td>
		    </tr>  
		    <tbody id="NoApplied" style="display:none">		   
			<tr>
			  	<td class="label">不签收意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkNoAppledOpinition" id="${sheetPageName}linkNoAppledOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'核查结果描述，最多输入100汉字'">${sheetLink.linkNoAppledOpinition}</textarea>
			    </td> 
		    </tr>
		    </tbody>
			<tr>
			    <td class="label">集团产品编码*</td>
		           <td class="content"> 
		             <input type="text"  class="text" name="${sheetPageName}linkGoupeProductCode" id="${sheetPageName}linkGoupeProductCode"  alt="allowBlank:true" value="${sheetLink.linkGoupeProductCode}"/>
		          </td>
		      <td class="label">处理方式*</td>
		        <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkDealMethod" id="${sheetPageName}linkDealMethod"  initDicId="1013302" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDealMethod}"/>	     	
		        </td>
		    </tr>  
			<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkDealOpinition" id="${sheetPageName}linkDealOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'处理意见描述，最多输入100汉字'">${sheetLink.linkDealOpinition}</textarea>
			    </td> 
		    </tr>
		    <%} %>
   <%}else if(taskName.equals("CodeDispthTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		    <%if(operateType.equals("72")||operateType.equals("11")){ %>
   		    <!--  
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="961" />
   		     -->
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="GetWayTask" />
   		      	   <!-- 
   		      	   <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				     	 -->	    	
				  
				<tr>
				    <td class="label">信令方式*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkInfoMethod" id="${sheetPageName}linkInfoMethod"  initDicId="1013303" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkInfoMethod}" onchange="onchangelinkInfoMethod(this.value);"/>	     	
			        </td>
			   </tr>
			   <tbody id="InfoMethod" style="display:none">	 	
			   <tr>
				    <td class="label">信令协议</td>
			        <td class="content"> 
			             <eoms:comboBox   name="${sheetPageName}linkInfoProtocol" id="${sheetPageName}linkInfoProtocol"  initDicId="1013304" styleClass="select-class" alt="allowBlank:true" defaultValue="${sheetLink.linkInfoProtocol}" />	     	
			        </td>
			       <td class="label">集团产品编码*</td>
		           <td class="content"> 
		             <input type="text"  class="text" name="${sheetPageName}linkGoupeProductCode" id="${sheetPageName}linkGoupeProductCode"  alt="allowBlank:true" value="${sheetLink.linkGoupeProductCode}"/>
		           </td>
		       </tr>
		       </tbody>
		       <!-- 
		       <tr>
				    <td class="label">退回原因分类*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkBackResonType" id="${sheetPageName}linkBackResonType"  initDicId="1013305" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkBackResonType}" />	     	
			        </td>
			   </tr> -->
			<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkDealOpinition" id="${sheetPageName}linkDealOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'核查结果描述，最多输入100汉字'">${sheetLink.linkDealOpinition}</textarea>
			    </td> 
		     </tr>
			  <%} %>
   <%}else if(taskName.equals("GetWayTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		    <%if(operateType.equals("73")||operateType.equals("11")){ %>
   		    <!--  
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="961" />
   		     -->
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OpenTask" />
   		      	   <!-- 
   		      	   <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				     	 -->	    	
				  
				<tr>
				    <td class="label">传输电路编号</td>
			        <td class="content"> 
			        <input type="text"  class="text" name="${sheetPageName}linkCircuitCode" id="${sheetPageName}linkCircuitCode"  alt="allowBlank:true" value="${sheetLink.linkCircuitCode}"/>
			        </td>
			        <td class="label">处理方式*</td>
		            <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkDealMethod" id="${sheetPageName}linkDealMethod"  initDicId="1013302" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDealMethod}"/>	     	
		        </td>
			   </tr>
				<tr>
				    <td class="label">业务接入网元</td>
			        <td colspan='3'> 
			        <input type="text"  class="text" name="${sheetPageName}linkBusiInputNet" id="${sheetPageName}linkBusiInputNet"  alt="allowBlank:true" value="${sheetLink.linkBusiInputNet}"/>
			        </td>

			   </tr>
				<tr>
				    <td class="label">中继端口数量*</td>
			        <td class="content"> 
			        <input type="text"  class="text" name="${sheetPageName}linkPointNumber" id="${sheetPageName}linkPointNumber"  alt="allowBlank:false" value="${sheetLink.linkPointNumber}"/>
			        </td>
			        <td class="label">信令端口数量*</td>
		            <td class="content">
					<input type="text"  class="text" name="${sheetPageName}linkInfoPointNumber" id="${sheetPageName}linkInfoPointNumber"  alt="allowBlank:false" value="${sheetLink.linkInfoPointNumber}"/>		
			   		</td>
			   </tr>
			   <!-- 
		       <tr>
				    <td class="label">退回原因分类*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkBackResonType" id="${sheetPageName}linkBackResonType"  initDicId="1013305" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkBackResonType}" />	     	
			        </td>
			   </tr> -->
			<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkDealOpinition" id="${sheetPageName}linkDealOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'核查结果描述，最多输入100汉字'">${sheetLink.linkDealOpinition}</textarea>
			    </td> 
		     </tr>
			  <%} %>
   		    <%if(operateType.equals("960")||operateType.equals("11")){ %>
   		      <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="960" />
   		      <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
   		    	
   		    <%} %>
   	<%}else if(taskName.equals("OpenTask")){%>
   			<!--  
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		  -->  <%if(operateType.equals("74")||operateType.equals("11")){ %>
   		    

   		      	   <!-- 
   		      	   <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				     	 -->
				     	 <%if(ifInvoke.equals("")||ifInvoke.equals("no")){
				     	 	System.out.println("@@ifInvoke=no");
				     	 %>
			   		      		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="waitReturn" />     		    	
			              		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111" />
			              	 
			              	 <%}else if(ifInvoke.equals("yes")){ 
			              	 System.out.println("@@ifInvoke=yes");
			              	 %>
								<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DataMakeTask" />  
								<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="74" />
   	
			 				<%} %> 	    	
			     <tr>
					<td class="label">是否调用业务开通工单</td>
					<td class="content" colspan='3'>
				    <!-- add by liufuyuan -->
				    <eoms:comboBox name="${sheetPageName}linkIsAppled" id="${sheetPageName}linkIsAppled" initDicId="1013301" defaultValue="${sheetLink.linkIsAppled}" styleClass="select" alt="allowBlank:false" 
						  	onchange="popOtherFlow('circuitdispatch',this.value);"/>
				    <html:button styleClass="btn" property="method.save" styleId="startCircuit" onclick="openwin('startCircuit')">
				         		业务开通传输工单
				     </html:button>
				    </td>
				  </tr>				  
				<tr>
				    <td class="label">电路编号</td>
			        <td class="content"> 
			        <input type="text"  class="text" name="${sheetPageName}linkCircuitCode" id="${sheetPageName}linkCircuitCode"  alt="allowBlank:true" value="${sheetLink.linkCircuitCode}"/>
			        </td>
			        <td class="label">电路调度结果*</td>
		            <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkIsDataOk" id="${sheetPageName}linkIsDataOk"  initDicId="1013306" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsDataOk}" onchange="onchangelinkIsDataOk(this.value);"/>	     	
		        </td>
			   </tr>
			   <tbody id="IsDataOk" style="display:none">	 
				<tr>
				    <td class="label">不成功原因</td>
			        <td colspan='3'> 
			           <textarea class="textarea max"  name="${sheetPageName}linkNoAppledOpinition" id="${sheetPageName}linkNoAppledOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'不成功原因描述，最多输入100汉字'">${sheetLink.linkNoAppledOpinition}</textarea>
			        </td>

			   </tr>
			   </tbody>
			   <!-- 
		       <tr>
				    <td class="label">退回原因分类*</td>
			        <td colspan="3""> 
			             <eoms:comboBox   name="${sheetPageName}linkBackResonType" id="${sheetPageName}linkBackResonType"  initDicId="1013305" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkBackResonType}" />	     	
			        </td>
			   </tr> -->
			<tr>
			  	<td class="label">回复意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkDealOpinition" id="${sheetPageName}linkDealOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'回复意见描述，最多输入100汉字'">${sheetLink.linkDealOpinition}</textarea>
			    </td> 
		     </tr>
			  <%} %>
		<%}else if(taskName.equals("DataMakeTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		    <%if(operateType.equals("75")||operateType.equals("11")){ %>
   		    <!--  
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="961" />
   		     -->
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusiTestTask" />
   		      	   <!-- 
   		      	   <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				     	 -->	    	
				  
				<tr>
			        <td class="label">数据是否完成*</td>
		            <td colspan='3'>
		             <eoms:comboBox   name="${sheetPageName}linkIsDataOk" id="${sheetPageName}linkIsDataOk"  initDicId="1013301" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsDataOk}" />	     	
		        </td>
			   </tr>
			 
				<tr>
				    <td class="label">数据制作结果</td>
			        <td colspan='3'> 
			           <textarea class="textarea max"  name="${sheetPageName}linkDateMakeResult" id="${sheetPageName}linkDateMakeResult" 
			      		alt="allowBlank:true,maxLength:200,vtext:'数据制作结果描述，最多输入100汉字'">${sheetLink.linkDateMakeResult}</textarea>
			        </td>

			   </tr>

			  <%} %>
	<%}else if(taskName.equals("BusiTestTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		    <%if(operateType.equals("76")||operateType.equals("11")){ %>
   		    <!--  
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="961" />
   		     -->
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DataOkTask" />
   		      	   <!-- 
   		      	   <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				     	 -->	    	
				  
			<tr>
			    <td class="label">是否签收*</td>
		        <td class="content"> 
		             <eoms:comboBox   name="${sheetPageName}linkIsAppled" id="${sheetPageName}linkIsAppled"  initDicId="1013301" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsAppled}" onchange="onchangeAppled(this.value);"/>	     	
		        </td>
		        <td class="label">处理方式*</td>
		        <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkDealMethod" id="${sheetPageName}linkDealMethod"  initDicId="1013307" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDealMethod}"/>	     	
		        </td>
		    </tr>  
		    <tbody id="NoApplied" style="display:none">		   
			<tr>
			  	<td class="label">不签收意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkNoAppledOpinition" id="${sheetPageName}linkNoAppledOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'核查结果描述，最多输入100汉字'">${sheetLink.linkNoAppledOpinition}</textarea>
			    </td> 
		    </tr>
		    </tbody>
		    <tbody id="DealOpinition" style="display:none">	
		  	<tr>
			  	<td class="label">处理意见</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkDealOpinition" id="${sheetPageName}linkDealOpinition" 
			      		alt="allowBlank:true,maxLength:200,vtext:'处理意见描述，最多输入100汉字'">${sheetLink.linkDealOpinition}</textarea>
			    </td> 
		    </tr> 		
			</tbody>
		<%} %>
	<%}else if(taskName.equals("DataOkTask")){%>
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" /> 	 
   		    <%if(operateType.equals("77")||operateType.equals("11")){ %>
   		    <!--  
   		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="961" />
   		     -->
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask" />
   		      	   <!-- 
   		      	   <tr>
				   <td class="label">
				     <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*           
		           </td>
		           <td class="content">
		              <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		                 name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit" 
		                 readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}" alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
		          
		           </td>
				   <td class="label">		     
		             <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}nodeCompleteLimit" readonly="readonly" 
		                  value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit" alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
		           </td>
				  </tr>
				     	 -->	    	
				  
			<tr>
			    <td class="label">满意度*</td>
		        <td class="content"> 
		             <eoms:comboBox   name="${sheetPageName}linkIsOk" id="${sheetPageName}linkIsOk"  initDicId="1013308" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsOk}" />	     	
		        </td>
			    <td class="label">是否满足需求*</td>
		        <td class="content"> 
		             <eoms:comboBox   name="${sheetPageName}linkIsOkNeed" id="${sheetPageName}linkIsOkNeed"  initDicId="1013301" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsOkNeed}" onchange="onchangelinkIsOkNeed(this.value);"/>	     	
		        </td>
		    </tr>  
		    <tbody id="IsOkNeed" style="display:none">		   
			<tr>
			  	<td class="label">不满足原因</td>
			    <td colspan="3">
			      <textarea class="textarea max"  name="${sheetPageName}linkNoOkResson" id="${sheetPageName}linkNoOkResson" 
			      		alt="allowBlank:true,maxLength:200,vtext:'核查结果描述，最多输入100汉字'">${sheetLink.linkNoOkResson}</textarea>
			    </td> 
		    </tr>
		    </tbody>
 		

		<%} %>

          <%}else if( taskName.equals("HoldTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask" />
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
			      alt="allowBlank:false,maxLength:2000,vtext:'è¯·è¾å¥å½æ¡£åå®¹ï¼æå¤è¾å¥1000æ±å­'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			    			 
              <%}%>
              <%if(operateType.equals("17")){ %>
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17" />
	              <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask" />
              
			     	<tr>
				       <td class="label">
				        <bean:message bundle="sheet" key="linkForm.remark" />*
					    </td>
						<td colspan="3">			
					        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
					        alt="allowBlank:false,width:500,vtext:'è¯·æå¤è¾å¥1000å­'" alt="width:'500px'">${sheetLink.remark}</textarea>
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
              				scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessimplementyy" />		   
			     </td>
		</tr>
	 <%}%>
     <%if(taskName.equals("AcceptTask")||taskName.equals("ExecuteTask")||taskName.equals("AcceptReply") ) {%> 
      <%if(operateType.equals("61")){ %>
		<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61" />							
    	<!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'æå¤è¾å¥100æ±å­'">${sheetLink.remark}</textarea>
		  </td>
		</tr> --> 	
		
		<%} }%>
		

		
		<% if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'è¯·æå¤è¾å¥1000æ±å­'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 		
			     
  </table>
</html:form>
<logic:present name="type">
<c:if test="${dealTemplateId != null && dealTemplateId != ''}">
		<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="saveDealTemplate('current')">
          	<bean:message bundle="sheet" key="button.saveCurTemplate" />
    	</html:button>
</c:if>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
         <bean:message bundle="sheet" key="button.delete" />
</html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
         <bean:message bundle="sheet" key="button.back" />
</html:button>
