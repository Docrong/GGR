<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	
});
</script>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <content tag="heading">
	    <b>专家团队列表</b>
    </content>

	<br><br>

	<display:table name="kmExpertGroupList" cellspacing="0" cellpadding="0"
		id="kmExpertGroup" pagesize="${pageSize}" class="table kmExpertBasicList"
		export="false"
		requestURI="${app}/kmmanager/kmExpertBasics.do?method=search"
		sort="list" partialList="true" size="resultSize">

		<display:column property="groupName" sortable="true"
			headerClass="sortable" titleKey="kmExpertGroup.groupName" />

		<display:column sortable="true" headerClass="sortable"
			titleKey="kmExpertGroup.groupLeader">
			 <eoms:id2nameDB id="${kmExpertGroup.groupLeader}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="true" headerClass="sortable"
			titleKey="kmExpertGroup.specialty">
			<eoms:id2nameDB id="${kmExpertGroup.specialty}" beanId="ItawSystemDictTypeDao" />
		</display:column>

		<display:column sortable="true" headerClass="sortable"
			titleKey="kmExpertGroup.createUser">
			 <eoms:id2nameDB id="${kmExpertGroup.createUser}" beanId="tawSystemUserDao" />
		</display:column>
		
		<display:column property="createTime" sortable="true" 
		    headerClass="sortable" titleKey="kmExpertGroup.createTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

		<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${kmExpertGroup.id }';
		                        var url='${app}/kmmanager/kmExpertGroup.do?method=detail';
		                        url = url + '&id=' + id;
		                        location.href=url"><img src="${app}/images/icons/search.gif"/></a>		    
		</display:column>

		<display:setProperty name="paging.banner.item_name" value="kmExpertGroup" />
		<display:setProperty name="paging.banner.items_name" value="kmExpertBasics" />
	</display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>