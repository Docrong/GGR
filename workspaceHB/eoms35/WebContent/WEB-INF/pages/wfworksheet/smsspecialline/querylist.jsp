<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function openSheet(url) {
        if (parent.frames['portal-north']) {
            parent.frames['portal-north'].location.href = url;
        } else {
            location.href = url;
        }
    }

</script>
<logic:notPresent name="recordTotal">
    <bean:define id="url" value="ordersheets.do"/>
    <display:table name="ordersheetList" cellspacing="0" cellpadding="0"
                   id="ordersheet" pagesize="${pageSize}" class="table ordersheetList"
                   export="true" requestURI="/businessupport/order/ordersheets.do?method=showDetail" sort="list">

        <display:column media="html">
            <input type="radio" name="id" value="${ordersheet.id}"/>
            <input type="hidden" name="orderType" value="${ordersheet.orderType}"/>
        </display:column>
        <display:column sortable="true" headerClass="orderType" title="定单类型"
                        url="/businessupport/order/ordersheets.do?method=showDetail&flag=showDetail&ifReference=${ifReference}"
                        paramId="id" paramProperty="id">
            <eoms:id2nameDB id="${ordersheet.orderType}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column property="mainProductInstanceCode" sortable="true" headerClass="sortable"
                        url="/businessupport/order/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
                        title="主产品实例编码"/>
        <display:column sortable="true" headerClass="sortable" title="紧急程度">
            <eoms:id2nameDB id="${ordersheet.urgentDegree}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="定单业务类型">
            <eoms:id2nameDB id="${ordersheet.orderBuisnessType}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column property="creatTime" sortable="true" headerClass="sortable"
                        url="/businessupport/order/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        title="生成时间"/>
        <display:column property="endTime" sortable="true" headerClass="sortable"
                        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        title="竣工时间"/>
        <display:column property="completeLimit" sortable="true" headerClass="sortable"
                        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        title="完成期限"/>
        <display:column property="changeTime" sortable="true" headerClass="sortable"
                        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        title="变更时间"/>
        <display:column property="cancelTime" sortable="true" headerClass="sortable"
                        url="/sheet/ordersheet/ordersheets.do?method=showDetail" paramId="id" paramProperty="id"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        title="撤销时间"/>

        <display:setProperty name="paging.banner.item_name" value="ordersheet"/>
        <display:setProperty name="paging.banner.items_name" value="ordersheets"/>
        <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.csv" value="false"/>

    </display:table>
</logic:notPresent>
<logic:present name="recordTotal">
    <center>
        <bean:message bundle="sheet" key="worksheet.query.total"/>${recordTotal}总数
    </center>
</logic:present>
<%@ include file="/common/footer_eoms.jsp" %>
