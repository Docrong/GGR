<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<jsp:directive.page
        import="com.boco.eoms.km.exam.util.KmExamSpecialtyConstants"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/kmExamTests.do?method=add&isRandom=0'/>'"
           value="添加普通考试题"/>
    <!--
    <input type="button" style="margin-right: 5px"
    onclick="location.href='<html:rewrite page='/kmExamTests.do?method=add&isRandom=1'/>'"
    value="添加随机试卷" />
    <input type="button" style="margin-right: 5px"
    onclick="location.href='<html:rewrite page='/kmExamAttends.do?method=attendRandomTest'/>'"
    value="进入随机考试" />
    -->
</c:set>


<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
    <html:form action="/kmExamTests.do?method=searchX" styleId="kmExamTestForm" method="post">
        <eoms:xbox id="tree"
                   dataUrl="${app}/kmmanager/kmExamSpecialtys.do?method=getNodesRadioTree"
                   rootId="<%=KmExamSpecialtyConstants.TREE_ROOT_ID%>" rootText='所属分类'
                   valueField="specialtyID" handler="specialtyName"
                   textField="specialtyName" checktype="forums" single="true"></eoms:xbox>

        <table align="center">
            <tr>
                <td>
                    <fmt:message key="kmExamTest.specialtyID"/>：
                </td>
                <td>
                    <input type="text" id="specialtyName" name="specialtyName" class="text" readonly="readonly"
                           value='<eoms:id2nameDB id="${kmExamTestForm.specialtyID}" beanId="kmExamSpecialtyDao" />'
                           alt="allowBlank:false'"/>
                    <input type="hidden" id="specialtyID" name="specialtyID" value="${kmExamTestForm.specialtyID}"/>&nbsp;&nbsp;
                </td>

                <td>
                    <fmt:message key="kmExamTest.testName"/>：
                </td>
                <td>
                    <input type="text" id="testName" name="testName" class="text" value="${kmExamTestForm.testName}">
                </td>
                <td>
                    <input type="submit" class="btn" value="<fmt:message key="kmTable.query"/>"/>
                </td>
            </tr>
        </table>
    </html:form>

    <content tag="heading">
        <b><fmt:message key="kmExamTest.list.heading"/></b>
    </content>

    <display:table name="kmExamTestList" cellspacing="0" cellpadding="0"
                   id="kmExamTestList" pagesize="${pageSize}"
                   class="table kmExamTestList" export="false"
                   requestURI="${app}/kmmanager/kmExamTests.do?method=search" sort="list"
                   partialList="true" size="resultSize">

        <display:column property="testName" sortable="true"
                        headerClass="sortable" titleKey="kmExamTest.testName"
                        href="${app}/kmmanager/kmExamTests.do?method=edit" paramId="testID"
                        paramProperty="testID"/>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExamTest.specialtyID">
            <eoms:id2nameDB id="${kmExamTestList.specialtyID}"
                            beanId="kmExamSpecialtyDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExamTest.create_user">
            <eoms:id2nameDB id="${kmExamTestList.createUser}"
                            beanId="tawSystemUserDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExamTest.createDept">
            <eoms:id2nameDB id="${kmExamTestList.createDept}"
                            beanId="tawSystemDeptDao"/>
        </display:column>

        <display:column property="createTime" sortable="true"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}" headerClass="sortable"
                        titleKey="kmExamTest.createTime"
                        href="${app}/kmmanager/kmExamTests.do?method=edit" paramId="testID"
                        paramProperty="testID"/>

        <display:column property="totalScore" sortable="true"
                        headerClass="sortable" titleKey="kmExamTest.totalScore"
                        href="${app}/kmmanager/kmExamTests.do?method=edit" paramId="testID"
                        paramProperty="testID"/>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamTest.stochastic">
            <eoms:dict key="dict-kmmanager" dictId="isOrNot"
                       itemId="${kmExamTestList.isPermittedOvertime}" beanId="id2nameXML"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExamTest.isPublic">
            <eoms:dict key="dict-kmmanager" dictId="isOrNot"
                       itemId="${kmExamTestList.isPublic}" beanId="id2nameXML"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamTest.browse">
            <a href="${app}/kmmanager/kmExamTests.do?method=getTestPaper&testID=${kmExamTestList.testID}">
                <img src="${app}/images/icons/table.png"/>
            </a>
        </display:column>

        <display:column sortable="true" headerClass="sortable" titleKey="kmExamTest.copy">
            <a href="${app}/kmmanager/kmExamTests.do?method=copyTest&testID=${kmExamTestList.testID}">
                <img src="${app}/images/icons/icon-add.gif"/>
            </a>
        </display:column>

        <display:setProperty name="paging.banner.item_name" value="kmExamTest"/>
        <display:setProperty name="paging.banner.items_name" value="kmExamTests"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>


<%@ include file="/common/footer_eoms.jsp" %>