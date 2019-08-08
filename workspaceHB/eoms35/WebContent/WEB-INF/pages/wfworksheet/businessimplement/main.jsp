<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<%
    String parentCorrelation = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentCorrelation"));
    System.out.println("@@parentCorrelation=" + parentCorrelation);
    String parentSheetId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentSheetId"));
    System.out.println("@@parentSheetId=" + parentSheetId);
    String parentSheetName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentSheetName"));
    System.out.println("@@parentSheetName@@=" + parentSheetName);
    String invokeMode = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("invokeMode"));
    String parentPhaseName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentPhaseName"));
    String parentPhaseName2 = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("parentPhaseName"));
    String orderSheetId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("orderSheetId"));
    System.out.println("@@orderSheetId called=" + orderSheetId);
    String title = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("title"));
    System.out.println("@@maintitle=" + title);
    String isShowLanguage = StaticMethod.nullObject2String(request.getParameter("isShowLanguage"));
    String isShowSms = StaticMethod.nullObject2String(request.getParameter("isShowSms"));
    String startType = StaticMethod.nullObject2String(request.getParameter("startType"));
    String specialtyType = StaticMethod.nullObject2String(request.getParameter("specialtyType"));
    //mainBelongPronice=isCalledByLangugage
    String isCalledByLangugage = StaticMethod.nullObject2String(request.getParameter("isCalledByLangugage"));
    System.out.println("@isCalledByLangugage" + isCalledByLangugage);
    System.out.println("@main.jspisShowLanguage" + isShowLanguage);
    System.out.println("@main.isShowSms" + isShowSms);
    System.out.println("@main.startType" + startType);
    System.out.println("@main.specialtyType" + specialtyType);
%>

<script type="text/javascript">
    var roleTree;
    var v;

    function initPage() {
        v = new eoms.form.Validation({form: 'theform'});
        $('btns').show();
        //v.preCommitUrl = "${app}/sheet/businessimplement/businessimplement.do?method=performPreCommit";
    }


    Ext.onReady(function () {
        //showPage();

        var tabs = new Ext.TabPanel('main');
        tabs.addTab('sheetform', "<bean:message bundle="businessimplement" key="businessimplement.header"/>");
        tabs.activate('sheetform');

        var el = Ext.get("idSpecial");
        var mgr = el.getUpdateManager();
        mgr.loadScripts = true;
        if ('${templateId}' != null && '${templateId}' != "") {
            mgr.update("${app}/sheet/businessimplement/businessimplement.do?method=showTemplateInputSheetPage&templateId=${templateId}&parentCorrelation=${parentCorrelation}&parentSheetId=${parentSheetId}&parentSheetName=${parentSheetName}&parentPhaseName=${parentPhaseName}&invokeMode=${invokeMode}&orderSheetId=<%=orderSheetId%>");
        } else {

            var i = "<%=orderSheetId%>";


            mgr.update("${app}/sheet/businessimplement/businessimplement.do?method=showNewInputSheetPage&processname=BusinessImplementProcess&parentCorrelation=${parentCorrelation}&parentSheetId=${parentSheetId}&parentSheetName=${parentSheetName}&parentPhaseName=${parentPhaseName}&title=<%=title%>&orderSheetId=" + i + "&invokeMode=${invokeMode}&isShowLanguage=<%=isShowLanguage%>&isShowSms=<%=isShowSms%>&startType=<%=startType%>&mainBelongPronice=<%=isCalledByLangugage%>&title=<%=title%>&specialtyType=<%=specialtyType%>");

        }

        mgr.on("update", initPage);
    });

    function changeType1() {

        <%if(isShowLanguage.equals("yes")||startType != ""){  System.out.println("is ProjectDealTask");%>

        $('${sheetPageName}phaseId').value = 'ProjectDealTask';
        $('${sheetPageName}operateType').value = "91";
        <%}else{System.out.println("is ImplementDealTask");%>
        $('${sheetPageName}phaseId').value = 'ImplementDealTask';
        $('${sheetPageName}operateType').value = "0";
        <%}%>


    }

    function changeType2() {
        // if(!v.check()){return;}
        $('${sheetPageName}phaseId').value = 'DraftTask';
        $('${sheetPageName}operateType').value = "22";


    }

    function saveTemplate(type) {
        var form = document.getElementById("theform");
        var ajaxForm = Ext.getDom(form);
        var templateManage = "${type}";
        if (v.check()) {
            v.passing = false;
            if (confirm("确认保存模板吗？")) {
                if (templateManage == "templateManage") {
                    form.action = "${app}/sheet/businessimplement/businessimplement.do?method=saveTemplate&templateId=${templateId}&type=${type}";
                    form.submit();
                } else {
                    if (type == "new") {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "businessimplement.do?method=saveTemplate&type=${type}",
                            success: function () {
                                alert("保存模板成功！");
                            }
                        });
                    } else {
                        Ext.Ajax.request({
                            form: ajaxForm,
                            method: "post",
                            url: "businessimplement.do?method=saveTemplate&templateId=${templateId}&type=${type}",
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
            thisform.action = "${app}/sheet/businessimplement/businessimplement.do?method=removeTemplate&templateId=${templateId}&type=${type}";
            thisform.submit();
        }
    }
</script>

<div id="sheetform" class="tabContent">
<html:form action="/businessimplement.do?method=newPerformAdd" styleId="theform">
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
        <!-- 如果是工单互调则不显示保存模板和引用模板 -->
        <c:if test="${empty parentSheetId}">
            <html:button styleClass="btn" property="method.save" styleId="method.save"
                         onclick="window.open('./businessimplement.do?method=getTemplatesByUserId',null,'left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes')">
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
