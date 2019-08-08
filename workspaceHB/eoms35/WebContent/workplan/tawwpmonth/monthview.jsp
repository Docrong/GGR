<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthPlanVO" %>
<%@ page import="com.boco.eoms.commons.system.user.model.*" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthCheckVO" %>
<%@ page import="com.boco.eoms.workplan.flow.model.Step" %>

<%
    TawwpMonthPlanVO tawwpMonthPlanVO = (TawwpMonthPlanVO) request.getAttribute("monthplanvo");
    List monthExecuteVOList = tawwpMonthPlanVO.getMonthExecuteVOList();
    List monthCheckVOList = tawwpMonthPlanVO.getMonthCheckVOList();
    TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
    TawwpMonthCheckVO tawwpMonthCheckVO = null;
    Step step = tawwpMonthPlanVO.getStep();
    String flowSerial = null;
    String stepSerial = null;
    if (step != null) {
        flowSerial = String.valueOf(step.getFlowSerial());
        stepSerial = step.getSerial();
    }
%>

<script language="JavaScript">

    Ext.onReady(function () {
        colorRows('executeTable');
    })
    Ext.onReady(function () {
        colorRows('checkTable');
    })

    function GoBack() {
        window.history.back();
    }

    function onEdit(_monthPlanId) {
        location.href = "../tawwpmonth/monthedit.do?monthplanid=" + _monthPlanId;
    }

    function onExport(_monthPlanId) {
        location.href = "../tawwpmonth/monthexport.do?monthid=" + _monthPlanId + "&reaction=addons/filedownload.jsp";
    }

    function monthReport(_monthPlanId) {
        location.href = "../tawwpmonth/reportmonth.do?monthid=" + _monthPlanId + "&reaction=addons/filedownload.jsp";
    }

    function onDelete(_monthPlanId) {
        if (!confirm("<bean:message key="monthview.title.warnMonthConfirm" />")) return;
        location.href = "../tawwpmonth/monthdel.do?monthplanid=" + _monthPlanId;
    }

    function onCheck(_monthPlanId) {

        var checkDept = document.forms[0].checkdept.value;
        var result = false;
        var selobj = document.getElementsByName("checkUsers");

        for (i = 0; i < selobj.length; i++) {
            if (selobj[i].checked == true) {
                result = true;
                break;
            }

        }

        if (!result) {
            alert("<bean:message key="monthview.title.warnNotSelectCkUser" />");
            return false;
        } else if (document.getElementById("execute") == null) {
            alert("请选择执行内容");
        } else if (document.getElementById("principal").value == null || document.getElementById("principal").value == "") {
            alert("请选择负责人");
        } else {
            if (!confirm("<bean:message key="monthview.title.warnSubmitToCheck" />")) return;
            document.forms[0].flowSerial.value = "<%=flowSerial%>";
            document.forms[0].stepserial.value = "<%=stepSerial%>";
            document.forms[0].monthplanid.value = _monthPlanId;
            document.forms[0].action = "../tawwpmonth/monthrefer.do";
            document.forms[0].submit();
        }
    }

    function onConmman(netserialno, executeid) {
        var _sHeight = 200;
        var _sWidth = 320;
        var sTop = (window.screen.availHeight - _sHeight) / 2;
        var sLeft = (window.screen.availWidth - _sWidth) / 2;
        var sFeatures = "dialogHeight: " + _sHeight + "px; dialogWidth: " + _sWidth + "px; dialogTop: " + sTop + "; dialogLeft: " + sLeft + "; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
        window.showModalDialog('commnetselect.do?netserialno=' + netserialno + '&executeid=' + executeid, window, sFeatures);

    }

    function onPublic(_monthPlanId) //直接进行发布
    {
        location.href = "../tawwpmonth/monthpublic.do?monthplanid=" + _monthPlanId;
    }

    function onExecuteEdit(_monthPlanId) {
        location.href = "../tawwpmonth/monthexecuteedit.do?monthplanid=" + _monthPlanId;
    }

    function onExecuteAdd(_monthPlanId) {
        location.href = "../tawwpmonth/monthexecuteadd.do?monthplanid=" + _monthPlanId;
    }

    function onExecuteAddTemp(_monthPlanId) {
        location.href = "../tawwpmonth/monthexecuteaddTemp.do?monthplanid=" + _monthPlanId;
    }
