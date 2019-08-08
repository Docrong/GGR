<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.km.expert.config.KmExpertProps"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        var deptTree = new xbox({
            btnId: 'deptName',
            dlgId: 'showdept-dlg',
            treeDataUrl: '${app}/xtree.do?method=userFromDept',
            treeRootId: '-1',
            treeRootText: '所属部门',
            treeChkMode: '',
            treeChkType: 'dept',
            showChkFldId: 'deptName',
            saveChkFldId: 'deptId'
        });

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
    });
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <content tag="heading">
        <b><fmt:message key="kmExpertBasic.tree.rootText"/></b>
    </content>

    <br><br>

    <html:form action="/kmExpertBasics.do?method=search" styleId="kmExpertBasicForm" method="post">
        <tr align='center'>
            <td>
                <fmt:message key="kmExpertBasic.deptId"/>：
                <input type="text" id="deptName" name="deptName" class="text" readonly="readonly" value=""
                       alt="allowBlank:false,vtext:'请选择知识分类(字典)...'"/>
                <html:hidden property="deptId" styleId="deptId" styleClass="text medium"
                             value="${kmExpertBasicForm.deptId}"/>
                &nbsp;&nbsp;
                <fmt:message key="kmExpertBasic.specialty"/>：
                <input type="text" id="specialtyName" name="specialtyName" class="text" readonly="readonly" value=""
                       alt="allowBlank:false,vtext:'请选择知识分类(字典)...'"/>
                <html:hidden property="specialty" styleId="specialty" styleClass="text medium"
                             value="${kmExpertBasicForm.specialty}"/>
                &nbsp;&nbsp;
                <input type="hidden" id="nodeId" name="nodeId" value="${nodeId }">
                <input type="hidden" id="fieldName" name="fieldName" value="${fieldName}">
                <input type="submit" value="<fmt:message key="button.query"/>"/>
            </td>
        </tr>
    </html:form>

    <display:table name="kmExpertBasicList" cellspacing="0" cellpadding="0"
                   id="kmExpertBasic" pagesize="${pageSize}" class="table kmExpertBasicList"
                   export="false"
                   requestURI="${app}/kmmanager/kmExpertBasics.do?method=search"
                   sort="list" partialList="true" size="resultSize">

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExpertBasic.userId">
            <eoms:id2nameDB id="${kmExpertBasic.userId}" beanId="tawSystemUserDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExpertBasic.deptId">
            <eoms:id2nameDB id="${kmExpertBasic.deptId}" beanId="tawSystemDeptDao"/>
        </display:column>

        <display:column property="phone" sortable="true"
                        headerClass="sortable" titleKey="kmExpertBasic.phone"/>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExpertBasic.specialty">
            <eoms:id2nameDB id="${kmExpertBasic.specialty}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExpertBasic.expertClass">
            <eoms:id2nameDB id="${kmExpertBasic.expertClass}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="kmExpertBasic.expertLevel">
            <eoms:id2nameDB id="${kmExpertBasic.expertLevel}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column property="intro" sortable="true"
                        headerClass="sortable" titleKey="kmExpertBasic.intro"/>

        <display:column title="查看" headerClass="imageColumn">
            <a href="javascript:var id = '${kmExpertBasic.id }';
		                        var userId = '${kmExpertBasic.userId}';
		                        var url='${app}/kmmanager/kmExpertBasics.do?method=detailExpert';
		                        url = url + '&id=' + id + '&userId=' + userId ;
		                        location.href=url"><img src="${app}/images/icons/search.gif"/></a>
        </display:column>
        <display:setProperty name="paging.banner.item_name" value="kmExpertBasic"/>
        <display:setProperty name="paging.banner.items_name" value="kmExpertBasics"/>
    </display:table>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>