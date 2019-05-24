<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<html:form action="/ordersheets.do?method=showListQuery" styleId="queryListForm">
<input type="hidden" name="findForward" value="${findForward}"/>
<table class="formTable">
   <tr>
      <td  class="label">生成时间:</td>
        <td width="100%" colspan="3">   	           	 	
	    	    <input type="hidden" name="ordersheet.creatTime"/>
	           <input type="hidden" id="creatTimeStartDateExpression" name="creatTimeStartDateExpression" value=""/>
	           <input type="text" name="creatTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="creatTimeLogicExpression" value="and"/>
	           到
	           <input type="hidden" id="creatTimeEndDateExpression" name="creatTimeEndDateExpression" value=""> 
	           <input type="text" name="creatTimeEndDate" id="creatTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />	             	
	    </td> 
   </tr>
   <tr>
      <td  class="label">完成期限:</td>
        <td width="100%" colspan="3">   	           	 	
	    	    <input type="hidden" name="ordersheet.completeLimit"/>
	           <input type="hidden" id="completeLimitStartDateExpression" name="completeLimitStartDateExpression" value=""/>
	           <input type="text" name="completeLimitStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="completeLimitLogicExpression" value="and"/>
	           到
	           <input type="hidden" id="completeLimitEndDateExpression" name="completeLimitEndDateExpression" value=""> 
	           <input type="text" name="completeLimitEndDate" id="completeLimitEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />	             	
	    </td> 
   </tr>
   <tr>
      <td  class="label">变更时间:</td>
        <td width="100%" colspan="3">   	           	 	
	    	    <input type="hidden" name="ordersheet.changeTime"/>
	           <input type="hidden" id="changeTimeStartDateExpression" name="changeTimeStartDateExpression" value=""/>
	           <input type="text" name="changeTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="changeTimeLogicExpression" value="and"/>
	           到
	           <input type="hidden" id="changeTimeEndDateExpression" name="changeTimeEndDateExpression" value=""> 
	           <input type="text" name="changeTimeEndDate" id="changeTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />	             	
	    </td> 
   </tr>
   <tr>
      <td  class="label">竣工时间:</td>
        <td width="100%" colspan="3">   	           	 	
	    	    <input type="hidden" name="ordersheet.endTime"/>
	           <input type="hidden" id="endTimeStartDateExpression" name="endTimeStartDateExpression" value=""/>
	           <input type="text" name="endTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="endTimeLogicExpression" value="and"/>
	           到
	           <input type="hidden" id="endTimeEndDateExpression" name="endTimeEndDateExpression" value=""> 
	           <input type="text" name="endTimeEndDate" id="endTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />	             	
	    </td> 
   </tr>
   <tr>
      <td  class="label">撤销时间:</td>
        <td width="100%" colspan="3">   	           	 	
	    	    <input type="hidden" name="ordersheet.cancelTime"/>
	           <input type="hidden" id="cancelTimeStartDateExpression" name="cancelTimeStartDateExpression" value=""/>
	           <input type="text" name="cancelTimeStartDate" onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text" />
	           <input type="hidden" name="cancelTimeLogicExpression" value="and"/>
	           到
	           <input type="hidden" id="cancelTimeEndDateExpression" name="cancelTimeEndDateExpression" value=""> 
	           <input type="text" name="cancelTimeEndDate" id="cancelTimeEndDate" onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""   readonly="true" class="text" />	             	
	    </td> 
   </tr>
   </table>
   <br>
   <table class="formTable">
      <tr>	   
	    <td class="label">定单编号:</td>
	    <td class="content"> 
	          <input type="hidden" name="orderNumberStringExpression" value="like"/>
		      <input type="text" name="orderNumber" id="orderNumber" class="text"/>		      
	    </td>
	    <td class="label">定单类型:</td>
	     <td class="content"> 
		      <input type="hidden" name="ordersheet.orderType" id="orderType" class="text"/>
		      <eoms:comboBox name="orderTypeChoiceExpression"  id="orderTypeChoiceExpression" initDicId="10401"/>
	    </td> 
    </tr>
    <tr>
	    <td class="label">定单业务类型:</td>
	     <td class="content"> 
		      <input type="hidden" name="ordersheet.orderBuisnessType" id="orderBuisnessType" class="text"/>
		      <eoms:comboBox name="orderBuisnessTypeChoiceExpression"  id="orderBuisnessTypeChoiceExpression" initDicId="1012301"/>
	    </td>     
	    <td class="label">紧急程度:</td>
	    <td class="content"> 
		      <input type="hidden" name="ordersheet.urgentDegree" id="urgentDegree" class="text"/>
		      <eoms:comboBox name="urgentDegreeChoiceExpression"  id="urgentDegreeChoiceExpression" initDicId="1010102"/>
	    </td>
    </tr>