</script>

<!-- body begin -->
<form name="monthplanviewform" action="" method="post">
    <input type=hidden value=<%=flowSerial%> name="flowSerial"/>
    <input type=hidden value="" name="monthplanid"/>
    <input type=hidden value="" name="stepserial"/>
    <table class="formTable" id="detailTable">
        <caption>
            <%=tawwpMonthPlanVO.getYearFlag()%>
            &nbsp;
            <bean:message key="monthview.title.labYear"/>
            &nbsp;
            <%=tawwpMonthPlanVO.getMonthFlag()%>
            &nbsp;
            <bean:message key="monthview.title.labMonth"/>
            &nbsp;&lt;&nbsp;
            <%=tawwpMonthPlanVO.getName()%>
            &nbsp;&gt;
        </caption>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthview.title.detailFmDept"/>
            </td>
            <td width="250">
                <%=tawwpMonthPlanVO.getDeptName()%>
            </td>
            <td width="100" class="label">
                <bean:message key="monthview.title.detailFmNetList"/>
            </td>
            <td width="250">
                <%
                    if (tawwpMonthPlanVO.getNetName().equals("无网元")) {
                %>
                <%=tawwpMonthPlanVO.getNetTypeName()%>
                <%
                } else {
                %>
                <%=tawwpMonthPlanVO.getNetName()%>
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthview.title.detailFmExecuteType"/>
            </td>
            <td width="250">
                <%=tawwpMonthPlanVO.getExecuteTypeName()%>
            </td>
            <td width="100" class="label">
                <bean:message key="monthview.title.detailFmPrincipal"/>
            </td>
            <td width="250">
                <input type="hidden" id="principal" name="principal" value="<%=tawwpMonthPlanVO.getPrincipal()%>">
                <%=tawwpMonthPlanVO.getPrincipalName()%>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="monthview.title.detailFmConstituteState"/>
            </td>
            <td width="250">
                <%=tawwpMonthPlanVO.getConstituteStateName()%>
            </td>
            <td width="100" class="label">
                <bean:message key="monthview.title.detailFmExecuteState"/>
            </td>
            <td width="250">
                <%=tawwpMonthPlanVO.getExecuteStateName()%>
            </td>
        </tr>


        <tr>
            <td colSpan="4">
                <%
                    if (tawwpMonthPlanVO.getConstituteState().equals("1")
                            && null != tawwpMonthPlanVO.getUnicomType()
                            && !("").equals(tawwpMonthPlanVO.getUnicomType())
                            && (null == tawwpMonthPlanVO.getReportFlag() || !tawwpMonthPlanVO
                            .getReportFlag().equals("1"))) {
                %>
                <input type="button"
                       value="<bean:message key="monthview.title.btnMonthReport" />"
                       name="B1" class="button"
                       onclick="javascript:monthReport('<%=tawwpMonthPlanVO.getId()%>');">
                <%
                    }
                %>
                <%--<input type="button"
                    value="<bean:message key="monthview.title.btnExport" />" name="B1"
                    class="button"
                    onclick="javascript:onExport('<%=tawwpMonthPlanVO.getId()%>');">
                --%><%
							if (tawwpMonthPlanVO.getConstituteState().equals("0")
							|| tawwpMonthPlanVO.getConstituteState().equals("2")) {
				%>
                <input type="hidden" name="checkdept"
                       value="<%=step.getCheckUserIdStr()%>">
                <input type="hidden" name="checkuser"
                       value="<%=step.getCheckUserIdStr()%>">
                <input type="button"
                       value="<bean:message key="monthview.title.btnRemove" />" name="B1"
                       class="button"
                       onclick="javascript:onDelete('<%=tawwpMonthPlanVO.getId()%>');">
                <input type="button"
                       value="<bean:message key="monthview.title.btnEdit" />" name="B1"
                       class="button"
                       onclick="javascript:onEdit('<%=tawwpMonthPlanVO.getId()%>');">
                <%
                    if (step != null && step.getCheckUserList().size() > 0) {
                %>
                <input type="button"
                       value="<bean:message key="monthview.title.btnToCheck" />" name="B1"
                       class="button"
                       onclick="javascript:onCheck('<%=tawwpMonthPlanVO.getId()%>');">
                <%
                } else {
                %>
                <input type="button"
                       value="<bean:message key="monthview.title.btnDeploy" />" name="B1"
                       class="button"
                       onclick="javascript:onCheck('<%=tawwpMonthPlanVO.getId()%>');">
                <%
                        }
                    }
                %>

                <input type="button"
                       value="<bean:message key="monthview.title.btnBack" />" name="B1"
                       class="button" onclick="javascript:GoBack();">
            </td>
        </tr>
        <%
            if (step != null && step.getCheckUserList().size() > 0) {
        %>
        <tr width="700" align="center">
            <td width="700" align="center" colSpan="4">
                <bean:message key="monthview.title.labCanCheckUser"/>

                <%
                    List listCheckUser = step.getCheckUserList();
                    for (int i = 0; i < listCheckUser.size(); i++) {
                        TawSystemUser user = (TawSystemUser) listCheckUser.get(i);

                        //if(tawwpMonthPlanVO.getDeptId().equals(user.getDeptid())){
                %> <input type="checkbox" value="<%=user.getUserid() %>" name="checkUsers"><%=user.getDeptname() %>&nbsp;<%=user.getUsername()%>&nbsp;&nbsp;

                <% // }
                }
                %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <br>
    <br>
    <table class="listTable" id="executeTable">
        <caption>
            <bean:message key="monthview.title.executeFmTitle"/>
        </caption>
        <thead>
        <tr class="label">
            <td width="120">
                作业
                <p>
                    项目
            </td>

            <td width="120">
                业务
                <p>
                    类型
            </td>
            <td width="120">
                执行单
                <p>
                    位级别
            </td>
            <td width="120">
                适用
                <p>
                    说明
            </td>

            <td colspan="31">
                计划执行时间
            </td>
        </tr>
        </thead>
        <tbody>
        <%
            for (int i = 0; i < monthExecuteVOList.size(); i++) {
                tawwpMonthExecuteVO = (TawwpMonthExecuteVO) (monthExecuteVOList
                        .get(i));
        %>
        <tr>
            <td rowspan="3" id="execute" nowrap>
                <%=tawwpMonthExecuteVO.getName()%>
            </td>


            <td rowspan="3" id="execute" nowrap>
                <%=tawwpMonthExecuteVO.getBotype() %>
            </td>
            <td rowspan="3" id="execute" nowrap>
                <%=tawwpMonthExecuteVO.getExecutedeptlevel() %>
            </td>
            <td rowspan="3" id="execute" nowrap>
                <%=tawwpMonthExecuteVO.getAppdesc() %>
            </td>


            <td colspan="20">
                执行单位或人员：
                <%=tawwpMonthExecuteVO.getExecuterName()%>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <bean:message key="monthview.title.executeFmCycle"/>
                <%=tawwpMonthExecuteVO.getCycleName()%>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <bean:message key="monthview.title.executeFmForm"/>
                <a target="_blank"
                   href="../tawwpaddons/addonsread.do?action=read&window=self&myid=&model=50&addonsid=<%=tawwpMonthExecuteVO.getFormId()%>&reaction=./"><%=tawwpMonthExecuteVO.getFormName()%>
                </a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                是否必须上传附件：
                <%=tawwpMonthExecuteVO.getFileFlagName() %>
                <p/>
                执行起始时间：
                <% if (tawwpMonthExecuteVO.getStartHH() != null && !tawwpMonthExecuteVO.getStartHH().equals("")) { %>
                <%=tawwpMonthExecuteVO.getStartHH() %>
                时
                <%} %>
                &nbsp;&nbsp;
                执行结束时间：
                <% if (tawwpMonthExecuteVO.getEndHH() != null && !tawwpMonthExecuteVO.getEndHH().equals("")) { %>
                <%=tawwpMonthExecuteVO.getEndHH() %>
                时
                <%} %>
            </td>
            <td
                    colspan="<%=(Integer.parseInt(tawwpMonthExecuteVO
										.getDayCount()) - 20)%>"
                    align="right">
                <%=tawwpMonthExecuteVO.getCommandName()%>
                <%
                    if (false && tawwpMonthPlanVO.getNetId() != null
                            && !tawwpMonthPlanVO.getConstituteState().equals("1")
                            && !tawwpMonthPlanVO.getConstituteState().equals("3")) {
                %>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button"
                       value="<bean:message key="monthview.title.btnSelCommon" />"
                       name="B1" class="button"
                       onclick="javascript:onConmman('<%=tawwpMonthPlanVO.getNetSerialNo()%>','<%=tawwpMonthExecuteVO.getId()%>');">
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <%
                for (int j = 0; j < Integer.parseInt(tawwpMonthExecuteVO
                        .getDayCount()); j++) {
            %>
            <td width="5">
                <%=j + 1%>
            </td>
            <%
                }
            %>
        </tr>
        <tr>
            <%
                char[] temp = (tawwpMonthExecuteVO.getExecuteDate())
                        .toCharArray();
                for (int k = 0; k < temp.length; k++) {
                    if (temp[k] == '1') {
            %>
            <td>
                <img src="${app }/images/icons/yes.gif"/>
            </td>
            <%
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
        %>
        </tbody>
        <%
            if (tawwpMonthPlanVO.getConstituteState().equals("0")
                    || tawwpMonthPlanVO.getConstituteState().equals("2")) {
        %>
        <tr>
            <td colspan="35">
                <INPUT type="button"
                       value="<bean:message key="monthview.title.btnEdit" />"
                       name="button"
                       Onclick="javascript:onExecuteEdit('<%=tawwpMonthPlanVO.getId()%>');"
                       Class="button">
                <INPUT type="button"
                       value="<bean:message key="monthview.title.btnAdd" />" name="button"
                       Onclick="javascript:onExecuteAdd('<%=tawwpMonthPlanVO.getId()%>');"
                       Class="button">
                <INPUT type="button" value="增加临时计划" name="button"
                       Onclick="javascript:onExecuteAddTemp('<%=tawwpMonthPlanVO.getId()%>');"
                       Class="button">

            </td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <br>
    <br>

    <table class="listTable" id="checkTable">
        <caption>
            <bean:message key="monthview.title.checkFmTitle"/>
        </caption>
        <thead>
        <tr class="label">
            <td width="100">
                <bean:message key="monthview.title.checkFmUser"/>
            </td>
            <td width="150">
                <bean:message key="monthview.title.checkFmTime"/>
            </td>
            <td width="400">
                <bean:message key="monthview.title.checkFmContent"/>
            </td>
            <td width="50">
                <bean:message key="monthview.title.checkFmState"/>
            </td>
        </tr>
        </thead>
        <tbody>
        <%
            for (int l = 0; l < monthCheckVOList.size(); l++) {
                tawwpMonthCheckVO = (TawwpMonthCheckVO) (monthCheckVOList
                        .get(l));
        %>
        <tr>
            <td width="100">
                <%=tawwpMonthCheckVO.getCheckUserName()%>
            </td>
            <td width="150">
                <%=tawwpMonthCheckVO.getChecktime()%>
            </td>
            <td width="400">
                <%=tawwpMonthCheckVO.getContent()%>
            </td>
            <td width="50">
                <%=tawwpMonthCheckVO.getStateName()%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <br>
    <br>
    <%
        if (tawwpMonthPlanVO.getConstituteState().equals("0")
                || tawwpMonthPlanVO.getConstituteState().equals("2")) {
            if (step != null) {
                if (step.getCheckUserIdStr().equals("")) {
    %>
    <table border="0" width="100%" cellspacing="1" cellpadding="1"
           align=center>
        <tr>
            <td width="60%" align="center" class="header">
                &lt;&nbsp;
                <%=step.getName()%>
                &nbsp;&gt;
                <bean:message key="monthview.title.labNoCheckUser"/>
            </td>
        </tr>
    </table>
    <%
                }
            }
        }
    %>

</form>
<%@ include file="/common/footer_eoms.jsp" %>




