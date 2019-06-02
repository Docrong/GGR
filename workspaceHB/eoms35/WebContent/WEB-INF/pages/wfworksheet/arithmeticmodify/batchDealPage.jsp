<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<div id="sheetform">
<html:form action="/arithmeticmodify.do?method=performBatchDeal" styleId="theform">
<h3 class="sheet-title">
</h3>
<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
  	<logic:present name="sheetMain" scope="request">
	<%@ include file="/WEB-INF/pages/wfworksheet/arithmeticmodify/basedetailnew.jsp"%>
	<br/>
	<table class="formTable"> 
	  <caption></caption>
		  	
	               <tr>
		           		<td class="label"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.mainNetSort1"/></td>
		            	<td class="content">  
		            		<eoms:id2nameDB id="${sheetMain.mainNetSort1}" beanId="ItawSystemDictTypeDao"/>
			        	</td>	   
		            	<td class="label"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.mainNetSort2"/></td>
		                <td class="content">  
		                	<eoms:id2nameDB id="${sheetMain.mainNetSort2}" beanId="ItawSystemDictTypeDao"/>
			        	</td>			        	             
		           </tr>

	                <tr>
		                <td class="label"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.mainNetSort3"/></td>
		                <td class="content">  
		                	<eoms:id2nameDB id="${sheetMain.mainNetSort3}" beanId="ItawSystemDictTypeDao"/>
			        	</td>	 
		                <td  class="label"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.mainTaskType"/></td>
		                <td class="content">  
		                	<eoms:id2nameDB id="${sheetMain.mainTaskType}" beanId="ItawSystemDictTypeDao"/>
			        	</td>			        	               
		            </tr>

			      <tr>
		            <td class="label"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.mainTaskDescription"/></td>
		            <td class="content" colspan="3"> 	
		              	<bean:write name="sheetMain" property="mainTaskDescription" scope="request"/>
                   	</td>
		          </tr>
				<tr>
					<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
				    <td colspan='3'>
				    <eoms:attachment name="sheetMain" property="sheetAccessories" 
				            scope="request" idField="sheetAccessories" appCode="arithmeticmodify" 
				             viewFlag="Y"/> 
				    </td>
				</tr>
	</table>
    </logic:present>
  </div>
  <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<!-- 任务列表及上一级处理的信息 -->
  <!-- Sheet Detail Tab Start -->
  <div id="sheetinfo">
  	<logic:present name="tasks" scope="request">
  	  
	<table class="listTable" width="100%" cellpadding="0" cellspacing="0"> 
	<caption>任务列表</caption>
			<tr>
				           		<td class="label" ><input type="checkbox" onclick="selectTask(this)"></td>	
					        	<td class="label" nowrap="nowrap" width="100%">上一级处理部门</td>	
					        	<td class="label" nowrap="nowrap" width="100%">上一级处理人员</td>
					        	<td class="label" nowrap="nowrap" width="100%">上一级完成情况</td>					        	             
			</tr>
	
  		<logic:iterate name="tasks" id="task" >
              <tr>
              	<td><input type="checkbox" name="ids" value="${task.id}" toOrgRoleId="${task.id}|${task.preDealUserId}" toSelfRoleId="${task.id}|${task.operateRoleId}"></td>
	        	<td>  
            		<eoms:id2nameDB id="${task.preDealDept}" beanId="tawSystemDeptDao"/>
	        	</td>	   
            	
                <td>  
                	<eoms:id2nameDB id="${task.preDealUserId}" beanId="tawSystemUserDao"/>
	        	</td>
	        	<td class="content">
	        		${prelinkMap[task.preLinkId].linkTaskComplete}
	        	</td>			        	             
           </tr>
         </logic:iterate>
	</table>
    </logic:present>
  </div>

