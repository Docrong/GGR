<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<c:choose>
	<!-- 等于确认受理 -->
	<c:when test="${operateType == 61}">
		<input type="submit" class="submit" onclick="this.form.action='${app}/sheet/${module}/${module}.do?method=performClaimTask'"  value="<bean:message bundle='sheet' key='button.accept'/>" />
	</c:when>
	
	<!-- 等于驳回 -->
	<c:when test="${operateType == 4}">
		<input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}" />
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
	</c:when>
	
	<!-- 其它 -->
	<c:otherwise>
		<!-- 提交 -->
		<input type="submit" class="submit" name="method.save" id="method.save"  onclick="javascript:isCopy()" value="<bean:message bundle='sheet' key='button.done'/>" >
		<!-- 保存 -->
		<input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/${module}/${module}.do?method=saveDealInfo'" value="<bean:message bundle='sheet' key='button.save'/>" />  
		<!-- 保存并返回 -->
		<input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/${module}/${module}.do?method=performSaveInfoAndExit'" value="<bean:message bundle='sheet' key='button.saveback'/>" />
		<!-- 返回 -->
		<input type="submit" class="submit" onclick="v.passing=true;this.form.action='${app}/sheet/${module}/${module}.do?method=showListsendundo'" value="<bean:message bundle='sheet' key='button.back'/>" />
	</c:otherwise>
</c:choose>
