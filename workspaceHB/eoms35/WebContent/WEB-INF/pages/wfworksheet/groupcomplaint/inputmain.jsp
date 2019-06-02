<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

 request.setAttribute("roleId","305"); 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 System.out.println("=====taskName=="+taskName); 

%>  

<script type="text/javascript">
function selectLimit(obj){
    if($("${sheetPageName}bservType").value == null ||$("${sheetPageName}bservType").value ==""){
       // alert("请选择故障专业！");
        return false;
    }

    var temp1=$("${sheetPageName}bservType").value;
          
    Ext.Ajax.request({
		method:"get",
		url: "groupcomplaint.do?method=newShowLimit&specialty1="+temp1+"&flowName=GroupComplaintProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	    //$("${sheetPageName}sheetAcceptLimit").value = "";
        	    //$('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
 
</script>

   <c:if test="${empty sheetMain.piid}">
		<script type="text/javascript">
		     //if($("${sheetPageName}dealTime1").value==""){
		      //var dt = new Date();
		      //alert(dt);
		      //$("${sheetPageName}dealTime1").value=dt.format('Y-m-d H:i:s');
		      //}
		      
		     //if($("${sheetPageName}dealTime2").value==""){
		      //var dt = new Date();
		      //alert(dt);
		      //$("${sheetPageName}dealTime2").value=dt.format('Y-m-d H:i:s');
		      //}		      
		</script>
	</c:if>


<%@ include file="/WEB-INF/pages/wfworksheet/groupcomplaint/baseinputmainhtmlnew.jsp"%>

	<input type="hidden" name="${sheetPageName}processTemplateName" value="GroupComplaintProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status==0}">
		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteHumTask" />
    </c:if>

    <input type="hidden" name="${sheetPageName}beanId" value="iGroupComplaintMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain"/>	<!--main表Model对象类名-->	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink"/> <!--link表Model对象类名-->
	
	<!-- templateName rule -->

	
	<!-- sheet info -->
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0" />
	<input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="0" />
	<input type="hidden" name="${sheetPageName}btype1" id="${sheetPageName}btype1" value="57" />
	
	<br/>

	
	<table id="sheet" class="formTable">	
		<c:if test="${status!=1}">
	
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.urgentDegree"/></td>
			  <td colspan="3">
		  		<eoms:comboBox name="${sheetPageName}urgentDegree" id="${sheetPageName}urgentDegree" initDicId="1010102" defaultValue="${sheetMain.urgentDegree}" alt="allowBlank:ture"/>
			  </td>			 					  
			</tr>		

			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.dealTime1"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}dealTime1" readonly="readonly" 
					id="${sheetPageName}dealTime1" value="${eoms:date2String(sheetMain.dealTime1)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}dealTime2',vtext:'受理时限不能晚于处理时限',allowBlank:false"
					/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.dealTime2"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}dealTime2" readonly="readonly" 
					id="${sheetPageName}dealTime2" value="${eoms:date2String(sheetMain.dealTime2)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}dealTime1',vtext:'受理时限不能早于受理时限',allowBlank:false"
					/>   
			  </td>		  
			</tr>			

<!-- 		<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.acceptLimit"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
					/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.completeLimit"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
					id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
					/>   
			  </td>		  
			</tr> -->	

			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.mainCompleteLimitT1"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT1" readonly="readonly" 
					id="${sheetPageName}mainCompleteLimitT1" value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}mainCompleteLimitT2',vtext:'T1时限不能晚于T2时限',allowBlank:false"
					/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.mainCompleteLimitT2"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT2" readonly="readonly" 
					id="${sheetPageName}mainCompleteLimitT2" value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}mainCompleteLimitT1',vtext:'T2时限不能早于T1时限',allowBlank:false"
					/>   
			  </td>		  
			</tr>
	
		</c:if>  
		  
	</table>	
	
	<br/>	

	<table id="sheet" class="formTable">	
		<c:if test="${status!=1}">

		   <tr>
			  <!-- <td class="label"><bean:message bundle="group" key="groupcomplaint.btype1"/></td>
			  <td>			  	
			  </td>	-->			
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.bservType"/></td>
			  <td colspan="3">
			  	<eoms:comboBox name="${sheetPageName}bservType" id="${sheetPageName}bservType" initDicId="1011002" defaultValue="${sheetMain.bservType}" alt="allowBlank:ture" onchange="selectLimit(this.value);"/>
			  </td>		  
			</tr>
			<tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.customNo"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customNo" id="${sheetPageName}customNo" value="${sheetMain.customNo}" alt="allowBlank:true,maxLength:25,vtext:'集团编号，最多输入25字符'"/>
			  </td>			
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.customName"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}customName" id="${sheetPageName}customName" value="${sheetMain.customName}" alt="allowBlank:true,maxLength:64,vtext:'集团名称，最多输入64字符'"/>
			  
			  </td>		  
			</tr>

			<tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.customLevel"/></td>
			  <td>
			  	<eoms:comboBox name="${sheetPageName}bservType" id="${sheetPageName}customLevel" initDicId="1011047" defaultValue="${sheetMain.customLevel}" alt="allowBlank:ture"/>
			  </td>		
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.provinceName"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}provinceName" id="${sheetPageName}provinceName" value="${sheetMain.provinceName}" alt="allowBlank:true,maxLength:64,vtext:'所属省份，最多输入64字符'"/>
			  
			  </td>		  
			</tr>

			<tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.cityName"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}cityName" id="${sheetPageName}cityName" value="${sheetMain.cityName}" alt="allowBlank:true,maxLength:64,vtext:'所属地市，最多输入64字符'"/>
			  </td>			
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.countyName"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}countyName" id="${sheetPageName}countyName" value="${sheetMain.countyName}" alt="allowBlank:true,maxLength:64,vtext:'所属区县，最多输入64字符'"/>
			  
			  </td>		  
			</tr>						


			
			   <tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.bdeptContact"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}bdeptContact" id="${sheetPageName}bdeptContact" value="${sheetMain.bdeptContact}" alt="allowBlank:true,maxLength:25,vtext:'集客部联系人，最多输入25字符'"/>
			  </td>			
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.bdeptContactPhone"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}bdeptContactPhone" id="${sheetPageName}bdeptContactPhone" value="${sheetMain.bdeptContactPhone}" alt="allowBlank:true,maxLength:50,vtext:'集客部联系人联系电话，最多输入50字符'"/>
			  
			  </td>		  
			</tr>

			<tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.cManagerPhone"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}cmanagerPhone" id="${sheetPageName}cmanagerPhone" value="${sheetMain.cmanagerPhone}" alt="allowBlank:true,maxLength:25,vtext:'客户经理，最多输入25字符'"/>
			  </td>			
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.cManagerContactPhone"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}cmanagerContactPhone" id="${sheetPageName}cmanagerContactPhone" value="${sheetMain.cmanagerContactPhone}" alt="allowBlank:true,maxLength:50,vtext:'客户经理联系电话，最多输入50字符'"/>			  
			  </td>		  
			</tr>			
			
			<tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.customContact"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customContact" id="${sheetPageName}customContact" value="${sheetMain.customContact}" alt="allowBlank:true,maxLength:25,vtext:'集团客户联系人，最多输入25字符'"/>
			  </td>			
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.customContactPhone"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}customContactPhone" id="${sheetPageName}customContactPhone" value="${sheetMain.customContactPhone}" alt="allowBlank:true,maxLength:50,vtext:'集团客户联系电话，最多输入50字符'"/>			  
			  </td>		  
			</tr>	
			<tr>			  
			    <td class="label"><bean:message bundle="group" key="groupcomplaint.productName"/></td>
			  <td colspan="3">
			  	<input type="text" class="text" name="${sheetPageName}productName" id="${sheetPageName}productName" value="${sheetMain.productName}" alt="allowBlank:true,maxLength:254,vtext:'产品名称，最多输入254字符'"/>
			  </td>				  
			</tr>								
  	        <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.affectedAreas"/></td>
			  <td>
		  	     <eoms:comboBox name="${sheetPageName}affectedAreas" id="${sheetPageName}affectedAreas" initDicId="1011003" defaultValue="${sheetMain.affectedAreas}" alt="allowBlank:ture"/>
			  </td>	
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.customAttribution"/></td>
			  <td>
			  	<input type="text" class="text" name="${sheetPageName}customAttribution" id="${sheetPageName}customAttribution" value="${sheetMain.customAttribution}" alt="allowBlank:true,maxLength:64,vtext:'用户归属地，最多输入64字符'"/>
			  </td>			  	 					  
			</tr>	
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.faultTime"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}faultTime" readonly="readonly" 
					id="${sheetPageName}faultTime" value="${eoms:date2String(sheetMain.faultTime)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'lessThen',link:'${sheetPageName}complaintTime',vtext:'故障时间不能晚于投诉时间',allowBlank:false"
					/>   
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintTime"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}complaintTime" readonly="readonly" 
					id="${sheetPageName}complaintTime" value="${eoms:date2String(sheetMain.complaintTime)}" 
					onclick="popUpCalendar(this, this)"
					alt="vtype:'moreThen',link:'${sheetPageName}faultTime',vtext:'投诉时间不能早于故障时间',allowBlank:false"
					/> 
		  			  
			  </td>
			</tr>						
		   <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintNum"/></td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}complaintNum" id="${sheetPageName}complaintNum" value="${sheetMain.complaintNum}" alt="allowBlank:true,maxLength:50,vtext:'故障号码，最多输入50字符'"/>
		      </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintLoca"/></td>
			  <td> 
			      <input type="text" class="text" name="${sheetPageName}complaintLoca" id="${sheetPageName}complaintLoca" value="${sheetMain.complaintLoca}" alt="allowBlank:true,maxLength:20,vtext:'故障地点，最多输入20字符'"/>
			   </td>		  
			</tr>				
			</tr>				
			  <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.termType"/></td>
			  <td colspan="3"> 
			      <input type="text" class="text" name="${sheetPageName}termType" id="${sheetPageName}termType" value="${sheetMain.termType}" alt="allowBlank:true,maxLength:254,vtext:'终端描述，最多输入254字符'"/>
			   </td>			 					  
			</tr>			
							
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.complaintDesc"/></td>
			  <td colspan="3">
		  		<textarea name="complaintDesc" id="complaintDesc" class="textarea max" alt="allowBlank:true,maxLength:2000,vtext:'投诉内容，最多输入1000汉字'">${sheetMain.complaintDesc}</textarea>	
			  </td>			 					  
			</tr>	
			<tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.preDealResult"/></td>
			  <td colspan="3">
		  		<textarea name="preDealResult" id="preDealResult" class="textarea max" alt="allowBlank:true,maxLength:1000,vtext:'预处理情况，最多输入500汉字'">${sheetMain.preDealResult}</textarea>	
			  </td>			 					  
			</tr>												
		   <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.enterpriseCode"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}enterpriseCode" id="${sheetPageName}enterpriseCode" value="${sheetMain.enterpriseCode}" alt="allowBlank:true,maxLength:64,vtext:'企业代码，最多输入64字符'"/>
		      
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.serverCode"/></td>
			  <td>
			  <input type="text" class="text" name="${sheetPageName}serverCode" id="${sheetPageName}serverCode" value="${sheetMain.serverCode}" alt="allowBlank:true,maxLength:64,vtext:'服务代码，最多输入64字符'"/>
		      
			   </td>		  
			</tr>
			 <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.apnName"/></td>
			  <td>
			   <input type="text" class="text" name="${sheetPageName}apnName" id="${sheetPageName}apnName" value="${sheetMain.apnName}" alt="allowBlank:true,maxLength:64,vtext:'APN名称，最多输入64字符'"/>
		      
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.circuitCode"/></td>
			  <td>
			   <input type="text" class="text" name="${sheetPageName}circuitCode" id="${sheetPageName}circuitCode" value="${sheetMain.circuitCode}" alt="allowBlank:true,maxLength:32,vtext:'传输专线电路代号，最多输入32字符'"/>
		      
			   </td>		  
			</tr>
			
		    <tr>
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.ecsiType"/></td>
			  <td>
			  <eoms:comboBox name="${sheetPageName}ecsiType" id="${sheetPageName}ecsiType" initDicId="1011004" defaultValue="${sheetMain.ecsiType}" alt="allowBlank:ture"/>
			  </td>				
			  <td class="label"><bean:message bundle="group" key="groupcomplaint.connectType"/></td>
			  <td>
			<eoms:comboBox name="${sheetPageName}connectType" id="${sheetPageName}connectType" initDicId="1011005" defaultValue="${sheetMain.connectType}" alt="allowBlank:ture"/>
			   </td>		  
              </tr>

										
		</c:if>		  
	</table>
	

	<br/>	
	<table id="sheet" class="formTable">	
		<c:if test="${status!=1}">




													
		</c:if>		  
	</table>	
		
	<br/>

	<%-- accessories --%>
	
	
	<table id="sheet" class="formTable">	
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
			 <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="groupcomplaint" />-->
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="groupcomplaint" /> 				
		    </td>
		  </tr>			

		
	</table>	
		  
	
	<br/>
	
   <c:if test="${status!=1}">	  
	<fieldset id="link1">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="group" key="role.FirstExcute"/>			 
	 </legend>
	

			<eoms:chooser id="test"  type="role" roleId="306" flowId="057" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发对象'}]" 
			  data="${sendUserAndRoles}" />
	</fieldset>			  
   </c:if>
		