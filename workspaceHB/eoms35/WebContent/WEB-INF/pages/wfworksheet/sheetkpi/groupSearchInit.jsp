<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@ include file="/common/header_eoms_form.jsp" %>
<%@page
        import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>


<%
    String sendTimeStartDate = com.boco.eoms.base.util.StaticMethod
            .getLocalString(-7);
    String sendTimeEndDate = com.boco.eoms.base.util.StaticMethod
            .getCurrentDateTime();

//	String fileName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("fileName"), "");
    String fileType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("fileType"), "");

    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
%>
<script type="text/javascript">

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: "theform"});
    });
</script>

<form id="theform" method="post" action="sheetKpiBaseAction.do?method=groupSearch">
    <input type="hidden" class="text" name="searchType" value="groupSearch">
    <table class="formTable">

        <tr>
            <td class="label"><bean:message bundle="sheet"
                                            key="query.sendTime"/></td>
            <td width="100%"><input type="hidden" name="main.sendTime"/>
                开始时间: <input type="text" name="sendTimeStartDate"
                             onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                             readonly="true" class="text" value="<%=sendTimeStartDate %>"/> &nbsp;&nbsp;
                结束时间： <input type="text" name="sendTimeEndDate"
                             id="sendTimeEndDate"
                             onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
                             value="<%=sendTimeEndDate %>" readonly="true" class="text"/>
                </div>
            </td>
        </tr>
        <%if ("1".equals(fileType)) {%>
        <tr>
            <td class="label">指标选择*</td>
            <td width="70%"><select type="select" name="filename" id="filename">
                <option value="B_sheet_traslevel2">专线故障平均历时报表</option>
                <option value="17">专线故障率报表</option>
                <option value="99">专线故障总历时报表</option>
                <option value="">专线业务可用率报表</option>
                <option value="17">专线投诉率报表</option>
                <option value="99">专线条数报表</option>
                <option value="">专线故障主动发现率报表</option>
                <option value="17">专线割接相关故障比例报表</option>
                <option value="99">专线故障原因定位成功率报表</option>
            </select></td>
        </tr>
        <%} else if ("2".equals(fileType)) {%>
        <tr>
            <td class="label">指标选择*</td>
            <td width="70%"><select type="select" name="filename" id="filename">
                <option value="B_sheet_traslevel2">全量集客故障工单列表</option>
                <option value="17">全量集客投诉工单列表</option>
                <option value="99">专线故障主动发现工单列表</option>
            </select></td>
        </tr>
        <%} %>
    </table>
    <input type="submit" name="method.save" id="method.save" class="btn"/>
</form>
<div ID="idSpecial"></div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp" %>
<%@ include file="/common/footer_eoms.jsp" %>
