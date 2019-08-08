<%@ include file="/common/taglibs.jsp" %>
<%
    String sheetNo = request.getParameter("serialNo");
    String zxType = request.getParameter("zxType");
    String sheetType = request.getParameter("sheetType");

    if (sheetType.equals("31")) {
        response.sendRedirect(request.getContextPath() + "/sheet/resourceconfirm/resourceconfirm.do?method=showHistoryPage&type=interface&userName=admin&sheetNo=" + sheetNo);
    } else {
        if (zxType.equals("2"))
            response.sendRedirect(request.getContextPath() + "/sheet/businessimplementyy/businessimplementyy.do?method=showHistoryPage&type=interface&userName=admin&sheetNo=" + sheetNo);
        else
            response.sendRedirect(request.getContextPath() + "/sheet/businessimplement/businessimplement.do?method=showHistoryPage&type=interface&userName=admin&sheetNo=" + sheetNo);
    }
%>