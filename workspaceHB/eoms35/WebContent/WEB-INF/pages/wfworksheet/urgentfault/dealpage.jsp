<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType==" + operateType);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("taskName"));
    System.out.println("=====taskName=====>" + taskName);
    request.setAttribute("operateType", operateType);
%>

<c:choose>
    <c:when test="${taskName=='DraftHumTask'}">
        <c:set var="methodValue" value="showDraftPage"/>
    </c:when>
    <c:when test="${taskName=='BackHumTask'}">
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

<c:url var="urlDeal" value="/sheet/urgentfault/urgentfault.do">
    <c:param name="method" value="${methodValue}"/>
    <c:param name="sheetKey" value="${sheetKey}"/>
    <c:param name="piid" value="${piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="${operateType}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
    <c:param name="backFlag" value="${backFlag}"/>
    <c:param name="processname" value="UrgentFaultMainFlowProcess"/>
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
        if ("${taskName}" != "cc") {
            v.preCommitUrl = "${app}/sheet/urgentfault/urgentfault.do?method=performPreCommit";
        }
    }

    Ext.onReady(function () {
        var dealTemplateId = "${dealTemplateId}";
        var strUrl = "${urlDeal}";
        var taskName = "${taskName}";
        if (dealTemplateId != null && dealTemplateId != "" && taskName != "DraftHumTask") {
            strUrl = '${app}/sheet/urgentfault/urgentfault.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&taskStatus=${taskStatus}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId=' + dealTemplateId;
        }
        var config = {
            url: strUrl,
            callback: initPage
        }
        eoms.util.appendPage("idSpecial", config);
    });


    function saveDealInfo() {
        var form = document.forms[0];
        var ajaxForm = Ext.getDom(form);
        if (v.check()) {
            v.passing = true;
            Ext.Ajax.request({
                form: ajaxForm,
                method: "post",
                url: "${app}/sheet/urgentfault/urgentfault.do?method=performSaveInfo",
                success: function () {
                    alert("保存成功！");
                }
            });
            v.passing = false;
        }
    }

    function saveDealTemplate(type) {
        var form = document.forms[0];
        var ajaxForm = Ext.getDom(form);
        if (v.check()) {
            v.passing = true;
            if (confirm("确认保存模板吗？")) {
                if (type == "new") {
                    Ext.Ajax.request({
                        form: ajaxForm,
                        method: "post",
                        url: "urgentfault.do?method=saveDealTemplate",
                        success: function () {
                            alert("保存模板成功！");
                        }
                    });
                } else {
                    Ext.Ajax.request({
                        form: ajaxForm,
                        method: "post",
                        url: "urgentfault.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}",
                        success: function () {
                            alert("保存模板成功！");
                        }
                    });
                }
            }
            v.passing = false;
        }
    }

    function saveTemplate(type) {
        var form = document.getElementById("theform");
        var ajaxForm = Ext.getDom(form);
        var templateManage = "${type}";
        if (v.check()) {
            v.passing = true;
            if (confirm("确认保存模板吗？")) {
                if (templateManage == "templateManage") {
                    form.action = "${app}/sheet/urgentfault/urgentfault.do?method=saveTemplate&templateId=${templateId}&type=${type}";
                    form.submit();
                } else {
                    if (type == "new") {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "urgentfault.do?method=saveTemplate&type=${type}",
                            success: function () {
                                alert("保存模板成功！");
                            }
                        });
                    } else {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "urgentfault.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
                            success: function () {
                                alert("保存模板成功！");
                            }
                        });
                    }
                }
            }
            v.passing = false;
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
</script>

<div id="sheetform">
    <html:form action="/urgentfault.do?method=performDeal" styleId="theform">
        <input type="hidden" name="${sheetPageName}operateType" value="${operateType}"/>
        <table>
            <tr>
                <td width="100%" align="left">
                    <c:if test="${operateType != ''&& operateType != '-10' && operateType != '8'&& operateType != '4'&& operateType != '61'&& operateType != '18'&& operateType != '46'}">
                        <html:button styleClass="btn" property="method.save" styleId="method.save"
                                     onclick="window.open('./urgentfault.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
                            <bean:message bundle="sheet" key="button.refereTemplate"/>
                        </html:button>
                        <c:choose>
                            <c:when test="${taskName=='DraftHumTask'}">
                                <html:button styleClass="btn" property="method.save" styleId="method.save"
                                             onclick="saveTemplate('new')">
                                    <bean:message bundle="sheet" key="button.saveTemplate"/>
                                </html:button>
                                <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
                                    <html:button styleClass="btn" property="method.save" styleId="method.save"
                                                 onclick="saveTemplate('current')">
                                        <bean:message bundle="sheet" key="button.saveCurTemplate"/>
                                    </html:button>
                                </c:if>
                            </c:when>
                            <c:otherwise>
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
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="dealTemplateNameRule" value="title;operateType"/>
                        <input type="hidden" name="title" value="${sheetMain.title}"/>
                        <input type="hidden" name="dictKey" value="dict-sheet-urgentfault"/>
                    </c:if>
                </td>
            </tr>
        </table>

        <!-- content -->
        <iframe name="newTemplate" src="" width="0" height="0"></iframe>
        <div id="idSpecial"></div>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"/>

        <c:choose>
            <c:when test="${operateType=='61'}">
                <div class="form-btns" id="btns">
                    <input type="submit" class="submit"
                           onclick="v.passing=false;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=performClaimTask'"
                           value="<bean:message bundle='sheet' key='button.done'/>"/>
                </div>
            </c:when>
            <c:when test="${taskName=='DraftHumTask'}">


                <div class="form-btns" id="btns">
                    <html:submit styleClass="btn" property="method.save" styleId="method.save">
                        <bean:message bundle="sheet" key="button.send"/>
                    </html:submit>
                    <html:button styleClass="btn" property="method.save" styleId="method.save"
                                 onclick="v.passing=true;javascript:saveDealInfo();">
                        <bean:message bundle='sheet' key='button.save'/>
                    </html:button>

                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=performSaveInfoAndExit&draft=1'"
                           value="<bean:message bundle='sheet' key='button.saveback'/>"/>

                        <%-- </c:if>--%>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=showDraftList'"
                           value="<bean:message bundle='sheet' key='button.back'/>"/>

                </div>

            </c:when>

            <c:when test="${taskName == 'HoldHumTask'}">
                <div class="form-btns" id="btns">
                    <input type="submit" class="submit" name="method.save" id="method.save"
                           onclick="javascript:ifisCopy();showCount();"
                           value="<bean:message bundle='sheet' key='button.done'/>">
                </div>
            </c:when>

            <c:otherwise>
                <!-- buttons -->
                <div class="form-btns hide" id="btns">

                    <input type="submit" class="submit" name="method.save" id="method.save"
                           onclick="javascript:ifisCopy()" value="<bean:message bundle='sheet' key='button.done'/>">
                    <html:button styleClass="btn" property="method.save" styleId="method.save"
                                 onclick="v.passing=true;javascript:saveDealInfo();">
                        <bean:message bundle='sheet' key='button.save'/>
                    </html:button><input type="submit" class="submit"
                                         onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=performSaveInfoAndExit'"
                                         value="<bean:message bundle='sheet' key='button.saveback'/>"/>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/urgentfault/urgentfault.do?method=showListsendundo'"
                           value="<bean:message bundle='sheet' key='button.back'/>"/>
                </div>
            </c:otherwise>
        </c:choose>

    </html:form>
</div>
