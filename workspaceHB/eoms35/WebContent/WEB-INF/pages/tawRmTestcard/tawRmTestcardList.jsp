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
	String supplyer=request.getParameter("supplyer");
	String iccid=request.getParameter("iccid");
	String msisdn=request.getParameter("msisdn");
	String imsi=request.getParameter("imsi");
	String pin=request.getParameter("pin");
	String openAccountDate=request.getParameter("openAccountDate");
	String logoutDate=request.getParameter("logoutDate");
	String takeOverDate=request.getParameter("takeOverDate");
	String state=request.getParameter("state");
	session.setAttribute("myCardType",cardType);
	session.setAttribute("myFromProvince",fromProvince);
	session.setAttribute("myFromCity",fromCity);
	session.setAttribute("myFromCountry",fromCountry);
	session.setAttribute("myFromOperator",fromOperator);
	session.setAttribute("myToProvince",toProvince);
	session.setAttribute("myToCity",toCity);
	session.setAttribute("myToCountry",toCountry);
	session.setAttribute("myToOperator",toOperator);
	session.setAttribute("mySupplyer",supplyer);
	session.setAttribute("myIccid",iccid);
	session.setAttribute("myMsisdn",msisdn);
	session.setAttribute("myImsi",imsi);
	session.setAttribute("myPin",pin);
	session.setAttribute("myOpenAccountDate",openAccountDate);
	session.setAttribute("myLogoutDate",logoutDate);
	session.setAttribute("myTakeOverDate",takeOverDate);
	session.setAttribute("myState",state);
%>
<display:table name="tawRmTestcardList" cellspacing="0" cellpadding="0"
    id="tawRmTestcardList" pagesize="${pageSize }" class="table tawRmTestcardList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/testcard/tawRmTestcards.do?method=searchList" 
    decorator="com.boco.eoms.otherwise.displaytag.support.TestCardListDisplaytagDecorator" >

    <display:column property="msisdn" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.msisdn"  paramId="id" paramProperty="id" href="${app}/testcard/tawRmTestcards.do?method=edit"/>
         
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

