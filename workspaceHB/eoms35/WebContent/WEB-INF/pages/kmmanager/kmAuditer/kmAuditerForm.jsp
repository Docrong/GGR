<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<script type="text/javascript" src="${app}/scripts/kmmanager/adapter-km.js"></script>

<%
    String auditType = StaticMethod.nullObject2String(request.getAttribute("auditType"));
    if (auditType.equals("content")) {
%>
<script type="text/javascript">
    var tree;
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'kmAuditerForm'});

    });


    function onTableChg(table) {
        var selValue = table.options[table.options.selectedIndex].value;
        var url = '${app}/kmmanager/kmAuditers.do?method=add&TABLE_ID=' + selValue;
        location.href = url;
    }

</script>
<%
    }
%>
<html:form action="/kmAuditers.do?method=save" styleId="kmAuditerForm" method="post" onsubmit="return doConfirm()">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

        <table class="formTable">
            <caption>
                <div class="header center">审核人配置新增</div>
            </caption>


            <%
                if (auditType.equals("content")) {
            %>
            <tr>
                <td class="label">
                    <fmt:message key="kmContents.tableId"/>
                </td>
                <td class="content">

                    <c:if test="${kmAuditerForm.tableId == null}">
                        <eoms:id2nameDB id="${tableId}" beanId="kmTableGeneralDao"/>
                        <input type="hidden" id="tableId" name="tableId" value="${tableId}"/>
                    </c:if>
                    <c:if test="${kmAuditerForm.tableId != null}">
                        <eoms:id2nameDB id="${kmAuditerForm.tableId}" beanId="kmTableGeneralDao"/>
                        <input type="hidden" id="tableId" name="tableId" value="${kmAuditerForm.tableId}"/>
                    </c:if>
                </td>

                <td class="label">
                    <fmt:message key="kmContents.themeId"/>
                </td>
                <td class="content">
                    <c:if test="${kmAuditerForm.themeId == null}">
                        <eoms:id2nameDB id="${nodeId}" beanId="kmTableThemeDao"/>
                        <input type="hidden" id="themeId" name="themeId" value="${nodeId}"/>
                    </c:if>
                    <c:if test="${kmAuditerForm.themeId != null}">
                        <eoms:id2nameDB id="${kmAuditerForm.themeId}" beanId="kmTableThemeDao"/>
                        <input type="hidden" id="themeId" name="themeId" value="${kmAuditerForm.themeId}"/>
                    </c:if>
                </td>
            </tr>

            <%
            } else if (auditType.equals("file")) {
            %>
            <td class="label">
                文件分类
            </td>
            <td class="content" colspan="3">
                <c:if test="${kmAuditerForm.nodeId == null}">
                    <eoms:id2nameDB id="${nodeId}" beanId="kmFileTreeDao"/>
                    <input type="hidden" id="nodeId" name="nodeId" value="${nodeId}"/>
                </c:if>
                <c:if test="${kmAuditerForm.nodeId != null}">
                    <eoms:id2nameDB id="${kmAuditerForm.nodeId}" beanId="kmFileTreeDao"/>
                    <input type="hidden" id="nodeId" name="nodeId" value="${kmAuditerForm.nodeId}"/>
                </c:if>
            </td>
            </tr>
            <%
                }
            %>


            <tr>
                <td class="label">
                    <fmt:message key="kmAuditer.roleId"/>
                </td>
                <td class="content">
                    <input type="button" name="rolet" id="rolet" value="请选择角色" class="btn"/>
                    <eoms:xbox id="role"
                               dataUrl="${app}/kmmanager/kmAuditers.do?method=getRoleTreeNodes&id=1&nodeType=role"
                               rootId="-1" single='true' rootText="选择角色" valueField="roleId" handler="rolet"
                               textField="roleName" viewer="true" mode="" checktype="subrole"
                               data="${kmAuditerForm.roleIdData}"
                    ></eoms:xbox>
                    <input type="hidden" id="roleName" name="roleName" value=""/>
                    <input type="hidden" id="roleId" name="roleId" value="${kmAuditerForm.roleId}"/>
                    <input type="hidden" id="roleType" name="roleType" value="subrole"/>
                </td>

                <td class="label">
                    <fmt:message key="kmAuditer.master"/>
                </td>
                <td class="content">
                    <input type="button" name="mastert" id="mastert" value="请选择主管" class="btn"/>
                    <eoms:xbox id="master" dataUrl="${app}/xtree.do?method=userFromDept" rootId="-1" single='true'
                               rootText="选择主管" valueField="masterId" handler="mastert"
                               textField="masterName" viewer="true" mode="" checktype="user"
                               data="${kmAuditerForm.masterIdData}"
                    ></eoms:xbox>
                    <input type="hidden" id="masterName" name="masterName" value=""/>
                    <input type="hidden" id="masterId" name="masterId" value="${kmAuditerForm.masterId}"/>
                    <input type="hidden" id="masterType" name="masterType" value="user"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmAuditer.masterAudit"/>
                </td>
                <td class="content">

                    <eoms:comboBox name="masterAudit" id="masterAudit"
                                   initDicId="10301" defaultValue="${kmAuditerForm.masterAudit}"
                                   styleClass="select-class"/>

                </td>
                <td class="label">
                    <fmt:message key="kmAuditer.isSign"/>
                </td>
                <td class="content">

                    <eoms:comboBox name="isSign" id="isSign"
                                   initDicId="10301" defaultValue="${kmAuditerForm.isSign}" styleClass="select-class"/>

                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmAuditer.remark"/>
                </td>
                <td class="content" colspan="3">
                    <textarea name="remark" id="remark" class="textarea max">${kmAuditerForm.remark}</textarea>
                </td>
            </tr>


        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${kmAuditerForm.id}"/>
    <html:hidden property="auditType" value="${auditType}"/>
    <input type="hidden" id="toOrgType" name="toOrgType" value=""/>
</html:form>
<script type="text/javascript">
    function doConfirm() {
        var roleId = document.getElementById("roleId").value;
        if (roleId == "") {
            alert("请选择审核角色");
            return false
        }
        var masterId = document.getElementById("masterId").value;
        if (masterId == "") {
            alert("请选择审核主管");
            return false
        }
        var masterAudit = document.getElementById("masterAudit").value;
        if (masterAudit == "") {
            alert("请确定是否需要主管审核");
            return false
        }
        var isSign = document.getElementById("isSign").value;
        if (isSign == "") {
            alert("请确定是否需要会签");
            return false
        }
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>