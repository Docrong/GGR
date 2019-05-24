<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<script language="javaScript" type="text/javascript"
	src="${app}/scripts/module/partner/ajax.js"></script>
<%@ page language="java"
	import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;
	
	"%>
	
<c:if test="${in!=null}">
<%@ include file="/WEB-INF/pages/partner/baseinfo/hrefSearch.jsp"%>
</c:if>
<c:choose>
	<c:when test="${isCity=='yes'}">
		<c:set var="buttons">
			<input type="button" style="margin-right: 5px"
				onclick="location.href='<html:rewrite page='/tawApparatuss.do?method=add&nodeId=${nodeId}'/>'"
				value="<fmt:message key="button.add"/>" />
		</c:set>
	</c:when>
</c:choose>
<fmt:bundle basename="config/applicationResources-partner-baseinfo">
	<html:form action="tawApparatuss.do?method=xquery" method="post"
		styleId="tawApparatusForm">
		<table class="formTable">
			<caption>
				<div class="header center">
					<fmt:message key="tawApparatus.list.heading" />
				</div>
			</caption>
			<tr>
				<td class="label">
					<fmt:message key="tawApparatus.type" />
				</td>
				<td class="content">
					<eoms:comboBox name="type" id="type" initDicId="11204" />
				</td>
				<td class="label">
					<fmt:message key="tawApparatus.state" />
				</td>
				<td class="content">
					<eoms:comboBox name="state" id="state" initDicId="11205" />
				</td>
			</tr>
			<tr>
			    
			    
				<td class="label">
					<fmt:message key="tawApparatus.area_id" />
				</td>
				<td class="content">
		
			<% 
			List listId = (List) request.getAttribute("listId");
							List listName = (List) request.getAttribute("listName");
							TawApparatusForm fm = (TawApparatusForm) request
									.getAttribute("tawApparatusForm");
							String nodeId = fm.getArea_id();
			%>
	<c:choose>
	<c:when test="${in=='province'}">
	<html:select property="area_id" styleId="area_id" onchange="changeDep();">
	<%
							if(nodeId==null){
							nodeId=" ";
							}
							for (int i = 0; i < listId.size(); i++) {
								if (nodeId.equals(listId.get(i))) {
						%>
						<option value="<%=listId.get(i)%>" selected>
							<%=listName.get(i)%>
						</option>
						<%
						} else {
						%>
						<option value="<%=listId.get(i)%>">
							<%=listName.get(i)%>
						</option>
						<%
							}
							}
						%>
					</html:select>
	</c:when>				
    <c:otherwise>
    <html:select property="area_id" disabled="true" styleId="area_id" onchange="changeDep();">
    <%
							if(nodeId==null){
							nodeId=" ";
							}
							for (int i = 0; i < listId.size(); i++) {
								if (nodeId.equals(listId.get(i))) {
						%>
						<option value="<%=listId.get(i)%>" selected>
							<%=listName.get(i)%>
						</option>
						<%
						} else {
						%>
						<option value="<%=listId.get(i)%>">
							<%=listName.get(i)%>
						</option>
						<%
							}
							}
						%>
					</html:select>
    <html:hidden property="area_id"/>
    </c:otherwise>
</c:choose>
					
					<%--<html:text property="area_id" styleId="area_id"
						styleClass="text medium"/>
		--%>
				</td>
				<td class="label">
					<fmt:message key="tawApparatus.dept_id" />
				</td>
				<td class="content">
					<html:select property="dept_id"  styleId="dept_id" size="1">
					</html:select>
			
					<%--<html:text property="dept_id" styleId="dept_id"
						styleClass="text medium" />
		--%>
				</td>


			</tr>
			<tr>
<input type="hidden" name="nodeId" value="<%=request.getAttribute("nodeId") %>">
				<td colspan="4" align="center">
					<html:submit styleClass="button" property="method.query">
						查询
					</html:submit>
			</tr>
		</table>
	</html:form>
	<display:table name="tawApparatusList" cellspacing="0" cellpadding="0"
		id="tawApparatusList" pagesize="${pageSize}" decorator="com.boco.eoms.partner.baseinfo.util.PartnerApparatusDecorator"
		class="table tawApparatusList" export="false"
		requestURI="${app}/partner/baseinfo/tawApparatuss.do?method=search"
		sort="list" partialList="true" size="resultSize">
		<display:column property="apparatusId" sortable="true"
			headerClass="sortable" titleKey="tawApparatus.apparatusId"
			href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
			paramId="id" paramProperty="id" />

		<display:column property="apparatusName" sortable="true"
			headerClass="sortable" titleKey="tawApparatus.apparatusName"
			href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
			paramId="id" paramProperty="id" />

		<display:column sortable="true" headerClass="sortable"
			titleKey="tawApparatus.factory">
			<html:link
				href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
				paramId="id" paramProperty="id" paramName="tawApparatusList">
				<eoms:id2nameDB id="${tawApparatusList.factory}"
					beanId="tawSystemDictTypeDao" />
			</html:link>
		</display:column>

		<display:column sortable="true" headerClass="sortable"
			titleKey="tawApparatus.type">
			<html:link
				href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
				paramId="id" paramProperty="id" paramName="tawApparatusList">
				<eoms:id2nameDB id="${tawApparatusList.type}"
					beanId="tawSystemDictTypeDao" />
			</html:link>
		</display:column>

		<display:column property="model" sortable="true"
			headerClass="sortable" titleKey="tawApparatus.model"
			href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
			paramId="id" paramProperty="id" />

		<display:column sortable="true" headerClass="sortable"
			titleKey="tawApparatus.state">
			<html:link
				href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
				paramId="id" paramProperty="id" paramName="tawApparatusList">
				<eoms:id2nameDB id="${tawApparatusList.state}"
					beanId="tawSystemDictTypeDao" />
			</html:link>
		</display:column>
		<display:column property="dept_id" sortable="true"
			headerClass="sortable" titleKey="tawApparatus.dept_id"
			href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
			paramId="id" paramProperty="id" />

		<display:column property="area_id" sortable="true"
			headerClass="sortable" titleKey="tawApparatus.area_id"
			href="${app}/partner/baseinfo/tawApparatuss.do?method=edit"
			paramId="id" paramProperty="id" />
		<display:setProperty name="paging.banner.item_name"
			value="tawApparatus" />
		<display:setProperty name="paging.banner.items_name"
			value="tawApparatuss" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>
<script type="text/javascript">

var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/partner/baseinfo/tawApparatuss.do?method=changeDep2&id="+id;
			 var fieldName = "dept_id";
			 var deptId ="<%=((TawApparatusForm) request
							.getAttribute("tawApparatusForm")).getDept_id()%>";
			 changeList(url,fieldName,deptId);
function changeDep()
		{    
			 var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/partner/baseinfo/tawApparatuss.do?method=changeDep2&id="+id;
			 var fieldName = "dept_id";
			 changeList(url,fieldName,"");
		}

  </script>
<%@ include file="/common/footer_eoms.jsp"%>
