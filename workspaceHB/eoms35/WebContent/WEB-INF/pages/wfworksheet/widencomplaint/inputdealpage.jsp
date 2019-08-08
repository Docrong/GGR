<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintMain" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%@page import="java.util.ArrayList" %>
<%@page import="com.boco.eoms.commons.system.dict.model.TawSystemDictType" %>

<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    request.setAttribute("operateType", operateType);

    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    WidenComplaintMain cMain = new WidenComplaintMain();
    cMain = (WidenComplaintMain) request.getAttribute("sheetMain");
    String username = sessionform.getUsername();
    String userdept = sessionform.getDeptname();
    String urgentDegree = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getUrgentDegree());
    String complaintType1 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType1());
    String complaintType2 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType2());
    String complaintType3 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType());
    String complaintType4 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType4());
    String complaintType5 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType5());
    String complaintType6 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType6());
    String complaintType7 = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getComplaintType7());
    String customBrand = com.boco.eoms.base.util.StaticMethod.nullObject2String(cMain.getCustomBrand());
    ITawSystemDictTypeManager m = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
    String urgentDegreeCn = m.id2Name(urgentDegree);
    String customBrandCn = m.id2Name(customBrand);
    String complaintTypeCn = m.id2Name(complaintType1) + "." + m.id2Name(complaintType2) + "." + m.id2Name(complaintType3) + "." + m.id2Name(complaintType4) + "." + m.id2Name(complaintType5) + "." + m.id2Name(complaintType6) + "." + m.id2Name(complaintType7);

    ArrayList dealDesclist = m.getDictSonsByDictid("1010627");
    ArrayList dealResultlist = m.getDictSonsByDictid("1010611");
