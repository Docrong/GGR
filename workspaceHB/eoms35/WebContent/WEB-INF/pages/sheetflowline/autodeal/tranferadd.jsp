<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<script type="text/javascript" src="../../sheetfllowline/css/functions.js"></script>
<%String jsonString = (String)request.getAttribute("jsonString");
  String jsonString1 = (String)request.getAttribute("jsonString1");
 %>

<script language="javascript">
Ext.onReady(function(){

	var	userTreeAction='${app}/role/tawSystemRoles.do?method=listSubrolesReUsers&roleId=191';
	userViewer = new Ext.JsonView("user-list",	
		'<div id="user-{id}" class="viewlistitem-user">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div>请选择用户</div>'								
		}
	);

		 var l = '<%=jsonString%>';
		userViewer.jsonData = eoms.JSONDecode(l);
		userViewer.refresh();
		userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:userTreeAction,treeRootId:'1',treeRootText:'用户',treeChkMode:'single',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'operateUserId' 
	});
	
	var	T2SubRoleTreeAction='${app}/role/tawSystemRoles.do?method=listSubrolesReUsers&roleId=192';
	var roleViewer = new Ext.JsonView("role-list",	
		'<div id="subrole-{id}" class="viewlistitem-subrole">{name}</div>',
		{ 
			multiSelect: true,		
			emptyText : '<div>请选择子角色</div>'								
		}
	);

 		var s = '<%=jsonString1%>';
		roleViewer.jsonData = eoms.JSONDecode(s);
		roleViewer.refresh();
		var roleTree = new xbox({
		btnId:'roleTreeBtn',dlgId:'dlg-user',	
		treeDataUrl:T2SubRoleTreeAction,treeRootId:'1',treeRootText:'T2子角色',treeChkMode:'single',treeChkType:'subrole',
		viewer:roleViewer,saveChkFldId:'tranferObject' 
	});
	
});
function SubmitCheck(){

  frmReg = document.forms[0];
  if(frmReg.operateUserId.value==''){
    alert("请选择操作人");
    return false;
  }
   if(frmReg.tranferObject.value==''){
    alert("请输入移交对象");
    return false;
  }
  if(frmReg.faultFirstDealDesc.value==''){
    alert("请输入故障处理情况");
    return false;
  }
 if(frmReg.tranferReason.value=='')
  {
     alert("请输入移交理由");
     return false;
  }
  
 return true;
}




</script>

<form name="addform" method="post" action="../sheetflowline/autoDealsop.do?method=saveObject" onsubmit="return SubmitCheck();">

<table width="500" class="formTable">
  <caption>移交下一级信息</caption>
   <tr>
    <td class="label">
     	 <input type="button" name="userTreeBtn" id="userTreeBtn" value="操作人" class="button" />
    </td>
    <td>
   		<div id="user-list" class="viewer-list"></div>
    	 <input type="hidden"  class="hidden" name="operateUserId" id="operateUserId"   />
    	
    </td>
    <td class="label">
     <input type="button" name="roleTreeBtn" id="roleTreeBtn" value="移交对象	" class="button" />
    	
    </td>
    <td>
    	  <div id="role-list" class="viewer-list"></div>
		  <input type="hidden" name="tranferObject" id="tranferObject" class="text"   />
		  <input type="hidden" name="alarmId" id="alarmId" class="hidden"  value="${alarmId}" />
		  <input type="hidden" name="alarmTitle" id="alarmTitle" class="hidden"  value="${alarmTitle}" />
		  <input type="hidden" name="autoDealTask" id="autoDealTask" class="hidden"  value="${autoDealTask}" />
		  <input type="hidden" name="autoDealMode" id="autoDealMode" class="hidden"  value="${autoDealMode}" />
		   <input type="hidden" name="id" id="id" class="hidden"  value="${object.id}" />
    </td>
  </tr>
  <tr>
  
    <td class="label">
     故障处理情况
    	
    </td>
    <td colspan="3">
   		<textarea name="faultFirstDealDesc" id="faultFirstDealDesc" class="textarea max"
		  		>${object.faultFirstDealDesc}</textarea>		
   </td>
   </tr>
   <tr>
    <td class="label">
    	移交理由
    </td>
    <td colspan="3">
    	<textarea name="tranferReason" id="tranferReason" class="textarea max"
		  		>${object.tranferReason}</textarea>	
    </td>
  </tr>

</table>
<br>
<input type="submit" value="保存" name="B1"  Class="submit">


</form>

<%@ include file="/common/footer_eoms.jsp"%>

    	
