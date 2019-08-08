<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
    <html:form action="/kmExpertEdus.do" method="post" styleId="kmExpertEduForm">
        <display:table name="kmExpertEduList" cellspacing="0" cellpadding="0"
                       id="kmExpertEduList" pagesize="${pageSize}"
                       class="table kmExpertEduList" export="false"
                       requestURI="${app}/kmmanager/kmExpertEdus.do?method=listDetail"
                       sort="list" partialList="true" size="resultSize">

            <display:column property="expertEduSch" sortable="true"
                            headerClass="sortable" titleKey="kmExpertEdu.expertEduSch"/>

            <display:column sortable="true" headerClass="sortable"
                            titleKey="kmExpertEdu.expertEduEdu">
                <eoms:id2nameDB id="${kmExpertEduList.expertEduEdu}" beanId="ItawSystemDictTypeDao"/>
            </display:column>

            <display:column property="expertEduStart" sortable="true"
                            format="{0,date,yyyy-MM-dd}" headerClass="sortable"
                            titleKey="kmExpertEdu.expertEduStart"/>

            <display:column property="expertEduEnd" sortable="true"
                            format="{0,date,yyyy-MM-dd}" headerClass="sortable"
                            titleKey="kmExpertEdu.expertEduEnd"/>

            <display:column property="expertEduPro" sortable="true"
                            headerClass="sortable" titleKey="kmExpertEdu.expertEduPro"/>

            <display:column property="expertEduProName" sortable="true"
                            headerClass="sortable" titleKey="kmExpertEdu.expertEduProName"/>

            <display:column property="expertEduCity" sortable="true"
                            headerClass="sortable" titleKey="kmExpertEdu.expertEduCity"/>

            <display:column property="expertEduDes" sortable="true"
                            headerClass="sortable" titleKey="kmExpertEdu.expertEduDes"/>

            <display:setProperty name="paging.banner.item_name" value="kmExpertEdu"/>
            <display:setProperty name="paging.banner.items_name" value="kmExpertEdus"/>
        </display:table>
    </html:form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>