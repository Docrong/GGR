<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<script type="text/javascript" src="${app}/scripts/kmmanager/adapter-km.js"></script>

<script type="text/javascript">
	function onSubmit(){
    	if(document.forms[0].auditResult.value==""){
    		alert('请选择审核结果');
    		return false; 
    	} 

       // document.forms[0].historyType.value="1";
        document.forms[0].submit();
        return true;
    }
</script>
<title><fmt:message key="kmFile.title" /></title>
<content tag="heading"><b>

</content>
<html:form action="/kmAuditerSteps.do?method=fileAuditDo" styleId="kmContentsOpinionForm" method="post"> 

  <table class="formTable middle">
    <caption>
      <fmt:message key="kmFile.fileName" />:
      ${kmFileForm.fileName }
    </caption>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.nodeId" />:
      </td>
      <td class="content" colspan="3">
       <eoms:id2nameDB id="${kmFileForm.nodeId}" beanId="kmFileTreeDao" />        
      </td>
    </tr>    
    <tr>
      <td class="label">
        <fmt:message key="kmFile.userId" />
      </td>
      <td class="content">
        <eoms:id2nameDB beanId="tawSystemUserDao" id="${kmFileForm.userId}"/>
      </td>
      <td class="label">
        <fmt:message key="kmFile.uploadTime" />
      </td>
      <td class="content">
        ${kmFileForm.uploadTime }
      </td>
    </tr>
    <tr>
		<td class="label">
			状态	
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmFileForm.state}" beanId="id2nameXML" />
	</td>
      <td class="label">
        <fmt:message key="kmFile.fileSize" />
      </td>
      <td class="content" >
        ${kmFileForm.fileSize}&nbsp;KB
      </td>
    </tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileGrade" />
      </td>
      <td class="content">
        <eoms:dict key="dict-kmmanager" dictId="fileGrade" itemId="${kmFileForm.fileGrade}" beanId="id2nameXML" />
      </td>
      <td class="label">
        <fmt:message key="kmFile.clickCount" />
      </td>
      <td class="content">
        ${kmFileForm.clickCount}
      </td>
    </tr>  
    <tr>
      <td class="label">
        <fmt:message key="kmFile.keywords" />
      </td>
      <td class="content"  colspan="3">
        ${kmFileForm.keywords}
      </td>
    </tr>   
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileAbstract" />
      </td>
      <td class="content" colspan="3">
        ${kmFileForm.fileAbstract}
      </td>
    </tr>
    <tr>
      <td class="label">
      	<fmt:message key="kmFile.download" />
      </td>
      <td class="content"  colspan="3">
        <a href="${app}/kmmanager/files.do?method=download&id=${kmFileForm.id}&nodeId=${kmFileForm.nodeId}" style="text-decoration:none">${kmFileForm.fileName}</a>
      </td>
    </tr>
    <%
		String master = StaticMethod.nullObject2String(request.getAttribute("master"));
		String roleId = StaticMethod.nullObject2String(request.getAttribute("roleId"));
		String panels = "[{text:'审核',dataUrl:'/kmmanager/kmAuditers.do?method=xgetRoleAndSubRole&id="+roleId+"&nodeType=role'}]";
		%>
<c:if test="${kmFileForm.state == 5}">	
	<tr id='userTree'>
		<td class="label">
			下一步审核			
		</td>
		<td class="content" colspan="3">
<eoms:chooser id="test"
	category="[{id:'toOrg',text:'审核',limit:1}]"
	panels="<%=panels%>"
/>
    <input type="hidden" id="toOrgId"   name="toOrgId"   value="" />
	<input type="hidden" id="toOrgType"   name="toOrgType"   value="" />
			</td>
	</tr>
</c:if>
<c:if test="${kmFileForm.state == 6}">	
<%
if(("").equals(master)){
%>	
    <input type="hidden" id="toOrgId"   name="toOrgId"   value="${kmFileForm.userId}" />
	<input type="hidden" id="toOrgType"   name="toOrgType"   value="user" />
<%
}else {
%>
	<tr id='userTree'>
		<td class="label">
			下一步审核			
		</td>
		<td class="content" colspan="3">
	<eoms:id2nameDB id="<%=master %>" beanId="tawSystemUserDao" />
    <input type="hidden" id="toOrgId"   name="toOrgId"   value="<%=master %>" />
	<input type="hidden" id="toOrgType"   name="toOrgType"   value="user" />
	</td></tr>
<%
}
%>
</c:if>
<c:if test="${kmFileForm.state == 7}">
    <input type="hidden" id="toOrgId"   name="toOrgId"   value="${kmFileForm.userId}" />
	<input type="hidden" id="toOrgType"   name="toOrgType"   value="user" />
</c:if>

	
	<tr>
		<td class="label">
			审核结果			
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="auditResult" isQuery="false" 
			           defaultId="" selectId="auditResult" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择评论星级(字典)...'"  onchange="changeState()"/>	
		</td>
		<td class="label">
			是否向下流转			
		</td>
		<td class="content">
		<c:if test="${kmFileForm.state == 6}">	
		是
		<input type="hidden" id="nextStep" name="nextStep" value="1030101" />
		</c:if>
		<c:if test="${kmFileForm.state != 6}">
		<eoms:comboBox name="nextStep" id="nextStep" 
            	      initDicId="10301" defaultValue="1030101" alt="allowBlank:false,vtext:''" styleClass="select-class" onchange="showUserTree() "/>	
		</c:if>
		</td>
	</tr>
	
	<tr>
      <td class="label">
        	审核意见
      </td>
      <td class="content" colspan="3">
      <!-- property中配一个空属性 -->
      		<textarea name="remark" cols="50" id="remark" class="textarea max" ></textarea>										
        <p/>
        <input type="button" value="提交" onclick="javascript:onSubmit();" class="button" />
      </td>
    </tr>
    <input type="hidden" id="id" name="id" value="${kmFileForm.id}" />
    <input type="hidden" id="nodeId" name="nodeId" value="${kmFileForm.nodeId}" />
	<input type="hidden" id="toOrgName"   name="toOrgName"   value="" />
	<input type="hidden" id="state"   name="state"   value="1" />
	<input type="hidden" id="fileState"   name="fileState"   value="${kmFileForm.state}" />
	<input type="hidden" id="createUser"   name="createUser"   value="${kmFileForm.userId}" />
</table>



<br>


</div>
</html:form>
  </table>
  </fmt:bundle>
  <script>
	function showUserTree(){
    	if(document.forms[0].nextStep.value=="1030101"){
    		 document.getElementById("userTree").style.display = "";  
    	} else {
    		 document.getElementById("userTree").style.display = "none"; 
    	}
    }
    function changeState(){
    	if(document.forms[0].auditResult.value=="0"){
    		 document.getElementById("state").value = "2"; 
    	} else {
    		 document.getElementById("state").value = "1"; 
    	}
    	if(${kmFileForm.state}=="7"){
    		 document.getElementById("state").value = "2"; 
    	}
    }
</script>
<%@ include file="/common/footer_eoms.jsp"%>
