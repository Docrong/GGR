<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<%@ page import="com.boco.eoms.testcard.controller.TawTestcardApplyForm"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<% 
TawTestcardApplyForm ApplyForm = (TawTestcardApplyForm)request.getAttribute("tawTestcardApplyForm");
String leaveid = StaticMethod.nullObject2String(ApplyForm.getLeaveid());
String cardpackage = StaticMethod.nullObject2String(ApplyForm.getCardpackage());
int temp = StaticMethod.nullObject2int(ApplyForm.getCardtype());
String temp0="",temp1="",temp2="",temp3="",temp4="",temp5="",temp6="";
switch(temp){
case 0:
temp0 = "checked";
break;
case 1:
temp1 = "checked";
break;
case 2:
temp2 = "checked";
break;
case 3:
temp3 = "checked";
break;
case 4:
temp4 = "checked";
break;
case 5:
temp5 = "checked";
break;
case 6:
temp6 = "checked";
break;
}
%>
<html>
	<head>
		<title>申请单 修改</title>
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
	var userStr = '${jsonAudit}';
	userViewer.jsonData = eoms.JSONDecode(userStr);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
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
	document.forms[0].strutsAction.value == 2;
	return true;
}


</script>

<script language="javascript">
function onSubmitAudit(){
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
    window.location.href='<%=request.getContextPath()%>/testcard/TawTestcardApply/submitaudit.do?formId='+<%=ApplyForm.getId()%>;
	return true;
}

function onSearchCard(){
    window.location.href='<%=request.getContextPath()%>/testcard/TawTestcardApply/searchcard.do?formId='+<%=ApplyForm.getId()%>+'&leaveid='+<%=leaveid%>;}

</script>

<script type="text/javascript">
window.onload = function(){
	document.forms[0].leaveid.value = <%=leaveid%>;
	document.forms[0].cardpackage.value = <%=cardpackage%>;
}
</script>
<body>
<center>
<html:form  method="post" action="/TawTestcardApply/save" styleId="tawTestcardApplyForm">
<table cellSpacing="0" cellPadding="0" width="85%" border="0">
<tr>
    <td align="right" width="100%" bgColor="#f5f5f5">
        <div align="center">
            <table cellSpacing="0" borderColorDark="#ffffff" cellPadding="2" width="100%" borderColorLight="#808080" border="1">
                 <tr> 
                    <td  colspan="4" valign="middle" bgcolor="#E5EDF8" align="center">
							申请单 修改
                    </td>
                 </tr>
<logic:present name="tawTestcardApplyForm" scope="request">
        <tr>
                <td noWrap width="100" bgcolor="#E5EDF8" class= "clsfth">卡类型</td>
                <td colspan = 3 width="380" align="center">
                  <input type="radio" id="cardType1" name="cardtype" value="1" <%=temp1%>/> 国际来访卡&nbsp;
                  <input type="radio" id="cardType0" name="cardtype" value="0" <%=temp0%>/> 国际出访卡&nbsp;
                  <input type="radio" id="cardType2" name="cardtype" value="2" <%=temp2%>/> 省际来访卡&nbsp;
                  <input type="radio" id="cardType3" name="cardtype" value="3" <%=temp3%>/> 省际出访卡&nbsp;
                  <input type="radio" id="cardType5" name="cardtype" value="5" <%=temp5%>/> 省内来访卡&nbsp;
                  <input type="radio" id="cardType6" name="cardtype" value="6" <%=temp6%>/> 省内出访卡&nbsp;
                  <input type="radio" id="cardType4" name="cardtype" value="4" <%=temp4%>/> 本地测试卡&nbsp;
        	</td>
        </tr>
        <tr>
			<td noWrap width="80" bgcolor="#E5EDF8" class="label">
				所属公司
			</td>
			<td width="380" id="leaveid">
				<eoms:comboBox name="leaveid" id="a1" sub="a2" initDicId="10401"/>
			</td>
          	<td class="clsfth">
				测试卡套餐类型
          	</td>
          	<td width="380" bgcolor="#E5EDF8" align="center">
						<html:select property="cardpackage" style="width: 4.0cm;" value=""
							onchange="showbutton(this.value);" title="套餐类型">
							<html:optionsCollection name="tawTestcardApplyForm"
								property="beCollep" />
						</html:select>
          	</td>
        </tr>
				<tr>
					<td noWrap bgcolor="#E5EDF8" width="80" class="label">
						申请单主题
					</td>
					<td width="380" colspan="3">
						<html:text styleClass="clstext" property="formName" size="60"
							title="申请单主题" />
					</td>
				</tr>
				<tr>
					<td noWrap bgcolor="#E5EDF8" width="80" class="label">
						审批者
					</td>
					<td width="380" colspan="3">
						<div id="user-list" class="viewer-box"></div>
						<input type="button" value="选择审核人" id="userTreeBtn" class="btn"/>
						<html:hidden property="auditJson" styleId="auditJson"/>
					</td>
				</tr>
				<tr>
					<td noWrap bgcolor="#E5EDF8" width="80" class="label">
						申请原因
					</td>
					<td width="380" colspan="3">
						<html:textarea property="applyreason" rows="4" cols="88" title="申请原因" />
					</td>
				</tr>
				<html:hidden property="id"/>
				<html:hidden property="strutsAction"/>
		
</logic:present>
			</table>
<table border="0" width="95%" cellspacing="1" class="listTable">
<tr>
	<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
		<b>
			已选择的测试卡 列表&nbsp; 
 		</b> 
	</td>
</tr>
<tr bgcolor="#FFFFFF">
       <td nowrap class="label"  align="center" height="25">
          存放公司
        </td>
        <td nowrap class="label"  align="center" height="25">
          手机号码
        </td>
</tr>
   <logic:iterate id="tawTestcard" name="tawTestcard" type="com.boco.eoms.testcard.model.TawTestcard">
<tr bgcolor="#FFFFFF">
     <td nowrap bgcolor="#E5EDF8" align="center">
                    <bean:write name="tawTestcard" property="leavename" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcard" property="phoneNumber" scope="page"/>
    </td>

</tr>
    </logic:iterate>
</table>
<table bgcolor="#f2f2f2" height="30" cellpadding="0" cellspacing="0" border="0" width="100%">
	<tr align="left" valign="middle">
		<td>
     		<input type="button" value="修改电话号码" onclick="onSearchCard();"/>&nbsp;
     		<input type="button" value="提交审批" onclick="onSubmitAudit();"/>&nbsp;
			<html:submit styleClass="clsbtn2" onclick="return check();">
        		保存修改
      		</html:submit>
      		<html:submit styleClass="clsbtn2" onclick="window.history.back(-1)">
        		返回
      		</html:submit>
		</td>
	</tr>	
</table>
        </div>
	</td>
</tr>
</table>
</html:form>
</center>
</body>
</html>
