<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*" %>
<%
    String oldsql = (String) request.getAttribute("oldsql");
    String id = (String) request.getAttribute("id");
    System.out.println(id + "tttttttttttttttttttttttttttttttttttttt");
%>

<script type="text/javascript">
    function intoUndo() {
        var form = document.getElementById("theform");
        form.submit();
    }

    window.setTimeout(intoUndo, 3000);

</script>

<style type="text/css">
    .successPage span.data {
        color: #1465B7;
        margin-left: 65px;
    }
</style>
<html:form action="/commonfault.do?method=performQueryHB" styleId="theform">
    <input type="hidden" name="oldsql" value="${oldsql}"/>
    <input type="hidden" name="id" value="${id}"/>
</html:form>
<div class="successPage">
    <h1 class="header">工单数据已经成功提交!</h1>
</div>
<%@ include file="/common/footer_eoms.jsp" %>