<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<bean:define id="phaseReply" value="phaseReply"/>
<bean:define id="transference" value="transference"/>
<bean:define id="split" value="split"/>



 <!-- 是父任务，没有子任务的情况 -->
 <c:if test='${ifsub == "" || ifsub == "false"}'>
 	<c:if test='${ifwaitfor == "false"}'>
 	
			<c:if test="${fn:indexOf(displaycommonoperate,transference) != -1 }">
				  <!-- 移交--> 
				  <c:if test="${displaytransferaudit == 'false'}">
					  <option value="${urlShowTransferkPage}"><bean:message bundle="sheet" key="common.transfer"/></option>
				  </c:if>
				  <!-- 转审 -->
				   <c:if test="${displaytransferaudit == 'true'}">
				  	  <option value="${urlShowTransferAuditPage}"><bean:message bundle="sheet" key="common.transferAudit"/></option>
				  </c:if>
			</c:if>
		  
		  <c:if test="${fn:indexOf(displaycommonoperate,phaseReply) != -1 }">
			  <!-- 阶段回复  -->
			  <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>
		  </c:if>
	</c:if>
</c:if>



<c:if test="${fn:indexOf(displaycommonoperate,split) != -1 }">
	<!-- 分派 -->
	<c:if test="${displaytransferaudit == 'false'}">
		<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
	</c:if>
	<!-- 会审 -->
	<c:if test="${displaytransferaudit == 'true'}">
		<option value="${urlShowInputSplitAudit}"><bean:message bundle="sheet" key="common.splitAudit"/></option>
	</c:if>
</c:if>

<!-- 是子任务 -->
 <c:if test="${ifsub == 'true'}">
	<!-- 分派回复 -->
	<c:if test='${ifwaitfor == "false"}'>
		<c:if test="${displaytransferaudit == 'false'}">
		  <option value="${urlShowDispatchPage}"><bean:message bundle="sheet" key="operateType.subTaskReply"/></option>
		</c:if>
		<c:if test="${displaytransferaudit == 'true'}">
			 <option value="${urlShowDispatchAuditPage}"><bean:message bundle="sheet" key="common.splitReplyAudit"/></option>
		</c:if>
	</c:if>
</c:if>


<!-- 需要处理回复 -->
 <c:if test='${needDealReply == "true" }'>
      <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet" key="common.dealReplyAccept"/></option>
      <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet" key="common.dealReplyReject"/></option>  
 </c:if>