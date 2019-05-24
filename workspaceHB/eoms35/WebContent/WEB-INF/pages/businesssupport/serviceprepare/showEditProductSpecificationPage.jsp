<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
Ext.onReady(function(){	
  v = new eoms.form.Validation({form:'theform'});
});
  function removeObject() {
   if (confirm('确定要删除吗')) {
		var thisform = document.forms[0];
		thisform.action = "${app}/businesssupport/serviceprepare/serviceprepare.do?method=editProductSpecification&type=delete";
		thisform.submit();
	}
 }
</script>
<html:form action="/serviceprepare.do?method=editProductSpecification" styleId="theform">
	<input type="hidden" name="id" id="id" value="${productSpecification.id}" />
	<input type="hidden" name="type" id="type" value="${type }" />    
	<table class="formTable"> 
		  <caption><center>产品规格表信息</center></caption>
          <tr>
              <td  class="label">服务规格名称*</td>
			  <td class="content">
                 <input type="text"  class="text" name="name" id="name"  value="${productSpecification.name}" alt="allowBlank:false,maxLength:25,vtext:'请填入服务规格名称，最多输入25字'"/>
			  </td>	  
              <td  class="label">编码*</td>
			  <td class="content">
                 <input type="text"  class="text" name="code" id="code"  value="${productSpecification.code}" alt="allowBlank:false,maxLength:25,vtext:'请填入编码，最多输入25字'"/>
			  </td>			          
          </tr>
		  <tr>
	          <td  class="label">服务规格说明*</td>
	          <td class="content" colspan="3"> 	
	  		      <textarea class="textarea max" name="comment" id="comment" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入服务规格说明，最多输入125字'">${productSpecification.comment}</textarea>
	          </td>
	      </tr>			      
	</table>
	<html:submit styleClass="btn" property="method.save"  styleId="method.save">
    <c:if test="${type=='add' }">
	提交    
    </c:if>
    <c:if test="${type=='modify' }">
	修改    
    </c:if>
	</html:submit>	
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeObject()">删除        
	</html:button>
	<html:button styleClass="btn" property="method.back" styleId="method.back" onclick="history.back(-1)">返回
	</html:button>	
	</html:form>
<%@ include file="/common/footer_eoms.jsp"%>