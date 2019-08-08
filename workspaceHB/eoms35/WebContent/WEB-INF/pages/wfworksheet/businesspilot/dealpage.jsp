<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink" %>


<%

    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));

    System.out.println("=====operateType==" + operateType);
    System.out.println("=====>" + operateType);
    request.setAttribute("operateType", operateType);

%>

<c:choose>
    <c:when test="${taskName=='draft'}">
        <c:set var="methodValue" value="showDraftPage"/>
    </c:when>
    <c:when test="${taskName=='reject'}">
        <c:set var="methodValue" value="showDraftPage"/>
    </c:when>
    <c:when test="${taskName=='cc'}">
        <c:set var="methodValue" value="showInputDealPage"/>
        <c:set var="operateType" value="-15"/>
    </c:when>
    <c:otherwise>
        <c:set var="methodValue" value="showInputDealPage"/>
        <c:set var="operateType" value="<%=operateType %>"/>
    </c:otherwise>
</c:choose>

<c:url var="urlDeal" value="/sheet/business/businesspilot.do">
    <c:param name="method" value="${methodValue}"/>
    <c:param name="sheetKey" value="${sheetKey}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="piid" value="${piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="${operateType}"/>
    <c:param name="backFlag" value="${backFlag}"/>
    <c:param name="processname" value="BusinessPilotProcess"/>
</c:url>

<script type="text/javascript">
    var ifAccept;
    var reviewResult;
    var roleTree;
    var v;

    function initPage() {
        v = new eoms.form.Validation({form: 'theform'});
        if ("${taskName}" != "DraftHumTask") {
            $('btns').removeClass('hide');
        }
        if ("${taskName}" == "pilot") {
            v.preCommitUrl = "${app}/sheet/business/businesspilot.do?method=performPreCommit";
        }
    }

    Ext.onReady(function () {
        var dealTemplateId = "${dealTemplateId}";
        var strUrl = "${urlDeal}";
        if (dealTemplateId != null && dealTemplateId != "") {
            strUrl = '${app}/sheet/business/businesspilot.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId=' + dealTemplateId;
        }
        var config = {
            url: strUrl,
            callback: initPage
        }


        eoms.util.appendPage("idSpecial", config);
    });


    function changeType1() {

        $('${sheetPageName}phaseId').value = "pilot";
        $('${sheetPageName}operateType').value = "3";
        //alert($('${sheetPageName}phaseId').value);

    }

    function changeType2() {

        $('${sheetPageName}phaseId').value = "draft";
        $('${sheetPageName}operateType').value = "22";
        //alert($('${sheetPageName}operateType').value);

    }

    function saveDealTemplate(type) {
        var form = document.forms[0];
        var ajaxForm = Ext.getDom(form);
        if (confirm("${eoms:a2u('确认保存模板吗？')}")) {
            if (type == "new") {
                Ext.Ajax.request({
                    form: ajaxForm,
                    method: "post",
                    url: "businesspilot.do?method=saveDealTemplate&operateType=${operateType}",
                    success: function () {
                        alert("${eoms:a2u('保存模板成功！')}");
                    }
                });
            } else {
                Ext.Ajax.request({
                    form: ajaxForm,
                    method: "post",
                    url: "businesspilot.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}&operateType=${operateType}",
                    success: function () {
                        alert("${eoms:a2u('保存模板成功！')}");
                    }
                });
            }
        }
    }

    function ifisCopy() {
        try {
            var ope = '${operateType}';
            if (ope == '11' || ope == '55' || ope == '4') {

                $('${sheetPageName}hasNextTaskFlag').value = 'true';
            }

            var str = document.forms[0]['${sheetPageName}copyPerformer'].value;
            var taskName = document.forms[0]['taskName'].value;
            if (taskName == "cc" || taskName == "reply" || taskName == "advice") {
                if (str == null || str == "")
                    $('${sheetPageName}hasNextTaskFlag').value = 'true';
            }
        } catch (e) {
        }
    }

    function saveAlert() {
        try {
            if (ope != '4') {
                alert('${eoms:a2u('请注意，不会保存派单及抄送对象')}');
            }
        } catch (e) {
        }

    }

</script>

<div id="sheetform">
    <html:form action="/businesspilot.do?method=newPerformDeal" styleId="theform">


        <table>
            <tr>
                <td width="100%" align="left">
                    <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4'&& operateType != '61' && taskName !='reject'}">
                        <c:if test="${taskName != 'draft'}">
                            <html:button styleClass="btn" property="method.save" styleId="method.save"
                                         onclick="window.open('./businesspilot.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}',null,'left=300,top=150,height=400,width=500,scrollbars=yes')">
                                <bean:message bundle="sheet" key="button.refereTemplate"/>
                            </html:button>
                            <html:button styleClass="btn" property="method.save" styleId="method.save"
                                         onclick="saveDealTemplate('new')">
                                <bean:message bundle="sheet" key="button.saveTemplate"/>
                            </html:button>
                            <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
                                <html:button styleClass="btn" property="method.save" styleId="method.save"
                                             onclick="saveDealTemplate('current')">
                                    <bean:message bundle="sheet" key="button.saveCurTemplate"/>
                                </html:button>
                            </c:if>
                        </c:if>
                        <input type="hidden" name="dealTemplateNameRule" value="title;operateType"/>
                        <input type="hidden" name="title" value="${sheetMain.title}"/>

                        <input type="hidden" name="dictKey" value="dict-sheet-businesspilot"/>
                    </c:if>
                </td>
            </tr>
        </table>
        <!-- content -->
        <div id="idSpecial"></div>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"/>
        <c:choose>
            <c:when test="${operateType=='61'}">
                <div class="form-btns" id="btns">
                    <input type="submit" class="submit"
                           onclick="this.form.action='${app}/sheet/business/businesspilot.do?method=performClaimTask'"
                           value="<bean:message bundle='sheet' key='button.accept'/>"/>
                </div>
            </c:when>
            <c:when test="${taskName=='draft'}">
                <div class="form-btns" id="btns">
                    <html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();"
                                 styleId="method.save">
                        <bean:message bundle="sheet" key="button.send"/>
                    </html:submit>

                    <html:submit styleClass="btn" property="method.saveDraft"
                                 onclick="v.passing=true;javascript:changeType2();" styleId="method.saveDraft">
                        <bean:message bundle="sheet" key="button.saveAsDraft"/>
                    </html:submit>
                </div>
            </c:when>
            <c:when test="${taskName=='reject'}">
                <div class="form-btns" id="btns">
                    <html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();"
                                 styleId="method.save">
                        <bean:message bundle="sheet" key="button.send"/>
                    </html:submit>
                </div>
            </c:when>
            <c:otherwise>
                <c:if test="${taskName=='pilot'&&operateType!='11'&&operateType!='4'}">
                    <font color=red>${eoms:a2u('为避免丢单,启动其他流程后请您尽快提交本工单!')}</font>
                </c:if>
                <!-- buttons -->
                <div class="form-btns hide" id="btns">

                    <input type="submit" class="submit" name="method.save" id="method.save"
                           onclick="javascript:ifisCopy();" value="<bean:message bundle='sheet' key='button.done'/>">
                    <c:if test="${taskName!='cc'}">
                        <input type="submit" class="submit"
                               onclick="saveAlert();v.passing=true;this.form.action='${app}/sheet/business/businesspilot.do?method=performSaveInfo'"
                               value="<bean:message bundle='sheet' key='button.save'/>"/>
                        <input type="submit" class="submit"
                               onclick="saveAlert();v.passing=true;this.form.action='${app}/sheet/business/businesspilot.do?method=performSaveInfoAndExit'"
                               value="<bean:message bundle='sheet' key='button.saveback'/>"/>
                    </c:if>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/business/businesspilot.do?method=showListsendundo'"
                           value="<bean:message bundle='sheet' key='button.back'/>"/>
                </div>
            </c:otherwise>
        </c:choose>

    </html:form>
</div>
