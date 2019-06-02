<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.net.URLEncoder"%>

<%
		String excelFilePath=request.getAttribute("excelFilePath").toString();
		String excelFileName=request.getAttribute("excelFileName").toString();
		String dataUrl=request.getAttribute("dataUrl").toString();
	  String path=request.getContextPath();
		String aurl=path+"/flowcontrol/sheetsecondloading.jsp";
		String burl=path+"/flowcontrol/deptsecondloading.jsp";
    
    request.setCharacterEncoding("UTF-8");
     List  statlist=(List)request.getAttribute("STATLIST");
	   int size= statlist.size();
	  String xml="";
	  StringBuffer xmlBuf = new StringBuffer();
	  xmlBuf.append("<graph caption='流程数据第一层监控饼图'  baseFontSize='12' showNames='1'  showBorder='1' decimalPrecision='0'>");
	  for(int t=0;t<size;t++){
	       Map  map=(Map)statlist.get(t);
	        String f1=(String)map.get("f1");
	        String f2=(String)map.get("f2");
	        String f3=(String)map.get("f3");
	        String f4=(String)map.get("f4");
	        String f5=(String)map.get("f5");
	        String f6=(String)map.get("f6");
	        String f7=(String)map.get("f7");
	        String dataUrlf1=URLEncoder.encode(dataUrl+"&fieldId=f1","UTF-8");
	        String dataUrlf2=URLEncoder.encode(dataUrl+"&fieldId=f2","UTF-8");
	        String dataUrlf3=URLEncoder.encode(dataUrl+"&fieldId=f3","UTF-8");
	        String dataUrlf4=URLEncoder.encode(dataUrl+"&fieldId=f4","UTF-8");
	        String dataUrlf5=URLEncoder.encode(dataUrl+"&fieldId=f5","UTF-8");
	        String dataUrlf6=URLEncoder.encode(dataUrl+"&fieldId=f6","UTF-8");
	        String dataUrlf7=URLEncoder.encode(dataUrl+"&fieldId=f7","UTF-8");
	        
	       xmlBuf.append("<set name='待受理'  value='"+f1+"'  link='n-"+dataUrlf1+"'   />");
	       xmlBuf.append("<set name='待处理'  value='"+f2+"'  link='n-"+dataUrlf2+"' />");
	       xmlBuf.append("<set name='待归档'  value='"+f3+"'  link='n-"+dataUrlf3+"' />");
	       xmlBuf.append("<set name='2小时超时预警'  value='"+f4+"'  link='n-"+dataUrlf4+"' />");
	       xmlBuf.append("<set name='1小时超时预警'  value='"+f5+"'  link='n-"+dataUrlf5+"' />");
	       xmlBuf.append("<set name='30分钟超时预警'  value='"+f6+"'  link='n-"+dataUrlf6+"' />");
	       xmlBuf.append("<set name='已超时工单'  value='"+f7+"'  link='n-"+dataUrlf7+"' />");
	    }
	   xmlBuf.append("</graph>");
	   xml=xmlBuf.toString();
	   System.out.println(xml);
	   String flash_path = path+ "/FusionCharts/Charts/Pie3D.swf";

%>
<link rel="stylesheet" href="${app}/FusionCharts/Contents/Style.css" type="text/css" />
<script language="JavaScript" src="${app}/FusionCharts/JSClass/FusionCharts.js"></script>
<style type="text/css">
#tabs {
	width:100%;
}
#tabs .x-tabs-item-body {
	display:none;
	padding:10px;
}

</style>
  <script language="JavaScript">
	 var Tabs = {
    init : function(){
        var tabs = new Ext.TabPanel('tabs');
        tabs.addTab('first','流程数据监控');
        tabs.addTab('info', '饼状图形报表');
        tabs.activate('first');
     }
   }
  Ext.onReady(Tabs.init, Tabs, true);	
	 	
	 	
  Ext.onReady(function(){
	   colorRows('list-table');
    }) 
    
       

  </script>
  
  
     
<div id="tabs">
	
<div id="first" class="tab-content">

			
<div id="sheet-list">
	<div class="list-title"></div>

