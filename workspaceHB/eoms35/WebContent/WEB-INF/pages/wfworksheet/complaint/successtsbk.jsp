<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*" %>

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
<html:form action="/complaint.do?method=getAccessories" styleId="theform">
</html:form>
<div class="successPage">
    <h1 class="header">数据已经成功提交!</h1>
</div>
<%@ include file="/common/footer_eoms.jsp" %>