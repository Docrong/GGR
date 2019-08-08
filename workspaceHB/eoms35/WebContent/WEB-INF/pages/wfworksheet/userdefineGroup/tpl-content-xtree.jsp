<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/header_tpl_json.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:choose>
    <c:when test="${nodetype=='subRoleList'}">
        <json:array name="roles" items="${roles}" var="item">
            <json:object>
                <json:property name="id" value="${item.id}"/>
                <json:property name="text" value="${item.subRoleName}"/>
                <json:property name="leaf" value="${0}"/>
                <json:property name="nodeType" value="subrole"/>
                <json:property name="iconCls" value="subrole"/>
            </json:object>
        </json:array>
    </c:when>
    <c:when test="${nodetype=='roleuserList'}">
        <json:array name="roleusers" items="${subusers}" var="item">
            <json:object>
                <json:property name="id" value="${item.userid}"/>
                <json:property name="text" value="${item.remark}"/>
                <json:property name="leaf" value="${1}"/>
                <json:property name="nodeType" value="user"/>
                <json:property name="iconCls" value="user"/>
            </json:object>
        </json:array>
    </c:when>
    <c:when test="${nodetype=='defineList'}">
        <json:array name="defines" items="${defines}" var="item">
            <json:object>
                <json:property name="id" value="${item.deptId}"/>
                <json:property name="text" value="${item.deptName}"/>
                <json:property name="leaf" value="${1}"/>
                <json:property name="nodeType" value="dept"/>
                <json:property name="iconCls" value="dept"/>
            </json:object>
        </json:array>
    </c:when>
    <c:when test="${nodetype=='deptList'}">
        <json:array name="depts" items="${depts}" var="item">
            <json:object>
                <json:property name="id" value="${item.deptId}"/>
                <json:property name="text" value="${item.deptName}"/>
                <json:property name="leaf" value="${1}"/>
                <json:property name="nodeType" value="dept"/>
                <json:property name="iconCls" value="dept"/>
            </json:object>
        </json:array>
    </c:when>

    <c:otherwise>
        <json:array name="users" items="${users}" var="item">
            <json:object>
                <json:property name="id" value="${item.userid}"/>
                <json:property name="text" value="${item.username}"/>
                <json:property name="leaf" value="${1}"/>
                <json:property name="nodeType" value="user"/>
                <json:property name="iconCls" value="user"/>
            </json:object>
        </json:array>
    </c:otherwise>


</c:choose>