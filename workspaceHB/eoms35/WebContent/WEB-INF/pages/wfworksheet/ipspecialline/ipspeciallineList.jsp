<%@ include file="/common/extlibs.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!-- 查询控制 -->
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if (ifReference.equals(""))
        ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
%>
<logic:notPresent name="ifReference" scope="request">
    <% if (!ifReference.equals("")) {
        request.setAttribute("ifReference", ifReference);
    }
    %>
</logic:notPresent>
<!-- 返回控制 -->
<% String back = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("back"));
    String taskNamePara = StaticMethod.nullObject2String(request.getParameter("taskName"));
    System.out.println("@@taskName=ipsList:" + taskNamePara);
%>
<logic:notPresent name="back" scope="request">
    <% if (!back.equals("")) {
        request.setAttribute("back", back);
    }
    %>
</logic:notPresent>
<script type="text/javascript">
    function query() {
        var formObj = document.forms[0];

        if ('${ifReference}' != null && '${ifReference}' != '') {
            formObj.action = "ipspeciallines.do?method=xsearch&ifReference=true&back=true&deleted=0";
        } else {
            formObj.action = "ipspeciallines.do?method=xsearch&back=true&deleted=0";
        }
        formObj.submit();
    }

    function deleted() {
        document.location.href = "ipspeciallines.do?method=xqueryDeleted&ifReference=${ifReference}";
    }

    function onkeydownEvt(evt) {
        evt = evt ? evt : (window.event ? window.event : null);
        if (evt.keyCode == 13) {
            query();
            return false;
        }
    }
</script>

<caption>
    <div class="header center">存量信息</div>
</caption>
<html:form action="ipspeciallines.do?method=xsearch" method="post" styleId="ipspeciallineForm">
    <bean:define id="url" value="ipspeciallines.do"/>
    <display:table name="ipspeciallineList" cellspacing="0" cellpadding="0"
                   id="ipspecialline" pagesize="15" class="table ipspeciallineList"
                   export="true" requestURI="/businessupport/order/ipspeciallines.do?method=showDetail" sort="list">
        <display:column property="businessBandwidth" headerClass="sortable" title="业务带宽"
                        url="/businessupport/product/ipspeciallines.do?method=showDetail&flag=showDetail&ifReference=${ifReference}"
                        paramId="id" paramProperty="id"/>
        <display:column property="businessAmount" headerClass="sortable" title="业务数量(传输条数)"/>
        <display:column property="ipAddressCount" headerClass="sortable" title="IP地址数量"/>
        <display:column property="RCAPDetailAddress" headerClass="sortable" title="接入端详细地址"/>

        <display:column property="ip1" headerClass="sortable" title="IP地址1（账号）"/>
        <display:column property="ip2" headerClass="sortable" title="IP地址2（账号）"/>
        <display:column property="cnetCode" headerClass="sortable" title="子网掩码"/>
        <display:column property="gateway" headerClass="sortable" title="网关"/>
        <display:column property="portANetDeptUser" headerClass="sortable" title="网络部联系人"/>
        <display:column property="portANetDeptPhone" headerClass="sortable" title="网络部门联系人电话"/>

        <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.csv" value="false"/>

    </display:table>
    <tr class="buttonBar bottom">
        <input type="button" styleClass="button"
               onclick="location.href='<c:url
                       value="/businessupport/product/ipspeciallines.do?method=xedit&type=add&ifReference=${ifReference}"/>'"
               value="添加"/>
        <logic:present name="back" scope="request">
            <input type="button" styleClass="button"
                   onclick="location.href='<c:url
                           value="/businessupport/product/ipspeciallines.do?method=xquery&ifReference=${ifReference}"/>'"
                   value="返回"/>
        </logic:present>
    </tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>


