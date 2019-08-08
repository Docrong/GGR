<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<%@page import="com.boco.eoms.commons.statistic.base.config.excel.* ,java.util.*" %>
<%
    Excel excelConfig = (Excel) request.getAttribute("excelConfig");
    if (excelConfig == null) throw new Exception("读取配置统计文件失败!");

    String excelConfigURL = String.valueOf(request.getAttribute("excelConfigURL"));
    String findListForward = String.valueOf(request.getAttribute("findListForward"));
    Calendar cld = Calendar.getInstance();
    int year = cld.get(Calendar.YEAR);
    int mondth = cld.get(Calendar.MONTH) + 1;
%>

<script language="JavaScript" type="text/JavaScript" src="${app}/scripts/module/partner/ajax.js"></script>
<script type="text/javascript">
    var userTreeAction = '${app}/xtree.do?method=dept';
    var treeAction = '${app}/xtree.do?method=userByDept';
    var userByDeptTree = '${app}/xtree.do?method=userFromDept';//部门用户树
    var roleTree = '${app}/xtree.do?method=roleTree'; //角色树
    var subroleFromDept = '${app}/xtree.do?method=subroleFromDept'; //部门角色树
    var v;


</script>

<html:form action="/stat.do?method=performStatistic" styleId="theform">


    <table class="formTable">
        <caption>输入条件</caption>
        <input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">
        <input type="hidden" name="findListForward" value="<%=findListForward %>">

        <c:if test="${type=='area'}"> <!-- 按地市统计 -->
            <input type="hidden" name="reportIndex" value="0">
            <tr>
                <td noWrap class="label">
                    地市名称
                </td>
                <td><select name="areaId" id="areaId" onchange="changeDep();">
                    <c:if test="${areaList!=null}">
                        <c:forEach var="area" items="${areaList}">
                            <option value="${area.nodeId }">${area.nodeName }</option>
                        </c:forEach>
                    </c:if>
                </select>
                    <input type="hidden" name="areaName" id="areaName" value="">
                </td>
            </tr>
            <tr>
                <td noWrap class="label">
                    合作伙伴名称
                </td>
                <td>
                    <select name="deptId" id="deptId">
                    </select>
                    <input type="hidden" name="deptName" id="deptName" value="">
                </td>
            </tr>
        </c:if>
        <c:if test="${type=='dept'}"> <!-- 按合作伙伴名称统计 -->
            <input type="hidden" name="reportIndex" value="1">
            <tr>
                <td noWrap class="label">
                    合作伙伴名称
                </td>
                <td>
                    <select name="deptName" id="deptName">
                        <c:if test="${deptList!=null}">
                            <c:forEach var="name" items="${deptList}">
                                <option value="${name }">${name }</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </td>
            </tr>
        </c:if>
    </table>

    <br/>
    <!-- buttons -->

    <html:submit styleClass="btn" property="method.save" styleId="method.save">
        <bean:message bundle="statistic" key="button.done"/>
    </html:submit>

</html:form>
<script type="text/javascript">
    var id = document.getElementById("areaId");
    if (id != null && id.value != "") {
        var areaName = document.getElementById("areaName");
        var deptName = document.getElementById("deptName");
        var url = "<%=request.getContextPath()%>/partner/baseinfo/partnerUsers.do?method=changeDep&id=" + id.value;
        var fieldName = "deptId";
        changeList(url, fieldName, "");
        areaName.value = document.getElementById("areaId").options[document.getElementById("areaId").selectedIndex].text;
        deptName.value = document.getElementById("deptId").options[document.getElementById("deptId").selectedIndex].text;
    }

    function changeDep() {
        var id = document.getElementById("areaId");
        if (id.value != null && id.value != "") {
            var url = "<%=request.getContextPath()%>/partner/baseinfo/partnerUsers.do?method=changeDep&id=" + id.value;
            var fieldName = "deptId";
            changeList(url, fieldName, "");
            areaName.value = document.getElementById("areaId").options[document.getElementById("areaId").selectedIndex].text;
            deptName.value = document.getElementById("deptId").options[document.getElementById("deptId").selectedIndex].text;
        }
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>
