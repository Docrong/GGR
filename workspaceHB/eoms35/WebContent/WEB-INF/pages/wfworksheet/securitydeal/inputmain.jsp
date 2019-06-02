<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
 %>
<script type="text/javascript">
function selectLimit(obj){
  
    if($("${sheetPageName}mainNetSortOne").value == null ||$("${sheetPageName}mainNetSortOne").value ==""){
        return false;
    }

    var temp1=$("${sheetPageName}mainNetSortOne").value;
    var temp2=$("${sheetPageName}mainNetSortTwo").value;
    var temp3=$("${sheetPageName}mainNetSortThree").value;
    
    Ext.Ajax.request({
		method:"get",
		url: "securitydeal.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&flowName=SecurityDealProcess",
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
<% request.setAttribute("roleId","330");%>
<%@ include file="/WEB-INF/pages/wfworksheet/securitydeal/baseinputmainhtmlnew.jsp"%> 

	<input type="hidden" name="${sheetPageName}processTemplateName" value="SecurityDealProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="SecurityDealProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet" />
	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="${operateType}"/>
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" value="MakeTask" id="${sheetPageName}phaseId" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iSecurityDealMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.securitydeal.model.SecurityDealMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.securitydeal.model.SecurityDealLink"/>
	<!-- sheet info -->
	<br/>
     <table class="formTable">
        <tr>
				  <td class="label"><bean:message bundle="securitydeal" key="securitydeal.sheetAcceptLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
						id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/> 
			  			  
				  </td>				
				  <td class="label"><bean:message bundle="securitydeal" key="securitydeal.sheetCompleteLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
						id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>   
					
				  </td>		  
                    </tr>
                   
         <c:if test="${status==1}">
		<!-- 归档时显示已经选择的字典值 -->
		
		</c:if>		
		<c:if test="${status!=1}">  
	             <tr>
			  <td class="label"><bean:message bundle="securitydeal" key="securitydeal.mainNetSortOne"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne" 
			  	      sub="${sheetPageName}mainNetSortTwo" initDicId="1010104" defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td >				
			  <td class="label"><bean:message bundle="securitydeal" key="securitydeal.mainNetSortTwo"/>*</td>
			  <td class="content">
			    <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo" 
			  	      sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}" defaultValue="${sheetMain.mainNetSortTwo}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>		  
			</tr>
            <tr>
			  <td class="label"><bean:message bundle="securitydeal" key="securitydeal.mainNetSortThree"/>*</td>
			  <td colspan='3' class="content">
			  	 <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
			  	      initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			</tr>
			<tr>
		            <td  class="label"><bean:message bundle="securitydeal" key="securitydeal.mainSecurityDealRequire"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainSecurityDealRequire" id="${sheetPageName}mainSecurityDealRequire" alt="allowBlank:false,maxLength:500,vtext:'请输入安全处理要求，最大长度为250个汉字！'">${sheetMain.mainSecurityDealRequire}</textarea>
                    </td>
		    </tr>
		    <tr>
		                <td class="label">
		    	            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			           </td>	
			           <td colspan="3" class="content">					
		                 <eoms:attachment name="tawSheetAccess" property="accesss" 
		                     scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y" />			
		                  </td>
		             </tr>
		    <tr>
		           <td class="label">
		    	    <bean:message bundle="sheet" key="mainForm.accessories"/>
			       </td>	
			       <td class="content" colspan="3">					
		            <eoms:attachment name="sheetMain" property="sheetAccessories" 
		               scope="request" idField="${sheetPageName}sheetAccessories" appCode="securitydeal" alt="allowBlank:true"/> 				
		           </td>
		        </tr>
        </c:if>	
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
	     	 <span id="roleName">:<bean:message bundle="securitydeal" key="role.secmaintenance"/>
	     	 </span>
	      </legend>
	       <eoms:chooser id="test"  type="role" roleId="317" flowId="073" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			  data="${sendUserAndRoles}" />
	</fieldset>
	</c:if>