%>
<script type="text/javascript">
    //处理时限不能超过工单完成时限
    var dtnow = new Date();
    var str = '${sheetMain.sheetCompleteLimit}';
    str = str.replace(/-/g, "/");
    str = str.substring(0, str.length - 2);
    var dt2 = new Date(str);
    if (dt2 > dtnow) {
        document.getElementById("tmpCompleteLimit").value = '${sheetMain.sheetCompleteLimit}';
    } else {
        document.getElementById("tmpCompleteLimit").value = '';
    }


    function selectTypeOne() {
        var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
        if (document.getElementById("issueReasonTypeOne").selectedIndex == 0) {
            document.getElementById("${sheetPageName}issueEliminatReason").value = "";
        } else {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a;
        }
    }

    function selectTypeTwo() {
        var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
        var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
        var bvalue = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].value;
        var bplan = document.getElementById("linkIsComplaintSolve").options[document.getElementById("linkIsComplaintSolve").selectedIndex].value;

        if (document.getElementById("issueReasonTypeTwo").selectedIndex == 0) {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a;
        } else {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a + "|" + b;
            if ("10106150107" == bvalue && "1030102" == bplan) {
                eoms.form.enableArea('typecombop');
                document.getElementById('typecombop').style.display = 'block';
            }

        }
    }

    function selectTypeThree() {
        var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
        var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
        var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;
        if (document.getElementById("issueReasonTypeThree").selectedIndex == 0) {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a + "|" + b;
        } else {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a + "|" + b + "|" + c;
        }
    }


    function selectTypeFour() {
        var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
        var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
        var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;
        var d = document.getElementById("issueReasonTypeFour").options[document.getElementById("issueReasonTypeFour").selectedIndex].text;
        if (document.getElementById("issueReasonTypeFour").selectedIndex == 0) {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a + "|" + b + "|" + c;
        } else {
            document.getElementById("${sheetPageName}issueEliminatReason").value = a + "|" + b + "|" + c + "|" + d;
        }
    }

    function selectTypeFive() {
        var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
        var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
        var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;

        var d = document.getElementById("issueReasonTypeFour").options[document.getElementById("issueReasonTypeFour").selectedIndex].text;

        var e = document.getElementById("issueReasonTypeFive").options[document.getElementById("issueReasonTypeFive").selectedIndex].text;

        var isseValue = document.getElementById("${sheetPageName}issueEliminatReason").value;
        if (document.getElementById("issueReasonTypeFive").selectedIndex == 0) {

            document.getElementById("${sheetPageName}issueEliminatReason").value = isseValue;
        } else {
            alert(isseValue);
            document.getElementById("${sheetPageName}issueEliminatReason").value = isseValue + "|" + e;
        }
    }


    function selectTypeSix() {
        var a = document.getElementById("issueReasonTypeOne").options[document.getElementById("issueReasonTypeOne").selectedIndex].text;
        var b = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].text;
        var c = document.getElementById("issueReasonTypeThree").options[document.getElementById("issueReasonTypeThree").selectedIndex].text;

        var d = document.getElementById("issueReasonTypeFour").options[document.getElementById("issueReasonTypeFour").selectedIndex].text;

        var e = document.getElementById("issueReasonTypeFive").options[document.getElementById("issueReasonTypeFive").selectedIndex].text;
        var f = document.getElementById("issueReasonTypeSix").options[document.getElementById("issueReasonTypeSix").selectedIndex].text;
        var issfValue = document.getElementById("${sheetPageName}issueEliminatReason").value;
        if (document.getElementById("issueReasonTypeSix").selectedIndex == 0) {
            document.getElementById("${sheetPageName}issueEliminatReason").value = issfValue;
        } else {
            document.getElementById("${sheetPageName}issueEliminatReason").value = issfValue + "|" + f;
        }
    }

    function changeComplaintSolve(objectvalue) {
        var bvalue = document.getElementById("issueReasonTypeTwo").options[document.getElementById("issueReasonTypeTwo").selectedIndex].value;

        if (objectvalue == '1030101') {
            eoms.form.enableArea('ComplaintSolveDate');
            eoms.form.disableArea('NoSolveReseason', true);
            eoms.form.disableArea('PlanSolveDate', true);
            eoms.form.disableArea('PlanSolveDate2', true);
            eoms.form.disableArea('PlanSolveDate3', true);
            eoms.form.disableArea('typecombop', true);
            document.getElementById('typecombop').style.display = 'none';
        } else if (objectvalue == '1030102') {
            if ("10106150107" == bvalue) {
                eoms.form.enableArea('typecombop');
                document.getElementById('typecombop').style.display = 'block';
            } else {
                eoms.form.disableArea('typecombop', true);
                document.getElementById('typecombop').style.display = 'none';
            }
            eoms.form.enableArea('NoSolveReseason');
            eoms.form.disableArea('ComplaintSolveDate', true);
            eoms.form.disableArea('PlanSolveDate', true);
            eoms.form.disableArea('PlanSolveDate2', true);
            eoms.form.disableArea('PlanSolveDate3', true);
        } else {
            eoms.form.disableArea('ComplaintSolveDate', true);
            eoms.form.disableArea('PlanSolveDate', true);
            eoms.form.disableArea('NoSolveReseason', true);
            eoms.form.disableArea('PlanSolveDate2', true);
            eoms.form.disableArea('PlanSolveDate3', true);
            eoms.form.disableArea('typecombop', true);
            document.getElementById('typecombop').style.display = 'none';
        }
    }

    function changePlanSolveTypeparent(objectvalue) {
        if (objectvalue.charAt(8) == '1') {
            var issueReasonTypeChose = document.getElementById("issueReasonTypeFive").options[document.getElementById("issueReasonTypeFive").selectedIndex].value;
            if (issueReasonTypeChose == '1010615012101' || issueReasonTypeChose == '1010615012102') {

                var pDate = new Date().add(Date.MONTH, 6);
                $("${sheetPageName}planDate").value = pDate.format('Y-m-d H:i:s');
                eoms.form.enableArea('PlanSolveDate2');
            } else if (issueReasonTypeChose == '1010615012103' || issueReasonTypeChose == '1010615012104' || issueReasonTypeChose == '1010615012105') {
                var pDate = new Date().add(Date.MONTH, 9);
                $("${sheetPageName}planDate").value = pDate.format('Y-m-d H:i:s');
                eoms.form.enableArea('PlanSolveDate3');
            } else {
                eoms.form.enableArea('PlanSolveDate');
            }

        } else {
            eoms.form.disableArea('PlanSolveDate', true);
        }
    }

    function changeNoSolveReseason(objectvalue) {
        if (objectvalue.charAt(8) == '1') {
            eoms.form.enableArea('PlanSolveDate');
        } else {
            eoms.form.disableArea('PlanSolveDate', true);
        }
    }

    function changeIsUserConfirm(objectvalue) {
        if (objectvalue == '1030102') {
            eoms.form.enableArea('NoConfirmReason');
        } else {
            eoms.form.disableArea('NoConfirmReason', true);
        }
    }

    function changeIsRepeatComplaint(objectvalue) {
        if (objectvalue == '1030101') {
            eoms.form.enableArea('IsRepeatComplaint');
        } else {
            eoms.form.disableArea('IsRepeatComplaint', true);
        }
    }

    function changeIsUserStatisfied(objectvalue) {
        if (objectvalue == '101061503') {
            eoms.form.enableArea('IsUserStatisfied');
        } else {
            eoms.form.disableArea('IsUserStatisfied', true);
        }
    }

    function changeIsContactUser(objectvalue) {
        if (objectvalue == '1030101') {
            eoms.form.enableArea('IsContactUser');
            eoms.form.disableArea('NoContactReason', true);

        } else if (objectvalue == '1030102') {
            eoms.form.enableArea('NoContactReason');
            eoms.form.disableArea('IsContactUser', true);
        } else {
            eoms.form.disableArea('IsContactUser', true);
            eoms.form.disableArea('NoContactReason', true);
        }
    }

    function changeIsAlarm(objectvalue) {
        if (objectvalue == '1030101') {
            eoms.form.enableArea('IsAlarm');
        } else {
            eoms.form.disableArea('IsAlarm', true);
        }
    }

    function selectAiNet() {
        var a = document.getElementById("selectAiNetReason").options[document.getElementById("selectAiNetReason").selectedIndex].text;
        if (document.getElementById("selectAiNetReason").selectedIndex == 0) {
            document.getElementById("${sheetPageName}aiNetReasonDesc").value = "";
            document.getElementById("${sheetPageName}aiNetReasonDesc").disabled = false;
        } else {
            document.getElementById("${sheetPageName}aiNetReasonDesc").value = a;
            document.getElementById("${sheetPageName}aiNetReasonDesc").disabled = 'disabled';
        }
    }
