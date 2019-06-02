<%@ include file="/common/taglibs.jsp"%>
<%
 request.setAttribute("roleId","204"); 
 String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 System.out.println("草稿进入............");
%>  
 <script type="text/javascript">
function check() {
	var templateIds = document.getElementsByName("procode");
	var i = 0;  
	var templateId = ""; 
	for (i = 0 ; i < templateIds.length; i ++) {
		if (templateIds[i].checked) {
			templateId = templateIds[i].value;
		} 
	} 
	if (templateId == "") {
		
		return false;
	} else {
		opener.inputProCode(templateId);
		window.close();
	}
}
function inputProCode(input,input2){ 
   try{
	  $('${sheetPageName}mainProductCode').value=input;
	   $('${sheetPageName}mainProductName').value=input2;
	}catch(e){
	}     
} 
     	selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "businesspilot.do?method=newShowLimit&flowName=BusinessPilotProcess",
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
<%@ include file="/WEB-INF/pages/wfworksheet/businesspilot/baseinputmainhtmlnew.jsp"%>

	<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessPilotProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessPilotProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<c:if test="${status==0}">
		<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="pilot" />
    </c:if>
 
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessPilotMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain"/>	<!--main表Model对象类名-->	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink"/> <!--link表Model对象类名-->
	
	<!-- sheet info -->
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0" />
		
		
	<br/>
	<!-- 工单主单信息 -->
	<table id="sheet" class="formTable">	
		<c:if test="${status!=1}">
		  <tr>
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.sheetAcceptLimit"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
					id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
					 alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false" onclick="popUpCalendar(this, this)"/> 
		  			  
			  </td>				
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.sheetCompleteLimit"/>*</td>
			  <td class="content">
			    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
					id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
					onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false" />   
				
			  </td>		  
			</tr>
		  
			    <tr>
		            <td  class="label"><bean:message bundle="businesspilot" key="businesspilot.mainProductType"/>*</td>
		            <td class="content">
		               <eoms:comboBox name="${sheetPageName}mainProductType" id="${sheetPageName}mainProductType" 
            	      initDicId="1010515"  sub="${sheetPageName}mainProductTypeTwo" alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainProductType}"/>
		            </td>
                    <td  class="label"><bean:message bundle="businesspilot" key="businesspilot.mainProductTypeTwo"/>*</td>
		            <td class="content">
		              <eoms:comboBox name="${sheetPageName}mainProductTypeTwo" id="${sheetPageName}mainProductTypeTwo" 
			  	      initDicId="${sheetMain.mainProductType}" defaultValue="${sheetMain.mainProductTypeTwo}" alt="allowBlank:false"/>
		        
		            </td>               
		          </tr>
			
			 <tr>
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainProductCode"/>*</td>
			  <td><input type="text"  class="text" name="${sheetPageName}mainProductCode" id="${sheetPageName}mainProductCode" readonly="true" alt="allowBlank:false" value="${sheetMain.mainProductCode}" />
			         <input type="button" class="btn" value="${eoms:a2u('引用')}" onclick="window.open('${app}/sheet/nBProducts/nbproductss.do?method=xquery&ifReference=true',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')"/>
			  </td>		 
			     <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainProductName"/>*</td>
			  <td>
			  	<input type="text"  class="text" name="${sheetPageName}mainProductName" id="${sheetPageName}mainProductName" readonly="true"  alt="allowBlank:false" value="${sheetMain.mainProductName}"/>
			  </td>		
			</tr>
			
			 <tr>
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainBusDept"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}mainBusDept" id="${sheetPageName}mainBusDept" 
			  	       initDicId="1010504" defaultValue="${sheetMain.mainBusDept}" alt="allowBlank:false"/>
			  </td>				
			   <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainBusSheetId"/></td>
			   <td colspan="3">
			    <input type="text" name="${sheetPageName}mainBusSheetId" class="text" id="${sheetPageName}mainBusSheetId" 
		         value="${sheetMain.mainBusSheetId}" alt="maxLength:100,vtext:'${eoms:a2u('相关业务设计配合工单号，最大长度为100个汉字！')}" />
			  </td>				
		   </tr>
			
			
			
			 <tr>
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainIsBus"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}mainIsBus" id="${sheetPageName}mainIsBus" 
			  	       initDicId="1010505" defaultValue="${sheetMain.mainIsBus}" alt="allowBlank:false"/>
			  </td>				
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainIsProject"/>*</td>
			  <td>
			    <eoms:comboBox name="${sheetPageName}mainIsProject" id="${sheetPageName}mainIsProject" 
			  	       initDicId="1010506" defaultValue="${sheetMain.mainIsProject}" alt="allowBlank:false"/>
			  </td>		  
			</tr>
				
			<tr>
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainStartTime"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}mainStartTime" readonly="readonly" 
					id="${sheetPageName}mainStartTime" value="${eoms:date2String(sheetMain.mainStartTime)}" 
					onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}mainEndTime',vtext:'${eoms:a2u("试点开始时间不能晚于试点结束时间")}',allowBlank:false"/> 			  
			  </td>				
			  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainEndTime"/>*</td>
			  <td>
			    <input type="text" class="text" name="${sheetPageName}mainEndTime" readonly="readonly" 
					id="${sheetPageName}mainEndTime" value="${eoms:date2String(sheetMain.mainEndTime)}" 
					onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}mainStartTime',vtext:'${eoms:a2u("试点结束时间不能早于试点开始理时间")}',allowBlank:false"/>  
			  </td>		  
			</tr>
							
           
			
		<tr>
		  <td class="label"><bean:message bundle="businesspilot" key="businesspilot.mainReqDesc"/>*</td>
		  <td colspan="3">
	  		<textarea name="mainReqDesc" id="mainReqDesc" class="textarea max"  alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入试点需求简述，最大长度为1000个汉字！')}">${sheetMain.mainReqDesc}</textarea>			   
		  </td> 
		</tr>
		<!--   <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		  </tr>	-->	
		<tr>
		    <td class="label">
		    	<bean:message bundle="businesspilot" key="businesspilot.mainAcc"/>*
			</td>	
			<td colspan="3">					
			
		    <eoms:attachment name="sheetMain" property="mainAcc" 
		            scope="request" idField="${sheetPageName}mainAcc" alt="allowBlank:false" appCode="businesspilot" /> 				
		    </td>
		  </tr>											
		</c:if>
				
		<c:if test="${status==1}">
		<!-- 归档时显示已经选择的字典值 -->
		
		</c:if>	  
		  
	</table>
		
	<br/>
	
   <c:if test="${status!=1}">	  
	<fieldset id="link1">
	 <legend>
			<bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			<bean:message bundle="businesspilot" key="role.supportPerformer"/>			 
	 </legend>
	 	<eoms:chooser id="test" type="role" roleId="221" flowId="022" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"/>
   	
	</fieldset>	
	
	<fieldset id="link2">
	 <legend>
			${eoms:a2u('抄送角色')}:
			<bean:message bundle="businesspilot" key="role.managePerformer"/>			 
	 </legend>
				<eoms:chooser id="test1" type="role" roleId="222" flowId="022" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user,subrole',allowBlank:false,text:'${eoms:a2u('抄送')}',vtext:'${eoms:a2u('请选择抄送对象')}'}]"
			   data="${sendUserAndRoles}"/>
   	 
	</fieldset>			  
   </c:if>
		