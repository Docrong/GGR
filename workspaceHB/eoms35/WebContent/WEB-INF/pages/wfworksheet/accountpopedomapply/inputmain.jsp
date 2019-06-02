<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<% request.setAttribute("roleId","330");
   long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<script type="text/javascript">
function selectLimit(obj){
    if($("${sheetPageName}netType1").value == null ||$("${sheetPageName}netType1").value ==""){
       // alert("请选择故障专业！");
        return false;
    }

    var temp1=$("${sheetPageName}netType1").value;
    var temp2=$("${sheetPageName}netType2").value;
    var temp3=$("${sheetPageName}netType3").value;
          
    Ext.Ajax.request({
		method:"get",
		url: "accountpopedomapply.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&flowName=AccountPopedomApplyProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	    //$("${sheetPageName}sheetAcceptLimit").value = "";
        	    //$('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date(times).add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	//$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	//$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
 </script>
<%@ include file="/WEB-INF/pages/wfworksheet/accountpopedomapply/baseinputmainhtmlnew.jsp"%>

	<input type="hidden" name="${sheetPageName}processTemplateName" value="AccountPopedomApplyProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet" />
	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" value="AuditHumTask" id="${sheetPageName}phaseId" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iAccountPopedomApplyMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.accountpopedomapply.model.AccountPopedomApplyMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.accountpopedomapply.model.AccountPopedomApplyLink"/>
	<!-- sheet info -->
	<br/>
     <table class="formTable">
           	
		<c:if test="${status!=1}">  
		 
	             <tr>
			  <td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.netType1"/>*</td>
			  <td>
			  	 <eoms:comboBox name="${sheetPageName}netType1" id="${sheetPageName}netType1" 
			  	      sub="${sheetPageName}netType2" initDicId="1010104" defaultValue="${sheetMain.netType1}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			  <td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.netType2"/>*</td>
			  <td>
			    <eoms:comboBox name="${sheetPageName}netType2" id="${sheetPageName}netType2" 
			  	      sub="${sheetPageName}netType3" initDicId="${sheetMain.netType1}" defaultValue="${sheetMain.netType2}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>		  
			</tr>
            <tr>
			  <td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.netType3"/>*</td>
			  <td >
			  	 <eoms:comboBox name="${sheetPageName}netType3" id="${sheetPageName}netType3"
			  	      initDicId="${sheetMain.netType2}" defaultValue="${sheetMain.netType3}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			 <td class="label"></td>
			 <td > </td> 
			</tr>			
			<tr>
		    <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.becomeTime"/>*</td>
		    <td> 	
		      <input type="text" class="text" onclick="popUpCalendar(this, this)" name="${sheetPageName}becomeTime" alt="allowBlank:false" readonly="readonly" id="${sheetPageName}becomeTime" value="${eoms:date2String(sheetMain.becomeTime)}" alt="timer:true"/> 
        </td>
        <td class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.abateTime"/>*</td>
		   	<td>
		   		<input type="text" class="text" onclick="popUpCalendar(this, this)" name="${sheetPageName}abateTime" alt="allowBlank:false" readonly="readonly" id="${sheetPageName}abateTime" value="${eoms:date2String(sheetMain.abateTime)}" alt="timer:true"/> 
		    </td>
		  </tr>
		  <tr>
		     <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.system"/>*</td>
		     <td  colspan="3"> 
		     	<input type="text"  class="text" name="${sheetPageName}system" id="${sheetPageName}system"  alt="allowBlank:false" value="${sheetMain.system}"/>
		     </td>
		   </tr>
			 <tr>
		     <td  class="label"><bean:message bundle="accountpopedomapply" key="accountpopedomapply.applyExplain"/>*</td>
		     <td colspan="3"> 	
    				<textarea class="textarea max" name="${sheetPageName}applyExplain" id="${sheetPageName}applyExplain" alt="width:500,allowBlank:false">${sheetMain.applyExplain}</textarea>
         </td>
		   </tr>
		    <tr>
		           <td class="label">
		    	    <bean:message bundle="sheet" key="mainForm.accessories"/>
			       </td>	
			       <td colspan="3">					
		            <eoms:attachment name="sheetMain" property="sheetAccessories" 
		               scope="request" idField="${sheetPageName}sheetAccessories" appCode="accountpopedomapply" alt="allowBlank:true"/> 				
		           </td>
		        </tr>
        </c:if>	
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
	     	 <span id="roleName">:<bean:message bundle="accountpopedomapply" key="accountpopedomapply.role.safetyPerformer"/>	
	     	 </span>
	      </legend>
	       <eoms:chooser id="test"  type="role" roleId="337" flowId="074" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
		       data="${sendUserAndRoles}"/>
	</fieldset>
	</c:if>