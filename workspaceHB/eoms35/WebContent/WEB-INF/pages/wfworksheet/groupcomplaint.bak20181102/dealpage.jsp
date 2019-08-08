<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userId = sessionform.getUserid();
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType=====>" + operateType);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("taskName"));
    System.out.println("=====taskName=====>" + taskName);
    request.setAttribute("operateType", operateType);
%>

<c:choose>
    <c:when test="${taskName=='DraftHumTask'}">
        <c:set var="methodValue" value="showDraftPage"/>
    </c:when>
    <c:when test="${taskName=='ByRejectHumTask'}">
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

<c:url var="urlDeal" value="/sheet/groupcomplaint/groupcomplaint.do">
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
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
    <c:param name="backFlag" value="${backFlag}"/>
    <c:param name="processname" value="GroupComplaintProcess"/>
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
            //v.preCommitUrl = "${app}/sheet/groupcomplaint/groupcomplaint.do?method=performPreCommit";
        }
    }

    Ext.onReady(function () {
        var dealTemplateId = "${dealTemplateId}";
        var strUrl = "${urlDeal}";
        var taskName = "${taskName}";
        if (dealTemplateId != null && dealTemplateId != "" && taskName != "DraftHumTask") {
            strUrl = '${app}/sheet/groupcomplaint/groupcomplaint.do?method=showTemplateDealInputSheetPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&taskStatus=${taskStatus}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&dealTemplateId=' + dealTemplateId;
        }

        var config = {
            url: strUrl,
            callback: initPage
        }


        eoms.util.appendPage("idSpecial", config);
    });


    function changeType1() {

        $('${sheetPageName}phaseId').value = "FirstExcuteHumTask";
        $('${sheetPageName}operateType').value = "3";
        //alert($('${sheetPageName}phaseId').value);

    }


    function saveDealInfo() {
        var form = document.forms[0];
        var ajaxForm = Ext.getDom(form);
        if (v.check()) {
            v.passing = false;
            Ext.Ajax.request({
                form: ajaxForm,
                method: "post",
                url: "${app}/sheet/groupcomplaint/groupcomplaint.do?method=performSaveInfo",
                success: function () {
                    alert("保存成功！");
                }
            });

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
                        url: "groupcomplaint.do?method=saveDealTemplate",
                        success: function () {
                            alert("保存模板成功！");
                        }
                    });
                } else {
                    Ext.Ajax.request({
                        form: ajaxForm,
                        method: "post",
                        url: "groupcomplaint.do?method=saveDealTemplate&dealTemplateId=${dealTemplateId}",
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
                    form.action = "${app}/sheet/groupcomplaint/groupcomplaint.do?method=saveTemplate&templateId=${templateId}&type=${type}";
                    form.submit();
                } else {
                    if (type == "new") {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "groupcomplaint.do?method=saveTemplate&type=${type}",
                            success: function () {
                                alert("保存模板成功！");
                            }
                        });
                    } else {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "groupcomplaint.do?method=saveTemplate&templateId=${dealTemplateId}&type=${type}",
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

    function showCount() {
        try {
            var count = document.forms[0]['${sheetPageName}mainRejectCount'].value;
            var mainRejectCount = parseInt(count, 10) + 1;
            $('${sheetPageName}mainRejectCount').value = mainRejectCount;
        } catch (e) {
        }
    }


    function searchKnowledge() {

        Ajax.Request(
            "${app}/sheet/groupcomplaint/groupcomplaint.do?method=getSearchKnowledgeResult",
            {
                method: "GET",
                parameters: "&sheetKey=${sheetMain.id}",
                onComplete: handleCallBack
            }
        );
        var height = window.screen.height / 6;
        var width = window.screen.width / 4;

        features = "dialogWidth:" + 1024 + "px;dialogHeight:" + 768 + "px; scroll:2; help:0; status:No; fullscreen;";
        features += "dialogLeft:" + width + ";dialogTop:" + height;
    }

    function handleCallBack(originalRequest) //回调函数，对服务端的响应处理，监视response状态

    {
        var url = originalRequest.responseText;
        window.open(url);
    }
</script>

<div id="sheetform">
    <html:form action="/groupcomplaint.do?method=performDeal" styleId="theform">
        <input type="hidden" name="type" id="type" value="interface"/>
        <input type="hidden" name="interface" id="interface" value="interface"/>
        <input type="hidden" name="userName" id="userName" value="<%=userId%>"/>
        <input type="hidden" name="${sheetPageName}operateType" value="${operateType}"/>
        <table>
            <tr>
                <td width="100%" align="left">
                    <c:if test="${operateType != '' && operateType != '18' && operateType != '-10'&& operateType != '4'&& operateType != '61'
	  	&& operateType != '8' && operateType != '1' && operateType != '46' && operateType != '11' && operateType != '56' && operateType != '54'
	  	 && operateType != '17' && operateType != '53' }">
                        <html:button styleClass="btn" property="method.save" styleId="method.save"
                                     onclick="window.open('./groupcomplaint.do?method=getDealTemplatesByUserId&sheetKey=${sheetMain.id}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskStatus=${taskStatus}&operateType=${operateType}&draft=${taskName}',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
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
                        <input type="hidden" name="dictKey" value="dict-sheet-groupcomplaint"/>
                    </c:if>
                </td>
            </tr>
        </table>
        <!-- content -->
        <div id="idSpecial"></div>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"/>
        <c:choose>

            <c:when test="${operateType=='61'}">
                <div class="form-btns" id="btns">
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=performClaimTask'"
                           value="<bean:message bundle='sheet' key='button.accept'/>"/>
                </div>
            </c:when>

            <c:when test="${taskName=='DraftHumTask'}">
                <div class="form-btns" id="btns">
                    <html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();"
                                 styleId="method.save">
                        <bean:message bundle="sheet" key="button.send"/>
                    </html:submit>
                    <html:button styleClass="btn" property="method.save" styleId="method.save"
                                 onclick="v.passing=true;javascript:saveDealInfo();">
                        <bean:message bundle='sheet' key='button.save'/>
                    </html:button>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=performSaveInfoAndExit'"
                           value="<bean:message bundle='sheet' key='button.saveback'/>"/>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=showDraftList'"
                           value="<bean:message bundle='sheet' key='button.back'/>"/>
                </div>
            </c:when>

            <c:when test="${taskName=='ByRejectHumTask'}">
                <div class="form-btns" id="btns">
                    <html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();"
                                 styleId="method.save">
                        <bean:message bundle="sheet" key="button.send"/>
                    </html:submit>
                    <html:button styleClass="btn" property="method.save" styleId="method.save"
                                 onclick="v.passing=true;javascript:saveDealInfo();">
                        <bean:message bundle='sheet' key='button.save'/>
                    </html:button>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=performSaveInfoAndExit'"
                           value="<bean:message bundle='sheet' key='button.saveback'/>"/>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=showListsendundo'"
                           value="<bean:message bundle='sheet' key='button.back'/>"/>
                </div>
            </c:when>

            <c:when test="${operateType=='4'}">
                <div class="form-btns" id="btns">
                    <input type="submit" class="submit" name="method.save" id="method.save"
                           onclick="javascript:ifisCopy();showCount();"
                           value="<bean:message bundle='sheet' key='button.done'/>">
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
                <c:if test="${taskName=='FirstExcuteHumTask' || taskName=='SecondExcuteHumTask'}">
                    <input type="button" title="knowledge" value="查询知识库" onclick="searchKnowledge();">
                </c:if>

                <div class="form-btns hide" id="btns">
                    <input type="submit" class="submit" name="method.save" id="method.save"
                           onclick="javascript:ifisCopy();showCount();"
                           value="<bean:message bundle='sheet' key='button.done'/>">
                    <html:button styleClass="btn" property="method.save" styleId="method.save"
                                 onclick="v.passing=true;javascript:saveDealInfo();">
                        <bean:message bundle='sheet' key='button.save'/>
                    </html:button><%-- <c:if test="${taskName!='cc'}">--%>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=performSaveInfoAndExit'"
                           value="<bean:message bundle='sheet' key='button.saveback'/>"/>
                        <%-- </c:if>--%>
                    <input type="submit" class="submit"
                           onclick="v.passing=true;this.form.action='${app}/sheet/groupcomplaint/groupcomplaint.do?method=showListsendundo'"
                           value="<bean:message bundle='sheet' key='button.back'/>"/>
                </div>
            </c:otherwise>
        </c:choose>


    </html:form>

</div>
