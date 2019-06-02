<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ page import="com.boco.eoms.common.fileupload.SmartUpload" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<%
try
{
  String path = StaticMethod.nullObject2String(request.getParameter("path"));
  String fileName = StaticMethod.nullObject2String(request.getParameter("fileName"));
  SmartUpload su = new SmartUpload();
  su.initialize(pageContext);
  su.setContentDisposition(null);
  System.out.println(path+fileName);
  su.downloadFile("sparepart/"+path+"/exportModel.xls","application/vnd.ms-excel","sparepartModule.xls");
}
catch(Exception e)
{
   e.printStackTrace();
}
%>
<%@ include file="/common/footer_eoms.jsp"%>

