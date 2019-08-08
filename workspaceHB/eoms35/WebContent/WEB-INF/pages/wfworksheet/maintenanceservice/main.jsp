<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    var roleTree;
    var v;

    function initPage() {
        v = new eoms.form.Validation({form: 'theform'});
        $('btns').show();
        v.preCommitUrl = "${app}/sheet/maintenanceservice/maintenanceservice.do?method=performPreCommit";
    }


    Ext.onReady(function () {
        //showPage();

        var tabs = new Ext.TabPanel('main');
        tabs.addTab('sheetform', "<bean:message bundle="maintenanceservice" key="maintenanceservice.header"/>");
        tabs.activate('sheetform');

        var el = Ext.get("idSpecial");
        var mgr = el.getUpdateManager();
        mgr.loadScripts = true;
        if ('${templateId}' != null && '${templateId}' != "") {
            mgr.update("${app}/sheet/maintenanceservice/maintenanceservice.do?method=showTemplateInputSheetPage&templateId=${templateId}");
        } else {
            mgr.update("${app}/sheet/maintenanceservice/maintenanceservice.do?method=showNewInputSheetPage&processname=MaintenanceService&auto=${auto}");
        }

        mgr.on("update", initPage);
    });

    function changeType1() {
        var auto = '${auto}';
        if (auto == 'true') {
            $('phaseId').value = "Confirmation";
            $('operateType').value = "0";
        } else {
            $('phaseId').value = "Examine";
            $('operateType').value = "107";
        }


    }

    function changeType2() {
        $('phaseId').value = 'DraftTask';
        $('operateType').value = "22";


    }

    function saveTemplate(type) {
        var form = document.getElementById("theform");
        var ajaxForm = Ext.getDom(form);
        var templateManage = "${type}";
        if (v.check()) {
            v.passing = false;
            if (confirm("确认保存模板吗？")) {
                if (templateManage == "templateManage") {
                    form.action = "${app}/sheet/maintenanceservice/maintenanceservice.do?method=saveTemplate&templateId=${templateId}&type=${type}";
                    form.submit();
                } else {
                    if (type == "new") {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "maintenanceservice.do?method=saveTemplate&type=${type}",
                            success: function () {
                                alert("保存模板成功！");
                            }
                        });
                    } else {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "maintenanceservice.do?method=saveTemplate&templateId=${templateId}&type=${type}",
                            success: function () {
                                alert("保存模板成功！");
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
            thisform.action = "${app}/sheet/maintenanceservice/maintenanceservice.do?method=removeTemplate&templateId=${templateId}&type=${type}";
            thisform.submit();
        }
    }
</script>

<div id="sheetform" class="tabContent">
<html:form action="/maintenanceservice.do?method=newPerformAdd" styleId="theform">
    <div ID="idSpecial"></div>
    <p/>
    <!-- buttons -->
    <div class="form-btns" id="btns" style="display:none">

    <logic:notPresent name="templateManage">
        <html:submit styleClass="btn" property="method.save" onclick="javascript:changeType1();" styleId="method.save">
            <bean:message bundle="sheet" key="button.send"/>
        </html:submit>

        <html:submit styleClass="btn" property="method.saveDraft" onclick="v.passing=true;javascript:changeType2()"
                     styleId="method.saveDraft">
            <bean:message bundle="sheet" key="button.saveAsDraft"/>
        </html:submit>

        <html:button styleClass="btn" property="method.save" styleId="method.save"
                     onclick="window.open('./maintenanceservice.do?method=getTemplatesByUserId',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
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