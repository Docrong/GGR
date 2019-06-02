<%@ page contentType="text/html;charset=GB2312"%>
<jsp:directive.page import="com.boco.eoms.common.util.StaticMethod"/>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<head>
<title>报表详细信息</title>
<link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/default/theme.css" />
<%----%>

<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/xmltree/xtree.js"></script>
<script language="JavaScript">
  var xmlManage="<%=request.getContextPath()%>/filemanager/rejectServer.jsp";
  var type ="-1";
 function submitReject(userid,flowId,reportFileId){
  	var auditInfo = document.getElementById(reportFileId).value;
    currFlowId=flowId;
    type = "2";
    var loadStr="<info userId='"+userid+"' type='reject2' auditInfo='"+auditInfo+"' flowId='"+flowId+"'/>";
    ReceiveStr(loadStr);
  }
  function submitPass(userid,flowId,reportFileId){
    currFlowId=flowId;
    type = "3";
    var auditInfo = document.getElementById(reportFileId).value;
    var loadStr="<info userId='"+userid+"' type='pass2' auditInfo='"+auditInfo+"' flowId='"+flowId+"'/>";
    ReceiveStr(loadStr);
  }
  function submitReject3(userid,flowId,reportFileId){
  	var auditInfo = document.getElementById(reportFileId).value;
    currFlowId=flowId;
    type = "4";
    var loadStr="<info userId='"+userid+"' type='reject3' auditInfo='"+auditInfo+"' flowId='"+flowId+"'/>";
    ReceiveStr(loadStr);
  }
  function submitPass3(userid,flowId,reportFileId){
    currFlowId=flowId;
    type = "5";
    var auditInfo = document.getElementById(reportFileId).value;
    var loadStr="<info userId='"+userid+"' type='pass3' auditInfo='"+auditInfo+"' flowId='"+flowId+"'/>";
    ReceiveStr(loadStr);
  }
  var currFlowId="-1";
  function reXml(temp){
  	var str="";
  	if(type=="2")
  	{
  	str="<b>二审驳回</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("驳回工单操作成功！");
  	}else if(type=="3"){
  	str="<b>二审通过</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("审核通过操作成功！");
  	}else if(type=="4"){
  	str="<b>驳回</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("驳回工单操作成功！");
  	}else if(type=="5"){
  	str="<b>通过</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("审核通过操作成功！");
  	}
  	window.history.go(0);
  }
</script>
</head>
<br/>
<br/>
<table width="100%">
	  <tr>
		<td>&nbsp;&nbsp;<font size="2"><b><bean:write name="ReportMgrBean" property="reportName"/></b></font></td>
 </tr>
</table>
<br/>
<table width="100%" class="formTable">
	 <tr>
		<td width="15%" class="label"><b>派发用户</b></td>
		<td >
		<bean:write name="SchemeMgrForm" property="createUserName"/>
		</td>
		<td width="15%" class="label"><b>联系方式</b></td>
		<td >
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
		<td class="label"><b>派发时间</b></td>
		<td>
        <bean:write name="ReportMgrBean" property="createTime" />
		</td>
		<td class="label"><b>上报时限</b></td>
		<td>
		    <bean:write name="ReportMgrBean" property="deadline" />
		</td>
   </tr>
 <tr>
		<td class="label"><b>紧急程度</b></td>
		<td colspan = 3>
         <bean:write name="SchemeMgrForm" property="faultClassName" />
		</td>

		<%--<td class="label"><b>专业类型</b></td>
		<td>

        <bean:write name="SchemeMgrForm" property="specialtyName" />

		</td>
 --%></tr>
	 <tr>
		<td class="label"><b>合并类型</b></td>
		<td colspan = 3>
        <bean:write name="SchemeMgrForm" property="combinTypeName" />
		</td>
 </tr>

	 <tr>
		<td class="label"><b>报表描述</b></td>
		<td colspan = 3>
        <bean:write name="SchemeMgrForm" property="reportDescription" />
		</td>
 </tr>
 <tr>
    <td class="label"><b>模板文件</b></td>
    <td  valign="middle" colspan=3>
      <logic:iterate id="fileList" name="SchemeMgrForm" property="fileUrl">
            <%
                String fileInfo=fileList.toString().trim();
                if(fileInfo!=null)fileInfo=fileInfo.trim();
                String[] fileInfoArr=fileInfo.split(",");
                String url=fileInfoArr[2];
                String fileName=fileInfoArr[1];
                String[] fileRealNameArr = url.split("/");
                String fileRealName = fileRealNameArr[fileRealNameArr.length-1];
            %>
            <a href="<%=request.getContextPath()%>/filemanager/fileUpload/download.jsp?url=<%=url%>&showName=<%=fileRealName%>"><%=fileRealName%></a><br/>
            </logic:iterate>
    </td>
 </tr>

</table>
<%--上传文件--%>

