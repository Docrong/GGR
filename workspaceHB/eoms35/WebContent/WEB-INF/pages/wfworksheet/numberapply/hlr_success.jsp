<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>

<script type="text/javascript">
    if ('${type}' == 'new') {
        var frame = window.opener.document.getElementById("frame1");
        frame.style.display = "block";
        frame.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new";
        window.close();
    } else if ('${type}' == 'DEL') {
        var frame = parent.window.document.getElementById("frame1");
        //alert(frame)
        frame.style.display = "block";
        //frame.location.reload();
        frame.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new";
    } else if ('${type}' == 'edit') {
        var frame = window.opener.parent.document.getElementById("frame1");
        frame.style.display = "block";
        frame.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new";
        window.close();
    } else if ('${type}' == 'BatchInsert') {
        var frame = window.opener.document.getElementById("frame1");
        frame.style.display = "block";
        frame.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=new";
        window.close();
    } else if ('${type}' == 'manual') {
        var frame = window.opener.parent.document.getElementById("frame2");
        frame.style.display = "block";
        frame.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=assign";
        window.close();
    } else if ('${type}' == 'automanual') {
        var frame = window.document.frames('frame2');
        //var frame = window.document.getElementById("frame2");
        frame.style.display = "block";
        //frame.location.reload();
        frame.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetKey}&actionForword=${actionForword}&stylepage=assign";
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>