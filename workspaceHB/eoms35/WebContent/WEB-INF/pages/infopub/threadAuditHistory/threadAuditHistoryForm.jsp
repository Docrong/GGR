<jsp:directive.page import="com.boco.eoms.base.util.StaticVariable"/>
<jsp:directive.page import="com.boco.eoms.workbench.infopub.util.InfopubConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/common/xtreelibs.jsp" %>
<%@ page import="net.fckeditor.FCKeditor" %>
<%@ page import="com.boco.eoms.workbench.infopub.webapp.form.ThreadAuditHistoryForm" %>
<title><bean:message key="threadAuditHistoryDetail.title"/></title>
<content tag="heading">
    <bean:message key="threadAuditHistoryDetail.heading"/>
    >
    <eoms:id2nameDB id="${thread.id}" beanId="threadDao"/>
</content>

<script type="text/javascript">

    var auditTree;

    function FCKeditor_OnComplete(editorInstance) {
        window.status = editorInstance.Description;
    }

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'threadAuditHistoryForm'});

        deptViewer = new Ext.JsonView("view",
            '<div class="viewlistitem-{nodeType}">{name}</div>',
            {
                emptyText: '<div><bean:message key='threadForm.tips.nochoose'/></div>'
            }
        );
        var s = '${jsonOrgs}';
        deptViewer.jsonData = eoms.JSONDecode(s);
        deptViewer.refresh();
        var treeAction = '${app}/xtree.do?method=getDeptSubRoleUserTree';
        deptTree = new xbox({
            btnId: 'clkOrg',
            dlgId: 'hello-dlg',
            treeDataUrl: treeAction,
            treeRootId: '-1',
            treeRootText: '<bean:message key='threadForm.tree.audit'/>',
            treeChkMode: 'single',
            treeChkType: 'user',
            showChkFldId: 'showOrg',
            saveChkFldId: 'orgId',
            viewer: deptViewer,
            returnJSON: true
        });
    });
</script>

<fmt:bundle basename="config/applicationResource-workbench-infopub">

    <display:table name="threadAuditHistoryList" cellspacing="0" cellpadding="0" id="threadAuditHistoryList"
                   pagesize="25" class="table threadAuditHistoryList" export="true"
                   requestURI="${app}/workbench/infopub/threadAuditHistory.do?method=search"
                   sort="external" partialList="true" size="resultSize"
                   decorator="com.boco.eoms.workbench.infopub.displaytag.support.ThreadAuditHistoryListDisplayTagDecorator">


        <display:column property="orgId" sortable="true" headerClass="sortable"
                        titleKey="threadAuditHistoryForm.orgId"/>

        <display:column property="submitTime" sortable="true" headerClass="sortable"
                        titleKey="threadAuditHistoryForm.submitTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="opinion" sortable="true" headerClass="sortable"
                        titleKey="threadAuditHistoryForm.opinion"/>

        <display:column property="status" sortable="true" headerClass="sortable"
                        titleKey="threadAuditHistoryForm.status"/>

        <display:column property="auditTime" sortable="true" headerClass="sortable"
                        titleKey="threadAuditHistoryForm.auditTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="isCurrent" sortable="true" headerClass="sortable"
                        titleKey="threadAuditHistoryForm.isCurrent"/>

        <display:column property="note" sortable="true" headerClass="sortable" titleKey="threadAuditHistoryForm.note"/>

        <display:setProperty name="paging.banner.item_name" value="threadAuditHistory"/>
        <display:setProperty name="paging.banner.items_name" value="threadAuditHistorys"/>
    </display:table>

</fmt:bundle>

<logic:notEqual value="<%=InfopubConstants.AUDIT_PASS%>" name="thread" property="status">
    <html:form action="/threadAuditHistory.do" method="post" styleId="threadAuditHistoryForm">
        <ul>
            <html:hidden property="threadId"/>
            <input type="hidden" id="orgId" name="orgId"/>

            <li>
                <eoms:label styleClass="desc" key="threadAuditHistoryForm.note"/>
                <html:errors property="note"/>
                <html:textarea property="note" styleId="note" styleClass="textarea medium"/>
            </li>

            <li>
                <eoms:label styleClass="desc" key="forumsForm.canread"/>
                <html:errors property="threadTypeId"/>
                <input type="text" readonly id="showOrg" name="showOrg"/>
                <input type="button" id="clkOrg" name="clkOrg" value="<bean:message key='threadForm.button.audit'/>"/>
                <div id="view"></div>
            </li>

            <li class="buttonBar bottom">
                <html:submit styleClass="button" property="method.save">
                    <bean:message key='threadForm.button.submitAudit'/>
                </html:submit>
            </li>
        </ul>
    </html:form>
</logic:notEqual>

<%@ include file="/common/footer_eoms.jsp" %>
