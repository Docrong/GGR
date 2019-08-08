<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<script type="text/javascript">
    function check() {

        var sleepTime = document.getElementById("sleepTime").value;
        var sleepReason = document.getElementById("sleepReason").value;
        if (sleepTime == '' || sleepReason == '') {
            alert("请检查表单数据完整");
            return false;
        }
    }
</script>

<div id="sheetform">
    <html:form action="/widencomplaint.do?method=performAsleepOp" styleId="theform">
        <!-- base info -->
        <input type="hidden" name="sheetKey" value="${sheetKey}"/>
        <input type="hidden" name="TKID" value="${TKID}"/>
        <input type="hidden" name="taskStatus" value="${taskStatus}"/>
        <table class="formTable">

            <caption><bean:message bundle="sheet" key="linkForm.header"/></caption>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateUserName"/>
                    <input type="hidden" name="${sheetPageName}userId" value="${userId}"/>
                </td>
                <td class="content">
                        ${userName }&nbsp;
                </td>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operateDeptName"/>
                    <input type="hidden" name="${sheetPageName}deptId" value="${deptId}"/>
                </td>
                <td class="content">
                        ${deptName }&nbsp;
                </td>
            </tr>

            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.operaterContact"/>
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
                    休眠时长
                </td>
                <td class="content">
                    <select id="${sheetPageName}sleepTime" name="${sheetPageName}sleepTime" alt="allowBlank:false">
                        <option value="">请选择</option>
                        <option value="1">1天</option>
                        <option value="2">2天</option>
                        <option value="3">3天</option>
                        <option value="4">4天</option>
                        <option value="5">5天</option>
                        <option value="6">6天</option>
                        <option value="7">7天</option>
                        <option value="15">15天</option>
                        <option value="30">30天</option>
                        <option value="60">60天</option>
                        <option value="90">90天</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="label">
                    申请原因
                </td>
                <td>
                    <input type="text" class="text max" name="${sheetPageName}sleepReason" maxlength="100"
                           id="${sheetPageName}sleepReason" alt="allowBlank:false"/>
                </td>
            </tr>
        </table>

        <div class="form-btns">
            <input type="submit" class="submit" name="method.save" id="method.save"
                   value="<bean:message bundle='sheet' key='button.done'/>" onclick="return check();"/>
        </div>
    </html:form>

</div>

