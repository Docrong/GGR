<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<html:form action="/ipspeciallines.do?method=showListQuery" styleId="queryListForm">
<input type="hidden" name="findForward" value="${findForward}"/>
   <table class="formTable">
      <tr>	   
	    <td class="label">客户名称:</td>
	    <td class="content"> 
	          <input type="hidden" name="portANetDeptUserStringExpression" value="like"/>
		      <input type="text" name="portANetDeptUser" id="portANetDeptUser" class="text"/>		      
	    </td>
	    <td class="label">专业类型:</td>
	     <td class="content"> 
		      <input type="hidden" name="ordersheet.specialLineType" id="specialLineType" class="text"/>
		      <eoms:comboBox name="specialLineTypeChoiceExpression"  id="specialLineTypeChoiceExpression" initDicId="1010504"/>
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