<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<content tag="heading"><fmt:message key="tawRmInoutRecordList.heading"/></content>
<display:table name="tawRmInoutRecordList" cellspacing="0" cellpadding="0"
    id="tawRmInoutRecordList" pagesize="${pageSize }" class="table tawRmInoutRecordList"
    export="true" requestURI="${app}/testcard/tawRmInoutRecords.do?method=searchList" 
    sort="external" partialList="true" size="resultSize"
    decorator="com.boco.eoms.otherwise.displaytag.support.InoutRecordListDisplaytagDecorator">

    <display:column property="iccid" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.iccid"/>
         
    <display:column property="msisdn" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.msisdn"/>
         
    <display:column property="imsi" sortable="true" headerClass="sortable"
         titleKey="tawRmTestcardForm.imsi"/>

    <display:column property="borrowDate" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.borrowDate"/>

    <display:column property="intendingReturnDate" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.intendingReturnDate"/>

    <display:column property="realReturnDate" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.realReturnDate"/>

    <display:column property="borrowerId" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.borrowerId"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.userId"/>

    <display:setProperty name="paging.banner.item_name" value="tawRmInoutRecord"/>
    <display:setProperty name="paging.banner.items_name" value="tawRmInoutRecords"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>

