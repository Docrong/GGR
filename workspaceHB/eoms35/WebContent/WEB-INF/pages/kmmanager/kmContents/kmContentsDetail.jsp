<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>

<%
String operateType = StaticMethod.nullObject2String(request.getAttribute("operateType"));
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String operateUserId = sessionform.getUserid();
if(operateType.indexOf("R")==-1&&!"admin".equals(operateUserId)){
%>
	<caption>
		<div class="header center">您没有查看权限</div>
	</caption>
<%
}else{
%>

<html:form action="/kmContentsOpinions.do?method=save" styleId="kmContentsOpinionForm" method="post"> 
<div id="info-page">
  <!-- 查看内容信息 -->
  <div id="content-info" class="tabContent">
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<!-- 知详细信息 -->
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="kmContents.detail.title"/></div>
	</caption>
	
	<!-- 定义知识内容变量 -->
	<c:set var="KmContentsMap" scope="page" value="${KmContents}"/>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.tableId" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.TABLE_ID}" beanId="kmTableGeneralDao" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.themeId" />
		</td>
		<td class="content">
		  	<eoms:id2nameDB id="${KmContentsMap.THEME_ID}" beanId="kmTableThemeDao" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.rolestrFlag" />			
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="rolestrFlag" itemId="${KmContentsMap.ROLESTR_FLAG}" beanId="id2nameXML" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.levelFlag" />			
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="levelFlag" itemId="${KmContentsMap.LEVEL_FLAG}" beanId="id2nameXML" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.createUser" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.CREATE_USER}" beanId="tawSystemUserDao" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.createDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.CREATE_DEPT}" beanId="tawSystemDeptDao" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="kmContents.contentTitle" />			
		</td>
		<td class="content" colspan="3">
			${KmContentsMap.CONTENT_TITLE}
		</td>
	</tr>
	
	<!-- 判断修改人是否为空 -->
	<c:if test="${not empty KmContentsMap.MODIFY_USER}">
	<tr>
		<td class="label">
			<fmt:message key="kmContents.modifyUser" />			
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.MODIFY_USER}" beanId="tawSystemUserDao" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.modifyDept" />			
		</td>
		<td class="content">
			<eoms:id2nameDB id="${KmContentsMap.MODIFY_DEPT}" beanId="tawSystemDeptDao" />
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="kmContents.modifyTime" />			
		</td>
		<td class="content" colspan="3">
			${KmContentsMap.MODIFY_TIME}
		</td>
	</tr>
	</c:if>
	
	
	<tr>
		<td class="label">
			<fmt:message key="kmContents.contentStatus" />			
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="contentStatus" itemId="${KmContentsMap.CONTENT_STATUS}" beanId="id2nameXML" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.auditFlag" />			
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${KmContentsMap.AUDIT_FLAG}" beanId="id2nameXML" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="kmContents.isBest" />			
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${KmContentsMap.IS_BEST}" beanId="id2nameXML" />
		</td>

		<td class="label">
			<fmt:message key="kmContents.isPublic" />			
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${KmContentsMap.IS_PUBLIC}" beanId="id2nameXML" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			知识评价		
		</td>	
		<td class="content" colspan="3">
			<fmt:message key="kmContents.gradeOne" />   ${KmContentsMap.GRADE_ONE}   次 <b>|</b>
			<fmt:message key="kmContents.gradeTwo" />   ${KmContentsMap.GRADE_TWO}   次 <b>|</b>
			<fmt:message key="kmContents.gradeThree" /> ${KmContentsMap.GRADE_THREE} 次 <b>|</b>
			<fmt:message key="kmContents.gradeFour" />  ${KmContentsMap.GRADE_FOUR}  次 <b>|</b>
			<fmt:message key="kmContents.gradeFive" />  ${KmContentsMap.GRADE_FIVE}  次
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.readCount" />			
		</td>
		<td class="content">
			${KmContentsMap.READ_COUNT}
		</td>

		<td class="label">
			<fmt:message key="kmContents.useCount" />			
		</td>
		<td class="content">
			${KmContentsMap.USE_COUNT}
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="kmContents.modifyCount" />			
		</td>
		<td class="content" colspan="3">
			${KmContentsMap.MODIFY_COUNT}
		</td>
	</tr>

	<tr>
	<%--
		modify by mios:
		mark有4个状态： 0：半行， 1：整行， 2：左半行，右为空行， 3：右半行，左为空行,通过mark控制是否输出空列
		flag用来控制是否换行，每输出两列后，flag+1 
	--%>
	<c:set var="flag" value="${0}"/>
	<c:forEach items="${KmTableColumnList}" var="item" varStatus="status">
	
	<c:if test="${item.mark == 3}"> <%-- 有右半行，填充左半行为空行--%>
	  <td class="label"></td>
	  <td class="content" role="4"></td>
	  <c:set var="flag" value="${flag+1}"/>
	</c:if>
	
	<c:if test="${item.isOpen == 1}">
	  <td class="label">
	  	${item.colChname}<c:if test="${item.isNullable == 0}">&nbsp;<font color='red'>*</font></c:if>
	  </td>
	  <td class="content" <c:if test="${item.mark == 1}">colspan="3"</c:if> role="${item.mark}">
		    <c:choose>
		    <c:when test="${item.colDictType == 0}">
		        <c:choose>
		        <c:when test="${item.colType == 1}">
		        <!-- 不绑定_普通文本 -->
		        <c:out value="${KmContentsMap[item.colName]}" />
		        </c:when>

		        <c:when test="${item.colType == 2}">
		        <!-- 不绑定_大文本域 -->
		        <textarea name="props(${item.colName})" cols="50" id="${item.colName}" class="textarea max" readonly="readonly"><c:out value="${KmContentsMap[item.colName]}" /></textarea>										
		        </c:when>
		        
		        <c:when test="${item.colType == 3}">
		        <!-- 不绑定_数字类型 -->
		        <c:out value="${KmContentsMap[item.colName]}" />
		        </c:when>

		        <c:when test="${item.colType == 4}">
		        <!-- 不绑定_日期时间 -->
		        <c:out value="${KmContentsMap[item.colName]}" />
		        </c:when>
		        
		        <c:when test="${item.colType == 7}">
		        <!-- 不绑定_附件上传 -->
		        <eoms:attachment name="KmContents" property="${item.colName}" scope="request" idField="${item.colName}" appCode="kmmanager" viewFlag="Y"/>			
		        </c:when>
		        </c:choose>
		    </c:when>

		    <c:when test="${item.colDictType == 1}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		        <!-- 普通字典_单选字段 -->
		        <eoms:id2nameDB id="${KmContentsMap[item.colName]}" beanId="ItawSystemDictTypeDao" />
			    </c:when>
			    
			    <c:when test="${item.colType == 6}">
			    <!-- 普通字典_多选字段 -->
			    <eoms:id2nameDB id="${KmContentsMap[item.colName]}" beanId="ItawSystemDictTypeDao" />
			    </c:when>
			    </c:choose>
		    </c:when>

		    <c:when test="${item.colDictType == 2}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		        <!-- 知识字典_单选字段 -->
		        <eoms:id2nameDB id="${KmContentsMap[item.colName]}" beanId="ItawSystemDictTypeDao" />
			    </c:when>
			    
			    <c:when test="${item.colType == 6}">
			    <!-- 知识字典_多选字段 -->
			    <eoms:id2nameDB id="${KmContentsMap[item.colName]}" beanId="ItawSystemDictTypeDao" />
			    </c:when>
			    </c:choose>		    
		    </c:when>

		    <c:when test="${item.colDictType == 3}">
		        <c:choose>
		        <c:when test="${item.colType == 5}">
		        <!-- 文件字典_单选字段 -->
		        <eoms:id2nameDB id="${KmContentsMap[item.colName]}" beanId="ItawSystemDictTypeDao" />
			    </c:when>
			    
			    <c:when test="${item.colType == 6}">
			    <!-- 文件字典_多选字段 -->
			    <eoms:id2nameDB id="${KmContentsMap[item.colName]}" beanId="ItawSystemDictTypeDao" />
			    </c:when>
			    </c:choose>		    
		    </c:when>
		    </c:choose>
		</td>
	</c:if>	

	<c:if test="${item.mark != 1}"> <%-- 半行--%>
	  <c:set var="flag" value="${flag+1}"/>
	</c:if>
		 
	 <c:if test="${item.mark == 2}"> <%-- 有左半行，填充右半行为空行--%>
	  <td class="label"></td>
	  <td class="content" role="4"></td>
	  <c:set var="flag" value="${flag+1}"/>
	</c:if>
	
	<c:if test="${item.mark == 1 || flag>=2}"> <%-- 整行，可以换行--%>
	  </tr>
	  ${!status.last?"<tr>":""}
	  <c:set var="flag" value="${0}"/>
	</c:if>
	
	</c:forEach>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContents.contentKeys" />
		</td>
		<td class="content" colspan="3">
		    ${KmContentsMap.CONTENT_KEYS}
		</td>
	</tr>
