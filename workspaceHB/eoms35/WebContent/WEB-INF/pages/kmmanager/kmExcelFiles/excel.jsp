<%/*
说明：适应weblogic的下载，修改了流读取。
@ page contentType="text/html;charset=gb2312"
import="com.boco.eoms.gzjh.util.*,com.boco.eoms.common.util.StaticMethod"
String excelfile = (String)request.getAttribute("excelfile");
String excelfilename = (String)request.getAttribute("excelfilename");
	SmartUpload su = new SmartUpload();
	su.initialize(pageContext);
	su.setContentDisposition(null);

	su.downloadFile(excelfile,"",excelfilename);
*/%><%@ page contentType="text/html;charset=gb2312" import="com.jspsmart.upload.*" %><%
String excelfile = (String)request.getAttribute("excelfile");
String excelfilename = (String)request.getAttribute("excelfilename");
// 新建一个SmartUpload对象
SmartUpload su = new SmartUpload();
// 初始化
su.initialize(pageContext);
// 设定contentDisposition为null以禁止浏览器自动打开文件，
//保证点击链接后是下载文件。若不设定，则下载的文件扩展名为
//doc时，浏览器将自动用word打开它。扩展名为pdf时，
//浏览器将用acrobat打开。
su.setContentDisposition(null);
// 下载文件
su.downloadFile(excelfile,"",excelfilename);
%>