<!-- 批量处理页面 -->
  <div id="sheetinfo">
	<table class="formTable"> 
	  <caption>处理页面</caption>
		  	
	               <tr>
		           		<td class="label">处理操作</td>
		            	<td class="content">  
		            		<select id="dealSelector" onchange="checkValue(this)" alt="allowBlank:false,vtext:'请选择处理操作'">
		            			 <option value=""></option>
		            			 <option value="Receive"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.dealrejectpass"/></option>
			 					 <option value="ExcuteHumTask"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.nopassback"/></option>
			 				</select> 
			        	</td>	   
		            	<td class="label"></td>
		                <td class="content">
		                	 <input type="hidden" name="operateUserId" value="${operateUserId}"/>
					         <input type="hidden" name="operateDeptId" value="${operateDetpId}"/>
					         <input type="hidden" name="operaterContact" value="${contactMobile}"/>
					         <input type="hidden" name="mainId" value="${mainId}"/>
					         <input type="hidden" id="toBatchOrgRoleId" name="toBatchOrgRoleId" /> 
		                	 <input type="hidden" id="phaseId" name="phaseId"/>
		                	 <input type="hidden" id="activeTemplateId" name="activeTemplateId"/>
	                		 <input type="hidden" id="taskIds" name="taskIds">
		                	 <input type="hidden" name="hasNextTaskFlag"  value="true"/>
		                	 <input type="hidden" name="operateType"  id="operateType"/>
		                	 <input type="hidden" name="beanId" value="iArithmeticModifyMainManager"/> 
					         <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain"/>	
					         <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink"/>
					         <input type="hidden" name="methodBeanId"  id="methodBeanId" value="${methodBeanId}"/>
					         <input type="hidden" name="piid" value="${piid}"/>  
			        	</td>			        	             
		           </tr>
	               <tr>
			            <td  class="label"><bean:message bundle="arithmeticmodify" key="arithmeticmodify.advice"/>*</td>
			              <td colspan="3"> 	
	    				  <textarea class="textarea max" name="linkTaskComplete" id="linkTaskComplete"  alt="allowBlank:false,width:500,maxLength:1000,vtext:'请填入备注，最多输入1000个字符'"></textarea>
	                    </td>
		          </tr>
	</table>
	<br/>
	<input type="submit" value="提交" class="btn"  onclick="onclicksubmit();">
  </div>
</html:form>
</div>


<script>
	Ext.onReady(function() {
		var v = new eoms.form.Validation({form:'theform'});
	});
	
 	 
	function checkValue(obj) {
		var phaseObj = document.getElementById("phaseId");
		phaseObj.value = obj.value;
	}
	function onclicksubmit(){
		var phaseObj = document.getElementById("phaseId");
		var operateTypeObj = document.getElementById("operateType");
		var linkTaskCompleteObj = document.getElementById("linkTaskComplete");
		var activeTemplateIdObj = document.getElementById("activeTemplateId");
		if (phaseObj.value == "Receive") {
			operateTypeObj.value = "211";
			activeTemplateIdObj.value="211";
		} else {
			operateTypeObj.value = "212";
			activeTemplateIdObj.value="212";
		}
		
		var taskIds="";
		var taskIdsAndPreOrgId = "";
		var taskIdsObj = document.getElementsByName("ids");
		for (var i = 0; i < taskIdsObj.length; i ++) {
			if (taskIdsObj[i].checked == true) {
				var toOrygRoleId ="";
				if (operateTypeObj.value == "212") {
					toOrygRoleId = taskIdsObj[i].getAttribute("toOrgRoleId");
				} else {
					toOrygRoleId = taskIdsObj[i].getAttribute("toSelfRoleId");
				}
				if (taskIds.length > 0) {
					taskIds = taskIds + "," ;
					taskIdsAndPreOrgId = taskIdsAndPreOrgId + ",";
				}
				taskIds = taskIds + taskIdsObj[i].value;
				taskIdsAndPreOrgId = taskIdsAndPreOrgId + toOrygRoleId;
			}
		}
		var taskObj = document.getElementById("taskIds");		
		var toOrgRoleIdObj = document.getElementById("toBatchOrgRoleId");
		toOrgRoleIdObj.value = taskIdsAndPreOrgId;
		taskObj.value = taskIds;
		if (taskIds == "") {
			alert("请选择任务工单进行批量处理！");
			return false;
		}	
	}
	
	function selectTask(allObj) {
		var taskIdsObj = document.getElementsByName("ids");
		if (allObj.checked == true) {
			for (var i = 0; i < taskIdsObj.length; i ++) {
				if (taskIdsObj[i].checked == false) {
					taskIdsObj[i].checked = true;
				}
			}
		} else {
			for (var i = 0; i < taskIdsObj.length; i ++) {
				if (taskIdsObj[i].checked == true) {
					taskIdsObj[i].checked = false;
				}
			}
		}
	}
</script>
<%@ include file="/common/footer_eoms.jsp"%>