</table>
<br>
<table class="formTable">
   <tr>	   
	    <td class="label">集团编号:</td>
	    <td class="content"> 
	          <input type="hidden" name="customNoStringExpression" value="like"/>
		      <input type="text" name="customNo" id="customNo" class="text"/>		      
	    </td> 
	    <td class="label">集团名称:</td>
	    <td class="content"> 
	          <input type="hidden" name="customNameStringExpression" value="like"/>
		      <input type="text" name="customName" id="customName" class="text"/>		      
	    </td>
   </tr>
</table>
<br>
<table class="formTable">
   <tr>	   
	    <td class="label">产品名称:</td>
	    <td class="content"> 
	          <input type="hidden" name="productNameStringExpression" value="like"/>
		      <input type="text" name="productName" id="productName" class="text"/>		      
	    </td> 
	    <td class="label">产品类别:</td>
	    <td class="content"> 
		      <input type="hidden" name="ordersheet.productKind" id="productKind" class="text"/>
		      <eoms:comboBox name="productKindChoiceExpression"  id="productKindChoiceExpression" initDicId="1012301"/>
	    </td>
   </tr>
   <tr>	   
	    <td class="label">产品编号:</td>
	    <td class="content"> 
	          <input type="hidden" name="productIDStringExpression" value="like"/>
		      <input type="text" name="productID" id="productID" class="text"/>		      
	    </td> 
	    <td class="label">产品序列号:</td>
	    <td class="content"> 
	          <input type="hidden" name="productSNStringExpression" value="like"/>
		      <input type="text" name="productSN" id="productSN" class="text"/>		      
	    </td>
   </tr>
</table>
<br>
<table class="formTable">
    <tr>	   
	    <td class="label">客户经理名称:</td>
	    <td class="content"> 
	          <input type="hidden" name="cmanagerPhoneStringExpression" value="like"/>
		      <input type="text" name="cmanagerPhone" id="cmanagerPhone" class="text"/>		      
	    </td> 
	    <td class="label">客户经理联系电话:</td>
	    <td class="content"> 
	          <input type="hidden" name="cmanagerContactPhoneStringExpression" value="like"/>
		      <input type="text" name="cmanagerContactPhone" id="cmanagerContactPhone" class="text"/>		      
	    </td>
   </tr>
   <tr>	   
	    <td class="label">集团客户联系人:</td>
	    <td class="content"> 
	          <input type="hidden" name="customContactStringExpression" value="like"/>
		      <input type="text" name="customContact" id="customContact" class="text"/>		      
	    </td> 
	    <td class="label">集团客户联系电话:</td>
	    <td class="content"> 
	          <input type="hidden" name="customContactPhoneStringExpression" value="like"/>
		      <input type="text" name="customContactPhone" id="customContactPhone" class="text"/>		      
	    </td>
   </tr>
   <tr>	   
	    <td class="label">集团业务联系人:</td>
	    <td class="content"> 
	          <input type="hidden" name="groupDealContactStringExpression" value="like"/>
		      <input type="text" name="groupDealContact" id="groupDealContact" class="text"/>		      
	    </td> 
	    <td class="label">集团业务联系人电话:</td>
	    <td class="content"> 
	          <input type="hidden" name="groupDealContactPhoneStringExpression" value="like"/>
		      <input type="text" name="groupDealContactPhone" id="groupDealContactPhone" class="text"/>		      
	    </td>
   </tr>
   <tr>	   
	    <td class="label">集团技术联系人:</td>
	    <td class="content"> 
	          <input type="hidden" name="groupDealContactStringExpression" value="like"/>
		      <input type="text" name="groupDealContact" id="groupDealContact" class="text"/>		      
	    </td> 
	    <td class="label">集团技术联系人电话:</td>
	    <td class="content"> 
	          <input type="hidden" name="groupDealContactPhoneStringExpression" value="like"/>
		      <input type="text" name="groupDealContactPhone" id="groupDealContactPhone" class="text"/>		      
	    </td>
   </tr>
   </table>
  <tr>
  		<td colspan="4">
             	<input type="submit" value="查询" class="submit"/>
             	<input type="reset"  value="重置" class="button"/>
         </td>
 </tr>
</table>
</html:form>