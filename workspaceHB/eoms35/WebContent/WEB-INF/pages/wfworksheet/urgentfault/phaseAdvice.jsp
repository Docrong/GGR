<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<style type="text/css">
    .x-form-column {
        width: 320px
    }
</style>

<script type="text/javascript">

    function checkDeal() {
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
            alert("请选择要通知的对象");
        }
    }


    var v;

    function initPage() {


    }

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'theform'});
    });
</script>
<div id="sheetform">
    <html:form action="/urgentfault.do?method=performProcessEvent" styleId="theform">
    <style type="text/css">
        .x-form-column {
            width: 320px
        }
    </style>

    <script type="text/javascript">
        var v;

        function initPage() {
            v = new eoms.form.Validation({form: 'theform'});


        }

        Ext.onReady(function () {
            var strUrl = '${app}/sheet/urgentfault/urgentfault.do?method=showInputEventPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType1=${operateType1}&operateFlag=${operateFlag}';

            var config = {
                url: strUrl,
                callback: initPage
            }
            eoms.util.appendPage("idSpecial", config);
        });
    </script>
    <div id="sheetform">
        <html:form action="/urgentfault.do?method=performProcessEvent" styleId="theform">
        <style type="text/css">
            .x-form-column {
                width: 320px
            }
        </style>

        <script type="text/javascript">
            var v;

            Ext.onReady(function () {
                v = new eoms.form.Validation({form: 'theform'});
            });
        </script>
        <div id="sheetform">
            <html:form action="/urgentfault.do?method=performProcessEvent" styleId="theform">
                <%
                    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
                    String dealPerformer = (String) request.getAttribute("dealPerformer");
                    String dealPerformerLeader = (String) request.getAttribute("dealPerformerLeader");
                    String dealPerformerType = (String) request.getAttribute("dealPerformerType");
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
                <input type="hidden" name="${sheetPageName}dealPerformer" value="${dealPerformer}"/>
                <input type="hidden" name="${sheetPageName}dealPerformerLeader" value="${dealPerformerLeader}"/>
                <input type="hidden" name="${sheetPageName}dealPerformerType" value="${dealPerformerType}"/>
                <%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtmlnew.jsp" %>

                <table class="formTable">
                    <logic:present name="toOperaterList" scope="request">
                        <logic:iterate id="toTask" name="toOperaterList"
                                       type="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask">
                            <tr>
                                <td>
                                    <input type="checkbox" name="${sheetPageName}deal" id="${sheetPageName}deal"
                                           value="${toTask.operateRoleId}" onclick="check()">
                                    <input type="hidden" name="${sheetPageName}performer" id="${sheetPageName}performer"
                                           value="${toTask.operateRoleId}"/>
                                    <input type="hidden" name="${sheetPageName}performerType"
                                           id="${sheetPageName}performerType" value="${toTask.operateType}"/>
                                    <input type="hidden" name="${sheetPageName}performerLeader"
                                           id="${sheetPageName}performerLeader" value="${toTask.taskOwner}"/>
                                </td>
                                <td>
                                    <eoms:id2nameDB id="${toTask.operateRoleId}"
                                                    beanId="tawSystemSubRoleDao"/><eoms:id2nameDB
                                        id="${toTask.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                                </td>
                                <td colspan="2">
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

                    <%if (taskName.equals("advice")) {%>
                    <input type="hidden" name="${sheetPageName}operateType" value="-11"/>
                    <tr>
                        <td class="label">
                            <bean:message bundle="sheet" key="linkForm.remark"/>*
                        </td>
                        <td colspan="3">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                             alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                             alt="width:'500px'">${sheetlink.remark}</textarea>
                        </td>
                    </tr>
                    <%} else if (taskName.equals("reply")) {%>
                    <input type="hidden" name="${sheetPageName}operateType" value="9"/>
                    <tr>
                        <td class="label">
                            <bean:message bundle="sheet" key="linkForm.remark"/>*
                        </td>
                        <td colspan="3">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                             alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                             alt="width:'500px'">${sheetlink.remark}</textarea>
                        </td>
                    </tr>
                </table>
                <%} %>


                <!-- buttons -->
                <div class="x-form-item">
                    <div class="form-btns">
                        <html:submit styleClass="btn" property="method.save" styleId="method.save">
                            <fmt:message key="button.save"/>
                        </html:submit></div>
                </div>


            </html:form>

        </div>
