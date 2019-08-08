<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    var v;
    Ext.onReady(function () {

        v = new eoms.form.Validation({form: 'theform'});
    });

</script>

<div id="sheetform">
    <html:form action="/plannadjust.do?method=searchNumber" styleId="theform">
        <table class="formTable">
            <!-- 派单时间 -->
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="query.sendTime"/>
                </td>
                <td width="100%">
                    <bean:message bundle="sheet" key="worksheet.query.startDate"/>
                    <input type="text" class="text" name="sendTimeStartDate" readonly="readonly"
                           id="sendTimeStartDate" value=""
                           onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                           alt="vtype:'lessThen',link:'sendTimeEndDate',vtext:'开始时间不能晚于结束时间',allowBlank:false"/> &nbsp;&nbsp;
                    <bean:message bundle="sheet" key="worksheet.query.endDate"/>
                    <input type="text" class="text" name="sendTimeEndDate" readonly="readonly"
                           id="sendTimeEndDate" value=""
                           onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                           alt="vtype:'moreThen',link:'sendTimeStartDate',vtext:'结束时间不能早于开始时间',allowBlank:false"/>
                </td>
            </tr>
            <!-- 统计类型  -->
            <tr>
                <td class="label">
                    统计类型
                </td>
                <td class="content">
                    <select name="queryType">
                        <option value="wholenet">全网</option>
                        <option value="territorialBranch">属地分公司</option>
                    </select>
                </td>
            </tr>
        </table>
        <!-- buttons -->
        <div class="form-btns">
            <html:submit styleClass="btn" property="method.save" styleId="method.save">
                <fmt:message key="button.done"/>
            </html:submit>
        </div>
    </html:form>
</div>
<%@ include file="/common/footer_eoms.jsp" %>