<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<bean:define id="url" value="numberapply.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="false" requestURI="numberapply.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.numberapply.webapp.action.MSCListDisplaytagDecoratorHelper">

    <display:column property="sel" sortable="true"
                    headerClass="sortable" title=""/>
    <c:if test="${stylepage == 'assign'}">
        <display:column property="manual" sortable="true"
                        headerClass="sortable" title=""/>
    </c:if>
    <c:if test="${stylepage == 'new'}">
        <display:column property="edit" sortable="true"
                        headerClass="sortable" title=""/>
        <display:column property="del" sortable="true"
                        headerClass="sortable" title=""/>
    </c:if>
    <display:column property="netName" sortable="true"
                    headerClass="sortable" title="网元名称"/>

    <display:column property="netProp" sortable="true"
                    headerClass="sortable" title="网元属性"/>

    <display:column property="buildAddress" sortable="true"
                    headerClass="sortable" title="建设地点"/>

    <display:column sortable="true"
                    headerClass="sortable" title="供应商">
        <eoms:id2nameDB id="${taskList.supplier}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="信令点编码(24位)">
        <c:if test="${empty taskList.commandCode24}">
            等待管理员分配
        </c:if>
        <c:if test="${!empty taskList.commandCode24}">
            ${taskList.commandCode24}
        </c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="MSC ID">
        <c:if test="${empty taskList.mscId}">
            等待管理员分配
        </c:if>
        <c:if test="${!empty taskList.mscId}">
            ${taskList.mscId}
        </c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="信令点编码(14位)">
        <c:if test="${empty taskList.commandCode14}">
            等待管理员分配
        </c:if>
        <c:if test="${!empty taskList.commandCode14}">
            ${taskList.commandCode14}
        </c:if>
    </display:column>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>