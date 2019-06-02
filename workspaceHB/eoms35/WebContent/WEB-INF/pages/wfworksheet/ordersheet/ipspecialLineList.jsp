<%@ include file="/common/extlibs.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<!-- 查询控制 -->
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if(ifReference.equals("")) ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
    
    String serviceType = StaticMethod.nullObject2String(request.getParameter("serviceType"));
	String taskNamePara = request.getParameter("taskName");
	System.out.println("@@taskName ip list="+taskNamePara);
	String status = StaticMethod.nullObject2String(request.getParameter("status"));
	String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
	String sheetType = StaticMethod.nullObject2String(request.getAttribute("sheetType"));
	System.out.println("@@taskStatus"+taskStatus);
	String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
	String addr = StaticMethod.nullObject2String(request.getParameter("addr"));
	
	String url = "";
	String head = "http://10.25.2.74:8899";
	String host = StaticMethod.nullObject2String(request.getRequestURL());
	if(host.indexOf("10.131.62") <0 ){
		head = "http://10.25.2.74:8899";
	}

	String serviceTypeEnum  = "";
	if("ApnTask".equals(taskNamePara))
		serviceTypeEnum = "9";
	else if("CityNetTask".equals(taskNamePara)||"CityTask".equals(taskNamePara))
		serviceTypeEnum = "11";
	else if("GGSNTask".equals(taskNamePara))
		serviceTypeEnum = "16";
   %>
   
<script type="text/javascript">
	function preOccupyRes(id){
	
		var serviceTypeEnum = '6';
		window.open("<%=head%>/Fulfillment/resConfig/resAllotInitForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
	}
	function viewOccupyRes(id){

		var serviceTypeEnum = '6';
		window.open("<%=head%>/Fulfillment/res/config/result/resConfigResultForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
	}
	function popupIrmsPreSurvey(id){

		var serviceTypeEnum = '<%=serviceTypeEnum%>';
		var urls ="${app}/businessupport/product/ipspeciallines.do?method=edit&id="+id+"&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&addr=<%=addr%>";
		window.open(urls);
	}
</script>
<display:table name="ipspecialList" cellspacing="0" cellpadding="0"
		id="list" pagesize="${pageSize}" class="listTable ipspecialList"
		export="false" requestURI="ordersheets.do"
		sort="external" size="total" partialList="true" style="width:100%"
		>
		<display:column headerClass="sortable" title="详细信息" >
		<a onclick="window.open('${app}/businessupport/product/ipspeciallines.do?method=showDetail&orderId=<%=orderId%>&isView=1&taskStatus=<%=taskStatus%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara %>&id=<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">查看详细信息</a>
		</display:column>
		<%if((taskNamePara.equals("CityTask")||taskNamePara.equals("ApnTask")||taskNamePara.equals("CityNetTask"))&&!taskStatus.equals("2")){%>
			<display:column media="html" title="资源预占">		   
	           <a onclick="preOccupyRes('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">资源预占</a>
    		</display:column>  
	    <%}%>
	     <%if(taskNamePara.equals("CityTask")||taskNamePara.equals("ApnTask")||taskNamePara.equals("CityNetTask")||status.equals("1")){%>
	    	<display:column media="html" title="查看资源使用情况">		   
	           <a onclick="viewOccupyRes('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">查看资源使用情况</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("UserTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="客户端勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">客户端勘查</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("AccessTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="接入点勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">接入点勘查</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TransferlTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输线路勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输线路勘查</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TransfereTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输容量勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输容量勘查</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("ProjectDealTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="最后一公里施工">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">最后一公里施工</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TrasitionTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="修改电路信息">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">修改电路信息</a>
	    	</display:column>  
		<%}%>
        <display:column property="businessBandwidth" headerClass="sortable" title="业务带宽"/>
		<display:column property="businessAmount" headerClass="sortable" title="业务数量(传输条数)" />
		<display:column property="ipNeedNum" headerClass="ipNeedNum" title="IP地址-互联需求数量(个)"/>
		<display:column property="ipServerNeedNum" headerClass="ipServerNeedNum" title="IP地址-客户应用服务需求数量（个）" />


        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/>
 
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>


