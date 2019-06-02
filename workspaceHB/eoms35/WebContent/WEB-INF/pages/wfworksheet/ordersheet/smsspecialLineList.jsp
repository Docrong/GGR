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
	System.out.println("@@taskName=lauguageList:"+taskNamePara);
	String status = StaticMethod.nullObject2String(request.getParameter("status"));
	String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
	String orderId = StaticMethod.nullObject2String(request.getAttribute("orderId"));

	String sheetType = StaticMethod.nullObject2String(request.getParameter("sheetType"));
	String addr = StaticMethod.nullObject2String(request.getParameter("addr"));
	
	String isShowSms = StaticMethod.nullObject2String(request.getAttribute("isShowSms"));
	
	String duanXinCaiXinShowType = StaticMethod.nullObject2String(request.getAttribute("duanXinCaiXinShowType"));
	
	System.out.println("@@duanXinCaiXinShowType"+duanXinCaiXinShowType);
	String specialtyType = StaticMethod.nullObject2String(request.getParameter("specialtyType"));
		System.out.println("@@list specialtyType"+specialtyType);
	System.out.println("@@isShowSms"+isShowSms);
	System.out.println("@@la list=orderid"+orderId);

   %>
   
<script type="text/javascript">

	function popupIrmsPreSurvey(id,value){

		
		var urls ="${app}/businessupport/product/smsspeciallines.do?method=edit&id="+id+"&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&specialtyType=<%=specialtyType %>"
				+"&addr=<%=addr%>"
				+"&taskType="+value;
		//window.open("http://10.131.62.172:8080/Fulfillment/res/config/result/resConfigResultForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
		window.open(urls);
	}
</script>
<display:table name="smsspeciallineList" cellspacing="0" cellpadding="0"
		id="list" pagesize="${pageSize}" class="listTable gprsspecialList"
		export="false" requestURI="ordersheets.do"
		sort="external" size="total" partialList="true" style="width:100%"
		>
		<display:column headerClass="sortable" title="详细信息" >
															  
		<a onclick="window.open('${app}/businessupport/product/smsspeciallines.do?method=showDetail&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&id=<%=((HashMap)pageContext.getAttribute("list")).get("id")%>&isShowSms=<%=isShowSms %>&specialtyType=<%=specialtyType %>');" style="cursor:pointer">查看详细信息</a>
		</display:column>
		<%if( taskNamePara.equals("UserTask")&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="客户端勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>','inputUser');" style="cursor:pointer">客户端勘查</a>
	    	</display:column>  
		<%}%>
		<%if((taskNamePara.equals("AccessTask"))&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="接入点勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">接入点勘查</a>
	    	</display:column>  
		<%}%>
		
		<%if((taskNamePara.equals("TransferlTask"))&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输线路勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输线路勘查</a>
	    	</display:column>  
		<%}%>
		<%if((taskNamePara.equals("CaiXinTask")||taskNamePara.equals("CityTask"))&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="行业网关接入勘察">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>','inputLanguage');" style="cursor:pointer">行业网关接入勘察</a>
	    	</display:column>  
		<%}%>
		<%if((taskNamePara.equals("TransfereTask"))&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输设备勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输设备勘查</a>
	    	</display:column>  
		<%}%>
		<%if((taskNamePara.equals("ProjectDealTask"))&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="最后一公里施工信息">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">最后一公里施工信息</a>
	    	</display:column>  
		<%}%>
		<%if(taskNamePara.equals("TrasitionTask") &&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="传输电路调度信息">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">传输电路调度信息</a>
	    	</display:column>  
		<%}%>
		<%if(specialtyType.equals("101230105")){ %>

   		<display:column headerClass="cableModem" title="彩信接入的方式">
		<eoms:id2nameDB id="${list.cableModem}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
	    <display:column headerClass="isSupportUpCaiXinStream" title="是否要支持上行彩信流量">
		<eoms:id2nameDB id="${list.isSupportUpCaiXinStream}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
	    <display:column property="scopeOfBusiness" headerClass="scopeOfBusiness" title="业务范围 " />
	    
	    <%}else if(specialtyType.equals("101230106")){ %>
	  
	    <display:column property="productType" headerClass="productType" title="产品类型 " />
	    <display:column property="productInstanceName" headerClass="productInstanceName" title="产品实例名称" />
	    <%} %>
        <display:setProperty name="export.excel" value="false"/>
        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/> 
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>