</table>

<input type="hidden" id="contentId" name="contentId" value="${KmContentsMap.ID}" />
<input type="hidden" id="tableId"   name="tableId"   value="${KmContentsMap.TABLE_ID}" />
<input type="hidden" id="themeId"   name="themeId"   value="${KmContentsMap.THEME_ID}" />

<c:if test="${KmContentsMap.CREATE_USER == sessionScope.sessionform.userid}">		
<br>
<!-- 修改或删除知识 -->
	<%
//	if(operateType.indexOf("W")!=-1||"admin".equals(operateUserId)){
	%>
<table>
	<tr>
		<td>
		    <input type="button" class="btn" value="修改" onclick="javascript:onSubmitEdit();"/>&nbsp;
		    
		    <c:if test="${KmContentsMap.CONTENT_STATUS != 3}"><!-- 可以将其它状态的知识失效 -->
		    <input type="button" class="btn" value="失效" onclick="javascript:onSubmitOver();"/>&nbsp;
		    </c:if>
		    
		    <c:if test="${KmContentsMap.CONTENT_STATUS == 3}"><!-- 可以删除失效的知识 -->
		    <input type="button" class="btn" value="删除" onclick="javascript:onSubmitDele();"/>&nbsp;
		    </c:if>
		</td>
	</tr>
