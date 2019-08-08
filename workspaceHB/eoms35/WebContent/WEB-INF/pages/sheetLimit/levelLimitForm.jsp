<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.sheet.limit.util.TimeFilter" %>
<%@ page import="com.boco.eoms.sheet.limit.model.StepLimit" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<script type="text/javascript">
    var v;
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'levelLimitForm'});
    });

    function numStrCheck(Str) {
        var tmplimit = Str;
        var i, j, strTemp;
        var strTemp = "0123456789";
        for (i = 0; i < Str.length; i++) {
            if (tmplimit.substring(0, 1) == '0' && tmplimit.length > 1) {
                tmplimit = tmplimit.substring(1);
            } else {
                Str = tmplimit;
                if (tmplimit == '0') {
                    alert("请在时限中输入一个大于0的整数!");
                    return 'NoNum';
                } else {
                    j = strTemp.indexOf(tmplimit.charAt(i));
                    if (j == -1) {
                        alert("请在时限中输入一个大于0的整数!");
                        return 'NoNum';
                    }
                }
            }
        }
        return Str;
    }

    function limitCheck() {
        var limit = document.getElementById("acceptLimit").value;
        var tmplimit = numStrCheck(limit);
        if (tmplimit == 'NoNum' || tmplimit == '0') {
            return false;
        }
        document.getElementById("acceptLimit").value = tmplimit;

        limit = document.getElementById("replyLimit").value;
        var tmplimit = numStrCheck(limit);
        if (tmplimit == 'NoNum' || tmplimit == '0') {
            return false;
        }
        document.getElementById("replyLimit").value = tmplimit;
        if (tmplimit - document.getElementById("acceptLimit").value < 0) {
            alert("受理时限不能晚于处理时限!");
            return false;
        }
        var limitreturn = true;
        limitreturn = checksteplimit(tmplimit);
        if (limitreturn == true) {
            document.forms[0].action = "${app}/sheet/newSheetLimit/sheetLimit.do?method=saveLevelLimit";
        } else {
            return false;
        }
        return true;
    }

    function checksteplimit(tmplimit) {
        var sIds = document.getElementsByName("sId");
        var stepIds = document.getElementsByName("stepId");
        var taskNames = document.getElementsByName("taskName");
        var taskCnNames = document.getElementsByName("taskCnName");
        var sacceptLimits = document.getElementsByName("sacceptLimit");
        var completeLimits = document.getElementsByName("completeLimit");

        var tmpids = "";
        var tmpstepIds = "";
        var tmptaskNames = "";
        var tmptaskCnNames = "";
        var stmpacceptLimits = "";
        var tmpcompleteLimits = "";
        var i;
        var totlelimit = 0;
        for (i = 0; i < sIds.length; i++) {
            tmpids += sIds[i].value + ",";
            tmpstepIds += stepIds[i].value + ",";
            tmptaskNames += taskNames[i].value + ",";
            tmptaskCnNames += taskCnNames[i].value + ",";
            tmpcompleteLimits += completeLimits[i].value + ",";
            stmpacceptLimits += sacceptLimits[i].value + ",";
            var tmpsteplimit = numStrCheck(completeLimits[i].value);
            if (tmpsteplimit == 'NoNum' || tmpsteplimit == '0') {
                return false;
            }
            var tmpsteplimit1 = numStrCheck(sacceptLimits[i].value);
            if (tmpsteplimit1 == 'NoNum' || tmpsteplimit1 == '0') {
                return false;
            }

            if (tmpsteplimit1 - tmpsteplimit > 0) {
                alert("环节受理时限不能晚于处理时限！");
                return false;
            }
            totlelimit = parseInt(totlelimit) + parseInt(completeLimits[i].value);
        }
        if (parseInt(tmplimit) != totlelimit) {
            alert("环节时限的总和应该等于工单处理时限！");
            return false;
        }
        document.getElementById("ids").value = tmpids;
        document.getElementById("stepIds").value = tmpstepIds;
        document.getElementById("taskNames").value = tmptaskNames;
        document.getElementById("taskCnNames").value = tmptaskCnNames;
        document.getElementById("sacceptLimits").value = stmpacceptLimits;
        document.getElementById("completeLimits").value = tmpcompleteLimits;
        return true;
    }


    function deleteCheck() {
        var s = confirm("删除该分类的时限将同时删除该分类下的所有步骤的时限，请确认！");
        if (s) {
            document.forms[0].action = "${app}/sheet/newSheetLimit/sheetLimit.do?method=deleteLevelLimit&id=${levelLimit.id}";
            return true;
        } else {
            return false;
        }
    }

    function setStepTime(acceptlimit) {
        var sacceptLimits = document.getElementsByName("sacceptLimit");
        sacceptLimits[0].value = acceptlimit;
    }
