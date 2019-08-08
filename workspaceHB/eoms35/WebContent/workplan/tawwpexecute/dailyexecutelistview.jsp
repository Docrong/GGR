<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthPlanVO" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpExecuteContentVO" %>

<%

    String year = (String) request.getAttribute("year");
    String month = (String) request.getAttribute("month");
    String firstdate = year + "-" + month + "-01 00:00:00";
    int week = TawwpUtil.getWeek(firstdate) - 1;
    if (week == 0) {
        week = 7;
    }

    int dayCount = TawwpUtil.getDayCountOfMonth(year, month);

    List monthPlanVOList = (List) request.getAttribute("monthPlanVOList");
    List stubMonthPlanVOList = (List) request.getAttribute("stubMonthPlanVOList");
    String curruser = (String) request.getAttribute("curruser");
%>

<script language="javascript">
    function changeDateSubmit() {
        var year = tawwpexecutelistviewform.year.options[tawwpexecutelistviewform.year.selectedIndex].value;
        var month = tawwpexecutelistviewform.month.options[tawwpexecutelistviewform.month.selectedIndex].value;
        tawwpexecutelistviewform.action = '../tawwpexecute/dailyexecutelistview.do?year=' + year + '&month=' + month;
        tawwpexecutelistviewform.submit();
    }

    function onExecuteAdd(_executeContentId, _monthPlanId, _userByStub) {
        location.href = "../tawwpexecute/executeadd.do?executecontentid=" + _executeContentId + "&monthplanid=" + _monthPlanId + "&userbystub=" + _userByStub;
    }

    function onExecuteEdit(_executeContentId, _monthPlanId, _userByStub, _executeType) {
        location.href = "../tawwpexecute/executeedit.do?executecontentid=" + _executeContentId + "&monthplanid=" + _monthPlanId + "&executetype=" + _executeType + "&userbystub=" + _userByStub;
    }


</script>


<form name="tawwpexecutelistviewform" method="POST" action="../tawwpexecute/dailyexecutelistview.do">
    <table class="formTable small">
        <tr>
            <td class="content">
                <select name="year" class="select" onChange="changeDateSubmit()">
                    <%
                        int yyyy = StaticMethod.null2int(year);
                        for (int i = yyyy - 5; i < yyyy + 5; i++) {
                            if (i == yyyy) {
                    %>
                    <option value="<%=i %>" selected><%=i %>
                    </option>
                    <% } else { %>
                    <option value="<%=i %>"><%=i %>
                    </option>
                    <% }
                    }%>
                </select>
            </td>
            <td class="label">
                年
            </td>
            <td class="content">
                <select name="month" class="select" onChange="changeDateSubmit()">
                    <%
                        for (int j = 1; j <= 12; j++) {
                            String tempmonth = j + "";
                            if (j < 10) {
                                tempmonth = "0" + tempmonth;
                            }
                            if (tempmonth.equals(month)) {
                    %>
                    <option value="<%=tempmonth %>" selected><%=tempmonth %>
                    </option>
                    <% } else { %>
                    <option value="<%=tempmonth %>"><%=tempmonth %>
                    </option>
                    <% }
                    }%>
                </select>
            </td>
            <td class="label">
                月
            </td>
        </tr>
    </table>
</form>


