<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>报表设置</title>
<script language="javascript" src="<%=request.getContextPath()%>/css/checkform.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
<%----%>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/red/theme.css" />
<script language="javascript">
//派发树
function selectTree(){
	dWinOrnaments = "status:no;scroll:no;resizable:no;dialogHeight:450px;dialogWidth:480px;";
	dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/listbox.jsp', window, dWinOrnaments);
}

//表单验证
function confirm(){
	var frm = document.forms[0];
	frm.status.value="2";

	if (!checkLength(frm.sendContact)) return false;
	if (!checkLength(frm.title)) return false;
	if (frm.mainTaskContent.value.length>1000)
	{
	alert("工单内容应小于1000字符");
	return false;
	}
	if (frm.sort1_deptid.value == "" && frm.sort1_userid.value =="")
	{
		alert("请选择派往部门或人员");
		return false;
	}
	return true;
}

var fileNumber=0;
function addNextFile(){
    fileNumber++;
    strFileHtml="<input type='file' name='reportTemplatFile"+fileNumber+"' size='40'>";
    document.all.nextFile.innerHTML=document.all.nextFile.innerHTML+strFileHtml+"<br>";
}
</script>
</head>
<body>
<br/>
<br/>
<table width="80%" id="list">
	<tr>
	<td><font size="2"><b>&nbsp;&nbsp;报表设置查看</b></font></TD>
	</tr>
	
</table>
<br/>
<%--enctype="multipart/form-data"--%>
<html:form action="/scheme/SchemeMgrAction.do" method="post" >

<table width="100%" class="formTable" id="list">
<html:hidden property="act"/>
<html:hidden property="topicId"/>
<html:hidden property="topicName"/>
<html:hidden property="schemeId"/>
<%--<input type="hidden" id="createUser" value="<bean:write name='SchemeMgrForm' property='createUser'/>" />--%>
<html:hidden property="createUser" value="<bean:write name='SchemeMgrForm' property='createUser'/>" />
	<tr>
	<td align="right" colspan="4" class="label"><font color="#FF0000">*为必填项</font></td>
	</tr>
	 <tr>
		<td width="15%" class="label"><b>派发用户</b></td>
		<td width="400">
		<bean:write name="SchemeMgrForm" property="createUserName"/>
		</td>
		<td width="15%" class="label"><b>联系方式*</b></td>
		<td width="400">
        <bean:write name="SchemeMgrForm" property="sendContact" />
		</td>
	</tr>
	<tr>
		<td class="label"><b>汇总报表部门</b></td>
		<td>
        <bean:write name="SchemeMgrForm" property="sendDeptName" />
		</td>
		<td class="label"><b>发布专题</b></td>
		<td>
		    <bean:write name="SchemeMgrForm" property="topicName" />
		</td>
   </tr>
  <tr>
		<td class="label"><b>报表周期</b></td>
		<td colspan="3">
		  <bean:write name="SchemeMgrForm" property="cycleDescription" />
		</td>
  </tr>
  <tr>
		<td class="label"><b>报表名称*</b></td>
		<td colspan=3>
        <bean:write name="SchemeMgrForm" property="title" />
		</td>
 </tr>
  <tr>
		<td class="label"><b>合并类型</b></td>
		<td colspan=3>
        <bean:write name="SchemeMgrForm" property="combinTypeName" />
		</td>
 </tr>
	 <tr>
		<td class="label"><b>紧急程度</b></td>
		<td colspan=3>
         <bean:write name="SchemeMgrForm" property="faultClassName" />
		</td><%--

		<td class="label"><b>专业类型</b></td>
		<td>

        <bean:write name="SchemeMgrForm" property="specialtyName" />

		</td>
 --%></tr>
	 <tr>
		<td class="label"><b>报表描述（限制1000字符以内）</b></td>
		<td colspan = 3>
        <bean:write name="SchemeMgrForm" property="reportDescription" />
		</td>
 </tr>
 <tr>
    <td class="label"><b>报表任务填写部门</b></td>
    <td colspan=3>
        <bean:write name="SchemeMgrForm" property="acceptDeptName" />
	</td>
 </tr>
 <tr>
    <td class="label"><b>报表任务审核人</b></td>
    <td colspan=3>
        <bean:write name="SchemeMgrForm" property="auditUserName" />
	</td>
 </tr>
 <tr>
    <td class="label"><b>报表任务接收人</b></td>
    <td colspan=3>
        <bean:write name="SchemeMgrForm" property="reportUserName" />
	</td>
 </tr>
 <tr>
    <td class="label"><b>模板文件</b></td>
    <td valign="middle" colspan=3>

        <logic:iterate id="fileList" name="SchemeMgrForm" property="fileUrl">
            <%
              String fileInfo=fileList.toString().trim();
              if(fileInfo!=null)fileInfo=fileInfo.trim();
              String[] fileInfoArr=fileInfo.split(",");
              String url=fileInfoArr[2];
              String fileName=fileInfoArr[1];
              String[] fileRealNameArr = url.split("/");
              String fileRealName = fileRealNameArr[fileRealNameArr.length-1];
              java.util.HashMap map = new java.util.HashMap();
              map.put("url", url);
              map.put("showName", fileName);
              pageContext.setAttribute("map", map, PageContext.PAGE_SCOPE);
              pageContext.setAttribute("fileName", fileName, PageContext.PAGE_SCOPE);
            %>
            <a href="<%=request.getContextPath()%>/filemanager/fileUpload/download.jsp?url=<%=url%>&showName=<%=fileRealName%>"><%=fileRealName%></a>
            <br/>
            </logic:iterate>
    </td>
 </tr>

</table>
<BR>

<%--
    <input type="button" name="eidt" onclick="actEdit()" value="修改" class="button"/>
    &nbsp;
    <input type="submit" name="confirm" onclick="actDelete()" value="删除" class="button"/>
--%>
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

<script language="JavaScript" type="text/JavaScript">
function actAdd(){
document.all.act.value="add";
document.forms[0].submit();
}
function actEdit(){
document.all.act.value="edit";
document.forms[0].submit();
}
function actDelete(){
document.all.act.value="delete";
document.forms[0].submit();
}
</script>