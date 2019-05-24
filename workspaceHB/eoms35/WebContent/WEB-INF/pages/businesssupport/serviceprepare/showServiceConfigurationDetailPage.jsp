<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
  function removeObject() {
   if (confirm('确定要删除吗')) {
		var thisform = document.forms[0];
		thisform.action = "${app}/businesssupport/serviceprepare/serviceprepare.do?method=deleteServiceConfiguration&type=delete";
		thisform.submit();
	}
 }
</script>
<c:if test="${type=='show' }">
<html:form action="/serviceprepare.do?" styleId="theform">
	<input type="hidden" name="id" id="id" value="${tmpMap.serviceConfiguration_id}" />
	<table class="formTable"> 
		  <caption><center>该服务详细信息</center></caption>
		  <tr>
	  		<td class="label">服务名称</td>
			<td class="content">
				<bean:write name="tmpMap" property="professionalServiceDirectory_name" scope="request"/>
			</td>
	  		<td class="label">环节名称</td>
			<td class="content">
				<bean:write name="tmpMap" property="taskLinks_chName" scope="request"/>
			</td>
		  </tr>	    
		  <tr>
	  		<td class="label">流程名称</td>
			<td class="content">
				<bean:write name="tmpMap" property="processType_name" scope="request"/>
			</td>
	  		<td class="label">服务规格名称</td>
			<td class="content" >
				<bean:write name="tmpMap" property="productSpecification_name" scope="request"/>
			</td>
		  </tr>		
		  <tr>
	  		<td class="label">是否必做任务</td>
			<td class="content" colspan="3">
				<c:if test="${tmpMap.serviceConfiguration_isNeed=='0' }">
				是
				</c:if>
				<c:if test="${tmpMap.serviceConfiguration_isNeed=='1' }">
				否
				</c:if>	
			</td>
		  </tr>		
		  <tr>
	  		<td class="label">备注</td>
			<td class="content" colspan="3">
				<bean:write name="tmpMap" property="serviceConfiguration_remark" scope="request"/>
			</td>
		  </tr>		      
	</table>
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeObject()">删除        
	</html:button>
	<html:button styleClass="btn" property="method.back" styleId="method.back" onclick="history.back(-1)">返回
	</html:button>	
	</html:form>
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>