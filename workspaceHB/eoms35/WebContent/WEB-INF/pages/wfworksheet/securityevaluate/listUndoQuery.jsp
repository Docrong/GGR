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
	    	   <bean:message bundle="sheet" key="mainForm.toDeptId"/>:
	    
	    </td>
	    <td>
	    		<input type="hidden" name="toDeptIdStringExpression" value="in"/>  
               	<input type="text" class="text"  readonly="readonly" name="showArea" id="showArea" beanId="tawSystemAreaDao"/>
               	<input type="hidden" name="main.toDeptId" id="toAreaId"/> 
	
	    </td>
	    <td>
	    		<bean:message bundle="sheet" key="query.status.stepId"/>:	           	 	
	    	
	    </td>
	    <td>
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
    			<bean:message bundle="sheet" key="querylist.netType"/>:
    	</td>
    	<td colspan="3">
    			<input type="hidden" name="titleStringExpression" value="like"/>
				<input type="hidden" name="main.mainNetSortOne" /> 
				<eoms:comboBox name="mainNetSortOneChoiceExpression" id="mainNetSortOne" sub="mainNetSortTwo" initDicId="1010104"/>
				<input type="hidden" name="main.mainNetSortTwo" />
				<eoms:comboBox name="mainNetSortTwoChoiceExpression" id="mainNetSortTwo"  sub="mainNetSortThree"/>
				<input type="hidden" name="main.mainNetSortThree"/>  
			    <eoms:comboBox name="mainNetSortChoiceExpression"  id="mainNetSortThree"/>

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
 Ext.onReady(function(){
    //地域
    var	areatreeAction='${app}/xtree.do?method=areaTree';
    deptetree = new xbox({
      btnId:'showArea',
      treeDataUrl:areatreeAction,treeRootId:'-1',treeRootText:'地市',treeChkMode:'',treeChkType:'area',
      showChkFldId:'showArea',saveChkFldId:'toAreaId'
    });
});
</script>