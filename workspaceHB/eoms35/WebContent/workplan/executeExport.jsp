<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>\
<%@ page import="java.util.*"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<%@ page import="com.boco.eoms.workplan.mgr.*"%>
<%@ page import="com.boco.eoms.workplan.vo.TawwpAddonsTableVO"%>
<%@ page import="com.boco.eoms.workplan.util.*"%>
<%@ page import="com.boco.eoms.workplan.model.TawwpExecuteContent"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<%

    	out.print(TawwpStaticVariable.rootDir+"<br>");
		ITawwpAddonsMgr tawwpAddonsMgr = (ITawwpAddonsMgr)ApplicationContextHolder.getInstance().getBean("tawwpAddonsMgr");
		ITawwpExecuteMgr tawwpExecuteMgr=(ITawwpExecuteMgr)ApplicationContextHolder.getInstance().getBean("tawwpExecuteMgr");
		List listExecute=tawwpExecuteMgr.listExecuteContent(" and formDataId is not null and formDataId<>''");
		for(int i=0;i<listExecute.size();i++){
		  TawwpExecuteContent  tawwpExecuteContent=(TawwpExecuteContent)listExecute.get(i); 
		 
			try{//tawwpExecuteContent.getFormDataId()
				tawwpAddonsMgr.exportAddonsToExcel("saveXML/50/"+tawwpExecuteContent.getFormDataId()+".xml","workplan/tawwpfile/execute");
				out.println(tawwpExecuteContent.getName()+"成功"+"<br>"); 
			}catch(Exception e){
			out.print(e);
			    out.println(tawwpExecuteContent.getId()+"<br>"); 
			}
			 
		}
		//tawwpAddonsMgr.exportAddonsToExcel("","");
		


 %>
  </body>
</html>
