<%@ include file="/common/taglibs.jsp"%>
<%
 request.setAttribute("roleId","204"); 
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
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
		//alert(" ");
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
		url: "businessoperation.do?method=newShowLimit&flowName=BusinessOperationProcess",
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessoperation/baseinputmainhtmlnew.jsp"%>

	<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessOperationProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="BusinessOperationProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="operate" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessOperationMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink"/>
    <br/>
	<!-- sheet info -->
     <table id=sheet class="formTable">
      <c:if test="${status!=1}">
             	           <tr>
				  <td class="label"><bean:message bundle="businessoperation" key="businessoperation.sheetAcceptLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
						id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/> 
			  			  
				  </td>				
				  <td class="label"><bean:message bundle="businessoperation" key="businessoperation.sheetCompleteLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
						id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>   
					
				  </td>		  
				</tr>
				 <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainProductType"/>*</td>
		            <td class="content">
		               <eoms:comboBox name="${sheetPageName}mainProductType" id="${sheetPageName}mainProductType" 
            	      initDicId="1010515"  sub="${sheetPageName}mainProductTypeTwo" alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainProductType}"/>
		            </td>
                    <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainProductTypeTwo"/>*</td>
		            <td class="content">
		              <eoms:comboBox name="${sheetPageName}mainProductTypeTwo" id="${sheetPageName}mainProductTypeTwo" 
			  	      initDicId="${sheetMain.mainProductType}" defaultValue="${sheetMain.mainProductTypeTwo}" alt="allowBlank:false"/>
		          
		            </td>               
		          </tr>
			
                 <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainProductCode"/>*</td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}mainProductCode" value="${sheetMain.mainProductCode}" id="${sheetPageName}mainProductCode" readOnly="true" alt="allowBlank:false,vtext:'${eoms:a2u('请选择新产品编号')}'"/>
                          <input type="button" class="btn" value="${eoms:a2u('引用')}" onclick="window.open('${app}/sheet/nBProducts/nbproductss.do?method=xquery&ifReference=true',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')"/>
		            </td>
		             <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainProductName"/>*</td>
		            <td class="content">  
   		                <input type="text"  class="text" name="${sheetPageName}mainProductName" id="${sheetPageName}mainProductName" readOnly="true"  alt="allowBlank:false" value="${sheetMain.mainProductName}"/>
			        </td>	
		         </tr>
		        

                <tr>
                  <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainTask"/>*</td>
		            <td  class="content" colspan="3"> 	
				        <eoms:comboBox name="${sheetPageName}mainTask" id="${sheetPageName}mainTask" 
            	          initDicId="1010512"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainTask}"/>
			       </td>	
		         </tr>

			      
                   <tr>
		              <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainIsGF"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainIsGF" id="${sheetPageName}mainIsGF" 
            	      initDicId="1010505"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainIsGF}"/>
			        </td>
		                 <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainIsGC"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainIsGC" id="${sheetPageName}mainIsGC" 
            	      initDicId="1010506"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainIsGC}"/>
			        </td>	                
		           </tr>
		           
			      <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainDesignSheetId"/></td>
		            <td  class="content" > 
		            <input type="text"  class="text" name="${sheetPageName}mainDesignSheetId" id="${sheetPageName}mainDesignSheetId"  value="${sheetMain.mainDesignSheetId}" alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('请填入新业务分类相关设计配合工单号，最多输入20字!')}'"/></td>
		          <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainSheetId"/></td>
		            <td  class="content" > 
		            <input type="text"  class="text" name="${sheetPageName}mainSheetId" id="${sheetPageName}mainSheetId"  value="${sheetMain.mainSheetId}" alt="allowBlank:true,maxLength:20,vtext:'${eoms:a2u('请填入新业务分类相关试点工单号，最多输入20字!')}'"/></td>
		          </tr>
		          
                  <tr>
		            <td  class="label"><bean:message bundle="businessoperation" key="businessoperation.mainSummarize"/>*</td>
		              <td  class="content" colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainSummarize"  id="${sheetPageName}mainSummarize" alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入推广概述，最多输入1000字!')}'">${sheetMain.mainSummarize}</textarea>
                    </td>
		          </tr>
		         <!-- <tr>
				    <td class="label">
				    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
					</td>	
					<td colspan="3">					
				    <eoms:attachment name="tawSheetAccess" property="accesss" 
				            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
				    </td>
		         </tr>	 -->	
		          
	                <tr>
  						<td class="label"><bean:message bundle="businessoperation" key="businessoperation.mainExtendAcc"/>*</td>
            			  
            			 <td  colspan='3'>
		   				 <eoms:attachment idList="" name="sheetMain" scope="request" property="mainExtendAcc" idField="${sheetPageName}mainExtendAcc" appCode="businessoperation" alt="allowBlank:false"/>
		                </td>
		                </td>
		            </tr>
     </c:if>
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset id="link1">
	      <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			 <bean:message bundle="businessoperation" key="role.businessSupportG"/>
			 </span>
	      </legend>
	  		<eoms:chooser id="test1" type="role" roleId="221" flowId="023" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"
			   data="${sendUserAndRoles}"/>
     </fieldset>
     <fieldset id="link2">
	 <legend>
			${eoms:a2u('抄送角色')}:
			<bean:message bundle="businessoperation" key="role.adminGroup"/>			 
	 </legend>
				<eoms:chooser id="test2" type="role" roleId="222" flowId="023" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user,subrole',allowBlank:false,text:'${eoms:a2u('抄送')}',vtext:'${eoms:a2u('请选择抄送对象')}'}]"
			   data="${sendUserAndRoles}"/>
   	 
	</fieldset>	
     
	</c:if>
