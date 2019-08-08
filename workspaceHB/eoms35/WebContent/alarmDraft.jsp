<%@ include file="/common/taglibs.jsp" %>
<%
    String userName = request.getParameter("userName");
    String sheetNo = request.getParameter("sheetNo");
    String url = request.getContextPath() + "/sheet/commonfault/commonfault.do?method=showInterfaceDraftPage&type=interface&userName=" + userName + "&sheetNo=" + sheetNo;
    //response.wait(900000000);
    //Response.write("<script>setTimeout(\"location.href='"+url+"'\",3);</script>") ;
    //response.sendRedirect(request.getContextPath()+"/sheet/commonfault/commonfault.do?method=showInterfaceDraftPage&type=interface&userName="+userName+"&sheetNo="+sheetNo);
%>
<script>
    setTimeout("location.href='<%=url%>'", 3000);
    //setTimeout("location.href='http://sports.cn.yahoo.com/'",3000);
</script>