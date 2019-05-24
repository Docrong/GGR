<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.duty.model.TawRmLoanRecord" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<!--根据给定的实例名生成标题 -->
<title><fmt:message key="tawRmLoanRecordDetail.title"/></title>
<content tag="heading"><fmt:message key="tawRmLoanRecordDetail.heading"/></content>

<%
	TawRmLoanRecord tawRmLoanRecord=(TawRmLoanRecord)request.getAttribute("tawRmLoanRecord");
	String recordId = (String) request.getParameter("id");
	String returnTime = (String) request.getAttribute("returnTime");
	String articleName=request.getParameter("tmpArticleName");
	String piece=request.getParameter("tmpPiece");
	String borrowerName=request.getParameter("tmpBorrowerName");
	String loanTime=request.getParameter("tmpLoanTime");
	String tmpReturnTime=request.getParameter("tmpReturnTime");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
%>

<!--对表单的自动生成的处�?-->
<html:form action="tawRmLoanRecord" method="post" styleId="tawRmLoanRecordForm"> 
<ul>

    <!--表示对所有的域有�? -->
	       <html:hidden property="id"/>
	        <html:hidden property="articleId"/>
	         <html:hidden property="articleType"/>
		   <html:hidden property="userId" />
		   <html:hidden property="workSerial" />
		   <html:hidden property="tmpArticleName" />
		   <html:hidden property="tmpPiece" />
		   <html:hidden property="tmpBorrowerName" />
		   <html:hidden property="tmpLoanTime" />
		   <html:hidden property="tmpReturnTime" />
		   <html:hidden property="roomId" />
		   <html:hidden property="startTime" />
		   <html:hidden property="endTime" />
		   <br>


		<table class="formTable">
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.articleName" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmLoanRecord.getArticleName()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmArticleForm.articleType" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmLoanRecord.getArticleType()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.piece" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmLoanRecord.getPiece()) %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.borrowerName" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmLoanRecord.getBorrowerName()) %>
				</td>
			</tr>
			
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.loanTime" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmLoanRecord.getLoanTime()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawRmLoanRecordForm.returnTime" />
				</td> 
				<td width="500" colspan="2">
					<%=StaticMethod.null2String(tawRmLoanRecord.getReturnTime()) %>
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