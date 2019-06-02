<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page	import="com.boco.eoms.commons.statistic.base.config.model.*" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>

<%@page import="com.boco.eoms.commons.statistic.base.config.model.report.ReportDefine"%>
<%@page import="com.boco.eoms.commons.statistic.base.config.model.report.ReportSummary"%>
<%@page import="com.boco.eoms.commons.statistic.base.config.model.report.ReportField"%>
<%@page import="com.boco.eoms.commons.statistic.base.util.*"%>
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})

</script>

<%
	String uri = request.getRequestURI();
//			System.out.println(request.getServletPath());
	uri = (String)request.getAttribute("kpiURI");

	System.out.println(uri);

	String listUrl = uri + "?method=showStatisticSheetList";

	List listResult = (List) request.getAttribute("listResult");

	Map kpiParam = (Map) request.getAttribute("kpiParam");

//	KpiDefine kpiDefine = (KpiDefine) request.getAttribute("kpiDefine");

  ReportDefine reportDefine = (ReportDefine)request.getAttribute("reportDefine");
  
	ReportSummary[] reportSummaries = reportDefine.getReportSummaries();

	java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
%>

<div id="sheet-list">
<div class="list-title"><%=reportDefine.getCaption() %></div>
<div class="list">
<table cellspacing="0" cellpadding="0" border="0" id="list-table">
<tbody>	

<%if (reportDefine.getUseJspHead()!=null&&!reportDefine.getUseJspHead().equals("")) {
%>
<%--@ include file="<%=kpiDefine.getUseJspHead() %>" --%>

<%} else {%>          
      <!-- head row 表头-->
			<!-- view-row-span-max 定义每行数据用几行表格显示			 -->      
<%		for(int iRowHead=0;iRowHead< reportDefine.getViewRowSpanMax();iRowHead++){
%>      
      
			<tr id="tr<%=iRowHead %>" class="header">
<%			if(iRowHead==0){
%>
				<td nowrap align="center" rowspan="<%=reportDefine.getViewRowSpanMax() %>" width="10%">
					<bean:message bundle="statistic" key="list.no" />
				</td>
				<!-- summary field header-->
<%			

					for (int iSummary = 0; iSummary < reportSummaries.length; iSummary++) {
						ReportSummary reportSummary = reportSummaries[iSummary];
%>
        <td nowrap align="center" rowspan="<%=reportDefine.getViewRowSpanMax()%>"  width="<%=reportSummary.getViewWidth()%>"><%=reportSummary.getViewName()%></td>

<%}
}
%>
				
<!-- 字段定义, 生成表头字段 -->
<%			for (int iReportField = 0; iReportField < reportDefine.getReportFields()
						.length; iReportField++) {
					ReportField reportField = reportDefine.getReportFields()[iReportField];
					
					if((reportField.getViewRowIndex()-1) == iRowHead){
%>
				<!-- data field header -->
				<td nowrap align="center" colspan="<%=reportField.getViewColumnSpan() %>" rowspan="<%=reportField.getViewRowSpan() %>" width="<%=reportField.getViewWidth() %>"><%=reportField.getViewName()%></td >

<%
		}
	}


			%>
			</tr><!-- row head end -->
<%} 
}%>		<!-- end else  -->	

<!-- ****************************************************** 
数据部分
data rows 此处开始遍历结果集,listResult是全集(共有多少行),每个query的结果集是一个子集,
						<tr>						
