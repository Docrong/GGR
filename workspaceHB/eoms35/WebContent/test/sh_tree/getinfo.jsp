<%@ page contentType="text/html; charset=UTF-8" %>

<%
    response.setHeader("Cache-Control", "no-cache");

    String name = new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");

    out.print(name);
%>

