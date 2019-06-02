<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms"%>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c"%>
<%
String s = (String) request.getAttribute("s");
%>
<%
String a = (String) request.getAttribute("a");
%>
<%
String r = (String) request.getAttribute("r");
%>
<html:html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>报表设置修改</title>
	<link rel="stylesheet" type="text/css" media="all"
		href="<%=request.getContextPath()%>/styles/red/theme.css" />
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/css/wsstyle.css" type="text/css">


	<script type="text/javascript"
		src="<%=request.getContextPath()%>/css/finallytree.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/css/finallytree.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=request.getContextPath()%>/scripts/local/zh_CN.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=request.getContextPath()%>/scripts/base/eoms.js"></script>
	<script type="text/javascript">
	eoms.appPath = "<%=request.getContextPath()%>";;
  </script>
	<link rel="stylesheet" type="text/css" media="all"
		href="<%=request.getContextPath()%>/styles/red/theme.css" />
	<!-- EXT LIBS verson 1.1 -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/scripts/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ext/ext-all.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=request.getContextPath()%>/scripts/adapter/ext-ext.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=request.getContextPath()%>/scripts/ext/source/locale/ext-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/scripts/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/styles/default/ext-adpter.css" />
	<!-- EXT LIBS END -->


	<script type="text/javascript">

	//depttree
	var deptTree;
	
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
		
	
	Ext.onReady(function(){
		deptViewer = new Ext.JsonView("view",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
var s='<%=s%>';
		deptViewer.jsonData = eoms.JSONDecode(s);
		deptViewer.refresh();
		var	treeAction=eoms.appPath+'/xtree.do?method=dept';
		deptTree = new xbox({
			btnId:'clkOrg',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'组织结构选择',treeChkMode:'',treeChkType:'dept',
			showChkFldId:'acceptDeptName',saveChkFldId:'acceptDeptId',viewer:deptViewer
		});
	
	});
	
	//wangsixuan add:auditUserTree
	var auditUserTree;
	
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
		
	
	Ext.onReady(function(){
		auditUserViewer = new Ext.JsonView("auditView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		var a='<%=a%>';
		auditUserViewer.jsonData = eoms.JSONDecode(a);
		auditUserViewer.refresh();
		var	treeAction=eoms.appPath+'/xtree.do?method=userFromDept';
		auditUserTree = new xbox({
			btnId:'auditUser',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'审核人选择',treeChkMode:'single',treeChkType:'user',
			showChkFldId:'auditUserName',saveChkFldId:'auditUserId',viewer:auditUserViewer
		});
	
	});
	
	//wangsixuan add:reportUserTree
	var reportUserTree;
	
	function FCKeditor_OnComplete(editorInstance) {
		window.status = editorInstance.Description;
	}
		
	
	Ext.onReady(function(){
		reportUserViewer = new Ext.JsonView("reportView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		var r='<%=r%>';
		reportUserViewer.jsonData = eoms.JSONDecode(r);
		reportUserViewer.refresh();
		var	treeAction=eoms.appPath+'/xtree.do?method=userFromDept';
		reportUserTree = new xbox({
			btnId:'reportUser',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'接收人选择',treeChkMode:'single',treeChkType:'user',
			showChkFldId:'reportUserName',saveChkFldId:'reportUserId',viewer:reportUserViewer
		});
	
	});
	
		function changeAudit(checkbox){	
		document.getElementById("auditUser").disabled=!checkbox.checked;
		if(!document.getElementById("isAudit").checked){
			document.getElementById("auditUserName").value="";
			document.forms[0].auditUserId.value="";
			var g='[]';
			auditUserViewer.jsonData = eoms.JSONDecode(g);
			auditUserViewer.refresh();
			auditUserViewer = new Ext.JsonView("auditView",
			'<div class="viewlistitem-{nodeType}">{name}</div>',
			{ 
				emptyText : '<div>没有选择项目</div>'
			}
		);
		}
	}
</script>




	<script language="javascript">
//弹出派发树窗口
function selectTree(){
	dWinOrnaments = "status:no;scroll:no;resizable:no;dialogHeight:450px;dialogWidth:480px;";
	dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/listbox_stat.jsp?sort=4&checkall=no&user=no&selectself=yes', window, dWinOrnaments);
}

var fileNumber=0;
function addNextFile(){
    fileNumber++;
    strFileHtml="<input type='file' name='reportTemplateFile"+fileNumber+"' size='40' class='file'>";
    document.all.nextFile.innerHTML=document.all.nextFile.innerHTML+strFileHtml+"<br>";
}
function displayAreaCtl(ele){
    var id=ele.value;
    for(var i=1;i<=5;i++){
        var areaEle=eval("document.all('cycleArea_"+i+"')");
        if(i==id)
            areaEle.style.display="block";
        else
          areaEle.style.display="none";
    }
}
function prepareSubmit(){
   if(document.forms[0].title.value=='')
	{
		alert('请输入报表名称！');
		document.forms[0].title.focus();
        return false;
	}
    if(document.forms[0].sendContact.value=='')
	{
		alert('请输入联系方式！');
		document.forms[0].sendContact.focus();
        return false;
	}
	 if(document.forms[0].reportTemplateFile.value=='')
	{
		alert('请选择报表模板文件！');
        return false;
	}
	if(document.getElementById("reportTemplateFile").value!=null&&document.getElementById("reportTemplateFile").value!=""&&document.getElementById("reportTemplateFile").value.indexOf(".xls")<0){
    	alert("确认选择的文件为Excel");
    	return false;
    }



     //处理周期                schemeAhead_1     schemeTime_2
     var id=1;
     var eleArr=document.forms[0].cycleType;
     var cycleDescription="";
     for(var i=0;i<eleArr.length;i++){
         if(eleArr[i].checked){
            id=eleArr[i].value;
            cycleDescription=eleArr[i].title;
         }
     }
       for(var i=1;i<=5;i++){
        var schemeAheadEle=eval("document.forms[0].schemeAhead_"+i);
        var schemeTimeEle=eval("document.forms[0].schemeTime_"+i);
        if(i==id){                              // 选中的类型
            var timeDesc="";
            if(id=='1'||id=='5')
                timeDesc=schemeTimeEle.value;
            else
                timeDesc=schemeTimeEle.options[schemeTimeEle.selectedIndex].text
            document.forms[0].schemeTime.value=schemeTimeEle.value;
            document.forms[0].schemeAhead.value=schemeAheadEle.value;
            document.forms[0].cycleDescription.value
                    =cycleDescription+" 时间："+timeDesc+" 时限："+schemeAheadEle.options[schemeAheadEle.selectedIndex].text;
        }
    }
    document.forms[0].submit();
}
 var cycleType="<bean:write name="SchemeMgrForm" property="cycleType"/>";
 function formInit(){
     if(cycleType!=1) {
         var schemeAhead=eval("document.forms[0].schemeAhead_"+cycleType);
         var schemeTime=eval("document.forms[0].schemeTime_"+cycleType);
         for(var i=0;i<schemeAhead.options.length;i++){
             if(schemeAhead.options[i].value==document.forms[0].schemeAhead.value){
                 schemeAhead.options[i].selected=true;
                 break;
             }
         }
          if(cycleType!=5){
                for(var i=0;i<schemeTime.options.length;i++){
                 if(schemeTime.options[i].value==document.forms[0].schemeTime.value){
                     schemeTime.options[i].selected=true;
                     break;
                 }
                }
          }else{
              schemeTime.value=document.forms[0].schemeTime
          }
     }
     
     for(var i=1;i<=5;i++){
        var areaEle=eval("document.all('cycleArea_"+i+"')");
        if(i==cycleType)
            areaEle.style.display="block";
        else
          areaEle.style.display="none";
    }
 }
</script>
</head>
<body>
	<br />
	<br />
	<table id="listTitle">
		<tr>
			<td>
				<b>&nbsp;&nbsp;报表设置</b>
			</TD>
		</tr>
	</table>
	<br />
	<%--enctype="multipart/form-data"--%>
	<html:form action="/scheme/SchemeMgrAction.do" method="post"
		enctype="multipart/form-data">
		<table width="100%" class="formTable" id="list">
			<tr>
				<td align="right" class="label" colspan="4">
					<font color="#FF0000">*为必填项</font>
				</td>
			</tr>

			<html:hidden property="act" />
			<html:hidden property="schemeId" />
			<html:hidden property="topicId" />
			<html:hidden property="schemeAhead" />
			<html:hidden property="schemeTime" />
			<html:hidden property="cycleDescription" />
			<tr>
				<td width="15%" class="label">
					<b>派发用户</b>
				</td>
				<td width="400">
					<html:hidden property="createUser" />
					<bean:write name="SchemeMgrForm" property="createUserName" />
				</td>
				<td width="15%" class="label">
					<b>联系方式*</b>
				</td>
				<td width="400">
					<html:text property="sendContact" size="20" title="联系方式"
						styleClass="text" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>汇总报表部门</b>
				</td>
				<td>
					<html:hidden property="sendDeptId" />
					<html:hidden property="sendDeptName" />
					<bean:write name="SchemeMgrForm" property="sendDeptName" />
				</td>
				<td class="label">
					<b>发布专题</b>
				</td>
				<td>
					<bean:write name="SchemeMgrForm" property="topicName" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>周期选择</b>
				</td>
				<td colspan="3">
					<html:radio property="cycleType" value="1"
						onclick="displayAreaCtl(this)">临时报表&nbsp;</html:radio>
					<html:radio property="cycleType" value="2"
						onclick="displayAreaCtl(this)">以周为周期&nbsp;</html:radio>
					<html:radio property="cycleType" value="3"
						onclick="displayAreaCtl(this)">以月为周期报表&nbsp;</html:radio>
					<html:radio property="cycleType" value="4"
						onclick="displayAreaCtl(this)">以季度为周期报表&nbsp;</html:radio>
					<%--<html:radio property="cycleType" value="5"
						onclick="displayAreaCtl(this)">其他复杂周期报表&nbsp;</html:radio>
				--%>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>通知时间</b>
				</td>
				<td colspan="3">
					<span id="cycleArea_1"> <logic:equal value="1"
							name="SchemeMgrForm" property="cycleType">
							<bean:define id="time" name="SchemeMgrForm" property="schemeTime" />
							<eoms:SelectTime name="schemeTime_1" formName="SchemeMgrForm"
								value="<%=time.toString()%>" />
						</logic:equal> <logic:notEqual value="1" name="SchemeMgrForm"
							property="cycleType">
							<eoms:SelectTime name="schemeTime_1" formName="SchemeMgrForm"
								day="3" />
						</logic:notEqual> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>报表汇总时间（时限）</b> <select
							name="schemeAhead_1" styleClass="select">
							<option value="1">
								一天以后
							</option>
							<option value="2">
								二天以后
							</option>
							<option value="3">
								三天以后
							</option>
							<option value="4">
								四天以后
							</option>
							<option value="5">
								五天以后
							</option>
							<option value="6">
								六天以后
							</option>
							<option value="7">
								七天以后
							</option>
						</select> </span>

					<span id="cycleArea_2"> <select name="schemeTime_2"
							styleClass="select">
							<option value="1">
								周一
							</option>
							<option value="2">
								周二
							</option>
							<option value="3">
								周三
							</option>
							<option value="4">
								周四
							</option>
							<option value="5">
								周五
							</option>
							<option value="6">
								周六
							</option>
							<option value="7">
								周日
							</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp; <b>报表汇总时间（时限）</b> <select
							name="schemeAhead_2" styleClass="select">
							<option value="1">
								一天以后
							</option>
							<option value="2">
								二天以后
							</option>
							<option value="3">
								三天以后
							</option>
							<option value="4">
								四天以后
							</option>
							<option value="5">
								五天以后
							</option>
							<option value="6">
								六天以后
							</option>
							<option value="7">
								七天以后
							</option>
						</select> </span>

					<span id="cycleArea_3" style="display:none"> <select
							name="schemeTime_3" styleClass="select">
							<option value="1">
								每月第一天
							</option>
							<option value="2">
								每月第二天
							</option>
							<option value="3">
								每月第三天
							</option>
							<option value="4">
								每月第四天
							</option>
							<option value="5">
								每月第五天
							</option>
							<option value="6">
								每月第六天
							</option>
							<option value="7">
								每月第七天
							</option>
							<option value="8">
								每月第八天
							</option>
							<option value="9">
								每月第九天
							</option>
							<option value="10">
								每月第十天
							</option>
							<option value="11">
								每月第十一天
							</option>
							<option value="12">
								每月第十二天
							</option>
							<option value="13">
								每月第十三天
							</option>
							<option value="14">
								每月第十四天
							</option>
							<option value="15">
								每月第十五天
							</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp; <b>报表汇总时间（时限）</b> <select
							name="schemeAhead_3" styleClass="select">
							<option value="1">
								一天以后
							</option>
							<option value="2">
								二天以后
							</option>
							<option value="3">
								三天以后
							</option>
							<option value="4">
								四天以后
							</option>
							<option value="5">
								五天以后
							</option>
							<option value="6">
								六天以后
							</option>
							<option value="7">
								七天以后
							</option>
						</select> </span>

					<span id="cycleArea_4" style="display:none"> <select
							name="schemeTime_4" styleClass="select">
							<option value="1">
								每季第一天
							</option>
							<option value="2">
								每季第二天
							</option>
							<option value="3">
								每季第三天
							</option>
							<option value="4">
								每季第四天
							</option>
							<option value="5">
								每季第五天
							</option>
							<option value="6">
								每季第六天
							</option>
							<option value="7">
								每季第七天
							</option>
							<option value="8">
								每季第八天
							</option>
							<option value="9">
								每季第九天
							</option>
							<option value="10">
								每季第十天
							</option>
							<option value="11">
								每季第十一天
							</option>
							<option value="12">
								每季第十二天
							</option>
							<option value="13">
								每季第十三天
							</option>
							<option value="14">
								每季第十四天
							</option>
							<option value="15">
								每季第十五天
							</option>
						</select> &nbsp;&nbsp;&nbsp;&nbsp; <b>报表汇总时间（时限）</b> <select
							name="schemeAhead_4" styleClass="select">
							<option value="1">
								一天以后
							</option>
							<option value="2">
								二天以后
							</option>
							<option value="3">
								三天以后
							</option>
							<option value="4">
								四天以后
							</option>
							<option value="5">
								五天以后
							</option>
							<option value="6">
								六天以后
							</option>
							<option value="7">
								七天以后
							</option>
						</select> </span>

					<span id="cycleArea_5" style="display:none"> <b>报表派发周期</b> <input
							type="text" name="schemeTime_5" id="schemeTime_5"
							styleClass="text"> &nbsp;&nbsp;&nbsp;&nbsp; <b>报表汇总时间（时限）</b>
						<select name="schemeAhead_5" styleClass="select">
							<option value="1">
								一天以后
							</option>
							<option value="2">
								二天以后
							</option>
							<option value="3">
								三天以后
							</option>
							<option value="4">
								四天以后
							</option>
							<option value="5">
								五天以后
							</option>
							<option value="6">
								六天以后
							</option>
							<option value="7">
								七天以后
							</option>
						</select> </span>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>报表名称*</b>
				</td>
				<td colspan=3>
					<html:text property="title" style="width:100%" title="报表名称"
						styleClass="text" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>合并类型</b>
				</td>
				<td colspan=3>
					<html:select property="combinType" styleClass="select">
						<html:options collection="combinType" property="value"
							labelProperty="label" />
					</html:select>
					<!--html:text property="combinType" style="width:100%" title="合并类型"/-->
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>紧急程度</b>
				</td>
				<td colspan=3>
					<html:select property="faultClass" styleClass="select">
						<html:options collection="faultClass" property="value"
							labelProperty="label" />
					</html:select>
				</td>
			</tr>


			<tr>
				<td class="label">
					<b>报表描述（限制1000字符以内）</b>
				</td>
				<td colspan=3>
					<html:textarea property="reportDescription"
						styleClass="textarea max" title="报表描述" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>报表任务填写部门</b>
				</td>
				<td colspan=3>
					<input type="text" readonly id="acceptDeptName"
						name="acceptDeptName" styleClass="text" />
					<input type="hidden" id="acceptDeptId" name="acceptDeptId" />
					<input type="button" id="clkOrg" name="clkOrg" value="选择任务填写部门"
						class="button" />
					<div id="view" class="viewer-box"></div>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>报表任务审核人</b>
				</td>
				<td colspan=3>
				<logic:equal value="0" name="SchemeMgrForm" property="isAudit">
					<input type="text" readonly id="auditUserName" name="auditUserName"
						styleClass="text" />
					<input type="hidden" id="auditUserId" name="auditUserId" />
					<input type="button" id="auditUser" name="auditUser" value="选择审核人"
						class="button" disabled="true" />
					<INPUT type="checkbox" id="isAudit" name="isAudit"
						onclick="javascript:changeAudit(this)" />
					是否需要审核
					<div id="auditView" class="viewer-box"></div>
				</logic:equal>
				<logic:equal value="1" name="SchemeMgrForm" property="isAudit">
					<input type="text" readonly id="auditUserName" name="auditUserName"
						styleClass="text" />
					<input type="hidden" id="auditUserId" name="auditUserId" />
					<input type="button" id="auditUser" name="auditUser" value="选择审核人"
						class="button" />
					<INPUT type="checkbox" id="isAudit" name="isAudit"
						onclick="javascript:changeAudit(this)" checked="checked"/>
					是否需要审核
					<div id="auditView" class="viewer-box"></div>
				</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>报表任务接收人</b>
				</td>
				<td colspan=3>
					<input type="text" readonly id="reportUserName"
						name="reportUserName" styleClass="text" />
					<input type="hidden" id="reportUserId" name="reportUserId" />
					<input type="button" id="reportUser" name="reportUser"
						value="选择接收人" class="button" />
					<div id="reportView" class="viewer-box"></div>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>已上传模板文件</b>
				</td>
				<td valign="middle" colspan=3>
					<iframe name="upfiles" frameborder=0 width="100%" height="60"
						scrolling="yes"
						src="<%=request.getContextPath()%>/filemanager/fileUpload/modUploadFile.jsp?ownerId=<bean:write name="SchemeMgrForm" property="schemeId"/>&fileType=1"></iframe>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>模板文件</b>
				</td>
				<td valign="middle" colspan=3>
					<input type="file" name="reportTemplateFile" size="40" class="file">
					<div id="nextFile"></div>
					<span
						onmouseover="this.style.border='2 #eeeeee outset';this.style.cursor='hand';width:10"
						onmouseout="this.style.border='2 #ffffff'"
						onmousedown="this.style.border='2 #cccccc inset';"
						onclick="addNextFile()" valign="center"><img border=0
							src="<%=request.getContextPath()%>/images/add1.gif"><font
						color="darkblue">增加上传文件</font> </span>
				</td>
			</tr>

		</table>
		<BR>
		<TABLE>
			<TR>
				<TD>
					<input type="button" class="button" name="cancel" value="取消"
						onClick="javascript:window.history.back();" />
					&nbsp;
					<input type="button" class="button" name="confirm"
						onclick=" prepareSubmit();" value="保存" />
			</TR>
		</TABLE>

	</html:form>

	<logic:messagesPresent>
		<html:messages id="error">
			<script type="text/javascript">
                    alert("<bean:write name="error"/>");
	</script>
		</html:messages>
	</logic:messagesPresent>
</html:html>
</body>
<script>
    formInit();
</script>
