<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/kmExamTestTypeContents.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<fmt:bundle basename="config/applicationResource-kmexamtesttypecontent">

    <content tag="heading">
        <fmt:message key="kmExamTestTypeContent.list.heading"/>
    </content>

    <display:table name="kmExamTestTypeContentList" cellspacing="0" cellpadding="0"
                   id="kmExamTestTypeContentList" pagesize="${pageSize}" class="table kmExamTestTypeContentList"
                   export="false"
                   requestURI="${app}/kmExamTestTypeContent/kmExamTestTypeContents.do?method=search"
                   sort="list" partialList="true" size="resultSize">

        <display:column property="typeContentID" sortable="true"
                        headerClass="sortable" titleKey="kmExamTestTypeContent.typeContentID"
                        href="${app}/kmExamTestTypeContent/kmExamTestTypeContents.do?method=edit" paramId="id"
                        paramProperty="id"/>

        <display:column property="testTypeID" sortable="true"
                        headerClass="sortable" titleKey="kmExamTestTypeContent.testTypeID"
                        href="${app}/kmExamTestTypeContent/kmExamTestTypeContents.do?method=edit" paramId="id"
                        paramProperty="id"/>

        <display:column property="score" sortable="true"
                        headerClass="sortable" titleKey="kmExamTestTypeContent.score"
                        href="${app}/kmExamTestTypeContent/kmExamTestTypeContents.do?method=edit" paramId="id"
                        paramProperty="id"/>

        <display:column property="questionID" sortable="true"
                        headerClass="sortable" titleKey="kmExamTestTypeContent.questionID"
                        href="${app}/kmExamTestTypeContent/kmExamTestTypeContents.do?method=edit" paramId="id"
                        paramProperty="id"/>


        <display:setProperty name="paging.banner.item_name" value="kmExamTestTypeContent"/>
        <display:setProperty name="paging.banner.items_name" value="kmExamTestTypeContents"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>