<table class="formTable large">
    <thead>
    <tr>
        <td nowrap rowspan="2">作业项目</td>
        <td nowrap rowspan="2">周期</td>
        <td>计</td>
        <%
            for (int i = 1; i <= dayCount; i++) {
        %>
        <td>
            <a href="../tawwpexecute/executeaddsnew.do?date=<%=i%>"><%=i%>
            </a>
        </td>
        <%
            }
        %>
    </tr>
    <tr>
        <td>周</td>
        <%
            for (int i = 1; i <= dayCount; i++) {
                if (week == 7) {
        %>
        <td>日</td>
        <%
            week = 1;
        } else {
        %>
        <td><%=week%>
        </td>
        <%
                    week++;
                }
            }
        %>
    </tr>
    </thead>

    <tbody>
    <%
        // 当前用户日常执行列表
        for (int i = 0; i < monthPlanVOList.size(); i++) {
            TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) monthPlanVOList.get(i);
            if (tawwpmonthplanvo.getMonthExecuteVOList() != null && tawwpmonthplanvo.getMonthExecuteVOList().size() > 0) {
    %>
    <tr>
        <td colspan="<%=dayCount+3 %>">
            [<%=tawwpmonthplanvo.getName() %>]&nbsp;&nbsp;&nbsp;&nbsp;网元:<%=tawwpmonthplanvo.getNetName() %>
        </td>
    </tr>
    <%
        }
        String userId = curruser;
        String tempStr = "";
        TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
        Hashtable executeContentVOHash = null;
        TawwpExecuteContentVO tawwpExecuteContentVO = null;
        Hashtable monthExecuteVOHash = tawwpmonthplanvo.getMonthExecuteVOHash();
        List monthExecuteVOList = tawwpmonthplanvo.getMonthExecuteVOList();
        for (int k = 0; k < monthExecuteVOList.size(); k++) {
            tawwpMonthExecuteVO = (TawwpMonthExecuteVO) monthExecuteVOList.get(k);
            executeContentVOHash = (Hashtable) monthExecuteVOHash.get(tawwpMonthExecuteVO);
    %>
    <tr>
        <td rowspan="2">
            <%=tawwpMonthExecuteVO.getName()%>
        </td>
        <td rowspan="2">
            <%=tawwpMonthExecuteVO.getCycleName()%>
        </td>
        <td>计</td>
        <%
            char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
            for (int x = 1; x < (temp.length + 1); x++) {
                if (temp[x - 1] == '1') {
                    if (x < 10) {
                        tempStr = tawwpMonthExecuteVO.getId() + "0" + String.valueOf(x);
                    } else {
                        tempStr = tawwpMonthExecuteVO.getId() + String.valueOf(x);
                    }
                    tawwpExecuteContentVO = (TawwpExecuteContentVO) (executeContentVOHash.get(tempStr));
                    if (tawwpExecuteContentVO != null) {
                        if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
                            if ("0".equals(tawwpmonthplanvo.getExecuteType()) ||
                                    (("," + tawwpExecuteContentVO.getCruser() + ",").indexOf("," +
                                            userId + ",") >= 0)) {
        %>
        <td>
            <a href="javascript:onExecuteEdit('<%=tawwpExecuteContentVO.getId()%>','<%=tawwpmonthplanvo.getId()%>','<%=tawwpmonthplanvo.getUserByStub()%>','<%=tawwpmonthplanvo.getExecuteType() %>');">
                <img src="${app }/images/icons/yes.gif">
            </a>
        </td>
        <%
        } else {
        %>
        <td>
            <a href="javascript:onExecuteAdd('<%=tawwpExecuteContentVO.getId()%>','<%=tawwpmonthplanvo.getId()%>','<%=tawwpmonthplanvo.getUserByStub()%>');">
                <img src="${app }/images/icons/yes.gif">
            </a>
        </td>
        <%
            }
        } else {
        %>
        <td>
            <a href="javascript:onExecuteAdd('<%=tawwpExecuteContentVO.getId()%>','<%=tawwpmonthplanvo.getId()%>','<%=tawwpmonthplanvo.getUserByStub()%>');">
                <img src="${app }/images/icons/yes.gif">
            </a>
        </td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
                }
            }
        %>
    </tr>
    <tr class="complete">
        <td>执</td>
        <%
            for (int x = 1; x < (dayCount + 1); x++) {
                if (x < 10) {
                    tempStr = tawwpMonthExecuteVO.getId() + "0" + String.valueOf(x);
                } else {
                    tempStr = tawwpMonthExecuteVO.getId() + String.valueOf(x);
                }

                tawwpExecuteContentVO = (TawwpExecuteContentVO) (executeContentVOHash.get(tempStr));
                if (tawwpExecuteContentVO != null) {
                    if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
                        if ("0".equals(tawwpmonthplanvo.getExecuteType()) ||
                                (("," + tawwpExecuteContentVO.getCruser() + ",").indexOf("," +
                                        userId + ",") >= 0)) {
        %>
        <td><img src="${app }/images/icons/yes.gif"></td>
        <%
        } else {
        %>
        <td></td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
                }
            }
        %>
    </tr>
    <%
            }
        }
    %>


    <%
        // 代理用户日常执行列表
        for (int i = 0; i < stubMonthPlanVOList.size(); i++) {
            TawwpMonthPlanVO tawwpmonthplanvo = (TawwpMonthPlanVO) stubMonthPlanVOList.get(i);
            if (tawwpmonthplanvo.getMonthExecuteVOList() != null && tawwpmonthplanvo.getMonthExecuteVOList().size() > 0) {
    %>
    <tr>
        <td colspan="<%=dayCount+3 %>">
            [<%=tawwpmonthplanvo.getName() %>]&nbsp;&nbsp;&nbsp;&nbsp;网元:<%=tawwpmonthplanvo.getNetName() %><font
                color="red">*代理</font>
        </td>
    </tr>
    <%
        }
        String userId = tawwpmonthplanvo.getUserByStub();
        String tempStr = "";
        TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
        Hashtable executeContentVOHash = null;
        TawwpExecuteContentVO tawwpExecuteContentVO = null;
        Hashtable monthExecuteVOHash = tawwpmonthplanvo.getMonthExecuteVOHash();
        List monthExecuteVOList = tawwpmonthplanvo.getMonthExecuteVOList();
        for (int k = 0; k < monthExecuteVOList.size(); k++) {
            tawwpMonthExecuteVO = (TawwpMonthExecuteVO) monthExecuteVOList.get(k);
            executeContentVOHash = (Hashtable) monthExecuteVOHash.get(tawwpMonthExecuteVO);
    %>
    <tr>
        <td rowspan="2">
            <%=tawwpMonthExecuteVO.getName()%>
        </td>
        <td rowspan="2">
            <%=tawwpMonthExecuteVO.getCycleName()%>
        </td>
        <td>计</td>
        <%
            char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
            for (int x = 1; x < (temp.length + 1); x++) {
                if (temp[x - 1] == '1') {
                    if (x < 10) {
                        tempStr = tawwpMonthExecuteVO.getId() + "0" + String.valueOf(x);
                    } else {
                        tempStr = tawwpMonthExecuteVO.getId() + String.valueOf(x);
                    }
                    tawwpExecuteContentVO = (TawwpExecuteContentVO) (executeContentVOHash.get(tempStr));
                    if (tawwpExecuteContentVO != null) {
                        if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
                            if ("0".equals(tawwpmonthplanvo.getExecuteType()) ||
                                    (("," + tawwpExecuteContentVO.getCruser() + ",").indexOf("," +
                                            userId + ",") >= 0)) {
        %>
        <td>
            <a href="javascript:onExecuteEdit('<%=tawwpExecuteContentVO.getId()%>','<%=tawwpmonthplanvo.getId()%>','<%=tawwpmonthplanvo.getUserByStub()%>','<%=tawwpmonthplanvo.getExecuteType()%>');">
                <img src="${app }/images/icons/yes.gif">
            </a>
        </td>
        <%
        } else {
        %>
        <td>
            <a href="javascript:onExecuteAdd('<%=tawwpExecuteContentVO.getId()%>','<%=tawwpmonthplanvo.getId()%>','<%=tawwpmonthplanvo.getUserByStub()%>');">
                <img src="${app }/images/icons/yes.gif">
            </a>
        </td>
        <%
            }
        } else {
        %>
        <td>
            <a href="javascript:onExecuteAdd('<%=tawwpExecuteContentVO.getId()%>','<%=tawwpmonthplanvo.getId()%>','<%=tawwpmonthplanvo.getUserByStub()%>');">
                <img src="${app }/images/icons/yes.gif">
            </a>
        </td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
                }
            }
        %>
    </tr>
    <tr class="complete">
        <td>执</td>
        <%
            for (int x = 1; x < (dayCount + 1); x++) {
                if (x < 10) {
                    tempStr = tawwpMonthExecuteVO.getId() + "0" + String.valueOf(x);
                } else {
                    tempStr = tawwpMonthExecuteVO.getId() + String.valueOf(x);
                }

                tawwpExecuteContentVO = (TawwpExecuteContentVO) (executeContentVOHash.get(tempStr));
                if (tawwpExecuteContentVO != null) {
                    if (!"0".equals(tawwpExecuteContentVO.getExecuteFlag())) {
                        if ("0".equals(tawwpmonthplanvo.getExecuteType()) ||
                                (("," + tawwpExecuteContentVO.getCruser() + ",").indexOf("," +
                                        userId + ",") >= 0)) {
        %>
        <td><img src="${app }/images/icons/yes.gif"></td>
        <%
        } else {
        %>
        <td></td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
            }
        } else {
        %>
        <td></td>
        <%
                }
            }
        %>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
<%
    session.setAttribute("monthPlanVOList", monthPlanVOList);
    session.setAttribute("stubMonthPlanVOList", stubMonthPlanVOList);
%>
<%@ include file="/common/footer_eoms.jsp" %>
