<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
               export="true" requestURI="plannconfirm.do"
               sort="external" size="total" partialList="true">

    <c:if test="${queryType == 'wholenet'}">
        <display:column property="territorialBranch" sortable="true"
                        headerClass="sortable" title="属地分公司" sortName="territorialBranch"/>
    </c:if>
    <c:if test="${queryType == 'territorialBranch'}">
        <display:column sortable="true" headerClass="sortable" title="属地分公司" paramId="territorialBranch"
                        paramProperty="territorialBranch">
            <eoms:id2nameDB id="${taskList.territorialBranch}" beanId="tawSystemDeptDao"/>
        </display:column>
    </c:if>

    <display:column property="amount" sortable="true"
                    headerClass="sortable" title="申请总量" sortName="amount"/>

    <display:column property="checkAmount" sortable="true"
                    headerClass="sortable" title="审核确认总量" sortName="checkAmount"/>

    <display:column property="throughAmount" sortable="true"
                    headerClass="sortable" title="审核通过总量" sortName="throughAmount"/>

    <display:column property="rejectAmount" sortable="true"
                    headerClass="sortable" title="审核驳回总量" sortName="rejectAmount"/>

    <display:column property="notcheckAmount" sortable="true"
                    headerClass="sortable" title="未审核总量（运行中）" sortName="notcheckAmount"/>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>

</display:table>

<%@ include file="/common/footer_eoms.jsp" %>