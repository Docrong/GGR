<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<title><fmt:message key="tawWorkbenchMemoDetail.title"/></title>
<content tag="heading">
    <fmt:message key="tawWorkbenchMemoDetail.heading"/>
</content>
<script type="text/javascript">
    Ext.onReady(function () {
        var userTreeAction = '${app}/xtree.do?method=userFromDept';
        var userTreeAction2 = '${app}/xtree.do?method=getContactTree';
        userViewer = new Ext.JsonView("user-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div>${eoms:a2u("")} </div>'

            }
        );
        userViewer.refresh();
        userTree = new xbox({
            btnId: 'userTreeBtn',
            dlgId: 'dlg-user',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("所有联系人")}',
            treeChkMode: '',
            treeChkType: 'user',
            viewer: userViewer,
            saveChkFldId: 'reciever'
        });
        userTree2 = new xbox({
            btnId: 'userTreeBtn2',
            dlgId: 'dlg-user',
            treeDataUrl: userTreeAction2,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("所有联系人")}',
            treeChkMode: '',
            treeChkType: 'contact',
            viewer: userViewer,
            saveChkFldId: 'reciever'
        });
    })
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawWorkbenchMemoForm'});
    });
</script>
<html:form action="/tawWorkbenchMemo.do?method=saveSend" method="post"
           styleId="tawWorkbenchMemoForm">
    <ul>
        <%
            String str = request.getParameter("folderPath").toString();
        %>
        <input type="hidden" name="folderPath" value=<%=str%>/>
        <html:hidden property="id"/>

        <table class="formTable">

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.title"/>
                </td>
                <td width="500" colspan="5">
                    <html:text property="title" styleId="title"
                               styleClass="text medium" alt="allowBlank:false,vtext:'${eoms:a2u('请输入便签标题')}'"/>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.content"/>
                </td>
                <td width="500" colspan="5">
                    <html:textarea property="content" styleId="content"
                                   styleClass="text medium" rows="5" cols="17"
                                   alt="allowBlank:false,vtext:'${eoms:a2u('请输入便签内容')}'"/>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.level"/>
                </td>
                <td width="500" colspan="5">
                    <eoms:dict key="memo-dict" dictId="level" beanId="selectXML"
                               isQuery="false" defaultId="${tawWorkbenchMemoForm.level}"
                               selectId="level" alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.reciever"/>
                </td>
                <td width="500" colspan="5">
                    <html:hidden property="reciever" alt="allowBlank:false,vtext:'${eoms:a2u('请选择联系人')}'"/>
                    <html:text property="reciever" styleId="title"
                               styleClass="text medium" readonly="true"/>
                    <div id="user-list" class="viewer-list"></div>
                    <%if (str.length() < 2) {%>
                    <DIV id="button">
                        <input type="button" value="${eoms:a2u('联系人列表')}" id="userTreeBtn" class="btn"/>
                    </DIV>
                    <DIV id="button2" style="visibility:hidden;">
                        <input type="button" value="${eoms:a2u('联系人列表')}" id="userTreeBtn2" class="btn"/>
                    </DIV>
                    <%} else {%>
                    <DIV id="button2">
                        <input type="button" value="${eoms:a2u('联系人列表')}" id="userTreeBtn2" class="btn"/>
                    </DIV>
                    <DIV id="button" style="visibility:hidden;">
                        <input type="button" value="${eoms:a2u('联系人列表')}" id="userTreeBtn" class="btn"/>
                    </DIV>
                    <%}%>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.sendmanner"/>
                </td>
                <td width="500" colspan="5">
                    <eoms:dict key="memo-dict" dictId="sendmanner" beanId="selectXML"
                               selectId="sendmanner" alt="allowBlank:false"/>
                </td>
            </tr>
        </table>
        <br>
        <table>
            <tr>
                <td>
                    <input type="submit" value="${eoms:a2u(" 新增派发")}" class="submit"/>

                </td>
            <tr>
        </table>
    </ul>
</html:form>


<%@ include file="/common/footer_eoms.jsp" %>
