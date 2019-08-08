<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if (ifReference.equals(""))
        ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
    System.out.println("success ifReference=" + ifReference); %>
<script type="text/javascript">
    Try.these(
        function () {
            parent.frames['portal-south'].location.reload();
        }
    );

    function intoUndo() {
        var undo = document.location.href;
        undo = undo.substring(0, undo.indexOf("?") + 1) + "method=xquery&ifReference=${ifReference}";
        if ('${ifReference}' != null && '${ifReference}' != '') {
            undo.action = "ordersheets.do?method=xquery&$ifReference=${ifReference}";
        } else {
            undo.action = "ordersheets.do?method=xquery";
        }
        location.href = undo;
    }
</script>

<div class="successPage">
    <h1 class="header">数据保存成功！</h1>
    <div class="content">
        <ul>
            <li><html:link href="#" onclick="intoUndo();">返回定单目录列表</html:link></li>
        </ul>
    </div>
</div>
<%@ include file="/common/footer_eoms.jsp" %>