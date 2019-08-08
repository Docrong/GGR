<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<html:form action="/kmContentsOpinions.do?method=save" styleId="kmContentsOpinionForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

        <!-- 知详细信息 -->
        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="kmContents.detail.title"/></div>
            </caption>

            <!-- 定义知识内容变量 -->
            <c:set var="KmContentsMap" scope="page" value="${KmContents}"/>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.tableId"/>
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${KmContentsMap.TABLE_ID}" beanId="kmTableGeneralDao"/>
                </td>

                <td class="label">
                    <fmt:message key="kmContents.themeId"/>
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${KmContentsMap.THEME_ID}" beanId="kmTableThemeDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.rolestrFlag"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="rolestrFlag" itemId="${KmContentsMap.ROLESTR_FLAG}"
                               beanId="id2nameXML"/>
                </td>

                <td class="label">
                    <fmt:message key="kmContents.levelFlag"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="levelFlag" itemId="${KmContentsMap.LEVEL_FLAG}"
                               beanId="id2nameXML"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.createUser"/>
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${KmContentsMap.CREATE_USER}" beanId="tawSystemUserDao"/>
                </td>

                <td class="label">
                    <fmt:message key="kmContents.createDept"/>
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${KmContentsMap.CREATE_DEPT}" beanId="tawSystemDeptDao"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.contentTitle"/>
                </td>
                <td class="content" colspan="3">
                        ${KmContentsMap.CONTENT_TITLE}
                </td>
            </tr>

            <!-- 判断修改人是否为空 -->
            <c:if test="${not empty KmContentsMap.MODIFY_USER}">
                <tr>
                    <td class="label">
                        <fmt:message key="kmContents.modifyUser"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${KmContentsMap.MODIFY_USER}" beanId="tawSystemUserDao"/>
                    </td>

                    <td class="label">
                        <fmt:message key="kmContents.modifyDept"/>
                    </td>
                    <td class="content">
                        <eoms:id2nameDB id="${KmContentsMap.MODIFY_DEPT}" beanId="tawSystemDeptDao"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <fmt:message key="kmContents.modifyTime"/>
                    </td>
                    <td class="content" colspan="3">
                            ${KmContentsMap.MODIFY_TIME}
                    </td>
                </tr>
            </c:if>


            <tr>
                <td class="label">
                    <fmt:message key="kmContents.contentStatus"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${KmContentsMap.CONTENT_STATUS}"
                               beanId="id2nameXML"/>
                </td>

                <td class="label">
                    <fmt:message key="kmContents.auditFlag"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${KmContentsMap.AUDIT_FLAG}"
                               beanId="id2nameXML"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.isBest"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${KmContentsMap.IS_BEST}"
                               beanId="id2nameXML"/>
                </td>

                <td class="label">
                    <fmt:message key="kmContents.isPublic"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${KmContentsMap.IS_PUBLIC}"
                               beanId="id2nameXML"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    知识评价
                </td>
                <td class="content" colspan="3">
                    <fmt:message key="kmContents.gradeOne"/> ${KmContentsMap.GRADE_ONE} 次 <b>|</b>
                    <fmt:message key="kmContents.gradeTwo"/> ${KmContentsMap.GRADE_TWO} 次 <b>|</b>
                    <fmt:message key="kmContents.gradeThree"/> ${KmContentsMap.GRADE_THREE} 次 <b>|</b>
                    <fmt:message key="kmContents.gradeFour"/> ${KmContentsMap.GRADE_FOUR} 次 <b>|</b>
                    <fmt:message key="kmContents.gradeFive"/> ${KmContentsMap.GRADE_FIVE} 次
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.readCount"/>
                </td>
                <td class="content">
                        ${KmContentsMap.READ_COUNT}
                </td>

                <td class="label">
                    <fmt:message key="kmContents.useCount"/>
                </td>
                <td class="content">
                        ${KmContentsMap.USE_COUNT}
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.modifyCount"/>
                </td>
                <td class="content" colspan="3">
                        ${KmContentsMap.MODIFY_COUNT}
                </td>
            </tr>

            <tr>
                    <%--
                        modify by mios:
                        mark有4个状态： 0：半行， 1：整行， 2：左半行，右为空行， 3：右半行，左为空行,通过mark控制是否输出空列
                        flag用来控制是否换行，每输出两列后，flag+1
                    --%>
                <c:set var="flag" value="${0}"/>
                <c:forEach items="${KmTableColumnList}" var="item" varStatus="status">

                <c:if test="${item.mark == 3}"> <%-- 有右半行，填充左半行为空行--%>
                    <td class="label"></td>
                    <td class="content" role="4"></td>
                    <c:set var="flag" value="${flag+1}"/>
                </c:if>

                <c:if test="${item.isOpen == 1}">
                    <td class="label">
                            ${item.colChname}
                        <c:if test="${item.isNullable == 0}">&nbsp;<font color='red'>*</font></c:if>
                    </td>
                    <td class="content"
                        <c:if test="${item.mark == 1}">colspan="3"</c:if> role="${item.mark}">
                        <c:choose>
                            <c:when test="${item.colDictType == 0}">
                                <c:choose>
                                    <c:when test="${item.colType == 1}">
                                        <!-- 不绑定_普通文本 -->
                                        <c:out value="${KmContentsMap[item.colName]}"/>
                                    </c:when>

                                    <c:when test="${item.colType == 2}">
                                        <!-- 不绑定_大文本域 -->
                                        <textarea name="props(${item.colName})" cols="50" id="${item.colName}"
                                                  class="textarea max" readonly="readonly"><c:out
                                                value="${KmContentsMap[item.colName]}"/></textarea>
                                    </c:when>

                                    <c:when test="${item.colType == 3}">
                                        <!-- 不绑定_数字类型 -->
                                        <c:out value="${KmContentsMap[item.colName]}"/>
                                    </c:when>

                                    <c:when test="${item.colType == 4}">
                                        <!-- 不绑定_日期时间 -->
                                        <c:out value="${KmContentsMap[item.colName]}"/>
                                    </c:when>

                                    <c:when test="${item.colType == 7}">
                                        <!-- 不绑定_附件上传 -->
                                        <eoms:attachment name="KmContents" property="${item.colName}" scope="request"
                                                         idField="${item.colName}" appCode="kmmanager" viewFlag="Y"/>
                                    </c:when>
                                </c:choose>
                            </c:when>

                            <c:when test="${item.colDictType == 1}">
                                <c:choose>
                                    <c:when test="${item.colType == 5}">
                                        <!-- 普通字典_单选字段 -->
                                        <eoms:id2nameDB id="${KmContentsMap[item.colName]}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </c:when>

                                    <c:when test="${item.colType == 6}">
                                        <!-- 普通字典_多选字段 -->
                                        <eoms:id2nameDB id="${KmContentsMap[item.colName]}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </c:when>
                                </c:choose>
                            </c:when>

                            <c:when test="${item.colDictType == 2}">
                                <c:choose>
                                    <c:when test="${item.colType == 5}">
                                        <!-- 知识字典_单选字段 -->
                                        <eoms:id2nameDB id="${KmContentsMap[item.colName]}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </c:when>

                                    <c:when test="${item.colType == 6}">
                                        <!-- 知识字典_多选字段 -->
                                        <eoms:id2nameDB id="${KmContentsMap[item.colName]}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </c:when>
                                </c:choose>
                            </c:when>

                            <c:when test="${item.colDictType == 3}">
                                <c:choose>
                                    <c:when test="${item.colType == 5}">
                                        <!-- 文件字典_单选字段 -->
                                        <eoms:id2nameDB id="${KmContentsMap[item.colName]}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </c:when>

                                    <c:when test="${item.colType == 6}">
                                        <!-- 文件字典_多选字段 -->
                                        <eoms:id2nameDB id="${KmContentsMap[item.colName]}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </c:when>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </td>
                </c:if>

                <c:if test="${item.mark != 1}"> <%-- 半行--%>
                    <c:set var="flag" value="${flag+1}"/>
                </c:if>

                <c:if test="${item.mark == 2}"> <%-- 有左半行，填充右半行为空行--%>
                    <td class="label"></td>
                    <td class="content" role="4"></td>
                    <c:set var="flag" value="${flag+1}"/>
                </c:if>

                <c:if test="${item.mark == 1 || flag>=2}"> <%-- 整行，可以换行--%>
            </tr>
                ${!status.last?"<tr>":""}
            <c:set var="flag" value="${0}"/>
            </c:if>

            </c:forEach>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.contentKeys"/>
                </td>
                <td class="content" colspan="3">
                        ${KmContentsMap.CONTENT_KEYS}
                </td>
            </tr>
        </table>

    </fmt:bundle>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
