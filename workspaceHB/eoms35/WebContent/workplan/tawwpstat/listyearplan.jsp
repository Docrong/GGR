<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpYearPlanVO" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    <!--
    Ext.onReady(function () {
        colorRows('list-table');
    })

    //-->
    function goBack() {
        location.href = "queryyearplan.do";
    }
</script>
<%
    List yearPlanList = (List) request.getAttribute("queryyearplan"); //获取年度作业计划集合结构
    TawwpYearPlanVO tawwpYearPlanVO = null;
%>

<table width="100%" class="listTable" id="list-table">
    <caption><bean:message key="listyearplan.title.formTitle"/></caption>
    <thead>
    <tr>
        <td height="20"><bean:message key="listyearplan.title.formName"/></td>
        <td><bean:message key="listyearplan.title.formSysType"/></td>
        <td><bean:message key="listyearplan.title.formNetType"/></td>
        <td><bean:message key="listyearplan.title.formCrtime"/></td>
        <td><bean:message key="listyearplan.title.formDeptName"/></td>
        <td><bean:message key="listyearplan.title.formCruser"/></td>
        <td><bean:message key="listyearplan.title.formState"/></td>
        <td><bean:message key="listyearplan.title.formDetail"/></td>
    </tr>
    </thead>
    <tbody>
    <%
        if (yearPlanList.size() > 0) {
            for (int i = 0; i < yearPlanList.size(); i++) {
                tawwpYearPlanVO = (TawwpYearPlanVO) yearPlanList.get(i);
    %>
    <tr>
        <td height="20"><%=tawwpYearPlanVO.getName()%>
        </td>
        <td><%=tawwpYearPlanVO.getSysTypeName()%>
        </td>
        <td><%=tawwpYearPlanVO.getNetTypeName()%>
        </td>
        <td><%=tawwpYearPlanVO.getCrtime()%>
        </td>
        <td><%=tawwpYearPlanVO.getDeptName()%>
        </td>
        <td><%=tawwpYearPlanVO.getCruserName()%>
        </td>
        <td><%=tawwpYearPlanVO.getStateName()%>
        </td>
        <td>
            <a href="queryyearplanresult.do?yearplanid=<%=tawwpYearPlanVO.getId()%>">
                <img src="${app }/images/icons/icon1.gif" width="18" height="18">
            </a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td height="25" colspan="8">
            <bean:message key="listyearplan.title.nolist"/>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br>
<input type="button" value="<bean:message key="listyearplan.title.btnBack" />" onclick="javascript:goBack();"
       class="button">
