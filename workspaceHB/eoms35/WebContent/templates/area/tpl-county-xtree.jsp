<%@ include file="/common/header_tpl_json.jsp" %>
<%-- @see com.boco.eoms.commons.system.area.model.TawSystemArea --%>
<json:array name="countys" items="${list}" var="item">
    <json:object>
        <json:property name="id" value="${item.areaid}"/>
        <json:property name="text" value="${item.areaname}"/>
        <json:property name="leaf" value="${0}"/>
        <json:property name="nodeType" value="county"/>
        <json:property name="iconCls" value="county"/>
        <json:property name="areacode" value="${item.areacode}"/>
        <json:property name="capital" value="${item.capital}"/>
    </json:object>
</json:array>