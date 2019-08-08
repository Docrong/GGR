<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>

<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
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

<%@ include file="/WEB-INF/pages/wfworksheet/commonfaultcorrigendum/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iCommonfaultCorrigendumLinkManager"/>
    <input type="hidden" name="beanId" value="iCommonfaultCorrigendumMainManager"/>
    <input type="hidden" name="mainClassName"
           value="com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumMain"/>
    <input type="hidden" name="linkClassName"
           value="com.boco.eoms.sheet.commonfaultcorrigendum.model.CommonfaultCorrigendumLink"/>
    <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
        </c:when>
    </c:choose>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label">
            备注说明*
        </td>
        <td colspan="3">
		        <textarea name="remark" class="textarea max" id="remark"
                          alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (taskName.equals("NetCorrigendum")) {%>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>


    <%if (operateType.equals("102") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>

    <tr>
        <td class="label">更新维护班组</td>
        <td colspan='3'>
            <eoms:chooser id="sendObject1" type="role" roleId="8005106" flowId="620"
                          category="[{id:'mainnewTeamRoleId',text:'选择',vtext:'请选择更新维护班组'}]"
                          data=""/>
        </td>
    </tr>
    <tr>
        <td class="label">更新抄送对象</td>
        <td colspan='3'>
            <eoms:chooser id="sendObject2" type="role" roleId="8005106" flowId="620"
                          category="[{id:'mainnewccObject',text:'选择',vtext:'请选择更新抄送对象'}]"
                          data=""/>
        </td>
    </tr>

    <tr>

        <td class="label">
            <!-- 处理措施 -->
            <bean:message bundle="commonfaultcorrigendum" key="commonfaultCorrigendumLink.linkMeasures"/>
        </td>
        <td colspan="3">
				<textarea name="linkMeasures" id="linkMeasures" class="textarea max"
                          alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                          alt="width:'500px'">${sheetLink.linkMeasures}</textarea>
        </td>

    </tr>


    <%}%>


    <%
        }
        if (taskName.equals("HoldTask")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="Over"/>
    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
    <input type="hidden" name="status" id="status" value="1"/>

    <%}%>


    <%
        }
        if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")) {
    %>
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
        <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="nodeAccessories" appCode="commonfaultcorrigendum"/>
        </td>
    </tr>
    <%}%>
    <%if (taskName.equals("NetCorrigendum") || taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("61")) { %>
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
    <% if (taskName.equals("cc")) {%>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="operateType" id="operateType" value="-15"/>
            <textarea name="remark" class="textarea max" id="remark"
                      alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
</table>


<% if (taskName.equals("NetCorrigendum")) { %>

<% if (operateType.equals("102")) { %>
<input type="hidden" name="dealPerformer" id="dealPerformer" value="8aa0813b1c6f2386011c6f39c8350027"/>
<input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="8aa0813b1c6f2386011c6f39c8350027"/>
<input type="hidden" name="dealPerformerType" id="dealPerformerType" value="subrole"/>
<% } %>

<% } %>