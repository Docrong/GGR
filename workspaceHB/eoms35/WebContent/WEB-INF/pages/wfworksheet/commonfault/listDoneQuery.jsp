<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html:form action="/${module}.do?method=performListQuery" styleId="queryListForm">
<!-- 公共隐藏域的值 -->
<input type="hidden" name="findForward" value="${findForward}"/>
<!-- 已处理动作 -->
<input type="hidden" name="doneType" value="senddone"/>
<table width="100%">
  <tr>
	    <td width="60">
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
	    	   <input type="hidden" name="main.sheetCompleteLimit"/>
	           <input type="hidden" id="sheetCompleteLimitStartDateExpression" name="sheetCompleteLimitStartDateExpression" value=">="/>
	           <input type="text" name="sheetCompleteLimitStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="sheetCompleteLimitLogicExpression" value="and"/>
	           到
	           <input type="hidden" id="sheetCompleteLimitEndDateExpression" name="sheetCompleteLimitEndDateExpression" value="<="> 
	           <input type="text" name="sheetCompleteLimitEndDate" id="sheetCompleteLimitEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />
	             	
	    </td>
  </tr>
  
  
  <tr>
	    <td>
	    	   <bean:message bundle="sheet" key="query.status.area"/>:
	    
	    </td>
	    <td>
	    		<input type="hidden" name="toDeptIdStringExpression" value="in"/>  
               	<input type="text" class="text"  readonly="readonly" name="showArea" id="showArea" beanId="tawSystemAreaDao"/>
               	<input type="hidden" name="main.toDeptId" id="toAreaId"/> 
	
	    </td> 
		<td>
                <bean:message bundle="sheet" key="mainForm.status" />
        </td>
        <td>
        	<input type="hidden" name="main.status"/> 
            <select name="statusChoiceExpression" class="select">
            	<option value=""></option>
                <option value="0"><bean:message bundle="sheet" key="query.status.running" /></option> 
                <option value="1"><bean:message bundle="sheet" key="query.status.hold" /></option> 
                <option value="-1"><bean:message bundle="sheet" key="query.status.cancel" /></option>
                <option value="-14"><bean:message bundle="sheet" key="query.status.forceHold" /></option>
                <option value="-13"><bean:message bundle="sheet" key="query.status.forceCancel" /></option>
                <option value="-12"><bean:message bundle="sheet" key="query.status.blankOut"/></option>    
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
			    <eoms:comboBox name="mainNetSortThreeChoiceExpression"  id="mainNetSortThree"/>
    	</td>
  </tr>
  <tr>
  		<td colspan="4">
             	<input type="submit" value="查询" class="submit"/>
             	<input type="reset"  value="重置" class="button"/>
         </td>
 </tr>
</table>
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