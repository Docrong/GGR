<%@ page language="java" errorPage="/error.jsp" pageEncoding="Gb2312" contentType="text/html;charset=Gb2312" %>
<%@page import="com.boco.eoms.sheet.base.util.SheetStaticMethod" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%
    String type = StaticMethod.null2String(request.getParameter("type"));
    String cityCnName = StaticMethod.null2String(request.getParameter("cityCnName"));
    String url = "";
    String head = "http://10.131.62.207:8089";

    String sitecode = StaticMethod.null2String(request.getParameter("sitecode"));
    String sitename = StaticMethod.null2String(request.getParameter("sitename"));
    String equecode = StaticMethod.null2String(request.getParameter("equecode"));
    String equename = StaticMethod.null2String(request.getParameter("equename"));
    System.out.println("sitecode:" + sitecode + ";sitename:" + sitename + ";equecode:" + equecode + ";equename:" + equename);
    String host = StaticMethod.nullObject2String(request.getRequestURL());
    if (host.indexOf("10.131.62") < 0) {
        head = "http://10.25.2.74:8899";
    }

    if (type.equals("1")) {
        url = head + "/Tnms_GIS_SX/flex/Irms_SX_index.jsp?username=admin&password=98A808DB6A91E68F&address=" + cityCnName;
    }

    if (type.equals("2")) {
        url = "http://10.32.2.18:8089/Tnms_GIS_SX/flex/irms_access/FreeFibers.jsp?sitecode=" + sitecode + "&sitename=" + sitename + "&equecode=" + equecode + "&equename=" + equename + "";
        System.out.println("URL" + url);
        //url = "http://10.32.2.18:8089/Tnms_GIS_SX2/flex/irms_access/FreeFibers.jsp?sitecode=SITE-8a0284bd11b245fd0111b25061bc0063&sitename="+sitename+"&equecode=FIBER_JOINT_BOX-8a808090132e927201134c06eb99071d&equename="+equename+"";
    }
%>
<html>
<head>
</head>
<body>
<iframe src="<%=url%>" width="100%" height="100%">
</iframe>
</body>

</html>