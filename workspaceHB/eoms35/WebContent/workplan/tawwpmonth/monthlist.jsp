<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.workplan.model.TawwpYearPlan" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthPlanVO" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<%
    String type = request.getSession().getAttribute("Mtype").toString();

    String yearId = (String) request.getAttribute("yearid");
    String monthId = (String) request.getAttribute("monthid");
    String selected = "selected";
    Hashtable monthPlanVOHash = (Hashtable) request.getAttribute("monthplanvohash");
    Hashtable yearPlanVOHash = (Hashtable) request.getAttribute("yearplanvohash");
    List listKey = (List) request.getAttribute("listKey");
    Enumeration hashKeys = null;
    List monthPlanVOList = null;
    TawwpYearPlan tawwpYearPlan = null;
    TawwpMonthPlanVO tawwpMonthPlanVO = null;
    TawwpMonthPlanVO tawwpMonthPVO = null;
    int count = 0;
    String netState = "";
%>

<script language="JavaScript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    var trueIcon = new Image();
    trueIcon.src = "../images/true.gif";
    var falseIcon = new Image();
    falseIcon.src = "../images/false.gif";

    function load(divID) {
        var targetTR = eval("document.all." + divID);
        if (targetTR.style.display != "block") {
            targetTR.style.display = "block";
            event.srcElement.src = trueIcon.src;
        } else {
            targetTR.style.display = "none";
            event.srcElement.src = falseIcon.src;
        }
    }

    function onMonth() {
        location.href = "../tawwpmonth/monthlist.do?yearid=" + document.forms[0].yearid.value + "&monthid=" + document.forms[0].monthid.value;
    }

    function onCopy(_yearPlanId) {
        var monthId = <%=monthId%>;//document.forms[0].monthid.value;
        if (monthId == "1") {
            alert("<bean:message key="monthlist.title.warnCantCopy" />");
            return false;
        } else {
            if (confirm("<bean:message key="monthlist.title.warnToMonth" />")) {
                location.href = "../tawwpmonth/monthcopy.do?yearplanid=" + _yearPlanId + "&monthid=" + monthId;
            }
        }
    }

    function onAdd(_yearPlanId, _netTypeId) {
        var monthId = <%=monthId%>//document.forms[0].monthid.value;
            location.href = "../tawwpmonth/netselect.do?yearplanid=" + _yearPlanId + "&nettypeid=" + _netTypeId + "&monthid=" + monthId;
    }

    function changeYear() {
        if (document.monthplan.yearid.value == "") {
            alert('请选择年份');
        } else {
            document.monthplan.action = "monthlists.do";
            document.monthplan.submit();
        }
    }
</script>

<!-- body begin -->

<form name="monthplan">

    <br>

    <table class="listTable" id="list-table">
        <%
            if (monthPlanVOHash.size() != 0) {
                hashKeys = monthPlanVOHash.keys();
                int m = 0;
        %>

        <caption>月计划列表
            <eoms:dict key="dict-workplan" dictId="yearFlag" isQuery="false" defaultId="<%=yearId%>"
                       onchange="changeYear()" selectId="yearid" beanId="selectXML"/>
            <eoms:dict key="dict-workplan" dictId="monthFlag" isQuery="false" defaultId="<%=monthId%>"
                       onchange="changeYear()" selectId="monthid" beanId="selectXML"/>
            <input type=hidden name="Mtype" value="<%=type%>">
        </caption>
        <thead>
        <tr>
            <td><bean:message key="monthlist.title.formPlanName"/></td>
            <td><bean:message key="monthlist.title.formNetName"/></td>
            <td><bean:message key="monthlist.title.formDeptName"/></td>
            <td><bean:message key="monthlist.title.formCrtime"/></td>
            <td><bean:message key="monthlist.title.formState"/></td>
            <td><bean:message key="monthlist.title.formEdit"/></td>
            <td><bean:message key="monthlist.title.formCopy"/></td>
            <td><bean:message key="monthlist.title.formAdd"/></td>
        </tr>
        </thead>
        <tbody>
        <%
            for (int z = 0; z < listKey.size(); z++) {
                //while (hashKeys.hasMoreElements()) {
                tawwpYearPlan = (TawwpYearPlan) listKey.get(z);
                if (tawwpYearPlan.getTypeIndex() == null) {
                    tawwpYearPlan.setTypeIndex("");
                }
                if (tawwpYearPlan.getNetTypeId().equals(type)) {
                    monthPlanVOList = (List) monthPlanVOHash.get(tawwpYearPlan);
                    m++;
                    count += 1;

        %>
        <tr>
            <td rowspan="<%=monthPlanVOList.size()%>"><%=StaticMethod.null2String(tawwpYearPlan.getName())%>
            </td>
            <%
                if (monthPlanVOList.size() == 0) {
            %>
            <td colspan="5"><bean:message key="monthlist.title.labNoNet"/></td>
            <td align="center">
                <a href="javascript:onCopy('<%=tawwpYearPlan.getId()%>');">
                    <img src="${app }/images/icons/table.gif" width="15" height="15"
                         alt='复制上月计划'/>
                </a>
            </td>
            <td align="center">
                <a href="javascript:onAdd('<%=tawwpYearPlan.getId()%>','<%=tawwpYearPlan.getNetTypeId()%>');">
                    <img src="${app }/images/icons/layout_add.png" alt="新建网元计划">
                </a>
            </td>
            <%
            } else {
                for (int k = 0; k < monthPlanVOList.size(); k++) {
                    tawwpMonthPlanVO = (TawwpMonthPlanVO) monthPlanVOList.get(k);
            %>
            <td>
                <%
                    if (tawwpMonthPlanVO.getNetName().equals("无网元")) {
                %>
                <%=tawwpMonthPlanVO.getNetTypeName()%>
                <% } else {%>
                <%=tawwpMonthPlanVO.getNetName()%>
                <% }%>
            </td>
            <td><%=tawwpMonthPlanVO.getDeptName()%>
            </td>
            <td><%=tawwpMonthPlanVO.getCrtime()%>
            </td>
            <td><%=tawwpMonthPlanVO.getConstituteStateName()%>
            </td>
            <td>
                <a href="../tawwpmonth/monthview.do?monthplanid=<%=tawwpMonthPlanVO.getId()%>">
                    <img src="${app }/images/icons/edit.gif"
                         alt="编辑网元计划"/>
                </a>
            </td>
            <td align="center">
                <a href="javascript:onCopy('<%=tawwpYearPlan.getId()%>');">
                    <img src="${app }/images/icons/table.gif" width="15" height="15"
                         alt="复制上月计划"/>
                </a>
            </td>
            <td align="center">
                <a href="javascript:onAdd('<%=tawwpYearPlan.getId()%>','<%=tawwpYearPlan.getNetTypeId()%>');">
                    <img src="${app }/images/icons/layout_add.png" alt="新建网元计划"/>
                </a>
            </td>
        </tr>
        <%
                        }//for end
                    }//if end
                }//if end
            }//for end
            if (m == 0) {
        %>
        <tr>
            <td colspan="8">
                <bean:message key="monthlist.title.labNolist"/>
            </td>
        </tr>

        <%
            }
        }//if end
        else {
        %>
        <tr>
            <td colspan="8">
                <bean:message key="monthlist.title.labNoPlan"/>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</form>

<!-- body end -->

<%@ include file="/common/footer_eoms.jsp" %>
