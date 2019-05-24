<%@ page contentType="text/html;charset=GB2312"%>
<jsp:directive.page import="com.boco.eoms.common.util.StaticMethod"/>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<head>
<title>������ϸ��Ϣ</title>
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
  	str="<b>���󲵻�</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("���ع��������ɹ���");
  	}else if(type=="3"){
  	str="<b>����ͨ��</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("���ͨ�������ɹ���");
  	}else if(type=="4"){
  	str="<b>����</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("���ع��������ɹ���");
  	}else if(type=="5"){
  	str="<b>ͨ��</b>";
  	var areaEle=eval("document.all.rejectArea_"+currFlowId);
          areaEle.innerHTML =str;
          alert("���ͨ�������ɹ���");
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
		<td width="15%" class="label"><b>�ɷ��û�</b></td>
		<td >
		<bean:write name="SchemeMgrForm" property="createUserName"/>
		</td>
		<td width="15%" class="label"><b>��ϵ��ʽ</b></td>
		<td >
        <bean:write name="SchemeMgrForm" property="sendContact" />
		</td>
	</tr>
	<tr>
		<td class="label"><b>���ܱ�����</b></td>
		<td>
        <bean:write name="SchemeMgrForm" property="sendDeptName" />
		</td>
		<td class="label"><b>����ר��</b></td>
		<td>
		    <bean:write name="SchemeMgrForm" property="topicName" />
		</td>
   </tr>
   	<tr>
		<td class="label"><b>�ɷ�ʱ��</b></td>
		<td>
        <bean:write name="ReportMgrBean" property="createTime" />
		</td>
		<td class="label"><b>�ϱ�ʱ��</b></td>
		<td>
		    <bean:write name="ReportMgrBean" property="deadline" />
		</td>
   </tr>
 <tr>
		<td class="label"><b>�����̶�</b></td>
		<td colspan = 3>
         <bean:write name="SchemeMgrForm" property="faultClassName" />
		</td>

		<%--<td class="label"><b>רҵ����</b></td>
		<td>

        <bean:write name="SchemeMgrForm" property="specialtyName" />

		</td>
 --%></tr>
	 <tr>
		<td class="label"><b>�ϲ�����</b></td>
		<td colspan = 3>
        <bean:write name="SchemeMgrForm" property="combinTypeName" />
		</td>
 </tr>

	 <tr>
		<td class="label"><b>��������</b></td>
		<td colspan = 3>
        <bean:write name="SchemeMgrForm" property="reportDescription" />
		</td>
 </tr>
 <tr>
    <td class="label"><b>ģ���ļ�</b></td>
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
<%--�ϴ��ļ�--%>

<logic:iterate id="reportFile" name="ReportList" type="com.boco.eoms.filemanager.form.ReportListBean">
<bean:define id="i" name="reportFile" property="flowId" />
<table width="100%" class="formTable">
 <tr>
	<td width="15%" class="label"><b>�������� ����������</b></td>
	<td >
    <bean:write name="reportFile" property="dealUserName" scope="page"/>[<bean:write name="reportFile" property="acceptDeptName" scope="page"/>]
	</td>
	<td width="15%" class="label"><b>��ϵ��ʽ</b></td>
	<td >
	<bean:write name="reportFile" property="acceptContact" scope="page"/>
	</td>
</tr>
 <tr>
	<td width="15%" class="label"><b>�Ƿ�ʱ</b></td>
	<td >
	 <bean:write name="reportFile" property="overtimeName" scope="page"/>
	</td>
	<td width="15%" class="label"><b>����״̬</b></td>
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
<%--                  <input type="button" name="btnVote" value="���ر���" class="button" onclick="if(window.confirm('�Ƿ�Ҫ����\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'�ı���'))  submitReject('<bean:write name="reportFile" property="flowId" />');" />--%>
<%--            <%} %>--%>
    </td>
</tr>
 <tr>
	<td width="15%" class="label"><b>�ɷ�ʱ��</b></td>
	<td >
	 <bean:write name="reportFile" property="sendTime" scope="page"/>
	</td>
	<td width="15%" class="label"><b>�����ϴ�ʱ��</b></td>
	<td >
	<bean:write name="reportFile" property="uploadTime" scope="page"/>
	</td>
</tr>
 <tr>
	<td class="label"><b>�����ļ�</b></td>
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
				<b>�ظ�˵��</b>
			</td>
			<td >
				<bean:write name="reportFile" property="replyInfo" scope="page" />
			</td>
			<%--<%
			request.setAttribute("flowId", reportFile.getFlowId());
          	%>
			--%><td colspan=2>
			
				<html:link href="ReportMgrAction.do?act=AuditList&flowId=${reportFile.flowId}">
					<b><u>�鿴��ʷ�����ϸ��Ϣ</u>
					</b>
				</html:link>
			</td>
 </tr>
 <tr>
			<logic:equal value="3" name="reportFile" property="status">
				<td class="label" width="15%">
					<b>������</b>
				</td>
				<td >
					<textarea cols="30" rows="3" id="<%="reportFile" + i%>"
						name="<%="reportFile" + i%>"></textarea>
				</td>
				<td width="15%">
					<input type="button" name="btnVote" value="���ر���" class="button"
						onclick="if(window.confirm('�Ƿ�Ҫ����\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'�ı���'))  submitReject('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
				<td >
					<input type="button" name="btnVote" value="ͨ������" class="button"
						onclick="if(window.confirm('�Ƿ�Ҫͨ��\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'�ı���'))  submitPass('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
			</logic:equal>
			<logic:equal value="1" name="reportFile" property="status">
				<logic:equal value="0" name="reportFile" property="isAudit">
				<td class="label" width="15%">
					<b>������</b>
				</td>
				<td >
					<textarea cols="30" rows="3" id="<%="reportFile" + i%>"
						name="<%="reportFile" + i%>"></textarea>
				</td>
				<td width="15%">
					<input type="button" name="btnVote" value="���ر���" class="button"
						onclick="if(window.confirm('�Ƿ�Ҫ����\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'�ı���'))  submitReject3('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
				<td >
					<input type="button" name="btnVote" value="ͨ������" class="button"
						onclick="if(window.confirm('�Ƿ�Ҫͨ��\'<bean:write name="reportFile" property="acceptDeptName" scope="page"/>\'�ı���'))  submitPass3('<bean:write scope="session" name="sessionform" property="userid"/>','<bean:write name="reportFile" property="flowId" />','<%="reportFile" + i%>');" />
				</td>
				</logic:equal>
			</logic:equal>
		</tr>
</table>
</logic:iterate>
<% 
   //add by chenyuanshu ֻ��һ������û���ϴ�ʱ����ʾ�ϲ���ť
   //update by wangsixuan ���Ϊ���ϲ�����ʾ��ť
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
  <input type="submit" name="combine" onclick="showCombine()" value="�ϲ�����" class="button"/>    
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
        <b>ѡ��ϲ�ģ��</b>
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
                <b>ѡ���ļ�</b>
        </td>
        <td nowrap class="label">
               <b> ��������</b>
        </td>
        <td nowrap class="label">
               <b> �ļ���</b>
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
<%--    <html:button style="display: block;" onclick="return true;" value="�ϲ�ѡ��" styleClass="button"/>--%>
 	<html:button property="btncombine" onclick="combineCheck()" value="�ϲ�ѡ��" styleClass="button"/>
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
   			alert("��ѡ��ϲ�ģ�壡");
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
			alert("��ѡ�����������ϲ�����");
			return false;
		}
		document.forms[0].submit();

   }
</script>
