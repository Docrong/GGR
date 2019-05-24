<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmReliefRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmReliefRecordDetail.heading"/></content>
<%
  String nuiteFlag=(String)request.getAttribute("nuiteFlag");
%>
<!--对表单的自动生成的处�?-->
<html:form action="tawRmReliefRecord" method="post" styleId="tawRmReliefRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpHandoverId" />
		   <html:hidden property="tmpSuccessorId" />
		   <html:hidden property="tmpRoomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />	
		   <html:hidden property="tmpTime" />
		   
		   <br>


		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmReliefRecordForm.handoverId" />
				</td> 
				<td width="500" colspan="2">
					<bean:write name="tawRmReliefRecordForm"  property="handoverName"/>   
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmReliefRecordForm.successorId" />
				</td> 
				<td width="500" colspan="2">
					<bean:write name="tawRmReliefRecordForm"  property="successorName"/>   
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmReliefRecordForm.roomId" />
				</td> 
				<td width="500" colspan="2">
					<bean:write name="tawRmReliefRecordForm"  property="roomName"/>   
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmReliefRecordForm.time" />
				</td> 
				<td width="500" colspan="2">
					<bean:write name="tawRmReliefRecordForm"  property="time"/>   
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmReliefRecordForm.lbProblem" />
				</td> 
				<td width="500" colspan="2">
					<bean:write name="tawRmReliefRecordForm"  property="lbProblem"/>   
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmReliefRecordForm.nuiteFlag" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="nuiteFlag" itemId="<%=nuiteFlag%>" beanId="id2nameXML" />
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