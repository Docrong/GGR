<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
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
        var auditStatus = document.getElementsByName("auditStatus11");
        document.forms[0].action = "${app}/workbench/report/tawWorkbenchReports.do?method=delete&flag=${flag }";
        var flag = false;
        for (var i = 0; i < objs.length; i++) {
            if (objs[i].checked == true) {
                if (auditStatus[i].value == 0) {
                    flag = true;
//		           break;
                } else {
                    alert("删除项中有已提交过的报告！请重新选择删除项！");
                    return false;
                }
            }
        }
        if (flag == true) {
            document.forms[0].submit();
            return true;
        } else if (flag == false) {
            alert("请选择删除项！");
            return false;
        }
    }
</script>
<html:form action="tawWorkbenchReports" method="post" styleId="tawWorkbenchReportForm">

    <c:if test="${flag==null||flag!='week'}">
        <content tag="heading"><bean:message key="tawWorkbenchReportList.heading"/></content>
    </c:if>
    <c:if test="${flag!=null&&flag=='week'}">
        <content tag="heading"><bean:message key="tawWorkbenchReportList.weekheading"/></content>
    </c:if>

    <c:set var="buttons">

        <input type="button" style="margin-right: 5px" class="button"
               onclick="location.href='<c:url
                       value="/workbench/report/tawWorkbenchReports.do?method=add&flag=${flag }"/>'"
               value="<fmt:message key="button.add"/>"/>

        <html:button style="margin-right: 5px" property="button1" onclick="isChecked();" styleClass="button">
            <fmt:message key="button.delete"/>
        </html:button>
    </c:set>

    <!-- <c:out value="${buttons}" escapeXml="false"/> -->
    <fmt:setBundle basename="config.ApplicationResources-workbench-report"/>
    <display:table name="tawWorkbenchReportList" cellspacing="0" cellpadding="0"
                   id="tawWorkbenchReportList" pagesize="25" class="table tawWorkbenchReportList"
                   export="false" requestURI="/workbench/report/tawWorkbenchReports.do?method=list" sort="external"
                   partialList="true" size="resultSize">

        <display:column title="<input type='checkbox' onclick='javascript:chooseAll();'>">
            <input type="checkbox" name="checkbox11" value="<c:out value='${tawWorkbenchReportList.id}'/>">
        </display:column>

        <display:column property="reportPersonName" sortable="true" headerClass="sortable"
                        url="/workbench/report/tawWorkbenchReports.do?method=edit&flag=${flag }" paramId="id"
                        paramProperty="id" titleKey="tawWorkbenchReportForm.reportPersonName"/>

        <display:column property="reportTime" sortable="true" headerClass="sortable"
                        titleKey="tawWorkbenchReportForm.reportTime"/>

        <display:column property="summary" sortable="true" headerClass="sortable"
                        titleKey="tawWorkbenchReportForm.summary"/>

        <c:if test="${flag==null||flag!='week'}">
            <display:column property="tomorrowTarget" sortable="true" headerClass="sortable"
                            titleKey="tawWorkbenchReportForm.tomorrowTarget"/>
        </c:if>

        <display:column property="auditName" sortable="true" headerClass="sortable"
                        titleKey="tawWorkbenchReportForm.auditName"/>


        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawWorkbenchReportForm.auditStatus">
            <c:if test="${tawWorkbenchReportList.auditStatus==0}">未提交</c:if>
            <c:if test="${tawWorkbenchReportList.auditStatus==1}">已提交</c:if>
            <c:if test="${tawWorkbenchReportList.auditStatus==2}">已审核</c:if>
            <input type="hidden" name="auditStatus11" id="auditStatus11" value="${tawWorkbenchReportList.auditStatus}">
        </display:column>

        <c:if test="${flag!=null&&flag=='week'}">
            <input type="hidden" name="flag" id="flag" value="<c:out value='${flag }'/>">
        </c:if>

        <display:setProperty name="paging.banner.item_name" value="tawWorkbenchReport"/>
        <display:setProperty name="paging.banner.items_name" value="tawWorkbenchReports"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>

