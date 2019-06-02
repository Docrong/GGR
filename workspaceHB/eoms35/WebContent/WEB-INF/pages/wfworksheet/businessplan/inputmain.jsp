<%@ include file="/common/taglibs.jsp"%>
<% request.setAttribute("roleId","204");
long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/businessplan/baseinputmainhtmlnew.jsp"%>
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
		alert(" ");
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
		url: "businessplan.do?method=newShowLimit&flowName=BusinessPlanProcess",
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
	<input type="hidden" name="${sheetPageName}processTemplateName" value="BusinessPlanProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newSheet" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="analyse" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessPlanMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.businessplan.model.BusinessPlanMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.businessplan.model.BusinessPlanLink"/>
	<!-- sheet info -->
	<br/>
     <table id=sheet class="formTable">
      <c:if test="${status!=1}">
      
	           <tr>
				  <td class="label"><bean:message bundle="businessplan" key="businessplan.sheetAcceptLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
						id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/> 
			  			  
				  </td>				
				  <td class="label"><bean:message bundle="businessplan" key="businessplan.sheetCompleteLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
						id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>   
					
				  </td>		  
				</tr>
				 <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.mainProductType"/>*</td>
		            <td class="content">
		               <eoms:comboBox name="${sheetPageName}mainProductType" id="${sheetPageName}mainProductType" 
            	      initDicId="1010515"  sub="${sheetPageName}mainProductTypeTwo" alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainProductType}"/>
		            </td>
                    <td  class="label"><bean:message bundle="businessplan" key="businessplan.mainProductTypeTwo"/>*</td>
		            <td class="content">
		              <eoms:comboBox name="${sheetPageName}mainProductTypeTwo" id="${sheetPageName}mainProductTypeTwo" 
			  	      initDicId="${sheetMain.mainProductType}" defaultValue="${sheetMain.mainProductTypeTwo}" alt="allowBlank:false"/>
		          
		            </td>               
		          </tr>
		            
			    <tr>
		            
                    <td  class="label"><bean:message bundle="businessplan" key="businessplan.mainProductCode"/>*</td>
		            <td class="content"><input type="text"  class="text" name="${sheetPageName}mainProductCode" readOnly="true" id="${sheetPageName}mainProductCode" alt="allowBlank:false,vtext:'${eoms:a2u('请选择新产品编号')}'" value="${sheetMain.mainProductCode}" />

                          <input type="button" class="btn" value="${eoms:a2u('引用')}" onclick="window.open('${app}/sheet/nBProducts/nbproductss.do?method=xquery&ifReference=true',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')"/>
		            </td>
		            <td class="label"><bean:message bundle="businessplan" key="businessplan.mainProductName"/>*</td>
		            <td class="content">  
   		                <input type="text"  class="text" name="${sheetPageName}mainProductName" id="${sheetPageName}mainProductName" readOnly="true"  alt="allowBlank:false" value="${sheetMain.mainProductName}"/>
			        </td>	                
		            </tr>
    	            
                 <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.mainReqType"/>*</td>
		            <td class="content" colspan="3">  
				        <eoms:comboBox name="${sheetPageName}mainReqType" id="${sheetPageName}mainReqType" 
            	      initDicId="1010501"  alt="allowBlank:false" styleClass="select-class" defaultValue="${sheetMain.mainReqType}"/>
			        </td>
		          </tr>
		          
		          
			      <tr>
		            <td  class="label"><bean:message bundle="businessplan" key="businessplan.mainTecDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainTecDesc" id="${sheetPageName}mainTecDesc" alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入技术规范说明，最大长度为1000个汉字！')}'" >${sheetMain.mainTecDesc}</textarea>
                    </td>
		          </tr>
        <!--  <tr>
		    <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td>
		  </tr> --> 
	                <tr>
  						<td class="label"><bean:message bundle="businessplan" key="businessplan.mainStandard" />*</td>
			            <td colspan="3">					
                   <eoms:attachment name="sheetMain" property="mainStandard" 
                     scope="request" idField="${sheetPageName}mainStandard" alt="allowBlank:false" appCode="businessplan"  />		            			
		                </td>
		           </tr>
</c:if>
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			 <bean:message bundle="businessplan" key="role.businessNetDeveloper"/>
			 </span>
	      </legend>
		<eoms:chooser id="test" type="role" roleId="205" flowId="021" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"
			   data="${sendUserAndRoles}"/>
   	
	</fieldset>
	</c:if>
