<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>

<%
	String auditName = StaticMethod.nullObject2String(request.getAttribute("auditName"));
	String excelUrl = StaticMethod.nullObject2String(request.getAttribute("excelUrl"));
%>

<style type="text/css">
.small {
	width: 10px;
}
</style>

<script type="text/javascript">

<%	if(!"".equals(auditName)){ %>
		alert('该条记录已提交 <%=auditName%> 审核');
<%	} %>

	function choose(checkbox){
		eoms.util.checkAll(checkbox,'ids');
	}

	var checkflag = "false";
	function choose() {
		var objs = document.getElementsByName("ids");
		if(checkflag=="false"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = true;
      			checkflag="true";
			}
		}else if(checkflag=="true"){
			for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" )
      			objs[i].checked = false;
      			checkflag="false"
			}
		}
	}
</script>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<display:table name="kmContentsList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}"
		class="table kmContentsList" export="false"
		requestURI="${app}/kmmanager/kmContentss.do?method=search" sort="list"
		partialList="true" size="resultSize"
		decorator="com.boco.eoms.km.knowledge.displaytag.support.KmContentsDisplaytabDecorator">

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.themeId">
			<eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao" />
		</display:column>

		<display:column sortable="true" property="contentTitle" titleKey="kmContents.contentTitle" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.createUser">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.createDept">
			<eoms:id2nameDB id="${kmContentsList.createDept}" beanId="tawSystemDeptDao" />
		</display:column>

		<display:column sortable="true" property="createTime" titleKey="kmContents.createTime" format="{0,date,yyyy-MM-dd HH:mm:ss}"
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.contentStatus">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmContentsList.contentStatus}" beanId="id2nameXML" />
		</display:column>

		<display:column title="查看" headerClass="imageColumn">
			<a href="javascript:var id = '${kmContentsList.id }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detail';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url">
				<img src="${app}/images/icons/search.gif" /> </a>
		</display:column>

		<display:column title="收藏" headerClass="imageColumn">
			<a href="javascript:if(confirm('确定要收藏该知识?')){
		                        var id = '${kmContentsList.id }';
		                        var tableId = '${kmContentsList.tableId}';
		                        var themeId = '${kmContentsList.themeId}';
		                        var url='${app}/kmmanager/kmContentsCollects.do?method=collect';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
		                        location.href=url}">
				<img src="${app}/images/icons/save.gif" /> </a>
		</display:column>

		<display:setProperty name="paging.banner.item_name" value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />

	</display:table>
</fmt:bundle>

<table cellpadding="0" cellspacing="0" width="98%">
	<tr>
		<td width="100%" height="32" align="right">
			<html:link href="<%=excelUrl%>" scope="page">导出EXCEL</html:link>
		</td>

	</tr>
</table>

<%@ include file="/common/footer_eoms.jsp"%>
