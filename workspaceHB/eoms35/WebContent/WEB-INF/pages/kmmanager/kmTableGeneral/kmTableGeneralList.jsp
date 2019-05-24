<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<jsp:directive.page	import="com.boco.eoms.km.table.util.KmTableGeneralConstants" />

<script>
function search(){  
    var themeId = eoms.$('themeId').value;
    var queryCon=" where 1=1";
    if(themeId!=null&&themeId!=""){
       queryCon = queryCon + " and kmTableGeneral.themeId like '"+themeId+"%'";
    }
    var tableChname = eoms.$('tableChname').value;
     if(tableChname!=null&&tableChname!=""){
       queryCon=queryCon+" and kmTableGeneral.tableChname like '%"+tableChname+"%'";
    }
    var url='${app}/kmmanager/kmTableGenerals.do?method=searchX&whereStr=yy';
	location.href=url;
} 
</script>

<fmt:bundle	basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<html:form action="/kmTableGenerals.do?method=searchX" styleId="kmTableGeneralForm" method="post">

		<eoms:xbox id="tree"
			dataUrl="${app}/kmmanager/kmTableThemes.do?method=getUsedFirstTree"
			rootId="<%=KmTableGeneralConstants.TREE_ROOT_ID%>" rootText="知识分类"
			valueField="themeId" handler="themeName" textField="themeName"
			checktype="forums" single="true">
		</eoms:xbox>

		<table align="center">
			<tr>
				<td>
					<fmt:message key="kmTableGeneral.themeId" />：
				</td>
				<td class="content">
					<input type="text" id="themeName" name="themeName" class="text" readonly="readonly"
						value='<eoms:id2nameDB id="${kmTableGeneralForm.themeId}" beanId="kmTableThemeDao" />'
						alt="allowBlank:false'" />
					<input type="hidden" id="themeId" name="themeId" value="${kmTableGeneralForm.themeId}" />
				</td>
				<td>
					<fmt:message key="kmTableGeneral.tableChname" />：
				</td>
				<td>
					<input type="text" id="tableChname" name="tableChname" class="text" value="${kmTableGeneralForm.tableChname}">
				</td>
				<td>
					<input type="submit" class="btn" value="<fmt:message key="button.query"/>" />&nbsp;&nbsp;
					<input type="reset" class="btn"	value="<fmt:message key="button.reset"/>" />&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</html:form>

	<content tag="heading">
	    <b>模型定义<fmt:message key="kmTableGeneral.list.heading" /></b>
	</content>

	<display:table name="kmTableGeneralList" cellspacing="0"
		cellpadding="0" id="kmTableGeneralList" pagesize="${pageSize}"
		class="table kmTableGeneralList" export="false"
		requestURI="${app}/kmmanager/kmTableGenerals.do?method=search"
		sort="list" partialList="true" size="resultSize">

		<display:column sortable="true" property="tableChname" titleKey="kmTableGeneral.tableChname"			
			paramId="id" paramProperty="id" 
			headerClass="sortable" href="${app}/kmmanager/kmTableGenerals.do?method=edit" />
			
		<display:column sortable="true" property="tableName" titleKey="kmTableGeneral.tableName"			
			paramId="id" paramProperty="id" 
			headerClass="sortable" href="${app}/kmmanager/kmTableGenerals.do?method=edit" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmTableGeneral.themeId">
			<eoms:id2nameDB id="${kmTableGeneralList.themeId}" beanId="kmTableThemeDao" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmTableGeneral.createUser">
			<eoms:id2nameDB id="${kmTableGeneralList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmTableGeneral.createDept">
			<eoms:id2nameDB id="${kmTableGeneralList.createDept}" beanId="tawSystemDeptDao" />
		</display:column>

		<display:column sortable="true" property="createTime" titleKey="kmTableGeneral.createTime" 
		    paramId="id" paramProperty="id"
			headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" titleKey="kmTableGeneral.isOpen">
			<eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${kmTableGeneralList.isOpen}" beanId="id2nameXML" />
		</display:column>

		<display:column sortable="true" headerClass="sortable" titleKey="kmTableGeneral.isCreated">
			<eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${kmTableGeneralList.isCreated}" beanId="id2nameXML" />
		</display:column>

		<display:column titleKey="kmTableGeneral.layout" headerClass="imageColumn">
			<c:if test="${kmTableGeneralList.isCreated == 1}">
				<a href="javascript:if(confirm('确定要调整页面?')){
		                        var id = '${kmTableGeneralList.id }';
		                        var url='${app}/kmmanager/kmTableColumns.do?method=addLayout';
		                        url = url + '&TABLE_ID=' + id ;
		                        location.href=url}">
					<img src="${app}/images/icons/table.png" />
				</a>
			</c:if>
		</display:column>

		<display:setProperty name="paging.banner.item_name" value="kmTableGeneral" />
		<display:setProperty name="paging.banner.items_name" value="kmTableGenerals" />
	</display:table>
	
	<br>
	
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmTableGenerals.do?method=add'/>'"
		value="<fmt:message key="button.add"/>" />
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>