</script>
<title><bean:message bundle="sheetLimit" key="sheetLimit.title"/></title>


<html:form action="sheetLimit.do" method="post" styleId="levelLimitForm">
    <table class="formTable">
        <caption>工单时限配置</caption>
        <!-- 分类 -->
        <c:if test="${!empty columnMap}">
            <%
                HashMap columnMap = (HashMap) request.getAttribute("columnMap");
                HashMap htmlMap = (HashMap) request.getAttribute("htmlMap");
                List list = (List) request.getAttribute("columnList");
                HashMap defaultValueMap = (HashMap) request.getAttribute("defaultValue");
                for (int i = 0; i < list.size(); i++) {
                    String key = (String) list.get(i);
                    String columnCnName = (String) columnMap.get(key);
                    TimeFilter filter = (TimeFilter) htmlMap.get(key);
            %>
            <tr>
                <td class="label">
                    <%=(String) columnMap.get(key) %>
                </td>
                <c:if test="${type=='add'}">
                    <td>
                        <%
                            if (filter.getHtmlType().equals("dict")) {
                                if (key.equals("level1") || key.equals("specialty1")) {
                        %>
                        <eoms:comboBox
                                name="<%=key %>"
                                id="<%=key %>"
                                defaultValue="<%=(String)defaultValueMap.get(key) %>"
                                initDicId="<%=filter.getDictId() %>"
                                sub="<%=filter.getSub() %>"
                                size="<%=filter.getSize() %>"
                                alt="allowBlank:false"
                                styleClass="select-class"/>
                        <%} else { %>
                        <eoms:comboBox
                                name="<%=key %>"
                                id="<%=key %>"
                                defaultValue="<%=(String)defaultValueMap.get(key) %>"
                                initDicId="<%=filter.getDictId() %>"
                                sub="<%=filter.getSub() %>"
                                size="<%=filter.getSize() %>"
                                alt="allowBlank:true"
                                styleClass="select-class"/>
                        <%
                                }
                            }
                        %>
                        <%if (filter.getHtmlType().equals("text")) {%>
                        <input
                                type="text"
                                class="text"
                                name="<%=key %>"
                                id="<%=key %>"
                                value="<%=(String)defaultValueMap.get(key) %>"
                                alt="allowBlank:false,maxLength:25"/>
                        <%} %>
                    </td>
                </c:if>
                <c:if test="${type=='modify'}">
                    <td>
                        <%
                            if (filter.getHtmlType().equals("dict")) {
                                if ((String) defaultValueMap.get(key) != null && !((String) defaultValueMap.get(key)).equals("")) {
                        %>
                        <eoms:id2nameDB id="<%=(String)defaultValueMap.get(key) %>" beanId="ItawSystemDictTypeDao"/>
                        <%
                        } else {

                        %>
                        全部
                        <%
                                }
                            } %>
                        <%if (filter.getHtmlType().equals("text")) {%>
                        <bean:write name="levelLimit" property="<%=key %>" scope="page"/>
                        <%} %>
                    </td>
                    <input type="hidden" name="<%=key %>"
                           value="<%=StaticMethod.nullObject2String(defaultValueMap.get(key)) %>"/>
                </c:if>
            </tr>
            <%} %>
        </c:if>
        <tr>
            <td class="label">
                <bean:message bundle="sheetLimit" key="sheetLimit.acceptLimit"/>
            </td>
            <td class="content">
                <c:if test="${empty addStep}">
                    <html:text property="acceptLimit" styleId="acceptLimit" styleClass="text medium"
                               alt="allowBlank:false,maxLength:9,vtext:'请填入受理时限，最多输入9个字符'"
                               value="${levelLimit.acceptLimit}" onchange="setStepTime(this.value);"/>
                </c:if>
                <c:if test="${addStep=='true'}">
                    ${levelLimit.acceptLimit}
                    <input type="hidden" id="acceptLimit" name="acceptLimit" value="${levelLimit.acceptLimit}"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="sheetLimit" key="sheetLimit.replyLimit"/>
            </td>
            <td class="content">
                <c:if test="${empty addStep}">
                    <html:text property="replyLimit" styleId="replyLimit" styleClass="text medium"
                               alt="allowBlank:false,maxLength:9,vtext:'请填入处理时限，最多输入9个字符'"
                               value="${levelLimit.replyLimit}"/>
                </c:if>
                <c:if test="${addStep=='true'}">
                    ${levelLimit.replyLimit}
                    <input type="hidden" id="replyLimit" name="replyLimit" value="${levelLimit.replyLimit}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <input type="hidden" name="flowName" value="${levelLimit.flowName }"/>
    <input type="hidden" name="id" value="${levelLimit.id }"/>
    <input type="hidden" name="type" value="${type}"/>
    <br>
    <table class="formTable">
        <caption>工单环节时限</caption>
        <%
            HashMap phaseIdMap = (HashMap) request.getAttribute("phaseIdMap");
            List phaseIdList = (List) request.getAttribute("phaseIdList");
            HashMap stepDefaultValue = (HashMap) request.getAttribute("stepDefaultValue");
            if (phaseIdList.size() > 0) {
        %>
        <thead>
        <tr>
            <td class="label">步骤名</td>
            <td class="label">受理时限</td>
            <td class="label">处理时限</td>
        </tr>
        </thead>
        <%
            int tmpint = 0;
            for (int i = 0; i < phaseIdList.size(); i++) {
                String tmpPhaseId = (String) phaseIdList.get(i);
                String tmpTaskName = (String) phaseIdMap.get(tmpPhaseId);
                StepLimit tmpStepLimit = (StepLimit) stepDefaultValue.get(tmpPhaseId);
                if (tmpPhaseId.toLowerCase().indexOf("draft") == -1 && tmpPhaseId.toLowerCase().indexOf("reject") == -1 && tmpPhaseId.toLowerCase().indexOf("back") == -1 && tmpPhaseId.toLowerCase().indexOf("hold") == -1) {
        %>
        <tr>
            <td class="label">
                <%=tmpTaskName %>时限(单位：分钟)
                <input type="hidden" name="sId" value="<%=tmpStepLimit.getId() %>"/>
                <input type="hidden" name="stepId" value="<%=tmpStepLimit.getStepId() %>"/>
                <input type="hidden" name="taskName" value="<%=tmpPhaseId %>"/>
                <input type="hidden" name="taskCnName" value="<%=tmpTaskName %>"/>
            </td>
            <td class="content">
                <%if (tmpint == 0) { %>
                <input type="text" readonly="readonly" class="text" name="sacceptLimit" styleClass="text medium"
                       alt="allowBlank:false,maxLength:9,vtext:'请填入受理时限，最多输入9个字符'" value="${levelLimit.acceptLimit }"/>
                <%} else { %>
                <input type="text" class="text" name="sacceptLimit" styleClass="text medium"
                       alt="allowBlank:false,maxLength:9,vtext:'请填入受理时限，最多输入9个字符'"
                       value="<%=tmpStepLimit.getAcceptLimit()%>"/>
                <%} %>
            </td>
            <td class="content">
                <input type="text" class="text" name="completeLimit" styleClass="text medium"
                       alt="allowBlank:false,maxLength:9,vtext:'请填入处理时限，最多输入9个字符'"
                       value="<%=tmpStepLimit.getCompleteLimit()%>"/>
            </td>
        </tr>
        <%
                        tmpint++;
                    }
                }
            }
        %>
        <input type="hidden" name="ids" id="ids"/>
        <input type="hidden" name="stepIds" id="stepIds"/>
        <input type="hidden" name="taskNames" id="taskNames"/>
        <input type="hidden" name="taskCnNames" id="taskCnNames"/>
        <input type="hidden" name="sacceptLimits" id="sacceptLimits""/>
        <input type="hidden" name="completeLimits" id="completeLimits""/>
    </table>
    <html:submit styleClass="button" property="method.save" onclick="return limitCheck();">
        <bean:message bundle='sheet' key='button.save'/>
    </html:submit>
    <html:submit styleClass="button" property="method.delete" onclick="v.passing=true;return deleteCheck();">
        <bean:message bundle='sheet' key='button.delete'/>
    </html:submit>
    <html:cancel styleClass="button" onclick="v.passing=true">
        <bean:message bundle='sheet' key='button.back'/>
    </html:cancel>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>