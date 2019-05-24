<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<html:form action="/kmContentsOpinions.do?method=save" styleId="kmContentsOpinionForm" method="post"> 
<div id="info-page">
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
  <!-- 查看内容信息 -->
  <div id="plan-info" class="tabContent">

<font color='red'>*</font>号为必填内容
<!-- 知详细信息 -->
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="trainPlan.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainName" />
		</td>
		<td class="content" colspan="3">
		${trainPlanForm.trainName}
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainUser" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainPlanForm.trainUser}" beanId="tawSystemUserDao" />
		</td>
		<td class="label">
			<fmt:message key="trainPlan.trainDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainPlanForm.trainDept}" beanId="tawSystemDeptDao" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainTel" />
		</td>
		<td class="content">
${trainPlanForm.trainTel}
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainUnit" />
		</td>
		<td class="content">
${trainPlanForm.trainUnit}
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainBeginTime" />
		</td>
		<td class="content">
${trainPlanForm.trainBeginTime}
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainTime" />
		</td>
		<td class="content">
${trainPlanForm.trainTime}
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainEndTime" />
		</td>
		<td class="content">
${trainPlanForm.trainEndTime}
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainAddress" />
		</td>
		<td class="content">
${trainPlanForm.trainAddress}
		</td>
	
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainVender" />
		</td>
		<td class="content">
${trainPlanForm.trainVender}
		</td>
			<td class="label">
			<fmt:message key="trainPlan.trainType" />
		</td>
		<td class="content">
${trainPlanForm.trainType}
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainSpeciality" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainPlanForm.trainSpeciality}" beanId="trainSpecialtyDao" />
		
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainRequireNo" />
		</td>
		<td class="content">
${trainPlanForm.trainRequireNo}
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainContent" />
		</td>
		<td class="content" colspan="3">
			<textarea cols="50" class="textarea max" readonly="readonly">${trainPlanForm.trainContent}</textarea>
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.fileName" />
		</td>
		<td class="content" colspan="3">
			<eoms:attachment name="trainPlanForm" property="fileName" scope="request" idField="fileName" appCode="kmmanager" viewFlag="Y"/>
		</td>
	</tr>

</table>
<input type="hidden" name="trainPlanId" id="trainPlanId" value="${trainPlanForm.id}">

<table>
	<tr>
		<td>
			<!-- 自己不能报名参加自己写的培训记录及反馈 -->
			<c:if test="${trainPlanForm.trainUser != sessionScope.sessionform.userid}">
		    	<input type="button" class="btn" value="报名" onclick="javascript:onSubmitEnter();"/>&nbsp;
		    	<input type="button" class="btn" value="反馈" onclick="javascript:onSubmitFeedback();"/>&nbsp;
		    </c:if>
		    <!-- 自己删除自己写的培训计划 -->
		    <c:if test="${trainPlanForm.trainUser == sessionScope.sessionform.userid}">
		    	<input type="button" class="btn" value="修改" onclick="javascript:onSubmitEdit();"/>&nbsp;
		    	<input type="button" class="btn" value="删除" onclick="javascript:onSubmitDele();"/>&nbsp;
		    </c:if>
		</td>
	</tr>
</table>

