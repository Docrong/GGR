<%@page contentType="text/html;charset=gb2312"%>
<%@ page import="com.boco.eoms.resmanage.entity.*"%>
<%@ page import="com.boco.eoms.resmanage.excel.*"%>
<%@ page import="com.jspsmart.upload.*,java.io.File"%>
<% 
	SmartUpload mySmartUpload=new SmartUpload();
	mySmartUpload.initialize(pageContext);
	mySmartUpload.setContentDisposition(null);
	String tabId=request.getParameter("sId");
    String cityId=request.getParameter("cityId");
	String separator=File.separator;
	//String fileName="resmanage" + separator + "upload" + separator+"tmp.xls";	
	String fileName = util.getUploadDir();
	fileName+=separator+"tmp.xls";
	//String tmp = File.getAbsolutePath();
	//out.println(tmp);
	excel xls = new excel();
	boolean b_success = xls.DBToExcel(tabId,fileName,cityId);
        if (b_success){
			mySmartUpload.downloadFile(fileName);
			//response.sendRedirect("..\\upload\\tmp.xls");
	}
%>