<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function openSheet(url) {
        if (parent.frames['portal-north']) {
            parent.frames['portal-north'].location.href = url;
        } else {
            location.href = url;
        }
    }
</script>

<bean:define id="url" value="bureaudataCityzone.do"/>
<display:table name="list" cellspacing="0" cellpadding="0"
               id="list" pagesize="${pageSize}" class="table businessdesignMain"
               export="false" requestURI="bureaudataCityzone.do" sort="list" size="total"
               partialList="true">
    <display:column sortable="true" headerClass="sortable"
                    title="地市名称" sortName="cityname">
        <a href="bureaudataCityzone.do?method=findByid&cityid=${list.cityid }&type=open">
            <bean:write name="list" property="cityname"/> </a>
    </display:column>
    <display:column property="zonenum" sortable="true"
                    headerClass="sortable" title="区号" sortName="zonenum"/>
    <display:column sortable="true"
                    headerClass="sortable" title="对应部门" sortName="deptid">
        <eoms:id2nameDB beanId="tawSystemDeptDao" id="${list.deptid }"/>
    </display:column>
    <display:column property="userid" sortable="true"
                    headerClass="sortable" title="负责人员ID号" sortName="userid"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
    <display:setProperty name="export.rtf" value="false"/>
    <display:setProperty name="export.excel" value="false"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>
