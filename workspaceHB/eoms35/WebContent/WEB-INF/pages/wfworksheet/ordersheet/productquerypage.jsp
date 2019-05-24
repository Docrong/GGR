<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<html:form action="/ordersheets.do?method=showProductsListQuery" styleId="queryListForm">
<input type="hidden" name="findForward" value="${findForward}"/> 
   <table class="formTable">
      <tr>	   
	    <td class="label">客户名称</td>
	    <td class="content"> 
		      <input type="text" name="customName" id="customName" class="text" alt="allowBlank:true,maxLength:50"/>		      
	    </td>
	    <td class="label">集团级别</td>
	     <td class="content"> 
		      <eoms:comboBox name="customLevel"  id="customLevel" alt="allowBlank:true" initDicId="1010107"/>
	    </td>     
    </tr>
    <tr>	   
	    <td class="label">集团编码</td>
	    <td class="content"> 
		      <input type="text" name="customNo" id="customNo" class="text" alt="allowBlank:true,maxLength:50"/>		      
	    </td>
	    <td class="label">专业类型</td>
	     <td class="content"> 
		      <eoms:comboBox name="orderBuisnessType"  id="orderBuisnessType" alt="allowBlank:true" initDicId="1012301"/>
	    </td>     
    </tr>
</table>
<br>

  <tr>
  		<td colspan="4">
             	<input type="submit" value="查询" class="submit"/>
             	<input type="reset"  value="重置" class="button"/>
         </td>
 </tr>

</html:form>
<script type="text/javascript">
   var v = new eoms.form.Validation({form:'queryListForm'});
</script>
