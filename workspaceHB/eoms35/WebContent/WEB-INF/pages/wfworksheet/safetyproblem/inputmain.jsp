<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
long localTimes=com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
String userId = sessionform.getUserid();
 %>
<%@ include file="/WEB-INF/pages/wfworksheet/safetyproblem/baseinputmainhtmlnew.jsp"%>
 <script type="text/javascript">
     	selectLimit();
	function selectLimit(){
    Ext.Ajax.request({
		method:"get",
		url: "safetyproblem.do?method=newShowLimit&flowName=SafetyProblemMainFlowProcess",
		success: function(x){
        	var o = eoms.JSONDecode(x.responseText);
        	if((o.acceptLimit == null || o.acceptLimit == "")&&(o.replyLimit == null || o.replyLimit == "")){
        	   // $("${sheetPageName}sheetAcceptLimit").value = "";
        	   // $('${sheetPageName}sheetCompleteLimit').value = "";
           	}else{
           	    var times=<%=localTimes%>;
	        	var dt1 = new Date().add(Date.MINUTE,parseInt(o.acceptLimit,10));
	        	var dt2 = dt1.add(Date.MINUTE,parseInt(o.replyLimit,10));
	           	$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
	          	$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
        	}
 		}
    });
   }
   </script>
	<input type="hidden" name="${sheetPageName}processTemplateName" value="SafetyProblemMainFlowProcess" />
	<input type="hidden" name="${sheetPageName}sheetTemplateName" value="SafetyProblemMainFlowProcess" />
	<input type="hidden" name="${sheetPageName}ifAgent" value="1" />
	<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet" />
	<c:if test="${status!=1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask" />
       <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${sheetPageName}operateType" />
    </c:if>
    <c:if test="${status==1}">
	   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverHumTask" />
    </c:if>
    <input type="hidden" name="${sheetPageName}beanId" value="iSafetyProblemMainManager"/> 
    <input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.safetyproblem.model.SafetyProblemMain"/>	
    <input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.safetyproblem.model.SafetyProblemLink"/>
    <br>
	<!-- sheet info --> 
     <table class="formTable">
					 <tr>
					   <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly" 
							id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" 
							onclick="popUpCalendar(this, this)"
							alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>   
					  </td>
					  
					  <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
					  <td >
					    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly" 
							id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}" 
							onclick="popUpCalendar(this, this)"
							alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>   
					  </td>
					</tr>
	                <tr>
		               <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.mainNetSort1"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSort1" id="${sheetPageName}mainNetSort1" 
            	      initDicId="1010104" sub="${sheetPageName}mainNetSort2" alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort1}" styleClass="select-class"/>
			        </td>	                
		               <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.mainNetSort2"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSort2" id="${sheetPageName}mainNetSort2" 
            	      initDicId="${sheetMain.mainNetSort1}" sub="${sheetPageName}mainNetSort3" alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort2}" styleClass="select-class"/>
			        </td>	                
		            </tr>

	                <tr>
		                 <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.mainNetSort3"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainNetSort3" id="${sheetPageName}mainNetSort3" 
            	      initDicId="${sheetMain.mainNetSort2}"  alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort3}" styleClass="select-class"/>
			        </td>	                
		               <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.mainTaskType"/>*</td>
		                <td class="content">  
				        <eoms:comboBox name="${sheetPageName}mainTaskType" id="${sheetPageName}mainTaskType" 
            	      initDicId="1010102"  alt="allowBlank:false" defaultValue="${sheetMain.mainTaskType}" styleClass="select-class"/>
			        </td>	                
		            </tr>

			      <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.mainTaskDescription"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainTaskDescription" id="${sheetPageName}mainTaskDescription" 
    				  alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入任务描述，多输入100汉字')}'">${sheetMain.mainTaskDescription}</textarea>
                    </td>
		          </tr>

<!-- deleted in B4 <tr>
		            <td  class="label"><bean:message bundle="safetyproblem" key="safetyproblem.mainRemark"/>*</td>
		              <td colspan="3" class="content"> 	
    				  <textarea class="textarea max" name="${sheetPageName}mainRemark" id="${sheetPageName}mainRemark" 
    				  alt="allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('最多输入1000汉字')}'">${sheetMain.mainRemark}</textarea>
                    </td>
		          </tr>-->
	<%-- accessories --%>
	
	

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

		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="${sheetPageName}sheetAccessories" appCode="safetyproblem" /> 				
		    </td>
		  </tr>				          

	</table>

	
	<br/>
	<c:if test="${status!=1}">
	<fieldset>
	 <legend>
	     	 <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
			 <span id="roleName">:${eoms:a2u('任务创建审批人')}
			 </span>
<%
if("lijinkai".equals(userId)||"zhumin".equals(userId)||"wanqinghong".equals(userId)||"xiaoxiaoyue".equals(userId)||"zuguomin".equals(userId)||"chenxu1".equals(userId)||"suhang".equals(userId)||"liufang".equals(userId)||"xuhaibo".equals(userId)||"guokai1".equals(userId)||"zhangxiong".equals(userId)||"xulei".equals(userId)||"liyanghong".equals(userId)||"liwei1".equals(userId)) {
%>
${eoms:a2u('(请选择派单至省集客部！)')}
<%
}
%>
	      </legend>
	        <div class="x-form-item" >
			<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'dealPerformer',childType:'user,dept',limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择任务执行人')}'},{id:'auditPerformer',childType:'user',text:'${eoms:a2u('审批')}'},
			   {id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"
        panels="[{text:'${eoms:a2u('部门与人员')}',dataUrl:'/xtree.do?method=userFromDept'},
                 {text:'${eoms:a2u('个性化部门树')}',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
			   data="${sendUserAndRoles}"
			   />
			   
			 </div>
	</fieldset>
	</c:if>

