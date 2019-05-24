<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<caption>
		<div class="header center">
			<b><fmt:message key="kmLesson.list.heading" /></b>
		</div>
	</caption>
		
	<display:table name="kmLessonList" cellspacing="0" cellpadding="0"
		id="kmLessonList" pagesize="${pageSize}" class="table kmLessonList"
		export="false" requestURI="${app}/kmmanager/kmLesson.do"
		sort="list" partialList="true" size="resultSize">

		<display:column property="lessonTheme" sortable="true"
			headerClass="sortable" titleKey="kmLesson.lessonTheme" />

		<display:column property="lessonClass" sortable="true"
			headerClass="sortable" titleKey="kmLesson.lessonClass" />
		    
		<display:column property="startTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}" 
		    headerClass="sortable" titleKey="kmLesson.startTime" />

		<display:column property="endTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}" 
		    headerClass="sortable" titleKey="kmLesson.endTime" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmLesson.createUser">
			 <eoms:id2nameDB id="${kmLessonList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column property="createTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}" 
		    headerClass="sortable" titleKey="kmLesson.createTime" />

		<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${kmLessonList.id }';
		                        var url='${app}/kmmanager/kmLesson.do?method=detail';
		                        url = url + '&id=' + id ;
		                        location.href=url"><img src="${app}/images/icons/search.gif"/></a>		    
		</display:column>

		<display:setProperty name="paging.banner.item_name"  value="kmLesson" />
	    <display:setProperty name="paging.banner.items_name" value="kmLessons" />
	</display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>