</table>
	<%
//	}
	%>
</c:if>

<!-- 对知识进行评价:zu作者不能对自己的知识进行评价 -->
<c:if test="${KmContentsMap.CREATE_USER != sessionScope.sessionform.userid}">			   
<br>
<table class="formTable">
	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.opinionGrade" />
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="opinionGrade" isQuery="false" 
			           defaultId="" selectId="opinionGrade" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择评论星级(字典)...'"/>	
		</td>
		<td class="label">
			<fmt:message key="kmContentsOpinion.isEdit" />
		</td>
		<td class="content">
		    <eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false" 
			           defaultId="" selectId="isEdit" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'是否提出修改(字典)...'"/>	
		</td>		
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmContentsOpinion.opinionContent" />
		</td>
		<td class="content" colspan="3">
			<textarea name="opinionContent" cols="50" id="opinionContent" class="textarea max" ></textarea><p/>
			<input type="button" class="btn" value="提交知识评论" onclick="javascript:onSubmit();"/>&nbsp;
		</td>
    </tr>

    <input type="hidden" name="isRefedit" value="0" /> <!-- 修改建议是否被采纳 -->
    <input type="hidden" name="isDeleted" value="0" /> <!-- 是否删除 -->
</table>
</c:if>

</fmt:bundle>

