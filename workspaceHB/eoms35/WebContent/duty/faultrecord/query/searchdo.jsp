<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager" %>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<style type="text/css">
    body {
        background-image: none;
    }
</style>
<content tag="heading">${eoms:a2u('故障记录')}&nbsp;<bean:message key="label.list"/></content>
<fmt:bundle basename="config/ApplicationResources-duty">
    <display:table name="faultrecordlist" cellspacing="0" cellpadding="0"
                   id="faultrecordlist" pagesize="${pageSize}" class="table faultrecordlist"
                   export="true" sort="list" partialList="true" size="resultSize"
                   requestURI="${app}/duty/Faultrecord/searchdo.do"
                   decorator="com.boco.eoms.duty.displaytag.support.FaultRecordDisplaytagDecorator">


        <display:column property="userId" sortable="true" headerClass="sortable"
                        titleKey="Faultrecord.createUser"/>

        <display:column property="deptId" sortable="true" headerClass="sortable"
                        titleKey="Faultrecord.createdept"/>

        <display:column property="insertTime" sortable="true" headerClass="sortable"
                        titleKey="Faultrecord.createTime"/>

        <display:column titleKey="Faultrecord.view" paramId="id" paramProperty="id"
                        href="${app}/duty/Faultrecord/view.do">${eoms:a2u('查看')}</display:column>

        <display:column titleKey="Faultrecord.edit" paramId="id" paramProperty="id"
                        href="${app}/duty/Faultrecord/edit.do">${eoms:a2u('编辑')}</display:column>

        <display:column titleKey="Faultrecord.delete" paramId="id" paramProperty="id"
                        href="${app}/duty/Faultrecord/del.do">${eoms:a2u('删除')}</display:column>

    </display:table>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp" %>

