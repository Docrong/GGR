<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<style type="text/css">
.small {
	width:10px;
}
</style>
<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';		
	roleUserTree = new xbox({
		btnId:'createUserName',dlgId:'dlg-role-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'用户',treeChkMode:'',treeChkType:'user',
		treeChkMode:'single',
		showChkFldId:'createUserName',
		saveChkFldId:'createUser' 
	});	
})

function onSubmit(){ 
		var arr = [<c:forEach items="${kmContentsSubscribeList}" var="obj" varStatus="status"><c:if test="${status.count > 1}">, </c:if>'${obj.createUser}'</c:forEach>];
		for(var i=0;i<arr.length;i++){
			if(document.forms[0].createUser.value==arr[i]){
				alert('已经被订阅过了');
				return false;
			}
		}	
    	if(document.forms[0].createUser.value==""){
    		alert('请选择要订阅用户');
    		return false; 
    	}
    	
        document.forms[0].submit();
        return true;
    }
</script>

<html:form action="/kmContentsSubscribes.do?method=saveSubscribe" method="post" styleId="kmContentsForm">
	<input type="hidden" name="status" id="status" />

	<table>
		<tr>
			<td>增加订阅</td>
			<td>
			<input type="text"   id="createUserName" name="createUserName" class="text" readonly="readonly" value="" />
			<input type="hidden" id="createUser"  name="createUser" value="" />	
			</td>
		</tr>
		<tr>
			<td>
			<input type="button" value="提交订阅" onclick="javascript:onSubmit();" class="button" />
			</td>
		</tr>
	</table>
	
	<display:table name="kmContentsSubscribeList" cellspacing="0" cellpadding="0"
		id="kmContentsList" pagesize="${pageSize}" class="table kmContentsCollectList"
		export="false"
		requestURI="${app}/kmmanager/kmContentsSubscribes.do"
		>

		<display:column sortable="true" headerClass="sortable" title="订阅人">
			<eoms:id2nameDB id="${kmContentsList.subscribeUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column property="createTime" title="订阅时间" sortable="true"
			paramId="id" paramProperty="id" headerClass="sortable" />

		<display:column sortable="true" headerClass="sortable" title="订阅的创建者">
			<eoms:id2nameDB id="${kmContentsList.createUser}" beanId="tawSystemUserDao" />
		</display:column>
		
		<display:column title="删除订阅" headerClass="imageColumn">
		    <a href="javascript:if(confirm('确定要删除该订阅？')){
		                        var id = '${kmContentsList.id }';
		                        var url='${app}/kmmanager/kmContentsSubscribes.do?method=removeCreateUser';
		                        url = url + '&ID=' + id ;
		                        location.href=url}"><img src="${app}/images/icons/list-delete.gif"/></a>		    
		</display:column>
		
		<display:setProperty name="paging.banner.item_name"  value="kmContents" />
		<display:setProperty name="paging.banner.items_name" value="kmContentss" />
	</display:table>
	
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>
