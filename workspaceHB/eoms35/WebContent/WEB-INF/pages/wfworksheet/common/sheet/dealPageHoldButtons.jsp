<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>

<c:if test="${sheetMain.status == 0}">
	<div class="sheet-deal-content" id="sheet-deal-content-self-force"></div>
	<!-- 管理者进入强制作废 -->
	<c:choose>
		<c:when test="${isAdmin == 'true'}">
				<div id="buttonDisplay" style="display:block">

				    <input type="button" value="<bean:message bundle="sheet" key="button.forceHold"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
				    <input type="button" value="<bean:message bundle="sheet" key="button.forceNullity"/>"  onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
				    <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
		    	</div>
		</c:when>
		<c:when test="${taskStatus == '5' && taskName != 'cc' && taskName != 'reply' && taskName != 'advice'}">
				 <div id="buttonDisplay">
		     		<input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
		    	 </div>
		</c:when>
		<c:when test="${(taskStatus == null || taskStatus == '') && (userId == sendUserId)}">
				<div id="advice" style="display:block">
				     <input type="button" value="<bean:message bundle="sheet" key="button.nullity"/>" onclick="$('advice').style.display='none';forceOperation(2);">
				     <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>" onclick="$('advice').style.display='none';eventOperation(1);">
	  			</div>
		</c:when>
	</c:choose>
</c:if>