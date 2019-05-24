<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.km.kmAuditerStep.model.KmAuditerStep"%>
<%@ page import="com.boco.eoms.km.file.model.KmFileHis"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<%
String operateType = StaticMethod.nullObject2String(request.getAttribute("operateType"));
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String operateUserId = sessionform.getUserid();
%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<script type="text/javascript">
	var readTree;
	Ext.onReady(function(){
			var tabs = new Ext.TabPanel('info-page');
        	tabs.addTab('file-info', '查看附件信息 ');
        	tabs.addTab('history-info', '查看附件版本信息 ');
        	tabs.addTab('audit-info', '查看附件审核信息 ');
    		tabs.activate(0);
	});
</script>
<title><fmt:message key="kmFile.title" /></title>
<content tag="heading"><b>

</content>
  <div id="info-page">
  <div id="file-info" class="tabContent">
  <table class="formTable">
    <caption>
      <fmt:message key="kmFile.fileName" />:
      ${kmFileForm.fileName }
    </caption>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.nodeId" />:
      </td>
      <td class="content">
       <eoms:id2nameDB id="${kmFileForm.nodeId}" beanId="kmFileTreeDao" />        
      </td>
    </tr>    
    <tr>
      <td class="label">
        <fmt:message key="kmFile.userId" />
      </td>
      <td class="content max">
        <eoms:id2nameDB beanId="tawSystemUserDao" id="${kmFileForm.userId}"/>
      </td>
    </tr>
        <tr>
      <td class="label">
        <fmt:message key="kmFile.deptId" />
      </td>
      <td class="content max">
        <eoms:id2nameDB beanId="tawSystemDeptDao" id="${kmFileForm.deptId}"/>
      </td>
    </tr>
        <tr>
      <td class="label">
        <fmt:message key="kmFile.phone" />
      </td>
      <td class="content max">
        ${kmFileForm.phone }
      </td>
    </tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.uploadTime" />
      </td>
      <td class="content max">
        ${kmFileForm.uploadTime }
      </td>
    </tr>
    <tr>
		<td class="label">
			状态	
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${kmFileForm.state}" beanId="id2nameXML" />
	</td>
	</tr>
	    <tr>
		<td class="label">
			版本号	
		</td>
		<td class="content">
			 ${kmFileForm.version }.0
	</td>
	</tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileSize" />
      </td>
      <td class="content">
        ${kmFileForm.fileSize}&nbsp;KB
      </td>
    </tr>
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileGrade" />
      </td>
      <td class="content">
        <eoms:dict key="dict-kmmanager" dictId="fileGrade" itemId="${kmFileForm.fileGrade}" beanId="id2nameXML" />
      </td>
    </tr>    
    <tr>
      <td class="label">
        <fmt:message key="kmFile.clickCount" />
      </td>
      <td class="content">
        ${kmFileForm.clickCount}
      </td>
    </tr>  
    <tr>
      <td class="label">
        <fmt:message key="kmFile.keywords" />
      </td>
      <td class="content">
        ${kmFileForm.keywords}
      </td>
    </tr>   
    <tr>
      <td class="label">
        <fmt:message key="kmFile.fileAbstract" />
      </td>
      <td class="content">
        ${kmFileForm.fileAbstract}
      </td>
    </tr>
    <tr>
      <td class="label">
      	<fmt:message key="kmFile.download" />
      </td>
      <td class="content">
      	<%
	if(operateType.indexOf("R")!=-1||"admin".equals(operateUserId)){
	%>
        <a href="${app}/kmmanager/files.do?method=download&id=${kmFileForm.id}&nodeId=${kmFileForm.nodeId}" style="text-decoration:none">${kmFileForm.fileName}</a>
    <%
	 }else {
	%>
        ${kmFileForm.fileName}
    <%
	 }
	%>
      </td>
    </tr>
  </table>
  </div>
  <div id="audit-info" class="tabContent">
    <table class="formTable">
    <tr>
    <td>操作时间</td>
    <td>审核人</td>
    <td>审核结果</td>
    <td>审核内容</td>
    </tr>
<%
List kmAuditColumnList = (List)request.getAttribute("kmAuditColumnList");
for(int i=0;i<kmAuditColumnList.size();i++){
	KmAuditerStep kmAuditerStep = (KmAuditerStep)kmAuditColumnList.get(i);
	%>
    <tr>
    <td><%=kmAuditerStep.getCreateTime() %></td>
    <td><eoms:id2nameDB id="<%=kmAuditerStep.getOperateId() %>" beanId="tawSystemUserDao" /></td>
    <%
    if(kmAuditerStep.getAuditResult()==null||kmAuditerStep.getAuditResult().equals("")){
    %>
    <td>重新提交</td>
    <%
    }else{
    %>
    <td><eoms:dict key="dict-kmmanager" dictId="auditResult" itemId="<%=kmAuditerStep.getAuditResult() %>" beanId="id2nameXML" /></td>
    <%
    }
    if(kmAuditerStep.getRemark()==null){   
    %>
    <td></td>
    </tr>
    <%
    }else{
    %>
    <td><%=kmAuditerStep.getRemark() %></td>
    </tr>
	<%
    }
}
%>
  </table>
  </div>
  
  
  <!-- 查看知识版本信息 -->
  <div id="history-info" class="tabContent">
  
      <table class="formTable">
    <tr>
    <td>操作时间</td>
    <td>修改人</td>
    <td>修改部门</td>
    <td>版本号</td>
    </tr>
<%
List kmFileHistoryList = (List)request.getAttribute("KmFileHistoryList");
for(int i=0;i<kmFileHistoryList.size();i++){
	KmFileHis kmFileHis = (KmFileHis)kmFileHistoryList.get(i);
	%>
    <tr>
    <td><%=kmFileHis.getUploadTime() %></td>
    <td><eoms:id2nameDB beanId="tawSystemUserDao" id="<%=kmFileHis.getUserId() %>"/></td>
    <td><eoms:id2nameDB beanId="tawSystemDeptDao" id="<%=kmFileHis.getDeptId() %>"/></td>
    <td><a href="#" onClick="javascript:var id = '<%=kmFileHis.getHisId() %>';
		                        var version = '<%=kmFileHis.getVersion() %>';
		                        var url='${app}/kmmanager/files.do?method=detailHistory';
		                        url = url + '&ID=' + id + '&VERSION=' + version;
		                        window.open(url);">
		                        <%=kmFileHis.getVersion() %>.0
		                        </a>
	</td>                      
    </tr>
	<%
}
%>
  </table>

  </div>
  
  
  
</div>
</fmt:bundle>
<%@ include file="/common/footer_eoms.jsp"%>
