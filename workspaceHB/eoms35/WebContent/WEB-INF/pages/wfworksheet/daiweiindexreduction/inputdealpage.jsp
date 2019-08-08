<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>

<%
    String taskName = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod
            .nullObject2String(request.getParameter("operateType"));
    request.setAttribute("operateType", operateType);
%>
<script type="text/javascript">
    //处理时限不能超过工单完成时限
    var dtnow = new Date();
    var str = '${sheetMain.sheetCompleteLimit}';
    str = str.replace(/-/g, "/");
    str = str.substring(0, str.length - 2);
    var dt2 = new Date(str);
    if (dt2 > dtnow) {
        document.getElementById("tmpCompleteLimit").value = '${sheetMain.sheetCompleteLimit}';
    } else {
        document.getElementById("tmpCompleteLimit").value = '';
    }
</script>
<script type="text/javascript">
    function changePhase(obj) {
        alert(obj);
    }

    function selectRoleId(val) {
    }
</script>
<%@ include
        file="/WEB-INF/pages/wfworksheet/daiweiindexreduction/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId"
           value="iDaiWeiIndexReductionLinkManager"/>
    <input type="hidden" name="beanId"
           value="iDaiWeiIndexReductionMainManager"/>
    <input type="hidden" name="mainClassName"
           value="com.boco.eoms.sheet.daiweiindexreduction.model.DaiWeiIndexReductionMain"/>
    <input type="hidden" name="linkClassName"
           value="com.boco.eoms.sheet.daiweiindexreduction.model.DaiWeiIndexReductionLink"/>
    <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag"
                   value="true"/>
        </c:when>
    </c:choose>


    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName =='DraftTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName }"/>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            备注说明
        </td>
        <td colspan="3">
            <textarea name="remark" class="textarea max" id="remark" alt="allowBlank:true,width:500,vtext:'请最多输入1000字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

    <%
        if (taskName.equals("TrialTask")) {
    %>
    <% if (operateType.equals("101")) { %>
    <!-- 隐藏提交的信息 -->
    <input type="hidden" name="operateType" id="operateType" value=""/>
    <input type="hidden" name="phaseId" id="phaseId" value=""/>
    <input type="hidden" name="infoId" id="infoId" value=""/>
    <input type="hidden" name="infoReserveC" id="infoReserveC" value=""/>
    <input type="hidden" name="infoReserveD" id="infoReserveD" value=""/>

    <tr>
        <td class="label"><!-- 联系人 --> <bean:message
                bundle="daiweiindexreduction" key="daiWeiIndexReductionLink.linkMan"/>
        </td>
        <td class="content"><input type="text" class="text"
                                   name="linkMan" id="linkMan"
                                   alt="allowBlank:true,maxLength:20,vtext:'请填入 联系人 信息，最多输入 20 字符'"
                                   value="${sheetLink.linkMan}"/></td>

        <td class="label"><!-- 联系人电话 --> <bean:message
                bundle="daiweiindexreduction"
                key="daiWeiIndexReductionLink.linkManTelephone"/></td>
        <td class="content"><input type="text" class="text"
                                   name="linkManTelephone" id="linkManTelephone"
                                   alt="allowBlank:true,maxLength:30,vtext:'请填入 联系人电话 信息，最多输入 30 字符'"
                                   value="${sheetLink.linkManTelephone}"/></td>
    </tr>


    <c:choose>
        <c:when test="${sheetMain.newFlag == 'newFlag'}">
            <tr>
                <td class="label">核减内容</td>
                <td colspan='3'>
                    <eoms:attachment name="sheetMain" property="sheetAccessories"
                                     scope="request" idField="sheetAccessories" appCode="daiweiindexreduction"
                                     viewFlag="Y"/>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <!-- 隐藏一个iframe的 入数据信息列表 -->
            <tr id="importInforList" style="display:true">

                <td colspan="4">
                    <iframe src="daiweiindexreduction.do?method=search&ifShowOther=yes&refSheetId=${sheetMain.mainReserveA}"
                            scrolling="auto" width="100%"
                            id="iframepage" onchange="changePhase(this);"></iframe>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>


    <tr>

        <td class="label"><!-- 初审说明 --> <bean:message
                bundle="daiweiindexreduction"
                key="daiWeiIndexReductionLink.linkTrialIllustrate"/></td>
        <td colspan="3"><textarea class="textarea max"
                                  name="mainIllustrate" id="mainIllustrate"
                                  alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetMain.mainIllustrate}</textarea>
    </tr>
    <%} %>

    <% if (operateType.equals("103")) { %>
    <!-- 隐藏提交的信息 -->
    <input type="hidden" name="operateType" id="operateType" value="101"/>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>
    <input type="hidden" name="infoId" id="infoId" value=""/>
    <input type="hidden" name="infoReserveC" id="infoReserveC" value=""/>
    <input type="hidden" name="infoReserveD" id="infoReserveD" value=""/>

    <tr>
        <td class="label"><!-- 联系人 --> <bean:message
                bundle="daiweiindexreduction" key="daiWeiIndexReductionLink.linkMan"/>
        </td>
        <td class="content"><input type="text" class="text"
                                   name="linkMan" id="linkMan"
                                   alt="allowBlank:true,maxLength:20,vtext:'请填入 联系人 信息，最多输入 20 字符'"
                                   value="${sheetLink.linkMan}"/></td>

        <td class="label"><!-- 联系人电话 --> <bean:message
                bundle="daiweiindexreduction"
                key="daiWeiIndexReductionLink.linkManTelephone"/></td>
        <td class="content"><input type="text" class="text"
                                   name="linkManTelephone" id="linkManTelephone"
                                   alt="allowBlank:true,maxLength:30,vtext:'请填入 联系人电话 信息，最多输入 30 字符'"
                                   value="${sheetLink.linkManTelephone}"/></td>
    </tr>


    <c:choose>
        <c:when test="${sheetMain.newFlag == 'newFlag'}">
            <tr>
                <td class="label">核减内容</td>
                <td colspan='3'>
                    <eoms:attachment name="sheetMain" property="mainReserveD"
                                     scope="request" idField="mainReserveD" appCode="daiweiindexreduction"
                                     viewFlag="Y"/>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <!-- 隐藏一个iframe的 入数据信息列表 -->
            <tr id="importInforList" style="display:true">

                <td colspan="4">
                    <iframe src="daiweiindexreduction.do?method=search&ifShowOther=yes&refSheetId=${sheetMain.mainReserveA}"
                            scrolling="auto" width="100%"
                            id="iframepage" onchange="changePhase(this);"></iframe>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>


    <tr>

        <td class="label"><!-- 初审说明 --> <bean:message
                bundle="daiweiindexreduction"
                key="daiWeiIndexReductionLink.linkTrialIllustrate"/></td>
        <td colspan="3"><textarea class="textarea max"
                                  name="mainIllustrate" id="mainIllustrate"
                                  alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetMain.mainIllustrate}</textarea>
    </tr>
    <%} %>

    <% if (operateType.equals("104")) { %>
    <!-- 隐藏提交的信息 -->
    <input type="hidden" name="operateType" id="operateType" value="102"/>
    <input type="hidden" name="phaseId" id="phaseId" value="RejectTask"/>
    <input type="hidden" name="infoId" id="infoId" value=""/>
    <input type="hidden" name="infoReserveC" id="infoReserveC" value=""/>
    <input type="hidden" name="infoReserveD" id="infoReserveD" value=""/>

    <tr>
        <td class="label"><!-- 联系人 --> <bean:message
                bundle="daiweiindexreduction" key="daiWeiIndexReductionLink.linkMan"/>
        </td>
        <td class="content"><input type="text" class="text"
                                   name="linkMan" id="linkMan"
                                   alt="allowBlank:true,maxLength:20,vtext:'请填入 联系人 信息，最多输入 20 字符'"
                                   value="${sheetLink.linkMan}"/></td>

        <td class="label"><!-- 联系人电话 --> <bean:message
                bundle="daiweiindexreduction"
                key="daiWeiIndexReductionLink.linkManTelephone"/></td>
        <td class="content"><input type="text" class="text"
                                   name="linkManTelephone" id="linkManTelephone"
                                   alt="allowBlank:true,maxLength:30,vtext:'请填入 联系人电话 信息，最多输入 30 字符'"
                                   value="${sheetLink.linkManTelephone}"/></td>
    </tr>


    <c:choose>
        <c:when test="${sheetMain.newFlag == 'newFlag'}">
            <tr>
                <td class="label">核减内容</td>
                <td colspan='3'>
                    <eoms:attachment name="sheetMain" property="mainReserveD"
                                     scope="request" idField="mainReserveD" appCode="daiweiindexreduction"
                                     viewFlag="Y"/>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <!-- 隐藏一个iframe的 入数据信息列表 -->
            <tr id="importInforList" style="display:true">

                <td colspan="4">
                    <iframe src="daiweiindexreduction.do?method=search&ifShowOther=yes&refSheetId=${sheetMain.mainReserveA}"
                            scrolling="auto" width="100%"
                            id="iframepage" onchange="changePhase(this);"></iframe>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>


    <tr>

        <td class="label"><!-- 初审说明 --> <bean:message
                bundle="daiweiindexreduction"
                key="daiWeiIndexReductionLink.linkTrialIllustrate"/></td>
        <td colspan="3"><textarea class="textarea max"
                                  name="mainIllustrate" id="mainIllustrate"
                                  alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetMain.mainIllustrate}</textarea>
    </tr>
    <%} %>


    <%} %>

    <%
        if (taskName.equals("HoldTask")) {
    %>
    <input type="hidden" name="operateType" id="operateType"
           value="<%=operateType %>"/>

    <%
        if (operateType.equals("18")) {
    %>
    <input type="hidden" name="phaseId" id="phaseId" value="Over"/>
    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag"
           value="true"/>
    <input type="hidden" name="status" id="status" value="1"/>
    <tr>
        <td class="label">归档满意度</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">归档意见</td>
        <td colspan="3">
				<textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                          alt="allowBlank:true,maxLength:200,vtext:'最多输入100汉字'">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%
        }
    %>

    <%
        }
        if (!operateType.equals("61") && !operateType.equals("18")
                && !operateType.equals("4")) {
    %>
    <c:choose>
        <c:when test="${sheetMain.newFlag == 'newFlag' && operateType != '104'}">
            <tr>
                <td class="label"><bean:message bundle="sheet"
                                                key="linkForm.accessories"/>*
                </td>
                <td colspan="3"><eoms:attachment name="sheetLink"
                                                 property="nodeAccessories" scope="request" idField="nodeAccessories"
                                                 appCode="daiweiindexreduction" alt="allowBlank:fasle"/></td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td class="label"><bean:message bundle="sheet"
                                                key="linkForm.accessories"/></td>
                <td colspan="3"><eoms:attachment name="sheetLink"
                                                 property="nodeAccessories" scope="request" idField="nodeAccessories"
                                                 appCode="daiweiindexreduction" alt="allowBlank:true"/></td>
            </tr>
        </c:otherwise>
    </c:choose>

    <%
        }
    %>

    <%
        if (taskName.equals("TrialTask") || taskName.equals("HoldTask")) {
    %>
    <%
        if (operateType.equals("61")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="61"/>
    <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> -->
    <%
            }
        }
    %>
    <%
        if (taskName.equals("cc")) {
    %>

    <tr>
        <td class="label"><bean:message bundle="sheet"
                                        key="linkForm.remark"/>*
        </td>
        <td colspan="3"><input type="hidden" name="operateType"
                               id="operateType" value="-15"/> <textarea name="remark"
                                                                        class="textarea max" id="remark"
                                                                        alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                                                                        alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%
        }
    %>
</table>

<%
    if (taskName.equals("TrialTask")) {
%>

<%
    if (operateType.equals("101") || operateType.equals("103") || operateType.equals("104")) {
%>
<fieldset style="display:">
    <legend><bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:建单人</span>
    </legend>
    <div class="x-form-item">
        &nbsp;&nbsp;
        <eoms:id2nameDB id="${sheetMain.sendUserId }" beanId="tawSystemUserDao"/>
        <input type="hidden" id="dealPerformer" name="dealPerformer" class="text" value="${sheetMain.sendUserId }"/>
        <input type="hidden" id="dealPerformerLeader" name="dealPerformerLeader" class="text"
               value="${sheetMain.sendUserId }"/>
        <input type="hidden" id="dealPerformerType" name="dealPerformerType" class="text" value="user"/>
    </div>
</fieldset>
<%
    }
%>


<%
    }
%>
