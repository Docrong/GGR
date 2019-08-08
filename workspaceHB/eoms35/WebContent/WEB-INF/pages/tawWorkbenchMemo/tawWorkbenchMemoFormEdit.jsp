<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>


<title><fmt:message key="tawWorkbenchMemoDetail.title"/></title>
<content tag="heading">
    <fmt:message key="tawWorkbenchMemoDetail.heading"/>
</content>

<%
    String send_flag = (String) request.getAttribute("s");
    String str = request.getParameter("folderPath").toString();
    System.out.println(send_flag);
    if (send_flag == null || send_flag.equals("")) {
        send_flag = "0";
    }
    String memoId = (String) request.getParameter("id");
    System.out.println(memoId);
%>
<script language="javascript">

    function confirmDelete(memoid) {

        if (confirm('${eoms:a2u("是否要删除此标签")}')) {
            document.forms[0].action = "editTawWorkbenchMemo.do?method=delete&id=" + memoid;
            document.forms[0].submit();
            return true;
        } else {
            return false;
        }
    }

    function confirmedit(memoid) {

        document.forms[0].action = "editTawWorkbenchMemo.do?method=save&id=" + memoid;
        document.forms[0].submit();
        return true;

    }

    function changeType(v) {
        id = '<%=memoId%>';

        document.getElementById("view1").style.display = (v == 0) ? "none" : "block";
        document.getElementById("view2").style.display = (v != 0) ? "none" : "block";
        if (id != 'null') {
            document.getElementById("view3").style.display = (v == 0) ? "none" : "block";
        }
    }

    window.onload = function setup() {
        var send = '<%=send_flag%>';
        changeType(send);
    }

    Ext.onReady(function () {
        var userTreeAction = '${app}/xtree.do?method=getContactTree';
        //var	userTreeAction='${app}/xtree.do?method=userFromDept';
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
            treeChkType: 'contact',
            viewer: userViewer,
            saveChkFldId: 'reciever'
        });
    })
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawWorkbenchMemoForm'});
    });
</script>

<html:form action="editTawWorkbenchMemo" method="post"
           styleId="tawWorkbenchMemoForm">
    <ul>

        <input type="hidden" name="folderPath" value=<%=str%>/>
        <html:hidden property="id"/>
        <br>

        <table class="formTable">

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.title"/>
                </td>
                <td width="500" colspan="2">
                    <html:text property="title" styleId="title"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:'${eoms:a2u('请输入便签标题')}'"/>
                </td>
            </tr>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.content"/>
                </td>
                <td width="500" colspan="2">
                    <html:textarea property="content" styleId="content"
                                   styleClass="text medium" rows="5" cols="17"
                                   alt="allowBlank:false,vtext:'${eoms:a2u('请输入便签内容')}'"/>
                </td>
            </tr>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.level"/>
                </td>
                <td width="500" colspan="2">
                    <eoms:dict key="memo-dict" dictId="level" beanId="selectXML"
                               isQuery="false" defaultId="${tawWorkbenchMemoForm.level}"
                               selectId="level" alt="allowBlank:false"/>
                </td>
            </tr>
            <%
                if (memoId != null) {
            %>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.sendflag"/>
                </td>
                <td width="500" colspan="2">
                    <eoms:dict key="memo-dict" dictId="sendflag" beanId="selectXML"
                               defaultId="${tawWorkbenchMemoForm.sendflag}" selectId="sendflag"
                               onchange="changeType(this.value);" alt="allowBlank:false"/>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <table class="formTable" id=view1>

            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.reciever"/>
                </td>
                <td width="500" colspan="5">
                    <input type="hidden" name="recievers"
                           value="${tawWorkbenchMemoForm.reciever}">
                    <%
                        if (memoId != null) {
                    %>
                    <html:hidden property="reciever"
                                 alt="allowBlank:false,vtext:'${eoms:a2u('请输入联系人')}'"/>
                    <%
                    } else {
                    %>
                    <html:hidden property="reciever"/>
                    <%
                        }
                    %>
                    <html:text property="reciever" styleId="title"
                               styleClass="text medium" readonly="true"/>
                    <div id="user-list" class="viewer-list"></div>
                    <input type="button" value="${eoms:a2u('联系人列表')}" id="userTreeBtn"
                           class="btn"/>
                </td>
            </tr>
            <%
                if (memoId != null) {
            %>
            <tr>
                <td width="100" class="label">
                    <eoms:label styleClass="desc" key="tawWorkbenchMemoForm.sendmanner"/>
                </td>

                <td width="500" colspan="2">
                    <eoms:dict key="memo-dict" dictId="sendmanner" beanId="selectXML"
                               defaultId="${tawWorkbenchMemoForm.sendmanner}"
                               selectId="sendmanner" alt="allowBlank:false"/>
                </td>
            </tr>
            <%
                }
            %>
        </table>

        <br>
        <table>
            <tr>
                <td id=view2>

                    <input type="button" name="button" class="button"
                           value="<fmt:message key="method.save" />"
                           onclick="return confirmedit('<%=memoId%>')">
                </td>
                <%
                    if (memoId != null) {
                %>
                <td id=view3>
                    <html:submit styleClass="button" property="method.save">
                        <fmt:message key="tawWorkbenchMemoForm.saveSend"/>
                    </html:submit>
                </td>
                <td>

                    <input type="button" name="button" class="button"
                           value="<fmt:message key="button.delete" />"
                           onclick="return confirmDelete('<%=memoId%>')">
                </td>

                <%
                    }
                %>

            </tr>
        </table>
    </ul>
</html:form>


<%@ include file="/common/footer_eoms.jsp" %>
