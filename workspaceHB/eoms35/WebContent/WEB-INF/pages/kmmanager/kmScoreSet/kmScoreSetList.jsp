<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<%
	String operateType = StaticMethod.nullObject2String(request.getAttribute("operateType"));
	TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
	String operateUserId = sessionform.getUserid();
	String auditName = StaticMethod.nullObject2String(request.getAttribute("auditName"));
%>
<% if (request.getAttribute("nodeId").toString().length()!=3) { %>
<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmScoreSets.do?method=add&nodeId=${nodeId}'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>
<% }%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">


	<display:table name="kmScoreSetList" cellspacing="0" cellpadding="0"
		id="kmScoreSetList" pagesize="${pageSize}" class="table kmScoreSetList"
		export="false"
		requestURI="${app}/kmmanager/kmScoreSets.do?method=search"
		sort="list" partialList="true" size="resultSize">
			
	<display:column property="powerName" sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.powerName"  paramId="id" paramProperty="id"/>
	
	<display:column property="powerWeight" sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.powerWeight"  paramId="id" paramProperty="id"/>

	<display:column property="actionName" sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.actionName"  paramId="id" paramProperty="id"/>
	
	<display:column property="actionWeight" sortable="true"
		headerClass="sortable" titleKey="kmScoreSet.actionWeight"  paramId="id" paramProperty="id"/>

	<display:column  sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.userLevel"  paramId="id" paramProperty="id">
			<eoms:dict key="dict-kmmanager" dictId="userLevel" itemId="${kmScoreSetList.userLevel}" beanId="id2nameXML" />
	</display:column>
	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.score"  paramId="id" paramProperty="id"/>

	<display:column sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.isCount"  paramId="id" paramProperty="id">
			<eoms:dict key="dict-kmmanager" dictId="isCount" itemId="${kmScoreSetList.isCount}" beanId="id2nameXML" />
	</display:column>
	<display:column property="remark" sortable="true"
			headerClass="sortable" titleKey="kmScoreSet.remark"  paramId="id" paramProperty="id"/>
	<% if (request.getAttribute("nodeId").toString().length()!=3) { %>
	<display:column sortable="false" headerClass="sortable" titleKey="kmFile.allOperate">
	<% if (operateType.indexOf("W") != -1 || "admin".equals(operateUserId)) { %>
	<a href="${app}/kmmanager/kmScoreSets.do?method=edit&nodeId=${nodeId}&id=${kmScoreSetList.id }">
	    <fmt:message key="kmScoreSet.updated" />
	</a>&nbsp;&nbsp;
	<a href="javascript:if(confirm('确定要删除该文件?')){var mappingName='${kmScoreSetList.id }';var nodeId='${nodeId}';var url='${app}/kmmanager/kmScoreSets.do?method=remove&id=' + mappingName + '&nodeId=' + nodeId;location.href=url}">
	    <fmt:message key="kmScoreSet.deleted" />
	</a>
	<% } else { %>
	无权限
	<% }%>
			
	</display:column>
	<% }%>
		<display:setProperty name="paging.banner.item_name" value="kmScoreSet" />
		<display:setProperty name="paging.banner.items_name" value="kmScoreSets" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>