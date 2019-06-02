<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/dealpageJs.jsp"%>
<html:form action="/${module}.do?method=performDealNew" styleId="theform">
<!-- 处理环节基本信息表头部分内容 -->  
<%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/baseinputlinkhtmlnew.jsp"%>
<!-- 公共隐藏域 -->    
<%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/dealPageInputHidden.jsp"%>
 
<table class="formTable"> 
 
<!-- 抄送 --> 
<c:if test="${taskName == 'cc'}">
    	<tr>
	       	<td class="label">
	        	<bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">
				<input type="hidden" name="operateType" id="operateType" value="-15" />
	           	<textarea name="remark" class="textarea max" id="remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>
		<!-- 派单树 -->
		<fieldset id="link4">
				<eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
			   category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
		</fieldset>
		<tr>
			<td colspan="2">
				<input type="submit" class="submit"  value="<bean:message bundle='sheet' key='button.done'/>" />
			</td>
		</tr> 	
		  
</c:if>


<!-- 确认 -->
<c:if test="${operateType == '61'}">
		<input type="hidden" name="dealPerformer" id="dealPerformer" value="${fOperateroleid}" />
		<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="${ftaskOwner}" />
		<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="${fOperateroleidType}" />
		 <input type="hidden" name="beanId" id="beanId" value="${beanId }"/> 
            <input type="hidden" name="mainClassName"  id="mainClassName" value="${mainClassName}"/>	
            <input type="hidden" name="linkClassName" id="linkClassName" value="${linkClassName }"/>
		<input type="hidden" name="operateType" id="operateType" value="61" />
		<br/>
		<input type="submit" class="submit" onclick="this.form.action='${app}/sheet/${module}/${module}.do?method=newPerformClaimTask'"  value="<bean:message bundle='sheet' key='button.accept'/>" />	
</c:if>



<!-- 驳回 -->
<c:if test="${operateType == '4'}">

			<input type="hidden" name="beanId" id="beanId" value="${beanId }"/> 
            <input type="hidden" name="mainClassName"  id="mainClassName" value="${mainClassName}"/>	
            <input type="hidden" name="linkClassName" id="linkClassName" value="${linkClassName }"/>
		<br/>
    	<tr>
	       <td class="label">
	        	<bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="submit" class="submit" onclick="this.form.action='${app}/sheet/${module}/${module}.do?method=performDealNew'"  value="<bean:message bundle='sheet' key='button.done'/>" />
				<!-- 隐藏域 -->		
				<input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}" />
				<input type="hidden" name="operateType" id="operateType" value="4" />
			</td>
		</tr>
</c:if>	     
</table>
</html:form>

 