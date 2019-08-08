<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/header_tpl_json.jsp" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<logic:empty name="novalue">
    <json:array name="jsonext" items="${jsonext}" var="item">
        <json:object>
            <json:property name="id" value="${item.id}"/>
            <json:property name="text" value="${item.defineName}"/>
            <json:property name="leaf" value="${0}"/>
            <json:property name="nodeType" value="subrole"/>
            <json:property name="iconCls" value="subrole"/>
        </json:object>
    </json:array>
</logic:empty>


