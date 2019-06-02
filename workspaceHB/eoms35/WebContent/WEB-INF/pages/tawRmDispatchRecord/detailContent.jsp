<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.duty.model.TawRmDispatchRecord"%>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmDispatchRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmDispatchRecordDetail.heading"/></content>

<%
	TawRmDispatchRecord tawRmDispatchRecord=(TawRmDispatchRecord)request.getAttribute("tawRmDispatchRecord");
	String fileName=request.getParameter("tmpFileName");
	String fileSource=request.getParameter("tmpFileSource");
	String fileProperty=request.getParameter("tmpFileProperty");
	String time=request.getParameter("tmpTime");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	String dispatchDeptId=request.getParameter("tmpDispatchDeptId");
	String dispatchDept=request.getParameter("tmpDispatchDept");
	String dispatcherId=request.getParameter("tmpDispatcherId");
	String dispatcher=request.getParameter("tmpDispatcher");
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmDispatchRecord" method="post" styleId="tawRmDispatchRecordForm"> 
<ul>
<!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpFileName" />
		   <html:hidden property="tmpFileSource" />
		   <html:hidden property="tmpFileProperty" />
		   <html:hidden property="tmpTime" />
		   <html:hidden property="roomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />
		   <html:hidden property="tmpDispatchDeptId" />
		   <html:hidden property="tmpDispatchDept" />
		   <html:hidden property="tmpDispatcherId" />
		   <html:hidden property="tmpDispatcher" />
		   <br>

		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.fileName" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getFileName() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.fileSource" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getFileSource() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.fileProperty" />
				</td> 
				<td width="500" colspan="2">
		      		<%=tawRmDispatchRecord.getFileProperty() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.time" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getTime() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.dispatchDept" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getDispatchDept() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.dispatcher" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getDispatcher() %>
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.excuteRequest" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getExcuteRequest() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmDispatchRecordForm.remark" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmDispatchRecord.getRemark() %>
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