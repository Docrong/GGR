<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms"%>
<%
	String usernameJson = (String) request.getAttribute("usernameJson");
	String aUserId = (String) request.getAttribute("aUserId");
	String aUserName = (String) request.getAttribute("aUserName");
%>
<link rel="stylesheet" type="text/css" media="all"
	href="<%=request.getContextPath()%>/styles/default/theme.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/wsstyle.css" type="text/css">

<script type="text/javascript"
	src="<%=request.getContextPath()%>/css/finallytree.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/scripts/local/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/scripts/base/eoms.js"></script>
<script type="text/javascript">
	eoms.appPath = "<%=request.getContextPath()%>";
  </script>
<link rel="stylesheet" type="text/css" media="all"
	href="<%=request.getContextPath()%>/styles/default/theme.css" />
<!-- EXT LIBS verson 1.1 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/scripts/ext/ext-all.js"></script>
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
	//wangsixuan add:auditUserTree
	var auditUserTree;
	var usernameJson='<%=usernameJson%>';
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
		auditUserViewer.jsonData = eoms.JSONDecode(usernameJson);
		auditUserViewer.refresh();
		var	treeAction=eoms.appPath+'/xtree.do?method=userFromDept';
		auditUserTree = new xbox({
			btnId:'auditUser',dlgId:'hello-dlg',
			treeDataUrl:treeAction,treeRootId:'-1',treeRootText:'审核人选择',treeChkMode:'single',treeChkType:'user',
			showChkFldId:'auditUserName',saveChkFldId:'auditUserId',viewer:auditUserViewer
		});
	
	});	
		function changeAudit(checkbox){	
		document.getElementById("auditUser").disabled=!checkbox.checked;
		if(!document.getElementById("isAudit").checked){
			document.getElementById("auditUserName").value='<%=aUserName%>';
			document.getElementById("auditUserId").value='<%=aUserId%>';
			auditUserViewer.jsonData = eoms.JSONDecode(usernameJson);
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>上报报表</title>
	<link rel="stylesheet" type="text/css" media="all"
		href="<%=request.getContextPath()%>/styles/default/theme.css" />
	<%----%>
	<html:form action="/ReportMgrAction.do" method="post"
		enctype="multipart/form-data">
		<html:hidden property="act" />
		<html:hidden property="flowId" />
		<html:hidden property="reportId" />
		<br />
		<br />
		<table width="100%" id="listTitle">
			<tr>
				<td>
					&nbsp;&nbsp;
					<font size="2"><b><bean:write name="ReportMgrBean"
								property="reportName" /> </b> </font>
				</td>
			</tr>
		</table>
		<br />
		<table width="100%" class="formTable">
			<tr>
				<td width="15%" class="label">
					<b>派发用户</b>
				</td>
				<td >
					<bean:write name="SchemeMgrForm" property="createUserName" />
				</td>
				<td width="15%" class="label">
					<b>联系方式*</b>
				</td>
				<td >
					<bean:write name="SchemeMgrForm" property="sendContact" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>汇总报表部门</b>
				</td>
				<td>
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
					<b>派发时间</b>
				</td>
				<td>
					<bean:write name="ReportMgrBean" property="createTime" />
				</td>
				<td class="label">
					<b>上报时限 
				</td>
				<td>
					<bean:write name="ReportMgrBean" property="deadline" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>紧急程度</b>
				</td>
				<td colspan=3>
					<bean:write name="SchemeMgrForm" property="faultClassName" />
				</td>

				<%--<td class="label">
					<b>专业类型</b>
				</td>
				<td>

					<bean:write name="SchemeMgrForm" property="specialtyName" />

				</td>
			--%>
			</tr>
			<tr>
				<td class="label">
					<b>报表描述（限制1000字符以内）</b>
				</td>
				<td colspan=3>
					<bean:write name="SchemeMgrForm" property="reportDescription" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>模板文件</b>
				</td>
				<td valign="middle" colspan=3>
					<logic:iterate id="fileList" name="SchemeMgrForm"
						property="fileUrl">
						<%
									String fileInfo = fileList.toString().trim();
									if (fileInfo != null)
								fileInfo = fileInfo.trim();
									String[] fileInfoArr = fileInfo.split(",");
									String url = fileInfoArr[2];
									String fileName = fileInfoArr[1];
									String[] fileRealNameArr = url.split("/");
									String fileRealName = fileRealNameArr[fileRealNameArr.length - 1];
						%>
						<a
							href="<%=request.getContextPath()%>/filemanager/fileUpload/download.jsp?url=<%=url%>&showName=<%=fileRealName%>"><%=fileRealName%>
						</a>
						<br />
					</logic:iterate>

				</td>
			</tr>

		</table>
		<%--上传文件--%>
		<table width="100%" class="formTable">
			<tr>
				<td width="15%" class="label">
					<b>制作部门 报表制作人</b>
				</td>
				<td >
					<bean:write name="ReportMgrForm" property="dealUserName" />
					[
					<bean:write name="ReportMgrForm" property="acceptDeptName" />
					]
					<html:hidden property="dealUserId" />
				</td>
				<td width="15%" class="label">
					<b>联系方式*</b>
				</td>
				<td >
					<html:text property="acceptContact" size="20" title="联系方式"
						styleClass="text" />
				</td>
			</tr>
			<tr>
				<td width="15%" class="label">
					<b>是否超时</b>
				</td>
				<td >
					<bean:write name="ReportMgrForm" property="overtimeName" />
				</td>
				<td width="15%" class="label">
					<b>报表状态*</b>
				</td>
				<td >
					<bean:write name="ReportMgrForm" property="statusName" />
				</td>
			</tr>
			<tr>
				<td width="15%" class="label">
					<b>派发时间</b>
				</td>
				<td >
					<bean:write name="ReportMgrForm" property="sendTime" />
				</td>
				<td width="15%" class="label">
					<b>报表上传时间*</b>
				</td>
				<td >
					<bean:write name="ReportMgrForm" property="uploadTime" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>报表任务审核人</b>
				</td>
				<td colspan=3>
					<logic:equal value="0" name="ReportMgrForm" property="isAudit">
					<input type="hidden" readonly id="auditUserName"
							name="auditUserName" styleClass="text" />
						<input type="hidden" id="auditUserId" name="auditUserId" />
						<input type="hidden" id="auditUser" name="auditUser" value="选择审核人"
							class="button" disabled="true" />
						<INPUT type="hidden" id="isAudit" name="isAudit"
							onclick="javascript:changeAudit(this)" />
					<div id="auditView"  class="viewer-box"></div>
					[不需要审核人]
				</logic:equal>
					<logic:equal value="1" name="ReportMgrForm" property="isAudit">
						<input type="text" readonly id="auditUserName"
							name="auditUserName" styleClass="text" />
						<input type="hidden" id="auditUserId" name="auditUserId" />
						<input type="button" id="auditUser" name="auditUser" value="选择审核人"
							class="button" disabled="true" />
						<INPUT type="checkbox" id="isAudit" name="isAudit"
							onclick="javascript:changeAudit(this)" />
					是否修改审核人
					<div id="auditView"  class="viewer-box"></div>
					</logic:equal>
				</td>
			</tr>
			<logic:notEmpty name="ReportMgrForm" property="replyInfo">
				<td width="15%" class="label">
					<b>回复意见</b>
				</td>
				<td >
					<bean:write name="ReportMgrForm" property="replyInfo" />
				</td>
				<td colspan=2>
				<html:link href="ReportMgrAction.do?act=AuditList&flowId=${ReportMgrForm.flowId}">
					<b><u>查看历史审核详细信息</u></b>
					</html:link>
				</td>
			</logic:notEmpty>
			<tr>
				<td class="label">
					<b>上传说明</b>
				</td>
				<td colspan=3>
					<html:textarea property="replyInfo" styleClass="textarea max"
						value="" title="报表描述" />
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>已报表文件</b>
				</td>
				<td colspan=3>
					<logic:lessEqual value="2" name="ReportMgrForm" property="status">
						<iframe name="upfiles" frameborder=0 width="100%" height="60"
							scrolling="yes"
							src="<%=request.getContextPath()%>/filemanager/fileUpload/modUploadFile.jsp?ownerId=<bean:write name="ReportMgrForm" property="flowId"/>&fileType=0&delete=1"></iframe>
					</logic:lessEqual>
					<logic:greaterEqual value="3" name="ReportMgrForm"
						property="status">
						<logic:lessEqual value="5" name="ReportMgrForm" property="status">
							<iframe name="upfiles" frameborder=0 width="100%" height="60"
								scrolling="yes"
								src="<%=request.getContextPath()%>/filemanager/fileUpload/modUploadFile.jsp?ownerId=<bean:write name="ReportMgrForm" property="flowId"/>&fileType=0&delete=0"></iframe>
						</logic:lessEqual>
					</logic:greaterEqual>
					<logic:equal value="6" name="ReportMgrForm" property="status">
						<iframe name="upfiles" frameborder=0 width="100%" height="60"
							scrolling="yes"
							src="<%=request.getContextPath()%>/filemanager/fileUpload/modUploadFile.jsp?ownerId=<bean:write name="ReportMgrForm" property="flowId"/>&fileType=0&delete=1"></iframe>
					</logic:equal>
					<logic:equal value="7" name="ReportMgrForm" property="status">
						<iframe name="upfiles" frameborder=0 width="100%" height="60"
							scrolling="yes"
							src="<%=request.getContextPath()%>/filemanager/fileUpload/modUploadFile.jsp?ownerId=<bean:write name="ReportMgrForm" property="flowId"/>&fileType=0&delete=0"></iframe>
					</logic:equal>
				</td>
			</tr>
			<tr>
				<td class="label">
					<b>上传报表文件</b>
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
		<TABLE>
			<TR>
				<TD>
					<input type="button" name="cancel" value="取消" class="button"
						onClick="javascript:window.history.back();" />
					&nbsp;
					<logic:equal value="0" name="ReportMgrForm" property="status">
						<input type="submit" name="confirm" onclick="return actValid();"
							value="保存" class="button" />
					</logic:equal>
					<logic:equal value="1" name="ReportMgrForm" property="status">
						<input type="submit" name="confirm" onclick="return actValid();"
							value="保存" class="button" />
					</logic:equal>
					<logic:equal value="2" name="ReportMgrForm" property="status">
						<input type="submit" name="confirm" onclick="return actValid();"
							value="保存" class="button" />
					</logic:equal>
					<logic:equal value="6" name="ReportMgrForm" property="status">
						<input type="submit" name="confirm" onclick="return actValid();"
							value="保存" class="button" />
					</logic:equal>
			</TR>
		</TABLE>
	</html:form>

	<script language="javascript">
    var fileNumber = 0;
    function addNextFile() {
        fileNumber++;
        strFileHtml = "<input type='file' name='reportTemplateFile" + fileNumber + "' size='40' class='file'>";
        document.all.nextFile.innerHTML = document.all.nextFile.innerHTML + strFileHtml + "<br>";
    }
   function actValid(){
    if(document.forms[0].reportTemplateFile.value=='')
	{
		alert('报表文件不能为空！');
		document.forms[0].reportTemplateFile.focus();
        return false;
	}
	if(document.getElementById("reportTemplateFile").value!=null&&document.getElementById("reportTemplateFile").value!=""&&document.getElementById("reportTemplateFile").value.indexOf(".xls")<0){
    	alert("确认选择的文件为Excel");
    	return false;
    }
    return true;
   }
</script>