<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp" %>

<%@page
        import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm;" %>
<%
    String startDate = com.boco.eoms.base.util.StaticMethod
            .getLocalString(-1);
    String endDate = com.boco.eoms.base.util.StaticMethod
            .getCurrentDateTime();

    String completeLimitstartDate = com.boco.eoms.base.util.StaticMethod
            .getLocalString(-1);
    String completeLimitendDate = com.boco.eoms.base.util.StaticMethod
            .getCurrentDateTime();
    String fileName = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getAttribute("fileName"), "");
    String areaflag = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getAttribute("areaflag"));

    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");

%>
<script type="text/javascript">

    Ext.onReady(function () {
        v = new eoms.form.Validation({form: "theform"});
    });
</script>

<form id="theform" method="post" action="http://10.25.2.113:8080/kpiDome/ExcelServlet">
    <input type="hidden" class="text" name="filename" value="<%=fileName %>">

    <table class="formTable">
        <!--  派单时间 -->
        <tr>
            <%if (fileName.equals("SQL_projectwarning")) { %>
            <td class="label">割接</td>
            <%} else { %>
            <td class="label"><bean:message bundle="sheet"
                                            key="query.sendTime"/></td>
            <%} %>
            <td width="100%"><input type="hidden" name="main.sendTime"/>
                开始时间 <input type="text"
                            name="sendTimeStartDate"
                            onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                            readonly="true" class="text" value="<%=startDate %>"/> &nbsp;&nbsp;
                结束时间 <input type="text" name="sendTimeEndDate"
                            id="sendTimeEndDate"
                            onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
                            value="<%=endDate %>" readonly="true" class="text"/>
                </div>
            </td>
        </tr>

        <%if (fileName.equals("SQL_complaintDuban")) { %>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="query.completeLimit"/><font color="red">*该条件只对投诉督办工单详情导出有效</font>
            </td>

            <td width="100%"><input type="hidden" name="main.completeLimit"/>
                开始时间 <input type="text"
                            name="completeLimitStartDate"
                            onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                            readonly="true" class="text" value="<%=completeLimitstartDate %>"/>
                &nbsp;&nbsp; 结束时间 <input type="text"
                                         name="completeLimitEndDate" id="sendTimeEndDate"
                                         onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt=""
                                         value="<%=completeLimitendDate %>" readonly="true" class="text"/>
                </div>
            </td>
        </tr>
        <%} %>

        <%if (!fileName.equals("SQL_projectwarning")) { %>
        <tr>
            <td width="15%" class="label">处理部门</td>
            <td width="70%"><c:set var="id">
                <%=sessionform.getDeptid()%>
            </c:set> <eoms:chooser id="test2"
                                   category="[{id:'deptid',text:'处理部门',allowBlank:true,vtext:'请选择处理部门'}]"
                                   data="[
					{id:'${id}',nodeType:'dept',categoryId:'deptid'}
				]"/></td>
        </tr>
        <tr>
            <td width="15%" class="label">T1处理人</td>
            <td width="70%"><c:set var="usersid">admin</c:set> <eoms:chooser
                    id="test3"
                    category="[{id:'userid',text:'选择',allowBlank:true,vtext:'请联系T1处理人'}]"
                    data="[
					{id:'${usersid}',nodeType:'user',categoryId:'userId'}
				]"/></td>
        </tr>
        <tr>
            <td width="15%" class="label">工单状态</td>
            <td width="70%"><select type="select" name="status" id="type">
                <option value="">全部</option>
                <option value="0">未归档</option>
                <option value="1">已归档</option>
            </select></td>
        </tr>

        <%if ("1".equals(areaflag)) {%>
        <!-- 所属地域 -->
        <tr>
            <td class="label"><bean:message bundle="sheet"
                                            key="query.status.area"/></td>
            <td width="100%"><input type="hidden"
                                    name="toDeptIdStringExpression" value="in"/> <input type="text"
                                                                                        class="text" readonly="readonly"
                                                                                        name="showArea" id="showArea"
                                                                                        beanId="tawSystemAreaDao"/>
                <input type="hidden"
                       name="toDeptId" id="toAreaId"/></td>
        </tr>
        <script>
            function changeAreaId() {
                var toAreaId = document.getElementById("toAreaId").value;
                if ('' != toAreaId) {
                    var toAreaIdArray = toAreaId.split(',');
                    var toAreaIdValue = "";
                    for (var i = 0; i < toAreaIdArray.length; i++) {
                        toAreaIdValue = toAreaIdValue + "'" + toAreaIdArray[i] + "',";
                    }
                    toAreaIdValue = toAreaIdValue.substring(0, toAreaIdValue.length - 1);
                    document.getElementById("toAreaId").value = toAreaIdValue;
                }
            }
        </script>
        <%} %>

        <tr>
            <td width="15%" class="label">工单级别</td>
            <td width="70%"><eoms:comboBox name="systype" id="a1" sub="a2"
                                           initDicId="1010304"/></td>
        </tr>
        <!-- ?????? -->
        <%if (filename.indexOf("commonfault") != -1) {%>
        <tr>
            <td class="label"><bean:message bundle="sheet"
                                            key="query.status.mainNetTypeOne"/></td>
            <td><input type="hidden" name="main.mainNetSortOne"/> <eoms:comboBox
                    name="mainNetSortOneChoiceExpression" id="mainNetSortOne"
                    sub="mainNetSortTwo" initDicId="1010104"/></td>
        </tr>
        <!-- ?????? -->
        <tr>
            <td class="label"><bean:message bundle="sheet"
                                            key="query.status.mainNetTypeTwo"/></td>
            <td><input type="hidden" name="main.mainNetSortTwo"/> <eoms:comboBox
                    name="mainNetSortTwoChoiceExpression" id="mainNetSortTwo"
                    sub="mainNetSortThree"/></td>
        </tr>
        <!-- ?????? -->
        <tr>
            <td class="label"><bean:message bundle="sheet"
                                            key="query.status.mainNetTypeThree"/></td>
            <td><input type="hidden" name="main.mainNetSortThree"/> <eoms:comboBox
                    name="mainNetSortThreeChoiceExpression" id="mainNetSortThree"/></td>
        </tr>

        <%
                }
            }
        %>


        <% if (fileName.equals("SQL")) { %>
        <tr>
            <td width="15%" class="label">驳回状态</td>
            <td width="70%"><select type="select" name="operatetype" id="type">
                <option value="">全部</option>
                <option value="17">驳回</option>
                <option value="99">未驳回</option>
            </select></td>
        </tr>
        <%}%>
    </table>
    <input type="submit" name="method.save" id="method.save" onclick="javascript:changeAreaId();" class="btn"/>


</form>
<div ID="idSpecial"></div>
<%@ include file="/WEB-INF/pages/wfworksheet/common/commonqueryJs.jsp" %>
<%@ include file="/common/footer_eoms.jsp" %>
