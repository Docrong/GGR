<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<style type="text/css">
.small {
	width: 10px;
}
</style>

<script type="text/javascript">
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


<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

    <caption>
		<div class="header center"><b><fmt:message key="kmContents.form.heading"/>&nbsp;本人建单</b></div>
	</caption>

	<display:table name="kmContentsList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}"
		class="table kmContentsList" export="false"
		requestURI="${app}/kmmanager/kmContentss.do" sort="list"
		partialList="true" size="resultSize"
		decorator="com.boco.eoms.km.knowledge.displaytag.support.KmContentsDisplaytabDecorator">

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.themeId">
			<eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao" />
		</display:column>

		<display:column sortable="true" property="contentTitle" titleKey="kmContents.contentTitle" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmContents.contentStatus">
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
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>
