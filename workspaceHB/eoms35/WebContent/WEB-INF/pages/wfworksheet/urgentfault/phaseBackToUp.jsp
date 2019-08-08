<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<style type="text/css">
    .x-form-column {
        width: 320px
    }
</style>
<script type="text/javascript">
    function selfx() {
        var put = document.getElementsByName("${sheetPageName}deal");
        for (i = 0; i < put.length; i++) {
            put[i].checked = (put[i].checked) ? false : true;
        }
    }

    function selfcheckall() {
        var put1 = document.getElementsByName("${sheetPageName}checkall");
        var put2 = document.getElementsByName("${sheetPageName}checkbackall");
        if (put1[0].checked == true) {
            put2[0].checked = false;
        } else {
            put2[0].checked = true;
        }
    }

    function selfcheckbackall() {
        var put1 = document.getElementsByName("${sheetPageName}checkall");
        var put2 = document.getElementsByName("${sheetPageName}checkbackall");
        if (put2[0].checked == true) {
            put1[0].checked = false;
        } else {
            put1[0].checked = true;
        }
    }

    function check() {
        var put1 = document.getElementsByName("${sheetPageName}checkall");
        var put2 = document.getElementsByName("${sheetPageName}checkbackall");
        if (put1[0].checked == true) {
            put1[0].checked = false;
        }
        if (put2[0].checked == true) {
            put2[0].checked = false;
        }

    }

    var v;

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'theform'});
        v.custom = function () {
            var checkArray = document.getElementsByName('${sheetPageName}deal');
            var _performer = document.getElementsByName('${sheetPageName}performer');
            var _performerType = document.getElementsByName('${sheetPageName}performerType');
            var _performerLeader = document.getElementsByName('${sheetPageName}performerLeader');
            var _deal = ",";
            var _dealType = ",";
            var _dealLeader = ",";
            var i = 0;
            for (var c0 = 0; c0 < checkArray.length; c0++) {
                if (checkArray[c0].checked) {
                    i = 1;
                    _deal = _deal + _performer[c0].value + ",";
                    _dealType = _dealType + _performerType[c0].value + ",";
                    _dealLeader = _dealLeader + _performerLeader[c0].value + ",";
                }
            }
            if (i == 1) {
                $('${sheetPageName}dealPerformer').value = _deal.substring(1, _deal.length - 1);
                $('${sheetPageName}dealPerformerType').value = _dealType.substring(1, _dealType.length - 1);
                $('${sheetPageName}dealPerformerLeader').value = _dealLeader.substring(1, _dealLeader.length - 1);
            } else {
                alert("请选择要回复的对象");
                return false;
            }

            return true;
        };
    });
