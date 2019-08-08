<%@ include file="/common/extlibs.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.*" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!-- 返回控制 -->
<% String back = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("back")); %>
<logic:notPresent name="back" scope="request">
    <% if (!back.equals("")) {
        request.setAttribute("back", back);
    }
    %>
</logic:notPresent>
<html:form action="ordersheets.do?method=xsearch" method="post" styleId="ordersheetForm">
    <caption>
        <div class="header center">定单目录库</div>
    </caption>
    <%int jNum = 0;%>
    <bean:define id="url" value="ordersheets.do"/>
    <display:table name="ordersheetList" cellspacing="0" cellpadding="0"
                   id="ordersheet" pagesize="15" class="table ordersheetList"
                   export="false" requestURI="/businessupport/order/ordersheets.do?method=showDetail" sort="list">
        <input type="hidden" name="${sheetPageName}id" id="${sheetPageName}id" value="${sheetPageName}id"/>
        <%
            jNum += 1;
            request.setAttribute("jNum", new Integer(jNum));
        %>

        <display:column headerClass="sortable" title="序号">${jNum}</display:column>
        <display:column sortable="true" headerClass="orderType" title="定单类型"
                        url="/businessupport/order/ordersheets.do?method=showDetail&flag=showDetail" paramId="id"
                        paramProperty="id">
            <eoms:id2nameDB id="${ordersheet.orderType}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column property="mainProductInstanceCode" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id" title="主产品实例编码"/>
        <display:column sortable="true" headerClass="sortable" title="紧急程度">
            <eoms:id2nameDB id="${ordersheet.urgentDegree}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="定单业务类型">
            <eoms:id2nameDB id="${ordersheet.orderBuisnessType}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column property="creatTime" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" title="生成时间"/>
        <display:column property="endTime" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" title="竣工时间"/>
        <display:column property="completeLimit" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" title="完成期限"/>
        <display:column property="changeTime" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" title="变更时间"/>
        <display:column property="cancelTime" sortable="true" headerClass="sortable"
                        paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" title="撤销时间"/>

        <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.csv" value="false"/>

    </display:table>
    <tr class="buttonBar bottom">
        <input type="button" styleClass="button"
               onclick="location.href='<c:url
                       value="/businessupport/order/ordersheets.do?method=xedit&type=add&ifReference=${ifReference}"/>'"
               value="添加"/>

        <input type="button" styleClass="button"
               onclick="location.href='<c:url value="/businessupport/order/ordersheets.do?method=showQueryPage"/>'"
               value="查询"/>
    </tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>


