<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page
	import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*,com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<html>
	<head>
		<title>申请单 新增</title>
		<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>

<script language="JavaScript"
			src="<%=request.getContextPath()%>/css/area.js"></script>
</head>
<body>
<html:form method="post" action="/TawTestcardApply/save" styleId="tawTestcardApplyForm">

<%
TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) session
					.getAttribute("sessionform");
String userName = saveSessionBeanForm.getUsername();
String userId = saveSessionBeanForm.getUserid();
String deptId = String.valueOf(saveSessionBeanForm.getDeptid())
					.toString();
%>
<html:hidden property="userId" value="userId" />
<html:hidden property="deptId" value="deptId" />
			<script language="JavaScript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'tawTestcardApplyForm'});
	
	// 人员树
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",
		'<div id="user-{id}" class="viewlistitem-{nodeType}">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div>未选择审核人</div>'
		}
	);
	var userStr = '[]';
	userViewer.jsonData = eoms.JSONDecode(userStr);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'single',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'auditJson', returnJSON:'true'
	});
})

function check(){
	if(document.forms[0].leaveid.value==""){
		alert("请选择所属分公司");
		return false;
	}
	if(document.forms[0].cardpackage.value==""){
		alert("请选择套餐类型");
		return false;
	}
	if(document.forms[0].formName.value==""){
		alert("请填写申请单主题");
		return false;
	}
	if(document.forms[0].auditJson.value==""){
		alert("请选择审批者");
		return false;
	}
	if(document.forms[0].applyreason.value==""){
		alert("填写申请原因");
		return false;
	}
	return true;
}
function showbutton(v){
  if(v==35){
    ms0.style.display="none";
    ms1.style.display="block";
    im0.style.display="none";
    im1.style.display="block";
    tr1.style.display="block";
  }
  else{
    ms0.style.display="block";
    ms1.style.display="none";
    im0.style.display="block";
    im1.style.display="none";
    tr1.style.display="none";
  }
}

 function defaultButton(bool){
     if (!bool){
       clickUnlock();
       return false;
     }else {
       document.forms[0].submit();
     }
   }
</script>
			<br>

			<html:hidden property="strutsAction" />
			<html:hidden property="id" />

			<table width="100%" class="formTable" align="center">
			<center>
				<caption>
					<b> 申请单 新增
				</caption>
				<tr>

					<td noWrap width="80" class="label">
						卡类型
					</td>
					<td colspan=3 width="80%" align="left">
						<input type="radio" name="cardtype" value="2" checked="checked"/>
						省际来访卡&nbsp;
						<input type="radio" name="cardtype" value="3"/>
						省际出访卡&nbsp;
						<input type="radio" name="cardtype" value="5"/>
						省内来访卡&nbsp;
						<input type="radio" name="cardtype" value="6"/>
						省内出访卡&nbsp;
						<input type="radio" name="cardtype" value="4"/>
						本地测试卡&nbsp;
						<input type="radio" name="cardtype" value="1"/>
						国际来访卡&nbsp;
						<input type="radio" name="cardtype" value="0"/>
						国际出访卡&nbsp;
					</td>
				</tr>
				<tr>
					<td noWrap width="80" class="label">
						所属公司
					</td>
					<td width="380" id="leaveid">
						<eoms:comboBox name="leaveid" id="a1" sub="a2" initDicId="10401"/>
					</td>

					<td class="label">
						套餐类型
					</td>
					<td>
						<html:select property="cardpackage" style="width: 4.0cm;" value=""
							onchange="showbutton(this.value);" title="套餐类型">
							<html:optionsCollection name="tawTestcardForm"
								property="beCollep" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td noWrap width="80" class="label">
						申请单主题
					</td>
					<td width="380" colspan="3">
						<html:text styleClass="clstext" property="formName" size="60"
							title="申请单主题" />
					</td>
				</tr>
				<tr>
					<td noWrap width="80" class="label">
						审批者
					</td>
					<td width="380" colspan="3">
						<div id="user-list" class="viewer-box"></div>
						<input type="button" value="选择审核人" id="userTreeBtn" class="btn"/>
						<html:hidden property="auditJson" styleId="auditJson"/>
					</td>
				</tr>
				<tr>
					<td noWrap width="80" class="label">
						申请原因
					</td>
					<td width="380" colspan="3">
						<html:textarea property="applyreason" rows="4" cols="88" title="申请原因" />
					</td>
				</tr>
				<tr>
					<td colspan=4 align="left">
						<html:submit styleClass="button" onclick="return check();">
        			保存
      			</html:submit>
						<html:reset styleClass="button">
                        	重置
      			</html:reset>
						&nbsp;&nbsp;

					</td>
				</tr>
			</table>

			</center>
		</html:form>

<%@ include file="/common/footer_eoms.jsp"%>