<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    String sheetNo = StaticMethod.nullObject2String(request.getParameter("sheetNo"));
    int datelimit = 131229;
    if (!"".equals(sheetNo)) {
        String[] sheetNos = sheetNo.split("-");
        if (sheetNos.length == 4) {
            String dateNum = sheetNos[2];
            int currentDateNum = new java.lang.Integer(dateNum).intValue();
            if (currentDateNum >= datelimit) {
                response.sendRedirect(request.getContextPath() + "/sheet/commonfault/commonfault.do?method=showHistoryPage&type=interface&userName=admin&sheetNo=" + sheetNo);
            } else {
                response.sendRedirect("http://10.25.2.113/sheet/commonfault/commonfault.do?method=showHistoryPage&type=interface&userName=admin&sheetNo=" + sheetNo);
            }
        }
    }

%>