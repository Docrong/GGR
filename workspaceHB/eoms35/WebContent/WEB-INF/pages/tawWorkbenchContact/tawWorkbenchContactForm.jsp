<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<title><fmt:message key="tawWorkbenchContactDetail.title"/>
</title>
<script type="text/javascript"
        src="${app}/scripts/layout/AppSimpleTree.js"></script>

<script type="text/javascript">
    var userTreeAction = '${app}/xtree.do?method=dept';
    var treeAction = '${app}/xtree.do?method=userByDeptForTaskplan';

    Ext.onReady(function () {

        dispatcherTree = new xbox({
            btnId: 'dispatcherTreeBtn',
            dlgId: 'dlg-user',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("部门列表")}',
            treeChkMode: 'single',
            treeChkType: 'dept',
            showChkFldId: 'deptName',
            saveChkFldId: 'deptId'
        });
    })
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawWorkbenchContactForm'});
    });

</script>

<html:form action="saveTawWorkbenchContact?method=xsave" method="post"
           styleId="tawWorkbenchContactForm">
    <ul>
        <li>
            <html:hidden property="oldContactName" styleId="oldContactName"
                         styleClass="text medium"/>
            <eoms:label styleClass="desc"
                        key="tawWorkbenchContactForm.contactName"/>
            <html:errors property="contactName"/>

            <html:text property="contactName" styleId="contactName"
                       styleClass="text medium"
                       alt="allowBlank:false,vtext:'${eoms:a2u('请输入用户名称')}'"/>

        </li>


        <li>
                <eoms:label styleClass="desc" key="tawWorkbenchContactForm.deptName"/>
                <html:errors property="deptId"/>
                <html:hidden property="deptId" styleId="deptId"
                             styleClass="text medium"/>
                <html:text property="deptName" styleId="deptName"
                           styleClass="text medium" readonly="true"/>

            <input type="button" value="${eoms:a2u('部门列表')}"
                   id="dispatcherTreeBtn" class="btn"/>
        <li>
        <li>
            <eoms:label styleClass="desc" key="tawWorkbenchContactForm.position"/>
            <html:errors property="position"/>

            <html:text property="position" styleId="position"
                       styleClass="text medium" alt="maxLength:50,vtext:'${eoms:a2u('输入的地址位数过长')}'"/>
        </li>

        <li>

            <eoms:label styleClass="desc" key="tawWorkbenchContactForm.tele"/>
            <html:errors property="tele"/>

            <html:text property="tele" styleId="tele" styleClass="text medium"
                       alt="maxLength:11,vtext:'${eoms:a2u('电话号码应为11为数字类型，请正确输入')}',vtype:'number'"/>
        </li>


        <li>

            <eoms:label styleClass="desc" key="tawWorkbenchContactForm.address"/>
            <html:errors property="address"/>

            <html:text property="address" styleId="address"
                       styleClass="text medium" alt="maxLength:50,vtext:'${eoms:a2u('输入的地址位数过长')}'"/>
        </li>
        <li>

            <eoms:label styleClass="desc" key="tawWorkbenchContactForm.email"/>
            <html:errors property="email"/>

            <html:text property="email" styleId="email" styleClass="text medium"
                       alt="vtype:'email'"/>
        </li>

        <html:hidden property="id"/>
        <html:hidden property="groupId"/>


        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save"
                         onclick="bCancel=false">
                <fmt:message key="button.save"/>
            </html:submit>

        </li>
    </ul>

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
