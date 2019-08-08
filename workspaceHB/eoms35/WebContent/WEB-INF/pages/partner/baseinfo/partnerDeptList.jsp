<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<script type="text/javascript">
    var checkflag = "false";

    function chooseAll() {
        var objs = document.getElementsByName("checkbox11");
        if (checkflag == "false") {
            for (var i = 0; i < objs.length; i++) {
                objs[i].checked = "checked";
            }
            checkflag = "checked";
        } else if (checkflag == "checked") {
            for (var i = 0; i < objs.length; i++) {
                objs[i].checked = false;
            }
            checkflag = "false";
        }

    }

    function isChecked() {
        var objs = document.getElementsByName("checkbox11");
        document.forms[1].action = "${app}/partner/baseinfo/partnerDepts.do?method=remove";
        var flag = false;
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].checked == true) {
                flag = true;
                break;
            }
        }
        if (flag == true) {
            document.forms[1].submit();
            return true;
        } else if (flag == false) {
            alert("请选择删除项！");
            return false;
        }
    }
</script>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="btn"
           onclick="location.href='<html:rewrite page='/partnerDepts.do?method=add&nodeId=${parentNodeId }'/>'"
           value="<fmt:message key="button.add"/>"/>

    <html:button style="margin-right: 5px" property="button1" onclick="isChecked()" styleClass="btn">
        <fmt:message key="button.delete"/>
    </html:button>
</c:set>

<fmt:bundle basename="config/applicationResources-partner-baseinfo">
    <html:form action="partnerDepts.do?method=search" method="post" styleId="partnerDeptForm">
        <input type="hidden" name="parentNodeId" value="${parentNodeId }">
        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="partnerDept.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="partnerDept.name"/>
                </td>
                <td class="content">
                    <input type="text" name="nameSearch" id="name"
                           class="text medium"
                           alt="allowBlank:false,vtext:''"/>
                </td>

                <td class="label">
                    <fmt:message key="partnerDept.manager"/>
                </td>
                <td class="content">
                    <input type="text" name="managerSearch" id="manager"
                           class="text medium"
                           alt="allowBlank:true,vtext:''"/>
                </td>

            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="partnerDept.phone"/>
                </td>
                <td class="content">
                    <input type="text" name="phoneSearch" id="phone"
                           class="text medium"
                           alt="allowBlank:true,vtext:''"/>
                </td>
            </tr>
        </table>
        <table align="center">
            <tr>
                <td>
                    <input type="submit" class="btn" value="查询"/>

                    <input type="reset" class="btn" value="重置"/>
                </td>
            </tr>
        </table>
    </html:form>

    <html:form action="partnerDepts" method="post" styleId="partnerDeptForm">
        <input type="hidden" name="parentNodeId" value="${parentNodeId }">
        <content tag="heading">
            <fmt:message key="partnerDept.list.heading"/>
        </content>

        <display:table name="partnerDeptList" cellspacing="0" cellpadding="0"
                       id="partnerDeptList" pagesize="${pageSize}" class="table partnerDeptList"
                       export="false"
                       requestURI="${app}/partner/baseinfo/partnerDepts.do?method=search"
                       sort="list" partialList="true" size="resultSize">

            <display:column title="<input type='checkbox' onclick='javascript:chooseAll();'>">
                <input type="checkbox" name="checkbox11" value="<c:out value='${partnerDeptList.id}'/>">
            </display:column>

            <display:column property="name" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.name"
                            href="${app}/partner/baseinfo/partnerDepts.do?method=edit" paramId="id" paramProperty="id"/>

            <display:column property="manager" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.manager" paramId="id" paramProperty="id"/>

            <display:column property="areaName" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.areaName" paramId="id" paramProperty="id"/>

            <display:column property="phone" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.phone" paramId="id" paramProperty="id"/>

            <display:column sortable="true"
                            headerClass="sortable" titleKey="partnerDept.aptitude" paramId="id" paramProperty="id">

                <eoms:id2nameDB id="${partnerDeptList.aptitude}" beanId="tawSystemDictTypeDao"/>

            </display:column>

            <display:column property="deadline" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.deadline" paramId="id" paramProperty="id"/>

            <display:column property="bank" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.bank" paramId="id" paramProperty="id"/>

            <display:column property="account" sortable="true"
                            headerClass="sortable" titleKey="partnerDept.account" paramId="id" paramProperty="id"/>

            <display:setProperty name="paging.banner.item_name" value="partnerDept"/>
            <display:setProperty name="paging.banner.items_name" value="partnerDepts"/>
        </display:table>

        <c:out value="${buttons}" escapeXml="false"/>
    </html:form>
</fmt:bundle>
<c:if test="${requestScope.refreshTree == 1}">
    <script type="text/javascript">
        try {
            //刷新父框架中的整颗树
            //parent.AppFrameTree.refreshTree();
            //刷新父框架中当前选中的节点
            parent.AppFrameTree.reloadNode();
        } catch (e) {
        }
    </script>
</c:if>

<%@ include file="/common/footer_eoms.jsp" %>