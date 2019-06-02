<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.commons.statistic.base.config.graphic.*,java.util.*"%>

<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})

</script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String swfpath = (String)request.getAttribute("swfpath");
String xmlpath = (String)request.getAttribute("xmlpath");
String title = (String)request.getAttribute("title");
List linesample = (List)request.getAttribute("linesample");
List columnsample = (List)request.getAttribute("columnsample");
%>

<div id="sheet-list">
	<div class="list-title"></div>
	<div class="list">
	
	<!-- 图形报表 -->
		<style type="text/css">
  		#Layer1 {
			position:absolute;
			left:20px;
			top:55px;
			width:100%;
			height:35px;
			z-index:2090;
			background-color: #FFFFFF;
			visibility: visible;
		}
  	</style>
	 
	 <div id="Layer1">
    	<br>
    	<p align="center"><%=title%></p>
     </div>
    
 	<!-- <div style="height:400px;border:1px solid red"> -->
	<div style="height:500px">
	
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
	</div>

	<div style="position:absolute;right:50px;top:90px;width:200px;height:35px;">
		<%
			if(columnsample!=null || linesample!=null){
		 %>
		<caption><font size="2">${eoms:a2u("图例")}</font></caption>
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

   <!-- 统计报表 -->
    <%
    	String HtmlString = (String)request.getAttribute("html");
    	
    	out.println(HtmlString);
    	 	
     %>
     
     <!-- 导出Excel -->
 	<form method="post" action="${app}/statisticfile/download.jsp" >
		
		<input type="hidden" name="excelFilePath" value="<%=(String)request.getAttribute("excelFilePath") %>">
		<input type="hidden" name="excelFileName" value="<%=(String)request.getAttribute("excelFileName") %>">
		<input type="submit" name="fileNamesubmit" value="<bean:message bundle="statistic" key="button.export"/>" >
		<!-- onclick="window.open('about:hello','_blank','left=0,top=0')" -->
	</form>
	
	</div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>