<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<html:form action="tawRmLoanRecord" method="post" styleId="tawRmLoanRecordForm"> 
<content tag="heading"><fmt:message key="tawRmLoanRecordList.heading"/></content>
<%
	ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	String articleName=request.getParameter("tmpArticleName");
	String piece=request.getParameter("tmpPiece");
	String borrowerName=request.getParameter("tmpBorrowerName");
	String loanTime=request.getParameter("tmpLoanTime");
	String returnTime=request.getParameter("tmpReturnTime");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	session.setAttribute("myTmpArticleName",articleName);
	session.setAttribute("myTmpPiece",piece);
	session.setAttribute("myTmpBorrowerName",borrowerName);
	session.setAttribute("myTmpLoanTime",loanTime);
	session.setAttribute("myTmpReturnTime",returnTime);
	session.setAttribute("myRoomId",roomId);
	session.setAttribute("myStartTime",startTime);
	session.setAttribute("myEndTime",endTime);
%>
 
<display:table name="tawRmLoanRecordList" cellspacing="0" cellpadding="0"
    id="tawRmLoanRecordList" pagesize="${pageSize }" class="table tawRmLoanRecordList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/tawRmLoanRecord.do?method=searchList" 
    decorator="com.boco.eoms.duty.displaytag.support.LoanListDisplaytagDecorator" >


	<display:column property="articleName" sortable="true" headerClass="sortable"
         titleKey="tawRmLoanRecordForm.articleName" paramId="id" paramProperty="id" href="${app}/duty/tawRmLoanRecord.do?method=edit"/>
         
    <display:column property="piece" sortable="true" headerClass="sortable"
         titleKey="tawRmLoanRecordForm.piece"/>

    <display:column property="borrowerName" sortable="true" headerClass="sortable"
         titleKey="tawRmLoanRecordForm.borrowerName"/>

    <display:column property="loanTime" sortable="true" headerClass="sortable"
         titleKey="tawRmLoanRecordForm.loanTime"/>

    <display:column property="returnTime" sortable="true" headerClass="sortable"
         titleKey="tawRmLoanRecordForm.returnTime"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.userid"/>

    

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>