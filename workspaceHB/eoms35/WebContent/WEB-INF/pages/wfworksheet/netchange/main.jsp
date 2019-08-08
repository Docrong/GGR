<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<%
    String parentCorrelation = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentCorrelation"));
    String parentSheetId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentSheetId"));
    String parentSheetName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentSheetName"));
    String invokeMode = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("invokeMode"));
%>
<script type="text/javascript">
    var roleTree;
    var v;

    function initPage() {
        v = new eoms.form.Validation({form: 'theform'});
        $('btns').show();
        v.preCommitUrl = "${app}/sheet/netchange/netchange.do?method=performPreCommit";
    }


    Ext.onReady(function () {
        //showPage();

        var tabs = new Ext.TabPanel('main');
        tabs.addTab('sheetform', "<bean:message bundle="netchange" key="netchange.header"/>");
        tabs.activate('sheetform');

        var el = Ext.get("idSpecial");
        var mgr = el.getUpdateManager();
        mgr.loadScripts = true;
        if ('${templateId}' != null && '${templateId}' != "") {
            mgr.update("${app}/sheet/netchange/netchange.do?method=showTemplateInputSheetPage&templateId=${templateId}");
        } else {
            mgr.update("${app}/sheet/netchange/netchange.do?method=showNewInputSheetPage&processname=NetChangeMainProcess&parentCorrelation=${parentCorrelation}&parentSheetId=${parentSheetId}&parentSheetName=${parentSheetName}&parentPhaseName=${parentPhaseName}&invokeMode=${invokeMode}");
        }

        mgr.on("update", initPage);
    });

    function changeType1() {
        if ($('${sheetPageName}mainIfRequireProject').value == "1030101") {
            $('${sheetPageName}phaseId').value = "AuditTask";
            $('${sheetPageName}operateType').value = "110";
            $('${sheetPageName}activeTemplateId').value = "ProjectCreateTask";
        } else {
            $('${sheetPageName}phaseId').value = "ProjectCreateTask";
            $('${sheetPageName}operateType').value = "0";
            $('${sheetPageName}activeTemplateId').value = "";
        }
    }

    function changeType2() {

        $('${sheetPageName}phaseId').value = "DraftTask";
        $('${sheetPageName}operateType').value = "22";
        $('${sheetPageName}status').value = "3";

    }

    function saveTemplate(type) {
        var form = document.getElementById("theform");
        var ajaxForm = Ext.getDom(form);
        var templateManage = "${type}";
        if (v.check()) {
            v.passing = false;
            if (confirm("${eoms:a2u('确认保存模板吗？')}")) {
                if (templateManage == "templateManage") {
                    form.action = "${app}/sheet/netchange/netchange.do?method=saveTemplate&templateId=${templateId}&type=${type}";
                    form.submit();
                } else {
                    if (type == "new") {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "netchange.do?method=saveTemplate&type=${type}",
                            success: function () {
                                alert("${eoms:a2u('保存模板成功！')}");
                            }
                        });
                    } else {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "netchange.do?method=saveTemplate&templateId=${templateId}&type=${type}",
                            success: function () {
                                alert("${eoms:a2u('保存模板成功！')}");
                            }
                        });
                    }
                }
            }
        }
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = "${app}/sheet/netchange/netchange.do?method=removeTemplate&templateId=${templateId}&type=${type}";
            thisform.submit();
        }
    }
</script>

<div id="sheetform" class="tabContent">
<html:form action="/netchange.do?method=newPerformAdd" styleId="theform">
    <div ID="idSpecial"></div>
    <p/>
    <!-- buttons -->
    <div class="form-btns" id="btns" style="display:none">

    <logic:notPresent name="templateManage">
        <html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();" styleId="submitone">
            <bean:message bundle="sheet" key="button.send"/>
        </html:submit>

        <html:submit styleClass="btn" property="method.saveDraft" onclick="v.passing=true;javascript:changeType2()"
                     styleId="method.saveDraft">
            <bean:message bundle="sheet" key="button.saveAsDraft"/>
        </html:submit>
        <!-- 如果是工单互调则不显示保存模板和引用模板 -->
        <c:if test="${empty parentSheetId}">
            <html:button styleClass="btn" property="method.save" styleId="method.save"
                         onclick="window.open('./netchange.do?method=getTemplatesByUserId',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
                <bean:message bundle="sheet" key="button.refereTemplate"/>
            </html:button>
            <html:button styleClass="btn" property="method.save" styleId="method.save"
                         onclick="v.passing=true;saveTemplate('new')">
                <bean:message bundle="sheet" key="button.saveTemplate"/>
            </html:button>
            <c:if test="${templateId != null && templateId != ''}">
                <html:button styleClass="btn" property="method.save" styleId="method.save"
                             onclick="v.passing=true;saveTemplate('current')">
                    <bean:message bundle="sheet" key="button.saveCurTemplate"/>
                </html:button>
            </c:if>
        </c:if>
        </div>
    </logic:notPresent>
    <logic:present name="templateManage">
        <c:if test="${templateId != null && templateId != ''}">
            <logic:present name="type">
                <div>
                    <table id="sheet" class="formTable">
                        <tr>
                            <td class="label"><bean:message bundle="sheet" key="input.templateName"/></td>
                            <td><input type="text" name="newSheetTemplateName" value="${sheetMain.sheetTemplateName}"
                                       class="text max"></td>
                        </tr>
                    </table>
                </div>
                <br>
                <html:button styleClass="btn" property="method.save" styleId="method.save"
                             onclick="v.passing=true;saveTemplate('current')">
                    <bean:message bundle="sheet" key="button.saveCurTemplate"/>
                </html:button>
                <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
                    <bean:message bundle="sheet" key="button.delete"/>
                </html:button>
            </logic:present>
            <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
                <bean:message bundle="sheet" key="button.back"/>
            </html:button>
        </c:if>
        </div>
    </logic:present>
</html:form>
</div>

<%@ include file="/common/footer_eoms.jsp" %>
