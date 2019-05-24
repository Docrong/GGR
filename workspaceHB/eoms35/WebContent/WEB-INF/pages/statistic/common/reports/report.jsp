<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String swfpath = (String)request.getAttribute("swfpath");
String xmlpath = (String)request.getAttribute("xmlpath");
String title = (String)request.getAttribute("title");
List linesample = (List)request.getAttribute("linesample");
List columnsample = (List)request.getAttribute("columnsample");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Report page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--  不需要引用这2个js，是flash直接读取xml，解析绘制
	<script type="text/javascript" src="/AnyReport/js/AnyChart.js"></script>
	<script type="text/javascript" src="/AnyReport/js/AC_OETags.js"></script>
	 -->
	 
  	<style type="text/css">
  		#Layer1 {

			position:absolute;
		
			left:0px;
		
			top:0px;
		
			width:100%;
		
			height:35px;
		
			z-index:2090;
		
			background-color: #FFFFFF;
		
			visibility: visible;
		
		}
  		
  	</style>
  </head>
  
  <body>
  
    	<div id="Layer1">
    		<br>
    		<p align="center"><%=title%></p>
    	</div>
    	
    	<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" 
    			codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" 
    			width="100%" height="100%" align="middle">
	
			<param name="allowScriptAccess" value="sameDomain" id="AnyChart"/>
			
			<param name="movie" value="<%=swfpath%>" />
			
			<param name="FlashVars" value="XMLFile=<%=xmlpath%>">
			
			<param name="quality" value="high" />
			
			<param name="bgcolor" value="#ffffff" />
			
			<param name="wmode" value="transparent"/>
	
		</object>
		<br>
		
		<div style="position:absolute;right:50px;top:40px;width:200px;height:35px;">
		<%
			if(columnsample!=null || linesample!=null){
		 %>
		<caption><font size="2">图例</font></caption>
		<%
			}
		 %>
		<table>
		<%
			if(columnsample!=null){
			for(int i = 0; i< columnsample.size(); i++){
				HashMap hmp = (HashMap)columnsample.get(i);
				String name = (String)hmp.get("sample");
				String color = (String)hmp.get("color");
				out.println("<tr>");
				out.println("<td height=\"10\" width=\"20\" bgcolor=\"" + color.replaceAll("0x","#") + "\"></td><td><font size='2'>" + name + "</font></td>");
				out.println("</tr>");
			}
			}
			if(linesample!=null)
			for(int i = 0; i< linesample.size(); i++){
				HashMap hmp = (HashMap)linesample.get(i);
				String name = (String)hmp.get("sample");
				String color = (String)hmp.get("color");
				out.println("<tr>");
				out.println("<td height=\"10\" width=\"20\" bgcolor=\"" + color.replaceAll("0x","#") + "\"></td><td><font size='2'>" + name + "</font></td>");
				out.println("</tr>");
			}
			
		%>
		</table>
		</div>
  </body>
</html>
