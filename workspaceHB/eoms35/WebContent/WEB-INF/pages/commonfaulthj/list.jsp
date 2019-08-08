<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<bean:define id="url" value="excelimportnet.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table commonfaulthjList"
               export="true" requestURI="${app}/commonfaulthj/excelimportnet.do?method=showList"
               sort="list" size="total" partialList="true"
               decorator="">

    <display:column property="netname" sortable="false"
                    headerClass="sortable" title="网元名称"/>

    <display:column property="alarmid" sortable="false"
                    headerClass="sortable" title="告警ID"/>

    <display:column property="alarmid" sortable="false"
                    headerClass="sortable" title="网元ID"/>

    <display:column property="city" sortable="false"
                    headerClass="sortable" title="地市"/>

    <display:column sortable="false" headerClass="sortable" title="匹配班组">
        <eoms:id2nameDB id="${taskList.subroleid}" beanId="tawSystemSubRoleDao"/>
    </display:column>


    <display:setProperty name="export.rtf" value="false"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>