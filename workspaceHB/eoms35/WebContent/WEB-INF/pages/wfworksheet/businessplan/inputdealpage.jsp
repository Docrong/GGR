<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    String roleName = "";
%>
<script type="text/javascript">
    function ifAuditPass(input) {
        if ('<%=operateType%>' != '55') {
            if (input == "101050301") {
                $('${sheetPageName}phaseId').value = 'appraisal';
                $('roleName').innerHTML = "${eoms:a2u('新业务网络开发组')}";

            } else if (input == "101050302") {
                $('${sheetPageName}phaseId').value = 'reject';
                $('roleName').innerHTML = "${eoms:a2u('新业务接口人')}";

            }
        }
    }

    function ifAuditPass1(input) {
        if ('<%=operateType%>' != '55') {
            if (input == "101050301") {
                $('${sheetPageName}phaseId').value = 'hold';
                $('roleName').innerHTML = "${eoms:a2u('新业务接口人')}";

            } else if (input == "101050302") {
                $('${sheetPageName}phaseId').value = 'appraisal';
                $('roleName').innerHTML = "${eoms:a2u('新业务网络开发组')}";

            }
        }
    }

    var frm = document.forms[0];
    var temp = frm.linkAuditResult ? frm.linkAuditResult.value : '';
    if (temp != '' && ('${taskName}' == 'audit')) {
        ifAuditPass(temp);
    }
    if (temp != '' && ('${taskName}' == 'standard')) {
        ifAuditPass1(temp);
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessplan/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <!--  <caption><bean:message bundle="businessplan" key="businessplan.header"/></caption>-->
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessPlanMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessplan.model.BusinessPlanMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessplan.model.BusinessPlanLink"/>
    <c:if test="${taskName != 'hold' }">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>
    <%
        if (taskName.equals("analyse")) {
            if (operateType.equals("91") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="audit"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkAnalyse"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAnalyse" id="${sheetPageName}linkAnalyse"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入可行性分析概述，最多输入1000字')}'">${sheetLink.linkAnalyse}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkreport"/>*</td>
        <td colspan='3'>
            <eoms:attachment name="sheetLink" idList="" scope="request" property="linkreport"
                             idField="${sheetPageName}linkreport" scope="request" appCode="businessplan"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkIsKx"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIsKx" id="${sheetPageName}linkIsKx"
                           initDicId="1010502" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkIsKx}"/>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("audit")) {
            if (operateType.equals("92") || operateType.equals("55")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="appraisal"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                           initDicId="1010503" alt="allowBlank:false" styleClass="select-class"
                           onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditResult}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入审批意见，最多输入1000字')}'">${sheetLink.linkAuditDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <%
        if (taskName.equals("standard")) {
            if (operateType.equals("94") || operateType.equals("55")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="hold"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                           initDicId="1010503" alt="allowBlank:false" styleClass="select-class"
                           onchange="ifAuditPass1(this.value);" defaultValue="${sheetLink.linkAuditResult}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkAuditDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入审批意见，最多输入1000字')}'">${sheetLink.linkAuditDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%
        if (taskName.equals("appraisal")) {
            if (operateType.equals("93") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="standard"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkOpion"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkOpion" id="${sheetPageName}linkOpion"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入技术规范评审意见，最多输入1000字')}'">${sheetLink.linkOpion}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessplan" key="businessplan.linkOpionReport"/>*</td>
        <td colspan='3'>

            <eoms:attachment name="sheetLink" idList="" property="linkOpionReport" scope="request"
                             idField="${sheetPageName}linkOpionReport" appCode="businessplan" alt="allowBlank:false"/>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject"/>
        </c:when>
        <c:when test="${fPreTaskName == 'draft'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:1000,width:500,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (taskName.equals("hold")) {%>
    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult"
                      alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('归档意见最多输入1000字')}'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%if (taskName.equals("analyse") || taskName.equals("audit") || taskName.equals("standard") || taskName.equals("appraisal")) {%>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:'500px',maxLength:1000,vtext:'${eoms:a2u('请最多输入500字')}'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
</table>


<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%} %>


<%
    if (taskName.equals("analyse")) {
        if (operateType.equals("91")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="businessplan" key="businessplan.linkAuditPer"/>:
        <bean:message bundle="businessplan" key="role.businessSupport"/>
    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="206" flowId="021" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("audit")) {
        if (operateType.equals("92")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <%--        <div id="audit" display="none">
              <eoms:chooser id="sendRole" type="role" roleId="205" flowId="021" config="{returnJSON:true,showLeader:true}"
                 category="[{id:'${sheetPageName}dealPerformer',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
            </div>--%>
</fieldset>
<%
        }
    }
%>


<%
    if (taskName.equals("appraisal")) {
        if (operateType.equals("93")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="businessplan" key="businessplan.linkAuditPer"/>:
        <bean:message bundle="businessplan" key="role.businessSupport"/>
    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="206" flowId="021" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("standard")) {
        if (operateType.equals("94")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>

    </legend>

    <%--   <eoms:chooser id="sendRole" type="role" roleId="204" flowId="021" config="{returnJSON:true,showLeader:true}"
               category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
             --%>
</fieldset>
<%
        }
    }
%>
	 
 
