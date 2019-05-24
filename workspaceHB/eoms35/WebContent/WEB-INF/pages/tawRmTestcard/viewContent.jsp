<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="com.boco.eoms.otherwise.model.TawRmTestcard"%>

<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmTestcardDetail.title"/></title>
<content tag="heading">
<fmt:message key="tawRmTestcardDetail.heading"/>
</content>
<%
	TawRmTestcard tawRmTestcard=(TawRmTestcard)request.getAttribute("tawRmTestcard");
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmTestcards" method="post" styleId="tawRmTestcardForm"> 
<ul>

		<html:hidden property="ascriptionPlace" />
		<html:hidden property="visitPlace" />
		<html:hidden property="state" />
		<html:hidden property="borrowerId" />
		<html:hidden property="borrowStartDate" />	
		<html:hidden property="borrowEndDate" />
		<html:hidden property="intendingReturnStartDate" />	
		<html:hidden property="intendingReturnEndDate" />
    <!--表示对所有的域有�? -->
		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.ascriptionPlace" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getAscriptionPlace() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.visitPlace" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getVisitPlace() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.supplyer" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getSupplyer() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.iccid" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getIccid() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.msisdn" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getMsisdn() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.imsi" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getImsi() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.pin" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getPin() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.puk" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getPuk() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.bootPassword" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getBootPassword() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.operation" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getOperation() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.openAccountDate" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getOpenAccountDate() %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.logoutDate" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getLogoutDate() %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.takeOverDate" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getTakeOverDate() %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.state" />
				</td> 
				<td width="500" colspan="2">
					<eoms:dict key="dict-plancontent" dictId="useState" itemId="<%=tawRmTestcard.getState()%>" beanId="id2nameXML" />	
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmTestcardForm.oldNumber" />
				</td> 
				<td width="500" colspan="2">
					<%=tawRmTestcard.getOldNumber() %>
				</td>
			</tr>
		</table>
		
		<br>
		<table>
			<tr>
				<td>
	        		<html:submit styleClass="button" property="method.statTestCard2">
	           			<fmt:message key="button.back"/>
	       			</html:submit>
				</td>
			</tr>
		</table>
</ul>
  <!--自动生成的Javascript脚本-->

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>