<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 System.out.println("@@taskName=Attribu="+taskName);
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
  String sheetMain = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("sheetMain")); 
  BusinessImplementYYMain basemain = (BusinessImplementYYMain)request.getAttribute("sheetMain");
String title = basemain.getTitle();
System.out.println("@@@title"+title);


  //
 String orderSheetId = basemain.getOrderSheetId();
  System.out.println("@@orderSheetId===id==="+orderSheetId);
 System.out.println("@@taskName===222==="+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 request.setAttribute("operateType",operateType);
  System.out.println("@@operateType======"+operateType);

 String ifInvoke=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
 System.out.println("@@ifInvoke="+ifInvoke);
 String operateUserId="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("BusinessImplementYY");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementyy/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
	<input type="hidden" id="tmpCompleteLimit" value="" alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
         <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementYYMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYLink"/>
         <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
	  <c:if test="${taskName != 'HoldTask' }">
		  	<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
      </c:if>
      
	<c:choose>
	<c:when test="${task.subTaskFlag == 'true'}">
		<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
	</c:when>
	</c:choose>      
      
      
		<%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
		  <c:choose> 
		  	    <c:when test="${taskName=='DataOkTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusiTestTask" />
				</c:when>
				<c:when test="${taskName=='BusiTestTask'}">
			
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DataMakeTask" />
				
				
				</c:when>
				<c:when test="${taskName=='DataMakeTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OpenTask" />
				</c:when>
				
				<c:when test="${taskName=='OpenTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="GetWayTask" />
				</c:when>
				<c:when test="${taskName=='GetWayTask'}">
				<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CodeDispthTask" />
				</c:when>	
				
				<c:when test="${taskName=='CodeDispthTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask" />
				</c:when>
				<c:when test="${taskName=='AuditTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>				
			</c:choose>				
    	<tr>
	       <td class="label">
	        		备注说明*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,vtext:'请最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
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
		   <!--		<tr>
			    <td class="label">集团产品编码*</td>
		           <td colspan="3"> 
		             <input type="text"  class="text" name="${sheetPageName}linkGoupeProductCode" id="${sheetPageName}linkGoupeProductCode"  alt="allowBlank:true" value="${sheetLink.linkGoupeProductCode}"/>
		          </td>
		       	      <td class="label">处理方式*</td>
		        <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkDealMethod" id="${sheetPageName}linkDealMethod"  initDicId="1013302" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDealMethod}"/>	     	
		        </td>  

		    </tr>  -->
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
				     		    	
				  
				<tr>
				    <td class="label">传输电路编号</td>
			        <td class="content"> 
			        <input type="text"  class="text" name="${sheetPageName}linkCircuitCode" id="${sheetPageName}linkCircuitCode"  alt="allowBlank:true" value="${sheetLink.linkCircuitCode}"/>
			        </td>
			        <td class="label">处理方式*</td>
		            <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkDealMethod" id="${sheetPageName}linkDealMethod"  initDicId="1013302" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDealMethod}"/>	     	
		        </td>
		         -->
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
					<td  colspan='3'>
				    <!-- add by liufuyuan -->
				    <eoms:comboBox name="${sheetPageName}linkIsAppled" id="${sheetPageName}linkIsAppled" initDicId="1013301" defaultValue="${sheetLink.linkIsAppled}" styleClass="select" alt="allowBlank:false" 
						  	onchange="popOtherFlow('circuitdispatch',this.value);"/>
				    <html:button styleClass="btn" property="method.save" styleId="startCircuit" onclick="openwin('startCircuit')">
				         		业务开通传输工单
				     </html:button>
				    </td>
				  </tr>		
				  <tbody id="BianHao" style="display:none">	   
				<tr>
				<!-- 
				    <td class="label">电路编号</td>
			        <td class="content"> 
			        <input type="text"  class="text" name="${sheetPageName}linkCircuitCode" id="${sheetPageName}linkCircuitCode"  alt="allowBlank:true" value="${sheetLink.linkCircuitCode}"/>
			        </td> -->
			        <td class="label">电路调度结果*</td>
		            <td colspan="3">
		             <eoms:comboBox   name="${sheetPageName}linkIsDataOk" id="${sheetPageName}linkIsDataOk"  initDicId="1013306" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsDataOk}" onchange="onchangelinkIsDataOk(this.value);"/>	     	
		        </td>
			   </tr>
			   </tbody>
			   
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
		        <td colspan="3"> 
		             <eoms:comboBox   name="${sheetPageName}linkIsAppled" id="${sheetPageName}linkIsAppled"  initDicId="1013301" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkIsAppled}" onchange="onchangeAppled(this.value);"/>	     	
		        </td>
		        <!-- 
		        <td class="label">处理方式*</td>
		        <td class="content">
		             <eoms:comboBox   name="${sheetPageName}linkDealMethod" id="${sheetPageName}linkDealMethod"  initDicId="1013307" styleClass="select-class" alt="allowBlank:false" defaultValue="${sheetLink.linkDealMethod}"/>	     	
		        </td>-->
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
			      alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
			    </td>
			  </tr>	        			    			 
              <%}%>
              <%if(operateType.equals("17")){ %>
	              <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17" />
	              <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask" />
              
			     	<tr>
				       <td class="label">
				        <bean:message bundle="sheet" key="linkForm.remark" />*
					    </td>
						<td colspan="3">			
					        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
					        alt="allowBlank:false,width:500,vtext:'请最多输入1000字'" alt="width:'500px'">${sheetLink.remark}</textarea>
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
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
     <%} %> 		
			     
  </table>
