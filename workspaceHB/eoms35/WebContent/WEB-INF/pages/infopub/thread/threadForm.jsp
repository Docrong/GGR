<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="net.fckeditor.FCKeditor" %>
<%@ page import="com.boco.eoms.workbench.infopub.webapp.form.ThreadForm" %>
<title><bean:message key="threadDetail.title"/></title>
<content tag="heading">
    <bean:message key="threadDetail.heading"/>
    <eoms:id2nameDB id="${threadForm.forumsId }" beanId="forumsDao"/>
</content>

<script type="text/javascript">

    var deptTree, auditTree;

    function FCKeditor_OnComplete(editorInstance) {
        window.status = editorInstance.Description;
    }

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'threadForm'});

        deptViewer = new Ext.JsonView("view",
            '<div class="viewlistitem-{nodeType}">{name}</div>',
            {
                emptyText: '<div><bean:message key='threadForm.tips.nochoose'/></div>'
            }
        );
        var s = '${jsonOrgs}';
        deptViewer.jsonData = eoms.JSONDecode(s);
        deptViewer.refresh();
        var treeAction = '${app}/xtree.do?method=userFromDept';
        deptTree = new xbox({
            btnId: 'clkOrg',
            dlgId: 'hello-dlg',
            treeDataUrl: treeAction,
            treeRootId: '-1',
            treeRootText: '<bean:message key='threadForm.tree.org'/>',
            treeChkMode: '',
            treeChkType: 'dept,user',
            showChkFldId: 'showOrg',
            saveChkFldId: 'org',
            viewer: deptViewer,
            returnJSON: true
        });


        auditUserViewer = new Ext.JsonView("auditView",
            '<div class="viewlistitem-{nodeType}">{name}</div>',
            {
                emptyText: '<div><bean:message key='threadForm.tips.nochoose'/></div>'
            }
        );
        var auditUser = '${jsonAudit}';
        auditUserViewer.jsonData = eoms.JSONDecode(auditUser);
        auditUserViewer.refresh();
        var treeAction = '${app}/xtree.do?method=userFromDept';
        auditTree = new xbox({
            btnId: 'clkAudit',
            dlgId: 'hello-audit',
            treeDataUrl: treeAction,
            treeRootId: '-1',
            treeRootText: '<bean:message key='threadForm.tree.audit'/>',
            treeChkMode: 'single',
            treeChkType: 'user',
            showChkFldId: 'showAuditUser',
            saveChkFldId: 'auditUser',
            viewer: auditUserViewer,
            returnJSON: true
        });
    });


    function changeAudit(checkbox) {
        var el = Ext.get('isAsend');
        if (checkbox.checked) {
            el.show();

        } else {
            el.hide();
        }
        document.getElementById("clkAudit").disabled = !checkbox.checked;
        if (!document.getElementById("isAudit").checked) {
            document.getElementById("showAuditUser").value = "";
            document.forms[0].auditUser.value = "";
            auditTree.reset();
            auditUserViewer = new Ext.JsonView("auditView",
                '<div class="viewlistitem-{nodeType}">{name}</div>',
                {
                    emptyText: '<div><bean:message key='threadForm.tips.nochoose'/></div>'
                }
            );
        }
    }

    function onSave(flag) {
        if (flag == 0) {
            if (document.forms[0].title.value == "") {
                alert('${eoms:a2u("请填写标题!")}');
                return false;
            }
            if (document.forms[0].threadTypeId.value == "") {
                alert('${eoms:a2u("请选择紧急程度!")}');
                return false;
            }
            if (document.forms[0].reply.value == "") {
                alert('${eoms:a2u("请选择是否需要回复!")}');
                return false;
            }
            if (document.forms[0].validity.value == "") {
                alert('${eoms:a2u("请填写有效期!")}');
                return false;
            } else if (!IsInteger(document.forms[0].validity.value, '+')) {
                alert('${eoms:a2u("有效期只能填写正整型数字!")}');
                return false;
            }
            document.forms[0].isSubmitAudit.value = "0";
            document.forms[0].submit();
            return true;
        } else if (flag == 1) {
            if (document.forms[0].title.value == "") {
                alert('${eoms:a2u("请填写标题!")}');
                return false;
            }
            if (document.forms[0].threadTypeId.value == "") {
                alert('${eoms:a2u("请选择紧急程度!")}');
                return false;
            }
            if (document.forms[0].reply.value == "") {
                alert('${eoms:a2u("请选择是否需要回复!")}');
                return false;
            }
            if (document.forms[0].validity.value == "") {
                alert('${eoms:a2u("请填写有效期!")}');
                return false;
            } else if (!IsInteger(document.forms[0].validity.value, '+')) {
                alert('${eoms:a2u("有效期只能填写正整型数字!")}');
                return false;
            }
            if (document.getElementById("isAudit").checked) {
                if (!confirm('${eoms:a2u("已经选择了审核人，确定要发布而不是提交审核？")}')) {
                    return false;
                }
            }
            document.forms[0].isSubmitAudit.value = "1";
            document.forms[0].submit();
            return true;
        } else if (flag == 2) {
            if (document.forms[0].title.value == "") {
                alert('${eoms:a2u("请填写标题!")}');
                return false;
            }
            if (document.forms[0].threadTypeId.value == "") {
                alert('${eoms:a2u("请选择紧急程度!")}');
                return false;
            }
            if (document.forms[0].reply.value == "") {
                alert('${eoms:a2u("请选择是否需要回复!")}');
                return false;
            }
            if (document.forms[0].validity.value == "") {
                alert('${eoms:a2u("请填写有效期!")}');
                return false;
            } else if (!IsInteger(document.forms[0].validity.value, '+')) {
                alert('${eoms:a2u("有效期只能填写正整型数字!")}');
                return false;
            }
            if (document.forms[0].auditUser.value.length > 2) {
                document.forms[0].isSubmitAudit.value = "2";
                document.forms[0].submit();
                return true;
            } else {
                alert('${eoms:a2u("请选择审核人!")}');
                return false;
            }
        }
    }

    function IsInteger(string, sign) {
        var integer;
        if ((sign != null) && (sign != '-') && (sign != '+')) {
            alert('IsInter(string,sign)的参数出错： sign为null或"-"或"+"');
            return false;
        }
        integer = parseInt(string);
        if (isNaN(integer)) {
            return false;
        } else if (integer.toString().length == string.length) {
            if ((sign == null) || (sign == '-' && integer < 0) || (sign == '+' && integer > 0)) {
                return true;
            } else
                return false;
        } else
            return false;
    }

