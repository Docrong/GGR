<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<html:form action="tawRmTestcards" method="post" styleId="tawRmTestcardForm"> 
<content tag="heading"><fmt:message key="tawRmTestcardList.heading"/></content>
<%
	String cardType=request.getParameter("cardType");
	String fromProvince=request.getParameter("fromProvince");
	String fromCity=request.getParameter("fromCity");
	String fromCountry=request.getParameter("fromCountry");
	String fromOperator=request.getParameter("fromOperator");
	String toProvince=request.getParameter("toProvince");
	String toCity=request.getParameter("toCity");
	String toCountry=request.getParameter("toCountry");
	String toOperator=request.getParameter("toOperator");
	String state=request.getParameter("state");
	String borrowerId=request.getParameter("borrowerId");
	String borrowStartDate=request.getParameter("borrowStartDate");
	String borrowEndDate=request.getParameter("borrowEndDate");
	String intendingReturnStartDate=request.getParameter("intendingReturnStartDate");
	String intendingReturnEndDate=request.getParameter("intendingReturnEndDate");
	session.setAttribute("myCardType",cardType);
	session.setAttribute("myFromProvince",fromProvince);
	session.setAttribute("myFromCity",fromCity);
	session.setAttribute("myFromCountry",fromCountry);
	session.setAttribute("myFromOperator",fromOperator);
	session.setAttribute("myToProvince",toProvince);
	session.setAttribute("myToCity",toCity);
	session.setAttribute("myToCountry",toCountry);
	session.setAttribute("myToOperator",toOperator);
	session.setAttribute("myState",state);
	session.setAttribute("myBorrowerId",borrowerId);
	session.setAttribute("myBorrowStartDate",borrowStartDate);
	session.setAttribute("myBorrowEndDate",borrowEndDate);
	session.setAttribute("myIntendingReturnStartDate",intendingReturnStartDate);
	session.setAttribute("myIntendingReturnEndDate",intendingReturnEndDate);
%>
<display:table name="statTestcardList" cellspacing="0" cellpadding="0"
    id="statTestcardList" pagesize="${pageSize }" class="table statTestcardList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/testcard/tawRmTestcards.do?method=statTestCard" 
    decorator="com.boco.eoms.otherwise.displaytag.support.TestCardListDisplaytagDecorator" >

	<!-- <display:column property="iccid" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.iccid" paramId="id" paramProperty="id" href="${app}/testcard/tawRmTestcards.do?method=viewContent"/>-->
         
    <display:column property="msisdn" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.msisdn"/>
         
	<display:column property="iccid" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.iccid"/>

    <display:column property="imsi" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.imsi"/>

    <display:column property="pin" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.pin"/>

    <display:column property="puk" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.puk"/>
         
    <display:column property="openAccountDate" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.openAccountDate"/>

    <display:column property="logoutDate" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.logoutDate"/>

    <display:column property="takeOverDate" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.takeOverDate"/>

    <display:column property="state" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.state"/>

    <display:column property="oldNumber" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.oldNumber"/>


    <display:column property="supplyer" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.supplyer"/>
         
    <display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.userId"/>

    <display:setProperty name="paging.banner.item_name" value="tawRmTestcard"/>
    <display:setProperty name="paging.banner.items_name" value="tawRmTestcards"/>
</display:table>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>

