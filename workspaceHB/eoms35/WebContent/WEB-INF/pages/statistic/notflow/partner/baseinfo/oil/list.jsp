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
<fmt:bundle basename="config/applicationResources-partner-baseinfo">
    <display:table name="estList" cellspacing="0" cellpadding="0"
                   id="process" pagesize="${pageSize}" class="table estList"
                   export="false" requestURI="oilStat.do"
                   sort="list" size="total" partialList="true"
                   decorator="com.boco.eoms.commons.statistic.commonstat.util.OilStatListDisplayHelper">

        <display:column property="kind" sortable="true"
                        headerClass="sortable" titleKey="tawPartnerOil.skind"/>

        <display:column property="num" sortable="true"
                        headerClass="sortable" titleKey="tawPartnerOil.num"/>

    </display:table>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>
