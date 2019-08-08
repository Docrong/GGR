<%@ page contentType="text/html;charset=gb2312"
         import="com.boco.eoms.gzjh.util.*,com.boco.eoms.common.util.StaticMethod,java.lang.*" %>

<%
    String excelfile = (String) request.getAttribute("excelfilename");
    try {
        SmartUpload su = new SmartUpload();
        su.initialize(pageContext);
        su.setContentDisposition(null);

        su.downloadFile("/netopt/excel/" + excelfile, "", excelfile);
    } catch (Exception e) {
    }
%>