<br>
</div>

  <!-- 查看知识评价信息 -->
  <div id="count-info" class="tabContent">
    <display:table name="kmContentsOpinionList" cellspacing="0" cellpadding="0" class="table kmContentsOpinionList" 
        id="kmContentsOpinionList" export="false" sort="list" partialList="true" size="resultSize" 
        pagesize="${pageSize}" requestURI="${app}/kmContentsOpinion/kmContentsOpinions.do?method=search">
		
		<display:column headerClass="sortable" title="评论人">
			<eoms:id2nameDB id="${kmContentsOpinionList.createUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column headerClass="sortable" title="评论部门">
			<eoms:id2nameDB id="${kmContentsOpinionList.createDept}" beanId="tawSystemDeptDao" />
		</display:column>

	    <display:column property="createTime" 
			headerClass="sortable" title="评论时间"  paramId="id" paramProperty="id"  format="{0,date,yyyy-MM-dd HH:mm:ss}" />
		
		<display:column headerClass="sortable" title="评价星级">
		    <eoms:dict key="dict-kmmanager" dictId="opinionGrade" itemId="${kmContentsOpinionList.opinionGrade}" beanId="id2nameXML" />
		</display:column>
		
		<display:column headerClass="sortable" title="是否提出修改">
		    <eoms:dict key="dict-kmmanager" dictId="isOrNot" itemId="${kmContentsOpinionList.isEdit}" beanId="id2nameXML" />		    
		</display:column>
		
	    <display:column property="opinionContent" 
			headerClass="sortable" title="评论内容"  paramId="id" paramProperty="id"/>

	    <display:setProperty name="paging.banner.item_name" value="kmContentsOpinion" />
	    <display:setProperty name="paging.banner.items_name" value="kmContentsOpinions" />

	</display:table>
  </div>

  <!-- 查看知识版本信息 -->
  <div id="history-info" class="tabContent">
    <display:table name="KmContentsHistoryList" cellspacing="0" cellpadding="0"  class="table KmContentsHistoryList"  
        id="KmContentsHistoryList" export="false" sort="list" partialList="true" size="resultSize">

	    <display:column property="modifyTime" headerClass="sortable" title="修改时间" paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column headerClass="sortable" title="修改作者">
			<eoms:id2nameDB id="${KmContentsHistoryList.modifyUser}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column headerClass="sortable" title="修改部门">
			<eoms:id2nameDB id="${KmContentsHistoryList.modifyDept}" beanId="tawSystemDeptDao" />
		</display:column>
		<display:column headerClass="sortable" title="版本号">
			${KmContentsHistoryList.version}.0
		</display:column>
		
		<display:column title="查看" headerClass="imageColumn">
		    <a href="#" onClick="javascript:var id = '${KmContentsHistoryList.id }';
		                        var tableId = '${KmContentsHistoryList.tableId}';
		                        var themeId = '${KmContentsHistoryList.themeId}';
		                        var version = '${KmContentsHistoryList.version}';
		                        var url='${app}/kmmanager/kmContentss.do?method=detailHistory';
		                        url = url + '&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId + '&VERSION=' + version;
		                        window.open(url);">
		       <img src="${app}/images/icons/search.gif"/></a>
		</display:column>
	</display:table>
  </div>

  <!-- 查看知识审核信息 -->
  <div id="audit-info" class="tabContent">
    <display:table name="KmAuditColumnList" cellspacing="0" cellpadding="0"  class="table KmAuditColumnList"  
        id="KmAuditColumnList" export="false" sort="list" partialList="true" size="resultSize">

	    <display:column property="createTime" sortable="false" 
			headerClass="sortable" title="操作时间"  paramId="id" paramProperty="id" format="{0,date,yyyy-MM-dd HH:mm:ss}" />

		<display:column headerClass="sortable" title="审核人">
			<eoms:id2nameDB id="${KmAuditColumnList.operateId}" beanId="tawSystemUserDao" />
		</display:column>

		<display:column headerClass="sortable" title="审核结果">	
		<eoms:dict key="dict-kmmanager" dictId="auditResult" itemId="${KmAuditColumnList.auditResult}" beanId="id2nameXML" />		
		</display:column>
		
	    <display:column property="remark" sortable="false" 
			headerClass="sortable" title="审核内容"  paramId="id" paramProperty="id"/>			
 	</display:table>
  </div>
  
  <div id="comments-info" class="tabContent">	 
  </div>

</div>
</html:form>

<script type="text/javascript">
	var readTree;
	Ext.onReady(function(){
			var tabs = new Ext.TabPanel('info-page');
			tabs.addTab('content-info', '内容信息 ');
        	tabs.addTab('count-info', '知识评价信息 ');
        	tabs.addTab('history-info', '知识版本信息 ');
        	tabs.addTab('audit-info', '知识审核信息 ');
    		tabs.activate(0);
	});
	
	//修改
	function onSubmitEdit(){
	    var id = document.getElementById("contentId").value;
	    var tableId = document.getElementById("tableId").value;
	    var themeId = document.getElementById("themeId").value;
	    var url='${app}/kmmanager/kmContentss.do?method=edit&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
	    window.location.href(url);
	    //alert(url);
    }
    //失效
	function onSubmitOver(){
	    var id = document.getElementById("contentId").value;
	    var tableId = document.getElementById("tableId").value;
	    var themeId = document.getElementById("themeId").value;
	    var url='${app}/kmmanager/kmContentss.do?method=over&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
	    window.location.href(url);
	    //alert(url);	
    }
    //删除
    function onSubmitDele(){
	    var id = document.getElementById("contentId").value;
	    var tableId = document.getElementById("tableId").value;
	    var themeId = document.getElementById("themeId").value;
	    var url='${app}/kmmanager/kmContentss.do?method=remove&ID=' + id + '&TABLE_ID=' + tableId + '&THEME_ID=' + themeId;
	    window.location.href(url);
	    //alert(url);    
    }
 
	//提交评论
	function onSubmit(){
    	if(document.forms[0].opinionGrade.value==""){
    		alert('请选择评价星级');
    		return false; 
    	}
    	if(document.forms[0].isEdit.value==""){
    		alert('请选择是否提出修改');
    		return false; 
    	}     	
    	if(document.forms[0].opinionContent.value==""){
    		alert('请填写评价内容');
    		return false; 
    	}
       // document.forms[0].historyType.value="1";
        document.forms[0].submit();
        return true;
    }
</script>
<%
}
%>
<%@ include file="/common/footer_eoms.jsp"%>
