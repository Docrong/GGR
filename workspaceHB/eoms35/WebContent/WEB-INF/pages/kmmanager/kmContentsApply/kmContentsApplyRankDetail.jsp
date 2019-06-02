<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<html:form action="/kmContentsApplys.do?method=save" styleId="kmContentsOpinionForm" method="post"> 
<div id="info-page" >
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
  <!-- 查看内容信息 -->
  <div id="apply-info" class="tabContent">
<content tag="heading">
	<fmt:message key="kmContentsApply.list.heading" />
</content>

	<display:table name="kmContentsApplyList" cellspacing="0" cellpadding="0"
		id="kmContentsApplyList" pagesize="${pageSize}" class="table kmContentsApplyList"
		export="false"
		requestURI="${app}/kmmanager/kmContentsApplys.do?method=searchX"
		sort="list" partialList="true" size="resultSize">

	<display:column sortable="true" headerClass="sortable" titleKey="kmContentsApply.applyTable">
		<eoms:id2nameDB id="${kmContentsApplyList.applyTable}" beanId="kmTableThemeDao" />
	</display:column>

	<display:column sortable="true" headerClass="sortable" titleKey="kmContentsApply.applyTheme">
		<eoms:id2nameDB id="${kmContentsApplyList.applyTheme}" beanId="kmTableThemeDao" />
	</display:column>
		
	<display:column property="applyTitle" sortable="true" 
		headerClass="sortable" titleKey="kmContentsApply.applyTitle"  paramId="id" paramProperty="id"/>

	<display:column  sortable="true" headerClass="sortable" titleKey="kmContentsApply.applyUser"  >
		<eoms:id2nameDB id="${kmContentsApplyList.applyUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column property="applyDate" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmContentsApply.applyDate"  paramId="id" paramProperty="id"/>
	
	<display:column title="查看" headerClass="imageColumn">
		    <a href="javascript:var id = '${kmContentsApplyList.id}';
		                        var url='${app}/kmmanager/kmContentsApplys.do?method=detail';
		                        url = url + '&id=' + id ;
		                        location.href=url">
		       <img src="${app}/images/icons/search.gif"/></a>	
	</display:column>	
		<display:setProperty name="paging.banner.item_name" value="kmContentsApply" />
		<display:setProperty name="paging.banner.items_name" value="kmContentsApplys" />
	</display:table>

	<c:out value="${buttons}" escapeXml="false" />
</div>

  
  <!-- 对应的柱状图 -->
  <div id="chart-info2" class="tabContent">
  <%if("".equals((String)request.getAttribute("chartName"))){ %>
  	没有记录
  <%}else{ %>
  	<img src="${app}/kmpictures/${chartName }">
  	<%} %>
  </div>
  
</fmt:bundle>
</div>
</html:form>

<script type="text/javascript">
	var readTree;
	Ext.onReady(function(){
			var tabs = new Ext.TabPanel('info-page');
			tabs.addTab('apply-info', '知识申请列表 ');
        	tabs.addTab('chart-info2', '日申请量柱状图 ');
    		tabs.activate(0);
	});
	
   
    //修改
    function onSubmitApply(){
	    var id = document.getElementById("applyId").value;
	    var url='${app}/kmmanager/kmContentsApplys.do?method=applyAgain&id=' + id;
	    window.location.href(url);
    }
	
</script>

<%@ include file="/common/footer_eoms.jsp"%>