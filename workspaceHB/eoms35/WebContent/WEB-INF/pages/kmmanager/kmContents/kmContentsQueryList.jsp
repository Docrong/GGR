<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<jsp:directive.page import="com.boco.eoms.workbench.infopub.util.InfopubConstants"/>
<style type="text/css">
    .small {
        width: 10px;
    }
</style>
<script type="text/javascript">
    function choose(checkbox) {
        eoms.util.checkAll(checkbox, 'ids');
    }

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
</script>

<content tag="heading">
</content>

<html:form action="/kmContentss.do?method=queryDo" method="post" styleId="kmContentsForm">
    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

        <input type="hidden" name="status" id="status"/>

        <display:table name="kmContentsList" cellspacing="0" cellpadding="0"
                       id="kmContentsList" pagesize="${pageSize}" class="table kmContentsList"
                       export="false"
                       requestURI="${app}/kmmanager/kmContentss.do?method=search"
                       sort="list" partialList="true" size="resultSize">

            <display:column sortable="true" headerClass="sortable" titleKey="kmContents.themeId">
                <eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao"/>
            </display:column>

            <display:column sortable="true" property="contentTitle" titleKey="kmContents.contentTitle"
                            paramId="id" paramProperty="id" headerClass="sortable"/>

            <display:column sortable="true" headerClass="sortable" titleKey="kmContents.createUser">
                <eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao"/>
            </display:column>

            <display:column sortable="true" headerClass="sortable" titleKey="kmContents.createDept">
                <eoms:id2nameDB id="${kmContentsList.createDept}" beanId="tawSystemDeptDao"/>
            </display:column>

            <display:column sortable="true" property="createTime" titleKey="kmContents.createTime"
                            format="{0,date,yyyy-MM-dd HH:mm:ss}"
                            paramId="id" paramProperty="id" headerClass="sortable"/>

            <display:column sortable="false" property="contentKeys" titleKey="kmContents.contentKeys"
                            paramId="id" paramProperty="id" headerClass="sortable"/>

            <display:column title="查看" headerClass="imageColumn">
                <a href="javascript:var id = '${kmContentsList.id }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detail';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url"><img src="${app}/images/icons/search.gif"/></a>
            </display:column>

            <display:setProperty name="paging.banner.item_name" value="kmContents"/>
            <display:setProperty name="paging.banner.items_name" value="kmContentss"/>
        </display:table>

    </fmt:bundle>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>