<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url
                   value="/sheet/autodistribute/autodistribute.do?method=showInputPage&type=add"/>'"
           value="<bean:message key="button.add"/>"/>
</c:set>


<display:table name="autoList" cellspacing="0" cellpadding="0"
               id="autoList" pagesize="${pageSize}" class="table autoList" size="autoTotal"
               export="true" requestURI="" sort="list"
               decorator="com.boco.eoms.sheet.autodistribute.webapp.action.AutoDistributeListDisplaytagDecoratorHelper">
    <display:column property="flowName" sortable="true" headerClass="sortable"
                    title="应用模块名称"/>
    <display:column property="autoType" sortable="true" headerClass="sortable"
                    title="动态分配任务类型"/>
    <display:column property="roleId" sortable="true" headerClass="sortable"
                    title="角色名"/>
    <display:column property="threshold" sortable="true" headerClass="sortable"
                    title="阀值"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<!-- <c:out value="${buttons}" escapeXml="false"/> -->
<%@ include file="/common/footer_eoms.jsp" %>