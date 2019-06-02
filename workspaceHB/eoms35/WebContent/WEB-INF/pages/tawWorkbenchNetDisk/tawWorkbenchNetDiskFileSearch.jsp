<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<style type="text/css">
  	body{background-image:none;}
</style>

<script type="text/javascript">
var userByDeptTree='${app}/xtree.do?method=userFromDept';//部门用户树

var v;
Ext.onReady(function(){
	v = new eoms.form.Validation({form:"theform"});
	
	//部门用户树
	userByDeptTree = new xbox({
		btnId:'userByDeptTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userByDeptTree,treeRootId:'-1',treeRootText:'${eoms:a2u("人员")}',treeChkMode:'',treeChkType:'user',
		showChkFldId:'uploadUserName',saveChkFldId:'uploadUser' 
	});
})
 
</script>

<form id="theform" action="${app}/workbench/netdisk/searchFiles.do?method=searchDo" method="post" styleId="theform">
<table class="formTable" width="75%">
	<%
		String useridsearch = request.getAttribute("userIdSearch")==null?"":(String)request.getAttribute("userIdSearch"); 
	%>
	<input type="hidden" name="userIdSearch" value="<%=useridsearch%>">

	<tr class="tr_show">
		<td class="label" align="right">${eoms:a2u('文件名称')}</td>
		<td colspan=3>
		<input type="text" name="fileName">
	   </td>
    </tr>
    
    <tr class="tr_show">
		<td class="label" align="right">${eoms:a2u('发布开始时间')}</td>
				 <td>
		           <input type="text" name="uploadStratTime" id="uploadStratTime" class="text"
					readonly="readonly"
					alt="vtype:'lessThen',link:'uploadEndTime',vtext:'${eoms:a2u('开始时间不能晚于结束时间！')}'"
					onclick="popUpCalendar(this, this,null,null,null,true,-1);"/>
		         </td>

		         <td class="label" align="right">${eoms:a2u('发布结束时间')}</td>
		         <td>
		           <input type="text" name="uploadEndTime" id="uploadEndTime" class="text"
					readonly="readonly"
					alt="vtype:'moreThen',link:'uploadStratTime',vtext:'${eoms:a2u('结束时间不能早于开始时间！')}'"
					onclick="popUpCalendar(this, this,null,null,null,true,-1);" />
		         </td>
	   </td>
    </tr>
    
    <tr class="tr_show">
		<td class="label" align="right">${eoms:a2u('发布人')}</td>
		<td colspan=3>
		<input type="button" value="${eoms:a2u('部门人员选择')}" id="userByDeptTreeBtn" class="btn" />
  		<input type="text"  name="uploadUserName"  value="" id ="uploadUserName" class="text" />
  		<input type="hidden" id="uploadUser" value="" name="uploadUser" />
	   </td>
    </tr>

</table>
<table border="0" width="70%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
                    <html:submit property="strutsButton" styleClass="button">
                     ${eoms:a2u('查询')}
                    </html:submit>
                    &nbsp;&nbsp;</td>
  </tr>
  </table>
  
</form>

<%@ include file="/common/footer_eoms.jsp"%>