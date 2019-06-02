<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonLogOperatorList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/log/editTawCommonLogOperator.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonLogOperatorList" cellspacing="0" cellpadding="0"
    id="tawCommonLogOperatorList" pagesize="25" class="table tawCommonLogOperatorList"
    export="true" requestURI="" sort="list">



    <display:column property="userid" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.userid"/>
         
   


 <%-- <display:none property="modelid" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.modelid"/>--%>

    <display:column property="modelname" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.modelname"/>
         
     <%--       <display:none property="operid" sortable="true" headerClass="sortable"
        url="/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.operid"/>--%>

    <display:column property="opername" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.opername"/>
         
 <display:column property="beginnotetime" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.beginnotetime"/>

    <display:column property="bzremark" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.bzremark"/>
    <display:column property="notemessage" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.notemessage"/>

    <display:column property="operatetime" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.operatetime"/>

    <display:column property="operremark" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.operremark"/>


    <display:column property="remoteip" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.remoteip"/>
         
    <display:column property="url" sortable="true" headerClass="sortable"
        url="/log/editTawCommonLogOperator.do" paramId="id" paramProperty="id"
         titleKey="tawCommonLogOperatorForm.url"/>


    <display:setProperty name="paging.banner.item_name" value="tawCommonLogOperator"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonLogOperators"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonLogOperatorList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>