<table class="listTable" > 
  <tr class="tr_show" >    
  	<td nowrap align="center" colspan="9" width="100%"><b>工单监控页面(第一层)</b></td>		      
  </tr>
	<tr class="tr_show">
			<td nowrap align="center"  ><b>待受理</b></td>		
			<td nowrap align="center"  ><b>待处理</b></td>		
			
	  	<td nowrap align="center"  ><b>待归档</b></td>		
	  	<td nowrap align="center"  ><b>下一层工单</b></td>		
	  	<td nowrap align="center"  ><b>下一层部门</b></td>		
			<td nowrap align="center"  ><b><font color="##0000FF">120分钟超时预警</font></b></td>		
			<td nowrap align="center"  ><b><font color="#D7DF01">60分钟超时预警</font></b></td>		
			<td nowrap align="center"  ><b><font color="#ff0000">30分钟超时警告</font></b></td>	
			<td nowrap align="center"  ><b>已超时工单数</b></td>			
		
	</tr>
<%  
	  
	   for(int j=0;j<size;j++){
	        Map  map=(Map)statlist.get(j);
	        String f1=(String)map.get("f1");
	        String f2=(String)map.get("f2");
	        String f3=(String)map.get("f3");
	        String f4=(String)map.get("f4");
	        String f5=(String)map.get("f5");
	        String f6=(String)map.get("f6");
	        String f7=(String)map.get("f7");
  
        out.print("<tr >");
            
	         out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f1' target=\"_blank\">");
                  out.print(f1);
               out.print("</a>");
            out.print("</td>");
	          out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f2' target=\"_blank\">");
                  out.print(f2);
               out.print("</a>");
            out.print("</td>");
            out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f3' target=\"_blank\">");
                  out.print(f3);
               out.print("</a>");
            out.print("</td>");
            
             out.print("<td align=\"center\">");
	             out.print("<a href=\'"+aurl+"\' target=\"_blank\">");
                  out.print("进入");
               out.print("</a>");
            out.print("</td>");
            
             out.print("<td align=\"center\">");
	             out.print("<a href=\'"+burl+"\' target=\"_blank\">");
                  out.print("进入");
               out.print("</a>");
            out.print("</td>");
            
           out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f4' target=\"_blank\">");
                  out.print(f4);
               out.print("</a>");
            out.print("</td>");
	          out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f5' target=\"_blank\">");
                  out.print(f5);
               out.print("</a>");
            out.print("</td>");
            out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f6' target=\"_blank\">");
                  out.print(f6);
               out.print("</a>");
            out.print("</td>"); 
            out.print("<td align=\"center\">");
	             out.print("<a href=\'"+dataUrl+"&fieldId=f7' target=\"_blank\">");
                  out.print(f7);
               out.print("</a>");
            out.print("</td>");             
             
	      out.print("</tr>"); 
	     
	      }
	     
	    %>
	
	 <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >指标说明:(按次数表示按同一部门去重复单)</td>		      
  </tr>
  <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >1、所有单元格显示当前对应的工单数;</td>		      
  </tr>
  <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >2、"待受理"，指派发后未受理的工单；</td>		      
  </tr>
   <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >3、"待处理"，指受理后未回复的工单；</td>		      
  </tr>
   <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >4、"待归档"，指回复后未归档的工单；</td>		      
  </tr>
   <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >5、"120分钟超时预警"，指距回复时限还有60～120分钟的工单；</td>		      
  </tr>
   <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >6、"60分钟超时预警"，指距回复时限还有30～60分钟的工单；</td>		      
  </tr>
   <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >7、"30分钟超时警告"，指距回复时限还有0～30分钟的工单；</td>		      
  </tr>
   <tr class="tr_show" >    
  	<td nowrap align="left" colspan="9" >8、"已超时工单数"，指已超时未回复的工单。</td>		      
  </tr> 
  
       </table>    
							
 <br>

				 	<form method="post" action="${app}/statisticfile/download.jsp" >
		           <input type="hidden" name="excelFilePath" value="<%=excelFilePath%>"/>
		           <input type="hidden" name="excelFileName" value="<%=excelFileName%>"/>
		         <input type="submit" name="fileNamesubmit" value="导出Excel" />
		      </form>

	          
	
			</div>
		</div>

	<div id="info">
  	
		<table align="center">
 	
   		<tr>
   			<td>
		<jsp:include page="/FusionCharts/Includes/FusionChartsHTMLRenderer.jsp" flush="true">
					<jsp:param name="chartSWF" value="<%=flash_path%>"/>
				 	<jsp:param name="strXML" value="<%=xml%>" />
					<jsp:param name="chartId" value="myFirst" /> 
					<jsp:param name="chartWidth" value="1000" />
					<jsp:param name="chartHeight" value="450" /> 
					<jsp:param name="debugMode" value="false" />
			   </jsp:include>
   			</td>
   		</tr>
   </table>

    
   </div>
 
 </div>
	
	
	
 
<br>
<%@ include file="/common/footer_eoms.jsp"%>
