<%@ include file="/common/extlibs.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if(ifReference.equals("")) ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
    
    String serviceType = StaticMethod.nullObject2String(request.getParameter("serviceType"));
	String taskNamePara = request.getParameter("taskName");
	String status = StaticMethod.nullObject2String(request.getParameter("status"));
	String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
	String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
	String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
	String addr = StaticMethod.nullObject2String(request.getParameter("addr"));
	
	System.out.println(taskNamePara);
	System.out.println(taskStatus);	
	
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
		var urls ="${app}/businessupport/product/transferspeciallines.do?method=edit&id="+id+"&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&addr=<%=addr%>";
		window.open(urls);
	}
</script>
    <display:table name="transferspecialList" cellspacing="0" cellpadding="0"
		id="list" pagesize="${pageSize}" class="listTable transferspecialList"
		export="false" requestURI="ordersheets.do"
		sort="external" size="total" partialList="true" style="width:100%"
		>
		<display:column headerClass="sortable" title="详细信息" >
		<a onclick="window.open('${app}/businessupport/product/transferspeciallines.do?method=showDetail&orderId=<%=orderId%>&isView=1&taskStatus=<%=taskStatus%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&id=<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">查看详细信息</a>
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
        <display:column property="portADetailAdd" headerClass="portADetailAdd" title="A端点详细地址" />
		<display:column property="portZDetailAdd" headerClass="portZDetailAdd" title="Z端点详细地址" />
		<display:column property="bandwidth" headerClass="bandwidth" title="带宽" />
		<display:column property="amount" headerClass="amount" title="数量" />

      <c:if test="${sheetType == 'resourceConfirm'}">
        <display:column  sortable="true" headerClass="sortable"  title="是否预占">  
           <eoms:id2nameDB id="${list.isBeforehandOccupy}" beanId="ItawSystemDictTypeDao" />
        </display:column>
      </c:if>
   </display:table>
<%@ include file="/common/footer_eoms.jsp"%>


