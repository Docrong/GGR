<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <caption>
        <div class="header center"><b>考试结果列表</b></div>
    </caption>

    <display:table name="kmExamAttendList" cellspacing="0" cellpadding="0"
                   id="kmExamAttendList" pagesize="${pageSize}"
                   class="table kmExamAttendList" export="false"
                   requestURI="${app}/kmmanager/kmExamAttends.do?method=searchIsPublic">

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamAttend.testId">
            <eoms:id2nameDB id="${kmExamAttendList.testId}" beanId="kmExamTestDao"/>
        </display:column>

        <display:column property="attendTime" sortable="true"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sortable"
                        titleKey="kmExamAttend.attendTime" paramId="id" paramProperty="id"/>

        <display:column property="attendOverTime" sortable="true"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sortable"
                        titleKey="kmExamAttend.attendOverTime" paramId="id"
                        paramProperty="id"/>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamAttend.readDept">
            <eoms:id2nameDB id="${kmExamAttendList.readDept}" beanId="tawSystemDeptDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamAttend.readUser">
            <eoms:id2nameDB id="${kmExamAttendList.readUser}" beanId="tawSystemUserDao"/>
        </display:column>

        <display:column property="score" sortable="true"
                        headerClass="sortable" titleKey="kmExamAttend.score" paramId="id"
                        paramProperty="id"/>

        <display:column title="查看" headerClass="imageColumn">
            <a href="javascript:var id = '${kmExamAttendList.id }';
		                        var testID = '${kmExamAttendList.testId}';
		                        var attendUser = '${kmExamAttendList.attendUser}';
		                        var url='${app}/kmmanager/kmExamAttends.do?method=attendRead';
		                        url = url + '&id=' + id + '&testID=' + testID +'&attendUser='+attendUser;
		                        location.href=url">
                <img src="${app}/images/icons/search.gif"/>
            </a>
        </display:column>

        <display:setProperty name="paging.banner.item_name" value="kmExamAttend"/>
        <display:setProperty name="paging.banner.items_name" value="kmExamAttends"/>
    </display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>