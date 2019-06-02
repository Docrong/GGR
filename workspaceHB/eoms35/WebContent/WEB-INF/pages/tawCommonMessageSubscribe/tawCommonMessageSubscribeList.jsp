<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonMessageSubscribeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/message/editTawCommonMessageSubscribe.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonMessageSubscribeList" cellspacing="0" cellpadding="0"
    id="tawCommonMessageSubscribeList" pagesize="25" class="table tawCommonMessageSubscribeList"
    export="true" requestURI="" sort="list">

    <display:column property="endday" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.endday"/>

    <display:column property="endhour" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.endhour"/>

    <display:column property="endmin" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.endmin"/>

   

    <display:column property="messageid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.messageid"/>

    <display:column property="receivertype" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.receivertype"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.remark"/>

    <display:column property="remarktwo" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.remarktwo"/>

    <display:column property="revecer" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.revecer"/>

    <display:column property="sendcount" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.sendcount"/>

   
    <display:column property="startday" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.startday"/>

    <display:column property="starthour" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.starthour"/>

    <display:column property="startmin" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.startmin"/>

    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.userid"/>

    <display:column property="sendcuttime" sortable="true" headerClass="sortable"
        url="/message/editTawCommonMessageSubscribe.do" paramId="id" paramProperty="id"
         titleKey="tawCommonMessageSubscribeForm.sendcuttime"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonMessageSubscribe"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonMessageSubscribes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonMessageSubscribeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>