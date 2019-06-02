<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager" %>
<%@ page import="com.boco.eoms.duty.service.ITawRmWorkorderRecordManager" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmWorkorderRecordDetail.title"/></title>
<content tag="heading">
<fmt:message key="tawRmWorkorderRecordDetail.heading"/>
</content>
<%
	ListOrderedMap item=(ListOrderedMap)request.getAttribute("item");
	String sheetId = StaticMethod.nullObject2String(item.getValue(0));
	String title=StaticMethod.nullObject2String(item.getValue(1));
	String replyTime=StaticMethod.nullObject2String(item.getValue(2));
	String prelinkid=StaticMethod.nullObject2String(item.getValue(3));
	String orderType=StaticMethod.nullObject2String(request.getAttribute("orderType"));
	String orderState=StaticMethod.nullObject2String(request.getAttribute("orderState"));
	ITawSystemUserManager userMgr = (ITawSystemUserManager) ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	ITawRmWorkorderRecordManager workOrderMgr = (ITawRmWorkorderRecordManager) ApplicationContextHolder.getInstance().getBean("ITawRmWorkorderRecordManager");
	String userName="";
	if(!prelinkid.trim().equals("")){
		String userId=workOrderMgr.getUserIdByPrelinkId(prelinkid);
		userName=userMgr.id2Name(userId);
	}
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmWorkorderRecord" method="post" styleId="tawRmWorkorderRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderTitle" />
				</td> 
				<td width="500" colspan="2">
					<%=title%>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.workOrderId" />
				</td> 
				<td width="500" colspan="2">
					<%=sheetId %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.sender" />
				</td> 
				<td width="500" colspan="2">
					<%=userName%>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.replyTime" />
				</td> 
				<td width="500" colspan="2">
					<%=replyTime%>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderType" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="orderType" itemId="<%=orderType %>" beanId="id2nameXML" />
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmWorkorderRecordForm.orderState" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="orderState" itemId="<%=orderState %>" beanId="id2nameXML" />
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
					<html:submit styleClass="button" property="method.edit">
	           			<fmt:message key="button.back"/>
	       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>