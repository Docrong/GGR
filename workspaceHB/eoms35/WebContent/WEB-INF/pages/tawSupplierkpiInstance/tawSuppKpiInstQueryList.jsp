<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<jsp:directive.page
	import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance"/>

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
	<display:table name="tawSupplierkpiInstanceList" cellspacing="0"
		cellpadding="0" pagesize="15"
		requestURI="/supplierkpi/QuerytawSuppKpiIns.do?method=query"
		id="tawSupplierkpiInstance"
		class="table tawSupplierkpiInstanceList" partialList="true"
		size="resultSize">

		<% 
		  String latitude = "";
		   if(pageContext.getAttribute("tawSupplierkpiInstance")!=null)	{
		   String timeLatitude =((TawSupplierkpiInstance)pageContext.getAttribute("tawSupplierkpiInstance")).getTimeLatitude();		    
		   String year = ((TawSupplierkpiInstance)pageContext.getAttribute("tawSupplierkpiInstance")).getYear();					
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

		<display:column property="manufacturerName" sortable="true"
			headerClass="sortable" titleKey="tawSupplierkpiInstanceList.supplier" />

		<display:column property="serviceTypeName" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.serviceType" />

		<display:column property="specialTypeName" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.specialType" />

		<display:column property="statictsCycleName" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.statictsCycle" />

		<display:column sortable="true" headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.timeLatitude">
			<%=latitude %>
		</display:column>

		<display:column property="examineContent" sortable="true"
			headerClass="sortable"
			titleKey="tawSupplierkpiInstanceList.examineContent" />

		<display:column property="unitName" sortable="true"
			headerClass="sortable" titleKey="tawSupplierkpiInstanceList.unit" />

		<display:column property="memo" sortable="true" headerClass="sortable"
			titleKey="tawSupplierkpiInstanceForm.memo" />
			
    <display:setProperty name="paging.banner.item_name" value="tawSupplierkpiInstance"/>
    <display:setProperty name="paging.banner.items_name" value="tawSupplierkpiInstances"/>
			

	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
