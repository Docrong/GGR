<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<jsp:directive.page import="com.boco.eoms.km.table.util.KmTableGeneralConstants"/>

<script type="text/javascript">
	function onSubmit(){
		var arr = [<c:forEach items="${kmContentsSubscribeTableList}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.themeId}'</c:forEach>];
		for(var i=0;i<arr.length;i++){
			if(document.forms[0].themeId.value==arr[i]){
				alert('已经被订阅过了');
				return false;
			}
		}	
	
    	if(document.forms[0].themeName.value==""){
    		alert('请选择要订阅分类');
    		return false; 
    	}
        document.forms[0].submit();
        return true;
   	}
   	
function search(){
	var url="${app}/kmmanager/kmContentsSubscribes.do?method=getNodesRadioTree";
    window.location.href = url;
}
</script>

<html:form action="/kmContentsSubscribes.do?method=saveSubscribeTable" styleId="kmContentsForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

	<eoms:xbox id="tree" dataUrl="${app}/kmmanager/kmTableThemes.do?method=getNodesRadioTreeForAll" 
	  	rootId="<%=KmTableGeneralConstants.TREE_ROOT_ID%>" 
	  	rootText="知识分类" 
	  	valueField="themeId" handler="themeName"
		textField="themeName"
		checktype="forums" single="true" 
	  />
	<table>
		<tr>
			<td>增加订阅</td>
			<td>
				<input type="text"   id="themeName" name="themeName" class="text" readonly="readonly"  
					value='<eoms:id2nameDB id="${kmTableGeneralForm.themeId}" beanId="kmTableThemeDao" />' alt="allowBlank:false'"/>
				<input type="hidden" id="themeId"   name="themeId" value="${kmTableGeneralForm.themeId}" />
			</td>
		</tr>
		<tr>
			<td>
			<input type="button" value="提交订阅" onclick="javascript:onSubmit();" class="button" />
			</td>
		</tr>
	</table>
	<display:table name="kmContentsSubscribeTableList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}" class="table kmContentsCollectList"
		export="false"
		requestURI="${app}/kmmanager/kmContentsSubscribes.do?method=saveSubscribeTable"
		>

		<display:column sortable="true" headerClass="sortable" title="订阅人">
			<eoms:id2nameDB id="${kmContentsList.subscribeUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column property="createTime"  title="订阅时间" sortable="true" 
			paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" title="订阅的分类">
			<eoms:id2nameDB id="${kmContentsList.themeId}" beanId="kmTableThemeDao" />
		</display:column>
		
		<display:column title="删除订阅" headerClass="imageColumn">
		    <a href="javascript:if(confirm('确定要删除该订阅?')){
		                        var id = '${kmContentsList.id }';
		                        var url='${app}/kmmanager/kmContentsSubscribes.do?method=removeTable';
		                        url = url + '&ID=' + id ;
		                        location.href=url}"><img src="${app}/images/icons/list-delete.gif"/></a>		    
		</display:column>
		
		<display:setProperty name="paging.banner.item_name"  value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />
	</display:table>
</fmt:bundle>

</html:form>

<%@ include file="/common/footer_eoms.jsp"%>