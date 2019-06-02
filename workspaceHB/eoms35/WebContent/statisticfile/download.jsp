<%@page pageEncoding="utf-8" contentType="text/html;charset=GB2312"%><%@page import="java.net.*,java.io.*"%><%
//try {
	String ExcelFilePath = request.getParameter("excelFilePath");
	String excelFileName = request.getParameter("excelFileName");
	//excelFileName = new String(excelFileName.getBytes("iso8859-1"),"GB2312");
	
	response.reset();
	response.setContentType("application/x-download");
	excelFileName = URLEncoder.encode(excelFileName, "UTF-8");
	response.setHeader("Content-disposition", "attachment; filename="+ excelFileName);
	System.out.println("==="+ExcelFilePath);
	javax.servlet.RequestDispatcher dispatcher = application.getRequestDispatcher(ExcelFilePath);
	if (dispatcher != null) 
	{
		dispatcher.forward(request, response);
	}
	
	response.flushBuffer();
%>