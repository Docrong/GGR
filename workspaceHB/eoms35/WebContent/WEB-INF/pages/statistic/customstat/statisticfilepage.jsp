<%@ page language="java" import="java.util.*,java.util.List" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ include file="/common/extlibs.jsp"%>

	根据订制信息统计出来的结果如下
	<br></br>
	
	<%
		String total = String.valueOf(request.getAttribute("total"));
		int pageSize = Integer.parseInt(String.valueOf(request.getAttribute("pageSize")));
		int estListSize = ((List)request.getAttribute("estList")).size();
		
		//out.println(${eoms:a2u("SHow Stat Detailed")});
		//out.println(" total :" + total);
		//out.print("</br>");
		//out.println("pageSize :" + pageSize);
		//out.print("</br>");
		//out.println("estListSize :" + estListSize);
		//out.print("</br>");
	 %>
	 
	 <%
		String reUrl = String.valueOf(request.getRequestURL()) + "?method=statistFileInfo";
		//out.println(reUrl);
 	%>
	 
	 
<script type="text/javascript">
Ext.onReady(function(){
	colorRows('list-table');
})
function openSheet(url){
	
	//alert(url);
	
	if(doConfirm("你确定删除吗？"))
	{
		if(parent.frames['north'])
		{
			parent.frames['north'].location.href = url;
		}
		else
		{
			location.href = url;
		}
	}
	else
	{
		return flase; 
	}
}

	function doConfirm(str)
	{ 
		if(confirm(str))
		{
			return true; 
		} 
		else
		{ 
			return flase; 
		} 
	}
	
</script>
 
< bean:define id="url" value="stat.do" />
<fmt:bundle basename="config/statistic/base-config/ApplicationResources-statistic">
	<display:table name="estList" cellspacing="0" cellpadding="0"
		id="process" pagesize="${pageSize}" class="table estList"
		export="false" requestURI="stat.do"
		sort="list" size="total" partialList="true"
		decorator="com.boco.eoms.commons.statistic.customstat.util.BaseStatFileListDisplaytagDecoratorHelper">

	<display:column headerClass="sortable" titleKey="list.no"
	 sortable="true"><%=pageContext.getAttribute("process_rowNum")%></display:column>
	<%--  
	<display:column property="subscriberDeptId" sortable="true"
		headerClass="sortable" titleKey="custom.department" />
--%>
	<display:column property="subscriberId" sortable="true"
		headerClass="sortable" titleKey="custom.person" />
		
	<display:column property="subscriberId" sortable="true"
		headerClass="sortable" titleKey="custom.subscibeid"/>

	<!-- display:column property="fileName" sortable="true"
		headerClass="sortable" titleKey="statfile.fileName" /-->

	<display:column property="statName" sortable="true"
		headerClass="sortable" titleKey="statfile.statName" />
	
	<display:column property="reportType" sortable="true"
		headerClass="sortable" titleKey="custom.reportType" />
		
	<display:column property="reportInfo" sortable="true"
		headerClass="sortable" titleKey="statfile.reportInfo" />
	
	<%--
	<display:column property="beginTime" sortable="true"
		headerClass="sortable" titleKey="statfile.beginTime" 
		format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		
	<display:column property="endTime" sortable="true"
		headerClass="sortable" titleKey="statfile.endTime" 
		format="{0,date,yyyy-MM-dd HH:mm:ss}" />
	 */
	 --%>	
	 
	<display:column property="saveTime" sortable="true"
		headerClass="sortable" titleKey="statfile.saveTime" />
	
	<display:column property="checkTime" sortable="true"
		headerClass="sortable" titleKey="statfile.checkTime" />
		
	<display:column property="checked" sortable="true"
		headerClass="sortable" titleKey="statfile.checked" />
		
	<display:column property="readedState" sortable="true"
		headerClass="sortable" titleKey="statfile.readedState" />
		
	<display:column property="showDetail" sortable="true"
		headerClass="sortable" titleKey="statfile.showDetail" />
		
	<display:column property="showDelete" sortable="true"
		headerClass="sortable" titleKey="statfile.showDelete" />
	
	<%-- 
	<display:column property="htmlFilePath" sortable="true"
		headerClass="sortable" title="htmlFilePath" />
	
	<display:column property="excelFilePath" sortable="true"
		headerClass="sortable" title="excelFilePath" />
 
	<display:column property="sendtime" sortable="true"
		headerClass="sortable" titleKey="list.sendtime"
		format="{0,date,yyyy-MM-dd HH:mm:ss}" /
		--%>

      
	</display:table>
</fmt:bundle>
	 

<%@ include file="/common/footer_eoms.jsp"%>