<logic:iterate id="reportFile" name="ReportList" type="com.boco.eoms.filemanager.form.ReportListBean">
<bean:define id="i" name="reportFile" property="flowId" />
<table width="100%" class="formTable">
 <tr>
	<td width="15%" class="label"><b>制作部门 报表制作人</b></td>
	<td >
    <bean:write name="reportFile" property="dealUserName" scope="page"/>[<bean:write name="reportFile" property="acceptDeptName" scope="page"/>]
	</td>
	<td width="15%" class="label"><b>联系方式</b></td>
	<td >
	<bean:write name="reportFile" property="acceptContact" scope="page"/>
	</td>
</tr>
 <tr>
	<td width="15%" class="label"><b>是否超时</b></td>
	<td >
	 <bean:write name="reportFile" property="overtimeName" scope="page"/>
	</td>
	<td width="15%" class="label"><b>报表状态</b></td>
	<td  id="rejectArea_<bean:write name="reportFile" property="flowId" />">
        
                  <logic:equal value="0" name="reportFile" property="status">
                  <font color="red">
                 </logic:equal>
               <b><bean:write name="reportFile" property="statusName" scope="page"/></b>
                  <logic:equal value="0" name="reportFile" property="status">
                  </font>
                 </logic:equal>
            &nbsp;
<%--            <%--%>
<%--            	if(reportFile.getStatus() == 1 && reportFile.getReject()==0){--%>
<%--             %>--%>
<%--                  <input type="button" name="btnVote" value="驳回报表" class="button" onclick="if(window.confirm('是否要驳回\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'的报表？'))  submitReject('<bean:write name="reportFile" property="flowId" />');" />--%>
<%--            <%} %>--%>
    </td>
</tr>
 <tr>
	<td width="15%" class="label"><b>派发时间</b></td>
	<td >
	 <bean:write name="reportFile" property="sendTime" scope="page"/>
	</td>
	<td width="15%" class="label"><b>报表上传时间</b></td>
	<td >
	<bean:write name="reportFile" property="uploadTime" scope="page"/>
	</td>
</tr>
 <tr>
	<td class="label"><b>报表文件</b></td>
	<td colspan = 3>
	<logic:iterate id="fileList" name="reportFile" property="fileUrl">
            <%
                String fileInfo=fileList.toString().trim();
                if(fileInfo!=null)fileInfo=fileInfo.trim();
                String[] fileInfoArr=fileInfo.split(",");
                String url=fileInfoArr[2];
                String fileName=fileInfoArr[1];
                String[] fileRealNameArr = url.split("/");
                String fileRealName = fileRealNameArr[fileRealNameArr.length-1];
            %>
                <a href="<%=request.getContextPath()%>/filemanager/fileUpload/download.jsp?url=<%=url%>&showName=<%=fileRealName%>"><%=fileRealName%></a><br/>
            </logic:iterate>
	</td>
 </tr>
 <tr>
 <td width="15%" class="label">
				<b>回复说明</b>
			</td>
			<td >
				<bean:write name="reportFile" property="replyInfo" scope="page" />
			</td>
			<%--<%
			request.setAttribute("flowId", reportFile.getFlowId());
          	%>
			--%><td colspan=2>
			
				<html:link href="ReportMgrAction.do?act=AuditList&flowId=${reportFile.flowId}">
					<b><u>查看历史审核详细信息</u>
					</b>
				</html:link>
			</td>
 </tr>
 <tr>
			<logic:equal value="3" name="reportFile" property="status">
				<td class="label" width="15%">
					<b>审核意见</b>
				</td>
				<td >
					<textarea cols="30" rows="3" id="<%="reportFile" + i%>"
						name="<%="reportFile" + i%>"></textarea>
				</td>
				<td width="15%">
					<input type="button" name="btnVote" value="驳回报表" class="button"
						onclick="if(window.confirm('是否要驳回\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'的报表？'))  submitReject('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
				<td >
					<input type="button" name="btnVote" value="通过报表" class="button"
						onclick="if(window.confirm('是否要通过\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'的报表？'))  submitPass('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
			</logic:equal>
			<logic:equal value="1" name="reportFile" property="status">
				<logic:equal value="0" name="reportFile" property="isAudit">
				<td class="label" width="15%">
					<b>审核意见</b>
				</td>
				<td >
					<textarea cols="30" rows="3" id="<%="reportFile" + i%>"
						name="<%="reportFile" + i%>"></textarea>
				</td>
				<td width="15%">
					<input type="button" name="btnVote" value="驳回报表" class="button"
						onclick="if(window.confirm('是否要驳回\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'的报表？'))  submitReject3('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
				<td >
					<input type="button" name="btnVote" value="通过报表" class="button"
						onclick="if(window.confirm('是否要通过\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'的报表？'))  submitPass3('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
				</logic:equal>
			</logic:equal>
		</tr>
