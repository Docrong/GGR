<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>



<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<caption>
		<div class="header center">${listTitle}</div>
	</caption>
	<display:table name="kmExpertAnswerList" cellspacing="0" cellpadding="0"
		id="kmExpertAnswerList" pagesize="${pageSize}" class="table kmExpertAnswerList"
		export="false"
		requestURI="${app}/kmmanager/kmExpertAnswer.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="createTime" sortable="true"
			headerClass="sortable" title="新增时间" href="${app}/kmmanager/kmExpertAnswer.do?method=detailQuestion" paramId="id" paramProperty="id"/>
	
	<display:column property="title" sortable="true"
			headerClass="sortable" title="主题" href="${app}/kmmanager/kmExpertAnswer.do?method=detailQuestion" paramId="id" paramProperty="id"/>
		
	<display:column sortable="true" headerClass="sortable" title="创建人" >
			<eoms:id2nameDB id="${kmExpertAnswerList.createUserId}" beanId="tawSystemUserDao" />
	</display:column>		
		
	<display:column sortable="true" headerClass="sortable" title="问题分类" >
			<eoms:id2nameDB id="${kmExpertAnswerList.type}" beanId="ItawSystemDictTypeDao" />
	</display:column>	

				
	<display:column sortable="true" headerClass="sortable" title="问题状态">
		    <eoms:dict key="dict-kmmanager" dictId="questionState" itemId="${kmExpertAnswerList.state}" beanId="id2nameXML" />
	</display:column>
	
		<display:setProperty name="paging.banner.item_name" value="kmExpertAnswer" />
		<display:setProperty name="paging.banner.items_name" value="kmExpertAnswers" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>