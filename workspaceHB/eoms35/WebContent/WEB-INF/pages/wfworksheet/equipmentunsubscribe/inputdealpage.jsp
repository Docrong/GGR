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

<%@ include file="/WEB-INF/pages/wfworksheet/equipmentunsubscribe/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iEquipmentUnsubscribeLinkManager"/>
    <input type="hidden" name="beanId" value="iEquipmentUnsubscribeMainManager"/>
    <input type="hidden" name="mainClassName"
           value="com.boco.eoms.sheet.equipmentunsubscribe.model.EquipmentUnsubscribeMain"/>
    <input type="hidden" name="linkClassName"
           value="com.boco.eoms.sheet.equipmentunsubscribe.model.EquipmentUnsubscribeLink"/>
    <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
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


    <%if (taskName.equals("Audit")) {%>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>


    <%if (operateType.equals("101") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="Dismantle"/>


    <tr>

        <td class="label">
            <!-- 备注 -->
            <bean:message bundle="equipmentunsubscribe" key="equipmentUnsubscribeLink.linkDescr"/>
        </td>
        <td colspan="3">
				<textarea class="textarea max" name="linkDescr"
                          id="linkDescr"
                          alt="allowBlank:true,maxLength:2000,vtext:'请填入 备注 信息，最多输入 2000 字符'">${sheetLink.linkDescr}</textarea>

        </td>
    </tr>
    <%}%>


    <%
        }
        if (taskName.equals("Dismantle")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("102") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>


    <tr>

        <td class="label">
            设备类型*
        </td>
        <td>
            <input type="text" class="text" name="linkEquipmentType" id="linkEquipmentType"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 安装设备类型 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkEquipmentType}"/>
        </td>


        <td class="label">
            <!-- 设备编号 -->
            <bean:message bundle="equipmentunsubscribe" key="equipmentUnsubscribeLink.linkEquipmentNum"/>
        </td>
        <td>
            <input type="text" class="text" name="linkEquipmentNum" id="linkEquipmentNum"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 设备编号 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkEquipmentNum}"/>
        </td>
    </tr>


    <tr>
        <td class="label">
            拆除时间*
        </td>
        <td colspan="3">
            <input type="text" class="text" name="linkEquipmentTime" readonly="readonly"
                   id="linkEquipmentTime" value="${eoms:date2String(sheetLink.linkEquipmentTime)}"
                   onclick="popUpCalendar(this, this, null, null, null, true, -1)" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>

        <td class="label">
            <!-- 现场施工照片 -->
            <bean:message bundle="equipmentunsubscribe" key="equipmentUnsubscribeLink.linkScenePhoto"/>*
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="linkScenePhoto"
                             scope="request" idField="linkScenePhoto" appCode="equipmentunsubscribe"
                             alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 设备入库照片 -->
            <bean:message bundle="equipmentunsubscribe" key="equipmentUnsubscribeLink.linkPutPhoto"/>*
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="linkPutPhoto"
                             scope="request" idField="linkPutPhoto" appCode="equipmentunsubscribe"
                             alt="allowBlank:false"/>
        </td>
    </tr>

    <c:if test="${sheetMain.mainIsEquipmentFee == '101390301'}">
        <tr>
            <td class="label">
                退费照片*
            </td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="linkReturnPhoto"
                                 scope="request" idField="linkReturnPhoto" appCode="equipmentunsubscribe"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
    </c:if>
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
    <!-- <tr>
		         <td class="label">
		    	  <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			     </td>	
			    <td colspan="3">					
		          <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		        </td>
		       </tr>		
			   <tr>
			     <td class="label"><bean:message bundle="sheet" key="linkForm.accessories" /></td>
			     <td colspan="3">				
					     <eoms:attachment name="sheetLink" property="nodeAccessories" 
              				scope="request" idField="nodeAccessories" appCode="equipmentunsubscribe" />		   
			     </td>
		</tr> -->
    <%}%>
    <%if (taskName.equals("Audit") || taskName.equals("Dismantle") || taskName.equals("HoldTask")) {%>
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


<% if (taskName.equals("Audit")) { %>

<% if (operateType.equals("101")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:分公司代维人员
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="Audit0" type="role" roleId="151" flowId="25" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("Dismantle")) { %>


<% if (operateType.equals("102")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:分公司投诉管理员
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="Dismantle0" type="role" roleId="149" flowId="25" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>


<% } %>