</script>


<%
    FCKeditor fckEditor = new FCKeditor(request, "content");
    fckEditor.setBasePath(request.getContextPath() + "/fckeditor");

%>


<html:form action="/thread.do?method=save" method="post" styleId="threadForm">

    <ul>

        <html:hidden property="id"/>
        <html:hidden property="forumsId"/>
        <html:hidden property="createrId"/>
        <html:hidden property="createrName"/>
        <html:hidden property="createTime"/>
        <html:hidden property="threadCount"/>
        <html:hidden property="isDel"/>
        <html:hidden property="status"/>
        <input type="hidden" id="org" name="org"/>
        <input type="hidden" id="auditUser" name="auditUser"/>
        <input type="hidden" id="isSubmitAudit" name="isSubmitAudit"/>


        <li>
            <eoms:label styleClass="desc" key="threadForm.title"/>
            <html:errors property="title"/>
            <html:text property="title" styleId="title" styleClass="text medium" alt="allowBlank:false"
                       style="width:300"/>
        </li>

        <li>
            <eoms:label styleClass="desc" key="threadForm.threadTypeId"/>
            <html:errors property="threadTypeId"/>
            <eoms:dict key="dict-workbench-infopub" dictId="threadType" selectId="threadTypeId" beanId="selectXML"
                       alt="allowBlank:false" defaultId="${threadForm.threadTypeId }"/>
        </li>
        <li>
            <eoms:label styleClass="desc" key="threadForm.Reply"/>
            <html:errors property="reply"/>
            <eoms:dict key="dict-workbench-infopub" dictId="reply" selectId="reply" beanId="selectXML"
                       alt="allowBlank:false" defaultId="${threadForm.reply }"/>
        </li>
        <li>
            <eoms:label styleClass="desc" key="threadForm.Validity"/>
            <html:errors property="threadTypeId"/>
            <html:text property="validity" styleId="validity" styleClass="text medium" alt="allowBlank:false"/>${eoms:a2u("天")}
        </li>
        <li>
            <eoms:label styleClass="desc" key="forumsForm.canread"/>
            <html:errors property="threadTypeId"/>
            <input type="hidden" readonly id="showOrg" name="showOrg"/>
            <input type="button" id="clkOrg" name="clkOrg" value="<bean:message key='threadForm.button.canread'/>"/>
            <html:checkbox property="isIncludeSubDept" styleId="isIncludeSubDept"
                           value="1">${eoms:a2u("包括子部门")}</html:checkbox>
            <html:checkbox property="isSend" styleId="isSend" value="1">${eoms:a2u("发送短信到所选范围")}</html:checkbox>
            <div id="view" class="viewer-list"></div>
        </li>

        <li>
            <eoms:label styleClass="desc" key="forumsForm.audit"/>
            <INPUT type="checkbox" id="isAudit" name="isAudit" onclick="javascript:changeAudit(this)"/>
            <html:errors property="threadTypeId"/>
            <input type="hidden" readonly id="showAuditUser" name="showAuditUser"/>
            <input type="button" id="clkAudit" name="clkAudit" value="<bean:message key='threadForm.button.audit'/>"
                   disabled="true"/>
            <span id="isAsend" style="{display:none}">	<html:checkbox property="isAuditSend" styleId="isAuditSend"
                                                                         value="1">${eoms:a2u("发送短信到审核人")}</html:checkbox></span>
            <div id="auditView" class="viewer-list"></div>
        </li>

        <li>

            <eoms:label styleClass="desc" key="threadForm.content"/>
            <html:errors property="content"/>
            <%
                ThreadForm threadForm = (ThreadForm) request
                        .getAttribute("threadForm");
                if (threadForm != null && threadForm.getContent() != null) {
                    fckEditor.setValue(threadForm.getContent());
                } else {
                    fckEditor.setValue("");
                }
                out.print(fckEditor);

            %>

        </li>

        <li>
                ${eoms:a2u("附件管理")}<p/>
            <eoms:attachment idList="" idField="accessories" appCode="9" scope="request" name="threadForm"
                             property="accessories"/>
        </li>
        <li class="buttonBar bottom">
            <input type="button" value="<fmt:message key="button.save" />" onClick="javascript:onSave(0);"
                   class="button">
            <input type="button" value="<bean:message key='threadForm.button.published'/>"
                   onClick="javascript:onSave(1);" class="button">
            <input type="button" value="<bean:message key='threadForm.button.submitAudit'/>"
                   onClick="javascript:onSave(2);" class="button">
            <input type="button" value="<fmt:message key="button.back" />"
                   onclick="javascript:window.location='<html:rewrite
                           page='/thread.do?method=list&forumsId=${threadForm.forumsId}'/>'" class="button"/>
            <logic:notEmpty name="threadForm" property="id">
                <input type="button" value="<fmt:message key="button.delete" />"
                       onclick="javascript:window.location='<html:rewrite
                               page='/thread.do?method=delete&id=${threadForm.id}&forumsId=${threadForm.forumsId}'/>'"
                       class="button"/>
            </logic:notEmpty>

        </li>
    </ul>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
