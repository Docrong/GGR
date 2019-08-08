<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>


<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <display:table name="kmExamAttendList" cellspacing="0" cellpadding="0"
                   id="kmExamAttendList" pagesize="${pageSize}" class="table kmExamAttendList"
                   export="false"
                   requestURI="${app}/kmmanager/kmExamAttends.do?method=searchNoRead"
                   sort="list" partialList="true" size="resultSize">

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamAttend.testId">
            <eoms:id2nameDB id="${kmExamAttendList.testId}" beanId="kmExamTestDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamAttend.attendDept">
            <eoms:id2nameDB id="${kmExamAttendList.attendDept}" beanId="tawSystemDeptDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamAttend.attendUser">
            <eoms:id2nameDB id="${kmExamAttendList.attendUser}" beanId="tawSystemUserDao"/>
        </display:column>

        <display:column property="attendTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        headerClass="sortable" titleKey="kmExamAttend.attendTime" paramId="id" paramProperty="id"/>

        <display:column property="attendOverTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
                        headerClass="sortable" titleKey="kmExamAttend.attendOverTime" paramId="id" paramProperty="id"/>

        <display:column title="进入阅卷" headerClass="imageColumn">
            <a href="javascript:var id = '${kmExamAttendList.id }';
		                        var testID = '${kmExamAttendList.testId}';
		                        var attendUser = '${kmExamAttendList.attendUser}';
		                        var url='${app}/kmmanager/kmExamAttends.do?method=attendNoRead';
		                        url = url + '&id=' + id + '&testID=' + testID +'&attendUser='+attendUser;
		                        location.href=url">
                <img src="${app}/images/icons/sheet-icons/arrow_join.png"></a>
        </display:column>

        <display:setProperty name="paging.banner.item_name" value="kmExamAttend"/>
        <display:setProperty name="paging.banner.items_name" value="kmExamAttends"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>