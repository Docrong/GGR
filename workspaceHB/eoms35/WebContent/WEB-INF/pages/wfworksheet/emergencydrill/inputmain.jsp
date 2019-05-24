<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<%
 String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
 long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<% request.setAttribute("roleId","365");%>
<script type="text/javascript">
function selectLimit(obj){
    if($("${sheetPageName}mainNetSortOne").value == null ||$("${sheetPageName}mainNetSortOne").value ==""){
       // alert("请选择故障专业！");
        return false;
    }

    var temp1=$("${sheetPageName}mainNetSortOne").value;
    var temp2=$("${sheetPageName}mainNetSortTwo").value;
    var temp3=$("${sheetPageName}mainNetSortThree").value;
          
    Ext.Ajax.request({
		method:"get",
		url: "emergencydrill.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&flowName=EmergencyDrillProcess",
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
<%@ include file="/WEB-INF/pages/wfworksheet/emergencydrill/baseinputmainhtmlnew.jsp"%>


	<input type="hidden" name="${sheetPageName}processTemplateName" value="EmergencyDrillProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWork" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="EmergencyDrillProcess" />
	<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeProgramTask" />
	   <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iEmergencyDrillMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillLink"/><br/>
	<!-- sheet info -->
     <table id=sheet class="formTable">
      <c:if test="${status!=1}">
             	           <tr>
				  <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
						id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/> 
			  			  
				  </td>				
				  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
						id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>   
					
				  </td>		  
				 <tr>
			  <td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainEmergencySortOne"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}mainEmergencySortOne" id="${sheetPageName}mainEmergencySortOne" 
			  	      sub="${sheetPageName}mainEmergencySortTwo" initDicId="1010801" defaultValue="${sheetMain.mainEmergencySortOne}" alt="allowBlank:false" />
			  </td>				
			  <td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainEmergencySortTwo"/>*</td>
			  <td class="content">
			    <eoms:comboBox name="${sheetPageName}mainEmergencySortTwo" id="${sheetPageName}mainEmergencySortTwo" 
			  	       initDicId="${sheetMain.mainEmergencySortOne}" defaultValue="${sheetMain.mainEmergencySortTwo}" alt="allowBlank:false" />
			  </td>		  
			</tr>

			      <tr>
			  <td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainNetSortOne"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne" 
			  	      sub="${sheetPageName}mainNetSortTwo" initDicId="1010104" defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			  <td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainNetSortTwo"/>*</td>
			  <td class="content">
			    <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo" 
			  	      sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}" defaultValue="${sheetMain.mainNetSortTwo}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>		  
			</tr>					
            <tr>
			  <td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainNetSortThree"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
			  	      initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>	
			    <td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainCompleteTime"/></td>
                  <td class="content">
					   <input type="text" class="text" name="${sheetPageName}mainCompleteTime" readonly="readonly" 
						id="${sheetPageName}mainCompleteTime" value="${eoms:date2String(sheetMain.mainCompleteTime)}" 
						onclick="popUpCalendar(this, this)"  defaultValue="${sheetMain.mainCompleteTime}" alt="allowBlank:true" />   
					 </td>		
            		           
        </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainRemarks"/></td>
		              <td class="content" colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainRemarks" id="${sheetPageName}mainRemarks" alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入125字'">${sheetMain.mainRemarks}</textarea>
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
  						<td class="label"><bean:message bundle="emergencydrill" key="emergencydrill.mainEmergencyPlan"/>*</td>
		   				 <td  class="content" colspan='3'>
		   				 <eoms:attachment name="sheetMain" property="mainEmergencyPlan" idList=""
            			  scope="request" idField="${sheetPageName}mainEmergencyPlan" appCode="emergencydrill" 
            			  alt="allowBlank:false"/> 
		                </td>
		            </tr>

     </c:if>
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset id="link1">
	      <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			 <bean:message bundle="emergencydrill" key="role.organizer"/>
			 </span>
	      </legend>
	  		<eoms:chooser id="test1" type="role" roleId="366" flowId="061" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},
			              {id:'copyPerformer',childType:'user,subrole',allowBlank:true,limit:'none',text:'抄送',vtext:'请选择抄送对象'}]"
			   data="${sendUserAndRoles}"/>
     </fieldset>
     
	</c:if>