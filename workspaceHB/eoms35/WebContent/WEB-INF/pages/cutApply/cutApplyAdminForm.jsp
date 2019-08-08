<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<html:form action="/cutApplys.do?method=saveAdmin"
           styleId="cutApplyAdminForm" method="post">
    <script type="text/javascript">
        Ext.onReady(function () {
            v = new eoms.form.Validation({form: 'cutApplyAdminForm'});
        });

        var reportUserTree;

        function FCKeditor_OnComplete(editorInstance) {
            window.status = editorInstance.Description;
        }

        Ext.onReady(function () {
            reportUserViewer = new Ext.JsonView("reportView",
                '<div class="viewlistitem-{nodeType}">{name}</div>',
                {
                    emptyText: '<div>没有选择项目</div>'
                }
            );
            var r = '[]';
            reportUserViewer.jsonData = eoms.JSONDecode(r);
            reportUserViewer.refresh();
            var treeAction = '${app}/xtree.do?method=userFromDept';
            reportUserTree = new xbox({
                btnId: 'reportUser',
                dlgId: 'hello-dlg',
                treeDataUrl: treeAction,
                treeRootId: '-1',
                treeRootText: '人员选择树',
                treeChkMode: 'single',
                treeChkType: 'user',
                saveChkFldId: 'userId',
                viewer: reportUserViewer
            });
        });
    </script>
    <fmt:bundle basename="config/applicationResource-cutapply">
        <table class="formTable">

            <td colspan="2" align="center">
                <h2>
                    干线割接管理-管理员角色新增
                </h2>
            </td>
            <tr>
                <td class="label" align="right">
                    割接申请人
                </td>
                <td>
                    <input type="hidden" id="userId" name="userId"/>
                    <div id="reportView" class="viewer-box"></div>
                    <input type="button" id="reportUser" name="reportUser" value="选择人员"
                           class="button"/>
                </td>
            </tr>
        </table>
    </fmt:bundle>
    <table>
        <tr>
            <td>
                <input type="submit" class="btn"
                       value="<fmt:message key="button.save"/>"/>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${cutApplyAdminForm.id}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