</table>
</logic:iterate>
<% 
   //add by chenyuanshu 只有一个或者没有上传时不显示合并按钮
   //update by wangsixuan 如果为不合并则不显示按钮
   int count = StaticMethod.nullObject2int(request.getAttribute("size"));
   String combintype = (String)request.getAttribute("combintype");
	if (count > 1){ %>
<table class="listTable" width="100%">
  <tr height="15">
    <td width="100%" align="right" nowrap valign="middle">
      <font size="2"><bean:write name="pagerHeader" scope="request" filter="false"/><%! String key;%></font>
    </td>
<!--ZF add-->  
  <br/>
  <%if (!combintype.equals("0")){%>
  <input type="submit" name="combine" onclick="showCombine()" value="合并报表" class="button"/>    
  <%} %>
  </tr>
</table>
<%} %>
<!--ZF add ===========================================================================================-->
<span id="combine_span" style="display: none;">
<html:form action="/ReportMgrAction.do?act=COMBINE" method="post" enctype="multipart/form-data">
<input type="hidden" name="combinType" value="<bean:write name="SchemeMgrForm" property="combinType" />">
<table width="100%" class="formTable">
    <tr id="combine_table1_add1">
    <td nowrap class="label">
        <b>选择合并模板</b>
    </td>
    </tr>
    <tr id="combine_table2_add1">
    	<td>
    	    <logic:iterate id="fileList5" name="SchemeMgrForm" property="fileUrl">
            <%
                String fileInfo5=fileList5.toString().trim();
                if(fileInfo5!=null)fileInfo5=fileInfo5.trim();
                String[] fileInfoArr5=fileInfo5.split(",");
                String url5=fileInfoArr5[2];
                String fileName5=fileInfoArr5[1];
                String[] fileRealNameArr = url5.split("/");
                String fileRealName = fileRealNameArr[fileRealNameArr.length-1];
            %>
            <html:radio property="templatFile" value="<%=url5%>">
<%--            <input type="radio" name="templatFile" id="templatFile" value="<%=url5%>">--%>
            <%=fileRealName%></html:radio>
            <br>
            </logic:iterate></td>
    </tr>
</table>
<table width="100%" cellspacing="1" cellpadding="1" class="formTable" align=center>    
    <tr id="combine_table1">
        <td nowrap class="label">
                <b>选中文件</b>
        </td>
        <td nowrap class="label">
               <b> 制作部门</b>
        </td>
        <td nowrap class="label">
               <b> 文件名</b>
        </td>
    </tr>
<logic:iterate id="reportFile2" name="ListForStatus" type="com.boco.eoms.filemanager.form.ReportListBean">
<logic:iterate id="fileList2" name="reportFile2" property="fileUrl"> 

<%
                String fileInfo2=fileList2.toString().trim();
                if(fileInfo2!=null)fileInfo2=fileInfo2.trim();
                String[] fileInfoArr2=fileInfo2.split(",");
            	String url2=fileInfoArr2[2];
                String fileName2=fileInfoArr2[1];
                String[] fileRealNameArr = url2.split("/");
                String fileRealName = fileRealNameArr[fileRealNameArr.length-1];
%> 
    <tr id="combine_table2">

        <td nowrap>
<%--         	<html:checkbox property="fileList" value="<%=url2%>" <%if(reportFile2.getReject() == 1){ %>enabled=false<%} %>/>         	--%>
				<input type="checkbox" name="fileList" value="<%=url2%>" <%if(reportFile2.getReject() == 1){ %>disabled="disabled"<%} %> />
        </td>
        <td nowrap>
         <bean:write name="reportFile2" property="dealUserName" scope="page"/>[<bean:write name="reportFile2" property="acceptDeptName" scope="page"/>]
        </td>
        <td nowrap>         
                <%=fileRealName%><br>          
        </td>
       
    </tr>
</logic:iterate>    
</logic:iterate>       
  </table>
  
<table width="100%">
<td>
<%--    <html:button style="display: block;" onclick="return true;" value="合并选中" styleClass="button"/>--%>
 	<html:button property="btncombine" onclick="combineCheck()" value="合并选中" styleClass="button"/>
</td>
</table>
</html:form>
</span>

<script language="javascript">
    function showCombine() {
    	//style="display: none;"   style="visibility:hidden"

        document.getElementById("combine_span").style.display="block";
   }
   
   function combineCheck(){
   		//var modelchecked = document.getElementById("templatFile").checked;
   		var modelchecked = document.all.templatFile.checked;
   		if(modelchecked == false){
   			alert("请选择合并模板！");
   			return false;
   		}

   		var elements = document.getElementsByTagName("input");
   		var count=0;
   		for(var i = 0;i < elements.length;i++)
		{
	        var e = elements[i];
	        if (e.type == "checkbox" && e.checked)
	        {
	        	count++;
	        }
		}
		if(count<2){
			alert("请选择至少两个合并报表！");
			return false;
		}
		document.forms[0].submit();

   }
</script>
