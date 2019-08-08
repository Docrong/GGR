<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
    String listType = StaticMethod.nullObject2String(request.getAttribute("listType"));
    String succesReturn = StaticMethod.nullObject2String(request.getAttribute("succesReturn"));
    String faultReturn = StaticMethod.nullObject2String(request.getAttribute("faultReturn"));
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String userId = sessionform.getUserid();

    String undoFlag = StaticMethod.nullObject2String(request.getAttribute("undoFlag"));

    String ifNetOpt = "3";
    String ifNetOpt1 = StaticMethod.nullObject2String(request.getAttribute("ifNetOpt"));
    if (!"".equals(ifNetOpt1) && ifNetOpt1 != null) {
        ifNetOpt = ifNetOpt1;
    }
    System.out.println("===listType====" + listType);
    System.out.println("===succesReturn====" + succesReturn + "===faultReturn=====" + faultReturn + "====ifNetOpt====" + ifNetOpt);
    if (listType.equals("ownerList")) {
%>
<script type="text/javascript">
    function intoOwner() {
        var undo = document.location.href;
        undo = undo.substring(0, undo.indexOf("?") + 1) + "method=showOwnStarterList";
        location.href = undo;
    }

    window.setTimeout(intoOwner, 3000);
</script>
<%} else { %>
<script type="text/javascript">
    function intoUndo() {
        var undo = document.location.href;
        //undo = undo.substring(0,undo.indexOf("?")+1)+"method=showListsendundo&batch=true";
        undo = undo.substring(0, undo.indexOf("?") + 1) + "method=showListAll&ifNetOpt=<%=ifNetOpt%>&type=interface&interface=interface&userName=<%=userId%>";
        location.href = undo;
    }

    function intoDone() {
        var done = document.location.href;
        done = done.substring(0, done.indexOf("?") + 1) + "method=showListsenddone&type=interface&interface=interface&userName=<%=userId%>";
        location.href = done;

    }

    function intoUndoBatch() {
        var done = document.location.href;
        done = done.substring(0, done.indexOf("?") + 1) + "method=showListsendundo&batch=true&type=interface&interface=interface&userName=<%=userId%>";
        location.href = done;

    }

    function intoUndoT2reject() {
        var done = document.location.href;
        done = done.substring(0, done.indexOf("?") + 1) + "method=showListEeveundo&undoFlag=t2reject&type=interface&interface=interface&userName=<%=userId%>";
        location.href = done;
    }

    <% if(faultReturn.equals("")&&succesReturn.equals("")&&"".equals(undoFlag)){ %>
    window.setTimeout(intoUndo, 3000);
    <%}%>

    <% if("t2reject".equals(undoFlag)){ %>
    window.setTimeout(intoUndoT2reject, 3000);
    <%}%>

    <% if(!faultReturn.equals("")){ %>
    window.setTimeout(intoUndoBatch, 3000);
    <%}%>
</script>
<%} %>
<style type="text/css">
    .successPage span.data {
        color: #1465B7;
        margin-left: 65px;
    }
</style>
<div class="successPage">
    <% if (faultReturn.equals("") && succesReturn.equals("")) { %>
    <h1 class="header">工单数据已经成功提交!</h1>
    <%
    } else {
        if (!succesReturn.equals("")) {
    %>
    <h1 class="header">本次批量处理成功的工单号为:</h1><br/><span class="data"><%=succesReturn%></span><br/>
    <%
        }
        if (!faultReturn.equals("")) {
    %>
    <h1 class="header">本次批量处理失败的工单号或者任务号为:</h1><br/><span class="data"><%=faultReturn%></span><br/>
    <%}%>
    <div class="content">
        <ul>
            <li><html:link href="#" onclick="intoUndo();">返回待处理工单列表</html:link></li>
            <li><html:link href="#" onclick="intoDone();">返回已处理工单列表</html:link></li>
        </ul>
    </div>
    <%} %>
</div>
<%@ include file="/common/footer_eoms.jsp" %>