<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<bean:define id="url" value="autoDealsop.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="autoDealsop.do"
               sort="external" size="total" partialList="true"
>
    <display:column sortable="true" sortName="task.sheetId"
                    headerClass="sortable" title="工单流水号">
        <a href='${app}/sheet/commonfault/commonfault.do?method=performShowSheetInfoBySheet&sheetId=${taskList.sheetId}'
           target="blank">
                ${taskList.sheetId}
        </a>
    </display:column>
    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="工单主题"/>
    <display:column property="mainNetName" sortable="true" sortName="main.mainNetName"
                    headerClass="sortable" title="网元名称"/>
    <display:column sortable="true" sortName="main.mainNetSortOne"
                    headerClass="sortable" title="网络一级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainNetSortTwo"
                    headerClass="sortable" title="网络二级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainNetSortThree"
                    headerClass="sortable" title="网络三级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainFaultResponseLevel"
                    headerClass="sortable" title="故障处理响应级别">
        <eoms:id2nameDB id="${taskList.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column property="sendTime" sortable="true" sortName="task.sendTime"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="sheetCompleteLimit" sortable="true" sortName="sheetCompleteLimit"
                    headerClass="sortable" title="完成时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column sortable="true" sortName="main.perAllocatedUser"
                    headerClass="sortable" title="预分配人员">
        <eoms:id2nameDB id="${taskList.perAllocatedUser}" beanId="tawSystemUserDao"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<%@ include file="/common/footer_eoms.jsp" %>