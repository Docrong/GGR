<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
 
<%
  request.setAttribute("roleId","342"); 
  String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
  long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
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
		url: "greatevent.do?method=newShowLimit&specialty1="+temp1+"&specialty2="+temp2+"&specialty3="+temp3+"&flowName=GreatEventProcess",
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
<%@ include file="/WEB-INF/pages/wfworksheet/greatevent/baseinputmainhtmlnew.jsp"%>
<% request.setAttribute("roleId","342");%>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="GreatEventProcess" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="GreatEventProcess" />
	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>" />
	<c:if test="${status==0}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask" />
	   <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${sheetPageName}operateType" />
    </c:if>
    <c:if test="${status==1}">
	    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iGreatEventMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.greatevent.model.GreatEventMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.greatevent.model.GreatEventLink"/>
	<!-- sheet info -->
	<br/>
     <table class="formTable">
        <c:if test="${status!=1}"> 
              <tr>
				  <td class="label"><bean:message bundle="greatevent" key="greatevent.sheetAcceptLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
						id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/> 
			  			  
				  </td>				
				  <td class="label"><bean:message bundle="greatevent" key="greatevent.sheetCompleteLimit"/>*</td>
				  <td class="content">
				    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
						id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>   
					
				  </td>		  
				</tr>	   
			   	<tr>
			  <td class="label"><bean:message bundle="greatevent" key="greatevent.mainNetSortOne"/>*</td>
			  <td class="content">
			  	 <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne" 
			  	      sub="${sheetPageName}mainNetSortTwo" initDicId="1010104" defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			  <td class="label"><bean:message bundle="greatevent" key="greatevent.mainNetSortTwo"/>*</td>
			  <td class="content">
			    <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo" 
			  	      sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}" defaultValue="${sheetMain.mainNetSortTwo}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>		  
			</tr>
					
		  <tr>
			  <td class="label"><bean:message bundle="greatevent" key="greatevent.mainNetSortThree"/>*</td>
			  <td class="content" colspan="3">
			  	 <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
			  	      initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}" alt="allowBlank:false" onchange="selectLimit(this.value);"/>
			  </td>				
			</tr>

	          <tr>
  			     <td class="label"><bean:message bundle="greatevent" key="greatevent.mainEventStartTime"/>*</td>
		   		 <td class="content">
		   				<input type="text" class="text" name="${sheetPageName}mainEventStartTime" readonly="readonly" 
						id="${sheetPageName}mainEventStartTime" value="${eoms:date2String(sheetMain.mainEventStartTime)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'lessThen',link:'${sheetPageName}mainEventEndTime',vtext:'事件开始时间不能晚于事件结束时间',allowBlank:false"/> 
		                </td>
  				 <td class="label"><bean:message bundle="greatevent" key="greatevent.mainEventEndTime"/>*</td>
		   		 <td class="content">
		   				<input type="text" class="text" name="${sheetPageName}mainEventEndTime" readonly="readonly" 
						id="${sheetPageName}mainEventEndTime" value="${eoms:date2String(sheetMain.mainEventEndTime)}" 
						onclick="popUpCalendar(this, this)" alt="vtype:'moreThen',link:'${sheetPageName}mainEventStartTime',vtext:'事件结束时间不能早于事件开始时间',allowBlank:false"/> 
		                </td>
		            </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.mainEventDesc"/></td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainEventDesc" id="${sheetPageName}mainEventDesc"  value="${sheetMain.mainEventDesc}" alt="allowBlank:true,maxLength:255,vtext:'请重新输入事件描述，最多输入125字'">${sheetMain.mainEventDesc}</textarea>
                    </td>
		          </tr>
                  <tr>	
			        <td  class="label"><bean:message bundle="greatevent" key="greatevent.mainInitDealResult"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainInitDealResult" id="${sheetPageName}mainInitDealResult" value="${sheetMain.mainInitDealResult}" alt="allowBlank:false,maxLength:255,vtext:'请重新输入初步处理结果，最多输入125字'">${sheetMain.mainInitDealResult}</textarea>
                    </td>		
			      </tr>
			      <tr>
		            <td  class="label"><bean:message bundle="greatevent" key="greatevent.mainEventDealAdvice"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainEventDealAdvice" id="${sheetPageName}mainEventDealAdvice"value="${sheetMain.mainEventDealAdvice}" alt="allowBlank:false,maxLength:255,vtext:'请重新输入事件处理建议，最多输入125字'">${sheetMain.mainEventDealAdvice}</textarea>
                    </td>
		          </tr>
		          <tr>
		    		<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>	
					<td colspan="3"><eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="businessbackout" /> 				
		    		</td>
		  		</tr>
  </c:if>	
	</table>
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
			 <bean:message bundle="greatevent" key="role.eventsecuritytaskgroup"/>
			 </span>
	      </legend>
	        <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'user,dept',limit:'none',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]" 
			   panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'},{text:'个性化部门树',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
			   data="${sendUserAndRoles}"/>	

	</fieldset>
	</c:if>
