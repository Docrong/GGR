<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
 String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId")); 
 String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId")); 
 System.out.println("=====taskName======"+taskName);
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 request.setAttribute("operateType",operateType);
 String operateUserId="";
BaseLink bl = (BaseLink)request.getAttribute("preLink");
IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("CircuitOperation");
if(bl != null) {
	String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
	if(!prelinkid.equals(""))
	{
	BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
	operateUserId = base.getOperateUserId();
	}
}
 		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
 		String userName = sessionform.getUserid();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/circuitoperation/baseinputlinkhtmlnew.jsp"%>
	<br/>
	<table class="formTable">
         <input type="hidden" name="${sheetPageName}beanId" value="iCircuitOperationMainManager"/> 
         <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.circuitoperation.model.CircuitOperationMain"/>	
         <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.circuitoperation.model.CircuitOperationLink"/>
	  <c:if test="${taskName != 'HoldTask' }">
		  <%if(operateType.equals("")){ %>
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
           
		
		
      <!-- Z端业务侧方案制作 -->
        <%if(taskName.equals("MakeZTask")){ %>
             <%if(operateType.equals("200")||operateType.equals("11")){ %>
		     <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
   		     <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTTask" />	
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
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.linkZContact"/>*</td>
	              <td class="content">  
  		                <input type="text"  class="text" name="${sheetPageName}linkZContact" id="${sheetPageName}linkZContact"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z施工联系人，最多输入25字'" value="${sheetLink.linkZContact}"/>
		          </td>					    
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.linkZContactTel"/>*</td>
	              <td class="content">  
  		                <input type="text"  class="text" name="${sheetPageName}linkZContactTel" id="${sheetPageName}linkZContactTel"    alt="allowBlank:false,maxLength:25,vtext:'请填入Z施工业务联系电话，最多输入25字'" value="${sheetLink.linkZContactTel}"/>
		          </td>	
		       </tr>	  		    	
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
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="circuitoperation" alt="allowBlank:true"/>
		                </td>
		         </tr>  		    	  
         <%}} %>
         
         
         
         
         
      <!-- 传输侧电路调度方案制作 -->
        <%if(taskName.equals("MakeTTask")){ %>
             <%if(operateType.equals("201")||operateType.equals("11")){ %>
		        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
   		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackTask" />
				<tr>
				  <td class="label">传输配置</td>
				  <td  colspan="3">
				  	<a onclick="popupCircuidPage()" style="cursor:pointer;">传输电路设计</a>
				  </td>	  					  
				</tr>   		    			    			  
		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.linkOperationDesc"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}linkOperationDesc" id="${sheetPageName}linkOperationDesc" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入操作描述，最多输入255个字符'">${sheetLink.linkOperationDesc}</textarea>
					</td>
				</tr> 
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
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="circuitoperation" alt="allowBlank:true"/>
		                </td>
		         </tr> 
 
 
         <%}} %>
               
      <!-- 实施结果反馈 -->
        <%if(taskName.equals("BackTask")){ %>
             <%if(operateType.equals("202")||operateType.equals("11")){ %>
		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />   
  		    	<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
   		    	<input type="hidden" name="${sheetPageName}extendKey2" id="${sheetPageName}extendKey2" value="HoldTask" /> 
   		    	<input type="hidden" name="${sheetPageName}toMorePhaseId" id="${sheetPageName}toMorePhaseId" value="DateATask,DateZTask" /> 
          <tr><td colspan="3">
          <fieldset id ="info">
          	<legend>传输信息</legend>
          <table class="formTable">
			  <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainTransferNum"/>*</td>
	              <td colspan="3">  
  		                <input type="text"  class="text" name="${sheetPageName}mainTransferNum" id="${sheetPageName}mainTransferNum"   alt="allowBlank:false,maxLength:25,vtext:'请填入传输调单号，最多输入25字'" value="${sheetMain.mainTransferNum}"/>
		          </td>					    
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainConstructor"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainConstructor" id="${sheetPageName}mainConstructor"   alt="allowBlank:false,maxLength:25,vtext:'请填入传输调单号，最多输入25字'" value="${sheetMain.mainConstructor}"/>
		          </td>			       
	     		  <td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainConstructionIfS"/>*</td>
	     		  <td >
	     			<eoms:comboBox name="${sheetPageName}mainConstructionIfS" id="${sheetPageName}mainConstructionIfS" initDicId="10301" defaultValue="${sheetMain.mainConstructionIfS}" alt="allowBlank:false"/>
				  </td>		       
		       </tr>
		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainConstructionDesc"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}mainConstructionDesc" id="${sheetPageName}mainConstructionDesc" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入施工结果描述，最多输入255个字符'">${sheetMain.mainConstructionDesc}</textarea>
					</td>
				</tr> 
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainCircuitCode"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainCircuitCode" id="${sheetPageName}mainCircuitCode"   alt="allowBlank:false,maxLength:25,vtext:'请填入电路代号，最多输入25字'" value="${sheetMain.mainCircuitCode}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainCircuitStatus"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainCircuitStatus" id="${sheetPageName}mainCircuitStatus"   alt="allowBlank:false,maxLength:25,vtext:'请填入电路状态，最多输入25字'" value="${sheetMain.mainCircuitStatus}"/>
		          </td>		       
		       </tr>				
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainSpeed"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainSpeed" id="${sheetPageName}mainSpeed"   alt="allowBlank:false,maxLength:25,vtext:'请填入速率，最多输入25字'" value="${sheetMain.mainSpeed}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainAClient"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainAClient" id="${sheetPageName}mainAClient"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端客户，最多输入25字'" value="${sheetMain.mainAClient}"/>
		          </td>		       
		       </tr>				
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZClient"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZClient" id="${sheetPageName}mainZClient"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端客户，最多输入25字'" value="${sheetMain.mainZClient}"/>
		          </td>			       
				   <td class="label">		     
		             <bean:message bundle="circuitoperation" key="circuitoperation.mainOpenDate"/>*
		           </td>
		           <td class="content"> 
		            <input class="text" onclick="popUpCalendar(this, this)" type="text" 
		               name="${sheetPageName}mainOpenDate" readonly="readonly" 
		                  value="${eoms:date2String(sheetMain.mainOpenDate)}" id="${sheetPageName}mainOpenDate" alt="allowBlank:false"/>
		           </td>		       
		       </tr>				
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainCircuitAlias"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainCircuitAlias" id="${sheetPageName}mainCircuitAlias"   alt="allowBlank:false,maxLength:25,vtext:'请填入电路别名，最多输入25字'" value="${sheetMain.mainCircuitAlias}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainNumber"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainNumber" id="${sheetPageName}mainNumber"   alt="allowBlank:false,maxLength:25,vtext:'请填入调单号，最多输入25字'" value="${sheetMain.mainNumber}"/>
		          </td>		       
		       </tr>				
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainCircuitBoard"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainCircuitBoard" id="${sheetPageName}mainCircuitBoard"   alt="allowBlank:false,maxLength:25,vtext:'请填入电路局向，最多输入25字'" value="${sheetMain.mainCircuitBoard}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainBandwidth"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainBandwidth" id="${sheetPageName}mainBandwidth"   alt="allowBlank:false,maxLength:25,vtext:'请填入带宽，最多输入25字'" value="${sheetMain.mainBandwidth}"/>
		          </td>		       
		       </tr>	
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainCircuitForUnit"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainCircuitForUnit" id="${sheetPageName}mainCircuitForUnit"   alt="allowBlank:false,maxLength:25,vtext:'请填入电路申请单位，最多输入25字'" value="${sheetMain.mainCircuitForUnit}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainCircuitUse"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainCircuitUse" id="${sheetPageName}mainCircuitUse"   alt="allowBlank:false,maxLength:25,vtext:'请填入电路用途，最多输入25字'" value="${sheetMain.mainCircuitUse}"/>
		          </td>		       
		       </tr>		       
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainBusinessType"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainBusinessType" id="${sheetPageName}mainBusinessType"   alt="allowBlank:false,maxLength:25,vtext:'请填入业务类型，最多输入25字'" value="${sheetMain.mainBusinessType}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainUseType"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainUseType" id="${sheetPageName}mainUseType"   alt="allowBlank:false,maxLength:25,vtext:'请填入使用方式，最多输入25字'" value="${sheetMain.mainUseType}"/>
		          </td>		       
		       </tr>		       
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainTransferBoard"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainTransferBoard" id="${sheetPageName}mainTransferBoard"   alt="allowBlank:false,maxLength:25,vtext:'请填入转接局，最多输入25字'" value="${sheetMain.mainTransferBoard}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainRouting"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainRouting" id="${sheetPageName}mainRouting"   alt="allowBlank:false,maxLength:25,vtext:'请填入路由，最多输入25字'" value="${sheetMain.mainRouting}"/>
		          </td>		       
		       </tr>	
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainABusinessMRoom"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainABusinessMRoom" id="${sheetPageName}mainABusinessMRoom"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端业务机房，最多输入25字'" value="${sheetMain.mainABusinessMRoom}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainABusinessNE"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainABusinessNE" id="${sheetPageName}mainABusinessNE"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端业务网元，最多输入25字'" value="${sheetMain.mainABusinessNE}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainABusinessPort"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainABusinessPort" id="${sheetPageName}mainABusinessPort"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端业务端口，最多输入25字'" value="${sheetMain.mainABusinessPort}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainABusinessDO"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainABusinessDO" id="${sheetPageName}mainABusinessDO"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端业务侧DDF/ODF，最多输入25字'" value="${sheetMain.mainABusinessDO}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZBusinessMRoom"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZBusinessMRoom" id="${sheetPageName}mainZBusinessMRoom"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端业务机房，最多输入25字'" value="${sheetMain.mainZBusinessMRoom}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainABusinessNE"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZBusinessNE" id="${sheetPageName}mainZBusinessNE"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端业务网元，最多输入25字'" value="${sheetMain.mainZBusinessNE}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZBusinessPort"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZBusinessPort" id="${sheetPageName}mainZBusinessPort"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端业务端口，最多输入25字'" value="${sheetMain.mainZBusinessPort}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZBusinessDO"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZBusinessDO" id="${sheetPageName}mainZBusinessDO"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端业务侧DDF/ODF，最多输入25字'" value="${sheetMain.mainZBusinessDO}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainATransferMRoom"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainATransferMRoom" id="${sheetPageName}mainATransferMRoom"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端传输机房，最多输入25字'" value="${sheetMain.mainATransferMRoom}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainATransferNE"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainATransferNE" id="${sheetPageName}mainATransferNE"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端传输网元，最多输入25字'" value="${sheetMain.mainATransferNE}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainATransferPort"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainATransferPort" id="${sheetPageName}mainATransferPort"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端传输端口，最多输入25字'" value="${sheetMain.mainATransferPort}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainATransferDO"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainATransferDO" id="${sheetPageName}mainATransferDO"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端传输侧DDF/ODF，最多输入25字'" value="${sheetMain.mainATransferDO}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZTransferMRoom"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZTransferMRoom" id="${sheetPageName}mainZTransferMRoom"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端传输机房，最多输入25字'" value="${sheetMain.mainZTransferMRoom}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZTransferNE"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZTransferNE" id="${sheetPageName}mainZTransferNE"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端传输网元，最多输入25字'" value="${sheetMain.mainZTransferNE}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZTransferPort"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZTransferPort" id="${sheetPageName}mainZTransferPort"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端传输端口，最多输入25字'" value="${sheetMain.mainZTransferPort}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZTransferDO"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZTransferDO" id="${sheetPageName}mainZTransferDO"   alt="allowBlank:false,maxLength:25,vtext:'请填入Z端传输侧DDF/ODF，最多输入25字'" value="${sheetMain.mainZTransferDO}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainNetSortOne"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"   alt="allowBlank:false,maxLength:25,vtext:'请填入一级网络分类，最多输入25字'" value="${sheetMain.mainNetSortOne}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainNetSortTwo"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"   alt="allowBlank:false,maxLength:25,vtext:'请填入二级网络分类，最多输入25字'" value="${sheetMain.mainNetSortTwo}"/>
		          </td>		       
		       </tr>
		       <tr>
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainATransferPortT"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainATransferPortT" id="${sheetPageName}mainATransferPortT"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端传输端口时隙，最多输入25字'" value="${sheetMain.mainATransferPortT}"/>
		          </td>			       
	              <td  class="label"><bean:message bundle="circuitoperation" key="circuitoperation.mainZTransferPortT"/>*</td>
	              <td>  
  		                <input type="text"  class="text" name="${sheetPageName}mainZTransferPortT" id="${sheetPageName}mainZTransferPortT"   alt="allowBlank:false,maxLength:25,vtext:'请填入A端传输端口时隙，最多输入25字'" value="${sheetMain.mainZTransferPortT}"/>
		          </td>		       
		       </tr>		       			
						    	
   		   </table> 
			</fieldset>     		   	
   	     </td></tr>	    		
		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.linkOperationDesc"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}linkOperationDesc" id="${sheetPageName}linkOperationDesc" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入操作描述，最多输入255个字符'">${sheetLink.linkOperationDesc}</textarea>
					</td>
				</tr> 
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
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="circuitoperation" alt="allowBlank:true"/>
		                </td>
		         </tr>  		    	      
         <%}} %>
         <!-- A端业务侧数据制作以及确认 -->
         <%if(taskName.equals("DateATask")){ %>
             <%if(operateType.equals("203")||operateType.equals("11")){ %>
 		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />                   
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=HoldTask />
         	 <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />             
		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.linkOperationDesc"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}linkOperationDesc" id="${sheetPageName}linkOperationDesc" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入操作描述，最多输入255个字符'">${sheetLink.linkOperationDesc}</textarea>
					</td>
				</tr> 
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
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="circuitoperation" alt="allowBlank:true"/>
		                </td>
		         </tr>  

         <%}} %>
         <!-- Z端业务侧数据制作以及确认 -->
         <%if(taskName.equals("DateZTask")){ %>
             <%if(operateType.equals("204")||operateType.equals("11")){ %>
 		    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>" />                   
             <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=HoldTask />
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />             
		        <tr>
		     		<td class="label"><bean:message bundle="circuitoperation" key="circuitoperation.linkOperationDesc"/>*</td>
		     		<td colspan="3">
		     			<textarea name="${sheetPageName}linkOperationDesc" id="${sheetPageName}linkOperationDesc" class="textarea max" alt="allowBlank:false,maxLength:255,vtext:'请填入操作描述，最多输入255个字符'">${sheetLink.linkOperationDesc}</textarea>
					</td>
				</tr> 
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
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="circuitoperation" alt="allowBlank:true"/>
		                </td>
		         </tr>  

         <%}} %>         
		 <%if(operateType.equals("4")){ %>
			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4" />
			
			<c:choose> 
			  	<c:when test="${empty fPreTaskName}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${fPreTaskName == 'DraftTask'}">
					<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask" />
				</c:when>
				<c:when test="${taskName == 'HandleTask'}">
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask" />				
				</c:when>
				<c:otherwise>
			  		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}" />
				</c:otherwise>
			</c:choose>				
    	<tr>
	       <td class="label">
	        <bean:message bundle="circuitoperation" key="circuitoperation.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		
		<%} %>         
		  <!-- 待归档 -->
          <%if( taskName.equals("HoldTask")){%>
         	 <%if(operateType.equals("18")){ %>
         	 	<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true" />
      			<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18" />
      			<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over" />
      			<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>	
      			
                 <%String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
	               if(parentProcessName.equals("iBusinessPilotMainManager")){%>
	                <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true" />
                    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}" />        
                    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName" value="backToBaseStationOpenYN" />
                    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId" value="BaseStationOpenYN" />                                                        
	                <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId" value="${parentMain.id}" />                
	                <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="CityEleCallTask" />        
	                <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="holdReplyProcess" />        	                                                
	             <%} %>
      			
      			
         	 	 
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
  						<td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
		   				 <td  colspan='3'>
		   				 <eoms:attachment idList="" scope="request" idField="${sheetPageName}nodeAccessories" name="sheetLink" property="nodeAccessories" appCode="circuitoperation" alt="allowBlank:true"/>
		                </td>
		         </tr> 			  	        			    			 
              <%}%>              
          <%}%>

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
		
		<%}%>
		

		
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
		
