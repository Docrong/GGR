<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table taskList"
               export="true" requestURI="commonfault.do" sort="external" size="total"
               partialList="true">

    <display:column sortable="true" sortName="main.sheetId" headerClass="sortable" title="工单流水号" media="html">
        <a href="${app}/sheet/commonfault/commonfault.do?method=showMainDetailPage&sheetKey=${taskList.id}&qctype=${flag}"
           target="_blank">${taskList.sheetId}</a>
    </display:column>

    <display:column property="sheetId" sortable="true" sortName="main.sheetId"
                    headerClass="sortable" title="工单流水号" media="excel"/>

    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="工单主题"/>

    <display:column property="endTime" sortable="true"
                    sortName="main.endTime" headerClass="sortable" title="归档时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column sortable="true" headerClass="sortable" title="网络一级分类"
                    sortName="main.mainNetSortOne">
        <eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="网络二级分类"
                    sortName="main.mainNetSortTwo">
        <eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="网络三级分类"
                    sortName="main.mainNetSortThree">
        <eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
    <display:setProperty name="export.rtf" value="false"/>
</display:table>


<%@ include file="/common/footer_eoms.jsp" %>
