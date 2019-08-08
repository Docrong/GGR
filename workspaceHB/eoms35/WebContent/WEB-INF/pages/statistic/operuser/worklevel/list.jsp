<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ page import="com.boco.eoms.commons.statistic.base.util.StatAttributeUrlLocator" %>

<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function openSheet(url) {
        if (parent.frames['north']) {
            parent.frames['north'].location.href = url;
        } else {
            location.href = url;
        }
    }
</script>
<!-- bean:define id="url" value="stat.do" / -->
<fmt:bundle basename="config/applicationResource-operuser">
    <display:table name="estList" cellspacing="0" cellpadding="0"
                   id="process" pagesize="${pageSize}" class="table estList"
                   export="false" requestURI="statoperuser.do"
                   sort="list" size="total" partialList="true"
                   decorator="com.boco.eoms.commons.statistic.operuser.util.OperuserListDisplayHelper">

        <display:column property="name" sortable="true"
                        headerClass="sortable" titleKey="operuser.name"/>

        <display:column property="subarea" sortable="true"
                        headerClass="sortable" titleKey="operuser.subarea"/>

        <display:column property="majortype" sortable="true"
                        headerClass="sortable" titleKey="operuser.majortype"/>

        <display:column property="deptid" sortable="true"
                        headerClass="sortable" titleKey="operuser.deptname"/>

    </display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>