<%if(taskName.equals("AuditTask")){ %>
   <% if(operateType.equals("71")){ %>
  <fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName3">:送编续码人员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test3"  type="role" roleId="952" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
  </fieldset>
	<% } %>
<%} %> 

<%if(taskName.equals("CodeDispthTask")){ %>
<% if(operateType.equals("72") ){ %>
<fieldset  >
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:设备端口分配人员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="953" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
  </fieldset>
<% } %>
<%} %> 
<%if(taskName.equals("GetWayTask")){ %>
  <% if(operateType.equals("73")){ %>
  <fieldset>
		<legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:专线开通人员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="954" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
</fieldset>
<% } %>

<%} %> 
<%if(taskName.equals("OpenTask")){ %>
     <% if(operateType.equals("74")){ %>
  <fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:数据制作人员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="955" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
  </fieldset>
	<% } %>

<%} %> 
<%if(taskName.equals("DataMakeTask")){ %>
     <% if(operateType.equals("75")){ %>
  <fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:业务测试人员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="956" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
  </fieldset>
	<% } %>

<%} %> 

<%if(taskName.equals("BusiTestTask")){ %>
     <% if(operateType.equals("76")){ %>
  <fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:开通确认人员
			 </span>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="957" flowId="270" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />			   
			 </div>
  </fieldset>
	<% } %>

<%} %> 
<%if(taskName.equals("DataOkTask")){ %>
     <% if(operateType.equals("77")){ %>
  <fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName1">:建单人
			 </span>
	      </legend>

  </fieldset>
	<% } %>

<%} %> 



<%if(taskName.equals("HoldTask")){ %>

	<% if(operateType.equals("17")){ %>
	<fieldset id="link4">
	 <legend>
	     	 <bean:message bundle="businessimplementyy" key="role.toOrgName"/>
			 <span id="roleName">:参数验证实施组
			 </span>
	 </legend>	
	 <div class="x-form-item" >
		
	 </div>
	 </fieldset> 		   
	 <%} %>	  		   
	
<%} %>
<!-- added by liufuyuan -->
<script type="text/javascript">
	if('<%=operateType%>'=='74'){
		
		popOtherFlow('circuitdispatch',$('${sheetPageName}linkIsAppled').value);

	}
	function popOtherFlow(input,value){
		if(input=='circuitdispatch'&&value=='101330101'){
			eoms.form.disableArea('BianHao',true);  
			document.getElementById('startCircuit').style.display='';
			$('${sheetPageName}phaseId').value='waitReturn';
			$('${sheetPageName}operateType').value='111';
			chooser_test1.disable();
		}else if(input=='circuitdispatch'&&value!='101330101'){
			eoms.form.enableArea('BianHao');
		    
			document.getElementById('startCircuit').style.display='none';
			$('${sheetPageName}phaseId').value='DataMakeTask';
			$('${sheetPageName}operateType').value='74';
			chooser_test1.enable();
		}
	}
	function openwin(flag) {
		var url;
		//alert(flag);
		if(flag=="startCircuit") {
			url="${app}/sheet/businessimplement/businessimplement.do?method=showNewSheetPage"
			  +"&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessImplementYYMainManager"
			  +"&title=<%=title%>"
			  +"&specialtyType=101230104"
			  +"&isShowLanguage=yes"
			  +"&isCalledByLangugage=yes"
			  +"&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}"
			  +"&orderSheetId=<%=orderSheetId%>";
		
			$('${sheetPageName}phaseId').value='waitReturn';
		} 
		window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
function onchangeAppled(input){
   		var frm = document.forms[0];
	    var temp = frm.linkIsAppled ? frm.linkIsAppled.value : '';
	    
		if(temp != ''){
		   	if(input==101330102){
				eoms.form.enableArea('NoApplied');
			}else if(input==101330101){
	   			eoms.form.disableArea('NoApplied',true);  
		    }else {
				eoms.form.disableArea('NoApplied',true);
	 		}	  
		}
}
function onchangelinkInfoMethod(input){
   		var frm = document.forms[0];
	    var temp = frm.linkInfoMethod ? frm.linkInfoMethod.value : '';
	    
		if(temp != ''){
		   	if(input==101330302){
				eoms.form.enableArea('InfoMethod'); 
		    }else {
				eoms.form.disableArea('InfoMethod',true);
	 		}	  
		}
}
function onchangelinkIsDataOk(input){
   		var frm = document.forms[0];
	    var temp = frm.linkIsDataOk ? frm.linkIsDataOk.value : '';
	    
		if(temp != ''){
		   	if(input==101330602){
				eoms.form.enableArea('IsDataOk'); 
		    }else {
				eoms.form.disableArea('IsDataOk',true);
	 		}	  
		}
}
function onchangelinkIsOkNeed(input){
   		var frm = document.forms[0];
	    var temp = frm.linkIsOkNeed ? frm.linkIsOkNeed.value : '';
	    
		if(temp != ''){
		   	if(input==101330102){
				eoms.form.enableArea('IsOkNeed'); 
		    }else {
				eoms.form.disableArea('IsOkNeed',true);
	 		}	  
		}
}
 </script>
