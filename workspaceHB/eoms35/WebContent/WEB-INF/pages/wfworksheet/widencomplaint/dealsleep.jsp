<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    var v;
    Ext.onReady(function () {
        var v = new eoms.form.Validation({form: 'theform'});
        v.preCommitUrl = "${app}/sheet/widencomplaint/widencomplaint.do?method=performSleepPreCommit";
    });
</script>

<div id="sheetform">
    <html:form action="/widencomplaint.do?method=dealAsleepSheet" styleId="theform">
        <!-- base info -->
        <input type="hidden" name="sheetKey" value="${sheetKey}"/>
        <input type="hidden" name="TKID" value="${TKID}"/>
        <table class="formTable">
            <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateUserName"/>*
                    <input type="hidden" name="${sheetPageName}userId" value="${userId}"/>
                </td>
                <td class="content">
                        ${userName }&nbsp;
                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateDeptName"/>*
                    <input type="hidden" name="${sheetPageName}deptId" value="${deptId}"/>
                </td>
                <td class="content">
                        ${deptName }&nbsp;
                </td>
            </tr>

            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operaterContact"/>*
                </td>
                <td class="content" colspan="3">
                    <input type="text" class="text" name="${sheetPageName}operaterContact"
                           id="${sheetPageName}operaterContact" value="${operaterContact}" alt="allowBlank:false"/>
                </td>
            </tr>
        </table>

        <br/>

        <table class="listTable">
            <tr>
                <td class="label">
                    申请人
                </td>
                <td class="content">
                    <eoms:id2nameDB id="${SleepUser}" beanId="tawSystemUserDao"/>
                </td>
                <td class="label">
                    休眠时长
                </td>
                <td class="content">
                        ${sleepTime }天
                </td>
            </tr>
            </tr>
            <tr>
                <td class="label">
                    申请原因
                </td>
                <td colspan="3" class="content">
                        ${sleepReason }
                </td>
            </tr>

            <tr>
                <td class="label">
                    休眠审批
                </td>
                <td colspan="3" class="content">
                    <select id="sleepApply" name="sleepApply" alt="allowBlank:false">
                        <option value="">请选择</option>
                        <option value="yes">通过</option>
                        <option value="no">不通过</option>
                    </select>
                </td>
            </tr>
        </table>

        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>"/>
        </div>
    </html:form>

</div>

