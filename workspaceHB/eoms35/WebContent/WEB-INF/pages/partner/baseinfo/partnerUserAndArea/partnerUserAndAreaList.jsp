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
        document.forms[1].action = "${app}/partner/baseinfo/partnerUserAndAreas.do?method=remove";
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
           onclick="location.href='<html:rewrite page='/partnerUserAndAreas.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>

    <html:button style="margin-right: 5px" property="button1" onclick="isChecked()" styleClass="btn">
        <fmt:message key="button.delete"/>
    </html:button>

</c:set>

<fmt:bundle basename="config/applicationResources-partner-baseinfo">
    <html:form action="/partnerUserAndAreas.do?method=search" styleId="partnerUserAndAreaForm" method="post">
        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="partnerUserAndArea.form.heading"/></div>
            </caption>
            <tr>
                <td class="label">
                    <fmt:message key="partnerUserAndArea.name"/>
                </td>
                <td class="content">
                    <input type="text" name="nameSearch" id="nameSearch"
                           class="text medium"
                           alt="allowBlank:false,vtext:''"/>
                </td>

                <td class="label">
                    <fmt:message key="partnerUserAndArea.userId"/>
                </td>
                <td class="content">
                    <input type="text" name="userIdSearch" id="userIdSearch"
                           class="text medium"
                           alt="allowBlank:false,vtext:''"/>
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

    <html:form action="partnerUserAndAreas" styleId="partnerUserAndAreaForm" method="post">
        <content tag="heading">
            <fmt:message key="partnerUserAndArea.list.heading"/>
        </content>

        <display:table name="partnerUserAndAreaList" cellspacing="0" cellpadding="0"
                       id="partnerUserAndAreaList" pagesize="${pageSize}" class="table partnerUserAndAreaList"
                       export="false"
                       requestURI="${app}/partner/baseinfo/partnerUserAndAreas.do?method=search"
                       sort="list" partialList="true" size="resultSize">

            <display:column title="<input type='checkbox' onclick='javascript:chooseAll();'>">
                <input type="checkbox" name="checkbox11" value="<c:out value='${partnerUserAndAreaList.id}'/>">
            </display:column>

            <display:column property="name" sortable="true"
                            headerClass="sortable" titleKey="partnerUserAndArea.name" paramId="id" paramProperty="id"/>

            <display:column property="userId" sortable="true"
                            headerClass="sortable" titleKey="partnerUserAndArea.userId"
                            href="${app}/partner/baseinfo/partnerUserAndAreas.do?method=edit" paramId="id"
                            paramProperty="id"/>

            <display:column property="areaNames" sortable="true"
                            headerClass="sortable" titleKey="partnerUserAndArea.areaNames" paramId="id"
                            paramProperty="id"/>

            <display:setProperty name="paging.banner.item_name" value="partnerUserAndArea"/>
            <display:setProperty name="paging.banner.items_name" value="partnerUserAndAreas"/>
        </display:table>

        <c:out value="${buttons}" escapeXml="false"/>
    </html:form>
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>