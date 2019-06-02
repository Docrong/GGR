<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.commons.statistic.flowcontrol.vo.FlowcontrolDetailVO"%>
<%@ page import="java.util.*,com.boco.eoms.common.util.*"%>


	<display:table name="estList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table estList"
		export="true" requestURI="stat.do"
		sort="list" size="total" partialList="true"
		decorator="">

	<display:column headerClass="sortable" title="编号"
	 sortable="true"><%=pageContext.getAttribute("process_rowNum")%></display:column>

  <display:column title="查看详情"  >
  <%
   FlowcontrolDetailVO vo= (FlowcontrolDetailVO)pageContext.getAttribute("process");
   String sheettype=vo.getSheettype();
   String mainid=vo.getMainid();
   String sheetid=vo.getSheetid();
   String url="";
   String sheettypename="";
   if("101010101".equals(sheettype)){
     url="/sheet/commonfault/commonfault.do?method=showDetailPage&sheetkey="+mainid;
     sheettypename="故障工单";
   }else{
  	 url="/sheet/complaint/complaint.do?method=showDetailPage&sheetkey="+mainid;
  	 sheettypename="投诉工单";
   }
    %>
	<a href='${app}<%=url%>' target="_blank"><%=sheettypename%></a>
	</display:column>
	
	

	<display:column property="title" sortable="true"
		headerClass="sortable" title="工单主题" />

	<display:column property="sheetid" sortable="true"
		headerClass="sortable" title="工单流水号" />

  <display:column property="sendtime" sortable="true"
		headerClass="sortable" title="派单时间" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
		
	  <display:column property="completetimelimit" sortable="true"
		headerClass="sortable" title="处理时限" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

	<display:setProperty name="export.pdf" value="false"/>
	<display:setProperty name="export.xml" value="false"/>
	<display:setProperty name="export.csv" value="false"/>  
	<display:setProperty name="export.rtf" value="false"/> 
	</display:table>

<%@ include file="/common/footer_eoms.jsp"%>
