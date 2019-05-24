<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager" %>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<style type="text/css">
  	body{background-image:none;}
</style>

<script language="javascript">

function confirmSave(){
if ( confirm('${eoms:a2u("是否要保存")}') ){
	return true;
	}else{
	return false;
	}
}
</script>
<%
	ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder.getInstance().getBean("ItawSystemCptroomManager");
%>
<content tag="heading"><fmt:message key="tawRmPlanContentDetail.heading"/></content>

<html:form action="tawRmPlanContent" method="post" styleId="tawRmPlanContentForm"> 
<ul>
	<table class="listTable" id="list-table">
		<tr height="30" class="header">
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmPlanContentForm.monthplanName" />
			</td>
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmPlanContentForm.excuteFlag" />
			</td>
			<td nowrap="nowrap" width="20%">
				<fmt:message key="tawRmPlanContentForm.roomId" />
			</td>
		</tr>
		<logic:iterate id="listIt" name="listIt">
			<tr height="30">
				<bean:define id="id" name="listIt" property="id" />
				<bean:define id="executeFlag" name="listIt" property="executeFlag" />
				<bean:define id="executer" name="listIt" property="executer" />
				<td nowrap="nowrap" width="20%">
					<a
						href="${app}/plancontent/tawRmPlanContent.do?method=view&excutecontentId=<%=id%>">
						<bean:write name="listIt" property="name" /> </a>
				</td>
				<td nowrap="nowrap" width="20%">
					<eoms:dict key="dict-plancontent" dictId="executeFlag" itemId="<%=executeFlag.toString()%>" beanId="id2nameXML" />
				</td>
				<td nowrap="nowrap" width="20%">
					<%=cptroommanager.getTawSystemCptroomName(new Integer(executer.toString())) %>
				</td>
			</tr>
		</logic:iterate>

	</table>
	
		<table>
			<tr>
				<td>
				<html:submit styleClass="button" property="method.save" onclick="bCancel=false;return confirmSave()">
           			<fmt:message key="button.save"/>
       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
</html:form>
<script src="${app}/scripts/util/iframe.js" type="text/javascript" />
<%@ include file="/common/footer_eoms.jsp"%>

