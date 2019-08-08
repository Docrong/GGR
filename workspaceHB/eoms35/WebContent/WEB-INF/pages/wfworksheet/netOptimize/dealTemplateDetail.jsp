<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink link = (com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink) request.getAttribute("sheetLink");
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
        form.action = "${app}/sheet/netOptimize/netOptimize.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/netOptimize/baseinputlinkhtmlnew.jsp" %>
<html:form action="/netOptimize.do" styleId="theform">
    <br/>

    <input type="hidden" name="${sheetPageName}beanId" value="iNetOptimizeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink"/> <!--link表Model对象类名-->

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
        <%
            if (taskName.equals("AcceptTask") && (operateType.equals("80") || operateType.equals("11"))) {

        %>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="callProcess"/>

        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkIsAccept"/>*
            </td>
            <td class="content max" colspan=3>
                <eoms:comboBox name="${sheetPageName}linkIsAccept" id="${sheetPageName}linkIsAccept"
                               alt="allowBlank:false" initDicId="1011801" onchange="AcceptChange(this.value);"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkTestResult"/>*
            </td>
            <td class="content max" colspan=3>
                <textarea name="linkTestResult" id="linkTestResult" class="textarea max"
                          alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入分析/测试结果，最大长度为200个汉字')}'">${sheetLink.linkTestResult}</textarea>
            </td>
        </tr>
        <tr>
                    <%}%>

                    <%if(taskName.equals("ProjectCreateTask") && (operateType.equals("2")||operateType.equals("11"))){ %>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
                   value="<%=operateType%>"/>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>

        <tr>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.linkProjectExplain"/>*</td>
            <td class="content max" colspan="3">
				        <textarea name="${sheetPageName}linkProjectExplain" class="textarea max"
                                  id="${sheetPageName}linkProjectExplain"
                                  alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入网优方案说明，最大长度为200个汉字')}'">${sheetLink.linkProjectExplain}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.linkProjectAcc"/>*</td>
            <td class="content max" colspan="3">
                <eoms:attachment name="sheetLink" property="linkProjectAcc"
                                 scope="request" idField="${sheetPageName}linkProjectAcc" alt="allowBlank:false"
                                 appCode="netOptimize"/>
            </td>
        </tr>

        <%} %>


        <%if (taskName.equals("AuditTask")) {%>
        <%if (operateType.equals("56") || operateType.equals("55")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteTask"/>
        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkAuditOpinion"/>*
            </td>
            <td class="content max" colspan=3>
                <eoms:comboBox name="${sheetPageName}linkAuditOpinion" id="${sheetPageName}linkAuditOpinion"
                               alt="allowBlank:false" initDicId="1011804" onchange="AuditIfPass(this.value);"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkAuditResult"/>*
            </td>
            <td class="content max" colspan=3>
                <textarea name="linkAuditResult" id="linkAuditResult" class="textarea max"
                          alt="allowBlank:false,maxLength:100,vtext:'${eoms:a2u('请输入审批结果，最大长度为100个汉字！')}'">${sheetLink.linkAuditResult}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>


        <%if (taskName.equals("ExcuteTask")) {%>
        <%if (operateType.equals("62") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ResultEvaluateTask"/>

        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkIsSuccess"/>*
            </td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIsSuccess" id="${sheetPageName}linkIsSuccess"
                               initDicId="1011803" defaultValue="${sheetLink.linkIsSuccess}"/>
            </td>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkIsStartSheet"/>*
            </td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIsStartSheet" id="${sheetPageName}linkIsStartSheet"
                               initDicId="1011802" onchange="IsDispatchSheet(this.value);"
                               defaultValue="${sheetLink.linkIsStartSheet}"/>
            </td>
        </tr>

        <tr id="invokeButton" style="display:none">
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkIsStartSheet"/>*
            </td>
            <td class="content max" colspan=3>
                <input type="hidden" name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" value=""/>
                <input type="hidden" name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" value=""/>

                <input type="button" class="btn" value="<eoms:id2nameDB id='101050804' beanId='ItawSystemDictTypeDao'/>"
                       onclick="javascript:openwin('101050804')">

            </td>
        </tr>


        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkSummarize"/>*
            </td>
            <td class="content max" colspan=3>
		  				   <textarea name="linkSummarize"
                                     id="linkSummarize" class="textarea max"
                                     alt="allowBlank:true,maxLength:200,vtext:'${eoms:a2u('您输入网优实施概述超出最大长度限制，最多支持200个汉字！')}'">${sheetLink.linkSummarize}</textarea>
            </td>
        </tr>


        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkOperationAcc"/>*
            </td>
            <td class="content" colspan=3>
                <eoms:attachment name="sheetLink" property="linkProjectAcc"
                                 scope="request" idField="${sheetPageName}linkProjectAcc" alt="allowBlank:false"
                                 appCode="netOptimize"/>
            </td>
        </tr>

        <%
                }
            }
        %>


        <%if (taskName.equals("ResultEvaluateTask")) {%>
        <%if (operateType.equals("64") || operateType.equals("11")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>

        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.linkAssessResult"/>*
            </td>
            <td class="content max" colspan=3>
	  				   <textarea name="linkAssessResult"
                                 id="linkResult" class="textarea max"
                                 alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入评定结果，最大长度为200个汉字！')}'">${sheetLink.linkAssessResult}</textarea>
            </td>
        </tr>

        <%
                }
            }
        %>

        <%if (taskName.equals("HoldTask") && !operateType.equals("61")) {%>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td class="content max" colspan="3">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" styleClass="select" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
            <td class="content max" colspan="3">
                <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                          class="textarea max">${sheetMain.endResult}</textarea>
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
       