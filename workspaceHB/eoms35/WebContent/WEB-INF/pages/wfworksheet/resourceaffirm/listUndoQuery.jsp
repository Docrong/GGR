<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html:form action="/${module}.do?method=performListQuery" styleId="queryListForm">
<!-- 公共隐藏域的值 -->
<input type="hidden" name="findForward" value="${findForward}"/>
<!-- 未处理动作 -->
<input type="hidden" name="doneType" value="sendundo"/>
<table width="100%">
  <tr>
	    <td width="100px">
	    	  <bean:message bundle="sheet" key="mainForm.title" />:
	    </td>
	    <td width="170px">
		      <input type="text" name="main.title" id="title" size="30" class="text"/>
		      <input type="hidden" name="titleStringExpression" value="like"/>
	    </td>
	    <td>
	          <bean:message bundle="sheet" key="querylist.completeLimit"/>:
	     
	    </td>
	    <td>   	           	 	
	    	   <input type="hidden" name="task.completeTimeLimit"/>
	           <input type="hidden" id="completeTimeLimitStartDateExpression" name="completeTimeLimitStartDateExpression" value=">="/>
	           <input type="text" name="completeTimeLimitStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="completeTimeLimitLogicExpression" value="and"/>
	           to
	           <input type="hidden" id="completeTimeLimitEndDateExpression" name="completeTimeLimitEndDateExpression" value="<="> 
	           <input type="text" name="completeTimeLimitEndDate" id="completeTimeLimitEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />
	            	
	    </td>
  </tr>
  
  
  <tr>

	    <td>
	    		<bean:message bundle="sheet" key="query.status.stepId"/>:	           	 	
	    	
	    </td>
	    <td colspan="3">
   				<input type="hidden" name="task.taskName"/> 
             	<select name="taskNameChoiceExpression"  id="stepId">
               	 	<option value=""></option>
               	 	<logic:iterate name="stepIdList" id="stepId" >
              	 		<option value="${stepId}"> ${phaseIdMap[stepId]}</option>
              	 	</logic:iterate> 
              	 </select>
		 </td>
  </tr>
  <tr>
  		<td>
             	<input type="submit" value="查询" class="submit"/>
             	<input type="reset"  value="重置" class="button"/>
         </td>
 </tr>
</table>
<br/>
</html:form>
<script>
</script>