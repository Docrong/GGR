<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.boco.eoms.km.table.model.KmTableColumn" %>
<%
    List tmp = (List) request.getAttribute("KmTableColumnList");
    if (tmp != null && tmp.size() > 0) {
        String appPath = request.getContextPath();
        String html = "<table name='imgtable' class='imgtable'>" +
                "<tr><td></td><td><img src='" + appPath + "/scripts/widgets/formdesigner/assets/up.gif'onclick='moveUporDown(this,0);'/></td><td></td>" +
                "_flag_</tr>" +
                "<tr><td><img src='" + appPath + "/scripts/widgets/formdesigner/assets/left.gif' onclick=\"move(this,'r');\"/></td>" +
                "<td><img src='" + appPath + "/scripts/widgets/formdesigner/assets/down.gif'onclick='moveUporDown(this,1);'/></td>" +
                "<td><img src='" + appPath + "/scripts/widgets/formdesigner/assets/right.gif' onclick=\"move(this,'l');\"/></td>" +
                "</tr></table>";
        String steerer_blank = html.replace("_flag_", "");
        String steerer = html.replace("_flag_",
                "<td rowspan=2><input type='button' onclick='changeLineType(this);' class='button' value='整行/半行' name='changeBtn'/></td>");
%>
<style type="text/css">
    .imgtable {
        width: 30px;
        bordor: 0;
    }

    .imgtable td {
        background-color: #EDF5FD;
        margin: 0;
        padding: 3px;
    }
</style>
<form id="adjustForm" method="post" action="${app}/kmmanager/kmTableColumns.do?method=saveLayout">
    <table class="formTable" id="adjustTable">
        <%--
            by mios:
            mark有4个状态： 0：半行， 1：整行， 2：左半行，右为空行， 3：右半行，左为空行,通过mark控制是否输出空列
            flag用来控制是否换行，每输出两列后，flag+1
        --%>

        <tr>
            <c:set var="flag" value="${0}"/>
            <c:forEach items="${KmTableColumnList}" var="item" varStatus="status">

            <c:if test="${item.mark == 3}"> <%-- 有右半行，填充左半行为空行--%>
                <td class="label"><%=steerer_blank%>
                </td>
                <td class="content" role="4"></td>
                <c:set var="flag" value="${flag+1}"/>
            </c:if>

            <c:if test="${item.isOpen == 1}">
                <td class="label">
                        ${item.colChname}<%=steerer%>
                    <c:if test="${item.isNullable == 0}">&nbsp;<font color='red'>*</font></c:if>
                </td>
                <td class="content"
                    <c:if test="${item.mark == 1}">colspan="3"</c:if> role="${item.mark}">

                    <c:choose>
                        <c:when test="${item.colDictType == 0}">
                            <c:choose>
                                <c:when test="${item.colType == 1}">
                                    <%-- 不绑定_普通文本 --%>
                                    <input type="text" name="TableInfo/${item.id}" id="${item.id}"
                                           value="${item.colDefault}" maxLength="${item.colSize}" class="text medium"
                                           alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'${item.colChname}'"/>
                                </c:when>

                                <c:when test="${item.colType == 2}">
                                    <%-- 不绑定_大文本域 --%>
                                    <textarea name="TableInfo/${item.id}" id="${item.id}"
                                              cols="50" class="textarea max"
                                              alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请输入${item.colChname}...'"
                                              type="_moz">${item.colDefault}</textarea>
                                </c:when>

                                <c:when test="${item.colType == 3}">
                                    <%-- 不绑定_数字类型 --%>
                                    <input type="text" name="TableInfo/${item.id}" id="${item.id}"
                                           value="${item.colDefault}" class="text medium"
                                           alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请输入${item.colChname}(正整数类型)...',vtype:'number'"/>
                                </c:when>

                                <c:when test="${item.colType == 4}">
                                    <%-- 不绑定_日期时间 --%>
                                    <input type="text" name="TableInfo/${item.id}" id="${item.id}"
                                           value="${item.colDefault}" size="20"
                                           onclick="popUpCalendar(this, this,null,null,null,true,-1);" readonly="true"
                                           class="text"
                                           alt="allowBlank:<c:if test="${item.isNullable == 1}">true</c:if><c:if test="${item.isNullable == 0}">false</c:if>,vtext:'请选择${item.colChname}...'"/>
                                </c:when>

                                <c:when test="${item.colType == 7}">
                                    <%-- 不绑定_附件上传 --%>
                                    <eoms:attachment name="" property="" scope="request" idField="TableInfo/${item.id}"
                                                     appCode="kmmanager" startsWith="0"/>
                                </c:when>
                            </c:choose>
                        </c:when>

                        <c:when test="${item.colDictType == 1}">
                            <c:choose>
                                <c:when test="${item.colType == 5}">
                                    <%-- 普通字典_单选字段 --%>
                                    <eoms:comboBox name="TableInfo/${item.id}" id="${item.id}"
                                                   initDicId="${item.colDictId}" defaultValue=""
                                                   alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
                                </c:when>

                                <c:when test="${item.colType == 6}">
                                    <%-- 普通字典_多选字段 --%>
                                    <eoms:comboBox name="TableInfo/${item.id}" id="${item.id}"
                                                   initDicId="${item.colDictId}" defaultValue=""
                                                   alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
                                </c:when>
                            </c:choose>
                        </c:when>

                        <c:when test="${item.colDictType == 2}">
                            <c:choose>
                                <c:when test="${item.colType == 5}">
                                    <%-- 知识字典_单选字段 --%>
                                    <eoms:comboBox name="TableInfo/${item.id}" id="${item.id}"
                                                   initDicId="${item.colDictId}" defaultValue=""
                                                   alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
                                </c:when>

                                <c:when test="${item.colType == 6}">
                                    <%-- 知识字典_多选字段 --%>
                                    <eoms:comboBox name="TableInfo/${item.id}" id="${item.id}"
                                                   initDicId="${item.colDictId}" defaultValue=""
                                                   alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
                                </c:when>
                            </c:choose>
                        </c:when>

                        <c:when test="${item.colDictType == 3}">
                            <c:choose>
                                <c:when test="${item.colType == 5}">
                                    <%-- 文件字典_单选字段 --%>
                                    <eoms:comboBox name="TableInfo/${item.id}" id="${item.id}"
                                                   initDicId="${item.colDictId}" defaultValue=""
                                                   alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
                                </c:when>

                                <c:when test="${item.colType == 6}">
                                    <%-- 文件字典_多选字段 --%>
                                    <eoms:comboBox name="TableInfo/${item.id}" id="${item.id}"
                                                   initDicId="${item.colDictId}" defaultValue=""
                                                   alt="allowBlank:false,vtext:'请选择${item.colChname}(单选字典)...'"/>
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
                <td class="label"><%=steerer_blank%>
                </td>
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
    </table>
    <input type="hidden" id="result"/>
    <br/>
    <input type='button' id="showRes" value='确定样式' class="button" onclick='save();'/>
    <input type='button' id="kill" value='删除空行' class="button" onclick='killBlank();'/>
    <input type='button' id="preview" value='预览' class="button" onclick='preView();'/>
    <input type="hidden" id="result"/>
    <input type="hidden" id="TABLE_ID" value="${TABLE_ID}"/>
</form>
<script type="text/javascript" src="${app}/scripts/widgets/formdesigner/formdesigner.js"></script>
<%
} else {
%>
请定义模型字段！
<%
    }
%>