</script>
<div id="sheetform">
    <html:form action="/urgentfault.do?method=performProcessEvent" styleId="theform">
        <%
            String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));

        %>

        <input type="hidden" name="${sheetPageName}beanId" value="iUrgentFaultMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain"/> <!--main表Model对象类名-->
        <input type="hidden" name="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink"/> <!--link表Model对象类名-->
        <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
               value="UrgentFaultMainFlowProcess"/>
        <input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName"
               value="nonFlowOperate"/>
        <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value=""/>
        <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
               value=""/>
        <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value=""/>
        <!-- base info -->
        <input type="hidden" name="${sheetPageName}linkId" id="${sheetPageName}linkId" value="${sheetLink.id}"/>
        <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetLink.id}"/>
        <input type="hidden" name="${sheetPageName}sheetId" id="${sheetPageName}sheetId" value="${sheetMain.sheetId}"/>
        <input type="hidden" name="${sheetPageName}piid" value="${sheetMain.piid}"/>
        <input type="hidden" name="${sheetPageName}aiid" value="${sheetLink.aiid}"/>
        <input type="hidden" name="${sheetPageName}activeTemplateId" value="${taskName}"/>
        <input type="hidden" name="${sheetPageName}mainId" value="${sheetMain.id}"/>
        <input type="hidden" name="${sheetPageName}sheetKey" value="${sheetMain.id}"/>
        <input type="hidden" name="${sheetPageName}correlationKey" value="${sheetMain.correlationKey}"/>
        <input type="hidden" name="${sheetPageName}TKID" value="${sheetLink.tkid}"/>
        <input type="hidden" name="${sheetPageName}preLinkId" value="${preLink.id}"/>
        <input type="hidden" name="${sheetPageName}taskCompleteTime"
               value="${eoms:date2String(preLink.nodeCompleteLimit)}"/>
        <input type="hidden" name="${sheetPageName}taskName" value="${taskName}"/>
        <input type="hidden" name="${sheetPageName}taskStatus" value="${taskStatus}"/>
        <table class="formTable">
            <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
                    <input type="hidden" name="${sheetPageName}operateUserId" value="${sheetLink.operateUserId}"/>
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${sheetLink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
                    <input type="hidden" name="${sheetPageName}operateDeptId" value="${sheetLink.operateDeptId}"/>
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${sheetLink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
                </td>
            </tr>


            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateRoleName"/>*
                    <input type="hidden" name="${sheetPageName}operateRoleId" value="${sheetLink.operateRoleId}"/>
                </td>
                <td colspan="3">
                    <eoms:id2nameDB id="${sheetLink.operateRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
                </td>
            </tr>

            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operaterContact"/> *
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}operaterContact"
                           id="${sheetPageName}operaterContact" value="${sheetLink.operaterContact}"
                           alt="allowBlank:false"/>
                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateTime"/>*
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="${sheetPageName}operateTime" readonly="readonly"
                           value="${eoms:date2String(sheetLink.operateTime)}" id="${sheetPageName}operateTime"
                           alt="allowBlank:false"/>
                </td>
            </tr>
        </table>

        <table class="formTable">
            <tr>
                <td class="label"><bean:message bundle="sheet" key="phase.pleaseSelect"/>
                    <input type="checkbox" name="${sheetPageName}checkall" id="${sheetPageName}checkall"
                           value="${toTask.operateRoleId}"
                           onclick="selfcheckall();eoms.util.checkAll(this, '${sheetPageName}deal')">
                    全选</br>
                    <input type="checkbox" name="${sheetPageName}checkbackall" id="${sheetPageName}checkbackall"
                           value="${toTask.operateRoleId}" onclick="selfcheckbackall();selfx()">
                    反选
                </td>
                <td colspan="3">
                    <table class="listTable">
                        <logic:present name="toOperaterList" scope="request">
                            <tr>
                                <td class="label">
                                    目的任务对象
                                </td>
                                <td class="label">
                                    目的任务所有者
                                </td>
                                <td class="label">
                                    任务名称
                                </td>
                            </tr>
                            <logic:iterate id="toTask" name="toOperaterList"
                                           type="com.boco.eoms.sheet.urgentfault.task.impl.UrgentFaultTask">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="${sheetPageName}deal" id="${sheetPageName}deal"
                                               value="${toTask.operateRoleId}" onclick="check()">
                                        <eoms:id2nameDB id="${toTask.operateRoleId}"
                                                        beanId="tawSystemSubRoleDao"/><eoms:id2nameDB
                                            id="${toTask.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                                        <input type="hidden" name="${sheetPageName}performer"
                                               id="${sheetPageName}performer" value="${toTask.operateRoleId}"/>
                                        <input type="hidden" name="${sheetPageName}performerType"
                                               id="${sheetPageName}performerType" value="${toTask.operateType}"/>
                                        <input type="hidden" name="${sheetPageName}performerLeader"
                                               id="${sheetPageName}performerLeader" value="${toTask.taskOwner}"/>
                                    </td>
                                    <td>
                                        <eoms:id2nameDB id="${toTask.taskOwner}"
                                                        beanId="tawSystemSubRoleDao"/><eoms:id2nameDB
                                            id="${toTask.taskOwner}" beanId="tawSystemUserDao"/>&nbsp;
                                    </td>
                                    <td>
                                        <bean:write name="toTask" property="taskDisplayName" scope="page"/>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </logic:present>
                    </table>
                </td>
            </tr>
            <%if (taskName.equals("advice")) {%>
            <input type="hidden" name="${sheetPageName}operateType" value="-11"/>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.remark"/>
                </td>
                <td colspan="4">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                             alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                             alt="width:'500px'">${sheetlink.remark}</textarea>
                </td>
            </tr>
            <%} else if (taskName.equals("reply")) {%>
            <input type="hidden" name="${sheetPageName}operateType" value="9"/>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.remark"/>
                </td>
                <td colspan="4">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                             alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                             alt="width:'500px'">${sheetlink.remark}</textarea>
                </td>
            </tr>
        </table>
        <%} %>
        <script type="text/javascript">


        </script>

        <!-- buttons -->
        <div class="x-form-item">
            <div class="form-btns">
                <html:submit styleClass="btn" property="method.save" styleId="method.save">
                    <fmt:message key="button.save"/>
                </html:submit></div>
        </div>

    </html:form>
</div>
