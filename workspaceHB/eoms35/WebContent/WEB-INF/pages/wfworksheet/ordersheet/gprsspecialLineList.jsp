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
	String taskNamePara = StaticMethod.nullObject2String(request.getParameter("taskName"));
	System.out.println("@@taskName=gprsList:"+taskNamePara);
	String status = StaticMethod.nullObject2String(request.getParameter("status"));
	String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
	String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
	String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
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
	
		var serviceTypeEnum = '<%=serviceTypeEnum%>';
		window.open("<%=head%>/Fulfillment/resConfig/resAllotInitForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
	}
	function viewOccupyRes(id){

		var serviceTypeEnum = '<%=serviceTypeEnum%>';
		window.open("<%=head%>/Fulfillment/res/config/result/resConfigResultForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
	}
	function popupIrmsPreSurvey(id){

		var serviceTypeEnum = '<%=serviceTypeEnum%>';
		var urls ="${app}/businessupport/product/gprsspeciallines.do?method=edit&id="+id+"&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&addr=<%=addr%>";
		window.open(urls);
	}
</script>
<display:table name="gprsspecialList" cellspacing="0" cellpadding="0"
		id="list" pagesize="${pageSize}" class="listTable gprsspecialList"
		export="false" requestURI="ordersheets.do"
		sort="external" size="total" partialList="true" style="width:100%"
		>
		<display:column headerClass="sortable" title="详细信息" >
		<a onclick="window.open('${app}/businessupport/product/gprsspeciallines.do?method=showDetail&orderId=<%=orderId%>&sheetType=<%=sheetType%>&isView=1&taskStatus=<%=taskStatus%>&taskName=<%=taskNamePara%>&id=<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">查看详细信息</a>
		</display:column>
		<%if((taskNamePara.equals("CityTask")||taskNamePara.equals("ApnTask")||taskNamePara.equals("CityNetTask")||taskNamePara.equals("GGSNTask"))&&!taskStatus.equals("2")){%>
			<display:column media="html" title="资源预占">		   
	           <a onclick="preOccupyRes('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">资源预占</a>
    		</display:column>  
	    <%}%>
	     <%if(taskNamePara.equals("CityTask")||taskNamePara.equals("ApnTask")||taskNamePara.equals("CityNetTask")||taskNamePara.equals("GGSNTask")||status.equals("1")){%>
	    	<display:column media="html" title="查看资源使用情况">		   
	           <a onclick="viewOccupyRes('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">查看资源使用情况</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("AccessTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="接入点勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">接入点勘查</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TransfereTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输设备查勘">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输设备查勘</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TransferlTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输线路查勘">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输线路查勘</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("UserTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="用户端查勘">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">用户端查勘</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TrasitionTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输电路调度">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输电路调度</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("ProjectDealTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="最后一公里施工">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">最后一公里施工</a>
	    	</display:column>  
		<%}%>
    <display:column headerClass="transferInputMethod" title="传输接入方式">
		<eoms:id2nameDB id="${list.transferInputMethod}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
	    <display:column property="apnName" headerClass="apnName" title="APN名称" />
		<display:column headerClass="apnType" title="APN类型">
		<eoms:id2nameDB id="${list.apnType}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
	    <display:column headerClass="manYouProperty" title="漫游属性">
		<eoms:id2nameDB id="${list.manYouProperty}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
		<display:column headerClass="endIDDividType" title="终端IP地址分配方式">
		<eoms:id2nameDB id="${list.endIDDividType}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
	    <display:column headerClass="isDoubleGGSN" title="是否双GGSN">
		<eoms:id2nameDB id="${list.isDoubleGGSN}" beanId="ItawSystemDictTypeDao"/>
	    </display:column> 
	    
        <display:setProperty name="export.excel" value="false"/>
        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/> 
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>


