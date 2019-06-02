<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemDeptRefPostList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editTawSystemDeptRefPost.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemDeptRefPostList" cellspacing="0" cellpadding="0"
    id="tawSystemDeptRefPostList" pagesize="25" class="table tawSystemDeptRefPostList"
    export="true" requestURI="/tawSystemDeptRefPosts.do" sort="external" partialList="true" size="resultSize">

    <display:column property="deptId" sortable="true" headerClass="sortable"
         titleKey="tawSystemDeptRefPostForm.deptId"/>

    <display:column property="postId" sortable="true" headerClass="sortable"
         titleKey="tawSystemDeptRefPostForm.postId"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemDeptRefPost"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemDeptRefPosts"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemDeptRefPostList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>