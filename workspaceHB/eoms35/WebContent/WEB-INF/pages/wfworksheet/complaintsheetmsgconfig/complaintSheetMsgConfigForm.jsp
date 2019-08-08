<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'complaintSheetMsgConfigForm'});
        v.preCommitUrl = "${app}/sheet/complaintSheetMsgConfig/complaintSheetMsgConfigs.do?method=performPreCommit";
    });

    function cancel() {
        var url = '${app}/sheet/complaintSheetMsgConfig/complaintSheetMsgConfigs.do?method=search';
        location.href = url;
    }
</script>
<html:form action="/complaintSheetMsgConfigs.do?method=save" styleId="complaintSheetMsgConfigForm" method="post">

    <table class="formTable">
        <caption>
            <div class="header center">投诉工单短信派发配置</div>
        </caption>
        <input type="hidden" name="id" id="id" value="${complaintSheetMsgConfigForm.id}"/>
        <input type="hidden" name="userId" id="userId" value="${userId}"/>
        <tr>
            <td class="label">
                故障地市
            </td>

            <td class="content" id="commonfaultarea">
                <input type="text" class="text" readonly="readonly" name="showDept" id="showDept"
                       alt="allowBlank:false,vtext:'请选择地域名称'"
                       value="<eoms:id2nameDB id='${complaintSheetMsgConfigForm.areaId}' beanId='tawSystemAreaDao'/>"/>
                <input type="hidden" name="areaId" id="areaId" value="${complaintSheetMsgConfigForm.areaId}"/>
                <eoms:xbox id="areaTree" dataUrl="${app}/xtree.do?method=areaTree" rootId="18" rootText="地域树"
                           valueField="areaId" handler="commonfaultarea" textField="showDept" single="true"
                           data="${area}">
                </eoms:xbox>
            </td>
        </tr>

        <tr>
            <td class="label">
                投诉分类
            </td>
            <td class="content">
                <eoms:comboBox name="complaintType" id="complaintType" initDicId="10301"
                               defaultValue="${complaintSheetMsgConfigForm.complaintType}"
                               alt="allowBlank:false,vtext:'请选择投诉分类'"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                需通知的人员
            </td>
            <td class="content" id="chooseUser">
                <input type="text" class="text" readonly="readonly" name="showUser" id="showUser"
                       alt="allowBlank:false,vtext:'请选择人员'"
                       value="<eoms:id2nameDB id='${complaintSheetMsgConfigForm.notifyUserIds}' beanId='tawSystemUserDao'/>"/>
                <input type="hidden" name="notifyUserIds" id="notifyUserIds"
                       value="${complaintSheetMsgConfigForm.notifyUserIds}"/>
                <eoms:xbox id="userTree" dataUrl="${app}/xtree.do?method=userFromDept" rootId="-1" rootText="用户树"
                           valueField="notifyUserIds" handler="chooseUser" textField="showUser" checktype="user"
                           data="${user}">
                </eoms:xbox>
            </td>
        </tr>

    </table>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty complaintSheetMsgConfigForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('confirm?')){
                                   var url='${app}/complaintSheetMsgConfig/complaintSheetMsgConfigs.do?method=remove&id=${complaintSheetMsgConfigForm.id}';
                                   location.href=url}"/>
                </c:if>
                <input type="button" class="btn" value="返回"
                       onclick="javascript:cancel()"/>
            </td>
        </tr>
    </table>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>