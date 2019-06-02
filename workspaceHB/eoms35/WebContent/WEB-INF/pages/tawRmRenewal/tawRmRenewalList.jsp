<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<content tag="heading"><fmt:message key="tawRmRenewalRecordList.heading"/></content>
<display:table name="tawRmRenewRecordList" cellspacing="0" cellpadding="0"
    id="tawRmRenewRecordList" pagesize="${pageSize }" class="table tawRmRenewRecordList"
    export="true" requestURI="${app}/testcard/tawRmRenewals.do?method=searchList" 
    sort="external" partialList="true" size="resultSize"
    decorator="com.boco.eoms.otherwise.displaytag.support.RenewalRecordListDisplaytagDecorator">

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

    <display:column property="renewDate" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.renewDate"/>

    <display:column property="borrowerId" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.borrowerId"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="tawRmInoutRecordForm.userId"/>

    <display:setProperty name="paging.banner.item_name" value="tawRmRenewal"/>
    <display:setProperty name="paging.banner.items_name" value="tawRmRenewals"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>