</script>

<%@ include file="/WEB-INF/pages/wfworksheet/widencomplaint/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iWidenComplaintLinkManager"/>
    <input type="hidden" name="beanId" value="iWidenComplaintMainManager"/>
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintMain"/>
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.widencomplaint.model.WidenComplaintLink"/>
    <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
        </c:when>
    </c:choose>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <c:choose>
        <c:when test="${empty fPreTaskName }">
            <input type="hidden" name="phaseId" id="phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName=='DraftTask' }">
            <input type="hidden" name="phaseId" id="phaseId" value="RejectTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            备注说明*
        </td>
        <td colspan="3">
		        <textarea name="remark" class="textarea max" id="remark"
                          alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (taskName.equals("ExcuteHumTask")) {%>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>
    <%if (operateType.equals("102") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="CheckingHumTask"/>

    <tr>
        <td class="label">
            首次联系用户时间*
        </td>
        <td>

            <input type="text" class="text" name="linkFirstContactUesrTime" readonly="readonly"
                   id="linkFirstContactUesrTime" value="${eoms:date2String(sheetLink.linkFirstContactUesrTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="vtype:'lessThen',link:'linkReservationTime',vtext:'首次联系用户时间 不能晚于 预约上门时间',allowBlank:false"
        </td>

        <td class="label">
            预约上门时间*
        </td>
        <td>

            <input type="text" class="text" name="linkReservationTime" readonly="readonly" id="linkReservationTime"
                   value="${eoms:date2String(sheetLink.linkReservationTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="vtype:'moreThen',link:'linkFirstContactUesrTime',vtext:'预约上门时间 不能早于 首次联系用户时间',allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">修障材料:网线*</td>
        <td>
            <input type="text" class="text" name="linkNetline" id="linkNetline"
                   value="0" alt="allowBlank:false,maxLength:200,vtext:''"/>米
        </td>

        <td class="label">修障材料:光缆*</td>
        <td>
            <input type="text" class="text" name="linkOptical" id="linkOptical"
                   value="0" alt="allowBlank:false,maxLength:200,vtext:''"/>米
        </td>

    </tr>


    <tr>
        <td class="label">现场测速照片*</td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="linkSpeed" alt="allowBlank:false"
                             scope="request" idField="linkSpeed" appCode="widencomplaint"/>
        </td>
    </tr>

    <tr>
        <td class="label">现场服务回执单*</td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="linkReceipt" alt="allowBlank:false"
                             scope="request" idField="linkReceipt" appCode="widencomplaint"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.linkIfInvokeChange"/></td>
        <td class="content" colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfInvokeChange" id="${sheetPageName}linkIfInvokeChange"
                           initDicId="10301" defaultValue="${sheetLink.linkIfInvokeChange}" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <!-- 联系人 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.ndeptContact"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                   value="${sheetLink.ndeptContact}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
        </td>
        <!-- 联系人方式 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.ndeptContactPhone"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                   id="${sheetPageName}ndeptContactPhone"
                   value="${sheetLink.ndeptContactPhone}" alt="allowBlank:false,maxLength:40,vtext:'最多输入20汉字'"/>
        </td>
    </tr>
    <tr>
        <!-- 投诉性质 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.compProp"/></td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}compProp" id="${sheetPageName}compProp"
                           initDicId="1010610" defaultValue="${sheetLink.compProp}" styleClass="select-class"/>
        </td>
        <!-- 是否已答复客户 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.isReplied"/></td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}isReplied" id="${sheetPageName}isReplied"
                           initDicId="10301" defaultValue="1030101" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <!-- 问题消除时间 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.issueEliminatTime"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}issueEliminatTime" readonly="readonly"
                   id="${sheetPageName}issueEliminatTime" value="${eoms:date2String(sheetLink.issueEliminatTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="allowBlank:false"/>
        </td>
        <!-- 处理结果 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.dealResult"/></td>
        <td>
            <select id="dealResult" name="dealResult" alt="allowBlank:false">
                <%
                    if (dealResultlist.size() > 0) {
                        for (int i = 0; i < dealResultlist.size(); i++) {
                            TawSystemDictType dealResult = (TawSystemDictType) dealResultlist.get(i);
                            String dealResultname = dealResult.getDictName();
                %>
                <option value="<%=dealResultname%>"><%=dealResultname%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </td>

    </tr>

    <tr>
        <td class="label">
            问题原因分类*
        </td>
        <td class="content" colspan="3">
            <eoms:comboBox name="issueReasonTypeOne" id="issueReasonTypeOne" sub="issueReasonTypeTwo"
                           initDicId="1010626" onchange="selectTypeOne();" alt="allowBlank:false"/>
            <eoms:comboBox name="issueReasonTypeTwo" id="issueReasonTypeTwo" sub="issueReasonTypeThree"
                           styleClass="border" onchange="selectTypeTwo();" alt="allowBlank:false"/>
            <eoms:comboBox name="issueReasonTypeThree" id="issueReasonTypeThree" sub="issueReasonTypeFour"
                           styleClass="border" onchange="selectTypeThree();" alt="allowBlank:false"/>
            <eoms:comboBox name="issueReasonTypeFour" id="issueReasonTypeFour" onchange="selectTypeFour();"
                           alt="allowBlank:false"/>
            <div id="typecombop" style="display:none">
                <eoms:comboBox name="issueReasonTypeFive" id="issueReasonTypeFive" sub="issueReasonTypeSix"
                               initDicId="10106150121" onchange="selectTypeFive(this);" alt="allowBlank:false"/>
                <eoms:comboBox name="issueReasonTypeSix" id="issueReasonTypeSix" onchange="selectTypeSix();"
                               alt="allowBlank:false"/>
            </div>
        </td>
    </tr>

    <tr>
        <!-- 问题原因 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.issueEliminatReason"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}issueEliminatReason" id="${sheetPageName}issueEliminatReason"
                      class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'最多输入254汉字'"
                      readonly="true">${sheetLink.issueEliminatReason}</textarea>
        </td>
    </tr>

    <tr>
        <!-- 解决措施 -->
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.dealDesc"/></td>
        <td colspan="3">
            <select id="dealDesc" name="dealDesc" alt="allowBlank:false">
                <%
                    if (dealDesclist.size() > 0) {
                        for (int i = 0; i < dealDesclist.size(); i++) {
                            TawSystemDictType dealDesc = (TawSystemDictType) dealDesclist.get(i);
                            String dealDescname = dealDesc.getDictName();
                %>
                <option value="<%=dealDescname%>"><%=dealDescname%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </td>
    </tr>

    <!-- 湖北本地化需求增加字段 -->
    <tr>
        <td class="label">回复人</td>
        <td>
            <input type="text" class="text" name="linkReplyPerson" id="linkReplyPerson"
                   value="${sheetLink.linkReplyPerson}" alt="allowBlank:true,maxLength:40,vtext:'最多输入20汉字'"/>
        </td>
        <td class="label">回复人联系电话</td>
        <td>
            <input type="text" class="text" name="linkReplayPhone" id="linkReplayPhone"
                   value="${sheetLink.linkReplayPhone}" alt="allowBlank:true,maxLength:40,vtext:'最多输入20汉字'"/>
        </td>
    </tr>
    <tr>
        <td class="label">处理经过（解释口径）*</td>
        <td colspan="3">
            <textarea name="linkDealDesc" id="linkDealDesc" class="textarea max"
                      alt="allowBlank:false,maxLength:3000,vtext:'最多输入2000汉字'">${sheetLink.linkDealDesc}</textarea>
        </td>

    </tr>

    <tbody id='ReciveFaultId' style="display:none">
    <tr>
        <td class="label">
            故障工单号
        </td>
        <td colspan="3">
            <input type="text" class="text" name="linkReciveFaultId" id="linkReciveFaultId"
                   value="${sheetLink.linkReciveFaultId}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
    </tr>
    </tbody>
    <tr>
        <td class="label">投诉是否解决*</td>
        <td colspan="3">
            <eoms:comboBox name="linkIsComplaintSolve" id="linkIsComplaintSolve" initDicId="10301"
                           defaultValue="${sheetLink.linkIsComplaintSolve}" alt="allowBlank:false"
                           onchange="changeComplaintSolve(this.value);"/>
        </td>

    </tr>
    <tbody id='ComplaintSolveDate' style="display:none">
    <tr>
        <td class="label">
            解决时间
        </td>
        <td colspan="3">
            <input type="text" class="text" name="linkComplaintSolveDate" readonly="readonly"
                   id="linkComplaintSolveDate" value="${eoms:date2String(sheetLink.linkComplaintSolveDate)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
        </td>
    </tr>
    </tbody>
    <tbody id='NoSolveReseason' style="display:none">
    <tr>
        <td class="label">
            是否计划解决
        </td>
        <td colspan="3">
            <eoms:comboBox name="linkPlanSolveTypeparent" id="linkPlanSolveTypeparent" initDicId="1010616"
                           sub="linkPlanSolveType" alt="allowBlank:false"
                           onchange="changePlanSolveTypeparent(this.value);"/>
            <eoms:comboBox name="linkPlanSolveType" id="linkPlanSolveType" defaultValue="${sheetLink.linkPlanSolveType}"
                           alt="allowBlank:ture" onchange=""/>
        </td>
    </tr>
    </tbody>
    <tbody id='PlanSolveDate' style="display:none">
    <tr>
        <td class="label">
            计划解决时间*
        </td>
        <td colspan="3">
            <input type="text" class="text" name="linkPlanSolveDate" readonly="readonly" id="linkPlanSolveDate"
                   value="${eoms:date2String(sheetLink.linkPlanSolveDate)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
        </td>
    </tr>
    </tbody>
    <tbody id='PlanSolveDate2' style="display:none">
    <tr>
        <td class="label">
            计划解决时间*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}planDate" id="${sheetPageName}planDate"
                   value="${sheetPageName}planDate"/>
            <input type="text" class="text" name="linkPlanSolveDate" readonly="readonly" id="linkPlanSolveDate"
                   value="${eoms:date2String(sheetLink.linkPlanSolveDate)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="vtype:'lessThen',link:'${sheetPageName}planDate',vtext:'计划解决时间不能晚于工单当日之后的6个月',allowBlank:false"/>
        </td>
    </tr>
    </tbody>
    <tbody id='PlanSolveDate3' style="display:none">
    <tr>
        <td class="label">
            计划解决时间*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}planDate" id="${sheetPageName}planDate"
                   value="${sheetPageName}planDate"/>
            <input type="text" class="text" name="linkPlanSolveDate" readonly="readonly" id="linkPlanSolveDate"
                   value="${eoms:date2String(sheetLink.linkPlanSolveDate)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="vtype:'lessThen',link:'${sheetPageName}planDate',vtext:'计划解决时间不能晚于工单当日之后的9个月',allowBlank:false"/>
        </td>
    </tr>
    </tbody>
    <tr>
        <td class="label">用户是否确认或签署回执单</td>
        <td colspan="3">
            <eoms:comboBox name="linkIsUserConfirm" id="linkIsUserConfirm" initDicId="10301"
                           defaultValue="${sheetLink.linkIsUserConfirm}" alt="allowBlank:ture"
                           onchange="changeIsUserConfirm(this.value);"/>
        </td>
    </tr>
    <tbody id='NoConfirmReason' style="display:none">
    <tr>
        <td class="label">
            原因
        </td>
        <td colspan="3">
            <textarea name="linkNoConfirmReason" id="linkNoConfirmReason" class="textarea max"
                      alt="allowBlank:true,maxLength:3000,vtext:'最多输入2000汉字'">${sheetLink.linkNoConfirmReason}</textarea>
        </td>
    </tr>
    </tbody>

    <tr>
        <td class="label">投诉是否重复投诉</td>
        <td colspan="3">
            <eoms:comboBox name="linkIsRepeatComplaint" id="linkIsRepeatComplaint" initDicId="10301"
                           defaultValue="${sheetLink.linkIsRepeatComplaint}" alt="allowBlank:ture"
                           onchange="changeIsRepeatComplaint(this.value);"/>
        </td>
    </tr>
    <tbody id='IsRepeatComplaint' style="display:none">
    <tr>
        <td class="label">
            重复投诉原因
        </td>
        <td colspan="3">
            <eoms:comboBox name="linkRepeatComplaintType" id="linkRepeatComplaintType" initDicId="1010617"
                           defaultValue="${sheetLink.linkRepeatComplaintType}" alt="allowBlank:ture"/>
        </td>
    </tr>
    </tbody>

    <tr>
        <td class="label">用户满意情况</td>
        <td colspan="3">
            <eoms:comboBox name="linkIsUserStatisfied" id="linkIsUserStatisfied" initDicId="1010618"
                           defaultValue="${sheetLink.linkIsUserStatisfied}" alt="allowBlank:ture"
                           onchange="changeIsUserStatisfied(this.value);"/>
        </td>
    </tr>
    <tbody id='IsUserStatisfied' style="display:none">
    <tr>
        <td class="label">
            不满意原因
        </td>
        <td colspan="3">
            <textarea name="linkUserNoStatisfied" id="linkUserNoStatisfied" class="textarea max"
                      alt="allowBlank:true,maxLength:3000,vtext:'最多输入2000汉字'">${sheetLink.linkUserNoStatisfied}</textarea>
        </td>
    </tr>
    </tbody>

    <tr>
        <td class="label">是否联系用户</td>
        <td colspan="3">
            <eoms:comboBox name="linkIsContactUser" id="linkIsContactUser" initDicId="10301"
                           defaultValue="${sheetLink.linkIsContactUser}" alt="allowBlank:ture"
                           onchange="changeIsContactUser(this.value);"/>
        </td>
    </tr>
    <tbody id='IsContactUser' style="display:none">
    <tr>
        <td class="label">
            联系时间
        </td>
        <td>
            <input type="text" class="text" name="linkContactDate" readonly="readonly" id="linkContactDate"
                   value="${eoms:date2String(sheetLink.linkContactDate)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:true"/>
        </td>
        <td class="label">
            联系方式
        </td>
        <td>
            <eoms:comboBox name="linkContactship" id="linkContactship" initDicId="1010619"
                           defaultValue="${sheetLink.linkContactship}" alt="allowBlank:ture"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            联系人
        </td>
        <td>
            <input type="text" class="text" name="linkContactUser" id="linkContactUser"
                   value="${sheetLink.linkContactUser}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
        <td class="label">
            联系电话
        </td>
        <td>
            <input type="text" class="text" name="linkContactPhone" id="linkContactPhone"
                   value="${sheetLink.linkContactPhone}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
    </tr>
    </tbody>
    <tbody id='NoContactReason' style="display:none">
    <tr>
        <td class="label">
            未与用户联系原因
        </td>
        <td colspan="3">
            <eoms:comboBox name="linkNoContactReason" id="linkNoContactReason" initDicId="1010621"
                           defaultValue="${sheetLink.linkNoContactReason}" alt="allowBlank:ture"/>
        </td>
    </tr>
    </tbody>
    <tr>
        <td class="label">
            投诉涉及专业
        </td>
        <td>
            <eoms:comboBox name="linkSpecialty" id="linkSpecialty" initDicId="1010620"
                           defaultValue="${sheetLink.linkSpecialty}" alt="allowBlank:ture"/>
        </td>
        <td class="label">
            投诉区域性质
        </td>
        <td>
            <eoms:comboBox name="linkQuality" id="linkQuality" initDicId="1010622"
                           defaultValue="${sheetLink.linkQuality}" alt="allowBlank:ture"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            主覆盖小区CI
        </td>
        <td>
            <input type="text" class="text" name="linkAddressCI" id="linkAddressCI" value="${sheetLink.linkAddressCI}"
                   alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
        <td class="label">
            主覆盖小区名称
        </td>
        <td>
            <input type="text" class="text" name="linkAddressName" id="linkAddressName"
                   value="${sheetLink.linkAddressName}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            设备类型
        </td>
        <td>
            <input type="text" class="text" name="linkEquipmentType" id="linkEquipmentType"
                   value="${sheetLink.linkEquipmentType}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
        <td class="label">
            设备类型厂家
        </td>
        <td>
            <input type="text" class="text" name="linkEquipmentFactory" id="linkEquipmentFactory"
                   value="${sheetLink.linkEquipmentFactory}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
    </tr>
    <tr>
        <td class="label">是否有告警</td>
        <td colspan="3">
            <eoms:comboBox name="linkIsAlarm" id="linkIsAlarm" initDicId="10301" defaultValue="${sheetLink.linkIsAlarm}"
                           alt="allowBlank:ture" onchange="changeIsAlarm(this.value);"/>
        </td>
    </tr>
    <tbody id='IsAlarm' style="display:none">
    <tr>
        <td class="label">
            告警详情
        </td>
        <td>
            <input type="text" class="text" name="linkAlarmDetail" id="" linkAlarmDetail""
            value="${sheetLink.linkAlarmDetail}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
        <td class="label">
            故障工单号
        </td>
        <td>
            <input type="text" class="text" name="linkCommonfaultNumber" id="linkCommonfaultNumber"
                   value="${sheetLink.linkCommonfaultNumber}" alt="allowBlank:true,maxLength:40,vtext:'最多输入30汉字'"/>
        </td>
    </tr>
    </tbody>
    <tr>
        <td class="label">
            弱覆盖三网测试情况*
        </td>
        <td colspan="1.5">
            <eoms:comboBox name="linkCoverDian" id="linkCoverDian" initDicId="1010623"
                           defaultValue="${sheetLink.linkCoverDian}" alt="allowBlank:false"/>
        </td>
        <td colspan="2">
            <eoms:comboBox name="linkCoverLian" id="linkCoverLian" initDicId="1010624"
                           defaultValue="${sheetLink.linkCoverLian}" alt="allowBlank:false"/>
        </td>
    </tr>
    <!--湖北本地化需求增加字段  结束  -->
    <%}%>

    <%
        }
        if (taskName.equals("CheckingHumTask")) {
    %>
    <!-- 质检通过 -->
    <%if (operateType.equals("103")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="103"/>
    <input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1030101"/>
    <input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="1"/>

    <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.linkCheckResult"/></td>
        <td colspan="3">
            是
            <input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult"
                   value="1030101"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.linkCheckIdea"/>*</td>
        <td colspan="3">
            <textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max"
                      alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>
        </td>
    </tr>
    <%} %>
    <!-- 质检不通过 -->
    <%if (operateType.equals("104")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="104"/>
    <input type="hidden" name="${sheetPageName}mainCheckResult" id="${sheetPageName}mainCheckResult" value="1030102"/>
    <input type="hidden" name="${sheetPageName}mainIfCheck" id="${sheetPageName}mainIfCheck" value="1"/>
    <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.linkCheckResult"/></td>
        <td colspan="3">
            否
            <input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult"
                   value="1030102"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="widencomplaint" key="widenComplaintLink.linkCheckIdea"/>*</td>
        <td colspan="3">
            <textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max"
                      alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>
        </td>
    </tr>
    <%} %>


    <%
        }
        if (taskName.equals("HoldTask")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="Over"/>
    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
    <input type="hidden" name="status" id="status" value="1"/>

    <%}%>


    <%
        }
        if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")) {
    %>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="nodeAccessories" appCode="widencomplaint"/>
        </td>
    </tr>
    <%}%>
    <%if (taskName.equals("ExcuteHumTask") || taskName.equals("CheckingHumTask") || taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="operateType" id="operateType" value="61"/>
    <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> -->
    <%
            }
        }
    %>
    <% if (taskName.equals("cc")) {%>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="operateType" id="operateType" value="-15"/>
            <textarea name="remark" class="textarea max" id="remark"
                      alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
</table>


<% if (taskName.equals("ExcuteHumTask")) { %>

<% if (operateType.equals("102")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:质检
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="ExcuteHumTask0" type="role" roleId="1743" flowId="58"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>


<% } %>

<% if (taskName.equals("CheckingHumTask")) { %>

<% if (operateType.equals("103")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:投诉建单人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>


<% } %>