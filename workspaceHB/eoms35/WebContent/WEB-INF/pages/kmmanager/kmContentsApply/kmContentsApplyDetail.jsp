<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<html:form action="/kmContentsApplys.do?method=save" styleId="kmContentsOpinionForm" method="post"> 
<div id="info-page">
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
  <!-- 查看内容信息 -->
  <div id="apply-info" class="tabContent">

<font color='red'>*</font>号为必填内容
<!-- 知详细信息 -->
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContentsApply.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyUser" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmContentsApplyForm.applyUser}" beanId="tawSystemUserDao" />
			<input type="hidden" name="applyUser" value="${kmContentsApplyForm.applyUser}" />
		</td>
	
		<td class="label">
			<fmt:message key="kmContentsApply.applyDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmContentsApplyForm.applyDept}" beanId="tawSystemDeptDao" />
			<input type="hidden" name="applyDept" value="${kmContentsApplyForm.applyDept}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyDate" />
		</td>
		<td class="content" colspan="3">
			${kmContentsApplyForm.applyDate}
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyTitle" />
		</td>
		<td class="content" colspan="3">
			${kmContentsApplyForm.applyTitle}
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyTable" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmContentsApplyForm.applyTable}" beanId="kmTableThemeDao" />
			<input type="hidden" name="applyTable" value="${kmContentsApplyForm.applyTable}" />
		</td>
	
		<td class="label">
			<fmt:message key="kmContentsApply.applyTheme" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmContentsApplyForm.applyTheme}" beanId="kmTableThemeDao" />
			<input type="hidden" name="applyTheme" value="${kmContentsApplyForm.applyTheme}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.applyContent" />
		</td>
		<td class="content" colspan="3">
			<textarea name="applyContent" id="applyContent" 
			          cols="50" class="textarea max" 
			          readonly="readonly">${kmContentsApplyForm.applyContent}</textarea>	
		</td>
	</tr>

	
	<c:if test="${kmContentsApplyForm.fileName!=null}">
	<tr>
		<td class="label">
			<fmt:message key="kmContentsApply.fileName" />
		</td>
		<td class="content" colspan="3">
			<eoms:attachment name="kmContentsApplyForm" property="fileName" scope="request" idField="fileName" appCode="kmmanager" viewFlag="Y"/>
		</td>
	</tr>
	</c:if>

</table>
<input type="hidden" name="applyId" id="applyId" value="${kmContentsApplyForm.id}">

<table>
	<tr>
		<td>
			<c:if test="${kmContentsApplyForm.applyUser == sessionScope.sessionform.userid}">
		    	<input type="button" class="btn" value="修改" onclick="javascript:onSubmitEdit();"/>&nbsp;
		    	<input type="button" class="btn" value="删除" onclick="javascript:onSubmitDele();"/>&nbsp;
		    </c:if>
		    <c:if test="${kmContentsApplyForm.applyUser != sessionScope.sessionform.userid || 'admin'==sessionScope.sessionform.userid}">
		    	<input type="button" class="btn" value="添加知识" onclick="javascript:onSubmitAdd();"/>&nbsp;
		    </c:if>
		</td>
	</tr>
</table>

<br>
</div>

  <!-- 对应的知识信息 -->
  <div id="content-info" class="tabContent">
  	<display:table name="kmContentsList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}"
		class="table kmContentsList" export="false"
		requestURI="${app}/kmmanager/kmContentss.do?method=search" 
		decorator="com.boco.eoms.km.knowledge.displaytag.support.KmContentsDisplaytabDecorator">

		<display:column sortable="false" headerClass="sortable" titleKey="kmContents.themeId">
			<eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao" />
		</display:column>

		<display:column sortable="false" property="contentTitle" titleKey="kmContents.contentTitle" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="false" headerClass="sortable" titleKey="kmContents.createUser">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="false" property="createTime" titleKey="kmContents.createTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="false" headerClass="sortable" titleKey="kmContents.contentStatus">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmContentsList.contentStatus}" beanId="id2nameXML" />
		</display:column>

		<display:column sortable="false" property="contentKeys" titleKey="kmContents.contentKeys" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column title="查看" headerClass="imageColumn">
			<a href="javascript:var id = '${kmContentsList.contentId }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detail';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url">
				<img src="${app}/images/icons/search.gif" /> </a>
		</display:column>

		<display:setProperty name="paging.banner.item_name" value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />

	</display:table>

  </div>

</fmt:bundle>
</div>
</html:form>

<script type="text/javascript">
	var readTree;
	Ext.onReady(function(){
			var tabs = new Ext.TabPanel('info-page');
			tabs.addTab('apply-info', '申请信息 ');
        	tabs.addTab('content-info', '申请对应知识 ');
    		tabs.activate(0);
	});
	
	//添加知识
	function onSubmitAdd(){
		var arr = [<c:forEach items="${kmContentsList1}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.createUser}'</c:forEach>];
		if(arr[0]!=null){
			alert('已经添加了相应的知识');
			return false;
		}
	    var applyId = document.getElementById("applyId").value;
	    var url='${app}/kmmanager/kmContentss.do?method=add&applyId='+applyId;
	    window.location.href(url);
    }
   
    //修改
    function onSubmitEdit(){
	    var id = document.getElementById("applyId").value;
	    var url='${app}/kmmanager/kmContentsApplys.do?method=edit&id=' + id;
	    window.location.href(url);
    }
	
	 //删除
    function onSubmitDele(){
	    var id = document.getElementById("applyId").value;
	    var url='${app}/kmmanager/kmContentsApplys.do?method=remove&id=' + id;
	    if(confirm('确定要删除该申请吗?')){
	    	 window.location.href(url);
	    }
    }
</script>

<%@ include file="/common/footer_eoms.jsp"%>