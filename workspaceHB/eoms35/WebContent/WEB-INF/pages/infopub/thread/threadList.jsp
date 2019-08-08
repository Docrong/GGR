<jsp:directive.page import="com.boco.eoms.workbench.infopub.util.InfopubConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<priv:region url="<%=InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_THREAD_MGR%>">
    <script type="text/javascript">
        Ext.onReady(function () {
            var treeAction = '${app}/workbench/infopub/forums.do?method=getNodes4query';
            var config4 = {
                btnId: 'forumsName',
                dlgId: 'dlg4',
                treeDataUrl: treeAction,
                treeRootId: '-1',
                treeRootText: '<bean:message key='forumsTree.title.choose'/>',
                treeChkMode: 'single',
                treeChkType: 'forums',
                showChkFldId: 'forumsName',
                saveChkFldId: 'toForumsId'
            }
            var t4 = new xbox(config4);
        });
    </script>
</priv:region>


<script type="text/javascript">

    var checkflag = "false";

    function choose() {
        var objs = document.getElementsByName("ids");
        if (checkflag == "false") {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    objs[i].checked = true;
                checkflag = "true";
            }
        } else if (checkflag == "true") {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    objs[i].checked = false;
                checkflag = "false"
            }
        }
    }

    function sort() {
        var toForumsId = document.getElementById("toForumsId");
        if ("" == toForumsId.value) {
            alert('<bean:message key='thread.tip.sort'/>');
            return false;
        }
        var flag = false;
        var objs = document.getElementsByName("ids");
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].type.toLowerCase() == "checkbox")
                if (objs[i].checked) {
                    flag = true;
                }
        }
        if (flag) {
            return true;
        } else {
            alert("<bean:message key='threadForm.tips.nochoose'/>");
            return false;
        }
    }

    function ConfirmDel() {
        var flag = false;
        if (confirm("<bean:message key='common.tips.delete'/>")) {
            var objs = document.getElementsByName("ids");
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].type.toLowerCase() == "checkbox")
                    if (objs[i].checked) {
                        flag = true;
                    }
            }
            if (flag) {
                return true;
            } else {
                alert("<bean:message key='threadForm.tips.nochoose'/>");
                return false;
            }

        } else {
            return false;
        }
    }

</script>


<logic:notEmpty name="threadForm" property="forumsId">
    <bean:message key="threadForm.forumsId"/> >
    <eoms:id2nameDB id="${threadForm.forumsId }" beanId="forumsDao"/>
</logic:notEmpty>


<html:form action="/thread.do" method="post" styleId="threadForm">

    <html:hidden property="forumsId"/>


    <display:table name="threadList" cellspacing="0" cellpadding="0" id="threadList" pagesize="${pageSize }"
                   class="table threadList" export="true"
                   requestURI="${app}/workbench/infopub/thread.do?method=list&forumsId=${forumsId }" sort="list"
                   partialList="true" size="resultSize"
                   decorator="com.boco.eoms.workbench.infopub.displaytag.support.ThreadListDisplaytagDecorator">

        <display:setProperty name="export.rtf" value="false"></display:setProperty>

        <logic:notEmpty name="threadMgrPriv">
            <display:column property="id" title="<input type='checkbox' onclick='javascript:choose();'>"/>
            <display:setProperty name="css.table" value="0,"/>
        </logic:notEmpty>

        <display:column property="title" sortable="true" headerClass="sortable" titleKey="infopub.threadForm.title"
                        paramId="id" paramProperty="id" href="${app}/workbench/infopub/thread.do?method=detail"/>

        <display:column sortable="true" headerClass="sortable" titleKey="infopub.threadForm.status"
                        href="${app}/workbench/infopub/threadAuditHistory.do?method=device" paramId="threadId"
                        paramProperty="id">
            <eoms:dict key="dict-workbench-infopub" dictId="auditStatus" itemId="${threadList.status}"
                       beanId="id2nameXML"/>
        </display:column>
        <display:column property="threadCount" sortable="true" headerClass="sortable"
                        titleKey="infopub.threadForm.num"/>
        <display:column property="createrName" sortable="true" headerClass="sortable"
                        titleKey="infopub.threadForm.createrId"/>

        <display:column property="createTime" sortable="true" headerClass="sortable"
                        titleKey="infopub.threadForm.createTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="editTime" sortable="true" headerClass="sortable"
                        titleKey="infopub.threadForm.editTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column sortable="true" headerClass="sortable" titleKey="infopub.threadForm.threadTypeId">
            <eoms:dict key="dict-workbench-infopub" dictId="threadType" itemId="${threadList.threadTypeId}"
                       beanId="id2nameXML"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable" titleKey="infopub.threadForm.forumsId">
            <eoms:id2nameDB id="${threadList.forumsId}" beanId="forumsDao"/>
        </display:column>

        <display:setProperty name="paging.banner.item_name" value="thread"/>
        <display:setProperty name="paging.banner.items_name" value="threads"/>
    </display:table>

    <logic:notEmpty name="allowAdd">
        <input type="button" class="button"
               onclick="location.href='<html:rewrite page='/thread.do?method=edit&forumsId=${forumsId}'/>'"
               value="<fmt:message key="button.add"/>"/>
    </logic:notEmpty>

    <input type="button" class="button" onclick="location.href='<html:rewrite page='/thread.do?method=initSearch'/>'"
           value="${eoms:a2u('查询')}"/>
    <input type="button" class="button"
           onclick="location.href='<html:rewrite page='/threadAuditHistory.do?method=waitList'/>'"
           value="${eoms:a2u('审核')}"/>

    <priv:region url="<%=InfopubConstants.WORKBENCH_INFOPUB_PERMISSION_THREAD_MGR%>">
        <html:submit styleClass="button" property="method.delChoose" onclick="return ConfirmDel()">
            <fmt:message key="button.delete"/>
        </html:submit>

        <html:submit styleClass="button" property="method.sort" onclick="return sort()">
            <bean:message key="threadList.sort"/>
        </html:submit>

        <input type="text" id="forumsName" name="forumsName" class="text" readonly="readonly"
               value="<bean:message key='forumsTree.title.choose'/>"/>
        <html:hidden property="toForumsId" styleId="toForumsId"/>
    </priv:region>

</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
