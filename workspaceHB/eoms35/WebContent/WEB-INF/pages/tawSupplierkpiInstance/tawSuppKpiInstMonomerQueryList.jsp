<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<jsp:directive.page
	import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance" />

<fmt:bundle basename="config/ApplicationResources-supplierkpi">
	<content tag="heading">
	<fmt:message key="tawSupplierkpiInstanceList.title" />
	</content>
</fmt:bundle>
<%
   String pageNum = (String)request.getAttribute("pageNum");
   int num = Integer.parseInt(pageNum); 
   num = num*15;
%>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
	<!-- <c:out value="${buttons}" escapeXml="false"/> -->
	<display:table name="tawSupplierkpiInstMonomerList" cellspacing="0"
		cellpadding="0" pagesize="15"
		requestURI="/supplierkpi/queryMonomertawSuppKpiIns.do?method=query"
		id="tawSupplierkpiInstMonomerList"
		class="table tawSupplierkpiInstMonomerList" partialList="true"
		size="resultSize">		

		<% 
		  String latitude = "";
		   if(pageContext.getAttribute("tawSupplierkpiInstMonomerList")!=null)	{
		   String timeLatitude =((TawSupplierkpiInstance)pageContext.getAttribute("tawSupplierkpiInstMonomerList")).getTimeLatitude();		    
		   String year = ((TawSupplierkpiInstance)pageContext.getAttribute("tawSupplierkpiInstMonomerList")).getYear();					
					if (timeLatitude.equals("year")) {
				latitude = year + "\u5E74\u5EA6";
					} else if (timeLatitude.equals("one")) {
				latitude = year + "\u5E74\u7B2C\u4E00\u5B63\u5EA6";
					} else if (timeLatitude.equals("two")) {
				latitude = year + "\u5E74\u7B2C\u4E8C\u5B63\u5EA6";
					} else if (timeLatitude.equals("three")) {
				latitude = year + "\u5E74\u7B2C\u4E09\u5B63\u5EA6";
					} else if (timeLatitude.equals("four")) {
				latitude = year + "\u5E74\u7B2C\u56DB\u5B63\u5EA6";
					} else if(!timeLatitude.equals("-1")){
				latitude = year + "-" + timeLatitude;
					}				
			}		
		%>
　　     <display:column titleKey="tawSupplierkpiInstanceList.numb" sortable="true"
			headerClass="sortable">
            <%=++num %>
       </display:column>

		<display:column property="kpiName" sortable="true"
			headerClass="sortable" titleKey="tawSupplierkpiInstanceList.kpiName" />

		<display:column property="examineContent" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.examineContent" />

		<display:column property="assessNote" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.assessNote" />
			
		<display:column sortable="true" headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.timeLatitude">
			<%=latitude %>
		</display:column>			

		<display:column property="fillRole" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.fillRole" />

		<display:column property="firstFillTime" sortable="true"
			headerClass="sortable" format="{0,date,yyyy-MM-dd hh:mm:ss}" 
			titleKey="tawSupplierkpiInstanceList.firstFillTime" />
		
    <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiInstance"/>
    <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiInstances"/>
			

	</display:table>
</fmt:bundle>
<c:out value="${buttons}" escapeXml="false" />

<%@ include file="/common/footer_eoms.jsp"%>
