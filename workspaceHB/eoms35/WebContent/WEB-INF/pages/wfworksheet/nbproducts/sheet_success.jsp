<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
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
            undo.action = "nbproductss.do?method=xquery&$ifReference=${ifReference}";
        } else {
            undo.action = "nbproductss.do?method=xquery";
        }
        location.href = undo;
    }

    function intodeleted() {
        var deleted = document.location.href;
        deleted = deleted.substring(0, deleted.indexOf("?") + 1) + "method=xqueryDeleted";
        deleted.action = "nbproductss.do?method=xqueryDeleted";
        location.href = deleted;
    }
</script>

<div class="successPage">
    <h1 class="header">${eoms:a2u('数据保存成功！')}</h1>
    <div class="content">
        <ul>
            <li><html:link href="#" onclick="intoUndo();">${eoms:a2u('返回新产品目录列表')}</html:link></li>
        </ul>
        <ul>
            <li><html:link href="#" onclick="intodeleted();">${eoms:a2u('返回已删除目录列表')}</html:link></li>
        </ul>
    </div>
</div>
<%@ include file="/common/footer_eoms.jsp" %>