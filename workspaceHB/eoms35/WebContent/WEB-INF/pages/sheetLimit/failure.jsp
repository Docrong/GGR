<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<div class="failurePage">
    <h1 class="header">配置时限失败!</h1>
    <div class="content">
        <%= StaticMethod.nullObject2String(request.getAttribute("errorMessage"))%>
    </div>
</div>

<%@ include file="/common/footer_eoms.jsp" %>

