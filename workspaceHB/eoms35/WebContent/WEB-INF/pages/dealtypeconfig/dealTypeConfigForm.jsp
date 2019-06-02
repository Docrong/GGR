<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript">
   	 
   var v;
   Ext.onReady(function(){
     v = new eoms.form.Validation({form:'dealTypeConfigForm'});
  });
 

 </script> 
<title><bean:message bundle="dealtypeconfigRes" key="dealTypeConfig.title"/></title>
<html:form action="dealtypeconfig.do" method="post" styleId="dealTypeConfigForm"> 
<table class="formTable">
	<caption><bean:message bundle="dealtypeconfigRes" key="dealTypeConfig.title"/></caption>

		<tr>
			<td class="label">
				<bean:message bundle="dealtypeconfigRes" key="dealTypeConfig.taskName"/>
			</td>
			<td>
			${dealtypeconfig.taskDisplayName}
			<input type="hidden" name="taskName" value="${dealtypeconfig.taskName}"/>
			<input type="hidden" name="taskDisplayName" value="${dealtypeconfig.taskDisplayName}"/>
			</td>
		</tr>
		<!-- 处理类型 -->
		<tr>
			<td class="label">
				<bean:message bundle="dealtypeconfigRes" key="dealTypeConfig.dealPerformerType"/>
			</td>
			<td>
              		 <select name="dealPerformerType" id="dealPerformerType">
              	 		<c:if test="${dealtypeconfig.dealPerformerType=='0' }">
              	 			<option value="">请选择</option>
              	 			<option value="0" selected="true">人员处理</option>
	               	 		<option value="1">角色处理</option>
	               	 	</c:if>
              	 		<c:if test="${dealtypeconfig.dealPerformerType=='1' }">
              	 			<option value="">请选择</option>
              	 			<option value="0">人员处理</option>
	               	 		<option value="1" selected="true">角色处理</option>
	               	 	</c:if>
              	 		<c:if test="${empty dealtypeconfig.dealPerformerType|| dealtypeconfig.dealPerformerType=='' }">
              	 			<option value="" selected="true">请选择</option>
              	 			<option value="0">人员处理</option>
	               	 		<option value="1">角色处理</option>
	               	 	</c:if>
	              	 </select>
			</td>
		</tr>
</table>
	<input type="hidden" name="flowName" value="${dealtypeconfig.flowName }"/>
	<input type="hidden" name="userId" value="${dealtypeconfig.userId }"/>
  	<input type="hidden" name="id" value="${dealtypeconfig.id }"/>
        <html:submit styleClass="button" property="method.save">
        	<bean:message bundle='sheet' key='button.save'/>
        </html:submit>
        <html:submit styleClass="button" property="method.delete">
            <bean:message bundle='sheet' key='button.delete'/>
        </html:submit>
        <html:cancel styleClass="button" onclick="v.passing=true">
        	<bean:message bundle='sheet' key='button.back'/>
        </html:cancel>
 </html:form>
<%@ include file="/common/footer_eoms.jsp"%>