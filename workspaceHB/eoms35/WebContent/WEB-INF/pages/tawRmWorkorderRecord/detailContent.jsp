<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="com.boco.eoms.duty.model.TawRmWorkorderRecord"%>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmWorkorderRecordDetail.title"/></title>
<content tag="heading">
<fmt:message key="tawRmWorkorderRecordDetail.heading"/>
</content>
<%
	TawRmWorkorderRecord tawRmWorkorderRecord=(TawRmWorkorderRecord)request.getAttribute("tawRmWorkorderRecord");
	ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	String sender=StaticMethod.nullObject2String(tawRmWorkorderRecord.getSender());
	String senderName="";
	if(!sender.trim().equals("")){
		senderName=userMgr.id2Name(sender);
	}
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmWorkorderRecord" method="post" styleId="tawRmWorkorderRecordForm"> 
<ul>

		<html:hidden property="title" />
		<html:hidden property="workOrderId" />
		<html:hidden property="orderState" />
		<html:hidden property="replyTime" />
		<html:hidden property="roomId" />
		<html:hidden property="startTime" />
		<html:hidden property="endTime" />
    <!--表示对所有的域有�? -->
		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderTitle" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmWorkorderRecord.getTitle() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.workOrderId" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmWorkorderRecord.getWorkOrderId() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.receiver" />
				</td> 
				<td width="500" colspan="2">
					<%=userMgr.id2Name(tawRmWorkorderRecord.getUserId()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.sender" />
				</td> 
				<td width="500" colspan="2">
					<%=senderName%>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.replyTime" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmWorkorderRecord.getReplyTime() %>		
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.receiveTime" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmWorkorderRecord.getReceiveTime() %>		
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderType" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="orderType" itemId="<%=tawRmWorkorderRecord.getOrderType() %>" beanId="id2nameXML" />	
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderState" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="orderState" itemId="<%=tawRmWorkorderRecord.getOrderState() %>" beanId="id2nameXML" />		
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<!-- <input type = "button" value='${eoms:a2u('返回')}' class="button"  onclick="javascript:history.back();"> -->
	        		<html:submit styleClass="button" property="method.searchList2">
	           			<fmt:message key="button.back"/>
	       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>