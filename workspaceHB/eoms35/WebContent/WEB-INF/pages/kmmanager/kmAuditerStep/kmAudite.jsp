<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<script type="text/javascript" src="${app}/scripts/kmmanager/adapter-km.js"></script>

<script type="text/javascript">
    function onSubmit() {
        if (document.forms[0].auditResult.value == "") {
            alert('请选择审核结果');
            return false;
        }

        // document.forms[0].historyType.value="1";
        document.forms[0].submit();
        return true;
    }
</script>


<html:form action="/kmAuditerSteps.do?method=contentAuditDo" styleId="kmContentsOpinionForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

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

            <c:forEach items="${KmTableColumnList}" var="item">
                <c:if test="${item.isVisibl == 1}">
                    <tr>
                        <td class="label">
                                ${item.colChname}
                        </td>
                        <td class="content" colspan="3">
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
                                            <eoms:attachment name="KmContents" property="${item.colName}"
                                                             scope="request" idField="${item.colName}"
                                                             appCode="kmmanager" viewFlag="Y"/>
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
                    </tr>
                </c:if>
            </c:forEach>

            <tr>
                <td class="label">
                    <fmt:message key="kmContents.contentKeys"/>
                </td>
                <td class="content" colspan="3">
                        ${KmContentsMap.CONTENT_KEYS}
                </td>
            </tr>
            <%
                String master = StaticMethod.nullObject2String(request.getAttribute("master"));
                String roleId = StaticMethod.nullObject2String(request.getAttribute("roleId"));
                String kmId = StaticMethod.nullObject2String(request.getAttribute("kmId"));
                String panels = "[{text:'审核',dataUrl:'/kmmanager/kmAuditers.do?method=xgetRoleAndSubRole&id=" + roleId + "&nodeType=role'}]";
            %>
            <c:if test="${KmContentsMap.CONTENT_STATUS == 5}">
                <tr id='userTree'>
                    <td class="label">
                        下一步审核
                    </td>
                    <td class="content" colspan="3">
                        <eoms:chooser id="test"
                                      category="[{id:'toOrg',text:'审核',limit:1}]"
                                      panels="<%=panels%>"
                        />
                        <input type="hidden" id="toOrgId" name="toOrgId" value=""/>
                        <input type="hidden" id="toOrgType" name="toOrgType" value=""/>
                    </td>
                </tr>
            </c:if>
            <c:if test="${KmContentsMap.CONTENT_STATUS == 6}">
                <%
                    if (("").equals(master)) {
                %>
                <input type="hidden" id="toOrgId" name="toOrgId" value="${KmContentsMap.CREATE_USER}"/>
                <input type="hidden" id="toOrgType" name="toOrgType" value="user"/>
                <%
                } else {
                %>
                <tr id='userTree'>
                    <td class="label">
                        下一步审核
                    </td>
                    <td class="content" colspan="3">
                        <eoms:id2nameDB id="<%=master %>" beanId="tawSystemUserDao"/>
                        <input type="hidden" id="toOrgId" name="toOrgId" value="<%=master %>"/>
                        <input type="hidden" id="toOrgType" name="toOrgType" value="user"/>
                    </td>
                </tr>
                <%
                    }
                %>
            </c:if>
            <c:if test="${KmContentsMap.CONTENT_STATUS == 7}">
                <input type="hidden" id="toOrgId" name="toOrgId" value="${KmContentsMap.CREATE_USER}"/>
                <input type="hidden" id="toOrgType" name="toOrgType" value="user"/>
            </c:if>


            <tr>
                <td class="label">
                    审核结果
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="auditResult" isQuery="false"
                               defaultId="" selectId="auditResult" beanId="selectXML"
                               alt="allowBlank:false,vtext:'请选择评论星级(字典)...'" onchange="changeState()"/>
                </td>
                <td class="label">
                    是否向下流转
                </td>
                <td class="content">
                    <c:if test="${KmContentsMap.CONTENT_STATUS == 6}">
                        是
                        <input type="hidden" id="nextStep" name="nextStep" value="1030101"/>
                    </c:if>
                    <c:if test="${KmContentsMap.CONTENT_STATUS != 6}">
                        <eoms:comboBox name="nextStep" id="nextStep"
                                       initDicId="10301" defaultValue="1030101" alt="allowBlank:false,vtext:''"
                                       styleClass="select-class" onchange="showUserTree() "/>
                    </c:if>
                </td>
            </tr>

            <tr>
                <td class="label">
                    审核意见
                </td>
                <td class="content" colspan="3">
                    <!-- property中配一个空属性 -->
                    <textarea name="remark" cols="50" id="remark" class="textarea max"></textarea>
                    <p/>
                    <input type="button" value="提交" onclick="javascript:onSubmit();" class="button"/>
                </td>
            </tr>

            <input type="hidden" id="kmId" name="TableInfo/ID" value="<%=kmId %>"/>
            <input type="hidden" id="themeId" name="TableInfo/THEME_ID" value="${KmContentsMap.THEME_ID}"/>
            <input type="hidden" id="tableId" name="TableInfo/TABLE_ID" value="${KmContentsMap.TABLE_ID}"/>
            <input type="hidden" id="contentTitle" name="TableInfo/CONTENT_TITLE"
                   value="${KmContentsMap.CONTENT_TITLE}"/>
            <input type="hidden" id="contentKeys" name="TableInfo/CONTENT_KEYS" value="${KmContentsMap.CONTENT_KEYS}"/>
            <input type="hidden" id="toOrgName" name="toOrgName" value=""/>
            <input type="hidden" id="state" name="state" value="1"/>
            <input type="hidden" id="contentState" name="contentState" value="${KmContentsMap.CONTENT_STATUS}"/>
            <input type="hidden" id="createUser" name="createUser" value="${KmContentsMap.CREATE_USER}"/>
        </table>

    </fmt:bundle>

    <br>


    </div>
</html:form>
<script>
    function showUserTree() {
        if (document.forms[0].nextStep.value == "1030101") {
            document.getElementById("userTree").style.display = "";
        } else {
            document.getElementById("userTree").style.display = "none";
        }
    }

    function changeState() {
        if (document.forms[0].auditResult.value == "0") {
            document.getElementById("state").value = "2";
        } else {
            document.getElementById("state").value = "1";
        }
        if (${KmContentsMap.CONTENT_STATUS}=="7"
    )
        {
            document.getElementById("state").value = "2";
        }
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>