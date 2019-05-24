<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.duty.model.TawRmVisitRecord" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmVisitRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmVisitRecordDetail.heading"/></content>

<%
	TawRmVisitRecord tawRmVisitRecord=(TawRmVisitRecord)request.getAttribute("tawRmVisitRecord");
	String visitorName=request.getParameter("tmpVisitorName");
	String company=request.getParameter("tmpCompany");
	String visitTime=request.getParameter("tmpVisitTime");
	String tmpLeftTime=request.getParameter("tmpLeftTime");
	String receiver=request.getParameter("tmpReceiver");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmVisitRecord" method="post" styleId="tawRmVisitRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpVisitorName" />
		   <html:hidden property="tmpCompany" />
		   <html:hidden property="tmpVisitTime" />
		   <html:hidden property="tmpLeftTime" />
		   <html:hidden property="tmpReceiver" />
		   <html:hidden property="roomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />
		   <br>


		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.visitorName" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmVisitRecord.getVisitorName()) %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.company" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmVisitRecord.getCompany()) %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.visitTime" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmVisitRecord.getVisitTime()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.leftTime" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmVisitRecord.getLeftTime()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.reason" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmVisitRecord.getReason()) %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmVisitRecordForm.receiver" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmVisitRecord.getReceiver()) %>
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
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