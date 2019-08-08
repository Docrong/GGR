<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<script type="text/javascript">
    function createRequest() {
        var httpRequest = null;
        if (window.XMLHttpRequest) {
            httpRequest = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            httpRequest = new ActiveXObject("MIcrosoft.XMLHttp");
        }
        return httpRequest;
    }

    function attendExam(testID) {
        var url = "${app}/kmmanager/kmExamAttends.do?method=attend&testID=" + testID;
        var httpRequest = createRequest();
        if (httpRequest) {
            httpRequest.open("POST", url, true);
            httpRequest.onreadystatechange = function () {
                if (httpRequest.readyState == 4)
                    if (httpRequest.status == 200) {
                        if (httpRequest.responseText == "1")
                            alert("您的本次考试已经结束！");
                        else if (httpRequest.responseText == "2")
                            alert("您正在进行某一考试，请完成后再进行此考试！");
                        else
                            location.href = url;
                    }
            }
            httpRequest.send(null);
        }
    }
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <caption>
        <div class="header center"><b>考试列表</b></div>
    </caption>

    <display:table name="kmExamTestList" cellspacing="0" cellpadding="0"
                   id="kmExamTestList" pagesize="${pageSize}"
                   class="table kmExamTestList" export="false"
                   requestURI="${app}/kmmanager/kmExamAttends.do?method=search">

        <display:column property="testName" sortable="true"
                        titleKey="kmExamTest.testName" headerClass="sortable"
                        paramId="testID" paramProperty="testID"/>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamTest.specialtyID">
            <eoms:id2nameDB id="${kmExamTestList.specialtyID}" beanId="kmExamSpecialtyDao"/>
        </display:column>

        <display:column property="testDescription" sortable="true"
                        titleKey="kmExamTest.test_description" headerClass="sortable"
                        paramId="testID" paramProperty="testID"/>

        <display:column property="testBeginTime" sortable="true" titleKey="kmExamTest.testBeginTime"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sortable"
                        paramId="testID" paramProperty="testID"/>

        <display:column property="testEndTime" sortable="true" titleKey="kmExamTest.testEndTime"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sortable"
                        paramId="testID" paramProperty="testID"/>

        <display:column property="testDuration" sortable="true"
                        titleKey="kmExamTest.testDuration" headerClass="sortable"
                        paramId="testID" paramProperty="testID"/>

        <display:column title="进入考试" headerClass="imageColumn">
            <a href="javascript:void(0)" onclick="attendExam('${kmExamTestList.testID }')">
                <img src="${app}/images/icons/sheet-icons/arrow_join.png">
            </a>
        </display:column>

        <display:setProperty name="paging.banner.item_name" value="kmExamTest"/>
        <display:setProperty name="paging.banner.items_name" value="kmExamTests"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>