<%if(taskName.equals("MakeZTask")){ %>
   	<% if(operateType.equals("200") ){ %>    
		<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="circuitoperation" key="role.toOrgName"/>
					 <span id="roleName">:电路调度员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="4062" flowId="421" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>	   	
<%}} %> 
<%if(taskName.equals("MakeTTask")){ %>
   	<% if(operateType.equals("201") ){ %>    
		<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="circuitoperation" key="role.toOrgName"/>
					 <span id="roleName">:电路调度员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="4062" flowId="421" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			 />		       	
			 </div>	
			 </fieldset>	   	
<%}} %> 
<%if(taskName.equals("BackTask")){ %>
   	<% if(operateType.equals("202") ){ %>    
		<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="circuitoperation" key="role.toOrgName"/>
					 <span id="roleName">:电路调度申请人
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test1"  type="role" roleId="4060" flowId="421" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'supplier1Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]" 
			 />		       	
			 </div>	
			 </fieldset>
		<fieldset id ="link2">
			 <legend>
			     	 <bean:message bundle="circuitoperation" key="role.toOrgName"/>
					 <span id="roleName">:Z端业务侧方案制作员
					 </span>
			 </legend>	
			 <div class="x-form-item" >
			<eoms:chooser id="test2"  type="role" roleId="4061" flowId="421" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'supplier2Performer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]" 
			 />		       	
			 </div>	
			 </fieldset>				 	   	
<%}} %> 


