<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.km.expert.config.KmExpertProps"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'kmExpertGroupForm'});

        var specXbox = new xbox({
            btnId: "specialtyName",
            dlgId: 'spec-dlg',
            treeDataUrl: '${app}/xtree.do?method=dict',
            treeRootId: '<%=KmExpertProps.getDictRootId("RootSpecialty")%>',
            treeRootText: '所有专业',
            treeChkMode: 'single',
            showChkFldId: "specialtyName",
            saveChkFldId: "specialty"
        });

        var singUserXbox = new xbox({
            btnId: 'groupLeaderName',
            dlgId: 'showuser-dlg',
            treeDataUrl: '${app}/kmmanager/kmExpertTree.do?method=userFromMenu',
            treeRootId: '-1',
            treeRootText: '人员列表',
            treeChkMode: 'single',
            treeChkType: 'user',
            showChkFldId: 'groupLeaderName',
            saveChkFldId: 'groupLeader'
        });

        var moreUserXbox = new xbox({
            btnId: 'groupMemberName',
            dlgId: 'showuser-dlg',
            treeDataUrl: '${app}/kmmanager/kmExpertTree.do?method=userFromMenu',
            treeRootId: '-1',
            treeRootText: '人员列表',
            treeChkMode: '',
            treeChkType: 'user',
            showChkFldId: 'groupMemberName',
            saveChkFldId: 'groupMember'
        });
    });
</script>

<html:form action="/kmExpertGroup.do?method=save" styleId="kmExpertGroupForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager"><br>
        <table class="formTable">

            <caption>
                <div class="header center">专家团队</div>
            </caption>

            <tr class="tr_show">
                <td class="label" nowrap="nowrap" width="15%">
                    团队名称&nbsp;<font color='red'>*</font>
                </td>
                <td nowrap="nowrap" width="85%">
                    <html:text property="groupName" styleId="groupName" styleClass="text medium"
                               value="${kmExpertGroupForm.groupName}"
                               alt="allowBlank:false,vtext:'请输入团队名称...'"/>
                </td>
            </tr>
            <tr class="tr_show">
                <td class="label">
                    所属专业&nbsp;<font color='red'>*</font>
                </td>
                <td class="content">
                    <input type="text" id="specialtyName" name="specialtyName" class="text" readonly="readonly"
                           value="<eoms:id2nameDB id="${kmExpertGroupForm.specialty}" beanId="ItawSystemDictTypeDao" />"
                           alt="allowBlank:false,vtext:'请选择专业(字典)...'"/>
                    <html:hidden property="specialty" styleId="specialty" value="${kmExpertGroupForm.specialty}"/>
                </td>
            </tr>

            <tr class="tr_show">
                <td class="label">
                    团队组长&nbsp;<font color='red'>*</font>
                </td>
                <td class="content">
                    <input type="text" id="groupLeaderName" name="groupLeaderName"
                           value="<eoms:id2nameDB id="${kmExpertGroupForm.groupLeader}" beanId="tawSystemUserDao" />"
                           readonly="readonly" class="text medium"
                           alt="allowBlank:false,vtext:'请选择团队组长...'"/>
                    <html:hidden property="groupLeader" value="${kmExpertGroupForm.groupLeader}"/>
                </td>
            </tr>

            <tr class="tr_show">
                <td class="label">
                    团队组员&nbsp;<font color='red'>*</font>
                </td>
                <td class="content">
                    <textarea name="groupMemberName" cols="50" id="groupMemberName" class="textarea medium"
                              alt="allowBlank:false,vtext:'请选择团队组员...'">${kmExpertGroupForm.groupMemberName}</textarea>
                    <html:hidden property="groupMember" value="${kmExpertGroupForm.groupMember}"/>
                </td>
            </tr>
        </table>
    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value='<fmt:message key="button.save"/>'/>
                <c:if test="${not empty kmExpertBasicForm.id}">
                    <input type="button" class="btn" value='<fmt:message key="button.back"/>'
                           onclick="javascript:parent.window.history.back();"/>
                </c:if>
            </td>
        </tr>
    </table>

    <html:hidden property="id" value="${kmExpertGroupForm.id}"/>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>