<br>
</div>


  <!-- 查看报名信息 -->
  <div id="enter-info" class="tabContent">
  	<display:table name="trainEnterList" cellspacing="0" cellpadding="0"
		id="trainEnterList" pagesize="${enterPageSize}" class="table trainEnterList"
		export="false"
		requestURI="${app}/kmmanager/trainEnters.do?method=search"
		>
	
	<display:column sortable="false" headerClass="sortable" titleKey="trainEnter.enterName">
		<eoms:id2nameDB id="${trainEnterList.enterName}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column  sortable="false" headerClass="sortable" titleKey="trainEnter.trainEnterDept" >
			<eoms:id2nameDB id="${trainEnterList.trainEnterDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	
	<display:column  sortable="false"	headerClass="sortable" titleKey="trainEnter.trainPlanId" >
		<eoms:id2nameDB id="${trainEnterList.trainPlanId}" beanId="trainPlanDao" />
	</display:column>
	
	<display:column property="trainEnterTel" sortable="false"
			headerClass="sortable" titleKey="trainEnter.trainEnterTel"  paramId="id" paramProperty="id"/>

	<display:column property="trainEnterTime" sortable="false" format="{0,date,yyyy-MM-dd HH:mm:ss}" 
			headerClass="sortable" titleKey="trainEnter.trainEnterTime"  paramId="id" paramProperty="id"/>
	
	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainEnterList.id }';
		                        var url='${app}/kmmanager/trainEnters.do?method=edit';
		                        url = url + '&id=' + id;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
	</display:column> 
	
		<display:setProperty name="paging.banner.item_name" value="trainEnter" />
		<display:setProperty name="paging.banner.items_name" value="trainEnters" />
	</display:table>

  </div>

  <!-- 查看反馈信息 -->
  <div id="feedback-info" class="tabContent">
  	<display:table name="trainFeedbackList" cellspacing="0" cellpadding="0"
		id="trainFeedbackList" pagesize="${FeedPageSize}" class="table trainFeedbackList"
		export="false"
		requestURI="${app}/trainFeedback/trainFeedbacks.do?method=search"
	>
	<display:column sortable="false" headerClass="sortable" titleKey="trainFeedback.feedbackUser">
		<eoms:id2nameDB id="${trainFeedbackList.feedbackUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column  sortable="false" headerClass="sortable" titleKey="trainFeedback.trainFeedbackDept" >
			<eoms:id2nameDB id="${trainFeedbackList.trainFeedbackDept}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column  sortable="false"	headerClass="sortable" titleKey="trainFeedback.trainPlanId" >
		<eoms:id2nameDB id="${trainFeedbackList.trainPlanId}" beanId="trainPlanDao" />
	</display:column>
	
	<display:column property="trainFeedbackTel" sortable="false"
			headerClass="sortable" titleKey="trainFeedback.trainFeedbackTel"  paramId="id" paramProperty="id"/>

	<display:column property="trainFeedbackTime" sortable="false" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="trainFeedback.trainFeedbackTime"  paramId="id" paramProperty="id"/>

	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${trainFeedbackList.id }';
		                        var url='${app}/kmmanager/trainFeedbacks.do?method=edit';
		                        url = url + '&id=' + id;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>		    
	</display:column> 
	
		<display:setProperty name="paging.banner.item_name" value="trainFeedback" />
		<display:setProperty name="paging.banner.items_name" value="trainFeedbacks" />
	</display:table>

  </div>
</fmt:bundle>
</div>
</html:form>

<script type="text/javascript">
	var readTree;
	Ext.onReady(function(){
			var tabs = new Ext.TabPanel('info-page');
			tabs.addTab('plan-info', '计划详细信息 ');
        	tabs.addTab('enter-info', '报名信息 ');
        	tabs.addTab('feedback-info', '反馈信息 ');
    		tabs.activate(0);
	});
	
	//报名
	function onSubmitEnter(){
		var arr = [<c:forEach items="${trainEnterList1}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.enterName}'</c:forEach>];
		//当前登陆人
		var user="${sessionScope.sessionform.userid}";
		for(var i=0;i<arr.length;i++){
			if(user==arr[i]){
				alert('您已经报过名了!!');
				return false;
			}
		}	
	    var id = document.getElementById("trainPlanId").value;
	    var url='${app}/kmmanager/trainEnters.do?method=add&trainPlanId=' + id;
	    window.location.href(url);
    }
    //反馈
	function onSubmitFeedback(){
	   var id = document.getElementById("trainPlanId").value;
	    var url='${app}/kmmanager/trainFeedbacks.do?method=add&trainPlanId=' + id;
	    window.location.href(url);
    }
    //修改
    function onSubmitEdit(){
	    var id = document.getElementById("trainPlanId").value;
	    var url='${app}/kmmanager/trainPlans.do?method=edit&trainPlanId=' + id;
	    window.location.href(url);
    }
	
	 //删除
    function onSubmitDele(){
	    var id = document.getElementById("trainPlanId").value;
	    var url='${app}/kmmanager/trainPlans.do?method=remove&trainPlanId=' + id;
	    window.location.href(url);
    }
</script>

<%@ include file="/common/footer_eoms.jsp"%>