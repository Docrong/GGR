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
	
	String isShowLanguage = StaticMethod.nullObject2String(request.getAttribute("isShowLanguage"));
	System.out.println("@@la list=isShowLanguage"+isShowLanguage);
	System.out.println("@@la list=orderid"+orderId);

	String url = "";
	String head = "http://10.131.62.59:8080";
	String host = StaticMethod.nullObject2String(request.getRequestURL());
	if(host.indexOf("10.131.62") <0 ){
		head = "http://10.131.184.2:9099";
	}
   %>
   
<script type="text/javascript">

	function preOccupyRes(id){
	
		var serviceTypeEnum = '15';
		window.open("<%=head%>/Fulfillment/resConfig/resAllotInitForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
	}
	function viewOccupyRes(id){

		var serviceTypeEnum = '15';
		window.open("<%=head%>/Fulfillment/res/config/result/resConfigResultForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
	}
	function popupIrmsPreSurvey(id,value){

		
		var urls ="${app}/businessupport/product/languagespeciallines.do?method=edit&id="+id+"&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>"
				+"&addr=<%=addr%>"
				+"&taskType="+value;
	
		//window.open("http://10.131.62.172:8080/Fulfillment/res/config/result/resConfigResultForEoms.action?serviceType="+serviceTypeEnum+"&productCode="+id);
		window.open(urls);
	}
</script>
<display:table name="languagespecialList" cellspacing="0" cellpadding="0"
		id="list" pagesize="${pageSize}" class="listTable gprsspecialList"
		export="false" requestURI="ordersheets.do"
		sort="external" size="total" partialList="true" style="width:100%"
		>
		<display:column headerClass="sortable" title="详细信息" >
		<a onclick="window.open('${app}/businessupport/product/languagespeciallines.do?method=showDetail&orderId=<%=orderId%>&sheetType=<%=sheetType%>&taskName=<%=taskNamePara%>&id=<%=((HashMap)pageContext.getAttribute("list")).get("id")%>&isShowLanguage=<%=isShowLanguage %>');" style="cursor:pointer">查看详细信息</a>
		</display:column>
		<%if(taskNamePara.equals("LauguageTask")&&!taskStatus.equals("2")){%>
			<display:column media="html" title="资源预占">		   
	           <a onclick="preOccupyRes('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">资源预占</a>
    		</display:column>  
	    <%}%>
	     <%if(taskNamePara.equals("LauguageTask")||status.equals("1")){%>
	    	<display:column media="html" title="查看资源使用情况">		   
	           <a onclick="viewOccupyRes('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>');" style="cursor:pointer">查看资源使用情况</a>
	    	</display:column>  
		<%}%>
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
		<%if((!isShowLanguage.equals("yes")&&taskNamePara.equals("LauguageTask"))&&!taskStatus.equals("2")){%>
	    	<display:column media="html" title="关口局勘查">		   
	           <a onclick="popupIrmsPreSurvey('<%=((HashMap)pageContext.getAttribute("list")).get("id")%>','inputLanguage');" style="cursor:pointer">关口局勘查</a>
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
		<display:column property="inputNumber" headerClass="inputNumber" title="接入号码" />
		<display:column property="bitLength" headerClass="bitLength" title="位长" />
   		<display:column headerClass="businessType" title="业务类型">
		<eoms:id2nameDB id="${list.businessType}" beanId="ItawSystemDictTypeDao"/>
	    </display:column>
		<display:column property="userCallNumber" headerClass="userCallNumber" title="客户主叫号码" />
	    
        <display:setProperty name="export.excel" value="false"/>
        <display:setProperty name="export.rtf" value="false"/>
		<display:setProperty name="export.pdf" value="false"/>
		<display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.csv" value="false"/> 
</display:table>
<%@ include file="/common/footer_eoms.jsp"%>


