<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<script type="text/javascript" src="${app}/scripts/kmmanager/adapter-km.js"></script>
 
<html:form action="/kmExpertAnswer.do?method=saveQuestion" styleId="KmExpertAnswerForm" method="post"  onsubmit="return doConfirm()"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<table class="formTable">
	<caption>
		<div class="header center">专家坐诊问答</div>
	</caption>

	

	<tr>
		<td class="label">
			创建人
		</td>
		<td class="content">
		<bean:write name="SessionUserName"/>
		<input type="hidden" id="createUser"  name="createUser" value="<bean:write name="SessionUserId"/>" />	
		</td>

		<td class="label">
			分类
		</td>
		<td class="content">		
	<eoms:comboBox name="type" id="type" 
            	      initDicId="1010104" defaultValue="101010401" alt="allowBlank:false,vtext:''" styleClass="select-class" onchange=""/>	
		
		</td>
	</tr>


		<td class="label">
			问题主题
		</td>
		<td class="content"  colspan="3">	
		<input type="text" id="title" class="text max" name="title" value="" />

		</td>
	</tr>



	<tr>
		<td class="label">
			问题描述
		</td>
		<td class="content" colspan="3">
		<textarea name="question" id="question" class="textarea max"></textarea>
		</td>

	</tr>


	<tr>
		<td class="label">
			备注
		</td>
		<td class="content" colspan="3">
		<textarea name="remark" id="remark" class="textarea max"></textarea>	
		</td>
	</tr>
	<tr>
	<td class="label">
	问题附件
	</td>
	<td class="content" colspan="3">
	<eoms:attachment idList="" idField="questionAtt" appCode="kmmanager"/> 
	</td>
	</tr>
	<tr>
		<td class="label">
		<!--
		<input type="button" name="selectUser" id="selectUser" value="匹配专家树" class="btn" onclick="changeRootId();"/><br>
		  -->
		解答专家
		</td>
		<td class="content" colspan="3">
<input type="button" name="answerUsert" id="answerUsert" value="请选择专家" class="btn"/>
<eoms:xbox  id="answerUsert" dataUrl="${app}/kmmanager/kmExpertAnswer.do?method=getNodes" rootId="-1" single='true' rootText="请选择专家" valueField="answerUserId" handler="answerUsert"
		textField="answerUserName" viewer="true" mode=""   checktype="user"
		data="${kmAuditerForm.masterIdData}">
</eoms:xbox>
			<input type="hidden" id="answerUserName"   name="answerUserName"   value="" />
			<input type="hidden" id="answerUserId"   name="answerUserId"   value="${kmAuditerForm.masterId}" />
			<input type="hidden" id="answerUserType"   name="answerUserType"   value="user" />	
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAuditerForm.id}" />
<html:hidden property="auditType" value="${auditType}" />
<input type="hidden" id="toOrgType"   name="toOrgType"   value="" />
</html:form>
<script>
	function changeRootId(){
	 var type = document.forms[0].type.value;
		alert(type);
		xbox_answerUsert.defaultTree.root.id = type; 
		xbox_answerUsert.reset();
    }
</script>
<%@ include file="/common/footer_eoms.jsp"%>