-->
<%
	if (listResult != null) {

		//定义合并行参数,汇总字段
		List listSummaryRowspan = new java.util.ArrayList();
		for (int iSummary = 0; iSummary < reportSummaries.length; iSummary++) {
			int[] i = {0,1};//i[0]是需要加<td rowspan>的行,i[1]是rowspan=?合并数量
			listSummaryRowspan.add(i);
		}
		
		//此处开始遍历汇总字段结果集,也就是数据全集
		for (int iRow = 0; iRow < listResult.size(); iRow++) {
			
			String summaryData = "";//封装 为表格数字加url时用到的参数
			
			Map rowData = (Map)listResult.get(iRow);//一行数据,包含汇总字段和统计字段
			
			String summaryKey = (String)rowData.get("summaryKey"); //每行汇总字段的标识key

			//合并行
			for(int i=0; i<listSummaryRowspan.size();i++){
				if(iRow==((int[])listSummaryRowspan.get(i))[0]+
				((int[])listSummaryRowspan.get(i))[1]){
					((int[])listSummaryRowspan.get(i))[0]=iRow;
				}
			}
			
			%>		
			<!-- view-row-span-max 定义每行数据用几行表格显示			 -->      
<%		for(int iRowHead=0;iRowHead< reportDefine.getViewRowSpanMax();iRowHead++){ %>      
			<tr>
<%			if(iRowHead==0){ %>
				<td rowspan="<%=reportDefine.getViewRowSpanMax() %>"><%=iRow %></td><!-- 序号 -->
					
<%		

					//遍历汇总字段个数,每个字段一个<td>
					for (int iSummary = 0; iSummary < reportSummaries.length; iSummary++) {
						
						ReportSummary reportSummary = reportSummaries[iSummary];
						//iSummaryData 汇总字段数值.
						String iSummaryData = (String)rowData.get(reportSummary.getId());
						
						if(iSummary>0) 
							summaryData+=",";
						summaryData += "'"+iSummaryData+"'";
						
						//合并行,<tr>合并 begin
						
						//当有多个汇总字段时(多个<td>)才进行合并行<tr>
						if(reportSummaries.length>1 && iSummary<reportSummaries.length-1 && reportDefine.isEnableSummarySpan()){
							if(iRow==((int[])listSummaryRowspan.get(iSummary))[0]){
								int rowspanSum =1;
								//遍历剩余全集数据,计算重复数据数量,得到<td rowspan=?的值>
								for(int iNextRow=iRow+1; iNextRow<listResult.size();iNextRow++){
									Map rowData_next = (Map)listResult.get(iNextRow);
									String nextData = (String)rowData_next.get(reportSummary.getId());

									if(!nextData.equals(iSummaryData)){
										//找到下一个不一样的数据,计算当前行到其之间的行数rowspanSum,也就是需要合并的行数
										rowspanSum=iNextRow-iRow;
										((int[])listSummaryRowspan.get(iSummary))[1]=rowspanSum;
										break;
									}
									if(iNextRow==listResult.size()-1){
										rowspanSum=listResult.size()-iRow;
										((int[])listSummaryRowspan.get(iSummary))[1]=rowspanSum;										
									}
									
								}

								
%>			
				<td rowspan=<%= (rowspanSum*reportDefine.getViewRowSpanMax())%>>
					<eoms:id2nameDB id="<%= iSummaryData%>" beanId="<%=reportSummary.getCodeToName() %>"/>
				</td>
<%
							}
						//合并行 end
						}else{%>					
				<td rowspan="<%=reportDefine.getViewRowSpanMax() %>">
					<eoms:id2nameDB id="<%= iSummaryData%>" beanId="<%=reportSummary.getCodeToName() %>"/>
				</td>
<%				}
				}
			}
%>				

<!-- 此处开始填充query的结果集, <td> -->
<%							

					for (int iReportField = 0; iReportField < reportDefine.getReportFields().length; iReportField++) {
							ReportField reportField = (ReportField)reportDefine.getReportFields()[iReportField];

							if((reportField.getViewRowIndex()-1) == iRowHead){

								String fieldData = StaticMethod.null2String((String)
									KpiUtil.getExpressionResult(rowData,reportField.getExpression())
									,"0");
									
								String prefix = reportField.getPrefix()!=null?reportField.getPrefix():"";
								String postfix = reportField.getPostfix()!=null?reportField.getPostfix():"";
								
								fieldData =prefix	+fieldData+postfix;
									
								System.out.println(reportField
										.getExpression()+","+fieldData	);
										
							%>
				<td colspan="<%=reportField.getViewColumnSpan() %>" rowspan="<%=reportField.getViewRowSpan() %>" >
<%if (!fieldData.equals("0")) {
								
%>
				<a href="<%=listUrl%>&kpiDefineName=<%=reportDefine.getKpiDefineName()%>&queryDefineName=<%=""%>&fieldAsName=<%=reportField.getExpression()%>&summaryData=<%=summaryData %>" target="sheetlist"><%=fieldData%></a>
<%} else {%>0<%}%>				
				</td>
<%											}
						}
%></tr><%
					}
				}
			}%>

</tbody>
</table>

</div>
</div>

<%@ include file="/common/footer_eoms.jsp"%>
