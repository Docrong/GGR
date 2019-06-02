<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/lessonAnaysiss.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="lessonAnaysis.list.heading" />&nbsp;&nbsp;
		<img src="${app}/images/icons/icon1.gif"/></div>
	</caption>
</table>
<!-- 考试信息统计部分 -->
<table class="formTable">
		<tr>
			<td class="label">
				<fmt:message key="lessonAnaysis.testID"/>
			</td>
			<td class="label">
				<fmt:message key="resultlist.totaluser"/>
			</td>
			<td class="label">
				<fmt:message key="resultlist.avgscore"/>
			</td>
		</tr>
	<logic:iterate id="list" name="resultlist">
		<tr>
			<td class="content">
				${list.testID }
			</td>
			<td class="content">
				${list.totaluser }
			</td>
			<td class="content">
				${list.avgscore }
			</td>
		</tr>
	</logic:iterate>
</table>	
	
	
	
	<!-- 考试信息列表部分 -->
	<display:table name="lessonAnaysisList" cellspacing="0" cellpadding="0"
		id="lessonAnaysisList" pagesize="${pageSize}" class="table lessonAnaysisList"
		export="false"
		requestURI="${app}/lessonanalysis/lessonAnaysiss.do?method=search"
		sort="list" partialList="true" size="resultSize">
			
	<display:column property="testID" sortable="true"
			headerClass="sortable" titleKey="lessonAnaysis.testID"  paramId="id" paramProperty="id">
			
	</display:column>
 	
	<display:column property="attendUser" sortable="false"
			headerClass="sortable" titleKey="lessonAnaysis.attendUser"  paramId="id" paramProperty="id">
			
	</display:column>
	<display:column property="attendDept" sortable="false"
			headerClass="sortable" titleKey="lessonAnaysis.attendDept"  paramId="id" paramProperty="id">
			
	</display:column>
	<display:column property="attendTime" sortable="true"
			headerClass="sortable" titleKey="lessonAnaysis.attendTime"  paramId="id" paramProperty="id">
			
	</display:column>
	<display:column property="attendOverTime" sortable="false"
			headerClass="sortable" titleKey="lessonAnaysis.attendOverTime"  paramId="id" paramProperty="id">
			
	</display:column>
	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="lessonAnaysis.score"  paramId="id" paramProperty="id">
			
	</display:column>
		<display:setProperty name="paging.banner.item_name" value="resultlist" />
		<display:setProperty name="paging.banner.item_name" value="lessonAnaysisList" />
	</display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>