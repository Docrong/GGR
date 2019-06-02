<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmRules.do?method=tree'/>'"
		value="<fmt:message key="button.add"/>" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<html:form action="/kmRules.do?method=searchX" styleId="kmRuleForm" method="post"> 
<table align="center">  
  <tr >
    <td>
		<fmt:message key="kmRule.contentId" />
	</td>
		<td class="content">
			<html:select property="contentId" styleId="contentId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:'请选择知识库...'" value="${kmRuleForm.contentId}">
				<html:option value="">请选择知识库</html:option>
			    <html:optionsCollection name="KmTableGeneralList" value="id" label="tableChname"></html:optionsCollection>
			</html:select>
		</td>
			
	 <td>
		 <fmt:message key="kmRule.ruleName" />
	</td>
	 <td>
		<input type="text" id="ruleName" name="ruleName" value="${kmRuleForm.ruleName}">	
	</td>
	
	 <td>
		 <input type="submit" class="btn" value="<fmt:message key="kmTable.query"/>"/>		
	</td>
  </tr>
</table>
</html:form>
<content tag="heading">
	<fmt:message key="kmRule.list.heading" />
</content>

	<display:table name="kmRuleList" cellspacing="0" cellpadding="0"
		id="kmRuleList" pagesize="${pageSize}" class="table kmRuleList"
		export="false"
		requestURI="${app}/kmmanager/kmRules.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="ruleName" sortable="true"
			headerClass="sortable" titleKey="kmRule.ruleName" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>
    <display:column sortable="true" headerClass="sortable" titleKey="kmRule.contentId">
		<eoms:id2nameDB id="${kmRuleList.contentId}" beanId="kmTableGeneralDao" />
	</display:column>
	  
	<display:column sortable="true" headerClass="sortable" titleKey="kmRule.createUser">
		<eoms:id2nameDB id="${kmRuleList.createUser}" beanId="tawSystemUserDao" />
	</display:column>
	
    <display:column sortable="true" headerClass="sortable" titleKey="kmRule.createDept">
			<eoms:id2nameDB id="${kmRuleList.createDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column property="createTime" sortable="true"
			headerClass="sortable" titleKey="kmRule.createTime" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>
    
	<display:column property="ruleScript" sortable="true"
			headerClass="sortable" titleKey="kmRule.ruleScript" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>

	<display:column property="ruleParameter" sortable="true"
			headerClass="sortable" titleKey="kmRule.ruleParameter" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>

	<!--display:column property="isDeleted" sortable="true"
			headerClass="sortable" titleKey="kmRule.isDeleted" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>  -->
    <display:column property="id" sortable="true"
			headerClass="sortable" titleKey="kmRule.id" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>
    
	<display:column property="remark" sortable="true"
			headerClass="sortable" titleKey="kmRule.remark" href="${app}/kmmanager/kmRules.do?method=edit" paramId="id" paramProperty="id"/>
   
		<display:setProperty name="paging.banner.item_name" value="kmRule" />
		<display:setProperty name="paging.banner.items_name" value="kmRules" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>