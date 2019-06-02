<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<style type="text/css">
.x-form-column{width:320px}
</style>
<script type="text/javascript">
Ext.onReady(function(){
 v = new eoms.form.Validation({form:'theform'});
});
function selectStep(statusChoice){
	var stepId = document.getElementById("stepId");
	if (statusChoice.value == "0")
		stepId.disabled = false;
	else {
		stepId.selectedIndex=0;
		stepId.disabled = true;
		
	}
}
</script>

<html:form action="/complaint.do?method=performQueryHide" styleId="theform">	
<table class="formTable">
			<!-- 是否查询已隐藏工单 -->
           <tr>
              <td class="label">
                  查询类型
              </td>
              <td>
                  <select name="ifQueryByHided">
                      <option value="0" selected>未隐藏工单</option>
                      <option value="1">已隐藏工单</option>
   	             </select>
              </td>
           </tr>
			<!-- 是否查询超时工单 -->
           <tr>
              <td class="label">
                  是否查询超时工单
              </td>
              <td>
                  <select name="ifQueryByOvertime" onchange="selectStep(this)">
                      <option value="0" selected>是</option>
                      <option value="1">否</option>
   	             </select>
              </td>
           </tr>
           <!-- 工单处理环节 -->
           <tr>	
         	   <td class="label">
               		超时步骤
               </td>
          	   <td>
          	   		<%String holdstep = ""; %>
              		 <select name="stepId" id="stepId">
	               	 	 <option value=""></option>
	               	 	<logic:iterate name="stepIdList" id="stepId" >
	              	 		<% if(stepId.toString().indexOf("Hold")!=-1){
	              	 			holdstep = stepId.toString();
	              	 			%>
	              	 			<option value="${stepId}">待归档</option>
	              	 			<%
	              	 		}else{
	              	 		%>
	              	 			<option value="${stepId}"> ${phaseIdMap[stepId]}</option>
	              	 		<%} %>
	              	 	</logic:iterate> 
	              	 	<option value="<%=holdstep%>status1">已归档</option>
	              	 </select>
	            </td>
			</tr>
        <!-- 派单时间 -->
          <tr>
	            <td class="label">
	              	<bean:message bundle="sheet" key="query.sendTime" />
	            </td>
	            <td width="100%">
	                <bean:message bundle="sheet" key="worksheet.query.startDate" />
	                <input type="text" name="sendTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" value="${startDate}"/> &nbsp;&nbsp;
	                <bean:message bundle="sheet" key="worksheet.query.endDate" />
	                <input type="text" name="sendTimeEndDate" id="sendTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="" value="${endDate}"   readonly="true" class="text"/></div>
	            </td>
          </tr>
 </table>
	<!-- buttons -->
	<div class="x-form-item">
	  <div class="form-btns">
		<html:submit styleClass="btn" property="method.save"  styleId="submitone">
		 <fmt:message key="button.query" />
		</html:submit>
	  </div>
    <br/>	
	</div>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>