<%if(taskName.equals("DateATask")){ %>
   	<% if(operateType.equals("203") ){ %>    
		<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="circuitoperation" key="role.toOrgName"/>
					 <span id="roleName">:电路调度申请人
					 </span>
			 </legend>	
			 <div class="x-form-item" >
		   <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
		   <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemUserDao" />
	       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemDeptDao" />&nbsp;&nbsp;	</br>	       	       	       	
			 </div>	
			 </fieldset>	
<%}} %>

<%if(taskName.equals("DateZTask")){ %>
   	<% if(operateType.equals("204") ){ %>    
		<fieldset id ="link1">
			 <legend>
			     	 <bean:message bundle="circuitoperation" key="role.toOrgName"/>
					 <span id="roleName">:电路调度申请人
					 </span>
			 </legend>	
			 <div class="x-form-item" >
		   <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
		   <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemUserDao" />
	       <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemDeptDao" />&nbsp;&nbsp;	</br>	       	       	       	
			 </div>	
			 </fieldset>	
<%}} %>

<%if(taskName.equals("HoldTask")){ %>		  	
<%} %>
<script type="text/javascript">
		function popupCircuidPage(){
		    var userName = "<%=userName%>";
			var urls = "http://10.32.1.116:9900/webattemp/raptor/subsystem/attempX/irms/index.jsp?sheetId=${sheetMain.mainTransferNum}&userName="+userName;
			window.open(urls);
		}
 </script>

