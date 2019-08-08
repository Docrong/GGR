<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink link = (com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(link.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(link.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/business/businesspilot.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
        form.submit();
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
            thisform.submit();
        }
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businesspilot/baseinputlinkhtmlnew.jsp" %>
<html:form action="/businesspilot.do" styleId="theform">
    <br/>

    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessPilotMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink"/> <!--link表Model对象类名-->

    <table class="listTable">
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>
        <%if (taskName.equals("pilot")) {%>
        <%if (operateType.equals("1")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value=""/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkOperStartTime"/>*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkOperStartTime" readonly="readonly"
                       id="${sheetPageName}linkOperStartTime"
                       onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkOperStartTime)}"/>
            </td>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkOperEndTime"/>*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkOperEndTime" readonly="readonly"
                       id="${sheetPageName}linkOperEndTime"
                       onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkOperEndTime)}"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkTestResult"/>*
            </td>
            <td class="content" colspan=3>
                <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                               initDicId="10301" onchange="ifTestPass(this.value);"
                               defaultValue="${sheetLink.linkTestResult}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkNetType"/>*
            </td>
            <td class="content" colspan=3>
                <eoms:comboBox name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" initDicId="10301"
                               defaultValue="${sheetLink.linkNetType}"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkBusDesc"/>*
            </td>
            <td class="content" colspan=3>
                <textarea name="linkBusDesc" id="linkBusDesc" class="textarea max"
                          alt="allowBlank:false">${sheetLink.linkBusDesc}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkOperateDesc"/>*
            </td>
            <td class="content">
                <eoms:attachment name="sheetLink" property="linkOperateDesc"
                                 scope="request" idField="${sheetPageName}linkOperateDesc" appCode="businesspilot"/>
            </td>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkPilotScheme"/>*
            </td>
            <td class="content">
                <eoms:attachment name="sheetLink" property="linkPilotScheme"
                                 scope="request" idField="${sheetPageName}linkPilotScheme" appCode="businesspilot"/>

            </td>
        </tr>

        <%
                }
            }
        %>
        <%if (taskName.equals("strategy")) {%>
        <%if (operateType.equals("5")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="5"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="prepare"/>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkIlluminate"/>*
            </td>
            <td colspan=3>
                <textarea name="linkIlluminate" id="linkIlluminate" class="textarea max"
                          alt="allowBlank:false">${sheetLink.linkIlluminate}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkTGPolicyAcc"/>*
            </td>
            <td colspan=3>
                <eoms:attachment name="sheetLink" property="linkTGPolicyAcc"
                                 scope="request" idField="${sheetPageName}linkTGPolicyAcc" appCode="businesspilot"/>
            </td>
        </tr>

        <%
                }
            }
        %>

        <%if (taskName.equals("prepare")) {%>
        <%if (operateType.equals("66")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="66"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="run"/>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkDeviceVerify"/>
            </td>
            <td colspan=3>
                <eoms:comboBox name="${sheetPageName}linkDeviceVerify" id="${sheetPageName}linkDeviceVerify"
                               initDicId="1011608" defaultValue="${sheetLink.linkDeviceVerify}"/>

            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkDataFile"/>
            </td>
            <td colspan=3>
		  				   <textarea name="linkDataFile"
                                     id="linkDataFile" class="textarea max">${sheetLink.linkDataFile}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkPassMan"/>
            </td>
            <td colspan=3>
		  				  <textarea name="linkPassMan"
                                    id="linkPassMan" class="textarea max">${sheetLink.linkPassMan}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkReport"/>
            </td>
            <td colspan=3>
		  				   <textarea name="linkReport"
                                     id="linkReport" class="textarea max">${sheetLink.linkReport}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkWorkplan"/>
            </td>
            <td colspan=3>
		  				   <textarea name="linkWorkplan"
                                     id="linkWorkplan" class="textarea max">${sheetLink.linkWorkplan}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkMeetingAcc"/>*
            </td>
            <td colspan=3>
                <eoms:attachment name="sheetLink" property="linkMeetingAcc"
                                 scope="request" idField="${sheetPageName}linkMeetingAcc" appCode="businesspilot"/>
            </td>
        </tr>

        <%
                }
            }
        %>


        <%if (taskName.equals("run")) {%>
        <%if (operateType.equals("46")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="46"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="check"/>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkRunFeedBack"/>*
            </td>
            <td colspan=3>
	  				   <textarea name="linkRunFeedBack"
                                 id="linkRunFeedBack" class="textarea max"
                                 alt="allowBlank:false">${sheetLink.linkRunFeedBack}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkRunReport"/>
            </td>
            <td colspan=3>
                <eoms:attachment name="sheetLink" property="linkRunReport"
                                 scope="request" idField="${sheetPageName}linkRunReport" appCode="businesspilot"/>
            </td>
        </tr>

        <%
                }
            }
        %>

        <%if (taskName.equals("check")) {%>
        <%if (operateType.equals("64")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="64"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="report"/>


        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkIsUpdate"/>*
            </td>
            <td colspan=3>
                <eoms:comboBox name="${sheetPageName}linkIsUpdate" id="${sheetPageName}linkIsUpdate" initDicId="1011608"
                               defaultValue="${sheetLink.linkIsUpdate}"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkUpdateAdvice"/>*
            </td>
            <td colspan=3>
                <textarea name="linkUpdateAdvice" id="linkUpdateAdvice" class="textarea max"
                          alt="allowBlank:false">${sheetLink.linkUpdateAdvice}</textarea>
            </td>
        </tr>

        <%
                }
            }
        %>
        <%if (taskName.equals("report")) {%>
        <%if (operateType.equals("7")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="note"/>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkIsSuccess"/>*
            </td>
            <td colspan=3>
                <eoms:comboBox name="${sheetPageName}linkIsSuccess" id="${sheetPageName}linkIsSuccess"
                               initDicId="1011608" defaultValue="${sheetLink.linkIsSuccess}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkConsult"/>*
            </td>
            <td class="content">
                <eoms:attachment name="sheetLink" property="linkConsult"
                                 scope="request" idField="${sheetPageName}linkConsult" appCode="businesspilot"/>
            </td>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkSummarizeAcc"/>*
            </td>
            <td class="content">
                <eoms:attachment name="sheetLink" property="linkSummarizeAcc"
                                 scope="request" idField="${sheetPageName}linkSummarizeAcc" appCode="businesspilot"/>

            </td>
        </tr>
        <%
                }
            }
        %>

        <%if (taskName.equals("note")) {%>
        <%if (operateType.equals("4")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="temp"/>

        <tr>
            <td class="label">
                <bean:message bundle="businesspilot" key="businesspilot.linkSDEndTime"/>
            </td>
            <td colspan=3>
                <input type="text" class="text" name="${sheetPageName}linkSDEndTime" readonly="readonly"
                       id="${sheetPageName}linkSDEndTime"
                       onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkSDEndTime)}"/>
            </td>
        </tr>
        <%} %>
        <%if (operateType.equals("56")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="56"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="taskOver"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" styleClass="select" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                          class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="businesspilot" key="businesspilot.linkIsFinish"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIsFinish"
                               id="${sheetPageName}linkIsFinish" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10301" onchange="isFinish(this.value);" styleClass="select"
                               alt="allowBlank:false"/>
            </td>
        </tr>

        <tr id="isChange" style="display:none">
            <td class="label"><bean:message bundle="businesspilot" key="businesspilot.linkIsChange"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIsChange"
                               id="${sheetPageName}linkIsChange" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10301" styleClass="select" alt="allowBlank:false"/>
            </td>
        </tr>
        <%} %>
        <%} %>

        <%if (taskName.equals("hold")) {%>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="taskOver"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" styleClass="select" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                          class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>


        <%} %>
        <% if (taskName.equals("cc")) {%>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
            </td>
        </tr>
        <%} %>
        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>
            </td>
            <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>

        <%} %>
    </table>

</html:form>
<logic:present name="type">
    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
        <html:button styleClass="btn" property="method.save" styleId="method.save"
                     onclick="saveDealTemplate('current')">
            <bean:message bundle="sheet" key="button.saveCurTemplate"/>
        </html:button>
    </c:if>
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
        <bean:message bundle="sheet" key="button.delete"/>
    </html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
    <bean:message bundle="sheet" key="button.back"/>
</html:button>
       